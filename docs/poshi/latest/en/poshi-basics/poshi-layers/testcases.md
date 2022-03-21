# Testcases

A testcase file in Poshi is a collection of test scenarios grouped in blocks of code called test blocks. The three main test blocks: `setUp`, `test`, and `tearDown`, are made up of multiple macros which describe the different user- and system-interactions making up the test scenario.

## setUp and tearDown Blocks

The `setUp` and `tearDown` blocks in `*.testcase` files run before and after each `test`, respectively. Although not required, `setUp` blocks are an efficient way to reuse macros that are universal to the start and end of each `test`. Below is an example of a `setUp` block where the user logs in before each test, and a `tearDown` pointing to a Blogs macro that would delete all blog entries to prepare for the next test.

```
definition {

  setUp {

    task ("Setup: Login") {
      User.firstLoginPG();
    }  
  }

  tearDown {

    BlogsEntry.tearDownCP();
  }

  test CanAddEntryInAdmin{

    task ("Given a site wherein I have System Admin privileges to Blogs Admin") {
      BlogsNavigator.openBlogsAdmin(siteURLKey = "guest");
    }

    task ("When I add a Blogs entry") {
      Blogs.addEntry(
      	entryContent = "Blogs Entry Content",
      	entryTitle = "Blogs Entry Title");
    }

    task ("And when I navigate to the entry") {
      BlogsNavigator.openBlogsAdmin(siteURLKey = "guest");

      BlogsNavigator.gotoEntryCP(entryTitle = "Blogs Entry Title");
    }

    task ("Then the entry should be viewable") {
      BlogsEntry.viewEntryCP(
      	entryContent = "Blogs Entry Content",
      	entryTitle = "Blogs Entry Title");
    }
  }
}
```

## Test Blocks

The `test` block contains the macros that build up the scenario to be tested. Within the test block, you can further use task blocks to group macros making it easier to read what the test is doing. In the example above, the macros are grouped according to BDD (Behavior-Driven Development) which uses Given-When-Then to break down the test into sections. Note that tasks blocks are not limited to BDD and can be used however you would like to group your macros.

## Best Practices

**Make test names concise, specific, and descriptive**: Vague test names make it difficult to figure out what the test is supposed to do. Example, naming a test `BlogEntry` does not help the reader or analyst in understanding what functionality is being tested.

**Convey intent:** Use words like "can" or "cannot" whenever possible to easily convey what the test is supposed to do. Take the test name `AddABlogEntryWithNoTitle`. Though specific and descriptive, it is not clear whether the expected behavior is that the blog entry can or cannot be added without a title.

**Stick to one scenario per test block**: Testing multiple scenarios per test blocks muddy the intention of the test, unnecessarily extends test runtime, and makes it difficult to analyze for failures later on.
