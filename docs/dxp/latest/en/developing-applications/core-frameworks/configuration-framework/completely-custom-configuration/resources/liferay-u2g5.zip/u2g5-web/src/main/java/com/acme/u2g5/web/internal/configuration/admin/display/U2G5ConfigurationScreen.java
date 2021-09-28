package com.acme.u2g5.web.internal.configuration.admin.display;

import com.acme.u2g5.web.internal.configuration.U2G5WebConfiguration;

import com.liferay.configuration.admin.display.ConfigurationScreen;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ConfigurationScreen.class)
public class U2G5ConfigurationScreen implements ConfigurationScreen {

	@Override
	public String getCategoryKey() {
		return "third-party";
	}

	@Override
	public String getKey() {
		return "u2g5-configuration-name";
	}

	@Override
	public String getName(Locale locale) {
		return "U2G5 Custom Configuration";
	}

	@Override
	public String getScope() {
		return "system";
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		try {
			httpServletRequest.setAttribute(
				U2G5WebConfiguration.class.getName(),
				_configurationProvider.getSystemConfiguration(
					U2G5WebConfiguration.class));

			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher("/u2g5.jsp");

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new IOException("Unable to render", exception);
		}
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference(
		target = "(osgi.web.symbolicname=com.acme.u2g5.web)", unbind = "-"
	)
	private ServletContext _servletContext;

}