# Using IFTTT to Sync Object Data with Google Sheets

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using [IFTTT](https://ifttt.com/). Syncing your data in this way requires an IFTTT account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Sign in to [IFTTT](https://ifttt.com/) and click *Create*.

    ![Click Create.](./using-ifttt-to-sync-object-data-with-google-sheets/images/01.png)

1. Click *Add* for the *If This* step.

    ![Click Add for the If This step.](./using-ifttt-to-sync-object-data-with-google-sheets/images/02.png)

1. Select *Webhooks*.

    ![Select Webhooks.](./using-ifttt-to-sync-object-data-with-google-sheets/images/03.png)

1. For trigger type, click *Receive a web request*.

    ![Select a trigger type.](./using-ifttt-to-sync-object-data-with-google-sheets/images/04.png)

1. Enter an *Event Name* and click *Create Trigger*.

    ![Enter an Event Name and click Create Trigger.](./using-ifttt-to-sync-object-data-with-google-sheets/images/05.png)

1. Click *add* for the *Then That* step.

    ![Click add for the Then That step.](./using-ifttt-to-sync-object-data-with-google-sheets/images/06.png)

1. Select *Google Sheets*.

    ![Search for and select Google Sheets.](./using-ifttt-to-sync-object-data-with-google-sheets/images/07.png)

1. For action type, click *Add row to spreadsheet*.

    ![Click add row to spreadsheet.](./using-ifttt-to-sync-object-data-with-google-sheets/images/08.png)

1. Configure the action and click *Create Action*.

    ![Configure the action.](./using-ifttt-to-sync-object-data-with-google-sheets/images/09.png)

    ```{note}
    When connecting a Google account, this opens a window for selecting the desired Google account and granting IFTTT permission to access the account's files.
    ```

1. Click *Continue*.

    ![Click Continue.](./using-ifttt-to-sync-object-data-with-google-sheets/images/10.png)

1. Review and click *Finish*.

    ![Review and click Finish.](./using-ifttt-to-sync-object-data-with-google-sheets/images/11.png)

1. When you finish the process, your *Applet* is already *connected*.

    ![Review and click Finish.](./using-ifttt-to-sync-object-data-with-google-sheets/images/12.png)

1. Once created, click the *Webhook Icon* &rarr; *Documentation* and copy the generated *webhook URL*.

    ![Click the webhook icon.](./using-ifttt-to-sync-object-data-with-google-sheets/images/13.png)

    ![Copy the generated webhook URL.](./using-ifttt-to-sync-object-data-with-google-sheets/images/14.png)

1. In your Liferay instance, use the copied URL to [define an Object action](../../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

    ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-ifttt-to-sync-object-data-with-google-sheets/images/15.png)

## Additional Information

* [Objects Overview](../../../objects.md)
* [Creating and Managing Objects](../../creating-and-managing-objects.md)
* [Understanding Object Integrations](../../understanding-object-integrations.md)