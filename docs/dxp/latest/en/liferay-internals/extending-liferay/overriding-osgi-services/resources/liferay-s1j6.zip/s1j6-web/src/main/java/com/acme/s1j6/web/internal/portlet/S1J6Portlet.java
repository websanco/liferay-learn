package com.acme.s1j6.web.internal.portlet;

import com.acme.s1j6.S1J6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=S1J6 Portlet"
	},
	service = Portlet.class
)
public class S1J6Portlet extends GenericPortlet {

	@Override
	protected void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		PrintWriter printWriter = renderResponse.getWriter();

		printWriter.println("This is the portlet.<br />");
		printWriter.println(_s1j6.doSomething());
	}

	@Reference
	private S1J6 _s1j6;

}