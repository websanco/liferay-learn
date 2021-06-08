package com.acme.m4q7.baker.osgi.commands;

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
		"destination.name=acme/m4q7_baker", "osgi.command.function=sendMessage",
		"osgi.command.scope=m4q7.baker"
	},
	service = MessageListener.class
)
public class M4Q7MessageSenderOSGiCommands implements MessageListener {

	@Override
	public void receive(Message message) {
		Object payload = message.getPayload();

		_log.info("Received message payload " + payload.toString());
	}

	public void sendMessage(String payload) {
		try {
			Message message = new Message();

			message.setPayload(payload);
			message.setResponseDestinationName("acme/m4q7_baker");

			Object response = _synchronousMessageSender.send(
				"acme/m4q7_able", message, 10000);

			if (_log.isInfoEnabled()) {
				_log.info(
					"SynchronousMessageSender#send(String, Message, long) " +
						"returned " + response);
			}
		}
		catch (MessageBusException messageBusException) {
			messageBusException.printStackTrace();
		}
	}

	@Activate
	private void _activate(BundleContext bundleContext) {
		Destination destination = _destinationFactory.createDestination(
			DestinationConfiguration.createSerialDestinationConfiguration(
				"acme/m4q7_baker"));

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
		M4Q7MessageSenderOSGiCommands.class);

	@Reference
	private DestinationFactory _destinationFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

	@Reference(target = "(mode=DEFAULT)")
	private SynchronousMessageSender _synchronousMessageSender;

}