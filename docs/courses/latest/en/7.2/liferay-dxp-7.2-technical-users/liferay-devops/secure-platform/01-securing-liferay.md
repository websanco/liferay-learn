## Hardening Your Liferay Solution
Hardening is the process of securing a system by reducing its vulnerability. Reducing available avenues of attack typically includes the removal of unnecessary software, user names, or logins, and disabling or removing unnecessary services. The ultimate "hardened" computer would be one with no internet access, no input ports or devices, and no physical access. This computer would also be completely useless. When hardening, we need to strike a balance between functionality and security.

<div class="key-point">
Key Point:<br>
There are three primary layers of hardening:
<ol>
	<li>Network
	<li>Server / Operating System
	<li>Application
</ol>
</div>

Each level comes with its own challenges and techniques for optimization.

<img src="../images/sysadmin-hardening-layers.png" style="max-width:100%;">

Usually Liferay's operating environment has been spread out to multiple servers. Design your network to be safe: Protect your network by firewall to control access to various servers. Remove or disable unnecessary communication between servers. Liferay should be at least behind an HTTP server to give an additional level of protection. Use a dedicated proxy server to connect Liferay to remote services via internet (pass-through).

<img src="../images/hardening-servers.png" style="max-width:100%;">

<br>

Earlier, we joked that the perfectly hardened server would have no physical access and no input ports or devices. The fact is, once someone with bad intentions gains physical access to a device, it's only a matter of time until the device is compromised. Physical servers should stay in a locked room and have strictly limited user access.

<div class="key-point">
Key Point:<br>
Some good rules to establish for server administration (Unix, Linux) include:
<ul>
	<li>Administrators should use their own personal user account to administer
	<li>No root-level access; use SUDO only
	<li>Audit administrators
</ul>
</div>

<br>

Since the goal is to maximize both function and security, any time you can improve both simultaneously, you should take it as a quick win. Disable (remove) unwanted services. Block unnecessary ports with the firewall. All applications and services should run with their respective operating system account. Always make sure the host OS is up-to-date and install the latest security fixes. Analyze the server to detect potential leaks (e.g., open ports, services, user accounts).

The Application Server should run with its respective operating system account. (**Do not** run as root). You should also configure your web server to run daemon as a separate user and make `root` the owner of the application server installation. Use log rotation to prevent the disk from being full. Allow only HTTP servers to connect to the application server. Configure protocols per need: HTTP, HTTPS, mixed mode. There are also specific configurations for hardening the HTTP server (Apache).

<br>

<div class="key-point">
Key Point:<br>
You also need to strengthen the database server:
<ul>
	<li> Limit access to servers.
	<li> Create a database user for Liferay that only has access to one schema.
</ul>
</div>

Inside of Liferay, managing account creation is a way to improve security. Disable account creation if registration is not required:
```properties
#portal.properties
company.security.strangers=false
```
Verify what account creation settings are necessary for your usage. Check default Roles / Site memberships / Groups / Organizations at _`Configuration → Instance Settings → Users`_.

<br>

<img src="../images/hardening-default-user-setup.png" style="max-width:100%;">

<br>

You can **disable** a user's personal public and private pages ("My Profile" and "My Dashboard") if those pages won't be needed by editing the following properties:
```properties
#portal.properties

layout.user.public.layouts.enabled=false
layout.user.public.layouts.power.user.required=false
layout.user.public.layouts.auto.create=false
layout.user.private.layouts.enabled=false
layout.user.private.layouts.auto.create=false
layout.user.private.layouts.power.user.required=false
```

When you start Liferay for the first time, a default administrative user will be created. You should change the default administrative account for any production Liferay instance.
```properties
# portal.properties default values

default.admin.password=test
default.admin.screen.name=test
default.admin.email.address.prefix=test
```

Liferay offers various encryption algorithms. You can manage Liferay's encryption with these properties:
```properties
#passwords.encryption.algorithm.legacy=BCRYPT
#passwords.encryption.algorithm.legacy=MD5
#passwords.encryption.algorithm.legacy=SHA
...
```

