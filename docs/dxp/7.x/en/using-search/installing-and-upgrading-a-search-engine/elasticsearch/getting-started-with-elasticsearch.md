# Getting Started with Elasticsearch

> Latest Supported Elasticsearch Version: 7.9 \
> Available: Liferay DXP 7.2 SP1+ and Liferay CE/DXP 7.3

Here's an overview of setting up Elasticsearch:

1. Determine the Elasticsearch server and connector client version to use.

1. Set up your Elasticsearch server(s).

1. Connect Liferay to Elasticsearch. 

1. Secure communication with Elasticsearch (required in production or if monitoring X-Pack security with Kibana).

## Elasticsearch and Connector Versions 

The [Compatibility Matrix](https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix) shows the latest Elasticsearch version (and corresponding Liferay Connector to Elasticsearch) that each Liferay version supports. Always install the latest supported version of Elasticsearch and the connector.

```warning::
   The Liferay Connector to Elasticsearch that your Liferay installation includes may not be the latest connector. Make sure to use the latest connector for the Elasticsearch version you're using. The connectors are available on `Liferay Marketplace <https://web.liferay.com/marketplace>`_.
```

## Installing Elasticsearch 

Elasticsearch can be installed and run locally on premises or using Docker. It can be configured on a cluster of servers too. Here are some different Elasticsearch hosting examples:

* [Installing Elasticsearch](./installing-elasticsearch.md) installs Elasticsearch manually.
* [Exercise Installing Elasticsearch](./exercise-installing-elasticsearch.md) installs Elasticsearch with a Docker `run` command
* [Elasticsearch Clustering with Docker](./elasticsearch-clustering-with-docker.md) installs Elasticsearch with Docker compose.

To walk through a quick setup of a single Liferay node remotely connected to a single Elasticsearch 7 node, see the [Exercise](./exercise-installing-elasticsearch.md).

To set up an example Liferay DXP cluster with a remote Elasticsearch connection, see the [Clustering for High Availability](../../../installation-and-upgrades/setting-up-liferay-dxp/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md) documentation.

## Connecting Liferay to Elasticsearch 

The Liferay 7.3 connector to Elasticsearch uses the [REST-based Elasticsearch Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html) while the Liferay 7.2 connector to Elasticsearch uses the [Java Transport Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html).

See [Connecting to Elasticsearch](./connecting-to-elasticsearch.md) for details on connecting your Liferay version to Elasticsearch.

## Securing Elasticsearch 

In production, you should secure communication between your Liferay and your Elasticsearch servers. [Securing Elasticsearch](./securing-elasticsearch.md) explains how to configure authentication, encryption, and data integrity. It also includes using PEM certificates, required when using Kibana to monitor Elasticsearch.

## What's Next 

[Installing Elasticsearch](./installing-elasticsearch.md) explains each manual installation and configuration step. Understanding these steps is helpful, even if you're running on Docker. If you'd rather start with running Elasticsearch and Liferay 7.3 on Docker, visit [Exercise Installing Elasticsearch](./exercise-installing-elasticsearch.md).