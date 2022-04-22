---
description: 5 - Working with Portlet Modules
title: Working with Liferay Portlet Modules
order: 3
---

## Working with Liferay Portlet Modules

Liferay has a portlet container that supports the [JSR-168](https://www.jcp.org/en/jsr/detail?id=168), [JSR-286](https://www.jcp.org/en/jsr/detail?id=286), and [JSR-362](https://www.jcp.org/en/jsr/detail?id=362) portlet specifications. If you created a portlet using the standard portlet methodology, you can get it to work and deploy in Liferay. 

While Liferay's native OSGi portlets are based on the Java portlet standards, they do not follow them completely. Liferay has its own implementation of a portlet that extends the [javax.portlet.GenericPortlet](https://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/GenericPortlet.html). Many of the concepts discussed in the Java standard portlet sections are in effect, but implemented differently. In this section, we'll explain the most important concepts of a Liferay OSGi portlet.

#### Introducing the Portlet Module

A portlet module is like any other Liferay module, with the exception of having a portlet component class. We'll create the portlet class as an OSGi service component and move all of the portlet properties from the XML descriptor files to portlet component properties:

<img src="../images/portlet-module.png" style="max-height:50%;" />

#### OSGi Portlet Module Structure

Below is an example of the OSGi portlet module structure. Compare to legacy WAR-style portlets:

<img src="../images/portlet-file-structure.png" style="max-height:100%;" />

Let's have a look at the `bnd.bnd`, `build.gradle`, and `TrainingPortlet.java` of the portlet module.

#### Bnd File

Headers in `bnd.bnd` are just like any other OSGi module:

```properties
Bundle-Name: Training Portlet
Bundle-SymbolicName: com.liferay.training.portlet
Bundle-Version: 1.0.0
```

#### build.gradle

The `build.gradle` contains the portlet, servlet, and JSTL dependencies:

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "javax.portlet", name: "portlet-api"
	compileOnly group: "javax.servlet", name: "javax.servlet-api"
	compileOnly group: "jstl", name: "jstl"
	compileOnly group: "org.osgi", name: "osgi.cmpn"
}
```

#### TrainingPortlet.java

The portlet class is an OSGi service component, in this case extending the Liferay `com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet`. We call this "a portlet component". See how the portlet properties, which were previously in XML descriptor files, are here component properties:

```java
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Training Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TrainingPortletKeys.TRAINING,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TrainingPortlet extends MVCPortlet {

}
```

#### Introducing the Liferay MVC Portlet

There are quite a few things to do to get a standard `javax.portlet.GenericPortlet` portlet up and running. 

Liferay's MVCPortlet inherits from `javax.portlet.Portlet` and extends `javax.portlet.GenericPortlet` so all the rules and patterns for `javax.portlet.GenericPortlet` still apply. Using the MVCPortlet or any of its extensions as the base class removes the need for boilerplate code:

<img src="../images/mvcportlet-class-inheritance.png" style="max-height:50%;" />

#### Portlet Configuration in OSGi

In OSGi portlets, portlet configuration is no longer done in `portlet.xml` or `liferay-portlet.xml`. All the properties are set in the `property` element of the `@Component` annotation. When using Liferay Workspace to create a portlet component, many of the properties are automatically set. 

Below is a comparison of portlet configuration in a standard `portlet.xml` and in an OSGi portlet's component properties:

**portlet.xml**

```xml
<?xml version="1.0"?>

<portlet-app xmlns="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" version="2.0">
	<portlet>
		<portlet-name>1</portlet-name>
		<display-name>Training Portlet</display-name>
		<portlet-class>com.liferay.training.portlet.TrainingPortlet</portlet-class>
		<init-param>
			<name>template-path</name>
			<value>/</value>
		</init-param>
		<init-param>
			<name>view-template</name>
			<value>/view.jsp</value>
		</init-param>
		<expiration-cache>0</expiration-cache>
		<supports>
			<mime-type>text/html</mime-type>
		</supports>
		<resource-bundle>content.Language</resource-bundle>
		<portlet-info>
			<title>Training Portlet</title>
			<short-title>Training Portlet</short-title>
			<keywords>Training Portlet</keywords>
		</portlet-info>
		<security-role-ref>
			<role-name>administrator</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>guest</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>power-user</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>user</role-name>
		</security-role-ref>
	</portlet>
</portlet-app>
```

**Component properties**

```java
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Training Portlet",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
			"javax.portlet.name=" + TrainingPortletKeys.TRAINING,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class TrainingPortlet extends MVCPortlet {

}
```

> Java standard portlet properties are prefixed with `javax.portlet.*`, whereas Liferay-specific portlet properties are prefixed with `com.liferay.portlet.*`. You can find the [OSGi property mapping here: https://dev.liferay.com/develop/reference/-/knowledge_base/7-2/portlet-descriptor-to-osgi-service-property-map.

#### Portlet Lifecycle Methods in OSGi

Although we still have the classical ways of supporting the various portlet lifecycle methods, the preferred way is to use __MVC Commands__. MVC Commands are Liferay's way of implementing the Render, Action, and Resource Serving phases of a portlet. MVC Commands are implemented as components and provide a more modular way of handling the portlet lifecycle. Rather than putting everything in the portlet class as is the traditional way, the Liferay way keeps the portlet class lean and makes the various phases of the portlet more manageable.

Below is an example of how the Render phase is handled by an MVC Command specific to the Render phase: MVCRenderCommand. A `renderURL` containing the `mvcRenderCommandName` parameter is created in the JSP. An `MVCRenderCommand` component registered to the portlet and listening to the named command handles the call:  

**view.jsp**
```html
<portlet:renderURL var="viewEntryUrl">
	<portlet:param name="mvcRenderCommandName" value="/training_portlet/view_entry" />
	<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
</portlet:renderURL>

<a href="<%= viewEntryUrl">Click here to view the entry</a>
```

**ViewMVCRenderCommand.java**
```java
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.TRAINING_PORTLET,
		"mvc.command.name=/training_portlet/view_entry"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand{

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		...
	}
```

#### Modular Portlet Applications

In a traditional portlet application, everything that was needed for a portlet to run was typically packaged as one war file. In a Liferay application, a portlet application typically consists of multiple modules.

In the example diagram below, the portlet component and user interface are located inside the `training-web` module. The service API and implementation are in the `training-api` and `training-service` modules. Additionally, there is a separate testing module `training-test`:

<img src="../images/portlet-application-modules.png" style="max-height:30%;" />

Below is the module structure of a [Liferay core Blogs application](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/blogs), demonstrating the multi-module approach:

<img src="../images/portlet-application.png" style="max-height:35%;" />

<br />

#### Available User Interface Technologies

Liferay provides several MVC Portlet extensions to leverage technologies other than JSP in the user interface. Take a look at some of the available Blade examples:

* NPM portlet: https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/apps/npm
* Kotlin: https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/apps/kotlin-portlet
* Freemarker: https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/apps/freemarker-portlet

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Liferay has its own implementation of a portlet that extends __________________________.</li>
  <li>Portlets in Liferay are created as _____________ that extend the Liferay ______________________________________________________________.</li>
  <li>___________________________ are Liferay's way of implementing the _______________, _______________, and _______________ resource phase of a portlet.</li>
  <li>In a Liferay app, the ___________________________________ only makes up one part of the application.</li>
  <li>The _______ and the _____________________________________________________ for a Liferay app are usually contained in a ______________________________________________________________________.</li>
</ul>
</div>