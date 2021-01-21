# Example Code Guidelines

## Table of Contents

* [Purpose](#purpose)
* [Creating an Example Project](#creating-an-example-project)
    * [Example Project Structure](#example-project-structure)
    * [Create an Example Project](#create-an-example-project)
* [Guidelines](#guidelines)
    * [Key Examples](#key-examples)
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
* [Review Process](#review-process)
    * [Test Your Code](#test-your-code)
    * [Code Changes Only](#code-changes-only)
    * [Submit Your Code](#submit-your-code)

## Purpose

Here are guidelines for tutorial code. It demonstrates starting an example project, describes tutorial code rules, and explains the code review process.

## Creating an Example Project

Here you'll learn how to create an example project for a tutorial. It will be a Java project that uses Liferay Workspace.

> **Prerequisite:** A Java 8 JDK (such as [Azul Zulu Java 8](https://www.azul.com/downloads/zulu-community/?version=java-8-lts&package=jdk)). See the [Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360049238151).

> **Important:** Use a dedicated branch (free of any new/modified articles) for your example code. Branches submitted for code review must contain only code changes--branches must not contain any new/modified articles.

### Example Project Structure

Here's the structure for an example project that has the ID `c3p0` (more on project IDs shortly). The project is for a tutorial article whose Markdown file will be `implementing-something.md`:

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

1. Decide on your project's *unique*, *random-looking* four-character ID, following the ID pattern below.

   ID Pattern:
   ```
   [a-z][0-9][a-z][0-9]
   ```
   
   Example: `c3p0`

    For convenience, these guidelines use `xxxx` as an ID placeholder--replace it with your unique ID.

    Here's a way of generating an ID that fits the pattern. 

    ```bash
    tr -cd a-z0-9 < /dev/urandom | head -c 1000 | sed 's/.*\([a-z]\).*\([0-9]\).*\([a-z]\).*\([0-9]\).*/\1\2\3\4\n/'
    ```

    > **Tip:** Make sure your ID is unique by searching your `liferay-learn` branch for any project folders that use the ID. For example, `find . -name liferay-c3p0.zip`

    > **Important:** Make sure your ID appears to be random. Avoid these things in an ID: Using your initials in multiple IDs (e.g., `j0h1`, `j2h3`), duplicating characters (e.g., `b1b5` or `a1z1`), using sequential characters (e.g., `a1b2`), and repeating part of an ID (e.g., `a8q1`, `a8q2`).

1. Create your `liferay-xxxx.zip` project folder in a `[tutorial-name]/resources/` folder that's in the same location (shown as `[tutorial path]` below) that you will eventually put the tutorial article Markdown file.

    ```
    [tutorial path]
    └── [tutorial-name]/resources/liferay-xxxx.zip
    ```

1. In your project, create a module(s) whose root folder follows these naming conventions:

    | Example Folder Name | Description |
    | :-------------- | :---------- |
    | xxxx-api | Contains an API. |
    | xxxx-impl | Contains an implementation. |
    | xxxx-web | Contains a web application, such as a portlet. |
    
    > **Note:** You can develop modules any way you like (e.g., manually, using [Liferay Dev Studio](https://liferay.dev/-/ide), using [Blade CLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI), and more), but refrain from committing any extranious files (e.g., a `.project` file) by adding such files to the repository's `.gitignore` file. 

1. In each module folder, create a `bnd.bnd` file. Please see [Initial Bnd Content](#initial-bnd-content) for example Bnd starting content for some different modules types.

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

Congratulations! Your tutorial now has an example project that uses Liferay Workspace. Develop your example next. When you're done developing, follow the [Review Process](#review-process) at the end of this article to submit your project.

## Guidelines 

Here are the rules and guidelines for example code.

### Key Examples

Here are some examples that demonstrate example project types.

| Example Type | Reference |
| :----------- | :-------- |
| API Implementation | [writing-a-similar-results-contributor/resources/liferay-r1s1.zip](../docs/dxp/7.x/en/using-search/developer-guide/writing-a-similar-results-contributor/resources/liferay-r1s1.zip) |
| API & OSGi Services | [using-an-osgi-service/resources/liferay-j1h1.zip](../docs/dxp/7.x/en/liferay-internals/fundamentals/using-an-osgi-service/resources/liferay-j1h1.zip) |
| Portlet | [using-mvc/using-a-jsp-and-mvc-portlet/resources/liferay-w3e7.zip](../docs/dxp/7.x/en/developing-applications/developing-a-java-web-application/using-mvc/using-a-jsp-and-mvc-portlet/resources/liferay-w3e7.zip) |
| Fragment (JS project) | [adding-configuration-options-to-fragments/resources/liferay-c7f8.zip](../docs/dxp/7.x/en/site-building/developer-guide/developing-page-fragments/adding-configuration-options-to-fragments/resources/liferay-c7f8.zip) |


### Module Structure

Java projects can have one or more modules. All of a project's modules go in the project folder (e.g., `liferay-xxxx.zip` folder)

#### API Module Structure

```
xxxx-web // Web Module
├── bnd.bnd
├── build.gradle
└── src/main/
    └── java/ // Put Java classes here
        └── com/acme/xxxx/DescribesWhatItDoes.java
```

#### Impl Module Structure

```
xxxx-impl
├── bnd.bnd
├── build.gradle
└── src/main/
    ├── java/ // Put Java classes here
    |   └── com/acme/xxxx/internal/[same/path/as/inteface/]XXXXInterfaceName.java
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
    ├── java/ // Put Java classes here
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

For an example, see [M6A8CommerceDiscountRuleTypeImpl.java](../docs/commerce/2.x/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/src/main/java/com/acme/m6a8/web/internal/commerce/discount/rule/type/M6A8CommerceDiscountRuleTypeImpl.java) in `liferay-m6a8.zip`.

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

For an example, see [R2F1DDMStorageAdapter.java](../docs/dxp/7.x/en/process-automation/forms/developer-guide/writing-a-form-storage-adapter/resources/liferay-r2f1.zip/r2f1-impl/src/main/java/com/acme/r2f1/internal/dynamic/data/mapping/storage/R2F1DDMStorageAdapter.java) in `liferay-r2f1.zip`.

## Templates

Put templates in your web module's `src/main/resources/META-INF/resources/` folder.

If your module uses a generic view JSP, name it `view.jsp`. If it has more than one, name them `view_1.jsp`, `view_2.jsp`, etc.

## Review Process 

Overview

1. Run `update_examples.sh xxxx` (replace `xxxx` with project ID) to compile and to run the source formatter.
1. [Test your code](#test-your-code); the code has to work. 
1. [Code changes only](#code-changes-only); don't include article changes.
1. [Submit your code changes](#submit-your-code) to `jhinkey` (Jim Hinkey) here in `liferay-learn`.

### Run update_examples.sh

```bash
cd docs
```

```bash
./update_examples.sh xxxx
```

Resolve any reported issues and commit any changes that you or the script made.

### Test Your Code 

Verify and validate your example on your target Liferay version. If it only works on a specific version or if it works slightly differently on different versions, note it for mentioning later in the tutorial article. 

Testing against a Liferay Docker image is typically easiest. Here are some references:

* [Install Docker CLI](https://docs.docker.com/engine/reference/commandline/docker/) (i.e, `docker`)
* [Get and start the latest DXP image](../docs/dxp/7.x/en/getting-started/starting-with-a-docker-image.md#get-started-with-liferay-dxp)
* Read [Docker Container Basics](../docs/dxp/7.x/en/installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md)

### Code Changes Only 

Branches submitted for code review must contain only code changes--branches must not contain any new/modified articles. 

> **Tip:** Use a dedicated branch (free of any new/modified articles) for your example code. However, if you've included article changes in your branch, back them up (e.g., copy the articles to your Desktop) and then remove them from your branch.

### Submit Your Code 

Send a pull request to `jhinkey` (Jim Hinkey). He will review your code before sending it onward for final review and merging.

Thanks for submitting your example to Liferay!