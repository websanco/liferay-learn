# Creating A Path File

## Test Scenario

Write your first path file using the test scenario below:

  You would like to test that your name does not appear on any learn.liferay.com article. The steps you would take are the following. Follow along manually on a separate browser to test the steps.

  1. Navigate to the [Liferay Learn site](http://learn.liferay.com).

  1. Click on the Search input field.

  1. Type in your name as the search term.

  1. Press the enter key to enter the search term.

  1. Assert that there are no search results. If searching for your name returns results, please pick a different name.

  1. Assert that a message appears saying there are no articles available.

## Gathering the Locators

From the given test scenario, gather the elements that your test needs to interact with.

1. You will need the URL for the Liferay Learn site: `http://learn.liferay.com`.

1. Open the browser's Web Developer Console (F12; this displays the DOM). The console will have an icon with an arrow that allows you to inspect the search bar to get the locator. This is called the selector.

1. Using the selector icon, click on the search bar and determine the locator from the highlighted portion of the DOM. In this case, use `//input[@id="docsSearch"]`.

1. Search for your name to navigate to the search results page.

1. Again, using the selector icon from the Web Developer Console, click on the "No Results" message and determine the path. In this case, use `//h4[@id="noResultsMessage"]`.

1. For an added assertion, search for a term that will surely have results, for example, search for the term "Staging". When the search results are displayed, use the selector icon to get the path for one of the results. In this case, use `//ul[@class='search']//a[contains(.,'Staging')]`. Note that the term 'Staging' is specific to this search result and makes the path inflexible to be used in other scenarios. To make this path reusable, use a variable that can be passed on from the testcase: `//ul[@class='search']//a[contains(.,'${key_searchTerm}')]`. See [Using Variables](../poshi-basics/poshi-layers/variables.md) for more information.

## Building the Path File

1. Start with an empty file in your `poshi-standalone/poshi-tests/paths` directory and name it `LiferayLearn.path`.

1. Add the html tags to create a table and add the file name as the title and the table header.

1. Name the locators and add the xpaths to each row as in the example below:

```
<html>
<head>
<title>LiferayLearn</title>
</head>

<body>
<table border="1" cellpadding="1" cellspacing="1">
<thead>
<tr><td rowspan="1" colspan="3">LiferayLearn</td></tr>
</thead>

<tbody>
<tr>
	<td>NO_RESULTS_MESSAGE</td>
	<td>//h4[@id="noResultsMessage"]</td>
	<td></td>
</tr>
<tr>
	<td>SEARCH_BAR</td>
	<td>//input[@id="docsSearch"]</td>
	<td></td>
</tr>
<tr>
	<td>SEARCH_RESULTS</td>
	<td>//ul[@class='search']//a[contains(.,'${key_searchTerm}')]</td>
	<td></td>
</tr>
</tbody>

</table>
</body>
</html>
```
