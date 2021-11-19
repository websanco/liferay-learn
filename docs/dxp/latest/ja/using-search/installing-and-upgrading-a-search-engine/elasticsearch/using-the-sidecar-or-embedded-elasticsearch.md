# サイドカーまたはEmbedded モードのElasticsearchの使用

ZIPファイルまたはDockerイメージを介してインストールされたLiferay Tomcatバンドルには、Liferayで始まるElasticsearchノードが含まれています。 Liferay 7.3のノードはサイドカーサーバーとして別のJVMで実行され、Liferay 7.2のノードはLiferayサーバーに組み込まれています。

Elasticsearchサーバーには、次のURLからアクセスできます。

  - Liferay 7.3の場合は<http://localhost:9201>
  - Liferay 7.2の場合は<http://localhost:9200>

サイドカーサーバーの出力例は次のとおりです。

``` json
{
  "name" : "liferay",
  "cluster_name" : "LiferayElasticsearchCluster",
  "cluster_uuid" : "DGRDj1_DS7Km2Y_kaaNqxg",
  "version" : {
    "number" : "7.9.0",
    "build_flavor" : "unknown",
    "build_type" : "unknown",
    "build_hash" : "a479a2a7fce0389512d6a9361301708b92dff667",
    "build_date" : "2020-08-11T21:36:48.204330Z",
    "build_snapshot" : false,
    "lucene_version" : "8.6.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

バンドルされているElasticsearchサーバーは開発とテストには便利ですが、どちらも本番環境には適していません。

```{note}
While it's not a supported production configuration, installing Kibana to monitor the bundled Elasticsearch server is useful during development and testing. Just be aware that you must install the [OSS only Kibana build](https://www.elastic.co/downloads/kibana-oss).
```

HSQLのような組み込みデータベースを本番環境で実行したり、バンドルされたElasticsearchサーバーを本番環境で実行したりしないでください。 代わりに、Elasticsearchをスタンドアロンサーバーまたはサーバーノードのクラスターとしてリモートモードで実行します。

```{important}
Synonym Sets and Result Rankings are applications that use the search index for primary data storage. No data is stored in the Liferay database. Therefore, if you have Synonym Sets or Result Rankings configured while using the sidecar or embedded Elasticsearch, switching to a remote Elasticsearch server and reindexing does `not` restore those configurations. Instead you must manually bring the Synonym Sets and Result Rankings into the remote Elasticsearch cluster. See the [Upgrade Guide](../elasticsearch.html) for details on using Elastic's [Snapshot and Restore](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshot-restore.html) feature to preserve these indexes.
```

## バンドルされているElasticsearchサーバーのユースケース

デフォルトのElasticsearchサーバー（サイドカーおよびEmbedded）の一般的な使用法は次のとおりです。

  - カスタムの[検索とインデックスコード](../../developer-guide.html)のテスト
  - Kibanaを介してElasticsearchで直接クエリを実行して検索クエリを開発する
  - [検索の調整](../../search_administration_and_tuning.rst)機能のテスト
  - [検索ウィジェット](../../search_pages_and_widgets.rst)の調査と設定

## アプリサーバーの違い

ElasticsearchサイドカーサーバーはLiferay DXP 7.3およびLiferay Portal CE 7.3 GA4+ TomcatバンドルとDockerイメージにバンドルされていますが、サポートされているアプリケーションサーバーにLiferay WARをインストールする場合は、いくつかの重要な違いがあります。

| Liferay DXPのフレーバー        | デフォルトのElasticsearch | 事前インストール | 手動による介入が必要  |
| ------------------------ | ------------------- | -------- | ----------- |
| Tomcatバンドル：7.3 GA4+      | サイドカー               | ✔        | ✘           |
| Tomcat：7.3 GA4+          | サイドカー               | ✘        | ✘（自動ダウンロード） |
| Dockerタグ：7.3 GA4+        | サイドカー               | ✔        | ✘           |
| JBoss：7.3 GA4+           | サイドカー               | ✘        | ✘（自動ダウンロード） |
| Wildfly：7.3 GA4+         | サイドカー               | ✘        | ✘（自動ダウンロード） |
| WebSphere：7.3 GA4+       | サイドカー               | ✘        | ✔           |
| Weblogic：7.3 GA4+        | サイドカー               | ✘        | ✔           |
| *すべてのフレーバー：7.2/7.3 GA3-* | *Embedded*          | ✔        | ✘           |

Tomcat以外のアプリケーションサーバーのバンドルをダウンロードした場合、サーバーを起動すると、Elasticsearchディストリビューションがオンザフライでダウンロードされ、サイドカーサーバーとして起動されます。

[WebSphere](../../../installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-websphere.md)および[Weblogic](../../../installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-weblogic.md)アプリケーションサーバーへのLiferay DXP 7.3のインストール手順には、サイドカーサーバーの初期化に必要なElasticsearchアーカイブを手動で提供するための手順が含まれています。

<!-- ongoing work, LRDOCS-8008 -->

```{important}
The bundled Elasticsearch server is useful for development and testing purposes and must not be used in production. See [Installing Elasticsearch](./getting-started-with-elasticsearch.md) to learn about installing a remote search engine.
```

## Embeddedとサイドカーの比較

ElasticsearchサーバーのEmbeddedモードとサイドカーモードの比較を次に示します。

| Embedded                              | サイドカー                                             |
| ------------------------------------- | ------------------------------------------------- |
| 安全な接続を構成できません                         | 安全な接続を構成できません                                     |
| ElasticsearchのOSSバージョンを使用します          | ElasticsearchのOSSバージョンを使用します                      |
| <http://localhost:9200>で実行します         | <http://localhost:9201>で実行します                     |
| すべてのLiferayディストリビューションに事前インストールされています | 常に事前インストールされているわけではありません                          |
| 本番環境ではサポートされていません                     | 本番環境ではサポートされていません                                 |
| アプリサーバーに特別な手順は必要ありません                 | [一部のアプリサーバー](#app-server-differences)では追加の手順が必要です |
