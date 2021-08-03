# Listening for Registration Events

The messaging API supports listening for destination and message listener registration events. Here are some reasons to listen for these events:

* Messages that interest you may be sent to the new destinations
* Messaging at the destinations may require tuning
* You may depend on a destination that's been unregistered
* Unregistrations free up resources that can reallocated

The example project demonstrates listening for these registration events. Start with running the example. Then examine the event listener implementations. Lastly, trigger the unregistration events.

## Trigger the Events in an Example

These deploy a destination registration listener, a message listener registration listener, and classes that trigger the events they're listening for.

1. Start a [Liferay Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. Download and unzip the example.

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/message-bus/liferay-s3z9.zip -O
    ```

    ```bash
    unzip liferay-s3z9.zip
    ```

1. Launch the Message Bus event listener by deploying the `s3z9-able-impl` module.

    ```bash
    cd liferay-s3z9/s3z9-able-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       This command is the same as copying the module JAR to ``/opt/liferay/osgi/modules`` on the Docker container.
    ```

1. Add a destination by deploying the `s3z9-baker-impl` module.

    ```bash
    cd ../s3z9-baker-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. The Docker container console shows `S3Z9AbleMessageBusEventListener`'s response to the newly added destination.

    ```bash
    [main][S3Z9AbleMessageBusEventListener:17] Destination added acme/s3z9_baker
    ```

1. Launch the destination event listener by deploying the `s3z9-charlie-impl` module.

    ```bash
    cd ../s3z9-charlie-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Register a message listener to the destination by deploying the `s3z9-dog-impl` module.

    ```bash
    cd ../s3z9-dog-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. The Docker container console shows `S3Z9CharlieDestinationEventListener`'s response to the newly registered message listener.

    ```bash
    [S3Z9CharlieDestinationEventListener:23] Registered message listener to acme/s3z9_baker
    ```

Here's the module overview:

1. `s3z9-able-impl`'s [`MessageBusEventListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageBusEventListener.java) implementation listens for destination additions and removals.
1. `s3z9-baker-impl`'s messaging configurator class adds a destination; `s3z9-able-impl`'s `MessageBusEventListener` implementation receives the added destination notification and logs the event.
1. `s3z9-charlie-impl`'s [`DestinationEventListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationEventListener.java) implementation listens for message listeners registering to or unregistering from the destination.
1. `s3z9-dog-impl`'s [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) implementation registers to the destination; `s3z9-charlie-impl`'s `DestinationEventListener` implementation receives message listener registration notification and logs the event.

## Examine the `MessageBusEventListener`

Message Bus notifies [`MessageBusEventListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageBusEventListener.java)s when [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java)s are added or removed. Here's the example `MessageBusEventListener` implementation:

```{literalinclude} ./listening-for-registration-events/resources/liferay-s3z9.zip/s3z9-able-impl/src/main/java/com/acme/s3z9/able/internal/messaging/S3Z9AbleMessageBusEventListener.java
   :language: java
   :lines: 10-31
```

The [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) annotation and its `service = MessageBusEventListener.class` attribute signal the runtime framework to register `S3Z9AbleMessageBusEventListener` as a `MessageBusEventListener`. The implementation overrides `MessageBusEventListener`'s two methods:

* `destinationAdded(Destination destination)` responds to the newly added `Destination`.
* `destinationRemoved(Destination destination)` responds to the newly removed `Destination`.

`S3Z9AbleMessageBusEventListener`'s method implementations log the destination events.

## Examine the `DestinationEventListener`

Message Bus notifies a [`DestinationEventListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationEventListener.java) when [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java)s register to or unregister from the `DestinationEventListener`'s specified destination. Here's the example `DestinationEventListener` implementation:

```{literalinclude} ./listening-for-registration-events/resources/liferay-s3z9.zip/s3z9-charlie-impl/src/main/java/com/acme/s3z9/charlie/internal/messaging/S3Z9CharlieDestinationEventListener.java
   :language: java
   :lines: 10-38
```

The `@Component` annotation's `property = "destination.name=acme/s3z9_baker"` and `service = MessageBusEventListener.class` attributes signal the runtime framework to register `S3Z9CharlieDestinationEventListener` as a `DestinationEventListener` for the `acme/s3z9_baker` destination. The implementation overrides `DestinationEventListener`'s two methods:

* `messageListenerRegistered(String destinationName, MessageListener messageListener)` responds to a new message listener registered to the destination.
* `messageListenerUnregistered(String destinationName, MessageListener messageListener)` responds to a new message listener unregistered from the destination.

`S3Z9CharlieDestinationEventListener`'s method implementations log the message listener registration events.

Read on to see the example `MessageBusEventListener` and `DestinationEventListener` respond to a message bus listener unregistration and a destination removal.

## Trigger the Other Events

You can unregister the example message listener and remove the example destination by stopping their modules. Remember that `s3z9-dog-impl` deployed the message listener and `s3z9-able-impl` deployed the destination. When you stop these modules, their classes unregister the message listener and destination, respectively.

1. Visit the Liferay instance with your browser at `http://localhost:8080` and sign in using your credentials.

1. Open the [Gogo shell](../../../liferay-internals/fundamentals/using-the-gogo-shell/using-the-gogo-shell.md).

1. List the example modules by entering this command in the Gogo shell command field:

    ```bash
    lb | grep S3Z9
    ```

    The start of each line includes the corresponding module's ID number.

    ```bash
    1839|Active     |   10|Acme S3Z9 Able Implementation (1.0.0)|1.0.0
    1840|Active     |   10|Acme S3Z9 Baker Implementation (1.0.0)|1.0.0
    1841|Active     |   10|Acme S3Z9 Charlie Implementation (1.0.0)|1.0.0
    1842|Active     |   10|Acme S3Z9 Dog Implementation (1.0.0)|1.0.0
    ```

1. Stop the message listener's module by entering the following Gogo shell command, replacing the number with your module's ID:

    ```bash
    stop 1842
    ```

1. Confirm the destination event listener's logged response to the message listener unregistration.

    ```bash
    [S3Z9CharlieDestinationEventListener:33] Unregistered message listener from acme/s3z9_baker
    ```

1. Stop the destination's module by entering the following Gogo shell command, replacing the number with your module's ID:

    ```bash
    stop 1840
    ```

Congratulations! You've triggered all of the message bus event listener and destination event listener events.

## What's Next

Now that you know how to listen for these Message Bus events, you can listen for messages at new destinations or [tune your messaging environment](./tuning-messaging-performance.md) in response to new registration related activities.

## Additional Information

* [Tuning Messaging Performance](./tuning-messaging-performance.md)
* [Listening for Messages](./listening-for-messages.md)
* [Using Asynchronous Messaging](./using-asynchronous-messaging.md)
