---
description:  3 - Liferay's OSGi Container
title: Manage Module Dependencies
order: 5
---

## Manage Module Dependencies

Liferay applications are modular and typically consist of multiple modules. In addition to inter-module dependencies within the project workspace, projects usually have external dependencies too. 

While the custom Gradle scopes provided by Liferay and Bndtools hide most of the complexity of dependency management tasks, it's important to understand the different roles of Gradle and Bndtools as well as the corresponding `build.gradle`, `bnd.bnd`, and `MANIFEST.MF` files. 

Dependencies of a deployable OSGi JAR archive are defined as import headers in the `MANIFEST.MF` file, but what gets written to the manifest file depends on the `build.gradle` and `bnd.bnd`. Where Gradle fetches and makes module dependencies defined in `build.gradle` available for the build process, Bndtools wires the dependency information together and creates the manifest file.

If dependencies are already available in the OSGi container, declaring it in the `compileOnly` or `provided` scope is usually the only necessary dependency management step for a developer. In case of external dependencies, the Liferay-provided `compileInclude` additionally embeds the dependencies in the bundle archive JAR. Sometimes, however, you might need to declare explicit _import_ or _resource including - or excluding_ statements in the `bnd.bnd` file.

#### Target Platform

Since version 7.1, Liferay has provided a Gradle plugin called *Target Platform*. This plugin helps you to target your dependencies to a specific release of Liferay Portal so that the dependency versions get resolved automatically. This makes not only development time but upgrading modules substantially easier.

The Target Platform is defined in the `gradle.properties` file in the root of your Liferay Workspace:

```properties
liferay.workspace.target.platform.version=7.2.0
```

When using the Target Platform plugin, you don't need to define version numbers for Liferay platform or other common dependencies, like `portlet-api` or `servlet-api`. Here's an example of a Target Platform plugin managing `build.gradle`:

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "javax.portlet", name: "portlet-api"
	compileOnly group: "javax.servlet", name: "javax.servlet-api"
	compileOnly group: "jstl", name: "jstl"
	compileOnly group: "org.osgi", name: "org.osgi.core"
	compileOnly group: "org.osgi", name: "osgi.cmpn"
}

```
> See more information about the Target Platform plugin on [Developer Network](https://dev.liferay.com/de/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace).

#### Common Dependency Management Tasks

Although the Target Platform plugin handles many of the time-taking dependency management tasks, sometimes we have to define the versions numbers explicitly. It's also good to know how dependency management works behind the scenes.

Examples of common tasks you might encounter when dealing with module dependencies:

1. Reference another module project in the project environment.
1. Find a library bundle containing a missing Liferay class (for example, JournalArticle).
1. Determine the right dependency scope for a library (compile, provided...)
1. Define the version range for the dependent library.
1. Embed non-OSGi-compliant libraries into your module.
1. Use a class from the global class loader (e.g., `TOMCAT/lib/ext`).

#### 1 - Referencing a Dependent Module Project

Declaring a dependent Liferay module project on the same Liferay Workspace is done in `build.gradle`. Bndtools will automatically take care of creating the feature-sharing headers:

```groovy
compileOnly project(":modules:training-module:training-module-api")
```

#### 2 - Finding a Library Bundle Containing a Liferay Class

A common task in developing Liferay modules is to find the dependency bundle containing a certain Liferay class. Liferay's bundle repository makes it easy:

#### Step 1 - Find the Bundle for BlogsEntry

Go to https://repository.liferay.com/nexus/index.html and search the bundle by the class name "BlogsEntry": 

<img src="../images/blogsentry-example-1.png" style="max-height:42%"/>

The *Group*, *Artifact* and *Version* information can be found in the *Maven* panel.

<br />

#### Step 2 - Add the Dependency to build.gradle

As the Blogs API in the example is under Target Platform management, you can remove the version:

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
	compileOnly group: "javax.portlet", name: "portlet-api"
	compileOnly group: "javax.servlet", name: "javax.servlet-api"
	compileOnly group: "jstl", name: "jstl"
	compileOnly group: "org.osgi", name: "osgi.cmpn"
	compileOnly group: 'com.liferay', name: 'com.liferay.blogs.api'
}
```

#### 3 - Determining Gradle Dependency Scope

If a dependent library is available in the OSGi container at runtime, you should usually try to use the `compileOnly` scope, which includes the dependency classes only at compile time but doesn't include those in the bundle. The `compileOnly` scope is not transitive, meaning that dependencies of the dependent library are not resolved automatically, and you have to take care of declaring those too. 

Below is a list of Gradle dependency scopes commonly used in Liferay plugin development. In addition to the standard Gradle scopes, there are some Liferay-provided custom scopes. For more information, please visit the Liferay Developer Network website.

* __compile:__ classes are included both at compile and at build time; transitive
* __compileOnly:__ classes are included only at compile time; non-transitive
* __compileInclude:__ like compileOnly, but takes care of including the resources too 
* __provided:__ classes only needed at runtime and provided by the container
* __runtime:__ classes needed at runtime
* __testCompile:__ like compileOnly, but for tests

