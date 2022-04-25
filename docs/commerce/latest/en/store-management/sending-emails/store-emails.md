# Store Emails

You can configure Liferay Commerce to send email notifications for a variety of store event triggers. Templates are customizable and define the content of an email. You can configure a single event to trigger emails to different target audiences using OOTB wildcards or by specifying the email addresses you want to send them to. For each of them, you must use a notification template.

```{note}
To use Liferay Commerce's Notifications feature, first configure the Mail settings for Liferay Digital Experience Platform (DXP). See [Configuring Mail](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/setting-up-liferay/configuring-mail.html) for more information.
```

## Configuring Store Email Notifications

After configuring Liferay Commerce's mail settings, navigate to the _Control Panel_ &rarr; _Commerce_ &rarr; _Channels_ to begin creating store email notifications. Email notifications are configured per [channel](../../store-management/channels/introduction-to-channels.md). Using an [accelerator](../../starting-a-store/accelerators.md) creates a store, catalog, and channel for you to start with.

![The Notification Templates available out-of-the-box.](./store-emails/images/02.png)

## Event Triggers

There are ten events that can trigger email notifications. You can use one of the out-of-the-box Notification Templates to create a standardized email notification.

| Notification Type | Event |
| :--- | :---|
| Order Placed | The store has received an order. |
| Order Processing | The store has begun processing an order. |
| Order Awaiting Shipment | The order is ready to be shipped. |
| Order Partially Shipped | The customer is notified if the items are being shipped separately. |
| Order Shipped | The order has been shipped. |
| Order Completed | Order delivered and marked as complete |
| Subscription Renewed | Renewal of a subscription (recurring order) |
| Subscription Activated | Activation of a subscription |
| Subscription Suspended | Suspension of a subscription pending review or action by the store. |
| Subscription Canceled | Cancellation of a subscription |

![Configuring a Notification Templates for a Channel.](./store-emails/images/01.png)

## Viewing the Notification Queue

You can view all email notifications in a channel's _Notification Queue_. Here, you can verify the status of emails that have been triggered by an event.

![The Notification Queue contains a list of the triggered notifications.](./store-emails/images/03.png)

By default, the system checks the Notification Queue at 15 minute intervals for unsent notifications. See [Configuring the Commerce Notification Queue](./configuring-the-commerce-notification-queue.md) article to learn more about changing the Check Interval.

## Customizing an Email Notification Template

You can customize the Notification Templates with your own text and wild card values that get substituted for key values in the _Email Settings_ and _Body_ fields. Some of the wildcards include a customer's name, the Order ID, shipping and billing addresses, and a list of items in the order.

For example, the _Email Body_ field can contain:

```
Dear [%ORDER_CREATOR%],

Your [%ORDER_ID%] has been shipped to [%ORDER_SHIPPING_ADDRESS%].
```

The wild cards are then replaced with the relevant content when sending the email.

See [Notification Template Variables Reference Guide](./notification-template-variables-reference-guide.md) to learn more.

## Viewing Order Communications History

Email notifications related to an order are tracked and you can review them under the *Emails* tab of an order. Navigate to the _Control Panel_ &rarr; _Commerce_ &rarr; _Orders_. Click on an Order ID then the _Email_ tab. See [Orders Information](../../order-management/orders/order-information.md) to learn more.

![You can view the triggered notification under the Emails tab of an Order.](./store-emails/images/04.png)

## Additional Information

* [Using Notification Templates](./using-notification-templates.md)
* [Configuring the Commerce Notification Queue](./configuring-the-commerce-notification-queue.md)
* [Notification Template Variables Reference Guide](./notification-template-variables-reference-guide.md)
