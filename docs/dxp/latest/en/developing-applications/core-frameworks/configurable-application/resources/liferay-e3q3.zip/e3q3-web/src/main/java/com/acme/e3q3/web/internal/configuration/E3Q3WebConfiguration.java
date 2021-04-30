package com.acme.e3q3.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "com.acme.e3q3.web.internal.configuration.E3Q3WebConfiguration")
public interface E3Q3WebConfiguration {

	@Meta.AD(deflt = "blue", required = false)
	public String fontColor();

	@Meta.AD(deflt = "serif", required = false)
	public String fontFamily();

	@Meta.AD(deflt = "16", required = false)
	public int fontSize();

}