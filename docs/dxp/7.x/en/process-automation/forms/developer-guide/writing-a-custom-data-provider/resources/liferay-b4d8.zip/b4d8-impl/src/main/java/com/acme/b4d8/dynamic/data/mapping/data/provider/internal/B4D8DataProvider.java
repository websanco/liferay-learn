package com.acme.b4d8.dynamic.data.mapping.data.provider.internal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.dynamic.data.mapping.data.provider.DDMDataProvider;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderException;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderParameterSettings;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseStatus;

@Component(
	immediate = true, property = "ddm.data.provider.type=b4d8",
	service = DDMDataProvider.class
)
public class B4D8DataProvider implements DDMDataProvider {

	@Override
	public DDMDataProviderResponse getData(DDMDataProviderRequest ddmDataProviderRequest)
			throws DDMDataProviderException {

		DDMDataProviderResponse.Builder builder = DDMDataProviderResponse.Builder.newBuilder();

		return builder.withStatus(DDMDataProviderResponseStatus.SERVICE_UNAVAILABLE).build();
	}

	@Override
	public Class<?> getSettings() {
		throw new UnsupportedOperationException();
	}
	
//	@Reference(target = "(ddm.data.provider.type=b4d8)")
//	private DDMDataProviderParameterSettings ddmDataProviderParameterSettings;
}
