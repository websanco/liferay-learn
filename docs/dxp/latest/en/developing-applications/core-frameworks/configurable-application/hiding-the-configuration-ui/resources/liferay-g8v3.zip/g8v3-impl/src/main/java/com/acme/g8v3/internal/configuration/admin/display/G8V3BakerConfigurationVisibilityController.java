package com.acme.g8v3.internal.configuration.admin.display;

import com.acme.g8v3.internal.configuration.G8V3AbleConfiguration;

import com.liferay.configuration.admin.display.ConfigurationVisibilityController;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

import java.io.Serializable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "configuration.pid=com.acme.g8v3.internal.configuration.G8V3BakerConfiguration",
	service = ConfigurationVisibilityController.class
)
public class G8V3BakerConfigurationVisibilityController
	implements ConfigurationVisibilityController {

	@Override
	public boolean isVisible(
		ExtendedObjectClassDefinition.Scope scope, Serializable scopePK) {

		try {
			G8V3AbleConfiguration g8v3AbleConfiguration =
				_configurationProvider.getSystemConfiguration(
					G8V3AbleConfiguration.class);

			return g8v3AbleConfiguration.enableG8V3BakerConfiguration();
		}
		catch (ConfigurationException configurationException) {
			_log.error(configurationException, configurationException);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		G8V3BakerConfigurationVisibilityController.class);

	@Reference
	private ConfigurationProvider _configurationProvider;

}