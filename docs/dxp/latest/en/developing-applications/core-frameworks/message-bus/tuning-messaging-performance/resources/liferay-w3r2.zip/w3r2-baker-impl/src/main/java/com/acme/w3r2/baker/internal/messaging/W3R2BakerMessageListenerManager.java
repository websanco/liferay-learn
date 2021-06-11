package com.acme.w3r2.baker.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component
public class W3R2BakerMessageListenerManager {

	@Activate
	private void _activate() {
		for (int i = 0; i < 10; i++) {
			_messageBus.registerMessageListener(
				"acme/w3r2_able",
				new MessageListener() {

					@Override
					public void receive(Message message) {
						if (_log.isInfoEnabled()) {
							Object payload = message.getPayload();

							_log.info(
								"Received message payload " +
									payload.toString());
						}
					}

				});
		}
	}

	@Deactivate
	private void _deactivate() {
		Destination destination = _messageBus.getDestination("acme/w3r2_able");

		if (destination != null) {
			destination.unregisterMessageListeners();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		W3R2BakerMessageListenerManager.class);

	@Reference
	private MessageBus _messageBus;

}