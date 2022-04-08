---
description: 2 - OSGi Basics
title: Create an OSGi Service Using Declarative Services and Bndtools
order: 3
---

<h2 class="exercise">Exercises</h2>

## Create an OSGi Service Using Declarative Services and Bndtools

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Create an OSGi Service using [Bndtools](https://bndtools.org/)</li>
		<ul>
			<li>Create a Bnd OSGi Project</li>
			<li>Implement the service interface</li>
			<li>Implement the service implementation component</li>
			<li>Implement the command interpreter service component for testing</li>
			<li>Final code review</li>
			<li>Run and test the application in the Gogo Shell</li>
		</ul>
	</ul>
</div>

#### Create a Bnd OSGi Project

1. **Click** *File → New → Other* in the Dev Studio menu bar to launch the new project wizard.
1. **Enter** "bnd" in the search bar.
1. **Choose** the *Bnd OSGi Project* project type from *Bndtools* and click *Next*:
	<img src="../images/create-a-new-bnd-osgi-project.png" style="max-height: 30%;"/>
	> You'll get a warning that the "bnd workspace has not been configured". Bndtools uses this workspace as a local repository for its various dependencies. We'll need to create the workspace before we can create our OSGi project.
1. **Click** the *Create a workspace first* link on the dialog:
	<img src="../images/create-bnd-workspace-first.png" style="max-height: 30%;"/>
1. **Click** *Next*, leaving the defaults:
	<img src="../images/setup-bnd-workspace.png" style="max-height:30%;"/>
1. **Click** *Next*, leaving the defaults:
	<img src="../images/select-bnd-workspace-template.png" style="max-height:30%;"/>
1. **Click** *Finish* to close the Bnd workspace wizard:
	<img src="../images/preview-changes.png" style="max-height:30%;"/>
1. **Click** *Yes* to switch to the Bnd perspective and return to the Bnd OSGi project wizard.
1. Select __Component Development__ from the list of available project templates.
1. **Click** *Next*:
	<img src="../images/select-osgi-template.png" style="max-height:30%;"/>
1. **Use** the following information:
	* __Project Name:__ "osgi-service"
	* Uncheck *Derive from project name*.
	* __Java Package:__ "com.liferay.training.osgi.service"
	<img src="../images/bnd-project-name.png" style="max-height:32%;"/>
1. **Click** *Next*	leaving the default values and *Finish* to close the wizard:
	<img src="../images/java-settings.png" style="max-height:30%;"/>

#### Implement the Service Interface

The wizard generated an example class in the package `com.liferay.training.osgi.service`. We'll remove that and create our own class:

1. **Delete** the example class `com.liferay.training.osgi.service.Example.java`.
1. **Create** a new interface `com.liferay.training.osgi.service.ClockApi`.
1. **Implement** a single method called `getTime()` as follows:

```java
package com.liferay.training.osgi.service;

public interface ClockApi {
	public String getTime();
}
```

#### Implement the Service Implementation Component

Next, we'll create an implementation class for the interface. We will use the Declarative Services `@Component` annotation to declare our class component and use the `service` property to declare which service we are publishing.

1. **Create** a new class `com.liferay.training.osgi.service.ClockImpl.java`.
1. **Implement** the class as follows:

```java
package com.liferay.training.osgi.service;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(
	service = ClockApi.class
)
public class ClockImpl implements ClockApi {

	@Override
	public String getTime() {
		return new Date().toString();
	}
}
```

#### Implement the Command Interpreter Service Component

Last, we will create a Gogo Shell command to get the current time using a command called *telltime*. For that, we will create a new component.

1. **Create** a new class `com.liferay.training.osgi.service.ClockCommand.java`.
1. **Implement** the class in the following way:

```java
package com.liferay.training.osgi.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"osgi.command.function=telltime",
		"osgi.command.scope=training"
	},
	service = ClockCommand.class
)
public class ClockCommand {

	public void telltime() {
		System.out.println(clock.getTime());
	}

	@Reference
	private ClockApi clock;
}
```

#### Final Code Review

1. **Check** that the classes created in this exercise look like this:

```java
package com.liferay.training.osgi.service;

public interface ClockApi {
	public String getTime();
}
```

```java
package com.liferay.training.osgi.service;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(
	service = ClockApi.class
)
public class ClockImpl implements ClockApi {

	@Override
	public String getTime() {
		return new Date().toString();
	}
}
```

```java
package com.liferay.training.osgi.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"osgi.command.function=telltime",
		"osgi.command.scope=training"
	},
	service = ClockCommand.class
)
public class ClockCommand {

	public void telltime() {
		System.out.println(clock.getTime());
	}

	@Reference
	private ClockApi clock;
}
```

#### Run and Test the Application in the Gogo Shell

The Bndtools launcher will take care of compiling the `MANIFEST.MF` when the application is built. We'll enable Bndtools to automatically handle any missing requirements:

1. **Double-click** the `launch.bndrun` file on the Dev Studio package explorer.
1. **Add** the __osgi-service__ bundle to the *Run Requirements* (use the green *Add Bundle Requirement* button in the respective panel).
1. **Check** the *Auto resolve on save* checkbox.
1. **Select** __JavaSE 1.8__ as *Execution Environment* (in the *Core Runtime* panel).
1. **Save** the file.
1. **Click** the *Run OSGi* button to launch the application:
	<img src="../images/auto-resolve-requirements.png" style="max-height:35%"/>
1. **Enter** "telltime" in the console to test our application. You should get the time as a response:
	
<img src="../images/telltime.png" style="max-height:35%"/>
