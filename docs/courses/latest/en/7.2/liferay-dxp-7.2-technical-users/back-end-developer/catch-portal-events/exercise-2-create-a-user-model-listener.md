---
description: 12 - Catch Portal Events
title: Creating a User Model Listener
order: 2
---

<h2 class="exercise">Exercises</h2>

## Creating a User Model Listener

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay Module Project</li>
		<li>Create the model listener component</li>
		<li>Declare dependencies</li>
		<li>Implement the listener component</li>
		<li>Do a final code review</li>
		<li>Deploy and test</li>
	</ul>
</div>

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** the _Command Line_ shell in your Liferay Workspace `modules` folder.
1. **Run** the command:
```bash
blade create -t service -p com.liferay.training.model.listener -s com.liferay.portal.kernel.model.ModelListener -c UserPostUpdateModelListener user-post-update-model-listener
```
1. **Run** Gradle refresh on the IDE.
	
**Option 2: Use Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__: "user-post-update-model-listener"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: service
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "UserPostUpdateModelListener"
	* __Package Name__: "com.liferay.training.model.listener"
	* __Service Name__: "com.liferay.portal.kernel.model.ModelListener"
1. **Click** *Finish* to close the wizard.

#### Declare Dependencies

1. **Open** the `build.gradle` file and declare dependencies as follows:
  ```groovy
	compileOnly group: "javax.mail", name: "mail"
	```
  
#### Extend the BaseModelListener

Our generated class declaration implements the `ModelListener` interface. Implementing that directly would require a lot of boilerplate code in the class. We'll change the class declaration so that it extends the `BaseModelListener`. That way, we only need to implement the methods we need.

1. **Open** the class `com.liferay.training.model.listener.UserPostUpdateModelListener`.
1. **Change** the class declaration as follows:

```java
public class UserPostUpdateModelListener extends BaseModelListener<User>
``` 
  
#### Override the `onAfterUpdate()` Method  
  
1. **Open** the class `com.liferay.training.model.listener.UserPostUpdateModelListener`.
1. **Add** the MailService reference to the end of the class as follows:
	```java
	@Reference
	private MailService _mailService;
	```
1. **Resolve** missing imports.
1. **Override** the `onAfterUpdate()` method as follows:
	```java
	@Override
	public void onAfterUpdate(User model) throws ModelListenerException {
		try {
			MailMessage message = new MailMessage();

			message.setSubject("Security Alert: Account Settings");
			message.setBody("Liferay has detected that your account settings has been changed.");

			InternetAddress toAddress = new InternetAddress(model.getEmailAddress());
			InternetAddress fromAddress = new InternetAddress("do-not-reply@liferay.com");

			message.setTo(toAddress);
			message.setFrom(fromAddress);

			_mailService.sendEmail(message);
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}
	```
1. **Resolve** missing imports.

#### Final Code Review

The complete implementation files will look like this:

**build.gradle**
```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "javax.mail", name: "mail"
	compileOnly group: "org.osgi", name: "osgi.cmpn"
}
```

**UserPostUpdateModelListener.java**
```java
/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.training.model.listener;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	service = ModelListener.class
)
public class UserPostUpdateModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterUpdate(User model) throws ModelListenerException {
		try {
			MailMessage message = new MailMessage();

			message.setSubject("Security Alert: Account Settings");
			message.setBody("Liferay has detected that your account settings has been changed.");

			InternetAddress toAddress = new InternetAddress(model.getEmailAddress());
			InternetAddress fromAddress = new InternetAddress("do-not-reply@liferay.com");

			message.setTo(toAddress);
			message.setFrom(fromAddress);

			_mailService.sendEmail(message);
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}

	@Reference
	private MailService _mailService;
}
```

#### Deploy and Test

1. **Find** the file `exercise-files/12-catch-portal-events/fakeSMTP-2.0.jar` in the provided materials.
1. **Run** the JAR to start the server, using the port `25`.
	> You need to run the JAR with administrator rights. For example, with Ubuntu Linux:
	>```bash
	>sudo java -jar fakeSMTP-2.0.jar
	>```
1. **Deploy** the *user-post-update-model-listener* module to the Liferay server.
1. **Open** http://localhost:8080/ with your browser and sign in.
1. **Sign in** with any user.
1. **Go to** *Control Panel → Users → Users and Organizations*.
1. **Open** any account for editing and make any changes.
1. **Click** *Save*.
1. **Check** the FakeSMTP server for an email. There should be an email waiting.