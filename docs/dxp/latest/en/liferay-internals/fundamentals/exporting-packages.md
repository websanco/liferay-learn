# Exporting Packages

In OSGi, packages are private by default. You must explicitly exporting a package so other modules can [import](./importing-packages.md) and use them.

Here's how to export packages:

1. Open your `bnd.bnd` file.

1. Add the `Export-Package:` header if you haven't already added it.

1. List the package names after the `Export-Package:` header.

    ```groovy
    Export-Package: com.liferay.petra.io,com.liferay.petra.io.unsync
    ```

[Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)-based projects created using [Blade CLI](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md) or [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md) have [Bnd](http://bnd.bndtools.org/). Bnd propagates OSGi headers from the `bnd.bnd` file to the module JAR `META-INF/MANIFEST.MF` file.

```{important}
Don't export the same package in multiple JARs. Exporting the same package from different modules leads to "split package" issues with unpredictable side effects.
```

```{note}
Bnd makes a module's exported packages *substitutable*. That is, Bnd substitutes your module's exported package with a compatible package of the same name, but potentially different version, that's exported from a different module. Bnd enables this by automatically making your module import every package it exports. In this way, the module can work on its own, but can also work in conjunction with modules that provide a different (compatible) version, or even the same version, of the package. A package from another module might provide better "wiring" opportunities with other modules. [Peter Kriens' blog post](http://blog.osgi.org/2007/04/importance-of-exporting-nd-importing.html) explains substitutable exports.
```

Now you can share your module's terrific packages!

## Additional Information

* [Configuring Dependencies](./configuring-dependencies.md)
* [Semantic Versioning](./semantic-versioning.md)
* [Liferay Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)
* [Blade CLI](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)
* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)