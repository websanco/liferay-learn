# Using the Autofill Rule

The Autofill Rule allows filtering a form field's options based on pre-configured conditions. The data all comes from the same [data provider](../introduction-to-data-providers.md), which can also [provide a list of options]((../using-data-providers-to-populate-form-options.md)) without conditions.

Here is an example of how to use the Autofill Rule to filter countries based on geographic region.

## Prerequisites

<!-- This make sit sound like any data provider will do. Don't we specifically need the restcountries.eu data provider form the other article? -->
1. [A data provider has been configured.](../using-data-providers-to-populate-form-options.md)
1. [Create a form](../../creating-forms.md) that has the following:

<!--It doesn't look from the screenshots and GIF like we're using the Rewards field, recommend removing if that's true -->
    * A Single Select field called _Rewards_ with two options: "Cash" or "All expenses paid trip"
    * A Text field called _Region_
    * A Select from List field called _Choose a Destination Country_ that uses the [restcountries.eu](https://restcountries.eu) data provider

## Configuring the Autofill Rule

To configure an Autofill rule:

1. Click the _Rules_ tab.
1. Click the Add (![Add](../../../../../images/icon-add.png)) button.
1. Select _Reward_ from the If Condition.
1. Create the rule: _Is equal to_ &rarr; _Value_ &rarr; _All expenses paid trip_.
1. Select _Autofill_ from the _Do_ Action dropdown menu.
1. Select the data provider from the _From Data Provider_ dropdown menu.
1. Select _Region_ from the _Region_ dropdown menu.
1. Select _Country_ from the _Country_ dropdown menu.

    ![Create the Autofill rule.](./using-the-autofill-rule/images/01.png)

1. Click _Save_ when finished.

## Verifying the Autofill Rule

1. Publish the form.
1. Navigate to the site where the form is displayed.
1. Enter a valid region into the Region field and observe that the options in the Select from List Field are filtered based on the Region. The [restcountries.eu](https://restcountries.eu) service has these regions: Africa, Americas, Asia, Europe, Oceania, and Polar.

    ![Filter countries by region of the world.](./using-the-autofill-rule/images/forms-autofill-region.gif)

Users can now search for a region then the countries in the form.

## Additional Information

* [Creating Forms](../../creating-forms.md)
* [Introduction to Data Providers](../introduction-to-data-providers.md)
* [Using Data Providers to Populate Form Options](../using-data-providers-to-populate-form-options.md)
