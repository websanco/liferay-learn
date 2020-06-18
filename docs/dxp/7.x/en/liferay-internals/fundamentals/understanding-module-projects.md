# Understanding Module Projects

Liferay software is developed using [OSGi modules](https://www.osgi.org/developer/what-is-osgi/). OSGi modules is a way of developing cohesive software packages that define APIs, provide implementations, and that use concrete implementations to provide functionality to users and other consumers.

Module packages are private by default. The only packages that are published are ones specified via the module's meta data. This keeps implementation details hidden from other modules. Liferay leverages a component framework called [Declarative Services](https://enroute.osgi.org/FAQ/300-declarative-services.html) that facilitates defining, implementing, and using APis. You'll see module components in action later. First, look at the simplicity of the module project structure:

```
[module project]
├── bnd.bnd
├── build.gradle
└── src
    └── main
        ├── java
        │   └── [Java packages]
        └── resources
            └── [any image files, templates, descriptors, etc.]
```

The module project comprises three basic things:

* **Code:** Java classes that define services, provide services, and consume services. Any required resources, such as images, templates, and additional descriptors, are also part of the code. Classes that serve a common purpose are grouped in the same Java package. Packages are private by default. Packages for other modules to use (such as API packages) are [specified for export](./exporting-packages.md) in the `bnd.bnd` file.

* **A Build Script:** Specifies required artifacts. Liferay uses Gradle to build modules.

* **Meta Data:** Headers in the `bnd.bnd` [Bnd](https://bnd.bndtools.org/) file define the module artifact and specify the module's characteristics, including capabilities that the module defines, provides, and requires. On building the module JAR file, Bnd propagates meta data from `bnd.bnd` file to the JAR's `META-INF/MANIFEST.MF` file and Bnd adds capability and component meta data based on its inspection of the module code. The runtime framework uses the meta data to add the module's services to the registry and to inject components with the services they reference. Component services are used in the example modules, discussed next. 

```tip::
   You can examine module JAR ``META-INF/MANIFEST.MF`` files to see the propagated ``bnd.bnd`` meta data and the meta data that Bnd generates based on the module code inspection.
```

Module code and the meta data mentioned above, are more easily understood by way of example.

## Example Modules: Greeting Command

The following modules collectively provide an [Apache Felix Gogo](https://felix.apache.org/documentation/subprojects/apache-felix-gogo.html) shell command that takes a String and uses it in a greeting. Consider this example to be a "Hello World" for modules.

![The example modules implement a greeting command for Gogo Shell.](./understanding-module-projects/images/01.png)

The example demonstrates three common types of modules:

* **API** modules define capabilities using Java interfaces. The interfaces reside in packages that the module exports.
* **Provider** modules implement (provide) capabilities using concrete Java classes.
* **Consumer** modules use API-defined capabilities. The run time framework injects consumer objects with concrete API implementations.

Here's what you'll do with the example:

1. [Launch the Example](#test-the-example)
1. [Examine the API](#examine-the-api)
1. [Examine the Provider \(Service\)](#examine-the-provider-service)
1. [Examine the Consumer \(Client\)](examine-the-consumer)

### Launch the Example

Start using the example modules.

1. Start a [Liferay Docker container](../../installation-and-upgrades/installing-liferay/using-liferay-dxp-docker-images//dxp-docker-container-basics.md).

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.2-ga3
    ```

1. Download and unzip `liferay-j1h1.zip`.

    ```curl
    curl https://learn.liferay.com/dxp-7.x/liferay-internals/fundamentals/liferay-j1h1.zip -O
    ```

    ```bash
    unzip liferay-j1h1.zip
    ```

    The following files are extracted:

    ```
    $(pwd)/
    ├── build.gradle
    ├── gradle/
    ├── gradlew
    ├── gradlew.bat
    ├── j1h1-api/
    ├── j1h1-command/
    ├── j1h1-service/
    ├── settings.gradle
    └── source-formatter-suppressions.xml
    ```

1. Build the example modules.

    ```bash
    ./gradlew jar
    ```

    Each module JAR file is generated to that module's `build/libs` folder.

     ```
     j1h1-api/build/libs/com.liferay.docs.j1h1.greeting.api-1.0.0.jar
     j1h1-command/build/libs/com.liferay.docs.j1h1.greeting.command-1.0.0.jar
     j1h1-service/build/libs/com.liferay.docs.j1h1.greeting.service-1.0.0.jar
     ```

1. Deploy the example module JARs.

    API module:

    ```bash
    docker cp j1h1-api/build/libs/com.liferay.docs.j1h1.greeting.api-1.0.0.jar $(docker ps -lq):/opt/liferay/deploy
    ```

    Provider (service) module:

    ```bash
    docker cp j1h1-service/build/libs/com.liferay.docs.j1h1.greeting.service-1.0.0.jar $(docker ps -lq):/opt/liferay/deploy
    ```

    Consumer module:

    ```bash
    docker cp j1h1-command/build/libs/com.liferay.docs.j1h1.greeting.command-1.0.0.jar $(docker ps -lq):/opt/liferay/deploy
    ```

1. Confirm the deployments in the Docker container console.

    ```
    STARTED com.liferay.docs.j1h1.greeting.api_1.0.0
    STARTED com.liferay.docs.j1h1.greeting.service_1.0.0
    STARTED com.liferay.docs.j1h1.greeting.command_1.0.0
    ```

1. Access the Gogo Shell by clicking *Control Panel &rarr; Configuration &rarr; Gogo Shell*.

1. In the *Command* field, use the `greet:greet` command send a greeting.

    ```groovy
    greet:greet "Captain Kirk"
    ```

1. Confirm the output.

    ```
    Hello Captain Kirk!
    ```

The modules work in concert to provide the `greet:greet` Gogo Shell command to greet people. Let's examine each module, starting with the API.

### Examine the API

The API defines the contract that a provider implements and a consumer uses. Here is its structure:

```
j1h1-api
├── bnd.bnd
├── build.gradle
├── settings.gradle
└── src
    └── main
        └── java
            └── `com/liferay/docs/j1h1/greeting/api`
                └── `Greeting.java`
```

Very simple, right? Beyond the Java source file, there are only three other files: a Gradle build script, a Gradle settings file (for this multi-project build), and a configuration file called `bnd.bnd`. The `bnd.bnd` file describes and configures the module:

```properties
Bundle-Name: j1h1 Greeting API
Bundle-SymbolicName: com.liferay.docs.j1h1.greeting.api
Bundle-Version: 1.0.0
Export-Package: com.liferay.docs.j1h1.greeting.api
```

The module's name is *j1hs Greeting API*. Its symbolic name--a name that ensures uniqueness--is `com.liferay.docs.j1h1.greeting.api`. Its semantic version is declared next, and its package is *exported*, which means it's made available to other modules. This module's package is just an API other modules can implement.

Finally, there's the Java class, which in this case is an interface:

```java
package com.liferay.docs.j1h1.greeting.api;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Greeting {

        public void greet(String name);

}
```

The interface's [`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) annotation tells the service registry that anything implementing the interface is a provider. The interface's one method asks for a `String` and doesn't return anything.

That's it! As you can see, creating modules is not very different from creating other Java projects.

### Examine the Provider (Service)

An interface only defines an API; to do something, it must be implemented. This is what the provider module is for. Here's what a provider module for the Greeting API looks like:

```
j1h1-service
├── bnd.bnd
├── build.gradle
├── settings.gradle
└── src
    └── main
        └── java
            └── `com/liferay/docs/j1h1/greeting/impl`
                └── `GreetingImpl.java`
```

It has the same structure as the API module: a build script, a build settings file, a `bnd.bnd` configuration file, and an implementation class. The only differences are the file contents. The `bnd.bnd` file is a little different:

```properties
Bundle-Name: j1h1 Greeting Impl
Bundle-SymbolicName: com.liferay.docs.j1h1.greeting.impl
Bundle-Version: 1.0.0
```

The bundle name, symbolic name, and version are all set similarly to the API.

Finally, there's no `Export-Package` declaration. A client (which is the third module you'll create) just wants to use the API: it doesn't care how its implementation works as long as the API returns what it's supposed to return. The client, then, only needs to declare a dependency on the API; the service registry injects the appropriate implementation at runtime.

All that's left, then, is the class that provides the implementation:

```java
package com.liferay.docs.j1h1.greeting.impl;

import com.liferay.docs.j1h1.greeting.api.Greeting;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
    },
    service = Greeting.class
)
public class GreetingImpl implements Greeting {

    @Override
    public void greet(String name) {
        System.out.println("Hello " + name + "!");
    }

}
```

The implementation is simple. It uses the `String` as a name and prints a hello message. A better implementation might be to use Liferay's API to collect all the names of all the users in the system and send each user a greeting notification, but the point here is to keep things simple.

This [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) annotation defines three options: `immediate = true`, an empty property list, and the service class that it implements. The `immediate = true` setting means that this module should not be lazy-loaded; the service registry loads it as soon as it's deployed, instead of when it's first used. Using the `@Component` annotation declares the class as a Declarative Services component, which is the most straightforward way to create components for OSGi modules. A component is a POJO that the runtime creates automatically when the module starts.

To compile this module, the API it's implementing must be on the classpath. You'd add the `j1h1-api` project to your `build.gradle` file's `dependencies { ... }` block, like this:

```groovy
compileOnly project(":j1h1-api")
```

That's all there is to a provider module.

### Examine the Consumer (Client)

The client or consumer uses the API that the API module defines and the provider module implements. DXP has many different kinds of consumer modules. Portlets are the most common consumer module type, but since they are a topic all by themselves, this example stays simple by creating an command for the Apache Felix Gogo shell. Note that consumers can, of course, consume many different APIs to provide functionality.

A consumer module has the same structure as the other module types:

```
j1h1-command
├── bnd.bnd
├── build.gradle
├── settings.gradle
└── src
    └── main
        └── java
            └── `com/liferay/docs/j1h1/greeting/command`
                └── `GreetingCommand.java`
```

Again, you have a build script, a build settings file, a `bnd.bnd` file, and a Java class. This module's `bnd.bnd` file is almost the same as the provider's:

```properties
Bundle-Name: j1h1 Greeting Command
Bundle-SymbolicName: com.liferay.docs.j1h1.greeting.command
Bundle-Version: 1.0.0
```

There's nothing new here: you declare the same things you declared for the provider.

Your Java class has a little bit more going on:

```java
package com.liferay.docs.j1h1.greeting.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.j1h1.greeting.api.Greeting;

@Component(
    immediate = true,
    property = {
        "osgi.command.scope=greet",
        "osgi.command.function=greet"
    },
    service = Object.class
)
public class GreetingCommand {

    public void greet(String name) {
        Greeting greeting = _greeting;

        greeting.greet(name);
    }

    @Reference
    private Greeting _greeting;

}
```

The `@Component` annotation declares the same attributes, but specifies some properties and a different service. As in Java, where every class is a subclass of `java.lang.Object` (even though you don't need to specify it by default), in Declarative Services, the runtime needs to know the type of class to register. Because you're not implementing any particular type, your parent class is `java.lang.Object`, so you must specify that class as the service. While Java doesn't require you to specify `Object` as the parent when you're creating a class that doesn't inherit anything, Declarative Services does.

The two properties define a Gogo shell command scope and a command function. All commands have a scope to define their context, as it's common for multiple APIs to have similar functions, such as `copy` or `delete`. These properties specify you're creating a command called `greet` in a scope called `greet`. While you get no points for imagination, this sufficiently defines the command.

Since you specified `osgi.command.function=greet` in the `@Component` annotation, your class must have a method named `greet`, and you do. But how does this `greet` method work? It obtains an instance of the `Greeting` OSGi service and invokes its `greet` method, passing in the `name` parameter. How is an instance of the `Greeting` OSGi service obtained? The `GreetingCommand` class declares a private service bean, `_greeting` of type `Greeting`. This is the OSGi service type that the provider module registers. The [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) annotation tells the OSGi runtime to instantiate the service bean with a service from the service registry. The runtime binds the `Greeting` object of type `GreetingImpl` to the private field `_greeting`. The `greet` method uses the `_greeting` field value.

Just like the provider, the consumer needs to have the API on its classpath in order to compile, but at runtime, since you've declared all the dependencies appropriately, the container knows about these dependencies, and provides them automatically.

This most basic of examples should make it clear that module-based development is easy and straightforward. The API-Provider-Consumer contract fosters loose coupling, making your software easy to manage, enhance, and support.

## What's Next

Now that you're familiar with module projects, explore how to [configure dependencies](./configuring-dependencies/configuring-dependencies.md).

## Additional Information

* [Importing Packages](./importing-packages.md)
* [Exporting Packages](./exporting-packages.md)
* [Semantic Versionings](./semantic-versioning.md)
