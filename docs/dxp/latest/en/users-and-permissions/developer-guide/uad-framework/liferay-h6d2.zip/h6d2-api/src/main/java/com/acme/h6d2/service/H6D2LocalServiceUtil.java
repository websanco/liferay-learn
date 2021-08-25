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

import com.acme.h6d2.model.H6D2;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for H6D2. This utility wraps
 * <code>com.acme.h6d2.service.impl.H6D2LocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see H6D2LocalService
 * @generated
 */
public class H6D2LocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.acme.h6d2.service.impl.H6D2LocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the h6d2 to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect H6D2LocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param h6d2 the h6d2
	 * @return the h6d2 that was added
	 */
	public static H6D2 addH6D2(H6D2 h6d2) {
		return getService().addH6D2(h6d2);
	}

	/**
	 * Creates a new h6d2 with the primary key. Does not add the h6d2 to the database.
	 *
	 * @param h6d2Id the primary key for the new h6d2
	 * @return the new h6d2
	 */
	public static H6D2 createH6D2(long h6d2Id) {
		return getService().createH6D2(h6d2Id);
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
	 * Deletes the h6d2 from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect H6D2LocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param h6d2 the h6d2
	 * @return the h6d2 that was removed
	 */
	public static H6D2 deleteH6D2(H6D2 h6d2) {
		return getService().deleteH6D2(h6d2);
	}

	/**
	 * Deletes the h6d2 with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect H6D2LocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param h6d2Id the primary key of the h6d2
	 * @return the h6d2 that was removed
	 * @throws PortalException if a h6d2 with the primary key could not be found
	 */
	public static H6D2 deleteH6D2(long h6d2Id) throws PortalException {
		return getService().deleteH6D2(h6d2Id);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.h6d2.model.impl.H6D2ModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.h6d2.model.impl.H6D2ModelImpl</code>.
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

	public static H6D2 fetchH6D2(long h6d2Id) {
		return getService().fetchH6D2(h6d2Id);
	}

	/**
	 * Returns the h6d2 matching the UUID and group.
	 *
	 * @param uuid the h6d2's UUID
	 * @param groupId the primary key of the group
	 * @return the matching h6d2, or <code>null</code> if a matching h6d2 could not be found
	 */
	public static H6D2 fetchH6D2ByUuidAndGroupId(String uuid, long groupId) {
		return getService().fetchH6D2ByUuidAndGroupId(uuid, groupId);
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

	/**
	 * Returns the h6d2 with the primary key.
	 *
	 * @param h6d2Id the primary key of the h6d2
	 * @return the h6d2
	 * @throws PortalException if a h6d2 with the primary key could not be found
	 */
	public static H6D2 getH6D2(long h6d2Id) throws PortalException {
		return getService().getH6D2(h6d2Id);
	}

	/**
	 * Returns the h6d2 matching the UUID and group.
	 *
	 * @param uuid the h6d2's UUID
	 * @param groupId the primary key of the group
	 * @return the matching h6d2
	 * @throws PortalException if a matching h6d2 could not be found
	 */
	public static H6D2 getH6D2ByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return getService().getH6D2ByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the h6d2s.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.acme.h6d2.model.impl.H6D2ModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @return the range of h6d2s
	 */
	public static List<H6D2> getH6D2s(int start, int end) {
		return getService().getH6D2s(start, end);
	}

	/**
	 * Returns all the h6d2s matching the UUID and company.
	 *
	 * @param uuid the UUID of the h6d2s
	 * @param companyId the primary key of the company
	 * @return the matching h6d2s, or an empty list if no matches were found
	 */
	public static List<H6D2> getH6D2sByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getH6D2sByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of h6d2s matching the UUID and company.
	 *
	 * @param uuid the UUID of the h6d2s
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of h6d2s
	 * @param end the upper bound of the range of h6d2s (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching h6d2s, or an empty list if no matches were found
	 */
	public static List<H6D2> getH6D2sByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<H6D2> orderByComparator) {

		return getService().getH6D2sByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of h6d2s.
	 *
	 * @return the number of h6d2s
	 */
	public static int getH6D2sCount() {
		return getService().getH6D2sCount();
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
	 * Updates the h6d2 in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect H6D2LocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param h6d2 the h6d2
	 * @return the h6d2 that was updated
	 */
	public static H6D2 updateH6D2(H6D2 h6d2) {
		return getService().updateH6D2(h6d2);
	}

	public static H6D2LocalService getService() {
		return _service;
	}

	private static volatile H6D2LocalService _service;

}