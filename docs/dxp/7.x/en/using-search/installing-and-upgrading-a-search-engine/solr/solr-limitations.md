# Solr Limitations 

```important::
   Liferay's Solr support will receive one more update (to support Solr 8) before being deprecated. Please plan to migrate to `Elasticsearch <../elasticsearch/getting-started-with-Elasticsearch.md>`_.

   See `LPS-11013 <https://issues.liferay.com/browse/LPS-111013>`__ to follow the progress of Liferay's Solr 8 integration.
```

## End-User Feature Limitations 

Liferay's Solr integration has limitations affect using Liferay Commerce and the following features. Elasticsearch is required to use the following features.

* [Workflow Metrics](../../../process-automation/workflow/user-guide/using-workflow-metrics.md)
* [Custom Filter search widget](../../search-pages-and-widgets/search-results/filtering-search-results.md)
* The Low Level Search Options widget
* Search Tuning: Customizing Search Results
* Search Tuning: Synonyms

## Developer Feature Limitations 

The following APIs are not currently supported by Liferay's Solr connector.

* From Portal Core (Module: `portal-kernel`, Artifact:
    `com.liferay.portal.kernel`):
  * `com.liferay.portal.kernel.search.generic.NestedQuery`
  * `com.liferay.portal.kernel.search.filter`:
    * `ComplexQueryPart`
    * `GeoBoundingBoxFilter`
    * `GeoDistanceFilter`
    * `GeoDistanceRangeFilter`
    * `GeoPolygonFilter`
* From the Portal Search API (Module: `portal-search-api`, Artifact:
    `com.liferay.portal.search.api`):
  * `com.liferay.portal.search.filter`:
    * `ComplexQueryPart`
    * `TermsSetFilter`
  * `com.liferay.portal.search.geolocation.*`
  * `com.liferay.portal.search.highlight.*`
  * `com.liferay.portal.search.query.function.*`
  * `com.liferay.portal.search.query.*`:
  * `com.liferay.portal.search.script.*`
  * `com.liferay.portal.search.significance.*`
  * `com.liferay.portal.search.sort.*`: only `Sort`,`FieldSort`, and `ScoreSort` are supported
* Portal Search Engine Adapter API (Module: `portal-search-engine-adapter-api`,
    Artifact: `com.liferay.portal.search.engine.adapter.api`)
  * `com.liferay.portal.search.engine.adapter.cluster.*`
  * `com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentRequest`
  * `com.liferay.portal.search.engine.adapter.index.*`: only `RefreshIndexRequest` is supported
  * `com.liferay.portal.search.engine.adapter.search.*`:
    * `MultisearchSearchRequest`
    * `SuggestSearchRequest`
  * `com.liferay.portal.search.engine.adapter.snapshot.*`

```note::
   Liferay Commerce requires the ``TermsSetFilter`` implementation which is only available in the Elasticsearch connector.
```
