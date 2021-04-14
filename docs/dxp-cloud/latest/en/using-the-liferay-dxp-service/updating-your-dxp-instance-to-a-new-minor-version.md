# Updating Your DXP Instance to a New Minor Version

Regularly updating your Liferay DXP installation is an important part of maintaining your DXP Cloud environment. Use the available tags on the [Liferay DXP Docker Hub page](https://hub.docker.com/r/liferay/dxp/tags) to update and deploy your service.

```note::
   Upgrading to a new major version (such as Liferay DXP 7.3) requires a different procedure from a smaller version update. See `Upgrading Your Liferay DXP Instance <./upgrading-your-liferay-dxp-instance.md>`__ for more information.
```

```note::
   If you want to install a `hotfix <https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-dxp-installation/patching-liferay/understanding-patch-types.html#hotfixes>`__, then follow `these steps <./deploying-to-the-liferay-service.md#deploying-hotfixes>`__ instead.
```

## Updating and Deploying a New Version of DXP

Minor version updates to Liferay DXP require a change to your project's repository.

If you are using [clustered services](./setting-up-clustering-in-dxp-cloud.md) and updating to any version that changes the Liferay database schema (such as a [service pack](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-dxp-installation/patching-liferay/understanding-patch-types.html#service-packs)), then follow [these steps](#updating-to-a-new-service-pack-with-clustering-enabled).

Otherwise, perform these steps to update the version of your DXP installation:

1. Find the tag for the version of Liferay you are updating to on [Docker Hub](https://hub.docker.com/r/liferay/dxp/tags).

1. Change the value of the `liferay.workspace.docker.image.liferay` property in [`liferay/gradle.properties`](./introduction-to-the-liferay-dxp-service.md#choosing-a-version):

    ```properties
    liferay.workspace.docker.image.liferay=liferay/dxp:7.3.10-ga1
    ```

1. [Deploy the change](./deploying-to-the-liferay-service.md) to the desired environment's `liferay` service.

Once you have deployed the changes, the `liferay` service restarts and begins any upgrade steps necessary to complete the update.

## Updating to a New Service Pack with Clustering Enabled

If you are updating to a version of Liferay DXP that changes the database schema, then your clustered `liferay` service requires temporary changes during the update procedure to ensure all nodes update correctly.

Follow these steps:

1. Set the `scale` property in your repository's `liferay/LCP.json` file to `1`:

   ```json
   {
       "scale": 1,
   }
   ```

1. [Deploy the change](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md) to the `liferay` service.

1. Find the tag for the version of Liferay you are updating to on [Docker Hub](https://hub.docker.com/r/liferay/dxp/tags).

1. Change the value of the `liferay.workspace.docker.image.liferay` property in `liferay/gradle.properties`:

    ```properties
    liferay.workspace.docker.image.liferay=liferay/dxp:7.3.10-ga1
    ```

1. Set the [deployment strategy](../build-and-deploy/understanding-deployment-strategies.md) to `Recreate` in `liferay/LCP.json`:

    ```json
    {
        "strategy": {
            "type": "Recreate"
        }
    }
    ```

1. [Deploy these changes](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md) to the `liferay` service.

    The Liferay DXP installation on your `liferay` service updates to the new version as it starts up. However, you must still reverse the temporary changes made to your service.

1. Set the `scale` property back to the desired number of nodes in `liferay/LCP.json`:

    ```json
    {
        "scale": 3,
    }
    ```

1. Reset the deployment strategy in `liferay/LCP.json` to its former value (or remove the property if it was only added for the version update).

1. [Deploy the changes](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md) one more time.

The updated `liferay` service starts back up with the desired number of nodes after the final deployment.

## Additional Information

* [Deploying to the Liferay Service](./deploying-to-the-liferay-service.md)
* [Installing Hotfixes](./deploying-to-the-liferay-service.md#deploying-hotfixes)
* [Setting Up Clustering in DXP Cloud](./setting-up-clustering-in-dxp-cloud.md)
* [Upgrading Your Liferay DXP Instance](./upgrading-your-liferay-dxp-instance.md)
