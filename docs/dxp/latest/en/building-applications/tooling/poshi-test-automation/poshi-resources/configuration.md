# Configuration

In order to give test writers easy access to commonly used functions, paths, or macros, Poshi resources were created to store existing Poshi files as dependencies. These resources can be loaded in a Poshi project as a Gradle dependency through the Poshi Runner Resources Gradle Plugin and can be used apart from the local Poshi files on the file system.

By default, any Poshi project using Poshi Standalone or the Liferay Gradle Plugins Defaults plugin will automatically have access to the default Poshi resources jar, ```com.liferay:com.liferay.poshi.runner.resources:latest.version```. This published ```com.liferay:com.liferay.poshi.runner.resources``` jar contains Poshi files from the [Liferay poshi-runner-resources directory](https://github.com/liferay/liferay-portal/tree/master/modules/test/poshi/poshi-runner-resources/src/main/resources/default) and is available through standard maven repositories [Maven Central](https://search.maven.org/artifact/com.liferay/com.liferay.poshi.runner.resources) and  [Liferay Nexus](https://repository.liferay.com/nexus/#nexus-search;gav~com.liferay~com.liferay.poshi.runner.resources).

To use the jar file, add the following to the Poshi project's build.gradle file, where the variables ``GROUP``, ``NAME``, and ``VERSION`` refer to the [Maven dependency](https://search.maven.org/artifact/com.liferay/com.liferay.poshi.runner.resources) plugin.:
```
dependencies {
	poshiRunnerResources group: "GROUP", name: "NAME", version: "VERSION"
}
```

To use the default Poshi resources jar file, replace the ``GROUP``, ``NAME``, and ``VERSION`` with the following information:
```
dependencies {
	poshiRunnerResources group: "com.liferay", name: "com.liferay.poshi.runner.resources", version: "latest.release"
}
```

To use a specific version of the Poshi resources jar, replace the ``GROUP``, ``NAME``, and ``VERSION`` similar to the following:
```
dependencies {
poshiRunnerResources group: "com.liferay.poshi.runner.resources", name: "portal-master", version: "20220413-38b5985"
}
````

For more information on configuring Poshi resources, see [Poshi Runner Resources Gradle Plugin](https://github.com/liferay/liferay-portal/blob/master/modules/sdk/gradle-plugins-poshi-runner/README.markdown#poshi-runner-resources-gradle-plugin).
