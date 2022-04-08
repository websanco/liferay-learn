---
description: 5 - Working with Portlet Modules
title: Create a Liferay MVC Portlet Module
order: 1
---

<h2 class="exercise">Exercises</h2>

## Create a Liferay MVC Portlet Module

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Implement a basic portlet using Liferay's MVCPortlet</li>
		<ul>
			<li>Create a Liferay MVC portlet module</li>
			<li>Deploy the portlet</li>
			<li>Implement <code>view.jsp</code></li>
			<li>Create a form in <code>view.jsp</code></li>
			<li>Implement an MVC Action Command for handling the form submission</li>
			<li>Test the module</li>
		</ul>
	</ul>
</div>

<div class="note">
The MVCPortlet class can be found here: <a href="https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java">https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java</a>
</div>

#### Create a Liferay MVC Portlet Module

**Option 1: Use the Command Line Blade tools**

1. **Open** the command line shell in your Liferay Workspace `modules` folder.
1. **Run** command:
```bash
blade create -t mvc-portlet -p com.liferay.training.portletmodule.portlet -c SimpleMVCPortlet mvc-portlet-module
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  "mvc-portlet-module"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: mvc-portlet
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "SimpleMVCPortlet"
	* __Package Name__: "com.liferay.training.portletmodule.portlet"
1. **Click** *Finish* to close the wizard.

#### Deploy the Portlet

1. **Drag** the *mvc-portlet-module* module onto your running server in the *Servers* view.
1. **Open** your browser to http://localhost:8080 and sign in.
1. **Click** on the plus button in the upper-right corner to add Widgets.
1. **Expand** the *Sample* category in the *Widgets* menu.
1. **Add** the *SimpleMVC* to the page. The page should look like this:

<img src="../images/deploy-mvc-portlet-1.png" style="max-height:100%;" />

#### Implement the view.jsp

1. **Open** the `src/main/resources/META-INF/resources/view.jsp` and implement as follows:
	```java
	<%@ include file="/init.jsp" %>

	<%
		String cssStyle = "";
		String backgroundColor = renderRequest.getParameter("backgroundColor");
		if (backgroundColor != null && !backgroundColor.isEmpty()) {
				cssStyle = "background-color: " + backgroundColor + ";";
		}
	%>

	<div style="<%= cssStyle %>;">
		<p class="caption">
			<liferay-ui:message key="simplemvc.caption" />
		</p>
	</div>

	<portlet:renderURL var="viewRedURL">
		<portlet:param name="backgroundColor" value="red"/>
	</portlet:renderURL>
	<portlet:renderURL var="viewYellowURL">
		<portlet:param name="backgroundColor" value="yellow"/>
	</portlet:renderURL>

	<div class="btn-group">
		<a class="btn btn-default" href="<%= viewRedURL %>">Set red</a>
		<a class="btn btn-default" href="<%= viewYellowURL %>">Set yellow</a>
	</div>
	```
1. **Save** the file and refresh the page in your browser.

> The `view.jsp` is configured as the default view-template in the portlet component's properties.

#### Create a Form in view.jsp

Add a form for our color test:

1. **Add** the form to the end of the `view.jsp`:

```java
<portlet:actionURL name="handleForm" var="actionURL"/>

<aui:form action="<%= actionURL %>" style="margin-top: 2rem;">
	<aui:select name="backgroundColor">
		<aui:option label="aqua"/>
		<aui:option label="gray"/>
		<aui:option label="lime" />
		<aui:option label="olive" />
		<aui:option label="silver" />
	</aui:select>
	<aui:button-row>
		<aui:button type="submit" value="send"/>
	</aui:button-row>
</aui:form>
```

#### Implement an MVC Action Command

Create an MVC Action Command component to catch the form submit:

1. **Create** a class `com.liferay.training.portlet.portletmodule.action.HandleFormMVCActionCommand`.
1. **Implement** the class as follows:

```java
package com.liferay.training.portlet.portletmodule.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.training.portletmodule.portlet.constants.SimpleMVCPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SimpleMVCPortletKeys.SimpleMVC,
		"mvc.command.name=handleForm"
	},
	service = MVCActionCommand.class
)
public class HandleFormMVCActionCommand implements MVCActionCommand {
	@Override
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		_handleActionCommand(actionRequest);

		return true;
	}

	private void _handleActionCommand(ActionRequest actionRequest) {

		System.out.println("HandleFormMVCActionCommand.doProcessAction()");

		String backgroundColor = actionRequest.getParameter("backgroundColor");

		System.out.println("backgroundColor = " + backgroundColor);

	}
}
```

#### Test the Module

1. **Refresh** the portal page.
1. **Click** *Send* on the exercise portlet.
1. **Watch** the console log. You should see messages from the MVC Action Command component:

```bash
2019-04-16 21:33:59.602 INFO  [pipe-start 1074][BundleStartStopLogger:39] STARTED com.liferay.training.portletmodule.portlet_1.0.0 [1074]
HandleFormMVCActionCommand.doProcessAction()
backgroundColor = aqua
```