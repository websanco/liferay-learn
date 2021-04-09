package com.acme.q4b9.internal.commerce.tax;

import com.liferay.commerce.exception.CommerceTaxEngineException;
import com.liferay.commerce.tax.CommerceTaxCalculateRequest;
import com.liferay.commerce.tax.CommerceTaxEngine;
import com.liferay.commerce.tax.CommerceTaxValue;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.math.BigDecimal;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "commerce.tax.engine.key=q4b9", service = CommerceTaxEngine.class
)
public class Q4B9CommerceTaxEngine implements CommerceTaxEngine {

	@Override
	public CommerceTaxValue getCommerceTaxValue(
			CommerceTaxCalculateRequest commerceTaxCalculateRequest)
		throws CommerceTaxEngineException {

		BigDecimal flatTaxValue = _ONE_POINT_FIVE_ZERO;

		if (commerceTaxCalculateRequest.isPercentage()) {
			flatTaxValue = _ONE_POINT_FIVE_ZERO.divide(new BigDecimal(100.0));

			flatTaxValue = flatTaxValue.multiply(
				commerceTaxCalculateRequest.getPrice());
		}

		return new CommerceTaxValue(
			"q4b9", "q4b9-commerce-tax-engine", flatTaxValue);
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.format(
			resourceBundle,
			"this-tax-engine-serves-a-fixed-x-percent-flat-tax-rate",
			_ONE_POINT_FIVE_ZERO);
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "q4b9-commerce-tax-engine");
	}

	private static final BigDecimal _ONE_POINT_FIVE_ZERO = new BigDecimal(
		"1.50");

}