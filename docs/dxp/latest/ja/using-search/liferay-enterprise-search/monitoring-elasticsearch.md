# Elasticsearchのモニタリング

> LESサブスクライバー

Liferay Enterprise Search（LES）の[サブスクリプション](https://www.liferay.com/products/dxp/enterprise-search)をお持ちの場合は、Elasticの[KibanaモニタリングUI](https://www.elastic.co/guide/en/kibana/7.x/introduction.html)をLiferay DXPと統合できるため、Liferay自体の中でモニタリングアクティビティを実行できます。

![LES Monitoringを使用すると、LiferayのUIからLiferayのインデックスをモニタリングできます。](./monitoring-elasticsearch/images/01.png)

Liferayのデータがインデックス化されている[セキュリティで保護された](../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)Elasticsearchクラスターのモニタリングに必要な手順は以下の5つだけです。

1.  Elasticsearchにデータ収集を有効にするように指示します。

2.  Kibanaをダウンロードしてインストールします。

3.  適切なセキュリティ設定でKibanaを構成します。

4.  LESモニタリングアプリをインストールします。

5.  Elasticsearchと通信するようにLESモニタリングアプリを設定します。

## データ収集を有効にする

Elasticsearchではデフォルトでモニタリングが有効になっていますが、データ収集は有効になっていません。 この行を`elasticsearch.yml`に追加して、データ収集を有効にします。

``` yaml
xpack.monitoring.collection.enabled: true
```

Elasticsearchを再起動してから、Kibanaをインストールします。

## Kibanaをインストールする

KibanaのバージョンがElasticsearchのバージョンと一致していることを確認してください。 詳細については、[Liferay Enterprise Search互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360016511651)を参照してください。

```{note}
Elasticsearch 6.x has reached [end of life](https://www.elastic.co/support/eol#elasticsearch). Liferay 7.2 systems still using Elasticsearch 6.x should be upgraded to Elasticsearch 7.x. See  [Upgrading to Elasticsearch 7](./../installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-to-elasticsearch-7.md)_ for details.
```

1.  [Kibanaをダウンロード](https://www.elastic.co/downloads/kibana)して、解凍します。 ルートフォルダは*Kibanaホーム*と呼ばれます。

2.  `kibana.yml`でElasticsearchのURLを設定して、モニタリングデータの送信先をKibanaに指示します。

    ``` yaml
    elasticsearch.hosts: [ "https://localhost:9200" ]
    ```

    ElasticsearchでTLS/SSLが有効になっていない場合、これは`http` URLです。それ以外の場合は、`https`を使用します。

    セキュリティを設定していない場合は、Kibanaを起動します。

3.  ここで認証を設定します。 `[Kibana Home]/config/kibana.yml`で組み込みの`kibana_system`ユーザーのパスワードを設定します。

    ``` yaml
    elasticsearch.username: "kibana_system"
    elasticsearch.password: "liferay"
    ```

    [セキュリティ設定](../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)の`kibana_system`ユーザーパスワードを使用します。 Kibanaをインストールすると、*[Management]* ユーザーインターフェイスから組み込みのユーザーパスワードを変更できます。

4.  証明書ファイルを提供して、暗号化の設定を開始します。 詳しくは[Elastic社のガイド](https://www.elastic.co/guide/en/kibana/7.x/using-kibana-with-security.html#using-kibana-with-security)を参照してください。

    [Elasticsearch自体用に作成された](../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md#generate-node-certificates)ファイルを再利用するには、`[Elasticsearch Home]/config/certs`フォルダを`[Kibana Home]/config/`フォルダにコピーします。

    Kibanaインスタンス用に個別の証明書を生成する場合は、Elasticsearchノード証明書と同じCAによって署名されていることを確認してください。

5.  これらの設定を`kibana.yml`に追加します。

    ``` yaml
    elasticsearch.ssl.truststore.path: "config/certs/elastic-stack-ca.p12"
    elasticsearch.ssl.truststore.password: "liferay"   
    elasticsearch.ssl.keystore.path: "config/certs/elastic-nodes.p12"
    elasticsearch.ssl.keystore.password: "liferay"
    elasticsearch.ssl.verificationMode: certificate

    server.ssl.enabled: true
    server.ssl.truststore.path: "config/certs/elastic-stack-ca.p12"
    server.ssl.truststore.password: "liferay"
    server.ssl.keystore.path: "config/certs/elastic-nodes.p12"    
    server.ssl.keystore.password: "liferay"

    xpack.security.encryptionKey: "xsomethingxatxleastx32xcharactersx"
    xpack.security.session.idleTimeout: "1h"
    xpack.security.session.lifespan: "30d"

    # Below Kibana 7.6.0 (https://www.elastic.co/guide/en/kibana/7.6/release-notes-7.6.0.html)
    # only PEM format certificates and keys are supported. Comment out the trust/keystore
    # paths and passwords above and instead use:

    #elasticsearch.ssl.certificateAuthorities: [ "config/certs/ca.crt" ]
    #elasticsearch.ssl.certificate: config/certs/elastic-nodes.crt
    #elasticsearch.ssl.key: config/certs/elastic-nodes.key

    #server.ssl.certificateAuthorities: [ "config/certs/ca.crt" ]
    #server.ssl.certificate: config/certs/elastic-nodes.crt
    #server.ssl.key: config/certs/elastic-nodes.key
    ```

この手順の後、`https://localhost:5601`でKibanaにアクセスし、Kibanaユーザーでサインインして設定を確認できます。 最後のステップは、KibanaをLiferayに接続することです。

続行する前にKibanaを停止してください。

## LESモニタリングアプリをインストールして設定する

LESモニタリングアプリをダウンロードし、LPKGファイルを`[Liferay Home]/deploy`フォルダにコピーしてインストールします。 Liferay DXPが実行されている場合、サーバーを再起動するように求められる場合があります。 または、Liferayを実行していない状態で、LPKGファイルを`[Liferay Home]/marketplace`フォルダに配置することもできます。

1.  コネクタがインストールされ、KibanaとElasticsearchが安全に設定されたら、`com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.MonitoringConfiguration.config`という名前の[構成ファイル](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)を作成します。

    Liferay DXP 7.2では、ファイルに`com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config`という名前を付けます。

2.  これらの設定を`.config`ファイルに配置します。

    ``` properties
    kibanaPassword="liferay"
    kibanaUserName="elastic"
    kibanaURL="https://localhost:5601"
    ```

    開発およびテスト中に、プロキシサーブレットログを有効にすると役立つ場合があります。

    ``` properties
    proxyServletLogEnable=B"true"
    ```

    正確な構成値は、Kibanaの構成によって異なります。 たとえば、TLSを有効にしていない場合は、`kibanaURL="http://localhost:5601"`などのURLを使用します。

    または、[システム設定](../../system-administration/configuring-liferay/system-settings.md)からモニタリングアダプターを設定します。 グローバルメニューで、*[コントロールパネル]* → *[設定]* → *[システム設定]* に移動し、[検索]カテゴリでElasticsearch Monitoringエントリを見つけます。 モニタリングコネクタのすべての設定オプションがそこに表示されます。

3.  構成ファイルを`Liferay Home/osgi/configs`にデプロイすると、実行中のインスタンスが設定を適用します。

4.  Kibana自体にさらに2つの設定を追加します。 1つ目は、Kibanaが`server.basePath`で始まるリクエストを書き換えることを禁止します。 2つ目は、モニタリングポートレットのKibanaのベースパスを、KibanaのモニタリングUIのプロキシとして機能するように設定します。 これを`kibana.yml`に追加します。

    \[Liferay DXP 7.3\]

    ``` yaml
    server.rewriteBasePath: false
    server.basePath: "/o/portal-search-elasticsearch-monitoring/monitoring-proxy"
    ```

    \[Liferay DXP 7.2\]

    ``` yaml
    server.rewriteBasePath: false
    server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"
    ```

    `server.basePath`を設定すると、KibanaのURL（`https://localhost:5601`など）からKibana UIにアクセスできなくなります。 Kibana UIへのすべてのアクセスは、サインインしたLiferayユーザーのみがアクセスできるモニタリングポートレットを介して行われます。 URLを使用してポートレットに直接移動します

    <http://localhost:8080/o/portal-search-elasticsearch-monitoring/monitoring-proxy/app/monitoring>

5.  LiferayのモニタリングポートレットをKibanaのUIのプロキシとして使用していて、自己署名証明書を使用しているため、Kibanaの証明書を信頼するようにアプリケーションサーバーの起動JVMパラメーターを構成する必要があります。

    1つのアプローチは、トラストストアのパス、パスワード、およびタイプをアプリケーションサーバーの起動JVMパラメーターに追加することです。 以下は、Tomcatサーバーの`CATALINA_OPTS`に追加するためのトラストストアとパスのパラメーターの例です。

    ``` bash
    -Djavax.net.ssl.trustStore=/path/to/elastic-nodes.p12 -Djavax.net.ssl.trustStorePassword=liferay -Djavax.net.ssl.trustStoreType=pkcs12
    ```

LiferayとKibanaを再起動します。

## Liferayでのモニタリング

KibanaとLES Monitoringをインストールして設定し、すべてのサーバーを実行したら、Elasticsearch Monitoringウィジェットをページに追加します。

1.  コンテンツページの*[フラグメントとウィジェット]* メニュー、またはウィジェットページの[Add Widgets]メニューを開きます。

2.  ウィジェット検索バーを使用して*モニタリング*を検索し、*Elasticsearch Monitoring*ウィジェットを[検索]カテゴリからページにドラッグします。 Liferay DXP 7.2の場合、ウィジェットは*X-Pack Monitoring*と呼ばれます。

> 詳細については、関連するElasticsearchのドキュメントを参照してください。
> 
> - [Monitoring a cluster](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/es-monitoring.html)
> - [Set up X-Pack---クラスター環境でのモニタリングとセキュリティのベストプラクティス](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-xpack.html)

## Kibana構成の例

以下は、上記で説明した`kibana.yml`の完全版です。

``` yaml
# X-Pack Security enabled (Basic Auth)
elasticsearch.username: "kibana_system"
elasticsearch.password: "liferay"
elasticsearch.hosts: [ "https://localhost:9200" ]

# When TLS/SSL is enabled in X-Pack Security
xpack.security.encryptionKey: "xsomethingxatxleastx32xcharactersx"
xpack.security.session.idleTimeout: "1h"
xpack.security.session.lifespan: "30d"
# Enable TLS/SSL for out-bound traffic: from Kibana to Elasticsearch
elasticsearch.ssl.truststore.path: "config/certs/elastic-stack-ca.p12"
elasticsearch.ssl.truststore.password: "liferay"   
elasticsearch.ssl.keystore.path: "config/certs/elastic-nodes.p12"
elasticsearch.ssl.keystore.password: "liferay"
elasticsearch.ssl.verificationMode: certificate

# Enable TLS/SSL for in-bound traffic: from browser or
#  DXP (Elasticsearch Monitoring widget, proxy) to Kibana
server.ssl.enabled: true
server.ssl.truststore.path: "config/certs/elastic-stack-ca.p12"
server.ssl.truststore.password: "liferay"
server.ssl.keystore.path: "config/certs/elastic-nodes.p12"    
server.ssl.keystore.password: "liferay"

# To use Kibana inside the Elasticsearch Monitoring widget
server.rewriteBasePath: false
server.basePath: "/o/portal-search-elasticsearch-monitoring/monitoring-proxy"

# Below Kibana 7.6.0 (https://www.elastic.co/guide/en/kibana/7.6/release-notes-7.6.0.html)
# only PEM format certificates and keys are supported. Comment out the trust/keystore
# paths and passwords above and instead use:

#elasticsearch.ssl.certificateAuthorities: [ "config/certs/ca.crt" ]
#elasticsearch.ssl.certificate: config/certs/elastic-nodes.crt
#elasticsearch.ssl.key: config/certs/elastic-nodes.key

#server.ssl.certificateAuthorities: [ "config/certs/ca.crt" ]
#server.ssl.certificate: config/certs/elastic-nodes.crt
#server.ssl.key: config/certs/elastic-nodes.key
```

## 関連トピック

  - [Securing Elasticsearch](../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)
  - [Cross-Cluster Replication](./cross-cluster-replication.md)
  - [Search Tuning](../search-administration-and-tuning.md)
  - [Liferay Installation and Upgrades](../../installation-and-upgrades.md)
