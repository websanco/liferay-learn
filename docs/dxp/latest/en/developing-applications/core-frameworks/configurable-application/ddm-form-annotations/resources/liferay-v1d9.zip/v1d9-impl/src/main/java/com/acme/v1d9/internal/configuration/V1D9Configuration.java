package com.acme.v1d9.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
	id = "com.acme.v1d9.internal.configuration.V1D9Configuration",
	localization = "content/Language", name = "v1d9-configuration-name"
)
public @interface V1D9Configuration {

	@Meta.AD(required = false)
	public String[] text();

	@Meta.AD(required = false)
	public boolean checkbox();

	@Meta.AD(required = false)
	public String select();

	@Meta.AD(required = false)
	public String numeric();

	@Meta.AD(required = false)
	public String date();

}