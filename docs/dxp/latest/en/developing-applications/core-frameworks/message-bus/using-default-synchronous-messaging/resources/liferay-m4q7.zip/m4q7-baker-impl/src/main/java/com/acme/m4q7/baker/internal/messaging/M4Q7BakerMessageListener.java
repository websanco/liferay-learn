package com.acme.m4q7.baker.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "destination.name=acme/m4q7_baker",
	service = MessageListener.class
)
public class M4Q7BakerMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		if (_log.isInfoEnabled()) {
			Object payload = message.getPayload();

			_log.info("Received message payload " + payload.toString());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		M4Q7BakerMessageListener.class);

}