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

import com.acme.h6d2.exception.NoSuchH6D2Exception;
import com.acme.h6d2.model.H6D2;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the h6d2 service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see H6D2Util
 * @generated
 */
@ProviderType
public interface H6D2Persistence extends BasePersistence<H6D2> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link H6D2Util} to access the h6d2 persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the h6d2s where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching h6d2s
	 */
	public java.util.List<H6D2> findByUuid(String uuid);

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
	public java.util.List<H6D2> findByUuid(String uuid, int start, int end);

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
	public java.util.List<H6D2> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator);

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
	public java.util.List<H6D2> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public H6D2 findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2>
				orderByComparator)
		throws NoSuchH6D2Exception;

	/**
	 * Returns the first h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public H6D2 fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator);

	/**
	 * Returns the last h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public H6D2 findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2>
				orderByComparator)
		throws NoSuchH6D2Exception;

	/**
	 * Returns the last h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public H6D2 fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator);

	/**
	 * Returns the h6d2s before and after the current h6d2 in the ordered set where uuid = &#63;.
	 *
	 * @param h6d2Id the primary key of the current h6d2
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next h6d2
	 * @throws NoSuchH6D2Exception if a h6d2 with the primary key could not be found
	 */
	public H6D2[] findByUuid_PrevAndNext(
			long h6d2Id, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2>
				orderByComparator)
		throws NoSuchH6D2Exception;

	/**
	 * Removes all the h6d2s where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of h6d2s where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching h6d2s
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the h6d2 where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchH6D2Exception</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public H6D2 findByUUID_G(String uuid, long groupId)
		throws NoSuchH6D2Exception;

	/**
	 * Returns the h6d2 where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public H6D2 fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the h6d2 where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public H6D2 fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the h6d2 where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the h6d2 that was removed
	 */
	public H6D2 removeByUUID_G(String uuid, long groupId)
		throws NoSuchH6D2Exception;

	/**
	 * Returns the number of h6d2s where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching h6d2s
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the h6d2s where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching h6d2s
	 */
	public java.util.List<H6D2> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<H6D2> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<H6D2> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator);

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
	public java.util.List<H6D2> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public H6D2 findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2>
				orderByComparator)
		throws NoSuchH6D2Exception;

	/**
	 * Returns the first h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public H6D2 fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator);

	/**
	 * Returns the last h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2
	 * @throws NoSuchH6D2Exception if a matching h6d2 could not be found
	 */
	public H6D2 findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2>
				orderByComparator)
		throws NoSuchH6D2Exception;

	/**
	 * Returns the last h6d2 in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public H6D2 fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator);

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
	public H6D2[] findByUuid_C_PrevAndNext(
			long h6d2Id, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2>
				orderByComparator)
		throws NoSuchH6D2Exception;

	/**
	 * Removes all the h6d2s where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of h6d2s where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching h6d2s
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Caches the h6d2 in the entity cache if it is enabled.
	 *
	 * @param h6d2 the h6d2
	 */
	public void cacheResult(H6D2 h6d2);

	/**
	 * Caches the h6d2s in the entity cache if it is enabled.
	 *
	 * @param h6d2s the h6d2s
	 */
	public void cacheResult(java.util.List<H6D2> h6d2s);

	/**
	 * Creates a new h6d2 with the primary key. Does not add the h6d2 to the database.
	 *
	 * @param h6d2Id the primary key for the new h6d2
	 * @return the new h6d2
	 */
	public H6D2 create(long h6d2Id);

	/**
	 * Removes the h6d2 with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param h6d2Id the primary key of the h6d2
	 * @return the h6d2 that was removed
	 * @throws NoSuchH6D2Exception if a h6d2 with the primary key could not be found
	 */
	public H6D2 remove(long h6d2Id) throws NoSuchH6D2Exception;

	public H6D2 updateImpl(H6D2 h6d2);

	/**
	 * Returns the h6d2 with the primary key or throws a <code>NoSuchH6D2Exception</code> if it could not be found.
	 *
	 * @param h6d2Id the primary key of the h6d2
	 * @return the h6d2
	 * @throws NoSuchH6D2Exception if a h6d2 with the primary key could not be found
	 */
	public H6D2 findByPrimaryKey(long h6d2Id) throws NoSuchH6D2Exception;

	/**
	 * Returns the h6d2 with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param h6d2Id the primary key of the h6d2
	 * @return the h6d2, or <code>null</code> if a h6d2 with the primary key could not be found
	 */
	public H6D2 fetchByPrimaryKey(long h6d2Id);

	/**
	 * Returns all the h6d2s.
	 *
	 * @return the h6d2s
	 */
	public java.util.List<H6D2> findAll();

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
	public java.util.List<H6D2> findAll(int start, int end);

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
	public java.util.List<H6D2> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator);

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
	public java.util.List<H6D2> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the h6d2s from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of h6d2s.
	 *
	 * @return the number of h6d2s
	 */
	public int countAll();

}