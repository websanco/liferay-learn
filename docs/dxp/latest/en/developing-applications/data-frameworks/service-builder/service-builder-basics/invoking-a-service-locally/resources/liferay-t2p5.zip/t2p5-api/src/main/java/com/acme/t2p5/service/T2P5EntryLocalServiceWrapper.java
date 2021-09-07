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

package com.acme.t2p5.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link T2P5EntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see T2P5EntryLocalService
 * @generated
 */
public class T2P5EntryLocalServiceWrapper
	implements ServiceWrapper<T2P5EntryLocalService>, T2P5EntryLocalService {

	public T2P5EntryLocalServiceWrapper(
		T2P5EntryLocalService t2p5EntryLocalService) {

		_t2p5EntryLocalService = t2p5EntryLocalService;
	}

	@Override
	public com.acme.t2p5.model.T2P5Entry addT2P5Entry(
			String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _t2p5EntryLocalService.addT2P5Entry(name, description);
	}

	/**
	 * Adds the t2p5 entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect T2P5EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param t2p5Entry the t2p5 entry
	 * @return the t2p5 entry that was added
	 */
	@Override
	public com.acme.t2p5.model.T2P5Entry addT2P5Entry(
		com.acme.t2p5.model.T2P5Entry t2p5Entry) {

		return _t2p5EntryLocalService.addT2P5Entry(t2p5Entry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _t2p5EntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new t2p5 entry with the primary key. Does not add the t2p5 entry to the database.
	 *
	 * @param t2p5EntryId the primary key for the new t2p5 entry
	 * @return the new t2p5 entry
	 */
	@Override
	public com.acme.t2p5.model.T2P5Entry createT2P5Entry(long t2p5EntryId) {
		return _t2p5EntryLocalService.createT2P5Entry(t2p5EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _t2p5EntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the t2p5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect T2P5EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry that was removed
	 * @throws PortalException if a t2p5 entry with the primary key could not be found
	 */
	@Override
	public com.acme.t2p5.model.T2P5Entry deleteT2P5Entry(long t2p5EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _t2p5EntryLocalService.deleteT2P5Entry(t2p5EntryId);
	}

	/**
	 * Deletes the t2p5 entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect T2P5EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param t2p5Entry the t2p5 entry
	 * @return the t2p5 entry that was removed
	 */
	@Override
	public com.acme.t2p5.model.T2P5Entry deleteT2P5Entry(
		com.acme.t2p5.model.T2P5Entry t2p5Entry) {

		return _t2p5EntryLocalService.deleteT2P5Entry(t2p5Entry);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _t2p5EntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _t2p5EntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _t2p5EntryLocalService.dynamicQuery();
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

		return _t2p5EntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.t2p5.model.impl.T2P5EntryModelImpl</code>.
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

		return _t2p5EntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.t2p5.model.impl.T2P5EntryModelImpl</code>.
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

		return _t2p5EntryLocalService.dynamicQuery(
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

		return _t2p5EntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _t2p5EntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.acme.t2p5.model.T2P5Entry fetchT2P5Entry(long t2p5EntryId) {
		return _t2p5EntryLocalService.fetchT2P5Entry(t2p5EntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _t2p5EntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _t2p5EntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _t2p5EntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _t2p5EntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the t2p5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.t2p5.model.impl.T2P5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of t2p5 entries
	 * @param end the upper bound of the range of t2p5 entries (not inclusive)
	 * @return the range of t2p5 entries
	 */
	@Override
	public java.util.List<com.acme.t2p5.model.T2P5Entry> getT2P5Entries(
		int start, int end) {

		return _t2p5EntryLocalService.getT2P5Entries(start, end);
	}

	/**
	 * Returns the number of t2p5 entries.
	 *
	 * @return the number of t2p5 entries
	 */
	@Override
	public int getT2P5EntriesCount() {
		return _t2p5EntryLocalService.getT2P5EntriesCount();
	}

	/**
	 * Returns the t2p5 entry with the primary key.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry
	 * @throws PortalException if a t2p5 entry with the primary key could not be found
	 */
	@Override
	public com.acme.t2p5.model.T2P5Entry getT2P5Entry(long t2p5EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _t2p5EntryLocalService.getT2P5Entry(t2p5EntryId);
	}

	/**
	 * Updates the t2p5 entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect T2P5EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param t2p5Entry the t2p5 entry
	 * @return the t2p5 entry that was updated
	 */
	@Override
	public com.acme.t2p5.model.T2P5Entry updateT2P5Entry(
		com.acme.t2p5.model.T2P5Entry t2p5Entry) {

		return _t2p5EntryLocalService.updateT2P5Entry(t2p5Entry);
	}

	@Override
	public T2P5EntryLocalService getWrappedService() {
		return _t2p5EntryLocalService;
	}

	@Override
	public void setWrappedService(T2P5EntryLocalService t2p5EntryLocalService) {
		_t2p5EntryLocalService = t2p5EntryLocalService;
	}

	private T2P5EntryLocalService _t2p5EntryLocalService;

}