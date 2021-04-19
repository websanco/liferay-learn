# Aggregating Resource Bundles

When working with a module that shares localized messages, the bnd header must specify the resource bundles you want to associate with the module. Liferay provides a bnd instruction that automatically specifies the necessary parameters for aggregating the resource bundles.

1. Open the `bnd.bnd` file.

1. Add the `-liferay-aggregate-resource-bundles:` bnd instruction and assign it the bundle symbolic names of modules whose resource bundles you want to aggregate with the current module: 

    ```properties
    -liferay-aggregate-resource-bundles: \
        [bundle.symbolic.name1],\
        [bundle.symbolic.name2]
    ```

    For example, a module that uses resource bundles from modules `com.acme.able.impl` and `com.acme.baker.impl` would be configured like this:

    ```properties
    -liferay-aggregate-resource-bundles: \
        com.acme.able.impl,\
        com.acme.baker.impl
    ```

The current module's resource bundle is prioritized over those of the listed modules. 

At build time, Liferay's bnd plugin converts the bnd instruction to `Require-Capability` and `Provide-Capability` parameters automatically. 

```note::
   To aggregate resources with Liferay's native modules you must specify the parameters manually. 
```

## Reference

The resulting OSGi header can be found in the `MANIFEST.MF` file of your jar file. For example, if your module's symbolic name is `com.acme.web` and you want to use the resource bundles from `com.acme.able.impl` and `com.acme.baker.impl`, The `Require-Capability` would be

```properties
Require-Capability: liferay.resource.bundle;filter:="(bundle.symbolic.name=com.acme.able.impl)",liferay.resource.bundle;filter:="(bundle.symbolic.name=com.acme.baker.impl)"
```

The `Provide-Capability` would be

```properties
Provide-Capability:  liferay.resource.bundle;resource.bundle.base.name="content.Language"\
liferay.resource.bundle;resource.bundle.aggregate="(bundle.symbolic.name=com.acme.web),(bundle.symbolic.name=com.acme.able.impl),(bundle.symbolic.name=com.acme.baker.impl)";\
bundle.symbolic.name="com.acme.web";resource.bundle.base.name="content.Language";\
service.ranking=1,;servlet.context.name="com.acme.web"
```

The example `Provide-Capability` header has two parts: 

1. `liferay.resource.bundle;resource.bundle.base.name="content.Language"` declares that the module provides a resource bundle whose base name is `content.language`. 

1. The `liferay.resource.bundle;resource.bundle.aggregate=...` directive specifies the list of bundles whose resource bundles are aggregated, the target bundle, the target bundle's resource bundle name, and this service's ranking:

    * `"(bundle.symbolic.name=com.acme.web),(bundle.symbolic.name=com.acme.able.impl),(bundle.symbolic.name=com.acme.baker.impl)"`: The service aggregates resource bundles from bundles `bundle.symbolic.name=com.acme.web` (the current module) with `bundle.symbolic.name=com.acme.able.impl` and `bundle.symbolic.name=com.acme.baker.impl`.

    Aggregate as many bundles as desired. Listed bundles are prioritized in descending order. 

    * `bundle.symbolic.name=com.acme.web;resource.bundle.base.name="content.Language"`: Override the `com.acme.web` bundle's resource bundle named `content.Language`.

    * `service.ranking=1`: The resource bundle's service ranking is `1`. The OSGi framework applies this service if it outranks all other resource bundle services that target `com.acme.web`'s `content.Language` resource bundle. 

    * `servlet.context.name=com.acme.web`: The target resource bundle is in servlet context `com.acme.web`.
