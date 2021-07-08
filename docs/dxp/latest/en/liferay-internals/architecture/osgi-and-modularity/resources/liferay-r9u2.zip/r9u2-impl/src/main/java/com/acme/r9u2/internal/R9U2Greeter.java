package com.acme.r9u2.internal;

import com.acme.r9u2.Greeter;

import org.osgi.service.component.annotations.Component;

@Component(service = Greeter.class)
public class R9U2Greeter implements Greeter {

	@Override
	public void greet(String name) {
		System.out.println("Hello " + name + "!");
	}

}