package com.acme.w3r2.charlie.internal.osgi.commands;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationStatistics;
import com.liferay.portal.kernel.messaging.MessageBus;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"osgi.command.function=listDestinationStats", "osgi.command.scope=w3r2"
	},
	service = W3R2CharlieOSGiCommands.class
)
public class W3R2CharlieOSGiCommands {

	public void listDestinationStats() {
		if (_log.isInfoEnabled()) {
			Destination destination = _messageBus.getDestination(
				"acme/w3r2_able");

			DestinationStatistics destinationStatistics =
				destination.getDestinationStatistics();

			_log.info(
				StringBundler.concat(
					"acme/w3r2_able active thread count ",
					destinationStatistics.getActiveThreadCount(),
					", current thread count ",
					destinationStatistics.getCurrentThreadCount(),
					", largest thread count ",
					destinationStatistics.getLargestThreadCount(),
					", max thread pool size ",
					destinationStatistics.getMaxThreadPoolSize(),
					", message listener count ",
					destination.getMessageListenerCount(),
					", min thread pool size ",
					destinationStatistics.getMinThreadPoolSize(),
					", pending message count ",
					destinationStatistics.getPendingMessageCount(),
					", sent message count ",
					destinationStatistics.getSentMessageCount()));
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		W3R2CharlieOSGiCommands.class);

	@Reference
	private MessageBus _messageBus;

}