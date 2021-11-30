# Liferay7.3の検索機能のアップグレード

Liferayをアップグレードした後、検索エクスペリエンスをアップグレードするための追加の手順があります。 正確な手順は、既存の検索エンジンのインストールとLiferayバージョンによって異なりますが、常に
既存のインデックスをバックアップすることから始めることをお勧めします。</p> 



```{important}
Always consult Liferay's [Breaking Changes](../../../../liferay-internals/reference/7-3-breaking-changes.md)_ before upgrading. One such breaking change that impacts the upgraded search experience: [Dynamic Data Mapping fields in Elasticsearch have changed to a nested document](../../../../liferay-internals/reference/7-3-breaking-changes.md#dynamic-data-mapping-fields-in-elasticsearch-have-changed-to-a-nested-document)_. 

This change affects custom code that executes queries in the Elasticsearch index using `ddm__keyword__*` and `ddm__text__*` fields, as well as Search Widget configurations (e.g., Custom Filter or Custom Facet widgets) that make use of those fields. Manual updates to code and configurations are required to account for the change.
```




## 検索機能アップグレードの概要

以下のリストは、検索機能アップグレードの全体像を示したものです。より複雑なシナリオ（Liferay Enterprise Searchモジュールのアップグレードなど）については説明していません。 Liferayバージョン、LESバージョン（LESを使用している場合）、および現在の検索エンジンスタックに一致するシナリオを見つけてください。 アップグレードの手順については、*「アップグレード手順」*の列を参照してください。

