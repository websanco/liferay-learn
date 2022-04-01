---
description: 8 - Customize the User Interface
title: Customize the Announcements Portlet JSP Using a Fragment Module
order: 2
---

<h2 class="exercise">Exercises</h2>

## Customize the Announcements Portlet JSP Using a Fragment Module

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create the Liferay Module Project fragment</li>
		<li>Implement the JSP</li>
		<li>Deploy the module</li>
		<li>Verify the results</li>
	</ul>
</div> 

#### Create the Liferay Module Project Fragment

**Option 1: Use the Command Line Blade tools**

1. **Open** the _Command Line_ shell in your Liferay Workspace `modules` folder.
1. **Create** the fragment module project with:
```bash
blade create -t fragment announcements-jsp-override -h com.liferay.announcements.web -H 4.0.8
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use the Developer Studio Wizard**

1. **Start** a new *Liferay Module Project Fragment* project wizard.
1. **Use** the following information for the first step:
	* __Project Name__: "announcements-jsp-override"
	* __Build Type__: Gradle
1. **Click** *Next* and use the following information for the second step:
	* __Select Host OSGi Bundle Class Name__: com.liferay.announcements.web-[VERSION].jar.
1. **Click** the Liferay logo button to add files from the OSGi bundle.
	* Add `META-INF/resources/announcements.view.jsp`.
	<img src="../images/announcements-jsp-override-wizard.png" style="max-height:30%;"/>
1. **Click** *Finish* to close the wizard.

#### Implement the JSP

1. **Open** the file `src/main/resources/META-INF/resources/announcements/view.jsp`.
1. **Implement** as follows:

```java
<%@ include file="/announcements/init.jsp" %>

<h2>This is the announcements view.jsp overridden by a Fragment Module.</h2>

<%-- The following allows you to include the original JSP that was overridden: --%>

<liferay-util:include
	page="/announcements/view.original.jsp"
	servletContext="<%= application %>"
/>
```

#### Deploy the Module

When you deploy a fragment module, you won't see a "STARTED" message for this module in the log because fragment bundles don't get to the *ACTIVE* state. However, the host bundle will restart:

1. **Deploy** the *announcements-jsp-override* module to the Liferay server. 
1. **Watch** the log for the Announcements portlet. You should see it restarting: 
	```bash
	2019-04-15 14:30:40.858 INFO  [Refresh Thread: Equinox Container: 35d41383-88fd-461a-b88f-b22dd8dfb6af][BundleStartStopLogger:42] STOPPED com.liferay.announcements.web_4.0.0 [251]
	2019-04-15 14:30:41.180 INFO  [Refresh Thread: Equinox Container: 35d41383-88fd-461a-b88f-b22dd8dfb6af][BundleStartStopLogger:39] STARTED com.liferay.announcements.web_4.0.0 [251]
	```

#### Verify and Test

1. **Open** your browser to http://localhost:8080 and sign in.
1. **Add** the *Announcements* portlet onto the page.
	*  You should see the message in the overridden JSP file:

<img src="../images/announcements-overridden.png" style="max-height:100%;"/>
