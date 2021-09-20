# Semantic Versioning

[Semantic Versioning](https://semver.org) is a three tiered versioning system for incrementing version numbers based on the degree of API change made in a releasable software component. It's a standard for communicating programmatic compatibility of a package or module for dependent consumers and API implementations. If a package is programmatically (i.e., semantically) incompatible with a project, [Bnd](http://bnd.bndtools.org) (used when building [Liferay generated module projects](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)) fails that project's build immediately.

The semantic version format looks like this:

```
MAJOR.MINOR.MICRO
```

Certain events force each tier to increment:

* *MAJOR:* an incompatible, API-breaking change is made
* *MINOR:* a change that affects only providers of the API, or new backwards-compatible functionality is added
* *MICRO:* a backwards-compatible bug fix is made

For more details on semantic versioning, see the official [Semantic Versioning](https://semver.org/) site and [OSGi Alliance's Semantic Versioning](http://www.osgi.org/wp-content/uploads/SemanticVersioning1.pdf) technical whitepaper.

All of Liferay's modules use Semantic Versioning.

Following Semantic Versioning is especially important because DXP is a modular platform containing hundreds of independent OSGi modules. With many independent modules that depend on each other, releasing new package versions can become terrifying without a way of declaring their compatibility. Semantic Versioning's straightforward system and [Liferay tooling](../../developing-applications/tooling/developer-tools-overview.md) help you mange the compatibility of your modules.

## Baselining Your Project

Following Semantic Versioning manually seems deceptively easy. There's a sad history of good-intentioned developers updating their projects' semantic versions manually, only to find out later they made a mistake. The truth is, it's hard to anticipate the ramifications of a simple update. To avoid this, you can *baseline* your project after it has been updated. This verifies that your project obeys the Semantic Versioning rules, and can catch API changes that are not always obvious to humans.
You can use Liferay's Baseline Gradle plugin to provide baselining capabilities. Add it to your Gradle build configuration and execute the following command:

```bash
./gradlew baseline
```

See [Baseline Gradle Plugin](../../developing-applications/tooling/other-tools/gradle-plugins.md) for configuration details. This plugin is not provided in [Liferay Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md) by default.

When you run the `baseline` command, the plugin compares the public exported API of your new module with the latest released non-snapshot module. If there are any changes, it uses the OSGi Semantic Versioning rules to calculate the minimum new version. If your new module has a lower version, it throws errors.

Don't rely solely on the tool. It's not smart enough to identify compatibility changes not represented in the signatures of Java classes or interfaces, or in API *use* changes (i.e., assumptions about method call order, or changes to input and/or output encoding). Baseline, as the name implies, does give you a certain measure of *baseline* comfort that a large class of compatibility issues won't sneak past you.

With baselining, your project's Semantic Versioning is as accurate as its API expresses.

## Managing Artifact and Dependency Versions

There are two ways to track your project's artifact and dependency versions with Semantic Versioning:

* Range of versions
* Exact version (one-to-one)

You should track a range of versions if you intend to build your project for multiple DXP versions and maintain maximum compatibility. In other words, if several package versions work for an app, you can configure the app to use any of them. What's more, Bnd automatically determines the semantically compatible range of each package a module depends on and records the range to the module's manifest.

For help with version range syntax, see the [OSGi Specifications](https://osgi.org/specification/osgi.core/7.0.0/framework.module.html#i3189032).

A version range for imported packages in an OSGi bundle's `bnd.bnd` looks like this:

```properties
Import-Package: com.liferay.docs.test; version="[1.0.0,2.0.0)"
```

Popular build tools also follow this syntax. In Gradle, a version range for a dependency looks like this:

```groovy
compile group: "com.liferay.portal", name: "com.liferay.portal.test", version: "[1.0.0,2.0.0)"
```

Specifying the latest release version can also be considered a range of versions with no upper limit. Here's how to specify it in Gradle:

```properties
version: "latest.release"`
```

Tracking a range of versions comes with a price. It's hard to reproduce old builds when you're debugging an issue. It also comes with the risk of differing behaviors depending on the version used. Also, relying on the latest release could break compatibility with your project if a major change is introduced. Proceed with caution when specifying a range of versions, and test your project on all included versions.

Tracking a dependency's exact version is safer, but less flexible. You could be limited to a specific DXP version or locked into APIs that exist only in that specific version. Your module, however, is much easier to test and has less chance for unexpected failures.

```{note}
When specifying package versions in your `bnd.bnd` file, exact versions are typically specified like this: `version="1.1.2"`. However, this syntax is technically a range; it is interpreted as [1.1.2, ∞). Therefore, if a higher version of the package is available, it's used instead of the version you specified. For these cases, it may be better to specify a version range for compatible versions that have been tested. If you want to specify a true exact match, the syntax is like this: `[1.1.2]`. See the [Version Range](https://osgi.org/specification/osgi.core/7.0.0/framework.module.html#i3189032) section in the OSGi specifications for more info.

Gradle uses exact versions when only one version is specified.
```

You now know the pros and cons for tracking dependencies as a range and as an exact match.

## Additional Information

* [Importing Packages](./importing-packages.md)
* [Exporting Packages](./exporting-packages.md)
* [Configuring Dependencies](./configuring-dependencies.md)
