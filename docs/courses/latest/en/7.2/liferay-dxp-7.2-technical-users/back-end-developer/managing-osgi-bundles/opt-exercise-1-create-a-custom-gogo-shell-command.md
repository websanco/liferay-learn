---
description: 4 - Managing OSGi Bundles
title: Create a Custom Gogo Shell Command
order: 1
---

<h2 class="exercise">Optional Exercise</h2>

## Create a Custom Gogo Shell Command

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Create a new Gogo Shell command to get the number of users and groups in the system:</li>
		<ul>
			<li>Create a new Liferay service module project using the *service* template</li>
			<li>Implement the OSGi command component</li>
			<li>Final code review</li>
			<li>Deploy and test</li>
		</ul>
	</ul>
</div>

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** command line shell in your Liferay Workspace `modules` folder.
1. **Run** command:
```bash
blade create -t service -v 7.1 -p com.liferay.training.gogo.command -c TrainingCommand -s Object training-gogo-command
```
1. **Run** Gradle refresh on the IDE.
1. **Add** the following component properties
	* osgi.command.scope=blade
	* osgi.command.function=portalstats		
	
**Option 2: Use the Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  "training-gogo-command"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: service
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "TrainingCommand"
	* __Package Name__: "com.liferay.training.gogo.command"
	* __Service Name__: "Object"
1. **Click** the green plus button to add two properties:
	* "osgi.command.scope" : "blade"
	* "osgi.command.function" : "portalstats"		
1. **Click** *Finish* to close the wizard

While being a service, this component is not meant to be referenced from other classes and doesn't have to implement an interface:

1. **Open** the class `com.liferay.training.gogo.command.TrainingCommand`
1. **Remove** the `implements` keyword and the interface from the class declaration.
	You class will look like this:
	```java
	package com.liferay.training.gogo.command;
	
	import org.osgi.service.component.annotations.Component;
	
	/**
	 * @author liferay
	 */
	@Component(
	        immediate = true,
	        property = {
				"osgi.command.scope=blade",
				"osgi.command.function=portalstats"
	        },
	        service = Object.class
	)
	public class TrainingCommand {
	
	        // TODO enter required service methods
	
	}
	```
1. **Organize** missing imports.

#### Implement the OSGi command component

1. **Add** references to [GroupLocalService](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-kernel/src/com/liferay/portal/kernel/service/GroupLocalService.java) and [UserLocalService](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-kernel/src/com/liferay/portal/kernel/service/UserLocalService.java) to the end of the class:
	```java
	@Reference
	GroupLocalService _groupLocalService;
	
	@Reference
	UserLocalService _userLocalService;
	```
1. **Implement** the `portalstats()` method as follows:
	```java
		public void portalstats() {
			System.out.println(
				"# of users: " + _userLocalService.getUsersCount());
			System.out.println(
				"# of groups: " + _groupLocalService.getGroupsCount());
		}
	```
1. **Organize** imports.

#### Final Code Review

The complete `com.liferay.training.gogo.command.api.TrainingCommand` will look like this:

```java
package com.liferay.training.gogo.command;

import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author liferay
 */
@Component(
        immediate = true,
        property = {
			"osgi.command.scope=blade",
			"osgi.command.function=portalstats"
        },
        service = Object.class
)
public class TrainingCommand {

	public void portalstats() {
		System.out.println(
			"# of users: " + _userLocalService.getUsersCount());
		System.out.println(
			"# of groups: " + _groupLocalService.getGroupsCount());
	}
	
	@Reference
	GroupLocalService _groupLocalService;
	
	@Reference
	UserLocalService _userLocalService;
}
```

#### Deploy and Test

1. **Deploy** the module.
1. **Login** to Gogo shell
1. **Test** the "portalstats" command:
```bash
g! blade:portalstats
# of users: 2
# of groups: 11
```

