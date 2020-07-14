# Using the Autofill Rule

Normally, a Form field's options may be populated by using a [data provider](../introduction-to-data-providers.md). For example, users can populate the list of countries and their states and provinces. See [Using Data Providers to Populate Form Options](../using-data-providers-to-populate-form-options.md) to learn more about setting up a data provider and populating a field.

The Autofill Rule allows users to populate a field based on specific conditions. In this example, users can list countries sorted by their geographic regions (the Americas, Europe, or Asia).

## Prerequisites

1. [A data provider has been configured.](../using-data-providers-to-populate-form-options.md)
1. [Create a form](../../creating-forms.md) that has the following:

    * A Single Select field called _Rewards_ with two options: "Cash" or "All expenses paid trip"
    * A Text field called _Region_
    * A Select from List field called _Choose a Destination Country_

## Configuring the Autofill Rule

To configure an Autofill rule:

1. Click the _Rules_ tab.
1. Click the Add (![Add](../../../images/icon-add.png)) button.
1. Define the rule:

    * If field *If I win I'd like my award to be* is equal to the Value *All Expenses Paid Vacation*, Autofill the *Choose a Destination Country* field from the *countries* data provider (note that you might have named this differently when setting it up).

    ![Figure 1: Build form rules quickly by defining your conditions and actions.](../../../images/forms-autofill.png)

1. Click _Save form_ when finished.

![Once a rule is saved, it is displayed so that you can easily understand what it does.](../../../images/forms-autofill2.png)

1. Create a form with these fields:

    **Text:** Use the Label *Region*.

    **Select from List:** Label it *Country*, and choose *From Autofill* under Create List.

    ![Create a form with a text field and a select from list field. These are used to provide the input to the data provider and be autofilled by its output.](../../../images/forms-autofill-input-output-fields.png)

1. Configure the Autofill rule.

    **Condition:** If *Region* **Is not Empty**

    **Action:** Do **Autofill** From Data Provider `restcountries`, Data Provider's Input: region---*Region*, Data Provider's Output: name---*Country*.

    ![Create the autofill rule.](../../../images/forms-autofill-rule.png)

Once you're done, publish the form and try it out, by entering a valid region into the Region field, and observing that the options in the Select from List Field are filtered based on the Region. The [restcountries.eu](https://restcountries.eu) service has these regions you can use: Africa, Americas, Asia, Europe, Oceania, and Polar.

![Filter countries by region of the world.](../../../images/forms-autofill-region.gif)

Autofill rules combine the power of data providers and form rules.
