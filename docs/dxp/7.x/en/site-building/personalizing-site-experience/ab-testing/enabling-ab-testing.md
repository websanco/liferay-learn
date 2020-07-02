# Enabling A/B Testing

1. [Connect your Liferay DXP Site to Analytics Cloud](https://learn.liferay.com/../../../../connect-liferay-dxp-to-ac.md) under the Liferay DXP *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings* &rarr; *Analytics Cloud* &rarr; *Analytics Cloud Connection*.

    ![Select the Site in the Lifeay DXP configuration for Analytics Cloud.](./configuring-ab-testing/images/01.png)

1. Publish the Content Page you want to run A/B tests on. Widget Pages don't support Experiences for different Segments.
1. Grant your Role [*Update* permissions](#configuring-update-permissions-for-your-users) for the Content Page if it doesn't already have them.

## Configuring Update Permissions for Your Users
    
1. Open the Product Menu and go to the Site Menu &rarr; *Site Builder* &rarr; *Pages*.
1. Click the Actions menu (![Action Menu](../../../images/icon-actions.png)) next to the Content Page and Select *Permissions*.
1. Check or verify the *Update* permission box for the Role(s) you want to grant A/B Testing access for and click *Save*.