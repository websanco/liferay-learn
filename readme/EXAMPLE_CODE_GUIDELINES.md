# Example Code Guidelines

## Table of Contents

* [Purpose](#purpose)
* [Creating an Example Project](#creating-an-example-project)
    * [Example Project Structure](#example-project-structure)
    * [Create an Example Project](#create-an-example-project)
* [Guidelines](#guidelines)
    * [Module Structure](#module-structure)
        * [API Module Structure](#api-module-structure)
        * [Impl Module Structure](#impl-module-structure)
        * [Web Module Structure](#web-module-structure)
    * [bnd.bnd](#bndbnd)
        * [Initial Bnd Content](#initial-bnd-content)
        * [Export API Packages](#export-api-packages)
    * [build.gradle](#buildgradle)
    * [Packages](#packages)
        * [Implementation PackageNames](#implementation-package-names)
    * [Classes](#classes)
        * [Class Names](#class-names)
        * [Component Annotations](#component-annotations)
    * [Logic](#logic)
        * [Localization](#localization)
            * [Labels and Headings](#labels-and-headings)
            * [Using Language Keys](#using-language-keys)
        * [Logging Output](#logging-output)
    * [Templates](#templates)

## Purpose

This document provides guidelines for tutorial example code. It demonstrates starting an example project, describes code example rules, and describes the code review process.

## Creating an Example Project

Here you'll learn how to create an example project to accompany your developer tutorial. It will be a Java project that uses Liferay Workspace.

> Prerequisite: A Java 8 JDK (such as [Azul Zulu Java 8](https://www.azul.com/downloads/zulu-community/?version=java-8-lts&package=jdk)). See the [Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360049238151).

### Example Project Structure

Here's the structure for an example project that has the ID `c3p0` (more on project IDs shortly). The project is shown alongside its tutorial called `implementing-something.md`:

```
[tutorial path] // This is the same folder your article will go in later.
└── implementing-something/resources/liferay-c3p0.zip/
    └── c3p0-impl // Module
        ├── bnd.bnd
        ├── build.gradle
        └── src/main/java/ // Your Java code goes here
```

You only need to create two module files:
1. `bnd.bnd`
1. `build.gradle`

Our `update_tutorials.sh` script creates the rest. Create your own project next.

### Create an Example Project

1. Decide on your example's *unique* four-character ID, following the ID pattern below.

   ID Pattern:
   ```
   [a-z][0-9][a-z][0-9]
   ```
   
   Example: `c3p0`

    For convenience, these steps use `xxxx` as an ID placeholder--replace it with your unique ID.

    > Tip: Make sure your ID is unique by searching the your liferay-learn branch for any project folders that use the ID. For example, `find . -name liferay-c3p0.zip`

1. Create your `liferay-xxxx.zip` example project folder in a `[tutorial-name]/resources/` folder, that is alongside your tutorial Markdown file.

    ```
    [tutorial path]
    └── [tutorial-name]/resources/liferay-xxxx.zip
    ```

1. In your project, create module(s) whose root folders follow these naming conventions:

    | Example Folder Name | Description |
    | :-------------- | :---------- |
    | xxxx-api | Contains an API. |
    | xxxx-impl | Contains an implementation. |
    | xxxx-web | Contains a web application, such as a portlet. |
    
    You can create modules any way you like (e.g., manually, using [Liferay Dev Studio](https://liferay.dev/-/ide), using [Blade CLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI), and more).

1. In each module folder, create a `bnd.bnd` file. Here is example Bnd content for the different modules types.

    | Module Type | Bnd Content |
    | :---------- | :---------- |
    | API | Bundle-Name: Acme XXXX API<br>Bundle-SymbolicName: com.acme.xxxx.api<br>Bundle-Version: 1.0.0 |
    | Implementation | Bundle-Name: Acme XXXX Implementation<br>Bundle-SymbolicName: com.acme.xxxx.impl<br>Bundle-Version: 1.0.0 |
    | Portlet/Web App | Bundle-Name: Acme XXXX Web<br>Bundle-SymbolicName: com.acme.xxxx.web<br>Bundle-Version: 1.0.0 |

1. In your module, create a `build.gradle` file that depends on `release.portal.api` or `release.dxp.api` (for DXP-only features). For example,

    ```
    dependencies {
    	compileOnly group: "com.liferay.portal", name: "release.portal.api"
    }
    ```

1. In your module, create a `src/main/java` folder for your Java code.

1. Copy our Java Workspace template to your project and build your project by running the `update_examples.sh` script from the `liferay-learn/docs` folder. For example,

    ```bash
    ./update_examples.sh c3p0
    ```
    
    Here's the file structure with the files added by `update_examples.sh`.
    

```
[tutorial path]
└── implementing-something/resources/liferay-c3p0.zip/
    ├── gradle.properties // Specifies the Liferay product/version to build against
    ├── gradlew // Gradle wrapper
    ├── gradlew.bat // Gradle wrapper (Windows)
    ├── settings.gradle // Specifies the artifact repository
    ├── source-formatter-suppressions.xml // Suppresses unneeded code format checks
    └── c3p0-impl // Module
        ├── bnd.bnd
        └── build.gradle
```

Congratulations! Your tutorial now has an example project that uses Liferay Workspace. Develop your example next.

## Guidelines 

Here are the rules and guidelines for example code.

### Module Structure

Java projects can have one or more modules. All of a project's modules go in the project folder (e.g., `liferay-xxxx.zip` folder)

#### API Module Structure

```
xxxx-web // Web Module
├── bnd.bnd
├── build.gradle
└── src/main/
    └── java/
        └── com/acme/xxxx/DescribesWhatItDoes.java
```

#### Impl Module Structure

```
xxxx-impl
├── bnd.bnd
├── build.gradle
└── src/main/
    ├── java/ // Put Java classes here
    └── resources/
        └── content/
            └── Language.properties // (Optional)
```

#### Web Module Structure

```
xxxx-web // Web Module
├── bnd.bnd
├── build.gradle
└── src/main/
    ├── java/
    |   └── com/acme/xxxx/web/internal/portlet/XXXXPortlet.java
    └── resources/
        ├── content/
        |   └── Language.properties
        └── META-INF/resources/ // Put Templates (JSPs) here
            ├── view_1.jsp
            └── view_2.jsp
```

### bnd.bnd

In each module folder, create a `bnd.bnd` file.

#### Initial Bnd Content

Here is example initial Bnd content for the different modules types.

| Module Type | Bnd Content |
| :---------- | :---------- |
| API | Bundle-Name: Acme XXXX API<br>Bundle-SymbolicName: com.acme.xxxx.api<br>Bundle-Version: 1.0.0 |
| Implementation | Bundle-Name: Acme XXXX Implementation<br>Bundle-SymbolicName: com.acme.xxxx.impl<br>Bundle-Version: 1.0.0 |
| Portlet/Web App | Bundle-Name: Acme XXXX Web<br>Bundle-SymbolicName: com.acme.xxxx.web<br>Bundle-Version: 1.0.0 |

#### Export API Packages 

If you've defined APIs in your module, export their packages in your `bnd.bnd`.

For example, the `k8s2-api` module publishes its com.acme.k8s2.Greeter interface by exporting the `com.acme.k8s2` package in the module's `bnd.bnd`:

```properties
Export-Package: com.acme.k8s2 
```

## build.gradle

Make sure your `build.gradle` file sets a dependency on `release.portal.api`, unless you're demonstrating a DXP-only feature--In which case use `release.dxp.api`. Here's your typical `build.gradle` starting content:

```
dependencies {
	compileOnly group: "com.liferay.portal", name: "release.portal.api"
}
```

## Packages

Start package names with this pattern:

```
com.acme.[ID]
```

### Implementation Package Names

An implementation package should resemble the interface package but be *internal*. The table below shows the class packages and names for an interface and an implementation of that interface--the similarities are in bold.

| Class Type | Fully Qualified Class Name |
| :--------- | :------------------------- |
| Interface | com.liferay.**dynamic.data.mapping.storage**.**DDMStorageAdapter** |
| Implementation | com.acme.r2f1.internal.**dynamic.data.mapping.storage**.R2F1**DDMStorageAdapter** |

## Classes

### Class Names

In most cases, prefix class names with the project ID (capitalized).


| Class Type | Class Name |
| :--------- | :------------------------- |
| Interface | *DescribeWhatItIs* (e.g., `Greeter`) |
| Implementation | XXXX*InterfaceName* (e.g., `C3P0Greeter`) |
| Portlet | XXXXPortlet (e.g., `C3P0Portlet`)|

### Component Annotations

#### immediate = true

Don't use the `immediate = true` property. Use it only if testing shows that you need it. You should only need it if the class must perform an initialization before it's used.

## Logic

### Localization 

If your module uses localized content, specify the content using language property files (*Language Keys*). Put your default Language Keys in this file:

`src/main/resources/content/Language.properties`

#### Labels and Headings

Include the project ID in labels and headings. For example, `c1n4` is the project ID used in these Language Keys:

```properties
c1n4-commerce-product-type=C1N4 Commerce Product Type
c1n4-screen-navigation-entry=C1N4 Screen Navigation Entry
```

#### Using Language Keys

Access Language Keys with resource bundles. Here's an example of returning a Language Key.

```java 
ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
    "content.Language", locale, getClass());

return LanguageUtil.get(resourceBundle, "c1n4-commerce-product-type");
```

### Logging Output

If you want to print messages, use a logger.

Import:

```java 
import com.liferay.portal.kernel.log.LogFactoryUtil;
```

Field:

```java 
private static final Log _log = LogFactoryUtil.getLog(
		R2F1DDMStorageAdapter.class);
``` 

Logging Logic:

```java
    if (_log.isWarnEnabled()) {
		_log.warn("Acme storage adapter's save method was invoked");
	}
```

## Templates

Put templates in your web module's `src/main/resources/META-INF/resources/` folder.

If your module uses a generic view JSP, name it `view.jsp`. If it has more than one, name them `view_1.jsp`, `view_2.jsp`, etc.