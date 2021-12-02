# Elasticsearchの保護

Elasticsearchを保護するために最初に行う必要があるのは、[X-Pack Securityを有効にする](#enable-x-pack-security)ことです。 その後、認証と暗号化通信の設定を開始できます。

```{note}
**Elasticsearch 6.x:** If you're using Elasticsearch 6, you'll need a Liferay Enterprise Search (LES) subscription and the Liferay Enterprise Search Security application to use Elastic's X-Pack Security. Starting with the Liferay Connector to Elasticsearch 7 (available on [the Customer Downloads portal](https://customer.liferay.com/downloads) and bundled in Liferay 7.3), support for Elastic's X-Pack security is included by default. To integrate with Elastic's X-Pack monitoring, LES is required.
```

## X-Pack Securityの有効化

セキュリティを有効にするには、各Elasticsearchノードの`[Elasticsearch Home]/config/elasticsearch.yml`ファイルに次の設定を追加します。

``` yaml
xpack.security.enabled: true
```

これで、X-Packユーザーを設定できます。

## X-Packユーザーの設定

X-Packを使用するシステムでは、次の[組み込みX-Packユーザー](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/built-in-users.html)が重要です。

  - `kibana_system`
  - `elastic`

Elasticsearchサーバーで、[`setup-passwords`コマンド](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-passwords.html)を使用してパスワードを設定します。

``` bash
./bin/elasticsearch-setup-passwords interactive
```

```{note}
The configurations shown below assume all passwords are set to *liferay*. Use your own passwords for your installation.
```

```{note}
To update a built-in user's password, use Kibana's UI or the [Change Password API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/security-api-change-password.html).
```

## Elasticsearch通信の暗号化

トランスポート層セキュリティ（TLS）を有効にするには、ノード証明書と鍵を生成し、ElasticsearchサーバーとLiferayサーバーに適用する必要があります。

### ノード証明書の生成

ノードごとに[証明書を生成する](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#node-certificates)か、Liferayなどのすべてのノードとクライアントで使用する証明書を生成します。 または、認証局を使用してノード証明書を取得します。

1.  X-Packの[`certutil`](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/certutil.html)コマンドを使用してX-Pack認証局を生成します。

    ``` bash
    ./bin/elasticsearch-certutil ca --ca-dn CN=elastic-ca
    ```

    `elastic-stack-ca.p12`というファイルが生成されます。

    認証局の証明書と秘密鍵をPEM形式で生成するには、

    ``` bash
    ./bin/elasticsearch-certutil ca --pem --ca-dn CN=elastic-ca
    ```

2.  認証局ファイルを`[Elasticsearch Home]/config/certs`フォルダに移動します。

3.  作成したCAを使用して、X.509証明書と秘密鍵を生成します。

    `PKCS#12`形式で証明書と鍵を生成するには、次のコマンドを実行します。

    ``` bash
    ./bin/elasticsearch-certutil cert --ca config/certs/elastic-stack-ca.p12 --ca-pass liferay --dns localhost --ip 127.0.0.1 --name elastic-nodes
    ```

    `PEM`形式で証明書と鍵を生成するには、次のコマンドを実行します。

    ``` bash
    ./bin/elasticsearch-certutil cert --pem --ca-cert config/certs/ca.crt --ca-key config/certs/ca.key --dns localhost --ip 127.0.0.1 --name elastic-nodes
    ```

    `PKSC#12`認証局から`PEM`形式のノード証明書と鍵を生成するには、次のコマンドを実行します。

    ``` bash
    ./bin/elasticsearch-certutil cert --pem --ca config/certs/elastic-stack-ca.p12 --ca-pass liferay --dns localhost --ip 127.0.0.1 --name elastic-nodes
    ```

    ```{note}
    On Liferay 7.3 only the following keystore types can be used in the Elasticsearch 7 connector configuration: https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keystore-types
    ```


    To generate a certificate that works with multiple hosts (for example to use the same certificate on all Elasticsearch and Liferay servers), use comma-separated lists when listing the DNS names and IP addresses:

    ``` bash
    ./bin/elasticsearch-certutil cert --ca config/certs/elastic-stack-ca.p12 --ca-pass liferay --dns localhost,example.com,es-node1,es-node2,es-node3 --ip 127.0.0.1,<IPv4-address-2>,<IPv4-address-3>,<IPv4-address-4>
    ```


    The `elasticsearch-certutil cert` command generates another file called `elastic-nodes.p12` (feel free to name it differently).

    ```{note}
    The `certutil` command defaults to using the *PKCS#12* format for certificate generation, which works with your Elastic Stack 7.x. Kibana 6.x does not work with PKCS#12 certificates, so the `--pem` option (generates the certificate in *PEM* format) is important if you're using Liferay 7.2 and Kibana 6.x with *Liferay Enterprise Search Monitoring*. The PEM command  for each case generates two ZIP files: `ca.crt` and `ca.key`, `elastic-nodes.crt` and `elastic-nodes.key`. Unzip the archive' contents in the *[Elasticsearch Home]/config/certs* folder.
    ```

4.  `elastic-nodes.p12`を`[Elasticsearch Home]/config/certs`フォルダに移動します。

    **チェックポイント：**`[Elasticsearch Home]/config/certs`フォルダに次のファイルができました。

    ``` bash
    elastic-nodes.p12
    elastic-stack-ca.p12
    ```

    または

    ``` bash
    ca.crt
    ca.key
    elastic-nodes.crt
    elastic-nodes.key
    ```

5.  ファイルを各Elasticsearchノードの同じフォルダと各Liferayサーバーノードの適切な場所にコピーします。

証明書と鍵が、Elasticsearch設定で使用できるようになりました。

### Elasticsearch用のTLSを設定する

`[Elasticsearch Home]/config/elasticsearch.yml`ファイルを介して各ノードで[TLSを有効](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html)にします。

1.  ノード間通信のために`elasticsearch.yml`で次の設定を使用してトランスポート層TLSを有効にします。

    ``` yaml
    xpack.security.transport.ssl.enabled: true
    ```

2.  証明書、鍵、認証局のパスを各ノードの`elasticsearch.yml`に追加します。

    ``` yaml
    # PKCS#12
    xpack.security.transport.ssl.keystore.path: certs/elastic-nodes.p12
    xpack.security.transport.ssl.keystore.password: liferay
    xpack.security.transport.ssl.truststore.path: certs/elastic-nodes.p12
    xpack.security.transport.ssl.truststore.password: liferay
    # PEM
    #xpack.security.transport.ssl.certificate_authorities: [ "certs/ca.crt" ]
    #xpack.security.transport.ssl.certificate: certs/elastic-nodes.key
    #xpack.security.transport.ssl.key: certs/elastic-nodes.crt

    xpack.security.transport.ssl.verification_mode: certificate
    ```

    上記のパスの例は、`[Elasticsearch Home]/config/certs`に証明書を追加したことを前提としています。

3.  HTTPレイヤーでTLSを有効にして、クライアント通信を暗号化します。

    ``` yaml
    xpack.security.http.ssl.enabled: true
    ```

4.  各ノードの`elasticsearch.yml`への証明書、鍵、および認証局のパスを設定します。

    ``` yaml
    # PKCS#12
    xpack.security.http.ssl.keystore.path: certs/elastic-nodes.p12
    xpack.security.http.ssl.keystore.password: liferay
    xpack.security.http.ssl.truststore.path: certs/elastic-nodes.p12
    xpack.security.http.ssl.truststore.password: liferay
    # PEM
    #xpack.security.http.ssl.certificate_authorities: [ "certs/ca.crt" ]
    #xpack.security.http.ssl.certificate: certs/elastic-nodes.crt
    #xpack.security.http.ssl.key: certs/elastic-nodes.key
    ```

### Elasticsearchのセキュリティ設定の例

Elasticsearch 7の完全な設定は次のとおりです（`elasticsearch.yml`。Elasticsearch 6.5.x以降にも同様に適用されます）。

``` yaml
cluster.name: LiferayElasticsearchCluster

# X-Pack Security
xpack.security.enabled: true

## TLS/SSL settings for Transport layer (PKCS#12)
xpack.security.transport.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.keystore.password: liferay
xpack.security.transport.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.truststore.password: liferay
xpack.security.transport.ssl.verification_mode: certificate

# TLS/SSL settings for HTTP layer (PKCS#12)
xpack.security.http.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.keystore.password: liferay
xpack.security.http.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.truststore.password: liferay

# Comment out when Kibana and Liferay's X-Pack Monitoring are also configured
#xpack.monitoring.collection.enabled: true
```

## LiferayでElasticsearchへの安全な接続を設定する

Liferayでは、セキュリティはコントロールパネルまたは設定ファイルを使用して構成できます。 *[コントロールパネル]* → *[設定]* → *[システム設定]* に移動します。 *[検索機能]* カテゴリを見つけて、Liferay 7.3の*[Elasticsearch 7]* エントリまたはLiferay 7.2の*[X-Pack Security]* エントリをクリックします。 ここにプロパティ値を入力することもできますが、`[Liferay Home]/osgi/configs`にデプロイされている設定ファイルを使用するのが一般的です。

ファイルの正確なコンテンツは、X-Packの設定によって異なります。 `password`は、上記のX-Packユーザーパスワードのセットアップ中に設定したものと一致する必要があります。

ここで参照されている証明書と鍵のファイルは、Elasticsearchサーバーノードで使用されているものと同じです。 それらをLiferayサーバーにコピーし、それに応じて設定内のパスを更新します。

TLSの設定に加えて、`authenticationEnabled`/`requiresAuthentication`を`true`に設定し、LiferayがElasticsearchへの認証に使用するElasticsearchユーザーの認証情報を提供することにより、以下の設定で認証を有効にします。

セキュリティの設定が完了したら、Elasticsearchを再起動します。 これらの手順では、Elasticsearchクラスターを完全に再起動する必要があります。

### Liferay 7.3でElasticsearchへの安全な接続を設定する

```{tip}
The [Installing Elasticsearch](./installing-elasticsearch.md)_ and [Connecting to Elasticsearch](./connecting-to-elasticsearch.md)_ articles default to enabling and configuring security, so you can also visit those articles for the 7.3 applicable security configurations.
```

Liferay 7.3にバンドルされているLiferay Connector to Elasticsearch 7には、X-Pack Securityのサポートが含まれています。 Elasticsearch 7コネクタの設定で使用できるサポートされているキーストアタイプの一覧については、[Java 11セキュリティのドキュメンテーション](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keystore-types)を参照してください。

以下のようなファイルを作成します。

``` bash
com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
```

次のようにファイルにデータを入力します。

``` properties
operationMode="REMOTE"
productionModeEnabled=B"true"
username="elastic"
password="liferay"
authenticationEnabled=B"true"
httpSSLEnabled=B"true"
networkHostAddresses=["https://localhost:9200"]
truststorePassword="liferay"
truststorePath="/PATH/TO/elastic-nodes.p12"
truststoreType="pkcs12"
```

### Liferay 7.2でElasticsearchへの安全な接続を設定する

Elasticsearch 7へのすべてのLiferayコネクタには、X-Pack Securityのサポートが含まれています。

```{note}
If you are on Liferay 7.2 and Elasticsearch 6.x and have a Liferay Enterprise Search subscription, [download](https://customer.liferay.com/downloads/-/download/liferay-enterprise-search-for-liferay-dxp-7-2)_ the `Liferay Enterprise Search Security` application. Install the LPKG file by copying it into the `[Liferay Home]/deploy` folder.
```

以下のようなファイルを作成します。

``` bash
com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config
```

（またはElastic Stack 6.xと`Liferay Enterprise Search Security`アプリケーションを使用している場合は`com.liferay.portal.search.elasticsearch6.configuration.XPackSecurityConfiguration.config`）

次のようにファイルにデータを入力します（`PKCS#12`）：

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

`PEM`形式の証明書を使用している場合は、次のような設定を使用します。

``` properties
certificateFormat="PEM"
sslKeyPath="/PATH/TO/elastic-nodes.key"
sslCertificatePath="/PATH/TO/elastic-nodes.crt"
requiresAuthentication=B"true"
username="elastic"
password="liferay"
sslCertificateAuthoritiesPaths="/PATH/TO/ca.crt"
transportSSLVerificationMode="certificate"
transportSSLEnabled="true"
```

### Liferay 7.3でのElasticsearch 7コネクタセキュリティの設定

7.3におけるElasticsearch 7コネクタのセキュリティ設定の完全な一覧は次のとおりです（括弧内はデフォルト値）。

`authenticationEnabled`（*true*）：ユーザー名とパスワードを使用したElasticsearchへの認証を有効または無効にします。

`username`（*elastic*）：[認証が有効]がオンになっている場合、認証用のユーザー名をElasticsearchに設定します。

`password`：[認証が有効]がオンになっている場合、認証用のパスワードをElasticsearchに設定します。

`httpSSLEnabled`（*false*）：TLS/SSLを有効または無効にします。

`truststoreType`（*pkcs12*）：[HTTP SSLが有効]がオンになっている場合、トラストストアの種類を設定します。

`truststorePath`（*/path/ro/localhost.p12*）：[HTTP SSLが有効]がオンになっている場合、トラストストアファイルへのパスを設定します。

`truststorePassword`：[HTTP SSLが有効]がオンになっている場合、パスワードをトラストストアに設定します。

### Liferay 7.2のエンタープライズ・サーチセキュリティ/X-Pack Securityの設定

Liferay 7.2のX-Pack Security構成の設定の完全な一覧は次のとおりです。

`sslKeyPath`（*/path/to/instance.key*）：秘密鍵を含むPEMエンコードファイルへのパスを設定します。

`sslCertificatePath`（*/path/to/instance.crt*）：クライアントが接続するときにクライアントに提示される証明書（または証明書チェーン）を含むPEMエンコードファイルへのパスを設定します。 デフォルトは`/path/to/instance.crt`です。

`sslCertificateAuthoritiesPaths`（*\["/path/to/ca.crt"\]*）：信頼できるPEMでエンコードされた証明書ファイルへのパスのリストを提供します。

`certificateFormat`（*PKCS\#12*）：証明書の形式を指定します（`PEM`または`PKCS#12`）。

`requireAuthentication`（*false*）：有効にすると、Elasticsearch/X-Packとの接続は設定されたユーザー名とパスワードで認証されます。

`username`（*elastic*）：[認証を要求]が有効になっている場合は、認証用のユーザー名をElasticsearchに設定する必要があります。

`password`：[認証を要求]が有効になっている場合、パスワードが必要です。

`transportSSLVerificationMode`（*certificate*）：LDAPを使用して中間者攻撃や証明書の偽造から保護する場合は、検証タイプ（`none`、`certificate`、または `full`）を指定します。

`transportSSLEnabled`（*false*）：TLS/SSLを設定または無効にします。

`sslKeystorePath`（*/path/to/elastic-certificates.p12*）：秘密鍵と証明書を保持しているキーストアへのパスを設定します。

`sslKeystorePassword`：パスワードをPKCS\#12ファイルに設定します。

`sslTruststorePath`（*/path/to/elastic-certificates.p12*）：トラストストアファイルへのパスを設定します。

`sslTruststorePassword`：パスワードをトラストストアに設定します。

## 関連トピック

  - [Monitoring Elasticsearch](../../liferay-enterprise-search/monitoring-elasticsearch.md)
  - [Cross-Cluster Replication](../../liferay-enterprise-search/cross_cluster_replication.rst)
  - [Search Tuning](../../search_administration_and_tuning.rst)
  - [Liferay Installation and Upgrades](../../../installation-and-upgrades.md)
