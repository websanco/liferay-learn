# Using Pabbly to Sync Object Data with Google Sheets

You can use Liferay Objects with data integration tools to create automated tasks for syncing Object data with external services. These tasks are triggered using webhooks and can connect to Google applications, Microsoft Office, and more.

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using [Pabbly](https://www.pabbly.com/). Syncing your data in this way requires an Pabbly account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Sign in to [Pabbly](https://www.pabbly.com/), navigate to the *All Apps* page, and click *Pabbly Connect*.

   ![Navigate to the All Apps page and click Pabbly Connect.](./using-pabbly-to-sync-object-data-with-google-sheets/images/01.png)

1. On the *Dashboard* page, click *Create Workflow*.

   ![Click Create Workflow.](./using-pabbly-to-sync-object-data-with-google-sheets/images/02.png)

1. Enter a name and click *Create*.

   ![Choose a name and click Create.](./using-pabbly-to-sync-object-data-with-google-sheets/images/03.png)

1. For the *Trigger*, select the *Webhook* app.

   ![Search for and select the Webhook app.](./using-pabbly-to-sync-object-data-with-google-sheets/images/04.png)

1. Copy the generated *webhook URL*.

   ![Copy the generated *webhook URL*.](./using-pabbly-to-sync-object-data-with-google-sheets/images/05.png)

1. In your Liferay instance, use the copied URL to [define an Object action](../../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

   ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-pabbly-to-sync-object-data-with-google-sheets/images/06.png)

1. Adding an Object entry to trigger the webhooko and then click *Capture Response* in Pabbly.

   This allows the Webhook app to determine the Object's data structure automatically.

   ![Trigger the webhook.](./using-pabbly-to-sync-object-data-with-google-sheets/images/07.png)

1. For the *Action*, select the *Google Sheets* app.

   ![Select the Google Sheets app.](./using-pabbly-to-sync-object-data-with-google-sheets/images/08.png)

1. In the *Action Event* dropdown menu, select *Add New Row* and click *Connect*.

   ![Select Add New Row.](./using-pabbly-to-sync-object-data-with-google-sheets/images/09.png)

1. Connect the desired Google account to the app and click *Save*.

   ![Connect the desired Google account.](./using-pabbly-to-sync-object-data-with-google-sheets/images/10.png)

1. Select the desired *Spreadsheet* and *Sheet* to sync with the Object.

   ![Select the desired Spreadsheet and sheet to sync with the Object.](./using-pabbly-to-sync-object-data-with-google-sheets/images/11.png)

1. Map the Sheet's columns to data fields in the Object's structure.

   ![ Map the Sheet's columns to data fields in the Object's structure.](./using-pabbly-to-sync-object-data-with-google-sheets/images/12.png)

1. Click the *Save & Send Test Request* button and verify the test was successful.

   ![Click the Save & send Test Request.](./using-pabbly-to-sync-object-data-with-google-sheets/images/13.png)

   If successful, the *Workflow* is activated and ready to use.

## Additional Information

* [Objects Overview](../../../objects.md)
* [Creating and Managing Objects](../../creating-and-managing-objects.md)
* [Understanding Object Integrations](../../understanding-object-integrations.md)