# Creating a .macro file

## Test Scenario

Write your first macro file using the test scenario below:

  Joe Bloggs, would like to test that the term "Pumpkin" does not appear on any learn.liferay.com article. The steps he would take are the following. Follow along manually on a separate browser to test the steps.

  1. Navigate to the [Liferay Learn site](http://learn.liferay.com).

  1. Click on the Search input field.

  1. Type in the search term, 'Pumpkin'.

  1. Press the enter key to enter the search term.

  1. Assert that there are no search results.

  1. Assert that a message appears saying there are no articles available.

## Building the macro file

1. Create an empty file in your `poshi-standalone/poshi-tests/macros` directory and name it `LiferayLearnSearch.macro`.

1. Macro files start with the definition block. Add this to your `LiferayLearnSearch.macro` file.

	```
	definition {

	}
	```

1. Within the definition block of the .macro file, macro blocks are used to define individual macros. To do so, use the macro keyword, followed by a string identifier name. These macro names must be unique and descriptive of the intended interaction. See [Macros](../poshi-basics/poshi-layers/macros.md) for more information. In the given scenario, the macro to search for a term can simply be called `searchForTerm`.

1. 	Steps 2 to 4 from the test scenario are actions that together make a single Search interaction. These three steps will be included within the `searchForTerm` macro block. These actions can be scripted by using [Functions](https://github.com/liferay/liferay-portal/tree/master/modules/test/poshi/poshi-runner-resources/src/main/resources/default/testFunctional/functions) with the locators created in the [Creating A Path file](./creating-a-path-file.md) article. For more information on using functions can be found in [Functions](../poshi-basics/poshi-layers/functions.md).

	The resulting macro is as follows:

	```
	macro searchForTerm {
		Default.Click(
			locator1 = "LiferayLearn#SEARCH_BAR");

		Default.Type(
			locator1 = "LiferayLearn#SEARCH_BAR",
			value1 = "${searchTerm}");

		Default.KeyPress(
			locator1 = "LiferayLearn#SEARCH_BAR",
			value1 = "\ENTER");
	}
	```

1. Apart from the `searchForTerm` macro, the test scenario calls for a second macro to assert the results. Though steps 5 and 6 are separate assertions, together they form a single user interaction of asserting that there are no search results. This macro can be named `assertNoResults`.

	```
	macro assertNoResults {
		Default.AssertElementNotPresent(locator1 = "LiferayLearn#SEARCH_RESULTS",
			value1 = "${searchTerm}");

		Default.AssertElementPresent.assertVisible(locator1 = "LiferayLearn#NO_RESULTS_MESSAGE");
	}
	```

1. The resulting macro file should look like the following:

	```
	definition {

		macro assertNoResults {
			Default.AssertElementNotPresent(locator1 = "LiferayLearn#SEARCH_RESULTS",
				value1 = "${searchTerm}");

			Default.AssertElementPresent.assertVisible(locator1 = "LiferayLearn#NO_RESULTS_MESSAGE");

		}

		macro searchForTerm {
			Default.Click(
				locator1 = "LiferayLearn#SEARCH_BAR");

			Default.Type(
				locator1 = "LiferayLearn#SEARCH_BAR",
				value1 = "${searchTerm}");

			Default.KeyPress(
				locator1 = "LiferayLearn#SEARCH_BAR",
				value1 = "\ENTER");
		}
	}
	```
