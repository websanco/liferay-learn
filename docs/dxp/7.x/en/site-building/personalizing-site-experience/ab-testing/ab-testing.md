# A/B Testing

> Available in Liferay DXP 7.2 SP1+.

With A/B Testing, you can show different versions of your content to different users and determine which version is more effective. Using A/B Testing, you maintain the current content and design of your webpage for the majority of the visitors, while providing an alternative page *Variant* for a selected group of them. The current page and the page Variant are then tested to determine which version performs better for a given goal (like *bounce rate*, *clickthrough*, or other).

Consider the following example. The Marketing team in a bank creates a Content Page advertising a new credit card. The page has been published for a few weeks, but a redesign might help to promote the new credit card better. With A/B Testing, the Marketing team can create a new page Variant and display the original page or the page Variant at random to visitors. Then, they can compare the clickthrough rate for each version of and find which page is more effective. If the new variant is more effective than the original page, they can publish it and replace the original page.

![Using A/B Test to compare the efficiency of two different call-to-action buttons](./ab-testing/images/01.png)

To run A/B Tests, you must have your Liferay DXP instance [connected to Liferay Analytics Cloud](../../../../../../analytics-cloud/latest/en/getting-started/connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.md). The overall process goes as follows:

* You create the A/B test in Liferay DXP.
* The A/B test is automatically synchronized with Analytics Cloud.
* You start or terminate the A/B test in Liferay DXP or Analytics Cloud.
* Liferay DXP and Analytics Cloud show your test's status and the winning variant when the test finishes.
* You manage other aspects of your A/B test in Analytics Cloud (test history, statistics, variant comparison, etc.).

For more information about working with A/B Testing in Analytics Cloud, see [A/B Testing](../../../../../../analytics-cloud/latest/en/touchpoints/a-b-testing.md) in the Analytics Cloud Learn Center.

## Related Information

- [Verifying A/B Test Requirements](./verifying-ab-test-requirements.md)
- [Creating A/B Tests](./creating-ab-tests.md)
- [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
- [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
