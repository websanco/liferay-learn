---
description: 5 - Working with Portlet Modules
title: Java Standard Portlet
order: 2
---

## Java Standard Portlet

Liferay's user interface relies on portlets but not strictly on the standard portlet specification. Although there are many ways of building out your application's user interface, using the MVC pattern along with portlets as the implementation of the Controller and View Layer is still the essence of Liferay development. 

Understanding key portlet concepts and the portlet lifecycle is fundamental to learning back-end development in Liferay and fundamental to customizing Liferay's controller layer, discussed in *Module 11 - Override Controller Actions*.

> In Liferay 7.1, the portlet applications were named *Widgets*. Although that term is used everywhere in the user interface, internally, the applications still rely on the portlet paradigm. Portlet is the term you as a back-end developer will encounter when researching Liferay's source code. In the context of this material, we'll be using the term "portlet" unless otherwise needed.

#### What is a Portlet?

A portlet is a web component or application that produces an HTML fragment of a page. Generally, any application that has a user interface in Liferay uses a portlet. To summarize, a portlet is:

* An application running in a portlet runtime environment called a __portlet container__
* An application in Liferay that has a __user interface__ (not all user interfaces are portlets, though)
* An application that follows the standards governed by the Portlet Specification JSR-168: https://www.jcp.org/en/jsr/detail?id=168, JSR-286: https://www.jcp.org/en/jsr/detail?id=286, or JSR-362https://www.jcp.org/en/jsr/detail?id=362.

The diagram below demonstrates a user's interaction with portlets on a portal page. When users come to a page in Liferay, they can see the portlets on the page. When a user interacts with a portlet, a request may be sent to Liferay's portlet container, which handles the request and sends a response back. Each one of the portlets on the page is contained and managed by the portlet container in Liferay.

<br />

<img src="../images/what-is-a-portlet-1.png" style="max-height:40%;" />

<br />

Portlets typically work as a bridge between the user interface and service layer. For example, you submit personal data from the user interface, and the portlet processes it to be sent to the service layer. The service layer then sends feedback, which the portlet provides back to the user interface.

<br />

<img src="../images/what-is-a-portlet-2.png" style="max-height:40%;" />

<br />

On a Liferay page, the UI elements we see are implemented as portlets. The Blogs, Alerts, Hello World, and RSS Publisher are all portlets on the page:

<img src="../images/portlets-on-a-page.png" style="max-height:45%;" />

Elements within the various parts of the _Product Menu_ and _Control Panel_ are also implemented as portlets. Creating Web Content for example, happens through the Web Content portlet:

<img src="../images/portlets-in-control-panel.png" style="max-height:32%;" />

#### Building Blocks of a Standard Portlet

The first key element of a standard Java portlet is a __deployment descriptor__. Two deployment descriptors are necessary:

* __web.xml__:  used to declare servlet filters, taglibs, and other configuration parameters
* __portlet.xml__: used to declare various properties like the portlet class

