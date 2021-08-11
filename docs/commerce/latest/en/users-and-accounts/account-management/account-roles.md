# Account Roles

Account roles are sets of permissions that grant users specific abilities within an Account context. These roles can be used to delegate Account responsibilities, or paired with Commerce Site roles to implement a complete purchase workflow for Business Accounts.

```{note}
Account roles were introduced with Liferay 7.4. In earlier versions, the following roles are listed as Site roles. 
```

Commerce includes two Account roles out of the box:

* **Account Administrator**: Users with this role can edit the Account, invite users to join the account, and assign roles to Account Members. This role also includes all Buyer and Order Manager permissions.

* **Account Member**: This role is automatically assigned to all users within an account and grants basic view permissions. Grant members the Buyer or Order Manager role to delegate purchase responsibilities.

  * **Buyer** (Site Role): Users with this role can view, create, and check out orders.

  * **Order Manager** (Site Role): Order Managers have all the permissions of buyers, and can also manage, delete and approve orders.

See [Commerce Roles Reference](../roles-and-permissions/commerce-roles-reference.md) for a list of all out-of-the-box roles, as well as common custom roles you may want to create for your own instance.

## Creating Account Roles

If existing Account roles are not sufficient to satisfy your business needs, you can create additional Account roles. You can also add permissions to existing Account roles. Doing this is the same process as creating and editing other types of DXP roles.

See [Creating and Managing Roles](https://learn.liferay.com/dxp/latest/en/users-and-permissions/roles-and-permissions/creating-and-managing-roles.html) and [Defining Role Permissions](https://learn.liferay.com/dxp/latest/en/users-and-permissions/roles-and-permissions/defining-role-permissions.html) for more information.

## Liferay 7.3 and Earlier

### Seller-side Account Management Roles

Accounts can also be managed by Administrators and Sales Agents. The Sales Agent allows a user to manage any account assigned to the role without granting administrative permissions.

To give a sales agent access to accounts:

1. Group your organizations in accounts using the Accounts Widget.
1. Associate sales agents with the same organizations.

![List of Users with the Sales Agent Role](./account-roles/images/01.png)

Sales agents can access any account within any of their associated organizations.

## Additional Information

* [Account Management](../account-management.md)
* [Creating a New Account](./creating-a-new-account.md)
* [Creating a New Account Group](./creating-a-new-account-group.md)
* [Understanding Roles and Permissions](https://learn.liferay.com/dxp/latest/en/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html)
