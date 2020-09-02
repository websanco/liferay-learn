# Developing Themelets

Themelets are small, extendable, and reusable pieces of code containing CSS and JavaScript, that you can extend Themes with. They can be shared with other developers to provide common components for Themes. 

Here, you'll learn how to develop a Themelet with the Liferay JS Themes Toolkit:

1. [Install a Themelet and Deploy It](#install-a-themelet-and-deploy-it)
1. [Extend the Theme with a New Themelet](#extend-the-theme-with-a-new-themelet)
1. [Deploy and Test](#deploy-and-test)

This example uses a Docker image with a fresh install of Liferay DXP.

## Install a Themelet and Deploy It

First, install an existing Themelet in a Theme and deploy it to see what it looks like:

1. Run the command below to start the Docker container:

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.1-ga2
    ```

1. Download and unzip the [Custom Tooltips Themelet and Pastel Purple Theme](https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-themes/liferay-m2t6.zip):

    ```bash
    curl https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-themes/liferay-m2t6.zip
    
    unzip liferay-m2t6.zip
    ```

1. From the module root, build the Theme and install it's dependencies:

    ```bash
    cd liferay-m2t6
    .\gradlew build
    ```

1. Navigate to the Themelet and globally install it with the `npm link` command to make it available for the Theme:

    ```bash
    cd liferay-m2t6/m2t6-impl/custom-tooltips-themelet
    npm link
    ```

1. Extend the Theme with the Themelet:

    ```bash
    cd liferay-m2t6/m2t6-impl/purple-pastel-theme
    npm run extend
    ```

1. Choose *Themelet* as the Theme asset to extend.

    ```bash
    [11:39:03] Using gulpfile ~\Desktop\test\liferay-m2t6\purple-pastel-theme\gulpfile.js
    [11:39:03] Starting 'extend'...
    ? What kind of theme asset would you like to extend?
      1) Base theme
      2) Themelet
      Answer: 2
    ```

1. Select *Search globally installed npm modules*

    ```bash
    ? Where would you like to search for themelets?
      1) Search globally installed npm modules (development purposes only)
      2) Search npm registry (published modules)
      3) Specify a package URL
      Answer: 1
    ```

1. Highlight the `custom-tooltips-themelet` Themelet, press spacebar to select it, and press *Enter* to install it.

    ```bash
    ? Select a themelet
    >(*) custom-tooltips-themelet
    ```
    
    The extend task completes with the output below:
    
    ```bash
    npm WARN pastel-purple-theme@1.0.0 No description
    npm WARN pastel-purple-theme@1.0.0 No repository field.
    npm WARN pastel-purple-theme@1.0.0 No license field.
    
    + custom-tooltips-themelet@1.0.0
    added 1 package and audited 6174 packages in 4.252s
    found 48 vulnerabilities (30 low, 1 moderate, 17 high)
      run `npm audit fix` to fix them, or `npm audit` for details
    [11:41:51] Finished 'extend' after 2.8 min
    ```

1. Build the updated Pastel Purple Theme's WAR:

    ```bash
    npm run build
    ```
    
    Verify in the output that the Themelet's CSS is injected in the Theme:
    
    ```bash
    [12:01:13] Starting 'build:themelets'...
    [12:01:13] Starting 'build:themelet-src'...
    [12:01:13] Finished 'build:themelet-src' after 14 ms
    [12:01:13] Starting 'build:themelet-css-inject'...
    [12:01:13] Starting 'build:themelet-js-inject'...
    [12:01:13] gulp-inject 1 file into _custom.scss.
    [12:01:13] gulp-inject Nothing to inject into portal_normal.ftl.
    [12:01:13] Finished 'build:themelet-js-inject' after 32 ms
    [12:01:13] Finished 'build:themelet-css-inject' after 99 ms
    [12:01:13] Finished 'build:themelets' after 135 ms
    ```

1. Deploy the WAR to the Docker Container:

    ```bash
    cd dist
    docker cp pastel-purple-theme.war docker-container-name:/opt/liferay/osgi/war
    ```

1. Open your browser to `https://localhost:8080`, and open the Product Menu and go to *Site Builder* &rarr; *Pages*. Click the (![Cog icon](../../../images/icon-control-menu-gear.png)) next to Public Pages.
1. Scroll down and click the *Change Current Theme* button, and click the custom Theme thumbnail. The custom Theme appears next to the Classic Theme.

    ![The custom Theme is listed in the Theme selector.](./developing-a-theme/images/01.png)

