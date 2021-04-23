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
			RenderRequest renderRequest, RenderResponse renderResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		long startTime = System.currentTimeMillis();

		filterChain.doFilter(renderRequest, renderResponse);

		long renderTime = (System.currentTimeMillis() - startTime) / 1000;

		_totalTime.add(renderTime);

		_count.increment();

		if (_log.isInfoEnabled()) {
			long count = _count.longValue();

			long averageRenderTime = _totalTime.longValue() / count;

			_log.info(
				"Blogs portlet rendered in " + renderTime +
					" ms with an average of " + averageRenderTime +
						" ms out of " + count + " renders.");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		B4K8PortletFilter.class);

	private final LongAdder _count = new LongAdder();
	private final LongAdder _totalTime = new LongAdder();

}