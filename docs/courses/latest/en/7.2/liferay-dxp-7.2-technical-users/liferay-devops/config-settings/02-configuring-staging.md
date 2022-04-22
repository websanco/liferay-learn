Configuring for Staging
=======================

When setting up our sites and placing applications on the page, changes happen instantaneously. Say that we want to update our production site, so we begin moving things around and adding new applications and content. This will result in a poor user experience for customers as the production site updates in real time.

In Liferay, there is a way to make changes behind the scenes without affecting the live production site: *Staging*.

<div class="key-point">
	Key Point: <br />
	Staging creates an environment, called the staged environment, that is separate from your live site.
</div>

 With *Staging*, we are able to customize our site and have it contained within the staged environment, away from our live site. Once we are satisfied with our staged environment, we can push the changes to the live site. Effectively, this allows our Site Administrators flexibility and control over how their pages and content are configured. To be sure that we are using Staging properly, we have to keep in mind the following:

1. Staging calls a data handler for each application, asking the app to package a `LAR` file to be published to the live site.
2. All data validation is handled by each individual application.

<div class="key-point">
	Key Point: <br />
	Staging comes in two flavors:
	<ul>
		<li>Local Live, which allows you to use Staging on a single server</li>
		<li>Remote Live, which allows you to use multiple servers for Staging</li>
	</ul>
</div>

Let's take a look at the different methods of Staging and see what they entail.

#### Local Live Staging

Local Live creates the staged environment on the same server as your live environment. Because both the staged and live environment are on the same server, they also share the same database. When using Local Live, only the Assets that are selected in the `staged content` section will be staged.

When Local Live is enabled, the Live Site is copied to create the staged environment. Once Staging is turned off, the staged environment is deleted, along with any content that was in the staged environment. Local Live staging also shares one Global scope across both live and staged environments. With Local Live, we don't have to worry about sending data across any sort of medium, making publishing much faster than Remote Live. It's also faster to switch between the staged environment and the live environment. On the other hand, because the data is on the same database, it isn't well protected or backed up. Local Live also cannot install new versions of an app, since only one version of an app can be installed at any given time.

#### Remote Live Staging

Remote Live staging establishes a connection between a remote server and the current site. The site on the remote server becomes the live environment, while the current site becomes the staged environment. This allows each environment to use a separate database and two Global scopes.

With the data stored in two different databases, this ensures that our data is protected and backed up. New versions of applications can be deployed to staged environments without interfering with the live environment. One Liferay Instance can be used as the staging server for many production servers. However, publishing on Remote Live is slower, since data has to travel over a network. Additional hardware may also be required to run a separate staging server.

When pushing the staged environment to the live environment, by default all changes that are made will be pushed to the live environment. This keeps things simple so we don't have to worry about options or configurations. If you're interested, you can enter advanced settings for Staging and configure what is published to live.

#### Setting up a Remote Staging Server

Remote Live Staging is often the best option for large amounts of enterprise data. When enabling Remote Live, make sure that the remote server and the local server are completely separate systems and that the remote server and the local server do not share the same database. When enabled, all of your site data will be transferred over the network.

<figure>
	<img src="../images/remote-live-staging.png" style="max-width: 100%" />
	<figcaption style="font-size:x-small">Fig.1 Staging Configuration options</figcaption>
</figure>

Before Remote Live can be enabled, we need to take care of a few things:

1. Each server needs to be aware of the others and identified as "allowed servers" in their `portal-ext.properties`.
2. An *authentication key* needs to be specified and shared across each server.
3. Each server needs to have the tunneling servlet authentication verifier enabled.

To configure each server properly, you need to add the following to the server's `portal-ext.properties`:

```properties
tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP,[Remote server IP address]
tunneling.servlet.shared.secret=[secret]
tunneling.servlet.shared.secret.hex=true
auth.verifier.TunnelingServletAuthVerifier.hosts.allowed=
auth.verifier.pipeline=com.liferay.portal.security.auth.
TunnelingServletAuthVerifier,com.liferay.portal.security.auth.
BasicAuthHeaderAutoLogin,com.liferay.portal.security.auth.
DigestAuthenticationAuthVerifier,com.liferay.portal.security.
auth.ParameterAutoLogin,com.liferay.portal.security.auth.
PortalSessionAuthVerifier
```

