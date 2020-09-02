# APIs as OSGi Services

After you've learned [what a module is](./module-projects.md) and how to deploy one, you can use modules to define and implement APIs. In Liferay, APIs are capabilities that are implemented as [OSGi services](https://enroute.osgi.org/). An OSGi service is a capability defined by a Java interface and implemented by a concrete Java class. The OSGi runtime framework registers service implementations dynamically for other classes to use.

Liferay uses a component-based framework called [OSGi Declarative Services](https://enroute.osgi.org/FAQ/300-declarative-services.html) (DS) to represent APIs and implementations as components. DS annotations, as you'll see in the code, are an easy way to specify components and their relationships.

Using separate modules for API and Implementation components is a practical way to separate concerns.

* **API** modules define capabilities using Java interfaces. The interfaces reside in packages that the module exports.
* **Implementation** modules provide capabilities using concrete Java classes.

Here you'll deploy an API module and Implementation module, confirm their runtime component relationships, and examine their code.

## Example: API Implementation

Here's what you'll do with the example:

1. [Launch the Example](#launch-the-example)
1. [Examine the API](#examine-the-api)
1. [Examine the Implementation](#examine-the-implementation)

### Launch the Example

Start up the example modules.

1. Start a [Liferay Docker container](../../installation-and-upgrades/installing-liferay/using-liferay-dxp-docker-images//dxp-docker-container-basics.md).

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.2-ga3
    ```

1. Download and unzip `liferay-p9g2.zip`.

    ```curl
    curl https://learn.liferay.com/dxp-7.x/liferay-internals/fundamentals/apis-as-osgi-services/liferay-p9g2.zip -O
    ```

    ```bash
    unzip liferay-p9g2.zip
    ```

    The following files are extracted:

    ```
    $(pwd)/
    ├── build.gradle
    ├── gradle/
    ├── gradlew
    ├── gradlew.bat
    ├── p9g2-api/
    ├── p9g2-impl/
    ├── settings.gradle
    └── source-formatter-suppressions.xml
    ```

1. Build the example modules.

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

1. Go to the [Gogo Shell](./using-the-gogo-shell/using-the-gogo-shell.md).

1. Get the module's bundle ID using the `lb` Gogo Shell command.

    ```bash
    g! lb | grep -i "P9G2"
    ```

    Output:

    ```
    1194|Active     |   15|Acme P9G2 Greeting API (1.0.0)|1.0.0
    1195|Active     |   15|Acme P9G2 Greeting Impl (1.0.0)|1.0.0
    ```

1. Inspect the Implementation module's service capabilities by executing the following command, replacing the number with the module's bundle ID.

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

The following sections show how the API and Implementation modules, together provide a `com.acme.p9g2.greeting.Greeting` OSGi service.

## Examine the API

An API specifies an API for greetings:

```java
package com.acme.p9g2.greeting;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Greeting {

    public void greet(String name);

}
```

The interface's [`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) annotation tells the Service Component Runtime that anything implementing the interface is a provider of the `com.acme.p9g2.greeting.Greeting` capability. This API's `greet` method takes a name `String` as input.

The `bnd.bnd` file describes the API module and exports the `com.acme.p9g2.greeting` package.

```properties
Bundle-Name: Acme P9G2 Greeting API
Bundle-SymbolicName: com.liferay.learn.p9g2.api
Bundle-Version: 1.0.0
Export-Package: com.acme.p9g2.greeting
```

The package export allows other modules to access the `Greeting` interface.

## Examine the Implementation

The Implementation module provides a concrete version of the API. The `P9G2Greeting` class below implements the `Greeting` interface:

```java
package com.acme.p9g2.internal.greeting;

import com.acme.p9g2.greeting.Greeting;

import org.osgi.service.component.annotations.Component;

@Component(
    service = Greeting.class
)
public class P9G2Greeting implements Greeting {

    @Override
    public void greet(String name) {

        System.out.println("Hello " + name + "!");
    }

}
```

The `@Component` annotation and its `service = Greeting.class` attribute make the `P9G2Greeting` class a `Greeting` service provider.

The module's `build.Gradle` file includes a compile-time dependency on the `p9g2-api` API module project.

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "release.portal.api"
	compileOnly project(":p9g2-api")
}
```

On building the Implementation module JAR, Bnd generates a `META-INF/MANIFEST.MF` file.

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

Here are some key service-related headers that Bnd generates in the manifest:

| Header | Description |
| :----- | :---------- |
| `Import-Package: com.acme.p9g2.greeting;version="[1.0,2)"` | Imports the API module's public package that contains the `Greeting` service definition. |
| `Provide-Capability: osgi.service;objectClass:List<String>="com.acme.j1h1.greeting.Greeting";uses:="com.acme.p9g2.greeting"` | Declares the implemented service. |
| `Service-Component: OSGI-INF/com.acme.p9g2.greeting.impl.P9G2Greeting.xml` | Lists the service component's configuration file. |

When the module is deployed, the Service Component Runtime registers the `P9G2Greeting` component as providing the `Greeting` service.

You have defined an API called `Greeting` and provided iin the runtime framework using a service component called `P9G2Greeting`.

## What's Next

The `Greeting` service is in place. How do clients access the service and use it? That's demonstrated next in [Using an OSGi Service](./using-an-osgi-service.md).

## Additional Information

* [Gogo Shell Commands](./using-the-gogo-shell/gogo-shell-commands.md)
* [Exporting Packages](./exporting-packages.md)
* [Importing Packages](./importing-packages.md)
* [Configuring Dependencies](./configuring-dependencies/configuring-dependencies.md)
* [Getting started with OSGi at OSGi EnRoute](https://enroute.osgi.org/)
* [Declarative Services](https://enroute.osgi.org/FAQ/300-declarative-services.html)
* [OSGi Alliance](https://www.osgi.org/)