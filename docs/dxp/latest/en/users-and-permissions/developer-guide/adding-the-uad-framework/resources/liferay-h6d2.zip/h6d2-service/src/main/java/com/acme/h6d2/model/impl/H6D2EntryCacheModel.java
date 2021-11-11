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

package com.acme.h6d2.model.impl;

import com.acme.h6d2.model.H6D2Entry;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing H6D2Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class H6D2EntryCacheModel
	implements CacheModel<H6D2Entry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof H6D2EntryCacheModel)) {
			return false;
		}

		H6D2EntryCacheModel h6d2EntryCacheModel = (H6D2EntryCacheModel)object;

		if (h6d2EntryId == h6d2EntryCacheModel.h6d2EntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, h6d2EntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", h6d2EntryId=");
		sb.append(h6d2EntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public H6D2Entry toEntityModel() {
		H6D2EntryImpl h6d2EntryImpl = new H6D2EntryImpl();

		if (uuid == null) {
			h6d2EntryImpl.setUuid("");
		}
		else {
			h6d2EntryImpl.setUuid(uuid);
		}

		h6d2EntryImpl.setH6d2EntryId(h6d2EntryId);
		h6d2EntryImpl.setGroupId(groupId);
		h6d2EntryImpl.setCompanyId(companyId);
		h6d2EntryImpl.setUserId(userId);

		if (userName == null) {
			h6d2EntryImpl.setUserName("");
		}
		else {
			h6d2EntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			h6d2EntryImpl.setCreateDate(null);
		}
		else {
			h6d2EntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			h6d2EntryImpl.setModifiedDate(null);
		}
		else {
			h6d2EntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			h6d2EntryImpl.setName("");
		}
		else {
			h6d2EntryImpl.setName(name);
		}

		h6d2EntryImpl.resetOriginalValues();

		return h6d2EntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		h6d2EntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(h6d2EntryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public String uuid;
	public long h6d2EntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;

}