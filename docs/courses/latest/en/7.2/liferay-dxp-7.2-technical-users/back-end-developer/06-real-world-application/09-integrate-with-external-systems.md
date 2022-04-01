---
description: 06 - Real World Application
title: Integrate with External Systems
order: 9
---

## Integrate with External Systems

Liferay Service Builder can automatically generate JSON and SOAP web services APIs for your service. As Liferay platform core services are created using the Service Builder pattern, they all have web service APIs available.

In addition to Service Builder generated web services, it is possible to publish JAX-RS REST and JAX-WS endpoints for any ad-hoc service.

#### Enabling Remote Services

Setting the `remote-service` attribute to true in the Service Builder entity definition creates the remote service variant for the entity and adds the `@JSONWebService` annotation to the service interface, making all the public methods of that interface available as JSON web services:

**service.xml**
```xml
<entity name="Assignment" uuid="true" local-service="true" remote-service="true">
```

**AssignmentService.java**
```java
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=gradebook",
		"json.web.service.context.path=Assignment"
	}, 
	service = AssignmentService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL, 
	rollbackFor =  {
		PortalException.class, SystemException.class
	}
)
public interface AssignmentService extends BaseService {
	...
}	
```

#### Ignoring a Method

A method can be prevented from being exposed as a web service by setting the mode attribute to `JSONWebServiceMode.IGNORE` in the remote service implementation class:

```java
@JSONWebService(
	mode = JSONWebServiceMode.IGNORE
)
public String myIgnoredMethod() {
```

#### Defining HTTP Methods

JSON enabled services are mapped to GET or POST HTTP methods with the following logic:

* __GET:__ if  method name starts with "get", "is", or "has"
* __POST:__ all other method prefixes

HTTP methods can, however, be explicitly defined on a method level by setting the method attribute: 

<br />

```java
@JSONWebService(
	value = "do-some-thing", 
	method = "PUT"
)
public void doSomething() {
```

<br /><br />

#### Explicit Method Registration

By setting the JSONWebService mode to `MANUAL`, the methods to be exposed can be declared manually. In the example below, only the getAssignment() method is exposed to the web service API.

```java
@JSONWebService(
	mode = JSONWebServiceMode.MANUAL
)
public class AssignmentServiceImpl extends AssignmentServiceBaseImpl{

	@JSONWebService
	public Assignment getAssignment(long assignmentId) {
		...
	}

	public void addAssignment(Assignment assignment) {
		...
	}    
```

#### Configuring the JSON Web Service API Properties

Global JSON Web Service configuration is done in the [portal properties](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/portal.properties). For example, the following settings are available:

```properties
# Enable / disable JSON web service API
json.web.service.enabled=false

# Discoverability through the test page http://[address]:[port]/api/jsonws
jsonws.web.service.api.discoverable=false

# Restricted HTTP methods
jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

# By default, the HTTP method is not checked when invoking a service call. This setting enables the strict mode.
jsonws.web.service.strict.http.method=true

# Web service paths that are accessible
jsonws.web.service.paths.includes=get*,has*,is*,

# Web service paths that aren't allowed. This setting takes precedence over the jsonws.web.service.paths.includes
jsonws.web.service.paths.excludes=set*, add*
```

#### Testing the JSON Web Service API

A JSON web service test page can be accessed at http://localhost:8080/api/jsonsws. URL, cURL, and JavaScript examples are provided:

<img src="../images/jsonws-test.png" style="max-height:65%"/>

#### Service Builder and SOAP API

Liferay uses [Apache Axis](https://axis.apache.org/axis/) for the SOAP services. To generate the SOAP API for your custom application, the WSDD builder task has to be added to the project to create the web service definition. The WSDD creation process is described in detail in this [Liferay Developer Network article](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-2/creating-remote-services).

#### Configuring the SOAP API Properties

The hosts allowed to access the SOAP API can be defined explicitly in the portal-ext.properties:

```properties
axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, [SERVER_IP]
```

#### Testing the SOAP API

A test page is available at http://localhost:8080/api/axis

<img src="../images/axis-test-page.png" style="max-height:100%"/>

#### Publishing JAX-RS and JAX-WS Services

Liferay supports publishing JAX-WS and JAX-RS services via the Apache CXF implementation. Publishing JAX-WS and JAX-RS services requires defining an endpoint and an extender. CXF endpoints are context paths the JAX web services are deployed to and accessible from. Extenders specify where the services are deployed:

* __SOAP Extenders:__ for publishing JAX-WS web services. Each SOAP extender can deploy the services to one or more CXF endpoints.
* __REST Extenders:__ for publishing JAX-RS web services. REST extenders for JAX-RS services are analogous to SOAP extenders for JAX-WS services. 

Steps for Publishing a JAX Web Service:

1. Create an OSGi service component for the web service.
1. Configure a Liferay endpoint to access the REST service.
1. Map the endpoint to your REST service using the REST or SOAP extender.

> See [Developer Network](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs) for more information.

#### REST Builder

Liferay 7.2 provides a new API generator tool which consumes OpenAPI profiles and generates the API scaffolding: JAX-RS endpoints, parsing, XML generation, and advanced features like filtering or multipart (binary files) support. The developer only has to fill the resource implementations, calling liferay remote services. 

> The [Rest Builder](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-rest-builder) source code.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>In addition to Service Builder-generated web services, it is possible to publish _____________________________________________________________________________________________________________________ for any _____________________________________________________.</li>
</ul>
</div>