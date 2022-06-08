# SAML Settings

Many of the same fields appear in both the Service Provider and the Identity Provider configurations. Here's a reference. 

**Name:** Name the SP or IdP. This is just a descriptive name; it's not used in the configuration. 

**Entity ID:** The official name of this SP or IdP. When configuring connections, they must match this name. 

**Enabled:** Check this box to enable this SAML provider. 

**Clock Skew:** Set the tolerance for the time difference between the SP and the IdP in milliseconds. 

**Force Authn:** Check this box to have the Service Provider ask the Identity Provider to re-authenticate the user before verifying the user.

**Unknown Users are Strangers:** Stranger behavior is defined in Control Panel -> Instance Settings -> Platform -> User Authentication -> General. 

**Metadata:** Provide a URL to the Service Provider metadata XML file or manually upload the Service Provider metadata XML file. If you provide a URL, the XML file is retrieved and periodically polled for updates. The update interval can be configured in System Settings with the Runtime Metadata Refresh Interval (`saml.metadata.refresh.interval` if using a `config` file) property which specifies a number of seconds. If fetching the metadata XML file by URL fails, you can't enable the Service Provider connection. If the Identity Provider server cannot access the metadata via URL, you can upload the XML file manually. In this case, the metadata XML file is not updated automatically.

