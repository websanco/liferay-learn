package com.acme.x6n5.charlie.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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

		// message.setResponse("X6N5CharlieMessageListener");

		Message responseMessage = new Message();

		responseMessage.setDestinationName(
			message.getResponseDestinationName());
		responseMessage.setPayload(
			"X6N5CharlieMessageListener");
		responseMessage.setResponseId(message.getResponseId());

		_messageBus.sendMessage(
			message.getResponseDestinationName(), responseMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		X6N5CharlieMessageListener.class);

	@Reference(target = "(mode=DIRECT)")
	private SynchronousMessageSender _directSynchronousMessageSender;

	@Reference
	private MessageBus _messageBus;

}