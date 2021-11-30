# Updating Liferay

Liferay DXP/Portal general availability (GA) releases are built from the source code. Updates and Security Updates follow the GA and are also built from the source code.

* **Security Updates** are releases that address the latest security issues immediately.

* **Updates** are releases that fix confirmed bugs and include new features. The features are disabled by default---you can opt-in to them in the UI when you want.

Staying current with updates gives you the best security and quality.

```{warning}
**Always** [back up](./backing-up.md) your database and installation before updating.
```

```{important}
Liferay DXP 7.3 and earlier use a patching model---it's different from the model described above. If you're on 7.3 and earlier, please see [Patching Liferay](./patching-dxp-7-3-and-earlier.md).
```

## Update Steps

Here's how to update your installation:

1. Download the Update's `.war` file and OSGi Dependencies ZIP file.

    * DXP: Help Center [Downloads](https://customer.liferay.com/downloads)
    * Portal: Liferay Community [Downloads](https://www.liferay.com/downloads-community)]

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