#### 4 - Defining Version Range for the Dependency

Target Platform usually doesn't require using versions in the `build.gradle`, but the feature-sharing of OSGi requires explicit versions. If you need to explicitly declare feature-sharing directives in the `bnd.bnd`, you'll always use version ranges:

```properties
Import-Package = com.liferay.training.module;version="[1.3,2)"
```

> It's important to notice the division of labor between Gradle and Bndtools. Dependencies declared in the `build.gradle` file make the libraries available for the build process, but bundle runtime dependencies (feature-sharing) are declared in the `bnd.bnd` file. 

The effective version range of the compiled module is a combination of both the gradle dependencies and the `Import-Package` configuration in the `bnd.bnd` file. The version made available by `build.gradle` becomes effectively the lowest accepted version. Take a look at a few examples illustrating this behavior:

In the first example, the compiled module accepts any version between 1.3 and 2, including 2, at runtime:

**bnd.bnd**
```properties
Import-Package = com.liferay.training.module;version="[1.3,2)"
```

**build.gradle**
```groovy
compileOnly group: "com.liferay", name: "com.liferay.training.module", version: "1.3.0"
```

In the second example, the resulting OSGi bundle only accepts versions from 1.3.2 to 2:

**bnd.bnd**
```properties
Import-Package = com.liferay.module;version="[1.3.0,2)"
```

**build.gradle**
```groovy
compileOnly group: "com.liferay", name: "com.liferay.module", version:"1.3.2"
```

Using, for example, version 1.3.1, would result in:

```
Unresolved requirement: Import-Package: com.liferay.module; version="[1.3.2,4.0.0)"
```

#### 5 - Embed Non-OSGi-Compliant Libraries into Your Module

The Liferay-provided, custom `compileInclude` Gradle scope provides a convenient way to embed a dependent artifact into the lib folder of a module’s JAR. The required `Bundle-ClassPath` header is added to the bundle manifest automatically:

```groovy
dependencies {
	compileInclude group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0'
}
```

> Transitive optional dependencies are not embedded automatically. 

External libraries can be embedded in the module JAR as well using the standard Gradle scopes. Below is an example of embedding Google's Guava library. First, declare the dependency in `build.gradle`, and then reference it in `bnd.bnd`:

**build.gradle**
```groovy
dependencies {
	compileOnly group: 'com.google.guava', name: 'guava', version: '21.0'
}
```

**bnd.bnd (Option 1)**
```properties
Include-Resource: @guava-21.0.jar
```

**bnd.bnd (Option 2)**
```properties
Bundle-ClassPath:\
  .,\
  lib/guava.jar

-includeresource:\
  lib/guava.jar=guava-21.0.jar
```

> See the Developer Network article (https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module) for more information on this use case. 

#### 6 - Importing a Library From a Global Class Loader

Every OSGi bundle in the container has its own class loader. Class loader delegation is managed by the OSGi container, which requires bundles to explicitly define what they expose from themselves to the container. This means that, by default, none of the bundle's own classes nor any embedded libraries are exposed to other bundles in the container.

Further, classes from Liferay's web application's `WEB-INF/classes` or `lib` folders are not available to the OSGi container. If you want to share a library from Tomcat's `lib/ext` directory, that has to be declared as an extra package in the portal configuration file:

<br />

portal-ext.properties:
```
module.framework.system.packages.extra=my.dependendent.library.package
```

#### Using Non-OSGi-Compliant Libraries

An OSGi-compliant library bundle can be deployed to the OSGi container and made available to other bundles. But what do you do if your project depends on a library that is not OSGi-compliant? 

[Eclipse Orbit](https://www.eclipse.org/orbit/) and [ServiceMix](http://servicemix.apache.org/) bundles are projects that try to solve this problem by providing OSGi variants for many common libraries. If these projects don't provide an OSGi version of the library required by your project, you have to consider other options. If you have the source code for the respective library available, you can try to make it OSGi-compliant yourself. If that doesn't work, you can embed the required library or classes in your bundle directly using the approach described above in *Embed Non-OSGi-Compliant Libraries into Your Module*.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>A Gradle dependency is _________ an OSGi dependency.</li>
		<ul>
			<li>_____________________ provides the dependencies.</li>
			<li>_____________________ wires the dependencies.</li>
		</ul>
	<li>Try to use ___________________ Gradle scope for dependencies not provided.</li>
	<li>Try to use the ________________ compatible version for dependencies.</li>
	<li>bnd.bnd and build.gradle version information is merged. In the case of build.gradle providing a __________________ number that is not accepted in bnd.bnd, the version from build.gradle becomes the minimum in effect.</li>
	<li>Run __________________________ whenever modifying the dependencies.</li>
	<li>In case of problems, clear the __________________ cache.</li>
</ul>
</div>