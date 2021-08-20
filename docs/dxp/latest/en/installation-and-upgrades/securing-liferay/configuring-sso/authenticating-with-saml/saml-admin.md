# SAML Admin

The SAML Admin panel is the best place to configure your SAML instance. Use it instead of Instance Settings to streamline your SAML administration experience. 

You can configure an Identity Provider (IdP), a Service Provider, and Service Provider connections using SAML Admin. To access it, click the _Global Menu_ (![Global Menu](../../../../images/icon-applications-menu.png)) &rarr; _Control Panel_ &rarr; _SAML Admin_. 

## General Tab

The General tab shows options that apply to SAML regardless of its role: 

**Enabled:** Check this box to enable SAML authentication once you have everything configured. 

**SAML Role:** Choose the SAML role, either Identity Provider or Service Provider. 

**Entity ID:** The unique identifier for this SAML entity (IdP or SP). It can be up to 1024 characters long. 

The Certificate and Private Key section displays the key generated when you set up your key store in [system settings](./configuring-saml-at-the-system-level.md). Here, you can replace the certificate by generating a new certificate or importing one you created elsewhere, and you can also download the certificate to import elsewhere. 

If you must replace the auto-generated certificate, it's easy: 

1. Click _Replace Certificate_. 

1. The Create Certificate tab is activated by default. Fill out the necessary fields for your key. 

1. When finished, click _Save_. 

Your new key's details are now displayed. 

If you've already generated a key, that key must be stored in a [PKCS #12](https://en.wikipedia.org/wiki/PKCS_12) archive to be imported into Liferay DXP: 

1. Click _Replace Certificate_. 

1. Click the _Import Certificate_ tab. 

1. Type your key store's password and either drop the PKCS #12 key file into the upload area or use the file selector to choose it. 

Your key is now imported. 

## Identity Provider

If you chose a SAML role of Identity Provider (IdP), you can configure it here. These fields correspond to similar fields in [instance settings](./configuring-saml-at-the-instance-level) where you can configure the same things. 

**Sign Metadata?:** Sign the metadata sent to peer SAML entities. 

**SSL Required:** Check this box to require SSL for the transfer of all SAML messages. All URLs in metadata sent to peers become prefixed with the `https` protocol. 

**Require Authn Request Signature:** Check this box to require each Authn request to be signed by the sending Service Provider. In most cases, this should be enabled. 

**Session Maximum Age:** The amount of time in seconds the SSO session, managed by the IdP, lasts. 

**Session Idle Timeout:** The amount of time in seconds an idle session lasts before it expires. 

## Service Provider Connection

You can use this interface to configure one or more Service Provider connections. To get started click _Add Service Provider_. Fill out the form, and when done, click _Save_. 

**Name:** Name the SP. This is just a descriptive name; it's not used in the configuration. 

**Entity ID:** Enter the entity ID of the IdP this SP connects to here. It must match the entity ID declared in the Service Provider metadata. 

**Enabled:** Check this box to activate the Service Provider connection. 

**Assertion Lifetime:** Enter the number of sections an assertion should be valid. 

**Force Encryption:** If the SP does not provide a public key for encrypting the assertions, abort the single sign-on.

**Metadata:** Provide a URL to the Service Provider metadata XML file or manually upload the Service Provider metadata XML file. If you provide a URL, the XML file is retrieved and periodically polled for updates. The update interval can be configured in System Settings with the Runtime Metadata Refresh Interval (`saml.metadata.refresh.interval` if using a `config` file) property which specifies a number of seconds. If fetching the metadata XML file by URL fails, you can't enable the Service Provider connection. If the Identity Provider server cannot access the metadata via URL, you can upload the XML file manually. In this case, the metadata XML file is not updated automatically.

**Name Identifier Format:** Choose the Name Identifier Format used in the SAML Response. Set this according to what the Service Provider expects to receive. For Liferay Service Providers, any selection other than email address indicates that the Name Identifier refers to screen name. The formats don't have any special meaning to Liferay Identity Providers. The `NameID` value is defined by the Name Identifier attribute. See the next option.

**Name Identifier Attribute Name:** This specifies which attribute of the Liferay `User` object to use as the `NameID` value. Possible values include `emailAddress`, `screenName` and `uuid`. You can prefix the name with `static:` or `expando:`. If you use the prefix `static`, the value is whatever comes after `static:`. If you use the prefix `expando`, the value is whatever custom field is specified after `expando:`. For example, `expando:SSN` would look up the `User` custom field with the name `SSN`.

**Attributes Enabled:** Include and resolve assertion attributes.

**Attributes Namespace Enabled:** Check this box to namespace the attribute names like this:

    urn:liferay:user:expando: urn:liferay:user: urn:liferay:groups: urn:liferay:organizationRole: urn:liferay:organization: urn:liferay:roles: urn:liferay:siteRole: urn:liferay:userGroupRole: urn:liferay:userGroups:

**Attributes:** Enter a list of attributes to include in the assertion, one per line. Each line is an expression that gets parsed. Examples:

    organizations organizationRoles roles siteRoles userGroups static:[attributeName]=[attributeValue] expando:[userCustomFieldName]

Note that the full namespace depends on the attribute name. Attribute namespaces can be useful. Use them when attribute names from different namespaces might conflict. For example, `expando:user` vs `urn:liferay:roles:user`.

**Keep Alive URL:** If users are logged into several Liferay SP instances via a Liferay IdP, their sessions can be kept alive as long as they keep a browser window open to one of them. Configure this only if the SP is Liferay DXP. The URL is `https://[SP host name]/c/portal/saml/keep_alive`.
