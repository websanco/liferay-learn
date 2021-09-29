package com.acme.t2p5.web.internal.portlet;

import com.acme.t2p5.service.T2P5EntryLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=T2P5 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp"
	},
	service = Portlet.class
)
public class T2P5Portlet extends MVCPortlet {

	public void addT2P5Entry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		_t2p5EntryLocalService.addT2P5Entry(
			ParamUtil.getString(actionRequest, "description"),
			ParamUtil.getString(actionRequest, "name"));
	}

	@Reference
	private T2P5EntryLocalService _t2p5EntryLocalService;

}