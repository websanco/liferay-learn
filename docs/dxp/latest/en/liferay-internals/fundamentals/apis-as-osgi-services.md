# APIs as OSGi Services

After you've learned what a [module](./module-projects.md) is and how to deploy one, you can use modules to define APIs and implement them. Liferay APIs are [OSGi services](https://enroute.osgi.org/), defined by Java interfaces and implemented by concrete Java classes.

Liferay exposes APIs, implementations, and clients as components. [OSGi Declarative Services](https://enroute.osgi.org/FAQ/300-declarative-services.html) (DS) annotations define components and their relationships.

* [`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) defines an interface that components can provide (implement) or consume.
* [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) declares the class a component, providing a particular capability.
* [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) wires another component to a class member (typically a field).

You can separate API and implementation concerns into different modules.

* **API** modules *define* capabilities using Java interfaces. The modules export the interface packages.
* **Implementation** modules *provide* capabilities using concrete Java classes.

Here you'll deploy API and implementation modules that create a simple greeter OSGi service. You'll also examine the implementation module and its JAR to learn how the implementation provides the greeter service capability. In the next tutorial, you'll create the client---the part that you can invoke in the UI.

## Deploy a Simple API and Implementation

Start the example modules.

1. Start a [Liferay Docker container](../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. Download and unzip `liferay-p9g2.zip`.

    ```curl
    curl https://learn.liferay.com/dxp/latest/en/liferay-internals/fundamentals/liferay-p9g2.zip -O
    ```

    ```bash
    unzip liferay-p9g2.zip
    ```

1. From the project root folder, deploy the modules.

    ```bash
    cd liferay-p9g2
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Confirm module startup in the Docker container console.

    ```
    STARTED com.acme.p9g2.api_1.0.0
    STARTED com.acme.p9g2.impl_1.0.0
    ```

1. Go to `http://localhost:8080` and sign in.

1. Go to the [Gogo Shell](./using-the-gogo-shell.md).

1. Get the module IDs using the `lb` Gogo Shell command.

    ```bash
    g! lb | grep -i "Acme P9G2"
    ```

    Output:

    ```
    1150|Active     |   15|Acme P9G2 API (1.0.0)|1.0.0
    1151|Active     |   15|Acme P9G2 Implementation (1.0.0)|1.0.0
    ```

1. List the implementation module service capabilities by executing the following command, replacing the number with your module's ID.

    ```bash
    g! inspect capability service 1195
    ```

    Output:

    ```
    com.acme.p9g2.impl_1.0.0 [1151] provides:
    -----------------------------------------
    service; com.acme.p9g2.Greeter with properties:
       service.id = 22933
       service.bundleid = 1151
       service.scope = bundle
       component.name = com.acme.p9g2.internal.P9G2Greeter
       component.id = 8462
    ```

The Acme P9G2 Implementation module provides one service: `com.acme.p9g2.Greeter`. The `component.name` property indicates that the module's `com.acme.p9g2.internal.P9G2Greeter` component implements the service.

You verified that the `P9G2Greeter` component provides the `Greeter` service.

Next you'll learn how the API module defines the greeter capability and the implementation module provides the greeter capability as an OSGi service. Start with creating the API.

## Create the API

An API is created in just two steps:

* [Define the Capability](#define-the-capability)
* [Export the Interface Package](#export-the-interface-package)

### Define the Capability

The example API module `Greeter` class is a Java interface.

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-api/src/main/java/com/acme/p9g2/Greeter.java
:language: java
:lines: 5-6
```

The [`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) annotation registers `Greeter` as a type that a component can implement or consume.

The `greet` method takes a name `String` as input.

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-api/src/main/java/com/acme/p9g2/Greeter.java
:dedent: 1
:language: java
:lines: 8
```

The `Greeter` capability is defined.

### Export the Interface Package

The API module `bnd.bnd` file describes the module and exports the `com.acme.p9g2` interface package.

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-api/bnd.bnd
```

The [package export](./exporting-packages.md) shares the `Greeter` interface with other modules.

The `Greeter` service type is available to implement and use.

## Create the Implementation

The example implementation module contains a concrete Java class that provides the `Greeter` capability. Here are the implementation steps.

* [Add the Component Annotion Class](#add-the-component-annotation)
* [Implement the Interface](#implement-the-interface)
* [Add a Dependency on the API](#add-a-dependency-on-the-api)
* [Examine the Module JAR](#examine-the-module-jar)

### Add the Component Annotation

The `P9G2Greeter` class implements the `Greeter` interface:

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-impl/src/main/java/com/acme/p9g2/internal/P9G2Greeter.java
:language: java
:lines: 7-8
```

The [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) annotation and its `service = Greeter.class` attribute make the `P9G2Greeter` class a `Greeter` service provider.

### Implement the Interface

The `Greeter` interface defines a method `greet(String)` with a `void` return value.

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-impl/src/main/java/com/acme/p9g2/internal/P9G2Greeter.java
:dedent: 1
:language: java
:lines: 10-13
```

The example `greet` method prints an enthusiastic greeting using the given name.

### Add a Dependency on the API

Here is the implementation module `build.gradle` file.

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-impl/build.gradle
:language: groovy
```

It includes a compile-time dependency on the `p9g2-api` module project because it requires the module's `Greeter` class.

### Examine the Module JAR

When you built the `p9g2-impl/build/libs/com.acme.p9g2.impl-1.0.0.jar` implementation module JAR, [Bnd](http://bnd.bndtools.org/) generated the JAR's `/META-INF/MANIFEST.MF` file.

Here are key service-related headers that Bnd generates in the manifest:

```properties
Import-Package: com.acme.p9g2;version="[1.0,2)"
```

The [`Import-Package`](./importing-packages.md) header imports the API module's public package that contains the `Greeter` service definition.

```properties
Provide-Capability: osgi.service;objectClass:List<String>="com.acme.p9
 g2.Greeter";uses:="com.acme.p9g2"
```

The `Provide-Capability` header configures the `P9G2Greeter` component service.

```properties
Service-Component: OSGI-INF/com.acme.p9g2.internal.P9G2Greeter.xml
```

The `Service-Component` header lists a configuration file (`.xml`) for each of the module's service components.

When you deployed the module, the Service Component Runtime registered the `P9G2Greeter` service component as providing the `Greeter` service.

## Conclusion

You have *defined* a service capability called `Greeter` and *provided* it in a service component called `P9G2Greeter`. The `Greeter` service is in place. How do clients access the service and use it? That's demonstrated in [Using an OSGi Service](./using-an-osgi-service.md).

## Additional Information

* [Gogo Shell Commands](./using-the-gogo-shell/gogo-shell-commands.md)
* [Exporting Packages](./exporting-packages.md)
* [Importing Packages](./importing-packages.md)
* [Configuring Dependencies](./configuring-dependencies.md)
* [Getting started with OSGi at OSGi EnRoute](https://enroute.osgi.org/)
* [Declarative Services](https://enroute.osgi.org/FAQ/300-declarative-services.html)
* [OSGi Alliance](https://www.osgi.org/)