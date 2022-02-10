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

package com.acme.p5d2.service.persistence;

import com.acme.p5d2.model.P5D2Entry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the p5d2 entry service. This utility wraps <code>com.acme.p5d2.service.persistence.impl.P5D2EntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P5D2EntryPersistence
 * @generated
 */
public class P5D2EntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(P5D2Entry p5d2Entry) {
		getPersistence().clearCache(p5d2Entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, P5D2Entry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<P5D2Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<P5D2Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<P5D2Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<P5D2Entry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static P5D2Entry update(P5D2Entry p5d2Entry) {
		return getPersistence().update(p5d2Entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static P5D2Entry update(
		P5D2Entry p5d2Entry, ServiceContext serviceContext) {

		return getPersistence().update(p5d2Entry, serviceContext);
	}

	/**
	 * Caches the p5d2 entry in the entity cache if it is enabled.
	 *
	 * @param p5d2Entry the p5d2 entry
	 */
	public static void cacheResult(P5D2Entry p5d2Entry) {
		getPersistence().cacheResult(p5d2Entry);
	}

	/**
	 * Caches the p5d2 entries in the entity cache if it is enabled.
	 *
	 * @param p5d2Entries the p5d2 entries
	 */
	public static void cacheResult(List<P5D2Entry> p5d2Entries) {
		getPersistence().cacheResult(p5d2Entries);
	}

	/**
	 * Creates a new p5d2 entry with the primary key. Does not add the p5d2 entry to the database.
	 *
	 * @param p5d2EntryId the primary key for the new p5d2 entry
	 * @return the new p5d2 entry
	 */
	public static P5D2Entry create(long p5d2EntryId) {
		return getPersistence().create(p5d2EntryId);
	}

	/**
	 * Removes the p5d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry that was removed
	 * @throws NoSuchP5D2EntryException if a p5d2 entry with the primary key could not be found
	 */
	public static P5D2Entry remove(long p5d2EntryId)
		throws com.acme.p5d2.exception.NoSuchP5D2EntryException {

		return getPersistence().remove(p5d2EntryId);
	}

	public static P5D2Entry updateImpl(P5D2Entry p5d2Entry) {
		return getPersistence().updateImpl(p5d2Entry);
	}

	/**
	 * Returns the p5d2 entry with the primary key or throws a <code>NoSuchP5D2EntryException</code> if it could not be found.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry
	 * @throws NoSuchP5D2EntryException if a p5d2 entry with the primary key could not be found
	 */
	public static P5D2Entry findByPrimaryKey(long p5d2EntryId)
		throws com.acme.p5d2.exception.NoSuchP5D2EntryException {

		return getPersistence().findByPrimaryKey(p5d2EntryId);
	}

	/**
	 * Returns the p5d2 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry, or <code>null</code> if a p5d2 entry with the primary key could not be found
	 */
	public static P5D2Entry fetchByPrimaryKey(long p5d2EntryId) {
		return getPersistence().fetchByPrimaryKey(p5d2EntryId);
	}

	/**
	 * Returns all the p5d2 entries.
	 *
	 * @return the p5d2 entries
	 */
	public static List<P5D2Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @return the range of p5d2 entries
	 */
	public static List<P5D2Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p5d2 entries
	 */
	public static List<P5D2Entry> findAll(
		int start, int end, OrderByComparator<P5D2Entry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of p5d2 entries
	 */
	public static List<P5D2Entry> findAll(
		int start, int end, OrderByComparator<P5D2Entry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the p5d2 entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of p5d2 entries.
	 *
	 * @return the number of p5d2 entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static P5D2EntryPersistence getPersistence() {
		return _persistence;
	}

	private static volatile P5D2EntryPersistence _persistence;

}