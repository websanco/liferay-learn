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

package com.acme.p5d2.service.persistence.impl;

import com.acme.p5d2.exception.NoSuchP5D2EntryException;
import com.acme.p5d2.model.P5D2Entry;
import com.acme.p5d2.model.P5D2EntryTable;
import com.acme.p5d2.model.impl.P5D2EntryImpl;
import com.acme.p5d2.model.impl.P5D2EntryModelImpl;
import com.acme.p5d2.service.persistence.P5D2EntryPersistence;
import com.acme.p5d2.service.persistence.P5D2EntryUtil;
import com.acme.p5d2.service.persistence.impl.constants.P5D2PersistenceConstants;

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
 * The persistence implementation for the p5d2 entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {P5D2EntryPersistence.class, BasePersistence.class})
public class P5D2EntryPersistenceImpl
	extends BasePersistenceImpl<P5D2Entry> implements P5D2EntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>P5D2EntryUtil</code> to access the p5d2 entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		P5D2EntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public P5D2EntryPersistenceImpl() {
		setModelClass(P5D2Entry.class);

		setModelImplClass(P5D2EntryImpl.class);
		setModelPKClass(long.class);

		setTable(P5D2EntryTable.INSTANCE);
	}

	/**
	 * Caches the p5d2 entry in the entity cache if it is enabled.
	 *
	 * @param p5d2Entry the p5d2 entry
	 */
	@Override
	public void cacheResult(P5D2Entry p5d2Entry) {
		entityCache.putResult(
			P5D2EntryImpl.class, p5d2Entry.getPrimaryKey(), p5d2Entry);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the p5d2 entries in the entity cache if it is enabled.
	 *
	 * @param p5d2Entries the p5d2 entries
	 */
	@Override
	public void cacheResult(List<P5D2Entry> p5d2Entries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (p5d2Entries.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (P5D2Entry p5d2Entry : p5d2Entries) {
			if (entityCache.getResult(
					P5D2EntryImpl.class, p5d2Entry.getPrimaryKey()) == null) {

				cacheResult(p5d2Entry);
			}
		}
	}

	/**
	 * Clears the cache for all p5d2 entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(P5D2EntryImpl.class);

		finderCache.clearCache(P5D2EntryImpl.class);
	}

	/**
	 * Clears the cache for the p5d2 entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(P5D2Entry p5d2Entry) {
		entityCache.removeResult(P5D2EntryImpl.class, p5d2Entry);
	}

	@Override
	public void clearCache(List<P5D2Entry> p5d2Entries) {
		for (P5D2Entry p5d2Entry : p5d2Entries) {
			entityCache.removeResult(P5D2EntryImpl.class, p5d2Entry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(P5D2EntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(P5D2EntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new p5d2 entry with the primary key. Does not add the p5d2 entry to the database.
	 *
	 * @param p5d2EntryId the primary key for the new p5d2 entry
	 * @return the new p5d2 entry
	 */
	@Override
	public P5D2Entry create(long p5d2EntryId) {
		P5D2Entry p5d2Entry = new P5D2EntryImpl();

		p5d2Entry.setNew(true);
		p5d2Entry.setPrimaryKey(p5d2EntryId);

		return p5d2Entry;
	}

	/**
	 * Removes the p5d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry that was removed
	 * @throws NoSuchP5D2EntryException if a p5d2 entry with the primary key could not be found
	 */
	@Override
	public P5D2Entry remove(long p5d2EntryId) throws NoSuchP5D2EntryException {
		return remove((Serializable)p5d2EntryId);
	}

	/**
	 * Removes the p5d2 entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the p5d2 entry
	 * @return the p5d2 entry that was removed
	 * @throws NoSuchP5D2EntryException if a p5d2 entry with the primary key could not be found
	 */
	@Override
	public P5D2Entry remove(Serializable primaryKey)
		throws NoSuchP5D2EntryException {

		Session session = null;

		try {
			session = openSession();

			P5D2Entry p5d2Entry = (P5D2Entry)session.get(
				P5D2EntryImpl.class, primaryKey);

			if (p5d2Entry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchP5D2EntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(p5d2Entry);
		}
		catch (NoSuchP5D2EntryException noSuchEntityException) {
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
	protected P5D2Entry removeImpl(P5D2Entry p5d2Entry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(p5d2Entry)) {
				p5d2Entry = (P5D2Entry)session.get(
					P5D2EntryImpl.class, p5d2Entry.getPrimaryKeyObj());
			}

			if (p5d2Entry != null) {
				session.delete(p5d2Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (p5d2Entry != null) {
			clearCache(p5d2Entry);
		}

		return p5d2Entry;
	}

	@Override
	public P5D2Entry updateImpl(P5D2Entry p5d2Entry) {
		boolean isNew = p5d2Entry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(p5d2Entry);
			}
			else {
				p5d2Entry = (P5D2Entry)session.merge(p5d2Entry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(P5D2EntryImpl.class, p5d2Entry, false, true);

		if (isNew) {
			p5d2Entry.setNew(false);
		}

		p5d2Entry.resetOriginalValues();

		return p5d2Entry;
	}

	/**
	 * Returns the p5d2 entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the p5d2 entry
	 * @return the p5d2 entry
	 * @throws NoSuchP5D2EntryException if a p5d2 entry with the primary key could not be found
	 */
	@Override
	public P5D2Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchP5D2EntryException {

		P5D2Entry p5d2Entry = fetchByPrimaryKey(primaryKey);

		if (p5d2Entry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchP5D2EntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return p5d2Entry;
	}

	/**
	 * Returns the p5d2 entry with the primary key or throws a <code>NoSuchP5D2EntryException</code> if it could not be found.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry
	 * @throws NoSuchP5D2EntryException if a p5d2 entry with the primary key could not be found
	 */
	@Override
	public P5D2Entry findByPrimaryKey(long p5d2EntryId)
		throws NoSuchP5D2EntryException {

		return findByPrimaryKey((Serializable)p5d2EntryId);
	}

	/**
	 * Returns the p5d2 entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param p5d2EntryId the primary key of the p5d2 entry
	 * @return the p5d2 entry, or <code>null</code> if a p5d2 entry with the primary key could not be found
	 */
	@Override
	public P5D2Entry fetchByPrimaryKey(long p5d2EntryId) {
		return fetchByPrimaryKey((Serializable)p5d2EntryId);
	}

	/**
	 * Returns all the p5d2 entries.
	 *
	 * @return the p5d2 entries
	 */
	@Override
	public List<P5D2Entry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @return the range of p5d2 entries
	 */
	@Override
	public List<P5D2Entry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p5d2 entries
	 */
	@Override
	public List<P5D2Entry> findAll(
		int start, int end, OrderByComparator<P5D2Entry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p5d2 entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>P5D2EntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of p5d2 entries
	 * @param end the upper bound of the range of p5d2 entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of p5d2 entries
	 */
	@Override
	public List<P5D2Entry> findAll(
		int start, int end, OrderByComparator<P5D2Entry> orderByComparator,
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

		List<P5D2Entry> list = null;

		if (useFinderCache) {
			list = (List<P5D2Entry>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_P5D2ENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_P5D2ENTRY;

				sql = sql.concat(P5D2EntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<P5D2Entry>)QueryUtil.list(
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
	 * Removes all the p5d2 entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (P5D2Entry p5d2Entry : findAll()) {
			remove(p5d2Entry);
		}
	}

	/**
	 * Returns the number of p5d2 entries.
	 *
	 * @return the number of p5d2 entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_P5D2ENTRY);

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
		return "p5d2EntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_P5D2ENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return P5D2EntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the p5d2 entry persistence.
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

		_setP5D2EntryUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setP5D2EntryUtilPersistence(null);

		entityCache.removeCache(P5D2EntryImpl.class.getName());
	}

	private void _setP5D2EntryUtilPersistence(
		P5D2EntryPersistence p5d2EntryPersistence) {

		try {
			Field field = P5D2EntryUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, p5d2EntryPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = P5D2PersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = P5D2PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = P5D2PersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_P5D2ENTRY =
		"SELECT p5d2Entry FROM P5D2Entry p5d2Entry";

	private static final String _SQL_COUNT_P5D2ENTRY =
		"SELECT COUNT(p5d2Entry) FROM P5D2Entry p5d2Entry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "p5d2Entry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No P5D2Entry exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		P5D2EntryPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private P5D2EntryModelArgumentsResolver _p5d2EntryModelArgumentsResolver;

}