# Using IFTTT to Sync Object Data with Google Sheets

You can use Liferay Objects with data integration tools to create automated tasks for syncing Object data with external services. These tasks are triggered using webhooks and can connect to Google applications, Microsoft Office, and more.

Here you'll learn how to use webhooks to trigger sync tasks between Liferay Objects and Google Sheets using IFTTT. Syncing your data in this way requires an IFTTT account, Google Spreadsheet, and active DXP 7.4 instance. The DXP instance must also have a published Object with the desired fields for sending or receiving data to the Google Spreadsheet.

## Syncing Object Data to a Google Sheet

Follow these steps to sync Object data to a Google Sheet:

1. Open IFTTT and click *Create*.

    ![Click Create.](./using-ifttt-to-sync-object-data-with-google-sheets/images/01.png)

1. Click *add* on the *Trigger* step. 

    ![Click add on the Trigger step.](./using-ifttt-to-sync-object-data-with-google-sheets/images/02.png)
   
1. Search for and select *Webhooks*.

    ![Search for and select Webhooks.](./using-ifttt-to-sync-object-data-with-google-sheets/images/03.png)

1. Choose a *Trigger* type.

    ![Choose a Trigger type.](./using-ifttt-to-sync-object-data-with-google-sheets/images/04.png)

1. Choose an *Event Name* and click on *Create Trigger*.

    ![Choose an Event Name and click on Create Trigger.](./using-ifttt-to-sync-object-data-with-google-sheets/images/05.png)

1. Click *add* on the *Action* step. 
 
    ![Click *add* on the Action step.](./using-ifttt-to-sync-object-data-with-google-sheets/images/06.png)

1. Search for and select *Google Sheets*.

    ![Search for and select Google Sheets.](./using-ifttt-to-sync-object-data-with-google-sheets/images/07.png)

1.  Choose an *Action* type.

    ![Choose a Action type.](./using-ifttt-to-sync-object-data-with-google-sheets/images/08.png)

1. Complete the Action's Fields and click on *Create Action*.

    ![Complete the Action's Fields.](./using-ifttt-to-sync-object-data-with-google-sheets/images/09.png)

    ```{note}
    when you choose a google account to connect, this opens a window for selecting the desired Google account and granting IFTTT permission to access the account's files.
    ```

1. Click *Continue*. 

    ![Click Continue.](./using-ifttt-to-sync-object-data-with-google-sheets/images/10.png) <!--Maybe the image is not necessary-->

1. Review and click *Finish*.

    ![Review and click Finish.](./using-ifttt-to-sync-object-data-with-google-sheets/images/11.png)

1. When you finish the process your *Applet* is already *connected*.

    ![Review and click Finish.](./using-ifttt-to-sync-object-data-with-google-sheets/images/12.png)

1. Once created, click on the *Webhook Icon* &rarr; Documentation &rarr; Copy the generated *webhook URL*. <!--maybe add a third image? This image would show the documentation step-->

    ![Webhook Icon.](./using-ifttt-to-sync-object-data-with-google-sheets/images/13.png)

    ![Copy the generated webhook URL.](./using-ifttt-to-sync-object-data-with-google-sheets/images/14.png)

1.  Use the copied URL to [define an Object action](../creating-and-managing-objects/defining-object-actions.md) that sends a request to the webhook endpoint whenever a new Object entry is added.

    ![Define an action that sends a request to the webhook endpoint whenever an entry is added.](./using-ifttt-to-sync-object-data-with-google-sheets/images/15.png)

## Additional Information

* [Objects Overview](../../objects.md)
* [Creating and Managing Objects](../creating-and-managing-objects.md)
* [Understanding Object Integrations](../understanding-object-integrations.md)