#### Shared Secrets

Liferay uses a shared key between the Staging and Production environments to help secure the remote publication process. This is done by providing an authorization context for the user using the permission checker. 

<div class="key-point">
	Key Point: <br />
	The values you can specify for the <code>tunneling.servlet.shared.secret</code> property depend on the configured encryption algorithm.
</div>

The following key lengths are supported:

1. `AES`: 128, 192, and 256 bit keys
2. `Blowfish`: 32 - 448 bit keys
3. `DESede (Triple DES)`: 112 or 168 bit keys

The shared secret between servers should be a strong password. If you set `tunneling.servlet.shared.secret.hex` to `true`, you can provide the shared secret as a hex value, instead of plain ASCII. Converting in a GNU/Linux or other POSIX environment can be done using tools like `hd`:

```bash
$ echo liferay | hd
000000006c 69 66 65 72 61 79 0a|liferay.|
00000008
```

- This password would be provided as the shared secret in the properties file:

```properties
tunneling.servlet.shared.secret=6c6966657261790a
tunneling.servlet.shared.secret.hex=true
```

- Alternatively, we could use plain text:

```properties
tunneling.servlet.shared.secret=liferay
tunneling.servlet.shared.secret.hex=false
```

#### Configuring Staging for Performance

Staging content is a key feature for content management teams. With such a commonly used tool, you need to make sure Staging performs well in practice. The best way to handle Staging will vary slightly depending on what type of Staging best fits your use case.

Our first performance consideration is that a *Local Live* staged site is an entire copy of the live site. When a *Local Live* environment is activated, a new site is created with the name of the live site plus the suffix *--staging*, and all content (pages, web content, documents, blogs, etc.) is copied from the live site to the Staging site. Navigation is provided in the staging menu to switch between live and Staging. Copying an entire site can be very costly, depending on the size and complexity of the site. When using *Local Live* Staging, turn on Staging very early, when the site is brand new and fresh. If you're going to use page versioning, turn it on *at the beginning*.

If you're using *Local Live* Staging, and your Liferay production server is clustered, you don't want to use all of the App Tier for staging. Since the load balancer should be optimized for production use, you'll want to offload staging traffic. To improve *Local Live* performance in a cluster, designate one node as the primary staging server. You should also configure the Load Balancer Tier and/or Web Tier to assign all staging traffic to the staging node. Staging traffic can be filtered by the URL, since the staged site will end in `-staging`.

Similar to *Local Live*, *Remote Live* Staging exists as a separate copy of the live environment. Performance over remote staging is based on the connection stability and speed between the staging server and production server, the size of the staged environment to publish, and the size of the content changes to publish. Traffic will be separate for the staging server and the production server, so performance concerns will mostly be with the individual servers and latency between servers.

Understanding the publication process will improve your success in staging as well as performance. For every publication process, configuration and content for each app in the site is exported. If any content references content in another site, that reference is resolved. The export is committed to a local file (*Local Live*) or transferred over the network (*Remote Live*). The export is validated on the production site. File versions, data integrity, corrupt binary data, and more are all checked for errors. If any errors are found, the production site is rolled back to the last successful publication. Staging data is kept on the staging site.

Staging requires decent system performance for handling content. The exact requirements depend on your system load, amount/size of content, and publication processes. Note: A publication process will trigger activity on the database, the document library, and the search index.

<div class="key-point">
	Key Point: <br />
	General guidelines for Staging include turning it on as early as possible and publishing often with smaller data sets.	
</div>
 
Common problems can occur when:

- Content references content in another site, but the referenced content wasn't published in production first
- Content references content in another site, and that site references content in the first site
- Too much data needs to be transferred, resulting in corrupt data or network timeouts
- Both systems have different versions

When publishing remotely, Liferay will split the data into smaller chunks. Remote publishing buffer size can be modified with a simple property:

```properties
 staging.remote.transfer.buffer.size
```

<div class="summary"><h3>Summary</h3>

<ul>
	<li>________________ creates an environment, called the __________________________________, that is separate from your live site. There are two kinds of Staging:</li>
	<ul>
		<li>___________________________: Allows you to use Staging on a __________________________</li>
		<li>___________________________: Allows you to use _____________________ for Staging</li>
	</ul>
</ul>
</div>
