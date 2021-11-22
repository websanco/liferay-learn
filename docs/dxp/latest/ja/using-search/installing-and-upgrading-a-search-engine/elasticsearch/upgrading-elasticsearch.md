# Upgrading Elasticsearch

```{toctree}
:maxdepth: 1

upgrading-elasticsearch/upgrading-search-infrastructure.md
upgrading-elasticsearch/upgrading-to-elasticsearch-7.md
upgrading-elasticsearch/backing-up-elasticsearch.md
```

Liferay 7.3では、Elasticsearch 7に対する組み込みのサポートを含む、検索機能の新たな改善<../../getting-started/whats-new-in-search-for-73.md>`__が行われています。 互換性マトリックス<https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix>`__では、最新のサポートの詳細を提供しています。

```{important}
Solr統合はLiferay7.3で廃止予定になり、Elasticsearch統合に置き換えられました。 Elasticsearchに移行するには、Elasticsearchを設定して<./getting-started-with-elasticsearch.md>`_、Liferayを接続する<./connecting-to-elasticsearch.md>`_必要があります。
```

```{important}
Elasticsearch 6.xはLiferay 7.3ではサポートされていません。
```

アップグレードを計画する前に、 [Upgrading Search for Liferay 7.3](./upgrading-elasticsearch/upgrading-search-for-liferay-73.md) を参照してください。 古いLiferay/Elasticsearchシステムをサポートされている最新の検索スタックにアップグレードするために必要な手順の概要を提供しています。 [Elasticsearchをアップグレードする](./upgrading-elasticsearch/upgrading-to-elasticsearch-7.md) 前に、必ず検索 [インデックスをバックアップ](./upgrading-elasticsearch/backing-up-elasticsearch.md) してください。

- [Upgrading Search Infrastructure](upgrading-elasticsearch/upgrading-search-infrastructure.md)
- [Upgrading to Elasticsearch 7](upgrading-elasticsearch/upgrading-to-elasticsearch-7.md)
- [Backing Up Elasticsearch](upgrading-elasticsearch/backing-up-elasticsearch.md)
