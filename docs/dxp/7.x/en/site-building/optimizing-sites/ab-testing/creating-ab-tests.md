# Creating A/B Tests

Before creating A/B tests, remember these things: 

- You can create a test for a default Experience or a personalized Experience mapped to a Segment.
- You can only create an A/B Test for one page or Experience at a time.
- You cannot edit an Experience that is part of a running A/B Test.
- You cannot create a test for an Experience that already has an active A/B Test running.
- When you delete a Content Page or Experience that is part of an A/B Test, you also delete the test.

To create the A/B test, [create the test](#creating-the-test) first, and then create one or more [test Variants](#creating-the-test-variants).

```note::
Verify that your environment `meets the requirements <./verifying-ab-test-requirements.md>`_ for A/B Testing before creating the test.
```

## Creating the Test

1. Go to the Content Page you want to test. 
1. In the Control Menu, click *A/B Testing* (![A/B Test icon](../../../images/icon-ab-testing.png)).
1. If you have additional Experiences for the Content Page, select the Experience you want to test.
1. Click *Create Test*.
1. Enter a *Name* for your test and optionally a *Description*.
1. Select the goal to track with your test:

   - *Bounce Rate*: Percentage of those who visit, don't exhibit any activity (like *click* or *scroll*), and navigate away without visiting another page.
   - *Click*: Percentage of those who click on the page, per session.

1. Click *Save* to save the test as a *Draft* (not yet visible to visitors).

    ```note::
      You can always edit or delete the new A/B test by clicking the *Actions* button in the top right of the A/B Test menu. Deleted tests are not recoverable (i.e., not sent to the Recycle Bin). These options are not available for an active running test.
    ```

## Creating the Test Variant

A test Variant is a customization of the Experience you want to optimize with A/B Testing. Each A/B Test must have at least one Variant.

1. Open the A/B Test sidebar panel for your Content Page.
1. If you selected the *Click* goal for the A/B Test, you must also select the element you want to test:
    1. Under the *Active Test* tab and *Click Goal* section, click *Set Element*.

     ![Click the Set Element to configure the element for your test](./creating-ab-tests/images/03.png)

    1. Click the element you want to test on the Content Page.
    1. Click *Set Element as Click Target*. 

     ![Click the Set Element as Click Target button to select it.](./creating-ab-tests/images/01.png)

     ```note::
        Only links and buttons with an ID attribute can be selected as a target for the click goal. The click target element applies to the whole A/B Test, and must be present in all variants.
     ```

1. Under the *Active Test* tab and *Variants* section, click *Create Variant*.
1. Enter a name for the variant and click *Save*.
1. Click the *Edit* button (![Edit icon](../../../images/icon-edit.png)) next to the variant's name. The current Content Page is displayed as the baseline for the variant.

1. Edit the variant with the changes you want to test. For example, if you want to test a different call-to-action text, update the button with the new text.
1. Click *Save Variant*.

After creating your test and test variant, you can [run the test](./running-and-monitoring-ab-tests).

## Related Information

- [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
- [A/B Testing](./ab-testing.md)
- [Configuring A/B Testing](./configuring-ab-testing.md)
- [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
