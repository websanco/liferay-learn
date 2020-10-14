# Connecting to Elasticsearch

After [setting up Elasticsearch](./getting-started-with-elasticsearch.md#installing-elasticsearch), you must connect Liferay to it using the Liferay Connector to Elasticsearch. Connection steps depend on the [connector](#available-liferay-connector-applications) you're configuring:

* [REST-based Elasticsearch Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html) is bundled with Liferay 7.3 and available on [Liferay Marketplace](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md).
* Connector client for Liferay 7.2 is backed by the [Java Transport Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html). It is available on [Liferay Marketplace](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md).

Notable installation and configuration procedure differences are presented here. 

```important::
   Stop each Liferay server node before configuring the connection.
```

If you're on Liferay 7.2, continue with the next section. If you're on Liferay 7.3, skip to [Configuring the Connector](#configuring-the-connector).

## Liferay 7.2: Installing Elasticsearch 7 Connector

### Stop the Elasticsearch 6 Connector

On Liferay 7.2, the bundled connector application and APIs are for Elasticsearch 6. These must be disabled before installing the Elasticsearch 7 connector.

1. Create a file called

    ```
    com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config
    ```

1. Add this content to the file:

    ```properties
    blacklistBundleSymbolicNames=[ \
    	"com.liferay.portal.search.elasticsearch6.api", \
    	"com.liferay.portal.search.elasticsearch6.impl", \
    	"com.liferay.portal.search.elasticsearch6.spi", \
    	"com.liferay.portal.search.elasticsearch6.xpack.security.impl", \
    	"Liferay Connector to X-Pack Security [Elastic Stack 6.x] - Impl", \
    	"Liferay Enterprise Search Security - Impl.lpkg" \
    ]
    ```

1. Place the file in your `[Liferay Home]/osgi/configs` folder.

When you start the Liferay server (not yet), Liferay reads this file and blocks the declared bundles from starting.

```tip::
   **Docker:** ``Liferay Home`` and other important folders of a Liferay installation are accessed in a Docker container at ``/mnt/liferay`` as described `here <../../../installation-and-upgrades/installing-liferay/using-liferay-dxp-docker-images/dxp-container-lifecycle-and-api.md#api>`_. You can use ``docker cp /path/to/local/file [container_name]:/mnt/liferay/files/osgi/configs`` to place configuration files into the container. Later, you can use ``docker cp`` to deploy the Liferay Connector to Elasticsearch 7 LPKG file.
```

### Install the Elasticsearch 7 Connector

1. Download the Liferay Connector to Elasticsearch 7.

   Make sure the connector corresponds to your Elasticsearch version. Note, the client libraries in the connector can be for an older version of Elasticsearch (e.g., 7.3) even though the connector application supports a newer version (e.g., 7.9.x). Liferay tests the connector with every minor Elasticsearch version and creates new update connector versions when needed. As always, consult the [compatibility matrix](https://help.liferay.com/hc/sections/360002103292-Compatibility-Matrix) for connector compatibility.

   * CE: [Liferay CE Connector to Elasticsearch](https://web.liferay.com/en/marketplace/-/mp/application/170642090)
   * DXP: [Liferay Connector to Elasticsearch](https://web.liferay.com/en/marketplace/-/mp/application/170390307)

1. [Install the LPKG](../../../system-administration/installing-and-managing-apps/installing-apps/installing-apps.md) by placing it in the folder

   ```bash
   [Liferay Home]/deploy
   ```

   For Docker:

   ```bash
   docker cp ~/path/to/Liferay\ Connector\ to\ Elasticsearch.lpkg [container]:/mnt/liferay/deploy
   ```

   When you start the Liferay server (not yet), Liferay deploys the LPKG.

You're ready to configure the connector.

## Configuring the Connector

The Elasticsearch 7 connector is configured via a configuration file named

```bash
com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
```

After specifying the configuration in the file, you can deploy it by placing it into your `[Liferay Home]/osgi/configs/` folder.

For Docker:

```bash
docker cp ~/path/to/com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config [container]:/mnt/liferay/files
```

Alternatively, you can configure the connector in the user interface: in the Applications Menu (![Applications Menu](../../../images/icon-applications-menu.png)), go to Control Panel &rarr; System Settings and open the _Search_ category. The entry is called Elasticsearch 7.

```note::
   Liferay 7.2: The Control Panel is in the Product Menu (![Product Menu](../../../images/icon-product-menu.png)).
```

### Configuring the Connector for 7.3

A simple 7.3 connector configuration enables production mode (`productionModeEnabled="true"`), sets the URL to each Elasticsearch node (`networkHostAddresses=["es-node:9200"]`), and identifies the connection you're configuring (`remoteClusterConnectionId="remote"`).


1. Create the following configuration file in your `[Liferay Home]/osgi/configs` folder:

    ```bash
    com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    ```

1. Specify the configuration properties in the `.config` file. Here's an example that includes [security properties](./securing-elasticsearch.md) too:

    ```properties
    # In CE/DXP7.3, productionModeEnabled replaces operationMode (deprecated):
    productionModeEnabled="true"
    networkHostAddresses=["http://es-node1:9200","http://es-node3:9200","http://es-node3:9200"]
    remoteClusterConnectionId="remote"
    # In CE/DXP 7.3 the HTTP security settings are included in the ElasticsearchConfiguration
    # In CE/DXP 7.2 the Transport security settings go in com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config
    # authentication
    authenticationEnabled="true"
    username="elastic"
    password="liferay"

    # TLS/SSL
    httpSSLEnabled="true"
    truststoreType="pkcs12"
    trustStorePath="/PATH/TO/truststore.p12"
    trustStorePassword="secret"

    # Highly recommended for all non-prodcution usage (e.g., practice, tests, diagnostics):
    #logExceptionsOnly="false"
    ```

```tip::
   The connectors contain many configuration settings. See the `Configuration Reference <./../../configuration-reference.md>`_ for their definitions. Most of the configurations correspond to settings available in `Elasticsearch <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index.html>`_.
```

Using a Remote Cluster Connection ID value other than `remote` requires adding a connection via the `ElasticsearchConnectionsConfiguration` entry.
<!-- NOTE: This looks like it changed since the draft was written. Needs investigating -->

To refer to Elasticsearch servers by name, map each  Elasticsearch server name to its IP address in your Liferay server's `/etc/hosts` file.

```tip::
  The network host address format is ``http[s]://[host name]:[port]``. If you're using a Liferay Docker container, you can use ``--add-host [host name]:[IP address]`` options with your ``docker run`` command to map a host name to each Elasticsearch server IP address. The port is defined in the Elasticsearch container's docker run command as the first value of the ``-p 1234:5678`` option (it's ``1234`` in this case). If you're running a local test environment without HTTPS enabled, all the addresses can be ``http://localhost:port``. See `Docker's documentation <https://docs.docker.com/engine/reference/run/#managing-etchosts>`_ for more details.
```

### Configuring the Connector for 7.2

1. Create the following configuration file in your `[Liferay Home]/osgi/configs` folder:

    ```bash
    com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    ```

1. Specify the configuration properties in the `.config` file. Here's an example that enables remote operation mode, sets the transport address for each Elasticsearch node, and identifies the connection you're configuring:

    ```properties
    # com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    operationMode="REMOTE"
    transportAddresses="ip.of.elasticsearch.node:9300"
    # Highly recommended for all non-prodcution usage (e.g., practice, tests, diagnostics):
    #logExceptionsOnly="false"
    ```

1. Place the `.config` file in your `[Liferay Home]/osgi/configs` folder.

### Start Liferay and Re-Index

If Elasticsearch is [installed and running](./installing-elasticsearch.md), start Liferay. In the Control Panel, navigate to Configuration &rarr; Search and verify the Elasticsearch connection is active.

![An active connection is displayed in the Search administrative panel.](./getting-started-with-elasticsearch/images/01.png)

Re-index your search indexes and spell check indexes. Invoke both of these actions in the Index Actions tab of Control Panel &rarr; Configuration &rarr; Search.

On Liferay 7.3, Re-index the [Workflow Metrics](../../../process-automation/workflow/user-guide/using-workflow-metrics.md) indexes from the Workflow Metrics Settings window: 

1. From the Applications Menu (![Applications Menu](../../../images/icon-applications-menu.png)) navigate to Applications &rarr; Workflow Metrics. 

1. Open the _Settings_ window from the App Options menu (![App Options](../../../images/icon-app-options.png)).

1. Click _Reindex All_.

Now Liferay is indexing content into your remote Elasticsearch 7 installation.

## Available Liferay Connector Applications

The bundled connector to Elasticsearch is not always the best choice for your installation. It's important to understand the differences between the connectors you can use to communicate with Elasticsearch:

| Liferay CE/DXP Version | Name | Availability | Communication Protocol | Supports Secure Connection | Compatible Elasticsearch Version | Recommended Elasticsearch Version | Operation Modes |
| ---------------------- | ---- | ------------ | ---------------------- | -------------------------- | ---------------------- | -------------------------- |--------------- |
| CE 7.3 GA4+ <br /><br /> DXP 7.3 GA1+ | Liferay Connector to Elasticsearch 7 | Bundled | [HTTP](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-overview.html) | &#10004; | 7.9+ | 7.9+| Sidecar <br /> Remote |
| 7.2, all patch levels | Liferay Connector to Elasticsearch 6 | Bundled | [Transport](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) | &#10004;\* (requires [LES](https://www.liferay.com/products/dxp/enterprise-search)) | 6.1.x-6.8.x | 6.8.x | Embedded <br /> Remote |
| DXP 7.2 SP3/FP8+ | Liferay Connector to Elasticsearch 7 (3.1.0+) | [Marketplace](https://web.liferay.com/marketplace/-/mp/application/170390307) | [Transport](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) | &#10004; | 7.3.x -7.9.x | 7.9.x | Embedded <br /> Remote |
| CE 7.2 GA2+ | Liferay CE Connector to Elasticsearch 7 (3.0.0) | [Marketplace](https://web.liferay.com/marketplace/-/mp/application/170642090) | [Transport](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) | &#10004; | 7.3.x -7.6.x | 7.6.x| Embedded <br /> Remote |

\* Through the [Liferay Connector to X-Pack Security [Elastic Stack 6.x]](https://web.liferay.com/marketplace/-/mp/application/106163963).

## What's Next 

Now that Liferay is connected to Elasticsearch, you can start using Elasticsearch. For production, you should secure communication between your Liferay and Elasticsearch servers. See [Securing Elasticsearch](./securing-elasticsearch.md) for more information.

## Related Topics

[Liferay Enterprise Search](../../liferay_enterprise_search.rst) \
[Search Pages](../../search-pages-and-widgets/working-with-search-pages/search-pages.md) \
[Administering and Tuning Search](../../search_administration_and_tuning.rst) \
Search Configuration Reference Guide (Coming soon)
