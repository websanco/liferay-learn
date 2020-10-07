package com.acme.r3b2.journal.article.expansion.internal.graphql.query.v1_0;

import com.acme.r3b2.journal.article.expansion.dto.v1_0.Article;
import com.acme.r3b2.journal.article.expansion.resource.v1_0.ArticleResource;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Liferay
 * @generated
 */
@Generated("")
public class Query {

	public static void setArticleResourceComponentServiceObjects(
		ComponentServiceObjects<ArticleResource>
			articleResourceComponentServiceObjects) {

		_articleResourceComponentServiceObjects =
			articleResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {firstArticleByUser(userId: ___){userId, id, title, content}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Article firstArticleByUser(@GraphQLName("userId") Integer userId)
		throws Exception {

		return _applyComponentServiceObjects(
			_articleResourceComponentServiceObjects,
			this::_populateResourceContext,
			articleResource -> articleResource.getFirstArticleByUser(userId));
	}

	@GraphQLName("ArticlePage")
	public class ArticlePage {

		public ArticlePage(Page articlePage) {
			actions = articlePage.getActions();

			items = articlePage.getItems();
			lastPage = articlePage.getLastPage();
			page = articlePage.getPage();
			pageSize = articlePage.getPageSize();
			totalCount = articlePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Article> items;

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

	private void _populateResourceContext(ArticleResource articleResource)
		throws Exception {

		articleResource.setContextAcceptLanguage(_acceptLanguage);
		articleResource.setContextCompany(_company);
		articleResource.setContextHttpServletRequest(_httpServletRequest);
		articleResource.setContextHttpServletResponse(_httpServletResponse);
		articleResource.setContextUriInfo(_uriInfo);
		articleResource.setContextUser(_user);
		articleResource.setGroupLocalService(_groupLocalService);
		articleResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<ArticleResource>
		_articleResourceComponentServiceObjects;

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