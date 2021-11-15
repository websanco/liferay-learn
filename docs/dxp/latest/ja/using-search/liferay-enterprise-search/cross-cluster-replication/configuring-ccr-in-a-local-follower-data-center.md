# ローカルフォロワーデータセンターでのCCRの構成

> 以下の手順を実行する前に、まず[リモート/リーダーデータセンターでCCRを構成](./configuring-ccr-in-a-remote-leader-data-center.md)してください。

ローカル/フォロワーデータセンターには、Liferay DXPクラスターノードが保持されており、同じ場所に配置されたElasticsearchクラスターへの読み取り専用接続とリモート/リーダーデータセンターのElasticsearchクラスターへの書き込み専用接続があります。

完全な構成例は、[CCR設定リファレンスガイド](./ccr-basic-use-case-config-reference.md)にも記載しています。

### フォロワーElasticsearchクラスターの構成

ローカルElasticsearchクラスターはフォロワー（レプリケート済み、読み取り専用）インデックスを保持する必要があり、同じ場所にあるLiferay DXPノードが読み取ることができるローカル検索エンジンとして機能します。

```{important}
**Securing a CCR Installation:** As [stated earlier](./configuring-an-example-ccr-installation-replicating-between-data-centers.md#prerequisite-for-security-configure-authentication-and-encryption)_ the Elasticsearch clusters should use node certificates signed by the same CA and the security settings of each cluster should match. For other approaches and details, [see Elastic's documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/cross-cluster-configuring.html)_.
```

1.  `elasticsearch.yml`を構成します。

    `[Follower Elasticsearch Home]/config/elasticsearch.yml`

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

    セキュリティ設定（`xpack.security...`）を使用するには、パスワードを設定し、ノード証明書を取得する必要があります。

2.  サーバーを起動します。 サーバーディレクトリのルートにいる場合は、以下を実行します。

    ``` bash
    ./bin/elasticssearch
    ```

3.  試用段階で、まだ適切なライセンスを持っていない場合は、Elasticsearchのトライアルを開始してください。
   
        POST /_license/start_trial?acknowledge=true

## ローカルLiferay DXPクラスターノードの構成

```{tip}
If testing locally, configure Tomcat to use different ports than your remote DXP node. Use `9080` as the HTTP port, `9443` as the redirect port, and `9005` as the shutdown port to follow this example setup (change the server ports in `[Liferay Home]/tomcat-[version]/conf/server.xml`).
```

