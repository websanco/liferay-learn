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

package com.acme.e4g5.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link E4G5EntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see E4G5EntryLocalService
 * @generated
 */
public class E4G5EntryLocalServiceWrapper
	implements E4G5EntryLocalService, ServiceWrapper<E4G5EntryLocalService> {

	public E4G5EntryLocalServiceWrapper() {
		this(null);
	}

	public E4G5EntryLocalServiceWrapper(
		E4G5EntryLocalService e4g5EntryLocalService) {

		_e4g5EntryLocalService = e4g5EntryLocalService;
	}

	/**
	 * Adds the e4g5 entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect E4G5EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param e4g5Entry the e4g5 entry
	 * @return the e4g5 entry that was added
	 */
	@Override
	public com.acme.e4g5.model.E4G5Entry addE4G5Entry(
		com.acme.e4g5.model.E4G5Entry e4g5Entry) {

		return _e4g5EntryLocalService.addE4G5Entry(e4g5Entry);
	}

	@Override
	public com.acme.e4g5.model.E4G5Entry addE4G5Entry(
			String description, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _e4g5EntryLocalService.addE4G5Entry(description, name);
	}

	/**
	 * Creates a new e4g5 entry with the primary key. Does not add the e4g5 entry to the database.
	 *
	 * @param e4g5EntryId the primary key for the new e4g5 entry
	 * @return the new e4g5 entry
	 */
	@Override
	public com.acme.e4g5.model.E4G5Entry createE4G5Entry(long e4g5EntryId) {
		return _e4g5EntryLocalService.createE4G5Entry(e4g5EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _e4g5EntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the e4g5 entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect E4G5EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param e4g5Entry the e4g5 entry
	 * @return the e4g5 entry that was removed
	 */
	@Override
	public com.acme.e4g5.model.E4G5Entry deleteE4G5Entry(
		com.acme.e4g5.model.E4G5Entry e4g5Entry) {

		return _e4g5EntryLocalService.deleteE4G5Entry(e4g5Entry);
	}

	/**
	 * Deletes the e4g5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect E4G5EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param e4g5EntryId the primary key of the e4g5 entry
	 * @return the e4g5 entry that was removed
	 * @throws PortalException if a e4g5 entry with the primary key could not be found
	 */
	@Override
	public com.acme.e4g5.model.E4G5Entry deleteE4G5Entry(long e4g5EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _e4g5EntryLocalService.deleteE4G5Entry(e4g5EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _e4g5EntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _e4g5EntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _e4g5EntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _e4g5EntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _e4g5EntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.e4g5.model.impl.E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _e4g5EntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.e4g5.model.impl.E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _e4g5EntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _e4g5EntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _e4g5EntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.acme.e4g5.model.E4G5Entry fetchE4G5Entry(long e4g5EntryId) {
		return _e4g5EntryLocalService.fetchE4G5Entry(e4g5EntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _e4g5EntryLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the e4g5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.e4g5.model.impl.E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e4g5 entries
	 * @param end the upper bound of the range of e4g5 entries (not inclusive)
	 * @return the range of e4g5 entries
	 */
	@Override
	public java.util.List<com.acme.e4g5.model.E4G5Entry> getE4G5Entries(
		int start, int end) {

		return _e4g5EntryLocalService.getE4G5Entries(start, end);
	}

	/**
	 * Returns the number of e4g5 entries.
	 *
	 * @return the number of e4g5 entries
	 */
	@Override
	public int getE4G5EntriesCount() {
		return _e4g5EntryLocalService.getE4G5EntriesCount();
	}

	/**
	 * Returns the e4g5 entry with the primary key.
	 *
	 * @param e4g5EntryId the primary key of the e4g5 entry
	 * @return the e4g5 entry
	 * @throws PortalException if a e4g5 entry with the primary key could not be found
	 */
	@Override
	public com.acme.e4g5.model.E4G5Entry getE4G5Entry(long e4g5EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _e4g5EntryLocalService.getE4G5Entry(e4g5EntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _e4g5EntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _e4g5EntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _e4g5EntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the e4g5 entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect E4G5EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param e4g5Entry the e4g5 entry
	 * @return the e4g5 entry that was updated
	 */
	@Override
	public com.acme.e4g5.model.E4G5Entry updateE4G5Entry(
		com.acme.e4g5.model.E4G5Entry e4g5Entry) {

		return _e4g5EntryLocalService.updateE4G5Entry(e4g5Entry);
	}

	@Override
	public com.acme.e4g5.model.E4G5Entry updateE4G5Entry(
			long e4g5EntryId, String description, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _e4g5EntryLocalService.updateE4G5Entry(
			e4g5EntryId, description, name);
	}

	@Override
	public E4G5EntryLocalService getWrappedService() {
		return _e4g5EntryLocalService;
	}

	@Override
	public void setWrappedService(E4G5EntryLocalService e4g5EntryLocalService) {
		_e4g5EntryLocalService = e4g5EntryLocalService;
	}

	private E4G5EntryLocalService _e4g5EntryLocalService;

}