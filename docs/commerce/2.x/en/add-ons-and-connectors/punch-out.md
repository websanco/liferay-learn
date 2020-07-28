# Punch Out

> Subscribers

Liferay Commerce offers the additional _Punch Out_ connector, a "mechanism which makes it possible for a buyer to access a supplier's website from the buyer's own procurement application" ([PunchOut2Go](https://www.punchout2go.com/frequently-asked-questions/)).

![Punch Out Flow Diagram](./punch-out/images/01.png)

This connector is available for subscribers with a Commerce subscription _and_ have purchased the additional `LPKG`. The Connector is available for download exclusively on [Help Center](http://customer.liferay.com/downloads).

## Deploy the Connector

1. Download the _Punch Out_ `LPKG` into the `${liferay.home}/deploy` folder. See the [Liferay Home](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html) article to learn more.
1. Verify that the following message displays in the application server console:

    ```
    2020-07-24 22:10:01.924 INFO  [fileinstall-/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:209] The portal instance needs to be restarted to complete the installation of file:/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20Connector%20to%20PunchOut2Go%20-%20API.lpkg
    2020-07-24 22:10:01.926 INFO  [fileinstall-../../liferay-portal-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:209] The portal instance needs to be restarted to complete the installation of file:/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20Connector%20to%20PunchOut2Go%20-%20Impl.lpkg`
    ```

1. Shut down the application server completely.
1. Start the application server.

<!-- 1. Verify that the following message displays in the application server console:

    ```
     [Success message]
    ```
-->

The connector has been deployed.

## Configure the Connector

The Punch Out connector is configured on a Commerce Channel. To learn more about Channels, see [Introduction to Channels](../managing-a-catalog/creating-and-managing-products/channels/introduction-to-channels.md).

Users must have the URL of the store's catalog page; in this example: `http://localhost:8080/web/everest.com/catalog`.

1. Navigate to the _Control Panel_ &rarr; _Commerce_ &rarr; _Channels_.
1. Click on the desired channel.
1. Click the _Punch Out_ tab.
1. Check the _Enabled_ checkbox.
1. Enter the Punch Out Start URL.
1. Click _Save_ when finished.

### Enable Auto Login Punch Out Access Token

Users must enable the Punch Out Access Token for the buyer's procurement system to gain access to the Liferay Commerce instance. Otherwise, the buyer's procurement will not be able to log into the Liferay Commerce instance. To enable the token:

1. Navigate to the _Control Panel_ &rarr; _Configuration_ &rarr; _System Settings_.
1. Click _API Authentication_ under _Security_.

    ![Authentication](./punch-out/images/02.png)

1. Click _Auto Login Punch Out Access Token_ in the left menu.
1. Check the _Enabled_ checkbox.

    ![Enable the Auto Login Access Token.](./punch-out/images/03.jpg)

1. Click the _Update_ button.

The Auto Login _Punch Out_ Token has been enabled and the buyer's procurement system can now log in.

### Punch Out Access Token Provider Configuration

Once the Punch Out Access Token has been enabled, users can configure the Punch Out Access Token Provider. In particular, they can set how long the token lasts and the size of the token. Follow the steps below:

1. Navigate to the _Control Panel_ &rarr; _Configuration_ &rarr; _System Settings_.
1. Click _OAuth2_ under _Security_.
1. Enter the following:

    * **Access Token Duration**:
    * **Access Token Size**:

    ![Enable the Auto Login Access Token.](./punch-out/images/04.jpg)

1. Click _Save_ when finished.

The Auto Login _Punch Out_ Token has been configured.

## Create a Punch Out Buyer Role

As best practice, users should create a Role for vendors using Punch Out. To learn more about Commerce Roles, see [Account Roles](../account-management/account-roles.md) and [Creating a Custom Account Role](../account-management/creating-a-custom-account-role.md). For more information about Roles and Permissions in DXP, see [Understanding Roles and Permissions](https://learn.liferay.com/dxp/7.x/en/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html)

1. Navigate to the _Control Panel_ → _Users_ → _Roles_.
1. Click the _Site Roles_ tab.
1. Click the _Add Site Role_ button.
1. Enter the following:
    * **Name**: Punch Out Vendor.
1. Click _Save_.
1. Click _Define Permissions_.
1. Expand the _Site Administration_ in the left menu.
1. Navigate to _Applications_ &rarr; _Open Carts_.
1. Check the following boxes (at the minimum):

    * **Check Out Open Orders**
    * **View Open Orders**

    ![Punch Out Role Permissions](./punch-out/images/05.png)

1. Click _Save_ when finished.

The Role has been created with the required minimum permissions. Assign this Role to the buyers who are using Punch Out.

## Verify Redirect to PunchOut2Go

Buyers who are using the Punch Out connector to Liferay Commerce checkout items the same way. However, once, they click _Submit_, they are redirected to PunchOut2Go.

![Punch out redirects once an order has been submitted.](./punch-out/images/06.jpg)

## Additional Information

* [Deploying Liferay Commerce to an Existing Liferay Installation](../installation-and-upgrades/deploying-liferay-commerce-to-an-existing-liferay-installation.md)
* [Activating Liferay Commerce Enterprise](../installation-and-upgrades/activating-liferay-commerce-enterprise.md)
* [Punch Out Reference Guide](./punch-out-reference-guide.md)