**Change** encryption algorithms as you need to. Stronger encryption will provide better security but will cost performance (i.e., increase login times).

By default, Liferay uses PBKDF2WithHmacSHA1/160/128000, which is very robust. For faster logins, you could configure a less robust encryption algorithm, but then you would be compromising security. One way to mitigate this is to use SSO for login. All of the heavy lifting of password encryption is handled by the SSO provider, and, in fact, Liferay doesn't even need to store or encrypt any of the login information. SSO is one example of how offloading complex tasks to another service can both increase security and improve performance simultaneously.

Auto-login functionality can be a great convenience, but you want to make sure that any unused services are disabled to properly secure your server. You can **disable** un-used auto-login functionality and servlet filters with these properties:

<br>

```properties
#portal.properties, changed default "true" value

com.liferay.portal.servlet.filters.autologin.AutoLoginFilter=false
com.liferay.portal.servlet.filters.sso.cas.CASFilter=false
com.liferay.portal.servlet.filters.sso.ntlm.NtlmFilter=false
com.liferay.portal.servlet.filters.sso.ntlm.NtlmPostFilter=false
com.liferay.portal.servlet.filters.sso.opensso.OpenSSOFilter=false
com.liferay.portal.sharepoint.SharepointFilter=false

#Remove any attached auto login hook
auto.login.hooks=
auto.login.ignore.hosts=
auto.login.ignore.paths=
```

Liferay DXP contains sample data for testing purposes. Make sure that the sample data is not installed for production.

Although there may not be a major security risk attached to any of them, removing unused portlets can help to both improve performance and prevent any potential vulnerabilities.

<div class="key-point">
Key Point:<br>
Non-used OSGi portlets can be removed from Liferay like this:
<ul>
	<li> <b>Delete</b> the corresponding OSGi bundle (JAR) from LIFERAY_HOME/osgi/modules.
	<li> <b>Back up</b> before deleting.
	<ul>
    	<li>Some OSGi modules (including portlets) are essential for minimal platform operation and cannot be deleted.
	</ul>
</ul>
</div>

### Password Policies {#pass}

One of the simplest ways to compromise a system is by taking advantage of insecure passwords. Every time there is a major data breach and passwords are compromised, you see lists of people using passwords like "12345" or "password". Sometimes it's a little bit more complicated, and they go with "baseball" or "qwerty", but bad passwords are everywhere. In addition to bad passwords, you have old passwords. If a shared account password hasn't been changed in five years, that means that the guy who got fired five years ago still has the password, and that's not a good thing. This is where password policies come in.

Fortunately, this simple method of compromising a system has a simple solution: make better passwords. Of course, you can't count on users to make better passwords by choice, so you'll need to force them to do it. This is where **Password Policies** come in. Password Policies enable you to define rules that will help make your system and users more secure.

<div class="key-point">
Key Point:<br>
You can use password policies to configure:
<ul>
	<li>Password Changes
	<li>Password Syntax Checking
	<li>Password History
	<li>Password Expiration
	<li>Lockout
</ul>
</div>

Let's take a look at a few common policy elements:

- **Password Changes** sets rules around system-generated passwords and password resets. You can also use it to set a minimum age for a password if you don't want users to constantly change their passwords.
- **Password Syntax Checking** allows you to require a specific length or a specific number of letters, numbers, symbols, uppercase letters, and total characters in a password. You can also exclude dictionary words from being used.
- **Password History** prevents a user from resetting a password to one used before.
- **Password Expiration** sets details on how long a password can be used before it expires.
- **Lockout** enables you to set rules as to when a user will be locked out for entering the wrong password too many times.

Each element of a password policy serves a different purpose. Forcing a user to change a system-generated password eliminates the risk of a password being compromised through new account setup or a password reset email. Syntax checking prevents users from creating easily guessable passwords. Combined with Lockout, it helps prevent brute force attacks (where a script tries numerous possible passwords in hopes of guessing the right one eventually). Password history and expiration settings help prevent situations like a stale password that may have been written on a post-it note and thrown away from being found and used. These settings also frequently prevent users from using the same password on their email or Facebook as they do for more sensitive accounts.

