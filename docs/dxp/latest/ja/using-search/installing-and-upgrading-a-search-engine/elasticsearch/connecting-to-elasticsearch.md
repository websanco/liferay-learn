# Elasticsearchへの接続

[Elasticsearchを設定](./installing-elasticsearch.md)した後、Liferay Connector to Elasticsearchを使用してLiferayをElasticsearchに接続する必要があります。 接続の手順は、設定している[コネクタ](#available-liferay-connector-applications)によって異なります。

  - Liferay 7.3：Liferay Connector to Elasticsearchは、Liferay DXP 7.3およびCE 7.3 GA4以降に含まれています。 また、[Liferay マーケットプレイス](../../../system-administration/installing-and-managing-apps/using-marketplace.md)でも入手可能です。
  - Liferay 7.2：Liferay Connector to Elasticsearchは[Liferay マーケットプレイス](../../../system-administration/installing-and-managing-apps/using-marketplace.md)で入手できます。

ここでは、インストールや設定手順の主な違いについて説明します。

```{important}
Stop each Liferay server node before configuring the connection.
```

Liferay 7.2を使用している場合は、[Liferay 7.2：Elasticsearch 7コネクタのインストール](#liferay-7.2-installing-elasticsearch-7-connector)にスキップしてください。

## コネクタの設定

Elasticsearch 7コネクタは、`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config`という名前の設定ファイルを介してLiferay 7.3用に設定されます。

ファイルで構成を指定したら、`[Liferay Home]/osgi/configs/`フォルダに配置してデプロイできます。

Dockerの場合、

``` bash
docker cp ~/path/to/com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config [container]:/mnt/liferay/files
```

または、ユーザーインターフェイスでコネクタを設定することもできます。 グローバルメニュー（![Global Menu](../../../images/icon-applications-menu.png)）で、[コントロールパネル] → [システム設定]に移動し、*[検索機能]* カテゴリを開きます。 このエントリはElasticsearch 7と呼ばれます。

> Liferay 7.2では、コントロールパネルはプロダクトメニュー（![Product Menu](../../../images/icon-product-menu.png)）にあります。

7.3コネクタのシンプル設定では、本番環境モード（`productionModeEnabled="true"`）が有効になり、各Elasticsearchノード（`networkHostAddresses=["http://es-node:9200"]`）へのURLが設定されます。

1.  次の設定ファイルを作成します。

    ``` bash
    com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    ```

2.  `.config`ファイルで設定プロパティを指定します。 コメントアウトされた[セキュリティプロパティ](./securing-elasticsearch.md)を含む例を次に示します（暗号化が有効になっている場合は `https`ネットワークホストアドレスを使用する必要があることに注意してください）。

    ``` properties
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

3.  `.config`ファイルを`[Liferay Home]/osgi/configs`フォルダに配置します。

<!-- end list -->

```{tip}
The connectors contain many configuration settings. See the [Elasticsearch Connector Settings](./elasticsearch-connector-settings.md) for their definitions. Most of the configurations correspond to settings available in [Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index.html).
```

Elasticsearchサーバーを名前で参照するには、各Elasticsearchサーバー名をDNSまたはLiferayサーバーの`/etc/hosts` ファイル内のIPアドレスにマップします。

```{tip}
The network host address format is `http[s]://[host name]:[port]`. If you're using a Liferay Docker container, you can use `--add-host [host name]:[IP address]` options with your `docker run` command to map a host name to each Elasticsearch server IP address. The port is defined in the Elasticsearch container's docker run command as the first value of the `-p 1234:5678` option (it's `1234` in this case). If you're running a local test environment without HTTPS enabled, all the addresses can be `http://localhost:port`. See [Docker's documentation](https://docs.docker.com/engine/reference/run/#managing-etchosts) for more details.
```

## Liferay 7.2：Elasticsearch 7コネクタのインストール

### Elasticsearch 6コネクタを停止する

Liferay 7.2では、バンドルされているコネクタアプリケーションとAPIはElasticsearch 6用です。 Elasticsearch 7コネクタをインストールする前に、これらを無効にする必要があります。

1.  以下のようなファイルを作成します。
   
        com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config

2.  以下のコンテンツをファイルに追加します。

    ``` properties
    blacklistBundleSymbolicNames=[ \
        "com.liferay.portal.search.elasticsearch6.api", \
        "com.liferay.portal.search.elasticsearch6.impl", \
        "com.liferay.portal.search.elasticsearch6.spi", \
        "com.liferay.portal.search.elasticsearch6.xpack.security.impl", \
        "Liferay Connector to X-Pack Security [Elastic Stack 6.x] - Impl", \
        "Liferay Enterprise Search Security - Impl.lpkg" \
    ]
    ```

3.  ファイルを`[Liferay Home]/osgi/configs`フォルダに配置します。

    Liferayサーバーを起動すると（まだ起動していない場合）、Liferayはこのファイルを読み取り、宣言されたバンドルの起動をブロックします。

    ```{tip}
    **Docker:** `Liferay Home` and other important folders of a Liferay installation are accessed in a Docker container at `/mnt/liferay` as described [here](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/container-lifecycle-and-api.md#api). You can use `docker cp /path/to/local/file [container_name]:/mnt/liferay/files/osgi/configs` to place configuration files into the container. Later, you can use `docker cp` to deploy the Liferay Connector to Elasticsearch 7 LPKG file.
    ```

### Elasticsearch 7コネクタのインストール

1.  Liferay Connector to Elasticsearch 7をダウンロードします。

    コネクタがElasticsearchのバージョンに対応していることを確認してください。 コネクタアプリケーションが新しいバージョン（7.9.xなど）をサポートしている場合でも、コネクタのクライアントライブラリが古いバージョンのElasticsearch（7.3など）用である可能性があることに注意してください。 Liferayは、Elasticsearchのマイナーバージョンごとにコネクタをテストし、必要に応じて新しいアップデートコネクタバージョンを作成します。 コネクタの互換性については、[Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651)を参照してください。

      - CE：[Liferay CE Connector to Elasticsearch](https://web.liferay.com/en/marketplace/-/mp/application/170642090)
      - DXP：[Liferay Connector to Elasticsearch](https://web.liferay.com/en/marketplace/-/mp/application/170390307)

2.  [LPKGをフォルダに配置してインストール](../../../system-administration/installing-and-managing-apps/installing-apps.md)します。

    ``` bash
    [Liferay Home]/deploy
    ```

    Dockerの場合、

    ``` bash
    docker cp ~/path/to/Liferay\ Connector\ to\ Elasticsearch.lpkg [container]:/mnt/liferay/deploy
    ```

    Liferayサーバーを起動すると（まだ起動していない場合）、LiferayはLPKGをデプロイします。

これで、コネクタを設定する準備が整いました。

### Liferay7.2用のコネクタを設定する

1.  次のElasticsearch設定ファイルを作成します。

    ``` bash
    com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    ```

2.  `.config`ファイルで設定プロパティを指定します。 ここでは、リモート動作モードを有効にし、各Elasticsearchノードのトランスポートアドレスを設定し、設定している接続を識別する例を示します。

    ``` properties
    # com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    operationMode="REMOTE"
    transportAddresses="ip.of.elasticsearch.node:9300"
    # Highly recommended for all non-production usage (e.g., practice, tests, diagnostics):
    #logExceptionsOnly="false"
    ```

3.  `.config`ファイルを`[Liferay Home]/osgi/configs`フォルダに配置して、設定をデプロイします。

これでLiferayを開始する準備が整いました。

## Liferayを開始してインデックスを再作成する

Elasticsearchが[インストールされて動作している](./installing-elasticsearch.md)場合は、Liferayを起動します。 コントロールパネルで、[設定] → [検索機能]に移動し、Elasticsearch接続が有効であることを確認します。

![有効な接続が[検索機能]管理パネルに表示されます。](./getting-started-with-elasticsearch/images/01.png)

検索インデックスとスペルチェックインデックスを再作成します。 [コントロールパネル] → [設定] → [検索機能]の[アクションをインデックスする]タブで、これらのアクションの両方を呼び出します。

Liferay 7.3では、[Workflow Metrics Settings]ウィンドウから[ワークフロー統計情報](../../../process-automation/workflow/using-workflows/using-workflow-metrics.md)インデックスを再作成します。

1.  グローバルメニュー（![Applications Menu](../../../images/icon-applications-menu.png)）から、[Applications] → [ワークフロー統計情報]に移動します。

2.  アプリオプションメニュー（![App Options](../../../images/icon-app-options.png)）から*[Settings]* ウィンドウを開きます。

3.  *[すべてインデックスを再構築]* をクリックします。

<!-- end list -->

```{note}
If you have Elasticsearch indexes used for primary data storage (storing data not backed by a database) you can bring that data into your new Elasticsearch cluster using the [snapshot and restore approach](./upgrading-elasticsearch/backing-up-elasticsearch.md)_. Liferay's own Search Tuning indexes (for Result Rankings and Synyonyms) are primary storage indexes.
```

これで、LiferayはリモートのElasticsearch 7インストールにコンテンツのインデックスを作成します。

## 利用可能なLiferayコネクタアプリケーション

バンドルされているElasticsearchへのコネクタが、インストールに最適であるとは限りません。 Elasticsearchとの通信に使用できるコネクタ間の違いを理解することが重要です。

| Liferay CE/DXPバージョン                               | 名前                                             | 利用可能性                                                                       | 通信プロトコル                                                                                            | セキュアな接続のサポート                                                             | 推奨されるElasticsearchのバージョン | Elasticsearchの推奨バージョン | 動作モード                      |
| ------------------------------------------------- | ---------------------------------------------- | --------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------ | ------------------------ | --------------------- | -------------------------- |
| CE 7.3 GA4+ <br /><br /> DXP 7.3 GA1+ | Liferay Connector to  Elasticsearch 7          | バンドル                                                                        | [HTTP](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-overview.html) | ✔                                                                        | 7.9+                     | 7.9+                  | サイドカー <br /> リモート    |
| 7.2、GA1～FP4                                       | Liferay Connector to  Elasticsearch 6          | バンドル                                                                        | [トランスポート](https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.5/transport-client.html) | ✔\*（ [LES](https://www.liferay.com/products/dxp/enterprise-search)が必要） | 6.5.x-6.8.x              | 6.8.x                 | Embedded <br /> リモート |
| 7.2、SP2+/FP5+                                     | Liferay Connector to  Elasticsearch 6          | バンドル                                                                        | [トランスポート](https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.8/transport-client.html) | ✔\*（ [LES](https://www.liferay.com/products/dxp/enterprise-search)が必要） | 6.8.x                    | 6.8.x                 | Embedded <br /> リモート |
| DXP 7.2 SP3/FP8+                                  | Liferay Connector to Elasticsearch 7（3.1.0+）   | [マーケットプレース](https://web.liferay.com/marketplace/-/mp/application/170390307) | [トランスポート](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) | ✔                                                                        | 7.3.x-7.9.x              | 7.9.x                 | Embedded <br /> リモート |
| DXP 7.2 FP9+                                      | Liferay Connector to Elasticsearch 7（3.2.0+）   | [マーケットプレース](https://web.liferay.com/marketplace/-/mp/application/170390307) | [トランスポート](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) | ✔                                                                        | 7.9.x                    | 7.9.x                 | Embedded <br /> リモート |
| CE 7.2 GA2+                                       | Liferay CE Connector to Elasticsearch 7（3.0.0） | [マーケットプレース](https://web.liferay.com/marketplace/-/mp/application/170642090) | [トランスポート](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) | ✔                                                                        | 7.3.x-7.9.x              | 7.9.x                 | Embedded <br /> リモート |

\* [Liferay Connector to X-Pack Security \[Elastic Stack 6.x\]](https://web.liferay.com/marketplace/-/mp/application/106163963)経由。

## 次のステップ

LiferayがElasticsearchに接続されたので、Elasticsearchの使用を開始できます。 本番環境では、LiferayサーバーとElasticsearchサーバー間の通信を保護する必要があります。 詳しくは、[Securing Elasticsearch](./securing-elasticsearch.md)を参照してください。

## 関連トピック

  - [Securing Elasticsearch](./securing-elasticsearch.md)
  - Liferay Enterprise Search（近日公開）
  - [Search Pages](../../search-pages-and-widgets/working-with-search-pages/search-pages.md)
  - Administering and Tuning Search（近日公開）
  - [Elasticsearch Connector Settings](./elasticsearch-connector-settings.md)
