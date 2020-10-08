# Installing the Patching Tool

> Subscribers

Patching DXP on an application server and updating the Patching Tool are done manually.

## Installation

1. Download the Patching Tool from the [Customer Portal](https://customer.liferay.com/downloads).

    * **For DXP 7.3+,** download the latest Patching Tool.
    * **For DXP 7.2,** download the latest version of Patching Tool 2.x.

1. Unzip the Patching Tool to your [Liferay Home](../../reference/liferay-home.md) folder (recommended) or to another folder.

The Patching Tool folder `patching-tool` includes the `patching-tool.sh` script.

To print the Patching Tool help message, execute this command in the `patching-tool` folder:

```bash
cd patching-tool
./patching-tool.sh help
```

## Updating the Patching Tool

The Patching Tool reports when a patch you're installing requires a newer Patching Tool version. Here's how to update the Patching Tool:

1. Download the Patching Tool from the [Customer Portal](https://customer.liferay.com/downloads).

    * **For DXP 7.3+,** download the latest Patching Tool.
    * **For DXP 7.2,** download the latest 2.x version of the Patching Tool.

1. Back up your existing Patching Tool `.properties` files.

1. Overwrite the existing Patching Tool by unzipping the new one to the `patching-tool` folder's parent folder (typically Liferay Home).

The Patching Tool is ready to configure with your DXP installation.

## Additional Information

* [Configuring the Patching Tool](./configuring-the-patching-tool.md)
* [Installing Patches](./installing-patches.md)
* [Custom Code and Patch Compatibility](./advanced-patching-for-dxp-7-2/custom-code-and-patch-compatibility.md)
