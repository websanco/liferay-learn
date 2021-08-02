# Invoking Actions with MVC Portlet

A portlet's [*Action phase*](../reference/portlets.md#portlet-phases) applies state changes. You can bind your portlet's action-handling methods to UI components using *portlet action URLs*. They are `portlet:actionURL` JSP tags that map the user's request to a portlet method for performing an action.

Here you'll learn how to invoke and examine an example portlet that uses action URLs three different ways.

## Deploy an MVC Portlet that Handles Actions

The example MVC Portlet has three portlet action URLs that map to separate portlet methods. Here's how to deploy the portlet and trigger its actions.

1. Start a [Liferay Docker container](../../installation-and-upgrades/installing-liferay/using-liferay-dxp-docker-images/dxp-docker-container-basics.md).

   ```bash
   docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

1. Download and unzip the example.

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/developing-applications/developing-a-java-web-application/using-mvc/liferay-u8t2.zip -O
   ```

   ```bash
   unzip liferay-u8t2.zip
   ```

1. Build and deploy the example.

    ```bash
    cd liferay-u8t2
    ```

    ```bash
     ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    This command is the same as copying module JARs to `/opt/liferay/osgi/modules` on the Docker container.
    ```

1. Confirm the deployment in the Docker container console.

    ```bash
    STARTED com.acme.u8t2.web_1.0.0
    ```

1. Open a browser to `http://localhost:8080`.

1. Sign in using the default credentials:

    **User Name:** `test@liferay.com`

    **Password:** `test`

