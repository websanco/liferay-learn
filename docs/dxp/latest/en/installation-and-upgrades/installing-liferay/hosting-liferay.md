# Hosting Liferay

Liferay runs on an application server. There are three ways to host Liferay: 

- Using a Docker container
- Using a Liferay Tomcat bundle, on premises
- On a supported application server of your choice, on premises 

The fastest way to host Liferay is with a pre-configured Docker image on the cloud. Use the image with any cloud provider and [configure it](./using-liferay-docker-images/docker-container-basics.md) by using environment variables. 

The Liferay Tomcat bundle is an archive you can extract onto any server, configure, and run. It's a lightweight Tomcat application server with Liferay already installed and ready to configure. 

Of course, you can always install Liferay on any supported application server, on premises. 

```note::
   Enterprise subscribers can `deploy Liferay DXP as a service <../../../../../dxp-cloud/latest/en/using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md>`_ on Liferay DXP Cloud. DXP Cloud reduces infrastructure costs (hardware, electricity bills), scales fast to handle more customers, provides a faster time to market, and is easy use.

   If you're interested in DXP Cloud, please see the DXP Cloud `product information <https://www.liferay.com/products/dxp-cloud>`_ or contact a Liferay representative.
```

If you want to develop on Liferay on your own machine, consider these convenient options:

* [Liferay Docker Image](#docker-image)
* [Liferay Tomcat Bundle](#liferay-tomcat-bundle)

## Docker Image

Liferay's Docker images are the fastest way to start using Liferay. The images come with Liferay pre-installed on a Tomcat application server. To get started, visit the [Docker Container Basics](./using-liferay-docker-images/docker-container-basics.md).

## Liferay Tomcat Bundle

The Liferay Tomcat Bundle is a ZIP file that has Liferay pre-installed on Tomcat. It's an easy way to start using Liferay outside of a container.

To get started with a bundle, see [Installing a Liferay Tomcat Bundle](./installing-a-liferay-tomcat-bundle.md).

```warning::
   In the Docker image and Tomcat bundle, Liferay is configured to use an embedded HSQL database by default. Beyond demonstration purposes, we recommend using a full-featured, `supported RDBMS <https://help.liferay.com/hc/en-us/articles/360049238151>`_. See `Database Configurations <../reference/database-configurations.md>`_ for configuration instructions.
```
## Installing Liferay on an Application Server

You can install Liferay on any [supported application server](https://help.liferay.com/hc/en-us/articles/360049238151). This is typically the most practical installation type to use in DevOps and high availability environments.

To get started,

1. Choose a supported application server from the [compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151).
1. Follow the instructions for installing Liferay on that [application server](./installing_liferay_on_an_application_server.html).


## What's Next 

After installing Liferay, go to [Setting Up Liferay](../setting-up-liferay.md) to configure these options:

* Search
* Localization
* Mail 
* File Storage
* Marketplace apps
* High availability
* and more

## Additional Information

* [Backing Up](../maintaining-a-liferay-dxp-installation/backing-up.md)
* [Patching Liferay DXP](../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md)
* [Securing Liferay](../securing-liferay/securing-liferay.md)
