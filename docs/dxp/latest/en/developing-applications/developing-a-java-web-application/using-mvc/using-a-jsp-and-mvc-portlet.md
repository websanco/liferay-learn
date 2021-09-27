# Using a JSP and MVC Portlet

An easy way to start developing a web application is to add markup to a JSP file and render it using a portlet Java class.

The W3E7 example application demonstrates this approach. 

![Here is the example portlet application.](./using-a-jsp-and-mvc-portlet/images/01.png)

The application has a JSP with markup content and an `MVCPortlet` class that renders the JSP. Deploy the example and examine it to learn how to create an application using a JSP with an MVC portlet. 

## Deploy a Simple MVC Portlet Module

Start with deploying the example.

1. Download and unzip the example.

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/developing-applications/developing-a-java-web-application/using-mvc/liferay-w3e7.zip -O
   ```

   ```bash
   unzip liferay-w3e7.zip
   ```

1. Start a [Liferay Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

   ```bash
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
   ```

1. Build and deploy the example.

    ```bash
    cd liferay-w3e7
    ```

    ```bash
     ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    This command is the same as copying module JARs to `/opt/liferay/osgi/modules` on the Docker container.
    ```

1. Confirm the deployment in the Docker container console.

    ```bash
    STARTED com.acme.w3e7.web_1.0.0
    ```

1. Open a browser to `http://localhost:8080`.

1. Sign in using the default credentials:

    **User Name:** `test@liferay.com`

    **Password:** `test`

1. Verify the application's availability by adding the *W3E7 Portlet* widget from the *Samples* category to a widget page.

![Here is the example portlet web application.](./using-a-jsp-and-mvc-portlet/images/02.png)

Congratulations, you've successfully built and deployed a new application.

Next, you'll learn how to create this portlet application.

## How to Create an Application Using MVC Portlet

There are two steps for building the example portlet:

1. Create the view using a JSP.
2. Create an [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) to register the application and render it.

### Create a View Template

In the module's `src/main/resources/META-INF/resources` folder, create a JSP file to be your view template. The file name is arbitrary; the example JSP file is `view.jsp`. Here is the JSP content.

```{literalinclude} ./using-a-jsp-and-mvc-portlet/resources/liferay-w3e7.zip/w3e7-web/src/main/resources/META-INF/resources/view.jsp
   :language: jsp
```

The markup above displays the heading "Hello W3E7."

### Create an MVCPortlet

In the module's `src/main/java` folder, create a package called `com.acme.w3e7.web.internal.portlet`. In that package, add a class called `W3E7Portlet` that extends [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java).

```{literalinclude} ./using-a-jsp-and-mvc-portlet/resources/liferay-w3e7.zip/w3e7-web/src/main/java/com/acme/w3e7/web/internal/portlet/W3E7Portlet.java
   :language: java
   :lines: 17
```

```{note}
The `*.web.internal.portlet` part of the package name is a convention: `web` for the web module type, `internal` because a portlet implementation is private, and `portlet` because the class is a portlet.
```

This extension doesn't require any additional methods; `MVCPortlet`'s built-in methods use component annotations (added next) to render the `view.jsp` template.

### Configure the Portlet With Annotations

A [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) annotation configures the portlet.

```{literalinclude} ./using-a-jsp-and-mvc-portlet/resources/liferay-w3e7.zip/w3e7-web/src/main/java/com/acme/w3e7/web/internal/portlet/W3E7Portlet.java
   :language: java
   :lines: 9-18
```

The `service = Portlet.class` attribute registers the class as a `Portlet`.

The `property` attribute's value describes the portlet web application. The `com.liferay.portlet.display-category=category.sample` property adds the app to the sample widget category. The `javax.portlet.display-name=W3E7 Portlet` property specifies the app's name.

The `javax.portlet.init-param.view-template=/view.jsp` property declares the view template path with respect to the application's `resources/META-INF/resources` folder. When you add the portlet to a page, the `resources/META-INF/resources/view.jsp` view template renders.

```{note}
The [Portlet Descriptor to OSGi Service Property Map](../../reference/portlet-descriptor-to-osgi-service-property-map.md) specifies how OSGi component property values map to traditional portlet descriptors.
```

## What's Next

Congratulations! You've created a web application using a JSP and one simple Java class. There are lots of directions you can go from here. To add more views, see [Rendering Views with MVC Portlet](./rendering-views-with-mvc-portlet.md). To add actions to your application, see [MVC Action Command](./mvc-action-command.md). Or implement a back-end data model using [Service Builder](../../data-frameworks/service-builder.md).

## Additional Information

* [Portlet Descriptor to OSGi Service Property Map](../../reference/portlet-descriptor-to-osgi-service-property-map.md)
* [Using MVC](./using-mvc.md)
