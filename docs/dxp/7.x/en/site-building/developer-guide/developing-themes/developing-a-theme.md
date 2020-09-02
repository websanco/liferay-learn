# Developing a Theme

Themes are packaged WAR files composed of CSS, HTML (FreeMarker), and JavaScript that can be used to create a custom look and feel for pages.

Here, you'll learn how to develop a Theme with the Liferay JS Themes Toolkit:

1. [Deploy a Theme](#deploy-a-theme)
1. [Modify the Theme and Redeploy](#modify-the-theme-and-redeploy)

This example uses a Docker image with a fresh install of Liferay DXP.

## Deploy a Theme

First, deploy an example to see what a Theme looks like:

1. Run the command below to start the Docker container:

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.1-ga2
    ```

1. Download and unzip the [example Theme](https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-themes/liferay-g4t8.zip) and install its dependencies:

    ```bash
    curl https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-themes/liferay-g4t8.zip
    
    unzip liferay-g4t8.zip
    cd liferay-g4t8
    .\gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
      If testing on Windows, you may need to build the module first with ``.\gradlew build`` and then manually copy the JAR to ``docker cp .\g4t8-impl\dist\pastel-purple-theme.war docker-container-name:/opt/liferay/osgi/war`` directly if deployment fails.
    ```

1. Confirm the deployment to the Liferay Docker container console. The log message below should appear in the Docker console:

    ```bash
    INFO  [fileinstall-/opt/liferay/osgi/war][BundleStartStopLogger:46] STARTED pastel-purple-theme_1.0.0 [1141]
    ```

1. Verify that the Theme is available. Open your browser to `https://localhost:8080`, and open the Product Menu and go to *Site Builder* &rarr; *Pages*. Click the (![Cog icon](../../../images/icon-control-menu-gear.png)) next to Public Pages.

1. Scroll down and click the *Change Current Theme* button, and click the Pastel Purple Theme thumbnail next to the Classic Theme.

    ![The Pastel Purple Theme is listed in the Theme selector.](./developing-a-theme/images/01.png)

1. Click *Save* to apply the changes, and go back to the home page.

    ![Themes can change the look and feel of your Site's pages.](./developing-a-theme/images/02.png)

Great! You successfully deployed and applied a Theme.

## Theme Breakdown

The Purple Pastel Theme was created with the [Liferay JS Themes Toolkit's](https://github.com/liferay/liferay-js-themes-toolkit) [Liferay Theme Generator](./reference/installing-the-theme-generator-reference.md). It's based on the Classic Theme, created with the Classic [Sub-generator](../reference/installing-the-theme-generator-reference.md#generator-and-sub-generator-commands):

```bash
yo liferay-theme:classic
```

```note::
  The ``liferay-theme:classic`` sub-generator is available for Liferay DXP 7.2+ in v9.50+ of the Liferay JS Themes Toolkit.
```

Custom CSS in `_custom.scss` changes the background color of the Header and Footer and updates the font color of the navigation links. Styles are condensed with the [Sass](https://sass-lang.com/) syntax, as demonstrated by the nested styles in the CSS below:

```scss
body header .navbar-classic {
  background-color: #9EB1F5;
  
  &.navbar-light {
    ul > li > a.nav-link.text-truncate {
      color: #FFF;
    }
  }
}
...
body #footer {
	background-color: #9EB1F5;
}
```

A new button is added to the Header's markup beside the User Personal Bar in `portal_normal.ftl`:

```markup
...
<div class="autofit-col">
  <@liferay.user_personal_bar />
</div>

<div class="ml-3 autofit-col">
  <button name="Alert Button" onclick="alert('You pushed the button.')" class="btn btn-primary">Alert</button>
</div>
...
```

Custom JavaScript in the `main.js` file prints a message in the browser's console when the Theme is loaded on the page:

```javascript
console.log('My Theme is loaded to the page.');
```

## Modify the Theme and Redeploy

To update the Theme you just have to modify the code and redeploy.

1. Open the Theme's `portal_normal.ftl` template to modify the page's HTML markup.
1. Remove the button markup in the Header shown below and save the file:

    ```markup
    <div class="ml-3 autofit-col">
      <button name="Alert Button" onclick="alert('You pushed the button.')" class="btn btn-primary">Alert</button>
    </div>
    ```

1. Build the Theme's WAR as you did before.

    ```bash
    cd liferay-g4t8
    .\gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
      If testing on Windows, you may need to build the module first with ``.\gradlew build`` and then manually copy the JAR to ``docker cp .\g4t8-impl\dist\pastel-purple-theme.war docker-container-name:/opt/liferay/osgi/war`` directly if deployment fails.
    ```

1. Confirm the deployment to the Liferay Docker container console:

    ```bash
    INFO  [fileinstall-/opt/liferay/osgi/war][BundleStartStopLogger:46] STARTED pastel-purple-theme_1.0.0 [1141]
    ```

1. Refresh the home page to view the changes.

![You can completely customize the page's styling with the Theme.](./developing-a-theme/images/03.png)

Congratulations! Now you know how to develop and deploy a Theme to Liferay DXP with the Liferay JS Themes Toolkit.

## Related Information

* [Installing the Theme Generator](../reference/themes/installing-the-theme-generator-reference.md)
* [Theme Development Workflow](../reference/themes/liferay-js-themes-toolkit-theme-development-workflow.md)