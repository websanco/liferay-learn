# Database Upgrade Options

When database schema changes are included in a release, update, or fix pack, it's helpful to know the options for applying those changes. Here the database upgrade options are listed for each level of schema change and the upgrade processes are summarized for each combination of DXP/Portal installation type and upgrade method.

## Upgrade Options for Applying Schema Changes

Here are the types of database schema changes a release, update, or fix pack may have and the database upgrade options for applying those changes.

| Database Schema Changes | Database Upgrade Options |
| :---------------------- | :----------------------- |
| Module micro version<br>(Upgrades optional) | * Docker image<br>* Gogo Shell<br>* Database Upgrade Tool |
| Module major/minor version<br>(Upgrades required) | * Docker image<br>* Gogo Shell<br>* Database Upgrade Tool |
| Core data schema<br>(Upgrades required) | * Docker image<br>* Database Upgrade Tool |

## Upgrade Process Summary for Installation Types

Here is an overview of using the database upgrade options with your current installation type.

| Current Installation Type | Docker<br>image<br>option | Tomccat<br>bundle<br>option | Lifery<br>WAR<br>option | Upgrade Process Summary |
| :--- | :--- | :--- | :--- | :--- |
| Liferay Docker image | x |  |  | 1. Configure the new image.<br>2. Run it with the upgrade environment variable enabled.<br>3. When the upgrades complete successfully, configure the database on a new container of the new image (do not enable upgrades). |
| Liferay Docker image |  | x |  | 1. Configure the Liferay Tomcat Bundle. 2. Run the Database Upgrade Tool.<br>3. When the upgrades complete successfully, configure the database on the new Docker image. |
| Liferay Tomcat Bundle | x |  |  | 1. Configure the new Docker image.<br>2. Run it with the upgrade environment variable enabled.<br>3. When the upgrades complete successfully, configure the database on the new Liferay Tomcat Bundle. |
| Liferay Tomcat Bundle |  | x |  | 1. Configure the new Liferay Tomcat Bundle.<br>2. Run the Database Upgrade Tool. |
| App server + Liferay WAR | x |  |  | 1. Configure the new Docker image.<br>2. Run it with the upgrade environment variable enabled.<br>3. When the upgrades complete successfully, install the new Liferay WAR and OSGi dependencies on the application server. |
| App server + Liferay WAR |  | x |  | 1. Configure the new Liferay Tomcat Bundle.<br>2. Run the Database Upgrade Tool.<br>3. When the upgrades complete successfully, install the new Liferay WAR and OSGi dependencies on the application server. |
| App server + Liferay WAR |  |  | x | 1. Run the Database Upgrade Tool.<br>2. When the upgrades complete successfully, install the new Liferay WAR and OSGi dependencies on the application server. |