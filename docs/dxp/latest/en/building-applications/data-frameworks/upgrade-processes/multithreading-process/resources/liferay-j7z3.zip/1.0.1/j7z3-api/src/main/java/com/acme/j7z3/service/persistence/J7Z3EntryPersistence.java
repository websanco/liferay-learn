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

import com.acme.j7z3.exception.NoSuchJ7Z3EntryException;
import com.acme.j7z3.model.J7Z3Entry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the j7z3 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see J7Z3EntryUtil
 * @generated
 */
@ProviderType
public interface J7Z3EntryPersistence extends BasePersistence<J7Z3Entry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link J7Z3EntryUtil} to access the j7z3 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the j7z3 entry in the entity cache if it is enabled.
	 *
	 * @param j7z3Entry the j7z3 entry
	 */
	public void cacheResult(J7Z3Entry j7z3Entry);

	/**
	 * Caches the j7z3 entries in the entity cache if it is enabled.
	 *
	 * @param j7z3Entries the j7z3 entries
	 */
	public void cacheResult(java.util.List<J7Z3Entry> j7z3Entries);

	/**
	 * Creates a new j7z3 entry with the primary key. Does not add the j7z3 entry to the database.
	 *
	 * @param j7z3EntryId the primary key for the new j7z3 entry
	 * @return the new j7z3 entry
	 */
	public J7Z3Entry create(long j7z3EntryId);

	/**
	 * Removes the j7z3 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry that was removed
	 * @throws NoSuchJ7Z3EntryException if a j7z3 entry with the primary key could not be found
	 */
	public J7Z3Entry remove(long j7z3EntryId) throws NoSuchJ7Z3EntryException;

	public J7Z3Entry updateImpl(J7Z3Entry j7z3Entry);

	/**
	 * Returns the j7z3 entry with the primary key or throws a <code>NoSuchJ7Z3EntryException</code> if it could not be found.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry
	 * @throws NoSuchJ7Z3EntryException if a j7z3 entry with the primary key could not be found
	 */
	public J7Z3Entry findByPrimaryKey(long j7z3EntryId)
		throws NoSuchJ7Z3EntryException;

	/**
	 * Returns the j7z3 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry, or <code>null</code> if a j7z3 entry with the primary key could not be found
	 */
	public J7Z3Entry fetchByPrimaryKey(long j7z3EntryId);

	/**
	 * Returns all the j7z3 entries.
	 *
	 * @return the j7z3 entries
	 */
	public java.util.List<J7Z3Entry> findAll();

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
	public java.util.List<J7Z3Entry> findAll(int start, int end);

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
	public java.util.List<J7Z3Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<J7Z3Entry>
			orderByComparator);

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
	public java.util.List<J7Z3Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<J7Z3Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the j7z3 entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of j7z3 entries.
	 *
	 * @return the number of j7z3 entries
	 */
	public int countAll();

}