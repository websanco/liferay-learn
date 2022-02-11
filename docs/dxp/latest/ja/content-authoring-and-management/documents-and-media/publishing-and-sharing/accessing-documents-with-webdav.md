# WebDAVを使用したドキュメントへのアクセス

デスクトップファイルマネージャーから [WebDAV](https://en.wikipedia.org/wiki/WebDAV) を介してドキュメントライブラリにアクセスできます。  WebDAVは、Webサーバーに保存されているファイルを管理するためのHTTPに基づくプロトコルです。 WebDAVは、Linux、macOS、Windowsなど、ほとんどの主要なオペレーティングシステムとデスクトップ環境でサポートされています。

ファイルブラウザからドキュメントライブラリフォルダにアクセスするには、ログイン認証情報とアクセスするフォルダのWebDAV URLを使用する必要があります。 次の手順を実行します：

1. アクセスするフォルダが含まれているドキュメントとメディアアプリに移動します。 フォルダのアクションアイコン（![Actions](../../../images/icon-actions.png)）をクリックし、 ［**デスクトップからアクセスする**］ を選択します。

    ![［デスクトップからアクセスする］を選択して、フォルダのWebDAV URLを取得します。](./accessing-documents-with-webdav/images/01.png)

1. WebDAV URLをコピーし、オペレーティングシステムの指示に従います。

    ***Windows：** ネットワークドライブのドライブをWebDAV URLにマップします。 プロンプトが表示されたら、認証情報を入力します。 ドキュメントライブラリフォルダがネットワークドライブに表示されます。 ファイルブラウザから、このフォルダ内のファイルを追加、編集、移動、または削除できるようになりました。

    ***macOS：** Finderで、 ［**Go**］ &rarr; ［**Connect to Server**］ を選択します。 ［Server Address］フィールドに、アクセスするフォルダのWebDAV URLを入力し、 ［**Connect**］ をクリックして、プロンプトが表示されたら認証情報を入力します。

    ***Linux：** ファイルマネージャーで、ドキュメントライブラリフォルダのWebDAV URLを少し変更する必要があります。 KDE Dolphinの場合は、URLのプロトコルを`http://`ではなく`webdav://`に変更してください。 GNOMEファイルの場合は、URLのプロトコルを`http://`ではなく`dav://`に変更してください。 次に、 **Enter** を押し、プロンプトが表示されたら認証情報を入力します。

これで、デスクトップファイルシステムからドキュメントライブラリフォルダにアクセスできるようになりました。 ファイルシステムのこのフォルダにあるファイルを編集すると、ポータルの同じドキュメントライブラリフォルダにも変更が表示されます。 さらに、編集によりファイルのマイナーバージョンがインクリメントされます。

<a name="追加情報" />

## 追加情報

* [Enabling and Configuring Document Sharing](./managing-document-access/enabling-and-configuring-sharing.md)
* [他のユーザーとのドキュメントの共有](./managing-document-access/sharing-documents-with-other-users.md)
* [フォルダの作成](../uploading-and-managing/creating-folders.md)
* [ドキュメントとメディアUIリファレンス](../documents-and-media-ui-reference.md)
