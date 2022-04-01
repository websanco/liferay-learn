---
description: 14 - Customize the Portal Search
title: Customize Indexing and Searching
order: 4
---

## Customize Indexing and Searching

The customization approaches for the platform search can generally be divided into the following categories: 

* __User interface related__
	* Customize the front-end by developing __module fragments for the search portlets__.
	* Contribute to results with the [Search Contributor SPI](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/results) result components.
	* Customize the result hits with [HitsProcessors](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/search/hits/HitsProcessor.java).
* __Indexing related__
	* Customize the __index settings and type mappings__ from the Control Panel.	
	* Contribute to the model's indexing with the [Search Contributor SPI](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor) indexing component.
	* Contribute to Elasticsearch type mappings with [IndexSettingsContributors](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search-elasticsearch6/portal-search-elasticsearch6-api/src/main/java/com/liferay/portal/search/elasticsearch6/settings/IndexSettingsContributor.java).
	* Customize indexing with [Indexer Post Processors](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/search/IndexerPostProcessor.java).
* __Searching related__	
	* Contribute to the model's indexing with the [Contributor SPI](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor) component.
	* Customize searching with [Indexer Post Processors](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/search/IndexerPostProcessor.java).

#### Search Contributors

Since version 7.1, Liferay's search framework has relied on API-SPI (Service Provider Interface) architecture and on a composition model, where index documents, search queries, and result hits are built with model-specific or generic model contributor components. 

As the name says, contributor components are primarily meant for contributing. If you'd like, for example, to __add__ a filter to all the queries so that only contents edited within one hour would be returned, you could create a [QueryPreFilterContributor](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/QueryPreFilterContributor.java) component:

```java
/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.training.search.queryprefiltercontributor;

import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	service = com.liferay.portal.search.spi.model.query.contributor.QueryPreFilterContributor.class
)
public class SampleQueryPreFilterContributor
	implements com.liferay.portal.search.spi.model.query.contributor.QueryPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		RangeTermFilter rangeTermFilter = new RangeTermFilter(
			Field.CREATE_DATE, true, true, "now-1d", null);

		booleanFilter.add(rangeTermFilter, BooleanClauseOccur.MUST);
	}

}
```

> See a Blade example (https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/extensions/search-model-pre-filter-contributor) of the model-specific ModelPreFilterQueryContributor.

There are many extension points available. See the sources for the extendable Search Model Contributor SPI here: https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/.  

#### Indexer Post Processor

Indexer post processors are components that allow you to customize both indexing and searching. Although you can achieve the same results with both Search Model Contributors and Indexer Post Processors, processors are less specialized components but give control over the query, query filters, and indexing in a single component. It's also important to notice that Contributor components are executed after the Indexer Post Processors.  

From a code perspective, an indexer post processor is an OSGi component that implements the [IndexerPostProcessor](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/search/IndexerPostProcessor.java) interface. The targeted model types are defined by a component's *indexer.class.name* property.

There can be multiple indexer post processors registered to a single model type. Put another way, a single indexer post processor can register to multiple model types.

Below is an example of an indexer post processor registered to both  Blogs Entries and Journal Articles:

```java
@Component(
	immediate = true,
	property = {
		"indexer.class.name=com.liferay.blogs.model.BlogsEntry",
		"indexer.class.name=com.liferay.journal.model.JournalArtice"
	},
	service = IndexerPostProcessor.class
)
public class BlogsIndexerPostProcessor implements IndexerPostProcessor {

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext)
		throws Exception {

		// Query processing code here
	}
}	
```

When you create indexer post processors, you typically extend the [BaseIndexerPostProcessor](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/search/BaseIndexerPostProcessor.java) base class instead of implementing the IndexerPostProcessor interface directly. That way, you can override just the methods you want and not implement the whole interface.
 
#### The Hits Processor

The [HitsProcessor](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/search/hits/HitsProcessor.java) allows you to perform actions on the hits returned. Example use cases include processing the hits before they are sent to the user interface, spell-checking, doing an alternative query, and indexing successful queries for the autocompletion/keyword suggestion purposes.

Hits processors can be chained. The processing orders is defined by the component's *sort.order* property.

Below is an example of a hits processor:

```java
@Component(
	immediate = true, 
	property = "sort.order=0", 
	service = HitsProcessor.class
)
public class CollatedSpellCheckHitsProcessor implements HitsProcessor {

	@Override
	public boolean process(SearchContext searchContext, Hits hits)
		throws SearchException {

		QueryConfig queryConfig = searchContext.getQueryConfig();

		if (!queryConfig.isCollatedSpellCheckResultEnabled()) {
			return true;
		}

		int collatedSpellCheckResultScoresThreshold =
			queryConfig.getCollatedSpellCheckResultScoresThreshold();

		if (hits.getLength() >= collatedSpellCheckResultScoresThreshold) {
			return true;
		}

		String collatedKeywords = IndexSearcherHelperUtil.spellCheckKeywords(
			searchContext);

		if (collatedKeywords.equals(searchContext.getKeywords())) {
			collatedKeywords = StringPool.BLANK;
		}

		hits.setCollatedSpellCheckResult(collatedKeywords);

		return true;
	}

}
```

The following hits processors are run by default. Configuration is done in [portal.properties](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/portal.properties) with `index.search` prefixes settings:

* [CollatedSpellCheckHitsProcessor](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/hits/CollatedSpellCheckHitsProcessor.java): Performs a spell check if the minimum score for the results is less than a given threshold
* [AlternateKeywordQueryHitsProcessor](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/hits/AlternateKeywordQueryHitsProcessor.java): issues an alternate query automatically based on suggested keywords from the CollatedSpellCheckHitsProcessor
* [QueryIndexingHitsProcessor](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/hits/QueryIndexingHitsProcessor.java): indexes a query if the number of hits has exceeded a configured quantity
* [QuerySuggestionHitsProcessor](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/hits/QuerySuggestionHitsProcessor.java): allows you to suggest other potential queries of previous searches that have yielded more results

<div class="summary">
<h3>Knowledge Check</h3>
<ul> 
	<li>__________________________________ are components that allow you to modify the way portal assets are being indexed.</li>
	<li>The hits processors allows you to perform ___________________ on the hits returned.</li>
	<li>The following hits processors are run by default:</li>
	<ul> 
		<li>____________________________</li>
    	<li>____________________________</li>
		<li>____________________________</li>
		<li>____________________________</li>
	</ul>
</ul>
</div>