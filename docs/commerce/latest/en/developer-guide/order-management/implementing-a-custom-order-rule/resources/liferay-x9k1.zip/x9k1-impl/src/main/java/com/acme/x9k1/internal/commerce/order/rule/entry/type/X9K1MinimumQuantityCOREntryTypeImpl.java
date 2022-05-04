package com.acme.x9k1.internal.commerce.order.rule.entry.type;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.rule.entry.type.COREntryType;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.acme.x9k1.internal.commerce.order.rule.web.display.context.X9K1MinimumQuantityDisplayContext;

import java.math.BigDecimal;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "commerce.order.rule.entry.type.key=minimum-quantity-order-rule",
                "commerce.order.rule.entry.type.order:Integer=1"
        },
        service = COREntryType.class
)
public class X9K1MinimumQuantityCOREntryTypeImpl implements COREntryType {

	@Override
	public boolean evaluate(COREntry corEntry, CommerceOrder commerceOrder)
		throws PortalException {
		
		X9K1MinimumQuantityDisplayContext x9k1MinimumQuantityDisplayContext = new X9K1MinimumQuantityDisplayContext(corEntry);
		
		int orderQuantity = 0;
		
		int minimumQuantity = x9k1MinimumQuantityDisplayContext.getMinimumQuantity();
		
		List<CommerceOrderItem> commerceOrderItems = commerceOrder.getCommerceOrderItems();
		
		for(CommerceOrderItem commerceOrderItem: commerceOrderItems) {
						
			orderQuantity = orderQuantity + commerceOrderItem.getQuantity();
			
		}
		
		if (orderQuantity < minimumQuantity){
            
			return false;
			
		}

		return true;
	}

	@Override
	public String getErrorMessage(
			COREntry corEntry, CommerceOrder commerceOrder, Locale locale)
		throws PortalException {
		
		X9K1MinimumQuantityDisplayContext x9k1MinimumQuantityDisplayContext = new X9K1MinimumQuantityDisplayContext(corEntry);

		int orderQuantity = 0;
		
		int minimumQuantity = x9k1MinimumQuantityDisplayContext.getMinimumQuantity();
		
		List<CommerceOrderItem> commerceOrderItems = commerceOrder.getCommerceOrderItems();
		
		for(CommerceOrderItem commerceOrderItem: commerceOrderItems) {
						
			orderQuantity = orderQuantity + commerceOrderItem.getQuantity();
			
		}
		
		return "Total order quantity does not meet the minimum total order quantity of " + minimumQuantity + 
				". Please add " + (minimumQuantity - orderQuantity) + " more item(s) to continue.";
	}

	@Override
	public String getKey() {
		return "minimum-quantity-order-rule";
	}
	
	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "x9k1-minimum-order-quantity");
	}
	
	private static final Log _log = LogFactoryUtil.getLog(
			X9K1MinimumQuantityCOREntryTypeImpl.class);

}