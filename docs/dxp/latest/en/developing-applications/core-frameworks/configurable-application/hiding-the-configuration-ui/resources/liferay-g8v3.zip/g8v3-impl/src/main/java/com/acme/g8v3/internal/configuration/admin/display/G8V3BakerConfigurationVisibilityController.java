package com.acme.g8v3.internal.configuration.admin.display;

import com.acme.g8v3.internal.configuration.G8V3AbleConfiguration;

import com.liferay.configuration.admin.display.ConfigurationVisibilityController;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
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

	public boolean isG8V3BakerConfigurationEnabled() {
		try {
			_g8v3AbleConfiguration =
				_configurationProvider.getSystemConfiguration(
					G8V3AbleConfiguration.class);
		}
		catch (ConfigurationException configurationException) {
		}

		return _g8v3AbleConfiguration.enableG8V3BakerConfiguration();
	}

	@Override
	public boolean isVisible(
		ExtendedObjectClassDefinition.Scope scope, Serializable scopePK) {

		return isG8V3BakerConfigurationEnabled();
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	private G8V3AbleConfiguration _g8v3AbleConfiguration;

}