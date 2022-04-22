## Integrating LDAP and SSO

_Single Sign-on (SSO)_ describes a number of services that enable users to manage sign-ons to multiple sites or services using only one account. There are a number of different SSO implementations, including both software and hardware solutions. One of the most popular SSO solutions is _Lightweight Directory Access Protocol (LDAP)._

<div class="key-point">
Key Point:<br>
LDAP is an application protocol for accessing and maintaining distributed directory information services over an IP network. 
</div>

LDAP servers process queries and updates to LDAP directories. Whereas databases are designed to process a high volume of updates in a short timespan, LDAP directories are optimized for read performance. Many organizations use LDAP directories to store information such as employee credentials, organization hierarchies, HR data, customer contact information, and network information. Many web applications support LDAP-integration and many organizations use credentials stored in LDAP to provide access to a variety of web applications. In this module, we'll enable LDAP users to authenticate to Liferay.

LDAP authentication is unlike other SSO implementations within the platform (e.g., Token, CAS, OpenSSO, etc). LDAP authentication uses a lower-level Liferay concept of an Authenticator. Authenticators are injected into the Liferay AuthPipeline and used as part of the platform's own authentication process. The implementation is encapsulated inside of `com.liferay.portal.ldap.internal.authenticator.LDAPAuth`. LDAPAuth is an Authenticator component that is registered to the authentication pipeline.

The simplest possible login process using Liferay for authentication would go something like this:
1. An unauthenticated user accesses a page
2. Liferay serves up the "Guest" version of the page
3. A user attempts to sign in
4. Liferay processes the request
5. If the sign-in fails, the user returns to the page, unauthenticated, with an error
6. If the sign-in succeeds, Liferay serves up the authenticated version of the page

<img src="../images/sso-ldap-no-ldap.png" style="max-width:100%;">

If you're using LDAP (or another SSO solution) for authentication, your login process will go something like this:

1. An unauthenticated user accesses a page
2. Liferay serves up the "Guest" version of the page
3. A user attempts to sign in
4. A request is sent to LDAP for authentication
5. If the sign-in fails, the user returns to the page, unauthenticated, with an error
6. If the sign-in succeeds, LDAP notifies Liferay that the user is signed in
7. Liferay serves up the authenticated version of the page

<img src="../images/sso-ldap-simple-ldap.png" style="max-width:100%;">

<div class="key-point">
Key Point:<br>
Liferay provides three primary levels of integration for LDAP:
<ol>
	<li> LDAP Authentication
	<li> LDAP User Import
	<li> LDAP User Export
</ol>
</div>

These services are encapsulated in the portal-ldap module - `com.liferay.portal.ldap.jar`.

The first thing you'll need to do is connect to an LDAP server. Once you have that taken care of, you'll need to configure Liferay's connection to your LDAP server. All LDAP settings can be configured through _`System Settings → Foundation`_ and `.config` files.

<img src="../images/sso-ldap-ldap-server.png" style="max-width:100%;">

For some situations, you may want to offload some or all user management duties from Liferay to LDAP. In these cases, you can configure Liferay to authenticate through the connected LDAP server through the *LDAP Auth* tab. Here, you can configure whether LDAP Authentication is enabled or even required (meaning that all logins must come for LDAP). You can also configure other settings regarding the password policy and encryption.

<img src="../images/sso-ldap-ldap-auth.png" style="max-width:100%;">

<div class="key-point">
Key Point:<br>
There are two methods that can be used for authentication:  
<ol>
	<li> <b>Bind</b> means that LDAP manages the full authentication, and a request is sent to LDAP when a user wants to sign in.
	<li> <b>Password-compare</b> validates the password on Liferay, and then compares it with the LDAP password to confirm the LDAP sign-in.
</ol>
Bind is preferred, since, among other things, password-compare provides a potential vulnerability if the passwords are not encrypted during the transaction.
</div>

If you're using LDAP for authentication, you may want to export existing Liferay users or any users created in Liferay to the LDAP server. Under *Import/Export* in the _`Authentication → LDAP`_ section of Instance Settings, you can configure if users will be exported, and whether or not their associations will be exported from Liferay to LDAP.

