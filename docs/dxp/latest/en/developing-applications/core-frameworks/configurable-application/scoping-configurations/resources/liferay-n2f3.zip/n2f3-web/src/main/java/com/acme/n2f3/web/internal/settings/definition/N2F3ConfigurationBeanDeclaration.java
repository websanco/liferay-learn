package com.acme.n2f3.web.internal.settings.definition;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import com.acme.n2f3.web.internal.configuration.N2F3WebConfiguration;


@Component
public class N2F3ConfigurationBeanDeclaration implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return N2F3WebConfiguration.class;
	}

}