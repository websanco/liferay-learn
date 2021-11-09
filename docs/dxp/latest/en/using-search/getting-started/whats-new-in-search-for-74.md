# What's New in Search for 7.4?

## Elasticsearch Integration

### Elasticsearch 7 Support

The following information is valid as of the Liferay DXP 7.4 GA1 and Liferay CE 7.4 GA4 releases. Check the [Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651) for the latest information.

* Liferay 7.4 is compatible with Elasticsearch 7.15.x. 

* The minimum required version of Elasticsearch for Liferay 7.4 is 7.14.x. The client application in Liferay uses Elasticsearch 7.14.1.

* The [Sidecar Elasticsearch server](../installing-and-upgrading-a-search-engine/elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md) uses the open source distribution and is a slightly older version than that supported in production: Elasticsearch OSS 7.10.2.

![Elasticsearch 7.15 is supported to run with Liferay DXP 7.4 GA1.](./whats-new-in-search-for-74/images/03.png)
```{important}
Elasticsearch 6.x is not supported on Liferay CE/DXP 7.4.
```

## Search Infrastructure & Administration

### Re-Index Each Virtual Instance's Indexes 

Beginning in Liferay 7.4, Server Administrators can limit the scope of the re-index actions to specific [Virtual Instances](../../system-administration/configuring-liferay/virtual-instances/understanding-virtual-instances.md). To choose which instances the index actions apply to,

1. Open Global Menu &rarr; Control Panel &rarr; Search and click the Index Actions tab.
1. Use the Execution Scope tab to choose the instances to re-index.

   ![Select the instances to re-index.](./whats-new-in-search-for-74/images/01.png)

## Search Widgets

No updates?

## Search Tuning

<!-- Now backed by database tables -->

> **Subscribers**

### Search Tuning Data is Stored in the Database 

Beginning in DXP Liferay 7.4, the Result Rankings and Synonym Sets data is stored in the Liferay database, and a re-index action from the Search Administration panel can be used to populate each application's index with the proper data. In prior versions the indexes were used as primary data storage for these applications.

To learn about how this impacts the upgrade process, read [Upgrading Search Infrastructure](../installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-search-infrastructure.md) and [Backing Up Elasticsearch](../installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/backing-up-elasticsearch.md).

![Re-index the Search Tunings data just like you would other Liferay DXP assets.](./whats-new-in-search-for-74/images/02.png)

## Liferay Enterprise Search (LES)

> **[LES Subscribers](https://www.liferay.com/products/dxp/enterprise-search)**

### LES Installation is Simplified

Starting with Liferay DXP 7.4, the Liferay Enterprise Search (LES) applications are included with all Liferay DXP bundles and Docker containers. LES is enabled by default and requires no additional installation steps. However, a LES add-on subscription must be purchased to receive official Liferay Support and additional benefits. Maintenance and updates to LES applications are delivered with Liferay DXP updates.

![The LES apps, like Cross-Cluster Replication, are installed with Liferay DXP 7.4.](./whats-new-in-search-for-74/images/04.png)

## Development

<!-- Probably nothing to say here -->

## Upgrading to DXP 7.4

Upgrading from a previous DXP version to 7.4 requires no special considerations for most setups. If using Liferay Enterprise Search or Search Tuning, however, make sure you consider these points: 

1. The Search Tuning applications are now backed by database tables, whereas previous versions used Elasticsearch indexes for their data storage. See [Upgrading Search Infrastructure](../installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-search-infrastructure.md) for more information.
1. The Liferay Enterprise Search applications are bundled with Liferay DXP. When upgrading to 7.4 no additional installation steps are required.

## Related Topics

* [Upgrading Search Infrastructure](../installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-search-infrastructure.md)
* [7.4 Breaking Changes](../../liferay-internals/reference/7-4-breaking-changes.md)