The second key element is the __portlet class__ (or Java class) that extends a specific implementation of the Portlet Specification. The default code implementation of a Java Standard [javax.portlet.Portlet](https://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/Portlet.html) interface is the [javax.portlet.GenericPortlet](https://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/GenericPortlet.html).

> When creating a Java Standard Portlet, you always extend `javax.portlet.GenericPortlet`.

The third key element of a Java standard portlet is __JSP__ files. Most of the time, when we want to see something displayed in a portlet application, we use JSP files. Portlets, by default, come with different modes. With each mode, a JSP can be displayed based on the mode that is selected.

Now that we have the building blocks of a portlet, we're going to take a look at the key functional concepts.

#### Portlet Lifecycle

The Portlet Lifecycle defines how portlets should handle specific types of requests. The portlet container is the one that will handle the requests that come in and determine what phase of the portlet's lifecycle should be invoked. 

There are six phases of the portlet lifecycle:

* Init Phase
* Action Phase
* Event Phase
* Render Phase
* Resource Serving Phase
* Destroy Phase

Each phase of the Portlet Lifecycle has a corresponding method in `javax.portlet.GenericPortlet`. 

<img src="../images/portlet-lifecycle.png" style="max-height:35%;" />

<br />

#### Init Phase

The Init phase of the portlet is called when the portlet class initializes, or, in other words, when the portlet is deployed. During the Init phase, initialization parameters are read. This phase only occurs once.

#### Render Phase

The Render phase is used to generate an __HTML fragment__. In order to have something displayed on a JSP, this phase has to be invoked. If the page ever changes or is refreshed, all the portlets on a page will have to go through the Render phase again.

This phase along with the action phase is part of what's known as the __request response lifecycle__. When interacting with this phase, there are wrapper objects, __RenderRequest__ and __RenderResponse__, that are used to store and retrieve various attributes that help with our development and control over what's happening throughout the Render phase and other phases.

In the `javax.portlet.GenericPortlet`, this phase is handled by the `render()` method.

#### Calling the Render Phase Example

The Render phase is automatically called, for example, when a portlet is added to a page, when the page is loaded or reloaded or after the Action phase is finished. When we want to display different JSPs, we usually manually call the Render phase.

In the example below, we first create a Render URL and assign that to a link, so that when the link is clicked, the Render phase is invoked:

```html
<portlet:renderURL var="viewEntryUrl">
	<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
</portlet:renderURL>

<a href="<%= viewEntryUrl">Click here to view the entry</a>
```

When the Render URL is invoked, it creates a URL. The URL contains parameters like which portlet is invoking the phase, what lifecycle phase is being invoked, and other information about the portlet:

<br />

```
http://localhost:8080/web/guest/home?p_p_id=com_liferay_blogs_web_portlet_BlogsPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_blogs_web_portlet_BlogsPortlet_entryId=34403
```

<br />

In the `render()` method of a portlet's Java class, we can retrieve parameters that were set in the JSP and stored in the __renderRequest__, and then, for example, call services using those parameters.

In the example below, we first retrieve the *entryId* parameter, then fetch the entry, and, lastly, set the object back to the request and send it over to the JSP to be displayed:

<br />

```java
@Override
public void render(RenderRequest renderRequest, RenderResponse renderResponse) 
	throws IOException, PortletException {

	String entryId = ParamUtil.getString(renderRequest,"entryId");

	MyEntry entry = myService.getEntry(entryId);
	
	renderRequest.setAttribute("entry", entry);   

	super.render(renderRequest, renderResponse);
}
```

#### Render Phase Flow

The Render phase is always invoked for all portlets on the page after the Action phase (which may or may not exist). It’s important to note that the order in which the Render phase of the portlets in a page gets executed is not guaranteed by the portlet specification. Liferay has an extension to the specification through the attribute `render-weight`. Portlets with a higher render weight will be rendered before those with a lower weight.

After all the portlets go through the Render phase, the collection of the HTML fragments are assembled together in the render response, containing the Markup for the page:

<img src="../images/render-phase-flow.png" style="max-height:40%;" />

#### Action Phase

The Action phase is used to respond to actions a user performs. Typically, it's used to handle an HTML form submit.

The corresponding `javax.portlet.GenericPortlet` method  is `processAction()`. Like the Render phase, there are __ActionRequest__ and __ActionResponse__ objects available. 

During the Action phase, __events__ can be triggered to invoke the Event phase. After the Action phase, the Render phase is called and all the portlets on the page will render.

#### Calling Action Phase Example

To call the Action phase, an ActionURL has to be created. In the example below, clicking on a _Submit_ button invokes the Action phase. The action `name` attribute makes it possible to handle different actions in a single `processAction()` method:

```html
<portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />

<aui:form action="<%= editEntryURL %>" method="post" name="fm">
	<aui:input name="entryId" type="hidden" value="<%= entryId %>" />
	<aui:input name="title" type="text" />
</aui:form>
```

As with the Render URL, the actionURL will generate a literal URL containing control parameters:

```
http://localhost:8080/web/guest/home?p_p_id=com_liferay_blogs_web_portlet_BlogsPortlet&p_p_lifecycle=1&p_p_state=maximized&p_p_mode=view&_com_liferay_blogs_web_portlet_BlogsPortlet_javax.portlet.action=%2Fblogs%2Fedit_entry&_com_liferay_blogs_web_portlet_BlogsPortlet_entryId=34403&_com_liferay_blogs_web_portlet_BlogsPortlet_cmd=update&_com_liferay_blogs_web_portlet_BlogsPortlet_title=Working+Example&p_auth=7uOmzvxB
```

In the example below, we retrieve parameters from the ActionRequest object and update the entry title by doing a service call:

```java
@Override
public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
	throws IOException, PortletException {

	String entryId = ParamUtil.getString(actionRequest,"entryId");
	String title = ParamUtil.getString(actionRequest,"title");
	
	myService.updateEntryTitle(entryId, title)

	super.processAction(actionRequest, actionResponse);
}
```

#### Action Phase Flow

Once a user makes an action request, the portlet container will invoke the Action phase for the appropriate portlet. Once the Action phase is finished, the Render phase is invoked, and all the portlets on a page will re-render:

<img src="../images/action-phase-flow.png" style="max-height:40%;" />

#### Event Phase

The Event phase was introduced as a way for portlets to communicate with each other. The concept of portlets talking to each other is called __Inter-Portlet Communication__ (IPC).

The Event phase is made up of publishers and receivers. For both publishers and receivers, you have to configure the supported events. Event publishing happens in the Action phase, and event processing happens in the Event phase. All portlets configured to process an event will process the event once the event is set.

Once the Event phase is finished, the Render phase will be called. The corresponding method for the Event phase in `javax.portlet.GenericPortlet` is `processEvent()`.

Below is an example of a sender publishing an event `message;http://www.liferay.com` and a receiver receiving it:

**Sender**
<pre style="font-size: 0.65em;"><code class="java"> 
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Event Publisher Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LifecyclePortletKeys.EVENT_PUBLISHER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-publishing-event=message;http://www.liferay.com",
	},
	service = Portlet.class
)
public class EventPublisherPortlet extends MVCPortlet {

