# Installing Elasticsearch

Here are the instructions for installing, configuring, and starting Elasticsearch on-premises.

## Environment Setup

Elasticsearch requires a higher _mmap count_ (for mapping the directory holding its indexes into memory) than the default for most operating systems. On Linux and as the root user, run

```bash
sysctl -w vm.max_map_count=262144
```

## Install Elasticsearch

Here's how to install Elasticsearch manually using an archive.

1. Download an Elasticsearch archive from [Elastic's website](https://www.elastic.co).

    ```important::
       Download the latest Elasticsearch archive compatible with you Liferay version.
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

> **Related Elasticsearch Documentation:** 
> - [Important Elasticsearch configuration](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/important-settings.html)
> - [Security settings in Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/security-settings.html)
> - [Bootstrap Checks, Development vs. production mode](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/bootstrap-checks.html)

**Important:** Each Elasticsearch node's `elasticsearch.yml` file must use unique values for the following properties.

* `node.name`
* `http.port`
* `network.host`
* `transport.port`

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

If you're running Liferay 7.2, note the `publish_address` address in the `TransportService` status message; you'll need to configure the Liferay servers to connect to Elasticsearch at this address. 

Elasticsearch is ready for [connections from Liferay](./connecting-to-elasticsearch.md).

If you're running in production, [secure communication between Liferay and Elasticsearch](./securing-elasticsearch.md).

## Additional Topics

* [Liferay Enterprise Search](../../liferay_enterprise_search.rst)
* [Search Pages](../../search-pages-and-widgets/working-with-search-pages/search-pages.md)
* [Administering and Tuning Search](../../search_administration_and_tuning.rst)
* [Search Configuration Reference Guide](../../search-configuration-reference.md)
