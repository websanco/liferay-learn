---
description: 7 - Platform Architecture Overview
title: Platform Architecture Overview
order: 2
---

## Platform Architecture Overview

The Liferay platform core builds on the following technologies:

* __Java EE:__ the runtime and programming environment
* __OSGi:__ the modular application runtime and development framework
* __Spring:__ for transactions and dependency injection in the core
* __Hibernate:__ for database access (along with direct JDBC access for optimized queries)
* __Ehcache:__ caching
* __Elasticsearch__ indexing and searching

<img src="../images/technologies.png" style="max-height:15%;" />

Liferay is compliant with many industry-proven standards. The following standards, for example, are supported:

* __[Portlet 1.0 (JSR-168)](https://jcp.org/en/jsr/detail?id=168)__, __[Portlet 2.0 (JSR-286)](https://jcp.org/en/jsr/detail?id=286)__, and __[Portlet 3.0 (JSR-362)](https://www.jcp.org/en/jsr/detail?id=362)__
	* Liferay can run any portlets that follow these portlet specifications. 
* __[JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html)__ (JSR-127, JSR-314, JSR-344)
	* The Java standard for building component-based web applications. Liferay is an active contributor to the standard and the lead of the JSF-Portlet Bridge specification.
* __[EcmaScript 2015 (ES6)](http://www.ecma-international.org/ecma-262/6.0/)__
	* Liferay's tooling supports the 6th edition of the ECMAScript specification and provides the ability to use it in all modern browsers with the integration of the Babel JS transpiler.
* __[JAX-WS and JAX-RS](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/jax-rs)__
	* The Java API for XML Web Services (JAX-WS) and for building RESTful services (JAX-RS) is incorporated as the preferred tooling to create web services. 
* __[OSGi r6](https://www.osgi.org/)__
	* Liferay supports the OSGi family of standards through its own implementations and also integrates implementations of the Apache Felix and Eclipse Equinox projects. Here are some of the most relevant supported standards:
	  * __OSGi runtime__: Allows any OSGi module to run in Liferay 
	  * __Declarative Services:__ Supports a dynamic component model for Liferay development
	  * __Configuration Admin__: Lets you create configurable applications that can be reconfigured on the fly. Liferay provides an auto-generated UI to change the configuration of any component that leverages this standard.
	  
#### Other Standards {#other}

Liferay provides tools and development patterns to comply with the EU’s General Data Protection Regulation (GDPR). In custom Service Builder projects, Liferay provides a way to define fields as *anonymizable* with the help of UAD (User Associated Data) attributes, for example:

```xml
<column name="userName" type="String" uad-anonymize-field-name="fullName" />
```

> See the Developer Network article (https://portal.liferay.dev/docs/7-2/user/-/knowledge_base/u/managing-users) on using UAD attributes.
 
#### Diagramming the Platform Architecture {#diagramming}

There are multiple ways to approach diagramming the logical and physical architecture of the platform. Below are some examples.  

#### Physical View {#physical}

<img src="../images/physical-architecture.png" style="max-height:100%;" />

#### Logical View {#logical}

<img src="../images/logical-architecture.png" style="max-height:100%;" />

#### Further Reading {#reading}

* Liferay Fundamentals: https://portal.liferay.dev/docs/7-2/user

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Liferay is built on many core technologies such as:</li>
  <ul>
    <li>_____________________________: the runtime and programming environment</li>
    <li>_____________________________: for transactions and dependency injection in the core </li>
    <li>_____________________________: indexing and searching</li>
  </ul>
  <li>Liferay is compliant with many ____________________ standards such as the portlet standard and JSF.</li>
  <li>Liferay supports the ____________ family of standards.</li>
</ul>
</div>