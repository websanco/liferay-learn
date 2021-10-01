# Using Asynchronous Messaging

Message Bus's asynchronous option provides "fire and forget" behavior; send a message and continue processing without waiting for a response.

An asynchronous message is sent to a *serial* or *parallel* destination.

* For *serial* destinations, the Message Bus queues messages and delegates one worker thread per message. The thread processes message listeners sequentially.

* For *parallel* destinations, the Message Bus queues messages and delegates one worker thread per message per message listener. The threads process message listeners simultaneously.

Start with sending a message to a serial destination where another class (a message listener) is listening. 

## Send a Message

Start with sending a message in an example project.

1. Download and unzip the example.

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/message-bus/liferay-n8k5.zip -O
   ```

   ```bash
   unzip liferay-n8k5.zip
   ```

1. Start a [Liferay Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

   ```bash
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

1. Build and deploy the destination module `n8k5-able-impl`.

    ```bash
    cd liferay-n8k5/n8k5-able-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    This command is the same as copying the module JAR to `/opt/liferay/osgi/modules` on the Docker container.
    ```

    The Docker container console shows that the module started.

    ```bash
    STARTED com.acme.n8k5.able.impl_1.0.0
    ```

1. Build and deploy the listener module `n8k5-charlie-impl`.

    ```bash
    cd ../n8k5-charlie-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    The Docker container console shows that the module started.

    ```bash
    STARTED com.acme.n8k5.charlie.impl_1.0.0
    ```

1. Build and deploy the sender module `n8k5-baker-impl`.

    ```bash
    cd ../n8k5-baker-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    In the Docker container console, confirm `N8K5Baker` sent a message, `N8K5CharlieMessageListener` received a message, and the `n8k5-baker-impl` module started.

   ```bash
   INFO  [pipe-start 2025][N8K5Baker:24] Sent message to acme/n8k5_able
   INFO  [acme/n8k5_able-4][N8K5CharlieMessageListener:21] Received message payload N8K5Baker#_activate
   STARTED com.acme.n8k5.baker.impl_1.0.0 [2025]
   ```

`N8K5Baker` reported sending a message to destination `acme/n8k5_able`. `N8K5CharlieMessageListener` received a message with payload `N8K5Baker#_activate` at destination `acme/n8k5_able`. Now you can examine the example code.

## Project Overview

The example's three modules each have one class. Each class represents one of the messaging components: destination, sender, listener: 

Example Classes:

| Class | Description |
| :---- | :---------- |
| `N8K5AbleMessagingConfigurator` in n8k5-able-impl | Creates a message destination named `acme/n8k5_able` and registers it with the Message Bus. |
| `N8K5Baker` in n8k5-baker-impl | Sends a message to the `acme/n8k5_able` destination. |
| `N8K5CharlieMessageListener` in n8k5-charlie-impl | Listens for messages sent to the `acme/n8k5_able` destination. |

Here's how they interact:

1. `N8K5Baker` activates (for example, when the `n8k5-baker-impl` module starts) and sends a message to the `acme/n8k5_able` destination.
1. The Message Bus sends the message to `N8K5CharlieMessageListener`.
1. `N8K5CharlieMessageListener` receives the message.

We'll examine the destination configuration and sender classes. The listener class `N8K5CharlieMessageListener` registers the same way [Listening for Messages](./listening-for-messages.md) demonstrates.

## Examine the Destination Configuration

The `n8k5-able-impl` module's `N8K5AbleMessagingConfigurator` class creates and configures the destination. Here's the code:

```{literalinclude} ./using-asynchronous-messaging/resources/liferay-n8k5.zip/n8k5-able-impl/src/main/java/com/acme/n8k5/able/internal/messaging/N8K5AbleMessagingConfigurator.java
:language: java
:lines: 15-42
```

Any class can create and configure a destination, but a [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) can have injected dependencies, like the `DestinationFactory`. The `_destinationFactory` field's [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) annotation signals Liferay's OSGi framework to inject a `DestinationFactory` instance. 

