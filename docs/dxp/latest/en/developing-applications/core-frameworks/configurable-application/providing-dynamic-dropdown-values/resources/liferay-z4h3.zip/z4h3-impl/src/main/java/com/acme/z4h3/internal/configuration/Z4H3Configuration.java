package com.acme.z4h3.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
	id = "com.acme.z4h3.internal.configuration.Z4H3Configuration",
	localization = "content/Language", name = "z4h3-configuration"
)
public @interface Z4H3Configuration {

	@Meta.AD(
		description = "this-list-is-manually-populated",
		name = "z4h3-asset-types",
		optionLabels = {"option1", "option2", "option3"},
		optionValues = {"Option 1", "Option 2", "Option 3"}, required = false
	)
	String entryList1();

	@Meta.AD(
		description = "this-list-is-dynamically-populated",
		name = "z4h3-asset-types",
		optionLabels = {"option1", "option2", "option3"},
		optionValues = {"Option 1", "Option 2", "Option 3"}, required = false
	)
	String entryList2();

}