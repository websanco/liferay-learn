# Using Workato to Sync Object Data with Google Sheets

You can use Liferay Objects with data integration tools to create automated tasks for syncing Object data with external services. These tasks are triggered using webhooks and can connect to Google applications, Microsoft Office, and more.

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using Workato. Syncing your data in this way requires an Workato account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Open Workato and navigate to the *Projects* page.
   
    ![Navigate to the Projects page.](./using-workato-to-sync-object-data-with-google-sheets/images/01.png)

1. Navigate to *Recipes* page and click on *Create Recipe*.

    ![Navigate to recipes page and click on create recipe.](./using-workato-to-sync-object-data-with-google-sheets/images/02.png)

1. Choose a *Name*, *Location* and *Pick a Starting Point*

    ![Choose a name, location and pick a starting point.](./using-workato-to-sync-object-data-with-google-sheets/images/03.png)

    ```{note}
    The starting point for this example's sake needs to be Trigger from a Webhook.
    ```

1. Click on *Start Guided Setup*.

    ![Click on start guided setup.](./using-workato-to-sync-object-data-with-google-sheets/images/04.png)

1. Choose an *Event Name* and copy the generated *webhook URL*.

    ![Choose an Event Name and click Next.](./using-workato-to-sync-object-data-with-google-sheets/images/05.png)

1. Use the copied URL to [define an Object action](../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

    ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-workato-to-sync-object-data-with-google-sheets/images/06.png)

1. Click *Next* to trigger the webhook and add a test entry to the Object.

   This allows the Webhooks module to determine the Object's data structure automatically.

    ![Trigger the webhook.](./using-workato-to-sync-object-data-with-google-sheets/images/07.png)

1. Verify if the test successfully determined the Object's data structure and click *Setup Webhook*

    ![Verify if the test successfully determined the object's data structure.](./using-workato-to-sync-object-data-with-google-sheets/images/08.png)

1. Navigate to the *Action* step and select *Action in a App*.

    ![Navigate to the action step and select action in a app.](./using-workato-to-sync-object-data-with-google-sheets/images/09.png)

1. Search for and select *Google Sheets*.

    ![Search for and select Google Sheets.](./using-workato-to-sync-object-data-with-google-sheets/images/10.png)

1. Select *Add Row*.

    ![Select add row.](./using-workato-to-sync-object-data-with-google-sheets/images/11.png)

1. Choose an Google account to connect.

    ![Choose an google account to connect.](./using-workato-to-sync-object-data-with-google-sheets/images/12.png)

1. Select the desired *Spreadsheet* and *Worksheet* to sync with the Object.

    ![Select the desired spreadsheet and worksheet.](./using-workato-to-sync-object-data-with-google-sheets/images/13.png)

1. Map the Sheet's columns to data fields in the Object's structure.

    ![Map the sheet's columns.](./using-workato-to-sync-object-data-with-google-sheets/images/14.png)

1. Click *Save*.

    ![Click save.](./using-workato-to-sync-object-data-with-google-sheets/images/15.png)

1. Navigate to *Assets* page, click the *kebab Button* and select *Start*. Your connection is active.

    ![Active Connection.](./using-workato-to-sync-object-data-with-google-sheets/images/16.png)

## Additional Information

* [Objects Overview](../../objects.md)
* [Creating and Managing Objects](../creating-and-managing-objects.md)
* [Understanding Object Integrations](../understanding-object-integrations.md)