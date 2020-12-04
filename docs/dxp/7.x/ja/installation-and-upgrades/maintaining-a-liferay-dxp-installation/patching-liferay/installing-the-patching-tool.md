# パッチツールのインストール

アプリケーションサーバー上のDXPへのパッチ適用とパッチ適用ツールの更新は手動で行われます。

## インストール

1.  [カスタマーポータル](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191019&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066)からパッチツールをダウンロードします。

2.  パッチツールを [Liferay Home](../../reference/liferay-home.md) フォルダー（推奨）または別のフォルダーに解凍します。

Patching Toolフォルダー `patching-tool` は、 `patching-tool.sh` スクリプトが含まれています。

パッチツールのヘルプメッセージを出力するには、 `patching-tool` フォルダーで次のコマンドを実行します。

``` bash
cd patching-tool
./patching-tool.sh help
```

## パッチツールの更新

Patching Toolは、インストールするパッチに新しいPatching Toolバージョンが必要な場合を報告します。 パッチツールを更新する方法は次のとおりです。

1.  最新の [パッチツール](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191019&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066)ダウンロードします。

2.  既存のパッチツール `.properties` ファイルをバックアップします。

3.  新しいパッチを `パッチツール` フォルダーの親フォルダー（通常はLiferayホーム）に解凍して、既存のパッチツールを上書きします。

パッチツールは、DXPインストールで構成する準備ができています。

## 追加情報

  - [パッチツールの構成](./configuring-the-patching-tool.md)
  - [パッチのインストール](./installing-patches.md)
  - [カスタムコードとパッチの互換性](./advanced-patching/custom-code-and-patch-compatibility.md)
