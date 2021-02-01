package com.acme.s1j6.baker.internal;

import com.acme.s1j6.S1J6;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = "service.ranking:Integer=100", service = S1J6.class)
public class S1J6BakerImpl implements S1J6 {

	@Override
	public String doSomething() {
		return _s1j6.doSomething() +
			"<br />This is the S1J6 Baker implementation.";
	}

	@Reference(
		target = "(component.name=com.acme.s1j6.able.internal.S1J6AbleImpl)"
	)
	private S1J6 _s1j6;

}