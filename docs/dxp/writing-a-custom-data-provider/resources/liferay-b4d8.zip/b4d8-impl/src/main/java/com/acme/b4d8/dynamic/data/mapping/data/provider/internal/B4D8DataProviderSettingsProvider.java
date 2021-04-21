package com.acme.b4d8.dynamic.data.mapping.data.provider.internal;

import org.osgi.service.component.annotations.Component;

import com.liferay.dynamic.data.mapping.data.provider.settings.DDMDataProviderSettingsProvider;

@Component(
	immediate = true, property = "ddm.data.provider.type=b4d8",
	service = DDMDataProviderSettingsProvider.class
)
public class B4D8DataProviderSettingsProvider
	implements DDMDataProviderSettingsProvider {

	@Override
	public Class<?> getSettings() {
		return B4D8DataProviderSettings.class;
	}

}
