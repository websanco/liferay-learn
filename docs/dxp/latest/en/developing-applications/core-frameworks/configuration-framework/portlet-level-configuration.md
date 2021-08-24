# Portlet Level Configuration

An [application's configuration can be set for different levels of scope](./scoping-configurations.md). Where instance and site scoped configurations use `ConfigurationProvider`, portlet scoped configurations should use `PortletDisplay` as shown in the example below.

Note, an application's configuration is overriden if portlet preferences are implemented and set. See [Portlet Preferences](../../developing-a-java-web-application/using-mvc/portlet-preferences.md) to learn more.

## See the Example Code

1. Start Liferay DXP. If you don't already have a docker container, use

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

    If you're running a different Liferay Portal version or Liferay DXP, adjust the above command accordingly. 

1. Download and unzip [Sharing Localized Messages](./liferay-x7y2.zip).

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/configurable-application/liferay-x7y2.zip -O
    ```

    ```bash
    unzip liferay-x7y2.zip
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
    STARTED com.acme.x7y2.web_1.0.0 [1651]
    ```

1. Verify that the example module is working. Open your browser to `https://localhost:8080`

1. Add the X7Y2 Portlet to a page. You can find the example portlet under Sample Widgets.