package com.acme.p8v5.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"javax.portlet.name=com_acme_p8v5_web_internal_portlet_P8V5Portlet",
		"mvc.command.name=/p8v5/download_greeting"
	},
	service = MVCResourceCommand.class
)
public class P8V5DownloadGreetingMVCResourceCommand
	implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, "p8v5_greeting.txt",
				"Hello P8V5!".getBytes(), "text");

			return false;
		}
		catch (IOException ioException) {
			_log.error(ioException, ioException);

			return true;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		P8V5DownloadGreetingMVCResourceCommand.class);

}