# Creating A Liferay Workspace

You can create a Liferay Workspace with [Blade CLI](../blade-cli/installing-and-updating-blade-cli.md) or manually. 

## Creating a Liferay Workspace with Blade CLI

1. At your command line interface, navigate to the folder where you want your workspace generated. 

1. Run this command: 

   ```bash
   blade init -v 7.3 [workspace name]
   ```

Your workspace is created. Note that you can target any 7.x version of Liferay: 

```bash
blade init -v 7.0 [workspace name]
```

The workspace version is stored in the hidden `.blade.properties` file in the workspace's root folder, using the `liferay.version.default` property. When you create projects based on templates, the version stored here is used to determine which template version should be used. 

## Creating a Liferay Workspace Manually

To create a Liferay Workspace manually, you must have [Gradle](https://gradle.org) installed. You must also either know the ID of the product you're targeting or be able to look it up with Blade CLI using the `blade init -l` command. 

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
    gradle wrapper --gradle-version 5.6.4
    ```

1.  Create a file called `gradle.properties` with this content: 

    ```properties
    liferay.workspace.product=portal-7.3-ga3
    ```

    This defines the lastest GA of Liferay Portal CE at the time of this writing. You can always get a current list using Blade CLI by typing `blade init -l`. 

## Configuring a Proxy 

If you're behind a proxy server, you can set it up on a Gradle- or Maven-based workspace. 

### Gradle

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

Excellent! Your proxy settings are set in your Liferay Workspace's Gradle
environment.

### Maven

1.  Open your `~/.m2/settings.xml` file. Create this file if it does not exist.

1.  Add the following XML snippet to the file:

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
        <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
            <proxies>
                <proxy>
                    <id>httpProxy</id>
                    <active>true</active>
                    <protocol>http</protocol>
                    <host>www.somehost.com</host>
                    <port>1080</port>
                </proxy>
                <proxy>
                    <id>httpsProxy</id>
                    <active>true</active>
                    <protocol>https</protocol>
                    <host>www.somehost.com</host>
                    <port>1080</port>
                </proxy>
            </proxies>
        </settings>
    ```

    Make sure to replace the proxy host and port values with your own.

1.  If the proxy server requires authentication, also add the `username` and `password` proxy properties. For example, the HTTP proxy authentication configuration would look like this:

    ```xml
    <proxy>
      <id>httpProxy</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>www.somehost.com</host>
      <port>1080</port>
      <username>userID</username>
      <password>somePassword</password>
    </proxy>
    ```

Excellent! Your Maven proxy settings are now set.
