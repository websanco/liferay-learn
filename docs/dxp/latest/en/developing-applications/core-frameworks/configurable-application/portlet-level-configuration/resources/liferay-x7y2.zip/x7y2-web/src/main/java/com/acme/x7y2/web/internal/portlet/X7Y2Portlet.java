package com.acme.x7y2.web.internal.portlet;

import com.acme.x7y2.web.internal.configuration.X7Y2PortletInstanceConfiguration;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	configurationPid = "com.acme.x7y2.web.internal.configuration.X7Y2PortletInstanceConfiguration",
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=X7Y2 Portlet",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=com_acme_x7y2_web_internal_portlet_X7Y2Portlet"
	},
	service = Portlet.class
)
public class X7Y2Portlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		try {
			X7Y2PortletInstanceConfiguration
				x7y2WebPortletInstanceConfiguration =
					portletDisplay.getPortletInstanceConfiguration(
						X7Y2PortletInstanceConfiguration.class);

			renderRequest.setAttribute(
				X7Y2PortletInstanceConfiguration.class.getName(),
				x7y2WebPortletInstanceConfiguration);
		}
		catch (ConfigurationException configurationException) {
			throw new PortletException(configurationException);
		}

		super.render(renderRequest, renderResponse);
	}

}