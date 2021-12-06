# ドキュメントとメディアの共有

ドキュメントとメディアライブラリは、サーバー上のファイルを保存および整理します。 あらゆる種類のファイルを受け入れ、仮想共有ドライブとして機能できます。 これらのファイルは、Liferayのインストール時に</a>設定されたファイル ストレージ方式</0>で保存されます。

```{tip}
各DXPサイトには、独自のドキュメントとメディアライブラリがあります。 したがって、ドキュメントはそのサイトに限定され、サイト間で共有されません。
```

[フォルダ](../../uploading-and-managing/creating-folders.md)を作成し、[ドキュメントをドキュメントとメディアライブラリにアップロード](../../uploading-and-managing/uploading-files.md)できます（ドラッグアンドドロップで一度に複数のドキュメントをアップロード可能）。

ユーザーは、カスタマイズ可能なドキュメントタイプとメタデータセットを使用してドキュメントを整理し、自動ドキュメントプレビュー生成で表示できます。 カスタマイズ可能なドキュメントタイプを作成するには、[ドキュメントタイプの定義](../../uploading-and-managing/managing-metadata/defining-document-types.md)を参照してください。 新しいメタデータセットを作成するには、[メタデータセットの使用](../../uploading-and-managing/managing-metadata/using-metadata-sets.md)を参照してください。

![カスタマイズ可能なドキュメントタイプまたはメタデータセットを作成できます。](./sharing-documents-with-other-users/images/01.png)

<!-- Stopped reviewing here. -Rich --> 

## 統合

ドキュメントとメディアライブラリをMicrosoftOffice365™またはGoogleドライブと統合できます。 詳細は、[Enabling Document Creation and Editing with Microsoft Office 365](../../devops/enabling-document-creation-and-editing-with-microsoft-office-365.md)、[Enabling Links to Google Drive Documents](../../devops/google-drive-integration/enabling-links-to-google-drive-documents.md)、および[Enabling Document Creation and Editing with Google Drive](../../devops/google-drive-integration/enabling-document-creation-and-editing-with-google-drive.md)を参照してください。

最後に、WebDAVを使用して、ファイルマネージャーからドキュメントにアクセスできます。 詳細は、[Accessing Documents with WebDAV](../accessing-documents-with-webdav.md)を参照してください

## ドキュメントとメディアでのワークフローの使用

DXPの[ワークフロー](../../../../process-automation/workflow/introduction-to-workflow.md)を使用して公開設定を管理できます。ドキュメントとメディアに対してワークフローが有効になっている場合、ドキュメントは公開される前にレビューされる必要があります。 ドキュメントとメディアに対しワークフローを有効化する詳細は、[ワークフローのアクティブ化](../../../../process-automation/workflow/using-workflows/activating-workflow.md#activating-workflow-for-specific-applications)を参照してください。

![DMドキュメントのワークフローを有効にできます。](./sharing-documents-with-other-users/images/04.png)

## バックアップとインポート / エクスポート

サイトのドキュメントとメディアライブラリのコンテンツをバックアップまたは[エクスポート](../../../../site-building/building-sites/importing-exporting-pages-and-content.md)できます。 エクスポートされたドキュメントとメディアの`LAR`（Liferayのアーカイブ）ファイルをバックアップとして保存することができます。 必要に応じてそれらを別のサイトにインポートしたり、元のサイトに戻します。

## メディアギャラリー

コンパニオンのメディアギャラリーウィジェットには、ドキュメントとメディアライブラリから選択したコンテンツが表示されます。 このウィジェットは、画像、音声、およびビデオファイルをレンダリングできます。

![メディアギャラリーウィジェットを使用して、画像、音声、およびビデオファイルをレンダリングできます。](./sharing-documents-with-other-users/images/02.png)

詳細は、[メディアギャラリーウィジェットの使用](../publishing-documents.md)を参照してください。

## 追加情報

  - [Documents and Media UI Reference](../../documents-and-media-ui-reference.md)
  - [Creating Folders](../../uploading-and-managing/creating-folders.md)
  - [Uploading Files](../../uploading-and-managing/uploading-files.md)
  - [ファイルストレージの構成](../../../../system-administration/file-storage.md)
