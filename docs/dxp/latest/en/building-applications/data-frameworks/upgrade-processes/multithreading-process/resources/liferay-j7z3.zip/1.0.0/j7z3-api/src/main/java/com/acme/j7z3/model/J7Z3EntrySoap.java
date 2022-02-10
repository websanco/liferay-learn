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

package com.acme.j7z3.model;

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
public class J7Z3EntrySoap implements Serializable {

	public static J7Z3EntrySoap toSoapModel(J7Z3Entry model) {
		J7Z3EntrySoap soapModel = new J7Z3EntrySoap();

		soapModel.setJ7z3EntryId(model.getJ7z3EntryId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static J7Z3EntrySoap[] toSoapModels(J7Z3Entry[] models) {
		J7Z3EntrySoap[] soapModels = new J7Z3EntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static J7Z3EntrySoap[][] toSoapModels(J7Z3Entry[][] models) {
		J7Z3EntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new J7Z3EntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new J7Z3EntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static J7Z3EntrySoap[] toSoapModels(List<J7Z3Entry> models) {
		List<J7Z3EntrySoap> soapModels = new ArrayList<J7Z3EntrySoap>(
			models.size());

		for (J7Z3Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new J7Z3EntrySoap[soapModels.size()]);
	}

	public J7Z3EntrySoap() {
	}

	public long getPrimaryKey() {
		return _j7z3EntryId;
	}

	public void setPrimaryKey(long pk) {
		setJ7z3EntryId(pk);
	}

	public long getJ7z3EntryId() {
		return _j7z3EntryId;
	}

	public void setJ7z3EntryId(long j7z3EntryId) {
		_j7z3EntryId = j7z3EntryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _j7z3EntryId;
	private String _name;

}