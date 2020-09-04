# Exporting Packages

An OSGi module's Java packages are private by default. To expose a package, you must explicitly export it. This way you share only the classes you want to share. Exported packages are available for other modules to [import](./importing-packages.md) and use.

To export a package in your module, open your `bnd.bnd` file, add an `Export-Package:` OSGi header to it if one doesn't exist, and add the package `Export-Package` header's list of packages. A header exporting `com.liferay.petra.io` and `com.liferay.petra.io.unsync` would look like this:

```groovy
Export-Package: com.liferay.petra.io,com.liferay.petra.io.unsync
```

[Workspace](../../developing-applications/tooling/liferay-workspace.md)-based projects created using [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md) or [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md) use [Bnd](http://bnd.bndtools.org/). On building such a project's module JAR, Bnd propagates the OSGi headers from the project's `bnd.bnd` file to the JAR's `META-INF/MANIFEST.MF`.

```important::
   Don't export the same package from different JARs. Multiple exports of the same package leads to "split package" issues with side affects differ from case to case.
```

```note::
   Bnd makes a module's exported packages *substitutable*. That is, the OSGi framework can substitute your module's exported package with a compatible package of the same name, but potentially different version, that's exported from a different module. Bnd enables this for your module by automatically making it import every package it exports. In this way, the module can work on its own, but can also work in conjunction with modules that provide a different (compatible) version, or even the same version, of the package. A package from another module might provide better "wiring" opportunities with other modules. `Peter Kriens' blog post <http://blog.osgi.org/2007/04/importance-of-exporting-nd-importing.html>`_ provides more details on how substitutable exports works.
```

Now you can share your module's terrific packages with other modules!

## Additional Information

* [Configuring Dependencies](./configuring-dependencies/configuring-dependencies.md)
* [Semantic Versioning](./semantic-versioning.md)
* [Liferay Workspace](../../developing-applications/tooling/liferay-workspace.md)
* [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)
* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)