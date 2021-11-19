package com.acme.s3z9.able.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBusEventListener;

import org.osgi.service.component.annotations.Component;

@Component(service = MessageBusEventListener.class)
public class S3Z9AbleMessageBusEventListener
	implements MessageBusEventListener {

	@Override
	public void destinationAdded(Destination destination) {
		if (_log.isInfoEnabled()) {
			_log.info("Added destination " + destination.getName());
		}
	}

	@Override
	public void destinationRemoved(Destination destination) {
		if (_log.isInfoEnabled()) {
			_log.info("Removed destination " + destination.getName());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		S3Z9AbleMessageBusEventListener.class);

}