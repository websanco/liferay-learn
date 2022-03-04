# Elasticsearchへの接続

[Elasticsearchを設定](./installing-elasticsearch.md)した後、Liferay Connector to Elasticsearchを使用してLiferayをElasticsearchに接続する必要があります。 接続の手順は、設定している [コネクタ](#available-liferay-elasticsearch-connectors) によって異なります。

* Liferay 7.3：Liferay Connector to Elasticsearchは、Liferay DXP 7.3およびCE 7.3 GA4以降に含まれています。 また、[Liferay マーケットプレイス](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md)でも入手可能です。
* Liferay 7.2：Liferay Connector to Elasticsearchは[Liferay マーケットプレイス](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md)で入手できます。

ここでは、インストールや設定手順の主な違いについて説明します。

```{important}
   接続を設定する前に、各Liferayサーバーノードを停止します。
```

Liferay 7.2を使用している場合は、 [Liferay 7.2：Elasticsearch 7コネクタのインストール](#liferay-7-2-installing-elasticsearch-7-connector) にスキップしてください。

<a name="configuring-the-connector" />

## コネクタの設定

Elasticsearch 7コネクタは、`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config`という名前の設定ファイルを介してLiferay 7.3用に設定されます。

ファイルで構成を指定したら、`［Liferay Home］/osgi/configs/`フォルダに配置してデプロイできます。

Dockerの場合、

```bash
docker cp ~/path/to/com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config [container]:/mnt/liferay/files
```

または、ユーザーインターフェイスでコネクタを設定することもできます。 グローバルメニュー（![Global Menu](../../../images/icon-applications-menu.png)）で、［コントロールパネル］&rarr; ［システム設定］を開き、 ［**検索**］ のカテゴリーを開きます。 このエントリはElasticsearch 7と呼ばれます。

> Liferay 7.2では、コントロールパネルはプロダクトメニュー（![Product Menu](../../../images/icon-product-menu.png)）にあります。

7.3コネクタのシンプル設定では、本番環境モード（`productionModeEnabled="true"`）が有効になり、各Elasticsearchノード（`networkHostAddresses=［"http://es-node:9200"］`）へのURLが設定されます。

1. 次の設定ファイルを作成します。

    ```bash
    com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    ```

1. `.config`ファイルで設定プロパティを指定します。 コメントアウトされた[セキュリティプロパティ](./securing-elasticsearch.md)を含む例を次に示します（暗号化が有効になっている場合は `https`ネットワークホストアドレスを使用する必要があることに注意してください）。

    ```properties
    # In CE/DXP7.3, productionModeEnabled replaces operationMode (deprecated):
    productionModeEnabled=B"true"
    networkHostAddresses=["http://es-node1:9200","http://es-node3:9201","http://es-node3:9202"]
    # In CE/DXP 7.3 the security settings are included in the ElasticsearchConfiguration
    # In CE/DXP 7.2 the security settings go in com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config
    # Authentication
    #authenticationEnabled=B"true"
    #username="elastic"
    #password="liferay"

    # TLS/SSL
    #networkHostAddresses=["https://es-node1:9200","https://es-node3:9201","https://es-node3:9202"]
    #httpSSLEnabled=B"true"
    #truststoreType="pkcs12"
    #trustStorePath="/PATH/TO/elastic-nodes.p12"
    #trustStorePassword="liferay"

    # Highly recommended for all non-prodcution usage (e.g., practice, tests, diagnostics):
    #logExceptionsOnly="false"
    ```

1. `.config`ファイルを`［Liferay Home］/osgi/configs`フォルダに配置します。


```{tip}
   コネクタには多くの設定項目があります。 それぞれの定義については、 [Elasticsearch コネクターの設定](./elasticsearch-connector-configuration-reference.md) をご参照ください。 ほとんどの設定は、 [Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index.html) で利用可能な設定に対応しています。
```

Elasticsearchサーバーを名前で参照するには、各Elasticsearchサーバー名をDNSまたはLiferayサーバーの`/etc/hosts` ファイル内のIPアドレスにマップします。

```{tip}
  ネットワークホストアドレスのフォーマットは、//[host name]:[port]` です。 LiferayのDockerコンテナを使用している場合、`docker run`コマンドで`--add-host [host name]:[IP address]`オプションを使用して、ホスト名を各ElasticsearchサーバのIPアドレスにマッピングすることができます。 ポートは、Elasticsearchコンテナのdocker runコマンドで、`-p 1234:5678`オプションの最初の値として定義されます（ここでは`1234`です）。 HTTPSが有効になっていないローカルテスト環境を実行している場合は、すべてのアドレスが `http://localhost:port` [になります。 cDockerのドキュメント](https://docs.docker.com/engine/reference/run/#managing-etchosts) をご覧ください。
```

<a name="liferay-72-installing-elasticsearch-7-connector" />

## Liferay 7.2：Elasticsearch 7コネクタのインストール

<a name="stop-the-elasticsearch-6-connector" />

### Elasticsearch 6コネクタを停止する

Liferay 7.2では、バンドルされているコネクタアプリケーションとAPIはElasticsearch 6用です。 Elasticsearch 7コネクタをインストールする前に、これらを無効にする必要があります。

1. 以下のようなファイルを作成します。

    ```
    com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config
    ```

1. 以下のコンテンツをファイルに追加します。

    ```properties
    blacklistBundleSymbolicNames=[ \
        "com.liferay.portal.search.elasticsearch6.api", \
        "com.liferay.portal.search.elasticsearch6.impl", \
        "com.liferay.portal.search.elasticsearch6.spi", \
        "com.liferay.portal.search.elasticsearch6.xpack.security.impl", \
        "Liferay Connector to X-Pack Security [Elastic Stack 6.x] - Impl", \
        "Liferay Enterprise Search Security - Impl.lpkg" \
    ]
    ```

1. ファイルを`［Liferay Home］/osgi/configs`フォルダに配置します。

    Liferayサーバーを起動すると（まだ起動していない場合）、Liferayはこのファイルを読み取り、宣言されたバンドルの起動をブロックします。

    ```{tip}
      **Docker:** eray Home` やその他のLiferayインストールの重要なフォルダは、 `/mnt/liferay` [にあるDockerコンテナにアクセスして、以下のように説明されます。](../../.../installation-and-upgrades/installing-liferay/using-liferay-docker-images/container-lifecycle-and-api.md#api) . docker cp /path/to/local/file [container_name]:/mnt/liferay/files/osgi/configs`を使って、コンフィグレーションファイルをコンテナ内に配置することができます。 その後、`docker cp`を使ってLiferay Connector to Elasticsearch 7のLPKGファイルをデプロイすることができます。
    ```

<a name="install-the-elasticsearch-7-connector" />

### Elasticsearch 7コネクタのインストール

1. Liferay Connector to Elasticsearch 7をダウンロードします。

   コネクタがElasticsearchのバージョンに対応していることを確認してください。 コネクタアプリケーションが新しいバージョン（7.9.xなど）をサポートしている場合でも、コネクタのクライアントライブラリが古いバージョンのElasticsearch（7.3など）用である可能性があることに注意してください。 Liferayは、Elasticsearchのマイナーバージョンごとにコネクタをテストし、必要に応じて新しいアップデートコネクタバージョンを作成します。 コネクタの互換性については、 [検索エンジンの互換性マトリックス](https://help.liferay.com/hc/ja/articles/360016511651) を参照してください。

   * CE： [Liferay CE Connector to Elasticsearch 7](https://web.liferay.com/en/marketplace/-/mp/application/170642090) です。
   * DXP： [Liferay Connector to Elasticsearch 7](https://web.liferay.com/en/marketplace/-/mp/application/170390307) です。

1. [LPKGをフォルダに配置してインストール](../../../system-administration/installing-and-managing-apps/installing-apps/installing-apps.md)します。

   ```bash
   [Liferay Home]/deploy
   ```

   Dockerの場合、

   ```bash
   docker cp ~/path/to/Liferay\ Connector\ to\ Elasticsearch.lpkg [container]:/mnt/liferay/deploy
   ```

   Liferayサーバーを起動すると（まだ起動していない場合）、LiferayはLPKGをデプロイします。

これで、コネクタを設定する準備が整いました。

<a name="configure-the-connector-for-liferay-72" />

### Liferay7.2用のコネクタを設定する

1. 次のElasticsearch設定ファイルを作成します。

    ```bash
    com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    ```

1. `.config`ファイルで設定プロパティを指定します。 ここでは、リモート動作モードを有効にし、各Elasticsearchノードのトランスポートアドレスを設定し、設定している接続を識別する例を示します。

    ```properties
    # com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    operationMode="REMOTE"
    transportAddresses="ip.of.elasticsearch.node:9300"
    # Highly recommended for all non-production usage (e.g., practice, tests, diagnostics):
    #logExceptionsOnly="false"
    ```

1. `.config`ファイルを`［Liferay Home］/osgi/configs`フォルダに配置して、設定をデプロイします。

これでLiferayを開始する準備が整いました。

<a name="start-liferay-and-re-index" />

## Liferayを開始してインデックスを再作成する

Elasticsearchが[インストールされて動作している](./installing-elasticsearch.md)場合は、Liferayを起動します。 コントロールパネルで［設定&rarr; 検索機能］に移動し、Elasticsearchの接続がアクティブになっていることを確認します。

![有効な接続が［検索機能］管理パネルに表示されます。](./getting-started-with-elasticsearch/images/01.png)

検索インデックスとスペルチェックインデックスのインデックスを再作成します。 コントロールパネルの &rarr; 構成 &rarr; 検索の［インデックスアクション］タブで、この2つのアクションを起動します。

Liferay 7.3では、［Workflow Metrics Settings］ウィンドウから[ワークフロー統計情報](../../../process-automation/workflow/using-workflows/using-workflow-metrics.md)インデックスを再作成します。

1. グローバルメニュー（![Applications Menu](../../../images/icon-applications-menu.png)）から［アプリケーション］ &rarr; ［ワークフローメトリクス］に移動します。

1. アプリオプションメニュー（![App Options](../../../images/icon-app-options.png)）から ［**Settings**］ ウィンドウを開きます。

1. ［**すべてインデックスを再構築**］ をクリックします。 この操作をシステム内の各仮想インスタンスに対して繰り返します。

```{note}
   一次データの保存（データベースにバックアップされていないデータの保存）に使用されているElasticsearchインデックスがある場合は、[snapshot and restore approach](./upgrading-elasticsearch/backing-up-elasticsearch.md) . Liferay独自の検索の調整インデックス(結果ランキングとSynyonyms用)は、プライマリーストレージのインデックスです。
```

これで、LiferayはリモートのElasticsearch 7インストールにコンテンツのインデックスを作成します。

<a name="available-liferay-elasticsearch-connectors" />

## 利用可能なLiferay Elasticsearchコネクタ

バンドルされているElasticsearchへのコネクタが、インストールに最適であるとは限りません。 Elasticsearchとの通信に使用できるコネクタ間の違いを理解することが重要です。

| Liferay CE/DXPバージョン     | 名前                                               | 利用可能性                                                                                                                                                    | 通信プロトコル                                                                                            | セキュアな接続のサポート                                                                              | 動作モード                             |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| ce 7.3 ga4, dxp 7.3 ga1 | Liferay (CE) コネクタ to Elasticsearch 7             | バンドル                                                                                                                                                     | [HTTP](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-overview.html) | &#10004;                                                                                  | Sidecar / Remote (Production)\* |
| CE 7.2、DXP 7.2          | Liferayコネクター（CE）とElasticsearch 6の連携              | バンドル                                                                                                                                                     | [トランスポート](https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.x/transport-client.html) | &#10004;\ **\**(requires [LES](https://www.liferay.com/products/dxp/enterprise-search) ) | エンベデッド/リモート                       |
| CE 7.2、DXP 7.2          | Liferay Connector (CE) to Elasticsearch 7 (v3.x) | マーケットプレイス [CE](https://web.liferay.com/marketplace/-/mp/application/170642090) , [DXP](https://web.liferay.com/marketplace/-/mp/application/170390307) です。 | [トランスポート](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) | &#10004;                                                                                  | エンベデッド/リモート                       |

\ **コネクタ設定の****動作モード***( `operationMode`) 設定は非推奨になり、 ****本番モードが有効**(`productionModeEnabled`)に置き換えられました。

\ **\** [Liferay Enterprise Search Security](https://web.liferay.com/marketplace/-/mp/application/106163963) アプリケーションを通して。

互換性のあるElasticsearchのバージョンや必要なパッチレベルなど、詳細な互換性情報については、 [検索エンジン互換性マトリクス](https://help.liferay.com/hc/ja/articles/360016511651) を参照してください。

<a name="whats-next" />

## 次のステップ

LiferayがElasticsearchに接続されたので、Elasticsearchの使用を開始できます。 本番環境では、LiferayサーバーとElasticsearchサーバー間の通信を保護する必要があります。 詳しくは、 [Elasticsearchの保護](./securing-elasticsearch.md) を参照してください。

<a name="related-topics" />

## 関連トピック

* [Elasticsearchの保護](./securing-elasticsearch.md)
* [Elasticsearchコネクタの設定](./elasticsearch-connector-configuration-reference.md)
* [サイドカーまたはEmbedded モードのElasticsearchの使用](./using-the-sidecar-or-embedded-elasticsearch.md)
* [Liferay Enterprise Search](../../liferay_enterprise_search.html)
* [検索ページ](../../search-pages-and-widgets/working-with-search-pages/search-pages.md)
* [検索の管理と調整](../../search_administration_and_tuning.html)
