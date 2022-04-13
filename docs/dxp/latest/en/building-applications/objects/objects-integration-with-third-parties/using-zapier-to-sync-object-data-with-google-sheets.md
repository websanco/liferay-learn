# Using Zapier to Sync Object Data with Google Sheets

You can use Liferay Objects with data integration tools to create automated tasks for syncing Object data with external services. These tasks are triggered using webhooks and can connect to Google applications, Microsoft Office, and more.

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using [Zapier](https://zapier.com/). Syncing your data in this way requires a premium Zapier account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Log in to [Zapier](https://zapier.com/) and click *Create Zap*.

   ![Create a Zap.](./using-zapier-to-sync-object-data-with-google-sheets/images/01.png)

1. Click the *Trigger* step and select *Webhooks by Zapier*.

   ![Click Trigger and select webhooks by Zapier.](./using-zapier-to-sync-object-data-with-google-sheets/images/02.png)

   ```{note}
   Webhooks is a premium Zapier feature.
   ```

1. Click the *Trigger Event* dropdown menu, select *Catch Hook*, and click *Continue*.

   ![Select Catch Hook for trigger event.](./using-zapier-to-sync-object-data-with-google-sheets/images/03.png)
   <!--NOTE: I removed the admonition because it seemed unnecessary for this use case. We can discuss it though. -->

1. Copy the generated *webhook URL*.

   ![Copy the generated webhook URL.](./using-zapier-to-sync-object-data-with-google-sheets/images/04.png)

1. In your Liferay instance, use the copied URL to [define an Object action](../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

   ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-zapier-to-sync-object-data-with-google-sheets/images/05.png)

1. Add a test Object entry to trigger the webhook.

   This allows the Webhooks module to determine the Object's data structure automatically.

1. Click the *Test Trigger* button.

   ![Click the test trigger button.](./using-zapier-to-sync-object-data-with-google-sheets/images/06.png)

1. Verify the test was successful and click *Continue*.

   ![Verify the test was successful.](./using-zapier-to-sync-object-data-with-google-sheets/images/07.png)

1. Click the *Action* step and select the *Google Sheets* app.

   ![Select Google Sheets.](./using-zapier-to-sync-object-data-with-google-sheets/images/08.png)

1. Click the *Action Event* dropdown menu and select *Create Spreadsheet Row*.

   ![Select Create Spreadsheet Row.](./using-zapier-to-sync-object-data-with-google-sheets/images/09.png)

1. Connect the Google Sheets app to a Google account and then click *Continue*.

   ![Connect the Google Sheets app to a Google account.](./using-zapier-to-sync-object-data-with-google-sheets/images/10.png)

1. Select the desired *Spreadsheet* and *Worksheet* to sync with the Object.

   ![Select the desired Spreadsheet and Worksheet.](./using-zapier-to-sync-object-data-with-google-sheets/images/11.png)

   ```{important}
   When using the Create Spreadsheet Row action, you must have text in the first column header. Otherwise, the Zap sends your data to the top of the sheet rather than the bottom.
   ```

1. Map the Sheet's columns to data fields in the Object's structure and click *Continue*.

   ![Map the Sheet's columns to Object fields.](./using-zapier-to-sync-object-data-with-google-sheets/images/12.png)

1. Click the *Test Action* button and verify the test is successful.

   ![Click the Test Action button and verify the test is successful.](./using-zapier-to-sync-object-data-with-google-sheets/images/13.png)

1. Turn on your Zap.

   ![Turn on your Zap.](./using-zapier-to-sync-object-data-with-google-sheets/images/14.png)

## Additional Information

* [Objects Overview](../../objects.md)
* [Creating and Managing Objects](../creating-and-managing-objects.md)
* [Understanding Object Integrations](../understanding-object-integrations.md)