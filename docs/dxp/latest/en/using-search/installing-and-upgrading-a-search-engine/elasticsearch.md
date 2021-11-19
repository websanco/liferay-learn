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

Elasticsearch is the highly scalable, full-text search engine Liferay uses by default. Elasticsearch is bundled with Liferay for non-production purposes. In production, Liferay requires Elasticsearch running on a separate remote server.

```{important}
Always check the [compatibility matrix](https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix) for the Elasticsearch version and server configuration compatible with your Liferay version.
```

```{important}
Liferay 7.2 and 7.3 include support for Elasticsearch 7 and securing authenticated, encrypted Elasticsearch connections. Securing the Elasticsearch 6 connector (available on Liferay 7.2) requires an additional module only available with a [Liferay Enterprise Search subscription](../liferay-enterprise-search.md).
```

## Installing

- [Getting Started with Elasticsearch](elasticsearch/getting-started-with-elasticsearch.md)
- [Installing Elasticsearch](elasticsearch/installing-elasticsearch.md)
- [Connecting to Elasticsearch](elasticsearch/connecting-to-elasticsearch.md)
- [Exercise: Run Liferay and Elasticsearch Using Docker](elasticsearch/exercise-run-liferay-and-elasticsearch-using-docker.md)
- [Troubleshooting Elasticsearch Installation](elasticsearch/troubleshooting-elasticsearch-installation.md)
- [Using the Sidecar or Embedded Elasticsearch](elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md)

- _[Clustering Liferay]_ [Add a Search Engine to a Liferay Cluster](../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md#start-a-search-engine-server)

## Upgrading

- [Upgrading Elasticsearch](elasticsearch/upgrading-elasticsearch.md)
- [Upgrading Search Infrastructure](elasticsearch/upgrading-elasticsearch/upgrading-search-infrastructure.md)
- [Backing Up Elasticsearch](elasticsearch/upgrading-elasticsearch/backing-up-elasticsearch.md)
- [Upgrading to Elasticsearch 7](elasticsearch/upgrading-elasticsearch/upgrading-to-elasticsearch-7.md)

## Securing

- [Securing Elasticsearch](elasticsearch/securing-elasticsearch.md)

## Configuring

- [Advanced Configuration of the Liferay Elasticsearch Connector](elasticsearch/advanced-configuration-of-the-liferay-elasticsearch-connector.md)
- [Elasticsearch Connector Configuration Reference](elasticsearch/elasticsearch-connector-configuration-reference.md)
