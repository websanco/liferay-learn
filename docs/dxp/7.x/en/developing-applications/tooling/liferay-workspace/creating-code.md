# Creating Code with Liferay Workspace

Liferay Workspace is the developer's tool for creating and managing Liferay development projects. To write code to run on Liferay DXP, you must have a Liferay runtime on which to deploy it. Your first task, therefore, after installing Liferay Workspace should be to add a Liferay bundle to your workspace. 

Once you've done that, you can create your first projects. 

## Adding a Liferay Bundle to Workspace

Workspace automates for you the download and setup of a runtime for your code. It takes only a few steps: 

1. Find the Liferay release you're targeting. 

   **DXP:** Go to the [Liferay Workspace Target Versions](https://help.liferay.com/hc/en-us/articles/360041818312) page and copy the Bundle URL to your clipboard. 

   **CE:** Browse [releases-cdn.liferay.com](https://releases-cdn.liferay.com), find the bundle you need, and copy its URL to your clipboard. 

1. Open `gradle.properties` from the root folder of your workspace and set the bundle URL property: 

   ```properties
   liferay.workspace.bundle.url=[paste the URL from your clipboard]
   ```

   If using Liferay DXP, also set these properties: 

   ```properties
   liferay.workspace.bundle.token.download=true
   liferay.workspace.bundle.token.email.address=[enter the email address registered on liferay.com]
   liferay.workspace.bundle.token.password=[enter your liferay.com password]
   ```

   Save and close the file. 

1. Ready to download the bundle? Run this command: 

   ```bash
   blade server init
   ```

If on DXP, for security reasons you should remove your password from the properties file after your bundle has downloaded. 

```tip::
If you're using the Maven version of Workspace, the steps are exactly the same, except you modify your ``pom.xml`` file. The properties are exactly the same, except they're XML-formatted: ``<liferay.workspace.bundle.url>[enter url]</liferay.workspace.bundle.url>``, etc. 

To initialize the server, use this command: ``mvn bundle-support:init``. 
```

## Creating Module Projects

[Blade CLI](../blade-cli/installing-and-updating-blade-cli.md) makes it easy to create projects in Gradle-based Liferay Workspaces. If you're a Maven user, there are many Liferay archetypes you can use. 

### Creating a Project Using Blade CLI

1. Find the project template that most closely matches what you want to do, using this command: 

   ```bash
   blade create -l
   ```

1. Once you've identified the template you want to use, generate your project: 

   ```bash
   blade create -t [template] [various options] [project name]
   ```

### Creating a Project Using Maven

1. List the archetypes and find the one that most closely matches what you want to do: 

   ```bash
   mvn archetype:generate -Dfilter=liferay
   ```

1. Choose the archetype, answer the prompts, and your project is generated. 

