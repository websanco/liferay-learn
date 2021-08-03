# Sharing Localized Messages

As you work on an application you might have multiple modules, each of which has its own language keys. Instead of maintaining various language properties files in different places, consolidate them into one place. This example project demonstrates how language keys can be shared across different modules.

## Run the Tutorial Code

1. Start Liferay DXP. If you don't already have a docker container, use

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

    If you're running a different Liferay Portal CE version or Liferay DXP, adjust the above command accordingly. 

1. Download and unzip [Sharing Localized Messages](./liferay-u8t2.zip).

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/developing-a-java-web-application/using-mvc/liferay-u8t2.zip -O
    ```

    ```bash
    unzip liferay-u8t2.zip
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
    STARTED com.acme.u8t2.impl_1.0.0 [1650]
    STARTED com.acme.u8t2.web_1.0.0 [1651]
    ```

1. Verify that the example module is working. Open your browser to `https://localhost:8080`

1. Add the U8T2 Portlet to a page. You can find the example portlet under Sample Widgets.

    ![Add the U8T2 Portlet to a page.](./sharing-localized-messages/images/01.png)

    You see the welcome message header and a list of colors. Note that the language keys for colors come from the shared language keys. The language key `colors` comes from Liferay's global language keys.

1. This example project also includes locales for Portuguese and Japanese. For example, use the language selector to select Brazilian Portuguese or Japanese to see the welcome message and list of colors.

    ![The example shows locales for Portuguese and Japanese.](./sharing-localized-messages/images/02.png)

Now it's time to learn how it works.

## Create the Language Properties File

Create a separate project module to hold all the shared language keys. In the example project, the shared keys are in the `Acme U8T2 Impl` module. 

Create a `Language.properties` file and add it to the module's `src/main/resources/content` folder. In the file define the keys you want to share with the other modules. 

The example project has a list of six colors used by the `Acme U8T2 Web` module:

```properties
blue=Blue
green=Green
orange=Orange
purple=Purple
red=Red
yellow=Yellow 
```

Language property files for other locales can also be included in the folder. For example, to include language keys for Japanese, add a `Language_ja.properties` file to the folder.

## Add the bnd Instruction

For each module that you want to share language keys with, you must specify the resource in the bnd header. 

```properties
Bundle-Name: Acme U8T2 Web
Bundle-SymbolicName: com.acme.u8t2.web
Bundle-Version: 1.0.0
-liferay-aggregate-resource-bundles: com.acme.u8t2.impl
```

The example project has a web portlet that uses the color language keys from `Acme U8T2 Impl`. In the `bnd.bnd` file of the `Acme U8T2 Web` module, the resource bundle is specified:

```properties
-liferay-aggregate-resource-bundles: com.acme.u8t2.impl
```

Note that you can still choose to place some language keys in an individual module. For example, the welcome message in the example project comes from the `Acme U8T2 Web` module's language keys and not the shared keys of `Acme U8T2 Impl`. Language keys of an individual module take priority over any shared keys specified by `-liferay-aggregate-resource-bundles`. 

## Related Topics

* [Aggregating Resource Bundles](../../core-frameworks/localization/reference/aggregating-resource-bundles.md)
