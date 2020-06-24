# Creating A/B Tests

Before creating A/B Tests, consider the following information:

- You can create a test for a default Experience or a personalized Experience mapped to a Segment.
- You can only create an A/B Test for one page or experience at a time.
- You cannot edit an Experience that is part of an A/B Test.
- You cannot create a test on a Experience that already has an active test running.
- When you delete a Content Page or Experience that is part of an A/B test, you also delete the test.

To create the A/B test, you must create the test first, and then you create one or more Test Variants.

## To Create the Test

1. Go to the Content Page where you want to run the A/B Test.
1. In the Control Menu, click the A/B Testing button (![A/B Test icon](../../../images/icon-ab-testing.png)).
1. If you have created additional Experiences for the Content Page, select the Experience you want to test.
1. Click *Create Test*.
1. Type a *Name* for your test and, optionally, a *Description*.
1. Select the goal you want to track with your test:

   - *Bounce Rate*: Percentage of users who arrive to the page, don't exhibit any activity (like click or scroll), and navigate away from the site without visiting another page.
   - *Click*: Percentage of users who clicked on the page (per session).

1. Click *Save*.

After you create the test, you can always click the A/B Testing button (![A/B Test icon](../../../../images/icon-ab-testing.png)) in the Content Page to view the test status and history.

## To Create the Test Variant

A test Variant is a customization of the Experience you want to optimize with A/B Testing. An A/B Test must have at least one Variant.

1. Go to the Content Page where you want to run the A/B Test.
1. In the Control Menu, click the A/B Testing button (![A/B Test icon](../../../images/icon-ab-testing.png)).
1. If you have created additional Experiences for the Content Page, select the Experience.
1. Under *Active Test* and *Variants* sections, click *Create Variant*.
1. Enter a name for the Variant and click *Save*.
1. Click the Edit button (![Edit icon](../../../images/icon-edit.png)) next to the Variant's name.

```note::
   The current Content Page is copied and displayed as the baseline for the Variant.
```

1. Edit the Variant with the changes you want to test.
1. Click *Save Variant*.
1. If you selected the *Click* goal for the A/B Test, you must also select the element you want to test:
    1. Under the *Active Test* and *Click Goal* sections, click *Set Element*.
    1. Click the element you want to test on the Content Page.
    1. Click *Set Element as Click Target*.
    
    ```note::
       You can select any clickable element with an ID attribute.
    ```
