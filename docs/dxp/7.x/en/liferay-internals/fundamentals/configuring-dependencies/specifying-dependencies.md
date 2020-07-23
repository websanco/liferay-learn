# Specifying Dependencies

Compiling a module and deploying it to Liferay requires satisfying the module's external dependencies. After [finding dependency artifacts](../finding-artifacts.md), you must [declare them as dependencies](https://docs.gradle.org/current/userguide/declaring_dependencies.html) in your Gradle build file.

Compilation requires locally available packages from artifacts. Deployment requires packages from artifacts at run time. Run time artifacts may be bundled with Liferay or can be deployed to Liferay separately in other modules or packaged with your project. Here you'll learn how to specify artifact dependencies for compile time and run time scenarios.

## Adding Dependencies

Here's how to configure dependencies:

1. In your Gradle build file (e.g., `build.Gradle`), declare a `dependencies` set, if one doesn't already exist.

    ```groovy
    dependencies {
    }
    ```

1. Make all Liferay-bundled APIs available at compile time by adding a `release.portal.api` dependency for your Liferay version.

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.portal", name: "release.portal.api", version: "7.2.0"
    }
    ```

1. Add dependencies for non-bundled artifacts (from Liferay or third parties), making sure to specify each dependency's attributes:

    * Group
    * Name
    * Version

    Here a non-bundled dependency on Bnd Lib has been added.

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.portal", name: "release.portal.api", version: "7.2.0"
        compileOnly group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.5.0"
        ...
    }
    ```

    Use the `compileOnly` scope initially and adjust scope as demonstrated in the remaining steps.

1. Change the scope to `providedCompile` for any artifacts that Liferay exports packages from already. Please see [Exported Third Party Packages](../../reference/exported-third-party-packages.md) for details.

    For example, if your project uses Spring Bean packages, use the Liferay-provided packages at run time by specifying the `providedCompile` scope. The `providedCompile` scope prevents the project from deploying the artifact with the project.

    ```groovy
    dependencies {
        providedCompile group: "org.springframework", name: "spring-bean", version: "4.1.9"
        ...
    }
    ```

1. Deploy your module and check for unsatisfied package dependencies by browsing the logs or using [Gogo Shell commands](../using-the-gogo-shell.md).

1. If there are unsatisfied dependencies on Liferay modules or third party modules, deploy those modules. Please see [Installing and Managing Apps](../../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md) for more information.

1. If there are unsatisfied dependencies on third party libraries (not modules), please see [Resolving Third Party Library Dependencies](./resolving-third-party-library-dependencies.md) for guidance.

Next, there is a table that shows dependency suggestions for various scenarios.

## Dependency Scenario Examples

The following table describes several different example dependency scenarios.

| Example Artifact | OSGi Module? | Liferay Artifact? | Deployment Method | Notes |
| :--------------- | :---------- | :---------------- | :---------------- | :-------------- |
| [Liferay Journal API](https://docs.liferay.com/dxp/apps/journal/latest/javadocs/) | yes | yes | Bundled with Liferay | Specify a `release.portal.api` dependency with the `compileOnly` scope |
| [Liferay Connector to Elasticsearch 7](https://web.liferay.com/marketplace/-/mp/application/170390307) | yes | yes | Deployed separately | Use `compileOnly` scope |
| Bnd Lib | yes | no | Deployed separately | Use `compileOnly` scope |
| Spring Framework Spring Bean | yes | no  | Bundled with Liferay | Use the `providedCompile` scope and the version of the artifact that Liferay uses |
| [Apachi Shiro](https://shiro.apache.org/) Shiro Core library | no  | no  | Deployed separately or in the project | Please see [Resolving Third-Party Library Dependencies](./resolving-third-party-library-package-dependencies.md) |

Nice! You know how to specify artifact dependencies. Now that's a skill you can depend on!

## Additional Information

* [Finding Artifacts](./finding-artifacts.md)
* [Importing Packages](../importing-packages.md)
* [Exporting Packages](../exporting-packages.md)
* [Resolving Third Party Library Package Dependencies](./resolving-third-party-library-package-dependencies.md)
* [Deploying WARs \(WAB Generator\)](../../../developing-apps/reference/deploying-wars-wab-generator.md)