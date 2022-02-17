# アップグレードのレポート

データをアップグレードするときは、行われた変更と発生した問題を把握することが重要です。 [データベースアップグレードツール](../upgrade-basics/using-the-database-upgrade-tool.md)は、次の詳細を含むレポートにこの情報をキャプチャします。

* 実行日時
* DXP / Portalスキーマの初期バージョン、想定されるバージョン、および最終バージョンとビルド番号
* データベースのベンダーとバージョン
* アップグレード関連のポータルプロパティ設定
* ドキュメントライブラリストアと`rootDir`の構成設定
* ドキュメントライブラリのサイズ（[ファイルシステムストア](../../../system-administration/file-storage/other-file-store-types/simple-file-system-store.md)および[高度なファイルシステムストア](../../../system-administration/file-storage/configuring-file-storage.md)の場合）
* 最も長く実行されている20のアップグレードプロセス
* エラーと各タイプの頻度
* 警告と各タイプの頻度

ツールは、この情報を`tools/portal-tools-db-upgrade-client/reports`フォルダの`upgrade_report.info`というファイルに報告します。 以降のアップグレードでは、ツールはファイル名にタイムスタンプを追加し（`upgrade_report.info.1631029824000`など）、新しいアップグレードの詳細を新しい`upgrade_report.info`ファイルに報告します。

```{note}
アップグレードレポートは、Liferay DXP/Portal 7.4以降で利用できます。
```

アップグレードレポートを有効にするには、`tools/portal-tools-db-upgrade-client/portal-upgrade-ext.properties`ファイルで`upgrade.report.enabled=true`[ポータルプロパティ](../../reference/portal-properties.md)を設定します。 例:

```bash
cd liferay-home
```

```bash
echo "upgrade.report.enabled=\"true\"" << tools/portal-tools-db-upgrade-client/portal-upgrade-ext.properties
```

[データベースアップグレードツール](../upgrade-basics/using-the-database-upgrade-tool.md)は、次回の実行時にアップグレードレポートを生成します。

## 追加情報

* [アップグレードの概要](../upgrade-basics/upgrade-overview.md)

* [Running the Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md)

* [データベースアップグレードツールのリファレンス](../reference/database-upgrade-tool-reference.md#manual-configuration)

* [Troubleshooting Upgrades](../reference/troubleshooting-upgrades.md)
