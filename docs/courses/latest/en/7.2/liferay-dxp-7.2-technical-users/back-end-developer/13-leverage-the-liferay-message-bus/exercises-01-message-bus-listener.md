---
description: 13 - Leverage Liferay Message Bus
title: Create a Documents and Media Message Bus Listener
order: 13
---

<h2 class="exercise">Exercises</h2>

## Create a Documents and Media Message Bus Listener

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay Module project using the service template</li>
		<li>Implement the PDFMessageListener class</li>
		<li>Do a final code review</li>
		<li>Deploy and test</li>
	</ul>
</div>

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** the _Command Line_ shell in your Liferay Workspace `modules` folder.
1. **Run** the command:
```bash
blade create -t service -p com.liferay.training.messagebuslistener -s com.liferay.portal.kernel.messaging.MessageListener -c PDFMessageBusListener message-bus-listener
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use the Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  "message-bus-listener"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: service
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "PDFMessageBusListener"
	* __Package Name__: "com.liferay.training.messagebuslistener"
1. **Click** on the browse button next to the *Service Name* field.
	* Enter "\*.MessageListener" in the *Select Service Name*.
	* Select `com.liferay.portal.kernel.messaging.MessageListener` from the list.
1. **Click** the green plus button to add a property.
	* Enter "destination.name" for the *Name*.
	* Enter "resolveme* for the *Value*.
	> We are going to change this later in the exercise.
1. **Click** *Finish* to close the wizard.

#### Implement the PDFMessageListener Class

1. **Open** the class `com.liferay.training.messagebuslistener.PDFMessageBusListener`.
1. **Fix** the `destination.name` property value and implement the `@Component` annotation as follows:
	```java
	@Component (
		immediate = true,
		property = {
			"destination.name=" + DestinationNames.DOCUMENT_LIBRARY_PDF_PROCESSOR
		},
		service = MessageListener.class
	)
	```
	> Message Bus destination name constants can be found in the [DestinationNames](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationNames.java) class. 	
1. **Resolve** missing imports.
1. **Put** the mouse over the class name (showing an error) and select *Add unimplemented methods* from the menu.	
1. **Implement** the `receive()` method as follows:
	```java
	@Override
	public void receive(Message message)
		throws MessageListenerException {

			Object[] payload = (Object[])message.getPayload();
			
			FileVersion fileVersion = (FileVersion)payload[1];
			
			System.out.println("Title=" + fileVersion.getTitle());
	}
	```
1. **Resolve** missing imports.
	* Choose `com.liferay.portal.kernel.messaging.MessageListenerException` when prompted for the right `MessageListenerException`.

<br />

#### Final Code Review

The complete implementation of the `PDFMessageListener` will look as follows: 

```java

package com.liferay.training.messagebuslistener;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.repository.model.FileVersion;

import org.osgi.service.component.annotations.Component;

@Component (
	immediate = true,
	property = {
		"destination.name=" + DestinationNames.DOCUMENT_LIBRARY_PDF_PROCESSOR
	},
	service = MessageListener.class
)
public class PDFMessageBusListener implements MessageListener {

	@Override
	public void receive(Message message)
		throws MessageListenerException {

		Object[] payload = (Object[]) message.getPayload();

		FileVersion fileVersion = (FileVersion) payload[1];

		System.out.println("Title=" + fileVersion.getTitle());
	}
}
```

#### Deploy and Test

You'll need a PDF file for testing. If you don't have one, you may want to download Liferay's Compatibility Matrix PDF: https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?.

1. **Deploy** the *message-bus-listener* module to the Liferay server.
1. **Open** your browser to http://localhost:8080 and sign in.
1. **Click** on the plus button in the upper-right corner to add Widgets.
1. **Add** the *Documents and Media* widget to the page.
1. **Click** the plus button on the top left corner of the Documents and Media widget and select *File Upload*.
1. **Choose** the test PDF.
1. **Click** *Publish*.

You should see the name of the file you uploaded in the console:

```bash
2019-04-17 09:56:35.748 INFO  [pipe-start 1075][BundleStartStopLogger:39] STARTED com.liferay.training.messagebuslistener_1.0.0 [1075]
Title=Liferay DXP 7.1 Compatibility Matrix.pd
```

If you don't see the message displayed on your console right away, try restarting the platform.

#### Takeaways

We've demonstrated how to implement a Message Bus Listener to listen for messages generated by Liferay's applications. In addition to implementing a listener, you can create destinations and senders for your own applications. Remember, the message bus is often applied in situations where you do not want a long running process to affect the responsiveness of your application.
