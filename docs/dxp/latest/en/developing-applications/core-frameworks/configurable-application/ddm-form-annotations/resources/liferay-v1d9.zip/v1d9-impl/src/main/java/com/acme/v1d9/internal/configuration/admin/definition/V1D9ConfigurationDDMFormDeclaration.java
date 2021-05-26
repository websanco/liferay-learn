package com.acme.v1d9.internal.configuration.admin.definition;

import com.liferay.configuration.admin.definition.ConfigurationDDMFormDeclaration;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "configurationPid=com.acme.v1d9.internal.configuration.V1D9Configuration",
	service = ConfigurationDDMFormDeclaration.class
)
public class V1D9ConfigurationDDMFormDeclaration
	implements ConfigurationDDMFormDeclaration {

	@Override
	public Class<?> getDDMFormClass() {
		return V1D9ConfigurationForm.class;
	}

}