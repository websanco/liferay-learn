---
description: 12 - Catch Portal Events
title: Creating a Post Login Event Listener
order: 1
---

<h2 class="exercise">Exercises</h2>

## Creating a Post Login Event Listener

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay Module Project using the service template</li>
		<li>Declare dependencies</li>
		<li>Implement the Event Listener Class</li>
		<li>Do a final code review</li>
		<li>Deploy and test</li>
	</ul>
</div>

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** the _Command Line_ shell in your Liferay Workspace `modules` folder.
1. **Run** the command:
```bash
blade create -t service -p com.liferay.training.login.events.post -s com.liferay.portal.kernel.events.LifecycleAction -c PostLoginEventListener post-login-event-listener
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  post-login-event-listener
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: service
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: PostLoginEventListener
	* __Package Name__: com.liferay.training.login.events.post
1. **Click** on the browse button next to the *Service Name* field.
	* Enter "\*.LifecycleAction" in the *Select Service Name*.
	* Select `com.liferay.portal.kernel.events.LifecycleAction` from the list.
1. **Click** the green plus button to add a property. 
	* Enter "key" for the *Name*.
	* Enter "login.events.post" for the *Value*.
	* Press the _Enter/Return_ key to confirm your property.
1. **Click** *Finish* to close the wizard.

#### Declare Dependencies

Since we want to send an email in our `PostLoginEventListener`, we have to add `javax.mail` to our dependencies:

1. **Open** the `build.gradle` file and add the dependency as follows:
```groovy
	compileOnly group: "javax.mail", name: "mail"
```

#### Implement the Event Listener Class

1. **Open** the class `com.liferay.training.login.events.post.PostLoginEventListener`.
1. **Add** the service references to the end of the class as follows:
	```java
	@Reference
	protected MailService _mailService;

	@Reference
	protected UserService _userService;
	```
3. **Resolve** missing imports.
1. **Implement** the `processLifecycleEvent()` method as follows:
	```java
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) 
			throws ActionException {
		try {

			System.out.println("processLifecycleEvent()");

			User user = _userService.getCurrentUser();

			MailMessage message = new MailMessage();

			message.setSubject("Security Alert");
			message.setBody("Liferay has detected that you logged in at " + user.getLastLoginDate());

			InternetAddress toAddress = new InternetAddress(user.getEmailAddress());
			InternetAddress fromAddress = new InternetAddress("do-not-reply@liferay.com");

			message.setTo(toAddress);
			message.setFrom(fromAddress);

			_mailService.sendEmail(message);

		} catch (PortalException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}
	```
5. **Add** `"key=login.events.post"` to the Component's property configuration:
```java
@Component(
	immediate = true,
	property = {
		"key=login.events.post"
	},
	service = LifecycleAction.class
)
```

#### Final Code Review

1. **Check** that the implementation files look like this:

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "javax.mail", name: "mail"
	compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"
}
```

```java
package com.liferay.training.login.events.post;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"key=login.events.post"
	},
	service = LifecycleAction.class
)
public class PostLoginEventListener implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) 
			throws ActionException {
		try {

			System.out.println("processLifecycleEvent()");

			User user = _userService.getCurrentUser();

			MailMessage message = new MailMessage();

			message.setSubject("Security Alert");
			message.setBody("Liferay has detected that you logged in at " + user.getLastLoginDate());

			InternetAddress toAddress = new InternetAddress(user.getEmailAddress());
			InternetAddress fromAddress = new InternetAddress("do-not-reply@liferay.com");

			message.setTo(toAddress);
			message.setFrom(fromAddress);

			_mailService.sendEmail(message);

		} catch (PortalException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		}

	}

	@Reference
	protected MailService _mailService;

	@Reference
	protected UserService _userService;
}
```

#### Deploy and Test

1. **Find** the file `exercise-files/12-catch-portal-events/fakeSMTP-2.0.jar` in the provided materials.
1. **Run** the JAR to start the server, using the port `25`.
	> You need to run the JAR with administrator rights. For example, with Ubuntu Linux:
	>```bash
	>sudo java -jar fakeSMTP-2.0.jar
	>```
1. **Deploy** the *post-login-event-listener* module to the Liferay server.
1. **Open** http://localhost:8080/ with your browser.
1. **Sign in** with any user.
1. **Check** the FakeSMTP server for an email.
