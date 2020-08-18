# Specifying Dependencies

Compiling a module and deploying it to Liferay requires satisfying the module's external dependencies. After [finding dependency artifacts](../finding-artifacts.md), you must [declare them as dependencies](https://docs.gradle.org/current/userguide/declaring_dependencies.html) in your Gradle build file. Liferay includes many artifacts whose [packages](../../reference/exported-third-party-packages.md) are available at run time. If you depend on other artifacts, you must deploy them manually. Here you'll find dependency configuration steps and examples.

## Configuring Dependencies

Here's how to configure dependencies:

1. In your Gradle build file (e.g., `build.Gradle`), declare a `dependencies` set, if one doesn't already exist.

    ```groovy
    dependencies {
    }
    ```

1. Add a dependency for each artifact your module requires. Use the [`compileOnly` configuration](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_plugin_and_dependency_management) and specify these artifact attributes:

    * Group
    * Name (Artifact ID)
    * Version

    Here are dependencies for Bnd Lib and Liferay's Journal API.

    ```groovy
    dependencies {
        compileOnly group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.5.0"
        compileOnly group: "com.liferay", name: "com.liferay.journal.api", version: "1.0.1"
        ...
    }
    ```

    ```important::
       Make sure to use artifact versions compatible with your Liferay product version.
    ```

    Use the `compileOnly` scope initially and adjust scope as needed.

1. Deploy your module and check for unsatisfied package dependencies by browsing the logs or using [Gogo Shell commands](../using-the-gogo-shell.md).

1. If there are unsatisfied dependencies on Liferay modules or third party modules, deploy them and check if your module resolves. Please see [Installing and Managing Apps](../../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md) for more information.

1. If there are unsatisfied dependencies on third party libraries (not modules), please see [Resolving Third Party Library Dependencies](./resolving-third-party-library-dependencies.md) for guidance in resolving them.

Next, there is a table that shows dependency suggestions for various scenarios.

## Dependency Scenario Examples

The following table describes several different example dependency scenarios.

| Example Artifact | OSGi Module? | Liferay Artifact? | Deployment Method | Notes |
| :--------------- | :---------- | :---------------- | :---------------- | :-------------- |
| [Liferay Journal API](https://docs.liferay.com/dxp/apps/journal/latest/javadocs/) | yes | yes | Bundled with Liferay | Use `compileOnly` scope |
| [Liferay Connector to Elasticsearch 7](https://web.liferay.com/marketplace/-/mp/application/170390307) | yes | yes | Deployed separately | Use `compileOnly` scope |
| [BndTools](https://bnd.bndtools.org/) [Bnd Lib](https://search.maven.org/search?q=a:biz.aQute.bndlib) | yes | no | Deployed separately | Use `compileOnly` scope |
| [Apachi Shiro](https://shiro.apache.org/) [Shiro Core](https://search.maven.org/search?q=a:shiro-core) | no  | no  | Deployed separately or in the project | Please see [Resolving Third-Party Library Dependencies](./resolving-third-party-library-package-dependencies.md) |

Nice! You know how to specify artifact dependencies. Now that's a skill you can depend on!

## Additional Information

* [Finding Artifacts](./finding-artifacts.md)
* [Importing Packages](../importing-packages.md)
* [Exporting Packages](../exporting-packages.md)
* [Resolving Third Party Library Package Dependencies](./resolving-third-party-library-package-dependencies.md)
* [Deploying WARs \(WAB Generator\)](../../../developing-apps/reference/deploying-wars-wab-generator.md)