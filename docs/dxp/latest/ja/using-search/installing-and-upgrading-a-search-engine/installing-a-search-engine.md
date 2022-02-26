# 検索エンジンのインストール

検索エンジンは、Liferayインストールの重要なコンポーネントです。 [サンプルクラスターを作成](./../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md#prepare-a-search-engine) することでインストールを開始できますが、このガイドでは、 **本番環境** のセットアップについて説明します

<!-- MAKE A DIAGRAM SIMILAR TO THE CCR ONE BUT WITH JUST ONE CONNECTION -->

Liferayを起動すると、組み込みのElasticsearchサーバー（サイドカー）が同時に起動します。 このデフォルトの検索エンジンは、テスト用の便利な検索機能を提供しますが、本番環境での使用はサポートされていません。 [Elasticsearchを開始する](./elasticsearch/getting-started-with-elasticsearch.md) では本番環境レベルのElasticsearchのセットアップについて説明しています。 [サイドカーまたはEmbedded モードのElasticsearchの使用](./elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md) では、デフォルトのElasticsearchサーバー（7.3ではサイドカー、7.2ではEmbedded）の機能と制限について説明しています。

```{note}
Liferayの [Solr](http://lucene.apache.org/solr) のサポートは、廃止される前にもう一つのアップデート（Solr 8のサポート）が行われます。 しかし、SolrはLiferayにバンドルされていないため、開発やテストの際にもリモートで接続する必要があります。 Solrを使用するには、 [Solrのインストール](./solr/installing-solr.md) を参照してください。
```

<a name="java要件" />

## Java要件

* 検索エンジンには、`JAVA_HOME`環境変数が必要です。 それを検索エンジンのホストに設定します。

* Liferay 7.2を使用している場合、ElasticsearchとLiferayは同じJavaバージョンとディストリビューションを使用する必要があります。 サポートされているJDKディストリビューションとバージョンの詳細は、 [Elasticsearch互換性マトリックス](https://www.elastic.co/support/matrix#matrix_jvm) と [Liferayの検索エンジン互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360016511651) を参照してください。  これはElasticsearchで指定できます。

    ```properties
    [Elasticsearch Home]/bin/elasticsearch.in.sh`: `JAVA_HOME=/path/to/java`
    ```

Elasticsearch 7コネクターはHTTP経由で通信するため、Javaのバージョンとディストリビューションの要件はLiferay 7.3には適用されません。JVMレベルのシリアル化はありません。 詳細は、 [Elastic's High-Level REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html) を参照してください。

SolrコネクターもHTTP経由で通信するため、同じ要件はSolrにも適用されません。

<a name="検索エンジンのクラスタリング" />

## 検索エンジンのクラスタリング

本番環境の検索エンジンは、負荷管理と最適なパフォーマンスのためにクラスター化する必要があります。 ElasticsearchとSolrはどちらも、リモート環境の複数のノードで設定できます。

* リモートのElasticsearchサーバーまたはクラスターを設定するには、 [Elasticsearchを開始する](./elasticsearch/getting-started-with-elasticsearch.md) を参照してください。

* リモートのSolrサーバーまたはクラスターを設定するには、 [Solrのインストール](./solr/installing-solr.md) を参照してください。

<a name="検索エンジンのベンダーとバージョンの選択" />

## 検索エンジンのベンダーとバージョンの選択

Elasticsearchは、Liferayでの検索とインデックス作成に推奨される検索エンジンです。 Solrは廃止予定で、[制限](./solr/solr-limitations.md)があります。

```{important}
サポートされている正確なバージョンを確認するには、必ず [検索エンジン互換性マトリクス](https://help.liferay.com/hc/en-us/articles/360016511651) を参照してください。
```

<a name="次のステップ" />

## 次のステップ

[Elasticsearchのインストール](./elasticsearch/getting-started-with-elasticsearch.md) を推奨します。 Solr（廃止予定）を使用する必要がある場合は、 [Solrのインストール](./solr/installing-solr.md) を参照してください。
