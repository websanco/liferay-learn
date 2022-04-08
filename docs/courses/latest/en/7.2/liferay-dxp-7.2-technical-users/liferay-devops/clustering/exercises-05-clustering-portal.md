<h3 class="exercise">Exercises</h3>

## Configure the Portal for Clustering

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Update configurations for clustered Document Store</li>
			<li>Update configurations for clustered Elasticsearch</li>
			<li>Enable ClusterLink</li>
			<li>Update Docker configurations</li>
			<li>Run and verify the clustered stack</li>
		</ul>
</div>

#### Update the Docker Files for AdvanceFileSystem Store

1. **Find** the _# Configure the AdvancedFileSystemStore_ comment around line 40.
1. **Remove** the comment symbol from the `COPY` command under the _AdvancedFileSystemStore_ comment:
	```dockerfile
	COPY ./config/com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config $LIFERAY_HOME/osgi/configs/com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config
	```
	* We are copying over the following configuration:
	```conf
	rootDir="data/document_library"
	```
1. **Save** the file.

Note: We *would* be almost done here, we'd only need to activate the Advanced File System Store *if the document library didn't contain any documents*. However, at least the default site's logo is served from document library, so we'll need to migrate the documents from the existing to the prospective document library.

If you don't mind a broken image (you can set another logo), you can skip the migration part. Typically you'd configure the correct store initially, before starting Liferay DXP for the first time, so that the initialization process would store the logo in the correct store. However, a migration is not too uncommon, so we're documenting these steps as well:

Build the updated image and start (or restart) the container with the updated Advanced File System Store configuration. Note that the store is *configured*, but *not active*: You're still operating on the default Simple File System Store.

1. (TO DO: Docker commands) docker-compose down; docker-compose build liferay-tomcat-1; docker-compose up -d liferay-tomcat-1