<img src="../images/sso-ldap-ldap-export.png" style="max-width:100%;">

Import enables you to make all of your LDAP users also Liferay users. If you want to use the full power of Liferay for user management, you will need to import users. If you don't _bulk-import_ users, Liferay will import them one at a time as you log in with them. You can configure various aspects of the import through the *LDAP Import* configuration.

<img src="../images/sso-ldap-ldap-import.png" style="max-width:100%;">

Regardless of what technology you'll use, you'll need to answer similar questions for how it interacts with Liferay. Questions like: Where are the users stored? How are the passwords encrypted? Who is responsible for managing authentication?

### Using LDAP with Liferay

Liferay has a generic user import service that imports users from any external system into Liferay's user store. The LDAP user import is an LDAP-specific implementation of this service. 

<div class="key-point">
Key Point:<br>
There are two paths during which users can be imported from LDAP:
<ol>
	<li> When LDAP authentication is enabled, any user that successfully authenticates into the platform will be automatically imported into the Liferay user store.
	<li> When LDAP-scheduled import is enabled, on a regularly configured interval, users are imported from LDAP into the Liferay user store.
</ol>
</div>

Liferay also has a generic user export service that complements the user import service. The user export service is capable of exporting users to any external system from Liferay's user store. The LDAP user export is an LDAP-specific implementation of this service. 

<div class="key-point">
Key Point:<br>
There are a few conditions under which users would be exported to LDAP:
<ul>
	<li> When users are added to Liferay's user store
	<li> When user profiles are modified in Liferay's user store
	<li> When users are added to User Groups
	<li> When users are removed from User Groups
	<li> When users are added to Roles
	<li> When users are removed from Roles
</ul>
</div>

Prior to 7.0, LDAP-related configurations were present in two places:
- `portal.properties`
- The database

Certain values were configurable through the Portal Settings user interface _`Authentication → LDAP`_. This led to a lot of inconsistent behavior and attempts to decipher which values should be changed where. Starting with 7.0, all LDAP configurations are configured using *System Settings* and `.config` files.

<br>

<div class="key-point">
Key Point:<br>
Let's take a look at the settings we have available for LDAP Import in <i>System Settings → LDAP Import</i>.
<ul>
	<li> <b>Enable Import</b> enables the import of users from LDAP to Liferay; all the other settings are meaningless if this isn't activated.
	<li> <b>Enable Import on Startup</b> will enable a mass import when Liferay starts.
	<li> <b>Import Interval</b> determines how many minutes Liferay will wait before checking to see if it needs to do an import.
	<li> <b>Import Method</b> determines whether Liferay will look at LDAP Groups and import all users in a group, or look at LDAP users and then import all groups associated with a user.
	<li> <b>Lock Expiration Time</b> sets how long it will take for a lock to expire in seconds.
	<li> <b>Import User Sync Strategy</b> determines how Liferay will check to see if a user already exists before importing them.
	<li> <b>Enable User Password</b> will enable the import of passwords for LDAP users.
	<li> <b>Autogenerate User Password</b> will create a new Liferay password for all LDAP users.
	<li> <b>Default User Password</b> will set a fallback password if one is neither imported nor generated.
	<li> <b>Enable Group Cache</b> will cache lookups during LDAP imports. This will improve performance, but possibly lose updates that happen on the LDAP side.
	<li> <b>Create Role per Group</b> automatically creates a Liferay Role for each group imported through LDAP.
</ul>
</div>

When performing an import, you'll need to make sure that your LDAP mappings match up with Liferay's. In the *LDAP Servers* configuration page, you can take a look at the default mappings that are configured between Liferay and LDAP:

<img src="../images/ldap-mappings.png" style="max-width:100%;">

You can edit these mappings if they don't match up to your specific implementation. In addition, if you have some custom field that needs mapping, you can click the "plus" icon for a *User Mappings* field to add an additional field that you can enter a custom mapping in:

<img src="../images/ldap-custom-mappings.png" style="max-width:100%;">