**Name Identifier:** Choose a Name Identifier Format from the available options in section 8.3 of the [SAML specification](http://docs.oasis-open.org/security/saml/v2.0/saml-core-2.0-os.pdf). Set this according to what the Service Provider expects to receive. For Liferay Service Providers, any selection other than email address indicates that the Name Identifier refers to screen name. The formats don't have any special meaning to Liferay Identity Providers. The `NameID` value is defined by the Name Identifier attribute.

**User Resolution:** Choose from No Matching, Match a User Field Chosen Dynamically Based on Name ID Format, or Match Using a Specific SAML Attribute Mapping. This algorithm determines how users are found or provisioned. For example, if you choose based on Name ID Format and the  Name ID Format is email address, the algorithm matches by email address. 

**Attribute Mapping:** Choose a field from Liferay to match to a SAML attribute. You can choose several fields from Liferay's User object or custom fields you have created for the User object. These attributes are updated from the SAML assertion when a user logs into the system. By default the `NameID` and Service Provider are bound to a user after the `emailAddress` is matched at least once. Bindings are preferred and checked before user matching is conducted, so users whose email addresses have changed don't lose their ability to log in, and email addresses can be corrected through SAML attribute mappings. 

**Keep Alive:** If users are logged into several Liferay SP instances via a Liferay IdP, their sessions can be kept alive as long as they keep a browser window open to one of them. Configure this only if the SP is Liferay DXP. The URL is `https://[SP host name]/c/portal/saml/keep_alive`.

## OSGi Configuration Properties

You can configure SAML outside the UI through [OSGi configuration files](../../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) and by uploading metadata XML to configure how connections are negotiated.

As noted previously, anything related to configuring SP connections must be done through the SAML Admin UI where configurations are saved to Liferay's database. 

```{note}
Don't use OSGi `.config` files or Liferay DXP's System Settings Control Panel application to configure SAML providers (IdP or SP). The System Settings UI is auto-generated, and is for advanced administrators. It does not perform the enhanced validation on the fields that the SAML Admin UI performs, so administrators could create invalid configurations.
```

This is a portal instance-scoped configuration which can be managed via OSGi Configuration Admin. The affected properties are those in the `SAMLProviderConfiguration` metatype:

* `keyStoreCredentialPassword()`
* `keyStoreEncryptionCredentialPassword()`
* `assertionSignatureRequired()`
* `authnRequestSignatureRequired()`
* `clockSkew()`
* `defaultAssertionLifetime()`
* `entityId()`
* `enabled()`
* `ldapImportEnabled`
* `role()`
* `sessionMaximumAge`
* `sessionTimeout()`
* `signAuthnRequest()`
* `signMetadata()`
* `sslRequired()`
* `allowShowingTheLoginPortlet()`

The SAML Admin UI remains the place for creating the portal instance scoped configuration instances.

Note that there is also a system wide configuration, represented by the `SamlConfiguration` metatype.

If you used Liferay 6.2, please note that the following system wide properties were removed:

* `saml.metadata.paths` (served no purpose after removal of SP connection defaults)
* `saml.runtime.metadata.max.refresh.delay`
* `saml.runtime.metadata.min.refresh.delay`

The latter two properties were replaced with the single property `com.liferay.saml.runtime.configuration.SamlConfiguration.getMetadataRefreshInterval()`.

Note also the introduction of the *SAML KeyStoreManager Implementation Configuration* in *Control Panel* &rarr; *System Settings* &rarr; Security &rarr; SSO. The options for this configuration are explained above in [Configuring SAML at the System Level](./configuring-saml-at-the-system-level.md).

In recent versions, `SHA256` is the default encryption algorithm used in the configuration and to generate keys. The default configuration tries `SHA256`, then `SHA384`, then `SHA512` before falling back to `SHA1`. Because `SHA1` is potentially vulnerable, you can blacklist it using this property:

```properties
blacklisted.algorithms=["blacklisted_algorithm_url", "another_blacklisted_algorithm_url"]
```

To blacklist `SHA1`, therefore, you'd have this configuration:

```properties
blacklisted.algorithms=["http://www.w3.org/2000/09/xmldsig#sha1"]
```

Place these in a config file with this name:

```bash
com.liferay.saml.opensaml.integration.internal.bootstrap.SecurityConfigurationBootstrap.config
```

There's a lot more granularity in how connections are negotiated if you configure the metadata XML.

## Configuring Negotiation Via metadata.xml

If the default negotiation configuration doesn't work for you, you can craft your own configuration and upload it. Before doing this, visit your host's metadata URL and save a copy of the configuration in case you need it later:

```
http://[hostname]/c/portal/saml/metadata
```

For example, if you're stuck connecting to a legacy IdP that only supports `SHA1`, you can upload a configuration that disables the other algorithms:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<md:EntityDescriptor xmlns:md="urn:oasis:names:tc:SAML:2.0:metadata" entityID="samlidp">
  <md:IDPSSODescriptor WantAuthnRequestsSigned="true" protocolSupportEnumeration="urn:oasis:names:tc:SAML:2.0:protocol">
    <md:Extensions>
      <alg:SigningMethod xmlns:alg="urn:oasis:names:tc:SAML:metadata:algsupport" Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
    </md:Extensions>
    <md:KeyDescriptor use="signing">
      <ds:KeyInfo xmlns:ds="http://www.w3.org/2000/09/xmldsig#">
        <ds:X509Data>
          <ds:X509Certificate>... omitted ...</ds:X509Certificate>
        </ds:X509Data>
      </ds:KeyInfo>
    </md:KeyDescriptor>
    <md:SingleLogoutService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="http://localhost:8080/c/portal/saml/slo"/>
    <md:SingleLogoutService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="http://localhost:8080/c/portal/saml/slo"/>
    <md:SingleSignOnService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="http://localhost:8080/c/portal/saml/sso"/>
    <md:SingleSignOnService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="http://localhost:8080/c/portal/saml/sso"/>
  </md:IDPSSODescriptor>
</md:EntityDescriptor>
```

Notice that in the configuration above, the `<md:Extensions>` block has only one signing algorithm: `SHA1`.

```{note}
Since the default configuration falls back to `SHA1`, you shouldn't need to do this unless your legacy system can't negotiate via the fallback mechanism. Also note that if you blacklisted `SHA1`, this won't work. Due to [vulnerabilities in SHA1](https://en.wikipedia.org/wiki/SHA-1), it's best to avoid using it altogether if possible.
```

If you've changed your metadata configuration, you can go back to the default configuration if you saved it before making the change. If you didn't, you can provide a URL instead of an uploaded XML file to one of your peers' metadata configurations.
