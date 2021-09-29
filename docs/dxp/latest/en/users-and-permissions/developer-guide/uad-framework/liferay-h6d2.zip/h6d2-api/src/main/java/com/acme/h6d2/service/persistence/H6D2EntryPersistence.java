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

import com.acme.h6d2.exception.NoSuchEntryException;
import com.acme.h6d2.model.H6D2Entry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the h6d2 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see H6D2EntryUtil
 * @generated
 */
@ProviderType
public interface H6D2EntryPersistence extends BasePersistence<H6D2Entry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link H6D2EntryUtil} to access the h6d2 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the h6d2 entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching h6d2 entries
	 */
	public java.util.List<H6D2Entry> findByUuid(String uuid);

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
	public java.util.List<H6D2Entry> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<H6D2Entry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator);

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
	public java.util.List<H6D2Entry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public H6D2Entry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public H6D2Entry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator);

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public H6D2Entry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public H6D2Entry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator);

	/**
	 * Returns the h6d2 entries before and after the current h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param h6d2EntryId the primary key of the current h6d2 entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	public H6D2Entry[] findByUuid_PrevAndNext(
			long h6d2EntryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the h6d2 entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of h6d2 entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching h6d2 entries
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public H6D2Entry findByUUID_G(String uuid, long groupId)
		throws NoSuchEntryException;

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public H6D2Entry fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public H6D2Entry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the h6d2 entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the h6d2 entry that was removed
	 */
	public H6D2Entry removeByUUID_G(String uuid, long groupId)
		throws NoSuchEntryException;

	/**
	 * Returns the number of h6d2 entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching h6d2 entries
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching h6d2 entries
	 */
	public java.util.List<H6D2Entry> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator);

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
	public java.util.List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public H6D2Entry findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public H6D2Entry fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator);

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	public H6D2Entry findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	public H6D2Entry fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator);

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
	public H6D2Entry[] findByUuid_C_PrevAndNext(
			long h6d2EntryId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the h6d2 entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching h6d2 entries
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Caches the h6d2 entry in the entity cache if it is enabled.
	 *
	 * @param h6d2Entry the h6d2 entry
	 */
	public void cacheResult(H6D2Entry h6d2Entry);

	/**
	 * Caches the h6d2 entries in the entity cache if it is enabled.
	 *
	 * @param h6d2Entries the h6d2 entries
	 */
	public void cacheResult(java.util.List<H6D2Entry> h6d2Entries);

	/**
	 * Creates a new h6d2 entry with the primary key. Does not add the h6d2 entry to the database.
	 *
	 * @param h6d2EntryId the primary key for the new h6d2 entry
	 * @return the new h6d2 entry
	 */
	public H6D2Entry create(long h6d2EntryId);

	/**
	 * Removes the h6d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry that was removed
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	public H6D2Entry remove(long h6d2EntryId) throws NoSuchEntryException;

	public H6D2Entry updateImpl(H6D2Entry h6d2Entry);

	/**
	 * Returns the h6d2 entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	public H6D2Entry findByPrimaryKey(long h6d2EntryId)
		throws NoSuchEntryException;

	/**
	 * Returns the h6d2 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry, or <code>null</code> if a h6d2 entry with the primary key could not be found
	 */
	public H6D2Entry fetchByPrimaryKey(long h6d2EntryId);

	/**
	 * Returns all the h6d2 entries.
	 *
	 * @return the h6d2 entries
	 */
	public java.util.List<H6D2Entry> findAll();

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
	public java.util.List<H6D2Entry> findAll(int start, int end);

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
	public java.util.List<H6D2Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator);

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
	public java.util.List<H6D2Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<H6D2Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the h6d2 entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of h6d2 entries.
	 *
	 * @return the number of h6d2 entries
	 */
	public int countAll();

}