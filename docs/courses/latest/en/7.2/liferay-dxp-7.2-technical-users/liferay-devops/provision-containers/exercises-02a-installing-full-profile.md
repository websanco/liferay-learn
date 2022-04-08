<h3 class="exercise">Exercises</h3>

# Review the Wildfly Configuration

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Review the configuration files for the Liferay instance deployed to the Wildfly server</li>
			<li>Start up the Wildfly instance in a container and login into the platform</li>
		</ul>
</div>

#### Find Environment Variables in the Docker File

Let's take a look at how we've configured out Wildfly stack. We start with an OpenJDK base image and add a fresh Wildfly server along with all of our Liferay dependencies and configuration files.

1. **Open** the _Dockerfile_ for the Wildfly server in your preferred text editor.
	* You can find the file in your _liferay/liferay-wildfly_ folder.
	* In this folder you'll also find the Wildfly server configuration files and Liferay dependencies.
1. **Find** the `FROM` instruction at the beginning of the file.
```dockerfile
FROM openjdk:8u171-jdk-alpine3.8
```
You'll see that we are again using an OpenJDK:Alpine image as the base image for our container. The base image takes an Alpine Linux container and installs Java 8u171 via OpenJDK. 
1. **Find** the `# declare environment variables` comment around line 3.
1. **Review** the environment variables:
``` Dockerfile
ENV LIFERAY_HOME=/opt/liferay
ENV DOWNLOADS=/opt/liferay/downloads
ENV WILDFLY_HOME=$LIFERAY_HOME/wildfly-[version]
ENV WILDFLY_FILE=wildfly-[version].Final.tar.gz
ENV LIFERAY_DEPENDENCIES_BASE=liferay-ce-portal-dependencies-[version]
ENV LIFERAY_DEPENDENCIES_FILE=liferay-ce-portal-dependencies-[version].zip
ENV LIFERAY_OSGI_BASE=liferay-ce-portal-osgi-[version]
ENV LIFERAY_OSGI_FILE=liferay-ce-portal-osgi-[version].zip
ENV LIFERAY_WAR_FILE=liferay-ce-portal-[version].war
```
Here we are declaring where the _LIFERAY\_HOME_ and _downloads_ directories will be created in the container. We also define the variables we'll be referencing throughout the rest of the Dockerfile.

With our variables defined, let's start using them.

#### Review the System and Create User Commands
1. **Find** the `# create user and liferay directories` comment around line 19.
	* Let's take a look at the `RUN` command:
```Dockerfile
RUN apk update && \
	apk add curl bash tar tree unzip && \
	adduser -D -h /etc/liferay liferay && \ 
	addgroup liferay liferay && \
	mkdir -p $WILDFLY_HOME && \
	chown -R liferay:liferay $LIFERAY_HOME
```
The first couple of lines update `apk`, the package manager for Alpine Linux, and install the tools we'll be using as we build our image (_curl_, _bash_, _tar_ and _unzip_). Next we create a new user, _liferay_, with the default settings. We also set the user home to _/etc/liferay_. We then create a new group, _liferay_ and add the _liferay_ user to the group. The `mkdir` command creates a _wildfly-11.0.0_ directory (and its parent directories) at _/opt/liferay/wildfly-11.0.0_. Finally we recursively set the owner and group for the _/opt/liferay_ directory to our new _liferay_ user and group.
1. **Find** the `WORKDIR` command:
```Dockerfile
WORKDIR $LIFERAY_HOME
```
	* This changes the Dockerfile's working directory to _/opt/liferay_. The `RUN` and `COPY` commands that follow will be executed in _/opt/liferay_.
1. **Find** the `USER` command.
```Dockerfile
USER liferay:liferay
```
	* This changes the system user to _liferay_ from the _liferay_ group. The `RUN` and `COPY` commands that follow will be executed as the _liferay_ user. 
1. **Find** the `RUN` command:
```dockerfile
RUN mkdir -p $DOWNLOADS && \
	mkdir -p $LIFERAY_HOME/data
```
	* This creates a _downloads_ directory (and the parent directory required) at _/opt/liferay/downloads_. It then creates a _data_ directory (and any parent directory required) at _/opt/liferay/data_.

Now that we've set up our user and Liferay Home directory, let's start copying over our dependencies.

#### Review the Dependencies for the Server
1. **Find** the `# copy liferay and server dependencies` comment around line 34.
	* These commands copy over our Wildfly server and dependencies to the specified locations in the container while simultaneously setting the owner (_liferay_) and group (_liferay_) for the files and directories we create.
```Dockerfile
COPY --chown=liferay:liferay ./dependencies/bundles/$WILDFLY_FILE $DOWNLOADS/$WILDFLY_FILE
COPY --chown=liferay:liferay ./dependencies/bundles/$LIFERAY_DEPENDENCIES_FILE $DOWNLOADS/$LIFERAY_DEPENDENCIES_FILE
COPY --chown=liferay:liferay ./dependencies/bundles/$LIFERAY_OSGI_FILE $DOWNLOADS/$LIFERAY_OSGI_FILE
COPY --chown=liferay:liferay ./dependencies/bundles/$LIFERAY_WAR_FILE $DOWNLOADS/$LIFERAY_WAR_FILE
```
We are copying the files from the _dependencies_ folder on the host machine to the _downloads_ folder we created in the _LIFERAY\_HOME_ directory. Once the dependncies are extracted and moved to their respective locations, the _downloads_ folder will be deleted.

