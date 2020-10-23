package com.acme.p9g2.internal;

import com.acme.p9g2.Greeting;

import org.osgi.service.component.annotations.Component;

@Component(service = Greeting.class)
public class P9G2Greeting implements Greeting {

	@Override
	public void greet(String name) {
		System.out.println("Hello " + name + "!");
	}

}