package com.acme.s5e6.web.internal.portlet;

import com.acme.s5e6.model.S5E6Entry;
import com.acme.s5e6.service.S5E6EntryLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=S5E6 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=com_acme_s5e6_web_internal_portlet_S5E6Portlet"
	},
	service = Portlet.class
)
public class S5E6Portlet extends MVCPortlet {

	public void addS5E6Entry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			S5E6Entry.class.getName(), actionRequest);

		_s5e6EntryLocalService.addS5E6Entry(
			themeDisplay.getUserId(), themeDisplay.getSiteGroupId(),
			ParamUtil.getString(actionRequest, "description"),
			ParamUtil.getString(actionRequest, "name"), serviceContext);
	}

	@Reference
	private S5E6EntryLocalService _s5e6EntryLocalService;

}