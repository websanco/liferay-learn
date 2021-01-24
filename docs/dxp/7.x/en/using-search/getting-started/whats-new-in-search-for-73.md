# What's New in Search for 7.3?

## Elasticsearch Integration

### Elasticsearch 7 Support

> Availability: Liferay CE 7.3 GA4+, Liferay DXP 7.3 GA1+

Liferay DXP 7.3 comes with out-of-the-box support for Elasticsearch 7. The minimum required version is 7.9. Refer to the [Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651) for detailed support information.

```important::
   Elasticsearch 6.x is not supported on Liferay CE/DXP 7.3.
```

### The Elasticsearch 7 Connector is Based on the REST Client

> Availability: Liferay CE 7.3 GA4+, Liferay DXP 7.3 GA1+

The Elasticsearch 7 connector bundled with DXP 7.3 uses [Elastic's Java REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-overview.html) to communicate with Elasticsearch over HTTP. This communication protocol does not require Java serialization between DXP and Elasticsearch, so Elasticsearch 7 nodes can now be run on a different JVM than Liferay DXP.

### X-Pack Security Features Integrated

> Availability: Liferay 7.3 CE GA4+, Liferay DXP 7.3 GA1+

The functionality of the _Liferay Enterprise Search Security_ (formerly _Liferay Connector to X-Pack Security_)  module is included in the _Liferay Connector to Elasticsearch 7_. All customers on CE/DXP 7.3 can connect securely to their Elasticsearch cluster without a [Liferay Enterprise Search (LES)](https://www.liferay.com/products/dxp/enterprise-search) subscription.

The [upgrade](../installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch.md) guide contains information on moving the encryption configuration into the Elasticsearch 7 connector.

### Multiple Elasticsearch Connections

> Availability: Liferay CE 7.3 GA4+, Liferay DXP 7.3 GA1+

Configure connections to multiple Elasticsearch clusters. In Liferay DXP 7.2 it was possible to query against any index (even non-Liferay indexes) in the Elasticsearch cluster that Liferay was connected to. It's now possible to query against indexes in non-Liferay Elasticsearch clusters. This feature can be used in conjunction with the Low Level Search Options and Search Results widgets to show results from third-party systems using Elasticsearch clusters. This functionality was added primarily to support the Liferay Enterprise Search (LES) [Cross-Cluster Replication](#cross-cluster-replication) feature.

### Embedded Mode Replaced with Sidecar

> Availability: Liferay CE 7.3 GA4+, Liferay DXP 7.3 GA1+

See the [Sidecar](#a-sidecar-elasticsearch-7-is-bundled) section under [Development](#development).

## Search Infrastructure & Administration

### Multi-Tenant Index Names

> Availability: Liferay 7.3 CE GA6+, Liferay DXP 7.3 GA1+, Liferay DXP 7.2 SP3+/FP8+

In a multi-tenant installation, a single Elasticsearch cluster holds the indexes of multiple Liferay CE/DXP deployments. The need for properly namespaced indexes is paramount. In 7.2 (prior to FP8/SP3), inconsistency in naming of indexes could arise, making multi-tenant installations impossible. In the latest 7.2 patch and in 7.3, all Liferay CE/DXP indexes (including those controlled by specific applications) have their prefix configured through the Elasticsearch 7 configuration entry in System Settings. Third party application indexes can now leverage the same API to ensure consistently namespaced indexes throughout the installation:

```bash
[indexNamePrefix]-[companyId]-[appName]
```

For example,

```
liferay-20096-search-tuning-rankings
```

If upgrading to 7.3 from DXP 7.2 SP2 or earlier, the new DXP indexes are created automatically; after a full reindex and verifying a successful upgrade, the old indexes can be deleted.

### Connections in Search Admin

> Availability: Liferay CE 7.3 GA1+, Liferay DXP 7.3 GA1+

View the status and health of Elasticsearch connections via the Control Panel's Search entry (found in the Configurations section). See

- The health of each connected Elasticsearch cluster
- The node names and versions within each cluster
- The Elasticsearch client versions

This replaces the information bar found on the top of the Index Actions page in prior versions.

### Standardized Index Document IDs

> Availability: Liferay CE 7.3 GA1+, Liferay DXP 7.3 GA1+

The UID (or `id`) of index documents now has 1-to-1 parity with the database rows of Liferay DXP entities.

## Search Widgets

### Customize Search Widgets Using Widget Templates

> Availability: Liferay CE 7.3 GA4+, Liferay DXP 7.3 GA1+

The Search widgets now support [Widget Templates]( ./../../site-building/displaying-content/customizing-widgets/styling-widgets-with-widget-templates.md) (previously known as Application Display Templates) for customizing the visual look and feel of each widget using Freemarker or Velocity templates. For example, the Search Results widget can be configured to display a card layout, and an asset's properties like `author` or `modified date` can be shown or hidden. Some default templates are provided out of the box for each supported search widget.

### Similar Results

> **Subscribers**

> Availability: Bundled with Liferay DXP 7.3 GA1+, installable on Liferay DXP 7.2 SP2+ via the [Similar Results](https://web.liferay.com/marketplace/-/mp/application/172465398) Marketplace app

The Similar Results widget displays a collection of assets similar to the main asset on a page. The widget determines the main asset display using the URL and leverages Elasticsearch's [more like this query](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-mlt-query.html) to find additional assets. The main asset can be displayed using a display widget (like the Blogs widget) or the Asset Publisher.

The first version of Similar Results supports Blogs, Message Boards, Documents, and Wikis. Support for additional asset types (both native and custom assets) is possible by implementing an [extension point](../developer-guide/writing-a-similar-results-contributor.md). The logic used to determine document similarity can be configured per Elasticsearch's More Like This [query parameters](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-mlt-query.html). 

See [Similar Results](../search-pages-and-widgets/similar-results.md) for details.

## Search Tuning

> **Subscribers**

### Search Tuning: Result Rankings

> Availability: Liferay DXP 7.2 SP1+, Liferay DXP 7.3 GA1+

Result Rankings are a graphical UI for custom-tuning the result relevancy for a given query. Result Rankings features three main capabilities to tune relevancy manually:

1. Results for a query can be pinned and ordered at the top of the list. If the desired document is not in the original result list, it can be added manually. This allows users to promote high-value results.
1. Results can be hidden from the result list. This capability can be used to remove results that are stale or irrelevant.
1. Aliases apply the same custom pinned and hidden results to alternate search queries. For example, if a result ranking is created with pinned and hidden results for the query "digital experience platform," adding the aliases "portal" and "dxp" apply the same pinned and hidden results.

### Search Tunings: Synonyms

> Availability: Liferay DXP 7.2 SP1+, Liferay DXP 7.3 GA1+

Synonyms relate queries with similar meaning, giving equivalent weight to the synonymous queries when searching. For example, the queries "mobile phone", "cell phone", and "hand phone" have equivalent meanings and can be used interchangeably. Defining a synonym set with these three queries shows users searching for "mobile phone" documents containing the words "cell phone" or "hand phone". See the [Synonyms documentation from Elastic](https://www.elastic.co/guide/en/elasticsearch/guide/current/synonyms.html).

## Liferay Enterprise Search (LES)

> **[LES Subscribers](https://www.liferay.com/products/dxp/enterprise-search)**

### Cross-Cluster Replication (LES)

> Availability: Liferay DXP 7.2 SP3+, Liferay DXP 7.3 GA1+ via the [Liferay Enterprise Search Cross-Cluster Replication]() application.

The Cross-Cluster Replication application replicates Elasticsearch clusters across remote data centers for disaster recovery (high availability) and geo-proximity performance optimization. It leverages Elasticsearch's [Cross-Cluster Replication](https://www.elastic.co/guide/en/elasticsearch/reference/7.9/xpack-ccr.html) feature which replicates search indexes across distributed clusters using an active-passive model. All write operations for all DXP nodes route to a single leader index, while each DXP cluster node can be configured to read from any follower index.

### Learning to Rank (LES)

> Availability: Liferay DXP 7.2 SP2+, Liferay DXP 7.3 GA1+, via the [Liferay Enterprise Search Learning to Rank](https://web.liferay.com/marketplace/-/mp/application/170666298) application.

Manually tuning search for optimal results is difficult. Optimizing your search algorithm for one specific query may unintentionally result in poorer results for hundreds of other queries.

Learning to Rank (LTR) tackles this challenge by applying a machine learning (ML) model to improving search results. The model is trained by 

- selecting "features" (a term for the search factors to consider when the model is trained---e.g., recency, geo-proximity, tags match)
- providing judgment lists (also known as "ground truth") curated either manually (e.g. search experts grading results for a query) or semi-automatically (e.g. measuring user click rate for a query).

Judgment lists are the source of truth that informs and guides the model training. When a user submits a search query, LTR takes the first _x_ number of results (by default the first 1,000 results) returned by the search engine, then re-scores and re-orders those results using the trained model before returning the list to the user.

### LES Applications Renamed

Though not explicitly linked to the Liferay CE/DXP 7.3 release, these apps were renamed to better reflect their functionality and to emphasize their identity as LES apps:

| Functionality | Old App Name | New App Name |
| ------------- | ------------ | ------------ |
| Monitoring the Elasticsearch cluster | Liferay Connector to X-Pack Monitoring [Elastic Stack 6.x] | Liferay Enterprise Search Monitoring |
| Securing the Elasticsearch cluster | Liferay Connector to X-Pack Security [Elastic Stack 6.x] | Liferay Enterprise Search Security |
| Using machine learning to optimize the search algorithm | Liferay Connector to Elasticsearch Learning to Rank | Liferay Enterprise Search Learning to Rank |
| Replicating indexes across remote data centers | NA (new app) | Liferay Enterprise Search Cross-Cluster Replication |

## Development

### New Index Settings Contributor Extension Point

> Availability: Liferay CE 7.3 GA6+, Liferay DXP 7.3 GA1+

An Elasticsearch-version agnostic `IndexSettingsContributor` was added ([src code here)](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/settings/IndexSettingsContributor.java):

* `com.liferay.portal.search.spi.settings.IndexSettingsContributor`
* `com.liferay.portal.search.spi.settings.IndexSettingsHelper.java`
* `com.liferay.portal.search.spi.settings.TypeMappingsHelper.java`

### A Sidecar Elasticsearch 7 is Bundled

> Availability: Liferay CE 7.3 GA4+, Liferay DXP 7.3 GA1+

The Open Source (OSS) version of Elasticsearch 7.9.0 (licensed under Apache 2.0) is bundled with the Liferay CE/DXP 7.3 Tomcat bundles and started simultaneously in a _sidecar_ JVM. This sidecar installation is referred to as _development mode_, and it replaces the previous _embedded_ mode (neither of which are supported for production installations). Sidecar has dedicated properties in the Elasticsearch 7 configuration in the System Settings. By default, it runs on HTTP port 9201. See [Using the Sidecar or Embedded Elasticsearch](../installing-and-upgrading-a-search-engine/elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md) for more information.

The Elasticsearch 7.9.0 OSS version is auto-downloaded at first startup. Please refer to manually installing a [sidecar server](../installing-and-upgrading-a-search-engine/elasticsearch/installing-the-elasticsearch-sidecar.md) if your deployment is not able to reach external sites to download resources.

## Upgrading to DXP 7.3

Upgrading from a previous DXP version to 7.3 requires some special steps due to the switch to the [REST client](#the-elasticsearch-7-connector-is-based-on-the-rest-client). Please consult the [upgrade documentation](../installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch.md) for important information to assist your upgrade journey.
