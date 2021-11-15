# Solrの制限事項

```{important}
Liferay's Solr support will receive one more update (to support Solr 8) before being deprecated. Please plan to migrate to [Elasticsearch](../elasticsearch/getting-started-with-elasticsearch.md).

See [LPS-111013](https://issues.liferay.com/browse/LPS-111013)_ to follow the progress of Liferay's Solr 8 integration.
```

## エンドユーザー機能の制限

LiferayのSolr統合には、Liferay Commerceおよび以下の機能の使用に影響する制限があります。 次の機能を使用するには、Elasticsearchが必要です。

  - [ワークフロー統計情報](../../../process-automation/workflow/using-workflows/using-workflow-metrics.md)
  - [カスタムフィルター検索ウィジェット](../../search-pages-and-widgets/search-results/filtering-search-results.md)
  - 低レベル検索オプションウィジェット
  - 検索のチューニング：検索結果のカスタマイズ
  - 検索チューニング：同義語

## 開発者機能の制限

以下のAPIは、現在LiferayのSolrコネクターではサポートされていません。

  - ポータルコアから（モジュール： `portal-kernel`、アーティファクト： `com.liferay.portal.kernel`）：
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
      - `com.liferay.portal.search.sort *`：`ソート`、`FieldSort`および `ScoreSort`のみがサポートされています。
  - ポータル検索エンジンアダプターAPI（モジュール： `portal-search-engine-adapter-api`、アーティファクト： `com.liferay.portal.search.engine.adapter.api`）
      - `com.liferay.portal.search.engine.adapter.cluster.*`
      - `com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentRequest`
      - `com.liferay.portal.search.engine.adapter.index。*`： `RefreshIndexRequest` のみがサポートされます
      - `com.liferay.portal.search.engine.adapter.search.*`:
          - `MultisearchSearchRequest`
          - `SuggestSearchRequest`
      - `com.liferay.portal.search.engine.adapter.snapshot.*`

<!-- end list -->

```{note}
Liferay Commerce requires the `TermsSetFilter` implementation which is only available in the Elasticsearch connector.
```
