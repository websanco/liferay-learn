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

package com.acme.t2p5.service.persistence;

import com.acme.t2p5.model.T2P5Entry;

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
 * The persistence utility for the t2p5 entry service. This utility wraps <code>com.acme.t2p5.service.persistence.impl.T2P5EntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see T2P5EntryPersistence
 * @generated
 */
public class T2P5EntryUtil {

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
	public static void clearCache(T2P5Entry t2p5Entry) {
		getPersistence().clearCache(t2p5Entry);
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
	public static Map<Serializable, T2P5Entry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<T2P5Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<T2P5Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<T2P5Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T2P5Entry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static T2P5Entry update(T2P5Entry t2p5Entry) {
		return getPersistence().update(t2p5Entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static T2P5Entry update(
		T2P5Entry t2p5Entry, ServiceContext serviceContext) {

		return getPersistence().update(t2p5Entry, serviceContext);
	}

	/**
	 * Caches the t2p5 entry in the entity cache if it is enabled.
	 *
	 * @param t2p5Entry the t2p5 entry
	 */
	public static void cacheResult(T2P5Entry t2p5Entry) {
		getPersistence().cacheResult(t2p5Entry);
	}

	/**
	 * Caches the t2p5 entries in the entity cache if it is enabled.
	 *
	 * @param t2p5Entries the t2p5 entries
	 */
	public static void cacheResult(List<T2P5Entry> t2p5Entries) {
		getPersistence().cacheResult(t2p5Entries);
	}

	/**
	 * Creates a new t2p5 entry with the primary key. Does not add the t2p5 entry to the database.
	 *
	 * @param t2p5EntryId the primary key for the new t2p5 entry
	 * @return the new t2p5 entry
	 */
	public static T2P5Entry create(long t2p5EntryId) {
		return getPersistence().create(t2p5EntryId);
	}

	/**
	 * Removes the t2p5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry that was removed
	 * @throws NoSuchT2P5EntryException if a t2p5 entry with the primary key could not be found
	 */
	public static T2P5Entry remove(long t2p5EntryId)
		throws com.acme.t2p5.exception.NoSuchT2P5EntryException {

		return getPersistence().remove(t2p5EntryId);
	}

	public static T2P5Entry updateImpl(T2P5Entry t2p5Entry) {
		return getPersistence().updateImpl(t2p5Entry);
	}

	/**
	 * Returns the t2p5 entry with the primary key or throws a <code>NoSuchT2P5EntryException</code> if it could not be found.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry
	 * @throws NoSuchT2P5EntryException if a t2p5 entry with the primary key could not be found
	 */
	public static T2P5Entry findByPrimaryKey(long t2p5EntryId)
		throws com.acme.t2p5.exception.NoSuchT2P5EntryException {

		return getPersistence().findByPrimaryKey(t2p5EntryId);
	}

	/**
	 * Returns the t2p5 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry, or <code>null</code> if a t2p5 entry with the primary key could not be found
	 */
	public static T2P5Entry fetchByPrimaryKey(long t2p5EntryId) {
		return getPersistence().fetchByPrimaryKey(t2p5EntryId);
	}

	/**
	 * Returns all the t2p5 entries.
	 *
	 * @return the t2p5 entries
	 */
	public static List<T2P5Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the t2p5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>T2P5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of t2p5 entries
	 * @param end the upper bound of the range of t2p5 entries (not inclusive)
	 * @return the range of t2p5 entries
	 */
	public static List<T2P5Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the t2p5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>T2P5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of t2p5 entries
	 * @param end the upper bound of the range of t2p5 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of t2p5 entries
	 */
	public static List<T2P5Entry> findAll(
		int start, int end, OrderByComparator<T2P5Entry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the t2p5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>T2P5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of t2p5 entries
	 * @param end the upper bound of the range of t2p5 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of t2p5 entries
	 */
	public static List<T2P5Entry> findAll(
		int start, int end, OrderByComparator<T2P5Entry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the t2p5 entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of t2p5 entries.
	 *
	 * @return the number of t2p5 entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static T2P5EntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<T2P5EntryPersistence, T2P5EntryPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(T2P5EntryPersistence.class);

		ServiceTracker<T2P5EntryPersistence, T2P5EntryPersistence>
			serviceTracker =
				new ServiceTracker<T2P5EntryPersistence, T2P5EntryPersistence>(
					bundle.getBundleContext(), T2P5EntryPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}