	@Override
	public void processAction(ActionRequest actionRequest,
		ActionResponse actionResponse)
			throws IOException, PortletException {

		String message = ParamUtil.getString(actionRequest, "message");

		QName qName = new QName("http://www.liferay.com", "message");
		actionResponse.setEvent(qName, message);

		super.processAction(actionRequest, actionResponse);
	}
}
</code></pre>

**Receiver**
<pre style="font-size: 0.65em;"><code class="java">  
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Event Receiver Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LifecyclePortletKeys.EVENT_RECEIVER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-processing-event=message;http://www.liferay.com",
	},
	service = Portlet.class
)
public class EventReceiverPortlet extends MVCPortlet {

	@ProcessEvent(qname = "{http://www.liferay.com}message")
	public void handleProcesseuserEmailAddressEvent(EventRequest request,
		EventResponse response)
			throws javax.portlet.PortletException, java.io.IOException {

		Event event = request.getEvent();
		String message = (String) event.getValue();

		response.setRenderParameter("messageReceived", message);
	}
}
</code></pre>

#### Event Phase Flow

A user invokes the Action phase of Portlet A. In the `processAction()` method, the event is set, and Portlet B's Event phase will be invoked. Once the Event phase of Portlet B is finished, all of the portlets on the page will enter the Render phase.

<br />

<img src="../images/event-phase-flow.png" style="max-height:40%;" />

#### Resource Serving Phase

The Resource Serving phase will provide a way to serve resources to the client without entering the Action or Render phase. Often, the Resource Serving phase is called with Ajax for: 

* Autocompletion of a search field
* Refreshing the news content area without a page refresh
* Doing any background operation without a page refresh

Below is an example of a Resource Serving phase being called from a JSP page via Ajax and handled in the `serveResource()` method in the portlet class. Once the method is executed without error, we'll parse the JSON from the response and display it:

**JSP**

```html 
<liferay-portlet:resourceURL var="resourceRequestURL" >
	<liferay-portlet:param name="entryId" value="<%=entryId %>" />
</liferay-portlet:resourceURL>

<a href="#" onclick="fetchData()">Fetch Data</a>

<div id="data-element"></div>

<aui:script>
	function fetchData() {
		AUI().use('aui-io-request', function(A) {
			A.io.request('<%= resourceRequestURL %>', {
				method: 'post',
				on: {
					success: function() {

						var data = JSON.parse(response.responseText);
						A.one('#data-element').html(data);
					}
				}
			});
		});
	}
</aui:script>
```

