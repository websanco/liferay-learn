# JARs Excluded from WABs

[Liferay-generated web application bundles \(WABs\)](./deploying-wars-wab-generator.md) are stripped of third party JARs that contain [packages that Liferay exports](../../liferay-internals/reference/exported-third-party-packages.md) already. Deploying the same third party packages from additional JARs can cause weird errors that are hard to debug. Liferay's [`module.framework.web.generator.excluded.paths`](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles) [portal property](../../installation-and-upgrades/reference/portal-properties.md) specifies the excluded JARs. Here's an excerpt from the default `module.framework.web.generator.excluded.paths` property:

```properties
module.framework.web.generator.excluded.paths=\
    WEB-INF/lib/ant.jar,\
    WEB-INF/lib/asm-debug-all.jar,\
    WEB-INF/lib/aspectj-rt.jar,\
    WEB-INF/lib/bnd.jar,\
    WEB-INF/lib/ccpp.jar,\
    WEB-INF/lib/commons-beanutils.jar,\
    WEB-INF/lib/commons-chain.jar,\
    ...
```

```note::
    Liferay excludes these JARs from a WAB, even if the WAB lists the JARs in a ``portal-dependency-jars`` property in a `liferay-plugin-package.properties <https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/liferay-plugin-package_7_3_0.properties.html>`_ file.
```

## Including a Different Version of a Third Party Package

If your WAR requires different versions of the [exported third party packages](../../liferay-internals/reference/exported-third-party-packages.md), you can include them in JARs named differently from the excluded JARs.

For example, here's how to include a different version of a Spring Framework package:

1. Determine the [package version that Liferay provides](../../liferay-internals/reference/exported-third-party-packages.md). For example, Liferay exports Spring Framework version 4.1.9 packages via the `com.liferay.portal.bootstrap` module:

    ```
    Export-Package:\
        ...
        org.springframework.*;version='4.1.9',\
        ...
    ```

1. [Find an artifact](../../liferay-internals/fundamentals/configuring-dependencies/finding-artifacts.md) that provides the package version you want.

1. Check the [`module.framework.web.generator.excluded.paths`](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Module%20Framework) portal property for the corresponding artifact that Liferay excludes. For example, Liferay excludes these Spring Framework JARs:

    ```properties
    module.framework.web.generator.excluded.paths=\
        ...
        WEB-INF/lib/spring-aop.jar,\
        WEB-INF/lib/spring-aspects.jar,\
        WEB-INF/lib/spring-beans.jar,\
        WEB-INF/lib/spring-context.jar,\
        WEB-INF/lib/spring-context-support.jar,\
        WEB-INF/lib/spring-core.jar,\
        WEB-INF/lib/spring-expression.jar,\
        WEB-INF/lib/spring-jdbc.jar,\
        WEB-INF/lib/spring-jms.jar,\
        WEB-INF/lib/spring-orm.jar,\
        WEB-INF/lib/spring-oxm.jar,\
        WEB-INF/lib/spring-tx.jar,\
        WEB-INF/lib/spring-web.jar,\
        WEB-INF/lib/spring-webmvc.jar,\
        WEB-INF/lib/spring-webmvc-portlet.jar,\
        ...
    ```

1. If the artifact you need has the same name as an excluded artifact (see the previous step), rename the artifact you need. For example, you can use Spring Framework version 3.0.7's Spring AOP JAR by including it in a project's `WEB-INF/lib` folder but renaming it to something other than `spring-aop.jar` (the excluded JAR).

    ```tip::
       Adding the version to a JAR name (i.e., `spring-aop-3.0.7.RELEASE.jar`) differentiates it from the excluded JAR and prevents it from being stripped from the WAB (the bundled WAR).
    ```

You are now including the version of the JAR your WAB requires.

## Additional Information

* [Exported Third Party Packages](../../liferay-internals/reference/exported-third-party-packages.md)
* [Configuring Dependencies](../../liferay-internals/fundamentals/configuring-dependencies.md)
* [Deploying WARs \(WAB Generator\)](./deploying-wars-wab-generator.md)
