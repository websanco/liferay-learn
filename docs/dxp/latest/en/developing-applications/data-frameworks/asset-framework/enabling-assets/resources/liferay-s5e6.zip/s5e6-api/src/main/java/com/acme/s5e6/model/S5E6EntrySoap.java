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

package com.acme.s5e6.model;

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
public class S5E6EntrySoap implements Serializable {

	public static S5E6EntrySoap toSoapModel(S5E6Entry model) {
		S5E6EntrySoap soapModel = new S5E6EntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setS5E6EntryId(model.getS5E6EntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDescription(model.getDescription());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static S5E6EntrySoap[] toSoapModels(S5E6Entry[] models) {
		S5E6EntrySoap[] soapModels = new S5E6EntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static S5E6EntrySoap[][] toSoapModels(S5E6Entry[][] models) {
		S5E6EntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new S5E6EntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new S5E6EntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static S5E6EntrySoap[] toSoapModels(List<S5E6Entry> models) {
		List<S5E6EntrySoap> soapModels = new ArrayList<S5E6EntrySoap>(
			models.size());

		for (S5E6Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new S5E6EntrySoap[soapModels.size()]);
	}

	public S5E6EntrySoap() {
	}

	public long getPrimaryKey() {
		return _S5E6EntryId;
	}

	public void setPrimaryKey(long pk) {
		setS5E6EntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getS5E6EntryId() {
		return _S5E6EntryId;
	}

	public void setS5E6EntryId(long S5E6EntryId) {
		_S5E6EntryId = S5E6EntryId;
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

	private String _uuid;
	private long _S5E6EntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _description;
	private String _name;

}