package com.acme.n2f3.web.internal.portlet;

import com.acme.n2f3.web.internal.configuration.N2F3WebConfiguration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import java.io.IOException;

import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	configurationPid = "com.acme.n2f3.web.internal.configuration.N2F3WebConfiguration",
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=N2F3 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class N2F3Portlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			_n2f3WebConfiguration =
				_configurationProvider.getCompanyConfiguration(
					N2F3WebConfiguration.class, companyId);
		}
		catch (ConfigurationException configurationException) {
			throw new PortletException(configurationException);
		}

		renderRequest.setAttribute(
			N2F3WebConfiguration.class.getName(), _n2f3WebConfiguration);

		super.render(renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_n2f3WebConfiguration = ConfigurableUtil.createConfigurable(
			N2F3WebConfiguration.class, properties);
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	private N2F3WebConfiguration _n2f3WebConfiguration;

}