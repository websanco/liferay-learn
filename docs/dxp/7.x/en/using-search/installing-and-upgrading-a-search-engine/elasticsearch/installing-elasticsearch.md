# Installing Elasticsearch

Here's how to install, configure, and start Elasticsearch on-premises.

## Environment Setup

### Adding Hosts in a Production-Like Local Environment

You can skip this if you'll set up a testing environment using localhost or Docker containers. For a production-like setup on your local machine, add the hosts for Liferay and the Elasticsearch cluster. Add this information to your operating system's `path/to/etc/hosts` file:

```properties
<your IP> es-node1
<your IP> es-node2
<your IP> es-node3
<your IP> dxp.liferay.com
```

Use the real IP address of your system, not the loopback address `127.0.0.1`.

### Docker Containers: Add Hosts

> Skip this step if your system is not using Docker containers.

The Liferay container(s) must recognize the Elasticsearch IP(s) to establish a connection. Add `/etc/hosts/` entries on the Liferay nodes that map the Elasticsearch container name to the Elasticsearch server host IP address. This can be established during the `docker run` phase by passing an `--add-host elasticsearch:[IP address]` argument for each Elasticsearch node.

To obtain the IP addresses of all running containers, run

```bash
docker network inspect bridge
```

The example IP value presented here is `172.17.0.2`:

```bash
docker run -it --name dxp-1 --add-host elasticsearch7:172.17.0.2 ...
```

### Adjusting mmap in Poduction Mode

Elasticsearch requires a higher _mmap count_ (for mapping the directory holding its indexes into memory) than the default for most operating systems. On Linux and as the root user, run

```bash
sysctl -w vm.max_map_count=262144
```

## Install Elasticsearch

1. Download an Elasticsearch archive from [Elastic's website](https://www.elastic.co).

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

### Example: Single-Node Elasticsearch Cluster

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

### Example: Multi-Node Elasticsearch Cluster

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

### Enforce Bootstrap Checks 

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
