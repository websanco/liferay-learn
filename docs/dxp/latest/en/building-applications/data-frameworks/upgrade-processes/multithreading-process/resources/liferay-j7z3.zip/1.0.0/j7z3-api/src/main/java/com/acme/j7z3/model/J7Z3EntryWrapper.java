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

package com.acme.j7z3.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link J7Z3Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see J7Z3Entry
 * @generated
 */
public class J7Z3EntryWrapper
	extends BaseModelWrapper<J7Z3Entry>
	implements J7Z3Entry, ModelWrapper<J7Z3Entry> {

	public J7Z3EntryWrapper(J7Z3Entry j7z3Entry) {
		super(j7z3Entry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("j7z3EntryId", getJ7z3EntryId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long j7z3EntryId = (Long)attributes.get("j7z3EntryId");

		if (j7z3EntryId != null) {
			setJ7z3EntryId(j7z3EntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public J7Z3Entry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the j7z3 entry ID of this j7z3 entry.
	 *
	 * @return the j7z3 entry ID of this j7z3 entry
	 */
	@Override
	public long getJ7z3EntryId() {
		return model.getJ7z3EntryId();
	}

	/**
	 * Returns the name of this j7z3 entry.
	 *
	 * @return the name of this j7z3 entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this j7z3 entry.
	 *
	 * @return the primary key of this j7z3 entry
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
	 * Sets the j7z3 entry ID of this j7z3 entry.
	 *
	 * @param j7z3EntryId the j7z3 entry ID of this j7z3 entry
	 */
	@Override
	public void setJ7z3EntryId(long j7z3EntryId) {
		model.setJ7z3EntryId(j7z3EntryId);
	}

	/**
	 * Sets the name of this j7z3 entry.
	 *
	 * @param name the name of this j7z3 entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this j7z3 entry.
	 *
	 * @param primaryKey the primary key of this j7z3 entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected J7Z3EntryWrapper wrap(J7Z3Entry j7z3Entry) {
		return new J7Z3EntryWrapper(j7z3Entry);
	}

}