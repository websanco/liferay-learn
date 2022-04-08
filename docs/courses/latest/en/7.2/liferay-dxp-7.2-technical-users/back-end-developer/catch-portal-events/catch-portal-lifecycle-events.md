---
description: 12 - Catch Portal Events
title: Catch Portal Lifecycle Events
order: 1
---

## Catch Portal Lifecycle Events

Portal lifecycle events allow you to execute actions on:

* Portal startup and shutdown
* Servlet service call 
* Login and logout 
* Layout (page) update

Lifecycle event listeners are OSGi components that implement the [LifecycleAction](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/events/LifecycleAction.java) interface. The event to catch is defined with the component's `key` property and the execution order with the `service.ranking` property. The higher the number, the higher the priority in the execution order (defaults to 0). In the example global startup listener below, the `processLifecycleEvent()` is executed once on portal startup:

```java
package com.liferay.training.login.events.post;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserService;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"key=global.startup.events", 
		"service.ranking:Integer=100"
	},
	service = LifecycleAction.class
)
public class PortalStartupListener implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) 
			throws ActionException {
		try {

			MailMessage message = new MailMessage();

			message.setSubject("Lifecycle Event Notification");
			message.setBody("The portal was rebooted.");

			InternetAddress toAddress = new InternetAddress("servicedesk@example.com");
			InternetAddress fromAddress = new InternetAddress("do-not-reply@liferay.com");

			message.setTo(toAddress);
			message.setFrom(fromAddress);

			_mailService.sendEmail(message);

		} catch (PortalException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		}

	}

	@Reference
	protected MailService _mailService;
}
```

<br />

#### Startup / Shutdown Events

Startup events are called once when either the portal (global prefix) or a portal instance (application prefix) starts up or shuts down:

|	 Key property        | Description 	|
| ------------- |---------------------|
|	 global.startup.events 	| Run once when the portal initializes	|
|	 global.shutdown.events	| Run once when the portal shuts down |
| application.startup.events | Run once for every portal instance that is initialized |
| application.shutdown.events | Run once for every instance that is shut down |

#### Service Action Events

Service action events are processed before or after a request is processed. Note that service action events are executed on every request, including those for the static resources:

|		 Key property        |  Description 	|
| ------------- |---------------------|
| servlet.service.events.pre | Occurs before a service action |
| servlet.session.create.events | Occurs on session create |
| servlet.service.events.post | Occurs after a service action |
| servlet.service.destroy.events | Occurs on session destroy |

#### Login Events

Login events allow you to catch an event pre and post login and logout:

| Key property        | Description 	|
| ------------- |---------------------|
| login.events.pre | Occurs pre-login |
| login.events.post | Occurs post-login |
| logout.events.pre | Occurs pre-logout |
| logout.events.post | Occurs post-logout |

<br />

#### Layout Events

Layout events are triggered when a layout (page) is updated or deleted:

| Key property        | Description 	|
| ------------- |---------------------|
| layout.configuration.action.update | Occurs on layout update |
| layout.configuration.action.delete | Occurs on layout delete |


#### Lifecycle Event Sequence

Below is an example of the sequence in which the lifecycle event listeners are being executed on login.
</div>

<img src="../images/lifecycle-event-flow.png" style="max-height:35%;" />

For the list of default lifecycle listeners, see the portal.properties (https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/portal.properties) source.

#### Steps for Creating a Portal Lifecycle Event Action

1. Create a LifecycleAction service component implementing the [LifecycleAction](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/events/LifecycleAction.java) interface
1. Define the lifecycle event with the `key` component property
1. If there are multiple listeners, define the execution order with the `service.ranking` property
1. Implement the `processLifecycleEvent()` method

#### Module Lifecycle Events

By default, OSGi components are activated when all their references have been satisfied. Using a reference for the [ModuleServiceLifecycle](https://docs.liferay.com/ce/portal/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/module/framework/ModuleServiceLifecycle.html) interface in a component allows you to define a lifecycle event, when a component should be activated. This pattern is useful, for example, if your component's activation method should not be run before the portal database is initialized.

The following events are supported:

* DATABASE_INITIALIZED
* PORTAL_INITIALIZED
* SPRING_INITIALIZED 

Below is an example of a component with a `ModuleServiceLifecycle` reference, which gets injected on PORTAL_INITIALIZED, triggering the component activation:

```java
package com.liferay.training.events.lifecycle.module;

import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class TrainingImpl implements TrainingApi {

	@Activate
	protected void activate(Map<Object, Object> properties) {

		// Components activation method will be run
		// when all the referenced services are initialized
		// Thus, this code block gets executed after PORTAL_INITIALIZED

	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _portalInitialized;
}
```

> See more information on this topic in this Developer Network article: https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-2/waiting-on-lifecycle-events.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>_________________________ are called once when either the portal (global prefix) or a portal instance (application prefix) starts up or shuts down.</li>
	<li>Service action events are processed before or after a _________________________ is processed.</li>
	<li>_________________________ events allow you to catch an event pre and post login and logout.</li>
	<li>Layout events are triggered when a layout (page) is __________________ or deleted.</li>
	<li>Using a reference for the _______________________ interface in a component allows you to define an event when a component should be activated and execute actions on the activation.</li>
</ul>
</div>