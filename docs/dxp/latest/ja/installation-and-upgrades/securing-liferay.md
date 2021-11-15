# Liferayの保護

```{toctree}
:maxdepth: 3

securing-liferay/authentication-basics.md
securing-liferay/configuring-sso.md
securing-liferay/multi-factor-authentication.md
securing-liferay/securing-web-services.md
```

Liferay DXPはセキュリティを考慮して構築されています。 これには、[OWASPトップ10](https://www.owasp.org/index.php/Top_10_2013-Top_10)および[CWE/SANSトップ25](https://www.sans.org/top25-software-errors/)で説明されているような一般的なセキュリティの脆弱性と悪用の緩和が含まれます。

Liferayのインストールのセキュリティ保護には、ホスティング環境、データベース、検索プロバイダー、アプリケーションサーバー、およびLiferay DXP自体のベストセキュリティプラクティスに従う方法などがありますが、これらに限定されるものではありません。

```{note}
たとえば、Liferay DXPは、HTTPヘッダーのCRLFのサニタイズをアプリケーションサーバーに依存しています。 これがアプリケーションサーバーで適切に設定されていることを確認する必要があります。 この構成をスキップすると、Veracodeなどのセキュリティ検証製品がセキュリティレポートで誤検知のフラグを立てることがあります。
```

ここでは、Liferay DXP自体を保護するための基本的な要素について学びます。 Liferay DXPのインストールに対するユーザー認証方法の構成、権限を持つユーザーの承認、Liferay DXP Webサービスへの安全なアクセスの構成、および必要に応じたセキュリティ機能の微調整などが含まれます。

```{important}
セキュリティパッチがリリースされたら、それらを展開することをお勧めします。 コミュニティとCEのインストールの場合は、以前のセキュリティパッチがすべて含まれている最新のコミュニティリリースを常に使用することをお勧めします。
```

## 認証

LiferayDXP認証は柔軟です。 デフォルトでは、ユーザーは*[Sign In]* ウィジェットを使用してLiferay DXPにログインします。このウィジェットでは、データベースを使用してユーザーを認証します。 デフォルトでは、ゲストは[Sign In]ウィジェットを使用して、デフォルトの権限を持つアカウントを作成できます。 デフォルトの認証エクスペリエンスのほぼすべての要素は、管理者が変更できます。 例:

  - [多要素認証](./securing-liferay/multi-factor-authentication/using-multi-factor-authentication.md)を設定できます。
  - [SSOを使用して](./securing-liferay/configuring-sso.md)認証を管理できます。
  - Liferayは、ポータルデータベースを使用する代わりに、[LDAPと統合](../users-and-permissions/connecting-to-a-user-directory/connecting-to-an-ldap-directory.md)してユーザーを検証することもできます。
  - ゲストアカウントの作成を[オフ](./securing-liferay/authentication-basics.md#disabling-guest-account-creation)にすることができます。

詳細は、[認証の基本](./securing-liferay/authentication-basics.md)を参照してください。

## パーミッション

Liferay DXPには、堅牢なロールベースのアクセス制御（RBAC）システムがあります。 ユーザーは、サイト、チーム、ユーザーグループ、または組織に割り当てることができます。 カスタムのロールを作成し、権限をこれらのロールに割り当て、それらのロールをユーザーに割り当てることができます。 ロールは、サイト、組織、またはグローバルなどの特定のコンテキストでのみ適用されるようにスコープが設定されています。 詳細は、[ロールと権限](../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)を参照してください。

## Webサービスの保護

Liferay Webサービスには、セキュリティと承認に対する多層的で構成可能なアプローチがあります。

  - [サービスアクセスポリシー](./securing-liferay/securing-web-services/setting-service-access-policies.md)は、リモートAPIへのアクセスを制御します。
  - [Authentication Verifier](./securing-liferay/securing-web-services/using-authentication-verifiers.md)は、提供された資格情報を検証します。
  - [クロスオリジンリソース共有](./securing-liferay/securing-web-services/setting-up-cors.md)設定では、信頼できるソースからのみリソースを取得できます。

詳細は、[Webサービスの保護の概要](./securing-liferay/securing-web-services.md)を参照してください。

## セキュリティの微調整

追加のセキュリティ機能を微調整または無効にする方法は複数あります。

  - LiferayポータルのHTTPS [Webサーバー](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Web%20Server)アドレスを設定する。
  - ユーザーを[リダイレクト](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Redirect)できる許可されているサーバーのリストを構成する。
  - 任意のページからアクセスできる[ポートレット](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Portlet)のリストを構成する。
  - アップロードおよびダウンロードを許可するファイルタイプを設定する。

これらの機能は、[ポータルプロパティ](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html)を使用して構成できます。

```{warning}
Liferayポータルの理念は「デフォルトで安全」です。 セキュリティ固有のデフォルトまたはホワイトリストを変更する場合は、十分に注意してください。 このようなアクションは、セキュリティの設定ミスや安全でないデプロイにつながる可能性があります。
```

## 追加情報

Liferay Portalのインストールのセキュリティ保護の詳細は、[当社のセキュリティステートメント](https://www.liferay.com/security)、[コミュニティセキュリティチーム](https://portal.liferay.dev/people/community-security-team)、およびこれらのページに記載されているリソースを参照してください。

[Liferay マーケットプレイス](https://www.liferay.com/marketplace)から追加のセキュリティプラグインを入手できます。

## 次のステップ

  - [認証の基本](./securing-liferay/authentication-basics.md)
  - [Webサービスの保護の概要](./securing-liferay/securing-web-services.md)
