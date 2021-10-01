# Using MVC

```{toctree}
:maxdepth: 3

using-mvc/using-a-jsp-and-mvc-portlet.md
using-mvc/rendering-views-with-mvc-portlet.md
using-mvc/invoking-actions-with-mvc-portlet.md
using-mvc/mvc-action-command.md
using-mvc/mvc-render-command.md
using-mvc/mvc-resource-command.md
using-mvc/portlet-preferences.md
using-mvc/using-localized-messages-in-an-mvc-portlet.md
using-mvc/sharing-localized-messages.md
```

If you're an experienced developer, this is not the first time you've heard about Model View Controller. If there are so many implementations of MVC frameworks in Java, why did Liferay create yet another one? Stay with us and you'll see that Liferay MVC Portlet provides these benefits:

* It's lightweight, as opposed to many other Java MVC frameworks.
* There are no special configuration files that need to be kept in sync with your code.
* It's an extension of [`GenericPortlet`](https://learn.liferay.com/reference/latest/en/portlet-api/javax/portlet/GenericPortlet.html).
* You avoid writing a bunch of boilerplate code, since Liferay's MVC Portlet framework only looks for some pre-defined parameters when the `init()` method is called.
* The controller can be broken down into MVC command classes, each of which handles the controller code for a particular [portlet phase](./reference/portlets.md) (render, action, and resource serving phases).
* MVC command classes can serve multiple portlets.
* Liferay's portlets use it. That means there are plenty of robust implementations to reference when you need to design or troubleshoot your Liferay applications.

The Liferay MVC Portlet framework is light and easy to use.

Here, you'll learn how MVC Portlet works by covering these topics:

* [MVC layers and modularity](#mvc-layers-and-modularity)
* [Liferay MVC command classes](#liferay-mvc-command-classes)
* [Liferay MVC portlet component](#liferay-mvc-portlet-component)
* [Simple MVC portlets](#a-simpler-mvc-portlet)

Consider how each layer of the Liferay MVC portlet framework helps you separate the concerns of your application.

## MVC Layers and Modularity

In MVC, there are three layers.

**Model:** The model layer holds the application data and logic for manipulating it.

**View:** The view layer displays data.

**Controller:** The middle man in the MVC pattern, the Controller passes the data back and forth between the view and the model layers.

Liferay DXP's applications are divided into multiple discrete [modules](../../liferay-internals/architecture/osgi-and-modularity.md). With [Service Builder](../data-frameworks/service-builder.md), the model layer is generated into `service` and `api` [modules](../../liferay-internals/fundamentals/module-projects.md). The view and the controller layers share a module, the `web` module.

Generating the skeleton for a multi-module Service Builder-driven MVC application [project](../../liferay-internals/fundamentals/module-projects.md) using [Workspace](../tooling/liferay-workspace/creating-code-with-liferay-workspace.md) saves you lots of time and gets you started on the more important (and interesting, if we're being honest) development work.

## Liferay MVC Command Classes

In a larger application, your `-Portlet` class can become monstrous and unwieldy if it holds all of the controller logic. Liferay provides MVC command classes to break up your controller functionality.

* **[`MVCActionCommand`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html):** Use `-ActionCommand` classes to hold each of your portlet actions, which are invoked by action URLs.
* **[`MVCRenderCommand`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html):** Use `-RenderCommand` classes to hold a `render` method that dispatches to the appropriate JSP, by responding to render URLs.
* **[`MVCResourceCommand`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html):** Use `-ResourceCommand` classes to serve resources based on resource URLs.

There must be some confusing configuration files to keep everything wired together and working properly, right? Wrong: it's all easily managed in the `-Portlet` class's `@Component` annotation.

## Liferay MVC Portlet Component

Whether or not you plan to split up the controller into MVC command classes, the portlet [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) annotation configures the portlet. Here's a simple portlet component as an example:

```java
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=Hello World Portlet",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=com_acme_hello_world_web_internal_portlet_HelloWorldPortlet"
	},
	service = Portlet.class
)
public class HelloWorldPortlet extends MVCPortlet {
```

The `javax.portlet.name` property is required. When using MVC commands, the `javax.portlet.name` property value links particular portlet URL/command combinations to the correct portlet.

```{important}
Make your portlet name unique, considering how [Liferay DXP uses the name to create the [portlet's ID](../reference/portlet-descriptor-to-osgi-service-property-map.md#ten).
```

There can be some confusion over exactly what kind of `Portlet.class` implementation you're publishing with a component. The service registry expects this to be the [`javax.portlet.Portlet`](https://learn.liferay.com/reference/latest/en/portlet-api/javax/portlet/Portlet.html) interface. Import that, and not, for example, `com.liferay.portal.kernel.model.Portlet`.

```{note}
The DTD [liferay-portlet-app_7_3_0.dtd](https://learn.liferay.com/reference/latest/en/dxp/definitions/liferay-portlet-app_7_3_0.dtd.html) defines all the Liferay-specific attributes you can specify as properties in your portlet components. The properties namespaced with `javax.portlet.` are elements of the [`portlet.xml` descriptor](https://docs.liferay.com/portlet-api/3.0/portlet-app_3_0.xsd).
```

## A Simpler MVC Portlet

In simpler applications, you don't use MVC commands. Your portlet render URLs specify JSP paths in the `mvcPath` parameter and your [`MVCPortlet`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html) method overrides implement control logic. The following JSP code includes a portlet render URL that specifies the JSP path `/view_2.jsp`:

```jsp
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:renderURL var="view2URL">
	<portlet:param name="mvcPath" value="/view_2.jsp" />
</portlet:renderURL>

<a href="<%= view2URL %>">Go to View 2</a>
```

If a user clicks the link, the portlet receives the `mvcPath` request parameter and handles the control logic in its `render` method. See [Rendering Views with MVC Portlet](./using-mvc/rendering-views-with-mvc-portlet.md) for details.

## What's Next

As you've seen, Liferay's MVC Portlet framework gives you a well-structured controller layer. If you want to learn MVC Portlet basics, start with these tutorials:

* [Using a JSP and MVC Portlet](./using-mvc/using-a-jsp-and-mvc-portlet.md)
* [Rendering Views with MVC Portlet](./using-mvc/rendering-views-with-mvc-portlet.md)
* [Invoking Actions with MVC Portlet](./using-mvc/invoking-actions-with-mvc-portlet.md)

If you want to develop control logic in separate MVC command classes, read these articles:

* [MVC Action Command](./using-mvc/mvc-action-command.md)
* [MVC Render Command](./using-mvc/mvc-render-command.md)
* [MVC Resource Command](./using-mvc/mvc-resource-command.md)

If you're ready to develop your model layer, see [Service Builder](../data-frameworks/service-builder.md).

## Additional Information

* [Portlet Preferences](./using-mvc/portlet-preferences.md)
* [Using Localized Messages in an MVC Portlet](./using-mvc/using-localized-messages-in-an-mvc-portlet.md)
* [Module Projects](../../liferay-internals/fundamentals/module-projects.md)
* [Workspace](../tooling/liferay-workspace/creating-code-with-liferay-workspace.md)
* [Fundamentals](../../liferay-internals/fundamentals.md)
* [Architecture](../../liferay-internals/architecture/architecture.md)
