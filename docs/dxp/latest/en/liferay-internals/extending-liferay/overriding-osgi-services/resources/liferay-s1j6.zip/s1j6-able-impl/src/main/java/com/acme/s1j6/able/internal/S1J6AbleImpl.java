package com.acme.s1j6.able.internal;

import com.acme.s1j6.S1J6;

import org.osgi.service.component.annotations.Component;

@Component(service = S1J6.class)
public class S1J6AbleImpl implements S1J6 {

	@Override
	public String doSomething() {
		return "This is the S1J6 Able implementation.";
	}

}