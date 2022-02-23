# サンプルのCCRインストールの構成：データセンター間での複製

> **Liferay Enterprise Search（LES）サブスクライバー**

この例では、Liferay DXPのクラスター横断レプリケーションモジュールとElasticsearchを構成して、2つの接続（一方のElasticsearchクラスターから1つのLiferay DXPクラスターノードへの読み取り/書き込み接続と、もう一方のElasticsearchクラスターから2番目のLiferay DXPクラスターノードへの読み取り接続）を設定します。

![クラスター横断レプリケーションを使用すると、異なるデータセンターがLiferay DXPインデックスを使用して同期されたElasticsearchクラスターを保持できます。](./configuring-an-example-ccr-installation-replicating-between-data-centers/images/01.png)

```{important}
   Liferay DXP 7.1、7.2、7.3の間の設定や手順の違いは、この説明書の中でインラインで記載されています。
```

`localhost`で2つのシングルノードElasticsearchクラスターを使用し、それぞれに同じインデックスのコピーを設定します。 これは、クラスター横断レプリケーションのデータの局所性とディザスタリカバリのメリットを享受するために構成できる最も単純なシナリオです。

バニラのLiferay DXPインストールには、 [クラスター横断レプリケーション](./cross-cluster-replication.md#liferay-dxp-decide-which-indexes-to-replicate-from-the-remote-cluster) で提示されるインデックスが含まれています。 Liferay DXPで使用されるすべてのElasticsearchクラスター（この例では2つのクラスター）には、これらのインデックスが必要です。

ここで提供されるElasticsearch API呼び出しは、Kibanaの開発ツールコンソールにコピーして貼り付けることができ、別のKibanaインストールまたは[LES モニタリングウィジェット](../monitoring-elasticsearch.md)からアクセスできます。

```{note}
   Kibanaを使うには、複数のElasticsearchクラスタ（この例では2つのシングルノードクラスタ）が稼働していることを思い出してください。 Kibanaの ``kibana.yml`` ファイル内の ``elasticsearch.hosts:[ "http://localhost:<port>" ]Kibanaの ``kibana.yml`` ファイルの `` 設定は、リーダーとフォロワーのクラスタが混在しないように、後述のインデックスやその他の設定を管理する際に、正しいポートを指し示す必要があります。 ここでは、リーダーとなるElasticserachクラスタノードが9200番台のポートを使用し、フォロワーが9202番台のポートを使用していることを想定しています。 
```

<a name="クラスターliferay-dxp" />

## クラスターLiferay DXP

各Liferay DXPノードには、次のプロパティを持つ`Liferay Home/portal-ext.properties`ファイルが必要です。

```properties
cluster.link.enabled=true
```

これは単純なクラスタリング構成です。 完全な構成については、[クラスタリング](../../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability.md)を参照してください。

<a name="必要なelasticsearchプラグインのインストール" />

## 必要なElasticsearchプラグインのインストール

必要なElasticsearch [プラグイン](../../installing-and-upgrading-a-search-engine/elasticsearch/installing-elasticsearch.md#install-elasticsearch) を必ずインストールしてください。

- `analysis-icu`
- `analysis-stempel`
- `analysis-kuromoji`
- `analysis-smartcn`

<a name="セキュリティの前提条件認証と暗号化を設定する" />

## セキュリティの前提条件：認証と暗号化を設定する

通信（TLS/SSL）を暗号化し、Liferay DXPとElasticsearchノード間のユーザー認証を有効にするには、[セキュリティを設定する](../../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)必要があります。

1. ElasticsearchクラスターでX-Pack Securityを構成します。 ノード証明書が同じCAによって署名されており、リーダークラスターとフォロワークラスターのセキュリティ設定が一致していることを確認してください。

   ```{note}
      後続のElasticsearchクラスタノードのHTTPおよびTransportレイヤでTLS/SSLが有効になっている必要があります。 Liferay DXPはHTTP経由でフォロアクラスタに接続し、フルリインデックスが実行された後、企業インデックスを再フォローします。
   ```

1. DXPノードを設定します。

   Liferay DXP 7.3の場合、`ElasticsearchConfiguration.config`ファイルでElasticsearchの本番環境モード設定を構成してから、`ElasticsearchConnectionConfiguration-［ccr/remote］.config`ファイルで接続を個別に構成します。


   Liferay DXP 7.1および7.2の場合、 `ElasticsearchConfiguration.config` でリモート接続を、 `ElasticsearchConnectionConfiguration-ccr.config` でリードオンリー接続を設定します。

   各接続にセキュリティ設定を含めます。 詳細は、 [Elasticsearchの保護](../../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md) を参照してください。 [Configuring CCR in a Local Follower Data Center](./configuring-ccr-in-a-local-follower-data-center.md)では、ローカルDXPノードからの読み取り専用CCR接続に対するセキュリティの設定について説明しています。

完全な構成例は、[こちら](./ccr-basic-use-case-config-reference.md)で提供しています。

<a name="クラスター横断レプリケーションモジュールのインストール" />

## クラスター横断レプリケーションモジュールのインストール

1. [LESダウンロードページ](https://customer.liferay.com/downloads) から、Elasticsearch LPKGのLiferay DXPクラスター横断レプリケーションをダウンロードします。

1. すべてのDXPノードに[LPKGをインストール](../../../system-administration/installing-and-managing-apps/installing-apps/installing-apps.md)します。

```{tip}
   クラスター横断レプリケーションを使用するLiferayDXP 7.1および7.2では複数のElasticsearch接続のみを使用するため、つながりUIは、クロスクラスターの場合、レプリケーションLPKGがデプロイされている場合、
検索管理パネル（［コントロールパネル］> ［構成］> ［検索］ --- ［つながり］タブの下）にのみ表示されます。 Liferay DXP 7.3では、つながりUIが常に表示されています。
```

前提条件のタスクがすべて完了し、クラスター横断レプリケーションモジュールがインストールされている場合は、[リモートデータセンターでサーバーを構成して](./configuring-ccr-in-a-remote-leader-data-center.md)続行します。
