# Using the Database Upgrade Tool

The Liferay Database Upgrade Tool is a client program for upgrading Liferay DXP and Liferay Portal CE databases offline.

```important::
   **Always** `back up <../../maintaining-a-liferay-dxp-installation/backing-up.md>`_ your data and installation before upgrading. Testing the upgrade process on backup copies is advised.
```

```important::
   If you're upgrading from 6.2 or earlier, update your file store configuration. See the `Updating the File Store <../configuration-and-infrastructure/updating-the-file-store.md>`_ for more information.
```

Modifying a database while it's detached from your Liferay instance allows you to [tune the database for upgrade operations](../upgrade-stability-and-performance/database-tuning-for-upgrades.md), [prune unnecessary data](../upgrade-stability-and-performance/database-tuning-for-upgrades.md) (e.g., unneeded versions of Web Content, Documents, and more) to improve upgrade performance, and resolve upgrade issues. These activities are especially important for upgrading DXP and any large, critical Portal CE environments safely and as quickly as possible. After you've accounted for tuning and pruning the database and completing relevant tasks described in the [Upgrade Overview](./upgrade-overview.md), you're ready to setup up the new installation and upgrade the database using the upgrade tool.

If you're [upgrading to a new Liferay Docker image](../../installing-liferay/using-liferay-docker-images/upgrading-to-a-new-docker-image.md) and want to use the Database Upgrade Tool, you'll use that tool from a [Liferay Tomcat Bundle](../../installing-liferay/installing-a-liferay-tomcat-bundle.md) of the new Liferay version.

## Setting Up a New Installation

1. Install the new Liferay release. If you're upgrading your database for a new Liferay Docker image, install the [Liferay Tomcat Bundle](../../installing-liferay/installing-a-liferay-tomcat-bundle.md).

1. Replace the new installation's `[Liferay Home]/data` folder with the `[Liferay Home]/data` folder from your [backup](../../maintaining-a-liferay-dxp-installation/backing-up.md).

1. Set up the [File Store (Document Library)](../../../system-administration/file-storage/configuring-file-storage.md) by copying it from your [backup](../../maintaining-a-liferay-dxp-installation/backing-up.md) to the new installation and or configuring the new installation to use it via a [`.config` file](../../../system-administration/configuring-liferay/understanding-configuration-scope.md).

