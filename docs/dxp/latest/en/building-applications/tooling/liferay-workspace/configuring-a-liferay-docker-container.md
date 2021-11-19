# Configuring a Liferay Docker Container

Creating and deploying containers is an important part of the modern developer workflow. Liferay Workspace makes it easy to use Docker containers both for development and for creating wholly contained working configurations. 

Creating a container happens in three main steps: 

1. Choose the Docker image you need for your container. 
1. Download that container to your workspace. 
1. Configure the container with settings and applications. 

You must have Docker installed and running before following the instructions below. 

## Choose a Liferay Docker Image

Most of the time you don't need to choose a Liferay Docker image, because the image version is inherited from your Workspace version. If, however, you must install an image version other than the one specified by your Workspace, you can set the version explicitly. If you don't need to do this, skip to the next section. 

Liferay's Docker images are on [Docker Hub](https://hub.docker.com/r/liferay/portal/tags). They're in two categories: 

- [Liferay/Portal](https://hub.docker.com/r/liferay/portal)
- [Liferay/DXP](https://hub.docker.com/r/liferay/dxp)

1. Using the links above, find the version of the Liferay container you need. 
1. Note the `docker pull` command that lists the full tag name for your container. The tag name looks like this: `liferay/dxp:7.3.10-ga1` or this: `liferay/portal:7.3.6-ga7`. 
1. Open `gradle.properties` in your Liferay Workspace. 
1. Specify the Docker tag using the property `liferay.workspace.docker.image.liferay`. Here are some examples: 

   ```properties
   liferay.workspace.docker.image.liferay=liferay/dxp:7.3.10-ga1
   ```

   ```properties
   liferay.workspace.docker.image.liferay=liferay/portal:7.3.6-ga7
   ```

1. Save the file. Now you're ready to create the Docker image.

## Create the Liferay Docker Image

Run this command from your workspace's root folder: 

   ```bash
   ./gradlew createDockerContainer
   ```

A Docker image based on the name of your workspace with `-liferay` appended is created. For example, if your workspace folder was `my-project`, your Docker container is called `my-project-liferay`. You can start/stop the container like any other Docker image, but you might want to configure it first. 

## Configuring a Docker Image

Beyond the regular Docker tools for configuring your container, building your container from the Gradle task sets up a folder pointing to [Liferay Home](../../../installation-and-upgrades/reference/liferay-home.md), so you can create whatever Liferay configuration you need. You'll find this folder in `configs/docker`. 

For example, you may want to enable telnet access to your container's Gogo shell. Here's how you'd do it: 

1. In your `configs/docker` folder create a `portal-ext.properties` file and add this property to it: 

   ```properties
   module.framework.properties.osgi.console=0.0.0.0:11311
   ```

1. Save the file. 

1. Start the container. The properties contained in the `portal-ext.properties` file you just created are applied to your running instance, and you should be able to telnet to its Gogo shell using this command: 

```bash
telnet localhost 11311
```

The Docker configuration is part of the larger [workspace configuration](configuring-liferay-workspace.md) that can handle multiple environments. If you're using that, the configurations stored in `configs/common` are merged with what you place in the `configs/docker` folder. 
