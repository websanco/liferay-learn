# Exported Third Party Packages

Liferay provides over one-hundred third party Java packages at run time. The `com.liferay.portal.bootstrap` module exports the packages by specifying individual packages explicitly and groups of packages using globs. For example, here is an excerpt from [7.3.4-ga5](https://github.com/liferay/liferay-portal/blob/7.3.4-ga5/modules/core/portal-bootstrap/system.packages.extra.bnd)'s `Export-Package` declaration:

```groovy
Export-Package:\
    ...
    \
    org.aspectj.*,\
    \
    org.dom4j.*;version='2.1.3',\
    \
    org.hibernate.*;version='3.6.10',\
    \
    org.jaxen.*;version='1.1.6',\
    \
    org.jdom.*;version='1.1.3',\
    \
    org.json.*;version='20180813',\
    \
    org.objectweb.asm;version='7.0',\
    org.objectweb.asm.commons;version='7.0',\
    org.objectweb.asm.signature;version='7.0',\
    org.objectweb.asm.tree;version='7.0',\
    org.objectweb.asm.tree.analysis;version='7.0',\
    org.objectweb.asm.util;version='7.0',\
    \
    org.slf4j;version='1.7.2',\
    org.slf4j.helpers;version='1.7.2',\
    org.slf4j.spi;version='1.7.2',\
    \
    org.springframework.*;version='4.1.9',\
    \
    ...
```

Multiple packages are specified using wild card characters, such as `*` in `org.aspectj.*`. Groups of packages, such as `org.objectweb.asm*` packages and `org.slf4j*` packages are separated by lines that have the `\` character only.

Exporting the same package from different JARs leads to "split package" issues. This can cause problems difficult to define. Therefore, refrain from deploying JARs that have the same packages that Liferay exports.

## Relying on Liferay for Exported Packages at Run Time

Here's how to make sure a project has required packages at compile time but relies on Liferay for its exported packages at run time.

1. Check if packages your project requires are listed in `com.liferay.portal.bootstrap` module's export manifest. There are two ways to check:

    **Bnd Source File:** If you have a copy of the Liferay source code, examine the `modules/core/portal-bootstrap/system.packages.extra.bnd` file's `Export-Package` declaration. It shows the exported packages in the user-friendly format shown above. Liferay generates the `com.liferay.portal.bootstrap` module's `META-INF/system.packages.extra.mf` file based on this `.bnd` file.

    **JAR Manifest:** The `META-INF/system.packages.extra.mf` file in `[Liferay Home]/osgi/core/com.liferay.portal.bootstrap.jar` declares the exported packages. The JAR is conveniently found in the Liferay installation but the manifest file's `Export-Package` declaration format is less user-friendly.

1. If your project uses any of the provided third party packages, add the compile time artifacts as dependencies using the `providedCompile` Gradle scope. Artifacts in the `providedCompile` scope are available at compile time but excluded from the generated JAR.

    For example, if your project uses Spring Bean packages, specify the following artifact dependency in the `providedCompile` scope.

    ```groovy
    dependencies {
        providedCompile group: "org.springframework", name: "spring-bean", version: "4.1.9"
        ...
    }
    ```

Now you can safely leverage Liferay's exported third party packages!

## Additional Information

* [Configuring Dependencies](../fundamentals/configuring-dependencies.md)
* [JARs Excluded from WABs](../../developing-applications/reference/jars-excluded-from-wabs.md)
* [Resolving Third Party Library Package Dependencies](../fundamentals/configuring-dependencies/resolving-third-party-library-package-dependencies.md)
