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

package com.acme.j7z3.model.impl;

import com.acme.j7z3.model.J7Z3Entry;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing J7Z3Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class J7Z3EntryCacheModel
	implements CacheModel<J7Z3Entry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof J7Z3EntryCacheModel)) {
			return false;
		}

		J7Z3EntryCacheModel j7z3EntryCacheModel = (J7Z3EntryCacheModel)object;

		if (j7z3EntryId == j7z3EntryCacheModel.j7z3EntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, j7z3EntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{j7z3EntryId=");
		sb.append(j7z3EntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public J7Z3Entry toEntityModel() {
		J7Z3EntryImpl j7z3EntryImpl = new J7Z3EntryImpl();

		j7z3EntryImpl.setJ7z3EntryId(j7z3EntryId);

		if (name == null) {
			j7z3EntryImpl.setName("");
		}
		else {
			j7z3EntryImpl.setName(name);
		}

		j7z3EntryImpl.resetOriginalValues();

		return j7z3EntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		j7z3EntryId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(j7z3EntryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long j7z3EntryId;
	public String name;

}