In the `_activate` method, `N8K5AbleMessagingConfigurator` uses the [`DestinationFactory`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationFactory.java) and a [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) to create a *serial* destination named `acme/n8k5_able`. Then it uses the OSGi Framework `BundleContext` to register a service for the `Destination`. When `N8K5AbleMessagingConfigurator` deactivates, the `_deactivate` method unregisters the service.

## Examine the Sender

The `N8K5Baker` class below sends a message with the payload `"N8K5Baker#_activate"` to the destination named `acme/n8k5_able`.

```{literalinclude} ./using-asynchronous-messaging/resources/liferay-n8k5.zip/n8k5-baker-impl/src/main/java/com/acme/n8k5/baker/internal/N8K5Baker.java
:language: java
:lines: 12-23
```

As a Component, `N8K5Baker` uses the `@Reference` annotation to inject a `MessageBus` instance.

On component activation, `N8K5Baker` creates and sends a message via its activation method `_activate()`. It constructs a [`Message`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Message.java) instance and adds a payload to it. A payload is one of several things you can populate in a `Message`. 

Here are the main message-populating methods:

| Method | Description |
| :----- | :---------- |
| `setPayload(Object)` | Adds your `Message`'s main content. |
| `setResponseDestinationName(String)` | Refers to a `Destination` to receive responses. |
| `setValues(Map<String,Object>)` | Provides additional data from a `Map`. |

`N8K5Baker` sends the message to a [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) named `acme/n8k5_able` by calling [`MessageBus`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageBus.java)'s `sendMessage(String, Message)` method. The `MessageBus` starts a new thread and sends the `Message` to [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) instances registered to the `acme/n8k5_able` `Destination`. `N8K5Baker`'s thread continues.

```{note}
If you want receive responses to a `Message`, set a response destination on the `Message` and register a class, such as `N8K5Baker`, as a `MessageListener` to that destination. See [Listening for Messages](./listening-for-messages.md) for details.
```

## Add Response Handling

If you want responses from message recipients, set a response destination for their replies:

1. Register a separate destination for message responses.
1. Register a class (e.g., the original sender) as a `MessageListener` on the response destination.
1. Pass the response destination in the messages.
1. Add response logic to the `MessageListener`s.

### Step 1: Register a Destination for Responses

You can modify `N8K5Baker` to manage a response destination in the same way that `N8K5AbleDestinationConfigurator` manages its destination. Replace the `_activate()` method signature to `_activate(BundleContext bundleContext)` and add code to it that creates, configures, and registers a service for the `acme/n8k5_baker` response destination. Add a `_deactivate()` method that unregisters the service. The `_activate(BundleContext bundleContext)` and `_deactivate()` methods should look like this:

```java
@Activate
private void _activate(BundleContext bundleContext) {
   Destination destination = _destinationFactory.createDestination(
      DestinationConfiguration.createSerialDestinationConfiguration(
         "acme/n8k5_baker"));

   _serviceRegistration = bundleContext.registerService(
      Destination.class, destination,
      MapUtil.singletonDictionary(
         "destination.name", destination.getName()));

   Message message = new Message();

   message.setPayload("N8K5Baker#_activate");

   _messageBus.sendMessage("acme/n8k5_able", message);
}

@Deactivate
private void _deactivate() {
   if (_serviceRegistration != null) {
      _serviceRegistration.unregister();
   }
}

@Reference
private DestinationFactory _destinationFactory;

private ServiceRegistration<Destination> _serviceRegistration;
```

### Step 2: Register `N8K5Baker` as a Listener on the Response Destination

Here are the changes for sender `N8K5Baker`:

1. Update the `@Component` annotation, declaring `N8K5Baker` a service of type `MessageListener.class` and mapping `N8K5Baker` to its response destination via a property `"destination.name=acme/n8k5_baker"`.
1. Implement the [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) interface.
1. Override the `receive(Message)` method with message-processing logic.

