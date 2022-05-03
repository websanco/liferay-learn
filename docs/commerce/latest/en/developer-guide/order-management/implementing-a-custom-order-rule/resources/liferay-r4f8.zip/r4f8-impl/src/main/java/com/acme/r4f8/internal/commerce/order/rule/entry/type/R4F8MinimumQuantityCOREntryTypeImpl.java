package com.acme.r4f8.internal.commerce.order.rule.entry.type;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.rule.entry.type.COREntryType;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;

import java.math.BigDecimal;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "commerce.order.rule.entry.type.key=r4f8-minimum-quantity-order-rule",
                "commerce.order.rule.entry.type.order:Integer=3"
        },
        service = COREntryType.class
)
public class R4F8MinimumQuantityCOREntryTypeImpl implements COREntryType {

	@Override
	public boolean evaluate(COREntry corEntry, CommerceOrder commerceOrder)
		throws PortalException {

		System.out.println("Evaluating new rule");

		return false;
	}

	@Override
	public String getErrorMessage(
			COREntry corEntry, CommerceOrder commerceOrder, Locale locale)
		throws PortalException {

		return "This is the error from the new Order Rule";
	}

	@Override
	public String getKey() {
		return "r4f8-minimum-quantity-order-rule";
	}
	
	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "r4f8-minimum-order-quantity");
	}

}