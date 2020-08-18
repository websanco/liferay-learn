# A/B Testing

```note::
   Available for Liferay DXP 7.2 SP1+.
```

With A/B Testing, you can show different versions of your site to different users and determine which version is more effective. Using A/B Testing, you maintain the current content and design of your webpage for the majority of the visitors, while providing an alternative page variant for a selected group of them. The current page and the page variant(s) are then tested to determine which version performs better for a given goal (like bounce rate, clicks, etc.).

For example, a Marketing team for a bank provides a Content Page advertising a new credit card. The page has been published for a few weeks, but a redesign might help to promote the new credit card better. With A/B Testing, the team can create a new page variant and display both pages at random to visitors. Then, they can compare the clickthrough rate for the two pages and find which page is more effective. If the new variant is more effective than the original page, they can publish it and replace the original page.

![Using A/B Test to compare the efficiency of two different call-to-action buttons](./ab-testing/images/01.png)

To run A/B Tests, you must have your Liferay DXP instance connected to Liferay Analytics Cloud. Liferay Analytics Cloud shares this workflow with Liferay DXP:

* You create the A/B test in Liferay DXP.
* The A/B test is automatically synchronized with Analytics Cloud.
* You may run or terminate the A/B test in Liferay DXP or Analytics Cloud.
* Liferay DXP and Analytics Cloud show your test's status and the winning variant when the test finishes.
* You manage other aspects of your A/B test in Analytics Cloud (test history, statistics, variant comparison, etc.).

For more information about working with A/B Testing in Analytics Cloud, see [A/B Testing Analytics](/../../../../ab-testing-analytics.md).

## Related Information

* [Configuring A/B Testing](./configuring-ab-testing.md)
* [Creating A/B Tests](./creating-ab-tests.md)
* [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
* [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
