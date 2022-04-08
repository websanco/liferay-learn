---
description: 02 - OSGi Basics
title: OSGi Framework
order: 2
---

## OSGi Framework

__OSGi__ stands for Open Service Gateway Initiative. OSGi is a specification and a set of standards for modular software development with Java. The organization behind the initiative is the [OSGi Alliance](https://www.osgi.org/), founded in 1999. 

OSGi is a stable and mature technology. OSGi Release 1 (R1) was published in May 2000, the same year as J2SE 1.3. It's  widely adopted in the industry and has multiple implementations of its standard:

<img src="../images/products-using-osgi.png" />

OSGi is at the center of Liferay's core technologies. Understanding OSGi concepts and architecture is paramount in Liferay development. In this module, we'll introduce you to the basic development units and concepts of the OSGi framework and environment:

* OSGi framework
* Bundle
* Components and services
* Sharing features 

Understanding these concepts will help you master back-end development in Liferay.

The OSGi framework is a standard for modular Java software development. Occasionally, the term "OSGi framework" is used to refer just to the OSGi application runtime, which is here called the *OSGi container*.

OSGi *bundles*, which are the runnables in an OSGi environment, reside in an OSGi runtime or here in an OSGi container, which in turn is running in a standard Java runtime:

<img src="../images/osgi-framework-and-bundle.png" />
