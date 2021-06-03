package com.acme.x6n5.charlie.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "destination.name=acme/x6n5_able",
	service = MessageListener.class
)
public class X6N5CharlieMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		if (_log.isInfoEnabled()) {
			Object payload = message.getPayload();

			_log.info("Received message payload " + payload.toString());
		}

		message.setResponse("X6N5CharlieMessageListener");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		X6N5CharlieMessageListener.class);

}