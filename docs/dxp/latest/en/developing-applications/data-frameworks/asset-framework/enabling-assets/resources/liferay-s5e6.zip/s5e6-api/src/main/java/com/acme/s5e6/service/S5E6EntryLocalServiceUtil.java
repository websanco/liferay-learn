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

import com.acme.s5e6.model.S5E6Entry;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for S5E6Entry. This utility wraps
 * <code>com.acme.s5e6.service.impl.S5E6EntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see S5E6EntryLocalService
 * @generated
 */
public class S5E6EntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.acme.s5e6.service.impl.S5E6EntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static S5E6Entry addS5E6Entry(
			long userId, long groupId, String description, String item,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addS5E6Entry(
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
	public static S5E6Entry addS5E6Entry(S5E6Entry s5e6Entry) {
		return getService().addS5E6Entry(s5e6Entry);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new s5e6 entry with the primary key. Does not add the s5e6 entry to the database.
	 *
	 * @param S5E6EntryId the primary key for the new s5e6 entry
	 * @return the new s5e6 entry
	 */
	public static S5E6Entry createS5E6Entry(long S5E6EntryId) {
		return getService().createS5E6Entry(S5E6EntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	public static S5E6Entry deleteS5E6Entry(long S5E6EntryId)
		throws PortalException {

		return getService().deleteS5E6Entry(S5E6EntryId);
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
	public static S5E6Entry deleteS5E6Entry(S5E6Entry s5e6Entry) {
		return getService().deleteS5E6Entry(s5e6Entry);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static S5E6Entry fetchS5E6Entry(long S5E6EntryId) {
		return getService().fetchS5E6Entry(S5E6EntryId);
	}

	/**
	 * Returns the s5e6 entry matching the UUID and group.
	 *
	 * @param uuid the s5e6 entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching s5e6 entry, or <code>null</code> if a matching s5e6 entry could not be found
	 */
	public static S5E6Entry fetchS5E6EntryByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchS5E6EntryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static List<S5E6Entry> getS5E6Entries(int start, int end) {
		return getService().getS5E6Entries(start, end);
	}

	/**
	 * Returns all the s5e6 entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the s5e6 entries
	 * @param companyId the primary key of the company
	 * @return the matching s5e6 entries, or an empty list if no matches were found
	 */
	public static List<S5E6Entry> getS5E6EntriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getS5E6EntriesByUuidAndCompanyId(uuid, companyId);
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
	public static List<S5E6Entry> getS5E6EntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<S5E6Entry> orderByComparator) {

		return getService().getS5E6EntriesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of s5e6 entries.
	 *
	 * @return the number of s5e6 entries
	 */
	public static int getS5E6EntriesCount() {
		return getService().getS5E6EntriesCount();
	}

	/**
	 * Returns the s5e6 entry with the primary key.
	 *
	 * @param S5E6EntryId the primary key of the s5e6 entry
	 * @return the s5e6 entry
	 * @throws PortalException if a s5e6 entry with the primary key could not be found
	 */
	public static S5E6Entry getS5E6Entry(long S5E6EntryId)
		throws PortalException {

		return getService().getS5E6Entry(S5E6EntryId);
	}

	/**
	 * Returns the s5e6 entry matching the UUID and group.
	 *
	 * @param uuid the s5e6 entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching s5e6 entry
	 * @throws PortalException if a matching s5e6 entry could not be found
	 */
	public static S5E6Entry getS5E6EntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getS5E6EntryByUuidAndGroupId(uuid, groupId);
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
	public static S5E6Entry updateS5E6Entry(S5E6Entry s5e6Entry) {
		return getService().updateS5E6Entry(s5e6Entry);
	}

	public static S5E6EntryLocalService getService() {
		return _service;
	}

	private static volatile S5E6EntryLocalService _service;

}