1. **Navigate** to Control Panel / Configuration / Server Administration / Data Migration
1. **Select** AdvancedFileSystemStore as the target Document Library Store
1. **Migrate** your data to the new location (delete it from the old store when you're brave)

Note: Our Advanced File System Store will use the same directory as Simple File System Store, but another storage structure. In production systems (especially when your cluster is actually distributed), you typically want to choose a shared directory, e.g. a shared drive or a SAN. A SAN typically improves your options for backup - but choose any directory that you can share between the different servers. Here, we only share a common Docker-volume on the single computer where we install the cluster.

Now that the data exists in the newly configured location, shut down the server, so that you're not tempted to upload a new document to the old store that's still active. In a production system, you'd need to make sure that nobody else currently updates documents during this migration. We're now going to activate the store.

#### Enable the AdvancedFileSystem Store

Next, we'll enable and move our Document Library to the Advanced File System Store. The Advanced File System Store programmatically creates a structure that can expand to millions of files by alphabetically nesting the files in folders. This will allow us to store a large number of files without performance degradation. 

1. **Find** the _Document Library Service_ comment around line 45.
1. **Remove** the comment symbol (`#`) from the `dl.store.impl` property around line 52:
	```properties
	dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
	```
1. **Save** the file.

#### Update the Docker Files for Elasticsearch

1. **Open** the _Dockerfile_ for the Tomcat service.
	* This file can be located at _liferay/liferay-tomcat/_.
1. **Find** the _Point to external Elasticsearch container_ comment around line 37.
1. **Remove** the comment symbol (`#`) for the `COPY` command under the comment:
	```dockerfile
	COPY --chown=liferay:liferay ./config/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config $LIFERAY_HOME/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config
	```
	* This `COPY` command copies a Liferay `.config` for Elasticsearch from the host to _/opt/liferay/osgi/configs_ in the container.
	* The file enables the portal to connect to the external Elasticsearch container. 
	```conf
	operationMode="REMOTE"
	transportAddresses="elasticsearch:9300"
	clusterName="LiferayElasticsearchCluster"
	``` 

#### Update portal-ext.properties to Enable ClusterLink

Let's configure our Tomcat services for clustering.

1. **Open** the `portal-ext.properties` file for the _liferay-tomcat_ service.
	* You can find this file at _liferay/liferay-tomcat/config_.
1. **Find** the _Web Server_ settings around line 16.
1. **Replace** `false` with `true` for the _web.server.display.node_ configuration around line 23:
	```properties
	web.server.display.node=true
	```
	* Setting this property to `true` will enable displaying the server name at the bottom of every Liferay page. This can be helpful during development, as it shows the name of the node being accessed.
1. **Find** the _ClusterLink_ settings around line 55.
1. **Remove** the comment symbol (`#`) from the _cluster.link.enabled_ configuration around line 62:
	```properties
	cluster.link.enabled=true
	```
	* Setting this property to true enables the use of DXP ClusterLink.

#### Update Docker Settings

Before we can start up our cluster, we need to enable our _liferay-tomcat-2_ service.

1. **Open** the `docker-compose.yml` file for the _liferay-tomcat_ stack.
1. **Find** the _liferay-tomcat-2_ service in the middle of the document, around line 68.
1. **Remove** the comment symbols (`#`) from the service:
```dockerfile
liferay-tomcat-2:
     build: .
     container_name: liferay-tomcat-2
     depends_on:
       - mysql
     networks:
       - network-liferay-tomcat
     ports:
       - 8082:8080
       - 127.0.0.1:21311:11311
     volumes:
       - volume-liferay-tomcat-data:/opt/liferay/liferay-portal-[version]/data
       - volume-liferay-tomcat-2-work:/opt/liferay/liferay-portal-[version]/work
```
	* This will allow us to create the _liferay-tomcat-2_ service with Docker Compose. This service references the same _liferay-tomcat_ build context as does the _liferay-tomcat-1_ service.

#### Review Elasticsearch Settings

1. **Find** the _elasticsearch_ service around line 12.
	```dockerfile
	elasticsearch:
	    build: elasticsearch
	    container_name: elasticsearch
	    environment: 
	      ES_JAVA_OPTS: "-Xms512m -Xmx512m"
	    networks:
	      - network-liferay-tomcat
	    ports:
	      - 9200:9200
	      - 9300:9300
	    ulimits:
	      memlock:
	        soft: -1
	        hard: -1
	    volumes:
	      - volume-elasticsearch:/usr/share/elasticsearch/data
	```
	* The _elasticsearch_ service has a few references that we haven't seen before:
	* The `environment:` reference allows you to create an environment variable for the container from the _docker-compose.yml_ file. Here, we set the min and max heap sizes that the JVM will use for Elasticsearch.
	* The `ulimits:` reference overrides the default ulimits for the container.
1. **Find** the _liferay-tomcat-1_ service around line 51.
1. **Remove** the comment symbol (`#`) for the Elasticsearch dependency:
	```dockerfile
	      - elasticsearch
	```
	* This enables the _elasticsearch_ service as a dependency for _liferay-tomcat-1_. Docker will make sure a container for the _elasticsearch_ service is running before starting a container for the _liferay-tomcat-service_.
1. **Find** the _liferay-tomcat-2_ service around line 68.
1. **Add** the _elasticsearch_ service to the `depends_on` reference:
	```dockerfile
	depends_on:
    	- elasticsearch
    	- mysql
	```
1. **Save** the file.

#### Build the Updated Tomcat Images

1. **Open** a new _Terminal_ or _Command Line_ window at `liferay/liferay-tomcat`.
1. **Stop** any running containers if necessary:
	```shell
	docker-compose down
	```
	* You can use the `docker ps` command to see if there are any active containers.
1. **Rebuild** the _liferay-tomcat-1_ service:
	```shell
	docker-compose build liferay-tomcat-1
	```
	* This will rebuild the _liferay-tomcat-1_ image with the changes we just made.
1. **Build** the _liferay-tomcat-2_ service:
	```shell
	docker-compose build liferay-tomcat-2
	```
	* You'll notice that because the _tomcat-2_ service is referencing the same _Dockerfile_ as the _tomcat-1_ service, the initial build time for _tomcat-2_ will be significantly shorter than it was for _tomcat-1_. Docker will use cached image layers when creating the new _tomcat-2_ image. 

<div class="note">
	Note: You can also build multiple images at once by specifying multiple services in the docker command as follows: <code>docker-compose build haproxy liferay-tomcat-1 liferay-tomcat-2</code>
</div>

#### Build Elasticsearch and Start the Containers

1. **Build** the _elasticsearch_ service:
	```shell
	docker-compose build elasticsearch
	```
	- Linux users may have issues running the Elasticsearch container. If this is the case, it is because max virtual memory is too low. To create a permanent solution for this, run the following to increase the virtual memory to the minimum requirement before moving on to the next step:
	```
	$ sudo sysctl -w vm.max_map_count=262144
	```
1. **Start** the _liferay-tomcat_ stack:
	```shell
	docker-compose up -d liferay-tomcat-1
	docker-compose up -d liferay-tomcat-2
	docker-compose up -d haproxy
	```
	* You can run `docker-compose logs -f liferay-tomcat-1` to see the logs for the _liferay-tomcat-1_ node. You should see confirmation that the Elasticsearch cluster has been configured.
	* You can also run `docker-compose logs -f elasticsearch` to see the logs for Elasticsearch and should see confirmation on startup.
	<div class=note>
	Note: If you run into errors starting up the clustered nodes, let the liferay-tomcat-1 container completely start up before starting up liferay-tomcat-2, especially if starting up clean containers for the very first time.
	</div>
1. **Go to** _localhost:8081_ in a web browser.
	<img src="../images/chapter-3/c3s4-node1.png" style="max-width: 70%">
	* You'll see the node name on the bottom of the page. We enabled this feature in the `portal-ext.properties` file.
	* This is the _liferay-tomcat-1_ node. 
1. **Go to** _localhost:8082_ in the web browser.
	<img src="../images/chapter-3/c3s4-node2.png" style="max-width: 70%">
	* This is the _liferay-tomcat-2_ node.
1. **Go to** _localhost:9999_ in the web browser.
1. **Sign in** with the following credentials:
	* User Name: `test@liferay.com`
	* Password: `test`

Here you can see that load balancing is configured for our back-end servers and that both our servers are active and up.

<img src="../images/chapter-3/c3s4-haproxy-load.png" style="max-width: 100%">

#### Add a File to the liferay-tomcat-1 Document Repository

1. **Go to** _localhost:8081_ in your web browser to access _liferay-tomcat-1_.
1. **Open** the _Menu_.
1. **Click** on _`Site Administration → Content & Data → Documents and Media`_.
1. **Click** on _`Add → File Upload`_ to add a file.
1. **Click** on _Choose File_.
1. **Choose** the `hello.txt` file provided in the _liferay-tomcat/dependencies_ folder.
1. **Click** _Publish_.

#### Verify the Document Repository Clustering

1. **Go to** _localhost:8082_ in a web browser to access _liferay-tomcat-2_.
	* Use a different browser or incognito mode.
1. **Sign in** to the platform.
1. **Go to** _`Site Administration → Content & Data → Documents and Media`_ in the _Menu_.

You should see the `hello.txt` in the _liferay-tomcat-2_ Document Repository.

<img src="../images/chapter-3/c3s5a-share-docs.png" style="max-width: 30%;">

#### Verify Elasticsearch Settings

1. **Go to** _localhost:8081_ in a web browser.
1. **Go to** the _`Control Panel → Configuration → System Settings`_ in the *Menu*. 
1. **Click** on the *Search* settings under _Platform_ options.
	<img src="../images/chapter-3/c3s5-search.png">
1. **Click** on *Elasticsearch 6*.
	* You'll see that the configurations we set when copying over the `.config` file are enabled on the portal. The operation mode has been set to _REMOTE_ and the transport address and cluster-name match that we set in the file.


You are now running a clustered Liferay stack.
