package com.acme.g8v3.internal.settings.definition;

import com.acme.g8v3.internal.configuration.G8V3AbleConfiguration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationBeanDeclaration.class)
public class G8V3AbleConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return G8V3AbleConfiguration.class;
	}

}