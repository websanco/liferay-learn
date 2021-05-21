package com.acme.n8k5.baker.internal;

import com.acme.n8k5.able.constants.N8K5AbleDestinationNames;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class N8K5Baker {

	@Activate
	private void _activate(BundleContext bundleContext) {
		Bundle bundle = bundleContext.getBundle();

		Message message = new Message();

		message.setPayload(
			"Invoke N8K5Baker#_activate(BundleContext) from " +
				bundle.getSymbolicName());

		_messageBus.sendMessage(
			N8K5AbleDestinationNames.N8K5_ABLE_DESTINATION, message);
	}

	@Reference
	private MessageBus _messageBus;

}