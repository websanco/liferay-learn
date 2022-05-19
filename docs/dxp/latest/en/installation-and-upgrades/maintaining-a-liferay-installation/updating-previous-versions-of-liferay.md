# Updating Previous Versions of Liferay

Staying current with updates gives you the best security and quality. 

* **Security Updates** are releases that address the latest security issues immediately.

* **Updates** are releases that have the latest security updates, fix confirmed bugs, and include new features. The features are disabled by default, but you can opt-in to them in the UI when you want.

Here you'll learn how to update to a new Liferay Docker image, update to a new Liferay Tomcat Bundle, and update an application server Liferay installation.

```{warning}
**Always** [back up](./backing-up.md) your database and installation before updating Liferay DXP/Portal.
```

```{important}
Versions before Liferay DXP 7.3 SP3 use a patching model instead. If you're on a version before Liferay DXP 7.3 SP3, please see [Patching DXP](./patching-dxp-7-3-and-earlier.md).
```

```{note}
Liferay DXP/Portal general availability (GA) releases are built from the source code. Updates and Security Updates follow the GA and are also built from the source code.
```

## Updating to a New Docker Image

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

    Delete the application server cache. Please consult the application server vendor's documentation for where where to find the cache.

    ```{note}
    If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next server start ensures that such modules reinstall with the appropriate state.
    ```

1. Find the Liferay Docker image and tag information on Docker Hub:

   * [Liferay DXP Images](https://hub.docker.com/r/liferay/dxp)
   * [Liferay Portal Images](https://hub.docker.com/r/liferay/portal)

1. Check the release notes for any database changes or index changes.

    If there are database changes, enable database upgrades to run automatically using this environment setting in your `docker run` command:

    ```bash
    -e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true
    ```

    If there are index changes, enable index updates using this environment setting in your `docker run` command:

    ```bash
    -e LIFERAY_DATABASE_PERIOD_INDEXES_PERIOD_UPDATE_PERIOD_ON_PERIOD_STARTUP=true
    ```

1. Run the new Docker image with your current environment and parameters, and with any required database/index environment settings (from the previous step). For example, here's a command for running an image that [bind mounts](../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md) a local folder called `liferay` to the new image.

    ```bash
    docker run -it -m 8g -p 8080:8080 \
     -v $(pwd)/liferay:/mnt/liferay \
     liferay/[place image name here]:[place tag here]
    ```

1. If you enabled database upgrades or index updates in your `docker run` command, the console and log report all upgrade failures, errors, and additional optional modules to upgrade. You can use [Gogo Shell commands](../upgrading-liferay/upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to address them. When the upgrades complete successfully, stop the Docker container and create a new container, executing your `docker run` command *without* any database upgrade and index update environment settings.

You're running on the new Liferay update Docker image.

## Updating to a New Liferay Tomcat Bundle

1. Export your modified System Settings (including your [File Storage](../../system-administration/file-storage.md) and [Elasticsearch](../../using-search/installing-and-upgrading-a-search-engine/elasticsearch/connecting-to-elasticsearch.md) settings) to [`.config` files](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files) and copy them to your `[Liferay Home]/osgi/configs/` folder.

    For example, if you're using [Advanced File System Store](../../system-administration/file-storage.md) or [Simple File System Store](../../system-administration/file-storage/other-file-store-types/simple-file-system-store.md), export your file store settings to a [`.config` file](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files) and copy it to your `[Liferay Home]/osgi/configs/` folder. Here's an example `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config` file with the required `rootDir` parameter:

    ```properties
    rootDir="data/document_library"
    ```

1. If you're using [Commerce](https://learn.liferay.com/commerce/latest/en/index.html) and the release notes mention database upgrades for Commerce, prepare to upgrade it. See [Upgrading Liferay Commerce](https://learn.liferay.com/commerce/latest/en/installation-and-upgrades/upgrading-liferay-commerce.html) for details.

1. Shut down your application server.

    Reasons:

    * On Unix-style systems, you can usually replace files that are running, but the old ones reside in memory.
    * On Windows systems, files in use are locked and can't be patched.

1. [Back up](./backing-up.md) your installation.

1. Download the Liferay DXP/Portal Tomcat Bundle update you want from [Help Center](https://help.liferay.com/hc) (subscribers) or [Community Downloads](https://www.liferay.com/downloads-community).

1. Unzip the bundle to an arbitrary location.

1. Replace the new bundle's `[Liferay Home]/data` folder with the `[Liferay Home]/data` folder from your [backup](./backing-up.md).

1. Copy these files from your [backup](./backing-up.md) to the new installation:

    * Configuration files (`.config` files)
    * DXP activation key (subscribers)
    * [Portal Properties](../reference/portal-properties.md) (e.g., `portal-ext.properties`)

    See [Migrating Configurations and Properties](../upgrading-liferay/migrating-configurations-and-properties.md) for additional information.

1. Replicate your Tomcat customizations (e.g., `[tomcat version]/conf` folder contents and added libraries) from your [backup](./backing-up.md) to the new installation.

1. Copy your custom widgets and modules to the new installation.

1. If the release notes mention database changes, use a compatible [database upgrade option](../upgrading-liferay/reference/database-upgrade-options.md) to apply all required changes and any optional changes you want.

1. Start the application server.

You're running on the Liferay update Tomcat Bundle.

## Updating an Application Server Installation

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

1. Start the application server again.

Congratulations! Your Liferay instance is updated and running.

## Additional Information

* [Backing Up](./backing-up.md)
* [Applying a Hotfix](./applying-a-hotfix.md)
* [Database Upgrade Options](../upgrading-liferay/reference/database-upgrade-options.md)
