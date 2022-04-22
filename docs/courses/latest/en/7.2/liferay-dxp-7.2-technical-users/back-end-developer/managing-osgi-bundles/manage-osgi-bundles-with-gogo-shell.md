---
description: 4 - Managing OSGi Bundles
title: Manage OSGi Bundles with the Gogo Shell
order: 2
---

## Manage OSGi Bundles with the Gogo Shell

OSGi framework implementations provide tools for managing the bundles inside the container. Some of the common bundle management tasks are to:

* Check which bundles are installed and what their versions are
* Start, stop, install, and uninstall bundles
* Refresh bundle service bindings with update and refresh
* Find bundles, components, or services in the container
* Check bundle headers
* Check component properties
* Check which service implementation your service reference is bound to
* Troubleshoot why a bundle or component didn't activate

The Apache Felix project provides the Gogo Shell and Felix Web Console tools for bundle management. The Gogo Shell is an OSGi application itself and a command shell interpreter for the OSGi container. The Gogo Shell provides full access to the OSGi container, making all the registered services and components available from within the Shell environment. It's based on the Tiny Shell Language (TSL) and supports features like: 

* Completion
* Pipes
* Closures
* Variable setting 
* Referencing container services
* Scripting

> Notice that since version 7.1, Gogo Shell access from the _Command Line_ is __disabled__ by the portal default configuration (portal.properties). You can, however, also access the Gogo Shell from the _Control Panel_ directly.

#### Command Namespaces

Gogo Shell commands are divided into namespaces:

* __dependencymanager:__ dependency management commands
* __ds:__ Declarative Services-specific
* __equinox:__ Equinox-specific
* __felix:__ Felix-specific
* __formNavigator:__ Liferay form navigator
* __gogo:__ Gogo Shell utility commands
* __obr:__ OSGi bundle repository commands
* __scr:__ service component runtime namespace for component management
* __thumbnails:__ Liferay thumbnails management
* __verify:__ Liferay module upgrade commands

Using namespace prefixes is not mandatory, but you should be aware that some of the commands exist in multiple namespaces. Without using the namespace, there's no guarantee which one will be executed. These commands are, for example, *list* or *refresh*.

#### Basic Commands

* __help:__ shows a list of all commands
* __help [command]:__ shows command-specific help
* __disconnect:__ disconnects from the Shell (*exit* shuts down the container)
* __lb:__ lists all bundles
* __services:__ lists all registered services
*  __start / stop [bundleid]:__ starts / stops a bundle
* __update [bundleid]:__ reinstalls a bundle
* __enable [componentid]:__ enables a component
* __b [bundleid]:__ displays information about a bundle
* __diag [bundleid]:__ diagnoses a bundle
* __headers [bundleid]:__ shows Manifest headers of a bundle 
* __inspect capability service [bundleid]:__ lists all services provided by a bundle
* __dm wtf:__ shows any missing dependencies 
* __getProperties:__ shows system properties 

#### Command Examples

#### lb
 
Find bundles by their symbolic name, containing a word "blogs":

```bash
g! lb -s | grep "blogs"
	1548|Active     |   10|com.liferay.adaptive.media.blogs.editor.configuration (1.0.1)
	1549|Active     |   10|com.liferay.adaptive.media.blogs.item.selector.web (1.0.1)
	1550|Active     |   10|com.liferay.adaptive.media.blogs.web (1.0.1)
	1551|Resolved   |   10|com.liferay.adaptive.media.blogs.web.fragment (1.0.1)
	1725|Active     |   10|com.liferay.blogs.api (3.1.0)
	1726|Active     |   10|com.liferay.blogs.editor.configuration (1.0.10)
	1727|Active     |   10|com.liferay.blogs.item.selector.api (1.1.0)
	1728|Active     |   10|com.liferay.blogs.item.selector.web (2.0.0)
	1729|Active     |   10|com.liferay.blogs.layout.prototype (2.0.13)
	1730|Active     |   10|com.liferay.blogs.recent.bloggers.api (1.0.1)
	1731|Active     |   10|com.liferay.blogs.recent.bloggers.web (2.0.0)
	1732|Active     |   10|com.liferay.blogs.service (1.1.11)
	1733|Active     |   10|com.liferay.blogs.web (2.0.3)
	1779|Active     |   10|com.liferay.microblogs.api (2.1.3)
	1780|Active     |   10|com.liferay.microblogs.service (2.1.15)
	1781|Active     |   10|com.liferay.microblogs.web (2.0.25)
true
g!
```

Show locations of bundles containing a word "com.liferay.blogs.web"

