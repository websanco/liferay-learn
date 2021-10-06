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

package com.acme.s5e6.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link S5E6EntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see S5E6EntryService
 * @generated
 */
public class S5E6EntryServiceWrapper
	implements S5E6EntryService, ServiceWrapper<S5E6EntryService> {

	public S5E6EntryServiceWrapper(S5E6EntryService s5e6EntryService) {
		_s5e6EntryService = s5e6EntryService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _s5e6EntryService.getOSGiServiceIdentifier();
	}

	@Override
	public S5E6EntryService getWrappedService() {
		return _s5e6EntryService;
	}

	@Override
	public void setWrappedService(S5E6EntryService s5e6EntryService) {
		_s5e6EntryService = s5e6EntryService;
	}

	private S5E6EntryService _s5e6EntryService;

}