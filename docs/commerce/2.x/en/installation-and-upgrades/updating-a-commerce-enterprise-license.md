# Updating a Commerce Enterprise License

Commerce Enterprise licenses are only valid for a set period of time based on the terms of a user's subscription. When a license nears expiration (i.e., < 30 days for most licenses, < 7 days for a 30 day license), a warning message is displayed in Commerce applications for Administrators. A license has a 2 day grace period after its given expiration date before it expires.

If a license expires, the Commerce modules remain activated, though it is no longer usable in the UI and does not allow API calls until the license is updated. A notification is displayed on Commerce applications for all users, indicating the Commerce application is unavailable. Administrators are asked to update the license, while other users are asked to contact their administrator.

The console also display an error message during server restart:

   ```log
   ERROR [main][LicenseManager:?] Liferay Commerce license is expired
   ```

In order to reactivate Liferay Commerce Enterprise, remove any expired keys from the server, and deploy the new key.

   ```tip::
      You can remove and add activation keys while the server is running.
   ```

* [Updating Your License in a Bundle](#updating-your-license-in-a-bundle)
* [Updating Your License in a Docker Container](#updating-your-license-in-a-docker-container)
<!-- * [Updating Your License in a DXP Cloud Project](#updating-your-license-in-a-dxp-cloud-project) -->

## Updating Your License in a Bundle

Follow these steps to update your Liferay Commerce license in a Bundle:

1. Download your new `.xml` activation key.

1. Remove the expired `.xml` key from the `${liferay.home}/osgi/modules` folder, and remove the expired license (`.li`) from the `${liferay.home}/data/license` folder.
    
    ```warning::
       While removing expired licenses, be careful **not** to delete any valid ones.
    ```

1. Add your new activation key to the [`${liferay.home}/deploy`](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html) folder.

1. Verify your key has successfully deployed to your bundle via the console:

   ```log
   INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:271] Processing activation-key-commercesubscriptiondevelopment-1-developeractivationkeys.xml
   ...
   INFO  [fileinstall-directory-watcher][LicenseManager:?] Commerce Subscription Development license validation passed
   INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for Commerce Subscription Development
   ```

## Updating Your License in a Docker Container

Follow these steps to update your Liferay Commerce license in a Docker Container:

1. Download your new `.xml` activation key.

1. Remove the expired `.xml` key from the `opt/liferay/osgi/modules` folder, and remove the expired license (`.li`) from the `opt/liferay/data/licenses` folder.
    
    ```warning::
       While removing expired licenses, be careful **not** to delete any valid ones.
    ```

1. Use the `docker cp` command to add your new activation key to the `/opt/liferay/deploy` folder in your container:

   ```bash
   docker cp [key-name.xml] [container-name]:/opt/liferay/deploy
   ```

1. Verify your key has successfully deployed to your container via the console:

   ```log
   INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:271] Processing activation-key-commercesubscriptiondevelopment-1-developeractivationkeys.xml
   ...
   INFO  [fileinstall-directory-watcher][LicenseManager:?] Commerce Subscription Development license validation passed
   INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for Commerce Subscription Development
   ```

<!--## Updating Your License in a DXP Cloud Project-->

## Additional Information

* [Installation Overview](./installation-overview.md)
* [Activating Liferay Commerce Enterprise](./activating-liferay-commerce-enterprise.md)
* [Upgrading Liferay Commerce](./upgrading-liferay-commerce.md)
