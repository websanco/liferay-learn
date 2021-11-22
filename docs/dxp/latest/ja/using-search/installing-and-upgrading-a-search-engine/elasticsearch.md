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
Liferayのバージョンと互換性のあるElasticsearchのバージョンとサーバー構成は、[互換性マトリクス](https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix) を確認してください。
```


```{important}
Liferay 7.2および7.3では、Elasticsearch 7をサポートし、認証および暗号化されたElasticsearch接続を保護します。 Elasticsearch 6コネクタ（Liferay 7.2で利用可能）のセキュリティを確保するには、 [Liferay Enterprise Searchサブスクリプション](../liferay-enterprise-search.md) でのみ利用可能な追加モジュールが必要です。
```

## インストール

- [Getting Started with Elasticsearch](elasticsearch/getting-started-with-elasticsearch.md)
- [Installing Elasticsearch](elasticsearch/installing-elasticsearch.md)
- [Connecting to Elasticsearch](elasticsearch/connecting-to-elasticsearch.md)
- [Exercise: Run Liferay and Elasticsearch Using Docker](elasticsearch/exercise-run-liferay-and-elasticsearch-using-docker.md)
- [Troubleshooting Elasticsearch Installation](elasticsearch/troubleshooting-elasticsearch-installation.md)
- [Using the Sidecar or Embedded Elasticsearch](elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md)

- `[Liferayのクラスタリング]` [Add a Search Engine to a Liferay Cluster](../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md#start-a-search-engine-server)

## アップグレード

- [Upgrading Elasticsearch](elasticsearch/upgrading-elasticsearch.md)
- [Upgrading Search Infrastructure](elasticsearch/upgrading-elasticsearch/upgrading-search-infrastructure.md)
- [Backing Up Elasticsearch](elasticsearch/upgrading-elasticsearch/backing-up-elasticsearch.md)
- [Upgrading to Elasticsearch 7](elasticsearch/upgrading-elasticsearch/upgrading-to-elasticsearch-7.md)

## 保護

- [Securing Elasticsearch](elasticsearch/securing-elasticsearch.md)

## 旧バージョンのElasticsearchのインストール

- Elasticsearch 6 for Liferay 7.2 (近日公開)

## コンフィグレーション

- [Advanced Configuration of the Liferay Elasticsearch Connector](elasticsearch/advanced-configuration-of-the-liferay-elasticsearch-connector.md)
- [Elasticsearch Connector Configuration Reference](elasticsearch/elasticsearch-connector-configuration-reference.md)
