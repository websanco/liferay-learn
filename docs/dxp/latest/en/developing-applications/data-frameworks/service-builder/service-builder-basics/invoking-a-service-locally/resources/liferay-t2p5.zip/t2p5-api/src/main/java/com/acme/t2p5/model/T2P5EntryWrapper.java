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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link T2P5Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see T2P5Entry
 * @generated
 */
public class T2P5EntryWrapper
	extends BaseModelWrapper<T2P5Entry>
	implements ModelWrapper<T2P5Entry>, T2P5Entry {

	public T2P5EntryWrapper(T2P5Entry t2p5Entry) {
		super(t2p5Entry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("t2p5EntryId", getT2p5EntryId());
		attributes.put("description", getDescription());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long t2p5EntryId = (Long)attributes.get("t2p5EntryId");

		if (t2p5EntryId != null) {
			setT2p5EntryId(t2p5EntryId);
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

	/**
	 * Returns the description of this t2p5 entry.
	 *
	 * @return the description of this t2p5 entry
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the name of this t2p5 entry.
	 *
	 * @return the name of this t2p5 entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this t2p5 entry.
	 *
	 * @return the primary key of this t2p5 entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the t2p5 entry ID of this t2p5 entry.
	 *
	 * @return the t2p5 entry ID of this t2p5 entry
	 */
	@Override
	public long getT2p5EntryId() {
		return model.getT2p5EntryId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the description of this t2p5 entry.
	 *
	 * @param description the description of this t2p5 entry
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the name of this t2p5 entry.
	 *
	 * @param name the name of this t2p5 entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this t2p5 entry.
	 *
	 * @param primaryKey the primary key of this t2p5 entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the t2p5 entry ID of this t2p5 entry.
	 *
	 * @param t2p5EntryId the t2p5 entry ID of this t2p5 entry
	 */
	@Override
	public void setT2p5EntryId(long t2p5EntryId) {
		model.setT2p5EntryId(t2p5EntryId);
	}

	@Override
	protected T2P5EntryWrapper wrap(T2P5Entry t2p5Entry) {
		return new T2P5EntryWrapper(t2p5Entry);
	}

}