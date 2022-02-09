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

package com.acme.p5d2.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class P5D2EntrySoap implements Serializable {

	public static P5D2EntrySoap toSoapModel(P5D2Entry model) {
		P5D2EntrySoap soapModel = new P5D2EntrySoap();

		soapModel.setP5d2EntryId(model.getP5d2EntryId());
		soapModel.setAble(model.getAble());

		return soapModel;
	}

	public static P5D2EntrySoap[] toSoapModels(P5D2Entry[] models) {
		P5D2EntrySoap[] soapModels = new P5D2EntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static P5D2EntrySoap[][] toSoapModels(P5D2Entry[][] models) {
		P5D2EntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new P5D2EntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new P5D2EntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static P5D2EntrySoap[] toSoapModels(List<P5D2Entry> models) {
		List<P5D2EntrySoap> soapModels = new ArrayList<P5D2EntrySoap>(
			models.size());

		for (P5D2Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new P5D2EntrySoap[soapModels.size()]);
	}

	public P5D2EntrySoap() {
	}

	public long getPrimaryKey() {
		return _p5d2EntryId;
	}

	public void setPrimaryKey(long pk) {
		setP5d2EntryId(pk);
	}

	public long getP5d2EntryId() {
		return _p5d2EntryId;
	}

	public void setP5d2EntryId(long p5d2EntryId) {
		_p5d2EntryId = p5d2EntryId;
	}

	public String getAble() {
		return _able;
	}

	public void setAble(String able) {
		_able = able;
	}

	private long _p5d2EntryId;
	private String _able;

}