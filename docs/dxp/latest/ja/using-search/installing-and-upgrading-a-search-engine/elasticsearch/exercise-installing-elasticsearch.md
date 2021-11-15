# 演習：Elasticsearchのインストール

> 利用可能：Liferay DXP 7.3、Liferay Portal CE 7.3 GA4以降

ここでは、ローカルマシンでのセットアップ例を通して、ElasticsearchとLiferay DXP 7.3の間で[Rest Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html)接続がどのように構成されているかを確認できます。  この例では、2つのDockerコンテナを使用しています。1つはElasticsearchコンテナ、もう1つはLiferay DXPコンテナです。 より概念的で本番環境に似た情報については、[Elasticsearchのインストール](./getting-started-with-elasticsearch.md)を参照してください。

Elasticsearch接続で認証と暗号化を有効にするには、[Securing Elasticsearch](./securing-elasticsearch.md)をお読みください。

## Dockerコンテナにバインドマウントするためのローカルフォルダを作成する

プラグインと構成ファイルを提供するために、ElasticsearchコンテナおよびDXPコンテナのシステムフォルダにバインドマウントできるローカルフォルダ構造を作成します。

``` bash
mkdir -p test-es-install/dxp/files/osgi/configs && mkdir -p test-es-install/elasticsearch && cd test-es-install
```

```{tip}
The `cd test-es-install` command at the end puts you in the `test-es-install` folder. Make sure you run the remaining commands for both Elasticsearch and Liferay DXP from this folder.
```

## Elasticsearchのインストール

1.  `elasticsearch790`という名前のElasticsearch `7.9.0`コンテナを設定して起動します。

    ``` bash
    docker run -it --name elasticsearch790 -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "node.name=es-node1" -v $(pwd)/elasticsearch:/usr/share/elasticsearch/data docker.elastic.co/elasticsearch/elasticsearch:7.9.0
    ```

2.  必要なElasticsearchプラグインをインストールします。 `docker exec -it`を使用して、インタラクティブなbashシェルにアクセスします。

    ``` bash
    docker exec -it elasticsearch790 bash -c '/usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-icu && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-kuromoji && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-smartcn && /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-stempel'
    ```

3.  Elasticsearchコンテナを再起動して、プラグインを登録します。 CTRL+Cでコンテナを停止した後、以下を実行して再度起動します。

    ``` bash
    docker start -i elasticsearch790
    ```

4.  実行中のElasticsearchコンテナのIPv4アドレスを取得します。

    ``` bash
    docker network inspect bridge
    ```

    この例では、`172.17.0.2`です。 システムが異なるIPアドレスを提供している場合は、Liferay DXPを実行するときに`docker run --add-host elasticsearch790:[IP]...`コマンドで使用する必要があります。

    ``` bash
    "Containers": {
                "2d4614fdcce2159322fa7922bfc5f866b79bd7f609a65cc888f9a260f80731f4": {
                    "Name": "elasticsearch790",
                    "EndpointID": "e89c3d0a87cc528753470eb359cee3b85fea9f9a5df3b249d54d203741a650a8",
                    "MacAddress": "02:42:ac:11:00:02",
                    "IPv4Address": "172.17.0.2/16",
                    "IPv6Address": ""
                }
            },
    ```

## Liferay DXPのインストール

Liferay DXPがElasticsearchに接続するために必要なプロパティを指定してから、DXPコンテナを実行します。

1.  まず、以下を実行してElasticsearch 7構成ファイルにデータを入力します。

    ``` bash
    cat <<EOT >> dxp/files/osgi/configs/com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config

    operationMode="REMOTE"
    productionModeEnabled=B"true"
    networkHostAddresses="http://elasticsearch790:9200"
    EOT
    ```

2.  構成ファイルを配置したら、次のコマンドでDXPコンテナを起動します。

    ``` bash
    docker run -it --name dxp73  --add-host elasticsearch771:172.17.0.2 -p 8080:8080 -v $(pwd)/dxp:/mnt/liferay [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

3.  **チェックポイント：** [コントロールパネル] → [設定] → [検索機能]でElasticsearch接続が有効になっていることを確認します。

    ![有効な接続が[検索機能]管理パネルに表示されます。](./exercise-installing-elasticsearch/images/01.png)

検索インデックスとスペルチェックインデックスを再作成します。 どちらの再インデックス操作も、[コントロールパネル] → [設定] → [検索機能]の[アクションをインデックスする]タブから実行します。

## 追加情報

  - [Securing Elasticsearch](./securing-elasticsearch.md)
  - Liferay Enterprise Search（近日公開）
  - [Search Pages](../../search-pages-and-widgets/working-with-search-pages/search-pages.md)
  - Administering and Tuning Search（近日公開）
  - [Elasticsearch Connector Settings](./elasticsearch-connector-settings.md)
