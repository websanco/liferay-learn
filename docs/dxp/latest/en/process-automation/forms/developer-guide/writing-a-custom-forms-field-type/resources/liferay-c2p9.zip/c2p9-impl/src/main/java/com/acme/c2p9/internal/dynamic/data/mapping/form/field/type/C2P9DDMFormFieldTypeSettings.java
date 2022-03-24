package com.acme.c2p9.internal.dynamic.data.mapping.form.field.type;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;

@DDMForm
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
	value = {
		@DDMFormLayoutPage(
			title = "%basic",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"label", "predefinedValue", "required", "tip"
							}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%advanced",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"dataType", "min", "max", "name", "showLabel",
								"repeatable", "type", "validation",
								"visibilityExpression"
							}
						)
					}
				)
			}
		)
	}
)
public interface C2P9DDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

	@DDMFormField(
		label = "%max-value",
		properties = "placeholder=%enter-the-top-limit-of-the-range",
		type = "numeric"
	)
	public String max();

	@DDMFormField(
		label = "%min-value",
		properties = "placeholder=%enter-the-bottom-limit-of-the-range",
		type = "numeric"
	)
	public String min();

}