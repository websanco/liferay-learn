# Upgrading Search Infrastructure

While upgrading Liferay, you must account for the search experience when using Elasticsearch. The exact steps depend on your existing search engine installation and Liferay version, but you should start by [backing up your existing indexes](./backing-up-elasticsearch.md).

* See the [Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651): it's always recommended to run the latest supported Elasticsearch version.
* Liferay Enterprise Search applications are now bundled with Liferay DXP. No additional installation steps are required. See [Activating Liferay Enterprise Search](../../../liferay-enterprise-search/activating-liferay-enterprise-search.md) for more information.
* If you're already on a supported Elasticsearch version, you may opt to continue using the existing Elasticsearch instance without updating it.
* Beginning in Liferay 7.4, the Search Tuning (Synonym Sets and Result Rankings) indexes are backed by database tables. If the search engine is connected to Liferay during Liferay's upgrade, the data is propagated to the database for you. If you are setting up a new Elasticsearch instance, you must [backup and restore the search tuning indexes](./backing-up-elasticsearch.md), then run a [Groovy script](#importing-the-search-tuning-indexes-in-7-4) to import the index data manually into the new database tables.

## Upgrade Steps

```{important}
[Back up the search indexes](./backing-up-elasticsearch.md) before proceeding with these steps.
```

1. Make sure your system is at least on the minimum supported Elasticsearch 7 version. If it's not, move to the [latest supported Elasticsearch](https://help.liferay.com/hc/en-us/articles/360016511651) by [upgrading.](upgrading-to-elasticsearch-7.md). It's possible to install a new Elasticsearch cluster and connect to the upgraded Liferay, but some data can be lost if the Elasticsearch cluster contained indexes used as primary storage, like the indexes for Liferay's Search Tuning features in Liferay DXP 7.2 and 7.3. See [Backing Up and Restoring Indexes Used for Primary Storage](backing-up-elasticsearch.md#backing-up-and-restoring-indexes-used-for-primary-storage) and [Importing the Search Tuning Indexes in 7.4.](#importing-the-search-tuning-indexes-in-7-4)

1. [Connect Liferay to Elasticsearch.](../connecting-to-elasticsearch.md)

1. [Configure security.](../securing-elasticsearch.md)

1. [Re-index the search indexes and spell check dictionaries.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md)

   Don't forget to re-index the [Workflow Metrics indexes.](../../../../process-automation/workflow/using-workflows/using-workflow-metrics.md#re-indexing-workflow-metrics)

1. [Upgrade Liferay.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics.md)

1. [Re-index the search indexes and spell check dictionaries.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md)

   Don't forget to re-index the [Workflow Metrics indexes.](../../../../process-automation/workflow/using-workflows/using-workflow-metrics.md#re-indexing-workflow-metrics)

