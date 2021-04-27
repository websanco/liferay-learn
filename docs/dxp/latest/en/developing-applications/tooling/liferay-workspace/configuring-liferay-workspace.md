# Configuring Liferay Workspace

STOP! Liferay Workspace is simple to use, and you can learn the basics by starting [at the beginning](./what-is-liferay-workspace.md). If you're happily [creating projects](./creating-code.md), [deploying code](./creating-code.md#deploying-code-via-liferay-workspace), or [using a Docker container](./configuring-a-liferay-docker-container.md), you may not need the information here. If, however, you want to do a deep dive and learn about all the things Workspace can do, this is the place for you. 

Here are the topics covered: 

- Updating Liferay Workspace and Bundled Plugins
- Using Development, UAT, and production environments
- Managing the Target Platform

## Updating Liferay Workspace and Bundled Plugins

Liferay Workspace is constantly updated to help developers be more productive, and bringing your Workspace up to date is a painless process. 

1. [Go to Workspace's releases](https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.gradle.plugins.workspace) on Liferay's repository. The versions appear in a list; take note of the version number you need. 
1. Open the `settings.gradle` file in your Workspace's root folder. 
1. In the `dependencies` block, update the version with the version you found in the repository. If you want to stay on the latest release, supply the text `latest.release` instead of the version number.

   ```groovy
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "[WORKSPACE_VERSION]"
    }
    ```

1. Save and close the file. To run the upgrade, execute any Gradle command, such as `tasks`: 

   ```bash
   ./gradlew tasks
   ```

Congratulations! Your Workspace is now upgraded. 

But what if you need a new feature in the [Source Formatter plugin](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.source.formatter/) or another plugin Workspace relies on, but the latest version of Workspace doesn't yet include it? You can still upgrade it! 

1. In the version of the Liferay Portal source you're targeting, open the `portal-tools.properties` file from `modules/sdk/gradle-plugins/src/main/resources/com/liferay/gradle/plugins/dependencies`. This file shows the Bundle Symbolic Names (BSNs) for Workspace plugins. 

1. Find the plugin you want to update. For example, the Source Formatter's BSN is `com.liferay.source.formatter`. 

1. Open your Workspace's `build.gradle` file and provide the BSN followed by `.version` in a property. For the value, specify the version from `portal-tools.properties`: 

   ```properties
   com.liferay.source.formatter.version=1.0.1085
   ```

   If you want to target the latest version, set the above property to `latest.release` to be upgraded to the latest available version. 

1. Run any Gradle task, such as `./gradlew tasks` to perform the upgrade. 

Excellent! You've now upgraded your plugin. 

## Creating Deployment Environments

There comes a point when your code is ready to share with somebody. For that, you must build an environment. In the corporate world, there are usually three environments: 

* Developer: an environment for early testing, where code can be fixed and redeployed rapidly. Power users test here. 
* User Acceptance Testing (UAT): an environment that more closely mirrors the production configuration. When your application is near-complete, you generally invite a larger segment of users to test here. 
* Production: the environment where your live site lives. Deployments are tightly controlled, and only code that's been tested on the other two environments is deployed. 

Liferay Workspace makes it easy to generate your deployment environments, whether they be container-based or traditional. You provide the configuration, run a Gradle task, and you can generate a distributable Docker container or server archive ready to install. Workspace contains an optional `configs` folder where the action happens. 

If you created your workspace using [Blade CLI](../blade-li/generating-projects-with-blade-cli.md), the `configs` folder already exists. If you created your Workspace [manually](./creating-a-liferay-workspace.md), create this folder structure in your Workspace directory: 

   ```
   ├── common
   │   └── portal-setup-wizard.properties
   ├── dev
   │   └── portal-ext.properties
   ├── docker
   ├── local
   ├── prod
   └── uat
       └── portal-ext.properties
   ```

Don't put anything in the files yet. 

### How Deployment Environments Work

The `configs` folders define specific scenarios: 

`common`: Contains configuration that applies to all the environments. 

`dev`: Contains configuration for the development environment. 

`docker`: Contains Docker configuration. 

`local`: Contains configuration for the local environment where the Workspace resides. 

`prod`: Contains configuration for the production environment. 

`uat`: Contains configuration for the User Acceptance Testing environment. 

Placing configuration files in a particular folder defines the configuration for that environment. In the case of `common`, that configuration is merged with the others when the environment is built. Now you're ready to build some environments. 

### Building Deployment Environments

Assume this scenario for your environments: 

- Developer environment should use developer properties 
- Local environment points to local database
- Developer and UAT environments point to their own databases
- All environments skip the setup wizard

Here's how to configure that scenario: 

1. In the `common` folder, add this property to `portal-setup-wizard.properties`: 

   ```properties
   setup.wizard.enabled=false
   ```
1. In the `local` folder, configure a local database in `portal-ext.properties`: 

   ```properties
   #
   # MySQL
   #
   jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
   jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
   jdbc.default.username=root
   jdbc.default.password=password
   ```

1. In the `dev` folder, enable developer properties and configure a database on the development server in `portal-ext.properties`: 

   ```properties
   include-and-override=portal-developer.properties

   #
   # MySQL
   #
   jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
   jdbc.default.url=jdbc:mysql://devel.server/lportaldev?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
   jdbc.default.username=root
   jdbc.default.password=password
   ```

1. In the `uat` folder, configure a database on the UAT environment in `portal-ext.properties`:

   ```properties
   #
   # MySQL
   #
   jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
   jdbc.default.url=jdbc:mysql://uat.server/lportaluat?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
   jdbc.default.username=root
   jdbc.default.password=password
   ```

Excellent! You're all set up now to generate and distribute environments. 

### Generating Deployment Environments

Now you can generate environments to run locally or to distribute to your server. This is done using either the `initBundle` or the `distBundle` Gradle task. 

1. You should test your environment first. To build it locally, use the `initBundle` command. For example, to build the `dev` environment, you'd run this: 

   ```bash
   ./gradlew initBundle -Pliferay.workspace.environment=dev
   ```
   This compiles and deploys all the projects in your Workspace to the runtime in the `bundles` folder. It also provides the configuration you specified---in this example, the `dev` environment's configuration. If you examine the `portal-ext.properties` file in the `bundles` folder, you'll see it matches what you provided for the `dev` configuration. 

1. If the environment looks good and tests well, you can build a distributable bundle containing all your applications and configuration. Run this command: 

   ```bash
   ./gradlew distBundleTar -Pliferay.workspace.environment=dev
   ```

   This builds a gzipped `tar` file in the `build` folder. You can take this archive and install it on your development server as you would any other Liferay bundle, except that this bundle is fully configured the way you wanted it and has all your applications already installed. 

```note::
You can use the ``distBundleZip`` command if you would rather have a .zip archive.
```

Follow the above steps to test and build each environment. 

## Managing the Target Platform

Normally when defining Gradle dependencies, you must provide versions of those dependencies, like this: 

```groovy
dependencies {
   compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.1"
   compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "4.0.1"
}
```

Since any application written on Liferay's platform targets that platform, Liferay has made it easy for you to specify all dependencies in one shot by declaring the version of Liferay and then inheriting other dependencies from Liferay. That way, you don't have the mess shown above. 

Target platform is enabled by default; you don't have to do anything extra to use it. Here's what most dependencies look like now: 

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "release.portal.api"
}
```

This brings in all the dependencies that come with Liferay. If for some reason you must specify a particular dependency, you still can: 

```groovy
dependencies {
        compileOnly group: "com.liferay.portal", name: "release.portal.api"
        cssBuilder group: "com.liferay", name: "com.liferay.css.builder", version: "3.0.2"
}
```

