package com.acme.n2f3.web.internal.settings.definition;

import com.acme.n2f3.web.internal.configuration.N2F3WebConfiguration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationBeanDeclaration.class)
public class N2F3WebConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return N2F3WebConfiguration.class;
	}

}