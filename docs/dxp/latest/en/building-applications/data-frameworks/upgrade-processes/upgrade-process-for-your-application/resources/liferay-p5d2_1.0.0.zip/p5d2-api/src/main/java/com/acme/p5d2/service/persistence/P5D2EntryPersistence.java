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

package com.acme.p5d2.service.persistence;

import com.acme.p5d2.exception.NoSuchP5D2EntryException;
import com.acme.p5d2.model.P5D2Entry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the p5d2 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P5D2EntryUtil
 * @generated
 */
@ProviderType
public interface P5D2EntryPersistence extends BasePersistence<P5D2Entry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link P5D2EntryUtil} to access the p5d2 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the p5d2 entry in the entity cache if it is enabled.
	 *
	 * @param p5d2Entry the p5d2 entry
	 */
	public void cacheResult(P5D2Entry p5d2Entry);

	/**
	 * Caches the p5d2 entries in the entity cache if it is enabled.
	 *
	 * @param p5d2Entries the p5d2 entries
	 */
	public void cacheResult(java.util.List<P5D2Entry> p5d2Entries);

	/**
	 * Creates a new p5d2 entry with the primary key. Does not add the p5d2 entry to the database.
	 *
	 * @param p5d2EntryId the primary key for the new p5d2 entry
	 * @return the new p5d2 entry
	 */
	public P5D2Entry create(long p5d2EntryId);

	/**
	 * Removes the p5d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry that was removed
	 * @throws NoSuchP5D2EntryException if a p5d2 entry with the primary key could not be found
	 */
	public P5D2Entry remove(long p5d2EntryId) throws NoSuchP5D2EntryException;

	public P5D2Entry updateImpl(P5D2Entry p5d2Entry);

	/**
	 * Returns the p5d2 entry with the primary key or throws a <code>NoSuchP5D2EntryException</code> if it could not be found.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry
	 * @throws NoSuchP5D2EntryException if a p5d2 entry with the primary key could not be found
	 */
	public P5D2Entry findByPrimaryKey(long p5d2EntryId)
		throws NoSuchP5D2EntryException;

	/**
	 * Returns the p5d2 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry, or <code>null</code> if a p5d2 entry with the primary key could not be found
	 */
	public P5D2Entry fetchByPrimaryKey(long p5d2EntryId);

	/**
	 * Returns all the p5d2 entries.
	 *
	 * @return the p5d2 entries
	 */
	public java.util.List<P5D2Entry> findAll();

	/**
	 * Returns a range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @return the range of p5d2 entries
	 */
	public java.util.List<P5D2Entry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p5d2 entries
	 */
	public java.util.List<P5D2Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P5D2Entry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of p5d2 entries
	 */
	public java.util.List<P5D2Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P5D2Entry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the p5d2 entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of p5d2 entries.
	 *
	 * @return the number of p5d2 entries
	 */
	public int countAll();

}