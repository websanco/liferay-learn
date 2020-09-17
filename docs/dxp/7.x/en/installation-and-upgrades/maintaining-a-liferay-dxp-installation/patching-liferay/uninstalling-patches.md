# Uninstalling Patches

After you install a patch, you may determine that you're not ready for the patch and decide to restore your installation to a previous patch level. Here you'll learn how to:

* Uninstall Patches
* Revert (uninstall) all patches at once.

## Uninstalling a Patch

You can uninstall patches and restore your installation to a desired patch level.

1. Remove the unwanted patch from your `patches` folder.

1. Run the `./patching-tool.sh install` command to restore your installation to the patch level that the remaining patches in the remaining `patches` folder define.

Your DXP installation now has the patch level that your patches specify.

## Reverting All Patches

If you want to remove all or most of your patches, it may be easier to use the Patching Tool's `revert` command. The `revert` command removes ALL patches from the DXP installation, bringing it back to the original installed version (e.g., GA1).

1. Remove all of your patches.

    ```bash
    ./patching-tool.sh revert
    ```

    All of the patches are removed from your `patches` folder.

1. Bring your installation up to a desired patch level, by [installing the applicable patches](./installing-patches.md).

```tip::
   It's helpful to store your patches in a convenient location in case you want to restore your installation to a particular patch level. You can always download fix packs again from the `Customer Portal <https://customer.liferay.com/downloads>`_.
```

## Reverting an installed Hotfix only

You can uninstall your installed hotfix while keeping the Fix Pack installed. (If the Hotfix is installed on the original version (e.g. GA1) then this command will be equivalent to `./patching-tool.sh revert`)

1. Run the `./patching-tool.sh revert -hotfix` command to remove your installed Hotfix.

Your DXP installation now has a Fix Pack installed only.

Now you know how to restore your DXP installation by uninstalling and reverting patches.

## Preventing an unstable state

Patching Tool uses multiple json files to determine how to patch your DXP bundle. Before starting the revert, Patching Tool checks if all necessary data can be accessed in the bundle. If a json file or the backup is missing, the process will stop as it would end up with an unstable DXP bundle.

In case you have accidentally removed these files you can restore them and re-run the revert.

If a json file is missing, you can always restore it from the Fix Pack / Hotfix you have installed. Important - the json files for Hotfixes are prefixed with the Hotfix name - e.g. hotfix-123-7310-renames.json.


## Additional Information

* [Installing Patches](./installing-patches.md)
* [Understanding Patch Types](./understanding-patch-types.md)
