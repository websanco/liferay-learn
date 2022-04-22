# Using Automate.io to Sync Object Data with Google Sheets

{bdg-secondary}`Available 7.4+`

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using [Automate.io](https://automate.io/). Syncing your data in this way requires an premium Automate.io account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Sign in to [Automate.io](https://automate.io/) and click *Create a Bot*.

   ![Open Automate.io and click Create a Bot.](./using-automate-io-to-sync-object-data-with-google-sheets/images/01.png)

1. For the *Trigger* step, select the *Webhooks* app.

   ![Select Webhooks.](./using-automate-io-to-sync-object-data-with-google-sheets/images/02.png)

   ```{note}
   Webhooks is a premium Automate.io feature.
   ```

1. For the *Trigger Event*, select *Incoming Hook*.

   ![Select Incoming Hook.](./using-automate-io-to-sync-object-data-with-google-sheets/images/03.png)

1. Copy the generated *webhook URL*.

   ![Copy the Webhooks URL.](./using-automate-io-to-sync-object-data-with-google-sheets/images/04.png)

1. In your Liferay instance, use the copied URL to [define an Object action](../../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

   ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-automate-io-to-sync-object-data-with-google-sheets/images/05.png)

1. Add a test Object entry to trigger the webhook.

   This allows the Webhooks app in your Automate.io bot to determine the Object's data structure automatically.

1. For the *Action* step, select the *Google Sheets* app.

   ![Select Google Sheets.](./using-automate-io-to-sync-object-data-with-google-sheets/images/06.png)

1. Click *Authorize* to connect the app with a Google account.

   ![Choose a Google account to connect.](./using-automate-io-to-sync-object-data-with-google-sheets/images/07.png)

1. For the Action field, select *Add Row*.

   ![Select Add Row.](./using-automate-io-to-sync-object-data-with-google-sheets/images/08.png)

1. Select the desired *Spreadsheet* and *Worksheet* to sync with the Object and click *Save*.

   ![Select the desired Spreadsheet and Worksheet.](./using-automate-io-to-sync-object-data-with-google-sheets/images/09.png)

1. *Turn on* your bot.

   ![Turn on your bot.](./using-automate-io-to-sync-object-data-with-google-sheets/images/10.png)

1. Add an Object entry to test your bot.

   ![Test your bot using live data.](./using-automate-io-to-sync-object-data-with-google-sheets/images/11.png)

1. Verify the bot successfully received the Object data and triggered the Google Sheets action.

   ![Verify the test was successful.](./using-automate-io-to-sync-object-data-with-google-sheets/images/12.png)

## Additional Information

* [Objects Overview](../../../objects.md)
* [Creating and Managing Objects](../../creating-and-managing-objects.md)
* [Understanding Object Integrations](../../understanding-object-integrations.md)