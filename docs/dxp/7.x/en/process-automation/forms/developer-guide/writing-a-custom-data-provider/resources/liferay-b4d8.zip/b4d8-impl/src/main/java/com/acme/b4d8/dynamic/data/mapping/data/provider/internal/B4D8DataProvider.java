package com.acme.b4d8.dynamic.data.mapping.data.provider.internal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.dynamic.data.mapping.data.provider.DDMDataProvider;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderException;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseStatus;
import com.liferay.dynamic.data.mapping.data.provider.settings.DDMDataProviderSettingsProvider;

@Component(
	immediate = true, property = "ddm.data.provider.type=b4d8",
	service = DDMDataProvider.class
)
public class B4D8DataProvider implements DDMDataProvider {

	@Override
	public DDMDataProviderResponse getData(DDMDataProviderRequest ddmDataProviderRequest)
			throws DDMDataProviderException {

		DDMDataProviderResponse.Builder builder = DDMDataProviderResponse.Builder.newBuilder();
		
		System.out.println("hi from the B4D8 data provider, beep boop");
		
		return builder.withOutput("hello", builder).withStatus(DDMDataProviderResponseStatus.OK).build();
	}

	@Override
	public Class<?> getSettings() {
		return ddmDataProviderSettingsProvider.getSettings();
	}
	
	@Reference(target = "(ddm.data.provider.type=b4d8)")
	protected DDMDataProviderSettingsProvider ddmDataProviderSettingsProvider;
}
