package com.acme.headless.r3b2.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Foo;

import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Locale;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-r3b2/v1.0
 *
 * @author Jonah the son of Amittai
 * @generated
 */
@Generated("")
@ProviderType
public interface FooResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public Page<Foo> getFooPage() throws Exception;

	public Foo postFoo(Foo foo) throws Exception;

	public Response postFooBatch(String callbackURL, Object object)
		throws Exception;

	public void deleteFoo(Long fooId) throws Exception;

	public Response deleteFooBatch(String callbackURL, Object object)
		throws Exception;

	public Foo getFoo(Long fooId) throws Exception;

	public Foo patchFoo(Long fooId, Foo foo) throws Exception;

	public Foo putFoo(Long fooId, Foo foo) throws Exception;

	public Response putFooBatch(String callbackURL, Object object)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser);

	public void setGroupLocalService(GroupLocalService groupLocalService);

	public void setRoleLocalService(RoleLocalService roleLocalService);

	public static class FactoryHolder {

		public static volatile Factory factory;

	}

	@ProviderType
	public interface Builder {

		public FooResource build();

		public Builder checkPermissions(boolean checkPermissions);

		public Builder httpServletRequest(
			HttpServletRequest httpServletRequest);

		public Builder preferredLocale(Locale preferredLocale);

		public Builder user(com.liferay.portal.kernel.model.User user);

	}

	@ProviderType
	public interface Factory {

		public Builder create();

	}

}