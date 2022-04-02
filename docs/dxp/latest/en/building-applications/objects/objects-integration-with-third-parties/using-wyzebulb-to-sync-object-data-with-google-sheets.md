# Using Wyzebulb to Sync Object Data with Google Sheets

You can use Liferay Objects with data integration tools to create automated tasks for syncing Object data with external services. These tasks are triggered using webhooks and can connect to Google applications, Microsoft Office, and more.

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using Wyzebulb. Syncing your data in this way requires an Wyzebulb account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Open Wyzebulb, on the *Trigger* step click on *App*.

    ![Open Wyzebulb, on the Trigger step click on App.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/01.png)

1. Search for and select *Webhooks*.

    ![Select Webhooks.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/02.png)

1. Select a *Trigger*.

    ![Select a Trigger.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/03.png)

1. Click *Save and Continue*. 

    ![Click Save and Continue.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/04.png)

1. Select the *Webhooks URL*.

    ![Select the Webhooks URL.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/05.png)

1. Copy the *Webhook URL*.

    ![Copy the Webhook URL.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/06.png)

1. Use the copied URL to [define an Object action](../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

    ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/07.png)

1. Trigger the webhook by adding a test entry to the Object.

   This allows the Webhooks module to determine the Object's data structure automatically.

1. Verify if the test successfully determined the Object's data structure. If the data sent to the webhook URL is found click *Save and Continue*.

    ![Successful Test.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/08.png)

1. On the *Action* step click on *App*.

    ![On the *Action* step click on App.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/09.png)

1. Search for and select *Google Sheets*.

    ![select Google Sheets.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/10.png)

1. Select an *Action*.

    ![Select an Action.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/11.png)

1.  Click *Save and Continue*. 

    ![Click Save and Continue.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/12.png)

1. Choose a Google Sheet account to connect.

    ![Choose a Google Sheet account to connect.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/13.png)

1. Select the desired *Spreadsheet*.

    ![Select the desired Spreadsheet.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/14.png)

1. Select the desired *Worksheet* name.

    ![Select the desired Worksheet.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/15.png)

1.  Map the Sheet's columns to data fields in the Object's structure.

    ![Map the Sheet's columns to Object fields.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/16.png)

1. Click on the *Test Action* button and verify if the test was successful.

    ![Successful Test.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/17.png)

1. Navigate to *My Flows* page and verify is your flow is running.

    ![Verify is your flow is running.](./using-wyzebulb-to-sync-object-data-with-google-sheets/images/18.png)

    ## Additional Information

* [Objects Overview](../../objects.md)
* [Creating and Managing Objects](../creating-and-managing-objects.md)
* [Understanding Object Integrations](../understanding-object-integrations.md)