#### Review the Provisioning Steps
1. **Find** the `# extract and provision the wildfly server` comment around line 40.
	* Let's take a look at the commands we'll use to set up our server. The following commands are executed in sequential order.

	This first command will extract the `wildfly-[version].Final.tar.gz` to the _/opt/liferay/wildfly-[version]_ directory.
	```dockerfile
	RUN tar -xf $DOWNLOADS/$WILDFLY_FILE --strip-components 1 -C $WILDFLY_HOME && \
	```
	Next we extract the `liferay-dxp-dependencies-[version].zip` file to the _/opt/liferay/downloads_ directory.
	```dockerfile
		unzip -q $DOWNLOADS/$LIFERAY_DEPENDENCIES_FILE -d $DOWNLOADS && \
	```
	 Then we create a new directory structure with necessary parent directories at _/opt/liferay/wildfly-[version]/modules/com/liferay/portal/main_
	```dockerfile
		mkdir -p $WILDFLY_HOME/modules/com/liferay/portal/main && \
	```
	Using the `-r` flag we recursively copy the extracted _liferay-dxp-dependencies-[version] directory and all the files in the directory to the newly created _/opt/liferay/wildfly-[version]/modules/com/liferay/portal/main_ directory.
	```dockerfile
		cp -r $DOWNLOADS/$LIFERAY_DEPENDENCIES_BASE/* $WILDFLY_HOME/modules/com/liferay/portal/main && \
	```
	After, we extract the `liferay-dxp-osgi-[version].zip` file to the _/opt/liferay/downloads_ directory.
	```dockerfile
		unzip -q $DOWNLOADS/$LIFERAY_OSGI_FILE -d $DOWNLOADS && \
	```
	Then we run the `mkdir` command to create a new _osgi_ directory with necessary parent directories at _/opt/liferay/osgi_
	```dockerfile
		mkdir -p $LIFERAY_HOME/osgi && \
	```
	Again, we recursively copy the extracted _liferay-dxp-osgi-[version]_ directory and all its files to the newly created _/opt/liferay/osgi_ directory.
	```dockerfile
		cp -r $DOWNLOADS/$LIFERAY_OSGI_BASE/* $LIFERAY_HOME/osgi && \
	```
	The next command extracts the `liferay-dxp-[version].war` to a new _/opt/liferay/wildfly-[version]/standalone/deployments/ROOT.war_ directory.
	```dockerfile
		unzip -q $DOWNLOADS/$LIFERAY_WAR_FILE -d $WILDFLY_HOME/standalone/deployments/ROOT.war && \
	```
	Here we create an empty _dodeploy_ file in `ROOT.war`. This file triggers the deployment of `ROOT.war`
	```dockerfile
		touch $WILDFLY_HOME/standalone/deployments/ROOT.war.dodeploy && \
	```
	This last command will forcibly delete the _/opt/liferay/downloads_ directory and all its contents.
	```dockerfile
		rm -rf $DOWNLOADS
	```

#### Examine the Configuration Files

1. **Find** the `COPY` commands that follow the `RUN` command we just looked at.
	* Let's take a closer look at the files we are copying over. As with the files that we copied over before, we are also setting the owner and group for the files when we copy them over to the container.
	Here we are copying the `module.xml` file from _liferay-wildfly\config_ on the host machine to _/opt/liferay/wildfly-[version]/modules/com/liferay/portal/main/_ in the container.
	The `module.xml` file enables Wildly to recognize the _jars_ we've added to the container in our dependencies folders.
	```dockerfile
	COPY --chown=liferay:liferay ./config/module.xml $WILDFLY_HOME/modules/com/liferay/portal/main/module.xml
	```
	Next we'll copy over the `standalone.xml` file from the _configs_ folder to _/opt/liferay/wildfly-[version]/standalone/configuration/_ in the container. The `standalone.xml` file is where we can configure the Wildfly server. You can take a look through the file to see the standard Liferay configurations, as well as configurations specific to this training.
	```dockerfile
	COPY --chown=liferay:liferay ./config/standalone.xml $WILDFLY_HOME/standalone/configuration/standalone.xml
	```
	Here we'll copy over the `standalone.conf` file to _/opt/liferay/wildfly-[version]/bin/_. This file is a script that will run additional configurations.  
	```dockerfile
	COPY --chown=liferay:liferay ./config/standalone.conf $WILDFLY_HOME/bin/standalone.conf
	```
	Here we are copying the `portal-ext.properties` file to _/opt/liferay_ in the container. In this file we are setting configurations for the Liferay portal including disabling the setup wizard and enabling the OSGi console. 
	```dockerfile
	COPY --chown=liferay:liferay ./config/portal-ext.properties $LIFERAY_HOME/portal-ext.properties
	```

