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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link S5E6Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see S5E6Entry
 * @generated
 */
public class S5E6EntryWrapper
	extends BaseModelWrapper<S5E6Entry>
	implements ModelWrapper<S5E6Entry>, S5E6Entry {

	public S5E6EntryWrapper(S5E6Entry s5e6Entry) {
		super(s5e6Entry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("S5E6EntryId", getS5E6EntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("description", getDescription());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long S5E6EntryId = (Long)attributes.get("S5E6EntryId");

		if (S5E6EntryId != null) {
			setS5E6EntryId(S5E6EntryId);
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public S5E6Entry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this s5e6 entry.
	 *
	 * @return the company ID of this s5e6 entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this s5e6 entry.
	 *
	 * @return the create date of this s5e6 entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this s5e6 entry.
	 *
	 * @return the description of this s5e6 entry
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the group ID of this s5e6 entry.
	 *
	 * @return the group ID of this s5e6 entry
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this s5e6 entry.
	 *
	 * @return the modified date of this s5e6 entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this s5e6 entry.
	 *
	 * @return the name of this s5e6 entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this s5e6 entry.
	 *
	 * @return the primary key of this s5e6 entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the s5e6 entry ID of this s5e6 entry.
	 *
	 * @return the s5e6 entry ID of this s5e6 entry
	 */
	@Override
	public long getS5E6EntryId() {
		return model.getS5E6EntryId();
	}

	/**
	 * Returns the user ID of this s5e6 entry.
	 *
	 * @return the user ID of this s5e6 entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this s5e6 entry.
	 *
	 * @return the user name of this s5e6 entry
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this s5e6 entry.
	 *
	 * @return the user uuid of this s5e6 entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this s5e6 entry.
	 *
	 * @return the uuid of this s5e6 entry
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
	 * Sets the company ID of this s5e6 entry.
	 *
	 * @param companyId the company ID of this s5e6 entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this s5e6 entry.
	 *
	 * @param createDate the create date of this s5e6 entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this s5e6 entry.
	 *
	 * @param description the description of this s5e6 entry
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the group ID of this s5e6 entry.
	 *
	 * @param groupId the group ID of this s5e6 entry
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this s5e6 entry.
	 *
	 * @param modifiedDate the modified date of this s5e6 entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this s5e6 entry.
	 *
	 * @param name the name of this s5e6 entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this s5e6 entry.
	 *
	 * @param primaryKey the primary key of this s5e6 entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the s5e6 entry ID of this s5e6 entry.
	 *
	 * @param S5E6EntryId the s5e6 entry ID of this s5e6 entry
	 */
	@Override
	public void setS5E6EntryId(long S5E6EntryId) {
		model.setS5E6EntryId(S5E6EntryId);
	}

	/**
	 * Sets the user ID of this s5e6 entry.
	 *
	 * @param userId the user ID of this s5e6 entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this s5e6 entry.
	 *
	 * @param userName the user name of this s5e6 entry
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this s5e6 entry.
	 *
	 * @param userUuid the user uuid of this s5e6 entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this s5e6 entry.
	 *
	 * @param uuid the uuid of this s5e6 entry
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
	protected S5E6EntryWrapper wrap(S5E6Entry s5e6Entry) {
		return new S5E6EntryWrapper(s5e6Entry);
	}

}