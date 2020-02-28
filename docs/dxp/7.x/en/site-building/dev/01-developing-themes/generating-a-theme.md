# Generating a Theme

Follow these steps to generate a Theme.

![Figure 1: The tools are in your hands to create any theme you can imagine.](./images/01.png)

1. Navigate to the folder you want to generate the Theme in.
1. Run the generator and follow the prompts to create your theme:

    ```bash
    yo liferay-theme
    ```
    
    Since v9.5.0 of the Liferay JS Themes Toolkit, you can also create themes based on the Classic Theme and create Admin themes for the Admin view:
    
    run this command to use the Classic theme as a starting point in v9.5.0+:

    ```bash
    yo liferay-theme:classic
    ```
    
    run this command to create an Admin theme in v9.5.0+:

    ```bash
    yo liferay-theme:admin
    ```

    ![Figure 2: You can generate a theme by answering just a few configuration questions.](./images/02.png)
    
    ```note::
      Since Liferay DXP Fix Pack 2 and Liferay Portal 7.2 CE GA2, Font Awesome is available globally as a system setting, which is enabled by default. If you're using Font Awesome icons in your theme, answer yes (y) to the Font Awesome question in v9.x.x generator to include Font Awesome imports in your theme. This ensures that your icons won't break if they are disabled in the global setting. 
    ```