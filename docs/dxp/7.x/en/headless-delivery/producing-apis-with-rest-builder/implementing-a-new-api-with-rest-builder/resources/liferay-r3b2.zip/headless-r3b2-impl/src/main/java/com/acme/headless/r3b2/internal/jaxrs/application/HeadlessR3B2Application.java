package com.acme.headless.r3b2.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonah the son of Amittai
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false", "osgi.jaxrs.application.base=/headless-r3b2",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=Liferay.Headless.R3B2"
	},
	service = Application.class
)
@Generated("")
public class HeadlessR3B2Application extends Application {
}