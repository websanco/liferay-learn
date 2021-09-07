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

package com.acme.w9b7.service.persistence.impl;

import com.acme.w9b7.exception.NoSuchW9B7EntryException;
import com.acme.w9b7.model.W9B7Entry;
import com.acme.w9b7.model.W9B7EntryTable;
import com.acme.w9b7.model.impl.W9B7EntryImpl;
import com.acme.w9b7.model.impl.W9B7EntryModelImpl;
import com.acme.w9b7.service.persistence.W9B7EntryPersistence;
import com.acme.w9b7.service.persistence.impl.constants.W9B7PersistenceConstants;

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
 * The persistence implementation for the w9b7 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {W9B7EntryPersistence.class, BasePersistence.class})
public class W9B7EntryPersistenceImpl
	extends BasePersistenceImpl<W9B7Entry> implements W9B7EntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>W9B7EntryUtil</code> to access the w9b7 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		W9B7EntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public W9B7EntryPersistenceImpl() {
		setModelClass(W9B7Entry.class);

		setModelImplClass(W9B7EntryImpl.class);
		setModelPKClass(long.class);

		setTable(W9B7EntryTable.INSTANCE);
	}

	/**
	 * Caches the w9b7 entry in the entity cache if it is enabled.
	 *
	 * @param w9b7Entry the w9b7 entry
	 */
	@Override
	public void cacheResult(W9B7Entry w9b7Entry) {
		entityCache.putResult(
			W9B7EntryImpl.class, w9b7Entry.getPrimaryKey(), w9b7Entry);
	}

	/**
	 * Caches the w9b7 entries in the entity cache if it is enabled.
	 *
	 * @param w9b7Entries the w9b7 entries
	 */
	@Override
	public void cacheResult(List<W9B7Entry> w9b7Entries) {
		for (W9B7Entry w9b7Entry : w9b7Entries) {
			if (entityCache.getResult(
					W9B7EntryImpl.class, w9b7Entry.getPrimaryKey()) == null) {

				cacheResult(w9b7Entry);
			}
		}
	}

	/**
	 * Clears the cache for all w9b7 entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(W9B7EntryImpl.class);

		finderCache.clearCache(W9B7EntryImpl.class);
	}

	/**
	 * Clears the cache for the w9b7 entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(W9B7Entry w9b7Entry) {
		entityCache.removeResult(W9B7EntryImpl.class, w9b7Entry);
	}

	@Override
	public void clearCache(List<W9B7Entry> w9b7Entries) {
		for (W9B7Entry w9b7Entry : w9b7Entries) {
			entityCache.removeResult(W9B7EntryImpl.class, w9b7Entry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(W9B7EntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(W9B7EntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new w9b7 entry with the primary key. Does not add the w9b7 entry to the database.
	 *
	 * @param w9b7EntryId the primary key for the new w9b7 entry
	 * @return the new w9b7 entry
	 */
	@Override
	public W9B7Entry create(long w9b7EntryId) {
		W9B7Entry w9b7Entry = new W9B7EntryImpl();

		w9b7Entry.setNew(true);
		w9b7Entry.setPrimaryKey(w9b7EntryId);

		return w9b7Entry;
	}

	/**
	 * Removes the w9b7 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry that was removed
	 * @throws NoSuchW9B7EntryException if a w9b7 entry with the primary key could not be found
	 */
	@Override
	public W9B7Entry remove(long w9b7EntryId) throws NoSuchW9B7EntryException {
		return remove((Serializable)w9b7EntryId);
	}

	/**
	 * Removes the w9b7 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the w9b7 entry
	 * @return the w9b7 entry that was removed
	 * @throws NoSuchW9B7EntryException if a w9b7 entry with the primary key could not be found
	 */
	@Override
	public W9B7Entry remove(Serializable primaryKey)
		throws NoSuchW9B7EntryException {

		Session session = null;

		try {
			session = openSession();

			W9B7Entry w9b7Entry = (W9B7Entry)session.get(
				W9B7EntryImpl.class, primaryKey);

			if (w9b7Entry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchW9B7EntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(w9b7Entry);
		}
		catch (NoSuchW9B7EntryException noSuchEntityException) {
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
	protected W9B7Entry removeImpl(W9B7Entry w9b7Entry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(w9b7Entry)) {
				w9b7Entry = (W9B7Entry)session.get(
					W9B7EntryImpl.class, w9b7Entry.getPrimaryKeyObj());
			}

			if (w9b7Entry != null) {
				session.delete(w9b7Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (w9b7Entry != null) {
			clearCache(w9b7Entry);
		}

		return w9b7Entry;
	}

	@Override
	public W9B7Entry updateImpl(W9B7Entry w9b7Entry) {
		boolean isNew = w9b7Entry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(w9b7Entry);
			}
			else {
				w9b7Entry = (W9B7Entry)session.merge(w9b7Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(W9B7EntryImpl.class, w9b7Entry, false, true);

		if (isNew) {
			w9b7Entry.setNew(false);
		}

		w9b7Entry.resetOriginalValues();

		return w9b7Entry;
	}

	/**
	 * Returns the w9b7 entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the w9b7 entry
	 * @return the w9b7 entry
	 * @throws NoSuchW9B7EntryException if a w9b7 entry with the primary key could not be found
	 */
	@Override
	public W9B7Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchW9B7EntryException {

		W9B7Entry w9b7Entry = fetchByPrimaryKey(primaryKey);

		if (w9b7Entry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchW9B7EntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return w9b7Entry;
	}

	/**
	 * Returns the w9b7 entry with the primary key or throws a <code>NoSuchW9B7EntryException</code> if it could not be found.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry
	 * @throws NoSuchW9B7EntryException if a w9b7 entry with the primary key could not be found
	 */
	@Override
	public W9B7Entry findByPrimaryKey(long w9b7EntryId)
		throws NoSuchW9B7EntryException {

		return findByPrimaryKey((Serializable)w9b7EntryId);
	}

	/**
	 * Returns the w9b7 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param w9b7EntryId the primary key of the w9b7 entry
	 * @return the w9b7 entry, or <code>null</code> if a w9b7 entry with the primary key could not be found
	 */
	@Override
	public W9B7Entry fetchByPrimaryKey(long w9b7EntryId) {
		return fetchByPrimaryKey((Serializable)w9b7EntryId);
	}

	/**
	 * Returns all the w9b7 entries.
	 *
	 * @return the w9b7 entries
	 */
	@Override
	public List<W9B7Entry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w9b7 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>W9B7EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of w9b7 entries
	 * @param end the upper bound of the range of w9b7 entries (not inclusive)
	 * @return the range of w9b7 entries
	 */
	@Override
	public List<W9B7Entry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the w9b7 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>W9B7EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of w9b7 entries
	 * @param end the upper bound of the range of w9b7 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of w9b7 entries
	 */
	@Override
	public List<W9B7Entry> findAll(
		int start, int end, OrderByComparator<W9B7Entry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the w9b7 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>W9B7EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of w9b7 entries
	 * @param end the upper bound of the range of w9b7 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of w9b7 entries
	 */
	@Override
	public List<W9B7Entry> findAll(
		int start, int end, OrderByComparator<W9B7Entry> orderByComparator,
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

		List<W9B7Entry> list = null;

		if (useFinderCache) {
			list = (List<W9B7Entry>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_W9B7ENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_W9B7ENTRY;

				sql = sql.concat(W9B7EntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<W9B7Entry>)QueryUtil.list(
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
	 * Removes all the w9b7 entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (W9B7Entry w9b7Entry : findAll()) {
			remove(w9b7Entry);
		}
	}

	/**
	 * Returns the number of w9b7 entries.
	 *
	 * @return the number of w9b7 entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_W9B7ENTRY);

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
		return "w9b7EntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_W9B7ENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return W9B7EntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the w9b7 entry persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new W9B7EntryModelArgumentsResolver(),
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
		entityCache.removeCache(W9B7EntryImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();
	}

	@Override
	@Reference(
		target = W9B7PersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = W9B7PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = W9B7PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_W9B7ENTRY =
		"SELECT w9b7Entry FROM W9B7Entry w9b7Entry";

	private static final String _SQL_COUNT_W9B7ENTRY =
		"SELECT COUNT(w9b7Entry) FROM W9B7Entry w9b7Entry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "w9b7Entry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No W9B7Entry exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		W9B7EntryPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class W9B7EntryModelArgumentsResolver
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

			W9B7EntryModelImpl w9b7EntryModelImpl =
				(W9B7EntryModelImpl)baseModel;

			long columnBitmask = w9b7EntryModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(w9b7EntryModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						w9b7EntryModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(w9b7EntryModelImpl, columnNames, original);
			}

			return null;
		}

		@Override
		public String getClassName() {
			return W9B7EntryImpl.class.getName();
		}

		@Override
		public String getTableName() {
			return W9B7EntryTable.INSTANCE.getTableName();
		}

		private static Object[] _getValue(
			W9B7EntryModelImpl w9b7EntryModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = w9b7EntryModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = w9b7EntryModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

	}

}