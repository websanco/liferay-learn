package com.acme.x9k1.internal.commerce.order.rule.entry.type.util;

import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;

public class X9K1MinimumQuantityUtil {

	public int getMinimumQuantity(COREntry corEntry) {
		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				corEntry.getTypeSettings()
			).build();

		return GetterUtil.getInteger(
			typeSettingsUnicodeProperties.getProperty("minimum-quantity"));
	}

}