### Password Hashing {#hash}

Of course, none of this matters much if you're storing plain text user credentials in a `.txt` file on your server, and this is where password hashing comes in. Hashing enables you to protect the passwords themselves from being compromised. By default, Liferay uses PBKDF2WithHmacSHA1/160/128000 hashing (Password Based Key Derivation Function, 160 bits, 128000 rounds) to provide the level of security recommended by the Open Web Application Security Project (OWASP). You can choose a different algorithm any time. Today, the algorithm is typically chosen for how much it discourages brute-force discovery. You can choose from several hashing algorithms. You can configure them in the `portal.properties` file:

```properties
passwords.encryption.algorithm=PBKDF2WithHmacSHA1/160/128000
```

Available hashing algorithms are:
* Key derivation with hashing:
	* PBKDF2
	* BCRYPT/10
	* UFC-CRYPT
* Just Hashing:
	* MD2 or MD5
	* SHA or SHA-256 or SHA-384
	* SSHA
* Unsecure:
	* NONE

<div class="note">
    Note: If "NONE" is used, passwords will be stored as plain text in your database. This is a BAD IDEA.
</div>

The most basic level of password protection is simple hashing. Source text is taken in, and a hashing function performed. The hashing function typically separates the source text into blocks (8-byte, 16-byte, etc.) The blocks are then operated on in any number of ways depending on the algorithm. Blocks are reassembled, and the starting *n*-number of bytes are pulled off and returned as the hash.

<img src="../images/password-hash.png" style="max-width:100%;">

<div class="key-point">
Key Point:<br>
Hash functions can be secure depending on:
<ul>
	<li> Hash length
	<li> Block size
	<li> Number of iterations
	<li> Number of possible collisions
</ul>
In general, the fewer the collisions and greater the hash length, the more secure. The following are considered less secure and not recommended:
<ul>
	<li> MD2 and MD5
	<li> SHA
	<li> SSHA
</ul>
</div>

More complex key derivation functions are a step up from plain hashing. Here are some examples:
- BCRYPT
- PBKDF2
- UFC-CRYPT

The general principle behind all derivation functions involves applying a hash function (such as SHA, SHA-256, Blowfish, etc.), mixing in a salt value (a pseudo-random set of bytes), and taking the output and possibly repeating the process a number of times.

<img src="../images/password-crypt.png" style="max-width:100%;">

The resulting hash is more robust against brute force than simple hashes and has lower possible collision rates. Key derivation is *much* more expensive than hashing, depending on key length. Some key derivation functions also involve a secret password in addition to the salt value. Some of the more expensive but secure functions include:
- PBKDF2
- BCRYPT

This enhanced security comes at a performance cost and potentially delayed login times or throughput for concurrent logins.

If you're using LDAP, you may have mixed users, some originating from LDAP and some from Liferay. This means that you might have different policies for both. But what we're more concerned with is the specific policies for LDAP users.

<div class="key-point">
Key Point:<br>
There are basically three cases based on your configuration:
<ol>
	<li>The default behavior: Liferay imports and stores all LDAP passwords in its database, hashing them and making them conform to policies accordingly.
	<li> The LDAP password is ignored, and a new Liferay password is generated according to Liferay's policies.
	<li> LDAP is configured to manage passwords and becomes responsible for the management and storage of all passwords.
</ol>
</div>

For maximum security and performance, you can offload all password management to LDAP or SSO and not store any user passwords in Liferay. You can configure LDAP policies through *System Settings*, under _`LDAP → Authentication`_. Instance-level settings can also be configured through *Instance Settings* under _`LDAP → General`_.

<img src="../images/password-ldap-conf.png" style="max-width:100%;">

### Securing Liferay at the Network Level {#network}

