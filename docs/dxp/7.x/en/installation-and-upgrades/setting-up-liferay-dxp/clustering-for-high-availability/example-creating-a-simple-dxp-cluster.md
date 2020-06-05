# Example: Creating a Simple DXP Cluster

A fast, easy way to learn DXP clustering is to set up a two node DXP cluster development environment on one machine using [Docker containers](https://docs.docker.com/get-started/overview/). Here you'll create two DXP server nodes and create supporting servers that provide a database, search engine, and file store.

Here are the servers you'll create:

| Server Type | Implementation | Server Container |
| :---------- | :------- | :---------- |
| Database | MariaDB  | `some-mariadb` |
| File Store | DBStore | `some-mariadb` |
| Search Engine | Elasticsearch | `elasticsearch` |
| DXP Server | Tomcat | `dxp-1` |
| DXP Server | Tomcat | `dxp-2` |

```important::
   This example is intended for learning purposes only. Although it can serve as a starting point for developing a clustered environment, it does not demonstrate a production environment. Please read all of the `Clustering for High Availability <./clustering-for-high-availability.md>`_ articles to develop an environment for production.
```

<!--
![DXP cluster environment.](./example-creating-a-simple-dxp-cluster/images/01.png)
Should we remove this diagram since it includes a load balancer? -->

Here are the main steps:

1. [Start a Database Server](#prepare-a-database-server)
1. [Start a File Store Server](#prepare-a-file-store-server)
1. [Start a Search Engine](#prepare-a-search-engine)
1. [Configure the Search Engine for Each Node](#configure-the-search-engine-for-each-node)
1. [Start the DXP Cluster](#start-the-dxp-cluster)
1. [Test the DXP Cluster](#test-the-dxp-cluster)

## Start a Database Server

A DXP cluster requires a data source that's accessible to all of the DXP cluster nodes. The data source can be a JNDI data source, a database server, or a database server cluster. Please see the [compatibility matrix](https://www.liferay.com/compatibility-matrix) for the database servers your DXP version supports. Please see [Database Configuration for Cluster Nodes](./database-configuration-for-cluster-nodes.md) for more information.

1. Start a Maria DB Docker container.

    ```bash
    docker run --name some-mariadb -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mariadb:10.2
    ```

1. In a shell on the container, [create the DXP database](../../reference/database-configurations.md).

    Sign in to the database server.

    ```bash
    docker exec -it some-mariadb bash -c "/usr/bin/mysql -uroot -pmy-secret-pw"
    ```

    Create a database for DXP.

    ```sql
    create database dxp_db character set utf8;
    ```

    End your database session.

    ```bash
    quit
    ```

    End your bash session.

    ```bash
    exit
    ```

Your database server is ready for DXP.

## Start a File Store Server

A DXP cluster requires a File Store that's accessible to all of the DXP cluster nodes. For convenience, this example uses a [DBStore File Store](../../../system-administration/file-storage/other-file-store-types/dbstore.md) configured on the DXP database. The database server already started in this example includes the File Store. Please see [Configuring File Storage](../../../system-administration/file-storage/configuring-file-storage.md) for information on all of the File Store types.

## Start a Search Engine Server

A DXP cluster requires a search engine (running as a separate process) that's accessible to all of the DXP cluster nodes. Please see [Installing a Search Engine](../../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md) for more information.

1. Set a local folder for storing an Elasticsearch server's data volume.  For example,

    ```bash
    mkdir -p elasticsearch/es_data_volume
    ```

1. Start the Elastisearch container.

    ```bash
    docker run -it --name elasticsearch -p 9200:9200 -p 9300:9300 -e cluster.name=LiferayElasticsearchCluster -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -v $(pwd)/elasticsearch/es_data_volume:/usr/share/elasticsearch/data elasticsearch:6.8.7
    ```

    ```note::
       If the container reports ``max virtual memory areas vm.max_map_count [xxxxx] is too low, increase to at least [xxxxxx]``, then set ``vm.max_map_count`` to a sufficient value using a command like this one: ``sudo sysctl -w vm.max_map_count=[xxxxxx]``. Then start the container.
    ```

1. Install the required Elasticsearch plugins.

    ```bash
    docker exec -it elasticsearch bash -c '/usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-icu && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-kuromoji && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-smartcn && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-stempel'
    ```

Your search engine is ready to manage search indexes.

## Configure the Search Engine Server For Each Node

Use [Configuration Files](../../../system-administration/system-settings/using-configuration-files.md) to configure Elasticsearch for both DXP nodes.

1. Create the Configuration Files locations.

    ```bash
    mkdir -p dxp-1/files/osgi/configs dxp-2/files/osgi/configs
    ```

1. Configure Elasticsearch for the `dxp-1` server node.

    ```bash
    cat <<EOT >> dxp-1/files/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config
    operationMode="REMOTE"
    transportAddresses="elasticsearch:9300"
    clusterName="LiferayElasticsearchCluster"
    EOT
    ```

1. Configure Elasticsearch for the `dxp-2` server node.

    ```bash
    cat <<EOT >> dxp-2/files/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config
    operationMode="REMOTE"
    transportAddresses="elasticsearch:9300"
    clusterName="LiferayElasticsearchCluster"
    EOT
    ```

You'll make these configuration files accessible to the cluster nodes via bind mounts when you start the DXP server containers next.

```note::
   The ``docker run --add-host elasticsearch:[ip] ...`` commands that start the DXP servers later add ``/etc/hosts/`` entries that map the name _elasticsearch_ to the Elasticsearch server host IP address.
```

## Start the DXP Cluster

The DXP cluster node containers will have these unique settings:

| Configuration | dxp-1 | dxp-2 |
| :------------ | :---- | :---- |
| AJP port mapping | `8009:8009` | `9009:8009` |
| HTTP port mapping | `8080:8080` | `9080:8080` |
| OSGi container port mapping | ``11311:11311`` | `11312:11311` |
| Bind mount | `${PWD}/dxp-1/files:/mnt/liferay` | `${PWD}/dxp-2/files:/mnt/liferay` |
| Cluster Link control channel logic name | control-channel-logic-name-1 | control-channel-logic-name-2 |
| Cluster Link transport channel logic name | transport-channel-logic-name-1 | transport-channel-logic-name-2 |

Start the DXP containers.

1. Start `dxp-1`.

    Command expanded for readability:

    ```bash
    docker run -it \
      --add-host elasticsearch:172.17.0.2 \
      --add-host some-mariadb:172.17.0.3 \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME=  \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=org.mariadb.jdbc.Driver \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL=jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=root \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=my-secret-pw \
      -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_ENABLED=true \
      -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_CONTROL=control-channel-logic-name-1 \
      -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_TRANSPORT_PERIOD_NUMBER0=transport-channel-logic-name-1 \
      -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_AUTODETECT_PERIOD_ADDRESS=some-mariadb:3306 \
      -e LIFERAY_WEB_PERIOD_SERVER_PERIOD_DISPLAY_PERIOD_NODE=true \
      -e LIFERAY_DL_PERIOD_STORE_PERIOD_IMPL=com.liferay.portal.store.db.DBStore \
      --name dxp-1 \
      -p 11311:11311 \
      -p 8009:8009 \
      -p 8080:8080 \
      -v ${PWD}/dxp-1:/mnt/liferay \
      liferay/portal:7.3.2-ga3
    ```

    Command condensed to one line:

    ```bash
    docker run -it --name dxp-1  --add-host elasticsearch:172.17.0.2 --add-host some-mariadb:172.17.0.3 -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME=  -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=org.mariadb.jdbc.Driver -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL=jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=root -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=my-secret-pw -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_ENABLED=true -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_CONTROL=control-channel-logic-name-1 -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_TRANSPORT_PERIOD_NUMBER0=transport-channel-logic-name-1 -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_AUTODETECT_PERIOD_ADDRESS=some-mariadb:3306 -e LIFERAY_WEB_PERIOD_SERVER_PERIOD_DISPLAY_PERIOD_NODE=true -e LIFERAY_DL_PERIOD_STORE_PERIOD_IMPL=com.liferay.portal.store.db.DBStore --name dxp-1 -p 11311:11311 -p 8009:8009 -p 8080:8080 -v ${PWD}/dxp-1:/mnt/liferay liferay/portal:7.3.2-ga3
    ```

1. Start `dxp-2`.

    Command expanded for readability:

    ```bash
    docker run -it \
      --add-host elasticsearch:172.17.0.2 \
      --add-host some-mariadb:172.17.0.3 \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME=  \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=org.mariadb.jdbc.Driver \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL=jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=root \
      -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=my-secret-pw \
      -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_ENABLED=true \
      -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_CONTROL=control-channel-logic-name-2 \
      -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_TRANSPORT_PERIOD_NUMBER0=transport-channel-logic-name-2 \
      -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_AUTODETECT_PERIOD_ADDRESS=some-mariadb:3306 \
      -e LIFERAY_WEB_PERIOD_SERVER_PERIOD_DISPLAY_PERIOD_NODE=true \
      -e LIFERAY_DL_PERIOD_STORE_PERIOD_IMPL=com.liferay.portal.store.db.DBStore \
      --name dxp-2 \
      -p 11312:11311 \
      -p 9009:8009 \
      -p 9080:8080 \
      -v ${PWD}/dxp-2:/mnt/liferay \
      liferay/portal:7.3.2-ga3
    ```

    Command condensed to one line:

    ```bash
    docker run -it --add-host elasticsearch:172.17.0.2 --add-host some-mariadb:172.17.0.3 -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME=  -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=org.mariadb.jdbc.Driver -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL=jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=root -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=my-secret-pw -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_ENABLED=true -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_CONTROL=control-channel-logic-name-2 -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_TRANSPORT_PERIOD_NUMBER0=transport-channel-logic-name-2 -e LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_AUTODETECT_PERIOD_ADDRESS=some-mariadb:3306 -e LIFERAY_WEB_PERIOD_SERVER_PERIOD_DISPLAY_PERIOD_NODE=true -e LIFERAY_DL_PERIOD_STORE_PERIOD_IMPL=com.liferay.portal.store.db.DBStore --name dxp-1 -p 11312:11311 -p 9009:8009 -p 9080:8080 -v ${PWD}/dxp-1:/mnt/liferay liferay/portal:7.3.2-ga3
    ```

The `--add-host [domain]:[IP address]` options [add `/etc/hosts` file entries](https://docs.docker.com/engine/reference/run/#managing-etchosts) that map the domain names to the IP addresses. Configurations (such as environment variables, Portal Properties, and `.config` files) can then refer to the servers by domain name or IP address. Note, the [`docker network inspect bridge`](https://docs.docker.com/engine/reference/commandline/network_inspect/) command reports the server IP addresses on the `bridge` network (the default network).

[Appendix A: Environment Settings](#appendix-a-environment-settings) describes the environment variables.

## Test the DXP Cluster

The DXP cluster nodes are available at the following URLs:

* DXP-1: http://localhost:8080
* DXP-2: http://localhost:9080

The figure below shows the cluster node home pages.

![DXP cluster nodes.](./example-creating-a-simple-dxp-cluster/images/02.png)

Each page shows the node's container ID and port (`Node: [id]:[port]`). The `LIFERAY_WEB_PERIOD_SERVER_PERIOD_DISPLAY_PERIOD_NODE=true` environment setting enabled this display feature. You can find a container's using the [`docker container ls`](https://docs.docker.com/engine/reference/commandline/container_ls/) command.

Test data synchronization between the nodes:

1. Add content to one of the cluster nodes.

    For example, add a new Widget Page called _New Stuff_ and add the Language Selector widget to it.

1. Refresh the UI on the other cluster node.

Both nodes show the same new content.

![Content is synchronized between the cluster nodes.](./example-creating-a-simple-dxp-cluster/images/03.png)

Congratulations on creating a working DXP cluster!

## What's Next

Tune your [database](./database-configuration-for-cluster-nodes.md) for your DXP cluster.

## Appendix A: Environment Settings

Here is a summary of the environment settings used:

| Configuration | Description |
| :------------ | :---------- |
| LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME= | Data source JNDI name |
| LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=\\<br>org.mariadb.jdbc.Driver | Database driver class |
| LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL=\\<br>jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false | Data source URL |
| LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=\\<br>root | Database admin user name |
| LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=\\<br>my-secret-pw | Database admin user password |
| LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_ENABLED=\\<br>true | Enables Cluster Link  |
| LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_CONTROL=\\<br>control-channel-logic-name-2 | The cluster node's unique control channel name |
| LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_CHANNEL_PERIOD_LOGIC_PERIOD_NAME_PERIOD_TRANSPORT_PERIOD_NUMBER0=\\<br>transport-channel-logic-name-2 | The cluster node's unique transport channel name |
| LIFERAY_CLUSTER_PERIOD_LINK_PERIOD_AUTODETECT_PERIOD_ADDRESS=\\<br>some-mariadb:3306 | Known address to ping to get cluster node addresses |
| LIFERAY_WEB_PERIOD_SERVER_PERIOD_DISPLAY_PERIOD_NODE=\\<br>true | Displays the server address and web server port |
| LIFERAY_DL_PERIOD_STORE_PERIOD_IMPL=\\<br>com.liferay.portal.store.db.DBStore | File Store (Document Library Store) class |

Please see the [Portal Properties](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html) definitions for more information.

## Additional Information

* [Database Configuration for Cluster Nodes](./database-configuration-for-cluster-nodes.md)
* [Configuring Cluster Link](./configuring-cluster-link.md)