# Elasticsearchのインストール

オンプレミスでElasticsearchをインストール、設定、起動する方法は次のとおりです。

```{important}
The Sidecar Elasticsearch server bundled with Liferay 7.3 uses the Elasticsearch OSS distribution. Do not install the OSS version for production. To run Liferay securely with Elasticsearch, you must install the Basic level of Elasticsearch at a minimum. See [Elastic's subscriptions page](https://www.elastic.co/subscriptions)_ for more information.
```

```{note}
If you have Elasticsearch indexes used for primary data storage (storing data not backed by a database) you can bring that data into your new Elasticsearch cluster using the [snapshot and restore approach](./upgrading-elasticsearch/backing-up-elasticsearch.md)_. Liferay's own Search Tuning indexes (for Result Rankings and Synyonyms) are primary storage indexes.
```

## 本番環境のようなインストールのための環境設定

### ホストの追加

localhostまたはDockerコンテナを使用してテスト環境をセットアップする場合は、これをスキップできます。 ローカルマシンで本番環境のようにセットアップするには、LiferayとElasticsearchクラスターのホストを追加します。 お使いのオペレーティングシステムの`path/to/etc/hosts`ファイルに次の情報を追加します。

``` properties
<your IP> es-node1
<your IP> es-node2
<your IP> es-node3
<your IP> dxp.liferay.com
```

ループバックアドレス`127.0.0.1`ではなく、システムの実際のIPアドレスを使用してください。

### mmapの調整

Elasticsearchは、ほとんどのオペレーティングシステムのデフォルトよりも多くの*mmapカウント*を必要とします（インデックスを保持するディレクトリをメモリにマッピングするため）。 Linuxでは、rootユーザーとして、次のように実行します。

``` bash
sysctl -w vm.max_map_count=262144
```

## Elasticsearchのインストール

1.  [ElasticのWebサイト](https://www.elastic.co)からElasticsearchアーカイブ（OSSバージョンではない）をダウンロードします。

    ```{important}
    Download the latest Elasticsearch archive [compatible with your Liferay version](./connecting-to-elasticsearch.html#available-liferay-connector-applications).
    ```

2.  Elasticsearchを実行するローカルフォルダにアーカイブの内容を展開します。 このフォルダが*Elasticsearchホーム*です。

3.  `[Elasticsearch Home]/bin`フォルダで次のコマンドを実行して、必要なElasticsearchプラグインをインストールします。

    ``` bash
    ./elasticsearch-plugin install analysis-icu
    ```

    ``` bash
    ./elasticsearch-plugin install analysis-kuromoji
    ```

    ``` bash
    ./elasticsearch-plugin install analysis-smartcn
    ```

    ``` bash
    ./elasticsearch-plugin install analysis-stempel
    ```

## Elasticsearchの設定

各Elasticsearchサーバは、`[Elasticsearch Home]/config/elasticsearch.yml`ファイルによって設定されます。

シングルノードおよびマルチノードのElasticsearchクラスター構成の例を次に示します。

Elasticsearch サーバーとの通信の認証と暗号化については、[Securing Elasticsearch](./securing-elasticsearch.md)を参照してください。

### 例：シングルノードの本番環境Elasticsearchクラスター

以下は、シングルノードクラスターの`elasticsearch.yml`構成です。

``` yaml
cluster.name: LiferayElasticsearchCluster

discovery.type: single-node
discovery.seed_hosts:
  - es-node1:9300
http.port: 9200
network.host: es-node1
node.name: es-node1
transport.port: 9300

# Additional security settings 
```

`LiferayElasticsearchCluster`と呼ばれるこのクラスターには、`es-node1`と呼ばれるノードが1つあります。

```{tip}
If you are not configuring hosts for a production mode setup, use `localhost` as the host value. Elasticsearch can bind to loopback addresses for HTTP and Transport communication. Along with single node discovery, this means the Elasticsearch server is running in `development mode`.
```

### 例：マルチノード本番環境Elasticsearchクラスター

以下は、3ノードクラスターの`es-node3`と呼ばれるノードの`elasticsearch.yml`です。

``` yaml
cluster.name: LiferayElasticsearchCluster

# Example production mode settings - 3-node cluster
cluster.initial_master_nodes:
  - es-node1
  - es-node2
  - es-node3
discovery.seed_hosts:
  - es-node1:9300
  - es-node2:9301
  - es-node3:9302
http.port: 9202
network.host: es-node3
node.name: es-node3
transport.port: 9302

# Add security settings here
```

```{tip}
If you are not configuring hosts for a production mode setup, use `localhost` as the host value. Elasticsearch can bind to loopback addresses for HTTP and Transport communication. This is referred to as `development mode`.

Related Elasticsearch Documentation:

- [Important Elasticsearch configuration](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/important-settings.html)

- [Security settings in Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/security-settings.html)

- [Bootstrap Checks, Development vs. production mode](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/bootstrap-checks.html)
```

```{important}
Each Elasticsearch node's `elasticsearch.yml` file must use unique values for the following properties.

- `node.name`

- `http.port`

- `network.host`

- `transport.port`
```

### 本番環境モードで単一サーバーのブートストラップチェックを実施する

Elasticsearch[ブートストラップチェック](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/bootstrap-checks.html)では、起動時に構成を検査し、構成が欠落しているか疑わしい場合は警告をログに記録します。 本番環境では、構成ミスの際に起動を停止するようにブートストラップチェックを設定する必要があります。

シングルノードクラスターでブートストラップチェックを実施するには、次のプロパティをノードの`[Elasticsearch Home]/config/jvm.options`ファイルの最後に追加します。

``` properties
-Des.enforce.bootstrap.checks=true
```

## Elasticsearchの起動

`bin`フォルダからElasticsearchを起動します。

``` bash
./elasticsearch
```

Elasticが起動したら、ステータスメッセージに下記のようなトランスポートアドレスが記載されます。

``` sh
[2019-04-01T16:55:50,127][INFO ][o.e.t.TransportService   ] [HfkqdKv] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}
```

Liferay 7.2を実行している場合は、`TransportService`ステータスメッセージの`publish_address`アドレスをメモしてください。 このアドレスでElasticsearchに接続するようにLiferayサーバーを設定する必要があります。

Elasticsearchは[Liferayからの接続](./connecting-to-elasticsearch.md)の準備ができています。

本番環境で実行している場合は、[LiferayとElasticsearch間の通信を保護します](./securing-elasticsearch.md)。

## 追加のトピック

  - [Securing Elasticsearch](./securing-elasticsearch.md)
  - [Liferay Enterprise Search](../../liferay_enterprise_search.rst)
  - [Search Pages](../../search-pages-and-widgets/working-with-search-pages/search-pages.md)
  - [Administering and Tuning Search](../../search_administration_and_tuning.rst)
  - [Elasticsearch Connector Settings](./elasticsearch-connector-settings.md)
