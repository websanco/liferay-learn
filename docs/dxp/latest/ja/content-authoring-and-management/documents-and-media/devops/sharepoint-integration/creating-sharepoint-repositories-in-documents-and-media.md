# ドキュメントとメディアでのSharePointリポジトリの作成

[SharePoint OAuth2設定](./enabling-liferays-rest-connector-to-sharepoint.md#adding-a-sharepoint-oauth2-configuration) を追加すると、それを使用して、SharePointライブラリにマウントされるドキュメントとメディアにリポジトリを作成できます。

次に、以下の手順を実行します。

1. サイトまたはアセットライブラリでドキュメントとメディアアプリケーションを開きます。

1. **追加**（![Add Button](../../../../images/icon-add.png)）をクリックし、 ［**リポジトリ**］ を選択します。

1. リポジトリの名前と説明を入力します。

1. フォームのフィールドに入力します（以下を参照）。

1. 新しいドキュメントとメディアSharePointリポジトリの権限を定義します。

1. 完了したら、 ［**保存**］ をクリックします。

**リポジトリタイプ** ：目的のSharePointリポジトリ設定を選択します。

**サイトの絶対URL** ：相対URLを解決するための絶対URLを入力します（例：SharePoint Onlineの場合は`https://［your-site-name].sharepoint.com`）。

**ライブラリへのパス** ：ドキュメントとメディアにマウントするSharePointドキュメントライブラリを指すサイトの絶対URLからの相対パスを入力します。

適切な権限を持つユーザーは、SharePointドキュメントとフォルダの読み取り/書き込み、ドキュメントのチェックイン/チェックアウトの実行、ドキュメントのダウンロードなどを行うことができます。 一方のコンテキストで実行された各アクションは、もう一方のコンテキストにプロパゲートされます。

<a name="additional-information" />

## 追加情報

* [LiferayのREST Connector to SharePointの有効化](./enabling-liferays-rest-connector-to-sharepoint.md)
