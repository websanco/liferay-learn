## Introduction to Docker

Docker is a popular tool that makes it possible to create, run, test, and deploy applications all within containers. At their most basic level, containers collect all the required components and dependencies of a development environment and store them in a portable package. 

<div class="key-point">
Key Point: <br />
Docker packages an application with all of its dependencies into a standardized unit for software development. 
</div>

You can think of these standardized units as something like an old video game. When you want to play a game, you simply put a cartridge or disk in the game console, and the game works. If you want to share the game with a friend, that friend can put the cartridge or disk in their game console, and the game will work in that console as well. Docker makes running software as simple as turning on a console with the game in. 

<figure>
	<img src="../images/chapter-1/docker-logo.jpg" style="max-width: 50%" /> 
	<figcaption style="font-size: x-small">Fig.1 Docker logo</figcaption>
</figure>

<br />

> The operations team at Livingstone Hotels & Resorts is interested in deploying their Liferay DXP stack with Docker. The team wants to use Docker containers to deploy the individual services that make up the stack to their live production environment. 


### Docker Images {#images}

You can think of a Docker _image_ as the recipe or template that Docker will use to build up the layers that make up a Docker container. 

<br />

<div class="key-point">
Key Point: <br />
A Docker image is a standalone, executable package that includes everything needed to run an application: code, runtime, system tools, system libraries, and settings.
</div>

The specifics of what an image will be are defined in a _Dockerfile_.

A Dockerfile is a simple text file where you designate the commands Docker will run to build an image. You can then use the `docker build` command to generate an image from the instructions in the Dockerfile and download all of the layers required by the image.

One of the benefits of the Dockerfile is that you don't have to start from scratch. You can reference and build on existing images. There are a number of existing images on [Docker Store](https://store.docker.com/) or [Docker Hub](https://hub.docker.com/). Layering images is a huge advantage.

### Containers {#containers}

When an image is run in the Docker engine, it becomes a _container_. In other words, a container is a running instance of an image. A Docker image is run using the following command:
```bash
docker run [OPTIONS] IMAGE [COMMAND] [ARG...]
```

<div class="key-point">
Key Point: <br />
Containers allow you to run applications that share an OS kernel, while also keeping the apps isolated from one another. 
</div>

An application's code and environment are wrapped up in a container, which acts similarly to a Virtual Machine, but with less overhead. Virtual machines require a full guest operating system which can make them relatively resource-intensive. 

<figure>
	<img src="../images/chapter-1/virtual-machine.png" style="max-width: 100%" /> 
	<figcaption style="font-size: x-small">Fig.2 Resources required to run a virtual machine</figcaption>
</figure>

Containers, on the other hand, benefit from sharing the host machine's OS and kernel, allowing them to start up quickly and use fewer resources. The OS layers are shared between the different containers. Because Docker is an application layer construct, containers use less storage and RAM. They also don't need separate VMs per container, making it much more efficient. 

<figure>
	<img src="../images/chapter-1/container-apps.png" style="max-width: 100%" /> 
	<figcaption style="font-size: x-small">Fig.3 Resources required to run containers</figcaption>
</figure>

<div class="note">
Note: You can run Docker on a host server or in a virtual machine.
</div>

### Volumes {#volumes}

When running the container, you may need a way to persist data. There are three ways of storing data in Docker:
<ul>
	<li><b>Bind mounts</b> may be stored anywhere on the host system. They may even be important system files or directories. Non-Docker processes on the Docker host or a Docker container can modify them at any time.</li>
	<li><b>Volumes</b> are stored in a part of the host filesystem managed by Docker (/var/lib/docker/volumes/ on Linux). Non-Docker processes should not modify this part of the filesystem.</li>
	<li><b>Tmpfs mounts (Linux only)</b> are stored in the host system’s memory only, and are never written to the host system’s filesystem.</li>
</ul>

