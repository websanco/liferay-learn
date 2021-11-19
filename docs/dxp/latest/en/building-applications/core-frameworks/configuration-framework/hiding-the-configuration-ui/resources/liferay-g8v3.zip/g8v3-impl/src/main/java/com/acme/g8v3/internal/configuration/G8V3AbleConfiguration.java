package com.acme.g8v3.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
	id = "com.acme.g8v3.internal.configuration.G8V3AbleConfiguration",
	localization = "content/Language", name = "g8v3-able-configuration-name"
)
public interface G8V3AbleConfiguration {

	@Meta.AD(
		deflt = "false", name = "enable-g8v3-baker-configuration",
		required = false
	)
	public boolean enableG8V3BakerConfiguration();

}