We've looked at a broad view of security, ranging from the physical security of your servers to user management and administrative account policies. Now we're going to take a closer look at network security. We need to ensure that the connection between your site and your users is secure. We'll look at TLS, HTTPS, and the other technologies you need to secure your site and ensure the privacy and security of your data and your users' data. But due to local machine names and the need for certificates for local machines, we won't have an exercise actually setting it up.

<div class="key-point">
Key Point:<br>
Our deployment architecture features multiple layers to support our applications:
<ul>
	<li> Load balancers
	<li> Web servers
	<li> Java EE servers
	<li> Document repositories
	<li> Search servers
	<li> Databases
</ul>
</div>

Some of these may be cloud services and some may be local to our installation. In all cases, they will exist on networks that may be shared with one another.

In general, we want to separate each tier or layer in its own subnet. This way, network addressing is very distinct between each type of server. An added benefit is that network chatter is kept at a minimum in each subnet. Since each network segment will be behind its own router, we can ensure internal security.

Network communication can be limited through router firewalls: Each layer should be able to send packets to directly connected layers. The database server shouldn't be able to send packets to the load balancers. Each layer should only be able to receive packets from layers it needs to hear directly from. The Java EE servers shouldn't receive network traffic from the load balancer, and especially not from the outside, but only directly from the web servers. These are just general guidelines that emphasize the need to separate and secure each network segment of the deployment. Specifics will vary widely based on your specific needs and use case. You may have network administrators that already take care of ensuring the security of your infrastructure. Liferay's Global Services team can also provide hands-on assessment and assistance of your production needs.

<br>

### Encryption Tools {#encrypt}

<div class="key-point">
Key Point:<br>
<ul>
	<li><b>Secure Socket Layer</b>, commonly referred to as <b>SSL</b>, is an encryption technology for securing the connection between your web server and your users.
	<li><b>Transport Layer Security</b>, or TLS, is a similar technology for web encryption. Both protocols use a <b>Public Key Infrastructure</b> system to encrypt data.
</ul>
</div>

SSL was superseded by TLS years ago. You probably wouldn't find any SSL in the wild any more, but both terms are still used synonymously.

A **Public Key** is used to encrypt data. A **Private Key** is used to decrypt data. When you access a website using one of these technologies, your browser will receive a "certificate" indicating that the website is encrypting your data.

<div class="key-point">
Key Point:<br>
<b>Hyper Text Transfer Protocol Secure</b>, or HTTPS, is the combination of HTTP and SSL or TLS technologies.
</div>

With standard HTTP, someone monitoring traffic over a network would have all of the data, from pages visited to user names and passwords to credit card numbers, sent over that connection. HTTPS uses encryption to prevent this from happening. When you access a website using HTTPS, your browser receives the Public Key and encrypts all the information that you send to the site. The Private Key is maintained on the web server and is the only thing that can decrypt the data that is sent.

Generally speaking, a page is either served up encrypted through HTTPS or not using HTTP. But in order to maximize performance while maintaining security, we also have what are known as **Mixed Mode** pages. In a Mixed Mode page, a logged in user will use HTTPS while a "guest" user will simply use HTTP. This can improve performance, but also adds some complexity to your configuration. Depending on the type of site that you're running, you may have mixed results in using this to improve performance. If most users are always logged in anyway, you won't see much benefit, but if you have a large number of unauthenticated users and only a handful who need to login, it might be more beneficial.

In our current setup, web traffic will initially hit the HAProxy load balancer that we have set up, and then be directed to the Liferay-Tomcat node. Traffic between your users and the load balancer is via HTTP. Traffic between the web server and your Java servers can be HTTP or AJP. If you use Apache httpd instead of HAProxy, and `mod_proxy_ajp` for communication, you'll use AJP, or you can use `mod_proxy_http` or `mod_jk_http`. AJP is often preferred, as it provides additional metadata in the communication over HTTP. In either case, if you're using HTTPS, you will need to also use a TLS connector to secure traffic between the load balancer/reverse proxy and the application server.

### Security Settings {#securitysettings}

Many security settings are configured through `portal.properties`, while others are configured through *System Settings*/`.config`.

