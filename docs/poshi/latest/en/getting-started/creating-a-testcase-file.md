# Creating the testcase file

## Test Scenario

Write your first testcase file using the test scenario below:

  Joe Bloggs, would like to test that the term "Pumpkin" does not appear on any learn.liferay.com article. The steps he would take are the following. Follow along manually on a separate browser to test the steps.

  1. Navigate to the [Liferay Learn site](http://learn.liferay.com).

  1. Click on the Search input field.

  1. Type in the search term, 'Pumpkin'.

  1. Press the enter key to enter the search term.

  1. Assert that there are no search results.

  1. Assert that a message appears saying there are no articles available.

## Building the testcase file

1. Create an empty file in your `poshi-standalone/poshi-tests/testcase` directory and name it `LiferayLearnSearch.testcase`.

1. Macro files start with the definition block. Add this to your `LiferayLearnSearch.testcase` file.

	```
	definition {

	}
	```

1. Within the definition block create the setUp block. Since the test starts with navigating to the Liferay Learn site, the setup block can contain the following function:

    ```
    setUp {
        task("Setup: Navigate to the Liferay Learn website.") {
          Default.Open(locator1 = "http://learn.liferay.com");
        }
    }
    ```

1. Start the test block by adding a description and test name. Name the test in a manner that is concise, specific, and descriptive. For the given test scenario, use `CannotSearchForTheTermPumpkins`.

    ```
    @description = "There should be no search results when a user searches for Pumpkins."
    test CannotSearchForPumpkins {

    }
    ```

1. Within the test block, add the macros from your `LiferayLearnSearch.macro` file in the order described in the test scenario. Don't forget to pass in the variable search term. For more on Variables, see [Using Variables](../poshi-basics/poshi-layers/variables.md). The resulting test should look like:

    ```
    definition {

      setUp {
        task("Setup: Navigate to the Liferay Learn website.") {
          Default.Open(locator1 = "http://learn.liferay.com");
        }
      }

      @description = "There should be no search results when a user searches for Pumpkins."
      test CannotSearchForPumpkins {
        task("When I search for the term 'Pumpkins'") {
          LiferayLearnSearch.searchForTerm(searchTerm = "Pumpkins");
        }

        task("Then I should see the no results message.") {
          LiferayLearnSearch.assertNoResults(searchTerm = "Pumpkins");
        }

      }
    }
    ```
