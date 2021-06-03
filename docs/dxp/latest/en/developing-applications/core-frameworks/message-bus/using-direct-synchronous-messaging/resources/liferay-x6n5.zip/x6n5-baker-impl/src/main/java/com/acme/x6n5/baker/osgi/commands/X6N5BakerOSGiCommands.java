package com.acme.x6n5.baker.osgi.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;
import com.liferay.portal.kernel.util.MapUtil;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"destination.name=" + X6N5BakerOSGiCommands.X6N5_BAKER_DESTINATION,
		"osgi.command.function=sendMessage", "osgi.command.scope=x6n5.baker"
	},
	service = MessageListener.class
)
public class X6N5BakerOSGiCommands implements MessageListener {

	public static final String X6N5_BAKER_DESTINATION = "acme/x6n5_baker";

	@Override
	public void receive(Message message) {
		if (_log.isInfoEnabled()) {
			Object payload = message.getPayload();

			_log.info("Received message payload " + payload.toString());
		}
	}

	public void sendMessage(String payload) {
		try {
			Message message = new Message();

			message.setPayload(payload);

			message.setResponseDestinationName("acme/x6n5_baker");

			Object response = _directSynchronousMessageSender.send("acme/x6n5_able", message);

			if (_log.isInfoEnabled()) {
				_log.info("SynchronousMessageSender#send(String, Message) returned " + response);
			}
		}
		catch (MessageBusException messageBusException) {
			messageBusException.printStackTrace();
		}
	}

	@Activate
	private void _activate(BundleContext bundleContext) {
		Destination destination = _destinationFactory.createDestination(
			DestinationConfiguration.createSynchronousDestinationConfiguration(
				"acme/x6n5_baker"));

		_serviceRegistration = bundleContext.registerService(
			Destination.class, destination,
			MapUtil.singletonDictionary(
				"destination.name", destination.getName()));
	}

	@Deactivate
	private void _deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		X6N5BakerOSGiCommands.class);

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference(target = "(mode=DIRECT)")
	private SynchronousMessageSender _directSynchronousMessageSender;

	private ServiceRegistration<Destination> _serviceRegistration;

}