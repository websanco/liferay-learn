# Developing Themelets

Themelets are small, extendable, and reusable pieces of code containing CSS and JavaScript, that you can extend Themes with. They can be shared with other developers to provide common components for Themes. The steps below show how to use the Liferay Theme Generator's Themelets sub-generator to create a Themelet. You can create a Themelet in just a few steps:

1. Generate the Themelet.
1. Customize the CSS.
1. Customize the JavaScript.
1. Globally install the Themelet.
1. Add the inject tags into the Theme.
1. Install the Themelet in the Theme.
1. Build the Theme's WAR file and copy it to the Docker container.

## Generate the Themelet

You can download and unzip this [example Theme and Themelet](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-themes/developing-themelets/liferay-m2t6.zip) if you want to deploy it or compare your code as you follow along:

    ```bash
    curl https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-themes/developing-themelets/liferay-m2t6.zip
    
    unzip liferay-m2t6.zip
    cd my-theme-to-extend
    npm install
    ```

1. Install the [Liferay Theme Generator](./installing-the-liferay-theme-generator.md) if it's not already installed. 
1. Run `yo liferay-theme:themelet` and follow the prompts to generate the Themelet.

    ![Themelets can add new functionality.](./developing-themelets/images/01.png)

## Customize the CSS

Add custom CSS to the Theme's `/src/css/_custom.scss` file. The example has the configuration below which changes the look of the tooltips, making them appear more round like speech bubbles and changes their color to blue:

```scss
.clay-tooltip-bottom .arrow::before, .clay-tooltip-bottom-left .arrow::before, .clay-tooltip-bottom-right .arrow::before {
    border-bottom-color: #0000FF;
}

.tooltip-inner {
    background-color: #0000FF;
    border-radius: 5rem;
}
```

## Customize the JavaScript

Add custom JavaScript to the generated `/src/js/main.js` file. If this folder doesn't exist, you must create it. The example has the configuration below which alerts the user whether they're viewing the site on a mobile device, after the page's HTML loads:

```javascript
AUI().ready(
	function() {
    if(Liferay.Browser.isMobile !== 'true'){
      alert("You're viewing this site on a desktop device.");
    }
    else if(Liferay.Browser.isMobile == 'true'){
      alert("You're viewing this site on a mobile device.");
    }
  }
);
```

## Globally Install the Themelet

To use the Themelet, you must first globally install it to make it available. Follow these steps:

1. Open the Command Line and navigate to the Themelet's root folder (`my-themelet` in the example).
1. Run the `npm link` command to globally install the Themelet so you can install it in your Theme.

## Add the Inject Tags in the Theme

To extend a Theme with a Themelet's CSS and JavaScript, you must have inject tags in the Theme's `_custom.scss` and `portal_normal` Theme template. Add these inject tags to the top of `/src/_custom.scss` if they're not there:

```scss
/* These inject tags are used for dynamically creating imports for themelet styles, you can place them where ever you like in this file. */

/* inject:imports */
/* endinject */

/* This file allows you to override default styles in one central location for easier upgrade and maintenance. */
```

Add these JavaScript inject tags to the bottom of the `<body>` tag in `/src/portal_normal.ftl` if they're not there:

```html
<body>
  ...
  <!-- inject:js -->
  <!-- endinject -->
</body>
```

## Install the Themelet in a Theme

Once the Themelet is globally installed, follow these steps to install it in a Theme:

1. Navigate to the Theme you want to extend (`my-theme-to-extend` in the example).
1. Extend the Theme with the command below:

    ```bash
    npm run extend
    ```

1. Choose *Themelet* as the Theme asset to extend.
1. Select *Search globally installed npm modules*

    ![You can extend your Theme using globally installed npm modules or published npm modules.](./developing-themelets/images/02.png)

1. Highlight the Themelet (`my-themelet` in the example), press spacebar to select it, and press *Enter* to install it.

    ![You can install Themelets in multiple Themes, so you can reuse code.](./developing-themelets/images/03.png)
    
```note::
  Themelets can also be installed from registered npm packages and npm package URLS. You can retrieve the URL for a package by running ``npm show package-name dist.tarball``.
```

## Build the Theme's WAR File and Copy It to the Docker Container

1. Start the Docker container:

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.0-ga1
    ```

1. Build the Theme's WAR with the command below:

    ```bash
    npm run build
    ```

1. Verify that the Themelet's CSS has been injected into `_custom.scss` and the JavaScript has been injected into `portal_normal.ftl`. If you've modified both files, these messages should print in the command line after the build script is run in the previous step:

    ```bash
    [15:41:37] Starting 'build:themelets'...
    [15:41:37] Starting 'build:themelet-src'...
    [15:41:37] Finished 'build:themelet-src' after 9.91 ms
    [15:41:37] Starting 'build:themelet-css-inject'...
    [15:41:37] Starting 'build:themelet-js-inject'...
    [15:41:37] gulp-inject 1 file into _custom.scss.
    [15:41:37] gulp-inject 1 file into portal_normal.ftl.
    [15:41:37] Finished 'build:themelet-js-inject' after 24 ms
    [15:41:37] Finished 'build:themelet-css-inject' after 76 ms
    [15:41:37] Finished 'build:themelets' after 106 ms
    ```

1. Copy the Theme's WAR to the `[host_folder]/deploy` folder for your Docker image's [bind mount](TODO), or create the `[host_folder]/deploy` folder if it doesn't exist.

    ```note::
    You must restart the Docker container if you're creating the `[host_folder]/deploy` folder for the first time in your bind mount.
    ```

1. Confirm the deployment to the Liferay Docker container console. The log message below should appear in the Docker console:

    ```bash
    INFO [fileinstall-/opt/liferay/osgi/war][BundleStartStopLogger:39] STARTED my-theme-to-extend_theme_1.0.0 [1114]
    ```

1. Verify that the Theme is available. Open your browser to `https://localhost:8080`, open the Product Menu and go to *Control Panel* &rarr; *Configuration* &rarr; *Components*, and select the *Themes* tab. [Apply the Theme](TODO:applying-themes). The example Themelet modifies the tooltips and alerts the user whether they're viewing the Site from a mobile device.

    ![Themelets can customize the CSS.](./developing-themelets/images/04.png)

    ![Themelets can customize the JavaScript.](./developing-themelets/images/05.png)

Great! You successfully built and deployed a custom Theme with a Themelet.