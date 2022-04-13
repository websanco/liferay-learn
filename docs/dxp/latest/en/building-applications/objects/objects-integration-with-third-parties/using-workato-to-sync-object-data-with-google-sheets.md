# Using Workato to Sync Object Data with Google Sheets

You can use Liferay Objects with data integration tools to create automated tasks for syncing Object data with external services. These tasks are triggered using webhooks and can connect to Google applications, Microsoft Office, and more.

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using [Workato](https://www.workato.com/). Syncing your data in this way requires an Workato account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Log in to [Workato](https://www.workato.com/) and navigate to the *Projects* page.

   ![Navigate to the Projects page.](./using-workato-to-sync-object-data-with-google-sheets/images/01.png)

1. Navigate to the *Recipes* page and click on *Create Recipe*.

   ![Navigate to the Recipes page and click on Create Recipe.](./using-workato-to-sync-object-data-with-google-sheets/images/02.png)

1. Enter a recipe *Name*, *Location*, and pick the *Trigger from a webhook*. Then click *Start building*.

   ![Enter a Name, Location and pick the starting point.](./using-workato-to-sync-object-data-with-google-sheets/images/03.png)

1. Click *Start Guided Setup*.
   <!--NOTE: Do users need to select the webhook trigger before clicking this? -->
   ![Click Start Guided Setup.](./using-workato-to-sync-object-data-with-google-sheets/images/04.png)

1. Enter an *Event Name* and copy the generated *webhook URL*.

   ![Enter an Event Name and click Next.](./using-workato-to-sync-object-data-with-google-sheets/images/05.png)

1. In your Liferay instance, use the copied URL to [define an Object action](../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

   ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-workato-to-sync-object-data-with-google-sheets/images/06.png)

1. Click *Next* and add a test Object entry to trigger the webhook.

   This allows the webhook module to determine the Object's data structure automatically.

   ![Trigger the webhook.](./using-workato-to-sync-object-data-with-google-sheets/images/07.png)

1. Verify if the test successfully determined the Object's data structure and click *Setup Webhook*

   ![Verify if the test successfully determined the object's data structure.](./using-workato-to-sync-object-data-with-google-sheets/images/08.png)

1. For the *Action*, select *Action in an app*.

   ![For the Action, select Action in an app.](./using-workato-to-sync-object-data-with-google-sheets/images/09.png)

1. Select the *Google Sheets* app.

   ![Select the Google Sheets app.](./using-workato-to-sync-object-data-with-google-sheets/images/10.png)

1. Select the *Add Row* action.

   ![Select the Add Row action.](./using-workato-to-sync-object-data-with-google-sheets/images/11.png)

1. Connect the app to a Google account.

   ![Connect the app to a Google account.](./using-workato-to-sync-object-data-with-google-sheets/images/12.png)

1. Select the desired *Spreadsheet* and *Worksheet* to sync with the Object.

   ![Select the desired Spreadsheet and Worksheet.](./using-workato-to-sync-object-data-with-google-sheets/images/13.png)

1. Map the Sheet's columns to data fields in the Object's structure.

   ![Map the Sheet's columns.](./using-workato-to-sync-object-data-with-google-sheets/images/14.png)

1. Click *Save*.

   ![Click Save.](./using-workato-to-sync-object-data-with-google-sheets/images/15.png)

1. Navigate to *Assets* page, click the *kebab Button* and select *Start* to activate the recipe.

   ![Activate the recipe.](./using-workato-to-sync-object-data-with-google-sheets/images/16.png)

## Additional Information

* [Objects Overview](../../objects.md)
* [Creating and Managing Objects](../creating-and-managing-objects.md)
* [Understanding Object Integrations](../understanding-object-integrations.md)