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

package com.acme.s5e6.model.impl;

import com.acme.s5e6.model.S5E6Entry;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing S5E6Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class S5E6EntryCacheModel
	implements CacheModel<S5E6Entry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof S5E6EntryCacheModel)) {
			return false;
		}

		S5E6EntryCacheModel s5e6EntryCacheModel = (S5E6EntryCacheModel)object;

		if (S5E6EntryId == s5e6EntryCacheModel.S5E6EntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, S5E6EntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", S5E6EntryId=");
		sb.append(S5E6EntryId);
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
		sb.append(", description=");
		sb.append(description);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public S5E6Entry toEntityModel() {
		S5E6EntryImpl s5e6EntryImpl = new S5E6EntryImpl();

		if (uuid == null) {
			s5e6EntryImpl.setUuid("");
		}
		else {
			s5e6EntryImpl.setUuid(uuid);
		}

		s5e6EntryImpl.setS5E6EntryId(S5E6EntryId);
		s5e6EntryImpl.setGroupId(groupId);
		s5e6EntryImpl.setCompanyId(companyId);
		s5e6EntryImpl.setUserId(userId);

		if (userName == null) {
			s5e6EntryImpl.setUserName("");
		}
		else {
			s5e6EntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			s5e6EntryImpl.setCreateDate(null);
		}
		else {
			s5e6EntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			s5e6EntryImpl.setModifiedDate(null);
		}
		else {
			s5e6EntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (description == null) {
			s5e6EntryImpl.setDescription("");
		}
		else {
			s5e6EntryImpl.setDescription(description);
		}

		if (name == null) {
			s5e6EntryImpl.setName("");
		}
		else {
			s5e6EntryImpl.setName(name);
		}

		s5e6EntryImpl.resetOriginalValues();

		return s5e6EntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		S5E6EntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		description = objectInput.readUTF();
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

		objectOutput.writeLong(S5E6EntryId);

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

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public String uuid;
	public long S5E6EntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String description;
	public String name;

}