# Applying a Hotfix

> Subscribers

A Hotfix is a fix or collection of fixes delivered between Updates (or between Fix Packs for DXP 7.3 and earlier) to address a customer's confirmed critical DXP issues. You can request at Hotfix via a [Help Center ticket](https://help.liferay.com/hc). After receiving a Hotfix, you can install it using the Patching Tool.

```{warning}
**Always** [back up](./backing-up.md) your database and installation before applying a Hotfix.
```

```{note}
If you're running DXP in a Docker container, please apply your Hotfix by following the instructions at [Patching DXP in Docker](../installing-liferay/using-liferay-docker-images/patching-dxp-in-docker.md).
```

## Requesting a Hotfix

Here's how to request a Hotfix:

1. Go to the [Help Center](https://help.liferay.com/hc)
1. Submit a ticket.

The Liferay Support Team works with you to determine if the issue is with the product, and provides a fix for any unintended product behavior.

## Configuring the Patching Tool

The Patching Tool applies Hotfixes. Here's how to configure it:

1. If you deployed DXP as a `.war` file on your application server, unzip the `.war` to a temporary location for applying the Hotfix.
1. Locate the `patching-tool.sh` Patching Tool script in your [Liferay Home](../reference/liferay-home.md) or install the Patching Tool if the DXP installation doesn't have it.

    * **DXP Tomcat Bundle:** `[Liferay Home]/patching-tool/`
    * **DXP app server installation:** [Install the Patching Tool](./reference/installing-the-patching-tool.md) on the server host.

1. Configure the Patching Tool for the DXP installation.

    **DXP Tomcat Bundle:** Configure the tool automatically be running the [`auto-discovery` command](./reference/configuring-the-patching-tool.md).
    
    ```bash
    ./patching-tool.sh auto-discovery
    ```
    
    **DXP app server installation:** [Configure the Patching Tool manually](./reference/configuring-the-patching-tool.md), including setting the Patching Tool's `war.path` property (e.g., in `default.properties`) to your unzipped DXP `.war` location.

## Installing a Hotfix

1. Download the patch from your [Help Center](https://help.liferay.com/hc) ticket to your `patching-tool/patches` folder---don't unzip the patch.
1. Shut down your application server.

    Reasons:

    * On Unix-style systems, you can usually replace files that are running, but the old ones reside in memory.
    * On Windows systems, files in use are locked and can't be patched.

1. Install the patch by running the Patching Tool's `install` command from the `patching-tool` folder:

    ```bash
    cd patching-tool
    ./patching-tool.sh install
    ```

1. Verify that the patch installed by executing the `info` command and checking the information on the currently installed patches:

    ```bash
    ./patching-tool.sh info
    ```

1. Clean up all DXP cache.

    Delete the `[Liferay Home]/osgi/state` folder.

    ```bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    Empty the `[Liferay Home]/work` folder.

    ```bash
    rm -rf work/*
    ```

    Delete the application server cache. Please consult the application server vendor's documentation on where where to find the cache.

    ```{note}
    If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup ensures that such modules reinstall with the appropriate state.
    ```

1. If you are installing DXP back onto an application server, ZIP the patched DXP application from its [temporary location](#preparing-to-patch-dxp-on-an-application-server) back into a `.war` file and copy the file into your application server. Refer to the [DXP installation instructions](../installing-liferay/installing-liferay-on-an-application-server.md) for your application server.
1. Start the application server again.

Congratulations! You applied a Hotfix to your DXP installation.

## Additional Information

* [Patching DXP in Docker](../installing-liferay/using-liferay-docker-images/patching-dxp-in-docker.md)
* [Getting Patch Information](./reference/getting-patch-information.md)
* [Uninstalling Patches](./reference/uninstalling-patches.md)