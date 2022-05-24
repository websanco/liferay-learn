# Auditing Object Definition Events

{bdg-secondary}`Available DXP 7.4 U24+ and Portal GA24+`

The Objects application is integrated with Liferay's Audit framework, so you can keep track of events related to Object definitions. The Audit application lists these events in a table and includes both user and resource information.

To access the Audit application, open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), go to the *Control Panel* tab, and click *Audit*.

![Click on Audit in the Control Panel to view events related to Object definitions.](./auditing-object-definition-events/images/01.png)

The table includes the following columns:

| Column | Description |
| :--- | :--- |
| User ID | Acting user's ID |
| User Name | Acting user's name |
| Resource ID | Affected resource's ID |
| Resource Name | Affected resource's name |
| Resource Action | Action performed (`ADD`, `UPDATE`, or `DELETE`) |
| Client IP | IP address responsible for the action |
| Create Date | When the event record was created |

You can click on any event field to view more a more complete record, including specific action details.

![Click on an event field to view more details.](./auditing-object-definition-events/images/02.png)

If desired, you can search and filter results using keywords and other facets.

## Additional Information

* [Objects](../../objects.md)
* [Creating and Managing Objects](../creating-and-managing-objects.md)
