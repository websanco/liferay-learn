# Using Variables

Though not a Poshi layer, variables are a large part of Poshi tests. Variables, like in other programming languages, are used to store data which can be referenced and reused in tests, macros, and paths. Using variables in path and macro files make them both specific and reusable.

As an example, the test below asserts that an Abstract Description can be added to a Blogs entry. The variable `entryAbstractDescription` is set at the testcase level as `This is the Blog Entry Abstract Description.`:

```
test CanAddAbstractDescriptionToABlogEntry {

  var entryAbstractDescription = 'This is the Blog Entry Abstract Description.';

  task ("Given a blog page") {
    Navigator.gotoPage(pageName = "Blogs Page");

    BlogsNavigator.gotoAddEntry();
  }

  task ("When I add a blog entry with a custom Abstract Description") {}
  	BlogsEntry.addEntryContent(
		entryContent = "${entryContent}",
		entrySubtitle = "${entrySubtitle}",
		entryTitle = "${entryTitle}");

	Panel.expandPanel(panel = "Configuration");

	BlogsEntry.addCustomAbstract(entryAbstractDescription = "${entryAbstractDescription}");

	PortletEntry.publish();
  }

  task ("and when I view the page") {
    Navigator.gotoPage(pageName = "Blogs Page");
  }

  task ("Then the Abstract Description should be displayed") {
    Blogs.viewContentAbstractDescription(entryAbstractDescription = "${entryAbstractDescription}");
  }
}
```

The variable is then passed to the `Blogs.viewContentAbstractDescription` macro:

```
macro viewContentAbstractDescription {
	var key_entryAbstractDescription = "${entryAbstractDescription}";

	AssertTextEquals.assertPartialText(
		locator1 = "BlogsEntry#CONTENT_ABSTRACT_DESC",
		value1 = "${key_entryAbstractDescription}");
}
```

The same variable is passed to the `BlogsEntry#CONTENT_ABSTRACT_DESC` path:

```
</tr>
<tr>
	<td>CONTENT_ABSTRACT_DESC</td>
	<td>//div[contains(@class,'asset-content')]//div[contains(.,'${key_entryAbstractDescription}')]</td>
	<td></td>
</tr>
```
