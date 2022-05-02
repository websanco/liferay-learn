# 例：単純なDXPクラスターの作成

DXPクラスタリングを学習する簡単な方法は、 [Dockerコンテナ](https://docs.docker.com/get-started/overview/) を使用して、単一のマシンに2ノードのDXPクラスタ環境をセットアップすることです。 ここでは、データベース、検索エンジン、およびファイルストア用のサーバーコンテナとともに、2つのDXPサーバーコンテナを作成します。

作成するサーバーコンテナは次のとおりです。

| サーバータイプ | 実装            | コンテナ名           |
|:------- |:------------- |:--------------- |
| データベース  | MariaDB       | `some-mariadb`  |
| ファイルストア | DBStore       | `some-mariadb`  |
| 検索エンジン  | Elasticsearch | `elasticsearch` |
| DXPサーバー | Tomcat        | `dxp-1`         |
| DXPサーバー | Tomcat        | `dxp-2`         |

```{warning}
この例は学習を目的としたものであり、本番環境のユースケースには適していません。 本番環境では、DXPサーバーへのリクエストの負荷分散用にHTTPサーバーを含め、読み取り専用操作と読み取り/書き込み操作に別々のデータベースサーバーを使用し、データベースサーバー、ファイルストアサーバー、および検索エンジンサーバーのクラスタリングと負荷分散を検討する必要があります。 詳細は、 [高可用性のクラスタリング](../clustering-for-high-availability.md) のすべての記事をお読みください。
```


<!--
![DXP cluster environment.](./example-creating-a-simple-dxp-cluster/images/01.png)
Should we remove this diagram since it includes a load balancer? -->

主な手順は次のとおりです。

1. [データベースサーバーを起動する](#prepare-a-database-server)
1. [ファイルストアサーバーを起動する](#prepare-a-file-store-server)
1. [検索エンジンサーバーを起動する](#prepare-a-search-engine-server)
1. [各ノードの検索エンジンを設定する](#configure-the-search-engine-for-each-node)
1. [DXPクラスターを開始する](#start-the-dxp-cluster)
1. [DXPクラスターをテストする](#test-the-dxp-cluster)

<a name="start-a-database-server" />

## データベースサーバーを起動する

DXPクラスターには、すべてのDXPクラスターノードからアクセスできるデータソースが必要です。 データソースは、JNDIデータソースにするか、データベースサーバーまたはデータベースサーバークラスターへの直接接続にすることができます。 DXPバージョンがサポートするデータベースサーバーについては、 [互換性マトリックス](https://help.liferay.com/hc/ja/articles/360049238151) を参照してください。 詳細は、 [クラスタノードのデータベース構成](./database-configuration-for-cluster-nodes.md) を参照してください。

データベースサーバーとDXPデータベースを作成します。

1. MariaDB Dockerコンテナを起動します。

    ```bash
    docker run --name some-mariadb -e MYSQL **ROOT** PASSWORD=my-secret-pw -d mariadb:10.2
    ```

1. コンテナ上のシェルで、[DXPデータベースを作成](../../reference/database-configurations.md)します。

    データベースサーバーにサインインします。

    ```bash
    docker exec -it some-mariadb bash -c "/usr/bin/mysql -uroot -pmy-secret-pw"
    ```

    DXP用のデータベースを作成します。

    ```sql
    create database dxp_db character set utf8;
    ```

    データベースセッションを終了します。

    ```bash
    quit
    ```

    bashセッションを終了します。

    ```bash
    exit
    ```

データベースサーバーでDXP用の準備が整いました。

<a name="start-a-file-store-server" />

## ファイルストアサーバーを起動する

DXPクラスターには、すべてのDXPクラスターノードからアクセスできるファイルストアが必要です。 便宜上、この例では、DXPデータベースで構成された [DBStoreファイルストア](../../../system-administration/file-storage/other-file-store-types/dbstore.md)を使用しています。 この例ですでに起動しているデータベースサーバーには、ファイルストアが含まれています。 すべてのファイルストアタイプの設定については、[ファイルストレージの構成](../../../system-administration/file-storage/configuring-file-storage.md)を参照してください。

<a name="start-a-search-engine-server" />

## 検索エンジンサーバーを起動する

DXPクラスターには、すべてのDXPクラスターノードからアクセス可能な検索エンジン（個別のプロセスとして実行）が必要です。 詳細は、 [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md) を参照してください。

Elasticsearchサーバーを作成して設定します。

1. Elasticsearchサーバーのデータボリュームを保存するためのローカルフォルダを設定します。  例:

    ```bash
    mkdir -p elasticsearch/es **data** volume
    ```

1. `elasticsearch`という名前のElasticsearchコンテナを起動します。

    ```bash
    docker run -it --name elasticsearch -p 9200:9200 -p 9300:9300 -e cluster.name=LiferayElasticsearchCluster -e ES **JAVA** OPTS="-Xms512m -Xmx512m" -v $(pwd)/elasticsearch/es **data** volume:/usr/share/elasticsearch/data elasticsearch:6.8.7
    ```

    ```{note}
    コンテナが`max virtual memory areas vm.max_map_count [xxxxx] is too low, increase to at least [xxxxxx]`と報告してきたら、`sudo sysctl -w vm.max_map_count=[xxxxxx]`のようなコマンドを使って `vm.max_map_count`を十分な値に設定してください。 次に、コンテナを起動します。
    ```

1. 必要なElasticsearchプラグインをインストールします。

    ```bash
    docker exec -it elasticsearch bash -c '/usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-icu && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-kuromoji && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-smartcn && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-stempel'
    ```

検索エンジンは検索インデックスを管理する準備ができました。

<a name="configure-the-search-engine-server-for-each-node" />

## ノードごとに検索エンジンサーバーを設定する

各DXPノードにElasticsearchを設定するには、[設定ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)を使用します。

1. 設定ファイルの場所を作成します。

    ```bash
    mkdir -p dxp-1/files/osgi/configs dxp-2/files/osgi/configs
    ```

1. `dxp-1`サーバーノードのElasticsearchを設定します。

    ```bash
    cat <<EOT >> dxp-1/files/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config
    operationMode="REMOTE"
    transportAddresses="elasticsearch:9300"
    clusterName="LiferayElasticsearchCluster"
    EOT
    ```

1. `dxp-2`サーバーノードのElasticsearchを設定します。

    ```bash
    cat <<EOT >> dxp-2/files/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config
    operationMode="REMOTE"
    transportAddresses="elasticsearch:9300"
    clusterName="LiferayElasticsearchCluster"
    EOT
    ```

これらの設定ファイルを、DXPサーバーコンテナのバインドマウントを介してクラスターノードからアクセスできるようにします。

```{note}
後でDXPサーバーに使用される`docker run --add-host elasticsearch:[ip] ...`コマンドは、名前`elasticsearch`をElasticsearchサーバーのホストIPアドレスにマップする`/etc/hosts/`エントリを追加します。
```

<a name="start-the-dxp-cluster" />

## DXPクラスターを開始する

DXPクラスターノードコンテナには、次の固有の設定があります。

| 設定                        | dxp-1                          | dxp-2                          |
|:------------------------- |:------------------------------ |:------------------------------ |
| AJPポートマッピング               | `8009:8009`                    | `9009:8009`                    |
| HTTPポートマッピング              | `8080:8080`                    | `9080:8080`                    |
| OSGiコンテナポートマッピング          | `11311:11311`                  | `11312:11311`                  |
| バインドマウント                  | `$(pwd)/dxp-1:/mnt/liferay`    | `$(pwd)/dxp-2:/mnt/liferay`    |
| クラスターリンク制御チャネルのロジック名      | control-channel-logic-name-1   | control-channel-logic-name-2   |
| クラスターリンクトランスポートチャネルのロジック名 | transport-channel-logic-name-1 | transport-channel-logic-name-2 |

DXPコンテナを起動します。

1. [`docker network inspect bridge`](https://docs.docker.com/engine/reference/commandline/network_inspect/) コマンドを実行して、`elasticsearch`および`some-mariadb`コンテナのコンテナIPアドレスを取得します。 `bridge`ネットワークがデフォルトネットワークです。

    ```{important}
    次の`docker run`コマンドでは、`[IP address]`を`elasticsearch`および`some-mariadb`コンテナのIPアドレスに置き換えます。
    ```

1. `dxp-1`を開始します。

    読みやすいように拡張されたコマンド：

    ```bash
    docker run -it \
      --add-host elasticsearch:[IP address] \
      --add-host some-mariadb:[IP address] \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **JNDI** PERIOD_NAME="" \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **DRIVER** UPPERCASEC **LASS** UPPERCASEN_AME=org.mariadb.jdbc.Driver \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **URL="jdbc:mariadb://some-mariadb:3306/dxp** db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD_USERNAME=root \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD_PASSWORD=my-secret-pw \
      -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD_ENABLED=true \
      -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **CHANNEL** PERIOD **LOGIC** PERIOD **NAME** PERIOD_CONTROL=control-channel-logic-name-1 \
      -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **CHANNEL** PERIOD **LOGIC** PERIOD **NAME** PERIOD **TRANSPORT** PERIOD_NUMBER0=transport-channel-logic-name-1 \
      -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **AUTODETECT** PERIOD_ADDRESS=some-mariadb:3306 \
      -e LIFERAY **WEB** PERIOD **SERVER** PERIOD **DISPLAY** PERIOD_NODE=true \
      -e LIFERAY **DL** PERIOD **STORE** PERIOD_IMPL=com.liferay.portal.store.db.DBStore \
      --name dxp-1 \
      -p 11311:11311 \
      -p 8009:8009 \
      -p 8080:8080 \
      -v $(pwd)/dxp-1:/mnt/liferay \
      liferay/portal:7.3.2-ga3
    ```

    1行に凝縮されたコマンド：

    ```bash
    docker run -it --name dxp-1  --add-host elasticsearch:[IP address] --add-host some-mariadb:[IP address] -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **JNDI** PERIOD **NAME="" -e LIFERAY** JDBC **PERIOD** DEFAULT **PERIOD** DRIVER **UPPERCASEC** LASS **UPPERCASEN** AME=org.mariadb.jdbc.Driver -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **URL="jdbc:mariadb://some-mariadb:3306/dxp** db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **USERNAME=root -e LIFERAY** JDBC **PERIOD** DEFAULT **PERIOD** PASSWORD=my-secret-pw -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **ENABLED=true -e LIFERAY** CLUSTER **PERIOD** LINK **PERIOD** CHANNEL **PERIOD** LOGIC **PERIOD** NAME **PERIOD** CONTROL=control-channel-logic-name-1 -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **CHANNEL** PERIOD **LOGIC** PERIOD **NAME** PERIOD **TRANSPORT** PERIOD **NUMBER0=transport-channel-logic-name-1 -e LIFERAY** CLUSTER **PERIOD** LINK **PERIOD** AUTODETECT **PERIOD** ADDRESS=some-mariadb:3306 -e LIFERAY **WEB** PERIOD **SERVER** PERIOD **DISPLAY** PERIOD **NODE=true -e LIFERAY** DL **PERIOD** STORE **PERIOD** IMPL=com.liferay.portal.store.db.DBStore --name dxp-1 -p 11311:11311 -p 8009:8009 -p 8080:8080 -v $(pwd)/dxp-1:/mnt/liferay [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
    ```

1. `dxp-2`を開始します。

    読みやすいように拡張されたコマンド：

    ```bash
    docker run -it \
      --add-host elasticsearch:[IP address] \
      --add-host some-mariadb:[IP address] \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **JNDI** PERIOD_NAME="" \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **DRIVER** UPPERCASEC **LASS** UPPERCASEN_AME=org.mariadb.jdbc.Driver \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **URL="jdbc:mariadb://some-mariadb:3306/dxp** db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD_USERNAME=root \
      -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD_PASSWORD=my-secret-pw \
      -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD_ENABLED=true \
      -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **CHANNEL** PERIOD **LOGIC** PERIOD **NAME** PERIOD_CONTROL=control-channel-logic-name-2 \
      -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **CHANNEL** PERIOD **LOGIC** PERIOD **NAME** PERIOD **TRANSPORT** PERIOD_NUMBER0=transport-channel-logic-name-2 \
      -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **AUTODETECT** PERIOD_ADDRESS=some-mariadb:3306 \
      -e LIFERAY **WEB** PERIOD **SERVER** PERIOD **DISPLAY** PERIOD_NODE=true \
      -e LIFERAY **DL** PERIOD **STORE** PERIOD_IMPL=com.liferay.portal.store.db.DBStore \
      --name dxp-2 \
      -p 11312:11311 \
      -p 9009:8009 \
      -p 9080:8080 \
      -v $(pwd)/dxp-2:/mnt/liferay \
      liferay/portal:7.3.2-ga3
    ```

    1行に凝縮されたコマンド：

    ```bash
    docker run -it --add-host elasticsearch:[IP address] --add-host some-mariadb:[IP address] -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **JNDI** PERIOD **NAME="" -e LIFERAY** JDBC **PERIOD** DEFAULT **PERIOD** DRIVER **UPPERCASEC** LASS **UPPERCASEN** AME=org.mariadb.jdbc.Driver -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **URL="jdbc:mariadb://some-mariadb:3306/dxp** db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" -e LIFERAY **JDBC** PERIOD **DEFAULT** PERIOD **USERNAME=root -e LIFERAY** JDBC **PERIOD** DEFAULT **PERIOD** PASSWORD=my-secret-pw -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **ENABLED=true -e LIFERAY** CLUSTER **PERIOD** LINK **PERIOD** CHANNEL **PERIOD** LOGIC **PERIOD** NAME **PERIOD** CONTROL=control-channel-logic-name-2 -e LIFERAY **CLUSTER** PERIOD **LINK** PERIOD **CHANNEL** PERIOD **LOGIC** PERIOD **NAME** PERIOD **TRANSPORT** PERIOD **NUMBER0=transport-channel-logic-name-2 -e LIFERAY** CLUSTER **PERIOD** LINK **PERIOD** AUTODETECT **PERIOD** ADDRESS=some-mariadb:3306 -e LIFERAY **WEB** PERIOD **SERVER** PERIOD **DISPLAY** PERIOD **NODE=true -e LIFERAY** DL **PERIOD** STORE **PERIOD** IMPL=com.liferay.portal.store.db.DBStore --name dxp-2 -p 11312:11311 -p 9009:8009 -p 9080:8080 -v $(pwd)/dxp-2:/mnt/liferay [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
    ```

`--add-host ［domain］:［IP address］`オプションは、ドメイン名をマップする`/etc/hosts` [ファイルエントリ](https://docs.docker.com/engine/reference/run/#managing-etchosts) をIPアドレスに追加します。 これにより、設定（環境変数、ポータルプロパティ、 `.config`ファイルなど）がドメイン名でサーバーを参照できるようになります。

`-e ［variable］=［value］`オプションは、DXPコンテナ環境変数を設定します。 詳細は、 [付録A：環境設定](#appendix-a-environment-settings) を参照してください。

### DXPノードにアクセスする

DXPクラスターノードは、次のURLで入手できます。

* DXP-1：http://localhost:8080
* DXP-2：http://localhost:9080

次の図は、クラスターノードのホームページを示しています。

![DXPクラスターノード。](./example-creating-a-simple-dxp-cluster/images/02.png)

各ノードのコンテナIDとポート（`ノード：  ［id］:［port］`）は、各ページの下部に表示されます。 `LIFERAY_WEB_PERIOD_SERVER_PERIOD_DISPLAY_PERIOD_NODE=true`環境設定により、この表示機能が有効になりました。 [`docker container ls`](https://docs.docker.com/engine/reference/commandline/container_ls/) コマンドを使用してコンテナのIDを見つけることができます。

### コンテンツを検索エンジンにインデックス付けする

DXPコンテンツのインデックス作成を開始します。

1. [**コントロールパネル] → [設定] → [検索機能**] に移動します。

1. ［アクションをインデックスする］タブで、次のオプションをクリックします。

    ****Reindex all search indexes**
    ****Reindex all spell check indexes**

コンテンツは検索エンジンにインデックス付けされます。 詳細は、 [検索の概要](../../../using-search/getting-started/search-overview.md) を参照してください。

<a name="test-the-dxp-cluster" />

## DXPクラスターをテストする

ノード間のデータ同期をテストします。

1. クラスタノードの1つにコンテンツを追加します。

    たとえば、 **New Stuff** という名前の新しいウィジェットページを追加し、それに言語セレクタウィジェットを追加します。

1. 他のクラスターノードのUIを更新します。

両方のノードに同じ新しいコンテンツが表示されます。

![コンテンツはクラスターノード間で同期されます。](./example-creating-a-simple-dxp-cluster/images/03.png)

作業中の DXPクラスタを作成しました!

<a name="whats-next" />

## 次のステップ

DXPクラスター用に[データベース](./database-configuration-for-cluster-nodes.md)を設定します。

<a name="appendix-a-environment-settings" />

## 付録A：環境設定

サンプルのDXPサーバーコンテナは次の設定を使用しています。

| 設定                                                                                                                                                                                                    | 説明                                |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |:--------------------------------- |
| LIFERAY\ **JDBC\** PERIOD\ **DEFAULT\** PERIOD\ **JNDI\** PERIOD\_NAME=                                                                                                                             | データソースJNDI名                       |
| LIFERAY\ **JDBC\** PERIOD\ **DEFAULT\** PERIOD\ **DRIVER\** UPPERCASEC\ **LASS\\<br>\** UPPERCASEN\_AME=\\<br>org.mariadb.jdbc.Driver                                               | データベースドライバークラス                    |
| LIFERAY\ **JDBC\** PERIOD\ **DEFAULT\** PERIOD\ **URL=\\<br>jdbc:mariadb://some-mariadb:3306/dxp\** db?\\<br>useUnicode=true\&characterEncoding=UTF-8\&useFastDateParsing=false     | データソースURL                         |
| LIFERAY\ **JDBC\** PERIOD\ **DEFAULT\** PERIOD\_USERNAME=\\<br>root                                                                                                                         | データベース管理者のユーザー名                   |
| LIFERAY\ **JDBC\** PERIOD\ **DEFAULT\** PERIOD\_PASSWORD=\\<br>my-secret-pw                                                                                                                 | データベース管理者ユーザーのパスワード               |
| LIFERAY\ **CLUSTER\** PERIOD\ **LINK\** PERIOD\_ENABLED=\\<br>true                                                                                                                          | クラスタリンクを有効にする                     |
| LIFERAY\ **CLUSTER\** PERIOD\ **LINK\** PERIOD\ **CHANNEL\** PERIOD\ **LOGIC\** PERIOD\ **NAME\\<br>\** PERIOD\_CONTROL=\\<br>control-channel-logic-name-2                          | クラスターノードの一意のコントロールチャネル名           |
| LIFERAY\ **CLUSTER\** PERIOD\ **LINK\** PERIOD\ **CHANNEL\** PERIOD\ **LOGIC\** PERIOD\ **NAME\\<br>\** PERIOD\ **TRANSPORT\** PERIOD\_NUMBER0=\\<br>transport-channel-logic-name-2 | クラスターノードの一意のトランスポートチャネル名          |
| LIFERAY\ **CLUSTER\** PERIOD\ **LINK\** PERIOD\ **AUTODETECT\** PERIOD\_ADDRESS=\\<br>some-mariadb:3306                                                                                     | クラスターノードアドレスを取得するためにpingする既知のアドレス |
| LIFERAY\ **WEB\** PERIOD\ **SERVER\** PERIOD\ **DISPLAY\** PERIOD\_NODE=\\<br>true                                                                                                          | サーバーアドレスとウェブサーバーポートを表示します         |
| LIFERAY\ **DL\** PERIOD\ **STORE\** PERIOD\_IMPL=\\<br>com.liferay.portal.store.db.DBStore                                                                                                  | ファイルストア（ドキュメントライブラリストア）クラス        |

詳細は、Env/ [ポータルプロパティ](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html) の定義を参照してください。

<a name="additional-information" />

## 追加情報

* [クラスタノードのデータベース構成](./database-configuration-for-cluster-nodes.md)
* [クラスタリンクの構成](./configuring-cluster-link.md)
