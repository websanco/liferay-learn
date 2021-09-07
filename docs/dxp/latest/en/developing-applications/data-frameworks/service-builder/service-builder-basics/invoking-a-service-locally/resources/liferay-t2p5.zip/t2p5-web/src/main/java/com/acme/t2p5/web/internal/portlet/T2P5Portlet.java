package com.acme.t2p5.web.internal.portlet;

import com.acme.t2p5.service.T2P5EntryLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

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
			ParamUtil.getString(actionRequest, "name"),
			ParamUtil.getString(actionRequest, "description"));
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			"entriesCount", _t2p5EntryLocalService.getT2P5EntriesCount());

		renderRequest.setAttribute(
			"entries", _t2p5EntryLocalService.getT2P5Entries(-1, -1));

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private T2P5EntryLocalService _t2p5EntryLocalService;

}