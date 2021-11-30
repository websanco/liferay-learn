# Installing Liferay

```{toctree}
:maxdepth: 2

installing-liferay/installing-a-liferay-tomcat-bundle.md
installing-liferay/configuring-a-database.md
installing-liferay/running-liferay-for-the-first-time.md
installing-liferay/using-liferay-docker-images.md
installing-liferay/installing-liferay-on-an-application-server.md
```

Liferay runs on an application server. There are three ways to host Liferay: 

* Using a Docker container
* Using a Liferay Tomcat bundle, on premises
* On a supported application server of your choice, on premises 

The fastest way to host Liferay is with a pre-configured Docker image on the cloud. Use the image with any cloud provider and [configure it](./installing-liferay/using-liferay-docker-images.md) by using environment variables. 

The Liferay Tomcat bundle is an archive you can extract onto any server, configure, and run. It's a lightweight Tomcat application server with Liferay already installed and ready to configure. 

Of course, you can always install Liferay on any supported application server, on premises. 

```{note}
Enterprise subscribers can [deploy Liferay DXP as a service](https://learn.liferay.com/dxp-cloud/latest/en/using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.html) on Liferay DXP Cloud. DXP Cloud reduces infrastructure costs (hardware, electricity bills), scales fast to handle more customers, provides a faster time to market, and is easy use.

If you're interested in DXP Cloud, please see the DXP Cloud [product information](https://www.liferay.com/products/dxp-cloud) or contact a Liferay representative.
```

If you want to develop on Liferay on your own machine, consider these convenient options:

* [Liferay Docker Image](#docker-image)
* [Liferay Tomcat Bundle](#liferay-tomcat-bundle)

## Docker Image

Liferay's Docker images are the fastest way to start using Liferay. The images come with Liferay pre-installed on a Tomcat application server. To get started, visit the [Using Liferay Docker Images](./installing-liferay/using-liferay-docker-images.md).

## Liferay Tomcat Bundle

The Liferay Tomcat Bundle is a ZIP file that has Liferay pre-installed on Tomcat. It's an easy way to start using Liferay outside of a container.

To get started with a bundle, see [Installing a Liferay Tomcat Bundle](./installing-liferay/installing-a-liferay-tomcat-bundle.md).

```{warning}
In the Docker image and Tomcat bundle, Liferay is configured to use an embedded HSQL database by default. Beyond demonstration purposes, we recommend using a full-featured, [supported RDBMS](https://help.liferay.com/hc/en-us/articles/360049238151). See [Database Configurations](./reference/database-configurations.md) for configuration instructions.
```

## Installing Liferay on an Application Server

You can install Liferay on any [supported application server](https://help.liferay.com/hc/en-us/articles/360049238151). This is typically the most practical installation type to use in DevOps and high availability environments.

To get started,

1. Choose a supported application server from the [compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151).
1. Follow the instructions for installing Liferay on that [application server](./installing-liferay/installing-liferay-on-an-application-server.md).

## What's Next 

After installing Liferay, go to [Setting Up Liferay](./setting-up-liferay.md) to configure these options:

* Search
* Localization
* Mail 
* File Storage
* Marketplace apps
* High availability
* and more

## Additional Information

* [Backing Up](./maintaining-a-liferay-installation/backing-up.md)
* [Updating Liferay](./maintaining-a-liferay-installation/updating-liferay.md)
* [Securing Liferay](./securing-liferay.md)