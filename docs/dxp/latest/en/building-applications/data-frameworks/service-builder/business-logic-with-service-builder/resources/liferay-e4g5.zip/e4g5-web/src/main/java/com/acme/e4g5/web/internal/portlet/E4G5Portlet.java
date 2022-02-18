package com.acme.e4g5.web.internal.portlet;

import com.acme.e4g5.service.E4G5EntryLocalService;

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
		"javax.portlet.display-name=E4G5 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp"
	},
	service = Portlet.class
)
public class E4G5Portlet extends MVCPortlet {

	public void addE4G5Entry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		_e4g5EntryLocalService.addE4G5Entry(
			ParamUtil.getString(actionRequest, "description"),
			ParamUtil.getString(actionRequest, "name"));
	}

	public void deleteE4G5Entry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		_e4g5EntryLocalService.deleteE4G5Entry(
			ParamUtil.getLong(actionRequest, "e4g5EntryId"));
	}

	public void updateE4G5Entry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		_e4g5EntryLocalService.updateE4G5Entry(
			ParamUtil.getLong(actionRequest, "e4g5EntryId"),
			ParamUtil.getString(actionRequest, "description"),
			ParamUtil.getString(actionRequest, "name"));
	}

	@Reference
	private E4G5EntryLocalService _e4g5EntryLocalService;

}