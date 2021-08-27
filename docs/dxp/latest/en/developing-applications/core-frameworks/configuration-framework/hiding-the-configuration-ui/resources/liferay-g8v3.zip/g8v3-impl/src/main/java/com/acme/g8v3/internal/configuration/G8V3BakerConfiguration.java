package com.acme.g8v3.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
	id = "com.acme.g8v3.internal.configuration.G8V3BakerConfiguration",
	localization = "content/Language", name = "g8v3-baker-configuration-name"
)
public interface G8V3BakerConfiguration {

	@Meta.AD(name = "advanced-settings", required = false)
	public String advancedSettings();

}