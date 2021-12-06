# サンプルのCCRインストールの構成：データセンター間での複製

> **Liferay Enterprise Search（LES）サブスクライバー**

この例では、Liferay DXPのクラスター横断レプリケーションモジュールとElasticsearchを構成して、2つの接続（一方のElasticsearchクラスターから1つのLiferay DXPクラスターノードへの読み取り/書き込み接続と、もう一方のElasticsearchクラスターから2番目のLiferay DXPクラスターノードへの読み取り接続）を設定します。

![クラスター横断レプリケーションを使用すると、異なるデータセンターがLiferay DXPインデックスを使用して同期されたElasticsearchクラスターを保持できます。](./configuring-an-example-ccr-installation-replicating-between-data-centers/images/01.png)

```{important}
Differences in the configurations and procedure between Liferay DXP 7.2 and 7.3 are noted inline throughout these instructions.
```

`localhost`で2つのシングルノードElasticsearchクラスターを使用し、それぞれに同じインデックスのコピーを設定します。 これは、クラスター横断レプリケーションのデータの局所性とディザスタリカバリのメリットを享受するために構成できる最も単純なシナリオです。

バニラのLiferay DXPインストールには、[クラスター横断レプリケーション](./cross-cluster-replication.md#liferay-dxp-decide-which-indexes-to-replicate-from-the-remote-cluster)で提示されるインデックスが含まれています。 Liferay DXPで使用されるすべてのElasticsearchクラスター（この例では2つのクラスター）には、これらのインデックスが必要です。

ここで提供されるElasticsearch API呼び出しは、Kibanaの開発ツールコンソールにコピーして貼り付けることができ、別のKibanaインストールまたは[LES モニタリングウィジェット](../monitoring-elasticsearch.md)からアクセスできます。

```{note}
To use Kibana, remember that you have multiple Elasticsearch clusters (two single-node clusters in this example) running. The `elasticsearch.hosts: [ "http://localhost:<port>" ]` setting in Kibana's `kibana.yml` file must point to the correct port when managing the indexes and other configurations described below to avoid mixing the leader and the follower clusters. Here, it's assumed that your leader Elasticserach cluster node uses port `9200` and the follower uses port `9202`. 
```

## クラスターLiferay DXP

各Liferay DXPノードには、次のプロパティを持つ`Liferay Home/portal-ext.properties`ファイルが必要です。

``` properties
cluster.link.enabled=true
```

これは単純なクラスタリング構成です。 完全な構成については、[クラスタリング](../../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability.md)を参照してください。

## 必要なElasticsearchプラグインのインストール

必要なElasticsearch[プラグイン](../../installing-and-upgrading-a-search-engine/elasticsearch/installing-elasticsearch.md#install-elasticsearch)を必ずインストールしてください。

  - `analysis-icu`
  - `analysis-stempel`
  - `analysis-kuromoji`
  - `analysis-smartcn`

## セキュリティの前提条件：認証と暗号化を設定する

通信（TLS/SSL）を暗号化し、Liferay DXPとElasticsearchノード間のユーザー認証を有効にするには、[セキュリティを設定する](../../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)必要があります。

1.  ElasticsearchクラスターでX-Pack Securityを構成します。 ノード証明書が同じCAによって署名されており、リーダークラスターとフォロワークラスターのセキュリティ設定が一致していることを確認してください。

    ```{note}
    TLS/SSL must be enabled for the HTTP and Transport layers on the follower Elasticsearch cluster nodes. Liferay DXP connects to the follower cluster over HTTP to re-follow the company indexes after a full reindex is performed.
    ```

2.  DXPノードを設定します。

    Liferay DXP 7.3の場合、`ElasticsearchConfiguration.config`ファイルでElasticsearchの本番環境モード設定を構成してから、`ElasticsearchConnectionConfiguration-[ccr/remote].config`ファイルで接続を個別に構成します。

    Liferay DXP 7.2の場合、`ElasticsearchConfiguration.config`でリモート接続を構成し、`ElasticsearchConnectionConfiguration-ccr.config`ファイルで読み取り専用接続を構成します。

    各接続にセキュリティ設定を含めます。 詳細については、[Securing Elasticsearch](../../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)を参照してください。 [Configuring CCR in a Local Follower Data Center](./configuring-ccr-in-a-local-follower-data-center.md)では、ローカルDXPノードからの読み取り専用CCR接続に対するセキュリティの設定について説明しています。

完全な構成例は、[こちら](./ccr-basic-use-case-config-reference.md)で提供しています。

## クラスター横断レプリケーションモジュールのインストール

1.  [LESダウンロードページ](https://customer.liferay.com/downloads)から、Elasticsearch LPKGのLiferay DXPクラスター横断レプリケーションをダウンロードします。

2.  すべてのDXPノードに[LPKGをインストール](../../../system-administration/installing-and-managing-apps/installing-apps.md)します。

<!-- end list -->

```{tip}
Because you only use multiple Elasticsearch connections on Liferay DXP 7.2 with Cross-Cluster Replication, the Connections UI is only visible in the Search administration panel (Control Panel > Configuration > Search---under the Connections tab) if the Cross-Cluster Replication LPKG is deployed. On Liferay DXP 7.3, the Connections UI is always present.
```

前提条件のタスクがすべて完了し、クラスター横断レプリケーションモジュールがインストールされている場合は、[リモートデータセンターでサーバーを構成して](./configuring-ccr-in-a-remote-leader-data-center.md)続行します。
