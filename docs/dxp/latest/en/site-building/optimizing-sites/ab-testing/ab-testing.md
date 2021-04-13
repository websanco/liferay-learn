# A/B Testing

> Available in Liferay DXP 7.2 SP1+.

With A/B Testing, you can determine the most effective version of your content by testing variations on different users. The majority of visitors see the current content and design of your page, while a select group sees an alternative *Variant*. You can then test the performance of the current page and the variant to determine which version performs better for a given goal, such as *bounce rate* or *clickthrough*.

Consider the following example. A bank's marketing team creates a Content Page advertising a new credit card. The page has been published for a few weeks, but a redesign might help to promote the new credit card better. The marketing team uses A/B Testing to test a new page Variant at random to visitors so they can compare the clickthrough rate for each page version. If the new variant is more effective than the original page, they can publish it and replace the original page.

![Using A/B Test to compare the efficiency of two different call-to-action buttons](./ab-testing/images/01.png)

To run A/B Tests, you must have your Liferay DXP instance [connected to Liferay Analytics Cloud](https://learn.liferay.com/analytics-cloud/latest/en/connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.html). Here's the process:

* You create the A/B test in Liferay DXP.
* The A/B test automatically synchronizes with Analytics Cloud.
* You start or terminate the A/B test in Liferay DXP or Analytics Cloud.
* Liferay DXP and Analytics Cloud show your test's status and the winning variant when the test finishes.
* You manage other aspects of your A/B test in Analytics Cloud (test history, statistics, variant comparison, etc.)

For more information about working with A/B Testing in Analytics Cloud, [A/B Testing](https://learn.liferay.com/analytics-cloud/latest/en/touchpoints/a-b-testing.html).

## Related Information

- [Verifying A/B Test Requirements](./verifying-ab-test-requirements.md)
- [Creating A/B Tests](./creating-ab-tests.md)
- [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
- [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
