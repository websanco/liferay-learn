# Configuring A/B Testing

You can run A/B Testing on any Content Page when all the following conditions are met:

- Liferay DXP is connected to Analytics Cloud. Fore information on how to set up this connection, see [Connecting Liferay DXP using OAuth](https://learn.liferay.com/../../../../connecting-liferay-dxp-using-oauth.md).
- Your page is a Content Page. Widget Pages do not support Experiences for different Segments.
- The Content Page you intend to test is published.
- You have *Update* permissions in the Content Page.

  To verify or configure the *Update* permission, use the following steps
    
    1. Go to *Site Administrator* &rarr; *Site Builder* &rarr; *Pages*.
    1. Click the Actions button () next to the Content Page and Select *Permissions*.
    1. Check or verify the *Update* permission box for the Role that must have access to the Content Page.
    1. Click Save.

In A/B Testing, Liferay DXP works along with Liferay Analytics Cloud in the following way:
<!-- cross-reference for Liferay Analytics Cloud needed -->

1. You create the A/B test in Liferay DXP.
1. The A/B test is automatically synchronized with Analytics Cloud.
1. You run the A/B test in Liferay DXP.
   
   Liferay DXP only shows your test's status and the winning Variant when the test finishes.

1. You manage the different aspects of your A/B test in Analytics Cloud (testing history, statistics, test's status, etc.).

For more information about working with A/B Testing in Analytics Cloud, see [A/B Testing Analytics](https://learn.liferay.com/../../../../ab-testing-analytics.md).
<!-- cross-reference for the corresponding Liferay Analytics article needed -->




