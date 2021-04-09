# Migrating an Angular Project to Use Liferay npm Bundler 2.x

Follow these steps to migrate your Angular project to use liferay-npm-bundler 2.x. While liferay-npm-bundler 1.x relied on Babel to perform some transformation steps, these transformations are now automatically applied in version 2.x. Therefore, you should remove Babel from your project:

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
1. Open your `tsconfig.json` file and replace the `"module": "amd"` compiler option with the configuration shown below to produce CommonJS modules:

    ```json
    {
      "compilerOptions": {
        ...
        "module": "commonjs",
        ...
      }
    }
    ```

1. Delete the `.babelrc` file to remove the Babel configuration.
1. Remove Babel from your `package.json` build process so it matches the configuration below:

    ```json    
    {
      "scripts": {
        "build": "tsc && liferay-npm-bundler"
      },
      ...
    }
    ```

1. Remove the following Babel dependencies from your `package.json` *devDependencies*:

    ```json
    "babel-cli": "6.26.0",
    "babel-preset-liferay-amd": "1.2.2"
    ```

Great! Your project is migrated to use liferay-npm-bundler 2.x.

## Related Information

* [Formatting Your npm Modules for AMD](../how-the-bundler-formats-js-modules.md)
* [What Changed between liferay-npm-bundler 1.x and 2.x](../changes-between-bundler-1.x-and-2.x.md)
