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

import com.acme.t2p5.exception.NoSuchT2P5EntryException;
import com.acme.t2p5.model.T2P5Entry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the t2p5 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see T2P5EntryUtil
 * @generated
 */
@ProviderType
public interface T2P5EntryPersistence extends BasePersistence<T2P5Entry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link T2P5EntryUtil} to access the t2p5 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the t2p5 entry in the entity cache if it is enabled.
	 *
	 * @param t2p5Entry the t2p5 entry
	 */
	public void cacheResult(T2P5Entry t2p5Entry);

	/**
	 * Caches the t2p5 entries in the entity cache if it is enabled.
	 *
	 * @param t2p5Entries the t2p5 entries
	 */
	public void cacheResult(java.util.List<T2P5Entry> t2p5Entries);

	/**
	 * Creates a new t2p5 entry with the primary key. Does not add the t2p5 entry to the database.
	 *
	 * @param t2p5EntryId the primary key for the new t2p5 entry
	 * @return the new t2p5 entry
	 */
	public T2P5Entry create(long t2p5EntryId);

	/**
	 * Removes the t2p5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry that was removed
	 * @throws NoSuchT2P5EntryException if a t2p5 entry with the primary key could not be found
	 */
	public T2P5Entry remove(long t2p5EntryId) throws NoSuchT2P5EntryException;

	public T2P5Entry updateImpl(T2P5Entry t2p5Entry);

	/**
	 * Returns the t2p5 entry with the primary key or throws a <code>NoSuchT2P5EntryException</code> if it could not be found.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry
	 * @throws NoSuchT2P5EntryException if a t2p5 entry with the primary key could not be found
	 */
	public T2P5Entry findByPrimaryKey(long t2p5EntryId)
		throws NoSuchT2P5EntryException;

	/**
	 * Returns the t2p5 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry, or <code>null</code> if a t2p5 entry with the primary key could not be found
	 */
	public T2P5Entry fetchByPrimaryKey(long t2p5EntryId);

	/**
	 * Returns all the t2p5 entries.
	 *
	 * @return the t2p5 entries
	 */
	public java.util.List<T2P5Entry> findAll();

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
	public java.util.List<T2P5Entry> findAll(int start, int end);

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
	public java.util.List<T2P5Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<T2P5Entry>
			orderByComparator);

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
	public java.util.List<T2P5Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<T2P5Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the t2p5 entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of t2p5 entries.
	 *
	 * @return the number of t2p5 entries
	 */
	public int countAll();

}