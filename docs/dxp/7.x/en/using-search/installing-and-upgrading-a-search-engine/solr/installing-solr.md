# Installing Solr 

> **Availability:** Liferay 7.3 (DXP 7.3 FP1+; CE 7.3 GA7+)

```important::
   Liferay's Solr support is deprecated; Solr 8 will be the last supported Solr version. Please plan to migrate to `Elasticsearch <../elasticsearch/getting-started-with-elasticsearch.md>`_.
```

Solr is a popular enterprise search platform built on Apache Lucene. It's reliable, scalable, and fault tolerant. Read more about it [here](http://lucene.apache.org/solr/).

[Elasticsearch](../elasticsearch/getting-started-with-elasticsearch.md) is the default search engine that ships with Liferay, and some Liferay Search features are only available on Elasticsearch. There are circumstances that force you to use Elasticsearch instead of Solr. Read [Solr Limitations](./solr-limitations.md) for more information.

See the [Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651) for supported Solr and Elasticsearch versions.

## Blacklisting Elasticsearch-Only Features

Before installing Solr, you must [blacklist](../../../system-administration/installing-and-managing-apps/managing-apps/blacklisting-apps.md) certain DXP [features that only work with Elasticsearch](./solr-limitations.md). 
 

1. Create a configuration file named

   ```bash
   com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config
   ```

1. Give it these contents:

   ```properties
   blacklistBundleSymbolicNames=["com.liferay.portal.search.tuning.web.api","com.liferay.portal.search.tuning.web","com.liferay.portal.search.tuning.synonyms.web","com.liferay.portal.search.tuning.rankings.web"]
   ```

1. Place the file in `Liferay Home/osgi/configs`. 

   You must also stop the Elasticsearch Connector that ships with Liferay. If you're ready to stop and blacklist its bundles right now, use these contents in the blacklist configuration file:

```properties
blacklistBundleSymbolicNames=["com.liferay.portal.search.tuning.web.api","com.liferay.portal.search.tuning.web","com.liferay.portal.search.tuning.synonyms.web","com.liferay.portal.search.tuning.rankings.web","com.liferay.portal.search.elasticsearch6.spi","com.liferay.portal.search.elasticsearch6.api","com.liferay.portal.search.elasticsearch6.impl","Liferay Enterprise Search Monitoring ","Liferay Enterprise Search Security "]
```

## Installing 

There are two ways to install the Liferay Connector to Solr 7:

