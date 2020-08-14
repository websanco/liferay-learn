# Configuring Dependencies

Liferay's modular environment lets modules provide and consume capabilities via Java packages. Leveraging packages from other modules or traditional libraries, requires configuring them as dependencies. Here you'll learn how to find artifacts (modules or libraries) and configure dependencies on them.

* [Finding Artifacts](./finding-artifacts.md) explains how to use the Application Manager, Gogo Shell, and Liferay reference documentation to find artifacts deployed on Liferay and available in repositories.

* [Specifying Dependencies](./specifying-dependencies.md) demonstrates specifying artifacts to module projects in Liferay Workspace. It shows you how to determine if Liferay already exports packages from an artifact and how to configure such artifacts as compile-time dependencies.

* [Resolving Third-Party Library Package Dependencies](./resolving-third-party-library-package-dependencies.md) provides a workflow for using packages that are only available in traditional library JARs (JARs that aren't OSGi modules). Transitive dependencies are minimized so you can resolve dependencies quicker and prevent bloating your project with unnecessary JARs.

Your first step is to find the artifacts you need.