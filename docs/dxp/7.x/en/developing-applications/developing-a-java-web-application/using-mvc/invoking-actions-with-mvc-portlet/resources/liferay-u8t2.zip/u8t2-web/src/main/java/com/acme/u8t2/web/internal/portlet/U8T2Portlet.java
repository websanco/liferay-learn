package com.acme.u8t2.web.internal.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=U8T2 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp"
	},
	service = Portlet.class
)
public class U8T2Portlet extends MVCPortlet {

	public void doSomething(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		if (_log.isWarnEnabled()) {
			_log.warn("Invoke #doSomething(ActionRequest, ActionResponse)");
		}
	}

	public void doSomethingElse(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		if (_log.isWarnEnabled()) {
			_log.warn("Invoke #doSomethingElse(ActionRequest, ActionResponse)");
		}
	}

	@ProcessAction(name = "action3")
	public void doSomethingMore(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		if (_log.isWarnEnabled()) {
			_log.warn("Invoke #doSomethingMore(ActionRequest, ActionResponse)");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(U8T2Portlet.class);

}