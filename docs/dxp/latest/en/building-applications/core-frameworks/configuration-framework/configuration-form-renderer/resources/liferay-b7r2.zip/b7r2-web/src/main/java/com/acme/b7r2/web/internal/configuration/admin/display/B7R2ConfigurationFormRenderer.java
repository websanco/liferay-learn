package com.acme.b7r2.web.internal.configuration.admin.display;

import com.acme.b7r2.web.internal.configuration.B7R2WebConfiguration;

import com.liferay.configuration.admin.display.ConfigurationFormRenderer;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ConfigurationFormRenderer.class)
public class B7R2ConfigurationFormRenderer
	implements ConfigurationFormRenderer {

	@Override
	public String getPid() {
		return "com.acme.b7r2.web.internal.configuration.B7R2WebConfiguration";
	}

	@Override
	public Map<String, Object> getRequestParameters(
		HttpServletRequest httpServletRequest) {

		String b7r2Color = ParamUtil.getString(httpServletRequest, "b7r2Color");

		return new HashMap<String, Object>() {
			{
				put("b7r2Color", b7r2Color);
			}
		};
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		try {
			httpServletRequest.setAttribute(
				B7R2WebConfiguration.class.getName(),
				_configurationProvider.getSystemConfiguration(
					B7R2WebConfiguration.class));

			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher("/b7r2.jsp");

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new IOException("Unable to render /b7r2.jsp", exception);
		}
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference(
		target = "(osgi.web.symbolicname=com.acme.b7r2.web)", unbind = "-"
	)
	private ServletContext _servletContext;

}