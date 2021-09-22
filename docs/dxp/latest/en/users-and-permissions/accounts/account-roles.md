# Account Roles

Like [regular roles](../roles-and-permissions/understanding-roles-and-permissions.md) in DXP, [Accounts](../accounts.md) have Account Roles that assign different permissions to different roles. Use Account Roles for your different Account Users based on your business needs.

```{note}
Accounts is a feature that is available in Liferay DXP 7.4 and above.
```

## View Account Roles

There are two categories of Account Roles: instance wide roles and Account specific roles. Instance wide roles are Account Roles that are available across all Accounts in a DXP Instance. Account specific roles are roles that are created and managed for a specific Account.

### View Instance Wide Account Roles

To view an instance wide role, 

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Roles*.

1. Click the *Account Roles* tab and view the list of available roles.

### View Account Specific Account Roles

To view an Account specific role,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Applications* &rarr; *Accounts*.

1. Click on the specific Account you wish to view. Click the *Roles* tab and view the list of available roles.

## Creating a New Account Role

Make a choice to create an instance wide role or an account specific role.

### Creating and Assigning an Instance Wide Account Role

To create an instance wide role,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Roles*.

1. Click the *Account Roles* tab. Click the Add icon (![Add icon](../../images/icon-add.png)).

1. Give the new role a title and description. Click *Save* to create the role.

    ![Give the role a new title and description.](./account-roles/images/01.png)

1. Click the *Define Permissions* tab at the top of the page. Select the different Account permissions to assign to this role. Click *Save* to save the permissions.

1. To assign an account user to this role, navigate to *Applications* &rarr; *Accounts*. Select a specific account. 

1. Click the *Roles* tab and click the Options icon (![Options icon](../../images/icon-actions.png)) of the role you just created. Click *Assign Users*.

    ![Click the options icon of the account and click Assign Users.](./account-roles/images/02.png)

1. Click the Add icon (![Add icon](../../images/icon-add.png)) and assign a user to this role.

### Creating and Assigning an Account Specific Account Role

To create an account specific role, 

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Applications* &rarr; *Accounts*.

1. Select the specific account for the new role. Click the *Roles* tab and click the Add icon (![Add icon](../../images/icon-add.png)).

1. Give the new role a title and description. Click *Save* to create the role.

1. Click the *Define Permissions* tab at the top of the page. Select the different Account permissions to assign to this role. 

    ![Select the permissions to assign to this role.](./account-roles/images/03.png)

1. To assign an account, click the *Assignees* tab. Click the Add icon (![Add icon](../../images/icon-add.png)) and assign a user to this role.