# Resolving Third Party Library Package Dependencies

An application can rely on multiple OSGi modules. Resolving their Java package dependencies can be challenging. In a perfect world, every package would be distributed in OSGi JARs, but many packages are only in traditional libraries (non-OSGi JARs). You can resolve dependencies on third party, non-OSGi JARs in several ways:

1. Projects, such as [Eclipse Orbit](https://www.eclipse.org/orbit/) and [ServiceMix Bundles](https://servicemix.apache.org/developers/source/bundles-source.html), convert hundreds of traditional Java libraries to OSGi modules. You might find an OSGi JAR with the packages. 

    * [Eclipse Orbit downloads \(select a build\)](https://download.eclipse.org/tools/orbit/downloads/)
    * [ServiceMix Bundles](https://mvnrepository.com/artifact/org.apache.servicemix.bundles)

    If you find a module with the packages, [deploy](../../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md) it and [add a `compileOnly` dependency](./specifying-dependencies.md) on it. If there is no module for the packages, continue to the next step.

1. Check if Liferay already exports library packages you're using. If Liferay exports them, adjust your dependency per the instructions in [Exported Third Party Packages](../../reference/exported-third-party-packages.md).

1. Add the non-OSGi JAR as a `compileInclude` dependency:

    ```groovy
    dependencies {
        compileInclude group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0'
    }
    ```

    Liferay's `compileInclude` configuration is transitive---it embeds the library and all of its dependencies in the module JAR's `lib` folder and adds the JARs to the module's `Bundle-ClassPath` manifest header.

    ```{note}
    The `compileInclude` configuration does not download transitive [optional dependencies](https://maven.apache.org/guides/introduction/introduction-to-optional-and-excludes-dependencies.html). If you require a package from an optional dependency, resolve the package as you would another third party library package.
    ```

1. Compile your module.

1. Deploy your module and check for unresolved package dependencies.

1. If there is an unresolved dependency on a package your module doesn't use, block importing that package.

    ```
    Import-Package:\
        !foo.bar.baz,\
        *
    ```

    The `!`character negates importing the package. The `*` character represents all packages that the module refers to explicitly. Putting `*` at the end of the list causes Bnd to import all packages your module references.

```{note}
If your WAR file requires a different version of a third-party package that [Liferay exports already](../../reference/exported-third-party-packages.md), specify that package in your [`Import-Package:` list](../importing-packages.md). If that package is in an OSGi module, deploy it.

When Liferay DXP deploys a WAR, it converts it to a [WAB](https://docs.osgi.org/specification/osgi.cmpn/7.0.0/service.war.html), and it strips already exported third-party JARs out of WABs at deploy time. To force deployment of a different version than Liferay exports, rename that package's non-OSGi JAR differently from the [JAR that the WAB generator excludes](../../../developing-applications/reference/jars-excluded-from-wabs.md) and [embed the JAR](#embedding-a-library) in your project.
```

Congratulations! You resolved dependencies on packages from non-OSGi JARs.

## Additional Information

* [Importing Packages](../importing-packages.md)
* [Exporting Packages](../exporting-packages.md)
* [Semantic Versioning](../semantic-versioning.md)