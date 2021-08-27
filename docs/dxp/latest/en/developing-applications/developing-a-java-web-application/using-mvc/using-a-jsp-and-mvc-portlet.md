# Using a JSP and MVC Portlet

An easy way to start developing a web application is to add markup to a JSP file and render it using a portlet Java class.

The W3E7 example application demonstrates this approach. 

![Here is the example portlet application.](./using-a-jsp-and-mvc-portlet/images/01.png)

The application has a JSP with markup content and an `MVCPortlet` class that renders the JSP. Deploy the example and examine it to learn how to create an application using a JSP with an MVC portlet. 

## Deploy a Simple MVC Portlet Module

Start with deploying the example.

1. Download and unzip the example.

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/developing-applications/using-mvc/liferay-w3e7.zip -O
   ```

   ```bash
   unzip liferay-w3e7.zip
   ```

1. Start a [Liferay Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

   ```bash
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

1. Build and deploy the example.

    ```bash
    cd liferay-w3e7
    ```

    ```bash
     ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       This command is the same as copying module JARs to ``/opt/liferay/osgi/modules`` on the Docker container.
    ```

1. Confirm the deployment in the Docker container console.

    ```bash
    STARTED com.acme.w3e7.web_1.0.0
    ```

1. Verify the application's availability. Open your browser to `https://localhost:8080`. Add the *W3E7 Portlet* widget from the *Samples* category to a widget page.

![Here is the example portlet web application.](./using-a-jsp-and-mvc-portlet/images/02.png)

Congratulations, you've successfully built and deployed a new application.

Next, you'll learn how to create this portlet.

## How to Create an Application Using MVC Portlet

There are two steps for building the example portlet:

1. Create the view using a JSP.
2. Create an [`MVCPortlet`](https://docs.liferay.com/dxp/portal/7.3-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html) to register the application and render it.

### Create a View Template

In the module's `src/main/resources/META-INF/resources` folder, create a JSP file to be your view template. The file name is arbitrary; the example JSP file is `view.jsp`. Here is the JSP content.

```javascript
<h4>W3E7 Portlet</h4>

<h5>Hello W3E7</h5>
```

The markup above displays "Hello W3E7" in bold.

### Create an MVCPortlet

In the module's `src/main/java` folder, create a package called `com.acme.w3e7.web.internal.portlet`. In that package, add a class called `W3E7Portlet` that extends [`MVCPortlet`](https://docs.liferay.com/dxp/portal/7.3-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html).

```java
public class W3E7Portlet extends MVCPortlet {
}
```

```note::
   The ``*.web.internal.portlet`` part of the package name is a convention: `web` for the web module type, ``internal`` because a portlet implementation is private, and ``portlet`` because the class is a portlet.
```

This extension doesn't require any additional methods; `MVCPortlet`'s built-in methods use the component annotations (added next) to render the `view.jsp` template.

### Configure the Portlet With Annotations

A `@Component` annotation configures the portlet.

```java
@Component(
   property = {
       "com.liferay.portlet.display-category=category.sample",
       "javax.portlet.display-name=W3E7 Portlet",
       "javax.portlet.init-param.view-template=/view.jsp"
   },
   service = Portlet.class
)
public class W3E7Portlet extends MVCPortlet {
}
```

The `service = Portlet.class` attribute registers the class as a `Portlet`.

The property values describe the portlet web application. The `com.liferay.portlet.display-category=category.sample` property adds the app to the sample widget category. The `javax.portlet.display-name=W3E7 Portlet` property specifies the app's name.

The template path initialization parameter `javax.portlet.init-param.template-path` declares the starting location in the `resources/META-INF/resources` for finding the view templates. The `javax.portlet.init-param.view-template=/view.jsp` property declares the portlet's view template `resources/META-INF/resources/view.jsp`. When you add the portlet to a page, the view template renders.

```note::
   The `Portlet Descriptor to OSGi Service Property Map <../../reference/portlet-descriptor-to-osgi-service-property-map.md>`_ specifies how OSGi component property values map to traditional portlet descriptors.
```

## Conclusion

Congratulations! You've created a web application using a JSP and one simple Java class. There are lots of directions you can go from here. To add actions to your application, see [MVC Action Command](./mvc-action-command.md). Or implement a back-end data model using [Service Builder](../../data-frameworks/service-builder.md).

## Additional Information

* [Portlet Descriptor to OSGi Service Property Map](../../reference/portlet-descriptor-to-osgi-service-property-map.md)
* [Using MVC](./using-mvc.md)
