package com.acme.j1h1.internal;

import com.acme.j1h1.Greeter;

import org.osgi.service.component.annotations.Component;

@Component(service = Greeter.class)
public class J1H1Greeter implements Greeter {

	@Override
	public void greet(String name) {
		System.out.println("Hello " + name + "!");
	}

}