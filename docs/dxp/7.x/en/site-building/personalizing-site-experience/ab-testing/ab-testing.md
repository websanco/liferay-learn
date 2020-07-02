# A/B Testing

```note::
   Available for Liferay DXP 7.2 SP1+.
```

A/B Testing lets you show different versions of your site randomly to different users to determine which version is more effective. You maintain the current content and design of your webpage for the majority of the visitors, while providing an alternative *test variant*---a customization
of the Experience you want to optimize---for a selected group of them. The current page and page variant(s) are then tested to determine which version performs better for a given goal (bounce rate, clicks, etc.). For example, a Marketing team for a bank can create an A/B Test for an underperforming credit card advertisement page to see if a page redesign can improve sales. If after comparing the clickthrough rate for the two pages they find that the page redesign is more effective, they can publish it to replace the original page.

To run A/B Tests, you must have your Liferay DXP instance connected to Liferay Analytics Cloud. Liferay Analytics Cloud shares this workflow with Liferay DXP:

* You create the A/B test in Liferay DXP.
* The A/B test is automatically synchronized with Analytics Cloud.
* You run or terminate the A/B test in Liferay DXP. 
* Liferay DXP shows your test's status and the winning variant when the test finishes.
* You manage other aspects of your A/B test in Analytics Cloud (testing history, statistics, test's status, etc.).

For more information about working with A/B Testing in Analytics Cloud, see [A/B Testing Analytics](https://learn.liferay.com/../../../../ab-testing-analytics.md).
