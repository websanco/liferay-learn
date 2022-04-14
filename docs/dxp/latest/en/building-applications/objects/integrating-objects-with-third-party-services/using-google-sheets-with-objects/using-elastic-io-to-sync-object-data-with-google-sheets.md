# Using Elastic.io to Sync Object Data with Google Sheets

You can use Liferay Objects with data integration tools to create automated tasks for syncing Object data with external services. These tasks are triggered using webhooks and can connect to Google applications, Microsoft Office, and more.

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using [Elastic.io](https://www.elastic.io/). Syncing your data in this way requires an Elastic.io account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Sign in to [Elastic.io](https://www.elastic.io/), navigate to the *Integrate* page, and click *Flows*.

   ![Navigate to the Integrate page, and click Flow.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/01.png)

1. Click *Add New Flow*.

   ![Add a new flow.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/02.png)

1. Click *Add the Initial Trigger*.

   ![Add the initial trigger.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/03.png)

1. For the *Trigger*, select the *Webhook* component.

   ![Search for and select Webhook.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/04.png)

1. Copy the generated *Webhook URL*.

   ![Copy the generated Webhook URL.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/05.png)

1. In your Liferay instance, use the copied URL to [define an Object action](../../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

   ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/06.png)

1. Add a test Object entry to trigger the webhook.

   This allows the Webhook component to determine the Object's data structure automatically.

1. Click the *Send Sample Request* button.

   ![Click the Send Sample Request button.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/07.png)

1. Verify the test successfully determined the Object's data structure. If the data sent to the webhook URL is found, the step will show it was successful.

   ![Verify the test was successful.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/08.png)

1. Click *Add New Action*.

   ![Add a new action.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/09.png)

1. For the *Action*, select the *Google Sheets* component.

   ![Select the Google Sheets component.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/10.png)

1. Select the *Add Spreadsheet Row* function.

   ![Select the Add Spreadsheet Row function.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/11.png)

1. Select the desired *Spreadsheet* and *Worksheet* to sync with the Object.

   ![Select the desired Spreadsheet and sheet to sync with the Object.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/12.png)

1. Indicate whether the selected Sheet includes headers.

   ![Indicate whether the selected Sheet includes headers.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/13.png)

1. Map the Sheet's columns to data fields in the Object's structure.

   ![Map the Sheet's columns to Object fields.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/14.png)

1. Click the *Retrieve Sample from Google Spreadsheet* button.

   ![Click the Retrieve Sample from Google Spreadsheet button.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/15.png)

1. Verify the test was successful.

   ![Verify the test was successful.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/16.png)

1. Click *Publish Draft*.

   ![Click Publish Draft.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/17.png)

1. Click *Start Flow* to activate the new flow.

   ![Click Start Flow.](./using-elastic-io-to-sync-object-data-with-google-sheets/images/18.png)

## Additional Information

* [Objects Overview](../../../objects.md)
* [Creating and Managing Objects](../../creating-and-managing-objects.md)
* [Understanding Object Integrations](../../understanding-object-integrations.md)