package com.acme.n2f3.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
	category = "n2f3", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.acme.n2f3.web.internal.configuration.N2F3WebConfiguration",
	localization = "content/Language", name = "n2f3-configuration-name"
)
public interface N2F3WebConfiguration {

	@Meta.AD(deflt = "blue", required = false)
	public String fontColor();

	@Meta.AD(deflt = "serif", required = false)
	public String fontFamily();

	@Meta.AD(deflt = "16", required = false)
	public int fontSize();

}