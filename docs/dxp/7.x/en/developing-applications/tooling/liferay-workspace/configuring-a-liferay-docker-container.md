# Configuring a Liferay Docker Container

Creating and deploying containers is an important part of the modern developer workflow. Liferay Workspace makes it easy to use Docker containers both for development and for creating wholly contained working configurations. 

Creating a container happens in three main steps: 

1. Choose the Docker image you need for your container. 
1. Download that container to your workspace. 
1. Configure the container with settings and applications. 

## Choose a Liferay Docker Image

Liferay's Docker images are on [Docker Hub](https://hub.docker.com/r/liferay/portal/tags). They're in two categories: 

- [Liferay/Portal](https://hub.docker.com/r/liferay/portal)
- [Liferay/DXP](https://hub.docker.com/r/liferay/dxp)

1. Using the links above, find the version of the Liferay container you need. 
1. Note the `docker pull` command that lists the full tag name for your container. The tag name looks like this: `liferay/dxp:7.3.10-ga1` or this: `liferay/portal:7.3.5-ga6`. 
1. Open `gradle.properties` in your Liferay Workspace. 
1. Specify the Docker tag using the property `liferay.workspace.docker.image.liferay`. Here are some examples: 

   ```properties
   liferay.workspace.docker.image.liferay=liferay/dxp:7.3.10-ga1
   ```

   ```properties
   liferay.workspace.docker.image.liferay=liferay/portal:7.3.5-ga6
   ```

1. Save the file and run this command from your workspace's root folder: 

   ```bash
   ./gradlew createDockerContainer
   ```
