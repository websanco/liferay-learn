package com.acme.c2p9.internal.dynamic.data.mapping.form.field.type;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "ddm.form.field.type.name=c2p9-slider",
	service = DDMFormFieldTemplateContextContributor.class
)
public class C2P9DDMFormFieldTemplateContextContributor
	implements DDMFormFieldTemplateContextContributor {

	@Override
	public Map<String, Object> getParameters(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("max", (String)ddmFormField.getProperty("max"));
		parameters.put("min", (String)ddmFormField.getProperty("min"));

		return parameters;
	}

}