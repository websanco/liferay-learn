# Data Cleanup

When you're done using an obsolete Liferay app or capability, you can remove its data. You can use the Control Panel or a [configuration file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) to do data cleanup.

Here's how to clean up data from obsolete features:

1. In the *Control Panel*, navigate to *System Settings* &rarr; *Upgrades* (in the *Platform* category) &rarr; *Data Cleanup*. The Data Cleanup screen appears.

    ![Data Cleanup provides an interface for removing data from obsolete Liferay applications.](./data-cleanup/images/01.png)

1. Select the modules you want to clean up and click *Save*.

    ```{note}
    To save the cleanup settings to a [configuration file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md), click the *Actions* menu and select *Export*.
    ```

   The data cleanup executes.

Your server is unburdened of obsolete data and Data Cleanup is disabled automatically for all the modules.

```{note}
After executing Data Cleanup (even via a configuration file), DXP disables it automatically by default for every module. This prevents unnecessary, redundant data cleanup.

In versions earlier than 7.4, Data Cleanup settings persist. To disable cleanup in these older versions, uncheck every module in the Data Cleanup screen and click *Save* or set the module cleanup keys `false` in a `com.liferay.data.cleanup.internal.configuration.DataCleanupConfiguration.config` [configuration file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md).
```

```{note}
You can also clean up module data on server startup (in a [Liferay Docker container](../upgrade-basics/upgrading-via-docker.md) or on a local machine) using a  `com.liferay.data.cleanup.internal.configuration.DataCleanupConfiguration.config` [configuration file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md). After the cleanup, prevent subsequent server startups from running the cleanups again by removing the configuration file from the `osgi/configs` folder.
```

## Additional Information

* [Data Removal](./data-removal.md)
* [Using Configuration Files](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)
* [Upgrading Via Docker](../upgrade-basics/upgrading-via-docker.md)
* [Providing Files to the Container](../../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md)
* [Using the Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md)