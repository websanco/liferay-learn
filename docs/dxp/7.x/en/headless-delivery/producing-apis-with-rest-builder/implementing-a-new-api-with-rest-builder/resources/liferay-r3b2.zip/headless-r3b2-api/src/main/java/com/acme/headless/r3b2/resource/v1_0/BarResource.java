package com.acme.headless.r3b2.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Bar;

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
public interface BarResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public void deleteBar(Long barId) throws Exception;

	public Response deleteBarBatch(String callbackURL, Object object)
		throws Exception;

	public Bar getBar(Long barId) throws Exception;

	public Bar patchBar(Long barId, Bar bar) throws Exception;

	public Bar putBar(Long barId, Bar bar) throws Exception;

	public Response putBarBatch(String callbackURL, Object object)
		throws Exception;

	public Page<Bar> getFooBarsPage(Long fooId) throws Exception;

	public Bar postFooBar(Long fooId, Bar bar) throws Exception;

	public Response postFooBarBatch(
			Long fooId, String callbackURL, Object object)
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

		public BarResource build();

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