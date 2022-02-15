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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link E4G5Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see E4G5Entry
 * @generated
 */
public class E4G5EntryWrapper
	extends BaseModelWrapper<E4G5Entry>
	implements E4G5Entry, ModelWrapper<E4G5Entry> {

	public E4G5EntryWrapper(E4G5Entry e4g5Entry) {
		super(e4g5Entry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("e4g5EntryId", getE4g5EntryId());
		attributes.put("description", getDescription());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long e4g5EntryId = (Long)attributes.get("e4g5EntryId");

		if (e4g5EntryId != null) {
			setE4g5EntryId(e4g5EntryId);
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
	public E4G5Entry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the description of this e4g5 entry.
	 *
	 * @return the description of this e4g5 entry
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the e4g5 entry ID of this e4g5 entry.
	 *
	 * @return the e4g5 entry ID of this e4g5 entry
	 */
	@Override
	public long getE4g5EntryId() {
		return model.getE4g5EntryId();
	}

	/**
	 * Returns the name of this e4g5 entry.
	 *
	 * @return the name of this e4g5 entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this e4g5 entry.
	 *
	 * @return the primary key of this e4g5 entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the description of this e4g5 entry.
	 *
	 * @param description the description of this e4g5 entry
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the e4g5 entry ID of this e4g5 entry.
	 *
	 * @param e4g5EntryId the e4g5 entry ID of this e4g5 entry
	 */
	@Override
	public void setE4g5EntryId(long e4g5EntryId) {
		model.setE4g5EntryId(e4g5EntryId);
	}

	/**
	 * Sets the name of this e4g5 entry.
	 *
	 * @param name the name of this e4g5 entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this e4g5 entry.
	 *
	 * @param primaryKey the primary key of this e4g5 entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected E4G5EntryWrapper wrap(E4G5Entry e4g5Entry) {
		return new E4G5EntryWrapper(e4g5Entry);
	}

}