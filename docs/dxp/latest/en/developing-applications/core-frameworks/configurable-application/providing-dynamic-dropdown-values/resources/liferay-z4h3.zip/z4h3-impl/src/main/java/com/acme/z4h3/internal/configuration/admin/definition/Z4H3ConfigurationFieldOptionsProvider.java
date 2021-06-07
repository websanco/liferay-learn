package com.acme.z4h3.internal.configuration.admin.definition;

import com.liferay.configuration.admin.definition.ConfigurationFieldOptionsProvider;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"configuration.field.name=dynamicallyPopulatedColorTypes",
		"configuration.pid=com.acme.z4h3.internal.configuration.Z4H3Configuration"
	},
	service = ConfigurationFieldOptionsProvider.class
)
public class Z4H3ConfigurationFieldOptionsProvider
	implements ConfigurationFieldOptionsProvider {

	public List<Option> getOptions() {
		Stream<Color> stream = _colors.stream();

		return stream.filter(
			color -> color.value != null
		).map(
			color -> new Option() {

				@Override
				public String getLabel(Locale locale) {
					ResourceBundle resourceBundle =
						ResourceBundleUtil.getBundle(
							"content.Language", locale, getClass());

					return LanguageUtil.get(resourceBundle, color.value);
				}

				@Override
				public String getValue() {
					return color.value;
				}

			}
		).collect(
			Collectors.toList()
		);
	}

	public class Color {

		public Color(String value) {
			this.value = value;
		}

		public String value;

	}

	private final List<Color> _colors = Arrays.asList(
		new Color("blue"), new Color("red"), new Color("yellow"));

}