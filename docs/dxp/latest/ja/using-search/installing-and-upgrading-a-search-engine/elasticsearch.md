# Elasticsearch

```{toctree}
:maxdepth: 2

elasticsearch/getting-started-with-elasticsearch.md
elasticsearch/installing-elasticsearch.md
elasticsearch/connecting-to-elasticsearch.md
elasticsearch/securing-elasticsearch.md
elasticsearch/exercise-run-liferay-and-elasticsearch-using-docker.md
elasticsearch/troubleshooting-elasticsearch-installation.md
elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md
elasticsearch/upgrading-elasticsearch.md
elasticsearch/elasticsearch-connector-configuration-reference.md
elasticsearch/advanced-configuration-of-the-liferay-elasticsearch-connector.md
```

Elasticsearchは、Liferayがデフォルトで使用する、拡張性の高いフルテキスト検索エンジンです。 ElasticsearchはLiferayにバンドルされており、本番環境以外の目的で使用されます。 本番環境では、Liferayは別のリモートサーバーで動作するElasticsearchを必要とします。

```{important}
常に [互換性マトリックス](https://help.liferay.com/hc/ja/sections/360002103292-Compatibility-Matrix)   でLiferayのバージョンと互換性のあるElasticsearchのバージョンとサーバー構成を確認してください。
```

```{important}
Liferay 7.2および7.3では、Elasticsearch 7をサポートし、認証および暗号化されたElasticsearch接続を保護します。 Elasticsearch 6コネクタ（Liferay 7.2で利用可能）のセキュリティを確保するには、 [Liferay Enterprise Searchサブスクリプションでのみ利用可能な追加モジュールが必要です](.../liferay_enterprise_search.md) 。
```

<a name="installing" />

## インストール

-  [Elasticsearchを開始する](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/getting-started-with-elasticsearch.md)
-  [Elasticsearchのインストール](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/installing-elasticsearch.md)
-  [Elasticsearchへの接続](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/connecting-to-elasticsearch.md)
-  [演習：Dockerを使用してLiferay とElasticsearchを実行する](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/exercise-run-liferay-and-elasticsearch-using-docker.md)
-  [Elasticsearchインストールのトラブルシューティング](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/troubleshooting-elasticsearch-installation.md)
-  [サイドカーまたはEmbedded モードのElasticsearchの使用](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md)

- `[Clustering Liferay]` [Add a Search Engine to a Liferay Cluster](../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md#start-a-search-engine-server)

<a name="upgrading" />

## Upgrading

-  [Elasticsearchのアップグレード](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch.md)
-  [Liferay7.3の検索機能のアップグレード](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-search-for-liferay-73.md)
-  [Elasticsearchのバックアップ](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/backing-up-elasticsearch.md)
-  [Elasticsearch 7へのアップグレード](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-to-elasticsearch-7.md)

<a name="securing" />

## 保護する

- [Elasticsearchの保護](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)
旧バージョンのElasticsearchをインストールする ***_*_*****_*_*****_*_*****_*_*****_*_*****_*_*****_*_*****_*_*****_*_****_**
- Elasticsearch 6 for Liferay 7.2 (近日公開)

<a name="configuring" />

## 設定

-  [Liferay Elasticsearch コネクタの高度な設定](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/advanced-configuration-of-the-liferay-elasticsearch-connector.md)
-  [Elasticsearchコネクターの設定リファレンス](/using-search/installing-and-upgrading-a-search-engine/elasticsearch/elasticsearch-connector-configuration-reference.md)
