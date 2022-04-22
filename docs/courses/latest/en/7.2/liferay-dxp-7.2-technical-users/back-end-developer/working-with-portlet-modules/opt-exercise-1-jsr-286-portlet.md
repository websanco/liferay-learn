---
description: 5 - Working with Portlet Modules
title: Implement a Basic JSR-286-Compliant Portlet
order: 1
---

<h2 class="exercise">Optional Exercise</h2>

## Implement a Basic JSR-286-Compliant Portlet

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Implement a basic JSR-286-compliant portlet that illustrates the most basic concepts of the Portlet Specification 2.0, like the portlet lifecycle, the portlet modes, and window states.</li>
		<ul>
			<li>Create a portlet WAR module project</li>
			<li>Implement the portlet class</li>
			<li>Configure the portlet class in <code>portlet.xml</code></li>
			<li>Build and deploy your JSR-286 portlet to Liferay</li>
			<li>Deploy the portlet and monitor the output in the server log</li>
			<li>Include a JSP for your response output</li>
		</ul>
	</ul>
</div>

#### Create a Portlet WAR Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** the command line shell in your Liferay Workspace `modules` folder.
1. **Run** command:
```bash
blade create -t war-mvc-portlet -p com.liferay.training.portlet.jsr286  jsr-286-portlet
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  "jsr-286-portlet"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: war-mvc-portlet
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: (*leave empty because we don't need component class*)
	* __Package Name__: "com.liferay.training.portlet.jsr286"
1. **Click** *Finish* to close the wizard.

#### Deploy and Test the Module

1. **Drag** the `jsr-286-portlet` folder from the *Project Explorer*  onto the Liferay server in the *Servers* panel.
1. **Watch** the console. The module is successfully deployed when you see a message like:
	```
	2019-04-03 18:19:14.042 INFO  [pipe-start 975][BundleStartStopLogger:39] STARTED com.liferay.training.portlet.jsr286 [975]
	```
1. **Open** your browser to http://localhost:8080 and sign in.
1. **Click** on the *Add* icon on the top right corner of the page to open the *Add Menu*.
1. **Find** the *jsr-286-portlet* portlet in the *Sample* Widget category.
1. **Drag and drop** the portlet on the page.

#### Implement the Portlet Class

1. **Create** a new class `com.liferay.training.portlet.jsr286.JSR286Portlet`.
	* Implement the `javax.portlet.Portlet` interface.
	<img src="../images/create-portlet-2.png" style="max-height:50%;"/>
1. **Add** status messages to the portlet lifecycle methods implementing the class as follows:
	```java
	package com.liferay.training.portlet.jsr286;

	import java.io.IOException;
	import java.io.PrintWriter;

	import javax.portlet.ActionRequest;
	import javax.portlet.ActionResponse;
	import javax.portlet.Portlet;
	import javax.portlet.PortletConfig;
	import javax.portlet.PortletException;
	import javax.portlet.RenderRequest;
	import javax.portlet.RenderResponse;

	public class JSR286Portlet implements Portlet {

		@Override
		public void init(PortletConfig config) throws PortletException {
			System.out.println("JSR286Portlet.init()");
		}

		@Override
		public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException, IOException {

			System.out.println("JSR286Portlet.processAction()");
		}

		@Override
		public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {

			System.out.println("JSR286Portlet.render()");

			PrintWriter printWriter = renderResponse.getWriter();		
			printWriter.write("Output from the JSR286Portlet's render() method.");

		}

		@Override
		public void destroy() {
			System.out.println("JSR286Portlet.destroy()");
		}
	}
	```
1. **Save** the file and refresh the page on your browser:

#### Configure the Portlet Class in portlet.xml

1. **Open** the `src/main/webapp/WEB-INF/portlet.xml` for editing.
1. **Replace** the contents of the `<portlet-class>` tag as follows:
```xml
<portlet-class>com.liferay.training.portlet.jsr286.JSR286Portlet</portlet-class>
```
1. **Save** the file and refresh the page on your browser:

<!-- Using auto-deploy in the steps above, but leaving this here in case it doesn't work

#### Build and Deploy Your JSR-286-Portlet to Liferay

1. **Find** the *jsr-286-portlet* project in the *wars* node of the *Gradle Tasks* view of Developer Studio.
1. **Expand** the project's `build` node in the tasks browser.
1. **Double-click** the `deploy` task to deploy your portlet to Liferay.
	<img src="../images/deploy-portlet-1.png" style="max-height:50%;" />
1. **Monitor** the console output of your development server. You should see the `JSR286Portlet.init()` message from your `init()` method implementation:	

```bash
2018-05-04 12:55:55.845 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:263] Processing jsr-286-portlet.war
2018-05-04 12:56:01.792 INFO  [fileinstall-/opt/training-workspace/bundles/osgi/war][BaseAutoDeployListener:43] Copying portlets for /opt/training-workspace/bundles/tomcat-9.0.6/temp/20180504125601791KEAVXNHT/jsr-286-portlet.war
2018-05-04 12:56:01.822 INFO  [fileinstall-/opt/training-workspace/bundles/osgi/war][BaseDeployer:876] Deploying jsr-286-portlet.war
2018-05-04 12:56:01.867 INFO  [fileinstall-/opt/training-workspace/bundles/osgi/war][BaseAutoDeployListener:50] Portlets for /opt/training-workspace/bundles/tomcat-9.0.6/temp/20180504125601791KEAVXNHT/jsr-286-portlet.war copied successfully
2018-05-04 12:56:02.261 INFO  [fileinstall-/opt/training-workspace/bundles/osgi/war][HotDeployImpl:226] Deploying jsr-286-portlet from queue
2018-05-04 12:56:02.262 INFO  [fileinstall-/opt/training-workspace/bundles/osgi/war][PluginPackageUtil:1003] Reading plugin package for jsr-286-portlet
04-May-2018 12:56:02.263 INFO [fileinstall-/opt/training-workspace/bundles/osgi/war] org.apache.catalina.core.ApplicationContext.log Initializing Spring root WebApplicationContext
2018-05-04 12:56:02.268 INFO  [fileinstall-/opt/training-workspace/bundles/osgi/war][PortletHotDeployListener:186] Registering portlets for jsr-286-portlet
JSR286Portlet.init()
2018-05-04 12:56:02.373 INFO  [fileinstall-/opt/training-workspace/bundles/osgi/war][PortletHotDeployListener:298] 1 portlet for jsr-286-portlet is available for use
2018-05-04 12:56:02.481 INFO  [fileinstall-/opt/training-workspace/bundles/osgi/war][BundleStartStopLogger:35] STARTED jsr-286-portlet_7.1.0.1 [700]
```

> The deploy task will first call the `compileJava` and `war` tasks to compile and package your project. It will then copy the generated `jsr-286-portlet.war` file from the project's `build/libs` directory to the server's `deploy` directory, where Liferay's AutoDeployListener will pick it up and deploy it to the server. The server will report the installation progress with several log messages.


#### Deploy the Portlet and Monitor the Output in the Server Log

After your portlet application has been installed on the server, you can deploy it to a portal page.

1. **Open** your browser to http://localhost:8080 and sign in.
1. **Click** on the plus button in the upper-right corner to add Widgets.
1. **Expand** the *Sample* category in the *Widgets* menu.
1. **Add** the *jsr-286-portlet* portlet onto the portal page.
	<img src="../images/deploy-portlet-2.png" style="max-height:100%;"/>

> After you have deployed the portlet onto a portal page, you can see the area occupied by your portlet on the page. By default, the portlet's name is displayed in the portlet-header section. The output from the render method is displayed in the portlet-body section of the portlet. Whenever you reload the page, you will see a `JSR286Portlet.render()` message in your server's log, indicating that your `render()` method has been called. When you navigate to another page of your platform or remove the portlet from the page, these messages are no longer displayed.

Congratulations! You have implemented and deployed a fully JSR-286 standard-compliant portlet!

-->

#### Include a JSP for Your Response Output

If you want to format your `render()` method's output, you can add HTML markup to the string passed to the PrintWriter's `write()` method. But because this approach quickly becomes cumbersome, the Portlet API provides the `PortletRequestDispatcher` interface, which defines an object that receives requests from the client and sends them to the specified resource, e.g., a JSP file on the server. The `PortletRequestDispatcher`'s `include()` method allows you to include the contents of a resource (your JSP file) in the response. In essence, this method enables programmatic server-side includes.

Let's modify the `render()` method to use the `view.jsp` file:

1. **Open** the portlet class `com.liferay.training.portlet.jsr286.JSR286Portlet.java`.
1. **Implement** the `render()` method as follows:
```java
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {
		System.out.println("JSR286Portlet.render()");
	
		// PrintWriter printWriter = renderResponse.getWriter();
		// printWriter.write("Output from the JSR286Portlet's render() method.");
	
		String path = "/view.jsp";
	
		PortletSession portletSession = renderRequest.getPortletSession();
		PortletContext portletContext = portletSession.getPortletContext();
		PortletRequestDispatcher portletRequestDispatcher = portletContext.getRequestDispatcher(path);
	
		if (portletRequestDispatcher == null) {
			System.err.println(path + " is not a valid include");
		}
		else {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}
```
1. **Resolve** the imports and save the file.
1. **Run** the Gradle `deploy` task to redeploy the portlet.
1. **Refresh** the page where you have installed the _JSR-286-portlet_ portlet. 

The render method now includes and outputs the `view.jsp` generated by the wizard:
<img src="../images/deploy-portlet-3.png" style="max-height:100%;"/>

#### Bonus Exercise: Set a Render Parameter

In `view.jsp`, you can make use of the tag libraries, simplifying the implementation of the portlet view. We'll now use the standard portlet tag library to create a render url, set a render parameter, and explore the behavior of our portlet in the render phase:

1. **Implement** the file `webapp/view.jsp` as follows:
	```java
	<%@ include file="/init.jsp" %>

	<%
		String cssStyle = "";

		String backgroundColor = renderRequest.getParameter("backgroundColor");

		if (backgroundColor != null && !backgroundColor.isEmpty()) {
			cssStyle = "background-color: " + backgroundColor + ";";
		}
	%>
	<div style="<%= cssStyle %>">

		<p class="caption">
			<liferay-ui:message key="jsr-286-portlet.caption" />
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
	
> The taglib includes are defined in the `init.jsp`, which the `view.jsp` includes. Both files have been created by the wizard when you created the project. The Tag Library Descriptor files can be found in the `src/main/webapp/WEB-INF/tld` folder of your project.

You can now explore the portlet's behavior when it is deployed multiple times on the same page:

1. **Deploy** the refactored portlet to the Liferay server.
1. **Add** the portlet two or more times onto your page. 
1. **Try** clicking buttons on different instances.

When you click the button in one instance, the background color of the respective portlet changes, while all other instances remain the same:

<img src="../images/render-parameter-1.png" style="max-height:100%;"/>

