## Import Your Users into Liferay

One of the main purposes of a DXP platform is to provide a great experience for your Users. You may need to manage many different kinds of User accounts on your platform, such as the following:
* Employees
* Customers
* Business Partners
* Contractors

Most companies will already have vast stores of users, each with unique data attached, such as: 
* First and Last Name
* Screen Name
* Password
* Email Address
* Group Membership

Easy, automatic integration with existing user databases is an important aspect of identity management. 

<br />

<div class="key-point">
Key Point: <br />
In Liferay, there are two primary methods for importing and authenticating outside Users:
<ol>
	<li>Importing Users from an LDAP server</li>
	<li>Single-sign on, OAuth 2, and SAML authentication options</li>
</ol>
</div>

<br />

<figure>
	<img src="../images/authentication-example.png" style="max-width: 100%" />
	<figcaption style="font-size: x-small">Fig.1 Authentication Options</figcaption>
</figure>

<br />

#### Livingstone Authentication Structure {#livingstone}

The Livingstone Hotels & Resorts team already has a company identity management system in place using LDAP (Lightweight Directory Access Protocol). It contains all the data for their employees and Livingstone Rewards customers.  They need a way to sync the data from Livingstone's identity management system. The Livingstone identity management system includes the following information about their users:
* First and Last Name
* Screen Name
* Job Title
* Email Address
* Group

The Livingstone team needs to add all their users to the platform to make sure they're given access to the proper content. The employees need access to the employee intranet, _Livingstone Loop_, as well as any other Site they'll be tasked with working on. Livingstone Rewards customers need to have access to a special loyalty customer dashboard as well as special content and campaigns designed just for them.

Once Josiah, the Livingstone platform administrator, has connected their identity management system to Liferay, he will need to automate keeping the two platforms in sync. When new users come to the platform and create an account, this will need to be updated in the identity management system and vice versa. He will also need to keep the two platforms in sync when modifying or creating groups. 

<figure>
	<img src="../images/LDAPImport.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.2 Livingstone Identity Management</figcaption>
</figure>

#### Liferay Authentication {#liferayauth}

Liferay comes out-of-the-box with a number of authentication options in the _Instance Settings_ found in _Control Panel → Configuration → Instance Settings_. 

Administrators can configure the general authentication settings for _Users_, those with an existing account, and _Guests_, those without any existing account. The settings can be configured to control the following:
1. How Users authenticate
2. If Users can log in automatically
3. If Users can request forgotten passwords
4. If User can request password reset links
5. If Users without any account can create one
6. If Users without any account can use the platform email
7. Whether or not to require Users without any account to verify their email address

<figure>
	<img src="../images/general-auth.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.3 General Authentication Options</figcaption>
</figure>

#### LDAP Mapping {#ldapmapping}

Liferay also integrates seamlessly with LDAP servers and can sync your current identity management system. Once connected to the platform, administrators can choose from the following configurations to determine what information is coming to the Liferay platform and what information to keep in sync with your identity management system: 
1. Enable Import from LDAP
2. Import or Autogenerate Password
3. Import individual Users or Groups
4. Create a Role per Group on Import
5. Enable Export from Liferay to the authentication system
6. Enable Group Export from Liferay to the authentication system
7. Use the LDAP Password Policy in Liferay

<figure>
	<img src="../images/ldap-first-look.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.4 LDAP Configuration</figcaption>
</figure>

<div class="key-point">
Key Point: <br />
Liferay integrates with LDAP and allows administrators to keep the systems in sync by: 
<ol>
	<li>Determining what data needs to be imported from the LDAP server</li>
	<li>Determining what data needs to be exported back to the LDAP server</li>
</ol>
</div>

#### Alternative Authentication {#alternativeauth}

Administrators can also set up Oauth 2 and SSO integration, which enable the user to log in once for all connected systems, including Liferay. Some of these alternate forms of authentication Liferay supports are:
 
* OAuth2 Authorize Screen Configuration
* SSOs Configuration:  
  * CAS Server
  * Token Based SSO
  * Facebook Connect
  * OpenID Connect
  * OpenSSO

<figure>
	<img src="../images/alternative-auth.png" style="max-width: 90%" />
	<figcaption style="font-size: x-small">Fig.5 SSO Configuration</figcaption>
</figure>

#### OAuth2 and SAML {#ssosaml}

SAML allows Users can log in with their credentials and are given access to other supported service providers. SAML works by transferring a user's identity from an identity provider (the system into which the user is logged in) to a service provider. OAuth2 on the other hand is a standard for the authorization of resources. Both can be used to use Liferay as both an identity and a service provider. 

<div class="key-point">
Key Point: <br />
OAuth2 and SAML integration allow administrators to further control access to different resources and content.
</div>

<!--
<div class="note">
Note: SAML Authentication is implemented via the SAML 2.0 Provider application. This application is available on Liferay Marketplace at <a href="https://web.liferay.com/marketplace/-/mp/application/15188711">https://web.liferay.com/marketplace/-/mp/application/15188711</a>.
</div>
-->

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>Liferay has two primary methods for importing and authenticating outside Users:</li>
	<ul>
		<li>Importing Users from an ____________________</li>
		<li>____________________, ____________________, and ____________________ authentication options</li>
	</ul>
	<li>Administrators can keep Liferay and LDAP in sync by what data needs to be ____________________ and ____________________.</li>
</ul>
</div>  
