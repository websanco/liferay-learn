# SAML Admin

The SAML Admin panel is the best place to configure your SAML instance. Use it instead of Instance Settings to streamline your SAML administration experience. 

You can configure an Identity Provider (IdP), a Service Provider, and Service Provider connections using SAML Admin. To access it, click the _Global Menu_ (![Global Menu](../../../../images/icon-applications-menu.png)) &rarr; _Control Panel_ &rarr; _SAML Admin_. 

## General Tab

The General tab shows options that apply to SAML regardless of its role: 

**Enabled:** Check this box to enable SAML authentication once you have everything configured. 

**SAML Role:** Choose the SAML role, either Identity Provider or Service Provider. 

**Entity ID:** The unique identifier for this SAML entity (IdP or SP). It can be up to 1024 characters long. 

The Certificate and Private Key section displays the key generated when you set up your key store here or in [system settings](./configuring-saml-at-the-system-level.md). Here, you can replace the certificate by generating a new certificate or importing one you created elsewhere, and you can also download the certificate to import elsewhere. 

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

## Service Provider

1. Choose the _Service Provider_ role from the SAML Role field. 

1. The Certificate and Private Key section is for creating a keystore for SAML. Click *Create Certificate* and enter the following information:

    * Your common name (your first and last name)
    * The name of your organization
    * The name of your organizational unit
    * The name of your city or locality
    * The name of your state or province
    * The name of your country
    * The length in days that your keystore will remain valid (how long before the keystore expires)
    * The key algorithm (RSA is the default)
    * The key length in bits (2048 is the default)
    * The key password

    Remember that the keystore has two storage options: file system storage (the default) and Documents and Media storage. By default, the certificate uses the `SHA256` algorithm for encryption and is fingerprinted and self-signed via `MD5` and `SHA256`. When you enter all the required information, click *Save*.

1. After you clicked *Save*, check that you can view information about your certificate or download your certificate. If you can, you successfully created a keystore. After you create a keystore, additional options appear. There are three tabs:

    **General**: Enables or disables SAML SP and manages the required keystore.

    **Service Provider**: This tab manages basic and advanced configurations for the SP.

    **Identity Provider Connection**: This tab manages connections to the IdP. There can be multiple IdP connections.

1. You can also generate an encryption certificate. This is a separate key for encrypting assertions. If you want assertions encrypted, you must generate a key for this. The procedure is exactly the same as generating your certificate in step 2 above.

1. Next, you must configure an Identity Provider connection. Click on the *Identity Provider Connections* tab. Enter a name for the Identity Provider, enter its entity ID, and enter its metadata URL. If you have already configured a separate Liferay DXP installation as an Identify provider, you'd enter the following information:

    * Name: *Liferay IdP*
    * Entity ID: [ID of IdP]
    * Clock Skew: Set the tolerance in milliseconds between SP and IdP.
    * Force Authn: Whether the IdP should force re-authentication regardless of context.
    * Metadata URL: `http://localhost:8080/c/portal/saml/metadata` (test this URL first)
    * Name Identifier Format: See [settings](./saml-settings.md).
    * Attribute Mapping: See [settings](./saml-settings.md).
    * Keep Alive URL: See [settings](./saml-settings.md).

    ```{important}
    The Liferay Connector to SAML 2.0 app supports using *either* a URL to a SAML IdP metadata file *or* an actual (uploaded) SAML metadata XML file. The value entered in the *Metadata URL* field is persisted to the database only when there a metadata URL and there is no specified metadata XML file. Otherwise, Liferay DXP keeps the original metadata URL in its database. This behavior ensures that once a metadata URL has been specified, there is always a metadata URL saved in the database. This way, if you forget the previously entered metadata URL or its format, you can look at the displayed metadata URL and choose to modify the displayed metadata URL or overwrite the previously saved metadata URL by specifying a metadata XML file.
    ```

1. Finally, after you save your certificate and private key information and configure an Identity Provider connection, check the *Enabled* box at the top of the General tab and click *Save*. Liferay is now a SAML Service Provider!

Note that the SAML Service Provider session is tied to the normal session on the application server. Session expiration on the application server terminates the session on the Service Provider but does not initiate single logout.

## Configuring the Service Provider

The Service Provider tab shows several configuration options: 

**Require Assertion Signature:** Check this box to require SAML assertions to be individually signed by the IdP in addition to the entire SAML message. 

**Clock Skew:** Set the tolerance for the time difference between the SP and the IdP in milliseconds. 

**LDAP Import Enabled:** Check this box to import user attributes from the LDAP servers declared in this SP's instance settings. 

**Sign Authn Requests?:** If configured as an SP, digitally sign Authn requests. 

**Sign Metadata?:** Sign the metadata sent to peer SAML entities. 

**SSL Required:** Check this box to require SSL for the transfer of all SAML messages. All URLs in metadata sent to peers become prefixed with the `https` protocol. 

**Allow showing the login portlet:** Allow the login portlet to appear when no SAML IdP is matched to the login request. Users in this scenario log in locally to Liferay DXP. 

```{Important}
Individual assertions need not be signed as long as the SAML response itself is signed. The SP and IdP should always communicate over `https` to have encryption at the transport level. 

Liferay DXP requires signed SAML responses. If you believe man-in-the-middle attacks are possible and the information in the assertions is sensitive, you can both sign and encrypt them. 
```

You can add multiple IdP connections. To add another Identity Provider, click *Add Identity Provider* again and enter the details for the other provider. When users log in, they are asked to choose an identity provider, so be sure to name the providers so users can recognize them.

## Setting Up Liferay DXP as a SAML Service Provider in a Clustered Environment

You can use the Liferay Connector to SAML 2.0 app as an SSO solution for a clustered  environment. If your multi-node cluster is behind a load balancer, you must enable all the nodes as SPs, and they must share the same keystore manager.

If using the Filesystem Keystore Manager (the default):

1. Configure each node of your [Liferay cluster](../../../setting-up-liferay/clustering-for-high-availability/clustering-for-high-availability.md) as a SAML service provider as above.

1. Copy the keystore file (`[Liferay Home]/data/keystore.jks`, by default) from the first node to the remaining nodes. This file is the Java keystore that's created by the SAML Provider app. The keystore contains the valid or self-signed certificate managed by the SAML connector app.

1. Verify that the service provider metadata has been generated to be used either as a URL or an XML file. The metadata is the same for all nodes because of the same database back-end. The IdP's request goes through the load balancer.

1. At this point, all  nodes have the same SAML SP configuration and each of them can respond to web requests and handle the SAML protocol. To test your SSO solution, sign into Liferay via your load balancer, navigate to a few pages of a few different sites, and then log out.

If using the Document Library Keystore Manager, skip step 2 because the keystore file is stored in the database shared by all the nodes.

Now you know how to configure Liferay DXP either as a SAML identity provider or a service provider. You also know how to configure SAML in a clustered environment.

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
