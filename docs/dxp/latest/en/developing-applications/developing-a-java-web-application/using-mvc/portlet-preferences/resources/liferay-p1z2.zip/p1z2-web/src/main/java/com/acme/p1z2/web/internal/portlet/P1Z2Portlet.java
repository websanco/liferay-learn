package com.acme.p1z2.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=P1Z2 Portlet",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=P1Z2Portlet",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class P1Z2Portlet extends MVCPortlet {
}