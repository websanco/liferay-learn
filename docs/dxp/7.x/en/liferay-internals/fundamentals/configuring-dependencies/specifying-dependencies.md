# Specifying Dependencies

Compiling a module and deploying it to Liferay requires satisfying the module's external dependencies. After [finding dependency artifacts](../finding-artifacts.md), you must declare them as dependencies in your Gradle build file. Liferay includes many artifacts whose [packages](../../reference/exported-third-party-packages.md) are available at run time. If you depend on other artifacts, you must deploy them manually. Here you'll find dependency configuration steps and examples.

## Configuring Dependencies

Here's how to configure dependencies:

1. In your Gradle build file (e.g., `build.Gradle`), [declare a `dependencies` set](https://docs.gradle.org/current/userguide/declaring_dependencies.html), if one doesn't already exist.

    ```groovy
    dependencies {
    }
    ```

1. To add Liferay, OSGi, and Bnd dependencies, use the [`compileOnly` configuration](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_plugin_and_dependency_management) and specify these artifact attributes:

    * `group`
    * `name` (artifact ID)
    * `version`

    Here are dependencies for Bnd Lib and Liferay's Journal API.

    ```groovy
    dependencies {
        compileOnly group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.5.0"
        compileOnly group: "com.liferay", name: "com.liferay.journal.api", version: "2.0.2"
        ...
    }
    ```

    ```important::
       Make sure to use artifact versions compatible with your `Liferay product version <./finding-artifacts.md>`_.
    ```

1. For all other dependencies, use the `compileInclude` configuration.

    `compileIndlude` bundles the dependency JAR, and transitive dependency JARs, with your module. `compileInclude` comes with the `com.liferay.plugin` Gradle plugin, available in [Workspace](../../../developing-applications/tooling/liferay-workspace.md) or by applying the plugin to your `build.gradle` file, as shown below:

    ```groovy
    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins", version: "4.0.4"
        }

        repositories {
            maven {
                url "https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/"
            }
        }
    }

    apply plugin: "com.liferay.plugin"
    ```

1. Deploy your module and check for unsatisfied package dependencies, by using [Gogo Shell commands](../using-the-gogo-shell.md) or browsing the logs.

1. If you have unsatisfied dependencies, resolve them:

    **For module dependencies,** deploy such modules. Please see [Installing and Managing Apps](../../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md) for more information.

    **For library dependencies,** follow the instructions at [Resolving Third Party Library Dependencies](./resolving-third-party-library-dependencies.md).

Nice! Specifying dependencies is a skill you can depend on!

## Additional Information

* [Finding Artifacts](./finding-artifacts.md)
* [Importing Packages](../importing-packages.md)
* [Exporting Packages](../exporting-packages.md)
* [Resolving Third Party Library Package Dependencies](./resolving-third-party-library-package-dependencies.md)
* [Deploying WARs \(WAB Generator\)](../../../developing-apps/reference/deploying-wars-wab-generator.md)