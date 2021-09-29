package com.acme.h6d2.web.internal.portlet;

import com.acme.h6d2.model.H6D2Entry;
import com.acme.h6d2.service.H6D2EntryLocalService;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=H6D2 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp"
	},
	service = Portlet.class
)
public class H6D2Portlet extends MVCPortlet {

	public void addH6D2Entry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String name = ParamUtil.getString(actionRequest, "name");

		User user = _portal.getUser(actionRequest);

	 	H6D2Entry h6d2Entry = _h6d2EntryLocalService.createH6D2Entry(
			CounterLocalServiceUtil.increment());

		h6d2Entry.setCompanyId(user.getCompanyId());
		h6d2Entry.setUserId(user.getUserId());
		h6d2Entry.setUserName(user.getFullName());

		h6d2Entry.setGroupId(themeDisplay.getSiteGroupId());

		h6d2Entry.setName(name);

		_h6d2EntryLocalService.addH6D2Entry(h6d2Entry);
	}

	@Reference
	private Portal _portal;

	@Reference
	private H6D2EntryLocalService _h6d2EntryLocalService;

}