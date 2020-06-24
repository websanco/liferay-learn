# Real-Time Alerts

Liferay DXP Cloud can alert system administrators of unexpected behaviors in a project. Examples of unexpected behaviors include auto-scaling events, higher than expected memory consumption, reaching the allotted CPU quota, and database connection issues.

## Alert Preferences

Users can set [Alert Preferences](https://console.liferay.cloud/account/alerts-preferences) for all environments via the DXP Cloud Console.

1. Click the *User Profile* icon at the top-right of the console navigation bar.
1. Click on *Alerts Preferences*.
1. Select the type of alerts you want to receive by clicking the checkbox for your preferred method of notification. To disable an alert, unselect the associated checkbox.
1. Click *Save Alerts Preferences* when finished.

![Real Time Alerts](./real-time-alerts/images/01.png)

Users can also access alert preferences from the Alerts page.

## Alerts Page

View and manage alerts delivered to your console from the [Alerts page](https://console.liferay.cloud/alerts). You can access this page by clicking the bell icon at the top-right of the page.

The Alerts table lists the following alert information:

* **Status**: This column indicates the alert's current state. Each alert is triggered by a specific event. If the event is still happening, its alert status is *Ongoing*. If the event is finished, the alert status is *Resolved*.
* **Started at**: This column indicates when the alert began.
* **Duration**: This column indicates how long the alert lasted.

Filter and manage alerts using the buttons above the table:

* **Unread alerts/All alerts**: View only unread alerts, or all alerts.
* **All environments/other environments**: Filter alerts by environment.
* **Mark all as read**: Mark all alerts as read.
* **Alerts Preferences**: Go to your alert preferences page.

![Real Time Alerts](./real-time-alerts/images/02.png)

## Additional Information

* [Application Metrics](https://learn.liferay.com/dxp-cloud/latest/en/manage-and-optimize/application-metrics.html)
* [Quotas](https://learn.liferay.com/dxp-cloud/latest/en/manage-and-optimize/quotas.html)
* [Team Activities](https://learn.liferay.com/dxp-cloud/latest/en/manage-and-optimize/team-activities.html)
