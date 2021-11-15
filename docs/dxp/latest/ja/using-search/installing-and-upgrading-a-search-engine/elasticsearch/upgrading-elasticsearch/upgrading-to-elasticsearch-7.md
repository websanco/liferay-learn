# Elasticsearch 7へのアップグレード

Elasticsearch 7は、Liferay7.3では標準サポートされています。 特定のバージョンの互換性の詳細については、[Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651)を参照してください。

Liferay 7.2では、Elasticsearch 7は[Liferay Connector to Elasticsearch 7](https://web.liferay.com/marketplace/-/mp/application/170390307)  (バージョン`3.x`) を介してサポートされています。 Liferay 7.3にアップグレードする場合、または既存の7.2検索エンジンをElasticsearch 7に移行する場合は、Elasticsearchサーバーもアップグレードする必要があります。 新しいLiferay7.2システムをセットアップするには、Elasticsearch 7をインストールし、[インストールガイド](../getting-started-with-elasticsearch.md)に従います。

```{important}
Before upgrading Elasticsearch, back up your existing data. If something goes wrong during or after the upgrade, roll back to the previous version using the uncorrupted index snapshots. Follow the steps in [Backing up Elasticsearch](./backing-up-elasticsearch.md)_.
```

既存のElasticsearchサーバー（またはクラスター）をElasticsearch 7にアップグレードするには、

1.  [Liferay Companyとシステムインデックスをバックアップします](./backing-up-elasticsearch.md)。

2.  [検索の調整のアプリケーション固有のインデックスをバックアップします](./backing-up-elasticsearch.md#backing-up-and-restoring-search-tuning-indexes)（同義語セットと結果ランキング）。

3.  [Elasticsearchをインストールして設定します](../installing-elasticsearch.md)。

4.  [Elasticsearchをアップグレードします](#upgrading-elasticsearch)

5.  X-Pack Securityを使用している場合は、有効になっていることを確認してください。

    ``` yaml
    xpack.security.enabled: true
    ```

    セキュリティ設定の範囲の詳細については、[Securing Elasticsearch](../securing-elasticsearch.md)を参照してください。

6.  \ [7.2のみ\] [バンドルされているLiferay Connector to Elasticsearch 6をブラックリストに登録し](#blacklisting-elasticsearch-6)、Connector to Elasticsearch 7を[インストール](../connecting-to-elasticsearch.md#install-the-elasticsearch-7-connector)します。

7.  Connector to Elasticsearch 7を設定してElasticsearchに接続します。

8.  すべての検索インデックスとスペルチェックインデックスを再作成します。

9.  スナップショットから検索調整インデックスを復元します。 以前にこれらの機能を使用していた場合は、これらのインデックスにデータが保存されている可能性があります。

10. 検索調整エントリが引き継がれていることを確認します。

## Elasticsearchのアップグレード

ローリングリスタート対象バージョン（`6.8.x`）を使用している場合は、[ローリングアップグレード](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/rolling-upgrades.html)を実行してElasticsearchクラスターをアップグレードすることをお勧めします。 それ以外の場合は、[フルクラスター再起動アップグレード ](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/restart-upgrade.html)ガイドに従ってください。

新しいElasticsearchサーバーをインストールし、アップグレード前のデータにインデックスを付けたい場合、Liferay[データベースがアップグレード](../../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.md)されると、UIから[再インデックス](#re-index)をトリガーすることで、ほとんどのLiferayインデックスを復元できます。 ただし、検索の調整（結果ランキングと同義語）インデックス、およびデータベースストレージにバックアップされていないカスタムインデックスは、[アップグレード前のインデックスのスナップショット](./backing-up-elasticsearch.md#backing-up-and-restoring-search-tuning-indexes)から復元する必要があります。

## Elasticsearch 6のブラックリストへの登録

これは、Liferay 7.2を実行している場合にのみ必要です。

1.  以下の設定ファイルを作成します

    ``` bash
    com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config
    ```

2.  以下のコンテンツを指定します

    ``` properties
    blacklistBundleSymbolicNames=[ \
        "com.liferay.portal.search.elasticsearch6.api", \
        "com.liferay.portal.search.elasticsearch6.impl", \
        "com.liferay.portal.search.elasticsearch6.spi", \
        "com.liferay.portal.search.elasticsearch6.xpack.security.impl", \
        "Liferay Connector to X-Pack Security [Elastic Stack 6.x] - Impl", \ 
        "Liferay Enterprise Search Security  - Impl" \
    ]
    ```

3.  ファイルをサーバーの`deploy`フォルダにコピーしてデプロイします。

## インデックスの再作成

LiferayがElasticsearchクラスターに接続されたら、該当するインデックスを新しいElasticsearchのインストールに再インデックスします。

1.  会社、システム、およびスペルチェックのインデックスを再作成します。 グローバルメニュー（![Global Menu](../../../../images/icon-applications-menu.png)）から、*[コントロールパネル]* → *[設定]* → *[検索機能]* に移動します。 *[Reindex all search indexes]* エントリの*[実行]* をクリックします。

2.  [ワークフロー統計情報インデックス](../../../../process-automation/workflow/using-workflows/workflow-metrics-reports.md#re-indexing-workflow-metrics)を再作成します。グローバルメニュー（![Global Menu](../../../../images/icon-applications-menu.png)）から、*[Applications]* → *[Workflow---Metrics]* に移動します。 設定メニュー（![Options](../../../../images/icon-options.png)）を開き、*[すべてインデックスを再構築]* をクリックします。

これにより、Liferayデータベースに保存されているデータから作成されたインデックスが復元されます。 プライマリストレージとして使用されるインデックスを復元するには、[Backing Up Elasticsearch](./backing-up-elasticsearch.md)を参照してください。

## Liferay 7.2：Elasticsearch 6への復帰

Liferay 7.2を使用していて、Elasticsearch 7へのアップグレード中に回復不能な障害が発生した場合は、Elasticsearch 6にロールバックして再グループ化できます。

Elasticsearch 6と7は現在2つの別個のインストールであるため、この手順にはいくつかのステップが必要です。

1.  Liferay Connector to Elasticsearch 7を停止します。

2.  Elasticsearch 7を停止し、Elasticsearch 6 `elasticsearch.yml` とコネクタアプリが同じポート（デフォルトでは9200）を使用するように構成されていることを確認します。

3.  インストール先の `osgi` フォルダからElasticsearch 6のブラックリスト設定ファイルを削除します。

4.  Elasticsearchサーバーを起動してから、Liferay Connector to Elasticsearch 6を再起動します。

アップグレードが完了したら、[Liferay 7.3で利用できる新しい検索機能](../../../getting-started/whats-new-in-search-for-73.md)を参照してください。