Here's what the sender changes look like:

```java
@Component(
	property = "destination.name=acme/n8k5_baker",
	service = MessageListener.class
)
public class N8K5Baker implements MessageListener {

	@Override
	public void receive(Message message) {
		Object payload = message.getPayload();

		_log.info("Received message payload " + payload.toString());
	}

   // Existing methods and fields

   private static final Log _log = LogFactoryUtil.getLog(N8K5Baker.class);
}
```

### Step 3: Pass the Response Destination in the Message

Set `acme/n8k5_baker` as the response destination in the message `N8K5Baker` sends. Here's what it looks like:

```java
@Activate
private void _activate(BundleContext bundleContext) {
   // Destination setup

   Message message = new Message();

   message.setPayload("N8K5Baker#_activate");
   message.setResponseDestinationName("acme/n8k5_baker");

   _messageBus.sendMessage("acme/n8k5_able", message);
}
```

### Step 4: Add Response Logic to the `MessageListener`s

In your `MessageListener`'s `receive(Message)` methods, set a response, get the response destination from the message, and send a response message to the response destination using a `MessageBus` instance. Here's what it looks like:

```java
public void receive(Message message) {
   // Message processing

   message.setResponse("N8K5CharlieMessageListener");

   Message responseMessage = new Message();

   responseMessage.setDestinationName(
      message.getResponseDestinationName());
   responseMessage.setPayload("N8K5CharlieMessageListener");
   responseMessage.setResponseId(message.getResponseId());

   _messageBus.sendMessage(
      message.getResponseDestinationName(), responseMessage);
}

// Existing methods and fields

@Reference
private MessageBus _messageBus;
```

### Test Your Changes

Test the changes by redeploying the example project.

```bash
cd ../../liferay-n8k5.zip
```

```bash
./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
```

The output looks like this:

```bash
STARTED com.acme.n8k5.charlie.impl_1.0.0 [2020]
STARTED com.acme.n8k5.baker.impl_1.0.0 [2025]
INFO  [acme/n8k5_able-2][N8K5CharlieMessageListener:23] Received message payload N8K5Baker#_activate
INFO  [acme/n8k5_baker-2][N8K5Baker:30] Received message payload N8K5CharlieMessageListener
```

`N8K5CharlieMessageListener` receives `N8K5Baker`'s message and then sends a response message to the response destination. `N8K5Baker` receives the response message and prints the message payload.

```{note}
If you want the classes to exchange messages again, you can restart the modules (OSGi bundles) in the [Gogo Shell](../../../liferay-internals/fundamentals/using-the-gogo-shell.md). List the bundles (`lb`) to get the bundle IDs, stop the bundles (`stop <id>`), and restart the bundles (`start <id>`).
```

```{note}
In classes that aren't OSGi Components, you can send messages using [MessageBusUtil](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageBusUtil.java) and `Destination`, `DestinationConfiguration`, `Message`, and `MessageListener` instances.
   
You can register `Destination` services as demonstrated, except you must get the `BundleContext` a different way (for example, by making these calls: `Bundle bundle = FrameworkUtil.getBundle(YourClass.class); BundleContext bundleContext = bundle.getBundleContext()`).
```

Congratulations! You've exchanged messages asynchronously between two classes.

## What's Next

Now that you're familiar with asynchronous messaging, you can tune it for optimal performance. Learn how at [Tuning Messaging Performance](./tuning-messaging-performance.md).

If you want to explore synchronous messaging using *default* and *direct* modes, see [Using Direct Synchronous Messaging](./using-direct-synchronous-messaging.md) and [Using Default Synchronous Messaging](./using-default-synchronous-messaging.md) for details.

## Additional Information

* [Message Bus](../message-bus.md)
* [Listening for Messages](./listening-for-messages.md)
* [Listening for Registration Events](./listening-for-registration-events.md)
