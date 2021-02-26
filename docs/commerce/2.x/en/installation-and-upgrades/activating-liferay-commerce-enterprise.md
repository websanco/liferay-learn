# Activating Liferay Commerce Enterprise

> Subscription Required

Liferay Commerce Enterprise is built on Liferay DXP and requires active DXP and Commerce licenses for use. Licenses are provided as XML (`.xml`) activation keys that must be copied to your DXP instance's `deploy` folder to activate Commerce Enterprise features. If you've already [purchased](https://www.liferay.com/contact-sales) a Commerce subscription, then you can obtain your activation key as a download in one of the following ways:

* Open a [Help Center](https://liferay-support.zendesk.com/agent/) ticket with the *Activation Key/Project Administration* component.

* Send a request by email to your regional Provisioning team (i.e., provisioning-[region]@liferay.com).

* Download a Commerce developer key from [here](https://customer.liferay.com/en_US/activation-key).

Commerce licenses use many of the same parameters as DXP licenses, including `product-version`, `license-type`, and `expiration-date`. Restrictions based on System resources (e.g., processor cores) or product version are not implemented in Commerce licenses.

```important::
   In order to activate Commerce Enterprise, both the DXP and Commerce activation keys must be of the same `license-type` (i.e., Production, Developer, or Enterprise). A warning is thrown in the server startup log if the license types do not match.
   
   *Production* licenses also require a matching hostname, matching IP address or matching mac address for validation.
```

Once you've acquired your XML activation key, copy it to the `${liferay.home}/deploy` folder for your DXP instance. While processing the activation key, Liferay relocates it to the `${liferay.home}/osgi/modules` folder and generates a license file (`.li`) in the `${liferay.home}/data/license` folder.

* [Deploying to a Bundle](#deploying-to-a-bundle)
* [Deploying to a Docker Container](#deploying-to-a-docker-container)
* [Deploying to DXP Cloud](#deploying-to-dxp-cloud)

## Deploying to a Bundle

Follow these steps to deploy a Commerce activation key to your DXP bundle:

1. Download your provisioned `.xml` key.

1. Copy your key to the [`${liferay.home}/deploy`](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html) folder for your DXP instance. If the instance is already running, the key is processed and stored in the `${liferay.home}/osgi/modules` folder, and Liferay generates a license file in the `${liferay.home}/data/license` folder. Otherwise, the activation key is deployed and processed during the next startup.

1. Verify your key has successfully deployed to your bundle via the console:

   ```log
   INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:271] Processing activation-key-commercesubscriptiondevelopment-1-developeractivationkeys.xml
   ...
   INFO  [fileinstall-directory-watcher][LicenseManager:?] Commerce Subscription Development license validation passed
   INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for Commerce Subscription Development
   ```

## Deploying to a Docker Container

You can deploy your Commerce activation key to a Docker Container using bind mounts, volumes, or the `docker cp` command. See [Providing Files to a Container](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/installing-liferay/using-liferay-docker-images/providing-files-to-the-container.html?highlight=opt) to learn more.

Follow these steps to deploy the key to a Docker container using the `docker cp` command:

1. Download your provisioned `.xml` key.

1. Copy your key to the `/opt/liferay/deploy` folder in your container using the following syntax:

   ```bash
   docker cp [key-name.xml] [container-name]:/opt/liferay/deploy
   ```

   If the instance is already running, the key is processed and stored in the `opt/liferay/osgi/modules` folder, and Liferay generates a license file in the `opt/liferay/data/license` folder. Otherwise, the activation key is deployed and processed during the next startup.

1. Verify your key has successfully deployed to your container via the console:

   ```log
   INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:271] Processing activation-key-commercesubscriptiondevelopment-1-developeractivationkeys.xml
   ...
   INFO  [fileinstall-directory-watcher][LicenseManager:?] Commerce Subscription Development license validation passed
   INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for Commerce Subscription Development
   ```

## Deploying to DXP Cloud

If you've purchased Commerce through a DXP Cloud contract, then the DXP Cloud team manages your activation key through the cloud infrastructure, and there is no need for you to manually deploy<!--or manage?--> the key for yourself. However, if you've added Commerce to an existing DXP Cloud project without going through the DXP Cloud team, then you'll need to manually deploy<!--and manage?--> the licence yourself.

Follow the steps to deploy a Commerce activation key to your Liferay service:

1. Download your provisioned `.xml` key.

1. Copy your key to the [`liferay/configs/{ENV}/deploy/`](https://learn.liferay.com/dxp-cloud/latest/en/using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.html#licenses) folder in your Project's central Git repository, and commit your changes.

   ```note::
      If you're using version 3.x.x services, then licenses belong in the ``lcp/liferay/license/{ENV}/`` folder of your repository.
   ```

1. Build and deploy your Liferay service using either Jenkins or the CLI tool. See [Overview of the DXP Cloud Deployment Workflow](https://learn.liferay.com/dxp-cloud/latest/en/build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.html) for more information.

   When processed, the key is relocated to the `opt/liferay/osgi/modules` folder, and Liferay generates a corresponding license file in the `opt/liferay/data/license` folder.

1. Verify your key has successfully deployed to your Project's Liferay service via the service logs:

   ```log
   INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:271] Processing activation-key-commercesubscriptiondevelopment-1-developeractivationkeys.xml
   ...
   INFO  [fileinstall-directory-watcher][LicenseManager:?] Commerce Subscription Development license validation passed
   INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for Commerce Subscription Development
   ```

## Additional Information

* [Activating a Marketplace App Through a Proxy Server](https://help.liferay.com/hc/en-us/articles/360018427391)
