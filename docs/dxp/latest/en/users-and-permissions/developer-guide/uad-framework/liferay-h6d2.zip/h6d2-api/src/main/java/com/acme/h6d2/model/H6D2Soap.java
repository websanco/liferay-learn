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
 * This class is used by SOAP remote services, specifically {@link com.acme.h6d2.service.http.H6D2ServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class H6D2Soap implements Serializable {

	public static H6D2Soap toSoapModel(H6D2 model) {
		H6D2Soap soapModel = new H6D2Soap();

		soapModel.setUuid(model.getUuid());
		soapModel.setH6d2Id(model.getH6d2Id());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTodo(model.getTodo());

		return soapModel;
	}

	public static H6D2Soap[] toSoapModels(H6D2[] models) {
		H6D2Soap[] soapModels = new H6D2Soap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static H6D2Soap[][] toSoapModels(H6D2[][] models) {
		H6D2Soap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new H6D2Soap[models.length][models[0].length];
		}
		else {
			soapModels = new H6D2Soap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static H6D2Soap[] toSoapModels(List<H6D2> models) {
		List<H6D2Soap> soapModels = new ArrayList<H6D2Soap>(models.size());

		for (H6D2 model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new H6D2Soap[soapModels.size()]);
	}

	public H6D2Soap() {
	}

	public long getPrimaryKey() {
		return _h6d2Id;
	}

	public void setPrimaryKey(long pk) {
		setH6d2Id(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getH6d2Id() {
		return _h6d2Id;
	}

	public void setH6d2Id(long h6d2Id) {
		_h6d2Id = h6d2Id;
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

	public String getTodo() {
		return _todo;
	}

	public void setTodo(String todo) {
		_todo = todo;
	}

	private String _uuid;
	private long _h6d2Id;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _todo;

}