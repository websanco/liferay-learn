# Troubleshooting Cross-Cluster Replication

Known common pitfalls encountered during the CCR setup are covered here, as well as general troubleshooting techniques. For further troubleshooting, look at [Elastic's CCR documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-overview.html) or visit [Elastic's forum](https://discuss.elastic.co/tag/ccr-cross-cluster-replication).

## Investigating Index Replication Issues

Successful read operations to the follower Elasticsearch cluster depend on replication of the leader's indexes.

To aid diagnosing replication problems, add an INFO log level for `com.liferay.portal.search.elasticsearch7.internal.ccr.CrossClusterReplicationHelperImpl`. Log Levels are added in Control Panel &rarr; Server Administration &rarr; Log Levels.

## Inspecting Connection Request/Response

Enabling Cross-Cluster Replication requires setting up multiple connections to Elasticsearch clusters.

To aid diagnosing connection issues, add an INFO log level for `com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchConnectionManager`. Log Levels are added in Control Panel &rarr; Server Administration &rarr; Log Levels.

## Exceptions During Reindex: `RetentionLeaseNotFoundException` and `IndexNotFoundException`

When a reindex is triggered on the Leader DXP node, the Follower Elasticsearch node may throw errors like this:

```
[2021-01-10T14:49:21,478][WARN ][o.e.x.c.a.ShardFollowNodeTask] [es-follower-node-1] shard follow task encounter non-retryable error
org.elasticsearch.transport.RemoteTransportException: [es-leader-node-1][127.0.0.1:9300][indices:data/read/xpack/ccr/shard_changes]
Caused by: org.elasticsearch.index.IndexNotFoundException: no such index
  at org.elasticsearch.cluster.routing.RoutingTable.shardRoutingTable(RoutingTable.java:119) ~[elasticsearch-6.8.6.jar:6.8.6]
```

and this:

```
[2021-01-10T14:49:50,750][WARN ][o.e.x.c.a.ShardFollowTasksExecutor] [es-follower-node-1] [liferay-20101][0] background management of retention lease [LiferayElasticsearchCluster_FOLLOWER/liferay-20101/3a22HGCGS9iDl5rCbutNHg-following-leader/liferay-20101/lZThZJuhTLSaNYTSxmeX8A] failed while following
org.elasticsearch.index.seqno.RetentionLeaseNotFoundException: retention lease with ID [LiferayElasticsearchCluster_FOLLOWER/liferay-20101/3a22HGCGS9iDl5rCbutNHg-following-leader/liferay-20101/lZThZJuhTLSaNYTSxmeX8A] not found
  at org.elasticsearch.index.seqno.ReplicationTracker.renewRetentionLease(ReplicationTracker.java:282) ~[elasticsearch-6.8.6.jar:6.8.6]
```

From <https://www.elastic.co/blog/follow-the-leader-an-introduction-to-cross-cluster-replication-in-elasticsearch>:

> With a shard history retention lease, a follower can mark in the history of operations on the leader where in history that follower currently is. The leader shards know that operations below that marker are safe to be merged away, but any operations above that marker must be retained for until the follower has had the opportunity to replicate them. These markers ensure that if a follower goes offline temporarily, the leader will retain operations that have not yet been replicated. Since retaining this history requires additional storage on the leader, these markers are only valid for a limited period after which the marker will expire and the leader shards will be free to merge away history. You can tune the length of this period based on how much additional storage you are willing to retain in case a follower goes offline, and how long you’re willing to accept a follower being offline before it would otherwise have to be re-bootstrapped from the leader.

## `ElasticsearchSecurityException` When Setting Up CCR

You may run into the following error when configuring CCR:

```bash
ElasticsearchSecurityException security_exception current license is non-compliant for [ccr]
```

[CCR requires](https://www.elastic.co/subscriptions#scalability-&-resiliency) a Platinum Elasticsearch license. As a LES subscriber you have access to CCR with the license provided to you by Liferay. If you're testing locally, you can start a [trial](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/start-trial.html) on each cluster.

## Local DXP Node Doesn't Read from Follower Elasticsearch Cluster

In a DXP cluster using Cross-Cluster Replication, each local DXP node must be mapped to read from the local follower Elasticsearch cluster. For example, if you have two local DXP nodes and the `connectionId` of your follower connection is `ccr`, to match them with the follower Elasticsearch cluster, the Local Cluster Configurations property should be configured with values like this:

```properties
localhost:9080,ccr
localhost:9180,ccr
```

Even if you're not binding the DXP nodes to `localhost`, the internal clustering code continues to identify each node using it, so `localhost` should be the hostname in this property. If you want to use a hostname other than `localhost` to identify DXP nodes internally (including in the CCR configuration) you must set the following [portal properties](./../../../installation-and-upgrades/reference/portal-properties.md) on each DXP node:

```properties
portal.instance.protocol=http
portal.instance.inet.socket.address=myhostname:9080
```

With these properties, the above Local Cluster Configurations property is

```properties
myhostname:9080,ccr
myhostname:9180,ccr
```

## Follower Elasticsearch Cluster with Red Status

The follower cluster may go to [red cluster health status](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cluster-health.html) after you successfully set up a CCR connection and enable CCR on the local DXP node. This can result in errors like this in the follower Elasticsearch node's console:

```
[2021-01-08T15:49:25,405][INFO ][o.e.x.c.a.ShardFollowTasksExecutor] [es-follower-node1] [liferay-0][0] Starting to track leader shard [liferay-0][0]
[2021-01-08T15:49:25,440][INFO ][o.e.x.c.a.ShardFollowNodeTask] [es-follower-node1] [liferay-0][0] following leader shard [liferay-0][0], follower global checkpoint=[-1], mapping version=[3], settings version=[2], aliases version=[1]
[2021-01-08T15:49:25,575][WARN ][o.e.i.c.IndicesClusterStateService] [es-follower-node1] [liferay-20097][0] marking and sending shard failed due to [failed recovery]
org.elasticsearch.indices.recovery.RecoveryFailedException: [liferay-20097][0]: Recovery failed on {es-follower-node1}{SRkirdciSGaPph5XFrWUJA}{wgPq7Q4mRPG83hQEYALRMA}{127.0.0.1}{127.0.0.1:9301}{dilmrt}{ml.machine_memory=33552576512, xpack.installed=true, transform.node=true, ml.max_open_jobs=20}
	at org.elasticsearch.index.shard.IndexShard.lambda$executeRecovery$21(IndexShard.java:2665) [elasticsearch-7.9.3.jar:7.9.3]
(...)
Caused by: org.elasticsearch.index.shard.IndexShardRecoveryException: failed recovery
	... 27 more
Caused by: org.elasticsearch.index.snapshots.IndexShardRestoreFailedException: restore failed
	... 25 more
Caused by: org.elasticsearch.index.snapshots.IndexShardRestoreFailedException: failed to restore snapshot [_latest_/_latest_]
	... 23 more
(...)
[2021-01-08T15:49:25,592][INFO ][o.e.c.r.a.AllocationService] [es-follower-node1] Cluster health status changed from [YELLOW] to [RED] (reason: [shards failed [[liferay-20097][0]]]).
```

This may happen if you've been configuring, restarting, and re-indexing repeatedly throughout the setting up procedure. If you see this happen and you are confident your connections are configured properly, re-enable the CCR functionality by deleting the follower indexes, then re-enabling CCR from Liferay's System Settings:

1. Delete all the follower indexes. This is most conveniently carried out in Kibana's Index Management UI.

1. Perform a full reindex from the Leader DXP node.

1. To re-enable the CCR configuration, go to System Settings &rarr; Search &rarr; Cross-Cluster Replication on the Local DXP node. De-select _Read from Local Clusters_ and click _Update_ to disable the module, then select _Read from Local Clusters_ and click _Update_ again to re-enable it.
