package com.acme.g8v3.internal.settings.definition;

import com.acme.g8v3.internal.configuration.G8V3BakerConfiguration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component
public class G8V3BakerConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return G8V3BakerConfiguration.class;
	}

}