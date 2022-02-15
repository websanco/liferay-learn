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

package com.acme.e4g5.service.persistence.impl;

import com.acme.e4g5.exception.NoSuchE4G5EntryException;
import com.acme.e4g5.model.E4G5Entry;
import com.acme.e4g5.model.E4G5EntryTable;
import com.acme.e4g5.model.impl.E4G5EntryImpl;
import com.acme.e4g5.model.impl.E4G5EntryModelImpl;
import com.acme.e4g5.service.persistence.E4G5EntryPersistence;
import com.acme.e4g5.service.persistence.E4G5EntryUtil;
import com.acme.e4g5.service.persistence.impl.constants.E4G5PersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the e4g5 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {E4G5EntryPersistence.class, BasePersistence.class})
public class E4G5EntryPersistenceImpl
	extends BasePersistenceImpl<E4G5Entry> implements E4G5EntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>E4G5EntryUtil</code> to access the e4g5 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		E4G5EntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public E4G5EntryPersistenceImpl() {
		setModelClass(E4G5Entry.class);

		setModelImplClass(E4G5EntryImpl.class);
		setModelPKClass(long.class);

		setTable(E4G5EntryTable.INSTANCE);
	}

	/**
	 * Caches the e4g5 entry in the entity cache if it is enabled.
	 *
	 * @param e4g5Entry the e4g5 entry
	 */
	@Override
	public void cacheResult(E4G5Entry e4g5Entry) {
		entityCache.putResult(
			E4G5EntryImpl.class, e4g5Entry.getPrimaryKey(), e4g5Entry);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the e4g5 entries in the entity cache if it is enabled.
	 *
	 * @param e4g5Entries the e4g5 entries
	 */
	@Override
	public void cacheResult(List<E4G5Entry> e4g5Entries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (e4g5Entries.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (E4G5Entry e4g5Entry : e4g5Entries) {
			if (entityCache.getResult(
					E4G5EntryImpl.class, e4g5Entry.getPrimaryKey()) == null) {

				cacheResult(e4g5Entry);
			}
		}
	}

	/**
	 * Clears the cache for all e4g5 entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(E4G5EntryImpl.class);

		finderCache.clearCache(E4G5EntryImpl.class);
	}

	/**
	 * Clears the cache for the e4g5 entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(E4G5Entry e4g5Entry) {
		entityCache.removeResult(E4G5EntryImpl.class, e4g5Entry);
	}

	@Override
	public void clearCache(List<E4G5Entry> e4g5Entries) {
		for (E4G5Entry e4g5Entry : e4g5Entries) {
			entityCache.removeResult(E4G5EntryImpl.class, e4g5Entry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(E4G5EntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(E4G5EntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new e4g5 entry with the primary key. Does not add the e4g5 entry to the database.
	 *
	 * @param e4g5EntryId the primary key for the new e4g5 entry
	 * @return the new e4g5 entry
	 */
	@Override
	public E4G5Entry create(long e4g5EntryId) {
		E4G5Entry e4g5Entry = new E4G5EntryImpl();

		e4g5Entry.setNew(true);
		e4g5Entry.setPrimaryKey(e4g5EntryId);

		return e4g5Entry;
	}

	/**
	 * Removes the e4g5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param e4g5EntryId the primary key of the e4g5 entry
	 * @return the e4g5 entry that was removed
	 * @throws NoSuchE4G5EntryException if a e4g5 entry with the primary key could not be found
	 */
	@Override
	public E4G5Entry remove(long e4g5EntryId) throws NoSuchE4G5EntryException {
		return remove((Serializable)e4g5EntryId);
	}

	/**
	 * Removes the e4g5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the e4g5 entry
	 * @return the e4g5 entry that was removed
	 * @throws NoSuchE4G5EntryException if a e4g5 entry with the primary key could not be found
	 */
	@Override
	public E4G5Entry remove(Serializable primaryKey)
		throws NoSuchE4G5EntryException {

		Session session = null;

		try {
			session = openSession();

			E4G5Entry e4g5Entry = (E4G5Entry)session.get(
				E4G5EntryImpl.class, primaryKey);

			if (e4g5Entry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchE4G5EntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(e4g5Entry);
		}
		catch (NoSuchE4G5EntryException noSuchEntityException) {
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
	protected E4G5Entry removeImpl(E4G5Entry e4g5Entry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(e4g5Entry)) {
				e4g5Entry = (E4G5Entry)session.get(
					E4G5EntryImpl.class, e4g5Entry.getPrimaryKeyObj());
			}

			if (e4g5Entry != null) {
				session.delete(e4g5Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (e4g5Entry != null) {
			clearCache(e4g5Entry);
		}

		return e4g5Entry;
	}

	@Override
	public E4G5Entry updateImpl(E4G5Entry e4g5Entry) {
		boolean isNew = e4g5Entry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(e4g5Entry);
			}
			else {
				e4g5Entry = (E4G5Entry)session.merge(e4g5Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(E4G5EntryImpl.class, e4g5Entry, false, true);

		if (isNew) {
			e4g5Entry.setNew(false);
		}

		e4g5Entry.resetOriginalValues();

		return e4g5Entry;
	}

	/**
	 * Returns the e4g5 entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the e4g5 entry
	 * @return the e4g5 entry
	 * @throws NoSuchE4G5EntryException if a e4g5 entry with the primary key could not be found
	 */
	@Override
	public E4G5Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchE4G5EntryException {

		E4G5Entry e4g5Entry = fetchByPrimaryKey(primaryKey);

		if (e4g5Entry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchE4G5EntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return e4g5Entry;
	}

	/**
	 * Returns the e4g5 entry with the primary key or throws a <code>NoSuchE4G5EntryException</code> if it could not be found.
	 *
	 * @param e4g5EntryId the primary key of the e4g5 entry
	 * @return the e4g5 entry
	 * @throws NoSuchE4G5EntryException if a e4g5 entry with the primary key could not be found
	 */
	@Override
	public E4G5Entry findByPrimaryKey(long e4g5EntryId)
		throws NoSuchE4G5EntryException {

		return findByPrimaryKey((Serializable)e4g5EntryId);
	}

	/**
	 * Returns the e4g5 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param e4g5EntryId the primary key of the e4g5 entry
	 * @return the e4g5 entry, or <code>null</code> if a e4g5 entry with the primary key could not be found
	 */
	@Override
	public E4G5Entry fetchByPrimaryKey(long e4g5EntryId) {
		return fetchByPrimaryKey((Serializable)e4g5EntryId);
	}

	/**
	 * Returns all the e4g5 entries.
	 *
	 * @return the e4g5 entries
	 */
	@Override
	public List<E4G5Entry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the e4g5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e4g5 entries
	 * @param end the upper bound of the range of e4g5 entries (not inclusive)
	 * @return the range of e4g5 entries
	 */
	@Override
	public List<E4G5Entry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the e4g5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e4g5 entries
	 * @param end the upper bound of the range of e4g5 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of e4g5 entries
	 */
	@Override
	public List<E4G5Entry> findAll(
		int start, int end, OrderByComparator<E4G5Entry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the e4g5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>E4G5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e4g5 entries
	 * @param end the upper bound of the range of e4g5 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of e4g5 entries
	 */
	@Override
	public List<E4G5Entry> findAll(
		int start, int end, OrderByComparator<E4G5Entry> orderByComparator,
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

		List<E4G5Entry> list = null;

		if (useFinderCache) {
			list = (List<E4G5Entry>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_E4G5ENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_E4G5ENTRY;

				sql = sql.concat(E4G5EntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<E4G5Entry>)QueryUtil.list(
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
	 * Removes all the e4g5 entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (E4G5Entry e4g5Entry : findAll()) {
			remove(e4g5Entry);
		}
	}

	/**
	 * Returns the number of e4g5 entries.
	 *
	 * @return the number of e4g5 entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_E4G5ENTRY);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "e4g5EntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_E4G5ENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return E4G5EntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the e4g5 entry persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_setE4G5EntryUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setE4G5EntryUtilPersistence(null);

		entityCache.removeCache(E4G5EntryImpl.class.getName());
	}

	private void _setE4G5EntryUtilPersistence(
		E4G5EntryPersistence e4g5EntryPersistence) {

		try {
			Field field = E4G5EntryUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, e4g5EntryPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = E4G5PersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = E4G5PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = E4G5PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_E4G5ENTRY =
		"SELECT e4g5Entry FROM E4G5Entry e4g5Entry";

	private static final String _SQL_COUNT_E4G5ENTRY =
		"SELECT COUNT(e4g5Entry) FROM E4G5Entry e4g5Entry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "e4g5Entry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No E4G5Entry exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		E4G5EntryPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private E4G5EntryModelArgumentsResolver _e4g5EntryModelArgumentsResolver;

}