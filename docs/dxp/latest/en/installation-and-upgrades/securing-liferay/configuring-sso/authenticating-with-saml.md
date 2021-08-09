# Authenticating with SAML

```{toctree}
:maxdepth: 3

authentication-with-saml/saml-authentication-process-overview.md
authentication-with-saml/configuring-saml-at-the-system-level.md
authenticating-with-saml/setting-up-liferay-as-a-saml-identity-provider.md
authenticating-with-saml/setting-up-liferay-as-a-saml-service-provider.md
authenticating-with-saml/registering-a-saml-service-provider.md
authenticating-with-saml/configuring-service-provider-and-identity-provider-connections.md
authenticating-with-saml/saml-configuration-reference.md
```

The SAML (Security Assertion Markup Language) adapter provides Single Sign On (SSO) and Single Log Off (SLO) in your deployment. SAML works by using Identity Providers (IdP) and Service Providers (SP):

**Identity Provider:** A trusted system that provides single sign-on for users to access other websites.

**Service Provider:** A website that hosts applications and grants access only to identified users with proper credentials.

Liferay DXP instances can serve as either Service Provider (SP) or Identity Provider (IdP).

```note::
   A single Liferay DXP instance is *either* the SP or the IdP in your SSO setup; it can't be both. You can, however, use separate instances for both purposes (for example, one instance is the SP and another is the IdP).
```

You can jump right to configuring SAML or learn how it works:

* [Setting Up Liferay as an Identity Provider](./authentication-with-saml/setting-up-liferay-as-a-saml-identity-provider.md)
* [Registering SAML Service Provider](./authentication-with-saml/registering-a-saml-service-provider.md)
* [Setting Up Liferay as a SAML Service Provider](./authentication-with-saml/setting-up-liferay-as-a-saml-service-provider.md)
* [Configuring SP and IdP Connections](./authentication-with-saml/configuring-service-provider-and-identity-provider-connections.md)
* [SAML Configuration Reference](./authentication-with-saml/saml-configuration-reference.md)
* [SAML Authentication Process Overview](./authentication-with-saml/saml-authentication-process-overview.md)

Visit the Liferay Marketplace to install the [SAML adapter](https://web.liferay.com/marketplace/-/mp/application/15188711).

## What's new in Liferay Connector to SAML 2.0

The `5.0.0` version of the application brings the following improvements:

* Liferay DXP acting as a Service Provider (SP) can now connect to multiple Identity Providers (IdP).
* Developers have an extension point for customizing which Identity Providers users can use to sign in.
* Support for stronger Signature Algorithms (like `SHA-256`)
* Signature method algorithm URLs can now be blacklisted from the metadata (for example, disabling `SHA-1`: `http://www.w3.org/2000/09/xmldsig#rsa-sha1`)

```important::
   If you're upgrading from a Liferay SAML adapter prior to version 3.1.0, your portal properties are automatically migrated to System Settings configurations. Please see the `SAML Configuration Reference <./saml-configuration-reference.md>`_ article for details on settings.
