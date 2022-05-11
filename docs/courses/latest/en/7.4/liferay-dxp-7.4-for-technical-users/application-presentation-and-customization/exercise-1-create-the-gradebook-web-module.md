# Exercise 1: Create the Gradebook Web Module

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/uw50Kg-ZkAY

> The exercise video above uses DXP 7.3. To complete the exercise using DXP/CE 7.4, follow the updated exercise steps below.

## Exercise Goals

- Create a Liferay MVC portlet module
- Declare dependencies
- Set portlet properties
- Set the portlet name
- Do a final code review
- Deploy the module
- Test the module

Over the next few exercises, we will create the user interface for the Gradebook application. We will be using coding conventions and patterns recommended for Liferay development, leveraging libraries, components, and high-level superclasses to remove the need for boilerplate coding.

We will use the Liferay MVC portlet as a portlet component. The portlet lifecycle and communication between the portlet back-end and user interface will be handled by MVC command components.

The user interface will be implemented with JSP technology. We will be using Liferay tag libraries, which both minimize the need for HTML coding and guarantee a [Twitter Bootstrap](https://getbootstrap.com/)-based responsive layout.



## Create a Liferay MVC Portlet Module

1. **Create** a Liferay MVC Portlet Module in the _modules/gradebook_ subfolder with the following attributes:
	- **Project Name**: "gradebook-web"
	- **Project Template**: "mvc-portlet"
	- **Component Class Name**: "Gradebook"
	- **Package Name**: "com.liferay.training.gradebook.web"

If you're using Blade CLI, the command to create this module is `blade create -t mvc-portlet -p com.liferay.training.gradebook.web -c Gradebook gradebook-web`.

We need to declare dependencies for the Gradebook service (API), Liferay Clay tag library, and Petra function utility.

## Declare Dependencies

1. **Open** the `build.gradle` of *gradebook-web* project.
2. **Implement** the new dependencies as follows:

```groovy
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
```

Note here how we reference the API (gradebook-api) and not the implementation (gradebook-service).

> What is Petra? If you developed for pre-7 Liferay, you probably remember the `com.liferay.util.java` utilities. The Petra library family contains the modularized and OSGi-ready versions of those utilities.

We'll have the following requirements for our portlet:

- We don't want the Gradebook portlet to be instanceable, as its data needs to be scoped under a site.
- We'd like the Gradebook portlet to appear in the *Liferay Training* Widgets category instead of the *Sample* category.

Let's change the portlet component properties to match these requirements:

## Set Portlet Properties

1. **Open** the `GradebookPortlet` class.
2. **Implement** the changes to component properties as follows:

```java
	"com.liferay.portlet.display-category=category.training",
	"com.liferay.portlet.instanceable=false",
```

It's a good practice to use a fully qualified name of the portlet class as the portlet identifier. We also have to update the name in our resource bundle (we'll discuss localization at later steps).

## Set the Portlet Name

1. **Open** the class `com.liferay.training.gradebook.web.constants.GradebookPortletKeys`.
2. **Update** the portlet name constant as follows:

```java
public static final String GRADEBOOK = "com_liferay_training_gradebook_web_portlet_GradebookPortlet";
```

3. **Open** the file `src/main/resources/content/Language.properties`.
4. **Implement** the contents as follows:

```properties
javax.portlet.description.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK 
javax.portlet.display-name.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
javax.portlet.keywords.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
javax.portlet.short-title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
javax.portlet.title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
```

## Do a Final Code Review

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
		"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
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

	public static final String GRADEBOOK = "com_liferay_training_gradebook_web_portlet_GradebookPortlet";
}
```

**Language.properties**

```properties
javax.portlet.description.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
javax.portlet.display-name.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
javax.portlet.keywords.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
javax.portlet.short-title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
javax.portlet.title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=GRADEBOOK
gradebook.caption=Hello from Gradebook!
```
	
## Deploy the Module

1. **Start** your Docker container if it's not running.
2. **Run** `../gradlew deploy` to deploy the module.
	- You should see the following message in the log:

```bash
STARTED com.liferay.training.gradebook.web_1.0.0
```

## Test the Module
1. **Open** your browser to http://localhost:8080.
2. **Click** the *Edit* icon on the top right corner of the page.
3. **Expand** the *category.training* category in the *Widgets* menu.
4. **Add** the *Gradebook* portlet on the page.

---

## Next Up

* [Implementing Portlet Actions](./implementing-portlet-actions.md)

## Previous Step

* [Creating Web Modules](./creating-web-modules.md)

