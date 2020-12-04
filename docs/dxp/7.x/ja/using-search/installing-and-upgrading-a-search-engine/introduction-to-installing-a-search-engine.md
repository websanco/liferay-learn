# 検索エンジンのインストールの概要

検索エンジンは、Liferay DXPインストールの重要なコンポーネントです。 この概要では、**本番**環境で検索エンジンをインストールする方法の基礎について説明します。
構成の概要で述べたように 、DXPには、同じJVMで実行されるElasticsearchサーバーが含まれています。 組み込みサーバーは開発ではサポートされていますが、本番環境ではサポートされていません。 管理者は、****本番環境で[埋め込まれたバージョン](https://help.liferay.com/hc/en-us/articles/360029031631-Elasticsearch#embedded-vs-remote-operation-mode)を使用してください。

Elasticsearchのほかに、DXPは[Solr](http://lucene.apache.org/solr)もサポートしています。 Solrは組み込まれておらず、リモートで接続する必要があることに注意してください。 Solrを使用するには、 [Solr](https://help.liferay.com/hc/articles/360032264052-Installing-Solr) インストールの記事を参照してください。

## 前提条件

### Java

検索エンジンとLiferay DXPは、同じJavaバージョンとディストリビューションを使用する必要があります(たとえば、Oracle Open JRE 1.8.0\_201)。

### ElasticsearchとJavaディストリビューションの互換性

サポートされているJDKディストリビューションとバージョンの詳細については、 [Elasticsearch互換性マトリックス](https://www.elastic.co/support/matrix#matrix_jvm) および [Liferay DXP互換性マトリックス](https://help.liferay.com/hc/sections/360002103292-Compatibility-Matrix) を参照してください。

### SolrとJavaディストリビューションの互換性

サーバー間でJVMレベルのシリアライゼーションは発生しないため、Solrではこの考慮は必要ありません。 すべての通信はHTTPレベルで行われます。

## 検索エンジンでのクラスタリングの有効化

上記のように、運用環境の検索エンジンはクラスター化する必要があります。 これにより、検索エンジンは負荷を処理し、DXPの全体的なパフォーマンスを最適化できます。 ElasticsearchとSolrはどちらも、リモート環境の複数のノードで正常に構成できます。

  - リモートElasticsearchサーバーまたはクラスターを構成するには、 [Elasticsearch](https://help.liferay.com/hc/articles/360028711132-Installing-Elasticsearch) インストールの記事を参照してください。

  - リモートSolrサーバーまたはクラスターを構成するには、 [Solr](https://help.liferay.com/hc/articles/360032264052-Installing-Solr) インストールの記事を参照してください。

## Solrの使用

Solrを検索エンジンとして使用することにはいくつかの欠点があります。 これらの制限は、SolrがさまざまなLiferay製品の検索リクエストを処理する方法に影響します。

### LiferayのSolr統合のエンドユーザー機能の制限

  - [Liferay Commerce](https://help.liferay.com/hc/en-us/articles/360017869952)
  - [*ワークフローメトリクス](https://help.liferay.com/hc/en-us/articles/360029042071-Workflow-Metrics-The-Service-Level-Agreement-SLA-)
  - [カスタムフィルター検索ウィジェット](https://help.liferay.com/hc/en-us/articles/360028721272-Filtering-Search-Results-with-the-Custom-Filter-Widget)
  - [低レベル検索オプションウィジェット](https://help.liferay.com/hc/en-us/articles/360032607571-Low-Level-Search-Options-Searching-Additional-or-Alternate-Indexes)
  - [検索のチューニング：検索結果のカスタマイズ](https://help.liferay.com/hc/en-us/articles/360034473872-Search-Tuning-Customizing-Search-Results)
  - [検索チューニング：同義語](https://help.liferay.com/hc/articles/360034473852-Search-Tuning-Synonym-Sets)

### LiferayのSolr統合の開発者機能の制限

以下のAPIの実装は将来追加される可能性がありますが、現在LiferayのSolrコネクタではサポートされていません。

  - ポータルコアから（モジュール： `ポータルカーネル`、アーティファクト： `com.liferay.portal.kernel`）：
      - `com.liferay.portal.kernel.search.generic.NestedQuery`
      - `com.liferay.portal.kernel.search.filter`：
          - `ComplexQueryPart`
          - `GeoBoundingBoxFilter`
          - `GeoDistanceFilter`
          - `GeoDistanceRangeFilter`
          - `GeoPolygonFilter`
  - ポータル検索APIから（モジュール： `portal-search-api`、アーティファクト： `com.liferay.portal.search.api`）：
      - `com.liferay.portal.search.filter`：
          - `ComplexQueryPart`
          - `TermsSetFilter`
      - `com.liferay.portal.search.geolocation.*`
      - `com.liferay.portal.search.highlight.*`
      - `com.liferay.portal.search.query.function.*`
      - `com.liferay.portal.search.query。*`：
      - `com.liferay.portal.search.script.*`
      - `com.liferay.portal.search.significance.*`
      - `com.liferay.portal.search.sort *`：のみ `替え`、`FieldSort`、及び `ScoreSort` がサポートされています。
  - ポータル検索エンジンアダプターAPI（モジュール： `portal-search-engine-adapter-api`、アーティファクト： `com.liferay.portal.search.engine.adapter.api`）
      - `com.liferay.portal.search.engine.adapter.cluster.*`
      - `com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentRequest`
      - `com.liferay.portal.search.engine.adapter.index。*`： `RefreshIndexRequest` のみがサポートされます
      - `com.liferay.portal.search.engine.adapter.search.*`:
          - `MultisearchSearchRequest`
          - `SuggestSearchRequest`
      - `com.liferay.portal.search.engine.adapter.snapshot.*`

Liferay Commerceには、Elasticsearchコネクタのみで使用可能な `TermsSetFilter` 実装が必要です。
