# Updating Liferay

Staying current with updates gives you the best security and quality. 

* **Security Updates** are releases that address the latest security issues immediately.

* **Updates** are releases that have the latest security updates, fix confirmed bugs, and include new features. The features are disabled by default---you can opt-in to them in the UI when you want.

Here you'll learn how to update to a new Liferay Docker image, update to a new Liferay Tomcat Bundle, and update an application server Liferay installation.

```{warning}
**Always** [back up](./backing-up.md) your database and installation before updating Liferay DXP/Portal.
```

```{important}
Liferay DXP 7.3 SP2 and earlier use a patching model---it's different from the model described here. If you're on 7.3 SP2 and earlier, please see [Patching DXP](./patching-dxp-7-3-and-earlier.md).
```

```{note}
Liferay DXP/Portal general availability (GA) releases are built from the source code. Updates and Security Updates follow the GA and are also built from the source code.
```

## Updating to a New Docker Image

If you're running Liferay DXP/Portal on Docker, here's how to update:

1. Shutdown your current Docker container.

1. Clean up the Liferay cache.

    Delete the `[Liferay Home]/osgi/state` folder.

    ```bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    Empty the `[Liferay Home]/work` folder.

    ```bash
    rm -rf work/*
    ```

    Delete the application server cache. Please consult the application server vendor's documentation on where where to find the cache.

    ```{note}
    If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next server startup ensures that such modules reinstall with the appropriate state.
    ```

1. Find the Liferay Docker image and tag information on Docker Hub:

    * [Liferay DXP Images](https://hub.docker.com/r/liferay/dxp)
    * [Liferay Portal Images](https://hub.docker.com/r/liferay/portal)

1. Check the release notes for any database changes or index changes.

    If there are database changes, enable database upgrades to run automatically using this environment setting, later in your `docker run` command:

    ```bash
    -e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true
    ```

    If there are index changes, enable index updates using this environment setting, later in your `docker run` command:

    ```bash
    -e LIFERAY_DATABASE_PERIOD_INDEXES_PERIOD_UPDATE_PERIOD_ON_PERIOD_STARTUP=true
    ```

1. Run the new Docker image with your current environment and parameters, and with any required database/index environment settings (from previous step). For example, here's a command for running an image that [bind mounts](../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md) a local folder called `liferay` to the new image.

    ```bash
    docker run -it -m 8g -p 8080:8080 \
     -v $(pwd)/liferay:/mnt/liferay \
     liferay/[place image name here]:[place tag here]
    ```

1. If you enabled database upgrades or index updates in your `docker run` command, the console and log report all upgrade failures, errors, and additional optional modules to upgrade. You can use [Gogo Shell commands](../upgrading-liferay/upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to address them. When the upgrades complete successfully, stop the Docker container and create a new container, executing your `docker run` command *without* any database upgrade and index update environment settings.

You're running on the new Liferay update Docker image.

## Updating to a New Liferay Tomcat Bundle

If you're running a Liferay DXP/Portal Tomcat Bundle, here's how to update:

1. Download the Liferay DXP/Portal Tomcat Bundle update you want from [Help Center](https://help.liferay.com/hc) (subscription) or [Community Downloads](https://www.liferay.com/downloads-community).

1. Shut down your application server.

    Reasons:

    * On Unix-style systems, you can usually replace files that are running, but the old ones reside in memory.
    * On Windows systems, files in use are locked and can't be patched.

1. Replace the new bundle's `[Liferay Home]/data` folder with the `[Liferay Home]/data` folder from your [backup](./backing-up.md).

1. Copy your DXP activation key (Subscription) and your [OSGi configuration files](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) from your [backup](./backing-up.md#liferay-home) to the new installation.

1. If the release notes mention database changes, use a compatible [database upgrade option](../upgrading-liferay/reference/database-upgrade-options.md) to apply all required changes and any optional changes you want.

1. If the release notes mention index updates, configure Liferay to update the indexes on startup. Set the [`database.indexes.update.on.startup`](../../../../../reference/latest/en/dxp/propertiesdoc/portal.properties.html#Database) Portal Property to `true` in a [`portal-ext.properties` file](../reference/portal-properties.md). For example,

    ```properties
    database.indexes.update.on.startup=true
    ```

    Only indexes that start with `LIFERAY_` OR `IX_` are updated. Make sure any custom indexes you have do not use this naming convention.

1. Start the application server.

You're running on the Liferay update Tomcat Bundle.

## Updating an Application Server Installation

If you're running Liferay DXP/Portal on an existing application server, here's how to update:

1. Download the Update's `.war` file and OSGi Dependencies ZIP file.

    * DXP: Help Center [Downloads](https://customer.liferay.com/downloads)
    * Portal: Liferay Community [Downloads](https://www.liferay.com/downloads-community)

1. Shut down your application server.

    Reasons:

    * On Unix-style systems, you can usually replace files that are running, but the old ones reside in memory.
    * On Windows systems, files in use are locked and can't be patched.

1. Extract the `.war` file contents on top of your existing Liferay web application in your application server installation.

    Here are links to more information about Liferay installation on supported application servers:

    * [Tomcat](../installing-liferay/installing-liferay-on-an-application-server/installing-on-tomcat.md)
    * [WildFly](../installing-liferay/installing-liferay-on-an-application-server/installing-on-wildfly.md)
    * [JBoss EAP](../installing-liferay/installing-liferay-on-an-application-server/installing-on-jboss-eap.md)
    * [WebLogic](../installing-liferay/installing-liferay-on-an-application-server/installing-on-weblogic.md)
    * [WebSphere](../installing-liferay/installing-liferay-on-an-application-server/installing-on-websphere.md)

1. Merge the contents of the OSGi Dependencies ZIP file into your `[Liferay Home]/osgi` folder.

1. Clean up the Liferay cache.

    Delete the `[Liferay Home]/osgi/state` folder.

    ```bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    Empty the `[Liferay Home]/work` folder.

    ```bash
    rm -rf work/*
    ```

    Delete the application server cache. Please consult the application server vendor's documentation on where where to find the cache.

    ```{note}
    If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next server startup ensures that such modules reinstall with the appropriate state.
    ```

1. If the release notes mention database changes, use a compatible [database upgrade option](../upgrading-liferay/reference/database-upgrade-options.md) to apply all required changes and any optional changes you want.

1. If the release notes mention index updates, configure Liferay to update the indexes on startup. Set the [`database.indexes.update.on.startup`](../../../../../reference/latest/en/dxp/propertiesdoc/portal.properties.html#Database) Portal Property to `true` in a [`portal-ext.properties` file](../reference/portal-properties.md). For example,

    ```properties
    database.indexes.update.on.startup=true
    ```

    Only indexes that start with `LIFERAY_` OR `IX_` are updated. Make sure any custom indexes you have do not use this naming convention.

1. Start the application server again.

Congratulations! Your Liferay instance is updated and running.

## Additional Information

* [Backing Up](./backing-up.md)
* [Applying a Hotfix](./applying-a-hotfix.md)
* [Database Upgrade Options](../upgrading-liferay/reference/database-upgrade-options.md)