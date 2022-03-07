# DXP CloudでのSSOの使用

顧客はSAML 2.0準拠のシングルサインオンIDプロバイダーを使用して、DXP Cloudプラットフォームに対してユーザーを認証できます。 このドキュメントでは、この統合を有効にするプロセスについて詳しく説明します。

SAMLを使用してSSOを実行するには、クライアント、サービスプロバイダー（SP）、アイデンティティプロバイダー（IdP）の3つのエージェントが必要です。 クライアントがサービスプロバイダーに接続しようとすると、サービスプロバイダーはクライアントをアイデンティティプロバイダーにリダイレクトします。 クライアントがIDプロバイダーによって認証された後、IDプロバイダーはクライアントの資格情報へのアクセスをサービスプロバイダーに許可します。

このシナリオでは、DXP Cloudはサービスプロバイダーとして機能します。 DXP Cloudにログインしようとしているお客様がクライアントです。 IDプロバイダーは、顧客が管理するエンタープライズディレクトリソリューションです。

<a name="enabling-sso-for-a-dxp-cloud-project" />

## DXP CloudプロジェクトのSSOの有効化

DXP CloudプロジェクトでSSOを有効にするには、次の手順を実行する必要があります：

1. [IdPメタデータをDXP Cloudチームに提供する](#provide-identity-provider-metadata-to-the-dxp-cloud-team)
1. [DXP Cloudチームは提供されたIdPデータをインポートし、サービスプロバイダー（SP）メタデータを提供します](#dxp-cloud-team-imports-provided-idp-data-and-provides-service-provider-metadata)
1. [Liferay DXP Cloudチームが提供するSPメタデータをインポートする](#import-sp-metadata-provided-by-the-liferay-dxp-cloud-team)

<a name="provide-identity-provider-metadata-to-the-dxp-cloud-team" />

### IDプロバイダーメタデータをDXP Cloudチームに提供する

DXP CloudプロジェクトでSSOを有効にしたいクライアントは、次の情報を含む必要がある ［**IdP**］ システムのメタデータを提供する必要があります：

| フィールド                              | 説明                                                                                                                            |
| ---------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| IdP発行者                             | ID発行者の名前。通常、 `EntityDescriptor` メタデータの `EntityID` 属性                                                                          |
| IdPシングルサインオンURL                    | SAML認証を受信するリクエストエンドポイント(例：<http://adfs.customer.com/saml/sso)>                                                                |
| IdP署名証明書                           | SAMLメッセージおよびアサーション署名へのIdPの公開鍵証明書                                                                                              |
| IdPシングルサインオンHTTPメソッド（リクエストバインディング） | 認証要求を受信するために顧客のIDプロバイダーによってサポートされるHTTPメソッド。 有効な回答は `POST` （デフォルト）と `GET`のみです。                                                 |
| 署名リクエスト                            | 顧客のIDプロバイダーに送信されたSAMLリクエストに署名する必要がある場合は、 `TRUE`に 設定します。 それ以外の場合は `FALSE`に設定します。                                               |
| 署名アルゴリズムのリクエスト（RSA）                | `Sign Requests` が `TRUE`に設定されている場合は、署名に使用されるアルゴリズムを提供します。 現時点では、SHA-1（非推奨）およびSHA-256をサポートしています。 署名リクエストが無効になっている場合、この設定は不要です。 |

#### ADFS固有の情報

Microsoft ADFSを使用するクライアントは、SAMLを使用してSSOを設定するために必要な次の設定に注意する必要があります。

| フィールド           | 説明                                                                           |
| --------------- | ---------------------------------------------------------------------------- |
| IdP発行者URI       | ［全般］タブの **フェデレーションサービス識別子** にあり、デフォルト値は <http://domain/adfs/services/trust>です。 |
| IdPシングルサインオンURL | デフォルト設定は `/ adfs/ls`です。 例： <http://adfs.example.com/adfs/ls/>                |
| IdP署名証明書        | DERエンコードされたバイナリX.509証明書ファイル                                                  |

IdPメタデータが生成されたら、 [はDXP Cloudチームでチケットを開きます](https://help.liferay.com/hc/) 。 IdPメタデータは、XMLファイルまたはURLエンドポイントのいずれかの形式で送信できます（ [https：// localhost：8080/c/saml/metadata](https://localhost:8080/c/saml/metadata) は基本的な例です）。

<a name="dxp-cloud-team-imports-provided-idp-data-and-provides-service-provider-metadata" />

### DXP Cloudチームは提供されたIdPデータをインポートし、サービスプロバイダーのメタデータを提供します

DXP Cloudチームは、次のSPメタデータ値をクライアントに提供します。

| フィールド                    | 説明                                                                       |
| ------------------------ | ------------------------------------------------------------------------ |
| アサーションコンシューマサービス（ACS）URL | DXP Cloudが受信したSAML応答。 これは常に <https://auth.liferay.cloud>からのアドレスサーバーになります |
| オーディエンスURL               | 顧客のIDプロバイダーにアクセスするために使用されるURL Liferay Cloud                              |

<a name="import-sp-metadata-provided-by-the-liferay-dxp-cloud-team" />

### Liferay DXP Cloudチームが提供するSPメタデータをインポートする

DXP CloudチームからSPメタデータを受け取ったら、SPメタデータ値をIdPに入力します。

<a name="using-sso" />

## SSOの使用

SSOを有効にすると、適切なアイデンティティプロバイダーを持つユーザーがSSOを使用して認証を行うことができます。

```{warning}
   ユーザーがSSOで初めて認証すると、そのユーザーアカウントが変更され、それ以降はSSOを使用して認証する必要があります。
```

SSOを使用してDXP Cloudにログインするには：

1. <https://console.liferay.cloud/login> に移動します。
1. ［**SSO経由でログイン** クリックします 。

   ![ログインページ](./using-sso-with-dxp-cloud/images/01.png)

1. ［**組織ID**］ フィールドに ［**会社名**］ を入力します。
1. ［**続行**］をクリックします。

    ```{note}
       組織のSSOで既に認証されている場合は、次の手順を実行する必要がない場合があります。
    ```

1. ［**Email Address**］ フィールドに **Email Address** を入力します。 これは、会社のデータベースまたはディレクトリサービス（LDAPやADFSなど）に保存されているメールアドレスと同じである必要があります。
1. ［**パスワード**］ フィールドに **パスワード** を入力します。 これは、会社のデータベースまたはディレクトリサービスに保存されているメールアドレスに関連付けられているパスワードと同じである必要があります。
1. ［**ログイン**］をクリックします。

ログインすると、ユーザーはすべてのプロジェクトと環境を確認できます。

![プロジェクトページ](./using-sso-with-dxp-cloud/images/02.png)
