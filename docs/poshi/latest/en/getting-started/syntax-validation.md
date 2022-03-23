# Syntax Validation

The Poshi Standalone grade projects includes a validation task that can be used to check your code for syntax errors before running your test. To use this, simply run `./gradlew validatePoshi` on your root directory. If there are no errors, the command runs successfully indicated by the message:

  ```
  BUILD SUCCESSFUL in 1s
  1 actionable task: 1 executed
  ```

Errors in validation are descriptive and will point to the line of code where the error is. In the example below, there are two poshi errors:

1. The function `ThisIsNotAFunction` on line 12 of the `LiferayLearnSearch.macro` is not valid.

1. The macro command `LiferayLearnSearch#search` on line 12 of the `LiferayLearn.testcase` is not valid.

  ```
  Exception in thread "main"
  2 errors in POSHI

  Unable to find class ThisIsNotAFunction at:
  /L:/poshi-standalone/poshi-tests/macros/LiferayLearnSearch.macro:12
     5 |            value1 = "${searchTerm}");
     6 |
     7 |        Default.AssertElementPresent.assertVisible(locator1 = "LiferayLearn#NO_RESULTS_MESSAGE");
     8 |
     9 |    }
    10 |
    11 |    macro searchForTerm {
  > 12 |        ThisIsNotAFunction(
    13 |            locator1 = "LiferayLearn#SEARCH_BAR");
    14 |
    15 |        Default.Type(
    16 |            locator1 = "LiferayLearn#SEARCH_BAR",
    17 |            value1 = "${searchTerm}");
    18 |
    19 |        Default.KeyPress(

  Invalid macro command LiferayLearnSearch#search at:
  /L:/poshi-standalone/poshi-tests/testcases/LiferayLearn.testcase:12

    10 |   test CannotSearchForPumpkins {
          at com.liferay.poshi.core.PoshiValidation._throwExceptions(PoshiValidation.java:1870)
    11 |       task("When I search for the term 'Pumpkins'") {
          at com.liferay.poshi.core.PoshiValidation.validate(PoshiValidation.java:106)
  > 12 |           LiferayLearnSearch.search(searchTerm = "Pumpkins");
    13 |       }
    14 |
    15 |       task("Then I should see the no results message.") {
          at com.liferay.poshi.core.PoshiValidation.main(PoshiValidation.java:60)
    16 |           LiferayLearnSearch.assertNoResults(searchTerm = "Pumpkins");
    17 |       }
    18 |
    19 |   }

    FAILURE: Build failed with an exception.

  * What went wrong:
  Execution failed for task ':validatePoshi'.
  > Process 'command 'C:\Program Files\Java\jdk1.8.0_301\bin\java.exe'' finished with non-zero exit value 1

  * Try:
  Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

  * Get more help at https://help.gradle.org

  BUILD FAILED in 1s
  1 actionable task: 1 executed
```
