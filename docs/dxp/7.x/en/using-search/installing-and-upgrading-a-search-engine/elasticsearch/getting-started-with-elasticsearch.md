# Getting Started with Elasticsearch

> Latest Supported Elasticsearch Version: 7.9 \
> Available: Liferay DXP/CE 7.3 and  Liferay DXP 7.2 SP1+

The Elasticsearch stack comprises:

* Elasticsearch server
* Elasticsearch connector 
* Elasticsearch monitoring (optional)

Here's an overview of the setup steps:

1. Determine the Elasticsearch server and connector to use.

1. Set up your Elasticsearch server(s).

1. Connect Liferay to Elasticsearch. 

1. Secure communication with Elasticsearch.

1. Set up monitoring (optional).

## Elasticsearch and Connector Versions 

The [compatibility matrix](https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix) shows the latest supported Elasticsearch component versions for each Liferay version. The Elasticsearch server and the connector to Elasticsearch are required components. Elasticsearch security is required for production.

```warning::
   The Liferay Connector to Elasticsearch that your Liferay installation includes may not be the latest connector. Make sure to use the latest connector for the Elasticsearch version you're using. The connectors are available on `Liferay Marketplace <https://web.liferay.com/marketplace>`_.
```

## Installing Elasticsearch 

Elasticsearch can be installed using the Elasticsearch archive or Docker image. It can be configured on a cluster of servers too. Here are some different Elasticsearch hosting examples:

* [Installing Elasticsearch from an archive](./installing-elasticsearch.md)
* [Installing Elasticsearch using Docker](./exercise-installing-elasticsearch.md)

Also the [Liferay clustering example](../../../installation-and-upgrades/setting-up-liferay-dxp/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md) demonstrates using a Liferay cluster with Elasticsearch.

## Connecting Liferay to Elasticsearch 

A connector to Elasticsearch is bundled with Liferay. The latest Liferay Connector to Elasticsearch application is also available on Liferay Marketplace. See [Connecting to Elasticsearch](./connecting-to-elasticsearch.md) for details on connecting your Liferay version to Elasticsearch.

## Securing Elasticsearch 

In production, you should secure Liferay's communication with Elasticsearch. [Securing Elasticsearch](./securing-elasticsearch.md) explains how to configure authentication and encryption. It also demonstrates using PEM certificates, required for Elasticsearch monitoring.

## What's Next 

[Installing Elasticsearch](./installing-elasticsearch.md) explains each manual installation and configuration step. These steps are helpful to understand, even if you're running on Docker. If you'd rather start with running Elasticsearch and Liferay 7.3 on Docker, visit [Exercise Installing Elasticsearch](./exercise-installing-elasticsearch.md).