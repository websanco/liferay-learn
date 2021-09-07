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

package com.acme.w9b7.model.impl;

import com.acme.w9b7.model.W9B7Entry;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing W9B7Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class W9B7EntryCacheModel
	implements CacheModel<W9B7Entry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof W9B7EntryCacheModel)) {
			return false;
		}

		W9B7EntryCacheModel w9b7EntryCacheModel = (W9B7EntryCacheModel)object;

		if (w9b7EntryId == w9b7EntryCacheModel.w9b7EntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, w9b7EntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{w9b7EntryId=");
		sb.append(w9b7EntryId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public W9B7Entry toEntityModel() {
		W9B7EntryImpl w9b7EntryImpl = new W9B7EntryImpl();

		w9b7EntryImpl.setW9b7EntryId(w9b7EntryId);

		if (description == null) {
			w9b7EntryImpl.setDescription("");
		}
		else {
			w9b7EntryImpl.setDescription(description);
		}

		if (name == null) {
			w9b7EntryImpl.setName("");
		}
		else {
			w9b7EntryImpl.setName(name);
		}

		w9b7EntryImpl.resetOriginalValues();

		return w9b7EntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		w9b7EntryId = objectInput.readLong();
		description = objectInput.readUTF();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(w9b7EntryId);

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

	public long w9b7EntryId;
	public String description;
	public String name;

}