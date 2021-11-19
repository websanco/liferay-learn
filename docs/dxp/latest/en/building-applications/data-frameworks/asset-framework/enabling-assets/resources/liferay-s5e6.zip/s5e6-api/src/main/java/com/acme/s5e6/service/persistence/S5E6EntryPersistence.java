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

package com.acme.s5e6.service.persistence;

import com.acme.s5e6.exception.NoSuchS5E6EntryException;
import com.acme.s5e6.model.S5E6Entry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the s5e6 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see S5E6EntryUtil
 * @generated
 */
@ProviderType
public interface S5E6EntryPersistence extends BasePersistence<S5E6Entry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link S5E6EntryUtil} to access the s5e6 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the s5e6 entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching s5e6 entries
	 */
	public java.util.List<S5E6Entry> findByUuid(String uuid);

	/**
	 * Returns a range of all the s5e6 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @return the range of matching s5e6 entries
	 */
	public java.util.List<S5E6Entry> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the s5e6 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s5e6 entries
	 */
	public java.util.List<S5E6Entry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the s5e6 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching s5e6 entries
	 */
	public java.util.List<S5E6Entry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first s5e6 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s5e6 entry
	 * @throws NoSuchS5E6EntryException if a matching s5e6 entry could not be found
	 */
	public S5E6Entry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
				orderByComparator)
		throws NoSuchS5E6EntryException;

	/**
	 * Returns the first s5e6 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s5e6 entry, or <code>null</code> if a matching s5e6 entry could not be found
	 */
	public S5E6Entry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator);

	/**
	 * Returns the last s5e6 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s5e6 entry
	 * @throws NoSuchS5E6EntryException if a matching s5e6 entry could not be found
	 */
	public S5E6Entry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
				orderByComparator)
		throws NoSuchS5E6EntryException;

	/**
	 * Returns the last s5e6 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s5e6 entry, or <code>null</code> if a matching s5e6 entry could not be found
	 */
	public S5E6Entry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator);

	/**
	 * Returns the s5e6 entries before and after the current s5e6 entry in the ordered set where uuid = &#63;.
	 *
	 * @param S5E6EntryId the primary key of the current s5e6 entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s5e6 entry
	 * @throws NoSuchS5E6EntryException if a s5e6 entry with the primary key could not be found
	 */
	public S5E6Entry[] findByUuid_PrevAndNext(
			long S5E6EntryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
				orderByComparator)
		throws NoSuchS5E6EntryException;

	/**
	 * Removes all the s5e6 entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of s5e6 entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching s5e6 entries
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the s5e6 entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchS5E6EntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching s5e6 entry
	 * @throws NoSuchS5E6EntryException if a matching s5e6 entry could not be found
	 */
	public S5E6Entry findByUUID_G(String uuid, long groupId)
		throws NoSuchS5E6EntryException;

	/**
	 * Returns the s5e6 entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching s5e6 entry, or <code>null</code> if a matching s5e6 entry could not be found
	 */
	public S5E6Entry fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the s5e6 entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching s5e6 entry, or <code>null</code> if a matching s5e6 entry could not be found
	 */
	public S5E6Entry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the s5e6 entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the s5e6 entry that was removed
	 */
	public S5E6Entry removeByUUID_G(String uuid, long groupId)
		throws NoSuchS5E6EntryException;

	/**
	 * Returns the number of s5e6 entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching s5e6 entries
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the s5e6 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching s5e6 entries
	 */
	public java.util.List<S5E6Entry> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the s5e6 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @return the range of matching s5e6 entries
	 */
	public java.util.List<S5E6Entry> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the s5e6 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s5e6 entries
	 */
	public java.util.List<S5E6Entry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the s5e6 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching s5e6 entries
	 */
	public java.util.List<S5E6Entry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first s5e6 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s5e6 entry
	 * @throws NoSuchS5E6EntryException if a matching s5e6 entry could not be found
	 */
	public S5E6Entry findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
				orderByComparator)
		throws NoSuchS5E6EntryException;

	/**
	 * Returns the first s5e6 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s5e6 entry, or <code>null</code> if a matching s5e6 entry could not be found
	 */
	public S5E6Entry fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator);

	/**
	 * Returns the last s5e6 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s5e6 entry
	 * @throws NoSuchS5E6EntryException if a matching s5e6 entry could not be found
	 */
	public S5E6Entry findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
				orderByComparator)
		throws NoSuchS5E6EntryException;

	/**
	 * Returns the last s5e6 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s5e6 entry, or <code>null</code> if a matching s5e6 entry could not be found
	 */
	public S5E6Entry fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator);

	/**
	 * Returns the s5e6 entries before and after the current s5e6 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param S5E6EntryId the primary key of the current s5e6 entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s5e6 entry
	 * @throws NoSuchS5E6EntryException if a s5e6 entry with the primary key could not be found
	 */
	public S5E6Entry[] findByUuid_C_PrevAndNext(
			long S5E6EntryId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
				orderByComparator)
		throws NoSuchS5E6EntryException;

	/**
	 * Removes all the s5e6 entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of s5e6 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching s5e6 entries
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Caches the s5e6 entry in the entity cache if it is enabled.
	 *
	 * @param s5e6Entry the s5e6 entry
	 */
	public void cacheResult(S5E6Entry s5e6Entry);

	/**
	 * Caches the s5e6 entries in the entity cache if it is enabled.
	 *
	 * @param s5e6Entries the s5e6 entries
	 */
	public void cacheResult(java.util.List<S5E6Entry> s5e6Entries);

	/**
	 * Creates a new s5e6 entry with the primary key. Does not add the s5e6 entry to the database.
	 *
	 * @param S5E6EntryId the primary key for the new s5e6 entry
	 * @return the new s5e6 entry
	 */
	public S5E6Entry create(long S5E6EntryId);

	/**
	 * Removes the s5e6 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param S5E6EntryId the primary key of the s5e6 entry
	 * @return the s5e6 entry that was removed
	 * @throws NoSuchS5E6EntryException if a s5e6 entry with the primary key could not be found
	 */
	public S5E6Entry remove(long S5E6EntryId) throws NoSuchS5E6EntryException;

	public S5E6Entry updateImpl(S5E6Entry s5e6Entry);

	/**
	 * Returns the s5e6 entry with the primary key or throws a <code>NoSuchS5E6EntryException</code> if it could not be found.
	 *
	 * @param S5E6EntryId the primary key of the s5e6 entry
	 * @return the s5e6 entry
	 * @throws NoSuchS5E6EntryException if a s5e6 entry with the primary key could not be found
	 */
	public S5E6Entry findByPrimaryKey(long S5E6EntryId)
		throws NoSuchS5E6EntryException;

	/**
	 * Returns the s5e6 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param S5E6EntryId the primary key of the s5e6 entry
	 * @return the s5e6 entry, or <code>null</code> if a s5e6 entry with the primary key could not be found
	 */
	public S5E6Entry fetchByPrimaryKey(long S5E6EntryId);

	/**
	 * Returns all the s5e6 entries.
	 *
	 * @return the s5e6 entries
	 */
	public java.util.List<S5E6Entry> findAll();

	/**
	 * Returns a range of all the s5e6 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @return the range of s5e6 entries
	 */
	public java.util.List<S5E6Entry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the s5e6 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of s5e6 entries
	 */
	public java.util.List<S5E6Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the s5e6 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of s5e6 entries
	 */
	public java.util.List<S5E6Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<S5E6Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the s5e6 entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of s5e6 entries.
	 *
	 * @return the number of s5e6 entries
	 */
	public int countAll();

}