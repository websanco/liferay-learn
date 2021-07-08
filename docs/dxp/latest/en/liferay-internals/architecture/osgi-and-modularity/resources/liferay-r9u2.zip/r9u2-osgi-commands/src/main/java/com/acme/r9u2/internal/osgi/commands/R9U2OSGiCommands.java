package com.acme.r9u2.internal.osgi.commands;

import com.acme.r9u2.Greeter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {"osgi.command.function=greet", "osgi.command.scope=r9u2"},
	service = R9U2OSGiCommands.class
)
public class R9U2OSGiCommands {

	public void greet(String name) {
		_greeter.greet(name);
	}

	@Reference
	private Greeter _greeter;

}