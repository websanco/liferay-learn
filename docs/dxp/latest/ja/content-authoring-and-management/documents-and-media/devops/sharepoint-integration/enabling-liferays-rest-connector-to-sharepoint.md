# LiferayのREST Connector to SharePointの有効化

> DXPサブスクリプションが必要です。 SharePoint 2016およびSharePoint Onlineと互換性があります

LiferayのREST Connector to SharePointを使用すると、ドキュメントとメディアをSharePointライブラリと統合できます。 これを使用すると、SharePointライブラリをマウントするためのドキュメントとメディアリポジトリを作成できます。 その後、SharePointドキュメントとフォルダの読み取り/書き込み、ドキュメントのチェックイン/チェックアウトの実行、ドキュメントのダウンロードなどを行うことができます。 一方のコンテキストで実行された各アクションは、もう一方のコンテキストにプロパゲートされます。

<a name="integration-limitations" />

## 統合の制限

このアプリケーションはSharePointのAPIを使用し、次の制限があります。

* 最初にチェックアウトせずにファイルを移動または名前変更すると、バージョン履歴が失われます。
* ファイル拡張子を変更することはできません。変更できるのはファイル名のみです。
* ファイルの現在の名前は、以前のすべてのバージョンにプロパゲートされます。
* ファイルをチェックアウトするユーザーは、そのファイルの作業コピーのバージョン番号を確認できる唯一のユーザーです。
* サフィックスまたは中間ワイルドカードのクエリは、包含のクエリに変換されます。
* コメント、評価、およびSharePointフォルダをドキュメントとメディアのルートフォルダとして使用することはサポートされていません。

RESTコネクターの動作の詳細は、 [javadocs API reference](https://docs.liferay.com/dxp/apps/sharepoint-rest/latest/javadocs/) を参照してください。

<a name="installing-the-rest-connector" />

## REST コネクターのインストール

次の手順に従って、Liferay REST Connector to SharePointをインストールします。

1. Liferayマーケットプレイスから [Liferay REST Connector to SharePoint](https://web.liferay.com/marketplace/-/mp/application/105406871) をLPKGファイルとしてダウンロードします。

1. ファイルをインスタンスの[`[LIFERAY_HOME]/deploy`](../../../../installation-and-upgrades/reference/liferay-home.md)フォルダにコピーし、[デプロイ](../../../../system-administration/installing-and-managing-apps/installing-apps/installing-apps.md)します。

   成功すると、コンソールのログに次のメッセージが表示されます。

   ```
   Processing Liferay REST Connector to SharePoint.lpkg
   The portal instance needs to be restarted to complete the installation of file:/opt/liferay/osgi/marketplace/Liferay%20REST%20Connector%20to%20SharePoint%20-%20API.lpkg
   ```

1. サーバーを再起動してインストールを完了します。

コネクターが正常にデプロイおよびインストールされたら、SharePoint OAuth2設定エントリーを追加してセットアップを完了できます。 必要に応じて、カスタム検索ソースを定義して、SharePoint検索結果を制限および微調整することもできます。

```{important}
   RESTコネクターはOAuth2によるSharePointサーバー認証を行うAzure ACSを使用するため、サーバーでHTTPSサポートが有効になっていることを確認してください。
```

<a name="updating-to-the-latest-version" />

## 最新バージョンへの更新

以前は、Liferay REST Connector to SharePointはLiferay Connector for SharePointと呼ばれていました。 古いバージョンのアプリケーションがLiferayサーバーにインストールされている場合は、次の手順に従って最新バージョンをインストールしてください。

1. Liferayマーケットプレイスから [Liferay REST Connector to SharePoint](https://web.liferay.com/marketplace/-/mp/application/105406871) をLPKGファイルとしてダウンロードします。

1. Liferay DXPサーバーを停止します。

1. [`[LIFERAY_HOME]/osgi/marketplace`](../../../../installation-and-upgrades/reference/liferay-home.md)フォルダに移動し、古いLPKGファイルを新しいファイルに置き換えます。

1. Liferay DXPサーバーを再起動します。

サーバーの再起動が完了すると、アプリケーションを使用できるようになります。

<a name="adding-a-sharepoint-oauth2-configuration" />

## Sharepoint OAuth2設定の追加

以下の手順に従って、Liferayコントロールパネルから新しいSharePoint OAuth2設定を追加します。

1. **グローバルメニュー**（![Global Menu](../../../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブをクリックして、 ［**システム設定**］ &rarr; ［**Documents & Media**］ &rarr; ［**SharePoint OAuth2**］ に移動します。

   ![［コントロールパネル］で［システム設定］ > ［Documents & Media］ > ［SharePoint OAuth 2］に移動します。](./enabling-liferays-rest-connector-to-sharepoint/images/01.png)

1. **追加** をクリックして、新しいSharePoint OAuth2設定エントリーを作成します。

1. フォームに記入します（以下を参照）。

1. 完了したら、 ［**保存**］ をクリックします。

**Name** ：設定の名前

**認証許可エンドポイント** ：OAuth2権限付与を要求するために使用されるURL（例：SharePoint Onlineの場合は`https://[your-site-name]/sharepoint.com/_layouts/oauthauthorize.aspx`）

**認証トークンエンドポイント** ：ACSのURL（例：SharePoint Onlineの場合は`https://accounts.accesscontrol.windows.net/［App ID]/tokens/OAuth/2`）

**クライアントID** ：クライアントID

**クライアント・シークレット** ：クライアント・シークレット

**スコープ** ：トークンに必要な権限セット。有効なスコープは、RESTコネクターの登録中に構成されます

**テナントID** ：テナントID

**サイトドメイン** ：アプリケーションに登録されているサイトドメイン

**リソース** ：この値は、使用するACSサービスに依存します（例：SharePoint Onlineの場合は`00000003-0000-0ff1-ce00-000000000000/［your-site-name].sharepoint.com@［tenant ID]`）

保存すると、SharePoint設定を使用して、ドキュメントとメディアにSharePointライブラリにマウントされるリポジトリを作成できます。

<a name="adding-a-sharepoint-search-configuration" />

## Sharepoint検索設定の追加

SharePointを使用すると、カスタムソースIDを定義して、検索結果を制限および微調整できます。 デフォルトでは、コネクターはドキュメントのソースIDを使用します。

次の手順に従って、別のソースIDでSharePoint検索設定を作成します。

1. **グローバルメニュー**（![Global Menu](../../../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブをクリックして、 ［**システム設定**］ &rarr; ［**Documents & Media**］ &rarr; ［**Sharepoint 検索**］ に移動します。

   ![［コントロールパネル］で［システム設定］ > ［Documents & Media］ > ［SharePoint OAuth 2］に移動します。](./enabling-liferays-rest-connector-to-sharepoint/images/02.png)

1. **追加** をクリックして、新しいSharePoint検索設定エントリーを作成します。

   ![追加をクリックして、新しいSharePoint検索設定エントリーを作成します。](./enabling-liferays-rest-connector-to-sharepoint/images/03.png)

1. 新しいエントリーのカスタムSharepoint 検索結果の SourceIDを入力します。

1. 完了したら、 ［**保存**］ をクリックします。

<a name="additional-information" />

## 追加情報

* [アプリのインストール](../../../../system-administration/installing-and-managing-apps/installing-apps/installing-apps.md)
* [ドキュメントとメディアでのSharePointリポジトリの作成](./creating-sharepoint-repositories-in-documents-and-media.md)
