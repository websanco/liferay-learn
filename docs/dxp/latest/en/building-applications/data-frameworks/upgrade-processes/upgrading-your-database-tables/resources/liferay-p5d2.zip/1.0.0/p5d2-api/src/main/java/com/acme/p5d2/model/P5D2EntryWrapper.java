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

package com.acme.p5d2.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link P5D2Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P5D2Entry
 * @generated
 */
public class P5D2EntryWrapper
	extends BaseModelWrapper<P5D2Entry>
	implements ModelWrapper<P5D2Entry>, P5D2Entry {

	public P5D2EntryWrapper(P5D2Entry p5d2Entry) {
		super(p5d2Entry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("p5d2EntryId", getP5d2EntryId());
		attributes.put("able", getAble());
		attributes.put("baker", isBaker());
		attributes.put("foo", getFoo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long p5d2EntryId = (Long)attributes.get("p5d2EntryId");

		if (p5d2EntryId != null) {
			setP5d2EntryId(p5d2EntryId);
		}

		Long able = (Long)attributes.get("able");

		if (able != null) {
			setAble(able);
		}

		Boolean baker = (Boolean)attributes.get("baker");

		if (baker != null) {
			setBaker(baker);
		}

		String foo = (String)attributes.get("foo");

		if (foo != null) {
			setFoo(foo);
		}
	}

	@Override
	public P5D2Entry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the able of this p5d2 entry.
	 *
	 * @return the able of this p5d2 entry
	 */
	@Override
	public long getAble() {
		return model.getAble();
	}

	/**
	 * Returns the baker of this p5d2 entry.
	 *
	 * @return the baker of this p5d2 entry
	 */
	@Override
	public boolean getBaker() {
		return model.getBaker();
	}

	/**
	 * Returns the foo of this p5d2 entry.
	 *
	 * @return the foo of this p5d2 entry
	 */
	@Override
	public String getFoo() {
		return model.getFoo();
	}

	/**
	 * Returns the p5d2 entry ID of this p5d2 entry.
	 *
	 * @return the p5d2 entry ID of this p5d2 entry
	 */
	@Override
	public long getP5d2EntryId() {
		return model.getP5d2EntryId();
	}

	/**
	 * Returns the primary key of this p5d2 entry.
	 *
	 * @return the primary key of this p5d2 entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns <code>true</code> if this p5d2 entry is baker.
	 *
	 * @return <code>true</code> if this p5d2 entry is baker; <code>false</code> otherwise
	 */
	@Override
	public boolean isBaker() {
		return model.isBaker();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the able of this p5d2 entry.
	 *
	 * @param able the able of this p5d2 entry
	 */
	@Override
	public void setAble(long able) {
		model.setAble(able);
	}

	/**
	 * Sets whether this p5d2 entry is baker.
	 *
	 * @param baker the baker of this p5d2 entry
	 */
	@Override
	public void setBaker(boolean baker) {
		model.setBaker(baker);
	}

	/**
	 * Sets the foo of this p5d2 entry.
	 *
	 * @param foo the foo of this p5d2 entry
	 */
	@Override
	public void setFoo(String foo) {
		model.setFoo(foo);
	}

	/**
	 * Sets the p5d2 entry ID of this p5d2 entry.
	 *
	 * @param p5d2EntryId the p5d2 entry ID of this p5d2 entry
	 */
	@Override
	public void setP5d2EntryId(long p5d2EntryId) {
		model.setP5d2EntryId(p5d2EntryId);
	}

	/**
	 * Sets the primary key of this p5d2 entry.
	 *
	 * @param primaryKey the primary key of this p5d2 entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected P5D2EntryWrapper wrap(P5D2Entry p5d2Entry) {
		return new P5D2EntryWrapper(p5d2Entry);
	}

}