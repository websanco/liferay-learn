package com.acme.s1j6.internal;

import com.acme.s1j6.S1J6;

import org.osgi.service.component.annotations.Component;

@Component(service = S1J6.class)
public class S1J6Impl implements S1J6 {

	@Override
	public String doSomething() {
		return "S1J6";
	}

}