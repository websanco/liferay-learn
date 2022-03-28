package com.acme.c2p9.internal.dynamic.data.mapping.form.field.type;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"ddm.form.field.type.description=c2p9-description",
		"ddm.form.field.type.display.order:Integer=10",
		"ddm.form.field.type.group=customized", "ddm.form.field.type.icon=text",
		"ddm.form.field.type.label=c2p9-label",
		"ddm.form.field.type.name=c2p9-slider"
	},
	service = DDMFormFieldType.class
)
public class C2P9DDMFormFieldType extends BaseDDMFormFieldType {

	@Override
	public String getModuleName() {
		return _npmResolver.resolveModuleName(
			"dynamic-data-mapping-form-field-type-c2p9-slider/C2P9/Slider.es");
	}

	@Override
	public String getName() {
		return "c2p9-slider";
	}

	@Override
	public boolean isCustomDDMFormFieldType() {
		return true;
	}

	@Reference
	private NPMResolver _npmResolver;

}