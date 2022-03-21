# Paths

A path is an element on a page that a test will interact with.  An element can be a button, text field, link, image, paragraph, or just about anything you would need to assert, click, or type into during a test. Each path is defined by the following:

**Locator key**: This is a descriptive label for what the element is (e.g. EDIT). Locator keys are all-caps and use underscores instead of spaces.

**XPath identifier**: This is the address that points to that particular element in the HTML page (e.g. //button[contains(.,'Edit')]).

Path files are written in HTML that when rendered displays a table containing the locator names and the locators. Think of path files as a library of test objects that a collection of tests can interact with.

```
<html>
<head>
<title>Button</title>
</head>
...
<tbody>
<tr>
	<td>EDIT</td>
	<td>//button[contains(.,'Edit')]</td>
	<td></td>
</tr>
<tr>
	<td>EDIT_PENCIL</td>
	<td>//a[contains(@class,'btn')]//i[contains(@class,'icon-pencil')]</td>
	<td></td>
</tr>
...
</tbody>
</table>
</body>
</html>
```

## Benefits of using path files

**Promotes reusability**: When writing multiple tests that interact with the same element, you are able to share and reuse locators instead of having them hardcoded for each interaction.

**Lowers cost of test maintenance**: When a locator is used in multiple tests and a UI change causes the xpath to change, you only need to change the path file instead of searching for all the instances of the locator.

## Best Practices

**Make path files specific yet reusable**: Path files must be specific enough to describe and locate the correct element, but general enough that they are reusable throughout the system being tested. Using [Variables](./variables.md) can help make locators both specific and reusable.

**Organize path files**: There are multiple ways to organize path files. Some suggestions are as follows:

  * **By Elements**: These paths are often very basic pieces of the UI that are reused across the system being tested, like buttons, checkboxes, text input, etc.

  * **By Sections**: These paths are more complex, yet still reusable, sections of the system being tested, like the CKEditor, menu bar, toolbar, etc.

  * **By Component/Portlets**: These paths are specific to the component, like the user status field for User and Systems Management.
