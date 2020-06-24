# Running and Monitoring A/B Tests

You can configure the following parameters when you run the A/B Test:

- *Traffic Split*: the percentage of visitors that are randomly split between the Variants when visiting the page. A visitor is randomly assigned to a Variant and always sees the same Variant until the test is finished.
- *Confidence Level Required*: represents the accuracy of your test. The higher the required confidence level, the longer it takes to declare the winning Variant.

The *Estimated Time to Declare Winner* provides an estimation of the test duration. This estimation is based on the *Traffic Split* and *Confidence Level Required* configurations, as well as on the estimated page traffic (based on the traffic history provided by Analytics Cloud.)

(![A/B Test Run Configurations](running-and-monitoring-ab-tests/images/01.png))

## To Run an A/B Test

1. Go to the Content Page where you want to run the A/B Test.
1. In the Control Menu, click the A/B Testing button (![A/B Test icon](../../../../images/icon-ab-testing.png)).
1. If you have created additional Experiences for the Content Page, select the Experience.
1. Under the *Active Test* section, click *Review and Run Test*.
1. Optionally, configure the *Traffic Split* and *Confidence Level Required* settings for your test.
1. Click *Run* and then, click *OK*.

You can cancel a running test at any time using the *Terminate Test* button.

```note::
   To delete an A/B Test, you must cancel the test first.
```

Liferay DXP only shows your test's status and the winning Variant when the test finishes. You can manage the different aspects of your A/B test in Analytics Cloud (testing history, statistics, test's status, etc.). For more information, see [A/B Testing Analytics](https://learn.liferay.com/../../../../ab-testing-analytics.md).