Your provisioning section should look something like this:
```dockerfile
# extract and provision the wildfly server
RUN tar -xf $DOWNLOADS/$WILDFLY_FILE --strip-components 1 -C $WILDFLY_HOME && \ 
	unzip -q $DOWNLOADS/$LIFERAY_DEPENDENCIES_FILE -d $DOWNLOADS && \
	mkdir -p $WILDFLY_HOME/modules/com/liferay/portal/main && \
	cp -r $DOWNLOADS/$LIFERAY_DEPENDENCIES_BASE/* $WILDFLY_HOME/modules/com/liferay/portal/main && \
	unzip -q $DOWNLOADS/$LIFERAY_OSGI_FILE -d $DOWNLOADS && \
	mkdir -p $LIFERAY_HOME/osgi && \
	cp -r $DOWNLOADS/$LIFERAY_OSGI_BASE/* $LIFERAY_HOME/osgi && \
	unzip -q $DOWNLOADS/$LIFERAY_WAR_FILE -d $WILDFLY_HOME/standalone/deployments/ROOT.war && \
	touch $WILDFLY_HOME/standalone/deployments/ROOT.war.dodeploy && \
	rm -rf $DOWNLOADS

COPY --chown=liferay:liferay ./config/module.xml $WILDFLY_HOME/modules/com/liferay/portal/main/module.xml
COPY --chown=liferay:liferay ./config/standalone.xml $WILDFLY_HOME/standalone/configuration/standalone.xml
COPY --chown=liferay:liferay ./config/standalone.conf $WILDFLY_HOME/bin/standalone.conf
COPY --chown=liferay:liferay ./config/portal-ext.properties $LIFERAY_HOME/portal-ext.properties
```

#### The Final CMD Command

1. **Find** the `CMD` command at the end of the _Dockerfile_.
	* Once the Docker image is created, this is the command that will run on container start up.
```dockerfile
CMD $WILDFLY_HOME/bin/standalone.sh -b=0.0.0.0
```
	When the container starts it will execute the `standalone.sh` script which will start up the Wildfly server and binds the sever to port `0.0.0.0`.
1. **Close** the file.

We just reviewed the Dockerfile for our Wildfly server. Before we start it up, let's take a look at our docker-compose file as well.

#### Review the Docker-Compose File

1. **Open** the _docker-compose.yml_ for the Wildfly stack in your preferred text editor.
1. **Find** the _liferay-wildfly_ service around line 10.
```dockerfile
liferay-wildfly:
    build: .
    container_name: liferay-wildfly 
    networks: 
      - network-liferay-wildfly 
    ports: 
      - 9090:8080
      - 127.0.0.1:31311:11311 
    volumes:
      - ./volumes/liferay-wildfly/osgi/configs:/opt/liferay/osgi/configs
      - volume-liferay-wildfly-data:/opt/liferay/data
```
The `build` command points to the build context and configurations that will be applied to the image at build time. In this case Docker will reference the _Dockerfile_ in the current folder. When the container is generated it will be named _liferay-wildfly_. If this option is removed Docker will automatically generate a name for the container. Note that every container name must be unique. Assigning a custom container name here will result in Docker only being able to generate one container from this service which may lead to issues if trying to scale the service. In `ports` lines we expose the container's ports to the host machine. The container's port `8080` will be accessible at the host machine's port `9090`. Port `11311` for the container can be accessed at port `127.0.0.1:31311` on the host machine.
	
In the `volumes` configuration we reference the _/volumes/liferay-wildfly/osgi/configs/_ folder on the host machine, and mounting the folder to _/opt/liferay/osgi/configs_ in the container. Files added to the _/configs_ folder on the host machine will also be added to _/configs_ in the container. We  also mount the named volume _volume-liferay-wildfly-data_ (defined at the end of the _docker-compose_ file) to _/opt/liferay/data_ in the container. Remember, as this is a volume, Docker will manage its directory structure automatically.

#### Start the Wildfly Server

Now that everything is in order, let's start our new Wildfly service.

1. **Open** a new _Terminal_ window.
1. **Go to** the _liferay/liferay-wildfly_ folder in the Terminal.
1. **Run** the following command to build the Wildfly image:
```shell
docker-compose build liferay-wildfly
```
1. **Run** the following command to start up the Wildfly service.
```shell
docker-compose up -d liferay-wildfly
```
Docker will create and start a new container.
	* Remember: You can run `docker ps` to see a list of all active containers.
1. **Go to** _localhost:9090_ in a web browser.
	* You should see the Liferay DXP welcome page. 
1. **Click** on the _Sign In_ link near the top right of the page.
1. **Sign in** with the following credentials:
	* Email Address: `test@liferay.com`
	* Password: `test`
1. **Go to** _I Agree_ on the _Terms of Use_ page.
1. **Type** in a password reminder prompt.
1. **Click** _Save_.

We've just started up our Wildfly server.
