# サイドカーまたはEmbedded モードのElasticsearchの使用

ZIPファイルまたはDockerイメージを介してインストールされたLiferay Tomcatバンドルには、Liferayで始まるElasticsearchノードが含まれています。 Liferay 7.3のノードはサイドカーサーバーとして別のJVMで実行され、Liferay 7.2のノードはLiferayサーバーに組み込まれています。

Elasticsearchサーバーには、次のURLからアクセスできます。

* Liferay 7.3の場合は<http://localhost:9201>
* Liferay 7.2の場合は<http://localhost:9200>

サイドカーサーバーの出力例は次のとおりです。

```json
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
   本番環境でサポートされている構成ではありませんが、KibanaをインストールしてバンドルされているElasticsearchサーバーを監視することは、開発やテストの際に役立ちます。 KibanaがビルドしたOSSのみ<https://www.elastic.co/downloads/kibana-oss>をインストールすることに注意してください。
```

HSQLのような組み込みデータベースを本番環境で実行したり、バンドルされたElasticsearchサーバーを本番環境で実行したりしないでください。 代わりに、Elasticsearchをスタンドアロンサーバーまたはサーバーノードのクラスターとしてリモートモードで実行します。

```{important}
   Synonym SetsとResult Rankingsは、検索インデックスを一次データの保存に使用するアプリケーションです。 Liferayのデータベースにデータは保存されません。 そのため、サイドカーや組み込みのElasticsearchを使用しているときにSynonym SetsやResult Rankingsが設定されていた場合、リモートのElasticsearchサーバに切り替えてインデックスを再作成しても、それらの設定は``復元されません。 その代わり、シノニムセットと結果ランキングをリモートのElasticsearchクラスタに手動で取り込む必要があります。 インデックスを維持するためのElasticの `スナップショットと復元 <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshot-restore.html>`_ 機能の詳細は `アップグレードガイド<../elasticsearch.html>`_ を参照してください。
```

<a name="バンドルされているelasticsearchサーバーのユースケース" />

## バンドルされているElasticsearchサーバーのユースケース

デフォルトのElasticsearchサーバー（サイドカーおよびEmbedded）の一般的な使用法は次のとおりです。

* カスタムの [検索とインデックスコード](../../developer_guide.html) のテスト
* Kibanaを介してElasticsearchで直接クエリを実行して検索クエリを開発する
* [検索の調整](../../search_administration_and_tuning.md)機能のテスト
* [検索ウィジェット](../../search_pages_and_widgets.md)の調査と設定

<a name="アプリサーバーの違い" />

## アプリサーバーの違い

Liferay DXP 7.3およびLiferay Portal 7.3 GA4のTomcatバンドルとDockerイメージには、Elasticsearchサイドカーサーバーがバンドルされていますが、サポートされているアプリケーションサーバーにLiferay WARをインストールする場合には、いくつかの重要な違いがあります。

| Liferay DXPのフレーバー        | デフォルトのElasticsearch | 事前インストール | 手動による介入が必要               |
| :--- | :--- | :--- | :--- |
| Tomcatバンドル：7.3 GA4+      | サイドカー               | &#10004; | &#10008;                 |
| Tomcat：7.3 GA4+          | サイドカー               | &#10008; | &#10008; (自動ダウンロードされました) |
| Dockerタグ：    7.3 GA4     | サイドカー               | &#10004; | &#10008;                 |
| JBoss：7.3 GA4+           | サイドカー               | &#10008; | &#10008; (自動ダウンロードされました) |
| Wildfly：7.3 GA4+         | サイドカー               | &#10008; | &#10008; (自動ダウンロードされました) |
| WebSphere：7.3 GA4+       | サイドカー               | &#10008; | &#10004;                 |
| Weblogic：7.3 GA4+        | サイドカー               | &#10008; | &#10004;                 |
| **すべてのフレーバー：7.2/7.3 GA3-** | **Embedded** | &#10004; | &#10008;                 |

Tomcat以外のアプリケーションサーバーのバンドルをダウンロードした場合、サーバーを起動すると、Elasticsearchディストリビューションがオンザフライでダウンロードされ、サイドカーサーバーとして起動されます。

[WebSphere](../../../installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-websphere.md)および[Weblogic](../../../installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-weblogic.md)アプリケーションサーバーへのLiferay DXP 7.3のインストール手順には、サイドカーサーバーの初期化に必要なElasticsearchアーカイブを手動で提供するための手順が含まれています。
<!-- ongoing work, LRDOCS-8008 -->

```{important}
   バンドルされているElasticsearchサーバは、開発およびテスト目的に有用であり、本番環境では使用しないでください。 リモート検索エンジンのインストールについては、 `Elasticsearchをインスト0るする <./getting-started-with-elasticsearch.md>`_ を参照してください。
```

<a name="embeddedとサイドカーの比較" />

## Embeddedとサイドカーの比較

ElasticsearchサーバーのEmbeddedモードとサイドカーモードの比較を次に示します。

| Embedded                              | サイドカー                                             |
| :--- | :--- |
| 安全な接続を構成できません                         | 安全な接続を構成できません                                     |
| ElasticsearchのOSSバージョンを使用します          | ElasticsearchのOSSバージョンを使用します                      |
| <http://localhost:9200>で実行します         | <http://localhost:9201>で実行します                     |
| すべてのLiferayディストリビューションに事前インストールされています | 常に事前インストールされているわけではありません                          |
| 本番環境ではサポートされていません                     | 本番環境ではサポートされていません                                 |
| アプリサーバーに特別な手順は必要ありません                 | [一部のアプリサーバー](#app-server-differences) では追加の手順が必要です |