<div class="key-point">
Key Point:<br>
There are a number of important properties to check for security purposes. Here's an excerpt:
<ul>
	<li> <code>jdbc.default.jndi.name</code>: Liferay's default configuration uses the manual database configuration with the driver, URL, name, and password stored in plain text in your configuration file. However, you can utilize JNDI to replace the standard configuration and secure the configuration.</li>
	<li> <code>company.security.strangers.*</code>: If you are running an intranet, you probably don't want random users to generate new accounts on your site. Know that by default they can.</li>
	<li> <code>default.admin.*</code>: You'll want to disable the default administrator account in most cases to eliminate the possibility of the account being compromised.</li>
	<li> <code>com.liferay.portal.servlet.filters.*</code>: Various filters that are active by default. If they refer to a product name that you don't know, most likely they can be disabled (e.g., for the SSO options that Liferay supports, you can disable either all or all but the one that you're using).</li>
</ul>
</div>

You can see the full reference here and find more security-related properties for your configuration at https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html.

Many security settings are also available through *System Settings*, which we've already covered.

<div class="key-point" style="page-break-before: always">
Key Point:<br>
The primary login and authentication configuration settings to be considered will be:
<ul>
	<li> <b>LDAP Configuration</b>: Under <i>LDAP</i>, you can find a number of LDAP settings, including configuration for whether passwords are stored in Liferay's database or in LDAP.
	<li> <b>Token-based SSO</b>: Contains additional settings for LDAP and other SSO options
</ul>
</div>

For a clustered environment, remember, all settings will need to be exported and deployed to other nodes, just like properties will need to be edited for all nodes.

One of the easiest ways to keep your system secure is simply to keep your system up to date. Liferay provides easy-to-install security updates for DXP users. Keeping up to date with all of the software in your web server stack will also protect you from any potential vulnerabilities in that software.

### Securing Services {#services}

<div class="key-point">
Key Point:<br>
Remote access to services is passed through multiple layers:
<ul>
	<li> <b>IP Permission Layer</b>: Requests must originate from an IP address that is whitelisted in portal.properties.
	<li> <b>Authentication Verification Layer</b>: Verifies the authentication token to check if it returns a valid user
	<li> <b>Permission Layer</b>: Checks to see if the user has the rights to perform the service
	<li> <b>Security Access Profile Layer</b>: Whitelist of services/functionality a user has access to
</ul>
Web service requests must pass all four layers of security.
</div>

<img src="../images/remote-service-invocation.png" style="max-width:60%;">

A remote agent needs to originate from an IP or host allowed by Liferay. IP addresses can be listed explicitly in `portal.properties`:
```bash
main.servlet.hosts.allowed=SERVER_IP,230.20.45.255
```

<br>

<div class="key-point">
Key Point:<br>
There are a number of servlets that can be IP-filtered:
<ul>
	<li> <code>main.servlet.hosts.allowed</code>: This affects all of the servlets and may block useful services like RSS/Atom feeds and regular web pages from the general public.
	<li> <code>axis.servlet.hosts.allowed</code>: All SOAP services are accessed through the Axis servlet.
	<li> <code>json.servlet.hosts.allowed</code>: All JSONWS services are accessed through the JSON servlet.
</ul>
</div>

Each property can be set to a list of addresses to limit access. You can use `SERVER_IP` to refer to localhost. A blank property *allows all hosts*.

Accessing remote services, especially through the web browser, often requires verification of user credentials.

<div class="key-point">
Key Point:<br>
Users can authenticate in a variety of ways:
<ul>
	<li> HTTP Basic Auth
	<li> HTTP Digest Auth
	<li> Java sessions
	<li> Shared secrets
	<li> Explicit credentials
</ul>
</div>

Whether a user has already authenticated or needs to authenticate in the request, services need to be able to protect against spoofing and other intrusion methods. The authentication verification (*AuthVerifier)* pipeline allows Liferay to verify a user's identity prior to invoking a service.

