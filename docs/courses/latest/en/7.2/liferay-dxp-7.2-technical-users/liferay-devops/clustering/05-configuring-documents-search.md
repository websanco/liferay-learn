## Configuring Documents and Search

With ClusterLink and a few other configurations, half the battle is already won. Each of our nodes can communicate with one another; our cache is clustered as well as our scheduler. The last half of the battle comes with the content portion of Liferay. In this section, we'll cover clustering Liferay's Document Repository and Search.

### Clustering the Document Repository {#docrepo}

Each of our Liferay instances has its own document repository that stores content. If our document repository isn't clustered, content created in Node 1 is stored in Node 1's document repository. Node 2 has its own document repository that is unaware of Node 1's and vice versa. As it stands, they'll never sync with one another.

<div class="note">
Note: Regardless of which storage we use, we want each of our nodes to be able to access the repository. This may require additional configuration on the repository if you're using a remote repository.
</div>

<div class="key-point">
	Key Point: <br>
	We can take three different approaches to cluster our document repository:
	<ul>	
		<li>System Settings</li>
		<li>Configuration File</li>
		<li>portal-ext.properties</li>
	</ul>
</div>

We'll cover how to cluster our document repository using each of these approaches.

By default, the document repository uses what's known as Simple File System Store. File System Store uses a local folder to store the files of the document repository. Since we are clustering, this requires a shared drive. We have the ability to change where Liferay creates the local folder, but otherwise it defaults to `LIFERAY_HOME/data/document_library`.

Our first method is to head over to the *System Settings* section under the _Configuration_ menu. To configure Simple File System Store, click on *File Storage → Simple File System Store*. By default, the document repository will be saved in `LIFERAY_HOME/data/document_library`. You can either edit the settings or keep the default settings. Hit the save button, and those settings will take effect. 

<div class="note">
Note: If you change the setting while the server is running, make sure that the new location has the same files available that the old location had: These files are naturally required at runtime to deliver the stored content.
</div>

The second method is through the configuration file called `com.liferay. portal.store.file.system.configuration. FileSystemStoreConfiguration.config`. Typically, this file is created when the export feature is used in *System Settings* and placed within the `osgi/configs/` folder. Modify this file, and wait a few seconds for Liferay to pick up the changes.

For those familiar with older versions of Liferay DXP: Many of the properties used to be in `portal.properties`. With the migration to the Modular Framework, many of the properties have been taken out of portal.properties and are now configured in *System Settings* or a configuration file. For the case of *Simple File System Store*, nothing has to be changed to implement it since it is the default.

Although this is the default means of storage for the document repository, there are still some things that need to be addressed. Using the method creates a folder structure like this: `/companyId/folderId/numericFileEntryName/versionNumber`. This folder structure causes files to be very closely bound to Liferay.

Along the same lines as the *Simple File System Store* is the *Advanced File System Storage*. What makes this advanced is the way that files are stored to overcome issues of storing too many files in a single folder. This is done by alphabetically nesting files into folders, allowing for more files to be stored overall and fewer files stored per folder.

To enable *Advanced File System Storage*, in *System Settings → File Storage*, select *Advanced File System Store*. Leave the default `data/document_library` or configure the root directory to your preferences and click save. The document repository will be stored using Liferay's *Advanced File System Store*.

Look in `osgi/configs` to locate `com.liferay. portal.store.file.system.configuration. AdvancedFileSystemStoreConfiguration.config`. If this file does not exist, you can create it by hand. Make whatever modification you desire to the file to change the configuration of the *Advanced File System Storage*.

To enable *Advance File System Storage* in `portal-ext.properties`, set the property `dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore` (we have prepared the setting, commented, for you in the file). Restart the server so your changes take effect. There are no other properties associated with *Advanced File System Store* in `portal.properties`.

If you already have a content repository that uses CMIS, you are able to point the Liferay document repository to the CMIS repository. Enter the repository URL, credentials, and your instance's root directory, typically Liferay Home in the CMIS settings of the _File Storage_ options.

To configure CMIS settings using a configuration file, locate the file `com.liferay.portal.store.cmis.configuration.CMISStoreConfiguration.config`. If this file doesn't exist, you can make it by hand or use *System Settings* to set the configurations there. Once the changes are made, Liferay picks them up and applies them. 

To configure CMIS settings, you can:
- Set the property `dl.store.impl=com.liferay.portal.store.cmis.CMISStore` to enable CMIS storage.
- Restart the server.
- Modify the configuration file related to CMIS to mount the CMIS server.

Liferay also supports the Java Content Repository standard. Behind the scenes, Liferay is using Jackrabbit, a JSR-170 compliant document repository. JCR functions similarly to the File Storage System; it allows JCR clients to access and store the files in a database:
- Navigate to *System Settings*, followed by Foundation, and select *JCR Store Configuration*. 
- Modify the properties to reflect your desired configuration. 
    - Additional configuration can be done with the `repository.xml` file.

When using a configuration file for JCR, modify *com.liferay.portal.store.jcr.configuration.JCRStoreConfiguration.config*. Remember that the xml still needs to be configured as well, regardless of which option you use. Portal properties has much of the configuration removed and is not a complete way to configure JCR.

If you have an account with Amazon S3, all you need is the account info, and you're on your way to storing your files in the cloud. Go to the *S3 Store* options in the _File Storage_ settings. Enter all the relevant info of your Amazon simple storage service.

To modify the configuration file, search or create *com.liferay. portal.store.s3.configuration.S3StoreConfiguration.config*. In *portal-ext.properties*, set *dl.store.impl=com.liferay.portlet.documentlibrary.store.S3Store* to enable Amazon S3 storage. Configure the `.config` file to finish the mounting of the server.

