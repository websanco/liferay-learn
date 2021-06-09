package com.acme.j1h1.internal.osgi.commands;

import com.acme.j1h1.Greeter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {"osgi.command.function=greet", "osgi.command.scope=j1h1"},
	service = GreeterOSGiCommands.class
)
public class GreeterOSGiCommands {

	public void greet(String name) {
		_greeter.greet(name);
	}

	@Reference
	private Greeter _greeter;

}