You can also create **Custom Fields** in Liferay to correspond to LDAP fields. From _`Control Panel → Configuration → Custom Fields`_, you can create various types of fields, including additional user data. You can map a custom field to an LDAP attribute.

Export is a bit simpler than import. If you want to keep your LDAP and Liferay users in sync, you'll need to export back to LDAP. **Export Enabled** allows modifications made to user information in Liferay to be synced back to LDAP. **Export Group Enabled** will include groups and associations in the export from Liferay to LDAP.

<!-- No longer applicable: OpenDJ is a free, open-source LDAP server sponsored by ForgeRock. It's a cross-platform, entirely Java-based application. OpenDJ provides a control panel, which allows you to manage the LDAP server and browse the directory using a graphical interface. We've already installed OpenDJ on your VMs. We've also imported some sample data into LDAP: two Organizations called *Astronauts* and *Engineers* and some users belonging to these Organizations. We'll import these LDAP Organizations and users into Liferay.-->

### Using SSO with Liferay

Consider three organizations that maintain a number of distinct web applications. Organization A uses no sign solution, and users need to remember user name/password combinations for each distinct web application. Organization B also uses a number of distinct web applications, but uses LDAP credentials to authenticate to each one. Members of Organization B only have to remember one user name/password combination (their LDAP credentials), but they still have to authenticate to each distinct web application separately. Finally, Organization C uses SSO to provide access to a number of distinct web applications. Members of Organization C only have to remember one user name/password combination (their SSO credentials), and they only have to authenticate once to gain access to all the systems.

<div class="key-point">
Key Point:<br>
There are several different ways to integrate with SSO providers:
<ul>
	<li> Token
	<li> Facebook
	<li> OpenID
	<li> CAS
	<li> OpenSSO
	<li> NTLM
	<li> Google
<ul>
</div>

Only one SSO provider is supported per deployment. SSO configurations are found in two places:

- System level: _`Control Panel → Configuration → System Settings → Foundation → [SSO-Type]`_
- Instance level: _`Control Panel → Configuration → System Settings → Authentication → [SSO-Type]`_

Token SSO authentication was introduced in Liferay 7 to standardize support for Shibboleth and SiteMinder and any other SSO product that works on the basis of propagating a token via one of the following mechanisms:
- HTTP request parameter
- HTTP request header
- HTTP cookie
- Session attribute

The token contains either the Liferay user's screen name or email address, whichever Liferay has been configured to use as a user name for that particular company.

### CAS Configuration Fields

