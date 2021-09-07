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

package com.acme.w9b7.model;

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
public class W9B7EntrySoap implements Serializable {

	public static W9B7EntrySoap toSoapModel(W9B7Entry model) {
		W9B7EntrySoap soapModel = new W9B7EntrySoap();

		soapModel.setW9b7EntryId(model.getW9b7EntryId());
		soapModel.setDescription(model.getDescription());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static W9B7EntrySoap[] toSoapModels(W9B7Entry[] models) {
		W9B7EntrySoap[] soapModels = new W9B7EntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static W9B7EntrySoap[][] toSoapModels(W9B7Entry[][] models) {
		W9B7EntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new W9B7EntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new W9B7EntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static W9B7EntrySoap[] toSoapModels(List<W9B7Entry> models) {
		List<W9B7EntrySoap> soapModels = new ArrayList<W9B7EntrySoap>(
			models.size());

		for (W9B7Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new W9B7EntrySoap[soapModels.size()]);
	}

	public W9B7EntrySoap() {
	}

	public long getPrimaryKey() {
		return _w9b7EntryId;
	}

	public void setPrimaryKey(long pk) {
		setW9b7EntryId(pk);
	}

	public long getW9b7EntryId() {
		return _w9b7EntryId;
	}

	public void setW9b7EntryId(long w9b7EntryId) {
		_w9b7EntryId = w9b7EntryId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _w9b7EntryId;
	private String _description;
	private String _name;

}