---
description: 02 - OSGi Basics
title:  OSGi Architecture
order: 6
---

## OSGi Architecture

OSGi has two main specifications: *OSGi Core* and *OSGi compendium*. 

<img src="../images/osgi-specification.png" style="max-height:20%;" />

#### OSGi Core

The [Core specification](https://osgi.org/specification/osgi.core/7.0.0/) specifies APIs, which are the bare minimum to run OSGi applications, and which every framework implementation must implement.	

There are several core implementations, like:

* Apache Felix
* Eclipse Equinox
* Knopflerfish
* ProSyst

#### OSGi Compendium

The [Compendium specification](https://osgi.org/specification/osgi.cmpn/7.0.0/) is a collection of additional OSGi framework services and APIs, which may be used in a modular fashion, meaning that implementations may only implement selected parts of the compendium. 

When creating Liferay modules, you'll see the Core implementation dependency in the project dependencies. Depending on the module type, there might also be the whole compendium (as in the example below) or just a certain subsystem dependency. 

```groovy
dependencies {
	...
	compileOnly group: "org.osgi", name: "org.osgi.core", version: "6.0.0"
	compileOnly group: "org.osgi", name: "osgi.cmpn", version: "6.0.0"
	...
}
```

#### OSGi Subsystems

All in all, the OSGi framework consists of five layers, also called subsystems:

<img src="../images/osgi-architecture.png" style="max-height:32%;" />

__Bundles:__ Bundles are self-contained, manageable, and testable units of deployment. At the file level, they are regular JAR files with OSGi-specific headers in their MANIFEST.MF file.

__Services:__ The _services_ layer contains the OSGi service registry, which allows you to publish, find, and bind services dynamically.

__Lifecycle:__ The _lifecycle_ layer takes care of a bundle's lifecycle transitions:

* Install
* Start
* Stop
* Update
* Uninstall

__Modules:__ The _modules_ layer manages bundle modularity and takes care of class loader delegation and feature-sharing like exports and imports.

__Execution Environment:__ The _execution environment_ layer manages the Java runtime compatibility since OSGi environments can be implemented on different Java editions. A bundle can define the Java platform compatibility with the header `Bundle-Required-Execution-Environment`. 

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
    <li> OSGi has two main specifications: __________________ and ___________________.
    <li> The ____________________ specifies the API that every OSGi framework implementation must implement.
    <li> OSGi implementations may only implement selected parts of the __________________. 
</ul>
</div>