1. Copy your DXP activation key (Subscription) and your OSGi configuration files from your [backup](../../maintaining-a-liferay-dxp-installation/backing-up.md#liferay-home) to the new installation.

1. Make sure you're using the JDBC database driver your database vendor recommends. If you're using MySQL, for example, set `jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver` in [`portal-ext.properties`](../../reference/portal-properties.md) and replace the MySQL JDBC driver JAR your app server uses. See [Database Drivers](../configuration-and-infrastructure/migrating-configurations-and-properties.md#database-drivers) for more details.

    If you're [upgrading to a new Liferay Docker image](../../installing-liferay/using-liferay-docker-images/upgrading-to-a-new-docker-image.md), make sure to specify your database connection using [Portal Properties](../../reference/portal-properties.md) instead of Docker env variables. The [Portal Properties reference](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html) lists the corresponding Portal Property with each Liferay Env variable.

1. If your installation is a Liferay Tomcat Bundle, it includes the upgrade tool at `[Liferay Home]/tools/portal-tools-db-upgrade-client`. Otherwise download the tool and install it to that folder.

| DXP Edition | Download Instructions |
| :---------- | :-------------------- |
| Liferay DXP (Subscription) | Go to the [*Downloads* page](https://customer.liferay.com/group/customer/downloads) and select the DXP version and the _Product/Service Packs_ file type. In the listing that appears, click _Download_ for the _Liferay DXP Upgrade Client_. |
| Liferay Portal CE | Go to the [_Downloads_ page](https://www.liferay.com/downloads-community). In the _Liferay Portal_ downloads menu, select _Other files_ and click _Download_. The latest Liferay Portal release assets GitHub page appears. Click on `liferay-ce-portal-tools-[version].zip`. |

## Running the Upgrade Tool

The upgrade tool is configured via its command line interface, or by [using properties files](../reference/database-upgrade-tool-reference.md#manual-configuration).

The `db_upgrade.sh` script in the `[Liferay Home]/tools/portal-tools-db-upgrade-client` folder invokes the upgrade tool. The `--help` option describes the tool's usage.

```bash
db_upgrade.sh --help
```

Here are steps for upgrading your database with the upgrade tool:

1. Start the upgrade tool. Here's an example command:

    ```bash
    cd liferay-home/tools/portal-tools-db-upgrade-client
    db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.timezone=GMT -Xmx4096m" -l "output.log"
    ```

    The command above executes the upgrade tool with the same JVM options recommended for the application server. File encoding (`UTF-8`), time zone (`GMT`), country, language, and memory settings (`-Xmx value`) should all match your application server's settings. For databases with >= 10 GB of data, we recommend allocated additional memory over the 4 GB default. The `-l "[file]"` arguments direct upgrade tool log messages to the specified file.

   If you haven't created [upgrade properties files](../reference/database-upgrade-tool-reference.md#manual-configuration), the upgrade tool prompts you for configuration values, and shows default values in parentheses. Here's an example interaction:

    ```
    Please enter your application server (tomcat):
    tomcat

    Please enter your application server directory (../../tomcat-9.0.17):

    Please enter your extra library directories (../../tomcat-9.0.17/bin):

    Please enter your global library directory (../../tomcat-9.0.17/lib):

    Please enter your portal directory (../../tomcat-9.0.17/webapps/ROOT):

    [ db2 mariadb mysql oracle postgresql sqlserver sybase ]
    Please enter your database (mysql):
    mariadb

    Please enter your database host (localhost):

    (etc.)
    ```

    If you want to use the default value shown in a prompt, press enter.

    After configuration is complete, the upgrade starts. You can monitor the log file. Log messages are reported for the start and completion of each upgrade process.

1. After the upgrade completes, check the log for any database upgrade failures or errors. You can use [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to troubleshoot issues and finish the upgrade.

You have completed the database upgrade and resolved any issues.

## Test the Upgraded Database

Now that the database upgrade is complete, test it.

1. If you're upgrading to a new Liferay Docker image, point your image to the upgraded database and validate Liferay with the database. Please see [Configuring Liferay Containers](../../installing-liferay/using-liferay-docker-images/configuring-containers.md) for more information.

1. Examine the [Post-Upgrade Considerations](./post-upgrade-considerations.md).

1. Copy and merge your custom [Liferay Home files](../../maintaining-a-liferay-dxp-installation/backing-up.md#liferay-home) and [application server files](../../maintaining-a-liferay-dxp-installation/backing-up.md#application-server) from your backup to the new installation. The files may include but are not limited to these:

    * `/license/*`: Activation keys. (Subscription)
    * `/log/*`: Log files.
    * `/osgi/*.config`: OSGi configuration files.
    * `portal-*.properties`: Portal properties files, such as `portal-ext.properties`.
    * Application server files: Modified scripts and configuration files.
    * `web.xml`: Portal web application descriptor.

1. [Update the Portal properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md#migrating-portal-properties) in your new installation.

1. Start your server and validate Liferay with its upgraded database.

    ![Here is the Liferay DXP landing screen.](./using-the-database-upgrade-tool/images/01.png)

You've upgraded your Liferay database using the Database Upgrade Tool.

If this was a trial upgrade and you want to shorten the upgrade time, tune your database for upgrade (if you haven't already) and [remove unnecessary data](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md) from the database. Repeat this article's steps as necessary.

## Next Steps

Revisit the [Upgrade Overview](./upgrade-overview.md) to see what's left to upgrade.