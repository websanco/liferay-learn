# Installing a Liferay-Tomcat Bundle

The Tomcat Bundle includes the Apache Tomcat application server with Liferay DXP/Portal pre-deployed. It's the easiest, fastest way to install Liferay on premises.

```{note}
If you're using one of the following application servers already, click the name of the one you're using to see instructions for deploying Liferay to it: [Tomcat](./installing-liferay-on-an-application-server/installing-on-tomcat.md), [WildFly](./installing-liferay-on-an-application-server/installing-on-wildfly.md), [JBoss EAP](./installing-liferay-on-an-application-server/installing-on-jboss-eap.md), [WebLogic](./installing-liferay-on-an-application-server/installing-on-weblogic.md), or [WebSphere](./installing-liferay-on-an-application-server/installing-on-websphere.md).
```

```{note}
To start an instance fast for touring or demonstration purposes, see [Starting With a Docker Image](../../getting-started/starting-with-a-docker-image.md).
```

## Prerequisites

A Java JDK 8 or 11 is required. See [the compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) to choose a JDK. See [JVM Configuration](../reference/jvm-configuration.md) for recommended settings.

## Download

1. Go to the [Help Center](https://help.liferay.com/hc) (subscription) or [Community Downloads](https://www.liferay.com/downloads-community).

1. Navigate to the Liferay DXP/Portal version you want.

1. Download a Tomcat Bundle:

| File | Description |
| :--- | :---------- |
| Tomcat Bundle (`.tar.gz`) | GZipped bundle archive that installs on any OS |
| Tomcat Bundle (`.7z`) | 7-Zipped bundle archive that installs on any OS |

## Install

Extract the bundle to a location on your host. This location is called your [Liferay Home](../reference/liferay-home.md).

Congratulations! You've installed Liferay. It's time to configure a database for it.

## Next Steps

* [Configuring a Database](./configuring-a-database.md)