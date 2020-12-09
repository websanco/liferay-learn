# Installing Elasticsearch

Here's how to install, configure, and start Elasticsearch on-premises.

```important::
   The Sidecar Elasticsearch server bundled with Liferay 7.3 uses the Elasticsearch OSS distribution. Do not install the OSS version for production. To run Liferay securely with Elasticsearch, you must install the Basic level of Elasticsearch at a minimum. See `Elastic's subscriptions page <https://www.elastic.co/subscriptions>`__ for more information.
```

```note::
   If you have Elasticsearch indexes used for primary data storage (storing data not backed by a database) you can bring that data into your new Elasticsearch cluster using the `snapshot and restore approach <./upgrading-elasticsearch/backing-up-elasticsearch.md>`__. Liferay's own Search Tuning indexes (for Result Rankings and Synyonyms) are primary storage indexes.
```

## Environment Setup for Production-Like Installation

### Adding Hosts

You can skip this if you'll set up a testing environment using localhost or Docker containers. For a production-like setup on your local machine, add the hosts for Liferay and the Elasticsearch cluster. Add this information to your operating system's `path/to/etc/hosts` file:

```properties
<your IP> es-node1
<your IP> es-node2
<your IP> es-node3
<your IP> dxp.liferay.com
```

Use the real IP address of your system, not the loopback address `127.0.0.1`.

### Adjusting mmap

Elasticsearch requires a higher _mmap count_ (for mapping the directory holding its indexes into memory) than the default for most operating systems. On Linux and as the root user, run

```bash
sysctl -w vm.max_map_count=262144
```

## Install Elasticsearch

1. Download an Elasticsearch archive (not the OSS version) from [Elastic's website](https://www.elastic.co).

    ```important::
       Download the latest Elasticsearch archive `compatible with your Liferay version <./connecting-to-elasticsearch.html#available-liferay-connector-applications.md>`_.
    ```

1. Extract the archive contents to a local folder where you want to run Elasticsearch. This folder is your *Elasticsearch Home*.

1. Install the required Elasticsearch plugins by running these commands in your `[Elasticsearch Home]/bin` folder:

   ```bash
   ./elasticsearch-plugin install analysis-icu
   ```

   ```bash
   ./elasticsearch-plugin install analysis-kuromoji
   ```

   ```bash
   ./elasticsearch-plugin install analysis-smartcn
   ```

   ```bash
   ./elasticsearch-plugin install analysis-stempel
   ```

## Configure Elasticsearch

Each Elasticsearch server is configured by its `[Elasticsearch Home]/config/elasticsearch.yml` file.

Here are example single-node and multi-node Elasticsearch cluster configurations.

### Example: Single-Node Production Elasticsearch Cluster

Here's an `elasticsearch.yml` configuration for a single-node cluster:

```yaml
cluster.name: LiferayElasticsearchCluster

discovery.type: single-node
discovery.seed_hosts:
  - es-node1:9300
http.port: 9200
network.host: es-node1
node.name: es-node1
transport.port: 9300

# Add security settings here
```

This cluster called `LiferayElasticsearchCluster` has one node called `es-node1`.

```tip::
   If you are not configuring hosts for a production mode setup, use ``localhost`` as the host value. Elasticsearch can bind to loopback addresses for HTTP and Transport communication. Along with single node discovery, this means the Elasticsearch server is running in `development mode`.
```

### Example: Multi-Node Production Elasticsearch Cluster

Here is an `elasticsearch.yml` for a node called `es-node3` of a three-node cluster:

```yaml
cluster.name: LiferayElasticsearchCluster

# Example production mode settings - 3-node cluster
cluster.initial_master_nodes:
  - es-node1
  - es-node2
  - es-node3
discovery.seed_hosts:
  - es-node1:9300
  - es-node2:9301
  - es-node3:9302
http.port: 9202
network.host: es-node3
node.name: es-node3
transport.port: 9302

# Add security settings here
```

```tip::
   If you are not configuring hosts for a production mode setup, use ``localhost`` as the host value. Elasticsearch can bind to loopback addresses for HTTP and Transport communication. This is referred to as `development mode`.

   Related Elasticsearch Documentation:

   - `Important Elasticsearch configuration <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/important-settings.html>`_

   - `Security settings in Elasticsearch <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/security-settings.html>`_

   - `Bootstrap Checks, Development vs. production mode <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/bootstrap-checks.html>`_
```

```important::
   Each Elasticsearch node's ``elasticsearch.yml`` file must use unique values for the following properties.

   - ``node.name``

   - ``http.port``

   - ``network.host``

   - ``transport.port``
```

### Enforce Bootstrap Checks for Single Server in Production Mode

Elasticsearch [bootstrap checks](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/bootstrap-checks.html) inspect configurations on startup and logs warnings for missing or suspicious configurations. In production, you should configure bootstrap checks to halt startup on misconfiguration. 

To enforce the bootstrap checks in a single-node cluster, add this property to the end of your node's `[Elasticsearch Home]/config/jvm.options` file:

```properties
-Des.enforce.bootstrap.checks=true
```

## Start Elasticsearch

Start Elasticsearch from the `bin` folder:

```bash
./elasticsearch
```

Elasticsearch starts, and one of its status messages includes a transport address: 

```sh
[2019-04-01T16:55:50,127][INFO ][o.e.t.TransportService   ] [HfkqdKv] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}
```

If you're running Liferay 7.2, note the `publish_address` address in the `TransportService` status message; you must configure the Liferay servers to connect to Elasticsearch at this address. 

Elasticsearch is ready for [connections from Liferay](./connecting-to-elasticsearch.md).

If you're running in production, [secure communication between Liferay and Elasticsearch](./securing-elasticsearch.md).

## Additional Topics

* [Liferay Enterprise Search](../../liferay_enterprise_search.md)
* [Search Pages](../../search-pages-and-widgets/working-with-search-pages/search-pages.md)
* [Administering and Tuning Search](../../search_administration_and_tuning.md)
* [Elasticsearch Connector Settings](./elasticsearch-connector-settings.md)
