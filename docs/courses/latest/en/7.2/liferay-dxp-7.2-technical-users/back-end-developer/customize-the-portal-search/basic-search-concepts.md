---
description: 14 - Customize the Portal Search
title: Basic Search Concepts
order: 3
---

## Basic Search Concepts

Many of the platform search customization tasks require an understanding of basic search concepts that are not Liferay-specific, but common for search engines in general. Some of these fundamental concepts are *indexing*, *analysis*, *searching*, and *queries*.

> In versions prior to 7.1, search and indexing was accomplished with one single indexer class extending a BaseIndexer. The new pattern is based on API-SPI model and composition of multiple worker components. Although deprecated, the old approach still works in 7.2.

#### Indexing 

To be able to search for model entities in Liferay, they have to be indexed. Indexing is a process of transforming input data to a search engine document, which is the storage model type for search engines. 

In Liferay, the input data is usually a registered portal asset. Every model type can have a different data structure, which is why there are dedicated indexing contributor components for every type. 

The contributor component below controls which Blog entry fields are indexed:

```java
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

package com.liferay.blogs.internal.search.spi.model.index.contributor;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Luan Maoski
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.blogs.model.BlogsEntry",
	service = ModelDocumentContributor.class
)
public class BlogsEntryModelDocumentContributor
	implements ModelDocumentContributor<BlogsEntry> {

	@Override
	public void contribute(Document document, BlogsEntry blogsEntry) {
		document.addText(Field.CAPTION, blogsEntry.getCoverImageCaption());

		String content = HtmlUtil.extractText(blogsEntry.getContent());

		document.addText(Field.CONTENT, content);

		document.addText(Field.DESCRIPTION, blogsEntry.getDescription());
		document.addDate(Field.DISPLAY_DATE, blogsEntry.getDisplayDate());
		document.addDate(Field.MODIFIED_DATE, blogsEntry.getModifiedDate());
		document.addText(Field.SUBTITLE, blogsEntry.getSubtitle());
		document.addText(Field.TITLE, blogsEntry.getTitle());

		for (Locale locale :
				LanguageUtil.getAvailableLocales(blogsEntry.getGroupId())) {

			String languageId = LocaleUtil.toLanguageId(locale);

			document.addText(
				LocalizationUtil.getLocalizedName(Field.CONTENT, languageId),
				content);
			document.addText(
				LocalizationUtil.getLocalizedName(Field.TITLE, languageId),
				blogsEntry.getTitle());
		}
	}

}

```

> See the complete source here: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/spi/model/index/contributor/BlogsEntryModelDocumentContributor.java.

<br /><br /><br />

#### Analysis

Analysis is a field-level process of transforming input data into search engine document field data. This process varies depending on the engine, but in Elasticsearch, analysis is accomplished in three phases:

1. __Character filtering__: for example, removing HTML tags
1. __Tokenizing__: effectively splitting the field value into individual tokens, sometimes also called words
1. __Token filtering__: language-specific analysis, removal of stop words, etc.

Analysis is a fully configurable process. Individual configurations are sometimes called __analyzers__ and are defined on an individual field-level, meaning that every single field in the index document can have a different analyzer assigned. Analyzers are configured in the index settings and assigned to the fields in the mapping definitions.

Analyzers are used both in indexing and in querying the index. Notice that the same field analyzers have to be used both at index and at query-time in order to get predictable results. For example, if a field is indexed using an English language filter, analyzing the search query with a German filter would result in a mismatch in, for example, possessive and plural word forms.

Below is a logical view to the indexing and analysis process in Elasticsearch:

<img src="../images/how-indexing-works.png" style="max-width: 100%;" />

#### Searching

Searching in technical terms involves sending a search query and obtaining results, also called hits, from the search engine.  

Searching involves regular and filter queries. Queries may also have sub-queries. 

A regular __query__ asks if an index document field matches the keywords in a way defined in the query (type) and how relevant the document field is to the search terms (score). The query type can be, for example, an exact match, wildcard, or term query. Every query type may have its own properties, but common ones are *operator*, which defines if a query with multiple words has to match all, none, or just one (AND, NOT, OR), and *boosting*, which defines the scoring weight of that specific query in the final, composed query.  

A __filter query__ is composed the same way as a regular query. Its only difference is that it works in a different context and returns only a simple "yes" or "no" to its condition, instead of scoring the relevancy. As filter queries don't impact scoring, they are practically used to limit the result set. For example, a filter query might limit the results only to a certain Liferay site, and the regular query would then find any relevant documents belonging to that site and score them against the search phrase. 

> Although the same result set can be achieved by using regular queries instead of filter queries, you should remember that filter queries are much faster.

Below is a logical view showing the different phases and components when doing a search in Liferay:

<img src="../images/how-searching-works.png" style="max-width: 100%;" />

<div class="summary">
<h3>Knowledge Check</h3>
<ul> 
	<li>____________________ is a process of transforming input data to a search engine document, which is the storage model type for search engines.</li>
	<li>Elasticsearch has three phases:</li>
	<ul>
		<li> __________________________</li>
    <li>__________________________</li>
		<li>__________________________</li>
	</ul>
	<li>_________________ involves sending a search query and obtaining results, also called ______________, from the search engine.  </li>
</ul>
</div>