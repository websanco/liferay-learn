package com.acme.x9k1.internal.commerce.order.rule.entry.type;

import com.acme.x9k1.internal.commerce.order.rule.web.display.context.X9K1MinimumQuantityDisplayContext;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.rule.entry.type.COREntryType;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"commerce.order.rule.entry.type.key=x9k1-minimum-quantity-order-rule",
		"commerce.order.rule.entry.type.order:Integer=1"
	},
	service = COREntryType.class
)
public class X9K1MinimumQuantityCOREntryTypeImpl implements COREntryType {

	@Override
	public boolean evaluate(COREntry corEntry, CommerceOrder commerceOrder)
		throws PortalException {

		X9K1MinimumQuantityDisplayContext x9k1MinimumQuantityDisplayContext =
			new X9K1MinimumQuantityDisplayContext(corEntry);

		int minimumQuantity =
			x9k1MinimumQuantityDisplayContext.getMinimumQuantity();

		if (_getTotalOrderItemQuantity(commerceOrder) < minimumQuantity) {
			return false;
		}

		return true;
	}

	@Override
	public String getErrorMessage(
			COREntry corEntry, CommerceOrder commerceOrder, Locale locale)
		throws PortalException {

		X9K1MinimumQuantityDisplayContext x9k1MinimumQuantityDisplayContext =
			new X9K1MinimumQuantityDisplayContext(corEntry);

		int minimumQuantity =
			x9k1MinimumQuantityDisplayContext.getMinimumQuantity();

		return "Total order quantity is less than the total minimum order " +
			"quantity of " + minimumQuantity + ". Please add " +
				(minimumQuantity - _getTotalOrderItemQuantity(commerceOrder)) +
					" more item(s) to continue.";
	}

	@Override
	public String getKey() {
		return "x9k1-minimum-quantity-order-rule";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "x9k1-minimum-order-quantity");
	}

	private int _getTotalOrderItemsQuantity(CommerceOrder commerceOrder) {
		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		int orderQuantity = 0;

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			orderQuantity = orderQuantity + commerceOrderItem.getQuantity();
		}

		return orderQuantity;
	}

}