package com.acme.b4k8.internal.portlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.util.concurrent.atomic.LongAdder;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"javax.portlet.name=com_liferay_blogs_web_portlet_BlogsPortlet",
		"service.ranking:Integer=100"
	},
	service = PortletFilter.class
)
public class B4K8PortletFilter implements RenderFilter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			RenderRequest request, RenderResponse response, FilterChain chain)
		throws IOException, PortletException {

		long startTime = System.nanoTime();

		chain.doFilter(request, response);

		long renderTime = (System.nanoTime() - startTime) / 1000;
		_hits.increment();
		_accumulatedTimeMs.add(renderTime);

		if (_log.isWarnEnabled()) {
			long totalHits = _hits.longValue();

			long averageRenderTimeNs =
				_accumulatedTimeMs.longValue() / totalHits;

			_log.warn(_PORTLET_NAME + " rendered in " + renderTime + " ms");

			_log.warn(
				_PORTLET_NAME + " rendered " + totalHits +
					" times with an average " + averageRenderTimeNs +
						" ms render time");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
	}

	private static final String _PORTLET_NAME =
		"com_liferay_blogs_web_portlet_BlogsPortlet";

	private static final Log _log = LogFactoryUtil.getLog(
		B4K8PortletFilter.class);

	private final LongAdder _accumulatedTimeMs = new LongAdder();
	private final LongAdder _hits = new LongAdder();

}