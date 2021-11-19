package com.acme.p8v5.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=P8V5 Portlet",
		"javax.portlet.init-param.view-template=/p8v5/view.jsp",
		"javax.portlet.name=com_acme_p8v5_web_internal_portlet_P8V5Portlet"
	},
	service = Portlet.class
)
public class P8V5Portlet extends MVCPortlet {
}