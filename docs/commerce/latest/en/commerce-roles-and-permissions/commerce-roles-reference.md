# Commerce Roles

User roles group together necessary permissions for accessing and using Liferay applications and resources. Each role can be scoped to an instance, Site, Organization, Asset Library, or Account. This scope determines which permissions can be assigned to the role. Each permission also has its own scope, which determines the context in which the applications and resources can be accessed and used. See [Understanding Roles and Permissions](https://learn.liferay.com/dxp/latest/en/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html) for more details.

Commerce includes four out-of-the-box Commerce roles: Account Administrator, Account Member, Buyer, and Order Manager. While these roles may satisfy your business/Commerce needs, other common roles include: Catalog Manager, Inventory Manager, Shipments Manager, Instance Order Manager, and Discount Manager.

Each Commerce role groups together , to address needs in specific use cases.

```note::
   For Liferay 7.3.x and earlier Commerce versions, Sales Agent is also included as an out-of-the-box role.
```

## Viewing Permissions

Follow these steps to view and manage permissions associated with a Commerce role:

1. Open the *Global Menu* (![Global Menu](../../images/icon-applications-menu.png)), click on the *Control Panel* tab, and go to *Users* &rarr; *Roles*.

   ![Click on Roles in the Control Panel.](./commerce-roles-reference/images/01.png)

1. Click on the desired role:

   * *Account Administrator* (Account Role)
   * *Account Member* (Account Role)
   * *Buyer* (Site Role)
   * *Order Manager* (Site Role)

   ```note::
      In Liferay 7.3 and earlier versions, the Account Administrator is a Site Role, and the Account Member role is not a default role. 
   ```

1. Click on the *Define Permissions* tab.

   From here, you can view a summary of all application and resource permissions assigned to the role.

   You can also remove or add permissions.

   ![Click on the Define Permissions tab to view and manage role permissions.](./commerce-roles-reference/images/02.png)

## Account Administrator (Account Role)

> Out-of-the-box

Account Administrators can modify their Account, invite or add users to it, and assign roles to other Account members. Note that Account Administrators cannot create or delete Accounts. This permission is reserved for instance administrators.

The following Accounts permissions pertain to the Account Administrator's ability to modify accounts in a B2B setting. Open Carts permissions pertain to the administrator's ability to modify Channel orders regardless of the store type.

| Permissions | Description |
| --- | --- |
| Accounts > Account: Add Account Entry | <!--FINISH--> |
| Accounts > Account Entry: Manage Users | <!--FINISH--> |
| Accounts > Account Entry: Update | <!--FINISH--> |
| Accounts > Account Entry: View | <!--FINISH--> |
| Accounts > Account Entry: View Users | <!--FINISH--> |
| Accounts > Commerce Account: Manage Addresses | Ability to add a new billing, a shipping, or a combined address |
| Accounts > Commerce Account: Manage Members | Ability to invite a new member or an organization and modify his or her credentials |
| Accounts > Commerce Account: Update | Ability to modify Accounts |
| Accounts > Commerce Account: View | Ability to view Accounts |
| Accounts > Commerce Account: View Addresses | Ability to view all the addresses associated with an Account |
| Accounts > Commerce Account: View Members | Ability to view all members in an Account |
| Open Carts > Commerce Order: Add Order | Ability to add an order to the Buyer's specified account (can have more than one account) |
| Open Carts > Commerce Order: Approve Open Orders | Allow account administrator to approve orders if workflow has been enabled |
| Open Carts > Commerce Order: Check Out Open Orders | Ability to complete the checkout process for new, incomplete orders |
| Open Carts > Commerce Order: Delete Orders | Grants the ability to delete an order |
| Open Carts > Commerce Order: Manage Orders | Change Order details such as mailing and billing addresses; ability to add a note to the order |
| Open Carts > Commerce Order: View Open Orders | Ability to view all open orders |
| Open Carts > Commerce Order: View Orders | Ability to view all orders regardless of their statuses |

## Account Member (Account Role)

> Out-of-the-box for Liferay 7.4+

This role is automatically assigned to all users within an account and grants basic view permissions.

| Permission | Description |
| --- | --- |
| Accounts > Account Entry: View | Ability to view an Account |

## Buyer (Site Role)

> Out-of-the-box

Users assigned this role can view, create, and check out orders.

| Permissions | Description |
| --- | --- |
| Open Carts > Commerce Order: Add Order | Ability to add an order to the Buyer's specified account (can have more than one account) |
| Open Carts > Commerce Order: Checkout Open Orders | Ability to complete the checkout process for new, incomplete orders |
| Open Carts > Commerce Order: View Open Orders | Ability to view all open orders |
| Open Carts > Commerce Order: View Orders | Ability to view all orders regardless of their statuses |

## Catalog Manager

> Custom role

The Catalog Manager role allows users to access, create, edit, and manage Products within a specific Catalog. This role is useful in multiple Catalog scenarios, where different vendors or company teams manage different Products in separate Catalogs. Note that Catalog Managers cannot manage inventory. For this responsibility, see [Inventory Manager](#inventory-manager)

| Permissions | Description |
| --- | --- |
| Catalogs: Access in Control Panel | <!--FINISH--> |
| Catalogs: View | <!--FINISH--> |
| Currencies > Commerce Currencies: Manage Currencies | <!--FINISH--> |
| Portal: View Control Panel Menu | <!--FINISH--> |
| Products: Access in Control Panel | <!--FINISH--> |
| Products: View | <!--FINISH--> |

In addition to the above permissions, you must also assign *View* and *Update* permissions for the individual Catalog you want the role will manage.

To do this, open the *Global Menu* (![Global Menu](../../images/icon-applications-menu.png)), click on the *Commerce* tab, and go to *Product Management* &rarr; *Catalog*. Then, click on the *Actions* button (![Actions Button](../../images/icon-actions.png)) for the desired Catalog, and select *Permissions*. Use the check boxes to assign the role *View* and *Update* permissions.

```note::
   If you'd like your Catalog Manager to manage Products within a Catalog without the ability to modify the Catalog itself, remove the ``Catalogs: Access in Control Panel`` and ``Catalogs: View`` permissions.
```

## Discount Manager

> Custom role

The Discount Manager role allows users to create, update, and delete Discounts in Liferay Commerce. Since Commerce Discounts can target different parts of an order and use different eligibility rules, there are a lot of possible permission configurations for a Discount Manager. The following configuration grants access to all Discount options.

| Permissions | Description |
| --- | --- |
| Account Groups: View | <!--FINISH--> |
| Catalogs: View | <!--FINISH--> |
| Catalogs > Commerce Catalogs: View Commerce Catalogs | <!--FINISH--> |
| Channels: View | <!--FINISH--> |
| Channels > Commerce Channels: View Commerce Channels | <!--FINISH--> |
| Discounts: Access in Control Panel | <!--FINISH--> |
| Discounts: Permissions | <!--FINISH--> |
| Discounts: View | <!--FINISH--> |
| Discounts > Commerce Discount: Delete | <!--FINISH--> |
| Discounts > Commerce Discount: Permissions | <!--FINISH--> |
| Discounts > Commerce Discount: Update | <!--FINISH--> |
| Discounts > Commerce Discount: View | <!--FINISH--> |
| Discounts > Commerce Discounts: Add Discount | <!--FINISH--> |
| Discounts > Commerce Discounts: View Discounts | <!--FINISH--> |
| Account Groups > Commerce Accounts: Manage All Accounts | <!--FINISH--> |<!--Accurate? or Shipments > Commerce Shipments: Manage All Accounts?-->
| Currencies > Commerce Currencies: Manage Currencies | <!--FINISH--> |
| Account Groups > Commerce Accounts: View Commerce Account Groups | <!--FINISH--> | <!--accurate?-->
| Portal: View Control Panel Menu | <!--FINISH--> |
| Price Lists: Access in Control Panel | <!--FINISH--> |
| Product Groups: View | <!--FINISH--> |

## Inventory Manager

> Custom role

The Inventory Manager role allows users to view and manage inventory in all Warehouses. Users with the role can add incoming shipments, update inventory levels, transfer between Warehouses, and view the changelog.

| Permissions | Description |
| --- | --- |
| Inventory: Access in Control Panel | <!--FINISH--> |
| Inventory: View | <!--FINISH--> |
| Warehouses > Commerce Inventories: Manage Inventory | <!--FINISH--> |
| Portal: View Control Panel Menu | <!--FINISH--> |

## Order Manager (Site Role)

> Out-of-the-box

This Order Manager role grants users all the permissions of Buyers and allows them to also manage, delete, and approve orders in a specific Channel Site.

| Permissions | Description |
| --- | --- |
| Open Carts > Commerce Order: Add Order | Allows order managers to add an order |
| Open Carts > Commerce Order: Approve Open Orders | Allow order managers to approve orders if workflow has been enabled |
| Open Carts > Commerce Order: Check Out Open Orders | Ability to complete the checkout process for new, incomplete orders |
| Open Carts > Commerce Order: Delete Orders | Allow order managers to delete orders |
| Open Carts > Commerce Order: Manage Orders | Change Order details such as mailing and billing addresses; ability to add a note to the order |
| Open Carts > Commerce Order: View Open Orders | Ability to view all open orders |
| Open Carts > Commerce Order: View Orders | Ability to view all orders regardless of their statuses |

## Order Manager (Regular Role)

> Custom role

This Order Manager role grants additional permissions that allow users to manage orders for one or more Channels.

| Permissions | Description |
| --- | --- |
| Open Carts > Commerce Order: Add Order | Ability to add an order |
| Open Carts > Commerce Order: Approve Open Orders | Ability to approve orders if workflow has been enabled |
| Open Carts > Commerce Order: Check Out Open Orders | Ability to complete the checkout process for new, incomplete orders |
| Open Carts > Commerce Order: Delete Orders | Ability to delete orders |
| Open Carts > Commerce Order: Manage Order Notes | Ability to change general order notes |
| Open Carts > Commerce Order: Manage Order Restricted Notes | Ability to change restricted order notes |
| Open Carts > Commerce Order: Manage Orders | Ability to change order details, such as mailing and billing addresses, adding an order note, and more |
| Open Carts > Commerce Order: View Open Orders | Ability to view all open Orders |
| Open Carts > Commerce Order: View Orders | Ability to view all orders regardless of their statuses |
| Orders: Access in Control Panel | <!--FINISH--> |
| Orders: Permissions | <!--FINISH--> |
| Orders: View | <!--FINISH--> |
| Account Groups > Commerce Accounts: Manage All Accounts | <!--FINISH--> | <!--accurate? OR: Shipments > Commerce Shipments: Manage All Accounts?-->
| Portal: View Control Panel Menu | <!--FINISH--> |

## Shipments Manager

> Custom role

The Shipment Manager role allows a user to process shipments. This includes the ability to add shipping details, shipping dates, add items to the shipment and update shipment status. In Commerce 3.0+ and Liferay 7.3+, users create shipments via the Orders application. If you only want the Shipment Manager to fill and process the shipment, then the following permissions are sufficient. Otherwise, you may want to add additional [Order Manager](#order-manager-regular-role) permissions.

| Permissions | Description |
| --- | --- |
| Open Carts > Commerce Order: View Orders | <!--FINISH--> |
| Account Groups > Commerce Accounts: Manage All Accounts | <!--FINISH--> | <!--accurate? OR Shipments > Commerce Shipments: Manage All Accounts?-->
| Warehouses > Commerce Inventories: Manage Inventory | <!--FINISH--> |
| Shipments > Commerce Shipments: Manage Shipments | <!--FINISH--> |
| Portal: View Control Panel Menu | <!--FINISH--> |
| Shipments: Access in Control Panel | <!--FINISH--> |
| Shipments: View | <!--FINISH--> |
| Shipments > Commerce Warehouse: View | <!--FINISH--> |

## Liferay 7.3 and Earlier

### Sales Agent (Regular Role)

> Out-of-the-box

The Sales Agent is a regular role in Liferay Commerce and assignees are able to manage any account assigned to the role without granting administrative permissions. Because it is a regular role, it is found in the *Regular Roles* tab.

![View default Sales Agent permissions in the Define Permissions tab.](./commerce-roles-reference/images/04.png)

| Permissions | Description |
| --- | --- |
| Manage Organizations | Ability to add or remove organizations |
| Manage Available Accounts | Ability to manage Accounts that the sales agent is a member of |

## Additional Information

* [Understanding Roles and Permissions](https://learn.liferay.com/dxp/latest/en/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html)
* [Order Management Permissions Reference](./order-management-permissions-reference.md)
* [Inventory Management Permissions Reference](./inventory-management-permissions-reference.md)
* [Pricing Permissions Reference](./pricing-permissions-reference.md)
* [Product Management Permissions Reference](./product-management-permissions-reference.md)
* [Store Management Permissions Reference](./store-management-permissions-reference.md)
* [Settings Permissions Reference](./settings-permissions-reference.md)
