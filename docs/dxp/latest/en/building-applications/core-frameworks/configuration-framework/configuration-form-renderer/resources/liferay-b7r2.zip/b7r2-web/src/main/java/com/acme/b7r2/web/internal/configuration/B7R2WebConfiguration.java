package com.acme.b7r2.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
	id = "com.acme.b7r2.web.internal.configuration.B7R2WebConfiguration",
	localization = "content/Language", name = "b7r2-configuration-name"
)
public interface B7R2WebConfiguration {

	@Meta.AD(deflt = "blue", name = "b7r2-color", required = false)
	public String b7r2Color();

}