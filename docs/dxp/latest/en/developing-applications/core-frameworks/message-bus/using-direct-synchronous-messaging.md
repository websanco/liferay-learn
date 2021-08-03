# Using Direct Synchronous Messaging

Direct synchronous messaging is the easiest way to block processing until all listeners receive a message. You call the `SynchronousMessageSender`'s `send(String, Message)` method and pass in a destination name and message instance. The `SynchronousMessageSender` uses the current thread to process message reception directly in each of the destination's registered message listeners. When listener processing completes, execution continues in the class that called the `send(String, Message)` method. This example demonstrates using direct synchronous messaging.

## Send a Direct Synchronous Message

In an example project, you'll use a `SynchronousMessageSender` to send a message directly to two listeners.

1. Start a [Liferay Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. Download and unzip the example.

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/message-bus/liferay-x6n5.zip -O
    ```

    ```bash
    unzip liferay-x6n5.zip
    ```

1. Build and deploy the example project modules.

    ```bash
    cd liferay-x6n5
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       This command is the same as copying the module JARs to ``/opt/liferay/osgi/modules`` on the Docker container.
    ```

1. The Docker container console shows that the modules started.

    ```bash
    STARTED com.acme.x6n5.able.impl_1.0.0
    STARTED com.acme.x6n5.baker.impl_1.0.0
    STARTED com.acme.x6n5.charlie.impl_1.0.0
    STARTED com.acme.x6n5.dog.impl_1.0.0
    ```

1. Visit the Liferay instance with your browser at `http://localhost:8080` and sign in using your credentials.

1. Open the [Gogo Shell](../../../liferay-internals/fundamentals/using-the-gogo-shell/using-the-gogo-shell.md).

1. In the Gogo Shell command field, enter `x6n5:sendMessage` followed by a message. For example,

    ```groovy
    x6n5:sendMessage foo
    ```

1. Confirm the output looks like this.

    ```
   INFO  [pipe-x6n5:sendMessage foo][X6N5DogMessageListener:21] Received message payload foo
   INFO  [pipe-x6n5:sendMessage foo][X6N5CharlieMessageListener:21] Received message payload foo
   INFO  [pipe-x6n5:sendMessage foo][X6N5BakerOSGiCommands:28] Response: X6N5CharlieMessageListener
    ```

The thread blocks in the message sender (i.e., `X6N5BakerOSGiCommands`) when it sends the message. After processing the message in `X6N5CharlieMessageListener` and `X6N5DogMessageListener`, the thread continues in the message sender.

## Project Overview

The four example modules have one class. On class manages the destination, another sends a message, and the other two listen for messages sent to the destination.

Example Classes:

| Class | Module | Description |
| :---- | :----- | :---------- |
| `X6N5AbleMessagingConfigurator` | `x6n5-able-impl` | Creates a message destination named `acme/x6n5_able` and registers it with the Message Bus. |
| `X6N5BakerOSGiCommands` | `x6n5-baker-impl` | Sends a message to the `acme/x6n5_able` destination and logs the response. |
| `X6N5CharlieMessageListener` | `x6n5-charlie-impl` | Listens for messages sent to the `acme/x6n5_able` destination. It logs the message payload and sets a response on the message. |
| `X6N5DogMessageListener` | `x6n5-dog-impl` |Listens for messages sent to the `acme/x6n5_able` destination. It logs the message payload and sets a response on the message. |

Here's the event flow:

1. When a user executes the `x6n5:sendMessage` Gogo shell command, `X6N5BakerOSGiCommands` sends the command arguments in a message payload to the `acme/x6n5_able` destination.
1. The current thread processes message reception for each listener (i.e., `X6N5CharlieMessageListener` and `X6N5DogMessageListener`) in succession. The listeners log the message payload and set a response on the message. The response from the latest listener processed supersedes previous responses.
1. Processing returns to `X6N5BakerOSGiCommands`, where it logs the message response.

Now you can examine each class, starting with the destination configurator 

## Examine the Destination Configurator

The `x6n5-able-impl` module's `X6N5AbleMessagingConfigurator` class creates and configures a destination named `acme/x6n5_able`. Here's the code:

```{literalinclude} ./using-direct-synchronous-messaging/resources/liferay-x6n5.zip/x6n5-able-impl/src/main/java/com/acme/x6n5/able/internal/messaging/X6N5AbleMessagingConfigurator.java
   :language: java
   :lines: 15-42
```

This configurator is a [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) class. It uses the [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) annotation to inject a `DestinationFactory` instance.

The `_activate(BundleContext)` method uses the [`DestinationFactory`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationFactory.java) and a [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) to create a *synchronous* destination named `acme/x6n5_able`. Synchronous destinations are optimized for synchronous messaging. Lastly, the method registers the [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) in an OSGi service using the `BundleContext`. 

When `X6N5AbleMessagingConfigurator` deactivates, its `_deactivate()` method unregisters the destination service.

## Examine the Sender

The `x6n5-baker-impl` module's `X6N5BakerOSGiCommands` class provides an OSGi Command that sends messages to the destination:

```{literalinclude} ./using-direct-synchronous-messaging/resources/liferay-x6n5.zip/x6n5-baker-impl/src/main/java/com/acme/x6n5/baker/internal/osgi/commands/X6N5BakerOSGiCommands.java
   :language: java
   :lines: 12-42
```

`X6N5BakerOSGiCommands` is a service `Component` of its own class type. It uses a `@Reference` annotation to inject a `SynchronousMessageSender` that's set to *direct* mode (specified by the annotation's `target = "(mode=DIRECT)"` attribute). 

