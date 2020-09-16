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

package com.acme.p9g2.internal;

import com.acme.p9g2.Greeting;

import org.osgi.service.component.annotations.Component;

/**
 * @author James Hinkey
 */
@Component(service = Greeting.class)
public class P9G2Greeting implements Greeting {

	@Override
	public void greet(String name) {
		System.out.println("Hello " + name + "!");
	}

}