# DDM Form Annotations

The configuration UI that is automatically generated after [creating a configuration interface](./setting-and-accessing-configurations.html#creating-the-configuration-interface) may be too simplistic for some configurations. Use the Dynamic Data Mapping (DDM) form annotations to customize the layout of the UI.

## See a Sample Configuration UI

1. Start Liferay DXP. If you don't already have a docker container, use

    ```bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. Download and unzip [DDM Form Annotations](./liferay-v1d9.zip)

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/configurable-application/liferay-v3d9.zip -O
    ```

    ```bash
    unzip liferay-v1d9.zip
    ```

1. From the module root, build and deploy.

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       This command is the same as copying the deployed jars to /opt/liferay/osgi/modules on the Docker container.
    ```

1. Confirm the deployment in the Liferay Docker container console.

    ```
    STARTED com.acme.v1d9.impl_1.0.0 [1650]
    ```

1. Open your browser to `https://localhost:8080` and navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*. Under Platform click *Third Party*. Click *V1D9 Configuration* on the left.

    ![The UI layout is customized by DDM form annotations.](./ddm-form-annotations/images/01.png)

Here's how the DDM form annotations work.

