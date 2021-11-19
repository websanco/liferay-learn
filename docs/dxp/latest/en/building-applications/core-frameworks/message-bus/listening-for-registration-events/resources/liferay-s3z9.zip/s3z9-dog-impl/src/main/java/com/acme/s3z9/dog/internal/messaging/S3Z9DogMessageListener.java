package com.acme.s3z9.dog.internal.messaging;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "destination.name=acme/s3z9_baker",
	service = MessageListener.class
)
public class S3Z9DogMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
	}

}