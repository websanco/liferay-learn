package com.acme.x9k1.internal.commerce.order.rule.web.entry.type;

import com.liferay.commerce.order.rule.entry.type.COREntryTypeJSPContributor;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.commerce.order.rule.entry.type.COREntryTypeJSPContributor;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.service.COREntryLocalService;
import com.acme.x9k1.internal.commerce.order.rule.web.display.context.X9K1MinimumQuantityDisplayContext;
import javax.servlet.ServletContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = "commerce.order.rule.entry.type.jsp.contributor.key=minimum-quantity-order-rule",
	service = COREntryTypeJSPContributor.class
)
public class X9K1MinimumQuantityCOREntryTypeJSPContributor
	implements COREntryTypeJSPContributor {

	@Override
	public void render(
			long corEntryId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {
		_log.debug("CorEntryID: " + corEntryId);
        COREntry corEntry = _corEntryLocalService.getCOREntry(corEntryId);
        _log.debug("CorEntry: " + corEntry);
        X9K1MinimumQuantityDisplayContext x9k1MinimumQuantityDisplayContext = new X9K1MinimumQuantityDisplayContext(corEntry);
        _log.debug("DisplayContext: " + x9k1MinimumQuantityDisplayContext);
        httpServletRequest.setAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT, x9k1MinimumQuantityDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/minimum_quantity.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;
	
    @Reference(
            target = "(osgi.web.symbolicname=com.acme.x9k1.impl)"
    )
    private ServletContext _servletContext;
	
    @Reference
    private COREntryLocalService _corEntryLocalService;
    
    private static final Log _log = LogFactoryUtil.getLog(
    		X9K1MinimumQuantityCOREntryTypeJSPContributor.class);

}