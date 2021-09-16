# Tuning Messaging Performance

Messaging performance is tuned at the destinations. Performance depends on the destination type, the amount of processing the message listeners require, and the thread pool available to process messages.

Here are the three destination types:

**Parallel Destination** 
* Messages sent here are queued.
* Worker threads from a thread pool deliver messages to registered message listeners, one worker thread per message per message listener. Threads simultaneously deliver the same message to a destination's message listeners.

**Serial Destination** 
* Messages sent here are queued.
* Worker threads from a thread pool deliver messages to registered message listeners, one worker thread per message.

**Synchronous Destination** 
* Messages sent here are directly delivered to message listeners.
* The thread sending the message also delivers the message to all message listeners.

You can send messages in different ways using the applicable destination types. 

**Destination Type Compatibility**

Below is each destination type's compatibility with [asynchronous messaging](./using-asynchronous-messaging.md), [default synchronous messaging](./using-default-synchronous-messaging.md), and [direct synchronous messaging](./using-direct-synchronous-messaging.md).

| Destination Type | Asynchronous Messaging | Default Synchronous Messaging | Direct Synchronous Messaging |
| :---| :--- | :---- | :--- |
| **Parallel** | yes | yes | no |
| **Serial** | yes | yes | no |
| **Synchronous** | no | no | yes |

Here you'll start with examining an example project's messaging performance. Then you'll use the API to get destination statistics and to configure the destination. Lastly, you'll reconfigure the example destination settings, re-run the example, and examine the statistics.

## Monitor Messaging in an Example Project

The example project creates a destination, registers message listeners, and lists destination statistics via a Gogo shell command.

