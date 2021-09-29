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

package com.acme.h6d2.service.persistence.impl;

import com.acme.h6d2.exception.NoSuchEntryException;
import com.acme.h6d2.model.H6D2Entry;
import com.acme.h6d2.model.H6D2EntryTable;
import com.acme.h6d2.model.impl.H6D2EntryImpl;
import com.acme.h6d2.model.impl.H6D2EntryModelImpl;
import com.acme.h6d2.service.persistence.H6D2EntryPersistence;
import com.acme.h6d2.service.persistence.impl.constants.H6D2PersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the h6d2 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {H6D2EntryPersistence.class, BasePersistence.class})
public class H6D2EntryPersistenceImpl
	extends BasePersistenceImpl<H6D2Entry> implements H6D2EntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>H6D2EntryUtil</code> to access the h6d2 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		H6D2EntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the h6d2 entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h6d2 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @return the range of matching h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<H6D2Entry> list = null;

		if (useFinderCache) {
			list = (List<H6D2Entry>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (H6D2Entry h6d2Entry : list) {
					if (!uuid.equals(h6d2Entry.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_H6D2ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(H6D2EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<H6D2Entry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry findByUuid_First(
			String uuid, OrderByComparator<H6D2Entry> orderByComparator)
		throws NoSuchEntryException {

		H6D2Entry h6d2Entry = fetchByUuid_First(uuid, orderByComparator);

		if (h6d2Entry != null) {
			return h6d2Entry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchEntryException(sb.toString());
	}

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry fetchByUuid_First(
		String uuid, OrderByComparator<H6D2Entry> orderByComparator) {

		List<H6D2Entry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry findByUuid_Last(
			String uuid, OrderByComparator<H6D2Entry> orderByComparator)
		throws NoSuchEntryException {

		H6D2Entry h6d2Entry = fetchByUuid_Last(uuid, orderByComparator);

		if (h6d2Entry != null) {
			return h6d2Entry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchEntryException(sb.toString());
	}

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry fetchByUuid_Last(
		String uuid, OrderByComparator<H6D2Entry> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<H6D2Entry> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the h6d2 entries before and after the current h6d2 entry in the ordered set where uuid = &#63;.
	 *
	 * @param h6d2EntryId the primary key of the current h6d2 entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public H6D2Entry[] findByUuid_PrevAndNext(
			long h6d2EntryId, String uuid,
			OrderByComparator<H6D2Entry> orderByComparator)
		throws NoSuchEntryException {

		uuid = Objects.toString(uuid, "");

		H6D2Entry h6d2Entry = findByPrimaryKey(h6d2EntryId);

		Session session = null;

		try {
			session = openSession();

			H6D2Entry[] array = new H6D2EntryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, h6d2Entry, uuid, orderByComparator, true);

			array[1] = h6d2Entry;

			array[2] = getByUuid_PrevAndNext(
				session, h6d2Entry, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected H6D2Entry getByUuid_PrevAndNext(
		Session session, H6D2Entry h6d2Entry, String uuid,
		OrderByComparator<H6D2Entry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_H6D2ENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(H6D2EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(h6d2Entry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<H6D2Entry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the h6d2 entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (H6D2Entry h6d2Entry :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(h6d2Entry);
		}
	}

	/**
	 * Returns the number of h6d2 entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching h6d2 entries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_H6D2ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"h6d2Entry.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(h6d2Entry.uuid IS NULL OR h6d2Entry.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry findByUUID_G(String uuid, long groupId)
		throws NoSuchEntryException {

		H6D2Entry h6d2Entry = fetchByUUID_G(uuid, groupId);

		if (h6d2Entry == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchEntryException(sb.toString());
		}

		return h6d2Entry;
	}

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the h6d2 entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs);
		}

		if (result instanceof H6D2Entry) {
			H6D2Entry h6d2Entry = (H6D2Entry)result;

			if (!Objects.equals(uuid, h6d2Entry.getUuid()) ||
				(groupId != h6d2Entry.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_H6D2ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<H6D2Entry> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					H6D2Entry h6d2Entry = list.get(0);

					result = h6d2Entry;

					cacheResult(h6d2Entry);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (H6D2Entry)result;
		}
	}

	/**
	 * Removes the h6d2 entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the h6d2 entry that was removed
	 */
	@Override
	public H6D2Entry removeByUUID_G(String uuid, long groupId)
		throws NoSuchEntryException {

		H6D2Entry h6d2Entry = findByUUID_G(uuid, groupId);

		return remove(h6d2Entry);
	}

	/**
	 * Returns the number of h6d2 entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching h6d2 entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_H6D2ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"h6d2Entry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(h6d2Entry.uuid IS NULL OR h6d2Entry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"h6d2Entry.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @return the range of matching h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<H6D2Entry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<H6D2Entry> list = null;

		if (useFinderCache) {
			list = (List<H6D2Entry>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (H6D2Entry h6d2Entry : list) {
					if (!uuid.equals(h6d2Entry.getUuid()) ||
						(companyId != h6d2Entry.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_H6D2ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(H6D2EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<H6D2Entry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<H6D2Entry> orderByComparator)
		throws NoSuchEntryException {

		H6D2Entry h6d2Entry = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (h6d2Entry != null) {
			return h6d2Entry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchEntryException(sb.toString());
	}

	/**
	 * Returns the first h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<H6D2Entry> orderByComparator) {

		List<H6D2Entry> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry
	 * @throws NoSuchEntryException if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<H6D2Entry> orderByComparator)
		throws NoSuchEntryException {

		H6D2Entry h6d2Entry = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (h6d2Entry != null) {
			return h6d2Entry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchEntryException(sb.toString());
	}

	/**
	 * Returns the last h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching h6d2 entry, or <code>null</code> if a matching h6d2 entry could not be found
	 */
	@Override
	public H6D2Entry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<H6D2Entry> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<H6D2Entry> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the h6d2 entries before and after the current h6d2 entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param h6d2EntryId the primary key of the current h6d2 entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public H6D2Entry[] findByUuid_C_PrevAndNext(
			long h6d2EntryId, String uuid, long companyId,
			OrderByComparator<H6D2Entry> orderByComparator)
		throws NoSuchEntryException {

		uuid = Objects.toString(uuid, "");

		H6D2Entry h6d2Entry = findByPrimaryKey(h6d2EntryId);

		Session session = null;

		try {
			session = openSession();

			H6D2Entry[] array = new H6D2EntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, h6d2Entry, uuid, companyId, orderByComparator, true);

			array[1] = h6d2Entry;

			array[2] = getByUuid_C_PrevAndNext(
				session, h6d2Entry, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected H6D2Entry getByUuid_C_PrevAndNext(
		Session session, H6D2Entry h6d2Entry, String uuid, long companyId,
		OrderByComparator<H6D2Entry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_H6D2ENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(H6D2EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(h6d2Entry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<H6D2Entry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the h6d2 entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (H6D2Entry h6d2Entry :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(h6d2Entry);
		}
	}

	/**
	 * Returns the number of h6d2 entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching h6d2 entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_H6D2ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"h6d2Entry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(h6d2Entry.uuid IS NULL OR h6d2Entry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"h6d2Entry.companyId = ?";

	public H6D2EntryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(H6D2Entry.class);

		setModelImplClass(H6D2EntryImpl.class);
		setModelPKClass(long.class);

		setTable(H6D2EntryTable.INSTANCE);
	}

	/**
	 * Caches the h6d2 entry in the entity cache if it is enabled.
	 *
	 * @param h6d2Entry the h6d2 entry
	 */
	@Override
	public void cacheResult(H6D2Entry h6d2Entry) {
		entityCache.putResult(
			H6D2EntryImpl.class, h6d2Entry.getPrimaryKey(), h6d2Entry);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {h6d2Entry.getUuid(), h6d2Entry.getGroupId()},
			h6d2Entry);
	}

	/**
	 * Caches the h6d2 entries in the entity cache if it is enabled.
	 *
	 * @param h6d2Entries the h6d2 entries
	 */
	@Override
	public void cacheResult(List<H6D2Entry> h6d2Entries) {
		for (H6D2Entry h6d2Entry : h6d2Entries) {
			if (entityCache.getResult(
					H6D2EntryImpl.class, h6d2Entry.getPrimaryKey()) == null) {

				cacheResult(h6d2Entry);
			}
		}
	}

	/**
	 * Clears the cache for all h6d2 entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(H6D2EntryImpl.class);

		finderCache.clearCache(H6D2EntryImpl.class);
	}

	/**
	 * Clears the cache for the h6d2 entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(H6D2Entry h6d2Entry) {
		entityCache.removeResult(H6D2EntryImpl.class, h6d2Entry);
	}

	@Override
	public void clearCache(List<H6D2Entry> h6d2Entries) {
		for (H6D2Entry h6d2Entry : h6d2Entries) {
			entityCache.removeResult(H6D2EntryImpl.class, h6d2Entry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(H6D2EntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(H6D2EntryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		H6D2EntryModelImpl h6d2EntryModelImpl) {

		Object[] args = new Object[] {
			h6d2EntryModelImpl.getUuid(), h6d2EntryModelImpl.getGroupId()
		};

		finderCache.putResult(_finderPathCountByUUID_G, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, h6d2EntryModelImpl);
	}

	/**
	 * Creates a new h6d2 entry with the primary key. Does not add the h6d2 entry to the database.
	 *
	 * @param h6d2EntryId the primary key for the new h6d2 entry
	 * @return the new h6d2 entry
	 */
	@Override
	public H6D2Entry create(long h6d2EntryId) {
		H6D2Entry h6d2Entry = new H6D2EntryImpl();

		h6d2Entry.setNew(true);
		h6d2Entry.setPrimaryKey(h6d2EntryId);

		String uuid = PortalUUIDUtil.generate();

		h6d2Entry.setUuid(uuid);

		h6d2Entry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return h6d2Entry;
	}

	/**
	 * Removes the h6d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry that was removed
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public H6D2Entry remove(long h6d2EntryId) throws NoSuchEntryException {
		return remove((Serializable)h6d2EntryId);
	}

	/**
	 * Removes the h6d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h6d2 entry
	 * @return the h6d2 entry that was removed
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public H6D2Entry remove(Serializable primaryKey)
		throws NoSuchEntryException {

		Session session = null;

		try {
			session = openSession();

			H6D2Entry h6d2Entry = (H6D2Entry)session.get(
				H6D2EntryImpl.class, primaryKey);

			if (h6d2Entry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(h6d2Entry);
		}
		catch (NoSuchEntryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected H6D2Entry removeImpl(H6D2Entry h6d2Entry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(h6d2Entry)) {
				h6d2Entry = (H6D2Entry)session.get(
					H6D2EntryImpl.class, h6d2Entry.getPrimaryKeyObj());
			}

			if (h6d2Entry != null) {
				session.delete(h6d2Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (h6d2Entry != null) {
			clearCache(h6d2Entry);
		}

		return h6d2Entry;
	}

	@Override
	public H6D2Entry updateImpl(H6D2Entry h6d2Entry) {
		boolean isNew = h6d2Entry.isNew();

		if (!(h6d2Entry instanceof H6D2EntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(h6d2Entry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(h6d2Entry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in h6d2Entry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom H6D2Entry implementation " +
					h6d2Entry.getClass());
		}

		H6D2EntryModelImpl h6d2EntryModelImpl = (H6D2EntryModelImpl)h6d2Entry;

		if (Validator.isNull(h6d2Entry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			h6d2Entry.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (h6d2Entry.getCreateDate() == null)) {
			if (serviceContext == null) {
				h6d2Entry.setCreateDate(date);
			}
			else {
				h6d2Entry.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!h6d2EntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				h6d2Entry.setModifiedDate(date);
			}
			else {
				h6d2Entry.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(h6d2Entry);
			}
			else {
				h6d2Entry = (H6D2Entry)session.merge(h6d2Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			H6D2EntryImpl.class, h6d2EntryModelImpl, false, true);

		cacheUniqueFindersCache(h6d2EntryModelImpl);

		if (isNew) {
			h6d2Entry.setNew(false);
		}

		h6d2Entry.resetOriginalValues();

		return h6d2Entry;
	}

	/**
	 * Returns the h6d2 entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h6d2 entry
	 * @return the h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public H6D2Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException {

		H6D2Entry h6d2Entry = fetchByPrimaryKey(primaryKey);

		if (h6d2Entry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return h6d2Entry;
	}

	/**
	 * Returns the h6d2 entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry
	 * @throws NoSuchEntryException if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public H6D2Entry findByPrimaryKey(long h6d2EntryId)
		throws NoSuchEntryException {

		return findByPrimaryKey((Serializable)h6d2EntryId);
	}

	/**
	 * Returns the h6d2 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param h6d2EntryId the primary key of the h6d2 entry
	 * @return the h6d2 entry, or <code>null</code> if a h6d2 entry with the primary key could not be found
	 */
	@Override
	public H6D2Entry fetchByPrimaryKey(long h6d2EntryId) {
		return fetchByPrimaryKey((Serializable)h6d2EntryId);
	}

	/**
	 * Returns all the h6d2 entries.
	 *
	 * @return the h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h6d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @return the range of h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findAll(
		int start, int end, OrderByComparator<H6D2Entry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the h6d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>H6D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of h6d2 entries
	 * @param end the upper bound of the range of h6d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of h6d2 entries
	 */
	@Override
	public List<H6D2Entry> findAll(
		int start, int end, OrderByComparator<H6D2Entry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<H6D2Entry> list = null;

		if (useFinderCache) {
			list = (List<H6D2Entry>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_H6D2ENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_H6D2ENTRY;

				sql = sql.concat(H6D2EntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<H6D2Entry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the h6d2 entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (H6D2Entry h6d2Entry : findAll()) {
			remove(h6d2Entry);
		}
	}

	/**
	 * Returns the number of h6d2 entries.
	 *
	 * @return the number of h6d2 entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_H6D2ENTRY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "h6d2EntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_H6D2ENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return H6D2EntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the h6d2 entry persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new H6D2EntryModelArgumentsResolver(),
			new HashMapDictionary<>());

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(H6D2EntryImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();
	}

	@Override
	@Reference(
		target = H6D2PersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = H6D2PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = H6D2PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_H6D2ENTRY =
		"SELECT h6d2Entry FROM H6D2Entry h6d2Entry";

	private static final String _SQL_SELECT_H6D2ENTRY_WHERE =
		"SELECT h6d2Entry FROM H6D2Entry h6d2Entry WHERE ";

	private static final String _SQL_COUNT_H6D2ENTRY =
		"SELECT COUNT(h6d2Entry) FROM H6D2Entry h6d2Entry";

	private static final String _SQL_COUNT_H6D2ENTRY_WHERE =
		"SELECT COUNT(h6d2Entry) FROM H6D2Entry h6d2Entry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "h6d2Entry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No H6D2Entry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No H6D2Entry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		H6D2EntryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class H6D2EntryModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			H6D2EntryModelImpl h6d2EntryModelImpl =
				(H6D2EntryModelImpl)baseModel;

			long columnBitmask = h6d2EntryModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(h6d2EntryModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						h6d2EntryModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(h6d2EntryModelImpl, columnNames, original);
			}

			return null;
		}

		@Override
		public String getClassName() {
			return H6D2EntryImpl.class.getName();
		}

		@Override
		public String getTableName() {
			return H6D2EntryTable.INSTANCE.getTableName();
		}

		private static Object[] _getValue(
			H6D2EntryModelImpl h6d2EntryModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = h6d2EntryModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = h6d2EntryModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

	}

}