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

			StringBundler sb = new StringBundler(16);

			sb.append("acme/w3r2_able message listener count ");
			sb.append(destination.getMessageListenerCount());

			DestinationStatistics destinationStatistics =
				destination.getDestinationStatistics();

			sb.append(", active thread count ");
			sb.append(destinationStatistics.getActiveThreadCount());
			sb.append(", current thread count ");
			sb.append(destinationStatistics.getCurrentThreadCount());
			sb.append(", largest thread count ");
			sb.append(destinationStatistics.getLargestThreadCount());
			sb.append(", max thread pool size ");
			sb.append(destinationStatistics.getMaxThreadPoolSize());
			sb.append(", min thread pool size ");
			sb.append(destinationStatistics.getMinThreadPoolSize());
			sb.append(", pending message count ");
			sb.append(destinationStatistics.getPendingMessageCount());
			sb.append(", sent message count ");
			sb.append(destinationStatistics.getSentMessageCount());

			_log.info(sb.toString());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		W3R2CharlieOSGiCommands.class);

	@Reference
	private MessageBus _messageBus;

}