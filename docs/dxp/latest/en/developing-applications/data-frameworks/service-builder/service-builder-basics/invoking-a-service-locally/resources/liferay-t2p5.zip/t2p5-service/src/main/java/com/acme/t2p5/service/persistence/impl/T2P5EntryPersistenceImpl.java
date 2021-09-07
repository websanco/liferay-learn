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

package com.acme.t2p5.service.persistence.impl;

import com.acme.t2p5.exception.NoSuchT2P5EntryException;
import com.acme.t2p5.model.T2P5Entry;
import com.acme.t2p5.model.T2P5EntryTable;
import com.acme.t2p5.model.impl.T2P5EntryImpl;
import com.acme.t2p5.model.impl.T2P5EntryModelImpl;
import com.acme.t2p5.service.persistence.T2P5EntryPersistence;
import com.acme.t2p5.service.persistence.impl.constants.T2P5PersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
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
 * The persistence implementation for the t2p5 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {T2P5EntryPersistence.class, BasePersistence.class})
public class T2P5EntryPersistenceImpl
	extends BasePersistenceImpl<T2P5Entry> implements T2P5EntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>T2P5EntryUtil</code> to access the t2p5 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		T2P5EntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public T2P5EntryPersistenceImpl() {
		setModelClass(T2P5Entry.class);

		setModelImplClass(T2P5EntryImpl.class);
		setModelPKClass(long.class);

		setTable(T2P5EntryTable.INSTANCE);
	}

	/**
	 * Caches the t2p5 entry in the entity cache if it is enabled.
	 *
	 * @param t2p5Entry the t2p5 entry
	 */
	@Override
	public void cacheResult(T2P5Entry t2p5Entry) {
		entityCache.putResult(
			T2P5EntryImpl.class, t2p5Entry.getPrimaryKey(), t2p5Entry);
	}

	/**
	 * Caches the t2p5 entries in the entity cache if it is enabled.
	 *
	 * @param t2p5Entries the t2p5 entries
	 */
	@Override
	public void cacheResult(List<T2P5Entry> t2p5Entries) {
		for (T2P5Entry t2p5Entry : t2p5Entries) {
			if (entityCache.getResult(
					T2P5EntryImpl.class, t2p5Entry.getPrimaryKey()) == null) {

				cacheResult(t2p5Entry);
			}
		}
	}

	/**
	 * Clears the cache for all t2p5 entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(T2P5EntryImpl.class);

		finderCache.clearCache(T2P5EntryImpl.class);
	}

	/**
	 * Clears the cache for the t2p5 entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(T2P5Entry t2p5Entry) {
		entityCache.removeResult(T2P5EntryImpl.class, t2p5Entry);
	}

	@Override
	public void clearCache(List<T2P5Entry> t2p5Entries) {
		for (T2P5Entry t2p5Entry : t2p5Entries) {
			entityCache.removeResult(T2P5EntryImpl.class, t2p5Entry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(T2P5EntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(T2P5EntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new t2p5 entry with the primary key. Does not add the t2p5 entry to the database.
	 *
	 * @param t2p5EntryId the primary key for the new t2p5 entry
	 * @return the new t2p5 entry
	 */
	@Override
	public T2P5Entry create(long t2p5EntryId) {
		T2P5Entry t2p5Entry = new T2P5EntryImpl();

		t2p5Entry.setNew(true);
		t2p5Entry.setPrimaryKey(t2p5EntryId);

		return t2p5Entry;
	}

	/**
	 * Removes the t2p5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry that was removed
	 * @throws NoSuchT2P5EntryException if a t2p5 entry with the primary key could not be found
	 */
	@Override
	public T2P5Entry remove(long t2p5EntryId) throws NoSuchT2P5EntryException {
		return remove((Serializable)t2p5EntryId);
	}

	/**
	 * Removes the t2p5 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the t2p5 entry
	 * @return the t2p5 entry that was removed
	 * @throws NoSuchT2P5EntryException if a t2p5 entry with the primary key could not be found
	 */
	@Override
	public T2P5Entry remove(Serializable primaryKey)
		throws NoSuchT2P5EntryException {

		Session session = null;

		try {
			session = openSession();

			T2P5Entry t2p5Entry = (T2P5Entry)session.get(
				T2P5EntryImpl.class, primaryKey);

			if (t2p5Entry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchT2P5EntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(t2p5Entry);
		}
		catch (NoSuchT2P5EntryException noSuchEntityException) {
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
	protected T2P5Entry removeImpl(T2P5Entry t2p5Entry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(t2p5Entry)) {
				t2p5Entry = (T2P5Entry)session.get(
					T2P5EntryImpl.class, t2p5Entry.getPrimaryKeyObj());
			}

			if (t2p5Entry != null) {
				session.delete(t2p5Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (t2p5Entry != null) {
			clearCache(t2p5Entry);
		}

		return t2p5Entry;
	}

	@Override
	public T2P5Entry updateImpl(T2P5Entry t2p5Entry) {
		boolean isNew = t2p5Entry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(t2p5Entry);
			}
			else {
				t2p5Entry = (T2P5Entry)session.merge(t2p5Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(T2P5EntryImpl.class, t2p5Entry, false, true);

		if (isNew) {
			t2p5Entry.setNew(false);
		}

		t2p5Entry.resetOriginalValues();

		return t2p5Entry;
	}

	/**
	 * Returns the t2p5 entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the t2p5 entry
	 * @return the t2p5 entry
	 * @throws NoSuchT2P5EntryException if a t2p5 entry with the primary key could not be found
	 */
	@Override
	public T2P5Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchT2P5EntryException {

		T2P5Entry t2p5Entry = fetchByPrimaryKey(primaryKey);

		if (t2p5Entry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchT2P5EntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return t2p5Entry;
	}

	/**
	 * Returns the t2p5 entry with the primary key or throws a <code>NoSuchT2P5EntryException</code> if it could not be found.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry
	 * @throws NoSuchT2P5EntryException if a t2p5 entry with the primary key could not be found
	 */
	@Override
	public T2P5Entry findByPrimaryKey(long t2p5EntryId)
		throws NoSuchT2P5EntryException {

		return findByPrimaryKey((Serializable)t2p5EntryId);
	}

	/**
	 * Returns the t2p5 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param t2p5EntryId the primary key of the t2p5 entry
	 * @return the t2p5 entry, or <code>null</code> if a t2p5 entry with the primary key could not be found
	 */
	@Override
	public T2P5Entry fetchByPrimaryKey(long t2p5EntryId) {
		return fetchByPrimaryKey((Serializable)t2p5EntryId);
	}

	/**
	 * Returns all the t2p5 entries.
	 *
	 * @return the t2p5 entries
	 */
	@Override
	public List<T2P5Entry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the t2p5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>T2P5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of t2p5 entries
	 * @param end the upper bound of the range of t2p5 entries (not inclusive)
	 * @return the range of t2p5 entries
	 */
	@Override
	public List<T2P5Entry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the t2p5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>T2P5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of t2p5 entries
	 * @param end the upper bound of the range of t2p5 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of t2p5 entries
	 */
	@Override
	public List<T2P5Entry> findAll(
		int start, int end, OrderByComparator<T2P5Entry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the t2p5 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>T2P5EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of t2p5 entries
	 * @param end the upper bound of the range of t2p5 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of t2p5 entries
	 */
	@Override
	public List<T2P5Entry> findAll(
		int start, int end, OrderByComparator<T2P5Entry> orderByComparator,
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

		List<T2P5Entry> list = null;

		if (useFinderCache) {
			list = (List<T2P5Entry>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_T2P5ENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_T2P5ENTRY;

				sql = sql.concat(T2P5EntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<T2P5Entry>)QueryUtil.list(
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
	 * Removes all the t2p5 entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (T2P5Entry t2p5Entry : findAll()) {
			remove(t2p5Entry);
		}
	}

	/**
	 * Returns the number of t2p5 entries.
	 *
	 * @return the number of t2p5 entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_T2P5ENTRY);

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
		return "t2p5EntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_T2P5ENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return T2P5EntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the t2p5 entry persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new T2P5EntryModelArgumentsResolver(),
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
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(T2P5EntryImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();
	}

	@Override
	@Reference(
		target = T2P5PersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = T2P5PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = T2P5PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_T2P5ENTRY =
		"SELECT t2p5Entry FROM T2P5Entry t2p5Entry";

	private static final String _SQL_COUNT_T2P5ENTRY =
		"SELECT COUNT(t2p5Entry) FROM T2P5Entry t2p5Entry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "t2p5Entry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No T2P5Entry exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		T2P5EntryPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class T2P5EntryModelArgumentsResolver
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

			T2P5EntryModelImpl t2p5EntryModelImpl =
				(T2P5EntryModelImpl)baseModel;

			long columnBitmask = t2p5EntryModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(t2p5EntryModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						t2p5EntryModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(t2p5EntryModelImpl, columnNames, original);
			}

			return null;
		}

		@Override
		public String getClassName() {
			return T2P5EntryImpl.class.getName();
		}

		@Override
		public String getTableName() {
			return T2P5EntryTable.INSTANCE.getTableName();
		}

		private static Object[] _getValue(
			T2P5EntryModelImpl t2p5EntryModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = t2p5EntryModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = t2p5EntryModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

	}

}