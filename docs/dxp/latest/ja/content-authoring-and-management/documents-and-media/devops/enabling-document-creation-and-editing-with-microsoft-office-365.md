# Microsoft Office 365でドキュメントの作成と編集を有効にする

Office 365&trade;を使用してドキュメントとメディアファイルを作成および編集する前に、 [Azureポータル](https://portal.azure.com) でアプリケーションに接続するようにDXPを設定する必要があります。 次の手順を実行するには、管理者権限が必要です。

<a name="アプリケーションをmicrosoft-identity-platformに登録する" />

## アプリケーションをMicrosoft Identity Platformに登録する

まず、アプリケーションをMicrosoft identity platform&trade;で設定します。 これを行うには、 [Microsoftのドキュメンテーション](https://docs.microsoft.com/en-gb/graph/auth-register-app-v2) に記載されている手順に従ってください。

**リダイレクトURI** パラメーターのURLを作成するには、次のパターンに従います：`https://[hostname]/o/document_library/onedrive/oauth2`

以下は、Office 365&trade;統合を使用するために必要な最小限の権限セットです。

* `Files.Read.All`
* `Files.ReadWrite.All`

権限の詳細は、 [Microsoftのドキュメンテーション](https://docs.microsoft.com/graph/permissions-reference) を参照してください。

<a name="dxpの設定" />

## DXPの設定

まず、DXPインストールをMicrosoft identity platform&trade;アプリケーションに接続する必要があります。 これは、次の2つのスコープで実行できます。

* グローバルにすると、DXPインストールのすべてのインスタンスに対し有効になります。
* インスタンススコープでは、DXPインストール内の1つ以上のインスタンスに対し有効になります。

これらのインスタンスを個別に設定することにより、1つ以上のインスタンスのグローバル設定をオーバーライドできます。 同様に、アプリケーションに接続するインスタンスのみを設定し、グローバル設定を空のままにすることができます。

### グローバルレベルの設定

1. ［**コントロールパネル**］ &rarr; ［**システム設定**］ &rarr; ［**ドキュメントとメディア**］ に移動します。

1. 左側のメニューで ［**OneDrive**］ をクリックします。

    ![クライアントID、クライアント・シークレット、およびテナントを入力します。](./enabling-document-creation-and-editing-with-microsoft-office-365/images/01.png)

1. アプリケーションのOAuth 2クライアントIDとクライアント・シークレットをそれぞれ ［**クライアントID**］ フィールドと ［**クライアント・シークレット**］ フィールドに入力します。
1. ［**テナント**］ フィールドにテナントIDを入力します。 テナントIDを見つけるには、 [Microsoftのドキュメンテーション](https://docs.microsoft.com/onedrive/find-your-office-365-tenant-id) を参照してください。
1. ［**保存**］ をクリックします。

アクセスはグローバルレベルで許可されます。

### インスタンスレベルの設定

1. ［**コントロールパネル**］ &rarr; ［**Instance Settings**］ &rarr; ［**コンテンツとデータ］ &rarr;**［ ドキュメントとメディア］*に移動します。

1. 左側のナビゲーションメニューで ［**OneDrive**］ をクリックします。

    ![インスタンスレベルの対応するフィールドに認証情報を入力します。](./enabling-document-creation-and-editing-with-microsoft-office-365/images/02.png)

1. アプリケーションのOAuth 2クライアントIDとクライアント・シークレットをそれぞれ ［**クライアントID**］ フィールドと ［**クライアント・シークレット**］ フィールドに入力します。
1. ［**テナント**］ フィールドにテナントIDを入力します。 テナントIDを見つけるには、 [Microsoftのドキュメンテーション](https://docs.microsoft.com/onedrive/find-your-office-365-tenant-id) を参照してください。
1. ［**保存**］ をクリックします。

 **注：** 有効にしたら、フォームからクライアントID、クライアント・シークレット、およびテナントの値を削除することで、この機能を無効にできます。

<a name="追加情報" />

## 追加情報

* [Googleドライブでドキュメントの作成と編集を有効にする](./google-drive-integration/enabling-document-creation-and-editing-with-google-drive.md)
* [Microsoft Office 365を使用したドキュメントの作成と編集](../uploading-and-managing/creating-documents/creating-and-editing-documents-with-microsoft-office-365.md)
