---
description: 02 - OSGi Basics
title: Components and Services
order: 4
---

## Components and Services

#### A Component

An OSGi component is any Java class inside a bundle that is declared to be a component, usually by annotating it with `@Component`. When a class is declared to be a component, it becomes managed by the OSGi container.

Like a bundle, a component has its own independent lifecycle, with two possible states: *activated* and *deactivated*. A component may contain corresponding lifecycle methods, which are identified by annotations like `@Activate` and `@Deactivate`. As with bundle activators, those methods can be used to initialize and clean up resources on component activation and deactivation.

Why declare a class to be a component? A component can __publish__ itself as a service and make itself available for the other components in the OSGi container. To __consume__ resources published by other components and provided by the OSGi container, a class has to declare itself as a component. If you want, for example, to get a reference to a service published in the OSGi container, your class has to be a *component*. A component may also have a __configuration__ managed by the [OSGi Configuration Admin](https://osgi.org/specification/osgi.cmpn/7.0.0/service.cm.html) subsystem. 

Below is an example of a component with an `@Activate` method:

```java
package com.liferay.training.component;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component
public class TrainingComponent {

	@Activate
	protected void activate(Map<String, Object> properties) {

		System.out.println("I'm active.")
	}
}
```

#### A Service

A service is an OSGi component registered to the OSGi container's __service registry__. Compared to a plain OSGi component, a service component has two additional characteristics:

* A `service` component property
* It implements an interface.

All OSGi services have to implement an interface, as they are always referenced by their interface, leveraging at the same time modularity and loose coupling. 

A service client doesn't need to know about the service implementation, as the OSGi framework provides facilities to replace the service reference with its implementation at runtime. Multiple implementations of a service may co-exist in the same container. Consuming clients may have policies and filters for binding specific implementations.

Below is an example of an OSGi service with an interface (API), a service component implementing the interface, and a client referencing the service by its interface.

```java
package com.liferay.training.service.api;

public interface EmployeeService {

	public String getName();
	public void setName(String name);
}
```

```java
package com.liferay.training.service.impl;

import com.liferay.training.example.api.EmployeeService;

import org.osgi.service.component.annotations.Component;

@Component(
	service = EmployeeService.class
)
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public String getEmployeeName() {
		
		return _name;
	}

	@Override
	public void setEmployeeName(String name) {
	
		_name = name;
	}

	private String _name;
}
```

```java
package com.liferay.training.client;

import com.liferay.training.example.api.EmployeeService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class EmployeeClient {
	
	public String getEmployeeName() {
		return employeeService.getEmployeeName();
	}
	
	@Reference
	EmployeeService employeeService;
}
```

#### The Service Registry

The OSGi *service registry* subsystem provides mechanisms to:

* Publish services to the OSGi container
* Look up and bind published services dynamically
* Hide the implementation details from the service clients
* Manage multiple service implementations in the same runtime

The diagram below shows the OSGi service registry pattern. Interface, implementation, and client are as shown in the diagram, usually in different bundles:

<img src="../images/osgi-service-registry-1.png" />

The __service API bundle__ (*Service API*) exports the package, which contains the interface.

The __service implementation bundle__ (*Service Impl*) imports the interface from the API bundle and provides an implementation for the interface. By declaring the implementing class to be a component and defining the interface class in the component `service` property, the service implementation bundle can register itself to the OSGi service registry. The registration is usually done with OSGi Declarative Services annotations. 

The __service client bundle__ (*Client*) contains a service-consuming component. This bundle imports the API bundle because a reference to an OSGi service is always done by its interface. 

The dependency injection, a reference to the service, is typically done with a `@Reference` annotation. In case there's no implementation registered, the component's configuration may or may not be satisfied. By default, a component won't start unless all of its references are satisfied, but specific reference policies might allow you to start the component even if no implementation for a referenced interface is available. 

The diagram below shows a scenario with multiple implementations of the same service registered in the container.

<img src="../images/osgi-service-registry-2.png" />

If the service client doesn't specify any policies or filters for the service implementation, the first available implementation will be bound to the client component. The client may also define policies that allow multiple implementations to be bound. This is especially useful when implementing factory patterns.

#### Declarative Services

[Declarative Services (DS)](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html) is an OSGi framework service handling dependency injection that allows you to publish, find, and bind services based on XML metadata and annotations.

Declarative Services is the OSGi dependency injection framework used by Liferay by default, but alternative implementations like [Apache Blueprint](http://aries.apache.org/modules/blueprint.html) can also be used. It is possible to use both even in the same bundle.

The standard annotations of Declarative Services are:

* __@Component:__	Identify the annotated class as a Service Component.
* __@Activate:__	Identify the annotated method as the _activate_ method of a Service Component.
* __@Deactivate:__	Identify the annotated method as the _deactivate_ method of a Service Component.
* __@Modified:__ 	Identify the annotated method as the _modified_ method of a Service Component.
* __@Reference:__ 	Inject a service implementation to the annotated variable (or by using a _setter_ method).

#### Declarative Services and Bndtools

By default, Declarative Services expects the service declarations to exist as XML files in the OSGi-INF folder of the bundle. These declaration files may be created and modified manually.

[Bndtools](https://bndtools.org) is a configuration tool that reads the annotations in the classes and allows the service declarations to be generated automatically at build time.

When bndtools is enabled in the project, it takes the responsibility of writing the manifest metadata. The instructions for the manifest file are written by default in the `bnd.bnd` file in the project's root directory. Combined with the information extracted from the annotations, bndtools builds the final `MANIFEST.MF`.  

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>An OSGi __________________ is any Java class inside a bundle that is declared to be a component.</li>
	<li>A ___________________ is an OSGi component registered to the OSGi container's <i>service registry</i>.</li>
	<li>Declarative Services (DS) is a service that handles OSGi ______________________ and allows it to <i>publish, find, and bind</i> services based on XML metadata and annotations.</li>
</ul>
</div>