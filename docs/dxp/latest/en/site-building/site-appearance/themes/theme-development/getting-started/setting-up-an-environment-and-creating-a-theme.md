# Setting Up an Environment and Creating a Theme

Creating a theme is the first step in theme development. Once you have your own theme, you can deploy it to your Liferay DXP instance, and use it for [various Site customizations](../../introduction-to-themes.md#developing-themes).

## Using the Liferay Theme Generator

Themes are created using the Liferay Theme Generator. Liferay DXP 7.3 uses version 10.x.x of the Theme Generator.

```note::
   Older versions of the Liferay Theme Generator can be used to generate themes for older versions of Liferay DXP. To create themes for DXP 7.0 or 7.1, install version 8.x.x of the Theme Generator.
```

### Installation

If you have not already done so, install the Liferay Theme Generator with this command:

```bash
npm install -g generator-liferay-theme@10.x.x
```

Install the Yeoman and Gulp dependencies with this command:

```bash
npm install -g yo gulp
```

### Running the Liferay Theme Generator

Run the Liferay Theme Generator with these steps:

1. Run the Liferay Theme Generator using Yeoman:

    ```bash
    yo liferay-theme
    ```

    ```important::
       You can add the name of a base theme to this command to base your new theme off of it. For example, running ``yo liferay-theme:classic`` will base your new theme off of DXP's Classic theme.
    ```
    <!-- Add link to an explanation of choosing (and changing) the base theme when available.-->

1. Type a name for your theme at the prompt. Press Enter to use the default, "My Liferay Theme".

    ```
    ? What would you like to call your theme? (My Liferay Theme)
    ```

1. Type an ID for your theme at the prompt. When the theme is generated, the ID will determine the name of the folder that your theme is built inside of. You can also press Enter to use a default ID based on the name.

    ```
    ? What id would you like to give to your theme? (my-liferay-theme)
    ```

1. Use the arrow keys to select the version of Liferay DXP to build the theme for at the prompt. You can generate a theme for 7.2 or 7.3 with version 10.x.x of the Liferay Theme Generator.

    ```
    ? Which version of Liferay is this theme for? (Use arrow keys)
    ❯ 7.3 
      7.2
    ```

1. Answer whether you would like to add Font Awesome as an available font for your theme at the prompt.

1. After the theme has been generated, complete the process by using the arrow keys to select the appropriate deployment type for your theme. You can choose to deploy with a local app server, Docker container, or other URL.

    ```
    ? Select your deployment strategy (Use arrow keys)
    ❯ Local App Server 
      Docker Container 
      Other
    ```

1. Provide the location of the app server at the prompt, depending on which deployment type you are using.

    You may provide the app server directory, the Docker container name, or the host URL to locate the app server.

The theme is then generated and placed inside of a folder named after the ID you have chosen. You can now build and deploy it to your DXP instance by running `gulp deploy` from the theme's base folder.

## Using Blade to Create a Theme

Coming soon!
<!-- Link to Theme Templates documentation when available-->

## Additional Information

* [Changing Your Site's Appearance](../../../../../getting-started/changing-your-sites-appearance.md)
