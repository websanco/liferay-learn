# Using the Liferay Commerce Docker Image

The latest version Liferay Commerce is available as a docker image. Follow these steps to download the latest Docker image release of Liferay Commerce and get started.

1. Get Liferay Commerce

    ```tip::
       Don't have Docker? Go here first: `Linux <https://docs.docker.com/install/linux/docker-ce/ubuntu/>`_ | `Windows <https://docs.docker.com/docker-for-windows/install/>`_ | `OSX <https://docs.docker.com/docker-for-mac/install/>`_
    ```

    ```bash
    docker pull [$LIFERAY_LEARN_COMMERCE_DOCKER_IMAGE$]
    ```

1. Start Liferay Commerce

    ```bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_COMMERCE_DOCKER_IMAGE$]
    ```

    ```important::
       Wait until you see ``org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds``
    ```

1. Open your browser to `https://localhost:8080`

## What's Next

Doing more than just checking things out? See the [Installation Overview](../installation-overview.md) to learn more.

Want to get a store up and running in minutes? Learn about [Accelerators](../../starting-a-store/accelerators.md).
