# Connecting Liferay DXP to Analytics Cloud

Your Liferay DXP instances are rich with web analytics data and visitor data from Users and. In order to monitor and collect website visitor information, you need to setup a connection between your Liferay DXP site with Analytics Cloud by adding a Data Source.

There are two ways to connect:

* Connect using the Access Token (recommended)
* Connect using OAuth

## Using an Access Token

To connect using an Access Token, your Liferay DXP installation must meet the following fixpack requirements:

* 7.2 Fix Pack 5/SP2
* 7.1 Fix Pack 18/SP4
* 7.0 Fix Pack 90/SP13

If you do not meet the requirements and are not able to upgrade to the minimum fixpack requirement, you can [connect using OAuth](./connecting-liferay-dxp-using-oauth.md) instead.

![Connecting a DXP Site to Analytics Cloud can be done using a token or with OAuth.](connecting-liferay-dxp-to-analytics-cloud/images/01.png)

### Adding a Data Source

1. Create a data source by navigating to *Setting* > *Data Sources* > *Add Data Source*. You must have the Admin role to perform this action.

1. Select Liferay DXP as your data source type. You will see a screen that provides the token to copy.

      ![Analytics Cloud provides a token to copy.](connecting-liferay-dxp-to-analytics-cloud/images/02.png)

1. Copy the token and navigate to the Liferay DXP instance. In the *Control Panel* under *Configuration* > *Instance Setting* > *Analytics Cloud*. Paste the Access Token in the Analytics Cloud Token field as shown in the image below, then press Connect.

      ![Adding the Analytics Cloud token to a Liferay DXP installations Instance Settings configuration.](connecting-liferay-dxp-to-analytics-cloud/images/03.png)

```note::
   For Liferay DXP 7.0, Analytics Cloud Admin is under *Configuration* > *Analytics Cloud*.
```

When the connection is successful, the message, `Your DXP instance is connected to Analytics Cloud.` is shown.

![A success message confirms correctly configuring a connection between DXP and Analytics Cloud.](connecting-liferay-dxp-to-analytics-cloud/images/04.png)

Congratulation, your DXP is now connected to your AC workspace!

## Next Steps

* [Tracking Sites and Individuals with Properties](./tracking-sites-and-individuals-using-properties.md)
* [Connecting Liferay DXP Using OAuth](./connecting-liferay-dxp-using-oauth.md)
