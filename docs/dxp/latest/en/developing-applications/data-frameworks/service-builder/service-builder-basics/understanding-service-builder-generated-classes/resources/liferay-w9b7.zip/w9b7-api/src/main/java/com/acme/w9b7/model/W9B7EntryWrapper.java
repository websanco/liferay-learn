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

package com.acme.w9b7.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link W9B7Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see W9B7Entry
 * @generated
 */
public class W9B7EntryWrapper
	extends BaseModelWrapper<W9B7Entry>
	implements ModelWrapper<W9B7Entry>, W9B7Entry {

	public W9B7EntryWrapper(W9B7Entry w9b7Entry) {
		super(w9b7Entry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("w9b7EntryId", getW9b7EntryId());
		attributes.put("description", getDescription());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long w9b7EntryId = (Long)attributes.get("w9b7EntryId");

		if (w9b7EntryId != null) {
			setW9b7EntryId(w9b7EntryId);
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
	 * Returns the description of this w9b7 entry.
	 *
	 * @return the description of this w9b7 entry
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the name of this w9b7 entry.
	 *
	 * @return the name of this w9b7 entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this w9b7 entry.
	 *
	 * @return the primary key of this w9b7 entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the w9b7 entry ID of this w9b7 entry.
	 *
	 * @return the w9b7 entry ID of this w9b7 entry
	 */
	@Override
	public long getW9b7EntryId() {
		return model.getW9b7EntryId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the description of this w9b7 entry.
	 *
	 * @param description the description of this w9b7 entry
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the name of this w9b7 entry.
	 *
	 * @param name the name of this w9b7 entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this w9b7 entry.
	 *
	 * @param primaryKey the primary key of this w9b7 entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the w9b7 entry ID of this w9b7 entry.
	 *
	 * @param w9b7EntryId the w9b7 entry ID of this w9b7 entry
	 */
	@Override
	public void setW9b7EntryId(long w9b7EntryId) {
		model.setW9b7EntryId(w9b7EntryId);
	}

	@Override
	protected W9B7EntryWrapper wrap(W9B7Entry w9b7Entry) {
		return new W9B7EntryWrapper(w9b7Entry);
	}

}