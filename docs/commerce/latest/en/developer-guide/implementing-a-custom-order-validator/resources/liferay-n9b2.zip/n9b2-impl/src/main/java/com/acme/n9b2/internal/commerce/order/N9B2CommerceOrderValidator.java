package com.acme.n9b2.internal.commerce.order;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.math.BigDecimal;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"commerce.order.validator.key=n9b2",
		"commerce.order.validator.priority:Integer=9"
	},
	service = CommerceOrderValidator.class
)
public class N9B2CommerceOrderValidator implements CommerceOrderValidator {

	@Override
	public String getKey() {
		return "n9b2";
	}

	@Override
	public CommerceOrderValidatorResult validate(
			Locale locale, CommerceOrder commerceOrder, CPInstance cpInstance,
			int quantity)
		throws PortalException {

		if (cpInstance == null) {
			return new CommerceOrderValidatorResult(false);
		}

		BigDecimal price = cpInstance.getPrice();

		if ((price.doubleValue() > _MAX_ITEM_PRICE) &&
			(quantity > _MAX_ITEM_QUANTITY)) {

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

			return new CommerceOrderValidatorResult(
				false,
				LanguageUtil.format(
					resourceBundle,
					"this-expensive-item-has-a-maximum-quantity-of-x",
					Integer.valueOf(_MAX_ITEM_QUANTITY)));
		}

		return new CommerceOrderValidatorResult(true);
	}

	@Override
	public CommerceOrderValidatorResult validate(
			Locale locale, CommerceOrderItem commerceOrderItem)
		throws PortalException {

		BigDecimal price = commerceOrderItem.getUnitPrice();

		if ((price.doubleValue() > _MAX_ITEM_PRICE) &&
			(commerceOrderItem.getQuantity() > _MAX_ITEM_QUANTITY)) {

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

			return new CommerceOrderValidatorResult(
				false,
				LanguageUtil.format(
					resourceBundle,
					"expensive-items-have-a-maximum-order-quantity-of-x",
					Integer.valueOf(_MAX_ITEM_QUANTITY)));
		}

		return new CommerceOrderValidatorResult(true);
	}

	private static final double _MAX_ITEM_PRICE = 100.0;

	private static final int _MAX_ITEM_QUANTITY = 10;

}