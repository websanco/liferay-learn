package com.acme.j1h1.osgi.commands;

import com.acme.j1h1.Greeting;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {"osgi.command.function=greet", "osgi.command.scope=j1h1"},
	service = Object.class
)
public class GreetingOSGiCommands {

	public void greet(String name) {
		_greeting.greet(name);
	}

	@Reference
	private Greeting _greeting;

}