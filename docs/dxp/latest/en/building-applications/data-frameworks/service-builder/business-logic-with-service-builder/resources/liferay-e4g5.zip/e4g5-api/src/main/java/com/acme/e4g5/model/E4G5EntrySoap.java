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

package com.acme.e4g5.model;

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
public class E4G5EntrySoap implements Serializable {

	public static E4G5EntrySoap toSoapModel(E4G5Entry model) {
		E4G5EntrySoap soapModel = new E4G5EntrySoap();

		soapModel.setE4g5EntryId(model.getE4g5EntryId());
		soapModel.setDescription(model.getDescription());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static E4G5EntrySoap[] toSoapModels(E4G5Entry[] models) {
		E4G5EntrySoap[] soapModels = new E4G5EntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static E4G5EntrySoap[][] toSoapModels(E4G5Entry[][] models) {
		E4G5EntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new E4G5EntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new E4G5EntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static E4G5EntrySoap[] toSoapModels(List<E4G5Entry> models) {
		List<E4G5EntrySoap> soapModels = new ArrayList<E4G5EntrySoap>(
			models.size());

		for (E4G5Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new E4G5EntrySoap[soapModels.size()]);
	}

	public E4G5EntrySoap() {
	}

	public long getPrimaryKey() {
		return _e4g5EntryId;
	}

	public void setPrimaryKey(long pk) {
		setE4g5EntryId(pk);
	}

	public long getE4g5EntryId() {
		return _e4g5EntryId;
	}

	public void setE4g5EntryId(long e4g5EntryId) {
		_e4g5EntryId = e4g5EntryId;
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

	private long _e4g5EntryId;
	private String _description;
	private String _name;

}