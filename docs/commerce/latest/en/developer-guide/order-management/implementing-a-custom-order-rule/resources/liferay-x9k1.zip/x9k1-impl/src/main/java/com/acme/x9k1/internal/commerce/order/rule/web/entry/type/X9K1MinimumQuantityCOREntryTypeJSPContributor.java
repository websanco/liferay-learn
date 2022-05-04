package com.acme.x9k1.internal.commerce.order.rule.web.entry.type;

import com.acme.x9k1.internal.commerce.order.rule.web.display.context.X9K1MinimumQuantityDisplayContext;

import com.liferay.commerce.order.rule.entry.type.COREntryTypeJSPContributor;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.service.COREntryLocalService;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = "commerce.order.rule.entry.type.jsp.contributor.key=x9k1-minimum-quantity-order-rule",
	service = COREntryTypeJSPContributor.class
)
public class X9K1MinimumQuantityCOREntryTypeJSPContributor
	implements COREntryTypeJSPContributor {

	@Override
	public void render(
			long corEntryId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		COREntry corEntry = _corEntryLocalService.getCOREntry(corEntryId);

		X9K1MinimumQuantityDisplayContext x9k1MinimumQuantityDisplayContext =
			new X9K1MinimumQuantityDisplayContext(corEntry);

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, x9k1MinimumQuantityDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/minimum_quantity.jsp");
	}

	@Reference
	private COREntryLocalService _corEntryLocalService;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(target = "(osgi.web.symbolicname=com.acme.x9k1.impl)")
	private ServletContext _servletContext;

}