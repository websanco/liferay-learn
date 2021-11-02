package com.acme.j6x8.internal.commerce.model;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.exception.CommerceShippingEngineException;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShippingEngine;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.model.CommerceShippingOption;
import com.liferay.commerce.service.CommerceAddressRestrictionLocalService;
import com.liferay.commerce.service.CommerceShippingMethodLocalService;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionLocalService;
import com.liferay.commerce.util.CommerceShippingHelper;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "commerce.shipping.engine.key=j6x8",
	service = CommerceShippingEngine.class
)
public class J6X8CommerceShippingEngine implements CommerceShippingEngine {

	@Override
	public String getCommerceShippingOptionLabel(String name, Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, name);
	}

	@Override
	public List<CommerceShippingOption> getCommerceShippingOptions(
			CommerceContext commerceContext, CommerceOrder commerceOrder,
			Locale locale)
		throws CommerceShippingEngineException {

		try {
			CommerceShippingMethod commerceShippingMethod =
				_commerceShippingMethodLocalService.fetchCommerceShippingMethod(
					commerceOrder.getScopeGroupId(), "j6x8");

			if (commerceShippingMethod == null) {
				return Collections.emptyList();
			}

			List<CommerceShippingOption> commerceShippingOptions =
				new ArrayList<>();

			List<CommerceShippingFixedOption> commerceShippingFixedOptions =
				_commerceShippingFixedOptionLocalService.
					getCommerceShippingFixedOptions(
						commerceShippingMethod.getCommerceShippingMethodId(),
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (CommerceShippingFixedOption commerceShippingFixedOption :
					commerceShippingFixedOptions) {

				CommerceAddress commerceAddress =
					commerceOrder.getShippingAddress();

				if (_commerceAddressRestrictionLocalService.
						isCommerceShippingMethodRestricted(
							commerceShippingFixedOption.
								getCommerceShippingMethodId(),
							commerceAddress.getCountryId())) {

					continue;
				}

				String name = commerceShippingFixedOption.getName(locale);

				if (_commerceShippingHelper.isFreeShipping(commerceOrder)) {
					commerceShippingOptions.add(
						new CommerceShippingOption(
							name, name, BigDecimal.ZERO));
				}

				BigDecimal amount = commerceShippingFixedOption.getAmount();

				amount = amount.multiply(BigDecimal.valueOf(0.75));

				commerceShippingOptions.add(
					new CommerceShippingOption(name, name, amount));
			}

			return commerceShippingOptions;
		}
		catch (Exception exception) {
			throw new CommerceShippingEngineException(exception);
		}
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "ship-for-a-discounted-price");
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "discounted-rate");
	}

	@Reference
	private CommerceAddressRestrictionLocalService
		_commerceAddressRestrictionLocalService;

	@Reference
	private CommerceShippingFixedOptionLocalService
		_commerceShippingFixedOptionLocalService;

	@Reference
	private CommerceShippingHelper _commerceShippingHelper;

	@Reference
	private CommerceShippingMethodLocalService
		_commerceShippingMethodLocalService;

}