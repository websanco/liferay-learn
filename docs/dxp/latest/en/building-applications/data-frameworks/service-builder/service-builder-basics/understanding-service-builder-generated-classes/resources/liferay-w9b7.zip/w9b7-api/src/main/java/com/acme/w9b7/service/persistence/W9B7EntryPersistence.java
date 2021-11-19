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

import com.acme.w9b7.exception.NoSuchW9B7EntryException;
import com.acme.w9b7.model.W9B7Entry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the w9b7 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see W9B7EntryUtil
 * @generated
 */
@ProviderType
public interface W9B7EntryPersistence extends BasePersistence<W9B7Entry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link W9B7EntryUtil} to access the w9b7 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the w9b7 entry in the entity cache if it is enabled.
	 *
	 * @param w9b7Entry the w9b7 entry
	 */
	public void cacheResult(W9B7Entry w9b7Entry);

	/**
	 * Caches the w9b7 entries in the entity cache if it is enabled.
	 *
	 * @param w9b7Entries the w9b7 entries
	 */
	public void cacheResult(java.util.List<W9B7Entry> w9b7Entries);

	/**
	 * Creates a new w9b7 entry with the primary key. Does not add the w9b7 entry to the database.
	 *
	 * @param w9b7EntryId the primary key for the new w9b7 entry
	 * @return the new w9b7 entry
	 */
	public W9B7Entry create(long w9b7EntryId);

	/**
	 * Removes the w9b7 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry that was removed
	 * @throws NoSuchW9B7EntryException if a w9b7 entry with the primary key could not be found
	 */
	public W9B7Entry remove(long w9b7EntryId) throws NoSuchW9B7EntryException;

	public W9B7Entry updateImpl(W9B7Entry w9b7Entry);

	/**
	 * Returns the w9b7 entry with the primary key or throws a <code>NoSuchW9B7EntryException</code> if it could not be found.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry
	 * @throws NoSuchW9B7EntryException if a w9b7 entry with the primary key could not be found
	 */
	public W9B7Entry findByPrimaryKey(long w9b7EntryId)
		throws NoSuchW9B7EntryException;

	/**
	 * Returns the w9b7 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry, or <code>null</code> if a w9b7 entry with the primary key could not be found
	 */
	public W9B7Entry fetchByPrimaryKey(long w9b7EntryId);

	/**
	 * Returns all the w9b7 entries.
	 *
	 * @return the w9b7 entries
	 */
	public java.util.List<W9B7Entry> findAll();

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
	public java.util.List<W9B7Entry> findAll(int start, int end);

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
	public java.util.List<W9B7Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<W9B7Entry>
			orderByComparator);

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
	public java.util.List<W9B7Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<W9B7Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the w9b7 entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of w9b7 entries.
	 *
	 * @return the number of w9b7 entries
	 */
	public int countAll();

}