```bash
g! lb -l | grep "com.liferay.blogs.web"

1733|Active     |   10|/com.liferay.blogs.web-2.0.3.jar?lpkgPath=/opt/liferay-dxp-digital-enterprise-7.0-sp7/osgi/marketplace/Liferay Collaboration.lpkg
true
```

#### inspect

Show services provided by a bundle:

```bash
g! inspect capability service 1780         

com.liferay.microblogs.service_2.1.15 [1780] provides:
------------------------------------------------------
service; com.liferay.portal.upgrade.registry.UpgradeStepRegistrator with properties:
	component.name = com.liferay.microblogs.internal.upgrade.MicroblogsServiceUpgrade
	component.id = 2752
	service.id = 1295
	service.bundleid = 1780
	service.scope = bundle
	Used by:
		com.liferay.portal.upgrade.impl_1.0.1 [1521]
service; com.liferay.portal.kernel.security.permission.ResourcePermissionChecker with properties:
	resource.name = com.liferay.microblogs
	component.name = com.liferay.microblogs.service.permission.MicroblogsPermission
	component.id = 2754
	service.id = 1296
	service.bundleid = 1780
	service.scope = bundle
...
```

#### scr:info

Show component info

```bash
g! scr:info com.liferay.portal.target.platform.indexer.IndexerFactory

*** Bundle: com.liferay.portal.target.platform.indexer (2120)
Component Description:
	Name: com.liferay.portal.target.platform.indexer.IndexerFactory
	Implementation Class: com.liferay.portal.target.platform.indexer.IndexerFactory
	Default State: enabled
	Activation: immediate
	Configuration Policy: optional
	Activate Method: activate
	Deactivate Method: deactivate
	Modified Method: -
	Configuration Pid: [com.liferay.portal.target.platform.indexer.IndexerFactory]
	Services: 
	com.liferay.portal.target.platform.indexer.IndexerFactory
	Service Scope: singleton
	Component Description Properties:
	Component Configuration:
	ComponentId: 28
	State: active      
	Component Configuration Properties:
		component.id = 28
		component.name = com.liferay.portal.target.platform.indexer.IndexerFactory
```

#### Scripting Support

OSGi runtime components are available and accessible in the Gogo Shell.

Below is a demonstration of how to call a Liferay User Service from within the Shell, to list all the users:

<pre style="font-size: 0.78em;"><code class="java">
portalUtilReference=(serviceReference "com.liferay.portal.kernel.util.PortalUtil")

portalUtil=(service $portalUtilReference)

defaultCompanyId=($portalUtil getDefaultCompanyId)

userServiceReference=(serviceReference "com.liferay.portal.kernel.service.UserLocalService")

userService=(service $userServiceReference)

users=$userService getCompanyUsers $defaultCompanyId 0 100   

each $users { $it getFullName }
</code></pre>

#### Using Gogo With Blade CLI

You can also execute Gogo Shell commands from Blade CLI. This allows you, for example, to pipe the Gogo Shell command ouput to your Bash shell. The syntax for the commands is:
```bash
blade sh [gogo_shell_command]
```

The following Bash script example finds all bundles that have "blogs" in their name and finds references to "com.liferay.blogs.kernel.model" in their headers:

```bash
#!/bin/bash

while read -r bundle
do
        printf "\n$bundle\n"
        for i in $(echo $bundle | awk -F\| '{print $1}')
        do
                blade sh "headers $i"
        done | grep com.liferay.blogs.kernel.model

done <<< "$(blade sh lb -s | grep -i blogs | awk '{print}')"
```

#### Creating Custom Shell Commands

Gogo Shell commands are OSGi service components. To create your own Gogo Shell commands, you just have to create an OSGi command component and deploy it to the OSGi. 

Below is an example of a Gogo Shell command component:

```java
@Component(
	property = {
		"osgi.command.function=portalStatistics",
		"osgi.command.scope=blade"
    },
	service = Object.class
)
public class PortalStatisticsCommands {
	...
}
```

#### Shell Configuration

Access to the Gogo Shell as well as its port is controlled by the following property in [`portal.properties`](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-impl/src/portal.properties):

```properties
module.framework.properties.osgi.console=localhost:11311
```

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>Gogo Shell commands are divided into ______________________.</li>
	<li>All component and service objects in the OSGi runtime are available and accessible in the _________________ Shell.</li>
	<li>To create your own Gogo __________________, you just have to create an OSGi command component and deploy it to the OSGi container.</li>
	<li>The Gogo Shell can be configured from ______________________________.</li>
</ul>
</div>