---
description: 02 - OSGi Basics
title: Bundles
order: 3
---

## Bundles

The basic unit of modularization in an OSGi environment is called a *bundle*. A bundle contains an OSGi application or part of an OSGi application. Basically, an OSGi bundle is just a regular JAR archive made up of Java classes and resources like any other JAR archive. The only required archive level difference is the `Bundle-SymbolicName` identifier in the JAR's `MANIFEST.MF` file:

<img src="../images/osgi-bundle.png" />

#### Bundle Identifier

Every bundle inside an OSGi container has to have a unique identifier. The identifier is composed of two headers:

* Bundle-SymbolicName
* Bundle-Version

This means that if there's a bundle with the same symbolic name but a different bundle version, it's seen as a different bundle. In this way, multiple versions of the same bundle or library can co-exist in the same runtime.

```properties
Manifest-Version: 1.0
Bundle-ManifestVersion: 2
Bundle-Name: Clock API
Bundle-SymbolicName: com.liferay.training.clock.api
Bundle-Version: 1.0.0.qualifier
Automatic-Module-Name: com.liferay.training.clock.api
Bundle-RequiredExecutionEnvironment: JavaSE-1.8
Export-Package: com.liferay.training.clock.api
```

> You can omit setting the `Bundle-Version` explicitly, which sets it automatically to `0.0.0` by the runtime.

#### Bundle Semantic Versioning

Bundle version numbers provide a powerful feature called [Semantic Versioning](https://www.osgi.org/wp-content/uploads/SemanticVersioning.pdf), meaning that each of the four sections of the version identifier have a specific meaning for the OSGi container. The syntax is presented in the diagram below:

<img src="../images/osgi-semantic-versioning.png" />

The sections are interpreted by the container as follows: 

* __Major:__ There are breaking changes in the API.
* __Minor:__ The API is changed but not broken.
* __Micro:__ No compatibility issues.
* __Qualifier:__ No impact.

Semantic Versioning effectively doesn't allow a bundle to run with an API-incompatible dependency in an OSGi container. If your bundle is depending on a bundle of a specific version and the version of that dependency bundle changes at least by its *minor* number, your bundle won't run.

> The [Bndtools](https://bndtools.org) baselining tool lets you automate numbering and check the semantics. It can test the compatibility of your development bundle against the release bundle in a bundle repository and build the version number accordingly.

<br />

#### Using Version Ranges

Declaring dependencies to other OSGi bundles requires defining version numbers. This is done in the bundle manifest and is always done using version ranges, which have the following syntax:

* Square brackets ‘[’ and ‘]’ indicate __inclusiveness__
* Parentheses ‘(’ and ‘)’ indicate __exclusiveness__
* __[1.1,2.0)__ means a version range from 1.1 to 2.0, including 1.1 and excluding 2.0

Below is an example of version ranges in an Export-Import bundle:

```properties
Export-Package: com.liferay.training.osgi.api; version=1.2.3.20180410
```

```properties
Import-Package: com.liferay.training.osgi.bundle; version=“[1.2,2.0)”
```

> If the `Import-Package` doesn't specify the *version* attribute, it is added implicitly and assumed to be from 0.0.0 to infinity.

#### Bundle Lifecycle

OSGi bundles are state-aware. *Bundle activators* are classes that implement the `org.osgi.framework.BundleActivator` interface and are used, for example, to prepare and clean up resources when a bundle is started and stopped. The `start()` method is run when the bundle starts, just before it gets into the *active* state. The `stop()` method runs when the bundle is stopped:

```java
package com.liferay.training.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Hello World!!");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");
	}
}
```

Bundles can have the following lifecycle states in the container: 

* Installed
* Resolved
* Starting
* Active
* Stopping
* Uninstalled

Possible state transition sequences are described in the diagram below:

<img src="../images/osgi-bundle-lifecycle.png" style="max-height:20%;" />

_Command Line_ and web-based management tools are available for managing the lifecycle states of bundles inside the container. [Apache Felix Gogo Shell](http://felix.apache.org/documentation/subprojects/apache-felix-gogo.html) is a sub-project of [Apache Felix](http://felix.apache.org) and a console tool for managing the OSGi bundles and the container from the _Command Line_:

<img src="../images/gogo-shell.png" style="max-height:15%;" />

The *lb* (list bundles) command shows the installed bundles, start level priority, and their lifecycle state. In the image below, all the bundles are installed, started successfully, and are in the active state:

<img src="../images/check-bundle-state.png" />

The bundle lifecycle can be managed with the Gogo Shell using the following commands:

* install
* uninstall
* resolve
* update
* refresh
* start
* stop

The following diagram illustrates the possible command sequences:

<img src="../images/osgi-bundle-lifecycle-commands.png" style="max-height:35%;" />

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li> The basic unit of modularization is called a ____________________.</li>
	<li>OSGi bundles always have a _________________________.</li>
	<li>Each number in the _________________ versioning of a bundle has a specific meaning.</li>
	<li>____________________ shared between bundles requires defined version numbers.</li>
	<li>Bundle ___________________ are classes that implement the <code>org.osgi.framework.BundleActivator</code> interface.</li>
</ul>
</div>