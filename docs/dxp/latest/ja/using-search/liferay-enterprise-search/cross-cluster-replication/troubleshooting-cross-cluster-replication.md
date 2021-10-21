# クラスター横断レプリケーションのトラブルシューティング

ここでは、CCRのセットアップ中に発生する既知の一般的な落とし穴と、一般的なトラブルシューティング手法について説明します。 さらに詳しいトラブルシューティングについては、[Elastic社のCCRドキュメント](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-overview.html)または [Elastic社のフォーラム](https://discuss.elastic.co/tag/ccr-cross-cluster-replication)を参照してください。

## インデックスレプリケーションの問題の調査

フォロワーElasticsearchクラスターへの読み取り操作が成功するかどうかは、リーダーのインデックスのレプリケーションに依存します。

レプリケーションの問題を診断するために、 `com.liferay.portal.search.elasticsearch7.internal.cr.CrossClusterReplicationHelperImpl`に対するINFOログレベルを追加します。 ログレベルは、[コントロールパネル]→[サーバ管理]→[ログレベル]で追加します。

## 接続要求/応答の検査

クラスター横断レプリケーションを有効にするには、Elasticsearchクラスターへの複数の接続を設定する必要があります。

接続の問題の診断を支援するために、`com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchConnectionManager`に対するINFOログレベルを追加します。 ログレベルは、[コントロールパネル]→[サーバ管理]→[ログレベル]で追加します。

## 再インデックス中の例外：`RetentionLeaseNotFoundException`および`IndexNotFoundException`

リーダーDXPノードでインデックスの再構築がトリガーされると、フォロワーElasticsearchノードは次のようなエラーをスローする場合があります。

    [2021-01-10T14:49:21,478][WARN ][o.e.x.c.a.ShardFollowNodeTask] [es-follower-node-1] shard follow task encounter non-retryable error
    org.elasticsearch.transport.RemoteTransportException: [es-leader-node-1][127.0.0.1:9300][indices:data/read/xpack/ccr/shard_changes]
    Caused by: org.elasticsearch.index.IndexNotFoundException: no such index
      at org.elasticsearch.cluster.routing.RoutingTable.shardRoutingTable(RoutingTable.java:119) ~[elasticsearch-6.8.6.jar:6.8.6]

および

    [2021-01-10T14:49:50,750][WARN ][o.e.x.c.a.ShardFollowTasksExecutor] [es-follower-node-1] [liferay-20101][0] background management of retention lease [LiferayElasticsearchCluster_FOLLOWER/liferay-20101/3a22HGCGS9iDl5rCbutNHg-following-leader/liferay-20101/lZThZJuhTLSaNYTSxmeX8A] failed while following
    org.elasticsearch.index.seqno.RetentionLeaseNotFoundException: retention lease with ID [LiferayElasticsearchCluster_FOLLOWER/liferay-20101/3a22HGCGS9iDl5rCbutNHg-following-leader/liferay-20101/lZThZJuhTLSaNYTSxmeX8A] not found
      at org.elasticsearch.index.seqno.ReplicationTracker.renewRetentionLease(ReplicationTracker.java:282) ~[elasticsearch-6.8.6.jar:6.8.6]

<https://www.elastic.co/blog/follow-the-leader-an-introduction-to-cross-cluster-replication-in-elasticsearch>から：

> フォロワーは、シャードの履歴保持リースで、リーダーの操作の履歴に、そのフォロワーが現在どこにいるのかをマークすることができます。 リーダーのシャードは、そのマーカーより下の操作はマージしても安全だが、そのマーカーより上の操作は、フォロワーがそれらをレプリケートする機会が得られるまで保持する必要があることを認識します。 これらのマーカーによって、フォロワーが一時的にオフラインになっても、リーダーはまだレプリケートされていない操作を保持することができます。 この履歴を保持するにはリーダー側に追加のストレージが必要なため、これらのマーカーは限られた期間のみ有効であり、その後マーカーは期限切れになり、リーダーのシャードは自由に履歴をマージできるようになります。 この期間の長さは、フォロワーがオフラインになったときに保持する追加ストレージの容量や、リーダーからリブートストラップしなくてはならなくなるまでのフォロワーのオフライン期間をどの程度許容するかによって調整できます。

## `ElasticsearchSecurityException` CCRを設定する場合

CCRの構成時に、次のエラーが発生する可能性があります。

``` bash
ElasticsearchSecurityException security_exception current license is non-compliant for [ccr]
```

[CCRには](https://www.elastic.co/subscriptions#scalability-&-resiliency)Platinum Elasticsearchライセンスが必要です。 LESサブスクライバーとして、Liferayから提供されたライセンスでCCRにアクセスできます。 ローカルでテストしている場合は、各クラスターで[トライアル](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/start-trial.html)を開始できます。

## ローカルDXPノードがフォロワーElasticsearchクラスターから読み取りを行わない

クラスター横断レプリケーションを使用するDXPクラスターでは、ローカルのフォロワーElasticsearchクラスターから読み取りを行うには各ローカルDXPノードをマッピングする必要があります。 たとえば、2つのローカルDXPノードがあり、フォロワー接続の`connectionId`が`ccr`である場合、それらをフォロワーElasticsearchクラスターと一致させるには、ローカルクラスター構成プロパティを次のような値で構成する必要があります。

``` properties
localhost:9080,ccr
localhost:9180,ccr
```

DXPノードを`localhost`にバインドしていない場合でも、内部クラスタリングコードはそれを使用している各ノードを識別し続けるため、このプロパティで`localhost`をホスト名にする必要があります。 `localhost`以外のホスト名を使用して内部で（CCR構成を含む）DXPノードを識別する場合は、各DXPノードで次の[ポータルプロパティ](./../../../installation-and-upgrades/reference/portal-properties.md)を設定する必要があります。

``` properties
portal.instance.protocol=http
portal.instance.inet.socket.address=myhostname:9080
```

これらのプロパティを使用すると、上記のローカルクラスター構成プロパティは次のようになります。

``` properties
myhostname:9080,ccr
myhostname:9180,ccr
```

## ステータスが赤のフォロワーElasticsearchクラスター

CCR接続を正常にセットアップし、ローカルDXPノードでCCRを有効にすると、フォロワークラスターが[赤のクラスターヘルスステータス](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cluster-health.html)になる場合があります。 これにより、フォロワーのElasticsearchノードのコンソールで次のようなエラーが発生する可能性があります。

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

これは、セットアップ手順全体で構成、再起動、およびインデックスの再構築を繰り返し行っている場合に発生する可能性があります。 このエラーが発生しているが、接続が適切に構成されていると確信できる場合は、フォロワーインデックスを削除してLiferayのシステム設定からCCRを再度有効にすることで、CCR機能を再度有効にします。

1.  すべてのフォロワーインデックスを削除します。 この作業は、Kibanaのインデックス管理UIで最も簡単に行うことができます。

2.  リーダーDXPノードから完全なインデックスの再構築を実行します。

3.  CCR構成を再度有効にするには、ローカルDXPノードで[システム設定]→[検索機能]→[クラスター横断レプリケーション]に移動します。 *[Read from Local Clusters]* の選択を解除し、*[アップデート]* をクリックしてモジュールを無効にしてから、*[Read from Local Clusters]* を選択し、もう一度*[アップデート]* をクリックして再度有効にします。

## Liferay 7.2：CCR LPKGとElasticsearchConnectionConfigurationファイルをデプロイした後、検索ができない

`ElasticsearchConnectionConfiguration-ccr.config`ファイルと同時にCCR LPKGファイルをデプロイした後に、以下のようなエラーがログに表示され、検索エンジン接続が切断される場合は、既知のバグ[LPS-127821](https://issues.liferay.com/browse/LPS-127821)が発生したことになります。 このバグを回避して検索エンジン接続を修正するには、Liferayを再起動するか、別のファイルサブネームを使用して構成を複製します（例： `-ccr2.config`。`connectionId`設定も更新します）。

``` bash
2021-02-11 22:08:45.402 ERROR [main][CCRElasticsearchConnection:93] bundle com.liferay.portal.search.elasticsearch7.impl:4.0.10 (207)[com.liferay.portal.search.elasticsearch7.internal.connection.CCRElasticsearchConnection(386)] : The activate method has thrown an exception 
java.lang.NullPointerException
    at com.liferay.portal.search.elasticsearch7.internal.connection.CCRElasticsearchConnection.loadRequiredDefaultConfigurations(CCRElasticsearchConnection.java:251)
        ...
```
