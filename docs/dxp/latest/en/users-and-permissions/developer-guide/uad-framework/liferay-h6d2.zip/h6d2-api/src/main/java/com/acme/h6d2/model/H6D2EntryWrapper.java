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
 * This class is a wrapper for {@link H6D2Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see H6D2Entry
 * @generated
 */
public class H6D2EntryWrapper
	extends BaseModelWrapper<H6D2Entry>
	implements H6D2Entry, ModelWrapper<H6D2Entry> {

	public H6D2EntryWrapper(H6D2Entry h6d2Entry) {
		super(h6d2Entry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("h6d2EntryId", getH6d2EntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long h6d2EntryId = (Long)attributes.get("h6d2EntryId");

		if (h6d2EntryId != null) {
			setH6d2EntryId(h6d2EntryId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	 * Returns the company ID of this h6d2 entry.
	 *
	 * @return the company ID of this h6d2 entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this h6d2 entry.
	 *
	 * @return the create date of this h6d2 entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this h6d2 entry.
	 *
	 * @return the group ID of this h6d2 entry
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the h6d2 entry ID of this h6d2 entry.
	 *
	 * @return the h6d2 entry ID of this h6d2 entry
	 */
	@Override
	public long getH6d2EntryId() {
		return model.getH6d2EntryId();
	}

	/**
	 * Returns the modified date of this h6d2 entry.
	 *
	 * @return the modified date of this h6d2 entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this h6d2 entry.
	 *
	 * @return the name of this h6d2 entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this h6d2 entry.
	 *
	 * @return the primary key of this h6d2 entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this h6d2 entry.
	 *
	 * @return the user ID of this h6d2 entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this h6d2 entry.
	 *
	 * @return the user name of this h6d2 entry
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this h6d2 entry.
	 *
	 * @return the user uuid of this h6d2 entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this h6d2 entry.
	 *
	 * @return the uuid of this h6d2 entry
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
	 * Sets the company ID of this h6d2 entry.
	 *
	 * @param companyId the company ID of this h6d2 entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this h6d2 entry.
	 *
	 * @param createDate the create date of this h6d2 entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this h6d2 entry.
	 *
	 * @param groupId the group ID of this h6d2 entry
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the h6d2 entry ID of this h6d2 entry.
	 *
	 * @param h6d2EntryId the h6d2 entry ID of this h6d2 entry
	 */
	@Override
	public void setH6d2EntryId(long h6d2EntryId) {
		model.setH6d2EntryId(h6d2EntryId);
	}

	/**
	 * Sets the modified date of this h6d2 entry.
	 *
	 * @param modifiedDate the modified date of this h6d2 entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this h6d2 entry.
	 *
	 * @param name the name of this h6d2 entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this h6d2 entry.
	 *
	 * @param primaryKey the primary key of this h6d2 entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this h6d2 entry.
	 *
	 * @param userId the user ID of this h6d2 entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this h6d2 entry.
	 *
	 * @param userName the user name of this h6d2 entry
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this h6d2 entry.
	 *
	 * @param userUuid the user uuid of this h6d2 entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this h6d2 entry.
	 *
	 * @param uuid the uuid of this h6d2 entry
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
	protected H6D2EntryWrapper wrap(H6D2Entry h6d2Entry) {
		return new H6D2EntryWrapper(h6d2Entry);
	}

}