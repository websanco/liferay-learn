# 7.3の検索の新機能

## Elasticsearchの統合

### Elasticsearch 7のサポート

> 利用可能:Liferay CE 7.3 GA4以降、Liferay DXP 7.3 GA1以降

Liferay DXP 7.3では、Elasticsearch 7を標準でサポートしています。 最小限必要なバージョンは7.9です。 詳細なサポート情報については、[Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651)を参照してください。

```{important}
Elasticsearch 6.x is not supported on Liferay CE/DXP 7.3.
```

### Elasticsearch 7コネクタはRESTクライアントに基づいています

> 利用可能:Liferay CE 7.3 GA4以降、Liferay DXP 7.3 GA1以降

DXP 7.3にバンドルされているElasticsearch 7コネクタは、[ElasticのJava RESTクライアント](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-overview.html)を使用して、HTTPを介してElasticsearchと通信します。 この通信プロトコルは、DXPとElasticsearch間のJavaシリアル化を必要としないため、Elasticsearch 7ノードをLiferay DXPとは異なるJVMで実行できるようになりました。

### X-Pack Security機能が統合されました

> 利用可能:Liferay 7.3 CE GA4以降、Liferay DXP 7.3 GA1以降

*Liferay Enterprise Search Security*（以前の*Liferay Connector to X-Pack Security*）モジュールの機能は、*Liferay Connector to Elasticsearch 7*に含まれています。 CE/DXP 7.3のすべてのお客様は、[Liferay Enterprise Search (LES)](https://www.liferay.com/products/dxp/enterprise-search) サブスクリプションなしでElasticsearchクラスターに安全に接続できます。

[アップグレード](../installing-and-upgrading-a-search-engine/elasticsearch/upgrading_elasticsearch.rst)ガイドには、暗号化設定をElasticsearch 7コネクタに移動するための情報が含まれています。

### 複数のElasticsearch接続

> 利用可能:Liferay CE 7.3 GA4以降、Liferay DXP 7.3 GA1以降

複数のElasticsearchクラスターへの接続を設定します。 Liferay DXP 7.2では、Liferayが接続されているElasticsearchクラスター内の任意のインデックス（Liferay以外のインデックスも含む）に対してクエリを実行できました。 Lifera以外のElasticsearchクラスターのインデックスに対してクエリを実行できるようになりました。 これにより、低レベル検索オプションおよび検索結果ウィジェットと組み合わせて使用することで、Elasticsearchクラスターを使用しているサードパーティシステムからの結果を表示することができます。 この機能は、主にLiferay Enterprise Search（LES）の[クラスター横断レプリケーション](#cross-cluster-replication)機能をサポートするために追加されました。

### 組み込みモードがサイドカーに置き換えられました

> 利用可能:Liferay CE 7.3 GA4以降、Liferay DXP 7.3 GA1以降

[「開発」](#development)の[「サイドカー」](#a-sidecar-elasticsearch-7-is-bundled)セクションを参照してください。

## 検索インフラストラクチャ & 管理

### マルチテナントインデックス名

> 利用可能：Liferay 7.3 CE GA6以降、Liferay DXP 7.3 GA1以降、Liferay DXP 7.2 SP3以降/FP8以降

マルチテナントインストールでは、単一のElasticsearchクラスターが複数のLiferay CE/DXPデプロイメントのインデックスを保持します。 ネームスペースが適切なインデックスの必要性が最も重要です。 7.2（FP8/SP3より前）では、インデックスの命名に一貫性がなく、マルチテナントのインストールが不可能になることがありました。 最新の7.2パッチおよび7.3では、すべてのLiferay CE/DXPインデックス（特定のアプリケーションによって制御されるインデックスを含む）のプレフィックスは、システム設定のElasticsearch 7設定エントリーから構成されています。 サードパーティーアプリケーションのインデックスは、同じAPIを利用して、インストール全体でネームスペースが一貫したインデックスを確保できるようになりました。

``` bash
[indexNamePrefix]-[companyId]-[appName]
```

例:

    liferay-20096-search-tuning-rankings

DXP 7.2 SP2以前から7.3にアップグレードすると、新しいDXPインデックスが自動的に作成されます。完全なインデックスの再構築と正常なアップグレードの確認後、古いインデックスを削除できます。

### 検索管理の接続

> 利用可能:Liferay CE 7.3 GA1以降、Liferay DXP 7.3 GA1以降

コントロールパネルの検索エントリ（[Configurations]セクションにあります）を使用して、Elasticsearch接続のステータスと正常性を表示します。 以下を参照してください。

  - 接続されている各Elasticsearchクラスターの状態
  - 各クラスター内のノード名とバージョン
  - Elasticsearchクライアントのバージョン

これは、以前のバージョンの[アクションをインデックスする]ページの上部にある情報バーに置き換わるものです。

### 標準化されたインデックスドキュメントID

> 利用可能:Liferay CE 7.3 GA1以降、Liferay DXP 7.3 GA1以降

インデックスドキュメントのUID（または `id`）は、Liferay DXPエンティティのデータベース行と1対1のパリティを持つようになりました。

## 検索ウィジェット

### ウィジェットテンプレートを使用して検索ウィジェットをカスタマイズする

> 利用可能:Liferay CE 7.3 GA4以降、Liferay DXP 7.3 GA1以降

検索ウィジェットは、FreemarkerまたはVelocityテンプレートを使用して各ウィジェットの視覚的なルックアンドフィールをカスタマイズするために、[ウィジェットテンプレート](./../../site-building/displaying-content/customizing-widgets/styling-widgets-with-widget-templates.md)（以前はアプリケーションディスプレイテンプレート）をサポートするようになりました。 たとえば、検索結果ウィジェットはカードレイアウトを表示するように設定でき、`author`や`modified date`のようなアセットのプロパティを表示または非表示にすることができます。 サポートされている検索ウィジェットごとに、すぐに使えるデフォルトテンプレートがいくつか用意されています。

### 類似結果

> **サブスクライバー**

> 利用可能：Liferay DXP 7.3 GA1以降にバンドルされており、[類似結果](https://web.liferay.com/marketplace/-/mp/application/172465398)マーケットプレイスアプリを介してLiferay DXP 7.2 SP2以降にインストール可能

類似結果ウィジェットには、ページのメインアセットに類似したアセットのコレクションが表示されます。 このウィジェットは、URLを使用してメインのアセット表示を決定し、Elasticsearchの[more like this query](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-mlt-query.html)を利用して追加のアセットを検索します。 メインアセットは、表示ウィジェット（ブログウィジェットなど）またはアセットパブリッシャーを使用して表示できます。

類似結果の最初のバージョンは、ブログ、掲示板、ドキュメント、およびWikiをサポートしています。 [拡張ポイント](../developer-guide/writing-a-similar-results-contributor.md)を実装することにより、追加のアセットタイプ（ネイティブアセットとカスタムアセットの両方）のサポートが可能になります。 ドキュメント類似性を判断するために使用されるロジックは、ElasticsearchのMore Like This [クエリパラメーター](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-mlt-query.html)ごとに設定できます。

詳細については、[Similar Results](../search-pages-and-widgets/similar-results.md)を参照してください。

## 検索の調整

> **サブスクライバー**

### 検索の調整：結果ランキング

> 利用可能:Liferay DXP 7.2 SP1以降、Liferay DXP 7.3 GA1以降

結果ランキングは、特定のクエリの結果の関連性をカスタム調整するためのグラフィカルUIです。 結果ランキングには、関連性を手動で調整するための3つの主要な機能があります。

1.  クエリの結果は、リストの一番上に固定して並べ替えることができます。 目的のドキュメントが元の結果リストにない場合は、手動で追加できます。 これにより、ユーザーは価値の高い結果をプロモートできます。
2.  結果は結果リストから非表示にすることができます。 この機能を使用して、古くなった結果や無関係な結果を削除できます。
3.  エイリアスは、同じカスタムの固定された結果および非表示の結果を代替検索クエリに適用します。 たとえば、クエリ「デジタルエクスペリエンスプラットフォーム」の結果を固定および非表示にして結果ランキングを作成する場合、エイリアス「portal」および「dxp」を追加すると、同じ固定された結果と非表示の結果が適用されます。

### 検索の調整：同義語

> 利用可能:Liferay DXP 7.2 SP1以降、Liferay DXP 7.3 GA1以降

同義語は、同様の意味を持つクエリを関連付け、検索時に同義クエリに同等の重みを与えます。 たとえば、「mobile phone」、「cell phone」、「hand phone」というクエリは同等の意味を持ち、同義で使用できます。 これらの3つのクエリで同義語セットを定義すると、「mobile phone」と検索したユーザーには、「cell phone」または「hand phone」という単語を含むドキュメントが表示されます。 [Synonyms documentation from Elastic](https://www.elastic.co/guide/en/elasticsearch/guide/current/synonyms.html)を参照してください。

## Liferay Enterprise Search（LES）

> **[LESサブスクライバー](https://www.liferay.com/products/dxp/enterprise-search)**

### クラスター横断レプリケーション（LES）

> 利用可能：Liferay DXP 7.2 SP3以降、Liferay DXP 7.3 GA1以降で、[Liferay Enterprise Searchクラスター横断レプリケーション](../liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.md)アプリケーションを使用。

クラスター横断レプリケーションアプリケーションは、ディザスタリカバリ（高可用性）と地理的近接パフォーマンスの最適化のために、リモートデータセンター全体でElasticsearchクラスターを複製します。 Elasticsearchの[クラスター横断レプリケーション](https://www.elastic.co/guide/en/elasticsearch/reference/7.9/xpack-ccr.html)機能を活用し、アクティブパッシブモデルを使用して分散したクラスター間で検索インデックスを複製します。 すべてのDXPノードのすべての書き込み操作は単一のリーダーインデックスにルーティングされますが、各DXPクラスターノードは任意のフォロワーインデックスから読み取るように設定できます。

### Learning to Rank（LES）

> 利用可能：Liferay DXP 7.2 SP2以降、Liferay DXP 7.3 GA1以降で、[Liferay Enterprise Search Learning to Rank](https://web.liferay.com/marketplace/-/mp/application/170666298)アプリケーションを使用。

最適な結果を得るために検索を手動で調整することは困難です。 ある特定のクエリに対して検索アルゴリズムを最適化すると、他の数百のクエリの結果が意図せず悪くなる可能性があります。

Learning to Rank（LTR）は、機械学習（ML）モデルを適用して検索結果を改善することにより、この課題に取り組んでいます。 モデルのトレーニングでは以下を行います。

  - 「機能」の選択（モデルのトレーニング時に考慮する検索要素の用語---たとえば、最新性、地理的近接性、タグの一致）
  - 手動（クエリの結果を評価する検索エキスパートなど）または半自動（クエリのユーザークリック率の測定など）で管理される判断リスト（「グラウンドトゥルース」とも呼ばれます）の提供。

判断リストは、モデルのトレーニングに情報を提供し、導くための信頼できる情報源です。 ユーザーが検索クエリを送信すると、LTRは検索エンジンから返された最初の *×* 件の結果（デフォルトでは最初の1,000件の結果）を受け取り、学習したモデルを使ってそれらの結果を再スコアリングして並べ替えた後、リストをユーザーに返します。

### LESアプリケーションの名前が変更されました

Liferay CE/DXP 7.3リリースには明示的にリンクされていませんが、次のアプリは、機能をより適切に反映し、LESアプリとしてのアイデンティティを強調するために名前が変更されました。

| 機能                        | 旧アプリ名                                                          | 新アプリ名                                      |
| ------------------------- | -------------------------------------------------------------- | ------------------------------------------ |
| Elasticsearchクラスターのモニタリング | Liferay Connector to X-Pack Monitoring \[Elastic Stack 6.x\] | Liferay Enterprise Search Monitoring       |
| Elasticsearchクラスターの保護     | Liferay Connector to X-Pack Security \[Elastic Stack 6.x\]   | Liferay Enterprise Search Security         |
| 機械学習を使用した検索アルゴリズムの最適化     | Liferay Connector to Elasticsearch Learning to Rank            | Liferay Enterprise Search Learning to Rank |
| リモートデータセンター間でのインデックスの複製   | NA（新アプリ）                                                       | Liferay Enterprise Searchクラスター横断レプリケーション   |

## 開発

### 新しいインデックス設定コントリビューター拡張ポイント

> 利用可能:Liferay CE 7.3 GA6以降、Liferay DXP 7.3 GA1以降

Elasticsearchのバージョンに依存しない`IndexSettingsContributor`が追加されました（[srcコードはこちら）](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/settings/IndexSettingsContributor.java)：

  - `com.liferay.portal.search.spi.settings.IndexSettingsContributor`
  - `com.liferay.portal.search.spi.settings.IndexSettingsHelper.java`
  - `com.liferay.portal.search.spi.settings.TypeMappingsHelper.java`

### Sidecar Elasticsearch 7がバンドルされています

> 利用可能:Liferay CE 7.3 GA4以降、Liferay DXP 7.3 GA1以降

オープンソース（OSS）版のElasticsearch 7.9.0（ライセンスはApache 2.0）がLiferay CE/DXP 7.3のTomcatバンドルにバンドルされ、*サイドカー*のJVMで同時に起動します。 このサイドカーのインストールは、*開発モード*と呼ばれ、以前の*組み込み*モードに置き換わるものです（どちらも本番環境のインストールではサポートされていません）。 サイドカーのシステム設定には、Elasticsearch 7設定に専用のプロパティがあります。 デフォルトでは、HTTPポート9201で実行されます。 詳細については、[Using the Sidecar or Embedded Elasticsearch](../installing-and-upgrading-a-search-engine/elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md)を参照してください。

Elasticsearch 7.9.0 OSSバージョンは、最初の起動時に自動ダウンロードされます。 リソースをダウンロードするための外部サイトにデプロイメントが到達できない場合は、[サイドカーサーバー](../installing-and-upgrading-a-search-engine/elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md)の手動インストールを参照してください。

## DXP 7.3へのアップグレード

以前のDXPバージョンから7.3にアップグレードするには、[RESTクライアント](#the-elasticsearch-7-connector-is-based-on-the-rest-client)に切り替えるため、いくつかの特別な手順が必要です。 アップグレードに役立つ重要な情報については、[アップグレードのドキュメンテーション](../installing-and-upgrading-a-search-engine/elasticsearch/upgrading_elasticsearch.rst)を参照してください。
