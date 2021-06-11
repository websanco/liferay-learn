package com.acme.w3r2.able.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.MapUtil;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component
public class W3R2AbleMessagingConfigurator {

	@Activate
	private void _activate(BundleContext bundleContext) {
		DestinationConfiguration destinationConfiguration =
			DestinationConfiguration.createSerialDestinationConfiguration(
				"acme/w3r2_able");

		if (_log.isInfoEnabled()) {
			_log.info(destinationConfiguration.toString());
		}

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
		W3R2AbleMessagingConfigurator.class);

	@Reference
	private DestinationFactory _destinationFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

}