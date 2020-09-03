# APIs as OSGi Services

After you've learned what a [module](./module-projects.md) is and how to deploy one, you can use modules to define and implement APIs. In Liferay, APIs are capabilities that are implemented as [OSGi services](https://enroute.osgi.org/). An OSGi service is a capability defined by a Java interface and implemented by a concrete Java class. The OSGi runtime framework registers service implementations dynamically for other classes to use.

Liferay uses a component-based framework called [OSGi Declarative Services](https://enroute.osgi.org/FAQ/300-declarative-services.html) (DS) to represent APIs and implementations as components. DS annotations simplify defining components and their relationships.

Using separate modules for API and Implementation components helps to separate concerns.

* **API** modules define capabilities using Java interfaces. The interfaces reside in packages that the module exports.
* **Implementation** modules provide capabilities using concrete Java classes.

Here you'll deploy an API module and Implementation module, confirm their runtime component relationships, and examine their code.

## Overview

1. [Deploy an Example](#deploy-an-example)
1. [Walk Through the Example](#walk-through-the-example)
1. [Conclusion](#conclusion)
1. [Additional Information](#additional-information)

## Deploy an Example

Start up the example modules.

1. Start a [Liferay Docker container](../../installation-and-upgrades/installing-liferay/using-liferay-dxp-docker-images//dxp-docker-container-basics.md).

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.2-ga3
    ```

1. Download and unzip `liferay-p9g2.zip`.

    ```curl
    curl https://learn.liferay.com/dxp-7.x/liferay-internals/fundamentals/liferay-p9g2.zip -O
    ```

    ```bash
    unzip liferay-p9g2.zip
    ```

1. From the module root, build the modules.

    ```bash
    ./gradlew jar
    ```

    Each module JAR file is generated to that module's `build/libs` folder.

     ```
     p9g2-api/build/libs/com.liferay.learn.p9g2.api-1.0.0.jar
     p9g2-impl/build/libs/com.liferay.learn.p9g2.impl-1.0.0.jar
     ```

1. Deploy the example module JARs.

    API module:

    ```bash
    docker cp p9g2-api/build/libs/com.liferay.learn.p9g2.api-1.0.0.jar $(docker ps -lq):/opt/liferay/deploy
    ```

    Implementation module:

    ```bash
    docker cp p9g2-impl/build/libs/com.liferay.learn.p9g2.impl-1.0.0.jar $(docker ps -lq):/opt/liferay/deploy
    ```

1. Confirm the deployments in the Docker container console.

    ```
    STARTED com.liferay.learn.p9g2.api_1.0.0
    STARTED com.liferay.learn.p9g2.impl_1.0.0
    ```

1. Go to `http://localhost:8080` and sign in using the default credentials:

   **User Name:** `test@liferay.com`

   **Password:** `test`

1. Go to the [Gogo Shell](./using-the-gogo-shell/using-the-gogo-shell.md).

1. Get the module bundle IDs using the `lb` Gogo Shell command.

    ```bash
    g! lb | grep -i "P9G2 Greeting"
    ```

    Output:

    ```
    1194|Active     |   15|Acme P9G2 Greeting API (1.0.0)|1.0.0
    1195|Active     |   15|Acme P9G2 Greeting Impl (1.0.0)|1.0.0
    ```

1. List the Implementation module's service capabilities by executing the following command, replacing the number with the module's bundle ID.

    ```bash
    g! inspect capability service  1195
    ```

    Output:

    ```
    com.liferay.learn.p9g2.impl_1.0.0 [1195] provides:
    --------------------------------------------------
    service; com.acme.p9g2.greeting.Greeting with properties:
       service.id = 12779
       service.bundleid = 1195
       service.scope = bundle
       component.name = com.acme.p9g2.greeting.impl.P9G2Greeting
       component.id = 6022
      ```

The module provides one service: `com.acme.p9g2.greeting.Greeting`. The `component.name` property indicates that the module's `com.acme.p9g2.internal.greeting.P9G2Greeting` component implements the service.

Now that you verified that the `P9G2Greeting` component provides the `Greeting` service, learn how the modules accomplish this.

## Walk Through the Example

Review the example. The API module's Java interface defines a greeting capability. The Implementation module's one class provides the greeting capability as an OSGi service.

* [Annotate the Java Interface](#annotate-the-java-interface)
* [Export the Interface Package](#export-the-interface-package)
* [Annotate the Implementation Class](#annotate-the-implementation-class)
* [Implement the `greet` Method](#implement-the-greet-method)
* [Add a Dependency on the API](#add-a-dependency-on-the-api)
* [Examine the Implementation Module JAR](#examine-the-implementation-module-jar)

### Annotate the Java interface

`Greeting` is a Java interface.

```java
@ProviderType
public interface Greeting {
```

The [`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) annotation registers `Greeting` as a type that modules can provide by implementing the interface.

The `greet` method takes a name `String` as input.

```java
public void greet(String name);
```

### Export the Interface Package

The `bnd.bnd` file describes the API module and exports the `com.acme.p9g2.greeting` package.

```properties
Bundle-Name: Acme P9G2 Greeting API
Bundle-SymbolicName: com.liferay.learn.p9g2.api
Bundle-Version: 1.0.0
Export-Package: com.acme.p9g2.greeting
```

The [package export](./exporting-packages.md) allows other modules to access the `Greeting` interface.

### Annotate the Implementation Class

The Implementation module's `P9G2Greeting` class implements the `Greeting` interface:

```java
package com.acme.p9g2.internal.greeting;

import com.acme.p9g2.greeting.Greeting;

import org.osgi.service.component.annotations.Component;

@Component(service = Greeting.class)
public class P9G2Greeting implements Greeting {
```

The `@Component` annotation and its `service = Greeting.class` attribute make the `P9G2Greeting` class a `Greeting` service provider.

### Implement the `greet` Method

```java
@Override
public void greet(String name) {
    System.out.println("Hello " + name + "!");
}
```

### Add a Dependency on the API

Here is the Implementation module's `build.Gradle` file.

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "release.portal.api"
	compileOnly project(":p9g2-api")
}
```

It includes a compile-time dependency on the `p9g2-api` API module project.

### Examine the Implementation module JAR

On building the Implementation module JAR, Bnd generated the `[JAR]/META-INF/MANIFEST.MF` file.

```properties
Manifest-Version: 1.0
Bnd-LastModified: 1599056546410
Bundle-ManifestVersion: 2
Bundle-Name: Acme P9G2 Greeting Impl
Bundle-SymbolicName: com.liferay.learn.p9g2.impl
Bundle-Version: 1.0.0
Created-By: 1.8.0_252 (Oracle Corporation)
Import-Package: com.acme.p9g2.greeting;version="[1.0,2)"
Javac-Debug: on
Javac-Deprecation: off
Javac-Encoding: UTF-8
Private-Package: com.acme.p9g2.greeting.impl
Provide-Capability: osgi.service;objectClass:List<String>="com.acme.j1
 h1.greeting.Greeting";uses:="com.acme.p9g2.greeting"
Require-Capability: osgi.extender;filter:="(&(osgi.extender=osgi.compo
 nent)(version>=1.3.0)(!(version>=2.0.0)))",osgi.ee;filter:="(&(osgi.e
 e=JavaSE)(version=1.8))"
Service-Component: OSGI-INF/com.acme.p9g2.greeting.impl.P9G2Greeting.x
 ml
Tool: Bnd-4.3.0.201909301554
```

Here are some key service-related headers that Bnd generated in the manifest:

```properties
Import-Package: com.acme.p9g2.greeting;version="[1.0,2)"
```

The [`Import-Package`](./importing-packages.md) header imports the API module's public package that contains the `Greeting` service definition.

```properties
Provide-Capability: osgi.service;objectClass:List<String>="com.acme.j1h1.greeting.Greeting";uses:="com.acme.p9g2.greeting"
```

The `Provide-Capability` header declares the services that the module's components implement.

```properties
Service-Component: OSGI-INF/com.acme.p9g2.greeting.impl.P9G2Greeting.xml
```

The `Service-Component` header lists a configuration file for each service-providing component in the module.

When the module deployed, the Service Component Runtime registered the `P9G2Greeting` service component as providing the `Greeting` service.

## Conclusion

You have defined an API called `Greeting` and provided it to the runtime framework in another module's service component called `P9G2Greeting`. The `Greeting` service is in place. How do clients access the service and use it? That's demonstrated in [Using an OSGi Service](./using-an-osgi-service.md).

## Additional Information

* [Gogo Shell Commands](./using-the-gogo-shell/gogo-shell-commands.md)
* [Exporting Packages](./exporting-packages.md)
* [Importing Packages](./importing-packages.md)
* [Configuring Dependencies](./configuring-dependencies/configuring-dependencies.md)
* [Getting started with OSGi at OSGi EnRoute](https://enroute.osgi.org/)
* [Declarative Services](https://enroute.osgi.org/FAQ/300-declarative-services.html)
* [OSGi Alliance](https://www.osgi.org/)