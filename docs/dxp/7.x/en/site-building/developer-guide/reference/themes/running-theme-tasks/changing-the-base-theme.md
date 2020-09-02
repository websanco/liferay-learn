# Changing Your Base Theme

1. Navigate to your Theme's root folder and run the `extend` task. 

    ```bash
    C:\Users\liferay\Desktop\projects\marketing-theme>npm run extend
    [12:21:26] Using gulpfile ~\Desktop\projects\marketing-theme\gulpfile.js
    [12:21:26] Starting 'extend'...
    ```

1. Enter 1 to select a new base Theme to extend.

    ```bash
    ? What kind of theme asset would you like to extend?
      1) Base theme
      2) Themelet
      Answer: 1 
    ```

1. By default, Themes created with the [Liferay Theme Generator](https://github.com/liferay/generator-liferay-theme) are based on the [styled Theme](https://www.npmjs.com/package/liferay-theme-styled). You can extend the styled or unstyled base Theme, a globally installed Theme, a Theme published on the npm registry, or you can specify a package URL. Enter the number for the option you wish to select. 

    ```note::
      You can retrieve the URL for a package by running ``npm show package-name dist.tarball``.
    ```

    ```bash
    ? What base theme would you like to extend?
      1) Styled
      2) Unstyled
       ──────────────
      3) Search globally installed npm modules (development purposes only)
      4) Search npm registry (published modules)
      5) Specify a package URL
    ```

    ```note::
      The Classic Theme implements an existing base Theme and is therefore not meant to be extended. If you want to use the Classic Theme as a starting point, use the `Classic sub-generator <../../reference/themes/installing-the-theme-generator-reference.md#generator-and-sub-generator-commands>`_ instead.
    ```

Your Theme's `package.json` contains the updated base Theme configuration:

```json
"liferayTheme": {
  "baseTheme": "styled",
  "screenshot": "",
  "templateLanguage": "ftl",
  "version": "7.2"
},
```

Great! You've updated your base Theme. When you [build your Theme's files](./building-themes.md) or [deploy it](./deploying-themes.md), your Theme inherits the updated base Theme's files.
