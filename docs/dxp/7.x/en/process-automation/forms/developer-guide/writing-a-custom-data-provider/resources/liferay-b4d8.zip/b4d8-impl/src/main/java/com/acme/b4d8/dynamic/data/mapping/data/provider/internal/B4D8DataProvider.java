package com.acme.b4d8.dynamic.data.mapping.data.provider.internal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.dynamic.data.mapping.data.provider.DDMDataProvider;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderException;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse;
import com.liferay.dynamic.data.mapping.data.provider.settings.DDMDataProviderSettingsProvider;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceService;

@Component(immediate = true, property = "ddm.data.provider.type=b4d8", service = DDMDataProvider.class)
public class B4D8DataProvider implements DDMDataProvider {

	@Override
	public DDMDataProviderResponse getData(DDMDataProviderRequest ddmDataProviderRequest)
			throws DDMDataProviderException {

//		DDMDataProviderResponse restResponse = ddmRESTDataProvider.getData(ddmDataProviderRequest);
		
		DDMDataProviderResponse.Builder builder = DDMDataProviderResponse.Builder.newBuilder();

		return builder.withOutput("id","value").build();
	}

	@Override
	public Class<?> getSettings() {
		return ddmRESTDataProviderSettingsProvider.getSettings();
	}

//	@Reference (target = "(ddm.data.provider.type=rest)")
//	protected DDMDataProvider ddmRESTDataProvider;

	@Reference
	protected DDMDataProviderInstanceService ddmDataProviderInstanceService;

	@Reference(target = "(ddm.data.provider.type=b4d8)")
	protected DDMDataProviderSettingsProvider ddmDataProviderSettingsProvider;

//	@Reference(target = "(ddm.data.provider.type=rest)")
//	protected DDMDataProviderSettingsProvider ddmRESTDataProviderSettingsProvider;
}
