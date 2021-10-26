# Upgrading Search for Liferay 7.4

After upgrading Liferay, there are additional steps to upgrade the search experience. The exact steps depend on your existing search engine installation and Liferay version, but you'll always want to start by [backing up your existing indexes](./backing-up-elasticsearch.md).

```{important}
* See the [Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651): it's always recommended to run the latest supported Elasticsearch version.
* Liferay Enterprise Search is now bundled with Liferay DXP. No additional steps are required. See [Activating Liferay Enterprise Search](../../../liferay-enterprise-search/activating-liferay-enterprise-search.md) for more information.
```

## Search Upgrade Overview

<!-- I began to refresh this to understand what setups we want to cover: all 7.2- and 7.3-7.4 scenarios?-->
The list below just scratches the surface of the search upgrade picture: it doesn't cover more complicated scenarios (such as upgrading Liferay Enterprise Search modules). Find the scenario that matches your Liferay version, LES version (if using LES), and your current search engine stack. Use the *Upgrade Steps* column to guide the upgrade.

| Upgrading from Liferay Version [+ LES Version] | Upgrading From Search Engine Version | Upgrade Steps |
| :-------- | :---------------- | :-------------- |
| **Liferay 7.3** | Elasticsearch 7.9+ | 1. [Connect Liferay to a newer Elasticsearch.](../connecting-to-elasticsearch.md)<br><br>2. [Configure security.](../securing-elasticsearch.md)<br><br>3. [Upgrade Liferay.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md)<br><br>4. [Re-index search & spell check indexes.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md) |
| **Liferay 7.3 + LES 3.0** (*Cross-Cluster Replication*, *Monitoring*, *Learning to Rank*) | Elasticsearch 7.9+ | 1. Connect Liferay to a newer Elasticsearch.<br><br>2. Configure security.<br><br>3. Install Kibana if you are currently using *Kibana and Monitoring*.<br><br>4. Install and configure LES Monitoring if you are currently using Kibana and *Elasticsearch Monitoring/X-Pack Monitoring*.<br><br>5. [Upgrade Liferay.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md)<br><br>6. [Re-index search & spell check indexes.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md) |
| **Liferay 7.2** | Elasticsearch 7.9+ | 1. [Connect Liferay to Elasticsearch 7.](../connecting-to-elasticsearch.md)<br><br>2. [Configure security.](../securing-elasticsearch.md)<br><br>3. [Upgrade Liferay.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md)<br><br>4. [Re-index search & spell check indexes.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md) |
| **Liferay 7.2 + LES 3.0** (*Monitoring*, *Learning to Rank*) | Elasticsearch 7.9+ | 1. Connect Liferay to a newer Elasticsearch.<br><br>2. Configure security.<br><br>3. Install Kibana if you are currently using *Kibana and Monitoring*.<br><br>4. Install and configure LES Monitoring if you are currently using Kibana and *Elasticsearch Monitoring/X-Pack Monitoring*.<br><br>5. [Upgrade Liferay.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md)<br><br>6. [Re-index search & spell check indexes.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md) |
| **Liferay 7.2** | Elasticsearch 7.3.x-7.8.x | 1. [Upgrade to the newest supported Elasticsearch version.](upgrading-to-elasticsearch-7.md)<br><br>2. Follow [Common Upgrade Steps](#common-upgrade-steps) |
| **Liferay 7.2 + LES 2.0** (*Monitoring*, *Learning to Rank*) | Elasticsearch 7.3.x-7.8.x | 1. [Upgrade to the newest supported Elasticsearch.](upgrading-to-elasticsearch-7.md)<br><br>2. Follow [LES Upgrade Steps](#les-upgrade-steps) |
| **Liferay 7.2** | Elasticsearch 6.x | 1. [Upgrade to the newest supported Elasticsearch version.](upgrading-to-elasticsearch-7.md)<br><br>2. Follow [Common Upgrade Steps](#common-upgrade-steps) |
| **Liferay 7.2 + LES 2.0** (*Security*, *Monitoring*, *Learning to Rank*) | Elasticsearch 6.x | 1. [Upgrade to the newest supported Elasticsearch version.](upgrading-to-elasticsearch-7.md)<br><br>2. Follow [LES Upgrade Steps](#les-upgrade-steps) |
| **Liferay 7.1** | Elasticsearch 6.x | 1. [Upgrade to the newest supported Elasticsearch.](upgrading-to-elasticsearch-7.md)<br><br>2. Follow [Common Upgrade Steps](#common-upgrade-steps) |
| **Liferay 7.1 + LES 2.0** (*Security*, *Monitoring*, *Learning to Rank*) | Elasticsearch 6.x | 1. [Upgrade to the newest supported Elasticsearch.](upgrading-to-elasticsearch-7.md)<br><br>2. Follow [LES Upgrade Steps](#les-upgrade-steps) |
| **Liferay 7.0** | Elasticsearch 6.x | 1. [Upgrade to the newest supported Elasticsearch.](upgrading-to-elasticsearch-7.md)<br><br>2. Follow [Common Upgrade Steps](#common-upgrade-steps) |
| **Liferay 7.0** | Elasticsearch 2.x | 1. [Install the newest supported Elasticsearch.](../installing-elasticsearch.md)<br><br>2. Follow [Common Upgrade Steps](#common-upgrade-steps) |
| **Liferay 7.2, 7.1, 7.0** | Solr (any version) | **Switch to Elasticsearch:**<br><br>1. [Install the newest supported Elasticsearch.](../installing-elasticsearch.md)<br><br>2. Follow [Common Upgrade Steps](#common-upgrade-steps) for configuring Liferay 7.4 with Elasticsearch<br> or follow [LES Upgrade Steps](#les-upgrade-steps) for Liferay 7.4 + Liferay Enterprise Search (LES) |

## Common Upgrade Steps

<!-- Where, if anywhere, does search tuning fit into this? Should we cover how synonym sets and rankings can now be re-indexed? I'll add a few Synonym Sets and Result Rankings to make sure it's working as expected --> 

```{important}
[Back up the search indexes](./backing-up-elasticsearch.md) before proceeding with these steps.
```

Upgrade scenarios for systems not including LES apps include these steps:

1. Move to the latest supported Elasticsearch by [upgrading Elasticsearch.](upgrading-to-elasticsearch-7.md)

1. [Connect Liferay to Elasticsearch.](../connecting-to-elasticsearch.md)

1. [Configure security.](../securing-elasticsearch.md)

1. [Upgrade Liferay.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md)

1. [Re-index the search and spell check indexes.](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md)

1. [Re-index the Workflow Metrics indexes.](../../../../process-automation/workflow/using-workflows/using-workflow-metrics.md#re-indexing-workflow-metrics)

1. Test the search experience in the upgraded system to ensure everything is working as expected.

## LES Upgrade Steps

<!-- This section might be totally unnecessary now. Maybe we should just say two things: Install Liferay 7.4 and configure the LES apps accordingly, then review the Breaking changes document -->

```{important}
[Back up the search indexes](./backing-up-elasticsearch.md) before proceeding with these steps.
```

Systems using LES apps must follow these additional steps:

1. Install Kibana 7.9+ if you are currently using Kibana and Monitoring.

1. Install and configure LES Monitoring app if you are currently using Kibana and Elasticsearch Monitoring/X-Pack Monitoring.

1. Install LES Learning to Rank app if you are currently using this in your environment.


## Test the Upgraded Search Experience

Manually test the upgraded search experience to ensure the features you depend on work as expected. If something is not working or is behaving differently than you expect, review [Liferay's Breaking Changes](./../../../../liferay-internals/reference/7-3-breaking-changes.md).

## LES Applications Renamed

<!-- Might need to update this section to account for a 7.2-7.4 upgrade -->

> **LES Subscribers**

Though not explicitly linked to the Liferay CE/DXP 7.4 release, these apps were renamed to better reflect their functionality and to emphasize their identity as LES apps:

| Functionality | Old App Name | New App Name | 7.2 Configuration File | 7.4 Configuration File |
| ------------- | ------------ | ------------ |------------ | ------------ |
| Monitoring the Elasticsearch cluster | Liferay Connector to X-Pack Monitoring [Elastic Stack 6.x] | Liferay Enterprise Search Monitoring | `com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config` | `com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.MonitoringConfiguration.config` |
| Securing the Elasticsearch cluster | Liferay Connector to X-Pack Security [Elastic Stack 6.x] | Liferay Enterprise Search Security | No action required, this app is not available for DXP 7.4; features are integrated into the Elasticsearch 7 connector. |
| Using machine learning to optimize the search algorithm | Liferay Connector to Elasticsearch Learning to Rank | Liferay Enterprise Search Learning to Rank | No changes. |

The Liferay 7.3 and 7.4 widget and configuration names are identical.

If you're upgrading from Liferay 7.2, the renaming of apps and configurations has the following upgrade impacts:
1. The LES Monitoring widget is now named *Elasticsearch Monitoring*. During startup, a module upgrade step runs, renaming the app when _Liferay Enterprise Search Monitoring_ for DXP 7.3 is deployed. No action is required.
1. The configuration file name changed from `com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config` to `com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.MonitoringConfiguration`. The properties are the same as before. During portal startup, a module upgrade step runs, renaming the configuration. No action is required.
1. The Kibana base path to the monitoring widget has changed. You must change the original setting in `kibana.yml`:

   ```yaml
   server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"
   ```

   to

   ```yaml
   server.basePath: "/o/portal-search-elasticsearch-monitoring/monitoring-proxy"
   ```

Liferay 7.4 supports only Elasticsearch 7.14+ via the out-of-the-box Liferay Connector to Elasticsearch 7. The matrix of pre-upgrade stacks you are migrating from is more numerous, and it's important to understand the high-level steps required to navigate safely from your existing stack to the Liferay 7.4 stack. 

## What's Next 

Now that you know your upgrade path, start upgrading to use Liferay 7.4 with the latest [Elasticsearch](./upgrading-to-elasticsearch-7.md) search engine.

## Additional Information

* [Upgrading Elasticsearch](../getting-started-with-elasticsearch.md)
* [Getting Started with Elasticsearch](../getting-started-with-elasticsearch.md)
* [Installing Elasticsearch](../installing-elasticsearch.md)
* [Connecting to Elasticsearch](../connecting-to-elasticsearch.md)
* [Securing Elasticsearch](../securing-elasticsearch.md)
* [Upgrading Liferay](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md)
