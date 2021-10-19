# Accounts

```{toctree}
:maxdepth: 3

accounts/account-users.md
accounts/account-groups.md
accounts/account-roles.md
accounts/account-management-widget.md
```

Accounts provide administrators with a way to organize and manage users for various commerce or business needs. Unlike [organizations](./organizations.md) or [user groups](./user-groups.md), accounts help facilitate interactions that might take place in business situations such as the purchasing of goods and services or managing existing customers.

There are three different account types, *Business*, *Person*, and *Guest* types. Business accounts all you to associate multiple users to an account. Person type accounts can only have one account associated with them. Guest-type accounts provide a way for unregistered users to interact with your business or site.

```{note}
Accounts is a feature, originally found in [Liferay Commerce](https://learn.liferay.com/commerce/latest/en/users-and-accounts/account-management.html), that is now a part of Liferay DXP 7.4 and above.
```

## Creating an Account

1. Open the Global Menu (![Global Menu](../images/icon-applications-menu.png)). Navigate to *Applications* &rarr; *Accounts*.

1. Click the _Add_ icon (![Add icon](../images/icon-add.png)) to create a new account.

1. Give your account a name and select the type. Note that once an Account is created the type cannot be changed.

   ![Give you account a name and select the type.](./accounts/images/01.png)

1. Add additional information for your account such as Tax ID or description. Click *Save* to create the new account.

1. After creating an account, tabs appear at the top of the page.

   ![New tabs appear after creating an account.](./accounts/images/02.png)

### Details Tab

The Details tab displays the main information about the account such as the account name and type. A default billing address and shipping address can also be set here. [Categories](../content-authoring-and-management/tags-and-categories/defining-categories-and-vocabularies-for-content.md) or [Custom Fields](../system-administration/configuring-liferay/adding-custom-fields.md) can also be added for further customization of the account.

### Addresses Tab

The Addresses tab displays the different addresses associated with the account. To add a new address click the Add icon (![Add icon](../images/icon-add.png)). Fill out the address information and click the *Save* button.

![Fill out the address information and click Save.](./accounts/images/03.png)

### Users Tab

The Users tab displays the different Users associated with the account. Note, this tab does not exist for Person type accounts or Guest type accounts. To associate a user to the account,

1. Click the _Add_ icon (![Add icon](../images/icon-add.png)). A window shows a list of account Users available to select.

1. Select users by checking the box next to a User's name and click the *Assign* button.

1. A new User can also be created from this view. Click the _Add_ icon (![Add icon](../images/icon-add.png)) and see an add new User page.

1. Fill in the information for the new User and click the *Save* button.

1. Any new User that was created or associated with the account is displayed in the users tab. See [Account Users](./accounts/account-users.md) to learn more.

### Organizations Tab

The Organizations tab displays the different Organizations associated with the account. To associate an organization, click the _Add_ icon (![Add icon](../images/icon-add.png)). Select the Organization to associate and click the *Assign* button.

### Account Groups Tab

The Account Groups tab displays the different group memberships for the account. To learn more about creating or managing these groups, see [Account Groups](./accounts/account-groups.md).

### Roles Tab

The Roles tab displays the different Roles that are available for the account. Note, this tab does not exist for Guest type accounts. To create an Account Role,

1. Click the _Add_ icon (![Add icon](../images/icon-add.png)).

1. On the following page, give the Role a title and description. Click *Save*.

1. Once the Role is created, you can define permissions and assign Users. Click the *Define Permissions* tab and choose the permissions for the Role.

1. Click the *Assignees* tab to assign a User to the Role. Click the _Add_ icon (![Add icon](../images/icon-add.png)) to select a User and click the *Assign* button.

See [Account Roles](./accounts/account-roles.md) to learn more.

## Making Changes to an Account

1. Click the _Options_ icon (![Options icon](../images/icon-actions.png)) of the account you want to edit.

1. Make one of the following selections:

   ![Make a selection to edit the account.](./accounts/images/04.png)

   * Edit - See and edit the account information.
   * Manage Users - Add or remove Account Users.
   * Manage Organizations - Add or remove Organization associations.
   * Deactivate - Deactivate an Account.
   * Delete - Delete an Account.

   Note that for Person Account type, there is no Manage Users functionality.

1. Selecting the Edit option shows more options:

   ![Select Edit to see more options.](./accounts/images/02.png)

   * Details tab - Modify or add additional information for the Account.
   * Addresses tab - Add or manage billing and shipping addresses.
   * Users tab - Add or remove Account Users. See [Account Users](./accounts/account-users.md) for more information.
   * Organizations tab - Add or remove Organization associations.
   * Account Groups tab - View the Account Groups the Account is a part of. See [Account Groups](./accounts/account-groups.md) for more information.
   * Roles tab - Add or manage the different roles for the Account. See [Account Roles](./accounts/account-roles.md) for more information.

## Related Information

* [Account Users](./accounts/account-users.md)
* [Account Groups](./accounts/account-groups.md)
* [Account Roles](./accounts/account-roles.md)
* [Account Management Widget](./accounts/account-management-widget.md)
