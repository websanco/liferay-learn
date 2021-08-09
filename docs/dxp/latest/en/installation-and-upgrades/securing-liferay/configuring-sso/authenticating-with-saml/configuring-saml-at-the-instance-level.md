# Configuring SAML at the Instance Level

Each portal instance can be a SAML provider, either an Identity Provider (IdP) or a Service Provider (SP). Whichever role your DXP instance fills, you can configure it in the same place. 

1. Go to _Control Panel_ &rarr; _Instance Settings_ &rarr; _Security_ &rarr; _SSO_ &rarr; _SAML Provider Configuration_. 

1. Fill out the form and at the bottom, click _Update_. 

**Key Store Credential Password:** Your key store credential password gets you access to the key store. 

**Key Store Encryption Credential Password:** Your key store encryption credential protects this SAML provider configuration in the key store. 

**Require Assertion Signature:** Check this box to require SAML assertions to be individually signed by the IdP in addition to the entire SAML message. 

**Require Authn Request Signature:** Check this box to require each Authn request to be signed by the sending Service Provider. In most cases, this should be enabled. 

**Clock Skew:** Set the tolerance for the time difference between the SP and the IdP in milliseconds. 

**Default Assertion Lifetime:** Define how long in seconds IdP assertions last. 

**Enabled:** Check this box to enable this SAML provider. 

**Entity ID:** Name this SP or IdP. 

**LDAP Import Enabled:** Check this box to import user attributes from the LDAP servers declared in this SP's instance settings. 