1. Navigate to [Liferay Marketplace](https://web.liferay.com/marketplace/) and download the app that corresponds to your portal.

<!-- Update links when available
   **Liferay Portal CE:** [Liferay CE Connector to Solr 7](https://web.liferay.com/marketplace/-/mp/application/118014614) 

   **Liferay DXP:** [Liferay Connector to Solr 7](https://web.liferay.com/marketplace/-/mp/application/117931595)
-->

   Once the app LPKG is downloaded, copy it to `Liferay_Home/osgi/marketplace`.

1. In your running portal instance, navigate to *Control Panel* &rarr; *Apps* &rarr; *Store*. Sign in using your credentials, search for Solr Search Engine, and purchase (it's free) the Liferay Connector to Solr 7 entry.

As you proceed, remember these terms: 

*Solr Home*: The center of the Solr system (pun intended). This directory is `solr-[version]/server/solr`.

*Liferay Home*: The root folder of your Liferay installation. It contains the `osgi`, `deploy`, `data`, and `license` folders, among others.

First install and configuring Solr 7, then installe and configure the Solr 7 connector for Liferay.

## Installing and Configuring Solr 7

To install and properly configure Solr for Liferay:

1. Download [Solr](https://archive.apache.org/dist/lucene/solr/8.6.3/) and unzip it.

1. Navigate to Solr Home (`solr-[version]/server/solr`) and create a new folder called `liferay`.

1. Create two new subfolders: `liferay/conf` and `liferay/data`.

1. Copy the contents of `Solr_Home/configsets/_default/conf` to `Solr_Home/liferay/conf`. From Solr Home run

   ```bash
   cp -r configsets/_default/conf/* liferay/conf/
   ```

1. Open the Liferay Connector to Solr 7's LPKG file with an archive manager.

   Open the `com.liferay.portal.search.solr7.impl.jar` file, and extract 

   ```
   META-INF/resources/solrconfig.xml
   ```

   and

   ```
   META-INF/resources/schema.xml
   ```

   to

   ```
   Solr_Home/liferay/conf
   ```

   This replaces the current `solrconfig.xml` and `schema.xml` files with ones that tell Solr how to index data coming from Liferay.

1. Create a `core.properties` file in `Solr_Home/liferay` and add this configuration:

   ```properties
   config=solrconfig.xml
   dataDir=data
   name=liferay
   schema=schema.xml
   ```

1. Checkpoint: your `Solr_Home/liferay` folder now has this structure:

   ```bash
   liferay
   ├── conf
   │   ├── lang
   │   │   ├── contractions_ca.txt
   │   │   ├── ....txt
   │   ├── managed-schema
   │   ├── params.json
   │   ├── protwords.txt
   │   ├── schema.xml
   │   ├── solrconfig.xml
   │   ├── stopwords.txt
   │   └── synonyms.txt
   ├── core.properties
   └── data
   ```

1. Start the Solr server by entering

   ./bin/solr start -f

   from the top-level folder of your Solr installation (`solr-[version]`).

1. The Solr server listens on port `8983` by default. Navigate to <http://localhost:8983/solr/#/~cores> (assuming you're testing locally with `localhost` as your host), and confirm that the `liferay` core is available.

Solr is now installed. Next install and configure the Solr connector.

## Installing and Configuring the Liferay Solr Adapter

Since Elasticsearch is the default search engine, the Elasticsearch connector is already installed and running. You must stop it before configuring the Solr connector.

### Stopping the Elasticsearch Connector

Stop the Elasticsearch connector bundle using the App Manager, the Felix Gogo shell, or the bundle blacklist. If you're a Liferay DXP customer, use the blacklist feature as described below. The App Manager and Gogo shell rely on the `osgi/state` folder to "remember" the state of the bundle. If you delete this folder (recommended during patching) the Elasticsearch connector is reinstalled and started automatically. 

Navigate to Control Panel &rarr; Apps &rarr; App Manager.

Once in the App Manager, search for *elasticsearch*. Find the Liferay Connector to Elasticsearch 6 module and click the Actions ((![Actions](../../../images/icon-actions.png))) button. Choose the Deactivate option.  This leaves the bundle installed, but stops it in the OSGi runtime.

Alternatively, use the [Felix Gogo shell](../../../liferay-internals/fundamentals/using-the-gogo-shell/using-the-gogo-shell.md) to stop the Elasticsearch connector. Enter

```bash
lb elasticsearch
```

You'll see two active bundles for the Liferay Connector to Elasticsearch 6: an API and an IMPL bundle. 

```bash
ID|State      |Level|Name
476|Active     |   10|Liferay (CE) Connector to Elasticsearch 6 - API (3.0.0)
478|Active     |   10|Liferay Portal Search Elasticsearch 6 API (3.0.4)
480|Active     |   10|Liferay Portal Search Elasticsearch 6 SPI (3.2.1)
706|Active     |   10|Liferay (CE) Connector to Elasticsearch 6 - Impl (3.0.0)
707|Active     |   10|Liferay Portal Search Elasticsearch 6 Implementation (3.0.15)
```
UPDATE OUTPUT

Stop the API bundle by entering 

```bash
stop [bundle ID]
```

In the example above, the `[bundle ID]` is `476`. 

**Liferay DXP:** DXP customers should [blacklist](../../../system-administration/installing-and-managing-apps/managing-apps/blacklisting-osgi-components.md) the Elasticsearch, Shield, and Marvel plugins.

1. Create a `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config`

   file with these contents:

   ```properties
   blacklistBundleSymbolicNames=["com.liferay.portal.search.elasticsearch6.spi","com.liferay.portal.search.elasticsearch6.api","com.liferay.portal.search.elasticsearch6.impl","Liferay Enterprise Search Monitoring ","Liferay Enterprise Search Security "]
   ```

   If the LES Security and Monitoring LPKG files are installed, you must blacklist these too.

1. Place the file in `Liferay Home/osgi/configs`.

### Install and Configure the Solr Connector

Now you're ready to install the connector:

1. Start Liferay, then deploy the Solr connector by copying the LPKG you downloaded to `Liferay_Home/deploy`.

   You'll see a `STARTED` message in your Liferay log once the Solr connector is installed. Here's what the log message looks like:

   ```bash
   2018-11-06 19:59:49.396 INFO  [pipe-start 943 944][BundleStartStopLogger:39] STARTED com.liferay.portal.search.solr7.api_2.0.5 [943]
   2018-11-06 19:59:49.490 INFO  [pipe-start 943 944][BundleStartStopLogger:39] STARTED com.liferay.portal.search.solr7.impl_2.0.11 [944]
   ```

1. To re-index against Solr, navigate to *Control Panel* &rarr; *Configuration* &rarr; *Search*, and click *Execute* next to the *Reindex all search indexes* option.

<!-->![Once the Solr connector is installed, you can re-index your Liferay data against your Solr server.](../../../images/solr-reindex.png)</!-->

In production deployments, specify your edits to the Solr connector's default configurations using a configuration file deployed to the `Liferay_Home/osgi/configs` folder. Name the file 

```
com.liferay.portal.search.solr7.configuration.SolrConfiguration.config
```

During testing and development, use the Solr 7 System Settings entry Control Panel &rarr; Configuration &rarr; System Settings for editing the default configurations.

<!-->
![You can configure Solr from Liferay's System Settings application. This is most useful during development and testing.](../../../images/solr-system-settings.png)
</!-->

The next article covers clustering Solr with SolrCloud.

## High Availability with SolrCloud

Use SolrCloud if you need a cluster of Solr servers. Note that to use SolrCloud in production, you should set up an [external ZooKeeper ensemble](https://cwiki.apache.org/confluence/display/solr/Setting+Up+an+External+ZooKeeper+Ensemble).  [ZooKeeper](http://zookeeper.apache.org/) is a centralized coordination service for managing distributed systems like your SolrCloud cluster.

The steps included here should be considered the bare minimum of what must be done to configure SolrCloud with Liferay. For example, these instructions cover configuring SolrCloud on a single machine, whereas a production environment would feature multiple physical or virtual machines. These instructions also assume you've followed the earlier section on *Installing and Configuring Solr 7*. Refer to the [SolrCloud guide for more information](https://cwiki.apache.org/confluence/display/solr/SolrCloud).

1. Stop the Solr server if it's running.

1. Navigate to the `Solr_Home/configsets` folder and create a folder called 

   `liferay_configs`

1. Copy the `conf` folder from `Solr_Home/liferay` to the `liferay_configs` folder you just created.

   The `configset/liferay_configs` folder contains the SolrCloud Liferay collection configuration and is uploaded to ZooKeeper. By copying the `conf` folder from the `liferay` server configured earlier, you're using the `schema.xml` and `solrconfig.xml` files provided with the Liferay Solr Adapter.

1. Next launch an interactive SolrCloud session to configure your SolrCloud cluster. Use this command:

   ```bash
   ./bin/solr -e cloud
   ```

1. Complete the setup wizard. These steps demonstrate creating a two-node cluster:

    -  Enter `2` for the number of nodes.
    -  Specify ports `8983` and `7574` (the defaults). Both nodes are started with the start commands printed in the log:

       ```bash
       Starting up Solr on port 8983 using command:
       "bin/solr" start -cloud -p 8983 -s "example/cloud/node1/solr"

       Waiting up to 180 seconds to see Solr running on port 8983 [|]  [-]  
       Started Solr server on port 8983 (pid=8846). Happy searching!

           
       Starting up Solr on port 7574 using command:
       "bin/solr" start -cloud -p 7574 -s "example/cloud/node2/solr" -z localhost:9983

       Waiting up to 180 seconds to see Solr running on port 7574 [|]  [/]  
       Started Solr server on port 7574 (pid=9026). Happy searching!
       ```

    -  Name the collection *liferay*.
    -  Split the collection into two shards.
    -  Specify two replicas per shard.
    -  When prompted to choose a configuration, enter *liferay_configs*. You should see a log message that concludes like this when the cluster has been started:

    ```bash
    SolrCloud example running, please visit http://localhost:8983/solr
    ```

Now you have a new collection called *liferay* in your local SolrCloud cluster.  Verify its status by running the *status* command:

```bash
./bin/solr status
```

You'll see log output like this:

```bash
Found 2 Solr nodes: 

Solr process 12828 running on port 8983
INFO  - 2019-07-18 16:46:35.137; org.apache.solr.util.configuration.SSLCredentialProviderFactory; Processing SSL Credential Provider chain: env;sysprop
{
  "solr_home":"/home/russell/liferay-bundles/liferay-portal-7.2.10-ga1/solr-7.5.0/example/cloud/node1/solr",
  "version":"7.5.0 b5bf70b7e32d7ddd9742cc821d471c5fabd4e3df - jimczi - 2018-09-18 13:07:55",
  "startTime":"2019-07-18T20:44:13.138Z",
  "uptime":"0 days, 0 hours, 2 minutes, 22 seconds",
  "memory":"56.4 MB (%11.5) of 490.7 MB",
  "cloud":{
    "ZooKeeper":"localhost:9983",
    "liveNodes":"2",
    "collections":"1"}}


Solr process 12995 running on port 7574
INFO  - 2019-07-18 16:46:35.848; org.apache.solr.util.configuration.SSLCredentialProviderFactory; Processing SSL Credential Provider chain: env;sysprop
{
  "solr_home":"/home/russell/liferay-bundles/liferay-portal-7.2.10-ga1/solr-7.5.0/example/cloud/node2/solr",
  "version":"7.5.0 b5bf70b7e32d7ddd9742cc821d471c5fabd4e3df - jimczi - 2018-09-18 13:07:55",
  "startTime":"2019-07-18T20:44:16.847Z",
  "uptime":"0 days, 0 hours, 2 minutes, 19 seconds",
  "memory":"108.2 MB (%22.1) of 490.7 MB",
  "cloud":{
    "ZooKeeper":"localhost:9983",
    "liveNodes":"2",
    "collections":"1"}}
```

To stop Solr while running in SolrCloud mode, use the *stop* command, like this:

```bash
./bin/solr stop -all
```

## Configure the Solr Adapter for SolrCloud

There's only one thing left to do: specify the client type as *CLOUD* in Liferay's Solr connector.

1. From System Settings or your OSGi configuration file, set the *Client Type* to *CLOUD*.

   ```properties
   clientType="CLOUD"
   ```

1. Start Liferay if it's not running already.

    <!-->
![From the Solr 7 System Settings entry, set the Client Type to Cloud.](../../../images/solr-client-type.png)
    </!-->

Now you can configure Liferay for Solr and Solr for Liferay. Remember that Elasticsearch is the default search engine, so if you're not constrained to use Solr or already a Solr expert, consider Elasticsearch for your search engine requirements. If you do use Solr, tell all your colleagues that your Liferay installation's search capability is Solr powered (pun intended).
