# Liferay JS Themes Toolkit Theme Development Workflow

The steps below outline the basic workflow for Theme development with the [Liferay JS Themes Toolkit](./installing-the-theme-generator-reference.md).

1. Build the Base Files. Run the build script for your Liferay JS Toolkit version. If you're unsure of your version run `npm list -g generator-liferay-theme` from your command line to print it.

    **v9.5.0+:**

    ```bash
    npm run build
    ```

    **Older versions:**

    ```bash
    node_modules\.bin\gulp build
    ```

1. Mirror the [structure of the files](./theme-anatomy-reference.md) you want to modify in the Theme's `/src/` folder.

1. Copy the files over from the `/src/build/` folder to the mirrored structure and add your modifications. The main modifications are placed in the files listed below:

    * `/src/templates/portal_normal.ftl`: main markup
    * `/src/css/_custom.scss`: custom CSS styling
    * `/src/js/main.js`: custom JavaScript

1. Build and deploy the Theme to your Liferay DXP server. The finished Theme is bundled as a WAR (Web application ARchive) file.

1. Apply the Theme [through the Look and Feel menu](applying-themes.md) by selecting your [Theme's thumbnail](../../developing-themes/creating-a-thumbnail-preview-for-your-theme.md). 

<!-- Add note back once Developer mode docs are ported
```note::
  While developing your Theme, we recommend that you enable `Developer Mode <./using-developer-mode-with-themes.md>`_. This un-minifies JavaScript files and disables caching for CSS and FreeMarker template files, making the debugging process much easier.
```
-->

Themes generated with the Liferay Theme Generator can access several helpful Gulp tasks to make the process easier:

* **build:** builds your Theme's files based on the specified base Theme. See the [Build task](./running-theme-tasks/building-themes.md) for more information.
* **extend:** sets the base Theme or Themelet to extend. See the [Extend task](./running-theme-tasks/changing-the-base-theme.md) for more information.
* **init:** specifies the app server to deploy your Theme to (automatically run during the initial creation of the Theme). See the [Init task](./running-theme-tasks/configuring-your-themes-app-server.md) for more information.
* **kickstart:** copies files from an existing Theme into your theme to help kickstart it. See the [Kickstart task](./running-theme-tasks/copying-an-existing-themes-files.md) for more information.
* **status:** lists the base Theme/Themelets that your Theme extends. See the [Status task](./running-theme-tasks/listing-your-themes-extensions.md) for more information.
* **watch:** watches for changes to your Theme's files and automatically deploys them to the server when a change is made. See the [Watch task](./running-theme-tasks/previewing-theme-changes.md) for more information.
