package com.acme.x7y2.web.internal.settings.definition;

import com.acme.x7y2.web.internal.configuration.X7Y2WebPortletInstanceConfiguration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationBeanDeclaration.class)
public class X7Y2WebPortletInstanceConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return X7Y2WebPortletInstanceConfiguration.class;
	}

}