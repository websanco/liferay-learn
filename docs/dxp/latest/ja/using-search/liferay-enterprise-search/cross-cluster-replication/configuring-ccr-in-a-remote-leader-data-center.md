# リモートリーダーデータセンターでのCCRの構成

> 以下の手順を実行する前に、必要な[前提条件の手順](./configuring-an-example-ccr-installation-replicating-between-data-centers.md)を完了していることを確認してください。

このデータセンターは、同じ場所に配置されたElasticsearchクラスターへの読み取り/書き込み接続があるLiferay DXPクラスターノードを保持します。

ここに示す例は、単一のLiferay DXPノードと単一のElasticsearchノードで構成されています。 構成例は、セキュリティ構成の設定を含む[CCR設定リファレンス](./ccr-basic-use-case-config-reference.md)にも記載されています。

### リモートリーダーElasticsearchクラスターの構成

このセットアップ例では、最初に設定するElasticsearchクラスターは、CCR固有の設定がない本番環境モードのクラスターです。ローカルのLiferay DXPノードからの読み取りと書き込み、および別のデータセンタにあるLiferay DXPノードからの書き込み要求を受け入れます。

1.  `elasticsearch.yml`を構成します。

    `[Remote Elasticsearch Home]/config/elasticsearch.yml`

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

    セキュリティ設定（`xpack.security...`）を使用するには、パスワードを設定し、ノード証明書を取得する必要があります。 詳しくは、[Securing Elasticsearch](../../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)を参照してください。

2.  サーバーを起動します。 サーバーディレクトリのルートにいる場合は、以下を実行します。

    ``` bash
    ./bin/elasticssearch
    ```

3.  試用段階で、まだ適切なライセンスを持っていない場合は、[Elasticsearchのトライアルライセンス](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/start-trial.html)を開始してください。
   
        POST /_license/start_trial?acknowledge=true

    正常にインストールされると、ログに`- valid`メッセージが表示されます。

    ``` bash
    [2020-02-26T10:19:36,420][INFO ][o.e.l.LicenseService     ] [es-leader-node-1] license [lf263a315-8da3-41f7-8622-lfd7cc14cae29] mode [trial] - valid
    ```

### リモートLiferay DXPクラスターノードの構成

このセットアップにおけるLiferay DXPノードの1つは、リーダー/リモートElasticsearchサーバーとの間で読み取りと書き込みを行います。

1.  `Liferay Home/osgi/configs`フォルダに構成ファイルを提供して、Liferay Connector to Elasticsearch 7を設定します。 以下の名前を付けます

    ``` bash
    com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
    ```

2.  以下のコンテンツを指定します

    Liferay DXP 7.3の場合：

    ``` properties
    productionModeEnabled="true"
    remoteClusterConnectionId="remote"
    logExceptionsOnly="false"
    ```

    Liferay DXP 7.2の場合：

    ``` properties
    clusterName="LiferayElasticsearchCluster_LEADER"
    operationMode="REMOTE"
    transportAddresses=["localhost:9300"]
    logExceptionsOnly="false"
    ```

    ```{tip}
    During development and testing, it's useful to set `logExceptionsOnly="false"` in the configuration files. 
    ```

3.  リモート接続を設定します。

    Liferay DXP 7.3の場合、`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-remote.config`という名前の構成ファイルを`Liferay Home/osgi/configs`フォルダに提供します。

    ``` properties
    active=B"true"
    connectionId="remote"
    username="elastic"
    password="liferay"
    authenticationEnabled=B"true"
    httpSSLEnabled=B"true"
    networkHostAddresses=["https://localhost:9200"]
    truststorePassword="liferay"
    truststorePath="/PATH/TO/elastic-nodes.p12"
    truststoreType="pkcs12"
    ```

    ```{important}
    The `remoteClusterConnectionId` value in the `ElasticsearchConfiguration.config` must match the `connectionId` in the `ElasticsearchConnectionConfiguration-remote.config` file. 
    ```


    For Liferay DXP 7.2, secure the connection by providing a configuration file named `com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config` with these contents:

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

4.  これらの`.config`ファイルは同じリモート（書き込み）接続を使用するため、各フォロワーDXPノードにコピーします。 読み取り専用のフォロワー接続は、[Configuring CCR in a Local/Follower Data Center](./configuring-ccr-in-a-local-follower-data-center.md)で別途設定します。

5.  Liferay DXPサーバーを起動します。

    ```{important}
    If you're configuring a new DXP installation, make sure to reindex the spell check indexes at Control Panel > Configuration > Search, in the *Index Actions* tab.
    ```

Kibanaがリモート/リーダーのElasticsearchクラスターに接続されている場合は、[Management]→[Index Management]に移動して、使用可能なLiferayインデックスを確認します。

![Kibana 7のリーダーインデックスを調べます。](./configuring-ccr-in-a-remote-leader-data-center/images/01.png)

リモート/リーダーElasticsearchサーバーを含むデータセンターが稼働すると、[ローカル/フォロワーデータセンター](./configuring-ccr-in-a-local-follower-data-center.md)をセットアップする準備が整います。
