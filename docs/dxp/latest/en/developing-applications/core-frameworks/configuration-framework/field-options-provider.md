# Field Options Provider

A [dropdown list can be populated manually](https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/configurable-application/setting-and-accessing-configurations.html#implementing-a-dropdown-selection-ui) in the `Meta.AD` annotation of the configuration interface. But the option labels and values can also be populated automatically with the `ConfigurationFieldOptionsProvider` class.

## Deploy the Tutorial Code

1. Start Liferay DXP. If you don't already have a docker container, use

    ```bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. Download and unzip [Field Options Provider](./liferay-z4h3.zip)

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/configurable-application/liferay-z4h3.zip -O
    ```

    ```bash
    unzip liferay-z4h3.zip
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
    STARTED com.acme.z4h3.impl_1.0.0 [1150]
    ```

1. Open your browser to `https://localhost:8080` and navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*. Under Platform click *Third Party*. Click *Z4H3 Configuration* on the left.

    ![The settings UI shows two dropdown lists.](./field-options-provider/images/01.png)

The first dropdown list is manually populated and the second dropdown list is provider populated.

## Setting the Configuration Interface

[Create a configuration interface](https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/configurable-application/setting-and-accessing-configurations.html#creating-the-configuration-interface) and set the configuration field name to be populated.

```literalinclude:: ./field-options-provider/resources/liferay-z4h3.zip/z4h3-impl/src/main/java/com/acme/z4h3/internal/configuration/Z4H3Configuration.java
   :dedent: 1
   :language: java
   :lines: 11-22
```

In the sample project, `providerPopulatedColors` is the configuration field name to be populated.

## Implement the Field Options Provider

Create a new class that implements the `ConfigurationFieldOptionsProvider` class. 

```literalinclude:: ./field-options-provider/resources/liferay-z4h3.zip/z4h3-impl/src/main/java/com/acme/z4h3/internal/configuration/admin/definition/Z4H3ConfigurationFieldOptionsProvider.java
   :language: java
   :lines: 14-20
```

Use an `@Component` annotation to register the service. Include the `configuration.field.name` from the previous section. Set the `configuration.pid` with the fully qualified class name of the configuration interface.

```literalinclude:: ./field-options-provider/resources/liferay-z4h3.zip/z4h3-impl/src/main/java/com/acme/z4h3/internal/configuration/admin/definition/Z4H3ConfigurationFieldOptionsProvider.java
   :dedent: 1
   :language: java
   :lines: 24-46
```

Add a `getOptions` method to return a list of `Option`s. The sample project includes an array that sets the `optionValue` as a string of a color and sets the `optionLabel` as the string stored in the `Langauge.properties` file of that color.

The tutorial code uses a simple example of a string array but a more complex use case could also be employed. For example, get a list of objects for a selection from another module or even iterate through a database to dynamically populate a list.