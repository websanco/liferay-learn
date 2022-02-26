# Elasticsearch インデックスのリファレンス

インストールされているデフォルトのLiferay DXP 7.3インデックスは、以下のリストに近似しています（変更される可能性があります）。 デフォルトのグローバル **インデックス名接頭辞** は`liferay-`です。これはElasticsearch 7コネクタ設定で変更できます。 `20101`は、データベース内の特定の会社の生成された`companyId`です。  UIにインスタンスIDとして表示され、[仮想インスタンス](../../system-administration/configuring-liferay/virtual-instances/understanding-virtual-instances.md)を表します。

| インデックスID                                            | インデックスタイプ  | インデックスの目的                                     |
| --------------------------------------------------- | ---------- | --------------------------------------------- |
| liferay-0                                           | システムインデックス | システム設定アプリケーションでの検索                            |
| liferay-20101                                       | 会社インデックス   | Liferay DXP仮想インスタンスのインデックス付きアセットの検索           |
| liferay-20101-search-tuning-rankings                | アプリインデックス  | 結果ランキングアプリケーションのプライマリデータストレージ                 |
| liferay-20101-search-tuning-synonyms                | アプリインデックス  | 特定の仮想インスタンスの同義語セットアプリケーションのプライマリデータストレージ      |
| liferay-20101-workflow-metrics-instances            | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフローインスタンスに関するデータを保存する   |
| liferay-20101-workflow-metrics-nodes                | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフローノードに関するデータを保存する      |
| liferay-20101-workflow-metrics-processes            | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフロープロセスに関するデータを保存する     |
| liferay-20101-workflow-metrics-sla-instance-results | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフローインスタンスごとのSLA結果のストレージ |
| liferay-20101-workflow-metrics-sla-task-results     | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフロー タスクごとのSLA結果のストレージ   |
| liferay-20101-workflow-metrics-tokens               | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフロートークンに関するデータを保存する     |
| liferay-20101-workflow-metrics-transitions          | アプリインデックス  | ワークフロー統計情報アプリケーションのワークフロートランジションに関するデータを保存する  |

```{important}
   Liferay 7.2のインデックス名は、パッチによってインデックスの命名パターンに変更が加えられたため、より複雑になっています。 詳しくは、 [Multi-Tenant Index Names](../getting-started/whats-new-in-search-for-73.md#multi-tenant-index-names) をご覧ください。
```

```{note}
   Liferay DXPは、カスタムElasticsearchインデックスを作成、使用（書き込み、読み込み）するためのAPIを提供します。 これらのAPIの使用方法については、  [Developer Guide](../developer-guide.html) をご参照ください。
```

もし、 [Liferay Commerce](https://www.liferay.com/products/commerce) のサブスクリプションを持っていて、それがインストールで有効になっている場合、これらのインデックスも持っています。

| インデックスID                                                     | インデックスタイプ | インデックスの目的     |
| ------------------------------------------------------------ | --------- | ------------- |
| liferay-20101-commerce-ml-forecast                           | アプリインデックス | 機械学習機能        |
| liferay-20101-product-content-commerce-ml-recommendation     | アプリインデックス | レコメンデーションサービス |
| liferay-20101-product-interaction-commerce-ml-recommendation | アプリインデックス | レコメンデーションサービス |

<a name="関連情報" />

## 関連情報

- [マルチテナントインデックス名](../getting-started/whats-new-in-search-for-73.md#multi-tenant-index-names)
