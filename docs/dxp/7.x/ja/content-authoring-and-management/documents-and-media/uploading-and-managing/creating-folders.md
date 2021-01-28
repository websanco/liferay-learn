# フォルダの作成

ドキュメントとメディアフォルダを使用して、ファイルを整理できます。 必要な権限を持つ認証されたユーザーのみがフォルダを管理できます。 詳細については、[Documents and Media Permissions Reference](../publishing-and-sharing/managing-document-access/documents-and-media-permissions-reference.md)を参照してください。 DXPのロールと権限の詳細については、[Roles and Permissions](https://help.liferay.com/hc/articles/360017895212-Roles-and-Permissions)をご覧ください 。

## フォルダを追加する

フォルダを追加する最も簡単な方法は、サイトページにデプロイされた*[Documents and Media]* ウィジェットを使用することです。

1.  *[Documents and Media]* ウィジェットがデプロイされているサイトページに移動します。

2.  ウィジェットのタイトルメニューの上にマウスを置き、*追加*アイコン（![Add](../../../images/icon-add.png)）をクリックして*[Folder]* を選択します。

    ![ウィジェットにフォルダを追加する](./creating-folders/images/01.png)

3.  フォルダの名前を入力します。

4.  フォルダの説明を入力します。

    ![ウィジェットにフォルダを追加する](./creating-folders/images/02.png)

5.  *[保存]* をクリックします。

フォルダを作成すると、ドキュメントライブラリに表示されます。 ユーザーはサブフォルダを作成することもできます。 サブフォルダを作成するには、親フォルダとして指定されているフォルダをクリックして、この手順を繰り返します。

## ドキュメントタイプの制限とワークフロー

フォルダを作成した後、そのフォルダで許可されるドキュメントのタイプを制限できます。 フォルダに追加または編集されたファイルの承認に使用する[ワークフロー](../../../process-automation/workflow/user-guide/introduction-to-workflow.md)（ある場合）を選択することもできます。

フォルダのドキュメントタイプの制限を変更するには：

1.  サイトの*サイト管理*メニューに移動します。

2.  *[Content & Data]* → *[Documents and Media]* の順にクリックします。

3.  フォルダの*アクション*メニュー（![Actions](../../../images/icon-actions.png)）をクリックし、*[Edit]* を選択します。

4.  *[Document Type Restrictions and Workflow]* セクションを展開します。

5.  *[Define Specific Document Type Restrictions and Workflow for this Folder（現在のフォルダ）]* をクリックします。

6.  *[Select Document Type]* をクリックします。

    ![ドキュメントタイプの設定](./creating-folders/images/03.png)

7.  このフォルダに関連付けるドキュメントタイプの横にある*[Choose]* をクリックします。

8.  このフォルダに関連付けるワークフローを選択します。

    ![ドキュメントタイプを選択](./creating-folders/images/04.png)

9.  完了したら、*[保存]* をクリックします。

## 追加情報

  - [Activating Workflow](../../../process-automation/workflow/user-guide/activating-workflow.md#documents-and-media-folders)
  - [Uploading Files](./uploading-files.md)
  - [Using the Media Gallery](../publishing-and-sharing/publishing-documents-on-a-dxp-site/using-the-media-gallery-widget.md)
  - [Enabling Xuggler and ImageMagick for previews](../../../system-administration/using-the-server-administration-panel/configuring-external-services.md)
