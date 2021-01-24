# Installing a Search Engine

A search engine is a critical component of your Liferay installation. [Creating an Example Cluster](./../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md#prepare-a-search-engine) can get you started with the installation, but this guide demonstrates setting up a **production** environment.

<!-- MAKE A DIAGRAM SIMILAR TO THE CCR ONE BUT WITH JUST ONE CONNECTION -->

When you start Liferay, a built-in Elasticsearch server (sidecar) starts simultaneously. This default search engine provides search capabilities as a convenience for testing, but it isn't supported for use in production. [Getting Started with Elasticsearch](./elasticsearch/getting-started-with-elasticsearch.md) describes production-level Elasticsearch setup. [Using the Sidecar or Embedded Elasticsearch](./elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md) describes the default Elasticsearch server (sidecar in 7.3 and embedded in 7.2) features and limitations.

```note::
   Liferay's `Solr <http://lucene.apache.org/solr>`_ support will receive one more update (to support Solr 8) before being deprecated. Though it can still be used, Solr is not bundled with Liferay and must be connected remotely, even for development and testing. To use Solr, see `Installing Solr <./solr/installing-solr.md>`_.
```

## Java Requirements

* Search engines require the `JAVA_HOME` environment variable. Set it on your search engine host.

* If you're using Liferay 7.2, Elasticsearch and Liferay must use the same Java version and distribution. Consult the [Elasticsearch compatibility matrix](https://www.elastic.co/support/matrix#matrix_jvm) and [Liferay's Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651) to learn more about supported JDK distributions and versions. You can specify this in Elasticsearch:

    ```properties
    [Elasticsearch Home]/bin/elasticsearch.in.sh`: `JAVA_HOME=/path/to/java`
    ```

The Java version and distribution requirement doesn't apply to Liferay 7.3 because the Elasticsearch 7 connector communicates via HTTP; there's no JVM level serialization. See the [Elastic's High-Level REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html) for details.

The same requirement doesn't apply to Solr either because the Solr connector also communicates via HTTP.

## Clustering the Search Engine

A production environment's search engine should be clustered for load management and optimal performance. Both Elasticsearch and Solr can be configured on multiple nodes in the remote environment.

* To configure a remote Elasticsearch server or cluster, see [Getting Started with Elasticsearch](./elasticsearch/getting-started-with-elasticsearch.md).

* To configure a remote Solr server or cluster, see [Installing Solr](./solr/installing-solr.md).

## Selecting a Search Engine Vendor and Version

Elasticsearch is the recommended search engine for search and indexing with Liferay. Solr is planned for deprecation and has [limitations](./solr/solr-limitations.md).

```important::
   Always refer to the `Search Engine Compatibility Matrix <https://help.liferay.com/hc/en-us/articles/360016511651>`_ to find the exact versions supported.
```

## What's Next 

[Installing Elasticsearch](./elasticsearch/getting-started-with-elasticsearch.md) is recommended. If you must use Solr (deprecated), see [Installing Solr](./solr/installing-solr.md). 
