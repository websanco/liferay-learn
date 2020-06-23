# Migrating Your Project to Use liferay-npm-bundler's New Mode

Since [issue #303](https://github.com/liferay/liferay-js-toolkit/issues/303), two modes of operation are available for the liferay-npm-bundler. You can preprocess files before the bundler runs, or you can use the bundler to handle the entire process, similar to webpack (processing source files via a set of rules). Follow these steps to migrate your project to use the new configuration mode, so the bundler can handle the entire process:

1. Open the project's `package.json` file and update the `build` script to use only the liferay-npm-bundler:

    old version:

    ```json
    {
      "scripts":{
        "build": "babel --source-maps -d build src && liferay-npm-bundler"
      }
    }
    ```

    new version:

    ```json
    {
      "scripts":{
        "build": "liferay-npm-bundler"
      }
    }
    ```

1. Define the rules for the bundler to use (e.g. running babel to transpile files) in the project's `.npmbundlerrc` file. The example configuration below defines rules for using the `babel-loader` to transpile JavaScript files. See the [Default Loaders reference](../default-bundler-loaders.md) for the full list of default loaders. Follow the steps in [Creating Custom Loaders for the Bundler](../../developer/creating-custom-loaders-for-the-bundler.md) <!-- TODO: Fix link --> to create a custom loader. The liferay-npm-bundler processes the `*.js` files in  `/src/` with babel and writes the results in the default `/build/` folder:

    ```json
    {
      "sources": ["src"],
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env"]
              }
            }
          ]
        }
      ]
    }
    ```

    ```note::
      Although the new mode of the liferay-npm-bundler acts very much like webpack, **it is not compatible with webpack**. Webpack creates a single JavaScript bundle file while the liferay-npm-bundler targets AMD loader.
    ```

## Related Information

* [Default liferay-npm-bundler Loaders](../default-bundler-loaders.md)
* [Understanding liferay-npm-bundler's Loaders](../understanding-bundler-loaders.md)