<figure>
	<img src="../images/chapter-1/types-of-mounts-volume.png" style="max-width: 70%" /> 
	<figcaption style="font-size: x-small">Fig.4 Methods of persisting container data</figcaption>
</figure>


<br />

Although _Bind mounts_ might be convenient for testing purposes, they introduce some risk in production environments, as data is exposed and can be changed from the file system.


**Volumes** are recommended as the preferred way to persist data generated by containers. 

<div class="key-point">
Key Point: <br />
Volumes have several advantages over bind mounts:
<ul>
	<li> Volumes are easier to back up or migrate than bind mounts.</li>
	<li> You can manage volumes using Docker CLI commands or the Docker API.</li>
	<li> Volumes work on both Linux and Windows containers.</li>
	<li> Volumes can be more safely shared among multiple containers.</li>
	<li> Volume drivers let you store volumes on remote hosts or cloud providers to encrypt the contents of volumes or to add other functionality.</li>
	<li> New volumes can have their content pre-populated by a container.</li>
</ul>
</div>

### Official Liferay Images {#dockerhub}

As mentioned before, DockerHub is a community driven library of Docker images. You can find all kinds of images for many kinds of projects and technologies. Many of the images are maintained by community members, but there are official vendor maintained repositories as well. Liferay maintains official repositories for Liferay DXP, CE and even Commerce on the Liferay organization page on DockerHub. You can reference these maintained images when building your Liferay stack. You can easily pull an image to your local host by running the `docker pull liferay/[repository]:[tag]` command. You can easily start a Liferay container directly from your terminal by running the image and referencing the DockerHub tag. We'll take a look at this a little later on. You can also reference these images as a starting point in Dockerfiles when developing a custom solution.

All of Liferay's images can be found by going to [https://hub.docker.com/u/liferay](https://hub.docker.com/u/liferay)

### Key Benefits of Using Docker {#benefits}

Using the above features, Docker benefits both Developers and System Administrators. 

Docker brings major benefits to the development and deployment process in the following ways:
<ul>
	<li>Portability: Applications, dependencies, and data can be easily ported across any environment.</li>
	<li>Reduced Overhead: There is no need to add the additional OS and kernel layers that a VM requires to run.</li>
	<li>Shared Data: Volumes can be used to easily store and share data across containers.</li>
	<li>Version Control: Because of the file-based nature of Dockerfiles, changes can easily be tracked using a version control system.</li>
</ul>

These are just a few benefits. We will be using Docker throughout this course to see how we can take advantage of these features in context of Liferay DXP. We'll start by first looking at how to install Docker on Windows, Ubuntu, and MacOS.

<div style="page-break-before: always"></div>

### Docker Commands {#commands}

Below is a list of the docker commands we'll be using most commonly throughout our exercises. 

<table style="width:100%">
  <tr>
    <th>Command</th>
    <th>Description</th>
  </tr>
  <tr>
    <td><code>docker-compose build [service]</code></td>
    <td>Builds container from service image</td>
  </tr>
  <tr>
    <td><code>docker-compose up -d [service]</code></td>
    <td>Starts a docker service in detached mode - without logs</td>
  </tr>
  <tr>
    <td><code>docker-compose down</code></td>
    <td>Stops and removes all running service containers </td>
  </tr>
  <tr>
    <td><code>docker ps</code></td>
    <td>Displays all active containers</td>
  </tr>
  <tr>
    <td><code>docker-compose restart [service]</code></td>
    <td>Restarts a service container</td>
  </tr>
  <tr>
    <td><code>docker-compose logs -f [service]</code></td>
    <td>Displays and follows logs for a service container</td>
  </tr>
</table> 

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>An ____________________ is a standardized unit in Docker.</li>
	<li>The _______________ lets you define the environment needed to run an application.</li>
	<li>When an _______________ is run, it becomes a _______________.</li>
	<li>The recommended way to store data is to use _____________.</li>
</ul>
</div>
