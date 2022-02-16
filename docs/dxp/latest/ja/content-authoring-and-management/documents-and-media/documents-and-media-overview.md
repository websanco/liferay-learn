# 「ドキュメントとメディア」の概要

ドキュメントとメディアアプリケーションは、Liferayインスタンス内のファイルを保存および管理します。 これには、ドキュメントとメディアに直接アップロードされたファイルだけでなく、添付ファイルをサポートしているアプリケーションにアップロードされたファイルも含まれます。

このアプリケーションはすべてのサイトで利用可能であり、[アセットライブラリ](../asset-libraries/asset-libraries-overview.md)で有効にできます。 ユーザーの仮想共有ドライブとして機能し、あらゆる種類のファイルを保存できます。

```{tip}
ファイルのスコープは、サイトのドキュメントとメディアにアップロードされているか、アセットライブラリのドキュメントとメディアにアップロードされているかよって異なります。 サイトにアップロードされたファイルはそのサイトにスコープされており、DXPインスタンス内の他のサイトから容易にアクセスすることはできません。 アセットライブラリにアップロードされたファイルはグローバルに利用可能であり、複数のサイトにリンクしてすぐにアクセスできます。
```

![ドキュメントとメディアを使用して、Liferayインスタンスのファイルを保存、表示、および管理します。](./documents-and-media-overview/images/01.png)

アップロードされたすべてのファイルは、指定された[ファイルストア](../../system-administration/file-storage/configuring-file-storage.md)に保存されます。 デフォルトでは、ドキュメントとメディアは[簡易ファイルシステムストア](../../system-administration/file-storage/other-file-store-types/simple-file-system-store.md)を使用しますが、代替ファイルストア（ [DBStore](../../system-administration/file-storage/other-file-store-types/dbstore.md) 、[Google Cloud Store](../../system-administration/file-storage/other-file-store-types/google-cloud-storage.md)、[Amazon S3 Store](../../system-administration/file-storage/other-file-store-types/amazon-s3-store.md)など）を使用するようにインスタンスを構成できます。

アップロードすると、Liferay UIを介してデジタルアセットを検索、フィルタリング、整理したり、[WebDAV](./publishing-and-sharing/accessing-documents-with-webdav.md)を使用してシステムのファイルマネージャーを介してドキュメントにアクセスしたりできます。 ドキュメントとメディアを[Microsoft Office 365](./devops/enabling-document-creation-and-editing-with-microsoft-office-365.md)、[OpenOffice](./devops/enabling-openoffice-libreoffice-integration.md)、[Google Drive](./devops/google-drive-integration/enabling-links-to-google-drive-documents.md)、およびその他のアプリケーションと統合して、Liferay UIに追加機能を提供することもできます。

ドキュメントとメディアのLiferay [ワークフロー](../../process-automation/workflow/using-workflows/activating-workflow.md#activating-workflow-for-specific-applications) を有効にすると、アセットの公開設定とバージョンの更新の管理に役立ちます。 有効にすると、ドキュメントを公開する前にレビューする必要があります。

ドキュメントとメディアの統合を構成して、アップロードまたは更新時にファイルの[プレビューを自動的に生成](./devops/configuring-documents-and-media-previews.md)できます。

![ドキュメントとメディアの統合を使用して、ファイルプレビューを生成します。](./documents-and-media-overview/images/02.png)

ドキュメントとメディアに追加されたファイルは、添付ファイルをサポートするアプリケーション（Webコンテンツ、掲示板など）、およびページフラグメントとウィジェット（ [メディアギャラリー](./publishing-and-sharing/publishing-documents.md#using-the-media-gallery-widget.md)）で表示できます。

必要に応じて、ドキュメントとメディアアセットをLiferayアーカイブ（`LAR`）ファイルとして[エクスポート](../../site-building/building-sites/importing-exporting-pages-and-content.md)できます。 これらのファイルは、バックアップとして使用したり、アセットを別のサイトまたはアセットライブラリにインポートするために使用できます。

<a name="追加情報" />

## 追加情報

* [ドキュメントとメディアUIリファレンス](./documents-and-media-ui-reference.md)
* [フォルダの作成](./uploading-and-managing/creating-folders.md)
* [ファイルのアップロード](./uploading-and-managing/uploading-files.md)
* [ファイルストレージの構成](../../system-administration/file-storage/configuring-file-storage.md)
