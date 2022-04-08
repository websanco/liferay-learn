---
description: 13 - Leverage Liferay Message Bus
title: Introducing Liferay Message Bus
order: 2
---

## Introducing Liferay Message Bus

Liferay Message Bus (LMB) is a service-level API for exchanging messages inside Liferay. Liferay Message Bus is similar to [JMS](https://download.oracle.com/otndocs/jcp/7195-jms-1.1-fr-spec-oth-JSpec/) (Java Message Service), but has a smaller feature set. It also supports synchronous and asynchronous messaging in the cluster, but lacks, for example, transactional and reliable delivery (acknowledgments).

The message bus is used in Liferay for many background and asynchrous processes like:

* Auditing
* Search engine integration (like sending search index write events)
* Running asynchronous background processes 
* Running scheduler tasks
* Running cluster operations like cache replication
* Document library processing
* Sending subscription emails
* Monitoring
* Running liferay/hot_deploy

The Message Bus API is available for custom applications and is the recommended approach for any time-consuming operation that should not block request processing like, for example:

* Sending bulk emails
* Running scheduled tasks
* File processing
* Cluster communication
* Auditing
* Sending payload to an integrated system

#### Message Bus Components

The message bus has three main components: *destinations*, *senders*, and *listeners*.

<img src="../images/components.png" style="max-height:20%;" />

#### Destinations

Destinations are addresses or named endpoints for sending and receiving messages that provide a loose coupling between *senders* and *listeners*.

There are three destination types:

* __Parallel__
	* Received messages are queued.
	* There's one worker thread per message per message listener.
* __Serial__
	* Received messages are queued
	* One worker thread per message
* __Synchronous__
	* No queue
	* The same thread that is sending the message delivers it to all the message listeners.


The diagram below illustrates the processing flow when using *asynchronous messaging with a parallel destination*:

<img src="../images/async-parallel-messaging-flow.png" style="max-height:20%;" />

The diagram below illustrates the processing flow when using *synchronous messaging with a serial destination*:

<img src="../images/sync-serial-messaging-flow.png" style="max-height:20%;" />

Generally, the steps for creating a Message Bus destination are:

1. Create a destination configuration (registrator) class.
	1. Create a destination using the DestinationFactory
	1. Register the destination as an OSGi service
	1. Manage the registration resource's lifecycle in the component's `@Activate` and `@DeActivate` methods.

Below is an example of a destination component:

```java
@Component(
	immediate = true
)
public class MessageBusDestinationRegistrator {

	@Activate
	protected void activate(ComponentContext componentContext) {

		_bundleContext = componentContext.getBundleContext();

		_log.info(
			"Registering message bus listener for " +
				TrainingDestinationNames.TRAINING_DESTINATION);
		
		register(
			DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
			TrainingDestinationNames.TRAINING_DESTINATION, null, true);
	}

	/**
	 * Register the endpoint.
	 * 
	 * @param destinationType
	 * @param destinationName
	 * @param destinationPropertyName
	 * @param destinationPropertyValue
	 */
	protected void register(
		String destinationType, String destinationName,
		String destinationPropertyName, Object destinationPropertyValue) {

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(destinationType, destinationName);

		destinationConfiguration.setMaximumQueueSize(5);
		destinationConfiguration.setRejectedExecutionHandler(
			new CallerRunsPolicy() {

				@Override
				public void rejectedExecution(
					Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

					if (_log.isWarnEnabled()) {
						_log.warn(
							"The current thread will handle the request " +
								"because the graph walker's task queue is at " +
								"its maximum capacity");
					}

					super.rejectedExecution(runnable, threadPoolExecutor);
				}

			});

		Destination destination =
						_destinationFactory.createDestination(destinationConfiguration);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			"destination.name", destinationConfiguration.getDestinationName());
		
		if (destinationPropertyName != null) {
			properties.put(destinationPropertyName, destinationPropertyValue);
		}

		ServiceRegistration<Destination> serviceRegistration =
			_bundleContext.registerService(
				Destination.class, destination, properties);

		_serviceRegistrations.put(destination.getName(), serviceRegistration);
	}

	@Deactivate
	protected void deactivate() {

		for (ServiceRegistration<Destination> serviceRegistration : _serviceRegistrations.values()) {

			Destination destination =
				_bundleContext.getService(serviceRegistration.getReference());

			serviceRegistration.unregister();

			destination.destroy();
		}

		_serviceRegistrations.clear();
	}

	@Modified
	protected void modified(ComponentContext componentContext) {

		deactivate();

		activate(componentContext);
	}

	private static final Log _log =
		LogFactoryUtil.getLog(MessageBusDestinationRegistrator.class);

	private volatile BundleContext _bundleContext;

	@Reference
	private DestinationFactory _destinationFactory;

	private final Map<String, ServiceRegistration<Destination>> _serviceRegistrations =
		new HashMap<>();
	
}
```

#### Senders

Senders invoke the Message Bus to send messages to registered destinations. Sending a message can be done from any class:

* __Directly__ to the message bus
* __Asynchronously__ using a SingleDestinationMessageSender
* __Synchronously__ using a SynchronousMessageSender

Below is an example of a method sending a message __directly__ to the message bus:

```java
protected void sendDirectMessage(String messageText) {

	Message message = new Message();

	message.setDestinationName(
		TrainingDestinationNames.TRAINING_DESTINATION);
	message.setPayload(messageText);
	message.setResponseDestinationName(
		TrainingDestinationNames.TRAINING_RESPONSE_DESTINATION);
	message.setResponseId("abcd");

	_messageBus.sendMessage(message.getDestinationName(), message);
}

@Reference
private MessageBus _messageBus;
```

In __asynchronous__ sending, after the message is sent (in a different thread), the sender is free to continue processing. By setting messages a *response destination*, a callback can be provided. Below is an example of sending an asynchronous message with a response destination:

```java
protected void sendAsyncMesssage(String messageText) {

	Message message = new Message();

	message.setDestinationName(
		TrainingDestinationNames.TRAINING_DESTINATION);
	message.setPayload(messageText);
	message.setResponseDestinationName(
		TrainingDestinationNames.TRAINING_RESPONSE_DESTINATION);
	message.setResponseId("abcd");

	_log.info("Sending async message: " + messageText);

	SingleDestinationMessageSender messageSender =
		_messageSenderFactory.createSingleDestinationMessageSender(
			TrainingDestinationNames.TRAINING_DESTINATION);

	messageSender.send(message);
}
```

__Synchronous Sending__ blocks the thread until it receives a response or the response times out. Two operation modes are available:

* __DEFAULT:__ Delivers the message in a separate thread with timeout 
* __DIRECT:__ Delivers the message in the same thread of execution and it blocks until it receives a response

Generally, as synchronous messaging can block threads, it should be used only if the delivery order has to be guaranteed.

Below is an example of sending a message synchronously:

```java
protected void sendSyncMessage(String messageText)
	throws Exception {

	Message message = new Message();

	message.setPayload(messageText);
	message.setResponseDestinationName(
		TrainingDestinationNames.TRAINING_RESPONSE_DESTINATION);
	message.setResponseId("abcd");

	SingleDestinationSynchronousMessageSender messageSender =
		_messageSenderFactory.createSingleDestinationSynchronousMessageSender(
			TrainingDestinationNames.TRAINING_DESTINATION,
			SynchronousMessageSender.Mode.DIRECT);

	Object response = messageSender.send(message, 10000);
}
```

#### Listeners

Listeners receive messages sent to their destinations. In order to receive messages, a listener has to register to a destination. Registration to an endpoint can be done in three ways:

1. __Automatic Registration as a Component:__ Publish the listener to the OSGi registry as a Declarative Services Component that specifies a destination. Message Bus automatically wires the listener to the destination.
2. __Registering via MessageBus:__ Obtain a reference to the Message Bus and use it directly to register the listener to a destination.
3. __Registering directly to a Destination:__ Obtain a reference to a specific destination and use it directly to register the listener with that destination.

Below is an example of automatic registration as a component:

```java
@Component(
    immediate = true, 
    property = {
    	"destination.name=" + TrainingDestinationNames.TRAINING_DESTINATION
    },
    service = MessageListener.class
)
public class MessageBusListener implements MessageListener {

	@Override
	public void receive(Message message)
		throws MessageListenerException {

		String payload = (String) message.getPayload();
	
		String responseDestinationName = message.getResponseDestinationName();

		if (Validator.isNotNull(responseDestinationName)) {

			String responsePayload = "Response to " + payload;

			Message responseMessage = new Message();
			
			responseMessage.setDestinationName(responseDestinationName);
			responseMessage.setPayload(responsePayload);
			responseMessage.setResponseId(message.getResponseId());

			_messageBus.sendMessage(
				message.getResponseDestinationName(), responseMessage);
		}
	}
```

In order to receive cluster messages, a ClusterBridgeMessageListener service component has to be registered in the destination. Below is an example:

```java
	@Component(
	    immediate = true,
	    service = MessageBusClusterListener.class
	)
	public class MessageBusClusterListener {
	
		@Activate
	    protected void activate() {
	
	        _clusterBridgeMessageListener = new ClusterBridgeMessageListener();
	        _destination.register(_clusterBridgeMessageListener);
	    }
	    
	    @Deactivate
	    protected void deactivate() {
	
	        _destination.unregister(_clusterBridgeMessageListener );
	    }
	
	    @Reference(target = "(destination.name=" + TrainingDestinationNames.TRAINING_DESTINATION + ")")
	    private Destination _destination;
	
	    private MessageListener _clusterBridgeMessageListener;
	}
```

#### Message Bus and Service Builder

Liferay Service Builder can leverage the message bus with two annotations: `@Async` and `@Clusterable`.

#### @Async

If any public service method is annotated with `@Async`, then the message bus calls to the method will be converted to asynchronous. This allows you to implement fire and forget capabilities in your services. This option is especially useful for features like notifications.

#### @Clusterable

Any service method annotated with `@Clusterable` will be invoked across the cluster. The `@Clusterable` annotation has two attributes:

* __onMaster:__ if set to true, it will only execute the request if the current portal JVM is holding the cluster wide “master” token
* __acceptor:__ specifies a custom ClusterInvokeAcceptor to determine whether a given portal JVM should accept and execute the request

#### Getting Message Bus Statistics

Message bus destinations are available as MBeans and can be monitored with any JMX tool like JConsole:

<img src="../images/jconsole.png" style="max-height:100%;" />

#### Further Reading

* Liferay Message Bus articles on Developer Network: https://dev.liferay.com/de/develop/tutorials/-/knowledge_base/7-2/message-bus


<div class="summary">
<h3>Knowledge Check</h3>
<ul> 
	<li> __________________________ is a service-level API for exchanging messages inside Liferay.</li>
	<li> The message bus has three main components:</li>
	<ul>
		<li> _________________: Addresses or endpoints to which listeners register to receive messages</li>
		<li> __________________: Invoke the Message Bus to send messages to destinations</li>
		<li> __________________: Receive messages sent to their registered destinations</li>
	</ul>
	<li> Liferay Service Builder can leverage the message bus with two annotations: ______________________ and ______________________.</li>
</ul>
</div>