# Creating Code with Liferay Workspace

Liferay Workspace is the developer's tool for creating and managing Liferay development projects. 

## Creating Projects

[Blade CLI](../blade-cli/installing-and-updating-blade-cli.md) makes it easy to create projects in Liferay Workspace by providing pre-built project templates. Alternatively, you can create projects manually. 

### Creating a Project Using Blade CLI

1. From inside your workspace, find the project template that most closely matches what you want to do, using this command: 

   ```bash
   blade create -l
   ```

1. Once you've identified the template you want to use, generate your project. For example, to create a MVC Portlet, use this command: 

   ```bash
   blade create -t mvc-portlet -p com.liferay.docs.myproject.portlet -c MyPortlet myproject-web
   ```

   This creates a project using the MVC Portlet template (`-t mvc-portlet`), generates the portlet class in the Java package you specify (`-p com.liferay.docs.myproject.portlet`), specifies the name of the portlet class (`-c MyPortlet`), and names the project `myproject-web`. 

### Creating Projects Manually

You don't need Blade CLI to create projects. You can create a project folder manually if you wish: 

1. From inside your workspace, create a folder to house your project: 

   ```bash
   cd [my project name]
   ```

1. Create a `build.gradle` script for your project. Since you're using Liferay Workspace, most of the time you only need one dependency: 

   ```groovy
   dependencies {
      compileOnly group: "com.liferay.portal", name: "release.portal.api"
   }
   ```

1. Create the folder structure for your project. 

To write code to run on Liferay DXP, you must have a Liferay runtime on which to deploy it. Your first task, therefore, after installing Liferay Workspace should be to add a Liferay bundle to your workspace. 

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
