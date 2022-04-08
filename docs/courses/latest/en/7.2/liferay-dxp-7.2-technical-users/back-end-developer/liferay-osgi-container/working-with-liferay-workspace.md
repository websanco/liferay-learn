---
description:  3 - Liferay's OSGi Container
title: Working With Liferay Workspace
order: 4
---

## Working With Liferay Workspace

Liferay Workspace is a generated wrapper environment for Liferay projects. Liferay Workspace provides you with all the tools needed to create and build Liferay modules and can be used from within a Java IDE or independently. Liferay Workspace is not mandatory for Liferay development, but in most cases, it simplifies and speeds up the development process significantly.

Liferay Workspace provides:

* Blade CLI tools
* Gradle and Maven wrapper scripts depending on which one is used as the build tool

> While Gradle and Maven are supported as dependency management tools, Liferay doesn't restrict you from using any other tools. Since Gradle is the preferred build and dependency management tool and is also used in core Liferay development, we'll be using it in our examples.

#### Blade CLI

Blade is an acronym for *Bootstrap Liferay Advanced Developer Environment*, which is a set of backbone command line tools for creating and managing Liferay module projects and the Liferay Workspace environment. Blade CLI is an independent set of tools and can also be used outside of Liferay Workspace.

The most relevant Blade CLI commands are listed below: 

* __convert:__ converts a Plugins SDK plugin project to a Gradle Workspace project
* __create:__ creates a new Liferay module project from available templates
* __deploy:__ builds and deploys bundles to the Liferay module framework
* __gw:__ executes a Gradle command using the Gradle Wrapper, if detected
* __help:__ gives help on a specific command
* __init:__ initializes a new Liferay Workspace
* __install:__ installs a bundle into Liferay’s module framework
* __open:__ opens or imports a file or project in Liferay IDE
* __samples:__ generates a sample project
* __server:__ starts or stops a server defined by your Liferay project
* __sh:__ connects to Liferay, executes a Gogo command, and returns output
* __update:__ updates Blade CLI to the latest version
* __version:__ displays version information about Blade CLI

> See Blade CLI's [GitHub page](https://github.com/liferay/liferay-blade-cli)

#### Using Gradle

Gradle is the default build and dependency management tool for Liferay plugin development. Available Gradle Tasks are listed in the Gradle Tasks pane of the Liferay Workspace perspective of the IDE. The perspective can be selected from Dev Studio's *Window* menu. By default, the Gradle Tasks pane is displayed in the perspective's upper right corner:

<img src="../images/workspace-gradle-tasks.png" style="max-height:33%"/>

#### Gradle Wrapper

Liferay Workspace ships with Gradle Wrapper scripts, taking care of downloading and making a compatible version of Gradle available. That way, you don't have to install Gradle manually in your development environment, and everybody who uses the project builds it with exactly the same version of Gradle.

From the _Command Line_, the available tasks can be listed by running the wrapper script in the root folder of the Liferay Workspace:

```bash
liferay@liferay-VirtualBox:/opt/liferay-workspace$ ./gradlew tasks
:tasks

------------------------------------------------------------
All tasks runnable from root project
------------------------------------------------------------

Build tasks
-----------
assemble - Assembles the outputs of this project.
build - Assembles and tests this project.
clean - Deletes the build directory.

Build Setup tasks
-----------------
init - Initializes a new Gradle build. [incubating]
wrapper - Generates Gradle wrapper files. [incubating]

Bundle tasks
------------
createToken - Creates a liferay.com download token.
distBundleTar - Assembles the Liferay bundle and zips it up.
distBundleZip - Assembles the Liferay bundle and zips it up.
initBundle - Downloads and unzips the bundle.
```

#### Using Maven

You can choose to use Maven when creating a new module:

<img src="../images/liferay-maven-workspace.png" style="max-height: 40%"/>

Liferay Maven Workspace can also be created from the _Command Line_ with:

```
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.workspace \
    -DgroupId=[GROUP_ID] \
    -DartifactId=[WORKSPACE_NAME] \
    -Dversion=[VERSION]
```

For more information on how to use Liferay Workspace with Maven, see the developer documentation at [Liferay Developer Network](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/maven-workspace).

#### Liferay Workspace Structure

By default, a Liferay Workspace is generated with the following files and folders: 

<img src="../images/liferay-workspace-folder-structure.png" style="max-height: 30%"/>

