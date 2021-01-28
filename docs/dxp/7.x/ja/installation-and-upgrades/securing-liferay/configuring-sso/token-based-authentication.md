# トークンベースのシングルサインオン認証

トークンベースのSSO認証は、Liferay Portal 7.0で導入され、Shibboleth、SiteMinder、Oracle OAM、および次のメカニズムのいずれかを介してトークンを伝播することによって機能するSSO製品のサポートが標準化されました。

  - HTTPリクエストパラメータ
  - HTTPリクエストヘッダー
  - HTTP cookie
  - セッション属性

これらのプロバイダーには、これらのパラメーター、ヘッダー、Cookie、または属性を読み取って設定する組み込みのWebサーバーモジュールがあるため、トークンSSO構成を使用する必要があります。

認証トークンには、ユーザーの画面名または電子メールアドレスのうち、特定の会社（ポータルインスタンス）で使用するように設定されている方が含まれています。 Liferay Portalは、次の3つの認証方法をサポートしています。

  - メールアドレスによる
  - 画面名による
  - ユーザーIDによる

トークンベースの認証では、メールアドレスと画面名のみがサポートされています。 トークンベースの認証が試行されたときにユーザーIDが設定されている場合、`TokenAutoLogin`クラスが次の警告を記録します。

    Incompatible setting for: company.security.auth.type

さらに、認証メカニズム用のモジュールまたはプラグインを備えたApacheなどのフロントWebサーバーなど、Liferay Portalの外部のセキュリティメカニズムを使用する必要があります。 リバースプロキシを使用すると、HTTPリクエストをクライアントのWebブラウザからLiferay Portalのアプリケーションサーバーに直接送信することで、悪意のあるユーザーのなりすましを防ぐことができます。

## トークンベースの認証の構成

トークンベースの認証はデフォルトで無効になっています。 トークンベースのSSO認証を管理するには、コントロールパネル → *[System Settings]* → *[Security]* → *[SSO]* に移動します。

![SSO設定は、システム設定のセキュリティセクションにあります。](token-based-authentication/images/01.png)

トークンベースのSSOモジュールの構成オプションは次のとおりです。

| 構成                         | 説明                                                                    |
| -------------------------- | --------------------------------------------------------------------- |
| **Enabled**                | トークンベースのSSO認証を有効にするには、このボックスをオンにします。                                  |
| **Import from LDAP**       | ユーザーが存在しない場合にLDAPから自動的にインポートするには、このボックスをオンにします。                       |
| **User token name**        | トークンの名前と同じに設定します。 これは、指定された場所から取得されます。 （例：`SM_USER`）                  |
| **Token location**         | ユーザートークンのタイプ（HTTPリクエストパラメータ、HTTPリクエストヘッダー、HTTP Cookie、セッション属性）に設定します。 |
| **Authentication cookies** | ログアウト後に削除する必要があるCookie名に設定します。 （例：`SMIDENTITY`、`SMSESSION`）           |
| **Logout redirect URL**    | ユーザーがLiferay Portalからログアウトすると、ユーザーはこのURLにリダイレクトされます。                  |

*[保存]* をクリックして、トークンベースのSSOをアクティブにしてください。

## 必要なSiteMinder構成

SiteMinderを使用する場合は、Liferay PortalのURLにチルダ文字が使用される場合があることに注意してください。 デフォルトでは、SiteMinderはチルダ文字（およびその他）を不正な文字として扱い、それらを含むURLを処理するとHTTP 500エラーを返します。 この問題を回避するには、SiteMinder構成でこのデフォルト設定を次のように変更します。

    BadUrlChars       //,./,/.,/*,*.,\,%00-%1f,%7f-%ff,%25

上記の設定は、`~`文字を除いてデフォルトと同じです。 SiteMinderを再起動して、構成のアップデートを有効にします。 詳細については、SiteMinderの[ドキュメント](https://techdocs.broadcom.com/us/product-content/recommended-reading/technical-document-index/ca-siteminder-informational-documentation-index.html)を参照してください。

## まとめ

Liferay PortalのトークンベースのSSO認証メカニズムは、柔軟性が高く、有効なLiferay Portalユーザーの画面名または電子メールアドレスを提供するSSOソリューションと互換性があります。 これらには、ShibbolethやSiteMinderなどがあります。
