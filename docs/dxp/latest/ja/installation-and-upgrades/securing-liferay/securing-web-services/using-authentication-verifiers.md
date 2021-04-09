# Authentication Verifierの使用

Authentication Verifierは、一元化された拡張可能な方法でLiferay PortalのAPIのリモート呼び出しを認証します。 2つの主な責任があります：

1.  登録済みの`AuthVerifier`インスタンスを使用して、提供された資格情報を確認します
2.  検証結果に基づいてポータル承認コンテキストを作成します

提供された資格情報がユーザーアカウントと一致すると判断できる利用可能な`AuthVerifier`がない場合は、非認証アクセスをサポートする承認コンテキストがゲストユーザー用に作成されます。 これにより、各APIは単一のAPIエンドポイントのみを公開できます。 対照的に、Liferay Portalのレガシー（6.2より前）バージョンでは、各APIに対して2つのAPIエンドポイントを公開していました。`/api/endpoint` URIは非認証アクセス用で、URI `/api/secure/endpoint`は認証済みアクセス用でした。

リモートクライアントがHTTP基本認証またはHTTPダイジェスト認証を使用する場合、要求パラメーターで資格情報を送信する場合、認証された`JSESSIONID`を送信する場合、または共有シークレットを使用して信頼を確立する場合など、最も一般的な状況に対応する組み込みの`AuthVerifier`実装があります。 他の`AuthVerifier`実装は、OSGiランタイムにサービスとして登録されている`AuthVerifier`インターフェイスの実装を含むモジュールとしてデプロイできます。

``` important::
   認証検証レイヤの焦点は、資格情報の提供ではなく、認証の検証にあります。 トークンや資格情報を発行したり、サインインポートレットを表示したりすることはありません。 代わりに、このレイヤは既存の資格情報と認証済みセッションを検証し、認証エンドポイントを補完します。 下位互換性を確保するために、デフォルトの実装では、ユーザー名とパスワードの資格情報を提供するリクエストをサポートしています。 したがって、認証検証レイヤは、認証と承認の境界に位置しています。
```

## 認証検証プロセス

Authentication Verifierを設定するには、*[Control Panel]* → *[Configuration]* → *[System Settings]* → *[Security]* → *[API Authentication]* に移動します。

![Authentication Verifierの設定画面](./using-authentication-verifiers/images/01.png)

### ステップ1：リクエストの認証情報を確認する

このレイヤは、Chain of Responsibilityの設計パターンを使用して、組み込みおよびサードパーティの`AuthVerifier`実装の両方をサポートしています。 各`AuthVerifier`は、マップされたURLやその他のプロパティを指定する構成を提供できます。

各着信要求は、登録済みのすべての`AuthVerifier`と照合され、要求の処理に使用される`AuthVerifier`の最終リストが選択されます。 受信要求の認証情報を確認するのは、各`AuthVerifier`の責任です。

### ステップ2：承認コンテキストを作成する

一致するすべての`AuthVerifier`によって要求が処理されると、Liferay DXPは解決されたユーザーに承認コンテキストを作成します。

ここでは、`HttpServletRequest` `remoteUser`が設定され、解決されたユーザーID設定`ThreadLocal`が解決されたユーザーに返されます。

解決されたユーザーは、`AuthVerifier`インスタンスのいずれかによって返されたユーザーか、または指定された資格情報を確認できたインスタンスがない場合はゲストユーザーになります。

## 組み込みAuthentication Verifierの構成

