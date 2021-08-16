package com.acme.x7y2.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	property = "javax.portlet.name=com_acme_x7y2_web_internal_portlet_X7Y2Portlet",
	service = ConfigurationAction.class
)
public class X7Y2WebPortletInstanceConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		setPreference(
			actionRequest, "color",
			ParamUtil.getString(actionRequest, "color"));

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

}