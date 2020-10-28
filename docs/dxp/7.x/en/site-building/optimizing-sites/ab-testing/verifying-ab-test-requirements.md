# Verifying A/B Test Requirements

Verify that the following requirements are met before running A/B Testing on your Content Pages:

- Liferay DXP is connected to Analytics Cloud. Fore information on how to set up this connection, see [Connect your Liferay DXP site to Analytics Cloud]((../../../../../../analytics-cloud/latest/en/getting-started/connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.md)).

    ```note::
       When setting up the connection to Analytics Cloud, you must select the Site with the content you want to test under the Liferay DXP *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings* &rarr; *Analytics Cloud* &rarr; *Analytics Cloud Connection*.
    ```

    ![Selecting the Site in the Liferay DXP configuration for Analytics Cloud](verifying-ab-test-requirements/images/01.png)

- Your page is a [Content Page](../../creating-pages/understanding-pages/understanding-pages.md). Widget Pages do not support Experiences for different Segments.
- The Content Page you intend to test is published.
- You have the *Update* permission in the Content Page.

  To verify or configure the *Update* permission:

    1. Go to *Site Administrator* &rarr; *Site Builder* &rarr; *Pages*.
    1. Click the Actions menu (![Action Menu](../../../images/icon-actions.png)) next to the Content Page and Select *Permissions*.
    1. Check or verify the *Update* permission box for the Role that must have access to the Content Page.
    1. Click *Save*.

## Related Information

- [A/B Testing](./ab-testing.md)
- [Creating A/B Tests](./creating-ab-tests.md)
- [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
- [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
