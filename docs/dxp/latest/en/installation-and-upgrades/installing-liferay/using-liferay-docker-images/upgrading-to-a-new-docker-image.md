# Upgrading to a New Docker Image

As new Liferay versions are released as Docker images, you can upgrade to them. The [Upgrade Overview](../../upgrading-liferay/upgrade-basics/upgrade-overview.md) describes the process.

```important::
   **Always** `back up <../../maintaining-a-liferay-dxp-installation/backing-up.md>`_ your data and installation before upgrading. Testing the upgrade process on backup copies is advised.
```

Upgrading involves updating configurations, Marketplace applications, and custom code. Perhaps the biggest part of the upgrade is upgrading the Liferay database. There are two ways to upgrade the database:

* [Using Auto-Upgrade in the Docker Image on Liferay Startup](../../upgrading-liferay/upgrade-basics/upgrading-via-docker.md): Running the new Liferay Docker image with auto-upgrade enabled upgrades your database on Liferay startup. After the upgrade completes, you can continue using Liferay via the Docker container. Portal CE environments that are simple and have small data sets can be upgraded this way.

* [Using the Database Upgrade Tool](../../upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.md): Upgrading a database using the Database Upgrade Tool must be done in a non-containerized Liferay installation. After the upgrade completes, you can point a new Liferay Docker container to it. DXP environments and Portal CE environments that are complex, have larger data sets, or have many customizations should be upgraded using the Database Upgrade Tool.

## What's Next

Start by reading the [Upgrade Overview](../../upgrading-liferay/upgrade-basics/upgrade-overview.md). Then if you are interested in using auto-upgrade in the new container, see [Upgrading via Docker](../../upgrading-liferay/upgrade-basics/upgrading-via-docker.md). If your environment calls for using the Database Upgrade Tool, see [Using the Database Upgrade Tool](../../upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.md).
