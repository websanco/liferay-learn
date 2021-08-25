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

package com.acme.h6d2.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link H6D2Service}.
 *
 * @author Brian Wing Shun Chan
 * @see H6D2Service
 * @generated
 */
public class H6D2ServiceWrapper
	implements H6D2Service, ServiceWrapper<H6D2Service> {

	public H6D2ServiceWrapper(H6D2Service h6d2Service) {
		_h6d2Service = h6d2Service;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _h6d2Service.getOSGiServiceIdentifier();
	}

	@Override
	public H6D2Service getWrappedService() {
		return _h6d2Service;
	}

	@Override
	public void setWrappedService(H6D2Service h6d2Service) {
		_h6d2Service = h6d2Service;
	}

	private H6D2Service _h6d2Service;

}