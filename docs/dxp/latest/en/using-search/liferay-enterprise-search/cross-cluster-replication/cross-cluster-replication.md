# Cross-Cluster Replication

> **Liferay Enterprise Search (LES) Subscribers**

In a classic Liferay DXP/search engine installation, one Liferay DXP cluster talks to one Elasticsearch cluster, sending all of its read (execute a search query) and write (create a document) requests through one connection to the search engine cluster. This setup assumes all Elasticsearch cluster nodes are located in a single data center (though they can be in a different data center than the Liferay DXP servers).

Addressing concerns about data locality and disaster recovery, Elasticsearch released a [Cross-Cluster Replication (CCR)](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/xpack-ccr.html) feature that [LES subscribers](https://www.liferay.com/products/dxp/enterprise-search) can use with Liferay DXP, for Elasticsearch 7+ (refer to the [LES compatibility matrix](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search) for version compatibility details). With the LES CCR module you can achieve an alternate form of multi-data-center deployment; it does not allow distributing an Elasticsearch cluster's nodes over multiple data centers, but does allow configuring and connecting separate Elasticsearch clusters in each data center. 

The configuration assumes one cluster holding _leader_ indexes and at least one cluster holding _follower_ indexes replicated from the leader. Follower indexes are only used by Liferay DXP for reading data. Leader indexes are always used for writing, but can also be used for reading. 

![With Cross-Cluster Replication, disparate data centers can hold synchronized Elasticsearch clusters with Liferay DXP indexes.](./cross-cluster-replication/images/01.png)

The diagram above shows a minimal example of CCR. Each data center holds one or more DXP nodes and one Elasticsearch cluster. The US data center holds the leader indexes, where all the DXP nodes write. The Hungary data center holds the follower indexes that the local (Hungary) DXP nodes read. Data is replicated one way, from leader to follower.

Liferay DXP has long supported the idea of a distributed cluster, with nodes in disparate locations, via Wide Area Network (WAN) protocols. Liferay DXP's flexibility and Elasticsearch's support for Cross-Cluster Replication can support different system designs.

To set up Cross-Cluster Replication, you must

- [Purchase a LES subscription](https://www.liferay.com/products/dxp/enterprise-search)
- Install the CCR Module on all Liferay DXP nodes that read from the follower Elasticsearch indexes
- Choose the indexes to replicate from the leader cluster
- Configure the Elasticsearch Clusters
- Configure the Liferay DXP Cluster's Elasticsearch connections
- Enable and Configure Cross-Cluster Replication on the Liferay DXP nodes that read from the follower indexes

## Liferay DXP: Install the LES Cross-Cluster Replication Module

Liferay DXP nodes that read from a local cluster's follower indexes and write through a separate connection to the remote cluster's leader indexes must have the CCR module installed. For consistency and adaptability, it's best to install it on every node in the cluster. This module is available to download (as an LPKG file) with your LES subscription.

##  Liferay DXP: Decide Which Indexes to Replicate from the Remote Cluster

The default Liferay DXP 7.3 indexes in your installation approximate the list below (subject to change). The default global *Index Name Prefix* is `liferay-`: it can be changed in the Elasticsearch 7 connector configuration. `20101` is the generated `companyId` of a given Company in your database. It is displayed as Instance ID in the UI and represents a [Virtual Instance](../../../system-administration/configuring-liferay/virtual_instances.md).

| Index ID                                              | Index Type    | Index Purpose |
| ----------------------------------------------------- | ------------- | ------------- |
| liferay-0                                             | System Index  | Searching in the System Settings application |
| liferay-20101                                         | Company Index | Searching the indexed assets of the Liferay DXP Virtual Instance |
| liferay-20101-search-tuning-rankings                  | App Index     | Primary data storage for the Result Rankings application |
| liferay-20101-search-tuning-synonyms                  | App Index     | Primary data storage for the Synonym Sets application for the given virtual instance |
| liferay-20101-workflow-metrics-instances              | App Index     | Store data about Workflow Instances for the Workflow Metrics application |
| liferay-20101-workflow-metrics-nodes                  | App Index     | Store data about Workflow Nodes for the Workflow Metrics application |
| liferay-20101-workflow-metrics-processes              | App Index     | Store data about Workflow Processes for the Workflow Metrics application |
| liferay-20101-workflow-metrics-sla-instance-results   | App Index     | Storage for SLA results per Workflow Instance for the Workflow Metrics application |
| liferay-20101-workflow-metrics-sla-task-results       | App Index     | Storage for SLA results per Workflow Task for the Workflow Metrics application |
| liferay-20101-workflow-metrics-tokens                 | App Index     | Store data about Workflow Tokens for the Workflow Metrics application |
| liferay-20101-workflow-metrics-transitions            | App Index     | Store data about Workflow Transitions for the Workflow Metrics application |

```important::
   Liferay 7.2 index names are more complex, as patches have introduced changes to the index naming pattern. See `Multi-Tenant Index Names <../../getting-started/whats-new-in-search-for-73.md#multi-tenant-index-names>`__ for more information.
   Liferay 7.1 installations contain just company and system indexes.
```

```note::
   Liferay DXP provides APIs for creating and using (writing to and reading from) custom Elasticsearch indexes that remain completely under your control. See the `Developer Guide <../../developer_guide.html>`__ for information on using these APIs.
```

If you have a [Liferay Commerce](https://www.liferay.com/products/commerce) subscription and it is activated in your installation, you also have indexes like these:

| Index ID                                                     | Index Type    | Index Purpose |
| ------------------------------------------------------------ | ------------- | ------------- |
| liferay-20101-commerce-ml-forecast                           | App Index     | Machine Learning capabilities |
| liferay-20101-product-content-commerce-ml-recommendation     | App Index     | Recommendation services       |
| liferay-20101-product-interaction-commerce-ml-recommendation | App Index     | Recommendation services       |

Unless your setup reveals a very compelling reason not to, you should replicate all of the Liferay DXP indexes and all of your custom indexes into the follower Elasticsearch cluster. 

## Configure the Elasticsearch Clusters

Set up the Elasticsearch clusters, using versions supported with Liferay DXP that also support Cross-Cluster Replications. See the [LES compatibility matrix](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search) for details.

Make sure you [install the Elasticsearch plugins Liferay DXP needs and provide cluster names](../../installing-and-upgrading-a-search-engine/elasticsearch/installing-elasticsearch.html#configure-elasticsearch) to differentiate your follower and leader clusters.

CCR requires an Elasticsearch Platinum level license, but [LES customers](../../liferay_enterprise_search.html) already have this. If you're testing locally, start a [trial license](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/start-trial.html) on each cluster.

## Connect Liferay DXP to Elasticsearch

```important::
   Configure the Liferay Clustering behavior first. In the example provided in the tutorial, some configuration is provided for testing purposes. See the `clustering documentation <../../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability.md>`__ for more information on setting up a production cluster.
```

All Liferay DXP nodes must have two Elasticsearch configurations: production mode enabled and the remote Elasticsearch connection declared. Supporting this, the remote Elasticsearch connection must be configured in Elasticsearch Connections. Nodes that read from the follower Elasticsearch cluster must also have that additional connection defined. Provide the proper configuration values (via a `.config` file or in System Settings), then start (or restart) the DXP nodes. Make sure the nodes that read and write to the leader indexes are functioning properly.

Start the nodes and if you haven't yet, install the LES app on all nodes in the cluster.

## Enable and Configure Cross-Cluster Replication

Liferay DXP contains logic to complete the CCR setup for you, but it relies on enabling the CCR functionality in the System Settings UI, and not via [configuration file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md). At a minimum, the `readFromLocalClusters` property must be triggered from the UI. Once CCR is configured, all that's left is to verify the index replication and start searching.

The first time you enable CCR (after clicking _Update_ in the configuration---see [Configuring CCR in a Local Follower Data Center](./configuring-ccr-in-a-local-follower-data-center.md)), 
each entry in Local Cluster Configurations is processed. First, a [remote cluster](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-remote-clusters.html) is registered via the [Cluster Update Settings API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cluster-update-settings.html). For each index in the remote cluster (excluding indexes that start with a `.` or any defined in the Excluded Indexes setting), the [Create Follower API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-put-follow.html) is then invoked to set up the follower/leader relationship with the remote indexes. 

After editing an existing CCR configuration, or when disabling CCR, each entry that was previously saved in Local Cluster Configurations is processed. For each of its indexes, [following is paused](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-post-pause-follow.html), [the index is closed](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/indices-close.html#indices-close), [the leader is unfollowed](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-post-unfollow.html), and [the follower index is deleted](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/indices-delete-index.html). The remote cluster is then unregistered via the [Cluster Update Settings API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cluster-update-settings.html). If you're just disabling CCR, this is where processing ends. If editing the configuration, the existing Local Cluster Configurations entries continue to be processed just as if enabling CCR for the first time. For each entry, a [remote cluster](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-remote-clusters.html) is registered via the [Cluster Update Settings API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cluster-update-settings.html). For all indexes in each remote cluster (excluding indexes that start with a `.` or any defined in the Excluded Indexes setting) the [Create Follower API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-put-follow.html) is called to set up the follower/leader relationship with the remote indexes.

Now that you understand the steps, [here's a basic, specific use case](./configuring-an-example-ccr-installation-replicating-between-data-centers.md) and get started setting up a local example.