1. Start a [Liferay Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. Download and unzip the example.

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/message-bus/liferay-w3r2.zip -O
    ```

    ```bash
    unzip liferay-w3r2.zip
    ```

1. Build and deploy the example project modules.

    ```bash
    cd liferay-w3r2
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       This command is the same as copying the module JARs to ``/opt/liferay/osgi/modules`` on the Docker container.
    ```

1. The Docker container console confirms module startup and reports the destination configuration.

    ```
    STARTED com.acme.w3r2.charlie.impl_1.0.0 [1390]
	STARTED com.acme.w3r2.able.impl_1.0.0 [1388]
	[W3R2AbleMessagingConfigurator:27] {_destinationName=acme/w3r2_able,
	_destinationType=serial, _maximumQueueSize=2147483647,
	_rejectedExecutionHandler=null, _workersCoreSize=2, _workersMaxSize=5}
	STARTED com.acme.w3r2.baker.impl_1.0.0 [1389]
    ```

1. Visit the Liferay instance with your browser at `http://localhost:8080` and sign in using your credentials.

1. Open the [Script console](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console.md).

1. In the script field, send a message by executing the following Groovy code:

    ```groovy
   import com.liferay.portal.kernel.messaging.*;

	MessageBusUtil.sendMessage(
		"acme/w3r2_able",
		new Message() {
			{
				setPayload("foo");
			}
		});
    ```

1. Confirm that message listeners from `W3R2BakerMessageListenerManager` received the message.

    ```
	[acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
	[acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
	[acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
	[acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
	[acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
    ```

1. Open the [Gogo shell](../../../liferay-internals/fundamentals/using-the-gogo-shell.md).

1. Get the destination statistics by executing the `w3r2:listDestinationStats` command in the Gogo shell command field.

    ```groovy
    w3r2:listDestinationStats
    ```

1. Confirm the `acme/w3r2_able` destination's 10 listeners and the sent message count.

    ```
	[pipe-w3r2:listDestinationStats][W3R2CharlieOSGiCommands:29] acme/w3r2_able
	active thread count 0, current thread count 1, largest thread count 1, max
	thread pool size 1, message listener count 10, min thread pool size 1, pending
	message count 0, sent message count 1
    ```

The example's three modules configured a destination, registered 10 message listeners, and provided a Gogo shell command to list the destination's statistics.

When `W3R2AbleMessagingConfigurator` in `w3r2-able-impl` activates, it configures the `acme/w3r2_able` destination and logs the `DestinationConfiguration`'s `toString()` value.

```{literalinclude} ./tuning-messaging-performance/resources/liferay-w3r2.zip/w3r2-able-impl/src/main/java/com/acme/w3r2/able/internal/messaging/W3R2AbleMessagingConfigurator.java
   :dedent: 1
   :language: java
   :lines: 20-37
```

`W3R2CharlieOSGiCommands` in the `w3r2-charlie-impl` module logs the destination statistics using the `w3r2:listDestinationStats` Gogo shell command it provides. Examine how `W3R2CharlieOSGiCommands`'s `listDestinationStats()` method gets destination statistics.

```{literalinclude} ./tuning-messaging-performance/resources/liferay-w3r2.zip/w3r2-charlie-impl/src/main/java/com/acme/w3r2/charlie/internal/osgi/commands/W3R2CharlieOSGiCommands.java
   :language: java
   :lines: 13-56
```

The `listDestinationStats()` method uses the `_messageBus` instance to get the `Destination` and then gets a `DestinationStatistics` instance from the destination. The destination populates the `DestinationStatistics` object with the latest statistics. The method logs the following destination information:

* Active thread count
* Current thread count
* Largest thread count
* Maximum thread pool size
* Message listener count
* Minimum (starting) thread pool size
* Pending message count
* Sent message count

You can monitor your message destination using this same API.

## Monitoring Messaging

The messaging API facilitates monitoring messaging performance at destinations in the context of their settings. The following tables list API methods to access destination settings and messaging statistics.

**Destination Settings:**

| Destination Setting | API Method |
| :------------------ | :--------- |
| Destination type | `Destination#getDestinationType()` |
| Maximum thread pool size | `DestinationConfiguration#getWorkersMaxSize()` and `DestinationStatistic#getMaxThreadPoolSize()` |
| Minimum thread pool size | `DestinationConfiguration#getWorkersCoreSize()` and `DestinationStatistic#getMinThreadPoolSize()` |
| Message queue size | `DestinationConfiguration#getMaximumQueueSize()` |

**Destination Statistics:**

| Destination Statistic | API Method |
| :-------------------- | :--------- |
| Message listener count | `Destination#getMessageListenerCount()` |
| Messages pending count | `DestinationStatistics#getPendingMessageCount()` |
| Messages sent count | `DestinationStatistics#getSentMessageCount()` |
| Current thread count | `DestinationStatistics#getCurrentThreadCount()` |
| Active thread count | `DestinationStatistics#getActiveThreadCount()` |
| Largest thread count | `DestinationStatistics#getLargestThreadCount()` |

Make sure to digest a destination's statistics in the context of its settings.

After examining destination statistics, you can try to improve performance by reconfiguring the destination.

## Changing Destination Type

If you're using a serial destination and messages aren't reaching some message listeners fast enough, you can increase the maximum thread pool size (explained next) or try switching to the parallel destination type. Message Bus processes parallel destination message listeners simultaneously using threads from the thread pool.

You can switch destination types by replacing your current [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) with one of the type you want. Create a new parallel or serial `DestinationConfiguration` using the applicable `DestinationConfiguration` method:

* `createParallelDestinationConfiguration(String)`
* `createSerialDestinationConfiguration(String)`

See [Reconfigure the Example Destination](#reconfigure-the-example-destination) for details.

## Configuring the Message Queue and Thread Pool

Each serial and parallel destination has a message queue and a dedicated thread pool.

If a message arrives when the queue is full, the destination's `RejectedExecutionHandler` handles the message. The default handler discards the message and logs a warning. The default maximum message queue size is Java's maximum integer value, but you can reduce it if you like.

The Message Bus draws message listener processing threads from the destination's thread pool. The pool has a starting size and a maximum size.

You can change the maximum message queue size, rejected execution handler, thread pool starting size (core size), and thread pool maximum size using these [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) methods:

* `setMaximumQueueSize(int maximumQueueSize)`
* `setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler)`
* `setWorkersCoreSize(int workersCoreSize)`
* `setWorkersMaxSize(int workersMaxSize)`

Next reconfigure the example destination.

## Reconfigure the Example Destination

Here you'll reconfigure the example's `acme/w3r2_able` destination with these settings:

* Destination type: `parallel`
* Starting thread pool size: `10`
* Maximum thread pool size: `20`

Here are the steps:

1. Use a different `DestinationConfiguration` by replacing `W3R2AbleMessagingConfigurator`'s `_activate(BundleContext)` method with this code:

	```java
	@Activate
	private void _activate(BundleContext bundleContext) {
		DestinationConfiguration destinationConfiguration =
			DestinationConfiguration.createParallelDestinationConfiguration(
				"acme/w3r2_able");

		destinationConfiguration.setWorkersCoreSize(10);
		destinationConfiguration.setWorkersMaxSize(20);

		if (_log.isInfoEnabled()) {
			_log.info(destinationConfiguration.toString());
		}

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		_serviceRegistration = bundleContext.registerService(
			Destination.class, destination,
			MapUtil.singletonDictionary(
				"destination.name", destination.getName()));
	}
	```

1. Redeploy the modules.

	```bash
	./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
	```

1. The Docker container console confirms the `w3r2-able-impl` module startup and reports the destination configuration.

	```
	STARTED com.acme.w3r2.able.impl_1.0.0 [1388]
	[W3R2AbleMessagingConfigurator:27] {_destinationName=acme/w3r2_able,
	_destinationType=parallel, _maximumQueueSize=2147483647,
	_rejectedExecutionHandler=null, _workersCoreSize=10, _workersMaxSize=20}
	```

1. Get the message listener module (Acme W3R2 Baker Implementation) ID by running this Gogo shell command:

    ```bash
    lb | grep W3R2
    ```

    Each line starts with the corresponding module's ID number.

    ```bash
    1388|Active     |   10|Acme W3R2 Able Implementation (1.0.0)|1.0.0
    1389|Active     |   10|Acme W3R2 Baker Implementation (1.0.0)|1.0.0
    1390|Active     |   10|Acme W3R2 Charlie Implementation (1.0.0)|1.0.0
    ```

1. Bind the message listeners to the destination replacement by restarting the message listener module with the following Gogo shell commands. Replace the number with your module's ID:

    ```bash
    stop 1389
    ```

    ```bash
    start 1389
    ```

1. Send another message by executing the following Groovy code again in the Script console.

    ```groovy
    import com.liferay.portal.kernel.messaging.*;

	MessageBusUtil.sendMessage(
		"acme/w3r2_able",
		new Message() {
			{
				setPayload("foo");
			}
		});
    ```

1. Get the destination statistics by executing the `w3r2:listDestinationStats` command in the Gogo shell.

    ```bash
	w3r2:listDestinationStats
	```

A log message like this one confirms your new settings.

```bash
[pipe-w3r2:listDestinationStats][W3R2CharlieOSGiCommands:29] acme/w3r2_able
active thread count 0, current thread count 10, largest thread count 10, max
thread pool size 20, message listener count 10, min thread pool size 10,
pending message count 0, sent message count 2
```

Now you know how to monitor messaging at a destination and adjust destination settings. You can test different settings to optimize performance.

## Additional Information

* [Using Asynchronous Messaging](./using-asynchronous-messaging.md)
* [Using Default Synchronous Messaging](./using-default-synchronous-messaging.md)
* [Using Direct Synchronous Messaging](./using-direct-synchronous-messaging.md)
