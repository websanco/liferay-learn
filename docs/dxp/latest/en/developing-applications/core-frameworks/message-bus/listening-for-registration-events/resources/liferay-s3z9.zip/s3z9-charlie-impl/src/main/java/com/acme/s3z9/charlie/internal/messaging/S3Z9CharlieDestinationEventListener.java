package com.acme.s3z9.charlie.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationEventListener;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "destination.name=acme/s3z9_baker",
	service = DestinationEventListener.class
)
public class S3Z9CharlieDestinationEventListener
	implements DestinationEventListener {

	@Override
	public void messageListenerRegistered(
		String destinationName, MessageListener messageListener) {

		if (_log.isInfoEnabled()) {
			_log.info("Registered message listener to " + destinationName);
		}
	}

	@Override
	public void messageListenerUnregistered(
		String destinationName, MessageListener messageListener) {

		if (_log.isInfoEnabled()) {
			_log.info("Unregistered message listener from " + destinationName);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		S3Z9CharlieDestinationEventListener.class);

}