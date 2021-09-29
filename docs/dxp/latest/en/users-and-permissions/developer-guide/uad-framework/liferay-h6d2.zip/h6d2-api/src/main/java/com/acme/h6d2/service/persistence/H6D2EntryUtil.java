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

package com.acme.h6d2.service.persistence;

import com.acme.h6d2.model.H6D2Entry;

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
 * The persistence utility for the h6d2 entry service. This utility wraps <code>com.acme.h6d2.service.persistence.impl.H6D2EntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see H6D2EntryPersistence
 * @generated
 */
public class H6D2EntryUtil {

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
	public static void clearCache(H6D2Entry h6d2Entry) {
		getPersistence().clearCache(h6d2Entry);
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
	public static Map<Serializable, H6D2Entry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<H6D2Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<H6D2Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<H6D2Entry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static H6D2Entry update(H6D2Entry h6d2Entry) {
		return getPersistence().update(h6d2Entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static H6D2Entry update(
		H6D2Entry h6d2Entry, ServiceContext serviceContext) {

		return getPersistence().update(h6d2Entry, serviceContext);
	}

	/**
	 * Returns all the h6d2 entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching h6d2 entries
	 */
	public static List<H6D2Entry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the h6d2 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @return the range of matching h6d2 entries
	 */
	public static List<H6D2Entry> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching h6d2 entries
	 */
	public static List<H6D2Entry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching h6d2 entries
	 */
	public static List<H6D2Entry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry findByUuid_First(
			String uuid, OrderByComparator<H6D2Entry> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry fetchByUuid_First(
		String uuid, OrderByComparator<H6D2Entry> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry findByUuid_Last(
			String uuid, OrderByComparator<H6D2Entry> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry fetchByUuid_Last(
		String uuid, OrderByComparator<H6D2Entry> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the h6d2 entries before and after the current h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param h6d2EntryId the primary key of the current h6d2 entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	public static H6D2Entry[] findByUuid_PrevAndNext(
			long h6d2EntryId, String uuid,
			OrderByComparator<H6D2Entry> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().findByUuid_PrevAndNext(
			h6d2EntryId, uuid, orderByComparator);
	}

	/**
	 * Removes all the h6d2 entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of h6d2 entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching h6d2 entries
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry findByUUID_G(String uuid, long groupId)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the h6d2 entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the h6d2 entry that was removed
	 */
	public static H6D2Entry removeByUUID_G(String uuid, long groupId)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of h6d2 entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching h6d2 entries
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching h6d2 entries
	 */
	public static List<H6D2Entry> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @return the range of matching h6d2 entries
	 */
	public static List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching h6d2 entries
	 */
	public static List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching h6d2 entries
	 */
	public static List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<H6D2Entry> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<H6D2Entry> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<H6D2Entry> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public static H6D2Entry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<H6D2Entry> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the h6d2 entries before and after the current h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param h6d2EntryId the primary key of the current h6d2 entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	public static H6D2Entry[] findByUuid_C_PrevAndNext(
			long h6d2EntryId, String uuid, long companyId,
			OrderByComparator<H6D2Entry> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().findByUuid_C_PrevAndNext(
			h6d2EntryId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the h6d2 entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching h6d2 entries
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Caches the h6d2 entry in the entity cache if it is enabled.
	 *
	 * @param h6d2Entry the h6d2 entry
	 */
	public static void cacheResult(H6D2Entry h6d2Entry) {
		getPersistence().cacheResult(h6d2Entry);
	}

	/**
	 * Caches the h6d2 entries in the entity cache if it is enabled.
	 *
	 * @param h6d2Entries the h6d2 entries
	 */
	public static void cacheResult(List<H6D2Entry> h6d2Entries) {
		getPersistence().cacheResult(h6d2Entries);
	}

	/**
	 * Creates a new h6d2 entry with the primary key. Does not add the h6d2 entry to the database.
	 *
	 * @param h6d2EntryId the primary key for the new h6d2 entry
	 * @return the new h6d2 entry
	 */
	public static H6D2Entry create(long h6d2EntryId) {
		return getPersistence().create(h6d2EntryId);
	}

	/**
	 * Removes the h6d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry that was removed
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	public static H6D2Entry remove(long h6d2EntryId)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().remove(h6d2EntryId);
	}

	public static H6D2Entry updateImpl(H6D2Entry h6d2Entry) {
		return getPersistence().updateImpl(h6d2Entry);
	}

	/**
	 * Returns the h6d2 entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	public static H6D2Entry findByPrimaryKey(long h6d2EntryId)
		throws com.acme.h6d2.exception.NoSuchEntryException {

		return getPersistence().findByPrimaryKey(h6d2EntryId);
	}

	/**
	 * Returns the h6d2 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry, or <code>null</code> if a h6d2 entry with the primary key could not be found
	 */
	public static H6D2Entry fetchByPrimaryKey(long h6d2EntryId) {
		return getPersistence().fetchByPrimaryKey(h6d2EntryId);
	}

	/**
	 * Returns all the h6d2 entries.
	 *
	 * @return the h6d2 entries
	 */
	public static List<H6D2Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the h6d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @return the range of h6d2 entries
	 */
	public static List<H6D2Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h6d2 entries
	 */
	public static List<H6D2Entry> findAll(
		int start, int end, OrderByComparator<H6D2Entry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of h6d2 entries
	 */
	public static List<H6D2Entry> findAll(
		int start, int end, OrderByComparator<H6D2Entry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the h6d2 entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of h6d2 entries.
	 *
	 * @return the number of h6d2 entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static H6D2EntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<H6D2EntryPersistence, H6D2EntryPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(H6D2EntryPersistence.class);

		ServiceTracker<H6D2EntryPersistence, H6D2EntryPersistence>
			serviceTracker =
				new ServiceTracker<H6D2EntryPersistence, H6D2EntryPersistence>(
					bundle.getBundleContext(), H6D2EntryPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}