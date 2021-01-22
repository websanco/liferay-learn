package com.acme.s1j6.internal;

import com.acme.s1j6.S1J6;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = "service.ranking:Integer=100", service = S1J6.class)
public class S1J6CustomImpl implements S1J6 {

	@Override
	public String doSomething() {
		StringBuilder sb = new StringBuilder();

		sb.append(
			"S1J6CustomImpl, which delegates to " +
				_defaultService.doSomething());

		return sb.toString();
	}

	@Reference(
		target = "(component.name=com.acme.s1j6.internal.S1J6Impl)",
		unbind = "-"
	)
	private S1J6 _defaultService;

}