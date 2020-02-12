# Configuring Asset Publisher Subscriptions

The Asset Publisher supports two kinds of subscriptions: email subscriptions and RSS feed subscriptions.

## Email Subscriptions

Users can subscribe to the Asset Publisher to receive email notifications when new assets are published. You must enable this notification first. Follow these steps:

1. Hover over the Asset Publisher and click the Options icon (![Options](../../../images/icon-app-options.png)) in the widget's menu and select *Configuration*.
1. Toggle the *Enable Email Subscription* selector to Yes.
1. Fill in the form and click *Save* to apply the changes.

    ![An email subscription notifies users when new assets are published.](./enabling-subscriptions/images/01.png)

1. Enable the *Subscribe* feature under the *Display Settings* tab &rarr; [*Set and Enable* section](./configuring-display-settings.md#set-and-enable-options) if it's not enabled. Users can click the *subscribe* button to receive email notifications of newly published assets.

![Enabling Email Subscription adds a Subscribe link to the Asset Publisher.](./enabling-subscriptions/images/02.png)

### Configuring the Asset Check Interval

Liferay Portal periodically checks for new assets and sends emails to subscribed users informing them about the new assets. By default, assets are checked every twenty-four hours. You can change the check interval through System Settings. Follow these steps:

1. Open the Product Menu and go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.
1. Select *Assets* under the *Content and Data* heading.
1. Go to *System Scope* &rarr; *Asset Publisher*. 
1. Change the *Check Interval* setting to the interval (in hours) that you want to check for new assets and notify subscribed users, and click *Save* to apply the changes.

![The Check Interval settings specifies how often assets are checked for updates.](./enabling-subscriptions/images/03.png)

## RSS Feed Subscriptions

```note::
  RSS feeds are deprecated for Liferay Portal 7.2+ and are disabled by default. To leverage RSS feeds, you must enable this feature.
```

To enable RSS feed subscriptions for the Asset Publisher, follow these steps:

1. Open the Product Menu and go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.
1. Select *Web Content* under the *Content and Data* heading.
1. Under the *System Scope* &rarr; *Administration* tab, check the *Show Feeds* box. For more information on deprecated apps, see [this article](TODO:deprecated-apps).

    ![Enable RSS feeds through System Settings.](./enabling-subscriptions/images/04.png)

1. Navigate back to the Asset Publisher widget, hover over it and click the Options icon (![Options](../../../images/icon-app-options.png)) in the widget's menu and select *Configuration*.
1. Toggle the *Enable RSS Subscription* selector to Yes.
1. Fill in the form and click *Save* to apply the changes.

    ![An RSS subscription sends RSS feeds to subscribers' RSS readers.](./enabling-subscriptions/images/05.png)

1. Enable the *Subscribe* feature under the *Display Settings* tab &rarr; [*Set and Enable* section](./configuring-display-settings.md#set-and-enable-options) if it's not enabled. Users can click the *RSS* link to subscribe to the RSS feed.

![Enabling RSS Subscription adds a RSS link to the Asset Publisher.](./enabling-subscriptions/images/06.png)

## Related Information

* [Setting up Email](TODO)