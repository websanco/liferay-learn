package com.acme.j1h1.internal;

import com.acme.j1h1.Greeting;

import org.osgi.service.component.annotations.Component;

@Component(service = Greeting.class)
public class J1H1Greeting implements Greeting {

	@Override
	public void greet(String name) {
		System.out.println("Hello " + name + "!");
	}

}