# Updating Liferay

{bdg-secondary}`Applicable for Liferay DXP 7.3 SP3+ and 7.4 GA1+`

Update your Liferay installation with Bundle Releases. The latest features, security releases, as well as library updates are all delivered together in one bundle. See [Updating Previous Versions of Liferay](./updating-previous-versions-of-liferay.md) for versions before Liferay.

```{warning}
**Always** [back up](./backing-up.md) your database and installation before updating Liferay DXP/Portal.
```

As your prepare for a new update, identify the important dependencies of your Liferay installation. This can include things such as your database, document library, and custom modules. Other files such as portal properties, OSGi configurations, Tomcat files, and database jars are also important. See [Important Files to Consider](#important-files-to-consider).

Note, you may choose to set your Liferay home directory to be outside of the bundle to prevent these important dependencies and files from being overwritten by a new bundle release.

1. Set your environment variable to a different directory.

   `export LIFERAY_HOME="/your/liferay/directory"`

2. Specify this directory path in your `portal-ext.properties` file.

   `liferay.home=/your/liferay/directory`

3. Setting the path above also sets Liferay's OSGi folder path to your dependencies. But set the directory path for the marketplace to point back to the bundle.

   `module.framework.marketplace.dir=/new_liferay_bundle/osgi/marketplace`

## Configuration Management

Save your important dependencies and files before updating with a new bundle release. Using a shell script or using the Liferay Workspace are two ways this can be done.

### Using a Shell Script

A simple shell script such as the one below, can be used to back up all dependent Liferay configuration files and libraries. This example uses an array to list out the files that are required when migrating to a new bundle release. 

      #!/bin/bash

      # Liferay and Tomcat locations
      LIFERAY_HOME="./liferay"
      CATALINA_HOME="$LIFERAY_HOME/tomcat-9.0.56"

      declare -a persistent_files=(
      "$LIFERAY_HOME/portal-ext.properties"
      "$LIFERAY_HOME/portal-setup-wizard.properties"
      "$LIFERAY_HOME/osgi/configs/com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration.cfg"
      "$LIFERAY_HOME/osgi/configs/com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg"
      "$LIFERAY_HOME/osgi/configs/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.cfg"
      "$CATALINA_HOME/conf/server.xml"
      "$CATALINA_HOME/conf/web.xml"
      "$CATALINA_HOME/bin/setenv.sh"
      "$CATALINA_HOME/webapps/ROOT/WEB-INF/classes/ehcache-config/"
      "$CATALINA_HOME/lib/ojdbc8.jar"
      "$LIFERAY_HOME/patching-tool/default.properties"
      "$LIFERAY_HOME/osgi/marketplace/override/"
      "$CATALINA_HOME/conf/Catalina/localhost/"
      "$CATALINA_HOME/webapps/ROOT/WEB-INF/classes/META-INF/portal-log4j-ext.xml"
      )

      echo "Backing up the following files"
      tar cvfz ./persisted_bundle_configs-`date +%Y%m%d.%H%M`.tgz --files-from <(printf "%s\n" "${persistent_files[@]}")

The above script will generate a compressed `tar` file that can be used to overlay on top of a newly downloaded bundle’s folder structure. For example, place the `.tgz` file in the parent folder of the new bundle release folder and untar it with the following command.

### Using the Liferay Workspace

The Liferay Workspace offers configuration management by using environment subfolders located under the *configs* folder. Additionally, there is a *common* folder for any files that are used in all environments. Note, the path for any configuration file placed inside one of the environment folders must match the path found in the bundle release. For example:

      ../configs/**dev**/tomcat-9.0.56/conf/server.xml 

See [Creating Deployment Environments](../../building-applications/tooling/liferay-workspace/configuring-liferay-workspace.html#creating-deployment-environments) for more information.

Next, use a Gradle task to generate a bundle. Use `distBundleZip` or `distBundleTar` to generate a specific bundle for a defined environment. For example: 

      ./gradlew distBundleZip -Pliferay.workspace.environment=dev

The Gradle task downloads a new bundle prior to layering the proper configuration files and compiling any modules and themes. 

Note, the resulting bundle(s) are found in the *build* folder of the Liferay workspace. The version of Liferay DXP in use is defined by the *liferay.workspace.product* property inside the *gradle.properties* file.

To generate bundles for all of your defined environments with a single task, use `distBundleZipAll` or `distBundleTarAll`. For example:

      ./gradlew distBundleTarAll -Pliferay.workspace.bundle.dist.include.metadata=true

Each resulting Zip or Tar's filename will include the name of the config environment and a timestamp. Note, this specific Gradle task is available in Liferay workspace 3.4.32 and above. 

## Important Files to Consider

Below is a list of common files to consider backing up when moving to a new bundle release. This list is not exhaustive and your installation may have additional files or libraries that are not mentioned. Additionally, application servers other than Apache Tomcat are not discussed but the principle is the same for maintaining any application server. See [Installing Liferay on an Application Server](../installing-liferay/installing-liferay-on-an-application-server.md).

### Liferay Properties (/LIFERAY/)
* portal-ext.properties
* portal-setup-wizard.properties

### Liferay OSGI Configurations (/LIFERAY/osgi/configs/)
The OSGI Config directory can potentially contain several configuration files. Below are a few of the common OSGi configuration files to be considered.
* com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config
* com.liferay.portal.search.elasticsearch[6|7].configuration.ElasticsearchConfiguration.config
* com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config

### Liferay Clustering (LIFERAY/TOMCAT/):
In addition to backing up the jgroups or ehcache configuration file, consider using JDBC Ping with a JNDI pool setting. This can abstract and simplify the ehcache configuration file. This eliminates both IPs and database specific settings from being defined, allowing the file to be almost generic.
* webapps/ROOT/WEB-INF/classes/ehcache-config/tcp.xml

### Liferay Persisted Logging Settings (LIFERAY/TOMCAT/)
* webapps/ROOT/WEB-INF/classes/META-INF/portal-log4j-ext.xml

## Tomcat/Application Server (LIFERAY/TOMCAT/) 
* conf/server.xml
* conf/web.xml
* bin/setenv.sh

### Database Libraries (LIFERAY/TOMCAT/)
Liferay ships with the MySQL driver. However, it is recommended to use JNDI with Hikari for connecting to the Liferay database. Therefore any required libraries for setting up JNDI connections such as Hikari, MySQL, Oracle, or other database drivers need to be backed up.  

In 7.4, these drivers are located in the LIFERAY/TOMCAT/lib folder. While in prior versions these libraries resided in LIFERAY/TOMCAT/lib/ext.   
For example:
* lib/ojdbc8.jar for Oracle
* lib/mysql.jar for MySQL
* lib/hikaricp.jar for Hikari DB Pool 

If you are not using JNDI, see LIFERAY/TOMCAT/webapps/ROOT/WEB-INF/shielded-container-lib in 7.4 or LIFERAY/TOMCAT/lib/ext in prior versions for database drivers that you may need to back up. 

### Other Files or Libraries 
Consider other libraries that may also need to be backed up. For example:
* lib/xuggler.jar (LIFERAY/TOMCAT/webapps/ROOT/WEB-INF/lib/) (Deprecated 7.3)
* SAML Keystore (LIFERAY/data/keystore.jks)