```note::
   In *direct* mode, the ``SynchronousMessageSender`` ``send`` method blocks the calling class until the current thread delivers the message to all listeners.
```

`X6N5BakerOSGiCommands`'s `@Component` properties define a Gogo shell command function called `sendMessage` and in the `x6n5` scope. The command and maps to the `sendMessage(String)` method and takes an input `String`.

The `sendMessage(String)` method creates a [`Message`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Message.java) with the Gogo shell command's `String` as a payload. The `SynchronousMessageSender` `send(String, Message)` method uses the current thread to deliver the message to `acme/x6n5_able` [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) message listeners. Execution blocks in the `X6N5BakerOSGiCommands` class until the thread processes the message in all the [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java)s. Then execution continues in the `X6N5BakerOSGiCommands` `sendMessage(String)` method, where it logs the message response.

## Examine the Listeners

The `x6n5-charlie-impl` module's `X6N5CharlieMessageListener` class and `x6n5-dog-impl` module's `X6N5DogMessageListener` class listen for messages sent to the `acme/x6n5_able` [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java). They register the same way [Listening for Messages](./listening-for-messages.md) demonstrates. 

`X6N5CharlieMessageListener` class:

```{literalinclude} ./using-direct-synchronous-messaging/resources/liferay-x6n5.zip/x6n5-charlie-impl/src/main/java/com/acme/x6n5/charlie/internal/messaging/X6N5CharlieMessageListener.java
   :language: java
   :lines: 10-30
```

`X6N5DogMessageListener` class:

```{literalinclude} ./using-direct-synchronous-messaging/resources/liferay-x6n5.zip/x6n5-dog-impl/src/main/java/com/acme/x6n5/dog/internal/messaging/X6N5DogMessageListener.java
   :language: java
   :lines: 10-30
```

Each listener's `receive(Message)` method logs the message payload and then sets the message response to its own class name.

Congratulations! You know how to use direct synchronous messaging.

## What's Next

If you want to explore synchronous messaging using *default* mode, see [Using Default Synchronous Messaging](./using-default-synchronous-messaging.md).

If you want to continue processing immediately after sending a message, see [Using Asynchronous Messaging](./using-asynchronous-messaging.md).

## Additional Information

* [Message Bus](./message-bus.md)
* [Listening for Messages](./listening-for-messages.md)
* [Using Asynchronous Messaging](./using-asynchronous-messaging.md)
* [Listening for Registration Events](./listening-for-registration-events.md)
