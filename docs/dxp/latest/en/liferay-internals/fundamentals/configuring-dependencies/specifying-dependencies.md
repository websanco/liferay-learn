# Specifying Dependencies

You must satisfy all dependencies to compile and deploy a module successfully. After you [find the dependency artifacts](./finding-artifacts.md), add them as dependencies in your Gradle build file. Liferay already includes many artifacts at run time. If you depend on other artifacts, you must deploy them manually or include them in your module. Here you'll find dependency configuration steps and examples.

## Configuring Dependencies

1. Open your `build.Gradle` file.

1. [Declare a `dependencies` set](https://docs.gradle.org/current/userguide/declaring_dependencies.html), if one doesn't already exist.

    ```groovy
    dependencies {
    }
    ```

1. If your module requires packages from Liferay, OSGi, or Bnd, add a `compileOnly` dependency on the Liferay Portal API.

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.portal", name: "release.portal.api"
    }
    ```

1. If your module doesn't compile or deploy, find an artifact that provides the packages you need and add a `compileInclude` dependency on the artifact.

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.portal", name: "release.portal.api",
        compileInclude group: 'com.google.guava', name: 'guava', version: '19.0'
    }
    ```

1. Deploy your module and check for unsatisfied package dependencies by using [Gogo Shell commands](../using-the-gogo-shell.md) or browsing the logs.

1. If you have unsatisfied dependencies, resolve them:

    **For module dependencies,** deploy the needed modules. Please see [Installing and Managing Apps](../../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md) for more information.

    **For library dependencies,** follow the instructions at [Resolving Third Party Library Dependencies](./resolving-third-party-library-package-dependencies.md).

Nice! Specifying dependencies is a skill you can depend on!

## Additional Information

* [Finding Artifacts](./finding-artifacts.md)
* [Importing Packages](../importing-packages.md)
* [Exporting Packages](../exporting-packages.md)
* [Resolving Third Party Library Package Dependencies](./resolving-third-party-library-package-dependencies.md)
* [Deploying WARs \(WAB Generator\)](../../../developing-applications/reference/deploying-wars-wab-generator.md)