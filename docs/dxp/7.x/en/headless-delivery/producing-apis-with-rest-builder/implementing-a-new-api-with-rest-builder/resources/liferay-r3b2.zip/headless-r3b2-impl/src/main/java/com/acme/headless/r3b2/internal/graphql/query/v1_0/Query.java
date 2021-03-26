package com.acme.headless.r3b2.internal.graphql.query.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Bar;
import com.acme.headless.r3b2.dto.v1_0.Foo;
import com.acme.headless.r3b2.resource.v1_0.BarResource;
import com.acme.headless.r3b2.resource.v1_0.FooResource;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Jonah the son of Amittai
 * @generated
 */
@Generated("")
public class Query {

	public static void setBarResourceComponentServiceObjects(
		ComponentServiceObjects<BarResource>
			barResourceComponentServiceObjects) {

		_barResourceComponentServiceObjects =
			barResourceComponentServiceObjects;
	}

	public static void setFooResourceComponentServiceObjects(
		ComponentServiceObjects<FooResource>
			fooResourceComponentServiceObjects) {

		_fooResourceComponentServiceObjects =
			fooResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {bar(barId: ___){description, fooId, id, name}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "")
	public Bar bar(@GraphQLName("barId") Long barId) throws Exception {
		return _applyComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> barResource.getBar(barId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {fooBars(fooId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "")
	public BarPage fooBars(@GraphQLName("fooId") Long fooId) throws Exception {
		return _applyComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> new BarPage(barResource.getFooBarsPage(fooId)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {foo{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "")
	public FooPage foo() throws Exception {
		return _applyComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> new FooPage(fooResource.getFooPage()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {foo(fooId: ___){description, id, name}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "")
	public Foo foo(@GraphQLName("fooId") Long fooId) throws Exception {
		return _applyComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> fooResource.getFoo(fooId));
	}

	@GraphQLTypeExtension(Bar.class)
	public class GetFooTypeExtension {

		public GetFooTypeExtension(Bar bar) {
			_bar = bar;
		}

		@GraphQLField(description = "")
		public Foo foo() throws Exception {
			return _applyComponentServiceObjects(
				_fooResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				fooResource -> fooResource.getFoo(_bar.getFooId()));
		}

		private Bar _bar;

	}

	@GraphQLTypeExtension(Foo.class)
	public class GetFooBarsPageTypeExtension {

		public GetFooBarsPageTypeExtension(Foo foo) {
			_foo = foo;
		}

		@GraphQLField(description = "")
		public BarPage bars() throws Exception {
			return _applyComponentServiceObjects(
				_barResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				barResource -> new BarPage(
					barResource.getFooBarsPage(_foo.getId())));
		}

		private Foo _foo;

	}

	@GraphQLName("BarPage")
	public class BarPage {

		public BarPage(Page barPage) {
			actions = barPage.getActions();

			items = barPage.getItems();
			lastPage = barPage.getLastPage();
			page = barPage.getPage();
			pageSize = barPage.getPageSize();
			totalCount = barPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Bar> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("FooPage")
	public class FooPage {

		public FooPage(Page fooPage) {
			actions = fooPage.getActions();

			items = fooPage.getItems();
			lastPage = fooPage.getLastPage();
			page = fooPage.getPage();
			pageSize = fooPage.getPageSize();
			totalCount = fooPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Foo> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(BarResource barResource)
		throws Exception {

		barResource.setContextAcceptLanguage(_acceptLanguage);
		barResource.setContextCompany(_company);
		barResource.setContextHttpServletRequest(_httpServletRequest);
		barResource.setContextHttpServletResponse(_httpServletResponse);
		barResource.setContextUriInfo(_uriInfo);
		barResource.setContextUser(_user);
		barResource.setGroupLocalService(_groupLocalService);
		barResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(FooResource fooResource)
		throws Exception {

		fooResource.setContextAcceptLanguage(_acceptLanguage);
		fooResource.setContextCompany(_company);
		fooResource.setContextHttpServletRequest(_httpServletRequest);
		fooResource.setContextHttpServletResponse(_httpServletResponse);
		fooResource.setContextUriInfo(_uriInfo);
		fooResource.setContextUser(_user);
		fooResource.setGroupLocalService(_groupLocalService);
		fooResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<BarResource>
		_barResourceComponentServiceObjects;
	private static ComponentServiceObjects<FooResource>
		_fooResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}