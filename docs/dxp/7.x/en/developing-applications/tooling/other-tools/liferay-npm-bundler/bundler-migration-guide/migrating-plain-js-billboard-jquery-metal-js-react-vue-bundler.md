# Migrating a Plain JavaScript, Billboard JS, JQuery, Metal JS, React, or Vue JS Project to Use Liferay npm Bundler 2.x

Follow these steps to migrate the framework projects shown below to use liferay-npm-bundler 2.x:

* plain JavaScript project
* Billboard.js project
* JQuery project
* Metal.js project
* React project
* Vue.js project

While Babel is required to transpile your source files, you must remove any Babel preset used for transformations from your project that bundler 1.x imposed. liferay-npm-bundler 2.x handles these transformations by default:

1. Update the `liferay-npm-bundler` dependency in your `package.json` to version 2.x:

    ```json
    {
      "devDependencies": {
        ...
        "liferay-npm-bundler": "^2.0.0",
        ...
      },
      ...
    }
    ```

1. Remove all `liferay-npm-bundler-preset-*` dependencies from your `package.json` because liferay-npm-bundler 2.x includes these by default.
1. Remove any bundler presets you configured in your `.npmbundlerrc` file. liferay-npm-bundler 2.x includes one smart preset that handles all frameworks automatically.
1. Remove the *liferay-project* preset from your project's `.babelrc` file. All that should remain is the `es2015` preset shown below:

    ```json
    {
      "presets": ["es2015"]
    }
    ```

    If your project uses React, make sure the `react` preset remains as well:

    ```json    
    {
      "presets": ["es2015", "react"]
    }
    ```

1. Remove the `babel-preset-liferay-project` dependency from your `package.json`.

Awesome! Your project is migrated to use liferay-npm-bundler 2.x.

## Related Information

* [Formatting Your npm Modules for AMD](../how-the-bundler-formats-js-modules.md)
<!-- * [Using the NPMResolver API in Your Portlets](/docs/7-2/frameworks/-/knowledge_base/f/using-the-npmresolver-api-in-your-portlets) TODO: Update link-->
* [What Changed between liferay-npm-bundler 1.x and 2.x](../changes-between-bundler-1.x-and-2.x.md)
