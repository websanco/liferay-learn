package com.acme.z4h3.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
	id = "com.acme.z4h3.internal.configuration.Z4H3Configuration",
	localization = "content/Language", name = "z4h3-configuration-name"
)
public @interface Z4H3Configuration {

	@Meta.AD(
		description = "this-list-is-manually-populated", name = "asset-types",
		optionLabels = {"Asset1", "Asset2", "Asset3"},
		optionValues = {"Asset1", "Asset2", "Asset3"}, required = false
	)
	String assetTypes();

	@Meta.AD(
		description = "this-list-is-dynamically-populated",
		name = "dynamically-populated-asset-types",
		optionLabels = {"Asset1", "Asset2", "Asset3"},
		optionValues = {"Asset1", "Asset2", "Asset3"}, required = false
	)
	String dynamicallyPopulatedAssetTypes();

}