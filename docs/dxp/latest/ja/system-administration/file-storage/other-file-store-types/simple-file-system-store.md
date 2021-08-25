# Simple File System Store

Simple File System Storeは、デフォルトのファイルストアタイプです。 ファイルシステム（ローカルまたはマウントされた共有）を使用してファイルを格納します。 クラスター環境でSimple File System Storeを使用する場合、そのフォルダはすべてのクラスターノードからアクセス可能で、同時要求を処理し、ファイルロックをサポートしている必要があります。 [記憶域ネットワーク](https://en.wikipedia.org/wiki/Storage_area_network)またはクラスター化ファイルシステムを使用することをお勧めします。

Simple File System Storeは、Liferay DXPデータベースにバインドされています。 デフォルトのルートフォルダは`[Liferay Home]/data/document_library`です。 Simple File System Storeは、次のファイルパス形式を使用してファイルを保存します。

    [Liferay Home]/data/document_library/companyId/folderId/numericFileEntryName/versionNumber

![Simple File System Storeのファイル構造の例。](./simple-file-system-store/images/01.png)

`[Liferay Home]/data/document_library`フォルダのファイル階層の説明は次のとおりです。

`companyId`：サイトの会社ID。

`folderId`：ファイルエントリが含まれているアプリのフォルダ（ドキュメントとメディアフォルダなど）のID。

`numericFileEntryName`：ファイルエントリの数値名。

`versionNumber`：ファイルエントリのバージョン番号。

**注：**ドキュメントの数値ファイルエントリ名とドキュメントIDは、次の点で異なります。

  - それぞれ独立したカウンターを持っています。
  - 数値ファイルエントリ名はストレージファイルパスで使用されますが、ドキュメントIDは使用されません。
  - それぞれ異なる`DLFileEntry`テーブル列に格納されます。 数値ファイルエントリ名は`name`列に格納され、ドキュメントIDは`fileEntryId`列に格納されます。

``` warning::
   ドキュメントライブラリでデータベーストランザクションのロールバックが発生した場合、トランザクションのファイルシステムの変更は元に戻され**ません**。 ドキュメントライブラリファイルとファイルシステムストア内のファイルとの間に不整合が発生する可能性があり、手動での同期が必要になる場合があります。 `DBStore <./dbstore.md>`_ を除くすべてのDXPストアは、この制限に対して脆弱です。
```

## ストアの構成

Simple File System Storeを構成するには、次の手順に従います。

1.  *メニュー*（![Menu](./simple-file-system-store/images/02.png)）ボタンをクリックして、*コントロールパネル*にアクセスします。

2.  *[Control Panel]* → *[Configuration]* → *[System Settings]* に移動します。

3.  *[Platform]* セクションで、*[File Storage]* をクリックします。

4.  *[Simple File System Store]* 画面で、*[Root directory]* を[Liferay Home](../../../installation-and-upgrades/reference/liferay-home.md)パス（絶対または相対）に設定します。

    ![システム設定の[File Storage]ページでは、ドキュメントリポジトリストレージを設定できます。](./simple-file-system-store/images/03.png)

5.  *[保存]* をクリックします。

ファイルストアがすぐに新しいフォルダに切り替わります。

別のストアタイプからSimple File System Storeに切り替える場合は、次のプロパティを使用して[`portal-ext.properties`](../../../installation-and-upgrades/reference/portal-properties.md)を設定し、DXPを再起動します。

``` properties
dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
```

## クラスター環境でのストアの使用

[クラスター環境](../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability.md)では、すべてのノードがアクセスできるネットワークマウントファイルシステムをストアに指定します。 ネットワーク化されたファイルシステムは、すべてのノードからアクセス可能で、同時要求をサポートし、ファイルロックをサポートしている必要があります。 そのようなファイルシステムなしでSimple File System Storeを使用し、複数のユーザーが同時に同じファイルに書き込もうとすると、データが破損する可能性があります。

## 追加情報

  - [ファイルストレージの構成](../configuring-file-storage.md)
  - [Amazon S3 Store](./amazon-s3-store.md)
  - [DBStore](./dbstore.md)
  - [File Store Migration](../file-store-migration.md)
