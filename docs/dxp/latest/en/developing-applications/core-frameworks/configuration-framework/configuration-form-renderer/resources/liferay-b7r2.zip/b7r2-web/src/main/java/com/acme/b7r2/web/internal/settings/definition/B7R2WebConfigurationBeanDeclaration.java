package com.acme.b7r2.web.internal.settings.definition;

import com.acme.b7r2.web.internal.configuration.B7R2WebConfiguration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationBeanDeclaration.class)
public class B7R2WebConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return B7R2WebConfiguration.class;
	}

}