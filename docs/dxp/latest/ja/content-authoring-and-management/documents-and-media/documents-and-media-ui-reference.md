# ドキュメントとメディアUIリファレンス

ドキュメントとメディアアプリケーションは、Liferay DXPインスタンスのファイルを保存、表示、および管理するために使用されます。 これはすべてのサイトで利用可能であり、[アセットライブラリ](../asset-libraries/asset-libraries-overview.md)に対して有効にすることができます。

サイト内のドキュメントとメディアにアクセスするには、 **サイトメニュー**（![Site Menu](../../images/icon-product-menu.png)）を開き、 ［**Content & Data**］ &rarr; ［**ドキュメントとメディア**］ に移動します。

![サイトメニューの［Content & Data］の下にある［ドキュメントとメディア］をクリックします。](./documents-and-media-ui-reference/images/01.png)

アセットライブラリのドキュメントとメディアにアクセスするには、有効になっているライブラリに移動し、 ［**ドキュメントとメディア**］ をクリックします。

![アセットライブラリの［ドキュメントとメディア］をクリックします。](./documents-and-media-ui-reference/images/02.png)

ドキュメントとメディアアプリケーションには、 [［**ドキュメントとメディア**］](#documents-and-media-tab) 、 [［**ドキュメントタイプ**］](#document-types-tab) 、 [［**メタデータセット**］](#metadata-sets-tab) の3つのメインタブがあります。

[アプリケーションバー](#application-bar-settings) の **アクション** ボタン（![Actions Button](../../images/icon-actions.png)）をクリックすると、追加の設定にアクセスできます。

```{note}
ドキュメントとメディアのUIは、アセットライブラリとサイトで同じです。
```

<a name="ドキュメントとメディアタブ" />

## ［ドキュメントとメディア］タブ

![［ドキュメントとメディア］タブでは、アプリケーションの主要機能にアクセスできます。](./documents-and-media-ui-reference/images/03.png)

［**ドキュメントとメディア**］ タブでは、アプリケーションの主要機能にアクセスできます。 ここでは、すべてのデジタルアセット、フォルダ、および接続されたリポジトリを表示、検索、追加、および管理できます。

```{note}
ホームフォルダは、ドキュメントとメディアアプリケーションのルートフォルダとして機能します。
```

**追加** ボタン（![Add Button](../../images/icon-add.png)）をクリックして、次のアクションにアクセスします：［ファイルアップロード］、［複数ファイルのアップロード］、［フォルダ］、［リポジトリ］、［ドキュメントとメディアのショートカット］、［External Video Shortcut］。 詳細については、 [フォルダの作成](./uploading-and-managing/creating-folders.md) および [ファイルのアップロード](./uploading-and-managing/uploading-files.md) を参照してください。

![追加ボタンをクリックして、新しいフォルダまたはデジタルアセットを作成します。](./documents-and-media-ui-reference/images/04.png)

カスタムの [ドキュメントタイプ](#document-types-tab) を作成した場合は、利用可能なオプションとしてここに一覧表示されます。

ダウンロード、編集、移動、または削除するファイルまたはフォルダの **アクション** ボタン（![Actions Button](../../images/icon-actions.png)）をクリックします。 ファイルを **チェックアウト** または **チェックイン** して、ファイルバージョンを作成することもできます。

<a name="ドキュメントタイプタブ" />

## ［ドキュメントタイプ］タブ

![［ドキュメントタイプ］タブで、ファイルアップロード用のカスタムテンプレートを作成します。](./documents-and-media-ui-reference/images/05.png)

ここでは、ドキュメントとメディアに新しいファイルを追加するために使用されるカスタムテンプレートを作成および管理できます。 これらのテンプレートはメタデータフィールドを使用して作成され、アップロードされたファイルの追加の検索可能な情報を提供します。

新しいドキュメントタイプを作成するには、構成可能なメタデータフィールドをドロップゾーンにドラッグアンドドロップします。 説明を追加したり、カスタム [メタデータセット](#metadata-sets-tab) を含めたり、権限を管理したりすることもできます。

![カスタムドキュメントタイプを作成および構成します。](./documents-and-media-ui-reference/images/06.png)

作成したら、テンプレートを使用して、［ドキュメントとメディア］タブで新しいデジタルアセットを追加できます。 詳細は、 [ドキュメントタイプの定義](./uploading-and-managing/managing-metadata/defining-document-types.md) を参照してください。

<a name="メタデータセットタブ" />

## ［メタデータセット］タブ

![［メタデータセット］タブでデータフィールドのカスタムグループを作成します。](./documents-and-media-ui-reference/images/07.png)

ここでは、カスタムドキュメントタイプに追加できるメタデータフィールドの再利用可能なグループを定義できます。 これらのメタデータセットは、メタデータフィールドを指定されたドロップゾーンにドラッグアンドドロップすることにより、ドキュメントタイプと同じ方法で構成されます。

![フィールドを使用して、再利用可能なメタデータセットを作成します。](./documents-and-media-ui-reference/images/08.png)

作成したメタデータセットは、カスタムドキュメントタイプに追加できます。 詳細は、 [メタデータセットの定義](./uploading-and-managing/managing-metadata/defining-metadata-sets.md) を参照してください。

<a name="アプリケーションバーの設定" />

## アプリケーションバーの設定

![アプリケーションバーでこれらのドキュメントとメディアの設定にアクセスします。](./documents-and-media-ui-reference/images/09.png)

アプリケーションバーから、次のドキュメントとメディアの設定にアクセスできます。

### デスクトップからアクセスする

ファイルエクスプローラーで **ドキュメントとメディア** リソースにアクセスするためのWebDAV URLを生成します。

![WebDAV URLを生成します。](./documents-and-media-ui-reference/images/10.png)

### Edit

すべてのドキュメントタイプの[ワークフロー](../../process-automation/workflow/using-workflows/activating-workflow.md)を有効または無効にします。 一般的なワークフローの詳細については、 [ワークフローの概要](../../process-automation/workflow/introduction-to-workflow.md) を参照してください。

![ワークフローの定義を選択します。](./documents-and-media-ui-reference/images/11.png)

### ホームフォルダー権限

ドキュメントとメディアの **フォームフォルダ** の権限を管理します。 一般的な権限の詳細については、 [ロールと権限について](../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md) を参照してください。

![ドキュメントとメディアのホームフォルダの権限を管理します。](./documents-and-media-ui-reference/images/12.png)

### エクスポート / インポート

ドキュメントとメディアコンテンツの[エクスポートまたはインポート](../../site-building/building-sites/importing-exporting-pages-and-content.md)操作を開始します。 コンテンツは`LAR`（Liferayアーカイブ）ファイルとしてエクスポートされます。 現在および以前のエクスポートを表示することもできます。

![ドキュメントとメディアのエクスポートまたはインポート操作を開始します。](./documents-and-media-ui-reference/images/13.png)

### 設定

ドキュメントとメディアのメール通知を設定します。 メール通知を設定する場合、情報の解析にデフォルトのプレースホルダーを使用できます（`[$COMPANY_ID$]`、`[$DOCUMENT_TYPE$]`、`[$TO_NAME$]`など）。 使用可能なプレースホルダーは、 ［**項目の定義**］ の下の各タブに表示されます。

```{note}
メール通知を使用するには、接続されたメールサーバーが必要です。 詳細については、 [Connecting to a Mail Server](../../installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md) を参照してください。
```

［**送信者］タブ** ：メールの送信者に使用する名前とメールアドレスを入力します。

![送信者の名前とメールアドレスを入力します。](./documents-and-media-ui-reference/images/14.png)

**ドキュメント追加時のメール** ：ドキュメントが追加されたときのメール通知を有効/無効にし、使用するメールを作成します。

![ドキュメントが追加されたときに送信されるメールを作成します。](./documents-and-media-ui-reference/images/15.png)

**ドキュメント更新時のメール** ：既存のドキュメントが更新されたときのメール通知を有効/無効にし、使用するメールを作成します。

![既存のドキュメントが更新されたときに送信されるメールを作成します。](./documents-and-media-ui-reference/images/16.png)

<a name="追加情報" />

## 追加情報

* [「ドキュメントとメディア」の概要](./documents-and-media-overview.md)
* [Importing/Exporting Sites and Contents](../../site-building/building-sites/importing-exporting-pages-and-content.md)
