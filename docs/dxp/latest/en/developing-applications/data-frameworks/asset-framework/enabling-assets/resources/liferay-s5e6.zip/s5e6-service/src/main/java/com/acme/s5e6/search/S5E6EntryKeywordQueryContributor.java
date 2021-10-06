package com.acme.s5e6.search;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "indexer.class.name=com.acme.s5e6.model.S5E6Entry",
	service = KeywordQueryContributor.class
)
public class S5E6EntryKeywordQueryContributor
	implements KeywordQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		queryHelper.addSearchLocalizedTerm(
			booleanQuery, searchContext, Field.CONTENT, false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, Field.SUBTITLE, false);
		queryHelper.addSearchLocalizedTerm(
			booleanQuery, searchContext, Field.TITLE, false);
	}

	@Reference
	protected QueryHelper queryHelper;

}