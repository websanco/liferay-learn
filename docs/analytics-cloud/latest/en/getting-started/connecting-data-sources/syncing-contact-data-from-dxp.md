# Syncing Contact Data from DXP

After connecting your [Liferay DXP instance to Analytics Cloud](connecting-liferay-dxp-to-analytics-cloud.md), you can begin syncing contact data. The latest releases of Liferay DXP gives you fine grain control over what fields to sync from your contact data into Analytics Cloud.

```Note
The ability to select contact fields to sync is available in the following releases or higher:
Liferay 7.3 Fix Pack 1
Liferay 7.2 Fix Pack 9
Liferay 7.1 Fix Pack 20
Liferay 7.0 Fix Pack 97
```

## Initial Sync of Contact Data
To sync contact data,

1.  Navigate to *Control Panel* &rarr; *Instance Setting* &rarr; *Analytics Cloud* in your Liferay DXP instance.

1. Click on the *Select Contacts* Button.

1. Click on *Sync Contacts*.

1. Use the switch to enable Sync All. Alternatively click on *Sync by User Groups* or *Sync by Organization* if you prefer to sync a subset of your contacts.

      ![Sync all your contacts or sync groups or organizations.](./syncing-contact-data-from-dxp/images/01.png)

      Click the *Save and Next* button.

1. Liferay DXP stores contact data in two separate tables (Contact and User). Switch between the Contact tab and User tab to select the fields you wish to sync. Some fields are required by Analytics Cloud to be synced and are grayed out from selection.

      ![Select the fields to sync for your contacts.](./syncing-contact-data-from-dxp/images/02.png)

      Note that any custom field created for users will also be available to sync. Learn more about [adding custom fields to users](https://learn.liferay.com/dxp/7.x/en/users-and-permissions/devops/adding-custom-fields-to-users.html).

      Click the *Save* button at the bottom of the page after making your selections.

## Modifying Sync of Contact Data Fields

To modify which contact data fields you wish to sync with Analytics Cloud,

1. Navigate to *Control Panel* &rarr; *Instance Setting* &rarr; *Analytics Cloud* &rarr; *Synced Contact Data* in your Liferay DXP instance. 

      ![Navigate to the Synced Contact Data section in the Control Panel.](./syncing-contact-data-from-dxp/images/03.png)

1. Click on Sync Data Fields. 

1. Make your modifications and click the *Save* button.