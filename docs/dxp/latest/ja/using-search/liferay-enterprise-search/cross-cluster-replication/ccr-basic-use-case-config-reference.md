# CCRの構成：設定リファレンス

CCR構成プロセスを支援するために、ここでは本ガイドの段階的な手順に従った設定例を示します。 これらの構成は可能な限り一般的なものになっていますが、環境に合わせてパスやポートなどを調整する必要があります。 さらに、インストールを完了するには特定の手順を手動で実行する必要があるため、これらの手順は段階的な手順に代わるものではありません。

以下の構成は、インストールでX-Pack Securityを介した暗号化通信（TLS/SSL）とユーザー認証を有効にすることを前提としています。 詳しくは、 [Elasticsearchの保護](../../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md) を参照してください。

```{tip}
   '<../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md>`_ は、ファイルが1つのノードにデプロイされると同時に、DXPクラスタ全体に伝播されます。 しかし、各クラスタノードには同一の構成を用意することが最善の方法です。 
```

<a name="remote-dxp-cluster-node-configurations" />

## リモートDXPクラスターノードの設定

これらの構成ファイルは`［Remote Liferay Home］/osgi/configs`にデプロイされます。

<a name="elasticsearch-configuration" />

### Elasticsearch の構成

リモートDXPクラスターには、`ElasticsearchConfiguration.config`ファイルが必要です。

ファイル名：`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config`

Liferay DXP 7.3ファイルの内容：

```properties
productionModeEnabled="true"
remoteClusterConnectionId="remote"
logExceptionsOnly="false"
```

Liferay DXP 7.1/7.2のファイル内容です。

```properties
clusterName="LiferayElasticsearchCluster_LEADER"
operationMode="REMOTE"
transportAddresses=["localhost:9300"]
logExceptionsOnly="false"
```

Liferay DXP 7.1/7.2では、個別の `XPackSecurityConfiguration.config` ファイルが必要です。

ファイル名：`com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config`

Liferay DXP 7.1/7.2のファイル内容です。


```properties
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
   Liferay DXP 7.3では、X-Packセキュリティ設定ファイルは必要ありません。 セキュリティの設定は、各コネクションの.configファイルで行います。 この値は、すべてのLiferayノードで同一でなければなりません。
```

<a name="elasticsearch-connection-configuration" />

### Elasticsearch接続設定

[DXP 7.3のみ】リモート接続ファイル名です。 `com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-remote.config`です。

ファイルの内容：

```properties
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
   Liferay DXP 7.1/7.2の場合、リモート接続を設定するために ``ElasticsearchConfiguration.config`` と ``XPackSecurityConfiguration.config`` ファイルを使用しました。
```

[DXP 7.3 only] 読み取り専用の接続ファイル名です。 `com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-ccr.config`となります。

Liferay DXP 7.3のファイルの内容：

```properties
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

[DXP 7.1/7.2 only] 読み取り専用の接続ファイル名です。 `com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.ElasticsearchConnectionConfiguration-ccr.config`となります。

Liferay DXP 7.1/7.2のファイル内容です。

```properties
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

<a name="local-dxp-cluster-node-configurations" />

## ローカルDXPクラスターノードの構成

これらの構成ファイルは`［Local Liferay Home］/osgi/configs`にデプロイされます。

リモートDXPノードに提供したローカルDXPノードに同一のElasticsearch 7構成（接続構成ファイルを含む）を提供します。

<a name="local-dxp-cluster-node-ccr-module-configurations" />

### ローカルDXPクラスターノードCCRモジュールの構成

任意のノード（リモートデータセンターにあるノードも含む）のシステム設定UIからLESクラスター横断設定アプリケーションを構成します。 LPKGが展開されている場合、設定項目は「システム設定」の &rarr; 「検索」 &rarr; 「クラスター横断レプリケーション」にあります。

次の値を設定します。

［***Read from Local Clusters**］ チェックボックスをオンにする
［***Local Cluster Configurations**］ プロパティに値 **localhost:9080,ccr** を設定する

![システム設定からCCRを設定します。](./ccr-basic-use-case-config-reference/images/01.png)

```{important}
   ここでは決してリモートデータセンターに値を設定しないでください（例では、``localhost:8080,remote``となります）。 これを設定すると、同じ名前のリーダーインデックスがすでに存在するリモートクラスタにフォロワーインデックスが作成されます。
```

<a name="leader-elasticsearch-cluster-node-configurations" />

## リーダーElasticsearchクラスターノードの構成

場所：`ES_LEADER_HOME/config`

ファイル名：`elasticsearch.yml`

ファイルの内容：
```yaml
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

<a name="follower-elasticsearch-cluster-node-configurations" />

## フォロワーElasticsearchクラスターノードの構成

場所：`ES_FOLLOWER_HOME/config`

ファイル名：`elasticsearch.yml`

ファイルの内容：

```yaml
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
