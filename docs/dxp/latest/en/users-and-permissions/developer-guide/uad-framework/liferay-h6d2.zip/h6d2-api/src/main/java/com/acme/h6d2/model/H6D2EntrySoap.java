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

package com.acme.h6d2.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class H6D2EntrySoap implements Serializable {

	public static H6D2EntrySoap toSoapModel(H6D2Entry model) {
		H6D2EntrySoap soapModel = new H6D2EntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setH6d2EntryId(model.getH6d2EntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static H6D2EntrySoap[] toSoapModels(H6D2Entry[] models) {
		H6D2EntrySoap[] soapModels = new H6D2EntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static H6D2EntrySoap[][] toSoapModels(H6D2Entry[][] models) {
		H6D2EntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new H6D2EntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new H6D2EntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static H6D2EntrySoap[] toSoapModels(List<H6D2Entry> models) {
		List<H6D2EntrySoap> soapModels = new ArrayList<H6D2EntrySoap>(
			models.size());

		for (H6D2Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new H6D2EntrySoap[soapModels.size()]);
	}

	public H6D2EntrySoap() {
	}

	public long getPrimaryKey() {
		return _h6d2EntryId;
	}

	public void setPrimaryKey(long pk) {
		setH6d2EntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getH6d2EntryId() {
		return _h6d2EntryId;
	}

	public void setH6d2EntryId(long h6d2EntryId) {
		_h6d2EntryId = h6d2EntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private String _uuid;
	private long _h6d2EntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;

}