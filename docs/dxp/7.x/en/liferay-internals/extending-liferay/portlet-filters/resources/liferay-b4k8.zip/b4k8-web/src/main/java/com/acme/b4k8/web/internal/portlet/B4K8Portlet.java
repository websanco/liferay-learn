package com.acme.b4k8.web.internal.portlet;

import com.acme.b4k8.model.Person;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + B4K8Portlet.B4K8_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class B4K8Portlet extends MVCPortlet {

	public static final String B4K8_PORTLET_NAME = "b4k8_portlet";

	public static final String LOAD_USERS_ACTION = "loadUsers";

	public static final String MEMBERLIST_ATTRIBUTE = "memberlist";

	public void loadUsers(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		actionRequest.setAttribute(
			B4K8Portlet.MEMBERLIST_ATTRIBUTE, _createStaticUserList());
	}

	private List<Person> _createStaticUserList() {
		return Arrays.asList(
			new Person("Sievert Shayne", "Sievert.Shayne@example.org"),
			new Person("Vida Jonas", "Vida.Jonas@example.net"),
			new Person("Nikola Septima", "Nikola.Septima@example.com"),
			new Person("Ericka Merav", "Ericka.Merav@example.dev"),
			new Person("Kennet Brandr", "Kennet.Brandr@example.com"));
	}

}