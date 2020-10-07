# Patching Liferay

As Liferay releases fixes, you'll want to keep your DXP instances up to date. Liferay aggregates fixes and makes them available to Enterprise Subscribers in ZIP files called _patches_.

## Patch Types

There are several different kinds of patches, and they serve different purposes.

* **Fix Packs:** Resolve the most recent issues.
* **Security Fix Packs:** Address the latest security issues immediately.
* **Hotfixes:** Requested by customers to fix business critical DXP issues fast.
* **Service Packs:** Incorporate larger fixes that require more testing. Service Pack releases also include full DXP Service Pack Tomcat bundles.

[Patch Types](./understanding-patch-types) explains all of the patch options.

## Installing Patches

Once you have a patch you need, you can use Liferay's Patching Tool to apply it. [Installing Patches](./installing-patches.md) provides the basic steps for patching DXP safely and comprehensively.

## Configuring the Patching Tool

The [`patching-tool.sh auto-discovery` command](./configuring-the-patching-tool.md) configures the tool automatically for Tomcat bundles and common app server configurations. You can also configure the Patching Tool manually to handle DXP installation variations.

## Other Patching Topics

As you start applying patches regularly, you may need to submit patch information in support requests or uninstall patches you no longer need.

* [Getting Patch Information](./getting-patch-information.md)
* [Uninstalling Patches](./uninstalling-patches.md)

## Patching DXP 7.2 

The following topics provide best practices for managing patches in DXP 7.2:

* [Slimming Down Patched Installations](./advanced-patching-for-dxp-7-2/slimming-down-patched-installations.md)
* [Using Slim Bundles](./advanced-patching-for-dxp-7-2/using-slim-bundles.md)
* [Handling Collisions between Patches and Custom Plugins](./advanced-patching-for-dxp-7-2/custom-code-and-patch-compatibility.md)

Now that you've digested the patching overview, learn about the [Patch Types](./understanding-patch-types.md) available to you. Then you'll be ready to apply patches following [Installing Patches](./installing-patches.md).
