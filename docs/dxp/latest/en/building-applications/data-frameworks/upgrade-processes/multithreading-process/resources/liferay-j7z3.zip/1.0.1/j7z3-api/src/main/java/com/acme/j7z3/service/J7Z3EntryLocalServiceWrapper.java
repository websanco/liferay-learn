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

package com.acme.j7z3.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link J7Z3EntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see J7Z3EntryLocalService
 * @generated
 */
public class J7Z3EntryLocalServiceWrapper
	implements J7Z3EntryLocalService, ServiceWrapper<J7Z3EntryLocalService> {

	public J7Z3EntryLocalServiceWrapper() {
		this(null);
	}

	public J7Z3EntryLocalServiceWrapper(
		J7Z3EntryLocalService j7z3EntryLocalService) {

		_j7z3EntryLocalService = j7z3EntryLocalService;
	}

	/**
	 * Adds the j7z3 entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect J7Z3EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param j7z3Entry the j7z3 entry
	 * @return the j7z3 entry that was added
	 */
	@Override
	public com.acme.j7z3.model.J7Z3Entry addJ7Z3Entry(
		com.acme.j7z3.model.J7Z3Entry j7z3Entry) {

		return _j7z3EntryLocalService.addJ7Z3Entry(j7z3Entry);
	}

	/**
	 * Creates a new j7z3 entry with the primary key. Does not add the j7z3 entry to the database.
	 *
	 * @param j7z3EntryId the primary key for the new j7z3 entry
	 * @return the new j7z3 entry
	 */
	@Override
	public com.acme.j7z3.model.J7Z3Entry createJ7Z3Entry(long j7z3EntryId) {
		return _j7z3EntryLocalService.createJ7Z3Entry(j7z3EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _j7z3EntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the j7z3 entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect J7Z3EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param j7z3Entry the j7z3 entry
	 * @return the j7z3 entry that was removed
	 */
	@Override
	public com.acme.j7z3.model.J7Z3Entry deleteJ7Z3Entry(
		com.acme.j7z3.model.J7Z3Entry j7z3Entry) {

		return _j7z3EntryLocalService.deleteJ7Z3Entry(j7z3Entry);
	}

	/**
	 * Deletes the j7z3 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect J7Z3EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry that was removed
	 * @throws PortalException if a j7z3 entry with the primary key could not be found
	 */
	@Override
	public com.acme.j7z3.model.J7Z3Entry deleteJ7Z3Entry(long j7z3EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _j7z3EntryLocalService.deleteJ7Z3Entry(j7z3EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _j7z3EntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _j7z3EntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _j7z3EntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _j7z3EntryLocalService.dynamicQuery();
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

		return _j7z3EntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.j7z3.model.impl.J7Z3EntryModelImpl</code>.
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

		return _j7z3EntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.j7z3.model.impl.J7Z3EntryModelImpl</code>.
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

		return _j7z3EntryLocalService.dynamicQuery(
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

		return _j7z3EntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _j7z3EntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.acme.j7z3.model.J7Z3Entry fetchJ7Z3Entry(long j7z3EntryId) {
		return _j7z3EntryLocalService.fetchJ7Z3Entry(j7z3EntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _j7z3EntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _j7z3EntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the j7z3 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.j7z3.model.impl.J7Z3EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of j7z3 entries
	 * @param end the upper bound of the range of j7z3 entries (not inclusive)
	 * @return the range of j7z3 entries
	 */
	@Override
	public java.util.List<com.acme.j7z3.model.J7Z3Entry> getJ7Z3Entries(
		int start, int end) {

		return _j7z3EntryLocalService.getJ7Z3Entries(start, end);
	}

	/**
	 * Returns the number of j7z3 entries.
	 *
	 * @return the number of j7z3 entries
	 */
	@Override
	public int getJ7Z3EntriesCount() {
		return _j7z3EntryLocalService.getJ7Z3EntriesCount();
	}

	/**
	 * Returns the j7z3 entry with the primary key.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry
	 * @throws PortalException if a j7z3 entry with the primary key could not be found
	 */
	@Override
	public com.acme.j7z3.model.J7Z3Entry getJ7Z3Entry(long j7z3EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _j7z3EntryLocalService.getJ7Z3Entry(j7z3EntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _j7z3EntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _j7z3EntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the j7z3 entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect J7Z3EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param j7z3Entry the j7z3 entry
	 * @return the j7z3 entry that was updated
	 */
	@Override
	public com.acme.j7z3.model.J7Z3Entry updateJ7Z3Entry(
		com.acme.j7z3.model.J7Z3Entry j7z3Entry) {

		return _j7z3EntryLocalService.updateJ7Z3Entry(j7z3Entry);
	}

	@Override
	public J7Z3EntryLocalService getWrappedService() {
		return _j7z3EntryLocalService;
	}

	@Override
	public void setWrappedService(J7Z3EntryLocalService j7z3EntryLocalService) {
		_j7z3EntryLocalService = j7z3EntryLocalService;
	}

	private J7Z3EntryLocalService _j7z3EntryLocalService;

}