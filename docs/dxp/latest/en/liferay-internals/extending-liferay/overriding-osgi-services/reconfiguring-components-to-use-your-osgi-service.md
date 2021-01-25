# Reconfiguring Components to Use Your OSGi Service

Liferay DXP's OSGi container is a dynamic environment in which a service can deployed (See [Examining a OSGi Service](./examining-an-osgi-service.md)). A service can also be overridden by using a configuration file (config file) to use a custom service.

## Overview

1. [**Deploy an Example**](#deploy-the-example)
1. [**Walk Through the Example**](#walk-through-the-example)
1. [**Additional Information**](#additional-information)

## Deploy the Example

1. Start Liferay DXP. If you don't already have a container, use

  ```bash
  docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
  ```

  If you already have a docker container, use

  ```bash
  docker start -i [container_name]
  ```

  If you're running a different Liferay Portcal CE version or Liferay DXP, adjust the above command accordingly.

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
  STARTED com.acme.r2f1.impl_1.0.0 [1009]
  ```

## Walk Through the Example

Review the deployed example. It contains four modules: a M1T1 api, an implementation, a custom implementation, and a web app.
