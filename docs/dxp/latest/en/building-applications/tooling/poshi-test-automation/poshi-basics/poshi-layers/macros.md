# Macros

A macro is a set of functions that perform a task. Macros are where locators (paths) and functions are brought together to script interactions that a user performs on the system being tested. Anything from clicking on a button, to performing navigations, to asserting information, or even sending API calls to improve test setup speed can be scripted on the macro layer.

## Benefits of Using Macro Files

**Promotes reusability**: Reusing macros is the most efficient way to use a set of interactions that is repeated across multiple tests. For example, say you are writing three separate tests to edit the title of a blog post, a document, and a web content. If the interaction to edit requires you to expand a panel and click on the edit button, you create an `Assets.macro` file with a macro similar to the following:

```
macro edit {
  Panel.expandPanel(locator1 = Panel#COLLAPSED_MENU);

  AssertClick(
    locator1 = "Button#EDIT",
    value1 = "Edit");

  AssertElementPresent(locator1 = Title#EDITABLE);
}
```

The above macro can then be used in the three tests (Blogs, Documents, and Web Content) simply by calling `Assets.edit ();`.

**Lowers cost of test maintenance**: When the method of interaction on a feature is changed, changing a single macro instead of each test is more efficient that changing all the test files. In the same example above to edit an asset, if the interaction changes wherein the button is no longer in a panel but is readily clickable on the page, we can simply edit the macro to the following instead of editing three tests.

```
macro edit {
  AssertClick(
    locator1 = "Button#EDIT",
    value1 = "Edit");

  AssertElementPresent(locator1 = Title#EDITABLE);    
}
```

## Best Practices

**Keep macro commands modular**:  Each macro command should encompass one set of actions for a particular purpose. This allows the macro command to be reusable rather than retyping all of the specific actions every time they are needed. Larger macro commands can be made by stringing together several smaller macro commands that are used in the same order every time.

**Avoid hard-coding dynamic values**: Hard-coding dynamic values such as the name of a user or the title of a blog entry makes it difficult to reuse the macro and restricts its functionality. This also makes it difficult to reuse the macro. To make macros both reusable and specific, see [Variables](./variables.md).

**Make macro command names specific and intuitive**: Others who did not write the macro should be able to read the macro command name and ascertain what the command will do based on its name and which macro file it belongs to. Begin names with a verb in lower case and camel case each additional word. Add nouns, adjectives, and additional qualifiers as necessary if the verb alone is not clear enough.
