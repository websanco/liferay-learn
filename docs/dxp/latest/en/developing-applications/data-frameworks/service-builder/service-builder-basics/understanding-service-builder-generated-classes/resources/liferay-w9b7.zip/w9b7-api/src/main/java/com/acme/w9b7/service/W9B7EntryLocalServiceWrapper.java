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

package com.acme.w9b7.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link W9B7EntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see W9B7EntryLocalService
 * @generated
 */
public class W9B7EntryLocalServiceWrapper
	implements ServiceWrapper<W9B7EntryLocalService>, W9B7EntryLocalService {

	public W9B7EntryLocalServiceWrapper(
		W9B7EntryLocalService w9b7EntryLocalService) {

		_w9b7EntryLocalService = w9b7EntryLocalService;
	}

	/**
	 * Adds the w9b7 entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect W9B7EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param w9b7Entry the w9b7 entry
	 * @return the w9b7 entry that was added
	 */
	@Override
	public com.acme.w9b7.model.W9B7Entry addW9B7Entry(
		com.acme.w9b7.model.W9B7Entry w9b7Entry) {

		return _w9b7EntryLocalService.addW9B7Entry(w9b7Entry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _w9b7EntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new w9b7 entry with the primary key. Does not add the w9b7 entry to the database.
	 *
	 * @param w9b7EntryId the primary key for the new w9b7 entry
	 * @return the new w9b7 entry
	 */
	@Override
	public com.acme.w9b7.model.W9B7Entry createW9B7Entry(long w9b7EntryId) {
		return _w9b7EntryLocalService.createW9B7Entry(w9b7EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _w9b7EntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the w9b7 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect W9B7EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry that was removed
	 * @throws PortalException if a w9b7 entry with the primary key could not be found
	 */
	@Override
	public com.acme.w9b7.model.W9B7Entry deleteW9B7Entry(long w9b7EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _w9b7EntryLocalService.deleteW9B7Entry(w9b7EntryId);
	}

	/**
	 * Deletes the w9b7 entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect W9B7EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param w9b7Entry the w9b7 entry
	 * @return the w9b7 entry that was removed
	 */
	@Override
	public com.acme.w9b7.model.W9B7Entry deleteW9B7Entry(
		com.acme.w9b7.model.W9B7Entry w9b7Entry) {

		return _w9b7EntryLocalService.deleteW9B7Entry(w9b7Entry);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _w9b7EntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _w9b7EntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _w9b7EntryLocalService.dynamicQuery();
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

		return _w9b7EntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.w9b7.model.impl.W9B7EntryModelImpl</code>.
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

		return _w9b7EntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.w9b7.model.impl.W9B7EntryModelImpl</code>.
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

		return _w9b7EntryLocalService.dynamicQuery(
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

		return _w9b7EntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _w9b7EntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.acme.w9b7.model.W9B7Entry fetchW9B7Entry(long w9b7EntryId) {
		return _w9b7EntryLocalService.fetchW9B7Entry(w9b7EntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _w9b7EntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _w9b7EntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _w9b7EntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _w9b7EntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the w9b7 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.w9b7.model.impl.W9B7EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of w9b7 entries
	 * @param end the upper bound of the range of w9b7 entries (not inclusive)
	 * @return the range of w9b7 entries
	 */
	@Override
	public java.util.List<com.acme.w9b7.model.W9B7Entry> getW9B7Entries(
		int start, int end) {

		return _w9b7EntryLocalService.getW9B7Entries(start, end);
	}

	/**
	 * Returns the number of w9b7 entries.
	 *
	 * @return the number of w9b7 entries
	 */
	@Override
	public int getW9B7EntriesCount() {
		return _w9b7EntryLocalService.getW9B7EntriesCount();
	}

	/**
	 * Returns the w9b7 entry with the primary key.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry
	 * @throws PortalException if a w9b7 entry with the primary key could not be found
	 */
	@Override
	public com.acme.w9b7.model.W9B7Entry getW9B7Entry(long w9b7EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _w9b7EntryLocalService.getW9B7Entry(w9b7EntryId);
	}

	/**
	 * Updates the w9b7 entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect W9B7EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param w9b7Entry the w9b7 entry
	 * @return the w9b7 entry that was updated
	 */
	@Override
	public com.acme.w9b7.model.W9B7Entry updateW9B7Entry(
		com.acme.w9b7.model.W9B7Entry w9b7Entry) {

		return _w9b7EntryLocalService.updateW9B7Entry(w9b7Entry);
	}

	@Override
	public W9B7EntryLocalService getWrappedService() {
		return _w9b7EntryLocalService;
	}

	@Override
	public void setWrappedService(W9B7EntryLocalService w9b7EntryLocalService) {
		_w9b7EntryLocalService = w9b7EntryLocalService;
	}

	private W9B7EntryLocalService _w9b7EntryLocalService;

}