The document repository can also be stored in the Liferay database by setting `dl.store.impl=com.liferay.portal.store.db.DBStore`. This is generally not recommended because of performance issues. Other repositories such as Alfresco, Sharepoint, and Documentum have connectors that can be found in the Liferay marketplace.

To review, using *System Settings*, we are able to cluster our document repository very quickly and easily. We can also use configuration files to manually configure our document repository settings. Although `portal-ext.properties` is an option, it only provides the ability to set which method to cluster the document repository.

### Clustering Elasticsearch {#elastic}

Elasticsearch is a distributed search engine built on Lucene. It is a highly-scalable full-text search and analytics engine. You can use it to store, search, and analyze a large volume of data. Elasticsearch is also extensible with plugins, visualizations, and tools.

Elasticsearch is the default Search Engine in DXP. 

<div class="key-point">
	Key Point: <br>
	Liferay DXP comes with an embedded instance of Elasticsearch for testing and demonstration purposes, but for production, you'll need to create a standalone instance.
</div>

Creating a standalone instance will:
- Ease the burden on your application server
- Enable you to cluster across multiple nodes
- Keep your index intact if your Liferay instance fails

Elasticsearch provides:

- (Near) real-time data
- Real-time advanced analytics
- High availability
- Multitenancy
- Full-text search
- Document-oriented search
- Developer-friendly RESTful API

Elasticsearch is massively distributed and schema-free, built on top of Lucene, which provides stability and familiarity for those who have worked with Lucene in the past. Elasticsearch is licensed under the Apache 2 Open Source license.

Let's talk about some basic search concepts:

- **Index**: A collection of documents that have somewhat similar characteristics. E.g., customer data, product catalog
- **Type**: A type is a logical category/partition of your index. In general, a type is defined for documents having the same set of common fields.
- **Document**: Basic unit of information that can be indexed; expressed in JSON
- **Near Real-Time (NRT)**: There is a slight latency (normally one second) from the time you index a document until the time the document becomes searchable.
- **Cluster**: Elasticsearch itself can be clustered to improve performance and stability. Each cluster will consist of some number of Elasticsearch server nodes. Each node can have a unique name. The Liferay default is to use a random Marvel Comic Book character's name for each node.

Elasticsearch provides a very comprehensive and powerful REST API that you can use to interact with your cluster. You can check your cluster, node, and index health, status, and statistics, administer your cluster, node, and index data and metadata, perform CRUD (Create, Read, Update, and Delete) and search operations against your indices, and execute advanced search operations such as paging, sorting, filtering, scripting, aggregations, and many other patterns:
```
curl -X <REST Verb> <Node>:<Port>/<Index>/<Type>/<ID>
```
For example:
```
curl 'localhost:9200/_cat/indices?v' 
curl -XPUT 'localhost:9200/customer?pretty' 
curl -XPUT 'localhost:9200/customer/external/1?pretty' \
-d '{ "name": "John Doe" }'
```

Elasticsearch provides the ability to subdivide your index into multiple pieces called shards. The number of shards can be defined. Each shard is a fully-functional and independent "index" that can be hosted on any node in the cluster. You can distribute and parallelize operations across shards (potentially on multiple nodes) to increase performance. Scaling is completely controlled by Elasticsearch and transparent for the end user. A copy of an index's shard, called a replica, can be created. Replicas provide high availability in case of failure. They also allow scaling out of search volume, as searches can be executed on all replicas in parallel.

Each index can be split into one or more shards. Each index can also be replicated. The number of shards and replicas can be defined per index at the time the index is created. *Note:* After the index is created, you may change the number of replicas dynamically at any time, but you can't change the number of shards. By default, Elasticsearch will create:
- 5 primary shards
- 1 replica shard

For EE customers, Liferay provides two plugins for Elasticsearch:
- **Shield** is a special module deployed to the platform that will allow you to set security to the ES server.
- **Marvel** allows admins to proxy to Kibana to view the `dashboards.na`.

In addition to being easy to use and query, Elasticsearch provides a simple configuration file. Each Elasticsearch instance can be configured with a single file, only overriding settings you need changed. If you want the default setting for a configurable area, you don't need to specify it in the file. Elasticsearch's configuration can be found in: [ELASTIC_HOME]/config/elasticsearch.yml. `ELASTIC_HOME` is the directory in which your Elasticsearch instance is configured.

Configuration for Elasticsearch is contained in YAML (*Yet Another Markup Language*) files. YAML syntax is compact and simple. It supports two basic data structures:

- **Associative arrays:** these are associated key-value pairs, separated by a **:**

```yaml
name: Ray
job: Engineer
```
- **Lists:** these are sequential values denoted by a **-**

```yaml
- one
- two
- 3
- what?
```
All settings for Elasticsearch are set in YAML as one of these two structures.

The two most common settings to configure in Elasticsearch are cluster name and node name. By default, node names are randomly generated, and the cluster name is *Elasticsearch*. In addition, if you need to change the IP or port ranges for cluster nodes in Elasticsearch, those are common settings as well.

<!-- Running on Linux Host, adjust memory to run Elasticsearch -->

<div class="summary"><h3>Summary</h3>

<ul>
	<li>By default, the document repository uses the _______________________________________________________________________________.</li>
	<ul>
		<li>___________________________________________________ uses a local folder to store the files of the document repository.</li>
	</ul>
	<li>_____________________________________________________ is the default Search Engine in Liferay DXP.</li>
	<li>_____________________________________________________ provides the ability to subdivide your index into multiple pieces called __________________________________.</li>
</ul>
</div>
