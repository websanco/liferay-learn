# Importing Packages

Modules often depend on functionality from other modules. Modules in the OSGi framework must [export](./exporting-packages.md) a package for your module to import it.

When an OSGi module imports a package, the OSGi framework finds other registered modules that export the package and wires it to the importing module. At run time, the importing module gets the class from the wired module that exports the package.

For this to happen, a module JAR's `META-INF/MANIFEST.MF` file must specify the `Import-Package` OSGi manifest header with a comma-separated list of the Java packages it needs. For example, if a module needs classes from the `javax.portlet` and `com.liferay.portal.kernel.util` packages, it must specify them like so:

```properties
Import-Package: javax.portlet,com.liferay.portal.kernel.util
```

Import packages must sometimes be specified manually, but not always. Conveniently, [Workspace](../../developing-applications/tooling/liferay-workspace.md)-based module projects automatically detect required packages and add them to the module manifest's package import list. Here are the different package import scenarios:

* [Automatic Package Import Generation](#automatic-package-import-generation)

* [Manually Adding Package Imports](#manually-adding-package-imports)

Read below to explore how package imports are specified in these scenarios.

## Automatic Package Imports

[Workspace](../../developing-applications/tooling/liferay-workspace.md)-based projects from the tutorial examples (see [Module Projects](./module-projects.md)) or created using [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md) or [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md) use [Bnd](http://bnd.bndtools.org/). On building such a project's module JAR, Bnd detects the packages the module uses and generates a `META-INF/MANIFEST.MF` file with an `Import-Package` header that lists the packages.

```note::
   Liferay's project templates use `a third-party Gradle plugin <https://github.com/TomDmitriev/gradle-bundle-plugin>`_ to invoke Bnd.
```

For example, suppose you're developing a Liferay module. In most cases, you specify your module's dependencies in your `build.gradle` file. At build time, the Gradle module plugin reads your `build.gradle` file and Bnd adds the required `Import-Package` headers to your module JAR's `META-INF/MANIFEST.MF`.

Here's an example dependencies section from the [Gogo Command Sample](https://github.com/liferay/liferay-blade-samples/tree/7.3/liferay-workspace/extensions/gogo)'s `build.gradle` file:

```groovy
dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
    compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"
}
```

And here's the `Import-Package` header that's generated in the module JAR's `META-INF/MANIFEST.MF` file:

```properties
Import-Package: com.liferay.portal.kernel.service;version="[4.3,5)"
```

The build file need only specify artifact dependencies. Bnd examines the module class path to dependency packages the module uses. The examination includes all classes found in the class path--even those from embedded [third party library JARs](./configuring-dependencies/resolving-third-party-library-package-dependencies.md).

```note::
   For a plugin WAR project, Liferay's `WAB Generator <../reference/deploying-wars-wab-generator.md>`_ detects their use in the WAR's JSPs, descriptor files, and classes (in ``WEB-INF/classes`` and embedded JARs). The WAB Generator searches the ``web.xml``, ``liferay-web.xml``, ``portlet.xml``, ``liferay-portlet.xml``, and `liferay-hook.xml` descriptor files. It adds package imports for classes that are neither found in the plugin's ```WEB-INF/classes``` folder nor in its embedded JARs.
```

## Manually Adding Package Imports

Package imports for classes referenced in the following places must be determined and added manually:

* Unrecognized descriptor file
* Custom or unrecognized descriptor element or attribute
* Reflection code
* Class loader code

Add them to an `Import-Package` OSGi header in the location appropriate to your project type:

| Project type | `Import-Package` header location |
| :----------- | :------------------------------- |
| Module JAR (uses Bnd)     | `[project]/bnd.bnd` |
| Plugin WAR | `WEB-INF/liferay-plugin-package.properties` |

### Java API Packages

Packages for Java APIs, such as Java Portlet, aren't [semantically versioned](./semantic-versioning.md) but have Portable Java Contracts. Each API's contract specifies the JSR it satisfies. Modules that use these APIs must specify requirements on the API contracts. The contract requirement specifies the module's relationship with the imported API packages. If the system you're running does *not* provide the exact contract, the module does not resolve. Resolving a missing package is better than handling an incompatibility failure during execution.

* **Workspace-based projects** specify Portable Java Contracts automatically! For example, if your Blade CLI or Liferay Developer Studio module uses the Java Portlet API and you compile against the Java Portlet 2.0 artifact, a contract requirement for the package is added to your module's manifest.

* **WAR projects** don't use Bnd and must specify contracts in their `WEB-INF/liferay-plugin-package.properties` file. For example, here's the specified contract for `JavaPortlet` 2.0:

    ```
    Import-Package: javax.portlet Require-Capability: osgi.contract;filter:=(&(osgi.contract=JavaPortlet)(version=2.0))
    ```

For Portable Java Contract details, see [Portable Java Contract Definitions](https://www.osgi.org/portable-java-contract-definitions/).

Congratulations! Now you can import all kinds of packages for your modules and plugins to use.

## Additional Information

* [Configuring Dependencies](./configuring-dependencies/configuring-dependencies.md)
* [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)
* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)
* [Deploying WARs \(WAB Generator\)](../reference/deploying-wars-wab-generator.md)
* [Semantic Versioning](./semantic-versioning.md)