# Using File Expiration and Review Dates

> Available: Liferay DXP 7.4+

With the Documents and Media application, you can set expiration and review dates for files. This can help you manage any digital assets that are only valid for a limited period of time (e.g., due to licensing). When [enabled](#enabling-expiration-and-review-dates), the application checks for any files that have reached their expiration or review date. This check occurs at [configurable intervals](#configuring-the-check-interval), according to the Documents and Media system setting.

If a file has reached its expiration or review date, a [notification email](#configuring-notification-emails) is automatically sent to file owners and reviewers, as well as any users subscribed to the folder containing the file.

Additionally, when a file expires, it is deactivated and cannot be used or edited in Liferay DXP. To reactivate the file, you must change its expiration date, or disable file expiration. While expired, the asset cannot be accessed in other applications (e.g, Web Content, Blogs) or via URL.

By contrast, when a file requires review, it remains available for use. Reaching the file's review date only triggers user notifications and does not affect the file in any way.

## Enabling Expiration and Review Dates

By default, files are set to never expire or require review. However, you can enable and set expiration and review dates when uploading or editing files.

When uploading or editing a single file, these settings appear as follows.

![Enable or disable expiration and review for an individual file.](./using-file-expiration-and-review-dates/images/01.png)

When uploading multiple files, expiration and review settings appear as follows.

![Enable or disable expiration and review for multiple files.](./using-file-expiration-and-review-dates/images/02.png)

To activate expiration or review, simply uncheck the *Never Expire* or *Never Review* checkbox. The default review and expiration dates are set to one year from the time of activation. If desired, you can set an alternative date and time.

Liferay's system only checks for files that have reached their expiration or review date according to the set [check interval](#configuring-the-check-interval).

When a date has been reached, relevant users are automatically [notified](#configuring-notification-emails). If the file is expired, it is no longer available for use on the platform.

## Configuring the Check Interval

By default, the Documents and Media application is configured to check file expiration and review dates every 15 minutes. However, you can configure this interval via the Control Panel.

1. Open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), click on the *Control Panel* tab, and go to *System Settings* &rarr; *Document and Media* &rarr; *Service*.

1. Use the *Check Interval* field to determine the number of minutes between file checks.

   ![Enter the number of minutes between checks.](./using-file-expiration-and-review-dates/images/03.png)

1. Click on *Save* when finished.

Once saved, the system will automatically check for files that have reached their expiration or review date according to the set interval.

## Configuring Notification Emails

When a file expires or requires review, a notification email is sent to the file's owners, reviewers, or any users subscribed to the folder containing the file. Documents and Media provides a default notification message, but you can edit the message if desired.

1. Navigate to the *Documents and Media* application in a Site or Asset Library.

1. Click on the Actions button (![Actions Button](../../../images/icon-actions.png)) in the Application Bar, and select *Configuration*.

1. In the modal window, click on the *Documents Needs Review Email* or *Document Expired Email* tabs.

   ![Edit the notification emails in the Configuration modal window.](./using-file-expiration-and-review-dates/images/04.png)

1. Draft the desired notification message.

1. Click on *Save* when finished.

## Additional Information

* [Uploading Files](./uploading-files.md)
* [Documents and Media Overview](../documents-and-media-overview.md)
* [Documents and Media UI Reference](../documents-and-media-ui-reference.md)
