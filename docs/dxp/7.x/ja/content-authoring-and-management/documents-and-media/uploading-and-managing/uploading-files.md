# ファイルのアップロード

*ドキュメントとメディア*アプリケーションは、ユーザーがDXPインスタンス内でファイルをネイティブに保存できる場所です。 特定のファイル形式が許可されていない場合を除き、ユーザーはドキュメント、画像、動画など、ほとんどのタイプのファイルをアップロードできます。

適切な権限を持つユーザーは、単一のファイルまたは複数のファイルを同時にアップロードできます。 詳細については、[Documents and Media Permissions Reference](../publishing-and-sharing/managing-document-access/documents-and-media-permissions-reference.md)を参照してください。 DXPのロールと権限の詳細については、[Roles and Permissions](https://help.liferay.com/hc/articles/360017895212-Roles-and-Permissions)をご覧ください 。

## 単一ファイルのアップロード

ファイルをアップロードする最も簡単な方法は、サイトページにデプロイされた*[Documents and Media]* ウィジェットを使用することです。 ルートフォルダまたは特定のフォルダ内にファイルをアップロードできます。

1.  *[Documents and Media]* ウィジェットがデプロイされているサイトページに移動します。

2.  ファイルを配置するフォルダをクリックします。

3.  ウィジェットのタイトルメニューの上にマウスを置き、*追加*アイコン（![Add](../../../images/icon-add.png)）をクリックして*[File Upload]* を選択します。

4.  *[Browse...]* ボタンをクリックして、ファイルがローカルにある場所に移動します。

5.  ファイルを選択して*[Open]* をクリックします。

6.  デフォルトでは、*[Title]* フィールドにはファイル名が自動的に入力されます。 ユーザーはファイルをサイトに公開する前にいつでも名前を変更できます。

7.  説明を入力します。

    ![PDFをアップロードします。](./uploading-files/images/01.png)

8.  完了したら、*[Publish]* をクリックします。

ファイルが公開されると、サイトにアップロードされます。 ファイル自体をクリックすると、ウィジェット内にプレビューが生成されます。 ファイルをダウンロードするか、ファイルのURLを共有できます。 ユーザーの権限に応じて、ファイルをロックして他のユーザーがファイルを変更できないようにすることもできます。

## 複数のファイルのアップロード

複数のファイルを一度にアップロードするには：

1.  ファイルを公開するフォルダを選択します。

2.  ウィジェットのタイトルメニューの上にマウスを置き、*追加*アイコン（![Add](../../../images/icon-add.png)）をクリックして*[Multiple Files Upload]* を選択します。

    ![一度に複数のファイルをアップロードする](./uploading-files/images/03.png)

3.  目的のファイルをドラッグアンドドロップします。 ユーザーは公開する前にファイルのドキュメントタイプを更新できます。 ファイルはまだサイトに公開されていません。

4.  説明を入力します。

5.  *[Publish]* をクリックします。

これでファイルがサイトに公開されます。

## 追加情報

  - [Creating Folders](./creating-folders.md)
  - [Media Galleryの使用](../publishing-and-sharing/publishing-documents-on-a-dxp-site/using-the-media-gallery-widget.md)
  - [Enabling Xuggler and ImageMagick for previews](../../../system-administration/using-the-server-administration-panel/configuring-external-services.md)
