# Elasticsearch Connector Configuration Reference

> The configuration information here applies to the latest available (bundled or through Marketplace) version of the Elasticsearch 6 and Elasticsearch 7 connectors for Liferay Portal 7.3 & 7.4 CE and for Liferay DXP 7.2 & 7.3. Appropriate information about the exact GA/Service Pack/Fix Pack and Marketplace versions are provided where needed.

## Overview

The connection to Elasticsearch is primarily defined in the _Elasticsearch 6/7_ configuration entry in System Settings (or via [corresponding configuration file](#elasticsearch-connection-configuration-entries)). Liferay 7.3 introduced the possibility to define multiple connections to Elasticsearch, through the [factory configuration](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-factory-configuration.md) _Elasticsearch Connections_ configuration. Both configuration entries are configurable through [System Settings](../../../system-administration/configuring-liferay/system-settings.md) or an [OSGi configuration file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) (recommended: use config files). 

## System Settings Entries & Config Files

| Connecting Servers | <div style="width:380px">System Settings Entry/Configuration File</div> |
| --------------- | -------------------------------------- | 
| Liferay 7.2.x<br />Elasticsearch 6.x  | Elasticsearch 6<br />`com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config` |
| Liferay 7.2.x<br />Elasticsearch 7.x  | Elasticsearch 7<br />`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config` |
| Liferay 7.3.x<br />Elasticsearch 7.x  | Elasticsearch 7<br />`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config`<br /><br />Elasticsearch Connections (factory)<br />`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-[key].config` |

In Liferay 7.3 and beyond, there's an additional connection configuration entry, Elasticsearch Connections. You can use this to define any connection to Elasticsearch, but if you are only configuring one connection you can still use the main Elasticsearch 7 configuration entry. If using multiple connections in 7.3+, define connections with files named accordingly:

```
com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-[connectionId].config
```

If configuring security on Elasticsearch 6, a separate Liferay configuration (as well as a LES subscription) is required. See [Securing Elasticsearch](securing-elasticsearch.md) for more information.

Deploy configuration files to `[Liferay_Home]/osgi/configs` and a listener auto-detects the configurations and writes them to the database.

## System Settings & Config File Properties

| System Settings UI Field Name | <div style="width:280px">Configuration File Usage, Default Value & Description | Available in |
| ----------------------------- | ------------------------------------------------------------------------------ | ------------ | 
| | <a href="#general-connection-settings" id="general-connection-settings">GENERAL CONNECTION SETTINGS</a> |
| Track Total Hits | `trackTotalHits=B"true"`<details><summary>Description</summary>If enabled, hits are accurately counted when there are more than 10,000 results for a search. Leaving this enabled may have an impact on performance when there is a large number of hits for a search.</details> | Liferay 7.2+<br />(Connector to Elasticsearch 7) |
  | Production Mode Enabled | `productionModeEnabled=B"false"`<details><summary>Description</summary>Enable production mode. In Liferay 7.3, <code>productionModeEnabled</code> replaces the deprecated setting <code>operationMode</code>. If this is checked, production mode is enabled and the Operation Mode configuration is ignored. Enabling production mode requires connecting to a remote standalone Elasticsearch cluster. If left disabled, the Operation Mode configuration is used.</details> | Liferay 7.3+ |
| Index Name Prefix | `indexNamePrefix="liferay-"` | Liferay 7.2+ |
| 7.3.x&rarr;Number of Company and System Index Replicas<br />7.2.x&rarr;Index Number of Replicas | `indexNumberOfReplicas="0-all"`<details><summary>Description</summary>Set the number of replicas for each Liferay company and system index. If unset, no replicas are used. Changing this value requires a full re-index. The default value is defined in a file called "index-settings-defaults.json" shipped with the connector.</details> | Liferay 7.2+ |
| 7.3.x&rarr;Number of Company and System Index Shards<br />7.2.x&rarr;Index Number of Shards | `indexNumberOfShards="1"`<details><summary>Description</summary>Set the number of shards to use when a Liferay company and system index is created. If unset, a single shard will be used. Changing this value requires a full re-index. The default value is defined in a file called "index-settings-defaults.json" shipped with the connector.</details> | Liferay 7.2+ |
| Log Exceptions Only | `logExceptionsOnly=B"true"` | Liferay 7.2+ |
| Retry On Conflict | `retryOnConflict="5"` | Liferay 7.2- |
| | <a href="#security-settings" id="security-settings">SECURITY SETTINGS</a> |
| Authentication Enabled | `authenticationEnabled=B"false"` | Liferay 7.3+ |
| Username | `username="elastic"` | Liferay 7.3+ |
| Password | `password=""` | Liferay 7.3+ |
| Http SSL Enabled | `httpSSLEnabled="false"` | Liferay 7.3+ |
| Truststore Type | `truststoreType="pkcs12"` | Liferay 7.3+ |
| Truststore Path | `truststorePath="/path/to/localhost.p12"` | Liferay 7.3+ |
| Truststore Password | `truststorePassword=""` | Liferay 7.3+ |
| | <a href="#elasticsearch-connections-settings" id="elasticsearch-connections-settings">ELASTICSEARCH CONNECTIONS SETTINGS</a> |
| Active | `active=B"false"` | Liferay 7.3+ |
| Connection ID | `connectionId=""` | Liferay 7.3+ |
| | <a href="#rest-client-settings" id="rest-client-settings">REST CLIENT SETTINGS</a> |
| Network Host Addresses | `networkHostAddresses="[http://localhost:9200]"` | Liferay 7.3+ |
| REST Client Logger Level | `RESTClientLoggerLevel="ERROR"` | Liferay 7.3+ |
| | <a href="#transport-client-settings" id="transport-client-settings">TRANSPORT CLIENT SETTINGS</a> |
| Cluster Name | `clusterName="LiferayElasticsearchCluster"` | Liferay 7.2-<br />On 7.3+, applies to development mode |
| Transport Addresses | `transportAddresses=["localhost:9300"]` | Liferay 7.2- |
| Client Transport Sniff | `clientTransportSniff=B"true"` | Liferay 7.2- |
| Client Transport Ignore Cluster Name | `clientTransportIgnoreClusterName=B"false"` | Liferay 7.2- |
| Client Transport Ping Timeout | `clientTransportPingTimeout=""` | Liferay 7.2- |
| Client Transport Nodes Sampler Interval | `clientTransportNodesSamplerInterval=""` | Liferay 7.2- |
| | <a href="#other-settings" id="other-settings">OTHER SETTINGS</a> |
| Remote Cluster Connection ID | `remoteClusterConnectionId=` | Liferay 7.3+ when using [LES Cross-Cluster Replication](../../liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.md) |
| | <a href="#development-mode-settings" id="development-mode-settings">DEVELOPMENT MODE SETTINGS</a> | 
| Bootstrap Mlock All | `bootstrapMlockAll="false"` | Liferay 7.2+ |
| Http CORS Allow Origin | `httpCORSAllowOrigin="/https?:\\/\\/localhost(:[0-9]+)?/"` | Liferay 7.2+ |
| Http CORS Configurations | `httpCORSConfigurations=` | Liferay 7.2+ |
| Http CORS Enabled | `httpCORSEnabled=B"true"` | Liferay 7.2+ |
| Network Host | `networkHost=""` | Liferay 7.2+  |
| Network Bind Host | `networkBindHost=""` | Liferay 7.2+ |
| Network Publish Host | `networkPublishHost=""` | Liferay 7.2+ |
| Node Name | `nodeName=` | Liferay 7.3+ |
| Sidecar Debug | `sidecarDebug=B"false"` | Liferay 7.3+ |
| Sidecar Debug Settings | `sidecarDebugSettings="-agentlib:jdwp=transport=dt_socket,address=8001,server=y,suspend=y,quiet=y"` | Liferay 7.3+ |
| Sidecar Heartbeat Interval | `sidecarHeartbeatInterval="10000"` | Liferay 7.3+ |
| Sidecar Home | `sidecarHome="elasticsearch7"` | Liferay 7.3+ |
| Sidecar HTTP Port | `sidecarHttpPort="9200"` | Liferay 7.3+ |
| Sidecar JVM Options | `sidecarJVMOptions="-Xms1g\|-Xmx1g\|-XX:+AlwaysPreTouch"` | Liferay 7.3+ |
| Sidecar Shutdown Timeout | `sidecarShutdownTimeout="10000"` | Liferay 7.3+ |
| Transport Tcp Port | `transportTcpPort=""` | Liferay 7.2+ |
| Zen Discovery Unicast Hosts Port | `discoveryZenPingUnicastHostsPort="9300-9400"` | Liferay 7.2+ |
| | <a href="#advanced-settings" id="advanced-settings">ADVANCED SETTINGS</a> |
| Additional Configurations | `additionalConfigurations=""` | Liferay 7.2+ |
| Additional Index Configurations | `additionalIndexConfigurations=""` | Liferay 7.2+ |
| Additional Type Mappings | `additionalTypeMappings=""` | Liferay 7.2+ |
| Override Type Mappings | `overrideTypeMappings=""` | Liferay 7.2+ |
| Proxy Host | `proxyHost=""` | Liferay DXP 7.3 FP1+/SP1+ and Liferay Portal CE GA7+ |
| Proxy Port | `proxyPort="0"` | Liferay DXP 7.3 FP1+/SP1+ and Liferay Portal CE GA7+ |
| Proxy Username | `proxyUserName=""` | Liferay DXP 7.3 FP1+/SP1+ and Liferay Portal CE GA7+ |
| Proxy Password | `proxyPassword=""` | Liferay DXP 7.3 FP1+/SP1+ and Liferay Portal CE GA7+ |
| | <a href="#deprecated-settings" id="deprecated-settings">DEPRECATED</a> |
| Operation Mode | `operationMode="EMBEDDED"` | Deprecated as of Liferay 7.3, replaced with _Production Mode Enabled_  |
| Embedded HTTP Port | `embeddedHttpPort="9201"` | Deprecated as of Liferay 7.3.x |
| Http Enabled | `httpEnabled=B"true"` | Deprecated as of Liferay 7.1.x<br />Deprecated Elasticsearch 6.3.x |

## Property Descriptions

The configuration settings are categorized by where they appear: those unique to the Elasticsearch 7 entry, those unique to the Elasticsearch connections entry, and those common to both. The Elasticsearch 7 and Elasticsearch Connections entries are found under System Settings &rarr; Search.

### Properties Unique to the Elasticsearch 7 Configuration

Many of the connection settings are only found in the Elasticsearch 7 configuration entry. They can be categorized according to 

- production mode settings for defining the connection mode and declaring which connection to use (if you have multiple connections defined in an Elasticsearch Connections entry)
- development mode settings to configure the embedded or sidecar Elasticsearch (e.g., naming the server node)
- Settings that are not specific to the connection, but that configure Liferay's search infrastructure or behavior (e.g., index name prefixes)

#### Defining the Remote Connection

**Operation Mode, `operationMode="EMBEDDED"`** \
There are two operation modes you can choose from: EMBEDDED or REMOTE. Set to REMOTE to connect to a remote standalone Elasticsearch cluster. Set to EMBEDDED to start Liferay with an internal Elasticsearch instance. EMBEDDED operation mode is unsupported for production environments and can be considered a "development mode" feature.

**Remote Cluster Connection ID, `remoteClusterConnectionId`** \
Choose the connection ID for a connection to the remote Elasticsearch cluster. The available connections are defined in the Elasticsearch Connections System Settings entry. If this value is not set then the connection configurations in the Elasticsearch 7 entry are used for the remote cluster connection.

#### Configuring Liferay's Search Infrastructure

**Index Name Prefix, `indexNamePrefix="liferay-"`** \
Set a String value to use as the prefix for the search index name. The default value should not be changed under normal conditions. If you change it, you must also perform a *re-index all* operation for the portal and then manually delete the old index using the Elasticsearch administration console.

**Additional Index Configurations, `additionalIndexConfigurations=`** \
Set the String values for custom settings for the Liferay index, in JSON or YML format (refer to the Elasticsearch Create Index API for more information).  See: Adding Settings to the Liferay Elasticsearch Connector

**Additional Type Mappings, `additionalTypeMappings=`** \
Set the String values for custom mappings for the `LiferayDocumentType`, in JSON format (refer to the Elasticsearch Put Mapping API for more information) See: Adding Settings to the Liferay Elasticsearch Connector

**Override Type Mappings, `overrideTypeMappings=`** \
Settings here override Liferay's default type mappings. This is an advanced feature that should be used only if strictly necessary. If you set this value, the default mappings used to define the Liferay Document Type in Liferay source code (for example, `liferay-type-mappings.json`) are ignored entirely, so include the whole mappings definition in this property, not just the segment you're modifying.

[7.2-] **Transport Addresses, `transportAddresses="localhost:9300"`** \
Set the String values for the addresses of the remote Elasticsearch nodes to connect to. This value is required when Operation Mode is set to remote (see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) for more information). Specify as many or few nodes as you see fit.

[7.2-] **Client Transport Sniff, `clientTransportSniff="true"`** \
Set this boolean to true to enable cluster sniffing and dynamically discover available data nodes in the cluster (see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) for more information).

