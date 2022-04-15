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

package com.acme.p5d2.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link P5D2EntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see P5D2EntryLocalService
 * @generated
 */
public class P5D2EntryLocalServiceWrapper
	implements P5D2EntryLocalService, ServiceWrapper<P5D2EntryLocalService> {

	public P5D2EntryLocalServiceWrapper() {
		this(null);
	}

	public P5D2EntryLocalServiceWrapper(
		P5D2EntryLocalService p5d2EntryLocalService) {

		_p5d2EntryLocalService = p5d2EntryLocalService;
	}

	/**
	 * Adds the p5d2 entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect P5D2EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param p5d2Entry the p5d2 entry
	 * @return the p5d2 entry that was added
	 */
	@Override
	public com.acme.p5d2.model.P5D2Entry addP5D2Entry(
		com.acme.p5d2.model.P5D2Entry p5d2Entry) {

		return _p5d2EntryLocalService.addP5D2Entry(p5d2Entry);
	}

	/**
	 * Creates a new p5d2 entry with the primary key. Does not add the p5d2 entry to the database.
	 *
	 * @param p5d2EntryId the primary key for the new p5d2 entry
	 * @return the new p5d2 entry
	 */
	@Override
	public com.acme.p5d2.model.P5D2Entry createP5D2Entry(long p5d2EntryId) {
		return _p5d2EntryLocalService.createP5D2Entry(p5d2EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p5d2EntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the p5d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect P5D2EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry that was removed
	 * @throws PortalException if a p5d2 entry with the primary key could not be found
	 */
	@Override
	public com.acme.p5d2.model.P5D2Entry deleteP5D2Entry(long p5d2EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p5d2EntryLocalService.deleteP5D2Entry(p5d2EntryId);
	}

	/**
	 * Deletes the p5d2 entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect P5D2EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param p5d2Entry the p5d2 entry
	 * @return the p5d2 entry that was removed
	 */
	@Override
	public com.acme.p5d2.model.P5D2Entry deleteP5D2Entry(
		com.acme.p5d2.model.P5D2Entry p5d2Entry) {

		return _p5d2EntryLocalService.deleteP5D2Entry(p5d2Entry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p5d2EntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _p5d2EntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _p5d2EntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _p5d2EntryLocalService.dynamicQuery();
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

		return _p5d2EntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.p5d2.model.impl.P5D2EntryModelImpl</code>.
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

		return _p5d2EntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.p5d2.model.impl.P5D2EntryModelImpl</code>.
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

		return _p5d2EntryLocalService.dynamicQuery(
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

		return _p5d2EntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _p5d2EntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.acme.p5d2.model.P5D2Entry fetchP5D2Entry(long p5d2EntryId) {
		return _p5d2EntryLocalService.fetchP5D2Entry(p5d2EntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _p5d2EntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _p5d2EntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _p5d2EntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns a range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.p5d2.model.impl.P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @return the range of p5d2 entries
	 */
	@Override
	public java.util.List<com.acme.p5d2.model.P5D2Entry> getP5D2Entries(
		int start, int end) {

		return _p5d2EntryLocalService.getP5D2Entries(start, end);
	}

	/**
	 * Returns the number of p5d2 entries.
	 *
	 * @return the number of p5d2 entries
	 */
	@Override
	public int getP5D2EntriesCount() {
		return _p5d2EntryLocalService.getP5D2EntriesCount();
	}

	/**
	 * Returns the p5d2 entry with the primary key.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry
	 * @throws PortalException if a p5d2 entry with the primary key could not be found
	 */
	@Override
	public com.acme.p5d2.model.P5D2Entry getP5D2Entry(long p5d2EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p5d2EntryLocalService.getP5D2Entry(p5d2EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p5d2EntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the p5d2 entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect P5D2EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param p5d2Entry the p5d2 entry
	 * @return the p5d2 entry that was updated
	 */
	@Override
	public com.acme.p5d2.model.P5D2Entry updateP5D2Entry(
		com.acme.p5d2.model.P5D2Entry p5d2Entry) {

		return _p5d2EntryLocalService.updateP5D2Entry(p5d2Entry);
	}

	@Override
	public P5D2EntryLocalService getWrappedService() {
		return _p5d2EntryLocalService;
	}

	@Override
	public void setWrappedService(P5D2EntryLocalService p5d2EntryLocalService) {
		_p5d2EntryLocalService = p5d2EntryLocalService;
	}

	private P5D2EntryLocalService _p5d2EntryLocalService;

}