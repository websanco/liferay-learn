# クラスター横断レプリケーション

> **Liferay Enterprise Search（LES）サブスクライバー**

従来のLiferay DXP/検索エンジンのインストールでは、1つのLiferay DXPクラスターが1つのElasticsearchクラスターと通信し、検索エンジンクラスターへの1つの接続を介して、すべての読み取り（検索クエリの実行）および書き込み（ドキュメントの作成）要求を送信します。 この設定では、すべてのElasticsearchクラスターノードが単一のデータセンターに配置されていることを前提としています（ただし、Liferay DXPサーバーとは異なるデータセンターに配置することもできます）。

Elasticsearchは、データの局所性とディザスタリカバリに関する懸念に対処するために、[LESサブスクライバー](https://www.liferay.com/products/dxp/enterprise-search)がElasticsearch 7以降でLiferay DXPで使用できる[クラスター横断レプリケーション（CCR）](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/xpack-ccr.html)機能をリリースしました（バージョン互換性の詳細については、[LES互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search)を参照してください）。 LES CCRモジュールを使用すると、別の形のマルチデータセンター展開が可能です。 Elasticsearchクラスターのノードを複数のデータセンターに分散することはできませんが、各データセンターで個別のElasticsearchクラスターを構成して接続することはできます。

この構成では、*リーダー*インデックスを保持する1つのクラスターと、リーダーからレプリケートされた*フォロワー*インデックスを保持する少なくとも1つのクラスターを想定しています。 フォロワーインデックスは、Liferay DXPがデータを読み取るためにのみ使用されます。 リーダーインデックスは常に書き込みに使用されますが、読み取りにも使用できます。

![クラスター横断レプリケーションを使用すると、異なるデータセンターがLiferay DXPインデックスを使用して同期されたElasticsearchクラスターを保持できます。](./cross-cluster-replication/images/01.png)

上の図は、CCRの最小限の例を示しています。 各データセンターには、1つ以上のDXPノードと1つのElasticsearchクラスターがあります。 米国のデータセンターには、すべてのDXPノードが書き込みを行うリーダーインデックスが保持されています。 ハンガリーのデータセンターには、ローカル（ハンガリー）のDXPノードが読み取りを行うフォロワーインデックスが保持されています。 データは、リーダーからフォロワーへの一方向にレプリケートされます。

Liferay DXPは、ワイドエリアネットワーク（WAN）プロトコルを介して、ノードが異なる場所にある分散クラスターのアイデアを長い間サポートしてきました。 Liferay DXPの柔軟性とElasticsearchのクラスター横断レプリケーションのサポートにより、さまざまなシステム設計をサポートできます。

クラスター横断レプリケーションを設定するには、次のことを行う必要があります

  - [LESサブスクリプションを購入する](https://www.liferay.com/products/dxp/enterprise-search)
  - フォロワーのElasticsearchインデックスから読み取りを行うすべてのLiferay DXPノードにCCRモジュールをインストールする
  - リーダークラスターからレプリケートするインデックスを選択する
  - Elasticsearchクラスターを構成する
  - Liferay DXPクラスターのElasticsearch接続を設定する
  - フォロワーインデックスから読み取りを行うLiferay DXPノードでクラスター横断レプリケーションを有効にして設定する

## Liferay DXP：LESクラスター横断レプリケーションモジュールのインストール

ローカルクラスターのフォロワーインデックスから読み取りを行い、リモートクラスターのリーダーインデックスへの個別の接続を介して書き込みを行うLiferay DXPノードには、CCRモジュールがインストールされている必要があります。 一貫性と適応性のために、クラスター内のすべてのノードにインストールするのが最善です。 このモジュールは、LESサブスクリプションとともに（LPKGファイルとして）ダウンロードできます。

## Liferay DXP：リモートクラスターからレプリケートするインデックスを決定する

インストールされているデフォルトのLiferay DXP 7.3インデックスは、以下のリストに近似しています（変更される可能性があります）。 デフォルトのグローバル*インデックス名接頭辞*は`liferay-`です。これはElasticsearch 7コネクタ設定で変更できます。 `20101`は、データベース内の特定の会社の生成された`companyId`です。  UIにインスタンスIDとして表示され、[仮想インスタンス](../../../system-administration/configuring-liferay/virtual_instances.rst)を表します。

| インデックスID                                            | インデックスタイプ  | インデックスの目的                                     |
| --------------------------------------------------- | ---------- | --------------------------------------------- |
| liferay-0                                           | システムインデックス | システム設定アプリケーションでの検索                            |
| liferay-20101                                       | 会社インデックス   | Liferay DXP仮想インスタンスのインデックス付きアセットの検索           |
| liferay-20101-search-tuning-rankings                | アプリインデックス  | 結果ランキングアプリケーションのプライマリデータストレージ                 |
| liferay-20101-search-tuning-synonyms                | アプリインデックス  | 特定の仮想インスタンスの同義語セットアプリケーションのプライマリデータストレージ      |
| liferay-20101-workflow-metrics-instances            | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフローインスタンスに関するデータを保存する   |
| liferay-20101-workflow-metrics-nodes                | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフローノードに関するデータを保存する      |
| liferay-20101-workflow-metrics-processes            | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフロープロセスに関するデータを保存する     |
| liferay-20101-workflow-metrics-sla-instance-results | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフローインスタンスごとのSLA結果のストレージ |
| liferay-20101-workflow-metrics-sla-task-results     | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフロー タスクごとのSLA結果のストレージ   |
| liferay-20101-workflow-metrics-tokens               | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフロートークンに関するデータを保存する     |
| liferay-20101-workflow-metrics-transitions          | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフロートランジションに関するデータを保存する  |

```{important}
Liferay 7.2 index names are more complex, as patches have introduced changes to the index naming pattern. See [Multi-Tenant Index Names](../../getting-started/whats-new-in-search-for-73.md#multi-tenant-index-names)_ for more information.
```

```{note}
Liferay DXP provides APIs for creating and using (writing to and reading from) custom Elasticsearch indexes that remain completely under your control. See the [Developer Guide](../../developer-guide.html)_ for information on using these APIs.
```

[Liferay Commerce](https://www.liferay.com/products/commerce)サブスクリプションがあり、インストールでアクティブ化されている場合は、次のようなインデックスもあります。

| インデックスID                                                     | インデックスタイプ | インデックスの目的     |
| ------------------------------------------------------------ | --------- | ------------- |
| liferay-20101-commerce-ml-forecast                           | アプリインデックス | 機械学習機能        |
| liferay-20101-product-content-commerce-ml-recommendation     | アプリインデックス | レコメンデーションサービス |
| liferay-20101-product-interaction-commerce-ml-recommendation | アプリインデックス | レコメンデーションサービス |

特段の理由がない限り、すべてのLiferay DXPインデックスとすべてのカスタムインデックスをフォロワーのElasticsearchクラスターにレプリケートする必要があります。

## Elasticsearchクラスターを構成する

クラスター横断レプリケーションにも対応しているLiferay DXPでサポートされているバージョンを使用して、Elasticsearchクラスターをセットアップします。 詳細については、[LES互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search)を参照してください。

[Liferay DXPが必要とするElasticsearchプラグインをインストールし、フォロワークラスターとリーダークラスターを区別するためのクラスター名](../../installing-and-upgrading-a-search-engine/elasticsearch/installing-elasticsearch.html#configure-elasticsearch)を指定してください。

CCRにはElasticsearch Platinumレベルのライセンスが必要ですが、[LESのお客様](../../liferay-enterprise-search.html)はすでに所有しています。 ローカルでテストしている場合は、各クラスターで[トライアルライセンス](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/start-trial.html)を開始してください。

## Liferay DXPをElasticsearchに接続する

```{important}
Configure the Liferay Clustering behavior first. In the example provided in the tutorial, some configuration is provided for testing purposes. See the [clustering documentation](../../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability.md)_ for more information on setting up a production cluster.
```

すべてのLiferay DXPノードには、2つのElasticsearch構成が必要です。本番モードを有効にし、リモートElasticsearch接続を宣言します。 これに対応するために、リモートElasticsearch接続をElasticsearch接続で設定する必要があります。 フォロワーのElasticsearchクラスターから読み取りを行うノードにも、追加の接続を定義する必要があります。 （`.config`ファイルまたはシステム設定で）適切な構成値を指定してから、DXPノードを起動（または再起動）します。 リーダーインデックスの読み取りと書き込みを行うノードが正しく機能していることを確認します。

ノードを起動し、クラスター内のすべてのノードにLESアプリをインストールします（まだ起動していない場合）。

## クラスター横断レプリケーションを有効にして構成する

Liferay DXPには、CCRセットアップを完了するためのロジックが含まれていますが、[構成ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)ではなく、システム設定UIでCCR機能を有効にすることに依存しています。 最低でも、`readFromLocalClusters`プロパティがUIからトリガーされなければなりません。 CCRを構成したら、あとはインデックスのレプリケーションを確認して検索を開始するだけです。

初めてCCRを有効にすると（設定で*[アップデート]* をクリックした後---[Configuring CCR in a Local Follower Data Center](./configuring-ccr-in-a-local-follower-data-center.md)を参照）、ローカルクラスター構成の各エントリが処理されます。 まず、[リモートクラスター](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-remote-clusters.html)が[クラスターアップデート設定API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cluster-update-settings.html)を介して登録されます。 次に、リモートクラスター内の各インデックス（`.`から始まるインデックスまたは[除外するインデックス]設定で定義されたインデックスを除く）に対して、[フォロワーの作成API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-put-follow.html)が呼び出され、リモートインデックスとのフォロワー/リーダーの関係が設定されます。

既存のCCR構成を編集した後、またはCCRを無効にすると、以前にローカルクラスター構成に保存された各エントリが処理されます。 インデックスごとに、[フォローが一時停止](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-post-pause-follow.html)され、[インデックスが閉じられ](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/indices-close.html#indices-close)、[リーダーがフォロー解除され](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-post-unfollow.html)、[フォロワーインデックスが削除](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/indices-delete-index.html)されます。 その後、リモートクラスターは[クラスターアップデート設定API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cluster-update-settings.html)を介して登録解除されます。 CCRを無効にするだけの場合は、ここで処理が終了します。 構成を編集する場合、既存のローカルクラスター構成エントリは、CCRを初めて有効にする場合と同じように引き続き処理されます。 エントリごとに、[リモートクラスター](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-remote-clusters.html)が[クラスターアップデート設定API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cluster-update-settings.html)を介して登録されます。 各リモートクラスター内のすべてのインデックス（`.`から始まるインデックスまたは[除外するインデックス]設定で定義されたインデックスを除く）に対して、[フォロワーの作成API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/ccr-put-follow.html)が呼び出され、リモートインデックスとのフォロワー/リーダーの関係が設定されます。

手順を理解したところで、[基本的な特定のユースケース](./configuring-an-example-ccr-installation-replicating-between-data-centers.md)を使用して、ローカルの例の設定を開始しましょう。
