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

package com.acme.h6d2.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link H6D2EntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see H6D2EntryLocalService
 * @generated
 */
public class H6D2EntryLocalServiceWrapper
	implements H6D2EntryLocalService, ServiceWrapper<H6D2EntryLocalService> {

	public H6D2EntryLocalServiceWrapper(
		H6D2EntryLocalService h6d2EntryLocalService) {

		_h6d2EntryLocalService = h6d2EntryLocalService;
	}

	/**
	 * Adds the h6d2 entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect H6D2EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param h6d2Entry the h6d2 entry
	 * @return the h6d2 entry that was added
	 */
	@Override
	public com.acme.h6d2.model.H6D2Entry addH6D2Entry(
		com.acme.h6d2.model.H6D2Entry h6d2Entry) {

		return _h6d2EntryLocalService.addH6D2Entry(h6d2Entry);
	}

	/**
	 * Creates a new h6d2 entry with the primary key. Does not add the h6d2 entry to the database.
	 *
	 * @param h6d2EntryId the primary key for the new h6d2 entry
	 * @return the new h6d2 entry
	 */
	@Override
	public com.acme.h6d2.model.H6D2Entry createH6D2Entry(long h6d2EntryId) {
		return _h6d2EntryLocalService.createH6D2Entry(h6d2EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _h6d2EntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the h6d2 entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect H6D2EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param h6d2Entry the h6d2 entry
	 * @return the h6d2 entry that was removed
	 */
	@Override
	public com.acme.h6d2.model.H6D2Entry deleteH6D2Entry(
		com.acme.h6d2.model.H6D2Entry h6d2Entry) {

		return _h6d2EntryLocalService.deleteH6D2Entry(h6d2Entry);
	}

	/**
	 * Deletes the h6d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect H6D2EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry that was removed
	 * @throws PortalException if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public com.acme.h6d2.model.H6D2Entry deleteH6D2Entry(long h6d2EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _h6d2EntryLocalService.deleteH6D2Entry(h6d2EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _h6d2EntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _h6d2EntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _h6d2EntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _h6d2EntryLocalService.dynamicQuery();
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

		return _h6d2EntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.h6d2.model.impl.H6D2EntryModelImpl</code>.
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

		return _h6d2EntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.h6d2.model.impl.H6D2EntryModelImpl</code>.
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

		return _h6d2EntryLocalService.dynamicQuery(
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

		return _h6d2EntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _h6d2EntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.acme.h6d2.model.H6D2Entry fetchH6D2Entry(long h6d2EntryId) {
		return _h6d2EntryLocalService.fetchH6D2Entry(h6d2EntryId);
	}

	/**
	 * Returns the h6d2 entry matching the UUID and group.
	 *
	 * @param uuid the h6d2 entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	@Override
	public com.acme.h6d2.model.H6D2Entry fetchH6D2EntryByUuidAndGroupId(
		String uuid, long groupId) {

		return _h6d2EntryLocalService.fetchH6D2EntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _h6d2EntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _h6d2EntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	/**
	 * Returns a range of all the h6d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.h6d2.model.impl.H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @return the range of h6d2 entries
	 */
	@Override
	public java.util.List<com.acme.h6d2.model.H6D2Entry> getH6D2Entries(
		int start, int end) {

		return _h6d2EntryLocalService.getH6D2Entries(start, end);
	}

	/**
	 * Returns all the h6d2 entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the h6d2 entries
	 * @param companyId the primary key of the company
	 * @return the matching h6d2 entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.acme.h6d2.model.H6D2Entry>
		getH6D2EntriesByUuidAndCompanyId(String uuid, long companyId) {

		return _h6d2EntryLocalService.getH6D2EntriesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of h6d2 entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the h6d2 entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching h6d2 entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.acme.h6d2.model.H6D2Entry>
		getH6D2EntriesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.acme.h6d2.model.H6D2Entry> orderByComparator) {

		return _h6d2EntryLocalService.getH6D2EntriesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of h6d2 entries.
	 *
	 * @return the number of h6d2 entries
	 */
	@Override
	public int getH6D2EntriesCount() {
		return _h6d2EntryLocalService.getH6D2EntriesCount();
	}

	/**
	 * Returns the h6d2 entry with the primary key.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry
	 * @throws PortalException if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public com.acme.h6d2.model.H6D2Entry getH6D2Entry(long h6d2EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _h6d2EntryLocalService.getH6D2Entry(h6d2EntryId);
	}

	/**
	 * Returns the h6d2 entry matching the UUID and group.
	 *
	 * @param uuid the h6d2 entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching h6d2 entry
	 * @throws PortalException if a matching h6d2 entry could not be found
	 */
	@Override
	public com.acme.h6d2.model.H6D2Entry getH6D2EntryByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _h6d2EntryLocalService.getH6D2EntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _h6d2EntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _h6d2EntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _h6d2EntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the h6d2 entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect H6D2EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param h6d2Entry the h6d2 entry
	 * @return the h6d2 entry that was updated
	 */
	@Override
	public com.acme.h6d2.model.H6D2Entry updateH6D2Entry(
		com.acme.h6d2.model.H6D2Entry h6d2Entry) {

		return _h6d2EntryLocalService.updateH6D2Entry(h6d2Entry);
	}

	@Override
	public H6D2EntryLocalService getWrappedService() {
		return _h6d2EntryLocalService;
	}

	@Override
	public void setWrappedService(H6D2EntryLocalService h6d2EntryLocalService) {
		_h6d2EntryLocalService = h6d2EntryLocalService;
	}

	private H6D2EntryLocalService _h6d2EntryLocalService;

}