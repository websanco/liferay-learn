package com.acme.p1z2.web.internal.portlet.action;

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
	property = "javax.portlet.name=P1Z2Portlet",
	service = ConfigurationAction.class
)
public class P1Z2ConfigurationAction extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String preference = ParamUtil.getString(actionRequest, "preference");

		setPreference(actionRequest, "preference", preference);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

}