1. Add the *U8T2 Portlet* widget from the *Samples* category to a widget page. The U8T2 Portlet appears.

    ![You've added the U8T2 Portlet to a page.](./invoking-actions-with-mvc-portlet/images/01.png)
    
    The portlet has three links:

    * *Do Something*
    * *Do Something Else*
    * *Do Something More*

    Clicking each link invokes a different action-handling method. For learning purposes, each method logs a message that identifies itself. 

1. Click *Do Something*. The portlet logs invoking the `doSomething` method.

    ```bash
    [U8T2Portlet:28] Invoke #doSomething(ActionRequest, ActionResponse)
    ```

1. Click *Do Something Else*. The portlet logs invoking the `doSomethingElse` method.

    ```bash
    [U8T2Portlet:36] Invoke #doSomethingElse(ActionRequest, ActionResponse)
    ```

1. Click *Do Something More*. The portlet logs invoking the `doSomethingMore` method.

    ```bash
    [U8T2Portlet:45] Invoke #doSomethingMore(ActionRequest, ActionResponse)
    ```

These actions are trivial, but they demonstrate different ways to map UI components to portlet action-handling methods. Next explore the portlet class and view JSP.

## Examine the Portlet's Action-Handling Methods

The `U8T2Portlet` class is a standard [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) that has three action-handling methods.

```{literalinclude} ./invoking-actions-with-mvc-portlet/resources/liferay-u8t2.zip/u8t2-web/src/main/java/com/acme/u8t2/web/internal/portlet/U8T2Portlet.java
   :language: java
   :lines: 22-51
```

The [`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html) annotation marks the class as an OSGi Declarative Services Component that provides the [`Portlet`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/Portlet.html) service. The properties make the portlet available in the *Sample* widget category, name the portlet *U8T2 Portlet*, and set the portlet's default view template to `/view.jsp`.

Each method takes an [`ActionRequest`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/ActionRequest.html) and [`ActionResponse`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/ActionResponse.html) parameter. The `ActionRequest` provides the method information and the `ActionResponse` provides a means for the method to pass along information. Each example method identifies itself with a log message. 

The JSP (discussed next) maps to the example methods using portlet action URLs. The first two methods are the same except for their names. The last method stands out because of its `@ProcessAction(name = "nameForTheDoSomethingMoreMethod")` annotation. Portlet action URLs can map to a method via a [`@ProcessAction`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/ProcessAction.html) annotation name instead of the method name. By using the `ProcessAction` name, for example, you can change the method name or assign the annotation to a different method without breaking the portlet action URL. Examining the JSP's portlet action URLs makes this easier to understand.

## Examine the JSP

The `view.jsp` binds UI components to the portlet's action-handling methods using portlet action URLs. Here's the `view.jsp` code:

```{literalinclude} ./invoking-actions-with-mvc-portlet/resources/liferay-u8t2.zip/u8t2-web/src/main/resources/META-INF/resources/view.jsp
   :language: javascript
```

The first line makes the Portlet 2.0 tag library available via the `portlet` prefix. This JSP binds actions to UI components using the tag library's `portlet:actionURL` tag. Examine each action URL.

## Example 1: Referencing an Action URL by its Variable

The `view.jsp` declares the following portlet action URL.

```{literalinclude} ./invoking-actions-with-mvc-portlet/resources/liferay-u8t2.zip/u8t2-web/src/main/resources/META-INF/resources/view.jsp
   :language: javascript
   :lines: 3
```

A `portlet:actionURL`'s `name` attribute maps to the `doSomething` portlet method. The `var` attribute assigns the portlet action URL to an arbitrary variable. This JSP binds the action URL to a hyperlink labeled `Do Something` by referencing the `actionURL` variable.

```{literalinclude} ./invoking-actions-with-mvc-portlet/resources/liferay-u8t2.zip/u8t2-web/src/main/resources/META-INF/resources/view.jsp
   :dedent: 1
   :language: javascript
   :lines: 6
```

Clicking this link invokes the portlet's `doSomething` method. You can bind the action URL to multiple UI components by referencing the action URL's variable.

## Example 2: Declaring an Action URL in a UI Component

The JSP declares another action URL directly in an anchor component.

```{literalinclude} ./invoking-actions-with-mvc-portlet/resources/liferay-u8t2.zip/u8t2-web/src/main/resources/META-INF/resources/view.jsp
   :dedent: 1
   :language: javascript
   :lines: 10
```

The component declares an action URL that binds the action to the portlet's `doSomethingElse` method. It's a more compact way of mapping an action URL.

## Example 3: Referencing a Portlet Action Name

Lastly, in the following anchor, the JSP declares an action URL that maps to an action-processing method associated with the `nameForTheDoSomethingMoreMethod` portlet action name.

JSP action URL:

```{literalinclude} ./invoking-actions-with-mvc-portlet/resources/liferay-u8t2.zip/u8t2-web/src/main/resources/META-INF/resources/view.jsp
   :dedent: 1
   :language: javascript
   :lines: 14
```

Portlet method:

```{literalinclude} ./invoking-actions-with-mvc-portlet/resources/liferay-u8t2.zip/u8t2-web/src/main/java/com/acme/u8t2/web/internal/portlet/U8T2Portlet.java
   :dedent: 1
   :language: java
   :lines: 40-42
```

The portlet parameter named `nameForTheDoSomethingMoreMethod` provides looser coupling between the action URL and method. For example, it frees you to change the method name or to assign the `@ProcessAction(name = "nameForTheDoSomethingMoreMethod")` annotation to a different method.

## What's Next

Now that you know how to use action URLs, you can concentrate on writing action methods. Alternatively, you could investigate using [MVCActionCommand classes](./mvc-action-command.md). Or if you're ready to explore handling other portlet phases, look at using [MVCRenderCommand](./mcv-render-command.md) and [MVCResourceCommand](./mvc-resource-command.md) classes.

## Additional Information

* [MVCActionCommand classes](./mvc-action-command.md)
* [MVCRenderCommand](./mcv-render-command.md)
* [MVCResourceCommand](./mvc-resource-command.md)
* [Using Localized Messages in an MVC Portlet](./using-localized-messages-in-an-mvc-portlet.md)