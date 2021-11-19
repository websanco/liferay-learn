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

package com.acme.w9b7.service.persistence;

import com.acme.w9b7.model.W9B7Entry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the w9b7 entry service. This utility wraps <code>com.acme.w9b7.service.persistence.impl.W9B7EntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see W9B7EntryPersistence
 * @generated
 */
public class W9B7EntryUtil {

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
	public static void clearCache(W9B7Entry w9b7Entry) {
		getPersistence().clearCache(w9b7Entry);
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
	public static Map<Serializable, W9B7Entry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<W9B7Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<W9B7Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<W9B7Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<W9B7Entry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static W9B7Entry update(W9B7Entry w9b7Entry) {
		return getPersistence().update(w9b7Entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static W9B7Entry update(
		W9B7Entry w9b7Entry, ServiceContext serviceContext) {

		return getPersistence().update(w9b7Entry, serviceContext);
	}

	/**
	 * Caches the w9b7 entry in the entity cache if it is enabled.
	 *
	 * @param w9b7Entry the w9b7 entry
	 */
	public static void cacheResult(W9B7Entry w9b7Entry) {
		getPersistence().cacheResult(w9b7Entry);
	}

	/**
	 * Caches the w9b7 entries in the entity cache if it is enabled.
	 *
	 * @param w9b7Entries the w9b7 entries
	 */
	public static void cacheResult(List<W9B7Entry> w9b7Entries) {
		getPersistence().cacheResult(w9b7Entries);
	}

	/**
	 * Creates a new w9b7 entry with the primary key. Does not add the w9b7 entry to the database.
	 *
	 * @param w9b7EntryId the primary key for the new w9b7 entry
	 * @return the new w9b7 entry
	 */
	public static W9B7Entry create(long w9b7EntryId) {
		return getPersistence().create(w9b7EntryId);
	}

	/**
	 * Removes the w9b7 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry that was removed
	 * @throws NoSuchW9B7EntryException if a w9b7 entry with the primary key could not be found
	 */
	public static W9B7Entry remove(long w9b7EntryId)
		throws com.acme.w9b7.exception.NoSuchW9B7EntryException {

		return getPersistence().remove(w9b7EntryId);
	}

	public static W9B7Entry updateImpl(W9B7Entry w9b7Entry) {
		return getPersistence().updateImpl(w9b7Entry);
	}

	/**
	 * Returns the w9b7 entry with the primary key or throws a <code>NoSuchW9B7EntryException</code> if it could not be found.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry
	 * @throws NoSuchW9B7EntryException if a w9b7 entry with the primary key could not be found
	 */
	public static W9B7Entry findByPrimaryKey(long w9b7EntryId)
		throws com.acme.w9b7.exception.NoSuchW9B7EntryException {

		return getPersistence().findByPrimaryKey(w9b7EntryId);
	}

	/**
	 * Returns the w9b7 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry, or <code>null</code> if a w9b7 entry with the primary key could not be found
	 */
	public static W9B7Entry fetchByPrimaryKey(long w9b7EntryId) {
		return getPersistence().fetchByPrimaryKey(w9b7EntryId);
	}

	/**
	 * Returns all the w9b7 entries.
	 *
	 * @return the w9b7 entries
	 */
	public static List<W9B7Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the w9b7 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>W9B7EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of w9b7 entries
	 * @param end the upper bound of the range of w9b7 entries (not inclusive)
	 * @return the range of w9b7 entries
	 */
	public static List<W9B7Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the w9b7 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>W9B7EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of w9b7 entries
	 * @param end the upper bound of the range of w9b7 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of w9b7 entries
	 */
	public static List<W9B7Entry> findAll(
		int start, int end, OrderByComparator<W9B7Entry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the w9b7 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>W9B7EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of w9b7 entries
	 * @param end the upper bound of the range of w9b7 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of w9b7 entries
	 */
	public static List<W9B7Entry> findAll(
		int start, int end, OrderByComparator<W9B7Entry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the w9b7 entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of w9b7 entries.
	 *
	 * @return the number of w9b7 entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static W9B7EntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<W9B7EntryPersistence, W9B7EntryPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(W9B7EntryPersistence.class);

		ServiceTracker<W9B7EntryPersistence, W9B7EntryPersistence>
			serviceTracker =
				new ServiceTracker<W9B7EntryPersistence, W9B7EntryPersistence>(
					bundle.getBundleContext(), W9B7EntryPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}