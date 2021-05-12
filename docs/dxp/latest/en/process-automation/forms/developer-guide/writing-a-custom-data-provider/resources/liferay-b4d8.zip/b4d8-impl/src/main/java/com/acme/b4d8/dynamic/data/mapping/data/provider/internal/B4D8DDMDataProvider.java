package com.acme.b4d8.dynamic.data.mapping.data.provider.internal;

import com.liferay.dynamic.data.mapping.data.provider.DDMDataProvider;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderException;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderInstanceSettings;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderOutputParametersSettings;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse;
import com.liferay.dynamic.data.mapping.data.provider.settings.DDMDataProviderSettingsProvider;
import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceService;
import com.liferay.portal.kernel.security.xml.SecureXMLFactoryProviderUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.Validator;

import java.io.StringReader;

import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;

@Component(
	immediate = true, property = "ddm.data.provider.type=b4d8",
	service = DDMDataProvider.class
)
public class B4D8DDMDataProvider implements DDMDataProvider {

	@Override
	public DDMDataProviderResponse getData(
			DDMDataProviderRequest ddmDataProviderRequest)
		throws DDMDataProviderException {

		try {
			return doGetData(ddmDataProviderRequest);
		}
		catch (Exception exception) {
			throw new DDMDataProviderException(exception);
		}
	}

	@Override
	public Class<?> getSettings() {
		return ddmDataProviderSettingsProvider.getSettings();
	}

	protected DDMDataProviderResponse createDDMDataProviderResponse(
			B4D8DDMDataProviderSettings b4d8DataProviderSettings,
			Document document)
		throws Exception {

		DDMDataProviderResponse.Builder builder =
			DDMDataProviderResponse.Builder.newBuilder();

		for (DDMDataProviderOutputParametersSettings outputParameterSettings :
				b4d8DataProviderSettings.outputParameters()) {

			String id = outputParameterSettings.outputParameterId();
			String type = outputParameterSettings.outputParameterType();

			NodeList nodeList = document.getElementsByTagName(
				outputParameterSettings.outputParameterPath());

			if (Objects.equals(type, "list")) {
				List<KeyValuePair> keyValuePairs = new ArrayList<>();

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);

					String nodeTextContent = node.getTextContent();

					keyValuePairs.add(
						new KeyValuePair(nodeTextContent, nodeTextContent));
				}

				builder = builder.withOutput(id, keyValuePairs);
			}
			else if (Objects.equals(type, "number")) {
				Node node = nodeList.item(0);

				NumberFormat numberFormat = NumberFormat.getInstance();

				builder = builder.withOutput(
					id, numberFormat.parse(node.getTextContent()));
			}
			else if (Objects.equals(type, "text")) {
				Node node = nodeList.item(0);

				String nodeTextContent = node.getTextContent();

				builder = builder.withOutput(id, nodeTextContent);
			}
		}

		return builder.build();
	}

	protected DDMDataProviderResponse doGetData(
			DDMDataProviderRequest ddmDataProviderRequest)
		throws Exception {

		Optional<DDMDataProviderInstance> ddmDataProviderInstance =
			fetchDDMDataProviderInstance(
				ddmDataProviderRequest.getDDMDataProviderId());

		B4D8DDMDataProviderSettings b4d8DataProviderSettings =
			ddmDataProviderInstanceSettings.getSettings(
				ddmDataProviderInstance.get(),
				B4D8DDMDataProviderSettings.class);

		Http.Options options = new Http.Options();

		options.setLocation(
			"https://api.geodatasource.com/cities?" +
				"key=LAOOBDZVQ5Z9HHYC4OCXHTGZGQLENMNA" +
					"&format=xml&lat=37.3861&lng=-122.084");

		String responseJSON = HttpUtil.URLtoString(options);

		Document document = _convertXMLStringToDocument(responseJSON);

		return createDDMDataProviderResponse(
			b4d8DataProviderSettings, document);
	}

	protected Optional<DDMDataProviderInstance> fetchDDMDataProviderInstance(
			String ddmDataProviderInstanceId)
		throws Exception {

		DDMDataProviderInstance ddmDataProviderInstance =
			ddmDataProviderInstanceService.fetchDataProviderInstanceByUuid(
				ddmDataProviderInstanceId);

		if ((ddmDataProviderInstance == null) &&
			Validator.isNumber(ddmDataProviderInstanceId)) {

			ddmDataProviderInstance =
				ddmDataProviderInstanceService.fetchDataProviderInstance(
					Long.valueOf(ddmDataProviderInstanceId));
		}

		return Optional.ofNullable(ddmDataProviderInstance);
	}

	@Reference
	protected DDMDataProviderInstanceService ddmDataProviderInstanceService;

	@Reference
	protected DDMDataProviderInstanceSettings ddmDataProviderInstanceSettings;

	@Reference(target = "(ddm.data.provider.type=b4d8)")
	protected DDMDataProviderSettingsProvider ddmDataProviderSettingsProvider;

	private Document _convertXMLStringToDocument(String xmlString)
		throws Exception {

		DocumentBuilderFactory documentBuilderFactory =
			SecureXMLFactoryProviderUtil.newDocumentBuilderFactory();

		DocumentBuilder documentBuilder =
			documentBuilderFactory.newDocumentBuilder();

		return documentBuilder.parse(
			new InputSource(new StringReader(xmlString)));
	}

}