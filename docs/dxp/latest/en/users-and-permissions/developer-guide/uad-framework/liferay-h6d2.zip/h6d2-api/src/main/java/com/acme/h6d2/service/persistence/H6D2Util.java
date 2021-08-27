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

import com.acme.h6d2.model.H6D2;

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
 * The persistence utility for the h6d2 service. This utility wraps <code>com.acme.h6d2.service.persistence.impl.H6D2PersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see H6D2Persistence
 * @generated
 */
public class H6D2Util {

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
	public static void clearCache(H6D2 h6d2) {
		getPersistence().clearCache(h6d2);
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
	public static Map<Serializable, H6D2> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<H6D2> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<H6D2> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<H6D2> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<H6D2> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static H6D2 update(H6D2 h6d2) {
		return getPersistence().update(h6d2);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static H6D2 update(H6D2 h6d2, ServiceContext serviceContext) {
		return getPersistence().update(h6d2, serviceContext);
	}

	/**
	 * Returns all the h6d2s where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching h6d2s
	 */
	public static List<H6D2> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the h6d2s where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @return the range of matching h6d2s
	 */
	public static List<H6D2> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the h6d2s where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching h6d2s
	 */
	public static List<H6D2> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<H6D2> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the h6d2s where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching h6d2s
	 */
	public static List<H6D2> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<H6D2> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public static H6D2 findByUuid_First(
			String uuid, OrderByComparator<H6D2> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public static H6D2 fetchByUuid_First(
		String uuid, OrderByComparator<H6D2> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public static H6D2 findByUuid_Last(
			String uuid, OrderByComparator<H6D2> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public static H6D2 fetchByUuid_Last(
		String uuid, OrderByComparator<H6D2> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the h6d2s before and after the current h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param h6d2Id the primary key of the current h6d2
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next h6d2
	 * @throws NoSuchH6D2Exception if a h6d2 with the primary key could not be found
	 */
	public static H6D2[] findByUuid_PrevAndNext(
			long h6d2Id, String uuid, OrderByComparator<H6D2> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().findByUuid_PrevAndNext(
			h6d2Id, uuid, orderByComparator);
	}

	/**
	 * Removes all the h6d2s where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of h6d2s where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching h6d2s
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the h6d2 where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchH6D2Exception</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public static H6D2 findByUUID_G(String uuid, long groupId)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the h6d2 where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public static H6D2 fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the h6d2 where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public static H6D2 fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the h6d2 where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the h6d2 that was removed
	 */
	public static H6D2 removeByUUID_G(String uuid, long groupId)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of h6d2s where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching h6d2s
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the h6d2s where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching h6d2s
	 */
	public static List<H6D2> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the h6d2s where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @return the range of matching h6d2s
	 */
	public static List<H6D2> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the h6d2s where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching h6d2s
	 */
	public static List<H6D2> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<H6D2> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the h6d2s where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching h6d2s
	 */
	public static List<H6D2> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<H6D2> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public static H6D2 findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<H6D2> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public static H6D2 fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<H6D2> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public static H6D2 findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<H6D2> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public static H6D2 fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<H6D2> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the h6d2s before and after the current h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param h6d2Id the primary key of the current h6d2
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next h6d2
	 * @throws NoSuchH6D2Exception if a h6d2 with the primary key could not be found
	 */
	public static H6D2[] findByUuid_C_PrevAndNext(
			long h6d2Id, String uuid, long companyId,
			OrderByComparator<H6D2> orderByComparator)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().findByUuid_C_PrevAndNext(
			h6d2Id, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the h6d2s where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of h6d2s where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching h6d2s
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Caches the h6d2 in the entity cache if it is enabled.
	 *
	 * @param h6d2 the h6d2
	 */
	public static void cacheResult(H6D2 h6d2) {
		getPersistence().cacheResult(h6d2);
	}

	/**
	 * Caches the h6d2s in the entity cache if it is enabled.
	 *
	 * @param h6d2s the h6d2s
	 */
	public static void cacheResult(List<H6D2> h6d2s) {
		getPersistence().cacheResult(h6d2s);
	}

	/**
	 * Creates a new h6d2 with the primary key. Does not add the h6d2 to the database.
	 *
	 * @param h6d2Id the primary key for the new h6d2
	 * @return the new h6d2
	 */
	public static H6D2 create(long h6d2Id) {
		return getPersistence().create(h6d2Id);
	}

	/**
	 * Removes the h6d2 with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param h6d2Id the primary key of the h6d2
	 * @return the h6d2 that was removed
	 * @throws NoSuchH6D2Exception if a h6d2 with the primary key could not be found
	 */
	public static H6D2 remove(long h6d2Id)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().remove(h6d2Id);
	}

	public static H6D2 updateImpl(H6D2 h6d2) {
		return getPersistence().updateImpl(h6d2);
	}

	/**
	 * Returns the h6d2 with the primary key or throws a <code>NoSuchH6D2Exception</code> if it could not be found.
	 *
	 * @param h6d2Id the primary key of the h6d2
	 * @return the h6d2
	 * @throws NoSuchH6D2Exception if a h6d2 with the primary key could not be found
	 */
	public static H6D2 findByPrimaryKey(long h6d2Id)
		throws com.acme.h6d2.exception.NoSuchH6D2Exception {

		return getPersistence().findByPrimaryKey(h6d2Id);
	}

	/**
	 * Returns the h6d2 with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param h6d2Id the primary key of the h6d2
	 * @return the h6d2, or <code>null</code> if a h6d2 with the primary key could not be found
	 */
	public static H6D2 fetchByPrimaryKey(long h6d2Id) {
		return getPersistence().fetchByPrimaryKey(h6d2Id);
	}

	/**
	 * Returns all the h6d2s.
	 *
	 * @return the h6d2s
	 */
	public static List<H6D2> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the h6d2s.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @return the range of h6d2s
	 */
	public static List<H6D2> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the h6d2s.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h6d2s
	 */
	public static List<H6D2> findAll(
		int start, int end, OrderByComparator<H6D2> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the h6d2s.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of h6d2s
	 */
	public static List<H6D2> findAll(
		int start, int end, OrderByComparator<H6D2> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the h6d2s from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of h6d2s.
	 *
	 * @return the number of h6d2s
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static H6D2Persistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<H6D2Persistence, H6D2Persistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(H6D2Persistence.class);

		ServiceTracker<H6D2Persistence, H6D2Persistence> serviceTracker =
			new ServiceTracker<H6D2Persistence, H6D2Persistence>(
				bundle.getBundleContext(), H6D2Persistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}