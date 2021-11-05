# Upgrading Search with Liferay

<!-- Tibor might want to change the title: https://github.com/lipusz/liferay-learn/pull/43#discussion_r743810985 -->

While upgrading Liferay, there are additional considerations to account for with the search experience. The exact steps depend on your existing search engine installation and Liferay version, but you'll always want to start by [backing up your existing indexes](./backing-up-elasticsearch.md).

* See the [Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651): it's always recommended to run the latest supported Elasticsearch version.
* Liferay Enterprise Search applications are now bundled with Liferay DXP. No additional installation steps are required. See [Activating Liferay Enterprise Search](../../../liferay-enterprise-search/activating-liferay-enterprise-search.md) for more information.
* If you're already on a supported Elasticsearch version, you may opt to continue using the existing Elasticsearch instance, without updating it.
* Beginning in Liferay 7.4, the Search Tuning (Synonym Sets and Result Rankings) indexes are backed by database tables. If the search engine is connected to Liferay during Liferay's upgrade, the data will be propagated to the database for you. If you are setting up a new Elasticsearch instance, you'll need to [backup and restore the search tuning indexes](./backing-up-elasticsearch.md), then run a [Groovy script](#importing-the-search-tuning-indexes-in-74) to manually import the index data into the new database tables.

## Upgrading Liferay's Search Infrastructure

```{important}
[Back up the search indexes](./backing-up-elasticsearch.md) before proceeding with these steps.
```

1. Make sure your system is at least on the minimum supported Elasticsearch 7 version. If it's not, move to the [latest supported Elasticsearch](https://help.liferay.com/hc/en-us/articles/360016511651) by [upgrading.](upgrading-to-elasticsearch-7.md) It's possible to install a new Elasticsearch cluster and connect to the upgraded Liferay, but some data can be lost if the Elasticsearch cluster contained indexes used as primary storage. See [Importing the Search Tuning Indexes in 7.4](#importing-the-search-tuning-indexes-in-7-4).

1. [Connect Liferay to Elasticsearch.](../connecting-to-elasticsearch.md)

1. [Configure security.](../securing-elasticsearch.md)

1. [Re-index the search indexes and spell check dictionaries.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md)

   Don't forget to re-index the [Workflow Metrics indexes.](../../../../process-automation/workflow/using-workflows/using-workflow-metrics.md#re-indexing-workflow-metrics)

1. [Upgrade Liferay.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md)

1. [Re-index the search indexes and spell check dictionaries.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md)

   Don't forget to re-index the [Workflow Metrics indexes.](../../../../process-automation/workflow/using-workflows/using-workflow-metrics.md#re-indexing-workflow-metrics)

1. [Test the search experience](#test-the-upgraded-search-experience) in the upgraded system to ensure everything is working as expected.

If you're on Liferay 7.4 and using the Liferay Enterprise Search applications, no installation steps are required, as they are [bundled with Liferay DXP](../../../liferay-enterprise-search/activating-liferay-enterprise-search.md). If you're on Liferay 7.2 or 7.3, read on to install the applications.

## Upgrading Liferay Enterprise Search on Liferay 7.2 and 7.3

Because LES and its apps are bundled with Liferay 7.4, these steps are only required if upgrading to Liferay 7.2 or 7.3. Follow the [basic upgrade steps](#upgrading-a-liferay-system-using-elasticsearch-7), then consider the following:

1. Install a Kibana version that matches the Elasticsearch version, if you are currently using [Kibana and Monitoring](../../../liferay-enterprise-search/monitoring-elasticsearch.md).

1. Install and configure the LES applications applicable to your setup and version. See the [LES Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651-Liferay-Enterprise-Search-Compatibility-Matrix#Liferay-Enterprise-Search) for details.


## Test the Upgraded Search Experience

Manually test the upgraded search experience to ensure the features you depend on work as expected. If something is not working or is behaving differently than you expect, review the Breaking Changes documentation:

- [Liferay 7.4 Breaking Changes](./../../../../liferay-internals/reference/7-4-breaking-changes.md)
- [Liferay 7.3 Breaking Changes](./../../../../liferay-internals/reference/7-3-breaking-changes.md)
- [Liferay 7.2 Breaking Changes](./../../../../liferay-internals/reference/7-2-breaking-changes.md)

## LES Applications Renamed after Liferay 7.2

> **LES Subscribers**

These LES apps were renamed in the 7.3 lifecycle to better reflect their functionality and to emphasize their identity as LES apps:

| Functionality | Old App Name | New App Name | 7.2 Configuration File | 7.3/7.4 Configuration File |
| ------------- | ------------ | ------------ |------------ | ------------ |
| Monitoring the Elasticsearch cluster | Liferay Connector to X-Pack Monitoring [Elastic Stack 6.x] | Liferay Enterprise Search Monitoring | `com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config` | `com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.MonitoringConfiguration.config` |
| Securing the Elasticsearch cluster | Liferay Connector to X-Pack Security [Elastic Stack 6.x] | Liferay Enterprise Search Security | No action required, this app is not available for DXP 7.4; features are integrated into the Elasticsearch 7 connector. |
| Using machine learning to optimize the search algorithm | Liferay Connector to Elasticsearch Learning to Rank | Liferay Enterprise Search Learning to Rank | No changes. |

The Liferay 7.3 and 7.4 widget and configuration names are identical.

If you're upgrading from Liferay 7.2, the renaming of apps and configurations has the following upgrade impacts:
1. The LES Monitoring widget is now named *Elasticsearch Monitoring*. During startup, a module upgrade step runs, renaming the app when _Liferay Enterprise Search Monitoring_ is deployed. No action is required.
1. The configuration file name changed from `com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config` to `com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.MonitoringConfiguration`. The properties are the same as before. During portal startup, a module upgrade step runs, renaming the configuration. No action is required.
1. The Kibana base path to the monitoring widget changed. You must change the original setting in `kibana.yml`:

   ```yaml
   server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"
   ```

   to

   ```yaml
   server.basePath: "/o/portal-search-elasticsearch-monitoring/monitoring-proxy"
   ```

## Importing the Search Tuning Indexes in 7.4

There are two search tuning indexes in the Liferay Elasticsearch cluster: 

* `liferay-[companyId]-search-tuning-rankings`
* `liferay-[companyId]-search-tuning-synonyms`

If you were using the search tuning applications in the pre-upgrade system, but the search tuning indexes are not present in the post-upgrade cluster, you must first [backup and restore the search tuning indexes](./backing-up-elasticsearch.md) from the pre-upgrade cluster to the post-upgrade cluster, then run a Groovy script to manually import the index data into the new database tables. This can happen if you are connecting to a new Elasticsearch cluster instead of upgrading the pre-upgrade cluster.

<!-- Provide the usage for the groovy script. -->

## What's Next 

Now that you know your upgrade path, start upgrading to use Liferay 7.4 with the latest [Elasticsearch](./upgrading-to-elasticsearch-7.md) search engine.

## Additional Information

* [Upgrading Elasticsearch](../getting-started-with-elasticsearch.md)
* [Getting Started with Elasticsearch](../getting-started-with-elasticsearch.md)
* [Installing Elasticsearch](../installing-elasticsearch.md)
* [Connecting to Elasticsearch](../connecting-to-elasticsearch.md)
* [Securing Elasticsearch](../securing-elasticsearch.md)
* [Upgrading Liferay](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md)
