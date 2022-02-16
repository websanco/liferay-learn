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

package com.acme.p5d2.model.impl;

import com.acme.p5d2.model.P5D2Entry;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing P5D2Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class P5D2EntryCacheModel
	implements CacheModel<P5D2Entry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof P5D2EntryCacheModel)) {
			return false;
		}

		P5D2EntryCacheModel p5d2EntryCacheModel = (P5D2EntryCacheModel)object;

		if (p5d2EntryId == p5d2EntryCacheModel.p5d2EntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, p5d2EntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{p5d2EntryId=");
		sb.append(p5d2EntryId);
		sb.append(", able=");
		sb.append(able);
		sb.append(", baker=");
		sb.append(baker);
		sb.append(", foo=");
		sb.append(foo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public P5D2Entry toEntityModel() {
		P5D2EntryImpl p5d2EntryImpl = new P5D2EntryImpl();

		p5d2EntryImpl.setP5d2EntryId(p5d2EntryId);
		p5d2EntryImpl.setAble(able);
		p5d2EntryImpl.setBaker(baker);

		if (foo == null) {
			p5d2EntryImpl.setFoo("");
		}
		else {
			p5d2EntryImpl.setFoo(foo);
		}

		p5d2EntryImpl.resetOriginalValues();

		return p5d2EntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		p5d2EntryId = objectInput.readLong();

		able = objectInput.readLong();

		baker = objectInput.readBoolean();
		foo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(p5d2EntryId);

		objectOutput.writeLong(able);

		objectOutput.writeBoolean(baker);

		if (foo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(foo);
		}
	}

	public long p5d2EntryId;
	public long able;
	public boolean baker;
	public String foo;

}