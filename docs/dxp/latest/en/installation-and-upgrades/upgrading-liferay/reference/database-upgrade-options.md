# Database Upgrade Options

When a release, update, or fix pack has database schema changes, it's helpful to know the options for applying the changes. Here you'll find the database upgrade options for each schema change level and learn the general steps for using each upgrade option with your DXP/Portal installation type.

Here are the database upgrade options:

* [Docker image](../upgrade-basics/upgrading-via-docker.md): Running the new DXP/Portal Docker image with the following environment variable auto-runs the core and module database upgrades on the configured database.

    ```bash
    -e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true
    ```

* [Gogo Shell](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md): While the server is running, you can execute Gogo Shell commands to list and invoke module upgrades. Note, core database upgrades require using a Docker image or the Database Upgrade Tool.

* [Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md): While the server is offline, you can execute core and module database upgrades using the Database Upgrade Tool.

```{important}
This is supplemental information for upgrading DXP/Portal databases. Please see [Upgrade Basics](../upgrade-basics.md) as a starting point for complete DXP/Portal upgrade instructions and see [Maintaining a Liferay Installation](../../maintaining-a-liferay-installation.md) for complete instructions on applying DXP/Portal updates and fix packs.
```

## Database Upgrade Options for Schema Change Levels

Here are the types of database schema changes a release, update, or fix pack may have and the database upgrade options for applying those changes.

| Database Schema Changes | Database Upgrade Options |
| :---------------------- | :----------------------- |
| Module micro version<br>(optional upgrades) | * Docker image<br> * Gogo Shell<br> * Database Upgrade Tool |
| Module major/minor version<br>(required upgrades) | * Docker image<br> * Gogo Shell<br> * Database Upgrade Tool |
| Core data schema<br>(required upgrades) | * Docker image<br> * Database Upgrade Tool |

Database upgrades can be performed from the new installation you're targeting or in a separate installation that's more convenient for upgrading the database. for example, if you're using an app server + Liferay WAR + dependencies, you can upgrade the database using the new Liferay Docker image, install the new Liferay WAR and dependencies to your app server, and restart your app server to run the new Liferay version.

There are three types of DXP/Portal installations:

1. [Liferay Docker image](../../installing-liferay/using-liferay-docker-images.md)
1. [Liferay Tomcat Bundle](../../installing-liferay/installing-a-liferay-tomcat-bundle.md)
1. [Application server (app server) + Liferay WAR (WAR) + dependencies](../../installing-liferay/installing-liferay-on-an-application-server/installing-on-tomcat.md)

Here are the general steps for using the database upgrade options with each installation type.

## Database Upgrade for Liferay Docker Images

Here are process summaries for using a Docker image or Tomcat Bundle to perform database upgrade for a new Liferay Docker image.

| Docker image option | Tomcat bundle option | Upgrade Process Summary |
| :--- | :--- | :--- |
| &#10004; |  | 1. Configure the new image.<br>2. Run it with the upgrade environment variable enabled.<br>3. When the upgrades complete successfully, configure the database on a new container of the new image (do not enable upgrades). |
|  | &#10004; | 1. Configure the Liferay Tomcat Bundle.<br>2. Run the Database Upgrade Tool from the bundle.<br>3. When the upgrades complete successfully, configure the database on the new Docker image. |

## Database Upgrade for Liferay Tomcat Bundles

Here are process summaries for using a Docker image or Tomcat Bundle to perform database upgrade for a new Liferay Tomcat Bundle.

| Docker image option | Tomcat bundle option | Upgrade Process Summary |
| :--- | :--- | :--- |
| &#10004; |  | 1. Configure the new Docker image.<br>2. Run it with the upgrade environment variable enabled.<br>3. When the upgrades complete successfully, configure the database on the new Liferay Tomcat Bundle. |
|  | &#10004; | 1. Configure the new Liferay Tomcat Bundle.<br>2. Run the Database Upgrade Tool. |

## Database Upgrade for Application Server Installations

Here are process summaries for using a Docker image or Tomcat Bundle to perform database upgrade for a new Liferay DXP/Portal installation on an application server.

| Docker image option | Tomcat bundle option | Upgrade Process Summary |
| :--- | :--- | :--- |
| &#10004; |  | 1. Configure the new Docker image.<br>2. Run it with the upgrade environment variable enabled.<br>3. When the upgrades complete successfully, install the new Liferay WAR and OSGi dependencies on the application server. |
|  | &#10004; | 1. Configure the new Liferay Tomcat Bundle.<br>2. Run the Database Upgrade Tool from the bundle.<br>3. When the upgrades complete successfully, install the new Liferay WAR and OSGi dependencies on the application server. |

## Additional Information

* [Upgrade Basics](../upgrade-basics.md)
* [Maintaining a Liferay Installation](../../maintaining-a-liferay-installation.md)