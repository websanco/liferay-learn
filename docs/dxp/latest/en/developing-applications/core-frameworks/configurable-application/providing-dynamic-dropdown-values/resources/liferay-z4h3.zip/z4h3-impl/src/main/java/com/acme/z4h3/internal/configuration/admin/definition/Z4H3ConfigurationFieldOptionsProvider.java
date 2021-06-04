package com.acme.z4h3.internal.configuration.admin.definition;

import com.liferay.configuration.admin.definition.ConfigurationFieldOptionsProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
        return _colors.stream()
        .filter( color -> (color._label != null) )
        .map(
            color -> new Option() {
                    @Override
                    public String getLabel(Locale locale) {
                        return color._label;
                    }
                    @Override
                    public String getValue() {
                        return color._value;
                    }
                }
        ).collect(Collectors.toList());
    }

	public class Color {

		public Color(String label, String value) {
			_label = label;
			_value = value;
		}

		public String _label;
		public String _value;

	}

	private final List<Color> _colors = Arrays.asList(
		new Color("Blue", "blue"), new Color("Red", "red"),
		new Color("Yellow", "yellow"));

}