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

package com.acme.s5e6.service.impl;

import com.acme.s5e6.model.S5E6Entry;
import com.acme.s5e6.service.base.S5E6EntryLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.acme.s5e6.model.S5E6Entry",
	service = AopService.class
)
public class S5E6EntryLocalServiceImpl extends S5E6EntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	public S5E6Entry addS5E6Entry(
			long companyId, long groupId, long userId, String userName,
			String item, ServiceContext serviceContext)
		throws PortalException {

		S5E6Entry s5e6Entry = s5e6EntryPersistence.create(
			counterLocalService.increment());

		Date now = new Date();

		s5e6Entry.setCompanyId(companyId);
		s5e6Entry.setCreateDate(serviceContext.getCreateDate(now));
		s5e6Entry.setGroupId(groupId);
		s5e6Entry.setModifiedDate(serviceContext.getModifiedDate(now));
		s5e6Entry.setName(item);
		s5e6Entry.setUserId(userId);
		s5e6Entry.setUserName(userName);

		s5e6Entry = s5e6EntryPersistence.update(s5e6Entry);

		assetEntryLocalService.updateEntry(
			userId, groupId, s5e6Entry.getCreateDate(),
			s5e6Entry.getModifiedDate(), S5E6Entry.class.getName(),
			s5e6Entry.getS5E6EntryId(), s5e6Entry.getUuid(), 0, null, null,
			true, true, null, null, null, null, ContentTypes.TEXT,
			s5e6Entry.getName(), null, null, null, null, 0, 0, 1.0);

		return s5e6Entry;
	}

}