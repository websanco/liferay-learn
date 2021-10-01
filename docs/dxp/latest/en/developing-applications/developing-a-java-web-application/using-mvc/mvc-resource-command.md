# MVC Resource Command

MVC Resource Command classes retrieve resources: images, XML, or any other kind of resource from a DXP/Portal instance without triggering any actions or renders. Requests or portlet resource URLs invoke MVC Resource Commands.

You'll deploy an example portlet that uses an MVC Resource Command and then examine it.

## Invoke an MVC Resource Command

The example portlet downloads a simple file using an MVC Resource Command.

1. Start a [Liferay Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

   ```bash
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

1. Download and unzip the example.

   ```bash
   curl https://learn.liferay.com/dxp/latest/dxp/latest/en/developing-applications/developing-a-java-web-application/using-mvc/liferay-p8v5.zip -O
   ```

   ```bash
   unzip liferay-p8v5.zip
   ```

1. Build and deploy the example.

    ```bash
    cd liferay-p8v5
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    This command is the same as copying module JARs to `/opt/liferay/osgi/modules` on the Docker container.
    ```

1. Confirm the deployment in the Docker container console.

    ```bash
    STARTED com.acme.p8v5.web_1.0.0
    ```

1. Open a browser to `http://localhost:8080`.

1. Sign in using the default credentials:

    **User Name:** `test@liferay.com`

    **Password:** `test`

1. Add the *P8V5 Portlet* widget from the *Samples* category to a widget page. The P8V5 Portlet appears.

    ![You've added the P8V5 Portlet to a page.](./mvc-resource-command/images/01.png)

    The link invokes an MVC Resource Command to download a simple text file.

1. Click *Download*. A file called `p8v5.txt` downloads to your machine.

1. Open the `p8v5.txt` file. Here's the content:

    ```
    Hello P8V5!
    ```

You've downloaded a file using an MVC Resource Command. Now see how it works.

## Examine the Portlet

`P8V5Portlet` is a minimal [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java).

```{literalinclude} ./mvc-resource-command/resources/liferay-p8v5.zip/p8v5-web/src/main/java/com/acme/p8v5/web/internal/portlet/P8V5Portlet.java
:language: java
:lines: 9-19
```

The [`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html) `javax.portlet.name` property sets the portlet's name.

```{literalinclude} ./mvc-resource-command/resources/liferay-p8v5.zip/p8v5-web/src/main/java/com/acme/p8v5/web/internal/portlet/P8V5Portlet.java
:dedent: 2
:language: java
:lines: 14
```

```{note}
`MVCResourceCommand`s bind to a portlet by the portlet's name (e.g., the portlet component `javax.portlet.name` property value).
```

The portlet's MVC Resource Command class is next.

## Examine the MVCResourceCommand Class

MVC Resource Command classes can implement [`MVCResourceCommand`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.java) directly or implement it indirectly by extending [`BaseMVCResourceCommand`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCResourceCommand.java). `P8V5DownloadMVCResourceCommand` directly implements `MVCResourceCommand`.

```{literalinclude} ./mvc-resource-command/resources/liferay-p8v5.zip/p8v5-web/src/main/java/com/acme/p8v5/web/internal/portlet/action/P8V5DownloadMVCResourceCommand.java
:language: java
:lines: 16-47
```

`P8V5DownloadMVCResourceCommand` is a [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) that provides the `MVCResourceCommand` service. The component properties apply the `P8V5DownloadMVCResourceCommand` to the portlet named `com_acme_p8v5_web_internal_portlet_P8V5Portlet` and map `P8V5DownloadMVCResourceCommand` to the MVC command name `/p8v5/download`. The name you specify here must match the name declared in the portlet. 

```{note}
You can associate an `MVCResourceCommand` component with multiple portlets by declaring separate `javax.portlet.name` properties for each portlet:

      @Component(
         property = {
            "javax.portlet.name=com_acme_p8v5_web_internal_portlet_P8V5Portlet",
            "javax.portlet.name=com_acme_p8v5_web_internal_portlet_P8V6Portlet",
            "mvc.command.name=/p8v5/download"
         },
         service = MVCResourceCommand.class
      )
```

The example `serveResource` method creates a simple text file and sends it to the user via [`PortletResponseUtil`](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/portlet/PortletResponseUtil.java). The method returns `true` if an error occurs or `false` otherwise.

Next you'll learn how the portlet's JSP maps a UI component action to the command.

## Examine the Portlet Resource URL

The portlet's `view.jsp` file renders a link for invoking the MVC Resource Command.

```{literalinclude} ./mvc-resource-command/resources/liferay-p8v5.zip/p8v5-web/src/main/resources/META-INF/resources/p8v5/view.jsp
:language: javascript
```

The first line makes the Portlet 2.0 tag library available via the `portlet` prefix. This JSP binds an action to a UI component using the tag library's `portlet:resourceURL` tag. The tag's `id` attribute uses the MVC Resource Command's `mvc.command.name` property value. The following table shows the correlation:

| `view.jsp` Portlet Resource URL | `P8V5DownloadMVCResourceCommand` Component Property |
| ----------------------------- | ----------------------------------------------- |
| `<a href="<portlet:resourceURL id="/p8v5/download" />">Download</a>` | `mvc.command.name=/p8v5/download` |

Clicking the view's *Download* link invokes `P8V5DownloadMVCResourceCommand`'s `serveResource` method.

## What's Next

Now you know how to use MVC Resource Commands. If you want to localize your portlet's content, see [Using Localized Messages](./using-localized-messages-in-an-mvc-portlet.md). If you're ready to develop model, persistence, and service layers, visit [Service Builder](../../data-frameworks/service-builder.md).

## Additional Information

* [Using MVC](./using-mvc.md)
* [MVC Render Command](./mvc-render-command.md)
* [MVC Action Command](./mvc-action-command.md)
* [Overriding MVC Commands](../../../liferay-internals/extending-liferay/overriding-mvc-commands.md)
