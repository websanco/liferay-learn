# Bundling and Installing Resources into Your Theme via Themelets

Themelets are small, modular pieces of code that you can reuse in multiple themes. Unlike themes themselves, themelets may only contain the files with your changes. You can use themelets to change all kinds of style or functionality that you would normally define as part of a theme.

## Prerequisites

Themelets are created using the Liferay Theme Generator (like [creating a theme](../getting-started/setting-up-an-environment-and-creating-a-theme.md)). If you have not already done so, then install it with this command:

```bash
npm install -g generator-liferay-theme@10.x.x
```

Install the Yeoman and Gulp dependencies with this command:

```bash
npm install -g yo gulp
```

## Creating a Themelet

Use the Liferay Theme Generator to create your themelet:

1. Run this command from the file location you want to create the themelet:

    ```bash
    yo liferay-theme:themelet
    ```

1. Type a name for your themelet at the prompt. Press Enter to use the default, "My Liferay Themelet."

    ```
    ? What would you like to call your themelet? Example
    ```

1. Type an ID for your themelet at the prompt. When the themelet is generated, the ID will determine the name of the folder that your themelet is built inside of. You can also press Enter to use a default ID based on the name.

1. Use the arrow keys to select the version of Liferay DXP to build the themelet for at the prompt, and press Enter. 

    ```
    ? Which version of Liferay is this themelet for? (Use arrow keys)
    ❯ 7.3 
      7.2 
      Any 
    ```

The themelet is generated and placed inside of a folder based on the chosen ID. Now you can add it to a theme to deploy.

To make your themelet usable within a theme, you must also navigate into the themelet's folder and run this command:

```bash
npm link
```

This command globally installs the themelet so that you can select it when extending your theme.

## Adding the Themelet to a Theme

Once you have a themelet installed globally, you can add it to any theme.

```note::
   If you do not have a theme to add your themelet to, then see `Creating a Theme <../getting-started/setting-up-an-environment-and-creating-a-theme.md>`__ for steps to create one.
```

Add your themelet to any theme with the `gulp extend` command:

1. Navigate to the root folder of your theme:

    ```bash
    cd my-theme/
    ```

1. Run the following command from this folder:

    ```bash
    gulp extend
    ```

1. At the prompt, use the arrow keys to choose to extend using a `Themelet`, and press Enter.

    ```
    ? What kind of theme asset would you like to extend? 
      Base theme 
    ❯ Themelet 
    ```

1. At the prompt, use the arrow keys select "Search globally installed npm modules."

    ```
    ? Where would you like to search for themelets? (Use arrow keys)
    ❯ Search globally installed npm modules (development purposes only)
      Search npm registry (published modules)
      Specify a package URL
    ```

1. Select your themelet from the list of globally installed modules with the arrow keys. You must have run `npm link` within the themelet for it to appear in the list.

The themelet is installed to the theme, and the changes are present the next time you build and deploy this theme with the `gulp deploy` command.

## Additional Information

* [Creating a Theme](../getting-started/setting-up-an-environment-and-creating-a-theme.md)
