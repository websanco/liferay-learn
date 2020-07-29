# Configuring Dependencies

Liferay's modular environment lets modules provide and consume capabilities via Java packages. To leverage packages from other modules or traditional libraries in your project, you must configure them as dependencies. Here you'll learn how to find artifacts (modules or libraries) and configure dependencies on them.

* [Finding Artifacts](./finding-artifacts.md) explains how to use the Application Manager, Gogo Shell, and Liferay DXP reference documentation to find artifacts deployed on Liferay DXP and available in repositories.

* [Specifying Dependencies](./specifying-dependencies.md) demonstrates specifying artifacts to Maven and Gradle build frameworks. It shows you how to determine whether Liferay DXP already exports packages from an artifact and how to configure such artifacts as compile-time dependencies.

* [Resolving Third-Party Library Package Dependencies](./resolving-third-party-library-package-dependencies.md) provides a workflow for using packages that are only available in traditional library JARs (JARs that aren't OSGi modules). It involves minimizing transitive dependencies so you can resolve dependencies quicker and prevent bloating your project with unnecessary JARs.

Your first step is to find the artifacts you need.