*AuthVerifier* modules can be turned on and off through their configuration in *System Settings*. Each verifier can also be active only on certain paths. For instance, we could have a verifier that runs only on `/o/rest/myservice`. When a request is filtered through a verifier, each *AuthVerifier* confirms the identity of the user. Based on the responses of the verifiers that are active, Liferay builds an *authorization context*. The *authorization context* contains information about the user so that the service can perform permission checks. If the user is not verified, the *authorization context* is created as a Guest. Guest users in Liferay have minimal permissions.

*AuthVerifiers* can be adjusted through *Control Panel → System Settings → API Authentication*. The available verifiers are Request Parameter, Auth Verifier, Portal Session, Auth Verifier, Basic Auth Verifier, Digest Auth Verifier, and Tunneling Auth Verifier. *AuthVerifiers* are passive; they don't prompt for authentication, they verify it.

Each *AuthVerifier* can be configured for any number of paths. You can add additional paths by using the *Add* button to add a new configuration. Configuration settings apply system-wide, so they'll apply to all nodes in a cluster.

<img src="../images/authverifier-paths.png" style="max-width:100%;">

Once applied to a set of paths, the verifier can be enabled or disabled. You can also exclude specific paths from the verifier if necessary.

Developers can easily create custom *AuthVerifier* modules. When installed, they should provide configuration settings so you can enable and disable them as necessary.

Once a request has made it through the IP Layer and the Authentication Layer, it must pass a permission check. It's up to the service to perform permission checks. Custom services may or may not have built-in permission checks on remote service access. Developers should implement permission checks wherever possible to verify identity before performing an operation.

Even if a user has permission to invoke a service, it must be whitelisted via a *Service Access Policy*. Policies can be configured individually and customized. We'll spend some time looking at sample policies.

<div class="key-point">
Key Point:<br>
Securing services can be approached from many levels:
<ul>
	<li> IP address filtering
	<li> Authentication verification
	<li> User permissions
	<li> Service whitelisting
</ul>
</div>

Using a combination of these methods provides the highest level of security to ensure that only authorized agents can remotely access services in your Liferay instances.

### Service Access Policies {#sap}

Once a user has access to a remote service, he or she can usually run any methods the service allows. As a best practice, services implement permission-checking on remote users. However, there may be situations where you'd like to limit what services and methods a user can access. Your developers may provide a new mobile messaging app that accesses Liferay's Message Board services.

By default, authenticated users can access all of the Message Board service methods. To protect against malicious users and code, you'd like to lock down the Message Board service to only allow certain methods through remote access. Liferay provides *Service Access Policies* to limit service access to a select whitelist of services and methods.

*Service Access Policies* operate like the doorman at a prestigious club. They contain a whitelist of service names and methods. If your request isn't on the list, the access policy will deny it. Multiple access policies can be in effect at a time, allowing a great amount of control. Access policies can be set by *AuthVerifiers* for remote users coming in.

*Service Access Policies* are defined in *Control Panel → Configuration → Service Access Policy*.

<div class="key-point">
Key Point:<br>
There are a number of default policies in effect:
<ul>
	<li> <b>SYSTEM_DEFAULT</b>: Applies to every request, including unauthenticated requests
	<li> <b>SYSTEM_USER_PASSWORD</b>: Requests whenever user authentication took place using a password
  <li> <b>OAUTH2_ANALYTICS.\*</b>: Integrates OAuth with Liferay Analytics Cloud, allowing OAuth to call JSON web services
  <li> <b>OAUTH2_EVERYTHING.\*</b>: Gives OAuth access to all JSON web services
</ul>
</div>

<img src="../images/service-access-policy.png" style="max-width:100%;">

Each *Service Access Policy* has two basic settings:
- **Enabled:** whether the policy can be used
- **Default:** whether the policy is always in effect whether or not a user is authenticated

<img src="../images/sap-options.png" style="max-width:100%;">

The default policies are used in certain situations. These policies are activated by their respective *AuthVerifiers*. Default policies will always be in effect. The policy *Name* and *Title* are important for *AuthVerifiers* to use the policy. Custom policies will only be used if there is a custom *AuthVerifier* that uses it or if it's a *default* policy.

