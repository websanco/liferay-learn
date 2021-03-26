package com.acme.headless.r3b2.internal.graphql.mutation.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Bar;
import com.acme.headless.r3b2.dto.v1_0.Foo;
import com.acme.headless.r3b2.resource.v1_0.BarResource;
import com.acme.headless.r3b2.resource.v1_0.FooResource;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Jonah the son of Amittai
 * @generated
 */
@Generated("")
public class Mutation {

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

	@GraphQLField(description = "")
	public boolean deleteBar(@GraphQLName("barId") Long barId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> barResource.deleteBar(barId));

		return true;
	}

	@GraphQLField
	public Response deleteBarBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> barResource.deleteBarBatch(callbackURL, object));
	}

	@GraphQLField(description = "")
	public Bar patchBar(
			@GraphQLName("barId") Long barId, @GraphQLName("bar") Bar bar)
		throws Exception {

		return _applyComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> barResource.patchBar(barId, bar));
	}

	@GraphQLField(description = "")
	public Bar updateBar(
			@GraphQLName("barId") Long barId, @GraphQLName("bar") Bar bar)
		throws Exception {

		return _applyComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> barResource.putBar(barId, bar));
	}

	@GraphQLField
	public Response updateBarBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> barResource.putBarBatch(callbackURL, object));
	}

	@GraphQLField(description = "")
	public Bar createFooBar(
			@GraphQLName("fooId") Long fooId, @GraphQLName("bar") Bar bar)
		throws Exception {

		return _applyComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> barResource.postFooBar(fooId, bar));
	}

	@GraphQLField
	public Response createFooBarBatch(
			@GraphQLName("fooId") Long fooId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_barResourceComponentServiceObjects, this::_populateResourceContext,
			barResource -> barResource.postFooBarBatch(
				fooId, callbackURL, object));
	}

	@GraphQLField(description = "")
	public Foo createFoo(@GraphQLName("foo") Foo foo) throws Exception {
		return _applyComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> fooResource.postFoo(foo));
	}

	@GraphQLField
	public Response createFooBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> fooResource.postFooBatch(callbackURL, object));
	}

	@GraphQLField(description = "")
	public boolean deleteFoo(@GraphQLName("fooId") Long fooId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> fooResource.deleteFoo(fooId));

		return true;
	}

	@GraphQLField
	public Response deleteFooBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> fooResource.deleteFooBatch(callbackURL, object));
	}

	@GraphQLField(description = "")
	public Foo patchFoo(
			@GraphQLName("fooId") Long fooId, @GraphQLName("foo") Foo foo)
		throws Exception {

		return _applyComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> fooResource.patchFoo(fooId, foo));
	}

	@GraphQLField(description = "")
	public Foo updateFoo(
			@GraphQLName("fooId") Long fooId, @GraphQLName("foo") Foo foo)
		throws Exception {

		return _applyComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> fooResource.putFoo(fooId, foo));
	}

	@GraphQLField
	public Response updateFooBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_fooResourceComponentServiceObjects, this::_populateResourceContext,
			fooResource -> fooResource.putFooBatch(callbackURL, object));
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}