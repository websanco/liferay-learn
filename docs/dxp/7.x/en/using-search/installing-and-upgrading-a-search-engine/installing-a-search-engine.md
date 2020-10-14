# Installing a Search Engine

A search engine is a critical component of your Liferay installation. The [creating an example cluster](./../../installation-and-upgrades/setting-up-liferay-dxp/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md#prepare-a-search-engine) documentation can get you started with the installation, but this section covers more of the details (though at a high level) you need for setting up a production system.

<!-- MAKE A DIAGRAM SIMILAR TO THE CCR ONE BUT WITH JUST ONE CONNECTION -->

When you start Liferay a built-in Elasticsearch server is simultaneously started. This default search engine provides search capabilities as a convenience for testing, but it isn't supported for use in production. [Getting Started with Elasticsearch](./elasticsearch/getting-started-with-elasticsearch.md) describes production-level Elasticsearch setup. [Using the Sidecar or Embedded Elasticsearch](./elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md) describes the default search engine's features and limitations.
<!-- Is this the place to introduce sidecar which will replace embedded in GA4? -->

```note::
   Besides Elasticsearch, Liferay also supports `Solr <http://lucene.apache.org/solr>`__. Solr is not embedded and must be connected remotely, even for development and testing. To use Solr, see `Installing Solr <https://help.liferay.com/hc/articles/360032264052-Installing-Solr>`_.
```
<!-- Is this the place to mention our deprecation of solr support? And that one can develop their own search engine adapter? And what we support in terms of custom search engine adapters? -->

## Java Requirements

- Search engines require the `JAVA_HOME` environment variable. Set it on your search engine host.

- If you're using Liferay 7.2, Elasticsearch and Liferay must use the same Java version and distribution. Consult the [Elasticsearch compatibility matrix](https://www.elastic.co/support/matrix#matrix_jvm) and the [Liferay compatibility matrix](https://help.liferay.com/hc/sections/360002103292-Compatibility-Matrix) to learn more about supported JDK distributions and versions. You can specify this in Elasticsearch:

    ```properties
    [Elasticsearch Home]/bin/elasticsearch.in.sh`: `JAVA_HOME=/path/to/java`
    ```

    This requirement doesn't apply to Liferay 7.3 because the Elasticsearch 7 connector communicates via HTTP; there's no JVM level serialization. See the [Elastic's High-Level REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html) for details.

    ```note::
       The same requirement is not applicable to Solr as no JVM level serialization happens between the servers. Rather, all communication occurs at the HTTP level.
    ```
## Clustering the Search Engine

A production environment's search engine should be clustered for load managements and optimal performance. Both Elasticsearch and Solr can be configured successfully on multiple nodes in the remote environment.

* To configure a remote Elasticsearch server or cluster, see [Getting Started with Elasticsearch](./elasticsearch/getting-started-with-elasticsearch.md).

* To configure a remote Solr server or cluster, see the [Installing Solr](./solr/installing-solr.md).

## Selecting a Search Engine Vendor and Version

Elasticsearch is the recommended search engine for search and indexing with Liferay. Elasticsearch and Solr are supported, but the [Solr integration is less robust](./installing-solr/solr-limitations.md)

```important::
   Always refer to the `compatibility matrix <https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix>`_ to find the exact versions supported.
```

- [Install the latest supported Elasticsearch version](./elasticsearch/getting-started-with-elasticsearch.md)
- [Installing the latest supported Solr version](./solr/installing-solr.md)

```note::
   If your installation requires a search engine besides Elasticsearch, whether as a replacement or in conjunction, you can write your own client application to provide a custom search engine connection. See the `Writing a Search Engine Adapter <../developer-guide/writing-a-search-engine-adapter.md>`_ for details.
```

<!-- Probably add at least a parenthetical statement about why a search engine might be used in conjunction w/Elasticsearch-->

<!-- Goes in the Solr section intro article and will need updates
## Using Solr

There are some drawbacks to using Solr as the search engine. These limitations affect how Solr processes search requests in various Liferay products.

### End User Feature Limitations of Liferay's Solr Integration

* [Liferay Commerce](https://help.liferay.com/hc/en-us/articles/360017869952)
* [Workflow Metrics](https://help.liferay.com/hc/en-us/articles/360029042071-Workflow-Metrics-The-Service-Level-Agreement-SLA-)
* [Custom Filter search widget](https://help.liferay.com/hc/en-us/articles/360028721272-Filtering-Search-Results-with-the-Custom-Filter-Widget)
* [The Low Level Search Options widget](https://help.liferay.com/hc/en-us/articles/360032607571-Low-Level-Search-Options-Searching-Additional-or-Alternate-Indexes)
* [Search Tuning: Customizing Search Results](https://help.liferay.com/hc/en-us/articles/360034473872-Search-Tuning-Customizing-Search-Results)
* [Search Tuning: Synonyms](https://help.liferay.com/hc/articles/360034473852-Search-Tuning-Synonym-Sets)

### Developer Feature Limitations of Liferay's Solr Integration

Implementation for the following APIs may be added in the future, but they are not currently supported by Liferay's Solr connector.

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

Liferay Commerce requires the `TermsSetFilter` implementation which is only available in the Elasticsearch connector.
-->
