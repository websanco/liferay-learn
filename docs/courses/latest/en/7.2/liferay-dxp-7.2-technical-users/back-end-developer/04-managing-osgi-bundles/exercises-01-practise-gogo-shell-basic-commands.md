---
description: 4 - Managing OSGi Bundles
title: Practice Gogo Shell Basic Commands
order: 1
---

<h2 class="exercise">Exercises</h2>

## Practice Gogo Shell Basic Commands

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Use the most common bundle management commands:</li>
		    <ul>
			<li>Use <code>lb</code> to check the bundle state</li>
			<li>Use <code>dm wtf</code> to show information about missing dependencies</li>
			<li>Use <code>headers</code> to check a bundle's manifest headers</li>
			<li>Use <code>scr:list</code> and <code>scr:info</code> to show information about a component</li>
			<li>Use <code>services</code> to find information about a service</li>
			<li>Use <code>inspect</code> to inspect bundle capabilities and requirements</li>
		</ul>
	</ul>
</div>

#### Telnet to the Liferay OSGi Container

We use Telnet to connect to Liferay's embedded OSGi container.

1. **Use** your Telnet client to connect to localhost at port 11311:

<img src="../images/telnet-to-gogo.png" style="max-height:100%;" />

> In Windows, you either have to first enable the Telnet client, or you can use an application like [Putty](https://www.putty.org). If you are unable to install or use Telnet client on your computer, you can also use the Gogo Shell in the *Control Panel -> Configuration -> Gogo Shell*.

> If you are using macOS High Sierra or newer (Mojave or Catalina), you will need to download Telnet. This can be done through the Homebrew package manager, which can be downloaded by opening Terminal and using `/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`. When Homebrew has been successfully installed, use `brew install telnet` in Terminal to install Telnet.

#### Use lb to Check the Bundle State

The `lb` (list bundles) command shows a list of all bundles installed in the container. 

We can pipe the output of Gogo commands to the `grep` command, familiar for Linux users, to filter the list. 

1. **List** bundles that have string *blogs* in their name:
	```bash
	lb -s | grep blogs
	```
	
The output should look like: 
<img src="../images/lb.png" style="max-height:100%;" />

> Alternatively, you could simply use `lb -s blogs.`

<br />

#### Use dm wtf to Show Information About Missing Dependencies

`dm wtf` (dependency manager where is the failure) shows if there are any dependency resolution problems in the bundles.

1. **Show** dependency manager information:
	```bash
	dm wtf
	```
If there are no problems, the output will show:
	<img src="../images/dm-ok.png" style="max-height:30%;" />
	* Let's now stop the blogs-api bundle and test again:
1. **Use** the `lb` command to find the bundle ID for the Blogs API module:
	```bash
	lb -s | grep com.liferay.blogs.api
	```
1. **Stop** the bundle:
	```bash
	stop [BLOGS_API_BUNDLE_ID]
	```
1. **Use** the `dm wtf` to check the status again. The output will now look like:
	<img src="../images/dm-not-ok.png" style="max-height:100%;" />
1. **Start** the `blogs-api` again.

#### Use headers to Investigate Bundle Manifest Headers

The `headers` command shows the bundle's manifest headers. With this command, you can, for example, check an `Import-Package` header and see if there's a problem with a dependent bundle.

1. **Use** `lb` and choose a bundle to investigate.
	* For example, try finding the `training-portlet` exercise bundle.
1. **Show** headers for the bundle:
	```bash
	headers [BUNDLE_ID]
	```
The output should look like: 
<img src="../images/headers.png" style="max-height: 100%"/>

#### Use scr:list and scr:info to Show Information About a Component

1. **List** all the components in the OSGi container:
	```BASH
	scr:list
	```	
1. **Choose** one component id to investigate and enter:
	```bash
	scr:info [COMPONENT_ID]
	```
The output should look like: 
<img src="../images/scr-info.png" style="max-height: 100%"/>

#### Use services to Find Information About a Service

The `services` command lists all the registered (published) services in the OSGi container.

With this command, LDAP-style filtering is supported.

1. **List** all the services having a package stem `com.liferay.blogs.web`:
	```bash
	services (objectClass="com.liferay.blogs.web*")	
	```
The output should look like:
<img src="../images/services.png" style="max-height: 100%"/>

#### Use inspect to Inspect Bundle Capabilities and Requirements

The `inspect` command can be used to show information about a service. This command also shows information about a bundle's capabilities and requirements.

1. **Select** a bundle id to investigate and enter:
	```bash
	inspect cap service [BUNDLE_ID]
	```
The output should look like:
<img src="../images/inspect.png" style="max-height: 100%"/>
