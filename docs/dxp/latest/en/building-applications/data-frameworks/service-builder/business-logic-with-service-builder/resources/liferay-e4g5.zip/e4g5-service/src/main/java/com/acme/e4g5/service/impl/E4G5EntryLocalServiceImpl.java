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

package com.acme.e4g5.service.impl;

import com.acme.e4g5.model.E4G5Entry;
import com.acme.e4g5.service.base.E4G5EntryLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.acme.e4g5.model.E4G5Entry",
	service = AopService.class
)
public class E4G5EntryLocalServiceImpl extends E4G5EntryLocalServiceBaseImpl {

	public E4G5Entry addE4G5Entry(String description, String name)
		throws PortalException {

		E4G5Entry e4g5Entry = e4g5EntryPersistence.create(
			counterLocalService.increment());

		e4g5Entry.setDescription(description);
		e4g5Entry.setName(name);

		return e4g5EntryPersistence.update(e4g5Entry);
	}

	public E4G5Entry deleteE4G5Entry(long e4g5EntryId)
		throws PortalException {

		E4G5Entry e4g5Entry = e4g5EntryPersistence.findByPrimaryKey(
			e4g5EntryId);

		e4g5EntryPersistence.remove(e4g5Entry);

		return e4g5Entry;
	}

	public E4G5Entry updateE4G5Entry(
			long e4g5EntryId, String description, String name)
		throws PortalException {

		E4G5Entry e4g5Entry = e4g5EntryPersistence.findByPrimaryKey(
			e4g5EntryId);

		e4g5Entry.setDescription(description);
		e4g5Entry.setName(name);

		return e4g5EntryPersistence.update(e4g5Entry);
	}

}