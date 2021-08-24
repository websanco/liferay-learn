# Portlet Preferences

Portlet preferences allow users to configure a preference for an individual portlet. Portlet preferences can be added to any MVC Portlet so that users can have a UI to access and set their preferences.

Note, portlet preferences are stored properties that are separate from an application's configuration. To learn more see [Portlet Level Configuration](../../core-frameworks/configurable-application/portlet-level-configuration.md)

## See a Sample Implementation

1. Start Liferay DXP. If you don't already have a docker container, use

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

    If you're running a different Liferay Portal version or Liferay DXP, adjust the above command accordingly. 

1. Download and unzip [Portlet Preferences](./liferay-p1z2.zip).

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/developing-a-java-web-application/using-mvc/liferay-p1z2.zip -O
    ```

    ```bash
    unzip liferay-p1z2.zip
    ```

1. From the module root, build and deploy.

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       This command is the same as copying the deployed jars to /opt/liferay/osgi/modules on the Docker container.
    ```

1. Confirm the deployment in the Liferay Docker container console.

    ```bash
    STARTED com.acme.p1z2.web_1.0.0 [2725]
    ```

1. Verify that the example module is working. Open your browser to `https://localhost:8080`

1. Add the P1Z2 portlet to a page. You can find the example portlet under Sample Widgets. Note the default color is set to blue.

1. Click on the portlet's options icon (![options icon](../../../images/icon-options.png)) and click *Configuration*. The portlet's preferences window opens.

    ![Open the portlet's preferences by clicking configuration](./portlet-preferences/images/01.png)

1. Select a different color and click *Save*. Close the preferences menu and now the portlet should display your new selection.

Let's examine how the preferences work.

## Create the Configuration JSP

```{literalinclude} ./portlet-preferences/resources/liferay-p1z2.zip/p1z2-web/src/main/resources/META-INF/resources/configuration.jsp
:language: jsp
:lines: 8-30
```

## Create the Configuration Action

```{literalinclude} ./portlet-preferences/resources/liferay-p1z2.zip/p1z2-web/src/main/java/com/acme/p1z2/web/internal/portlet/action/P1Z2ConfigurationAction.java
:language: java
:lines: 14-34
```

## Add the Component Definition

```{literalinclude} ./portlet-preferences/resources/liferay-p1z2.zip/p1z2-web/src/main/java/com/acme/p1z2/web/internal/portlet/P1Z2Portlet.java
:language: java
:lines: 9-18
```

## Add the Preference Logic

```{literalinclude} ./portlet-preferences/resources/liferay-p1z2.zip/p1z2-web/src/main/resources/META-INF/resources/view.jsp
:language: jsp
:lines: 7
```