1. [Test the search experience](#test-the-upgraded-search-experience) in the upgraded system to ensure everything is working as expected.

If you're on Liferay 7.4 and using the Liferay Enterprise Search applications, no installation steps are required, as they are [bundled with Liferay DXP](../../../liferay-enterprise-search/activating-liferay-enterprise-search.md). If you're on Liferay 7.2 or 7.3, read on to install the applications.

## Upgrading Liferay Enterprise Search on Liferay 7.2 and 7.3

Because LES and its apps are bundled with Liferay 7.4, these steps are only required if upgrading to Liferay 7.2 or 7.3. Follow the [basic upgrade steps](#upgrading-a-liferay-system-using-elasticsearch-7), then these optional steps: 

1. Install a Kibana version that matches the Elasticsearch version, if you are currently using [Kibana and Monitoring](../../../liferay-enterprise-search/monitoring-elasticsearch.md).

1. Install and configure the LES applications applicable to your setup and version. See the [LES Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search) for details.

## Test the Upgraded Search Experience

Manually test the upgraded search experience to ensure the features you depend on work as expected. If something is not working or is behaving differently than you expect, review the Breaking Changes documentation:

- [Liferay 7.4 Breaking Changes](./../../../../liferay-internals/reference/7-4-breaking-changes.md)
- [Liferay 7.3 Breaking Changes](./../../../../liferay-internals/reference/7-3-breaking-changes.md)
- [Liferay 7.2 Breaking Changes](./../../../../liferay-internals/reference/7-2-breaking-changes.md)

## LES Applications Renamed after Liferay 7.2

> **LES Subscribers**

These LES apps were renamed in the 7.3 life cycle to better reflect their functionality and to emphasize their identity as LES apps:

| Functionality | Old App Name | New App Name | 7.2 Configuration File | 7.3/7.4 Configuration File |
| ------------- | ------------ | ------------ |------------ | ------------ |
| Monitoring the Elasticsearch cluster | Liferay Connector to X-Pack Monitoring [Elastic Stack 6.x] | Liferay Enterprise Search Monitoring | `com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config` | `com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.MonitoringConfiguration.config` |
| Securing the Elasticsearch cluster | Liferay Connector to X-Pack Security [Elastic Stack 6.x] | Liferay Enterprise Search Security | No action required; this app is not available for DXP 7.4. Its features are integrated into the Elasticsearch 7 connector. |
| Using machine learning to optimize the search algorithm | Liferay Connector to Elasticsearch Learning to Rank | Liferay Enterprise Search Learning to Rank | No changes. |

The Liferay 7.3 and 7.4 widget and configuration names are identical.

If you're upgrading from Liferay 7.2, the renaming of apps and configurations has these upgrade impacts:
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

The following Liferay DXP Search Tuning indexes are in the Elasticsearch cluster: 

* `liferay-[companyId]-search-tuning-rankings`
* `liferay-[companyId]-search-tuning-synonyms`

If you were using the search tuning features in the pre-upgrade system, but the search tuning index documents are not present in the post-upgrade cluster, you must first [backup and restore the search tuning indexes](./backing-up-elasticsearch.md) from the pre-upgrade cluster to the post-upgrade cluster, then run a Groovy script to manually import the index data into the new database tables. This can happen if you are connecting to a new Elasticsearch cluster instead of upgrading the pre-upgrade cluster.

To run the Groovy import script,

1. Go to the scripting console. Navigate to the Script tab in Control Panel &rarr; Server Administration.

1. Run the following script to import the Result Rankings data into its database table:

   ```groovy
   import com.liferay.portal.instances.service.PortalInstancesLocalService;
   import com.liferay.portal.search.tuning.rankings.storage.RankingsDatabaseImporter;
   import com.liferay.registry.Registry;
   import com.liferay.registry.RegistryUtil;
   
   Registry registry = RegistryUtil.getRegistry();
   
   PortalInstancesLocalService portalInstancesLocalService = registry.getServices(PortalInstancesLocalService.class, null)[0];
   RankingsDatabaseImporter rankingsDatabaseImporter = registry.getServices(RankingsDatabaseImporter.class, null)[0];
   
   for (long companyId : portalInstancesLocalService.getCompanyIds()) {
   	rankingsDatabaseImporter.populateDatabase(companyId);
   }
   ```

1. Run the following script to import the Synonym Sets data into its database table:

   ```groovy
   import com.liferay.portal.instances.service.PortalInstancesLocalService;
   import com.liferay.portal.search.tuning.synonyms.storage.SynonymSetsDatabaseImporter;
   import com.liferay.registry.Registry;
   import com.liferay.registry.RegistryUtil;
   
   Registry registry = RegistryUtil.getRegistry();
   
   PortalInstancesLocalService portalInstancesLocalService = registry.getServices(PortalInstancesLocalService.class, null)[0];
   SynonymSetsDatabaseImporter synonymSetsDatabaseImporter = registry.getServices(SynonymSetsDatabaseImporter.class, null)[0];
   
   for (long companyId : portalInstancesLocalService.getCompanyIds()) {
   	synonymSetsDatabaseImporter.populateDatabase(companyId);
   }
   ```

1. Make sure you test the Synonym Sets and Result Rankings to ensure everything is working as expected.

## Additional Information

* [Upgrading Elasticsearch](../getting-started-with-elasticsearch.md)
* [Getting Started with Elasticsearch](../getting-started-with-elasticsearch.md)
* [Installing Elasticsearch](../installing-elasticsearch.md)
* [Connecting to Elasticsearch](../connecting-to-elasticsearch.md)
* [Securing Elasticsearch](../securing-elasticsearch.md)
* [Upgrading Liferay](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics.md)