| アップグレード前のLiferayバージョン\[+ LESバージョン\]                                 | アップグレード前の検索エンジンバージョン      | アップグレード手順                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
|:--------------------------------------------------------------------- |:------------------------- |:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Liferay 7.2**                                                       | Elasticsearch 7.9+        | 1\. [LiferayをElasticsearch 7に接続します。](../connecting-to-elasticsearch.md)<br><br>2. [セキュリティを設定します。](../securing-elasticsearch.md)<br><br>3. [Liferayをアップグレードします。](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics.md)<br><br>4. [検索 & スペルチェックインデックスを再作成します。](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md)                                                                                                                                                                                                                                                                                                                         |
| **Liferay 7.2 + LES 3.0**（*Monitoring*、*Learning to Rank*）            | Elasticsearch 7.9+        | 1\. LiferayをElasticsearch 7.9+に接続します。<br><br>2. セキュリティを設定します。<br><br>3. 現在*KibanaおよびMonitoring*を使用している場合はKibana 7.9+をインストールします。<br><br>4. 現在Kibanaと*Elasticsearch Monitoring/X-Pack Monitoring*を使用している場合は、LES Monitoringをインストールして構成します。<br><br>5. [Liferayをアップグレードします。](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics.md)<br><br>6. [検索 & スペルチェックインデックスを再作成します。](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md)                                                                                                                                                                                         |
| **Liferay 7.2**                                                       | Elasticsearch 7.3.x-7.8.x | 1\. [Elasticsearch 7.9+にアップグレードします。](upgrading-to-elasticsearch-7.md)<br><br>2. [一般的なアップグレード手順](#common-upgrade-steps)に従ってください                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **Liferay 7.2 + LES 2.0**（*Monitoring*、*Learning to Rank*）            | Elasticsearch 7.3.x-7.8.x | 1\. [Elasticsearch 7.9+にアップグレードします。](upgrading-to-elasticsearch-7.md)<br><br>2. [LESアップグレード手順](#les-upgrade-steps)に従ってください                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| **Liferay 7.2**                                                       | Elasticsearch 6.x         | 1\. [Elasticsearch 7.9+にアップグレードします。](upgrading-to-elasticsearch-7.md)<br><br>2. [一般的なアップグレード手順](#common-upgrade-steps)に従ってください                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **Liferay 7.2 + LES 2.0**（*Security*、*Monitoring*、*Learning to Rank*） | Elasticsearch 6.x         | 1\. [Elasticsearch 7.9+にアップグレードします。](upgrading-to-elasticsearch-7.md)<br><br>2. [LESアップグレード手順](#les-upgrade-steps)に従ってください                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| **Liferay 7.1**                                                       | Elasticsearch 6.x         | 1\. [Elasticsearch 7.9+にアップグレードします。](upgrading-to-elasticsearch-7.md)<br><br>2. [一般的なアップグレード手順](#common-upgrade-steps)に従ってください                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **Liferay 7.1 + LES 2.0**（*Security*、*Monitoring*、*Learning to Rank*） | Elasticsearch 6.x         | 1\. [Elasticsearch 7.9+にアップグレードします。](upgrading-to-elasticsearch-7.md)<br><br>2. [LESアップグレード手順](#les-upgrade-steps)に従ってください                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| **Liferay 7.0**                                                       | Elasticsearch 6.x         | 1\. [Elasticsearch 7.9+にアップグレードします。](upgrading-to-elasticsearch-7.md)<br><br>2. [一般的なアップグレード手順](#common-upgrade-steps)に従ってください                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **Liferay 7.0**                                                       | Elasticsearch 2.x         | 1\. [Elasticsearch 7.9+をインストールします。](../installing-elasticsearch.md)<br><br>2. [一般的なアップグレード手順](#common-upgrade-steps)に従ってください                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **Liferay 7.2、7.1**                                                   | Solr 7.x                  | **Elasticsearchに切り替えます：**<br><br>1. [Elasticsearch 7.9+をインストールします。](../installing-elasticsearch.md)<br><br>2. [一般的なアップグレード手順](#common-upgrade-steps)に従ってLiferay 7.3とElasticsearch<br>を構成するか、[LESアップグレード手順](#les-upgrade-steps)に従って、Liferay 7.3とLiferay Enterprise Search（LES）3.0を構成します<br><br>**または**<br><br><br>**Solrをアップグレードします（廃止予定）：**<br><br>1. [Solr 8.xをセットアップします。](../../solr.html)<br><br>2. [Liferayをアップグレードします。](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics.md)<br><br>3. [検索 & スペルチェックインデックスを再作成します。](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md) |
| **Liferay 7.0**                                                       | Solr 5.x                  | 上記のSolr 7.xの手順を使用します。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |




## 一般的なアップグレード手順



```{important}
[Back up the search indexes](./backing-up-elasticsearch.md)_ before proceeding with these steps.
```


LESアプリを含まないシステムのアップグレードシナリオには、次の手順が含まれます。

1.  [Elasticsearch7.9+にアップグレード](upgrading-to-elasticsearch-7.md)してElasticsearch 7.9+に移行するか、SolrインストールまたはElasticsearch 2.xからアップグレードする場合は、最初から[Elasticsearch 7.9+をインストール](../installing-elasticsearch.md)します。

2.  [LiferayをElasticsearch 7.9+に接続します。](../connecting-to-elasticsearch.md)

3.  [セキュリティを設定します。](../securing-elasticsearch.md)

4.  [Liferayをアップグレードします。](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics.md)

5.  [検索インデックスとスペルチェックインデックスを再作成します。](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/post-upgrade-considerations.md)

6.  [ワークフロー統計情報インデックスを再作成します。](../../../../process-automation/workflow/using-workflows/workflow-metrics-reports.md#re-indexing-workflow-metrics)

7.  アップグレードされたシステムで検索エクスペリエンスをテストして、すべてが期待どおりに機能していることを確認します。



## LESアップグレード手順



```{important}
[Back up the search indexes](./backing-up-elasticsearch.md)_ before proceeding with these steps.
```


LESアプリを使用するシステムでは、次の追加手順に従う必要があります。

1.  現在KibanaおよびMonitoringを使用している場合はKibana 7.9+をインストールします。

2.  現在KibanaとElasticsearch Monitoring/X-Pack Monitoringを使用している場合は、LES Monitoringアプリをインストールして構成します。

3.  LES Learning to Rankアプリを現在ご使用の環境で使用している場合は、インストールします。



## アップグレードされた検索エクスペリエンスをテストする

アップグレードされた検索エクスペリエンスを手動でテストして、依存する機能が期待どおりに機能することを確認します。 正常に動作していない機能があったり、期待とは異なる動作をしている場合は、[Liferayの旧バージョンと非互換の変更](./../../../../liferay-internals/reference/7-3-breaking-changes.md)を確認してください。



## LESアプリケーションの名前が変更されました



> **LESサブスクライバー**

Liferay CE/DXP 7.3リリースには明示的にリンクされていませんが、次のアプリは、機能をより適切に反映し、LESアプリとしてのアイデンティティを強調するために名前が変更されました。

| 機能                        | 旧アプリ名                                                          | 新アプリ名                                      | 7.2設定ファイル                                                                                                                  | 7.3設定ファイル                                                                                                      |
| ------------------------- | -------------------------------------------------------------- | ------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| Elasticsearchクラスターのモニタリング | Liferay Connector to X-Pack Monitoring \[Elastic Stack 6.x\] | Liferay Enterprise Search Monitoring       | `com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config` | `com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.MonitoringConfiguration.config` |
| Elasticsearchクラスターの保護     | Liferay Connector to X-Pack Security \[Elastic Stack 6.x\]   | Liferay Enterprise Search Security         | アクションは不要です。このアプリはDXP 7.3では使用できません。機能は、DXP 7.3にバンドルされているElasticsearch 7コネクタに統合されています。                                       |                                                                                                                |
| 機械学習を使用した検索アルゴリズムの最適化     | Liferay Connector to Elasticsearch Learning to Rank            | Liferay Enterprise Search Learning to Rank | 変更なし。                                                                                                                      |                                                                                                                |


Liferay 7.2の場合、ウィジェットと設定名は変更されていません。 Liferay 7.3では、モニタリングウィジェットと設定の名前が変更されました。

アプリと設定の名前を変更すると、アップグレードに次のような影響があります。

1.  LESモニタリングウィジェットは、*Elasticsearch Monitoring*に名前が変更されました。 起動時に、モジュールのアップグレード手順が実行され、DXP 7.3用の*Liferay Enterprise Search Monitoring*がデプロイされたときにアプリの名前が変更されます。 アクションは必要ありません。

2.  コンフィギュレーションファイル名が`com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config`から`com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.MonitoringConfiguration`に変更されました。 プロパティは以前と同じです。 ポータルの起動中に、モジュールのアップグレード手順が実行され、構成の名前が変更されます。 アクションは必要ありません。

3.  モニタリングウィジェットへのKibanaベースパスが変更されました。 `kibana.yml`で元の設定を変更する必要があります。 
   
   

    ``` yaml
    server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"
    ```


変更後 



    ``` yaml
    server.basePath: "/o/portal-search-elasticsearch-monitoring/monitoring-proxy"
    ```


Liferay 7.3は、すぐに使えるLiferay Connector to Elasticsearch 7を介してのみElasticsearch 7.9+をサポートしています。 移行元のアップグレード前のスタックのマトリックスはさらに多いため、既存のスタックからLiferay 7.3スタックに安全に移動するために必要な高度な手順を理解することが重要です。



## 次のステップ

アップグレードパスがわかったところで、アップグレードを開始して、Liferay 7.3で最新の[Elasticsearch](./upgrading-to-elasticsearch-7.md)（推奨）または[Solr](../../solr.html)（Liferay 7.3で廃止予定になりました）検索エンジンを使用しましょう。



## 追加情報

  - [Upgrading Elasticsearch](../getting-started-with-elasticsearch.md)
  - [Getting Started with Elasticsearch](../getting-started-with-elasticsearch.md)
  - [Elasticsearchのインストール](../installing-elasticsearch.md)
  - [Connecting to Elasticsearch](../connecting-to-elasticsearch.md)
  - [Securing Elasticsearch](../securing-elasticsearch.md)
  - [Upgrading Liferay](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics.md)
