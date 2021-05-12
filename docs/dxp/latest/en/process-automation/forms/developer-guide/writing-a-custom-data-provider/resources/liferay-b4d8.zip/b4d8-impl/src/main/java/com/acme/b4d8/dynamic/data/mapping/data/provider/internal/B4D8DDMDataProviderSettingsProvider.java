package com.acme.b4d8.dynamic.data.mapping.data.provider.internal;

import com.liferay.dynamic.data.mapping.data.provider.settings.DDMDataProviderSettingsProvider;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, property = "ddm.data.provider.type=b4d8",
	service = DDMDataProviderSettingsProvider.class
)
public class B4D8DDMDataProviderSettingsProvider
	implements DDMDataProviderSettingsProvider {

	@Override
	public Class<?> getSettings() {
		return B4D8DDMDataProviderSettings.class;
	}

}