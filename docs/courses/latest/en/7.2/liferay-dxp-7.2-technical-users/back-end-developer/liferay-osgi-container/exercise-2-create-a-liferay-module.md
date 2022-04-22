---
description:  3 - Liferay's OSGi Container
title: Create a Liferay Module
order: 2
---

<h2 class="exercise">Exercises</h2>

## Create a Liferay Module

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Demonstrates the benefits of using Liferay module templates and Liferay Dev Studio IDE</li>
		<ul>
			<li>Create a Liferay module project using the MVC portlet project template</li>
			<li>Deploy and test the module</li>
		</ul>
	</ul>
</div>

#### Create a Liferay Module Project

Option 1: Use the Command Line Blade tools

1. **Open** the _Command Line_ shell in your Liferay Workspace `modules` folder.
1. **Run** the command:
```bash
blade create -t mvc-portlet -p com.liferay.training.portlet -c TrainingMVCPortlet training-portlet
```
1. **Run** Gradle refresh on the IDE.

Option 2: Use the Developer Studio Wizard

1. **Launch** the *Liferay Module Project* wizard in _Developer Studio_.
1. **Use** the following information for the first step:
	* __Project Name__: "training-portlet"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: mvc-portlet
1. **Click** *Next*, and use the following information in the second step:
	* __Component Class Name__: "TrainingMVCPortlet"
	* __Package Name__: "com.liferay.training.portlet"
1. **Click** *Finish* to close the wizard.

<br />

#### Deploy and Test the Module

1. **Drag** the `training-portlet` folder from the *Project Explorer*  onto the Liferay server in the *Servers* panel.
1. **Watch** the console. The module is successfully deployed when you see a message like:
	```
	2019-04-03 18:19:14.042 INFO  [pipe-start 975][BundleStartStopLogger:39] STARTED com.liferay.training.portlet_1.0.0 [975]
	```
1. **Open** your browser to http://localhost:8080 and sign in.
1. **Click** on the *Add* icon on the top right corner of the page to open the *Add Menu*.
1. **Find** the *Training* portlet in the *Sample* Widget category.
1. **Drag and drop** the portlet on the page:
	<img src="../images/hello-from-training.png" style="max-height: 35%"/>

<br />

When you make changes in the source code, the changes deploy almost instantly:

7. **Open** the file `src/main/resources/META-INF/resources/view.jsp`.
8. **Change** the contents of the file, for example:
	```java
	<%@ include file="/init.jsp" %>
	<p>
		<b><liferay-ui:message key="training.caption"/></b>
	</p>
	<p>Hello Liferay!</p>
	```
9. **Save** the file and refresh the page on your browser:
	<img src="../images/hello-liferay.png" style="max-height: 35%"/>
