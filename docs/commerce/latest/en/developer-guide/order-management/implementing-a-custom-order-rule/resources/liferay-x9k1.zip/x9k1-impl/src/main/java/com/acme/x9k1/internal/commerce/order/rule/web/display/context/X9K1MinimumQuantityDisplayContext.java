package com.acme.x9k1.internal.commerce.order.rule.web.display.context;

import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;

import java.math.BigDecimal;

public class X9K1MinimumQuantityDisplayContext {

    public X9K1MinimumQuantityDisplayContext(COREntry corEntry) {
        _corEntry = corEntry;
    }
    
    public int getMinimumQuantity(){

        UnicodeProperties typeSettingsUnicodeProperties =
                UnicodePropertiesBuilder.fastLoad(
                        _corEntry.getTypeSettings()
                ).build();

        return GetterUtil.getInteger(
                        typeSettingsUnicodeProperties.getProperty("minimum-quantity"));
    }

	private final COREntry _corEntry;
}