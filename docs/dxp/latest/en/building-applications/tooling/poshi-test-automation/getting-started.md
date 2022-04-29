---
html_meta:
  "documentation-type": "user-guide"
---

# Getting Started

```{toctree}
:maxdepth: 3

getting-started/setup.md
getting-started/creating-a-path-file.md
getting-started/creating-a-macro-file.md
getting-started/creating-a-testcase-file.md
getting-started/syntax-validation.md
getting-started/running-a-test.md
getting-started/understanding-test-results-and-debugging-tests.md
```

Previously, Poshi tests could only be executed by downloading and creating the test files within the [Liferay source
code](https://github.com/liferay/liferay-portal/). The _Poshi Standalone_ gradle project enables you to use Poshi in any directory of your choosing without having to fetch the entire Liferay Portal github repository.

::::{grid} 1
:gutter: 3 3 3 3

:::{grid-item-card} Setup
:link: ./getting-started/setup.md

Setup the Poshi Standalone gradle project.
:::

:::{grid-item-card} Creating a Path File
:link: ./getting-started/creating-a-path-file.md

Create the path file for locators.
:::

:::{grid-item-card} Creating a Macro File
:link: ./getting-started/creating-a-macro-file.md

Script interactions on the macro file.
:::

:::{grid-item-card} Creating a Testcase File
:link: ./getting-started/creating-a-testcase-file.md

Script user behaviors on the testcase file.
:::

:::{grid-item-card} Syntax Validation
:link: ./getting-started/syntax-validation.md

Checking syntax before running tests.
:::

:::{grid-item-card} Running a Test
:link: ./getting-started/running-a-test.md

Run Poshi tests
:::

:::{grid-item-card} Understanding Test Results and Debugging Poshi
:link: ./getting-started/understanding-test-results-and-debugging-tests.md

Debugging failures in test runs
:::

::::
