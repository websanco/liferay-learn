package com.acme.u2g5.web.internal.settings.definition;

import com.acme.u2g5.web.internal.configuration.U2G5WebConfiguration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationBeanDeclaration.class)
public class U2G5WebConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return U2G5WebConfiguration.class;
	}

}