`AuthVerifier`は開発者によって作成され、OSGiランタイムに登録されている限り自動的に処理されます。 このレイヤと周囲のプロセスは、`javax.servlet.Filter`インターフェイスを実装する`AuthVerifierFilter`クラスによって提供されます。 製品に同梱されているAuth Verifierの構成は次のとおりです。

  - [基本認証ヘッダー](#basic-auth-header)\*
  - [ダイジェスト認証](#digest-auth-header)
  - [HTTPトンネルエクステンダー](#http-tunnel-extender)
  - [画像リクエスト](#image-request-authentication-verifier)
  - [ポータルセッション](#portal-sessions-auth-verifiers)\*
  - [リクエストパラメータ](#request-parameter)
  - [トンネル認証](#tunnel-auth)

> \*デフォルトで有効になっており、追加の設定不要でリモートAPIにアクセスするために使用できます。

### 基本認証ヘッダー

このAuth Verifierにより、リモートクライアントは[HTTP基本認証](https://en.wikipedia.org/wiki/Basic_access_authentication)を使用して認証できます。 この方法で認証する必要のあるURLパスを指定して設定します。 *[Force Basic Authentication]* フィールドがオンになっている場合は、HTTP基本認証が必要です。

WebサービスのデフォルトのURLは`/api/*,/xmlrpc*`です。 このマッピングでは、`TunnelServlet`へのアクセスを防ぐために`/api/liferay*`を除外しています。 詳細については、「トンネルAuthentication Verifier」をご覧ください。

### ダイジェスト認証ヘッダー

このAuth Verifierにより、リモートクライアントは[HTTPダイジェスト認証](https://en.wikipedia.org/wiki/Digest_access_authentication)を使用して認証できます。 この方法で認証する必要のあるURLパスを指定して設定します。 [Force Digest Authentication]フィールドがオンになっている場合は、HTTP基本認証が必要です。

このAuth Verifierはデフォルトでは有効になっていません。

### HTTPトンネルエクステンダー

Liferayではモジュール性を採用したため、このエクステンダーはモジュールを`TunnelServlet`の一部にすることができるように作成されました。 `TunnelServlet`および`TunnelingServletAuthVerifier`がモジュールサーブレットコンテキストにマップされます。 マニフェストに`Http-Tunnel`が含まれるモジュールは、トンネルサーブレットを利用でき、`/o/_module_/api/liferay/do`を介してAPIを公開できます。
トンネリングを許可するクライアントIPアドレスを設定して構成します。 詳細については、以下を参照してください [プロパティ文書](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling) と同様にリモートのステージングを <!-- future link required --> 。

これはリモートAPIをエクスポートする方法としてはお勧めできません。JAX-RSまたはLiferay JSON Web Serviceテクノロジーを使用してリモートサービスを公開する方がはるかに優れています。

### 画像リクエストAuthentication Verifier

LibreOffice/OpenOfficeに接続している場合、Officeプロセスは、画像を含むドキュメントをレンダリングするためにLiferay Portalから画像をダウンロードする必要があります。 これを行うには、画像に安全にアクセスするための[JWTトークン](https://jwt.io)を作成します。

許可するホスト、含めるURL、必要に応じて除外するURLを設定して、これを構成します。

このAuth Verifierはデフォルトでは有効になっていません。

### ポータルセッションAuth Verifier

ブラウザのJavaScriptが、既存のポータルセッションを使用してLiferay JSON Webサービスにアクセスできるようにします。

デフォルトの構成では、URLに含まれるフィールドは、レガシーJSONリモートサービスレイヤー`/api/json*,/api/jsonws*,/c/portal/json_service*`へのアクセスを保護します。

### リクエストパラメータAuth Verifier

`RequestParameterAutoLogin`との下位互換性のために、HTTP要求パラメーター`parameterAutoLoginLogin`および`parameterAutoLoginPassword`内の資格情報を使用して、ポータルエンドポイントを認証してアクセスできます。

このAuth Verifierはデフォルトでは有効になっていません。

### トンネルAuthentication Verifier

`TunnelServlet`は、`/api/liferay/do`でマッピングされたレガシーリモートAPIエンドポイントであり、ポータルリモートサービスへのアクセスを提供します。 トンネルAuth Verifierでは、ユーザーに代わって提供された任意のユーザーIDを使用して、信頼されたリモートクライアントが認証済みアクセスを行うことができます。

信頼されたリモートクライアントの例として、ステージングリモート発行機能があります。

信頼されたリモートクライアントは、ポータルプロパティ`tunneling.servlet.shared.secret`に格納されている共有シークレットを使用して認証します。 初期値は空であり、すべてのアクセスが禁止されます。
デフォルト設定はデフォルトで有効になっていますが、アクセスはローカルホストのみに制限されています。 トンネリングを許可するクライアントIPアドレスを設定して構成します。 詳細については、[プロパティのドキュメント](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling)とリモートステージングを参照してください。

## 関連トピック

[Service Access Policies](./setting-service-access-policies.md)
