# Poshi Advantages

## Simplified Syntax

To make it easier for less technical testers to read and write test automation, Poshi uses a simplified Groovy-like script syntax. It is less wordy than most programming languages and automatically handles the importing of other test files. Poshi is also written so that the test writer does not need to understand Java or JavaScript in order to write new tests, script new behaviors, define page objects, or even create new methods. The String data type is supported with string manipulation and math operations being available.

## Page Objects Separated

Poshi uses the page object design pattern. However, unlike the traditional page object model, Poshi separates the page locators from the page interactions. Embracing the idea of test logic being separated from web elements, all page object locators are defined in a single layer called the path file. This single layer maintains all locators and contains a library of page element locators that can be used in any test case. The current Poshi pattern treats the product as a combination of elements, rather than a combination of pages. When a Poshi test writer needs to add a new test, it is likely that most of the objects are already defined and able to be referenced by an easy to read locator name. Also, when a UI change is made on the product or web page being tested, generally only one file needs to be updated instead of fixing all test cases.

## WebDriver Extended

Poshi implements and extends WebDriver methods to allow more helpful functions that goes beyond the functionality provided by Selenium. This implementation includes a custom interface of Selenium methods called _LiferaySelenium_ which provides new methods that enables your test to interact reliably with elements on a page. Together with the _*WebDriverImpl_ classes, wherein the LiferaySelenium interface is implemented and extended in browser-specific classes to override methods or initializations that may be required due to browser differences, Poshi provides a robust, reliable, and flexible solution to your test automation needs.
