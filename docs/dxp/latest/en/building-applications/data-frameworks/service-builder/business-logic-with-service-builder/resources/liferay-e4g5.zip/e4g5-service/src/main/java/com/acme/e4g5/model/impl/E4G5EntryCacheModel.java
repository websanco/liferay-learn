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

package com.acme.e4g5.model.impl;

import com.acme.e4g5.model.E4G5Entry;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing E4G5Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class E4G5EntryCacheModel
	implements CacheModel<E4G5Entry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof E4G5EntryCacheModel)) {
			return false;
		}

		E4G5EntryCacheModel e4g5EntryCacheModel = (E4G5EntryCacheModel)object;

		if (e4g5EntryId == e4g5EntryCacheModel.e4g5EntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, e4g5EntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{e4g5EntryId=");
		sb.append(e4g5EntryId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public E4G5Entry toEntityModel() {
		E4G5EntryImpl e4g5EntryImpl = new E4G5EntryImpl();

		e4g5EntryImpl.setE4g5EntryId(e4g5EntryId);

		if (description == null) {
			e4g5EntryImpl.setDescription("");
		}
		else {
			e4g5EntryImpl.setDescription(description);
		}

		if (name == null) {
			e4g5EntryImpl.setName("");
		}
		else {
			e4g5EntryImpl.setName(name);
		}

		e4g5EntryImpl.resetOriginalValues();

		return e4g5EntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		e4g5EntryId = objectInput.readLong();
		description = objectInput.readUTF();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(e4g5EntryId);

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

	public long e4g5EntryId;
	public String description;
	public String name;

}