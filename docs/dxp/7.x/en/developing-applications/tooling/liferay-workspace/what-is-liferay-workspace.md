# What is Liferay Workspace? 

Liferay Workspace is a set of folders and Gradle scripts that represents the Liferay-opinionated way of handling a full development lifecycle:

- [Creating projects](#creating-projects)
- [Building projects](#building-projects)
- [Deploying projects](#deploying-projects)
- [Testing projects](#testing-projects)

## Creating Projects

Liferay Workspace works hand-in-hand with Liferay's CLI utilities. Using [Blade CLI](../blade-cli/installing-and-updating-blade-cli.md), you can [create projects](../blade-cli/generating-projects-with-blade-cli.md) for new applications and widgets or for customization of various parts of Liferay DXP. These include themes created either way, using Blade CLI and Java or using the Liferay Theme Generator and Node.js. 

## Building Projects

Liferay Workspace incorporates Liferay's Gradle and Maven plugins seamlessly to make it easier to build your projects. A Gradle wrapper comes with Workspace so you can use Gradle without having to install it globally on your machine. 

Since Workspace is set up to build Liferay applications already, it saves you the time of hunting down repositories and dependencies to create your build configurations. 

## Deploying Projects

You can deploy projects easily from Liferay Workspace using Blade CLI, Gradle, or Maven, which means you can also deploy easily from your IDE that supports those CLI tools. And since Workspace also has a mechanism for auto-downloading a Liferay runtime on which to debug your code, deployment and testing is a snap. 

## Testing Projects

Liferay Workspace uses Docker to help you configure development, acceptance, and production configurations to simulate any environment in which to test your code. Environments are separated by folders, and each folder can supply its own database, `portal-ext.properties` file, Elasticsearch configuration, and more. These configurations overlay the Workspace-controlled installation of Liferay DXP or CE. 

If you're a Maven user, you're already to go. If not, start by [installing Blade CLI](../blade-cli/installing-and-updating-blade-cli.md). Once that's done you can [create](./creating-a-liferay-workspace.md) your first Liferay Workspace. 
