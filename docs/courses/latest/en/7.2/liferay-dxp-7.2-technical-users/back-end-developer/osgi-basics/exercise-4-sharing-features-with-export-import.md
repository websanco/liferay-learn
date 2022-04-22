---
description: 2 - OSGi Basics
title: Sharing Features with Export-Import
order: 4
---

<h2 class="exercise">Exercises</h2>

## Sharing Features with Export-Import

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Create two bundles and see that we won't be able to consume the interface in one bundle from the other without sharing features between them:</li>
		<ul>
			<li>Create an API bundle</li>
			<li>Create a service interface in the API bundle</li>
			<li>Create an implementation bundle</li>
			<li>Create an implementation class</li>
			<li>Create an <code>Export-Package</code> header in the <code>MANIFEST.MF</code> of API bundle</li>
			<li>Create an <code>Import-Package</code> header in the <code>MANIFEST.MF</code> of the implementation bundle</li>
			<li>Resolve imports in the implementation bundle</li>
			<li>Run and test</li>
		</ul>
	</ul>
</div>

#### Create an API Bundle

1. **Click** *File → New → Other* in the Dev Studio menu bar to launch the new project wizard.
1. **Enter** "plugin" in the search bar.
1. **Choose** *Plug-in Project* for the project type and click *Next*:
	<img  src="../images/new-plugin-project-wizard.png" style="max-height:30%;" />
1. **Use** the following information for the first step:
	* __Project Name__:  "clock-api"
	* __Target Platform__: standard
	*  Make sure the radio button for an *OSGi framework* is selected.
	<img  src="../images/create-clock-api.png" style="max-height:32%;" />
1. **Use** the following information for the second step:
	* __ID:__ "com.liferay.training.clock.api"
	* __Name:__ "Clock API"
	* Uncheck the *Generate an activator...*
	<img  src="../images/clock-api-properties.png" style="max-height:35%;" />
1. **Leave** the defaults and click *Finish* to close the wizard.
	<img  src="../images/clock-api-wizard-finish.png" style="max-height:32%;" />

> When prompted, you can change perspectives to the *Plug-In* perspective.

#### Create an Interface in the API Bundle

1. **Create** a service interface `com.liferay.training.clock.api.ClockApi.java`.
1. **Implement** the `getTime()` in the interface as follows:

```java
package com.liferay.training.clock.api;

public interface ClockApi {

	public String getTime();

}
```

#### Create an Implementation Bundle

1. **Create** the implementation bundle exactly as you did the API bundle but using the following information (replacing the "api" with "impl"):
* __Project Name__: "clock-impl"
* __ID:__ "com.liferay.training.clock.impl"
* __Name:__ "Clock Impl"

#### Create the Implementation Class

Since we're just demonstrating how OSGi shares features, we don't need to make the implementation class a component. In real life, the implementation class would be annotated with `@Component`:

1. **Create** a new class `com.liferay.training.clock.impl.ClockImpl`.
1. **Implement** as follows:
	```java
	package com.liferay.training.clock.impl;

	import java.util.Date;

	import org.osgi.service.component.annotations.Component;

	public class ClockImpl implements ClockApi {

		@Override
		public String getTime() {
			return new Date().toString();
		}
	}
	```
1. **Resolve** missing imports.

> You'll notice that the ClockApi won't resolve. That's because we haven't shared the features yet.

#### Create the Export-Package Header in the API Bundle

To make our interface accessible to other bundles in the OSGi container, we have to use the `Export-Package` manifest header.

1. **Open** the `MANIFEST.MF` file in the API bundle.
1. **Switch** to *Source* view.
1. **Add** the `Export-Package` header to the end of the file and save the file. The file will now look like this:

```properties
Manifest-Version: 1.0
Bundle-ManifestVersion: 2
Bundle-Name: Clock API
Bundle-SymbolicName: com.liferay.training.clock.api
Bundle-Version: 1.0.0.qualifier
Automatic-Module-Name: com.liferay.training.clock.api
Bundle-RequiredExecutionEnvironment: JavaSE-1.8
Export-Package: com.liferay.training.clock.api
```

> Note that the source editor supports code-completion.

<br /><br /><br />

#### Create the Import-Package Header in the Implementation Bundle

To make the interface available for the implementation bundle classes, we must first import the API package into our bundle.

1. **Open** the `MANIFEST.MF` file of the implementation bundle.
1. **Switch** to *Source* view.
1. **Add** the `Import-Package` header to the end of the file and save the file. The file will now look like this:

```properties
	Manifest-Version: 1.0
	Bundle-ManifestVersion: 2
	Bundle-Name: Clock Impl
	Bundle-SymbolicName: com.liferay.training.impl
	Bundle-Version: 1.0.0.qualifier
	Automatic-Module-Name: com.liferay.training.impl
	Bundle-RequiredExecutionEnvironment: JavaSE-1.8
	Import-Package: com.liferay.training.clock.api
```

#### Resolve Imports

Now that we have shared the features, the `ClockApi` interface should be available for the `ClockImpl` class:

1. **Open** the class `com.liferay.training.clock.impl.ClockImpl`.
1. **Resolve** missing imports.

#### Run and Test

Now that we have the implementation bundle importing and implementing the API bundle, both bundles are ready to be deployed.

1. **Right-click** on the *clock-impl* project to open the context menu.
1. **Choose** *Run As → OSGi Framework*. The Gogo Shell should open in the console panel.
1. **Use** the `lb` command to check that our bundles are deployed and active:
	<img  src="../images/check-bundle-state.png" style="max-height:12%;" />
	* Let's test what would happen if the clock-api would not export the package `com.liferay.training.clock.api`.
1. **Stop** the OSGi container by entering `exit` in the Gogo Shell or clicking the red *Stop* icon on the bar.
1. **Remove** the `Export-Package` header from the API bundle's `MANIFEST.MF` file and save the file.
1. **Right-click** on the *clock-impl* project to open the context menu.
1. **Select** *Run As → OSGi Framework*.  You will get an error message:
	<img  src="../images/missing-constraint.png" style="max-height:20%;" />
1. **Click** *Continue* and type the *lb* command again to check the bundle state. You'll see that the implementation bundle is in the *installed* state:
	<img  src="../images/clock-impl-installed.png" style="max-height:12%;" />
1. **Stop** the OSGi container, restore the header, and try again.

#### Takeaways

We demonstrated that in order to access features from other bundles, they have to be shared. Here we used the *Import-Package - Export-Package* mechanism and, while more robust and complex, the *Provide-Capability - Require-Capability* (used in wiring the OSGi services) works conceptually the same way.

Multi-module projects can use Liferay development tools to take care of creating the feature-sharing headers automatically for you. But understanding the internal workings makes troubleshooting during development much easier.
