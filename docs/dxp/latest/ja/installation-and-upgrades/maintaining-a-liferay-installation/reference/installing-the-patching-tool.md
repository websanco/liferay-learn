# パッチツールのインストール

> サブスクライバー

アプリケーションサーバー上のDXPへのパッチ適用とパッチ適用ツールの更新は手動で行われます。

<a name="installation" />

## インストール

1.  [カスタマーポータル](https://customer.liferay.com/downloads?p **p** id=com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet&** com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet** productAssetCategoryId=118191019& **com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066) からパッチツールをダウンロードします。

    ***DXP 7.3以降の場合、** 最新のパッチツールをダウンロードします。
    ***DXP 7.2の場合、** 最新バージョンのパッチツール2.xをダウンロードします。

1. パッチツールを [Liferay Home](../../reference/liferay-home.md) フォルダー（推奨）または別のフォルダーに解凍します。

Patching Toolフォルダー `patching-tool` は、 `patching-tool.sh` スクリプトが含まれています。

パッチツールのヘルプメッセージを出力するには、 `patching-tool` フォルダーで次のコマンドを実行します。

```bash
cd patching-tool
./patching-tool.sh help
```

<a name="updating-the-patching-tool" />

## パッチツールの更新

Patching Toolは、インストールするパッチに新しいPatching Toolバージョンが必要な場合を報告します。 パッチツールを更新する方法は次のとおりです。

1.  最新の [パッチツール](https://customer.liferay.com/downloads?p **p** id=com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet&** com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet** productAssetCategoryId=118191019& **com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066) ダウンロードします。

    ***DXP 7.3以降の場合、** 最新のパッチツールをダウンロードします。
    ***DXP 7.2の場合、** パッチツールの最新の2.xバージョンをダウンロードします。

1. 既存のパッチツール `.properties` ファイルをバックアップします。

1. 新しいパッチを `パッチツール` フォルダーの親フォルダー（通常はLiferayホーム）に解凍して、既存のパッチツールを上書きします。

パッチツールは、DXPインストールで構成する準備ができています。

<a name="additional-information" />

## 追加情報

* [パッチツールの構成](./configuring-the-patching-tool.md)
* [Installing Patches](./installing-patches.md)
* [カスタムコードとパッチの互換性](./advanced-patching-for-dxp-7-2/custom-code-and-patch-compatibility.md)
