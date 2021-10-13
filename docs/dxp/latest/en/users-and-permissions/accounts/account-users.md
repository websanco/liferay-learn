# Account Users

Account Users are user accounts that are associated with a Business Account or a Person Account. 

```{note}
Accounts is a feature, originally found in [Liferay Commerce](../../../../latest/en/users-and-accounts/account-management.md), that is now a part of Liferay DXP 7.4 and above.
```

## Adding an Account User

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Applications* &rarr; *Account Users*.

1. Click the Add icon (![Add icon](../../images/icon-add.png)) to create a new account user. 

1. Select which Account this new user will be associated with.

1. Fill in the necessary information for this new user.

    ![Fill in the account user's information.](./account-users/images/01.png)

    Click the *Save* button to save the new account user.

```{note}
Users for Accounts can only be created within this *Account Users* interface. A regular DXP user that is created in the *Users and Organizations* interface cannot be associated with any Accounts. However, an Account User can be managed and edited from *Users and Organizations* after creation.
```

### Setting a Password for an Account User

When you create an account user, Liferay DXP generates a password for the user. If a [mail server was set up](../../installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md), Liferay DXP sends an email message with the user's new password.

If you haven't set up a mail server, set a password manually for the user.

1. In Account Users, click on the Options icon (![Options icon](../../images/icon-actions.png)) of the account user you want to edit.

1. Under the General tab, click *Password*. Set a password for the user and click *Save*.

## Editing an Account User

To edit an existing Account User,

1. In Account Users, click on the Options icon (![Options icon](../../images/icon-actions.png)) of the account user you want to edit.

1. Click *Edit* to see the edit user page.

The following options are available in the edit user page:

| General Tab | Description |
| --- | --- |
| Information | Edit or change the user's personal information. |
| Accounts | View or modify the accounts the user is associated with. |
| Password | Add or change the user's password. |

| Contact Tab | Description |
| --- | --- |
| Addresses | Add or change the user's addresses. |
| Contact Information | Add or change the user's contact information |

| Preferences Tab | Description |
| --- | --- |
| Alert and Announcements Delivery | Change the user's notification settings. |
| Display Settings | Change the user's time zone and greeting settings. |

## Deactivating and Deleting an Account User

Deleting an Account User is a two-step process. Some reasons for this are:

* You may decide that you want to keep the Account User after all.
* There may be user data that is tied with [legal requirements](./managing_user_data.html) you need to address. 
* You may require more time to review a user's account before deletion.

For these reasons and more you must first deactivate an Account User before deleting an Account User.

### Deactivating an Account User

Deactivating an Account User prevents the user from logging in, but still preserves the user's data and information. 

1. In Account Users, click on the Options icon (![Options icon](../../images/icon-actions.png)) of the account user you want to edit.

1. Click *Deactivate* and click the *OK* button to confirm your change. The Account User is now deactivated. 

To see a deactivated user, click the *Filter and Order* drop-down menu and click *Inactive* under Filter by Status. You can click the Options icon (![Options icon](../../images/icon-actions.png)) of a user and click *Activate* to change a user back to active status.

### Deleting an Account User

To delete an Account User,

1. In Account Users, click the *Filter and Order* drop-down menu and click *Inactive* under Filter by Status.

1. Click the Options icon (![Options icon](../../images/icon-actions.png)) of the inactive user and click *Delete*

    ![Select the inactive user and click Delete.](./account-users/images/02.png)

1. Click *OK* on the pop-up form to confirm your decision. The Account User is now deleted.

## Impersonating an Account User

You can impersonate an account user to view the system as they would see it. This helps to diagnose permission issues an administrator can't see, such as making sure a User doesn't have access to restricted data. Note, only users that have this permissions can impersonate a different user.

1. In Account Users, click on the Options icon (![Options icon](../../images/icon-actions.png)) of the account user you want to edit.

2. Click *Impersonate User* and a new window will open with you logged in as that Account User.