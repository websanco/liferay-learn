package com.acme.h6d2.web.internal.portlet;

import com.acme.h6d2.model.H6D2;
import com.acme.h6d2.service.H6D2LocalService;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=H6D2 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class H6D2Portlet extends MVCPortlet {

	public void addH6D2(ActionRequest actionRequest, ActionResponse response)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String item = ParamUtil.getString(actionRequest, "item");

		User user = _portal.getUser(actionRequest);

		H6D2 h6d2 = _h6d2LocalService.createH6D2(
			CounterLocalServiceUtil.increment());

		h6d2.setCompanyId(user.getCompanyId());
		h6d2.setUserId(user.getUserId());
		h6d2.setUserName(user.getFullName());

		h6d2.setGroupId(themeDisplay.getSiteGroupId());

		h6d2.setTodo(item);

		_h6d2LocalService.addH6D2(h6d2);
	}

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		super.doView(renderRequest, renderResponse);
	}

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private H6D2LocalService _h6d2LocalService;

	@Reference
	private Portal _portal;

}