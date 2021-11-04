# Liferay Startup Phases

Knowing Liferay's startup phases helps you troubleshoot startup failures. By learning the phase triggered events, you can listen for phases and act on them. This article describes the startup phases and identifies how to [implement actions for phase events](#acting-on-events).

Startup consists of these main phases:

1. **Portal Context Initialization Phase:** focuses on low level tasks without a web context.

1. **Main Servlet Initialization Phase:** focuses on the portlet container and the Liferay DXP web application's UI features such as Struts, Themes, and more.

The Portal Context Initialization Phase sets the stage for the Main Servlet Initialization Phase.

### Portal Context Initialization Phase

The Portal Context Initialization phase runs first with these tasks:

1. Set up low level utilities such as logging and those in [`PortalUtil`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-impl/com/liferay/portal/util/InitUtil.html) and [`InitUtil`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-impl/com/liferay/portal/util/InitUtil.html).

1. OSGi framework is initialized.

1. Spring Phase 1: INFRASTRUCTURE beans specified by the Spring context files listed in the [`spring.infrastructure.configs`](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html#Spring) Portal Property are loaded. 


1. INFRASTRUCTURE beans are published as [OSGi services](./osgi-and-modularity.md).

1. OSGi framework starts.

    1. Static bundles are installed and started.
    1. Dynamic bundles are started.

1. OSGi framework starts the runtime.

1. Spring Phase 2: MAIN

    1. Load Spring beans specified by the Spring context files listed in the [`spring.configs`](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html#Spring) Portal Property.
    1. A [`ModuleServiceLifecycle` event service](#moduleservicelifecycle-events) with a service property `module.service.lifecycle` value `spring.initialized` (i.e., [`SPRING_INITIALIZED`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.SPRING_INITIALIZED)) registers.

1. MAIN Spring beans are published as OSGi services.

### Main Servlet Initialization Phase

Here's the phase's activity sequence:

1. The [`ModuleServiceLifecycle` event service](#moduleservicelifecycle-events) is updated with the service property `module.service.lifecycle` value `database.initialized` (i.e., [`DATABASE_INITIALIZED`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.DATABASE_INITIALIZED)).

1. The [Global Startup event](#portal-startup-events) fires.

1. For each portal instance, the [Application Startup events](#portal-startup-events) fire.

1. The [`ModuleServiceLifecycle` event service](#moduleservicelifecycle-events) is updated with the service property `module.service.lifecycle` value `portal.initialized` (i.e., [`PORTAL_INITIALIZED`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/constant-values.html#com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle.PORTAL_INITIALIZED)).

Now that you're acquainted with the startup phases, you can concentrate on the events they fire.

## Acting on Events

The ways to act on events depends on the event type. These subsections describe the event types.

### ModuleServiceLifecycle Events

You can wait for and act on `ModuleServiceLifecycle` event services. See Listening on Liferay Lifecycle Events (Coming soon).

### Liferay Startup Phases

In a [Portal Properties](../../installation-and-upgrades/reference/portal-properties.md) file (e.g., `portal-ext.properties`), you can override the following properties and add your own [`LifecycleAction`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/events/LifecycleEvent.html) classes to the list of action classes to invoke on events.

**Global Startup Event** runs once when Liferay DXP/Portal initializes. The [`global.startup.events` property](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html#Startup%20Events) defines the event's default actions.

**Application Startup Events** runs once for each Site instance DXP initializes. The [`application.startup.events` property](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html#Startup%20Events) defines the event's default actions.

## Additional Information

* Listening on Liferay Lifecycle Events (Coming soon)
* [Module Lifecycle](./module-lifecycle.md)
* [OSGi and Modularity](./osgi-and-modularity.md)