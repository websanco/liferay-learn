---
description: 14 - Customize the Portal Search
title: Introducing Liferay Search
order: 2
---

## Introducing Liferay Search

Liferay has an extensive search framework. Customization can be done on all layers, from the user interface to the indexing process and index settings. Because of the pluggable architecture and strong APIs, even writing a completely new custom adapter for a third-party search engine is possible.

In the following sections, we will be focusing on the features and customization options using the default Elasticsearch engine adapter. 

#### Why an External Search Engine?

Why do we need a separate search engine? Why can't we just use database queries? There are several reasons. For starters, RDBMS data structures are not optimal for searching, and database JOIN clauses can be performance killers. Search engines, on the other hand, use storage algorithms and structures like __inverted indexes__, which are optimized for speed. Search engines also use a fully configurable process called __analysis__ that optimizes the data for search-ability in different use cases and scenarios. Search engines also give access to querying features like __relevance__ and __scoring__ and provide support for __advanced query types__ and features like fuzzy or proximity searches. In production systems, search engines typically run in a dedicated server, reducing the load from Liferay servers and improving the overall __performance__.

#### Liferay's Search Engine Support

Applications on the Liferay platform communicate through the portal search API, making the search engine support pluggable and possible to create new search engine adapters: 

<img src="../images/adapter-design.png" style="max-width: 100%;" />

Liferay's default search engine is [Elasticsearch](https://www.elastic.co/), but an adapter for SOLR is also available. 

The Elasticsearch adapter has two available operation modes: 

1. Embedded
1. Remote

When running in embedded mode, Liferay is using the embedded Elasticsearch engine, which runs in the same process as the Liferay platform. In the remote operation mode, Liferay is configured to use an external Elasticsearch server or cluster. Using embedded mode is not recommended in the production systems, as it will usually have a negative impact on the Liferay platform's overall performance.

<img src="../images/elasticsearch-operation-modes.png" style="max-width: 100%;" />

<div class="summary">
<h3>Knowledge Check</h3>
<ul> 
	<li>______________________ is the default search engine in Liferay, but an adapter for ____________________ is also provided.</li>
	<li>Elasticsearch has two available operation modes:</li>
	<ul>
		<li>_____________________</li>
		<li>_____________________</li>
	</ul>
	<li>Code using the Liferay platform search always communicates through the _____________________________.</li>
</ul>
</div>