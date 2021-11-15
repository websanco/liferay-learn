# CCRの構成：設定リファレンス

CCR構成プロセスを支援するために、ここでは本ガイドの段階的な手順に従った設定例を示します。 これらの構成は可能な限り一般的なものになっていますが、環境に合わせてパスやポートなどを調整する必要があります。 さらに、インストールを完了するには特定の手順を手動で実行する必要があるため、これらの手順は段階的な手順に代わるものではありません。

以下の構成は、インストールでX-Pack Securityを介した暗号化通信（TLS/SSL）とユーザー認証を有効にすることを前提としています。 詳しくは、[Securing Elasticsearch](../../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)を参照してください。

```{tip}
[Configuration values provided by .config files](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)_ are propagated throughout a DXP cluster as soon as the file is deployed to a single node. However, it's a best practice to provide identical configurations for each cluster node. 
```

## リモートDXPクラスターノードの構成

これらの構成ファイルは`[Remote Liferay Home]/osgi/configs`にデプロイされます。

### Elasticsearch の構成

リモートDXPクラスターには、`ElasticsearchConfiguration.config`ファイルが必要です。

ファイル名：`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config`

Liferay DXP 7.3ファイルの内容：

``` properties
productionModeEnabled="true"
remoteClusterConnectionId="remote"
logExceptionsOnly="false"
```

Liferay DXP 7.2ファイルの内容：

``` properties
clusterName="LiferayElasticsearchCluster_LEADER"
operationMode="REMOTE"
transportAddresses=["localhost:9300"]
logExceptionsOnly="false"
```

Liferay DXP 7.2では、`XPackSecurityConfiguration.config`ファイルが別途必要です。

ファイル名：`com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config`

Liferay DXP 7.2ファイルの内容：

``` properties
certificateFormat="PKCS#12"
sslKeystorePath="/PATH/TO/elastic-nodes.p12"
sslKeystorePassword="liferay"
sslTruststorePath="/PATH/TO/elastic-nodes.p12"
sslTruststorePassword="liferay"
requiresAuthentication=B"true"
username="elastic"
password="liferay"
transportSSLVerificationMode="certificate"
transportSSLEnabled=B"true"
```

```{tip}
The X-Pack security configuration file is not required on Liferay DXP 7.3. Security is configured in each connection's `.config` file. The values should be identical on all Liferay nodes.
```

### Elasticsearch接続設定

\[DXP 7.3のみ\]リモート接続ファイル名： `com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-remote.config`

ファイルの内容：

``` properties
active="true"
connectionId="remote"
networkHostAddresses=["https://localhost:9200"]
username="elastic"
password="liferay"
authenticationEnabled=B"true"
httpSSLEnabled=B"true"
truststorePassword="liferay"
truststorePath="/PATH/to/elastic-nodes.p12"
truststoreType="pkcs12"
```

```{tip}
For Liferay DXP 7.2, you used the `ElasticsearchConfiguration.config` and the `XPackSecurityConfiguration.config` files to configure the remote connection.
```

\[DXP 7.3のみ\]読み取り専用接続ファイル名： `com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-ccr.config`

Liferay DXP 7.3のファイルの内容：

``` properties
active="true"
connectionId="ccr"
networkHostAddresses=["https://localhost:9201"]
username="elastic"
password="liferay"
authenticationEnabled=B"true"
httpSSLEnabled=B"true"
truststorePassword="liferay"
truststorePath="/PATH/to/elastic-nodes.p12"
truststoreType="pkcs12"
```

\[DXP 7.2のみ\]読み取り専用接続ファイル名： `com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.ElasticsearchConnectionConfiguration-ccr.config`

Liferay DXP 7.2のファイルの内容：

``` properties
clusterName="LiferayElasticsearchCluster_FOLLOWER"
connectionId="ccr"
username="elastic"
password="liferay"
authenticationEnabled=B"true"
transportSSLEnabled=B"true"
networkHostAddress="https://localhost:9201"
transportAddresses=["localhost:9301"]
sslTruststorePassword="liferay"
sslTruststorePath="/PATH/TO/elastic-nodes.p12"
certificateFormat="pkcs12"
sslKeystorePassword="liferay"
sslKeystorePath="/PATH/TO/elastic-nodes.p12"
```

## ローカルDXPクラスターノードの構成

これらの構成ファイルは`[Local Liferay Home]/osgi/configs`にデプロイされます。

リモートDXPノードに提供したローカルDXPノードに同一のElasticsearch 7構成（接続構成ファイルを含む）を提供します。

### ローカルDXPクラスターノードCCRモジュールの構成

任意のノード（リモートデータセンターにあるノードも含む）のシステム設定UIからLESクラスター横断設定アプリケーションを構成します。 LPKGがデプロイされている場合、設定エントリーは[システム設定]→[検索機能]→[クラスター横断レプリケーション]にあります。

次の値を設定します。

  - *[Read from Local Clusters]* チェックボックスをオンにする
  - *[Local Cluster Configurations]* プロパティに値*localhost:9080,ccr*を設定する

![システム設定からCCRを設定します。](./ccr-basic-use-case-config-reference/images/01.png)

```{important}
Never set the value to the remote data center here (in the example, it would be `localhost:8080,remote`). Setting this would cause follower indexes to be created in the remote cluster, where leader indexes of the same name already reside.
```

## リーダーElasticsearchクラスターノードの構成

場所：`ES_LEADER_HOME/config`

ファイル名：`elasticsearch.yml`

ファイルの内容：

``` yaml
cluster.name: LiferayElasticsearchCluster_LEADER
node.name: es-leader-node-1

http.port: 9200
transport.port: 9300

xpack.security.enabled: true

### TLS/SSL settings for Transport layer
xpack.security.transport.ssl.enabled: true
xpack.security.transport.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.keystore.password: liferay
xpack.security.transport.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.truststore.password: liferay
xpack.security.transport.ssl.verification_mode: certificate

## TLS/SSL settings for HTTP layer
xpack.security.http.ssl.enabled: true
xpack.security.http.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.keystore.password: liferay
xpack.security.http.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.truststore.password: liferay

# For Kibana
xpack.monitoring.collection.enabled: true
```

## フォロワーElasticsearchクラスターノードの構成

場所：`ES_FOLLOWER_HOME/config`

ファイル名：`elasticsearch.yml`

ファイルの内容：

``` yaml
cluster.name: LiferayElasticsearchCluster_FOLLOWER
node.name: es-follower-node-1

http.port: 9201
transport.port: 9301

xpack.security.enabled: true

### TLS/SSL settings for Transport layer
xpack.security.transport.ssl.enabled: true
xpack.security.transport.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.keystore.password: liferay
xpack.security.transport.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.truststore.password: liferay
xpack.security.transport.ssl.verification_mode: certificate

## TLS/SSL settings for HTTP layer
xpack.security.http.ssl.enabled: true
xpack.security.http.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.keystore.password: liferay
xpack.security.http.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.truststore.password: liferay

# For Kibana
xpack.monitoring.collection.enabled: true
```
