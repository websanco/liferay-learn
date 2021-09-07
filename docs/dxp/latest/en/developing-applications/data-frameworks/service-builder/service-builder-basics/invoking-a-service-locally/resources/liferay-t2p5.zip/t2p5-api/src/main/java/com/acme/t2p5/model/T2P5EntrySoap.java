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

package com.acme.t2p5.model;

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
public class T2P5EntrySoap implements Serializable {

	public static T2P5EntrySoap toSoapModel(T2P5Entry model) {
		T2P5EntrySoap soapModel = new T2P5EntrySoap();

		soapModel.setT2p5EntryId(model.getT2p5EntryId());
		soapModel.setDescription(model.getDescription());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static T2P5EntrySoap[] toSoapModels(T2P5Entry[] models) {
		T2P5EntrySoap[] soapModels = new T2P5EntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static T2P5EntrySoap[][] toSoapModels(T2P5Entry[][] models) {
		T2P5EntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new T2P5EntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new T2P5EntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static T2P5EntrySoap[] toSoapModels(List<T2P5Entry> models) {
		List<T2P5EntrySoap> soapModels = new ArrayList<T2P5EntrySoap>(
			models.size());

		for (T2P5Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new T2P5EntrySoap[soapModels.size()]);
	}

	public T2P5EntrySoap() {
	}

	public long getPrimaryKey() {
		return _t2p5EntryId;
	}

	public void setPrimaryKey(long pk) {
		setT2p5EntryId(pk);
	}

	public long getT2p5EntryId() {
		return _t2p5EntryId;
	}

	public void setT2p5EntryId(long t2p5EntryId) {
		_t2p5EntryId = t2p5EntryId;
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

	private long _t2p5EntryId;
	private String _description;
	private String _name;

}