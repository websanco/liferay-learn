---
description: 1 - Setting Up the Development Environment
title: Set Up the Development Environment
order: 2
---

## Overview

Liferay offers developers a platform and a comprehensive set of tools for building web applications. Although the platform itself relies on Java EE and the OSGi framework for modular application development, Liferay is completely tool-agnostic when it comes to writing code. You can use any Java IDE or even a text editor to write Liferay applications.

The minimum set of tools required to develop for Liferay includes:

* A code editor
* JDK 11 for compiling
* A build tool for creating the deployables
* A servlet container and a SQL database for running the portal

<img src="../images/development-environment-structure.png" style="max-width: 100%;"/>

#### IDE Support

Any Java IDE can be used for Liferay development, but support is only provided for the Eclipse-based *Liferay Dev Studio*, which is available in two versions:

__Liferay Dev Studio DXP__ is a commercial and productized version of Eclipse available for enterprise customers. It's both the preferred and supported tool and is bundled with all the tools needed for Liferay DXP development.

__Liferay Dev Studio Community Edition__ is an open-source version of Liferay Dev Studio DXP. It can be downloaded as a standalone IDE or with Liferay Workspace. Liferay Workspace automates the download and installation of the Liferay CE bundle. Liferay Dev Studio Community Edition can be downloaded as a bundle or installed on top of an existing Eclipse installation.  

With regard to other IDEs, an IntelliJ plugin is also available: https://plugins.jetbrains.com/plugin/10739-liferay-intellij-plugin.

## Liferay Plugins SDK

Liferay Plugins SDK is deprecated but still available. It should be used only for developing Ext-plugins (deprecated) or for upgrading legacy plugins. For new projects, always use Liferay Workspace. 

#### Java Runtime

A __full JDK__ is required both by the development tools and for running the Liferay platform. Oracle Java 11, OpenJDK 11, and IBM J9 JDK 11 for WebSphere are supported. Using certified JDKs is recommended.  See the Liferay Portal Compatibility Matrix (https://web.liferay.com/services/support/compatibility-matrix) for the supported runtimes.

#### Servlet Containers

Liferay can run on any Java servlet container or EE server. Portal bundles for Apache Tomcat and WildFly are provided. When using the Liferay Workspace development environment, the Tomcat bundle can be downloaded and installed automatically from within the workspace and integrated seamlessly into the environment. Liferay Dev Studio also provides a server connector for Tomcat, which lets you control multiple servlet containers from within the IDE.

#### Database

A relational database is needed for running the Liferay platform. A Liferay server bundle ships with the Hypersonic in memory database, which is __meant only for testing or evaluation purposes__. Using a supported database product for development is recommended as well. See the Liferay Portal Compatibility Matrix (https://web.liferay.com/services/support/compatibility-matrix) for a complete list of supported databases.

> Commercial database JDBC drivers are not shipped with the bundles provided by Liferay and must be installed manually.

## Tooling Scenario Examples

#### "Minimal"

This hypothetical scenario demonstrates the bare minimum for Liferay development.

<img src="../images/tooling-scenario-1.png" style="max-width: 100%;"/>

#### "Typical"

Any Java IDE can be used for developing on the Liferay platform. But, especially when you start a new project, Liferay Dev Studio in connection with the Tomcat server bundle provides an easy and quick way to access all tools required for a productive Liferay development process.

<img src="../images/tooling-scenario-2.png" style="max-width: 100%;"/>

#### "Advanced"

Depending on your project's requirements, in advanced scenarios, the development environment can be integrated with additional tools like a testing and continuous integration pipeline, a Java profiler, or a graphical SQL client.

<img src="../images/tooling-scenario-3.png" style="max-width: 100%;"/>

<br />

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
	<li>Liferay is tool-_________________, giving developers the freedom to choose the tools they like best and with which they are most familiar.</li>
	<li>A full Eclipse-based IDE is provided under the name _________________________, containing all the tools needed for efficient back-end development.</li>
	<li>The ___________________________ is seamlessly integrated into the Liferay Workspace environment, but can also be downloaded separately.</li>
	<li>Any _________________ container or ____________________ can be used for running the platform.</li>
</div>