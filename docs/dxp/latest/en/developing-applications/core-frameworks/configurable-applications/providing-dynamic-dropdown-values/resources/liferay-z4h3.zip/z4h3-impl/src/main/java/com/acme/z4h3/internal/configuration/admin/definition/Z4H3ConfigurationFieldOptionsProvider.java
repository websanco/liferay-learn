package com.acme.z4h3.internal.configuration.admin.definition;

import com.liferay.configuration.admin.definition.ConfigurationFieldOptionsProvider;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"configuration.field.name=providerPopulatedColors",
		"configuration.pid=com.acme.z4h3.internal.configuration.Z4H3Configuration"
	},
	service = ConfigurationFieldOptionsProvider.class
)
public class Z4H3ConfigurationFieldOptionsProvider
	implements ConfigurationFieldOptionsProvider {

	public List<Option> getOptions() {
		return Arrays.asList(
			_getOption("green"), _getOption("orange"), _getOption("purple"));
	}

	private Option _getOption(String color) {
		return new Option() {

			@Override
			public String getLabel(Locale locale) {
				ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
					"content.Language", locale, getClass());

				return LanguageUtil.get(resourceBundle, color);
			}

			@Override
			public String getValue() {
				return color;
			}

		};
	}

}