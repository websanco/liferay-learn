package com.acme.m4q7.baker.osgi.commands;

import com.acme.m4q7.able.constants.M4Q7AbleDestinationNames;

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
		"destination.name=" + M4Q7BakerOSGiCommands.M4Q7_BAKER_DESTINATION,
		"osgi.command.function=sendPayload", "osgi.command.scope=m4q7.baker"
	},
	service = MessageListener.class
)
public class M4Q7BakerOSGiCommands implements MessageListener {

	public static final String M4Q7_BAKER_DESTINATION =
		"acme/m4q7_baker_destination";

	@Override
	public void receive(Message message) {
		if (_log.isInfoEnabled()) {
			_log.info("Received message " + message.toString());
		}
	}

	public void sendPayload(String payload) {
		try {
			Message message = new Message();

			message.setPayload(payload);
			message.setResponseDestinationName(M4Q7_BAKER_DESTINATION);

			_synchronousMessageSender.send(
				M4Q7AbleDestinationNames.M4Q7_ABLE_DESTINATION, message, 10000);
		}
		catch (MessageBusException messageBusException) {
			messageBusException.printStackTrace();
		}
	}

	@Activate
	private void _activate(BundleContext bundleContext) {
		DestinationConfiguration destinationConfiguration =
			DestinationConfiguration.createSerialDestinationConfiguration(
				M4Q7_BAKER_DESTINATION);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

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
		M4Q7BakerOSGiCommands.class);

	@Reference
	private DestinationFactory _destinationFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

	@Reference(target = "(mode=DEFAULT)")
	private SynchronousMessageSender _synchronousMessageSender;

}