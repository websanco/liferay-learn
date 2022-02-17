# Googleドライブドキュメントへのリンクを有効にする

Liferayは、ドキュメントとメディアをGoogleドライブと統合するためのマーケットプレイスプラグインを提供しています。 有効にすると、Googleドライブファイルへのショートカットを作成して、ドキュメントとメディアを介してファイルを表示および管理できるようになります。 このプラグインは、 [Liferay Portal CE](https://web.liferay.com/marketplace/-/mp/application/105847499) システムと [Liferay DXP](https://web.liferay.com/marketplace/-/mp/application/98011653) システムの両方で使用できます。 Googleドライブ用のLiferayプラグインをインストールする方法については、[Installing Apps](../../../../system-administration/installing-and-managing-apps/installing-apps/installing-apps.md)を参照してください。

インストール後、Googleプロジェクトをセットアップし、Liferayのドキュメントとメディアアプリケーションを設定する必要があります。

```{important}
Googleドライブ用のLiferayプラグインは、Liferay CEポータルとLiferay DXPで利用できるLabsアプリケーションです。 Labsアプリは実験的なものであり、Liferayではサポートされていません。 これらは、便利な機能や最先端の機能の提供を加速するためにリリースされています。 この状態は予告なく変更されることがあります。 Labsアプリはご自身の判断でお使いください。
```

<a name="setting-up-your-google-project" />

## Googleプロジェクトの設定

Liferayプラグインを有効にするには、GoogleドライブAPIとGoogle Picker APIの両方が有効になっているアクティブなGoogleプロジェクトが必要です。 GoogleドライブAPIを使用すると、ユーザーはネイティブエディタでGoogleファイル（ドキュメント、シート、スライドなど）を表示および編集できます。 Picker APIを使用すると、ユーザーは、ドキュメントとメディアにファイルをアップロードするときに、Googleドライブに保存されているデジタルアセット（写真、ビデオ、ドキュメントなど）を表示して選択できます。 プロジェクトをまだ作成していない場合は、 [Creating a Google Project](https://support.google.com/googleapi/answer/6251787?hl=en&ref_topic=7014522) を参照してください。 それ以外の場合は、 [Enabling APIs](https://support.google.com/googleapi/answer/6158841) を参照して、プロジェクトでPicker APIを有効にする方法を確認してください。

```{note}
必要に応じて、Googleドライブ用のLiferayプラグインとは別に、ドキュメントとメディアを介してGoogleドキュメントの作成と編集を有効にすることができます。 詳細は、［Enabling Document Creation and Editing with Google Drive］(./enabling-document-creation-and-editing-with-google-drive.md)を参照してください。
```

プロジェクトでGoogle Picker APIを有効にした後、Picker APIの [APIキーを生成](https://support.google.com/googleapi/answer/6158862) し、 [OAuth 2を設定](https://support.google.com/cloud/answer/6158849) します。 OAuth 2の設定には、OAuth同意画面の設定、OAuth 2認証情報の生成、および承認されたJavaScriptオリジンのリストへのLiferayリクエストURIの追加が含まれます。 OAuth 2の認証情報を生成する際には、アプリケーションの種類として、 **Webアプリケーション** を選択していることを確認してください。

Googleプロジェクトの準備ができたら、Liferayコントロールパネルを介してドキュメントとメディアアプリケーションを設定する必要があります。 これには、Picker APIキー、およびOAuth2クライアントIDとクライアント・シークレットが必要です。

<a name="configuring-documents-and-media" />

## ドキュメントとメディアの設定

次の手順に従って、ドキュメントとメディアアプリケーションを設定します。

1. **グローバルメニュー**（![Global Menu](../../../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブをクリックして、 ［**システム設定**］ &rarr; ［**ドキュメントとメディア**］ に移動します。

1. ［仮想インスタンススコープ］の下の ［**Google ドライブ**］ をクリックします。

    ![［仮想インスタンススコープ］の下の［Google ドライブ］をクリックします。](./enabling-links-to-google-drive-documents/images/01.png)

1. プロジェクトのOAuth 2.0の **クライアントID** と **クライアント・シークレット** を入力します。

1. **Picker APIキー** を入力します。

1. ［**保存**］ をクリックします。

有効にすると、ユーザーはGoogleアセットへのドキュメントとメディアのショートカットを作成できます。

```{note}
この機能を無効にするには、Googleドライブの*アクション*ボタンをクリックし、*デフォルト値をリセットする*を選択します。
```

<a name="creating-google-drive-shortcuts" />

## Googleドライブのショートカットの作成

次の手順に従って、Googleドライブファイルへのドキュメントとメディアのショートカットを作成します。

1. サイトアセットライブラリでドキュメントとメディアアプリケーションを開きます。

1. ［ドキュメントとメディア］タブで **追加** ボタン（![Add Button](../../../../images/icon-add.png)）をクリックし、 ［**Google Drive Shortcut**］ を選択します。

   ![追加ボタンをクリックして、［Google Drive Shortcut］を選択します。](./enabling-links-to-google-drive-documents/images/02.png)

1. ［**ファイルの選択**］ をクリックします。

   ![［ファイルの選択］をクリックします。](./enabling-links-to-google-drive-documents/images/03.png)

1. 目的のGoogleアカウントを選択します。

1. 目的のGoogleドライブファイルを選択します。

   ![目的のGoogleドライブファイルを選択します。](./enabling-links-to-google-drive-documents/images/04.png)

1. ショートカットの **名前** と **説明** を入力します。

1. 完了したら、 ［**Publish**］ をクリックします。

公開されると、ドキュメントとメディアアプリケーションを介してGoogleドライブファイルにアクセスし、ファイルをLiferayで使用できるようになります。

<a name="additional-information" />

## 追加情報

* [Googleドライブでドキュメントの作成と編集を有効にする](./enabling-document-creation-and-editing-with-google-drive.md)