**Portlet class**
<pre style="font-size: 0.55em;"><code class="java">  
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Lifecycle Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LifecyclePortletKeys.LIFECYCLE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
	},
	service = Portlet.class
)
public class LifecyclePortlet extends GenericPortlet {

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {

		String entryId = ParamUtil.getString(resourceRequest, "entryId");

		String entryData = getEntryData(entryId);

		JSONObject json = JSONFactoryUtil.createJSONObject();
		json.put("entryData", entryData);

		JSONPortletResponseUtil.writeJSON(
				resourceRequest, resourceResponse, json);
	}
	...
</code></pre>

#### Destroy Phase

When a portlet is undeployed, the Destroy phase is invoked to do any clean-up before the portlet is removed. Resources are cleaned up and the portlet itself is released from the portlet container to be eligible for garbage collection. The method that is called when the Destroy phase is invoked is `destroy()`.

#### Portlet Modes

The portlet specification makes it possible for the portlet to have different perspectives or modes. Each mode, when selected, will render a JSP configured for that mode. The typical naming convention is `view.jsp` for the VIEW mode, `edit.jsp` for the EDIT mode, and `help.jsp` for the HELP mode.

There are three standard modes, each with their respective uses:

* VIEW: Standard mode used as a general point of view
* EDIT: Configuration mode used to customize the behavior of the portlet
* HELP: Displays portlet’s help information

Liferay also provides custom modes that can be leveraged:

* About
* Config
* Edit default
* Edit guest
* Print
* Preview

> The only required mode is the `VIEW` mode.

`javax.portlet.GenericPortlet` has a handler method for each of the standard modes. For the VIEW mode, `GenericPortlet` will call `doView()`, the EDIT mode will call `doEdit()`, and the HELP mode will call `doHelp()`. These methods are called from `render()` via  `doDispatch()`, which figures out which portlet mode is selected. Once the portlet mode is selected, the corresponding method is called, but it's up to us as the developers to customize `doView`, `doEdit`, and `doHelp` if we are creating a Java standard portlet.

There are two different ways you can set portlet modes, either in the JSP or in the Portlet class:

**JSP**
```html
<portlet:renderURL portletMode="VIEW" var="renderURL" />
```

**Portlet class**
```java
actionResponse.setPortletMode(PortletMode.EDIT);
```

#### Window States

Window State defines how much space the portlet is going to take up on a page once it renders. There are three window states that the Portlet Specification defines: 

* __NORMAL__: default window state where this portlet may share the page with other portlets
* __MAXIMIZED__: portlet will take up the whole page
* __MINIMIZED__: In Liferay, this will only display the title bar of a portlet.

Window states, just like portlet modes, can be set in either the JSP or in the Portlet class:

**JSP**
```html
<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="renderURL">
```

**Portlet class**
```java
actionResponse.setWindowState(WindowState.NORMAL);
```

#### Inter-Portlet Communication

One of the limitations of the first Portlet Specification, JSR-168, was the lack of a standard way for portlets to communicate with each other. In the second Portlet Specification, JSR-268, two methods of Inter-Portlet Communication (IPC) were established: Events and Public Render Parameters. We've already discussed Events, so let's take a look at the Public Render Parameters.

#### Public Render Parameters

Declaring a Public Render Parameter in a Java Standard Portlet happens in `portlet.xml`. We define the Public Render Parameter and what Public Render Parameters we want to use/support. We are then able to use Public Render Parameters like any other portlet parameter:

**portlet.xml**
```xml
<?xml version="1.0"?>

<portlet-app>
	...
	<portlet>
		<portlet-name>trainingPortlet</portlet-name>
		<display-name>Training Portlet</display-name>
		...
		<supported-public-render-parameter>tag</supported-public-render-parameter>
	</portlet>

	<public-render-parameter>
		<identifier>tag</identifier>
		<qname xmlns:x="http://www.liferay.com/public-render-parameters">x:tag</qname>
	</public-render-parameter>
</portlet-app>
```

**JSP**
```html
<portlet:actionURL var="editEntryURL" />

<aui:form action="<%= editEntryURL %>" method="post" name="fm">
		<aui:input name="tag" type="text" />
</aui:form>
```

**Java**
```java
String name = ParamUtil.getString(request, "tag","");
```

#### Non-Standard Communication Methods

#### Client-Side IPC

We can use Ajax JavaScript calls to communicate with other portlets on the same page. These legacy methods are provided in Liferay's JavaScript library. As with all IPC, the portlets that are communicating with each other have to be on the same page.

**Sender**
```javascript   
<script type="text/javascript"/>
	Liferay.fire('eventName',{

		parameterName1:parameterValue1,
		parameterName2:parameterValue2

	});
</script>
```

**Receiver**
```javascript
<script type="text/javascript"/>
	Liferay.on('eventName',function(event) {

		console.log(event.parameterName1)
		console.log(event.parameterName2)

	});
</script>
```

#### Portlet Sessions

A portlet can share the otherwise private session data by declaring the following in liferay-portlet.xml:

```xml
<private-session-attributes>false</private-session-attributes>
```

#### Wrapping It Up

* Entering the Action or the Render phase in a single portlet forces all the portlets on a page to re-render.
* The Render phase is for producing the HTML fragment. It cannot do redirection.
* A window state can only be set in the Action phase.
* By default, ActionRequest parameters are not available in the Render phase, but must be set programmatically through the respective methods of the ActionResponse object. Liferay's MVCPortlet simplifies this process by copying all ActionRequest parameters to the Render phase. This behavior can be disabled in the `portlet.xml`.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>A __________ is a web component or application that produces an HTML fragment of a page.</li>
  <li>Java Standard Portlet creation requires several key elements including _________________________, a ____________________, and _______________________.</li>
	<li>There are six phases of the portlet lifecycle:</li>
	<ul>
		<li>______________________</li>
		<li>______________________</li>
		<li>______________________</li>
		<li>______________________</li>
		<li>______________________</li>
		<li>______________________</li>
	</ul>
	<li>There are three Portlet Modes: ___________, ___________ and ___________.</li>
	<li>JSR-268 established two methods of Inter-Portlet Communication: _________________ and _________________________________________.</li>
</ul>
</div>