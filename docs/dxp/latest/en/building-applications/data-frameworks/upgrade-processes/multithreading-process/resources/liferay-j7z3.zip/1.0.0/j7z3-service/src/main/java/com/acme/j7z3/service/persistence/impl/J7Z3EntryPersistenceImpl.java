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

package com.acme.j7z3.service.persistence.impl;

import com.acme.j7z3.exception.NoSuchJ7Z3EntryException;
import com.acme.j7z3.model.J7Z3Entry;
import com.acme.j7z3.model.J7Z3EntryTable;
import com.acme.j7z3.model.impl.J7Z3EntryImpl;
import com.acme.j7z3.model.impl.J7Z3EntryModelImpl;
import com.acme.j7z3.service.persistence.J7Z3EntryPersistence;
import com.acme.j7z3.service.persistence.J7Z3EntryUtil;
import com.acme.j7z3.service.persistence.impl.constants.J7Z3PersistenceConstants;

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
 * The persistence implementation for the j7z3 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {J7Z3EntryPersistence.class, BasePersistence.class})
public class J7Z3EntryPersistenceImpl
	extends BasePersistenceImpl<J7Z3Entry> implements J7Z3EntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>J7Z3EntryUtil</code> to access the j7z3 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		J7Z3EntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public J7Z3EntryPersistenceImpl() {
		setModelClass(J7Z3Entry.class);

		setModelImplClass(J7Z3EntryImpl.class);
		setModelPKClass(long.class);

		setTable(J7Z3EntryTable.INSTANCE);
	}

	/**
	 * Caches the j7z3 entry in the entity cache if it is enabled.
	 *
	 * @param j7z3Entry the j7z3 entry
	 */
	@Override
	public void cacheResult(J7Z3Entry j7z3Entry) {
		entityCache.putResult(
			J7Z3EntryImpl.class, j7z3Entry.getPrimaryKey(), j7z3Entry);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the j7z3 entries in the entity cache if it is enabled.
	 *
	 * @param j7z3Entries the j7z3 entries
	 */
	@Override
	public void cacheResult(List<J7Z3Entry> j7z3Entries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (j7z3Entries.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (J7Z3Entry j7z3Entry : j7z3Entries) {
			if (entityCache.getResult(
					J7Z3EntryImpl.class, j7z3Entry.getPrimaryKey()) == null) {

				cacheResult(j7z3Entry);
			}
		}
	}

	/**
	 * Clears the cache for all j7z3 entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(J7Z3EntryImpl.class);

		finderCache.clearCache(J7Z3EntryImpl.class);
	}

	/**
	 * Clears the cache for the j7z3 entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(J7Z3Entry j7z3Entry) {
		entityCache.removeResult(J7Z3EntryImpl.class, j7z3Entry);
	}

	@Override
	public void clearCache(List<J7Z3Entry> j7z3Entries) {
		for (J7Z3Entry j7z3Entry : j7z3Entries) {
			entityCache.removeResult(J7Z3EntryImpl.class, j7z3Entry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(J7Z3EntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(J7Z3EntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new j7z3 entry with the primary key. Does not add the j7z3 entry to the database.
	 *
	 * @param j7z3EntryId the primary key for the new j7z3 entry
	 * @return the new j7z3 entry
	 */
	@Override
	public J7Z3Entry create(long j7z3EntryId) {
		J7Z3Entry j7z3Entry = new J7Z3EntryImpl();

		j7z3Entry.setNew(true);
		j7z3Entry.setPrimaryKey(j7z3EntryId);

		return j7z3Entry;
	}

	/**
	 * Removes the j7z3 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry that was removed
	 * @throws NoSuchJ7Z3EntryException if a j7z3 entry with the primary key could not be found
	 */
	@Override
	public J7Z3Entry remove(long j7z3EntryId) throws NoSuchJ7Z3EntryException {
		return remove((Serializable)j7z3EntryId);
	}

	/**
	 * Removes the j7z3 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j7z3 entry
	 * @return the j7z3 entry that was removed
	 * @throws NoSuchJ7Z3EntryException if a j7z3 entry with the primary key could not be found
	 */
	@Override
	public J7Z3Entry remove(Serializable primaryKey)
		throws NoSuchJ7Z3EntryException {

		Session session = null;

		try {
			session = openSession();

			J7Z3Entry j7z3Entry = (J7Z3Entry)session.get(
				J7Z3EntryImpl.class, primaryKey);

			if (j7z3Entry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJ7Z3EntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(j7z3Entry);
		}
		catch (NoSuchJ7Z3EntryException noSuchEntityException) {
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
	protected J7Z3Entry removeImpl(J7Z3Entry j7z3Entry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(j7z3Entry)) {
				j7z3Entry = (J7Z3Entry)session.get(
					J7Z3EntryImpl.class, j7z3Entry.getPrimaryKeyObj());
			}

			if (j7z3Entry != null) {
				session.delete(j7z3Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (j7z3Entry != null) {
			clearCache(j7z3Entry);
		}

		return j7z3Entry;
	}

	@Override
	public J7Z3Entry updateImpl(J7Z3Entry j7z3Entry) {
		boolean isNew = j7z3Entry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(j7z3Entry);
			}
			else {
				j7z3Entry = (J7Z3Entry)session.merge(j7z3Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(J7Z3EntryImpl.class, j7z3Entry, false, true);

		if (isNew) {
			j7z3Entry.setNew(false);
		}

		j7z3Entry.resetOriginalValues();

		return j7z3Entry;
	}

	/**
	 * Returns the j7z3 entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j7z3 entry
	 * @return the j7z3 entry
	 * @throws NoSuchJ7Z3EntryException if a j7z3 entry with the primary key could not be found
	 */
	@Override
	public J7Z3Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJ7Z3EntryException {

		J7Z3Entry j7z3Entry = fetchByPrimaryKey(primaryKey);

		if (j7z3Entry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJ7Z3EntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return j7z3Entry;
	}

	/**
	 * Returns the j7z3 entry with the primary key or throws a <code>NoSuchJ7Z3EntryException</code> if it could not be found.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry
	 * @throws NoSuchJ7Z3EntryException if a j7z3 entry with the primary key could not be found
	 */
	@Override
	public J7Z3Entry findByPrimaryKey(long j7z3EntryId)
		throws NoSuchJ7Z3EntryException {

		return findByPrimaryKey((Serializable)j7z3EntryId);
	}

	/**
	 * Returns the j7z3 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param j7z3EntryId the primary key of the j7z3 entry
	 * @return the j7z3 entry, or <code>null</code> if a j7z3 entry with the primary key could not be found
	 */
	@Override
	public J7Z3Entry fetchByPrimaryKey(long j7z3EntryId) {
		return fetchByPrimaryKey((Serializable)j7z3EntryId);
	}

	/**
	 * Returns all the j7z3 entries.
	 *
	 * @return the j7z3 entries
	 */
	@Override
	public List<J7Z3Entry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j7z3 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>J7Z3EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of j7z3 entries
	 * @param end the upper bound of the range of j7z3 entries (not inclusive)
	 * @return the range of j7z3 entries
	 */
	@Override
	public List<J7Z3Entry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j7z3 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>J7Z3EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of j7z3 entries
	 * @param end the upper bound of the range of j7z3 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j7z3 entries
	 */
	@Override
	public List<J7Z3Entry> findAll(
		int start, int end, OrderByComparator<J7Z3Entry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the j7z3 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>J7Z3EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of j7z3 entries
	 * @param end the upper bound of the range of j7z3 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of j7z3 entries
	 */
	@Override
	public List<J7Z3Entry> findAll(
		int start, int end, OrderByComparator<J7Z3Entry> orderByComparator,
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

		List<J7Z3Entry> list = null;

		if (useFinderCache) {
			list = (List<J7Z3Entry>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_J7Z3ENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_J7Z3ENTRY;

				sql = sql.concat(J7Z3EntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<J7Z3Entry>)QueryUtil.list(
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
	 * Removes all the j7z3 entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (J7Z3Entry j7z3Entry : findAll()) {
			remove(j7z3Entry);
		}
	}

	/**
	 * Returns the number of j7z3 entries.
	 *
	 * @return the number of j7z3 entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_J7Z3ENTRY);

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
		return "j7z3EntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_J7Z3ENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return J7Z3EntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the j7z3 entry persistence.
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

		_setJ7Z3EntryUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setJ7Z3EntryUtilPersistence(null);

		entityCache.removeCache(J7Z3EntryImpl.class.getName());
	}

	private void _setJ7Z3EntryUtilPersistence(
		J7Z3EntryPersistence j7z3EntryPersistence) {

		try {
			Field field = J7Z3EntryUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, j7z3EntryPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = J7Z3PersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = J7Z3PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = J7Z3PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_J7Z3ENTRY =
		"SELECT j7z3Entry FROM J7Z3Entry j7z3Entry";

	private static final String _SQL_COUNT_J7Z3ENTRY =
		"SELECT COUNT(j7z3Entry) FROM J7Z3Entry j7z3Entry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "j7z3Entry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No J7Z3Entry exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		J7Z3EntryPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private J7Z3EntryModelArgumentsResolver _j7z3EntryModelArgumentsResolver;

}