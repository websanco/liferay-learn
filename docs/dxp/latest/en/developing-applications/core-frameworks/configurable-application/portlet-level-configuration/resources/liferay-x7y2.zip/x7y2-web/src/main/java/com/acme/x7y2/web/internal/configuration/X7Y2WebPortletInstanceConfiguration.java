package com.acme.x7y2.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
	category = "x7y2",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.acme.x7y2.web.internal.configuration.X7Y2WebPortletInstanceConfiguration",
	name = "X7Y2 Portlet"
)
public interface X7Y2WebPortletInstanceConfiguration {

	@Meta.AD(
		deflt = "green", name = "color",
		optionLabels = {"Green", "Orange", "Purple"},
		optionValues = {"green", "orange", "purple"}, required = false
	)
	public String color();

}