For each policy, you can define which services are available:
- **Service Class:** the fully-qualified name of the service being allowed
- **Method Name:** the name of a method to allow

Both settings can be configured with *empty* values to allow *all options*. Both settings can accept wildcard (`*`) characters to allow multiple methods:

<br>

```
com.liferay.portal.kernel.*
get*
getBlog*
```

<br>

Take time to audit your Liferay instance's *Service Access Policies* along with the rest of the security settings. The default policies may be too permissive for your needs. You may also need to create custom profiles for all users or for specific custom applications. Use the policy lists to fine-tune what services can be accessed remotely.

<br>
<br>
<br>

### Security for Remote Staging {#remotestage}

Similar to other layers in our deployment, Staging introduces potential attack vectors. Like performance, security in Staging depends on whether you support *Local Live* Staging or *Remote Live* Staging.

<div class="key-point">
Key Point:<br>
In general, there are a few areas that Staging exposes:
<ul>
	<li> Network access to Staging
	<li> Client machine access to Staging
	<li> Passwords and encryption in Staging
</ul>
</div>

Whether you're using a *Local Live* setup or a *Remote Live* setup brings varying complications: Staging locally means securing the staging environment in tandem with production. Staging remotely means securing the machine that hosts the staging environment, the production environment, and communication between the two. In general, these principles apply: You should restrict network access to only what is necessary, limit outside access to staging wherever possible and use good encryption and password access as applicable.

<div class="key-point">
Key Point:<br>
When staging using a *Local Live* setup, you have two basic considerations:
<ul>
	<li> Using all production nodes for staging site access
	<li> Assigning one production node for staging site access
</ul>
</div>

As with performance, you can achieve better security if you designate one particular node for staging access. Using your Web Tier, you can restrict all staging traffic to only that server. Additionally, you can specify limits on external access to those URIs by limiting client access by IP forcing TLS and limiting to an IP range used by VPN servers internally.

When staging using *Remote Live*, the process is the same as adding a new network segment. The staging environment should exist in its own subnet, away from production or any other environment. Network traffic should only flow in one direction: Outside access to staging should only come from one external connection or router. The staging environment network should only allow incoming traffic from the external router. Traffic leaving staging can only go to the production network. Traffic can't flow "backwards". Production can't reach staging, and external clients can't reach production. As with *Local Live* security, you can require TLS, VPN access, limit IP ranges to inside a particular subnet for specific clients, or implement any other restriction you'd like to impose.

*Remote Live* staging adds another complication: the staging server needs to communicate and authenticate with the production server without a user inputting his or her password. This is done with the Tunneling Servlet using a *shared secret*. The shared secret can be a plain ASCII key or a hex key. The shared secret is then encrypted and sent over the network for verification on the remote production instance. You can control the encryption by setting one of various key production algorithms (AES, Blowfish, DESede, etc.) Encryption schemes are set through the property:
```properties
tunneling.servlet.encryption.algorithm
```

Securing your staging environment is generally as simple as walking through your network setup and defining who should have access. Additionally, concerns with *Remote Live* staging come down to using a good encryption standard and hex-encapsulated shared secrets of a good length. As long as you take the time to set up your staging environment intentionally and define access to it proactively, it can be a very secure environment.

<div class="summary"><h3>Summary</h3>

<ul>
	<li>___________________________ is the process of securing a system by reducing its ____________________________________________.</li>
	<li>______________________________________ enable you to define rules for what passwords are acceptable on your site.</li>
	<li>______________________________________ enables you to protect the passwords themselves from being compromised, revealed, or discovered.</li>
	<li>By default, Liferay uses ______________________________________ to provide the level of security recommended by Open Web Application Security Project.</li>
	<li>Network communication can be limited through ________________________________: Each layer should be able to send packets to __________________________________.</li>
	<li> The _____________________________ pipeline allows Liferay to verify a user's identity prior to invoking a service.</li>
	<li>______________________________________ contain a whitelist of service names and methods.</li>
</ul>
</div>
