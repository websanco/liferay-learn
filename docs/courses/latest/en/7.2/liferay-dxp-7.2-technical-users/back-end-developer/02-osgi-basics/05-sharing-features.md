---
description: 02 - OSGi Basics
title: Sharing Features
order: 5
---

## Sharing Features

OSGi applications typically consist of multiple bundles. By default, bundles have a private class and package visibility, and they do not expose any features to other bundles in the container. By *features* in this context, we mean:

* Classes
* Packages
* OSGi services and components

There are three primary ways to share *features* between bundles: 

* Export Package - Import Package
* Provide Capability - Require Capability
* Require bundle 

#### Export Package - Import Package

This method is meant to make classes available for other bundles.

An exporting bundle explicitly defines which packages to export and make available to other bundles. Similarly, the importing bundle explicitly defines which packages to import. The [OSGi resolver](https://osgi.org/specification/osgi.core/7.0.0/service.resolver.html) is responsible for wiring the bundles together.

Below is an example of `MANIFEST.MF` exporting and importing a package:

```properties
Manifest-Version: 1.0
Bundle-ManifestVersion: 2
Bundle-Name: OSGi Service API
Bundle-SymbolicName: com.liferay.training.osgi.service.api
Bundle-Version: 1.0.0.qualifier
Automatic-Module-Name: com.liferay.training.osgi.service.api
Bundle-RequiredExecutionEnvironment: JavaSE-1.8
Export-Package: com.liferay.training.osgi.service.api
```

```properties
Manifest-Version: 1.0
Bundle-ManifestVersion: 2
Bundle-Name: OSGi Service Impl
Bundle-SymbolicName: com.liferay.training.osgi.service.impl
Bundle-Version: 1.0.0.qualifier
Bundle-RequiredExecutionEnvironment: JavaSE-1.8
Import-Package: com.liferay.training.osgi.service.api, org.osgi.framework;version="1.3.0"
Automatic-Module-Name: com.liferay.training.osgi.service.impl
```

#### Provide Capability - Require Capability

A capability describes a functionality for the OSGi Container. In general terms, a *capability* is defined by two main attributes:

* __Namespace:__ a unique identifier, like a package name 
* __Attributes:__ a list of properties that describe the capability

A capability is a more robust and complex mechanism than export-import and is used, for example, for sharing OSGi services. When you create an OSGi service component, it's declared a capability in the `MANIFEST.MF` during the build time.

In Liferay development, Bndtools automates many of the build time tasks, like creating the *provide* and *require* capability headers automatically based on the component service declarations. On the requiring end, the capability header is automatically generated based on the dependencies defined in the build.gradle file. 

Below is an example of providing and requiring headers in the `MANIFEST.MF`. A service implementation bundle *provides* implementations for a service. A client bundle is requiring the capability:

```properties
Provide-Capability =\
	osgi.service;objectClass:List<String>="com.liferay.training.gradebook.service.AssignmentService"
```

```properties
Require-Capability =\
	osgi.service;filter:="(objectClass=com.liferay.training.gradebook.service.AssignmentService)";effective:=active
```

#### Require Bundle

Requiring a bundle in another bundle defines an explicit dependency contract and a tight coupling between the bundles, as all the packages in the imported bundle are exposed automatically to the importing bundle. Generally, __this approach should be avoided__.

#### Links and Resources

* Raymond Auge's Blog About Using Requirements and Capabilities: https://blog.osgi.org/2015/12/using-requirements-and-capabilities.html

<br />

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
    <li>_______________ a bundle explicitly defines which packages to export and make available to other bundles.</li>
    <li>_______________ a bundle explicitly defines which packages to import.</li>
    <li>A capability describes a functionality for the ____________ Container.</li>
    <li>In general, ________________ a bundle should not be used.</li>
</ul>
</div>