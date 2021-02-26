# Updating a Commerce Enterprise License

Commerce Enterprise licenses are only valid for a set period of time based on the terms of a user's subscription. When a license is near to expiration (i.e., < 30 days for most licenses, < 7 days for a 30 day license), a warning message is displayed in Commerce applications for Administrators. A license has a 2 day grace period after its given expiration date before it expires.

If a license expires, the Commerce modules remain activated, though it is no longer usable in the UI and does not allow API calls until the license is updated. A notification is displayed on Commerce applications for all users, indicating the Commerce application is unavailable. Administrators are asked to update the license, while other users are asked to contact their administrator.

The console also display an error message during server restart:

   ```log
   ERROR [main][LicenseManager:?] Liferay Commerce license is expired
   ```

In order to reactivate Liferay Commerce Enterprise, remove any expired keys from the server, and deploy the new key to the `liferay.home/deploy` folder. <!--If the expired licenses are not removed,-->

* [Updating the Commerce License in a Bundle](#updating-the-commerce-license-in-a-bundle)
* [Updating the Commerce License in a Docker Container](#updating-the-commerce-license-in-a-docker-container)
* [Updating the Commerce License in a DXP Cloud Project](#updating-the-commerce-license-in-a-dxp-cloud-project)

## Updating the Commerce License in a Bundle

Follow these steps to update your Liferay Commerce license:

1. Navigate to the following folders, and remove any expired licenses activation keys:

    * `${liferay.home}/data/license`
    * `${liferay.home}/osgi/modules`
    
    ```warning::
       While removing expired licenses, be careful **not** to delete any valid ones.
    ```

1. Deploy your new activation key to the [`${liferay.home}/deploy`](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html) folder.

    ```tip::
      You do not have to shut down the application server to deploy or remove activation keys.
    ```

1. Verify your new activation key has successfully deployed via the console.

## Updating the Commerce License in a Docker Container

<!--## Updating the Commerce License in a DXP Cloud Project-->

## Additional Information

* [Installation Overview](./installation-overview.md)
* [Activating Liferay Commerce Enterprise](./activating-liferay-commerce-enterprise.md)
* [Upgrading Liferay Commerce](./upgrading-liferay-commerce.md)
