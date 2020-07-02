# Reviewing A/B Test Results and Publishing Test Variants

When the A/B Test finishes, Analytics Cloud computes and presents the results. You can find the test results in both Analytics Cloud and Liferay DXP. Analytics Cloud only declares a winning variant when the *Confidence Level Required* is achieved during the duration of the test. When the *Confidence Level Required* isn't met, Analytics Cloud doesn't declare a winner. To understand how to configure the *Confidence Level Required* for your test, see [Creating A/B Tests](./creating-ab-tests.md).

To review the A/B Test results and publish your variant,

1. Go to the Content Page the A/B Test is running on.
1. In the Control Menu, click the *A/B Testing* flask icon (![A/B Test icon](../../../images/icon-ab-testing.png)). Since Liferay DXP 7.3, you can also access the A/B Test panel (via the flask icon) and view the test status for an experience through the [experience selection dialog](../experience-personalization/content-page-personalization.md).
1. If you have created additional Experiences for the Content Page, select the Experience.
1. Review the test results under the *Active Test* section:
    
    * *Winner Declared*: One of the test variants met the Confidence Level Required.
    * *No Winner*: None of the test variants met the Confidence Level Required for the duration of the test.

    ![Review A/B Test Results from the A/B Test panel.](reviewing-ab-test-results-and-publishing-test-variants/images/01.png)

1. Click *Publish* next to the winning variant (marked with a checkmark) or a non-winning variant to replace the original page, or click *Discard Test* to ignore the A/B test recommendations and keep the original Content Page.

    ![You can publish the winning variant or discard the A/B Test results.](reviewing-ab-test-results-and-publishing-test-variants/images/02.png)

You can click the *View Data in Analytics Cloud* button from the A/B Tests panel to go to the Analytics Cloud dashboard to view your test's traffic, reports, statistics, etc.. For more information, see [A/B Testing Analytics](https://learn.liferay.com/../../../../ab-testing-analytics.md).