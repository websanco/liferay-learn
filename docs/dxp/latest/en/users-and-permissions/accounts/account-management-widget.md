# Account Management Widget

The Account Management widget makes it easy for your account administrators and account users to manage and access account information. Deploy the widget to any Site page for your users. This is a useful way to provide access for users who do not have permissions to view the Applications Menu.

```{note}
Accounts is a feature that is available in Liferay DXP 7.4 and above.
```

## Adding the Account Management Widget to a Page

To add the Account Management widget to a page, 

1. Navigate to a Site page where the app is desired.

1. Click the Edit icon (![Edit icon](../../images/icon-edit-pencil.png)) at the top right of the page.

1. Locate the widget under the Accounts section of widgets. Drag the widget to the page.

    ![Locate the widget and drag it to the page.](./account-management-widget/images/01.png)

1. A list of Accounts should now be displayed by the widget.

## Using the Account Management Widget

The Account Management widget displays different Accounts depending on which Accounts the Account User has access to. For users who have access to multiple Accounts, click the *Filter and Order* tab to quickly find a specific account. Filter by status: Active or Inactive. Filter by type: All, Business, Guest, Person. Also use the search bar to find a specific account.

![The account management widget displays different accounts.](./account-management-widget/images/02.png)

The widget displays the following information:

* Name - The name of the Account
* ID - The ID number of the Account
* Organizations - Any organizations the Account is assigned to
* Type - The type of Account (i.e. Business, Guest, Person)
* Status - Whether the Account is active or inactive
* Selected - The checkmark shows the current Account the User is set up to view

Note, when an Account User has access to multiple Accounts, the Selected checkmark indicates which Account the user is currently viewing or managing. This gives the ability to save different settings for each Account such as price or order information.

Click on the Options icon (![Options icon](../../images/icon-actions.png)) of the account you want to edit or manage. The following choices are available:

* Edit - Make changes to the Account
* Manage Users - Manage the Account Users
* Manage Organizations - Manage the organizations the Account is assigned to
* Deactivate - Deactivate the Account
* Delete - Delete the Account