# Upgrade Overview

Liferay's tools and instructions facilitate upgrading DXP and Portal CE environments safely and quickly. Non-clustered environments that have small data sets, for example can be upgraded using a [Docker image](./upgrading-via-docker.md). DXP environments and Portal CE environments that are complex, have larger data sets, or have many customizations are upgraded using the [Database Upgrade Tool](./using-the-database-upgrade-tool.md).

```warning::
   **Always** `back up <../../maintaining-a-liferay-dxp-installation/backing-up.md>`_ your database and installation before upgrading. Testing the upgrade process on backup copies is advised.
```

```note::
   `Upgrading via Docker <./upgrading-via-docker.md>`_ is the easiest way to upgrade a database for a small, casual Portal CE environment.
```

Before starting your upgrade, consider what may be involved. The upgrade topics fall into these categories:

* [Preparation and Planning](#preparation-and-planning)
* [Updating Custom Plugin Code](#updating-custom-plugin-code)
* [Migrating Configurations and Infrastructure](#migrating-and-updating-configurations-and-infrastructure)
* [Improving Upgrade Performance](#improving-upgrade-performance)
* [Executing the Database Upgrade](#executing-the-database-upgrade)

## Preparation and Planning

Preparation and planning may be of little consequence for small, casual installations but is *mandatory* for large enterprise-level installations.

### Review Available Upgrade Paths

Look up your current Liferay DXP/Portal version in this table to determine your installation upgrade path.

| Upgrade Path                            | Description |
| --------------------------------------- | ----------- |
| Liferay DXP/Portal 6.2+ &rarr; DXP 7.3      |             |
| Liferay Portal 6.1.x &rarr; DXP/Portal 7.1 &rarr; DXP 7.3 | Support life ended for Liferay Portal 6.1 |
| Liferay Portal 5.x and 6.0.x &rarr; Liferay Portal 6.2 &rarr; Liferay DXP 7.3 | Support life ended for Liferay Portal 5.0, 5.1, 5.2, and 6.0 |

If your path includes upgrading to Liferay Portal 6.2, follow the [Liferay Portal 6.2 upgrade instructions](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay) first.

### Review Deprecations and Changes to Default Settings

Features and configuration defaults may change in new versions of Liferay. See the reference section or the following articles for the most recent deprecations and changes to features and to settings:

* [Deprecations](../reference/deprecations-in-liferay-dxp-7-3.md)
* [Features in Maintenance Mode](../reference/features-in-maintenance-mode.md)
* [Changes to Default Settings](../reference/changes-to-default-settings.md)

### Request an Upgrade Patch (Subscription)

> Subscription

If you have a Liferay DXP subscription, update to the latest fix pack and/or request an upgrade patch to prepare for the database upgrade. File a ticket in the [Help Center](https://help.liferay.com/hc/requests/new) to start this process.

### Update Marketplace Apps

Marketplace apps should be updated to the latest version for the Liferay version you're currently on before upgrading the Liferay database. Skipping app updates can be problematic and prevent the apps from enabling on the new Liferay version.

```important::
   Do this on your current installation before upgrading.
```

After upgrading the database, install the latest app versions for your new Liferay instance.

## Updating Custom Plugin Code

Plugins (e.g., themes, apps, and customizations) you've developed must be adapted to the new Liferay version. This can be as simple as updating dependencies or involve updating code to API changes. If you forgo updating your custom plugins, they may be disabled on the new Liferay version. [Upgrading Code](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2) walks through the code upgrade process.

## Migrating and Updating Configurations and Infrastructure

Configurations and supporting infrastructure must be migrated and updated from your previous installation to your new one.

```important::
   If you're upgrading from 6.2 or earlier, update your file store configuration. See the `Updating the File Store <../configuration-and-infrastructure/updating-the-file-store.md>`_ for more information.
```

You can wait until after database upgrade to update your other settings. See these [Migrating Configurations and Properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md) for more information.

## Improving Upgrade Performance

Upgrading large data sets can take a prohibitively long time, if you leave unnecessary data intact or forgo performance tuning. There are several ways to quicken database upgrades.

### Prune Data

If your Liferay server has instances, sites, pages, or versioned content items (e.g., Web Content articles, Documents and Media files, and more) that are unnecessary, removing them can cut down upgrade time considerably. See [Database Pruning for Faster Upgrades](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md) on ways to prune your database of unnecessary data.

### Tune Database Performance

Adjusting your database for upgrade operations (more data writes than in production) improves database upgrade performance. See [Database Tuning for Upgrades](../upgrade-stability-and-performance/database-tuning-for-upgrades.md) for details.

### Tune the Search Engine

The search engine typically indexes regularly while the Liferay server is running. However, this indexing can have a detrimental impact to upgrade performance if it is left on when upgrading the database during server startup. Even if Liferay is offline during database upgrade, upgrade processes that call services that call a search server produce errors if indexing is on. Indexing should be disabled before performing an upgrade, and re-enabled once an upgrade is complete. The database upgrade steps and post upgrade steps demonstrate configuring the indexing.

## Executing the Database Upgrade

There are two ways to upgrade your Liferay database:

* [Upgrade via Docker](./upgrading-via-docker.md) involves passing an auto upgrade parameter to the command for starting a Docker image. The image updates the database and then starts up using the upgraded database.

* [Using the Database Upgrade Tool](./using-the-database-upgrade-tool.md). The Upgrade Tool is a client program for updating the Liferay database while it's detached from any Liferay instance. It facilitates focusing on the upgrade process, [tuning the database](../upgrade-stability-and-performance/database-tuning-for-upgrades.md) for upgrade operations, and [pruning unnecessary data](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md) to quicken the database upgrade.

## Conclusion

Once you complete the tasks outlined above, your upgrade is complete. But before using Liferay, you must re-establish desired runtime settings and undo any upgrade-specific tuning. Plus there may be applications that weren't available on your previous Liferay version that are recommended for new Liferay production instances. See the [Post-Upgrade Considerations](./post-upgrade-considerations.md) for more information.

Now that you're familiar with the upgrade components, you can upgrade your Liferay instance. Make sure to do preliminary tasks on your current installation, before upgrading the database. It's common to upgrade custom plugin code and execute the database upgrade in parallel. Since, database upgrade is a popular task to start first, here are links to the two methods:

* [Upgrading Via Docker](./upgrading-via-docker.md)
* [Using the Database Upgrade Tool](./using-the-database-upgrade-tool.md)

Additionally, refer to these other upgrade scenarios if they relate to your upgrade:

* [Upgrading a Sharded Environment](../other-upgrade-scenarios/upgrading-a-sharded-environment.md)
* [Maintaining Clustered Installations](../../maintaining-a-liferay-dxp-installation/maintaining-clustered-installations/maintaining-clustered-installations.md)