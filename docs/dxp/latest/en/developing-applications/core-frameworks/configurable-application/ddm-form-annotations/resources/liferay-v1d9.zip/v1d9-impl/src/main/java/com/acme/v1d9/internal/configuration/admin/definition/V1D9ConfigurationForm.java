package com.acme.v1d9.internal.configuration.admin.definition;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;

@DDMForm
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.SINGLE_PAGE_MODE,
	value = {
		@DDMFormLayoutPage(
			{
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(size = 6, value = "text"),
						@DDMFormLayoutColumn(size = 6, value = "checkbox")
					}
				),
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(size = 6, value = "select"),
						@DDMFormLayoutColumn(size = 6, value = "numeric")
					}
				),
				@DDMFormLayoutRow(
					{@DDMFormLayoutColumn(size = 12, value = "date")}
				)
			}
		)
	}
)
public interface V1D9ConfigurationForm {

	@DDMFormField(label = "%text")
	public String[] text();

	@DDMFormField(label = "%checkbox", properties = "showAsSwitcher=true")
	public boolean checkbox();

	@DDMFormField(
		label = "%select", optionLabels = {"%foo", "%bar"},
		optionValues = {"foo", "bar"}, type = "select"
	)
	public String select();

	@DDMFormField(
		label = "%numeric", properties = "placeholder=%seconds",
		type = "numeric",
		validationErrorMessage = "%please-enter-an-integer-between-0-and-60-seconds",
		validationExpression = "(numeric >= 0) && (numeric <= 60)"
	)
	public String numeric();

	@DDMFormField(label = "%date", type = "date")
	public String date();

}