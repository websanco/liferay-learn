package com.acme.l6y9.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=L6Y9 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=L6Y9Portlet"
	},
	service = Portlet.class
)
public class L6Y9Portlet extends MVCPortlet {
}