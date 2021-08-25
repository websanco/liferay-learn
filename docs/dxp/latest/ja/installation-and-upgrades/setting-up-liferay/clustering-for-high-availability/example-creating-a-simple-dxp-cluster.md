# 例：単純なDXPクラスターの作成

DXPクラスタリングを学習する簡単な方法は、 [Dockerコンテナ](https://docs.docker.com/get-started/overview/)を使用して、単一のマシンに2ノードのDXPクラスタ環境をセットアップすることです。 ここでは、必要な各サーバーと2つのDXPアプリサーバーノードをそれぞれのコンテナに準備します。 コンテナは、Dockerブリッジネットワークを介してコンテナ名で相互に参照します。 これは、DXPクラスタ開発環境をセットアップするための迅速な方法です。

作成するサーバーは次のとおりです。

| サーバータイプ | 実装            | サーバーコンテナ        |
|:------- |:------------- |:--------------- |
| データベース  | MariaDB       | `some-mariadb`  |
| 検索エンジン  | Elasticsearch | `elasticsearch` |
| ファイルストア | DBStore       | `some-mariadb`  |
| アプリサーバー | Tomcat        | `dxp-1`         |
| アプリサーバー | Tomcat        | `dxp-2`         |
| アプリサーバー | Apache Httpd  | `httpd`         |

次の図は、作成するDXPクラスター環境を示しています。

![DXPクラスター環境。](./example-creating-a-simple-dxp-cluster/images/01.png)

クラスタを作成する手順は次のとおりです。

1.  [コンテナのネットワークを構成します](#configure-a-network-for-the-containers) （例固有）
2.  [データベースサーバーの準備](#prepare-a-database-server)
3.  [検索エンジンを準備する](#prepare-a-search-engine)
4.  [ファイルストアを準備する](#prepare-a-file-store)
5.  [DXPサーバークラスターの構成](#configure-the-dxp-server-cluster)
6.  [クラスターの前面にWebサーバーを配置する](#front-the-cluster-with-a-web-server)
7.  [クラスターをテストする](#test-the-cluster)

<!-- end list -->

```{important}
Dockerをお持ちではありませんか？ まずは [Linux](https://docs.docker.com/install/linux/docker-ce/ubuntu/) | [Windows](https://docs.docker.com/docker-for-windows/install/) | [OSX](https://docs.docker.com/docker-for-mac/install/) に移動してください。
```

```{note}
DXP cluster environments can also be set up using an [on-premises DXP Tomcat bundle](../../installing-liferay/installing-a-liferay-tomcat-bundle.md), using [DXP installed to an app server](../../installing-liferay/installing_liferay_on_an_application_server.md) on-premises, or using any combination of Docker containers and DXP installations.
```

## コンテナのネットワークを構成する

この例のサーバーコンテナは同じマシンで実行されるため、サーバーのIPアドレスは複雑になる可能性があります。 [Dockerブリッジネットワーク](https://docs.docker.com/network/bridge/)ですべてのコンテナを起動することにより、コンテナはIPアドレスではなくコンテナ名で相互に参照できます。 この種の環境以外では、IPアドレスが使用されます。

ホストマシンで使用するコンテナ用に任意の名前のDockerブリッジネットワークを作成します。

``` bash
docker network create --driver=bridge my-bridge
```

`my-bridge` と呼ばれるブリッジネットワークが利用可能です。

## データベースサーバーの準備

DXPクラスターには、すべてのアプリサーバーノードからアクセスできるデータソースが必要です。 データソースは、JNDIデータソース、データベースサーバー、またはデータベースサーバークラスターです。 DXPバージョンがサポートするデータベースサーバーについては、 [互換性マトリックス](https://www.liferay.com/compatibility-matrix) を参照してください。

[MariaDB Dockerイメージ](https://hub.docker.com/_/mariadb/)基づいてデータベースサーバーコンテナを作成します。

1.  MariaDBデータベースサーバーコンテナを作成します。 まず、MariaDB Dockerイメージをダウンロードします。

    ``` bash
    docker pull mariadb:10.2
    ```

    Maria DB Dockerイメージを実行します。

    ``` bash
    docker run --name some-mariadb --network=my-bridge -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mariadb:10.2
    ```

    `docker run` コマンドは、 `my-bridge` Dockerネットワーク上に `some-mariadb` というMariaDB Dockerコンテナを作成します。 データベースサーバーの `root` ユーザーパスワードは `my-secret-pw`です。 詳細については、 [MariaDB Docker Hubページ](https://hub.docker.com/_/mariadb/) を参照してください。

2.  コンテナ上のシェルで、[DXP用のデータベース](../../reference/database-configurations.md)を作成します。

    MariaDBコンテナのターミナルに接続します。

    ``` bash
    docker exec -it some-mariadb bash
    ```

    データベースにログインします。

    ``` bash
    mysql -uroot -pmy-secret-pw
    ```

    データベースを作成します。

    ``` sql
    create database dxp_db character set utf8;
    ```

    データベースセッションを終了します。

    ``` bash
    quit
    ```

    ターミナルセッションを終了します。

    ``` bash
    exit
    ```

詳細については、「 [クラスタノードのデータベース構成](./database-configuration-for-cluster-nodes.md) 」を参照してください。

データベースサーバーはDXPの準備ができています。

## 検索エンジンを準備する

DXPクラスターには、すべてのアプリサーバーノードからアクセス可能な検索エンジン（個別のプロセスとして実行）が必要です。 詳細については、「 [検索エンジン](../../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md) インストール」を参照してください。

[Elasticsearch Dockerイメージ](https://hub.docker.com/_/elasticsearch)基づいて検索エンジンコンテナを作成します。

1.  DXPバージョンと互換性のあるElasticsearch Dockerイメージをダウンロードします。

    ``` bash
    docker pull elasticsearch:6.8.7
    ```

2.  Elasticsearchコンテナを起動し、コンテナのデータフォルダーをホストマシンフォルダーにマウントします。

    Elasticsearchインデックスを保持するフォルダーを作成します。

    ``` bash
    mkdir -p ~/elasticsearch/es_data_volume
    ```

    Elasticsearchコンテナを実行します。

    ``` bash
    docker run -it -p 9200:9200 -p 9300:9300  -e cluster.name=LiferayElasticsearchCluster -e ES_JAVA_OPTS="-Xms512m -Xmx512m" --network my-bridge --name elasticsearch -v ~/elasticsearch/es_data_volume:/usr/share/elasticsearch/data elasticsearch:6.8.7
    ```

    `docker run` コマンドは、ポート `9200` およびポート `9300`で公開するElasticsearch Dockerコンテナを作成し、 `LiferayElasticsearchCluster`と呼ばれるElasticsearchクラスターを持ちます。 `512m` の初期メモリがサーバーに割り当てられます。 `-v ...` オプションは、コンテナのデータフォルダーを、作成したホストマシンフォルダーにマップします。

3.  コンテナのシェルに、必要なElasticsearchプラグインをインストールします。

    Elasticsearchコンテナターミナルに接続します。

    ``` bash
    docker exec -it elasticsearch bash
    ```

    `/elasticsearch` ディレクトリに移動します。

    ``` bash
    cd /usr/share/elasticsearch
    ```

    必要なプラグインをインストールします。

    ``` bash
    ./bin/elasticsearch-plugin install analysis-icu
    ```

    ``` bash
    ./bin/elasticsearch-plugin install analysis-kuromoji
    ```

    ``` bash
    ./bin/elasticsearch-plugin install analysis-smartcn
    ```

    ``` bash
    ./bin/elasticsearch-plugin install analysis-stempel
    ```


    Finish your terminal session.

    ``` bash
    exit
    ```

検索エンジンは、DXPの検索インデックスを保存および取得する準備ができました。

## ファイルストアを準備する

DXPクラスターには、すべてのアプリサーバーノードからアクセスできるファイルストアが必要です。 便宜上、この例では、DXPデータベースで構成された [DBStore File Store](../../../system-administration/file-storage/other-file-store-types/dbstore.md) 使用しています。 アプリサーバーノードによって構成されます（次で説明します）。 他のファイルストアタイプについては、 [ファイルストア](../../../system-administration/file-storage/configuring-file-storage.md) を参照してください。

## DXPサーバークラスターの構成

クラスターノードとして追加する各DXPアプリサーバーは、クラスター用に構成し、作成したサーバーに接続するように構成する必要があります。

設定する項目の概要は次のとおりです。

| 項目        | 構成方法                                                                                  |
|:--------- |:------------------------------------------------------------------------------------- |
| 検索エンジン接続  | [設定ファイル](../../../system-administration/system-settings/using-configuration-files.md) |
| データソース接続  | `portal-ext.properties` ファイル                                                          |
| ファイルストア接続 | `portal-ext.properties` ファイル。 ファイルストアの種類によっては、構成ファイルも必要です。                            |
| クラスターリンク  | `portal-ext.properties` ファイル                                                          |

[ポータルプロパティ](../../reference/portal-properties.md) は、Docker環境変数または `portal-ext.properties` ファイルを使用して指定できます。 この例ではいくつかのプロパティを使用するため、プロパティファイルが使用されます。

ノード構成を整理する1つの方法は、各ノードのフォルダーを作成することです。

``` bash
mkdir dxp-1 dxp-2
```

DXPサーバーノードを構成する準備が整いました。

### 検索エンジン接続を構成する

1.  検索エンジン [構成ファイル](../../../system-administration/system-settings/using-configuration-files.md)作成します。

    `/osgi/config/` フォルダーを作成します。

    ``` bash
    mkdir -p dxp-1/files/osgi/configs
    ```

    検索エンジン構成ファイルを作成します。

    ``` bash
    touch dxp-1/files/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config
    ```

2.  次のElasticsearchサーバー接続設定を `.config` ファイルに追加します。

    ``` properties
    operationMode="REMOTE"
    transportAddresses="elasticsearch:9300"
    clusterName="LiferayElasticsearchCluster"
    ```

3.  構成を他のノードにコピーします。

    2番目のDXPノード用に `/osgi/config/` ディレクトリを作成します。

    ``` bash
    mkdir -p dxp-2/files/osgi/configs
    ```

    検索構成を2番目のDXPノードにコピーします。

    ``` bash
    cp dxp-1/files/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config dxp-2/files/osgi/configs/
    ```

詳細については、「 [構成ファイルの使用](../../../system-administration/system-settings/using-configuration-files.md) 」を参照してください。

### クラスタリンクとその他のサーバー接続を構成する

各DXPサーバーで、 [ポータルプロパティ](../../reference/portal-properties.md) を使用して、クラスターリンクを有効にし、データソースとファイルストアとの接続を構成します。

1.  各ノードに `portal-ext.properties` ファイルを作成します。

    ``` bash
    touch dxp-1/files/portal-ext.properties
    ```

    ``` bash
    touch dxp-2/files/portal-ext.properties
    ```

2.  以下の構成を `dxp-1/files/portal-ext.properties` ファイルに追加します。

    ``` properties
    jdbc.default.jndi.name=

    jdbc.default.driverClassName=org.mariadb.jdbc.Driver
    jdbc.default.url=jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=root
    jdbc.default.password=my-secret-pw

    dl.store.impl=com.liferay.portal.store.db.DBStore

    cluster.link.enabled=true

    cluster.link.autodetect.address=some-mariadb:3306

    cluster.link.channel.logic.name.control=control-channel-logic-name-1
    cluster.link.channel.logic.name.transport.0=transport-channel-logic-name-1

    web.server.display.node=true
    ```

3.  以下の構成を `dxp-2/files/portal-ext.properties` ファイルに追加します。

    ``` properties
    jdbc.default.jndi.name=

    jdbc.default.driverClassName=org.mariadb.jdbc.Driver
    jdbc.default.url=jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=root
    jdbc.default.password=my-secret-pw

    dl.store.impl=com.liferay.portal.store.db.DBStore

    cluster.link.enabled=true

    cluster.link.autodetect.address=some-mariadb:3306

    cluster.link.channel.logic.name.control=control-channel-logic-name-2
    cluster.link.channel.logic.name.transport.0=transport-channel-logic-name-2

    web.server.display.node=true
    ```

サーバー接続の構成、クラスターリンクの有効化と構成に必要なプロパティを構成しました。 Cluster Linkはノード間の通信を可能にし、ノード間でキャッシュを複製します。

以下の表は、共通および固有のプロパティ設定を示しています。

#### 共通のプロパティ

これらのプロパティ設定は、各ノードに共通です。

| プロパティ設定                                                                                                                             | 説明                                |
|:----------------------------------------------------------------------------------------------------------------------------------- |:--------------------------------- |
| `cluster.link.autodetect.address=some-mariadb:3306`                                                                                 | クラスターノードアドレスを取得するためにpingする既知のアドレス |
| `cluster.link.enabled=true`                                                                                                         | クラスタリンクを有効にする                     |
| `dl.store.impl=com.liferay.portal.store.db.DBStore`                                                                                 | ファイルストア（ドキュメントライブラリストア）クラス        |
| `jdbc.default.jndi.name=`                                                                                                           | データソースJNDI名                       |
| `jdbc.default.driverClassName=org.mariadb.jdbc.Driver`                                                                              | データベースドライバークラス                    |
| `jdbc.default.url=jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false` | データソースURL                         |
| `jdbc.default.username=root`                                                                                                        | データベース管理者のユーザー名                   |
| `jdbc.default.password=my-secret-pw`                                                                                                | データベース管理者ユーザーのパスワード               |
| `web.server.display.node=true`                                                                                                      | サーバーアドレスとウェブサーバーポートを表示します         |

#### 特徴的な特性

次のポートプロパティとクラスターロジック名のプロパティは、各ノードを区別します。

| プロパティ                                         | dxp-1                          | dxp-2                          |
|:--------------------------------------------- |:------------------------------ |:------------------------------ |
| `cluster.link.channel.logic.name.control`     | control-channel-logic-name-1   | control-channel-logic-name-2   |
| `cluster.link.channel.logic.name.transport.0` | transport-channel-logic-name-1 | transport-channel-logic-name-2 |

クラスタ構成の詳細については、 [Cluster Link](./configuring-cluster-link.md) を参照してください。

## クラスターの前面にWebサーバーを配置する

最後に、Webサーバーはリバースプロキシおよびロードバランサーとして使用されます。 すべてのリクエストを受け入れ、最も利用可能なアプリケーションサーバーにリダイレクトします。 Webサーバーは、アプリケーションサーバーをユーザーに対して透過的にします。

この例では、アプリケーションサーバークラスターの前に、要求数によって負荷を分散する [Apache Webサーバー](http://httpd.apache.org/) ています。 しかし、希望するWebサーバーをDXPの前に置くことができます。

1.  [Apache Web Serverイメージ](https://hub.docker.com/_/httpd)ダウンロードします。

    ``` bash
    docker pull httpd
    ```

2.  Webサーバーのコンテナを作成します。

    ``` bash
    docker run -it --name httpd -p 80:80 --network my-bridge httpd
    ```

3.  構成ファイルをローカルにコピーして編集します。

    ``` bash
    docker cp httpd:/usr/local/apache2/conf/httpd.conf .
    ```

4.  `httpd.conf` ファイルで、これらのモジュールをロードするための行を追加またはコメント解除します。
   
        LoadModule xml2enc_module modules/mod_xml2enc.so
        LoadModule proxy_html_module modules/mod_proxy_html.so
        LoadModule proxy_module modules/mod_proxy.so
        LoadModule proxy_http_module modules/mod_proxy_http.so
        LoadModule proxy_ajp_module modules/mod_proxy_ajp.so
        LoadModule proxy_balancer_module modules/mod_proxy_balancer.so
        LoadModule slotmem_shm_module modules/mod_slotmem_shm.so
        LoadModule ssl_module modules/mod_ssl.so
        LoadModule lbmethod_byrequests_module modules/mod_lbmethod_byrequests.so
        LoadModule lbmethod_bytraffic_module modules/mod_lbmethod_bytraffic.so

5.  `httpd.conf` ファイルの最後に [`VirtualHost` 要素](https://httpd.apache.org/docs/2.4/vhosts/) を追加して、リクエストプロキシと負荷分散を構成します（Apacheの [`byrequests` メソッド](https://httpd.apache.org/docs/2.4/mod/mod_lbmethod_byrequests.html)）。<VirtualHost *:80> ProxyRequests off ProxyPass / balancer://liferaycluster/ ProxyPassReverse / balancer://liferaycluster/ # Set the header for the http protocol RequestHeader set X-Forwarded-Proto "http" # Serve /excluded from the local httpd data ProxyPass /excluded ! # Preserve the host when invoking tomcat ProxyPreserveHost on Header add Set-Cookie "ROUTEID=.%{BALANCER_WORKER_ROUTE}e; path=/" env=BALANCER_ROUTE_CHANGED <Proxy balancer:> BalancerMember "http://dxp-1:8080" route=liferay1 BalancerMember "http://dxp-2:8080" route=liferay2 ProxySet lbmethod=byrequests ProxySet stickysession=ROUTEID </Proxy> </VirtualHost>

6.  編集した `httpd.conf` ファイルを `httpd` コンテナにコピーします。

    ``` bash
    docker cp httpd.conf httpd:/usr/local/apache2/conf
    ```

7.  `httpd` コンテナを再起動して、変更を適用します。

    ``` bash
    docker stop httpd
    docker start -i httpd
    ```

Webサーバーは、要求をプロキシし、DXPアプリケーションサーバー間で要求の負荷を分散する準備ができています。 DXPクラスターの動作を確認する時が来ました。

## DXPクラスタノードを起動します。

DXPクラスターノードコンテナには次の構成があります。

| 設定               | dxp-1                       | dxp-2                       |
|:---------------- |:--------------------------- |:--------------------------- |
| AJPポートマッピング      | `8009:8009`                 | `9009:8009`                 |
| HTTPポートマッピング     | `8080:8080`                 | `9080:8080`                 |
| OSGiコンテナポートマッピング | `11311:11311`               | `11312:11311`               |
| ボリュームバインドマウント    | `$(pwd)/dxp-1:/mnt/liferay` | `$(pwd)/dxp-2:/mnt/liferay` |

ホストポートはコンテナポートにマッピングされています。 固有のホストポートは、ホストでの衝突を防ぎます。 コンテナポートは、各コンテナ内で一意である必要があるだけなので、各ノードで同じにすることができます（たとえば、各コンテナは、WebサーバーのHTTPポートとして `8080` を使用します）。 サンプルのプロキシサーバーとロードバランサは、各コンテナのHTTPポートを介してリクエストをコンテナに転送することに注意してください。 以下は、 `httpd.conf` ファイルからのプロキシ構成の抜粋です。

``` 
  ...
  <Proxy balancer://liferaycluster>
    BalancerMember "http://dxp-1:8080" route=liferay1
    BalancerMember "http://dxp-2:8080" route=liferay2
    ...
  </Proxy>
  ...
```

上記の表で、OSGiコンテナのポートマッピングは、各コンテナでGogoシェルを使用するためのものです。 ボリュームバインドマウントは、DXPコンテナの構成フェーズを利用して、 `portal-ext.properties` ファイルをDXPサーバーの [Liferay Home](../../reference/liferay-home.md)にコピーします。

DXPクラスターノードを起動します。

1.  開始 `dxp-1`：

    ``` bash
    docker run -it --name dxp-1 --network my-bridge -p 8009:8009 -p 8080:8080 -p 11311:11311 -v $(pwd)/dxp-1:/mnt/liferay liferay/portal:7.3.1-ga2
    ```

    DXPの起動が完了すると、次のようなJGroupsクラスターメッセージがコンソールに出力されます。

    ``` bash
    ...
    INFO  [SCR Component Actor][JGroupsClusterChannelFactory:173] Autodetecting JGroups outgoing IP address and interface for some-mariadb:3306
    INFO  [SCR Component Actor][JGroupsClusterChannelFactory:210] Setting JGroups outgoing IP address to 172.18.0.4 and interface to eth0

    -------------------------------------------------------------------
    GMS: address=control-channel-logic-name-1, cluster=liferay-channel-control, physical address=172.18.0.4:47533
    -------------------------------------------------------------------
    INFO  [SCR Component Actor][JGroupsReceiver:93] Accepted view [control-channel-logic-name-1|0] (1)[control-channel-logic-name-1]
    INFO  [SCR Component Actor][JGroupsClusterChannel:110] Create a new JGroups channel {channelName: liferay-channel-control, localAddress: control-channel-logic-name-1, ...
    ...
    -------------------------------------------------------------------
    GMS: address=transport-channel-logic-name-1, cluster=liferay-channel-transport-0, physical address=172.18.0.4:53231
    -------------------------------------------------------------------
    ...
    INFO  [SCR Component Actor][JGroupsReceiver:93] Accepted view [transport-channel-logic-name-1|0] (1) [transport-channel-logic-name-1]
    INFO  [SCR Component Actor][JGroupsClusterChannel:110] Create a new JGroups channel {channelName: liferay-channel-transport-0, localAddress: transport-channel-logic-name-1,...
    ...
    ```

    上記のメッセージは、次のことを示しています。

      - JGroupsは `dxp-1`のIPアドレスを `"172.18.0.4`として自動検出し` 。</li>
<li>JGroupsは <code>dxp-1`の制御チャネルを作成し、それをJGroupsビューに受け入れました。
      - JGroupsは `dxp-1`のトランスポートチャネルを作成し、それをJGroupsビュー（クラスター）に受け入れました。

2.  開始 `dxp-2`：

    ``` bash
    docker run -it --name dxp-2 --network my-bridge -p 9009:8009 -p 9080:8080 -p 11312:11311 -v $(pwd)/dxp-2:/mnt/liferay liferay/portal:7.3.1-ga2
    ```

    `dxp-2` ノードが起動すると、 `dxp-1` は次のようなクラスターメッセージをコンソールに出力します。

    ``` bash
    INFO  [jgroups-42,liferay-channel-control,control-channel-logic-name-1][JGroupsReceiver:93] Accepted view [control-channel-logic-name-1|1] (2) [control-channel-logic-name-1, control-channel-logic-name-2]
    INFO  [jgroups-41,liferay-channel-transport-0,transport-channel-logic-name-1][JGroupsReceiver:93] Accepted view [transport-channel-logic-name-1|1] (2) [transport-channel-logic-name-1, transport-channel-logic-name-2]
    INFO  [default-2][ClusterExecutorImpl:544] Updated cluster node {bindInetAddress=/172.18.0.5, clusterNodeId=e6ee6b63-4625-1996-0bd6-dd2edf106d95, portalInetSocketAddress=/127.0.0.1:8080, portalProtocol=http}
    ```

    これらのメッセージは、 `dxp-2`のIPアドレス（`172.18.0.5`）と、JGroupsが `dxp-2`の制御チャネルとトランスポートチャネルを作成し、チャネルをJGroupsビューに受け入れたことを示しています。

DXPは<http://localhost>にあります。 Webサーバーが要求をDXPサーバークラスターに送信します。

### クラスターをテストする

クラスターをテストして、ノードに同じコンテンツの変更が表示され、少なくとも1つのクラスターノードが実行されている場合にDXPが引き続き使用できることを確認します。

<http://localhost> にコンテンツ (言語選択ウィジェットなど) をサイトに追加することから始めます。

ノードのアドレスとポートに注意してください(`ノード: [adress]:[port]`)。 ノードはDockerコンテナで実行されているため、IPアドレスの代わりにコンテナIDが表示されます。 例として下図を参照してください。

![DXPクラスタノードの上部に言語セレクタウィジェットがあり、下部にコンテナIDとポートが表示されます。](./example-creating-a-simple-dxp-cluster/images/02.png)

`docker container ls -a` コマンドを使用して、コンテナIDをDXPコンテナと一致させることができます。

``` bash
$ docker container ls -a | grep dxp-1
0335b3568fa1        liferay/portal:7.3.1-ga2       "/bin/sh -c /usr/loc…"   About an hour ago   Up About an hour (healthy)   8000/tcp, 8009/tcp, 11311/tcp, 0.0.0.0:8080->8080/tcp   dxp-1
$ docker container ls -a | grep dxp-2
aa547271b4d3        liferay/portal:7.3.1-ga2       "/bin/sh -c /usr/loc…"   43 minutes ago      Up 43 minutes (healthy)      8000/tcp, 8009/tcp, 11311/tcp, 0.0.0.0:9080->8080/tcp   dxp-2
```

そのDXPコンテナを停止して、サーバーのフェイルオーバーをテストします。 たとえば、ブラウザが `dxp-1` コンテナを使用している場合は、そのコンテナを停止します。

``` bash
docker stop dxp-1
```

ブラウザを更新して、残りのDXPサーバーがリクエストを処理し、以前に追加したコンテンツを表示していることを確認します。

![コンテンツはクラスターノード間で同期されます。](./example-creating-a-simple-dxp-cluster/images/03.png)

作業中の DXPクラスタを作成しました\!

```{tip}
When you're ready to stop containers, use the `docker container stop [container ID]` command like you did to stop the DXP container above. Similarly, use the `docker container start -i [container ID]` command to restart the containers.
```

## 次のステップ

DXPクラスターの [データベース](./database-configuration-for-cluster-nodes.md) を調整します。

## 追加情報

  - [クラスタノードのデータベース構成](./database-configuration-for-cluster-nodes.md)
  - [クラスタリンクの構成](./configuring-cluster-link.md)