* __bundles (optionally generated):__ the default folder for Liferay Portal bundles
* __configs:__ holds the configuration files for different environments
* __gradle:__ holds the Gradle Wrapper used by your workspace
* __modules:__ holds your custom modules
* __plugins-sdk (optionally generated):__ holds plugins to migrate from previous releases
* __themes:__ holds your custom themes, which are built using the Theme Generator
* __wars__: holds traditional WAR-style web application projects
* __build.gradle:__ the common Gradle build file
* __gradle-local.properties:__ sets user-specific properties for your workspace 
* __gradle.properties:__ specifies the Workspace’s project locations and Liferay's server configuration globally
* __gradlew__: executes the Gradle command wrapper
* __settings.gradle:__ applies plugins to the workspace and configures its dependencies

#### Using Liferay Workspace Without IDE

Liferay Workspace can be used without an IDE as well. Below is an example of the steps required to build a Workspace project which, for example, has been downloaded from GitHub:

1. Have a Java 8 JDK installed on your computer
1. Clone a Liferay workspace project containing Liferay modules from GitHub 
1. Run the gradle build tool script (example for Linux):

```bash
PATH_TO_LIFERAY_WORKSPACE/gradlew clean build 
```

After your build has completed successfully, the deployables can be found in the *build/libs* directories of the respective project folders. 

#### Setting Up Liferay Portal for Liferay Workspace

If you use Tomcat as your development server, you can choose to manually download a Liferay Tomcat bundle and create a server in the Dev Studio workspace using the Liferay 7.x server adapter, or you can let Liferay Workspace do the work automatically for you.

When you start a new Liferay Workspace project, you can choose to download a Liferay bundle. If the bundle has not already been downloaded and cached, the workspace launcher will first download the bundle and then set up the Tomcat server automatically:

<img src="../images/set-bundle-url.png" style="max-height: 25%"/>
<br />

If you didn't choose to download the server when you initially created the Liferay Workspace, you can use the `initBundle` Gradle task at any time to download and extract the bundle to the bundle's subdirectory of the Liferay Workspace. Afterward, you can create a new server just as if you were creating a workspace server manually. You must only point the *Liferay Portal Bundle Directory* to the directory where the bundle is downloaded and extracted. The download URL of the bundle can be defined in the `gradle.properties` file or in the Liferay Workspace create dialog. For more information, see the [Developer Network article](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/configuring-a-liferay-workspace).

Using Liferay Workspace server bundles facilitates generating and configuring multiple server environments in your development environment. For more information about this scenario, please see the [Developer Network article](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/development-lifecycle-for-a-liferay-workspace#testing-projects).

#### Portal Configuration Files

The main portal configuration file, `portal.properties`, defines every aspect of the platform configuration. Amongst others, it defines default settings for: 

* The Liferay home folder
* Database connection of the database pool reference
* Clustering settings

The default settings can be inspected in the source file, which can be extracted from the portal-impl.jar package in your Liferay installation or downloaded online from [GitHub](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-impl/src/portal.properties).

Settings in `portal.properties` can be overridden with one or both of the following files:

* portal-ext.properties
* portal-setup-wizard.properties (generated only if the setup wizard is run)

These configuration files are looked up at startup in the following locations:

* The Liferay home folder
* LIFERAY\_WEB\_APPLICATION_ROOT/WEB-INF/classes

Some of the settings found in `portal.properties` are portal instance-specific and can be managed in the *Control Panel*. If instance-specific settings are saved through the _Control Panel_, they are persisted to the database. In the case of overlapping settings, the order of precedence is the following (the last one remains):

1. portal.properties
1. portal-ext.properties
1. portal-setup-wizard.properties
1. Settings persisted through the _Control Panel_

> Many of the settings in `portal.properties` are in the process of becoming *Control Panel*-managed. When updating or patching, it's always a good practice to check the release notes for possible changes.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>Blade is an acronym for ___________________________________________________.</li>
	<li>____________________ is the default build and dependency management tool for Liferay plugin development.</li>
	<li>You can choose between Gradle and ____________________ as a build system when you create a new Liferay workspace.</li>
	<li>Liferay Workspace can be used without an ________________.</li>
	<li>The main portal configuration file, __________________________, defines every aspect of the platform configuration.</li>
	<li>You can override the main configuration file by editing either the _________________________ or the ___________________________ files.</li>
</ul>
</div>