[7.2-] **Client Transport Ignore Cluster Name, `clientTransportIgnoreClusterName="false"`** \
Set this boolean to true to ignore cluster name validation of connected nodes (see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) for more information).

[7.2-] **Client Transport Ping Timeout, `clientTransportPingTimeout=`** \
Set the time (in seconds) the client node waits for a ping response from a node. If unset, the default Elasticsearch `client.transport.ping_timeout` is used.

[7.2-] **Client Transport Nodes Sampler Interval, `clientTransportNodesSamplerInterval=`** \
Set this String value to instruct the client node on how often to sample / ping the nodes listed and connected (see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html) for more information).

**Log Exceptions Only, `logExceptionsOnly="true"`** \
A boolean setting that, when set to true, only logs exceptions from Elasticsearch, and does not re-throw them.

#### Configuring the Development Mode Server

**Cluster Name, `clusterName="LiferayElasticsearchCluster"`** \
The cluster name is only needed for the Transport Client in Liferay 7.2. Set a String value to declare the cluster to integrate with. In Liferay 7.3+, where the connection is managed through the REST client, this property is only used for naming the embedded cluster when in development mode.

**Node Name, `nodeName=`** \
Name the embedded Elasticsearch server's node. A remote Elasticsearch server's node name is configured in its `elasticsearch.yml`.