Let's take a look at some of the CAS Configuration fields: 
- `importFromLDAP`: Users authenticated from CAS that do not exist in the platform are imported from LDAP. LDAP must be enabled separately.
- `loginURL`: CAS server login URL `logoutOnSessionExpiration`: If true, then the browser with expired sessions will be redirected to the CAS logout URL.
- `logoutURL`: The CAS server logout URL. Set this if you want the platform's logout function to trigger a CAS logout.
- `serverName`: The name of the platform (e.g., liferay.com). If the provided name includes the scheme (https://, for example), then this will be used together with the `path/c/portal/login` to construct the URL that the CAS server will provide tickets to. If no scheme is provided, then the scheme normally used to access the Liferay login page will be used.
- `serverURL`: The CAS server base URL. For example, http://server.com:8080/cas-web
- `serviceURL`: If provided, this will be used as the URL that the CAS server will provide tickets to. This overrides any URL constructed based on the Server Name as above.
- `noSuchUserRedirectURL`: Set the URL to redirect the user if the user can authenticate with CAS but cannot be found in the platform. If "Import from LDAP" is enabled, the user is redirected if he cannot be found or could not be imported from LDAP.

### Facebook Configuration Fields

Facebook SSO is an integration with Facebook's Graph API and works on the basis of retrieving the user's Facebook profile information, and then attempting to match existing Liferay users on either Facebook ID or email address. In order to integrate Liferay with Facebook, you must first create an "application" on Facebook's website at https://developers.facebook.com. This is because Facebook Connect requires Liferay to authenticate using the OAuth 2.0 protocol. Facebook will provide you with the necessary application ID and secret that will be used in OAuth messages sent between Liferay and Facebook. One benefit of this is allowing the Facebook user to revoke access from Liferay at a later date. The integration will attempt to retrieve the following Facebook profile information in order to successfully create a user in Liferay:
- Email Address
- First Name
- Last Name
- Gender

Let's take a look at some of Facebook's configuration fields:

- `Enabled`: Check this box to enable Facebook Connect SSO authentication.
- `Verified account required`: Check this box to allow logins by Facebook users who have gone through the Facebook email verification process to prove that they can access the inbox associated with the email address they provided when registering for a Facebook account.
- `Application ID`: Enter the ID of your registered Facebook application (can only be set at the platform instance level).
- `Application Secret`: Enter the secret of your registered Facebook application (can only be set at the portal instance level).
- `Graph URL`: The base URL of the Facebook graph API. You will only need to change this if Facebook changes their graph API; otherwise, the default URL will suffice.
- `OAuth Authorization URL`: Facebook's OAuth authorization URL. You will only need to change this if Facebook changes their OAuth authorization endpoint. This URL will be decorated with dynamic data and linked to from the Liferay login portlet.
- `OAuth Token URL`: Facebook's OAuth access token URL. Liferay will use this URL to exchange a request token for an access token.
- `OAuth Redirect URL`: This is the URL that the user will be directed back to once an OAuth request token has been generated. The URL points to a Liferay service that will exchange the request token for the access token, which is required in order for Liferay to make successful calls to the Facebook Graph API. You should only ever need to change this URL if requests to your Liferay instance need to go via a fronting web server such as Apache that does URL rewriting.

### NTLM Configuration Fields

OpenID SSO authentication was introduced in Liferay. As a decentralized authentication protocol, it leaves the user to decide which iDP (Identity provider) the user wishes to use to log into Liferay.
Let's take a look at a few NTLM Configuration fields: 
- `enabled`: If enabled
- `domainController`: IP address of your domain controller
- `domain`: The domain / workgroup name
- `serviceAccount`: You need to create a service account for NTLM. This account will be a computer account, not a user account.
- `servicePassword`: Enter the password for the service account.
- `negotiateFlags`: Only available at system level. Set according to the client's requested capabilities and the server's ServerCapabilities.

### Google Configuration Fields

Here are a few of Google's Configuration fields: 
- `enabled`: If enabled
- `clientId`: The client ID provided by Google
- `clientSecret`: The client secret provided by Google

### Open SSO Configuration Fields: 

- `enabled`: if enabled
- `importFromLDAP`: If true, then users authenticated from OpenSSO that do not exist in the platform are imported from LDAP. LDAP must be enabled.
- `loginURL`: The URL to the login page of the OpenSSO server
- `logoutURL`: The URL to the logout page of the OpenSSO server
- `serviceURL`: The URL where OpenSSO can be accessed to use the authentication web services
- `screenNameAttr`: The name of the attribute on the OpenSSO representing the user's screen name
- `emailAddressAttr`: The name of the attribute on the OpenSSO representing the user's email address
- `firstNameAttr`: The name of the attribute on the OpenSSO representing the user's first name
- `lastNameAttr`: The name of the attribute on the OpenSSO representing the user's last name

OpenAM is a free, open-source SSO solution sponsored by ForgeRock. OpenAM is a fork of the OpenSSO project that began when Oracle discontinued development of OpenSSO. It's a cross-platform, Java-based application that runs on a servlet container. We've put a separate Tomcat server on your VMs: `/opt/openam/tomcat`. If you want to install and set up OpenAM to test with Liferay, you can try it here!

### Configuring for SAML

<div class="key-point">
Key Point:<br>
With talk of SSO, LDAP authentication, and LDAP user importing, it's important to note that:
<ul>
	<li> SSO solutions can have different formats, protocols, and interchange formats.
	<li> LDAP, regardless of implementation, uses the same format and protocol.
</ul>
</div>

Since LDAP is standardized, all LDAP servers meet an expectation for API, content, and behavior. SSO in general doesn't have that standard. With SAML, however, you can have a standards-based authentication framework.

SAML (*Security Assertion Markup Language*) is both the format for content and the protocol standard for authentication and authorization. Liferay only supports implementing the authentication component, but it's a very important and widely-used standard. SAML-compliant servers and clients communicate using an XML-based document that uses the markup. Any number of clients can use SAML, and they have no idea how it's implemented. 

<br>

<div class="key-point">
Key Point:<br>
SAML architecture is based on two concepts:
<ul>
	<li> <b>Identity providers (IdP):</b> These are servers that manage user information, including user names, email addresses, passwords, and more.
	<li> <b>Service providers (SP):</b> Services or applications that provide functionality the user wants, but that need to be authenticated.
</ul>
Liferay can operate as either an SP or an IdP.
</div>

<img src="../images/SAML.png" style="max-width:100%;">

Liferay can fulfill the role of either the Identity Provider or Service Provider. When acting as an *Identity Provider*, Liferay becomes the source of authentication - other services ask Liferay to authenticate for them. When Liferay is a *Service Provider*, Liferay's asking to use the SAML *IdP* to authenticate users.

Liferay can fulfill either role, but needs to provide certain information to plug into the SAML framework:
- Certificate
- Private Key
- Name
- User attribute mapping

Providing a unique name is an easy thing to configure, but the rest may sound difficult.

When configuring SAML, security is an important concern. To confirm your identity and participate in communication, you'll need a certificate and private key. Liferay uses some basic information to generate:
- Your name
- The name of your organization or company
- Your address or the address of the company
- How many days the key will be valid
- The encryption algorithm for the key
- The password for the key

Liferay will then be able to generate the certificate and private
key for others to authenticate you.

Whether you're acting as a *Service Provider* or an *Identity Provider*, you may need to make sure that the user model in SAML works with your Liferay users. User attribute mapping lets you map SAML user attributes to the Liferay user object. This ensures that the correct information for binding (like user name, email address, etc.) is properly communicated.

Whether you want to use Liferay as an *SP* or an *IdP*, a few configuration changes are all that's needed. Liferay can ask to authenticate users through the SAML *IdP* and use it much like an SSO. Liferay can also become the *IdP* for other *SP*s in SAML. You can even set up one Liferay instance as an *IdP*, and other Liferay instances as *SP*s, turning Liferay into its own SSO.

<div class="summary"><h3>Summary</h3>

<ul>
	<li>__________________________________________ (_________) describes a number of services that enable users to manage sign-ons to multiple sites or services using ______________________________________________________</li>
	<li>_______________________________________________________________________________________________________________________________ (______________) is one of the most popular SSO solutions.</li>
	<li>______________ is an ____________________________________________________ for accessing and maintaining distributed directory information services over an IP network.</li>
	<li>Liferay provides three primary levels of integration for LDAP:</li>
	<ul>
		<li>_________________________________________________</li>
		<li>_________________________________________________</li>
		<li>_________________________________________________</li>
	</ul>
	<li>There are two options for importing users from LDAP:</li>
	<ul>
		<li>When LDAP _______________________________ is enabled, any user that ________________________________________________________________ into the platform will be automatically ________________________________ into the Liferay user store.</li>
		<li>When _______________________________________________ import is enabled, on a regularly configured interval users are imported from LDAP into the Liferay user store.</li>
	</ul>
	<li>In Liferay DXP, all LDAP configurations are configured using ___________________________________________________ and __________________________ files.</li>
	<li>____________ solutions can have different ________________________, _____________________________, and _______________________________________________________.</li>
	<li>Only one ___________________________________________ is supported per deployment.</li>
	<li>________________ (______________________________________________________________________________________) is both a ____________________ for content and the ______________________________________ for authentication and authorization.</li>
	<li>________________ architecture is based on two concepts.</li>
	<ul>
		<li>______________________________________________: these are servers that manage user information, including user names, email addresses, passwords, and more.</li>
		<li>______________________________________________: services or applications that provide functionality the user wants, but that need to be authenticated.</li>
	</ul>
	<li>Liferay can operate as either a __________________________________________________ or an ____________________________________________________________________________.</li>
</ul>
</div>
