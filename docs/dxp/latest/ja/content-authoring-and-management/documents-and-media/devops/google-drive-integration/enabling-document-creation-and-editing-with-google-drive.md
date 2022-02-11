# Googleドライブでドキュメントの作成と編集を有効にする

Liferay DXPはGoogleドライブとの統合を提供しているため、ドキュメントとメディアアプリケーションを介してGoogleドキュメント、シート、およびスライドを作成および編集できます。 これらのドキュメントタイプのいずれかを追加または編集すると、ファイルを作成できるGoogleの外部エディタにリダイレクトされます。

![ドキュメントを介してドキュメント、シート、スライドを作成します。](./enabling-document-creation-and-editing-with-google-drive/images/01.png)

```{note}
この統合は、グローバルに（つまり、DXPインストール内のすべてのポータルインスタンスに対して）、または個々のインスタンスに対して有効にすることができます。 インスタンスレベルでの設定は、グローバル設定よりも優先されます。
```

## 前提条件

LiferayのGoogleドライブ統合を有効にするには、GoogleドライブAPIが有効で、OAuth 2が適切に設定されたアクティブなGoogleプロジェクトが必要です。 OAuth 2を設定するには、OAuth同意画面を設定し、OAuth 2認証情報を生成し、LiferayリクエストURIをOAuthクライアントの承認済みURIのリストに追加します。

OAuth 2の認証情報を生成する際には、アプリケーションの種類として、*Webアプリケーション*を選択していることを確認してください。 生成されたら、ドキュメントとメディアを設定するための認証情報のクライアントIDとクライアント・シークレットが必要になります。

必要に応じて、Google Cloudのドキュメンテーションを参照してください。

* [Creating a Google Project](https://support.google.com/googleapi/answer/6251787?hl=en&ref_topic=7014522)
* [Enabling APIs for a Project](https://support.google.com/googleapi/answer/6158841)
* [Setting Up Your OAuth Consent Screen](https://support.google.com/cloud/answer/10311615?hl=en)
* [Setting Up OAuth 2](https://support.google.com/cloud/answer/6158849)

Googleプロジェクトの準備ができたら、Liferayのドキュメントとメディアアプリケーションを設定できます。

## ドキュメントとメディアの設定

以下の手順に従って、LiferayとGoogleドライブの統合を有効にします。

1. *グローバルメニュー*（![Global Menu](../../../../images/icon-applications-menu.png)）を開き、*［コントロールパネル］*タブをクリックして、*［システム設定］* &rarr; *［ドキュメントとメディア］*に移動します。

1. ［仮想インスタンススコープ］の下の*［Google ドライブ］*をクリックします。

    ![［仮想インスタンススコープ］の下の［Google ドライブ］をクリックします。](./enabling-document-creation-and-editing-with-google-drive/images/02.png)

1. GoogleプロジェクトのOAuth 2*クライアントID*と*クライアント・シークレット*を入力します。

   ```{note}
   ［Google Picker API］(https://developers.google.com/picker/docs)を使用すると、ユーザーはLiferayからGoogleに保存されている写真、ビデオ、マップ、ドキュメントを選択できます。 これは、LiferayのGoogleドライブ用プラグインを使用する場合にのみ必要です。 詳細は、［Enabling Links to Google Drive Documents］(./enabling-links-to-google-drive-documents.md)を参照してください。
   ```

1. *［保存］* をクリックします。

有効にすると、承認されたユーザーはドキュメントとメディアを介してGoogleドキュメント、スライド、シートを作成できます。

```{note}
この機能を無効にするには、Googleドライブの*アクション*ボタンをクリックし、*デフォルト値をリセットする*を選択します。
```

## 追加情報

* [Googleドライブドキュメントへのリンクを有効にする](./enabling-links-to-google-drive-documents.md)
* [Googleドライブでのドキュメントの作成と編集](../../uploading-and-managing/creating-documents/creating-and-editing-documents-with-google-drive.md)
