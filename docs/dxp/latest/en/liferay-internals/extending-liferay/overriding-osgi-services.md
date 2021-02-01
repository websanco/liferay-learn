# Reconfiguring Components to Use Your OSGi Service

Liferay DXP's OSGi container is a dynamic environment in which a service can deployed (See [Using an OSGi Service](../fundamentals/using-an-osgi-service.md)). A service can also be overridden with a configuration file (config file) to use a custom service.

## Overview

1. [**Deploy an Example**](#deploy-the-example)
1. [**Override a Service**](#override-a-service)

## Deploy the Example

1. Start Liferay DXP. If you don't already have a container, use

  ```bash
  docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
  ```

  If you already have a docker container, use

  ```bash
  docker start -i [container_name]
  ```

  If you're running a different Liferay Portal CE version or Liferay DXP, adjust the above command accordingly.

1. Download and unzip [Custom OSGi Service](./reconfiguring-components-to-use-your-osgi-service/resources/liferay-m1t1.zip).

  ```bash
  curl https://learn.liferay.com/dxp/7.x/en/liferay-internals/extending-liferay/overriding-osgi-services/reconfiguring-components-to-use-your-osgi-service/resources/liferay-m1t1.zip -O
  ```

  ```bash
  unzip liferay-r1s1.zip
  ```

1. From the module root, build and deploy.

  ```bash
  ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
  ```

  ```tip::
    This command is the same as copying the deployed jars to /opt/liferay/osgi/modules on the Docker container.
  ```

1. Confirm the deployment of each module in the Liferay Docker container console.

  ```bash
  STARTED com.acme.m1t1.api_1.0.0 [1357]
  STARTED com.acme.m1t1.custom.impl_1.0.0 [1358]
  STARTED com.acme.m1t1.impl_1.0.0 [1359]
  STARTED com.acme.m1t1.web_1.0.0 [1360]
  ```

## Override a Service

The deployed example contains four modules: a M1T1 API, a M1T1 implementation, a M1T1 custom implementation, and a M1T1 web app.

The M1T1 web app is a custom widget that can be added to a Site page. To add the widget,

1. Navigate to a Site page.

1. Click the edit icon (![edit icon](../../../images/icon-edit.png)) and find the M1T1 Portlet under the Sample section of Widgets. Drag it onto your page.

  ![Find the M1T1 Portlet widget and add it to your page.](./reconfiguring-components-to-use-your-osgi-service/images/01.png)

1. The M1T1 widget displays a message:

  `I'm calling a service ... M1T1`

  By default, the widget is utilizing the M1T1 implementation.

1. To have the widget use the M1T1 custom implementation, deploy the configuration file to the Liferay Docker container:

  ```bash
  docker cp com.acme.m1t1.web.internal.portlet.M1T1Portlet.config $(docker ps -lq):/opt/liferay/deploy/osgi/configs
  ```

1. The configuration file overrides the M1T1 implementation with the M1T1 custom implementation. Refresh the page and the M1T1 Portlet widget now displays:

  `I'm calling a service ... M1T1CustomImpl, which delegates to M1T1`
