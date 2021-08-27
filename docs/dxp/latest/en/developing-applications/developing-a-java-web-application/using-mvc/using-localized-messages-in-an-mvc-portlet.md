# Using Localized Messages in an MVC Portlet

Liferay's localization framework is for creating localized messages in your MVC Portlet. 

## Deploy the Sample Code

1. Start Liferay DXP. If you don't already have a docker container, use

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. Download and unzip [Using Localized Messages](./liferay-b6f5.zip).

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/developing-a-java-web-application/using-mvc/liferay-b6f5.zip -O
    ```

    ```bash
    unzip liferay-b6f5.zip
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
    STARTED com.acme.b6f5.web.0.0 [1009]
    ```

1. Verify that the example module is working. Open your browser to `https://localhost:8080`

1. Add the B6F5 Portlet to a page. You can find the example portlet under Sample Widgets.

    ![Add the B6F5 Portlet to a page.](./using-localized-messages-in-an-mvc-portlet/images/01.png)

    You should see the welcome message header from the example.

1. The example project also includes locales for Portuguese and Japanese. For example, use the language selector to select Brazilian Portuguese or Japanese to see the welcome message.

    ![The example shows locales for Portuguese and Japanese.](./using-localized-messages-in-an-mvc-portlet/images/02.png)

Now you can see how it works. 

## Create the Language Properties File

Create a `Language.properties` file and add it to your module's `src/main/resources/content` folder. In the file define the keys to use in your MVC portlet. 

Language property files for other locales can also be included in the folder. For example, to include language keys for Japanese, add a `Language_ja.properties` file to the folder.

## Create the JSP File

Create your JSP file and add it to your module's `/src/main/resources/META-INF/resources` folder.

The example project includes a `view.jsp` file with a simple welcome message that uses the liferay-ui tag library.

```jsp
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<h4>B6F5 Portlet</h4>

<b><liferay-ui:message key="b6f5-portlet-welcome" /></b>
```

Note that there is an extra `liferay-ui:message` included in the example jsp file: 

```jsp
<liferay-ui:message key="supercalifragilisticexpialidocious" />
```

This demonstrates that if no such key is found in the `Language.properties` file, the label appears instead. 

Learn more about tag libraries in [Liferay Tag Library Reference](https://docs.liferay.com/portal/7.3-latest/taglibs/util-taglib/). Each tag library has a list of attributes that can be passed to the tag.

## Add the Component Definition

As you create your custom MVC Portlet, make sure to include the language resource bundle property in the class's `@Component` definition.

```java
@Component(
	property = {
      ...
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.supported-locale=en_US,ja,pt_BR"
	},
	service = Portlet.class
)
```

The example project includes this property to use the language resource bundle: 

```java
"javax.portlet.resource-bundle=content.Language"
```

The different locales are also defined for every language the portlet supports:

```java
"javax.portlet.supported-locale=en_US,ja,pt_BR"
```
