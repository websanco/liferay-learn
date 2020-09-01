# Theme Anatomy

A Theme contains several files. Although most of the files are named after their matching components, their functions may be unclear. This reference guide explains each file's usage to make clear which files to modify.

Themes built with the [Liferay JS Theme Toolkit](https://github.com/liferay/liferay-js-themes-toolkit/tree/master/packages) have the anatomy shown below:

- `theme-name/`
    - `src/`
        - `css/`
            - [`_clay_custom.scss`](#clay-custom-scss)
            - [`_clay_variables.scss`](#clay-variables-scss)
            - [`_custom.scss`](#custom-scss)
            - [`_liferay_variables_custom.scss`](#liferay-variables-custom-scss)
        - `images/`
            -   (custom images)
        - `js/`
            - [`main.js`](#main-js)
        - `templates/`
            - [`init_custom.ftl`](#init-custom-ftl)
            - [`navigation.ftl`](#navigation-ftl)
            - [`portal_normal.ftl`](#portal-normal-ftl)
            - [`portal_pop_up.ftl`](#portal-pop-up-ftl)
            - [`portlet.ftl`](#portlet-ftl)
        - `WEB-INF/`
            - [`liferay-look-and-feel.xml`](#liferay-look-and-feel-xml)
            - [`liferay-plugin-package.properties`](#liferay-plugin-package-properties)
            - `src/`
                - `resources-importer/`
                    - (Many directories)
    - [`liferay-theme.json`](#liferay-theme-json)
    - [`package.json`](#package-json)

Regarding CSS files, you should only modify `_clay_custom.scss`, `_clay_variables.scss`, `_custom.scss`, and `_liferay_variables_custom.scss`.

You can of course overwrite any CSS file you want, but if you modify any other files, you're removing styling that Liferay Portal needs to work properly.

## Theme Files

### _clay_custom.scss

Used for Clay custom styles, i.e. styles for a third party Bootstrap Theme. Anything written in this file is compiled in the same scope as Bootstrap/Lexicon, so you can use their variables, mixins, etc. You can also implement any of the variables you define in `_clay_variables.scss`.

### _clay_variables.scss

Used to store custom Sass variables. This file gets injected into the Bootstrap/Lexicon build, so you can overwrite variables and change how those libraries are compiled.

### _custom.scss

Used for custom CSS styles. You should place all of your custom CSS modifications in this file.

### _liferay_variables_custom.scss

Used for overwriting variables defined in `_liferay_variables.scss` without wiping out the whole file.

### init_custom.ftl

Used for custom FreeMarker variables i.e. [theme setting](../../developing-themes/customizing-configuration-settings/adding-theme-settings.md) variables.

### navigation.ftl

The theme template for the Theme's navigation.

### portal_normal.ftl

Similar to a static site's `index.html`, this file acts as a hub for all theme templates.

### portal_pop_up.ftl

The theme template for pop up dialogs for the Theme's portlets.

### portlet.ftl

The theme template for the Theme's portlets. If your Theme uses [Application Decorators](theming-portlets.md#portlet-decorators), you can modify this file to create application decorator-specific theme settings.

### liferay-theme.json

Contains the configuration settings for your app server, in Node.js tool-based themes. You can change this file manually at any time to update your server settings. The file can also be updated via the [`gulp init` task](updating-your-themes-app-server.md).

### package.json

Contains theme setting information such as the theme template language, version, and base Theme, for Node.js tool developed themes. You can update this file manually. The [`gulp extend` task](changing-your-base-theme.md) can also be used to change the base Theme.

### main.js

Used for custom JavaScript.

### liferay-look-and-feel.xml

Contains basic information for the Theme. If your Theme has [theme settings](../../developing-themes/customizing-configuration-settings/adding-theme-settings.md), they are defined in this file. For a full explanation of this file, please see the [Definitions docs](https://docs.liferay.com/portal/7.3-latest/definitions/liferay-look-and-feel_7_3_0.dtd.html).

### liferay-plugin-package.properties

Contains general properties for the Theme. [Resources Importer](importing-resources-with-a-theme.md) configuration settings are also placed in this file. For a full explanation of the properties available for this file please see the [Properties documentation](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/liferay-plugin-package_7_3_0.properties.html).
