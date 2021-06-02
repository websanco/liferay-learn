package com.acme.n2f3.web.internal.configuration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition.Scope;

public class N2F3WebConfigurationProvider {

	private N2F3WebConfiguration n2f3WebConfiguration;
	@Activate
	@Modified
	protected void configProvider() {

		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			this.n2f3WebConfiguration = _configurationProvider.getCompanyConfiguration(N2F3WebConfiguration.class, companyId);
		} catch (ConfigurationException e) {

		}
	}

	@Reference
	private ConfigurationProvider _configurationProvider;
}