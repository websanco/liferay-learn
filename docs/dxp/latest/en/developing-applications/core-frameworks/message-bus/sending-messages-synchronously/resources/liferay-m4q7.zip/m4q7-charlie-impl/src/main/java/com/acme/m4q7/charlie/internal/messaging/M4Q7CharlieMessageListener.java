package com.acme.m4q7.charlie.internal.messaging;

import com.acme.m4q7.able.constants.M4Q7AbleDestinationNames;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "destination.name=" + M4Q7AbleDestinationNames.M4Q7_ABLE_DESTINATION,
	service = MessageListener.class
)
public class M4Q7CharlieMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		if (_log.isInfoEnabled()) {
			Object payload = message.getPayload();

			_log.info("Received message payload " + payload.toString());

			Message responseMessage = new Message();

			responseMessage.setDestinationName(
				message.getResponseDestinationName());
			responseMessage.setPayload(
				"Payload from M4Q7CharlieMessageListener");
			responseMessage.setResponseId(message.getResponseId());

			_messageBus.sendMessage(
				message.getResponseDestinationName(), responseMessage);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		M4Q7CharlieMessageListener.class);

	@Reference
	private MessageBus _messageBus;

}