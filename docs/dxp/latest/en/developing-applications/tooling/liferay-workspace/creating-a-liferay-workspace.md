# Creating A Liferay Workspace

You can create a Liferay Workspace manually or with [Blade CLI](../blade-cli/installing-and-updating-blade-cli.md). 

## Creating a Liferay Workspace Manually

To create a Liferay Workspace manually, you must have [Gradle](https://gradle.org) installed. You must also either know the ID of the Liferay product (DXP, Portal rolling release, or Commerce) you're targeting or be able to look it up with Blade CLI using the `blade init -l` command. 

1.  Create a folder on your system to store your workspace. 

1.  Inside this folder, create a file called `settings.gradle` with this content: 

    ```groovy
    buildscript {
            dependencies {
                    classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "latest.release"
            }

            repositories {
                    mavenLocal()

                    maven {
                            url "https://repository-cdn.liferay.com/nexus/content/groups/public"
                    }
            }
    }

    apply plugin: "com.liferay.workspace"
    ```

1.  Now add the Gradle wrapper to your project using this command: 

    ```bash
    gradle wrapper --gradle-version 6.6.1
    ```

1.  Create a file called `gradle.properties` containing this: 

    ```properties
    liferay.workspace.product=$LIFERAY_LEARN_PORTAL_WORKSPACE_TOKEN$
    ```

    This defines the lastest GA of Liferay Portal. You can always get a current list using Blade CLI by typing `blade init -l`. 

## Creating a Liferay Workspace with Blade CLI

1. At your command line interface, navigate to the folder where you want your workspace generated. 

1. Find the version of Liferay you want to target by listing the available versions: 

   ```bash
   blade init -l
   ```

1. Now you're ready to create your workspace. Run this command: 

   ```bash
   blade init -v [Liferay version] [workspace name]
   ```

   For example, 

   ```bash
   blade init -v portal-7.4-ga1 my-workspace
   ```

Your workspace is created. Note that you can target any 7.x version of Liferay: 

```bash
blade init -v portal-7.0-ga7 [workspace name]
```

The workspace version is stored in the hidden `.blade.properties` file in the workspace's root folder, using the `liferay.version.default` property. When you create projects based on templates, the version stored here determines which template version is used. 

## Configuring a Proxy 

If you're behind a proxy server, you can set it up. 

1.  Open your `~/.gradle/gradle.properties` file. Create this file if it does not exist.

1.  Add the following properties to the file:

    ```properties
    systemProp.http.proxyHost=www.somehost.com
    systemProp.http.proxyPort=1080
    systemProp.https.proxyHost=www.somehost.com
    systemProp.https.proxyPort=1080
    ```

    Make sure to replace the proxy host and port values with your own.

3.  If the proxy server requires authentication, also add the following properties:

    ```properties
    systemProp.http.proxyUser=userId
    systemProp.http.proxyPassword=yourPassword
    systemProp.https.proxyUser=userId
    systemProp.https.proxyPassword=yourPassword
    ```

Excellent! Your proxy settings are set in your Liferay Workspace. 

