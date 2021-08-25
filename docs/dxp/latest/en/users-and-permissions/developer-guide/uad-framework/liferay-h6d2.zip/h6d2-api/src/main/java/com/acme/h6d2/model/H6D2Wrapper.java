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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link H6D2}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see H6D2
 * @generated
 */
public class H6D2Wrapper
	extends BaseModelWrapper<H6D2> implements H6D2, ModelWrapper<H6D2> {

	public H6D2Wrapper(H6D2 h6d2) {
		super(h6d2);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("h6d2Id", getH6d2Id());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("todo", getTodo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long h6d2Id = (Long)attributes.get("h6d2Id");

		if (h6d2Id != null) {
			setH6d2Id(h6d2Id);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String todo = (String)attributes.get("todo");

		if (todo != null) {
			setTodo(todo);
		}
	}

	/**
	 * Returns the company ID of this h6d2.
	 *
	 * @return the company ID of this h6d2
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this h6d2.
	 *
	 * @return the create date of this h6d2
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this h6d2.
	 *
	 * @return the group ID of this h6d2
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the h6d2 ID of this h6d2.
	 *
	 * @return the h6d2 ID of this h6d2
	 */
	@Override
	public long getH6d2Id() {
		return model.getH6d2Id();
	}

	/**
	 * Returns the modified date of this h6d2.
	 *
	 * @return the modified date of this h6d2
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this h6d2.
	 *
	 * @return the primary key of this h6d2
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the todo of this h6d2.
	 *
	 * @return the todo of this h6d2
	 */
	@Override
	public String getTodo() {
		return model.getTodo();
	}

	/**
	 * Returns the user ID of this h6d2.
	 *
	 * @return the user ID of this h6d2
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this h6d2.
	 *
	 * @return the user name of this h6d2
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this h6d2.
	 *
	 * @return the user uuid of this h6d2
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this h6d2.
	 *
	 * @return the uuid of this h6d2
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this h6d2.
	 *
	 * @param companyId the company ID of this h6d2
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this h6d2.
	 *
	 * @param createDate the create date of this h6d2
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this h6d2.
	 *
	 * @param groupId the group ID of this h6d2
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the h6d2 ID of this h6d2.
	 *
	 * @param h6d2Id the h6d2 ID of this h6d2
	 */
	@Override
	public void setH6d2Id(long h6d2Id) {
		model.setH6d2Id(h6d2Id);
	}

	/**
	 * Sets the modified date of this h6d2.
	 *
	 * @param modifiedDate the modified date of this h6d2
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this h6d2.
	 *
	 * @param primaryKey the primary key of this h6d2
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the todo of this h6d2.
	 *
	 * @param todo the todo of this h6d2
	 */
	@Override
	public void setTodo(String todo) {
		model.setTodo(todo);
	}

	/**
	 * Sets the user ID of this h6d2.
	 *
	 * @param userId the user ID of this h6d2
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this h6d2.
	 *
	 * @param userName the user name of this h6d2
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this h6d2.
	 *
	 * @param userUuid the user uuid of this h6d2
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this h6d2.
	 *
	 * @param uuid the uuid of this h6d2
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected H6D2Wrapper wrap(H6D2 h6d2) {
		return new H6D2Wrapper(h6d2);
	}

}