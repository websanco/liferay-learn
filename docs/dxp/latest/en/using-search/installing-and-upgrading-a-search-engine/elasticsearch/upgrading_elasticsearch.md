# Upgrading Elasticsearch

```{toctree}
:maxdepth: 1

upgrading-elasticsearch/upgrading-search-infrastructure.md
upgrading-elasticsearch/upgrading-to-elasticsearch-7.md
upgrading-elasticsearch/backing-up-elasticsearch.md
```

Liferay supports Elasticsearch 7. The [compatibility matrix](https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix) provides the latest support details.

```{important}
Solr integration is deprecated as of Liferay 7.3 and removed in 7.4, replaced by Elasticsearch integration. Migrating to Elasticsearch requires [setting up Elasticsearch](./getting-started-with-elasticsearch.md) and [connecting Liferay ](./connecting-to-elasticsearch.md) to it.
```

```{important}
Elasticsearch 6.x is not supported on Liferay 7.3+.
```

Before planning your upgrade, read [Upgrading Search Infrastructure](./upgrading-elasticsearch/upgrading-search-infrastructure.md). It provides an overview of the steps required to upgrade older Liferay/Elasticsearch systems to the latest supported search stack. Always [back up your search indexes](./upgrading-elasticsearch/backing-up-elasticsearch.md) before [upgrading Elasticsearch](./upgrading-elasticsearch/upgrading-to-elasticsearch-7.md).

- [Upgrading Search Infrastructure](upgrading-elasticsearch/upgrading-search-infrastructure.md)
- [Upgrading to Elasticsearch 7](upgrading-elasticsearch/upgrading-to-elasticsearch-7.md)
- [Backing Up Elasticsearch](upgrading-elasticsearch/backing-up-elasticsearch.md)
