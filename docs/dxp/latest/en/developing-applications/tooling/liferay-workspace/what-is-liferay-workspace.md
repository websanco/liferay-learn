# What is Liferay Workspace? 

Liferay Workspace is a set of folders and Gradle scripts that represents the Liferay-opinionated way of handling a full development life cycle:

- [Creating projects](#creating-projects)
- [Building projects](#building-projects)
- [Deploying projects](#deploying-projects)
- [Testing projects](#testing-projects)

## Creating Projects

Liferay Workspace is implemented using Gradle scripts and plugins. As such, it integrates into any IDE or development tool. It also works hand-in-hand with Liferay's CLI utilities. You can create projects using standard tools or with [Blade CLI](../blade-cli/generating-projects-with-blade-cli.md). 

## Building Projects

Liferay Workspace incorporates Liferay's Gradle plugins seamlessly to jump-start building Liferay projects. A Gradle wrapper comes with Workspace so you can use Gradle without having to install it globally on your machine. 

Since Workspace is pre-configured to build Liferay applications, it saves you the time of hunting down repositories and dependencies to create your build configurations. 

## Deploying Projects

You can deploy projects easily from Liferay Workspace using Gradle or Blade CLI, which means you can also deploy easily from your IDE that supports those CLI tools. And since Workspace also has a mechanism for auto-downloading a Liferay runtime on which to debug your code, deployment and testing is a snap. 

## Testing Projects

Liferay Workspace uses Docker to help you configure development, user acceptance testing, and production configurations to simulate any environment in which to test your code. Environments are separated by folders, and each folder can supply its own database, `portal-ext.properties` file, Elasticsearch configuration, and more. These configurations overlay the Workspace-controlled installation of Liferay DXP or CE. 

You can use Workspace with or without [Blade CLI](../blade-cli/installing-and-updating-blade-cli.md). Read on to learn how you can [create](./creating-a-liferay-workspace.md) your first Liferay Workspace. 
