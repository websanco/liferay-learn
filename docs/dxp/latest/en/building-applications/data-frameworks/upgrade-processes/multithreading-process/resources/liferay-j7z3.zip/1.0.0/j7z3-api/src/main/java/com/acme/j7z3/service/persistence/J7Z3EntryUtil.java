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

package com.acme.j7z3.service.persistence;

import com.acme.j7z3.model.J7Z3Entry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the j7z3 entry service. This utility wraps <code>com.acme.j7z3.service.persistence.impl.J7Z3EntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see J7Z3EntryPersistence
 * @generated
 */
public class J7Z3EntryUtil {

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
	public static void clearCache(J7Z3Entry j7z3Entry) {
		getPersistence().clearCache(j7z3Entry);
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
	public static Map<Serializable, J7Z3Entry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<J7Z3Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<J7Z3Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<J7Z3Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<J7Z3Entry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static J7Z3Entry update(J7Z3Entry j7z3Entry) {
		return getPersistence().update(j7z3Entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static J7Z3Entry update(
		J7Z3Entry j7z3Entry, ServiceContext serviceContext) {

		return getPersistence().update(j7z3Entry, serviceContext);
	}

	/**
	 * Caches the j7z3 entry in the entity cache if it is enabled.
	 *
	 * @param j7z3Entry the j7z3 entry
	 */
	public static void cacheResult(J7Z3Entry j7z3Entry) {
		getPersistence().cacheResult(j7z3Entry);
	}

	/**
	 * Caches the j7z3 entries in the entity cache if it is enabled.
	 *
	 * @param j7z3Entries the j7z3 entries
	 */
	public static void cacheResult(List<J7Z3Entry> j7z3Entries) {
		getPersistence().cacheResult(j7z3Entries);
	}

	/**
	 * Creates a new j7z3 entry with the primary key. Does not add the j7z3 entry to the database.
	 *
	 * @param j7z3EntryId the primary key for the new j7z3 entry
	 * @return the new j7z3 entry
	 */
	public static J7Z3Entry create(long j7z3EntryId) {
		return getPersistence().create(j7z3EntryId);
	}

	/**
	 * Removes the j7z3 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry that was removed
	 * @throws NoSuchJ7Z3EntryException if a j7z3 entry with the primary key could not be found
	 */
	public static J7Z3Entry remove(long j7z3EntryId)
		throws com.acme.j7z3.exception.NoSuchJ7Z3EntryException {

		return getPersistence().remove(j7z3EntryId);
	}

	public static J7Z3Entry updateImpl(J7Z3Entry j7z3Entry) {
		return getPersistence().updateImpl(j7z3Entry);
	}

	/**
	 * Returns the j7z3 entry with the primary key or throws a <code>NoSuchJ7Z3EntryException</code> if it could not be found.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry
	 * @throws NoSuchJ7Z3EntryException if a j7z3 entry with the primary key could not be found
	 */
	public static J7Z3Entry findByPrimaryKey(long j7z3EntryId)
		throws com.acme.j7z3.exception.NoSuchJ7Z3EntryException {

		return getPersistence().findByPrimaryKey(j7z3EntryId);
	}

	/**
	 * Returns the j7z3 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry, or <code>null</code> if a j7z3 entry with the primary key could not be found
	 */
	public static J7Z3Entry fetchByPrimaryKey(long j7z3EntryId) {
		return getPersistence().fetchByPrimaryKey(j7z3EntryId);
	}

	/**
	 * Returns all the j7z3 entries.
	 *
	 * @return the j7z3 entries
	 */
	public static List<J7Z3Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the j7z3 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>J7Z3EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of j7z3 entries
	 * @param end the upper bound of the range of j7z3 entries (not inclusive)
	 * @return the range of j7z3 entries
	 */
	public static List<J7Z3Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the j7z3 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>J7Z3EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of j7z3 entries
	 * @param end the upper bound of the range of j7z3 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j7z3 entries
	 */
	public static List<J7Z3Entry> findAll(
		int start, int end, OrderByComparator<J7Z3Entry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the j7z3 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>J7Z3EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of j7z3 entries
	 * @param end the upper bound of the range of j7z3 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of j7z3 entries
	 */
	public static List<J7Z3Entry> findAll(
		int start, int end, OrderByComparator<J7Z3Entry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the j7z3 entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of j7z3 entries.
	 *
	 * @return the number of j7z3 entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static J7Z3EntryPersistence getPersistence() {
		return _persistence;
	}

	private static volatile J7Z3EntryPersistence _persistence;

}