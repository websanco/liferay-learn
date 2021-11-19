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

package com.acme.s5e6.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link S5E6EntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see S5E6EntryLocalService
 * @generated
 */
public class S5E6EntryLocalServiceWrapper
	implements S5E6EntryLocalService, ServiceWrapper<S5E6EntryLocalService> {

	public S5E6EntryLocalServiceWrapper(
		S5E6EntryLocalService s5e6EntryLocalService) {

		_s5e6EntryLocalService = s5e6EntryLocalService;
	}

	@Override
	public com.acme.s5e6.model.S5E6Entry addS5E6Entry(
			long userId, long groupId, String description, String item,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _s5e6EntryLocalService.addS5E6Entry(
			userId, groupId, description, item, serviceContext);
	}

	/**
	 * Adds the s5e6 entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect S5E6EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param s5e6Entry the s5e6 entry
	 * @return the s5e6 entry that was added
	 */
	@Override
	public com.acme.s5e6.model.S5E6Entry addS5E6Entry(
		com.acme.s5e6.model.S5E6Entry s5e6Entry) {

		return _s5e6EntryLocalService.addS5E6Entry(s5e6Entry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _s5e6EntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new s5e6 entry with the primary key. Does not add the s5e6 entry to the database.
	 *
	 * @param S5E6EntryId the primary key for the new s5e6 entry
	 * @return the new s5e6 entry
	 */
	@Override
	public com.acme.s5e6.model.S5E6Entry createS5E6Entry(long S5E6EntryId) {
		return _s5e6EntryLocalService.createS5E6Entry(S5E6EntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _s5e6EntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the s5e6 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect S5E6EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param S5E6EntryId the primary key of the s5e6 entry
	 * @return the s5e6 entry that was removed
	 * @throws PortalException if a s5e6 entry with the primary key could not be found
	 */
	@Override
	public com.acme.s5e6.model.S5E6Entry deleteS5E6Entry(long S5E6EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _s5e6EntryLocalService.deleteS5E6Entry(S5E6EntryId);
	}

	/**
	 * Deletes the s5e6 entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect S5E6EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param s5e6Entry the s5e6 entry
	 * @return the s5e6 entry that was removed
	 */
	@Override
	public com.acme.s5e6.model.S5E6Entry deleteS5E6Entry(
		com.acme.s5e6.model.S5E6Entry s5e6Entry) {

		return _s5e6EntryLocalService.deleteS5E6Entry(s5e6Entry);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _s5e6EntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _s5e6EntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _s5e6EntryLocalService.dynamicQuery();
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

		return _s5e6EntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.s5e6.model.impl.S5E6EntryModelImpl</code>.
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

		return _s5e6EntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.s5e6.model.impl.S5E6EntryModelImpl</code>.
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

		return _s5e6EntryLocalService.dynamicQuery(
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

		return _s5e6EntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _s5e6EntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.acme.s5e6.model.S5E6Entry fetchS5E6Entry(long S5E6EntryId) {
		return _s5e6EntryLocalService.fetchS5E6Entry(S5E6EntryId);
	}

	/**
	 * Returns the s5e6 entry matching the UUID and group.
	 *
	 * @param uuid the s5e6 entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching s5e6 entry, or <code>null</code> if a matching s5e6 entry could not be found
	 */
	@Override
	public com.acme.s5e6.model.S5E6Entry fetchS5E6EntryByUuidAndGroupId(
		String uuid, long groupId) {

		return _s5e6EntryLocalService.fetchS5E6EntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _s5e6EntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _s5e6EntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _s5e6EntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _s5e6EntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _s5e6EntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the s5e6 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.s5e6.model.impl.S5E6EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @return the range of s5e6 entries
	 */
	@Override
	public java.util.List<com.acme.s5e6.model.S5E6Entry> getS5E6Entries(
		int start, int end) {

		return _s5e6EntryLocalService.getS5E6Entries(start, end);
	}

	/**
	 * Returns all the s5e6 entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the s5e6 entries
	 * @param companyId the primary key of the company
	 * @return the matching s5e6 entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.acme.s5e6.model.S5E6Entry>
		getS5E6EntriesByUuidAndCompanyId(String uuid, long companyId) {

		return _s5e6EntryLocalService.getS5E6EntriesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of s5e6 entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the s5e6 entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of s5e6 entries
	 * @param end the upper bound of the range of s5e6 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching s5e6 entries, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.acme.s5e6.model.S5E6Entry>
		getS5E6EntriesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.acme.s5e6.model.S5E6Entry> orderByComparator) {

		return _s5e6EntryLocalService.getS5E6EntriesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of s5e6 entries.
	 *
	 * @return the number of s5e6 entries
	 */
	@Override
	public int getS5E6EntriesCount() {
		return _s5e6EntryLocalService.getS5E6EntriesCount();
	}

	/**
	 * Returns the s5e6 entry with the primary key.
	 *
	 * @param S5E6EntryId the primary key of the s5e6 entry
	 * @return the s5e6 entry
	 * @throws PortalException if a s5e6 entry with the primary key could not be found
	 */
	@Override
	public com.acme.s5e6.model.S5E6Entry getS5E6Entry(long S5E6EntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _s5e6EntryLocalService.getS5E6Entry(S5E6EntryId);
	}

	/**
	 * Returns the s5e6 entry matching the UUID and group.
	 *
	 * @param uuid the s5e6 entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching s5e6 entry
	 * @throws PortalException if a matching s5e6 entry could not be found
	 */
	@Override
	public com.acme.s5e6.model.S5E6Entry getS5E6EntryByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _s5e6EntryLocalService.getS5E6EntryByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Updates the s5e6 entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect S5E6EntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param s5e6Entry the s5e6 entry
	 * @return the s5e6 entry that was updated
	 */
	@Override
	public com.acme.s5e6.model.S5E6Entry updateS5E6Entry(
		com.acme.s5e6.model.S5E6Entry s5e6Entry) {

		return _s5e6EntryLocalService.updateS5E6Entry(s5e6Entry);
	}

	@Override
	public S5E6EntryLocalService getWrappedService() {
		return _s5e6EntryLocalService;
	}

	@Override
	public void setWrappedService(S5E6EntryLocalService s5e6EntryLocalService) {
		_s5e6EntryLocalService = s5e6EntryLocalService;
	}

	private S5E6EntryLocalService _s5e6EntryLocalService;

}