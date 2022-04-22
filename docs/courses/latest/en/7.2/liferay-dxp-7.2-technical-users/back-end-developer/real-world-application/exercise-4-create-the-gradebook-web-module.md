---
description: Real World Application
title: Create the Gradebook Web Module
order: 4
---

<h2 class="exercise">Exercises</h2>

## Create the Gradebook Web Module

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay MVC portlet module</li>
		<li>Declare dependencies</li>
		<li>Set portlet properties</li>
		<li>Set the portlet name</li>
		<li>Do a final code review</li>
		<li>Deploy the module </li>
		<li>Test the module </li>
	</ul>
</div>

<br />

Over the next few exercises, we will create the user interface for the Gradebook application. We will be using coding conventions and patterns recommended for Liferay development, leveraging libraries, components, and high-level superclasses to remove the need for boilerplate coding.

We will use the Liferay MVC portlet as a portlet component. The portlet lifecycle and communication between the portlet back-end and user interface will be handled by MVC command components.

The user interface will be implemented with JSP technology. We will be using Liferay tag libraries, which both minimize the need for HTML coding and guarantee a [Twitter Bootstrap](https://getbootstrap.com/)-based responsive layout.

#### Create a Liferay MVC Portlet Module

Option 1: Use the Command Line Blade Tools

1. **Open** command line shell in your Liferay Workspace `modules/gradebook` folder.
1. **Run** command:
```bash
blade create -t mvc-portlet -p com.liferay.training.gradebook.web -c GradebookPortlet gradebook-web
```
1. **Run** Gradle refresh on the IDE.

Option 2: Use Developer Studio Wizard

1. **Launch** the *Liferay Module Project* wizard in Developesr Studio.
1. **Use** the following information for the first step:
	* __Project Name__: "gradebook-web"
	* __Use Default Location__: uncheck and browse to *modules/gradebook* subfolder
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: mvc-portlet
1. **Click** *Next*, and use the following information for the second step:
	* __Component Class Name__: "Gradebook"
	* __Package Name__: "com.liferay.training.gradebook.web"
1. **Click** *Finish* to close the wizard.

#### Declare Dependencies

We need to declare dependencies for the Gradebook service (API), Liferay Clay tag library, and Petra function utility:

1. Open the `build.gradle` of *gradebook-web* project.
1. Implement the new dependencies as follows:
	```groovy
	compileOnly group: 'com.liferay', name: 'com.liferay.petra.function'
	compileOnly group: 'com.liferay', name: 'com.liferay.frontend.taglib.clay'
	compileOnly project(":modules:gradebook:gradebook-api")
	```
	> Notice here how we reference the API (gradebook-api) and not the implementation (gradebook-service).

	> What is Petra? If you developed for pre-7 Liferay, you probably remember the `com.liferay.util.java` utilities. The Petra library family contains the modularized and OSGi-ready versions of those utilities.  

#### Set Portlet Properties

We'll have the following requirements for our portlet:

* We don't want the Gradebook portlet to be instanceable, as its data needs to be scoped under a site.
* We'd like the Gradebook portlet to appear in the *Liferay Training* Widgets category instead of the *Sample* category.

Let's change the portlet component properties to match these requirements:

1. **Open** the `GradebookPortlet` class.
1. **Implement** the changes to component properties as follows:
	```java
	"com.liferay.portlet.display-category=category.training",
	"com.liferay.portlet.instanceable=false",
	```

#### Set the Portlet Name

It's a good practice to use a fully qualified name of the portlet class as the portlet identifier. We also have to update the name in our resource bundle (we'll discuss localization at later steps):

1. **Open** the class `com.liferay.training.gradebook.web.constants.GradebookPortletKeys`.
1. **Update** the portlet name constant as follows:
	```java
	public static final String Gradebook = "com_liferay_training_gradebook_web_portlet_GradebookPortlet";
	```
1. **Open** the file `src/main/resources/content/Language.properties`.
1. **Implement** the contents as follows:
	```properties
	javax.portlet.description.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook 
	javax.portlet.display-name.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
	javax.portlet.keywords.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
	javax.portlet.short-title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
	javax.portlet.title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
	```
	
#### Do a Final Code Review

**build.gradle**
```groovy
dependencies {
	// Clay taglib.

	compileOnly group: 'com.liferay', name: 'com.liferay.frontend.taglib.clay'

	// Needed for the Assignments Management Toolbar.

	compileOnly group: 'com.liferay', name: 'com.liferay.petra.function'

	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
	compileOnly group: "javax.portlet", name: "portlet-api"
	compileOnly group: "javax.servlet", name: "javax.servlet-api"
	compileOnly group: "jstl", name: "jstl"
	compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"

	// Gradebook service.
	
	compileOnly project(":modules:gradebook:gradebook-api")
}
```	

**GradebookPortlet.java**
```java
package com.liferay.training.gradebook.web.portlet;

import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.training",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GradebookPortlet extends MVCPortlet {
}
```

**GradebookPortletKeys.java**
```java
package com.liferay.training.gradebook.web.constants;

/**
 * @author liferay
 */
public class GradebookPortletKeys {

	public static final String Gradebook = "com_liferay_training_gradebook_web_portlet_GradebookPortlet";
}
```

**Language.properties**
```properties
javax.portlet.description.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.display-name.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.keywords.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.short-title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
gradebook.caption=Hello from Gradebook!
```
	
#### Deploy the Module

1. **Drag** the *gradebook-web* onto the Liferay server to deploy the module. 
	* You should see the following message in the log:
	```bash
	STARTED com.liferay.training.gradebook.web_1.0.0
	```

#### Test the Module

Dev Studio's hot deploy feature allows us to see the changes on the user interface in almost real-time as we work with the code. Let's do a quick test to see how this feature works:

1. **Open** your browser to http://localhost:8080 and sign in.
1. **Click** the *Add* icon on the top right corner of the page.
1. **Expand** the *category.training* category in the *Widgets* menu.
1. **Add** the *gradebook-web* portlet on the page.
	
<img src="../images/gradebook-web-on-a-page.png" style="max-height: 100%"/>
