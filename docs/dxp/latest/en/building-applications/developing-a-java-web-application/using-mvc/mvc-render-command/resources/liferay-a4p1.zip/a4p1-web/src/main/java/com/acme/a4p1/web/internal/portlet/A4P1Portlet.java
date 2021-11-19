package com.acme.a4p1.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=A4P1 Portlet",
		"javax.portlet.init-param.view-template=/a4p1/able.jsp",
		"javax.portlet.name=com_acme_a4p1_web_internal_portlet_A4P1Portlet"
	},
	service = Portlet.class
)
public class A4P1Portlet extends MVCPortlet {
}