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

package com.liferay.commerce.order.rule.web.entry.type;

import com.liferay.commerce.order.rule.entry.type.COREntryTypeJSPContributor;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.commerce.order.rule.entry.type.COREntryTypeJSPContributor;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.service.COREntryLocalService;
import com.liferay.commerce.order.rule.web.display.context.R4F8MinimumQuantityDisplayContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = "commerce.order.rule.entry.type.jsp.contributor.key=r4f8-minimum-quantity-order-rule",
	service = COREntryTypeJSPContributor.class
)
public class R4F8MinimumQuantityCOREntryTypeJSPContributor
	implements COREntryTypeJSPContributor {

	@Override
	public void render(
			long corEntryId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {
		
        COREntry corEntry = _corEntryLocalService.getCOREntry(corEntryId);
        R4F8MinimumQuantityDisplayContext r4f8MinimumQuantityDisplayContext = new R4F8MinimumQuantityDisplayContext(corEntry);
        httpServletRequest.setAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT, r4f8MinimumQuantityDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"minimum_quantity.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;
	
    @Reference(
            target = "(osgi.web.symbolicname=com.acme.r4f8.impl)"
    )
    private ServletContext _servletContext;
	
    @Reference
    private COREntryLocalService _corEntryLocalService;

}