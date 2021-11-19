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

package com.acme.t2p5.model.impl;

import com.acme.t2p5.model.T2P5Entry;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing T2P5Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class T2P5EntryCacheModel
	implements CacheModel<T2P5Entry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof T2P5EntryCacheModel)) {
			return false;
		}

		T2P5EntryCacheModel t2p5EntryCacheModel = (T2P5EntryCacheModel)object;

		if (t2p5EntryId == t2p5EntryCacheModel.t2p5EntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, t2p5EntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{t2p5EntryId=");
		sb.append(t2p5EntryId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public T2P5Entry toEntityModel() {
		T2P5EntryImpl t2p5EntryImpl = new T2P5EntryImpl();

		t2p5EntryImpl.setT2p5EntryId(t2p5EntryId);

		if (description == null) {
			t2p5EntryImpl.setDescription("");
		}
		else {
			t2p5EntryImpl.setDescription(description);
		}

		if (name == null) {
			t2p5EntryImpl.setName("");
		}
		else {
			t2p5EntryImpl.setName(name);
		}

		t2p5EntryImpl.resetOriginalValues();

		return t2p5EntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		t2p5EntryId = objectInput.readLong();
		description = objectInput.readUTF();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(t2p5EntryId);

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

	public long t2p5EntryId;
	public String description;
	public String name;

}