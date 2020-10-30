# Getting Started with Elasticsearch

> Latest Supported Elasticsearch Version: 7.9 \
> Available: Liferay DXP/CE 7.3 and  Liferay DXP 7.2 SP1+

A typical Liferay system's search infrastructure comprises

* Liferay servers in a clustered or single node (on-premises or Docker containers)
* Elasticsearch server(s) (on-premises or Docker containers)
* An Elasticsearch connector application deployed* onto Liferay and configured to fit your deployment.

```tip::
   Additional features and intergrations are available with a `Liferay Enterprise Search <../../liferay_enterprise_search.rst>`__ subscription.
```

> Note: Each Liferay CE and DXP version is bundled with a _connector_ to Elasticsearch that is compatible with a specific Elasticsearch major version. To add support for newer major Elasticsearch versions for released Liferay Portal and DXP versions, Liferay can release additional connectors through Marketplace which can be used as drop-in replacements for the out-of-the-box (bundled) connectors. If you are using the bundled connector, you do not need to deploy it separately.

Assuming your Liferay servers are in place already, the remaining steps include

1. Determining the Elasticsearch server and connector version to use

1. Setting up your Elasticsearch server(s)

1. Connecting Liferay to Elasticsearch

## Finding Compatible Elasticsearch and Connector Versions 

The [compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) shows the latest supported Elasticsearch component versions for each Liferay version. The Elasticsearch server and the connector to Elasticsearch are required components.

```warning::
   The Liferay Connector to Elasticsearch that your Liferay installation includes may not be the latest connector. Make sure to use the latest connector for the Elasticsearch version you're using. The connectors are available on `Liferay Marketplace <https://web.liferay.com/marketplace>`_.
```

## Installing Elasticsearch 

Elasticsearch can be installed using an Elasticsearch archive or Docker image. It can be configured on a cluster of servers too. Here are some different Elasticsearch hosting examples:

* [Installing Elasticsearch from an archive](./installing-elasticsearch.md)
* [Installing Elasticsearch using Docker](./exercise-installing-elasticsearch.md)

Also the [Liferay clustering example](../../../installation-and-upgrades/setting-up-liferay-dxp/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md) demonstrates using a Liferay cluster with Elasticsearch.

## Connecting Liferay to Elasticsearch 

A connector to Elasticsearch is bundled with Liferay. The latest Liferay Connector to Elasticsearch application is also available on Liferay Marketplace. See [Connecting to Elasticsearch](./connecting-to-elasticsearch.md) for details.

## Securing Elasticsearch 

In production, you should secure Liferay's communication with Elasticsearch. [Securing Elasticsearch](./securing-elasticsearch.md) explains how to configure authentication and encryption and demonstrates using PEM certificates, required for Liferay Enterprise Search Monitoring.

## What's Next 

[Installing Elasticsearch](./installing-elasticsearch.md) explains each manual installation and configuration step. These steps are helpful to understand, even if you're running on Docker. If you'd rather start with running Elasticsearch and Liferay on Docker, visit [Exercise Installing Elasticsearch](./exercise-installing-elasticsearch.md).
