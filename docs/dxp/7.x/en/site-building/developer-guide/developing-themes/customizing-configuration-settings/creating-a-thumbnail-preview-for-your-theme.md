# Creating a Thumbnail Preview for Your Theme

A Theme thumbnail helps identify your Theme in the Theme Selector when it's being applied. Theme thumbnails are also used to choose between your Theme's [color schemes](./creating-color-schemes-for-your-theme) if it has any. If you've [created your Theme with the Liferay JS Themes Toolkit](../developing-a-theme.md), a placeholder thumbnail is included. To update/create your Theme's thumbnail,

1. Take a screenshot of your Theme and scale it to the dimensions 675px x 381px. Your thumbnail **must be** these exact dimensions to display properly.
1. Save the image as a `.png` file named `thumbnail.png`. Replace the placeholder `thumbnail.png` file in the Theme's `src/images` folder (if it exists) with the updated thumbnail.

    ```note::
      The `Theme Builder Gradle plugin <./theme-builder-gradle-plugin>`_ doesn't recognize a ``thumbnail.png`` file. If you're using this plugin to build your Theme instead, you must create a ``screenshot.png`` file in your Theme's ``images`` folder that is 1080 pixels high by 864 pixels wide. The thumbnail is automatically generated from the screenshot for you when the Theme is built.
    ```

1. Deploy the Theme and [apply it](../../../../changing-your-theme.md). The Theme's thumbnail is updated.

![Your Theme thumbnail is displayed with the rest of the available Themes.](./creating-a-thumbnail-preview-for-your-theme/images/01.png)

## Related Information

* [Developing a Theme](../developing-a-theme.md)
* [Creating Color Schemes for Your Theme](./creating-color-schemes-for-your-theme.md)
* [Adding Configurable Theme Settings](./adding-configurable-theme-settings.md)