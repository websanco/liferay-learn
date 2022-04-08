---
description: 02 - OSGi Basics
title:  OSGi Benefits
order: 7
---

## OSGi Benefits

#### True Modularization

In traditional Java EE web application development, because of the class loading and contextual restrictions, web applications tended to end up being monolithic and difficult to maintain.

Developing applications based on the OSGi framework removes many of the restrictions of traditional web application development and allows for a truly modular application design and architecture. The OSGi framework takes control of application class loading, provides a dynamic application lifecycle management, and requires modules to explicitly declare what they expose of themselves. The service registry allows you to publish and bind services in a granular and controlled way across module boundaries. Bundle identity management with semantic versioning provides a way for multiple versions of the same bundle or library to coexist within the same container. 

#### Solving "JAR Hell"

If you have created complex web applications before, you probably have either encountered it by yourself or have at least heard about it: "JAR hell." At the heart of the problem lies the traditional Java class loading.

#### Class Loading

Class loading is a mechanism of the Java runtime environment to dynamically load classes. Practically, it means scanning all the jars on the classpath to find a certain requested class. 

What is a class loader? Each object in a Java runtime environment is linked to the runtime environment via its class loader, which is just another Java class taking care of loading the classes. The class loader is a contextual boundary. Objects loaded with one class loader cannot directly access the contextual information like instance state from an object under a different class loader.

According to the Servlet Specification, each Java EE web application has its own class loader in the runtime environment. In addition to class loaders of other web applications, there are also other class loaders like the common class loader of the application server and the Java Bootstrap class loader.

#### How Does Class Loading Work?

Class loaders are arranged in a hierarchical tree. When class scanning is executed, it is carried out in a hierarchical manner that can, depending on the runtime environment or application server, be configured. The most common class loading mode is the __parent first__ mode.

In parent first class loading mode, the class loader first tries to retrieve the requested class from its parent loader. If the parent loader is unable to find the requested class, it asks its parent, and so on. If the last class loader in the hierarchy is not able to find the class, the initial class loader tries to load it from its own context. 

Class loading principles are:

* __Delegation:__ forward a class loading request to a parent class loader 
* __Visibility:__ the child class loader can see the parent loader's classes, but not vice versa
* __Uniqueness:__ a class can be loaded only once

How does that work in practice? Below is an example of the Tomcat class loading process. When a web application asks for a class, the first one to look for it is the common class loader. From there, the lookup goes to the top and back to the web application, if the class was not found.

<img src="../images/tomcat-class-loading-hierarchy.png" style="max-height:22%;" />

How could this be an issue? One problematic scenario is caused by transitive dependencies. The library you are requiring requires another library, which might require another library, and so on. All the required libraries have to be available to the web application. 

To illustrate this, let's consider the following scenario: a web application has a direct dependency on *Library X* and on *Library 1*. *Library 1* also has a transitive dependency on the same *Library X*. Both the web application and Library 1 require the same version, and no problem arises.

<img src="../images/jar-hell-1.png" style="max-height:18%;" />

In the scenario diagram below, the web application requires version 1 of *Library X*, but the dependent library *Library 1* requires version 2 of the same library. If the library versions are not compatible, they cannot coexist under the same class loader, because either the web application or Library 1 could get into a situation, where classes from the wrong version were loaded. The Java class loader only tries to find the requested class, but doesn't care about the version.

<img src="../images/jar-hell-2.png" style="max-height:20%;" />

In the third scenario, things gets even more complicated if Tomcat and both web applications are requiring a different version of the same library.

<img src="../images/jar-hell-3.png" style="max-height:24%;" />

In this kind of scenario, dependent classes could be loaded by the web application from a conflicting library version, ending up in exceptions like:

* ClassNotFoundException 
* NoClassDefFoundError
* ClassCastException 

This brings the application to the state known as *JAR hell*.

#### How Does OSGi Help?

In the OSGi environment, each bundle has its own class loader, but the class loader delegation is completely managed by the OSGi container.

As all bundles have a unique identifier, the OSGi container can locate the bundle directly without needing to scan the whole class path, which in turn makes OSGi class loading more efficient.

As the class and package visibility are by default private and have to be declared explicitly, no bundle ends up loading classes from another bundle by accident.

These benefits can be illustrated with the following diagrams, which display the difference of application development based on the OSGi framework compared to traditional Java EE web application development.

Traditional Java EE Web applications:

<img src="../images/traditional-webapp.png" style="max-height:30%;" />

OSGi applications:

<img src="../images/osgi-application.png" style="max-height:28%;" />

#### Further OSGi Benefits

The OSGi framework service component model, which requires interfacing that enforces loose coupling, makes the code more modular and reusable. This also ensures consistency both in the code and in the design patterns.

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
	<li>In traditional Java EE web application development, because of the class loading and contextual restrictions, web applications tended to end up being __________________ and difficult to maintain.</li>
	<li>The most common class loading mode is the _______________________ mode.</li>
	<li>In the OSGi environment, each bundle has its own ___________________, but the class loader delegation is completely managed by the OSGi container.</li>
</ul>
</div>