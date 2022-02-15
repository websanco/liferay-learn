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

package com.acme.e4g5.service.persistence;

import com.acme.e4g5.model.E4G5Entry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the e4g5 entry service. This utility wraps <code>com.acme.e4g5.service.persistence.impl.E4G5EntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see E4G5EntryPersistence
 * @generated
 */
public class E4G5EntryUtil {

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
	public static void clearCache(E4G5Entry e4g5Entry) {
		getPersistence().clearCache(e4g5Entry);
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
	public static Map<Serializable, E4G5Entry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<E4G5Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<E4G5Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<E4G5Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<E4G5Entry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static E4G5Entry update(E4G5Entry e4g5Entry) {
		return getPersistence().update(e4g5Entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static E4G5Entry update(
		E4G5Entry e4g5Entry, ServiceContext serviceContext) {

		return getPersistence().update(e4g5Entry, serviceContext);
	}

	/**
	 * Caches the e4g5 entry in the entity cache if it is enabled.
	 *
	 * @param e4g5Entry the e4g5 entry
	 */
	public static void cacheResult(E4G5Entry e4g5Entry) {
		getPersistence().cacheResult(e4g5Entry);
	}

	/**
	 * Caches the e4g5 entries in the entity cache if it is enabled.
	 *
	 * @param e4g5Entries the e4g5 entries
	 */
	public static void cacheResult(List<E4G5Entry> e4g5Entries) {
		getPersistence().cacheResult(e4g5Entries);
	}

	/**
	 * Creates a new e4g5 entry with the primary key. Does not add the e4g5 entry to the database.
	 *
	 * @param e4g5EntryId the primary key for the new e4g5 entry
	 * @return the new e4g5 entry
	 */
	public static E4G5Entry create(long e4g5EntryId) {
		return getPersistence().create(e4g5EntryId);
	}

	/**
	 * Removes the e4g5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param e4g5EntryId the primary key of the e4g5 entry
	 * @return the e4g5 entry that was removed
	 * @throws NoSuchE4G5EntryException if a e4g5 entry with the primary key could not be found
	 */
	public static E4G5Entry remove(long e4g5EntryId)
		throws com.acme.e4g5.exception.NoSuchE4G5EntryException {

		return getPersistence().remove(e4g5EntryId);
	}

	public static E4G5Entry updateImpl(E4G5Entry e4g5Entry) {
		return getPersistence().updateImpl(e4g5Entry);
	}

	/**
	 * Returns the e4g5 entry with the primary key or throws a <code>NoSuchE4G5EntryException</code> if it could not be found.
	 *
	 * @param e4g5EntryId the primary key of the e4g5 entry
	 * @return the e4g5 entry
	 * @throws NoSuchE4G5EntryException if a e4g5 entry with the primary key could not be found
	 */
	public static E4G5Entry findByPrimaryKey(long e4g5EntryId)
		throws com.acme.e4g5.exception.NoSuchE4G5EntryException {

		return getPersistence().findByPrimaryKey(e4g5EntryId);
	}

	/**
	 * Returns the e4g5 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param e4g5EntryId the primary key of the e4g5 entry
	 * @return the e4g5 entry, or <code>null</code> if a e4g5 entry with the primary key could not be found
	 */
	public static E4G5Entry fetchByPrimaryKey(long e4g5EntryId) {
		return getPersistence().fetchByPrimaryKey(e4g5EntryId);
	}

	/**
	 * Returns all the e4g5 entries.
	 *
	 * @return the e4g5 entries
	 */
	public static List<E4G5Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the e4g5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e4g5 entries
	 * @param end the upper bound of the range of e4g5 entries (not inclusive)
	 * @return the range of e4g5 entries
	 */
	public static List<E4G5Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the e4g5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e4g5 entries
	 * @param end the upper bound of the range of e4g5 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of e4g5 entries
	 */
	public static List<E4G5Entry> findAll(
		int start, int end, OrderByComparator<E4G5Entry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the e4g5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e4g5 entries
	 * @param end the upper bound of the range of e4g5 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of e4g5 entries
	 */
	public static List<E4G5Entry> findAll(
		int start, int end, OrderByComparator<E4G5Entry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the e4g5 entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of e4g5 entries.
	 *
	 * @return the number of e4g5 entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static E4G5EntryPersistence getPersistence() {
		return _persistence;
	}

	private static volatile E4G5EntryPersistence _persistence;

}