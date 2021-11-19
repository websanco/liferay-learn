package com.acme.n8k5.charlie.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "destination.name=acme/n8k5_able",
	service = MessageListener.class
)
public class N8K5CharlieMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		if (_log.isInfoEnabled()) {
			_log.info("Received message payload " + message.getPayload());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		N8K5CharlieMessageListener.class);

}