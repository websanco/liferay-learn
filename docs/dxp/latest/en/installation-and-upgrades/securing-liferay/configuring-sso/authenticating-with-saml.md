# Authenticating with SAML

```{toctree}
:maxdepth: 3

authenticating-with-saml/saml-authentication-process-overview.md
authenticating-with-saml/configuring-saml-at-the-system-level.md
authenticating-with-saml/saml-admin.md
authenticating-with-saml/configuring-saml-at-the-instance-level.md
authenticating-with-saml/saml-configuration-reference.md
```

The SAML (Security Assertion Markup Language) adapter provides Single Sign On (SSO) and Single Log Off (SLO) in your deployment. SAML works by using Identity Providers (IdP) and Service Providers (SP):

**Identity Provider:** A trusted system that provides single sign-on for users to access other websites.

**Service Provider:** A website that hosts applications and grants access only to identified users with proper credentials.

Liferay DXP instances can serve as either Service Provider (SP) or Identity Provider (IdP).

```{note}
A single Liferay DXP instance is *either* the SP or the IdP in your SSO setup; it can't be both. You can, however, use separate instances for both purposes (for example, one instance is the SP and another is the IdP).
```

You can jump right to configuring SAML or learn how it works:

* [SAML Authentication Process Overview](./authenticating-with-saml/saml-authentication-process-overview.md)
* [Configuring SAML at the System Level](./authenticating-with-saml/configuring-saml-at-the-system-level.md)
* [SAML Admin](./authenticating-with-saml/saml-admin.md)
* [Configuring SAML at the Instance Level](./authenticating-with-saml/configuring-saml-at-the-instance-level.md)
* [SAML Configuration Reference](./authenticating-with-saml/saml-configuration-reference.md)
