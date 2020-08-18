# Configuring A/B Testing

Verify that the following conditions are met to run A/B Testing on your Content Pages:

- Liferay DXP is connected to Analytics Cloud. Fore information on how to set up this connection, see [Connect your Liferay DXP site to Analytics Cloud](/../../../../connect-liferay-dxp-to-ac.md).

```note::
   When setting up the connection to Analytics Cloud, remember to select the Site with the content you want to test under the Liferay DXP *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings* &rarr; *Analytics Cloud* &rarr; *Analytics Cloud Connection*.
```

![Selecting the Site in the Liferay DXP configuration for Analytics Cloud](configuring-ab-testing/images/01.png)

- Your page is a Content Page. Widget Pages do not support Experiences for different Segments.
- The Content Page you intend to test is published.
- You have *Update* permission in the Content Page (this permission is a requirement for running A/B Testing.)

  To verify or configure the *Update* permission, use the following steps:

    1. Go to *Site Administrator* &rarr; *Site Builder* &rarr; *Pages*.
    1. Click the Actions menu (![Action Menu](../../../images/icon-actions.png)) next to the Content Page and Select *Permissions*.
    1. Check or verify the *Update* permission box for the Role that must have access to the Content Page.
    1. Click *Save*.
