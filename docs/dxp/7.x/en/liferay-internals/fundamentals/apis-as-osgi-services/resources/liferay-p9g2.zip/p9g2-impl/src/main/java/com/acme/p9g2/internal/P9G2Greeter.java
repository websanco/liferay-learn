package com.acme.p9g2.internal;

import com.acme.p9g2.Greeter;

import org.osgi.service.component.annotations.Component;

@Component(service = Greeter.class)
public class P9G2Greeter implements Greeter {

	@Override
	public void greet(String name) {
		System.out.println("Hello " + name + "!");
	}

}