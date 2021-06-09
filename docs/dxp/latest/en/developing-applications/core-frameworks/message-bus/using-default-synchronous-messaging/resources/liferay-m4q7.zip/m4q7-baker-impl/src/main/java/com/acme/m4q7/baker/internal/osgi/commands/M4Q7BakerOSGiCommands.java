package com.acme.m4q7.baker.internal.osgi.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {"osgi.command.function=sendMessage", "osgi.command.scope=m4q7"},
	service = M4Q7BakerOSGiCommands.class
)
public class M4Q7BakerOSGiCommands {

	public void sendMessage(String payload) throws MessageBusException {
		Message message = new Message();

		message.setPayload(payload);
		message.setResponseDestinationName("acme/m4q7_baker");

		Object response = _synchronousMessageSender.send(
			"acme/m4q7_able", message, 10000);

		if (_log.isInfoEnabled()) {
			_log.info("Response: " + response);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		M4Q7BakerOSGiCommands.class);

	@Reference(target = "(mode=DEFAULT)")
	private SynchronousMessageSender _synchronousMessageSender;

}