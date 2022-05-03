/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.order.rule.web.display.context;

import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;

import java.math.BigDecimal;

public class R4F8MinimumQuantityDisplayContext {

    public R4F8MinimumQuantityDisplayContext(COREntry corEntry) {
        _corEntry = corEntry;
    }
    
    public double getMinimumQuantity(){

        double maximumVolumeAmount = 22.0;

        return maximumVolumeAmount;
    }

	private final COREntry _corEntry;
}