1.  [Elasticsearch接続構成ファイル](./configuring-ccr-in-a-remote-leader-data-center.md#configure-the-remote-liferay-dxp-cluster-node)をリモートDXPクラスターノードの`osgi/configs`フォルダからローカルDXPクラスターノードの対応するフォルダにコピーします。

    Liferay DXP 7.3の場合、これには`*ElasticsearchConnectionConfiguration-remote.config` と`*ElasticsearchConfiguration.config`が含まれています。

    ```{important}
    The `remoteClusterConnectionId` value in the `ElasticsearchConfiguration.config` must match the `connectionId` in the `ElasticsearchConnectionConfiguration-remote.config` file. 
    ```

    Liferay DXP 7.2の場合、これには`*ElasticsearchConfiguration.config`と`*XPackSecurityConfiguration.config`が含まれています。

    これらのファイルが提供されると、ローカルDXPクラスターノードの書き込み接続が構成されます。

2.  次に、フォロワーインデックスを使用してローカルElasticsearchサーバーへの読み取り専用接続を構成します。

    Liferay DXP 7.3の場合、`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-ccr.config`という名前の構成ファイルを`Liferay Home/osgi/configs`に提供します。

    以下のコンテンツを指定します

    ``` properties
    active=B"true"
    connectionId="ccr"
    username="elastic"
    password="liferay"
    authenticationEnabled=B"true"
    httpSSLEnabled=B"true"
    networkHostAddresses=["https://localhost:9201"]
    truststorePassword="liferay"
    truststorePath="/PATH/TO/elastic-nodes.p12"
    truststoreType="pkcs12"
    ```

    Liferay DXP 7.2の場合、`com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.ElasticsearchConnectionConfiguration-ccr.config`という名前の構成ファイルを`Liferay Home/osgi/configs`に提供します。

    ```{warning}
    On Liferay 7.2, do not deploy the configuration file for the CCR connection (e.g., `ElasticsearchConnectionConfiguration-ccr.config`) simultaneously with the LES Cross-Cluster Replication LPKG's initial deployment. There's a known bug ([LPS-127821](https://issues.liferay.com/browse/LPS-127821)_) that breaks Liferay's search functionality if the configuration file is deployed before the module is fully started. If you've already encounterd this, see [Troubleshooting Cross-Cluster Replication](./troubleshooting-cross-cluster-replication.md#liferay-7-2-after-deploying-the-ccr-lpkg-and-the-elasticsearchconnectionconfiguration-file-search-is-broken)_ for the workaround.
    ```

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


    You can use any suffix (`-ccr` in this example) for the [configuration file name](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-factory-configuration.md), but for consistency you should make it identical to the `connectionId` property in the configuration.

3.  Liferay DXPクラスターノードを起動します。

これで接続が構成されました。

![CCR（READ）接続が構成され、使用できるようになりました。](./configuring-ccr-in-a-local-follower-data-center/images/03.png)

あとは、CCR自体を有効にして構成するだけです。

## クラスター横断レプリケーションモジュールの設定

LESクラスター横断レプリケーションモジュールは、リーダークラスターの後続、およびリーダークラスターからフォロワークラスターへのすべてのインデックスの初期レプリケーションをトリガーします。 フォローとレプリケーションをトリガーするには、構成ファイル（`.config`）ではなく、システム設定のUIでCCR機能を有効にする必要があります。 いずれかのデータセンターのLiferay DXPノードからCCRを構成します。

1.  グローバルメニューを開き、*[コントロールパネル]* → *[システム設定]* に移動します。 *[検索機能]* カテゴリを開きます。

2.  *[クラスター横断レプリケーション]* を開きます。

3.  *[Read from Local Clusters]* のボックスをオンにします。

4.  *[Local Cluster Configurations]* に値を1つ設定します （`localhost:9080,ccr`）。

    ```{important}
    Never set the value to the remote data center here (in the example, it would be `localhost:8080,remote`). Setting this would cause follower indexes to be created in the remote cluster, where leader indexes of the same name already reside.
    ```

    これは、読み取り専用とする接続を定義するものです。 人間の言語で言うと、ここの各エントリは「このアドレス（`localhost:9080`）のLiferayサーバーはこの名前（この例では`ccr`）のElasticsearch接続から読み取りを行う」ということを示しています。

5.  *[アップデート]* をクリックします。

本番環境のセットアップでは、リモートのElasticsearchクラスターに別のトランスポートアドレスを設定したり（この例ではデフォルトを使用）、フォロワーのElasticsearchクラスターへのレプリケートから一部のインデックスを除外したりすることができます。 これらの目的のための設定フィールドがあります。

**リモートクラスター内シードノードのトランスポートアドレス**：リモートクラスターとローカルクラスター間の接続を確立するために使用される、リモートクラスター内のノードのトランスポートアドレス。 デフォルトは`localhost:9300`です。

**除外するインデックス**：クラスター横断レプリケーションから除外するインデックス名を入力します。 ピリオド（.）で始まるインデックスは常に除外されます。 デフォルトでは、リモートクラスター内のすべてのインデックスがローカルクラスターにレプリケートされます。 自動レプリケーションが有効になっていない場合、この設定は無視されます。

**Automatic Replication Enabled**：ローカルクラスターからの読み取りが有効になっている場合、ローカルElasticsearchクラスターでのフォロワーインデックスの自動作成を有効または無効にします。 Elasticsearchを介してレプリケーションを手動で管理する場合は、この設定を無効にします。 デフォルトは *[enabled]* です。

![システム設定でCCRを構成します。](./configuring-ccr-in-a-local-follower-data-center/images/02.png)

インデックスレプリケーションが成功し、読み取り接続が有効になっていることを示すログメッセージが表示されます。

    2021-01-22 02:15:11.112 INFO  [liferay/configuration-1][CrossClusterReplicationConfigurationModelListener:163] Creating follower indexes
    2021-01-22 02:15:12.864 INFO  [liferay/configuration-1][CrossClusterReplicationConfigurationModelListener:70] Read operations from local clusters are enabled

接続が構成され、インデックスがレプリケートされたら、システムが正しく機能していることを確認します。

## セットアップの確認

フォロワーDXPクラスターノードで、[コントロールパネル]→[設定]→[検索機能]に移動します。 Liferay DXP 7.2では、*[Connections]* タブもクリックする必要があります。 接続は次のようになります。

![検索管理パネルでElasticsearch 7の接続を確認します。](./configuring-ccr-in-a-local-follower-data-center/images/01.png)

これでCCRが構成されました。 構成で問題が発生した場合は、[トラブルシューティングガイド](./troubleshooting-cross-cluster-replication.md)を確認してください。