1. Click *Save* to apply the changes, and go back to the home page and hover over an icon in the Control Menu to see the tooltip CSS applied from the Themelet.

    ![Themelets can customize the JavaScript.](./developing-themelets/images/01.png)

## Themelet Breakdown

The Theme includes the Themelet's code with inject tags. CSS styles are injected with these tags at the top of `/src/_custom.scss`:

```scss
/* These inject tags are used for dynamically creating imports for themelet styles, you can place them where ever you like in this file. */

/* inject:imports */
/* endinject */

/* This file allows you to override default styles in one central location for easier upgrade and maintenance. */
```

JavaScript is injected with these tags at the bottom of the `<body>` tag in `/src/portal_normal.ftl`:

```html
<body>
  ...
  <!-- inject:js -->
  <!-- endinject -->
</body>
```

The Themelet changes the look of the tooltips with the CSS in the `_custom.scss` below:

```scss
.clay-tooltip-bottom .arrow::before, .clay-tooltip-bottom-left .arrow::before, .clay-tooltip-bottom-right .arrow::before {
    border-bottom-color: #0000FF;
}

.tooltip-inner {
    background-color: #0000FF;
    border-radius: 5rem;
}
```

## Extend the Theme with a New Themelet

Theme's can have mutliple Themelet extensions. Here you'll create a new Themelet to extend the Theme in just a few steps that prints to the console whether the Theme is being viewed on Desktop or Mobile.

1. [Install the Theme Generator](../reference/installing-the-theme-generator-reference.md) and run the [Themelet sub-generator](../reference/installing-the-theme-generator-reference.md#generator-and-sub-generator-commands) with the command below:

    ```bash
    yo liferay-theme:themelet
    ```

    ```bash
    ? What would you like to call your themelet? Platform Console Logger Themelet
    ? What id would you like to give to your themelet? platform-console-logger-themelet
    ? Which version of Liferay is this themelet for?
    > 7.3
      7.2
      Any 
    ```
    
1. Create a `/src/main.js` file in the Themelet and add the code below to it:

    ```bash
    cd platform-console-logger-themelet
    touch main.js
    ```

    Add the code below to the `main.js` file:

    ```javascript
    AUI().ready(
    	function() {
        if(Liferay.Browser.isMobile !== 'true'){
          console.log("You're viewing this site on a desktop device.");
        }
        else if(Liferay.Browser.isMobile == 'true'){
          console.log("You're viewing this site on a mobile device.");
        }
      }
    );
    ```

1. Globally install the Platform Console Logger Themelet with the `npm link` command to make it available for the Theme:

    ```bash
    cd platform-console-logger-themelet
    npm link
    ```

1. Extend the Theme with the Themelet:

    ```bash
    cd purple-pastel-theme
    npm run extend
    ```

1. Choose *Themelet* as the Theme asset to extend.
1. Select *Search globally installed npm modules*
1. Highlight the `platform-console-logger-themelet`, press spacebar to select it, and press *Enter* to install it.

    ```bash
    ? Select a themelet
     (*) custom-tooltips-themelet
    >(*) platform-console-logger-themelet
    ```

1. Build the updated Pastel Purple Theme's WAR:

    ```bash
    npm run build
    ```

1. Deploy the WAR to the Docker Container:

    ```bash
    cd dist
    docker cp pastel-purple-theme.war docker-container-name:/opt/liferay/osgi/war
    ```

1. Open your browser to `https://localhost:8080`, and refresh the page.
1. Open the browser console to view the message from the Platform Console Logger Themelet:

    You're viewing this site on a desktop device.

Great! Now you know how to create Themelets with the Liferay JS Themes Toolkit.

## Related Information

* [Developing a Theme](./developing-a-theme.md)
* [Theme Development Workflow](../reference/themes/liferay-js-themes-toolkit-theme-development-workflow.md)
* [Look and Feel Overview](./look-and-feel-overview.md)