**Additional Configurations, `additionalConfigurations=`** \
Set the String values for custom settings for embedded Elasticsearch, in YML format. See: Adding Settings to the Liferay Elasticsearch Connector

**Bootstrap Mlock All, `bootstrapMlockAll="false"`**
A boolean setting that, when set to `true`, tries to lock the process address space into RAM, preventing any Elasticsearch memory from being swapped out (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-configuration-memory.html#bootstrap-memory_lock)) for more information)

**Http CORS Allow Origin, `httpCORSAllowOrigin="/https?:\\/\\/localhost(:[0-9]+)?/"** \
Set the String origins to allow when HTTP CORS is enabled (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-http.html#_settings) for more information).

**Http CORS Configurations, `httpCORSConfigurations=** \
Set the String values for custom settings for HTTP CORS, in YML format (`elasticsearch.yml`) (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-http.html#_settings) for more information).

**Http CORS Enabled, `httpCORSEnabled="true"** \
Set this boolean to false to disable cross-origin resource sharing. When set to `false`, a browser on another origin cannot make requests to Elasticsearch. Web front end tools like Elasticsearch Head may be unable to connect (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-http.html#_settings) for more information).

**Network Host, `networkHost=""** \
Set this String value to instruct the node to bind to this hostname or IP address and publish (advertise) this host to other nodes in the cluster. This is a shortcut which sets the bind host and the publish host at the same time (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-network.html#common-network-settings) for more information).

**Network Bind Host, `networkBindHost=""** \
Set the String value of the network interface(s) a node should bind to in order to listen for incoming requests (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-network.html#advanced-network-settings) for more information).

**Network Publish Host, `networkPublishHost=""** \
Set the String value of a single interface that the node advertises to other nodes in the cluster, so that those nodes can connect to it (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-network.html#advanced-network-settings) for more information).

**Sidecar Debug, `sidecarDebug="false"** \
Set this to true to enable debug mode for the sidecar process.

**Sidecar Debug Settings, `sidecarDebugSettings="-agentlib:jdwp=transport=dt_socket,address=8001,server=y,suspend=y,quiet=y"** \
Set the JVM options used to debug the sidecar process.

**Sidecar Heartbeat Interval, `sidecarHeartbeatInterval="10000"** \
Set the heartbeat interval in milliseconds used to detect the health of the sidecar process.

**Sidecar Home, `sidecarHome="elasticsearch7"** \
Set the path of the sidecar base folder used to start the sidecar process.

**Sidecar HTTP Port, `sidecarHttpPort="9200"** \
This configuration only applies to Liferay 7.3 with the sidecar Elasticsearch. Set the HTTP port range of the sidecar Elasticsearch node. Set to AUTO to automatically find a port in the 9201-9300 range. If unset, the value of Embedded HTTP port (`9201` by default) is used.

**Sidecar JVM Options, `sidecarJVMOptions="-Xms1g|-Xmx1g|-XX:+AlwaysPreTouch"** \
Set the JVM options used by the sidecar process.

**Sidecar Shutdown Timeout, `sidecarShutdownTimeout="10000"** \
Set the time in milliseconds to wait before the sidecar process is forcibly shut down.

**Transport Tcp Port, `transportTcpPort=""** \
Set the String value for the port to bind for communication between nodes.  Accepts a single value or a range (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-transport.html#_tcp_transport) for more information).

**Zen Discovery Unicast Hosts Port, `discoveryZenPingUnicastHostsPort="9300-9400"** \
Set a String value for the range of ports to use when building the value for `discovery.zen.ping.unicast.hosts`. Multiple Elasticsearch nodes on a range of ports can act as gossip routers at the same computer (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-discovery-hosts-providers.html) for more information).

### Properties Unique to the Elasticsearch Connections Configuration

**Active, `active="false"`** \
Activate or deactivate the connection as needed. Do not deactivate a connection if it's selected in the Elasticsearch 7 configuration's Remote Cluster Connection ID setting.

**Connection ID, `connectionId=`** \
Set a unique ID for the connection. If active, this connection becomes available to select in the Elasticsearch 7 configuration's Remote Cluster Connection ID property.

### Properties Shared Between the Elasticsearch 7 and Elasticsearch Connections Configurations

Configuration that would be unique depending on the defined connection are available from both the Elasticsearch 7 and the Elasticsearch Connections configurations.

**Network Host Addresses, `networkHostAddresses="http://localhost:9200"** \
Set the remote HTTP hosts to connect to. This is required in Liferay 7.3 as it configures the REST client connection.

**Authentication Enabled, `authenticationEnabled="false"** \
Enable or disable authentication to Elasticsearch with a user name and password.

**Username, `username="elastic"** \
Set the user name for authenticating to Elasticsearch if Authentication Enabled is checked.

**Password, `password=** \
Set the password for authenticating to Elasticsearch if Authentication Enabled is checked.

**Http SSL Enabled, `httpSSLEnabled="false"** \
Enable or disable TLS/SSL.

**Truststore Type, `truststoreType="pkcs12"** \
Set the truststore type if HTTP SSL Enabled is checked.

**Truststore Path, `truststorePath="/path/to/localhost.p12"** \
Set the path to the truststore file if HTTP SSL Enabled is checked.

**Truststore Password, `truststorePassword=** \
Set the password to the truststore if HTTP SSL Enabled is checked.

**Proxy Host, `proxyHost=`** \
Set the proxy host for the client connection.

**Proxy Port, `proxyPort="0"`** \
Set the proxy port for the client connection.

**Proxy Username, `proxyUserName=`** \
Set the proxy user name for a proxy connection.

**Proxy Password, `proxyPassword=`** \
Set the password for connecting to the proxy.

## Related Topics

- [What's New in Search for 7.3?](../../getting-started/whats-new-in-search-for-73.md)
- [Securing Elasticsearch](securing-elasticsearch.md)
- [Connecting to Elasticsearch](connecting-to-elasticsearch.md)
- [Liferay DXP Elasticsearch Connectors: Technical Data Sheet (KB Reference)](https://help.liferay.com/hc/en-us/articles/360046478452)
- [Understanding Liferay DXP's compatibility with Elasticsearch (KB Reference)](https://help.liferay.com/hc/en-us/articles/360051492032)
