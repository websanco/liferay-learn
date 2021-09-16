# Importing Packages

You often find yourself in a position of needing functionality provided by another module. To access this functionality, you must import packages from other modules into your module's classpath. This requires that those other modules have already [exported](./exporting-packages.md) their packages containing the functionality you want. The OSGi framework wires the packages to the importing module's classpath. The module JAR's `META-INF/MANIFEST.MF` file uses the `Import-Package` OSGi header to import packages.

```properties
Import-Package: javax.portlet,com.liferay.portal.kernel.util
```

Import packages must sometimes be specified manually, but not always. Conveniently, [Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)-based module projects automatically detect required packages and add them to the module manifest's package import list. Import packages must sometimes be specified manually.

There are two different package import scenarios:

* [Automatic Package Imports](#automatic-package-imports)
* [Manual Package Imports](#manual-package-imports)

Read below to explore how package imports are specified in these scenarios.

## Automatic Package Imports

[Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)-based projects from the tutorial examples (see [Module Projects](./module-projects.md)) or created using [Blade CLI](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md) or [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md) use [Bnd](http://bnd.bndtools.org/). A Gradle plugin invokes Bnd, which can then read the Gradle dependencies and resolve the imports. When you build the project's JAR, Bnd detects the packages the module uses, generates a `META-INF/MANIFEST.MF` file, and assigns the packages to an `Import-Package` header. In that sense, package import is automatic, because you must only define your dependencies in one place: the build script.

```{note}
Liferay's project templates use [a third-party Gradle plugin](https://github.com/TomDmitriev/gradle-bundle-plugin) to invoke Bnd.
```

The [Gogo Command Sample](https://github.com/liferay/liferay-blade-samples/tree/7.3/liferay-workspace/extensions/gogo)'s `build.gradle`, for example, uses packages from `com.liferay.portal.kernel` and `org.osgi.service.component.annotations`. Here's the sample's `build.gradle` file:

```groovy
dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
    compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"
}
```

And here's the `Import-Package` header that Bnd generates in sample JAR `META-INF/MANIFEST.MF` file:

```properties
Import-Package: com.liferay.portal.kernel.service;version="[4.3,5)"
```

The build file specifies dependencies. Bnd examines the module classpath to import packages the module uses. The examination includes all classes found in the classpath---even those from embedded [third party library JARs](./configuring-dependencies/resolving-third-party-library-package-dependencies.md).

```{note}
For a plugin WAR project, Liferay's [WAB Generator](../../developing-applications/reference/deploying-wars-wab-generator.md) detects packages used in the WAR's JSPs, descriptor files, and classes (in `WEB-INF/classes` and embedded JARs). Also the WAB Generator searches the `web.xml`, `liferay-web.xml`, `portlet.xml`, `liferay-portlet.xml`, and `liferay-hook.xml` descriptor files. It adds package imports for classes that are neither found in the plugin's `WEB-INF/classes` folder nor in its embedded JARs.
```

## Manual Package Imports

If a module references a class in only the following places, you must manually add a package import.

* Unrecognized descriptor file
* Custom or unrecognized descriptor element or attribute
* Reflection code
* Classloader code

Here's how to manually import the package:

1. Open your module's `bnd.bnd` file.

1. Add the `Import-Package` header.

1. Add the package to the header's package list.

```properties
Import-Package: [... existing package list,][add the package here]
```

```{note}
To manually import a package in a plugin WAR project, add an `Import-Package` header like the one above to the project's `WEB-INF/liferay-plugin-package.properties` file.
```

### Java API Packages

Packages for Java APIs, such as Java Portlet, aren't [semantically versioned](./semantic-versioning.md) but have [Portable Java Contracts](https://www.osgi.org/portable-java-contract-definitions/). Each API's contract specifies the JSR it satisfies. Modules that use these APIs must specify requirements on the API contracts. The contract requirement defines the module's relationship with the imported API packages. If the system you're running does *not* provide the exact contract, the module does not resolve. Resolving a missing package is better than handling an incompatibility failure during execution.

[Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)-based projects specify Portable Java Contracts automatically! For example, if your module uses the Java Portlet API and you compile against the Java Portlet 2.0 artifact, a contract requirement for the package is added to your module's manifest.

```{note}
WAR projects don't use Bnd and must specify contracts in their `WEB-INF/liferay-plugin-package.properties` file. For example, here's the specified contract for `JavaPortlet` 2.0: `Import-Package: javax.portlet Require-Capability: osgi.contract;filter:=(&(osgi.contract=JavaPortlet)(version=2.0))`
```

Congratulations! Now you can import all kinds of packages for your modules to use.

## Additional Information

* [Configuring Dependencies](./configuring-dependencies.md)
* [Blade CLI](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)
* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)
* [Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)
* [Semantic Versioning](./semantic-versioning.md)
* [Deploying WARs \(WAB Generator\)](../../developing-applications/reference/deploying-wars-wab-generator.md)
