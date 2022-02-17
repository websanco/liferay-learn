# SAMLによる認証

```{toctree}
:maxdepth: 3

authenticating-with-saml/saml-authentication-process-overview.md
authenticating-with-saml/configuring-saml-at-the-system-level.md
authenticating-with-saml/saml-admin.md
authenticating-with-saml/configuring-saml-at-the-instance-level.md
authenticating-with-saml/saml-configuration-reference.md
```

SAML（Security Assertion Markup Language）アダプターは、シングルサインオン（SSO）とシングルログオフ（SLO）をデプロイ環境に提供します。 SAMLは、アイデンティティプロバイダー（IdP）とサービスプロバイダー（SP）を使用して機能します。

**アイデンティティプロバイダー：** ユーザーが他のWebサイトにアクセスするためのシングルサインオンを提供する信頼できるシステム。

**サービスプロバイダー：** アプリケーションをホストし、適切な認証情報を持つ識別されたユーザーにのみアクセスを許可するWebサイト。

Liferay DXPインスタンスは、サービスプロバイダー（SP）またはアイデンティティプロバイダー（IdP）のいずれかとして機能できます。

```{note}
単一のLiferay DXPインスタンスは、SSOセットアップにおけるSPまたはIdPのいずれかであり、両方にすることはできません。 ただし、両方の目的で別々のインスタンスを使用できます（たとえば、一方のインスタンスをSPにし、もう一方をIdPにするなど）。
```

SAMLの構成に直接移動するか、SAMLがどのように機能するかを学ぶことができます。

* [SAML Authentication Process Overview](./authenticating-with-saml/saml-authentication-process-overview.md)
* [Configuring SAML at the System Level](./authenticating-with-saml/configuring-saml-at-the-system-level.md)
* [SAML管理](./authenticating-with-saml/saml-admin.md)
* [Configuring SAML at the Instance Level](./authenticating-with-saml/configuring-saml-at-the-instance-level.md)
* [SAML Configuration Reference](./authenticating-with-saml/saml-configuration-reference.md)
