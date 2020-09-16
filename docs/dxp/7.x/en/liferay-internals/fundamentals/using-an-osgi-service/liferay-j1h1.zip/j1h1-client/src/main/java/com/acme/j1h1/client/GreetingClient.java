/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.acme.j1h1.client;

import com.acme.j1h1.Greeting;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author James Hinkey
 */
@Component(
	property = {"osgi.command.function=greet", "osgi.command.scope=greet"},
	service = Object.class
)
public class GreetingClient {

	public void greet(String name) {
		_greeting.greet(name);
	}

	@Reference
	private Greeting _greeting;

}