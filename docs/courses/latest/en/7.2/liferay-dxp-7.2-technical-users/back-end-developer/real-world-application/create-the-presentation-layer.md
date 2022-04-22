---
description: 06 - Real World Application
title: Create the Presentation Layer
order: 4
---

## Create the Presentation Layer

In modular Liferay applications, the presentation layer is usually located in what's called the *web module*. For example, in the native Blogs application, this module is called [blogs-web](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/blogs). In terms of the MVC pattern, the web module typically contains both the view (user interface) and the controller (portlet) layers.

You can use a variety of technologies to develop Liferay portlets. Module templates are provided for example for:

* __mvc-portlet:__ Liferay MVC portlet
* __freemarker-portlet:__ portlet using Freemarker template language
* __npm-angular-portlet:__ Angular portlet
* __npm-react-portlet:__ React portlet
* __npm-vuejs-portlet:__ VueJS portlet
* __spring-mvc-portlet:__ Spring WAR style portlet
* __war-mvc-portlet:__ MVC WAR style portlet

You can also use JSF, Vaadin or with portlet 3.0, supported since Liferay DXP 7.1, or take advantage of Java EE CDI with Bean Portlets. There are plenty of examples available in [Liferay Blade Samples](https://github.com/liferay/liferay-blade-samples) and [Liferay Developer Network resources](https://dev.liferay.com).

The implementation steps and their order of execution depend on the chosen technology and approach. For the Gradebook exercise, we will, in general, be using the following approach:

1. Create the web module.
3. Implement portlet actions using MVC commands.
4. Implement portlet JSPs using tag libraries.
5. Implement portlet validation and feedback. 
2. Add localization resources.
6. Add CSS resources.
7. Add JavaScript resources.

Let's discuss the steps and concepts related to the user interface of our Gradebook application.

#### Creating the Web Module

Liferay provides several module templates for building the presentation layer, like:

* __mvc portlet:__ a module template with a sample Liferay MVC portlet component, localization resources, and JSP files
* __freemarker-portlet:__ a module template for the user interface with FreeMarker templating language and FreeMarker portlet back-end. The FreeMarkerPortlet class is an extension of Liferay MVC portlet.
* __NPM portlets__: several Liferay MVC portlet-based templates for building the user interface with JavaScript frameworks like Angular and React; package management with NPM
* __spring-mvc-portlet:__ for building a Spring MVC portlet
* __war-mvc-portlet:__ a template for legacy WAR-style portlets

In addition to these, JSF and [Vaadin](https://vaadin.com/) portlet-type user interfaces are supported out of the box.

#### Implementing Portlet Actions with MVC Commands

The interaction between a portlet back-end and a user is handled by portlet lifecycle methods as described in *Module 5 - Java Standard Portlet*. 

*MVC commands* are Liferay-provided, portlet lifecycle handler components meant to be used in conjunction with Liferay MVC portlets. They reduce the need for boilerplate portlet coding and provide a more modular application design compared to legacy-style lifecycle handlers. 

MVC commands are single class OSGi components, responsible for handling an action defined by components in the `mvc.command.name` property. Generally, three qualities wire an MVC command to the right action in the right place:
1. Wiring to a certain portlet lifecycle (render, action, resource) 
1. Responding to a certain command, defined in component properties with `mvc.command.name`
1. Registration to a certain portlet via the `javax.portlet.name` component property

> Notice that a single MVC command component can respond to multiple commands and also register to multiple portlets. 

The following diagram illustrates the architecture:

<img src="../images/mvc-commands.png" style="max-height:100%"/>

By convention, the MVC command classes are named by the following pattern, corresponding to the portlet lifecycle:

* \*MVCRenderCommand.java
* \*MVCActionCommand.java
* \*MVCResourceCommand.java

With this pattern, an MVC command responsible for rendering assignments list view could become `ViewAssignmentsMVCRenderCommand`.

#### MVC Render Commands

MVC Render Commands handle portlet's *render* phase. They are called by setting the `mvcRenderCommandName` parameter in the calling URL:

```html
<portlet:renderURL var="viewEntryUrl">
    <portlet:param name="mvcRenderCommandName" value="/my_portlet/view_entry" />
    <portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
</portlet:renderURL>

<a href="<%= viewEntryUrl">Click here to view the entry</a>
```

#### MVC Action Commands

MVC action commands handle the portlet's *action* phase. Actions are typically form submits that trigger events, like entity update, on the model layer. The action to respond is defined in the calling URL's `name` parameter.

```html
<portlet:actionURL name="/my_portlet/edit_entry" var="editEntryURL" />

<aui:form action="<%= editEntryURL %>" method="post" name="fm">
```

#### MVC Resource Commands

MVC Resource Commands handle the *resource serving* phase. As the resource serving lifecycle phase doesn't invoke the render phase, it's typically used for operations that don't need full page refresh. Such use cases can be, for example:

* Updating a list with Ajax call
* Subscription actions
* Captcha checking  

```html
<portlet:resourceURL id="/login/captcha" var="captchaURL" />
```

#### Using Tag Libraries

Tag libraries are collections of user interface components called *tags* for JSP development. From a user interface design perspective, they allow a clean separation between the look-and-feel and business logic. 
Tag libraries can significantly reduce development time and remove boilerplate coding. 

Tags inside a tag library are JSP markup elements, which are replaced by a respective markup at render times. Tags available in a library can be browsed in the library's descriptor TLD file (inside taglib JAR).

Liferay-provided libraries contain tags for the most common user interface components (like forms) and are designed to be responsive. 

Below is an example of using a `liferay-ui` tag library for showing user details and rendered HTML. Notice the required library TLD declaration, which typically resides in an `init.jsp` file. Also notice the namespacing of the tag library, using the `prefix` attribute:

**init.jsp**
```java
<% @taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
```

```html   view.jsp
<liferay-ui:user-display
    markupView="lexicon"
    showUserDetails="true"
    showUserName="true"
    userId="<%= themeDisplay.getRealUserId() %>"
    userName="<%= themeDisplay.getRealUser().getFullName() %>"
/>
```

**Rendered HTML**
```html
<div class="profile-header"> 
	<div class="nameplate"> 
		<div class="nameplate-field"> 
			<div class="user-icon-color-0 user-icon user-icon-default">
				<span>TT</span>
			</div> 
		</div> 
		<div class="nameplate-content"> 
			<div class="heading4"> 
				<a href="http://localhost:8080/web/test"> Test Test </a> 
			</div> </div> <div class="nameplate-content"> 
		</div> 
	</div> 
</div>
```

#### Standard Tag Libraries

The *Portlet Standard* libraries are:
* __portlet:__ standard portlet JSR 168 tag library (overrun in Liferay's portlet\_2\_0 library)
* __portlet\_2\_0:__ standard portlet JSR 286 tag library (included in Liferay's liferay\_portlet\_2\_0\_ext)
* __portlet\_3\_0:__ standard portlet JSR 362 tag library 
* __JSTL tag libraries__ 

Standard JSTL Libraries contain general purpose tags, for example, for:

* Variables, conditionals (if-else), loops
* Formatting strings, dates, and numbers
* Basic string functions (replace, join, lowercase, etc.)
* Parsing XML
* Showing date 

Below is an example of standard JSTL tags usage (in `c` namespace):

```html
<c:choose>
	<c:when test="${themeDisplay.isSignedIn()}">
	<p>You appear to be signed in. Your groups: </p>
	<ul>
		<c:forEach items="${themeDisplay.getUser().getGroups()}" var="group">
			<li><c:out value = "${group.getName(locale)}"/></li>
		</c:forEach>
	</ul>
	</c:when>    
	<c:otherwise>
	<p>You are not signed in.
	</c:otherwise>
</c:choose>
```

The standard JSR-362 (portlet\_3\_0) library provides portlet lifecycle url generation and portlet namespacing. Below are examples of creating an action url and render url with the portlet 3.0 tag library:

**TLD_declaration**
```java
<%@ taglib uri="http://xmlns.jcp.org/portlet_3_0" prefix="portlet" %>
```

**actionURL**
```xml
<portlet:actionURL name="/gradebook/assignment/delete" var="deleteAssignmentURL">
	<portlet:param name="redirect" value="${currentURL}" />
	<portlet:param name="assignmentId" value="${assignment.assignmentId}" />
</portlet:actionURL>
```

**renderURL**
```xml
<portlet:renderURL var="viewSubmissionsURL">
	<portlet:param name="mvcRenderCommandName" value="/gradebook/submissions/view" />
	<portlet:param name="redirect" value="${currentURL}" />
	<portlet:param name="assignmentId" value="${assignment.getAssignmentId()}" />
</portlet:renderURL>
```

#### Liferay Tag Libraries

Liferay provides a rich set of its own libraries, like:

* __liferay-theme:__ provides theme components
* __liferay-portlet:__ a wrapper for standard portlet\_2\_0 library
* __liferay-ui:__ contains numerous display tags like alert, icons, search-container
* __liferay-util:__ provides page-level tags like includes
* __aui:__ Responsive Alloy UI tags for forms and containers
* __liferay-security:__ provides doAsURL and permissionURL
* __clay:__ set of tags for creating Liferay Clay UI components
* __chart:__ charting and data modeling tags

The Clay library supersedes the AUI library of many parts, leveraging the Liferay Clay framework and providing tags, for example, for:

* Alerts
* Badges
* Buttons
* Cards
* Dropdown Menus and Action Menus
* Form Elements
* Icons
* Labels and links
* Navigation Bars
* Progress Bars
* Stickers

Below is an example of using the Clay library:

**init.jsp**
```java
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>
```

**view.jsp**
```html
<clay:stripe
    message="This is a success message."
    style="success"
    title="Success"
/>
```

The code above renders as:

<img src="../images/clay-success-message.png" style="max-height:100%"/>

> See [Developer Network](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/front-end-taglibs) for more information about Liferay tag libraries.

#### Implementing Validation and Feedback

In real world application design, it's important to have control over both user input and output. A robust validation is done on multiple layers, and it never relies solely on the user interface.

When implementing validation and feedback, it's good to be aware of Liferay's utility classes. Liferay provides utility classes for validation but other common tasks, like string manipulation, date formatting and parameter handling. Many of the utilities can be found in the [com.liferay.portal.kernel.util](https://docs.liferay.com/portal/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/package-summary.html) package and in the [Petra libraries](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/petra).

#### Input Validation

There are many good reasons to implement multi-level input validation and user interface feedback in your applications:
1. __Security:__ to protect against malicious input, no user input should be allowed to enter the model layer without validation
1. __Usability:__ early validation and feedback provides a better user experience
1. __Resource usage:__ validation on a user interface reduces faulty form submissions and server load

__Client side__ input validation is provided with the `aui-validator` tag and Twitter Bootstrap.

The following tags support validation with `<aui-validator>` tag:

* `<aui:input>` 
* `<aui:select>`
* `<liferay-ui:input-date>`
* `<liferay-ui:input-search>`

Below is a simple example of using the `<aui-validator>` tag with `<aui-input>` tag:

```html
<aui:form> 
	<aui:input name="title" >
		<aui:validator name="required" errorMessage="Please enter the title."/>
	 </aui:input>
<aui:form>
```

A custom validator function can be provided for the `<aui-validator>` tag. The validator function has to return true or false:

```html
<aui:input name="title">
	<aui:validator errorMessage="you-must-specify-a-file-or-a-title" name="custom">
		function(val, fieldNode, ruleValue) {
			return ((val != '') || A.one('#<portlet:namespace />file').val() != ''); }
	</aui:validator>
</aui:input>
```

Liferay's user interface relies on Twitter Bootstrap to support its validation methods. Below is an example of using the `required` attribute:

```html
<form data-toggle="validator" role="form">
	<div class="form-group">
		<label for="inputName" class="control-label">Name</label>
		<input type="text" class="form-control" id="inputName" placeholder="Your Name" required>
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
</form>  
```

On the __back-end__ side, the [Validator](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/util/Validator.java) utility class provides methods for common validation tasks:

```java
String name = ParamUtil.getString(request, “title”);
If (Validator.isNull(name)) { 
	SessionErrors.add(request, “title-empty”);
	return false;
}
```

#### Note on AntiSamy

Although not a validation functionality as such, there is an additional security module that protects against malicious user input. The __AntiSamy__ module leverages the OWASP AntiSamy library, processing user input on form submit and stripping away all the HTML elements and content not explicitly allowed. The module is configurable through *Control Panel → System Settings → Foundation → AntiSamy Sanitizer*.

### Output Validation

In addition to input validation, an output validation for malicious content should be done to prevent malicious code from executing. Usually, this means escaping content prior to displaying.

On the client side, escaping can be done with the standard JSTL library:  

```html
<c:out escapeXML="true">${bodyText}</c:out>	
```

On the server side, the Liferay HTMLUtil class can be used:

```java
request.setAttribute("bodyText", HtmlUtil.escape(bodyText));	
```

#### Showing Feedback

In Liferay portlet JSP applications, the feedback from the back-end to the user interface is often transported  with [SessionErrors](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/servlet/SessionErrors.java) and [SessionMessages](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/servlet/SessionMessages.java) objects. Below is an example demonstrating setting a message key in the back-end, doing a localization in `Language.properties`, and showing the message on the user interface using the `<liferay-ui:success>` and `<liferay-ui:error>` tags:

**Portlet class**
```java
String name = ParamUtil.getString(request, “title”);
If (Validator.isNull(name)) { 
	SessionErrors.add(request, “titleEmpty”);
	return false;
}

if (SessionErrors.isEmpty() {
	SessionMessages.add(request, “assignmentUpdatedSuccessfully”);
}
```

**Language.properties**
```java
assignment-updated-successfully=Assignment was updated successfully
please-enter-title=Please enter the title.
```

**view.jsp**
```html
<liferay-ui:success key="assignmentUpdatedSuccessfully" message="assignment-updated-successfully" />
<liferay-ui:error key="titleEmpty" message="please-enter-title" />
```

This renders on error as:

<img src="../images/please-enter-the-title.png" style="max-height:100%"/>

### Hiding Default Messages

Liferay sets default success messages for successful portlet actions. These messages can be hidden with portlet component properties:

```java
@Component(
	immediate = true,
	property = {
		...
		"javax.portlet.init-param.add-process-action-success-action=false",
		...
	},
	service = Portlet.class
)		 	 	 		
```

#### Common Guidelines for Implementing Validation

* Establish validation on both the client and server side.
	* User interface validation is not for securing but for usability.
* Establish control over all input.
* Establish control over all output; escape the output on the user interface.
* Escaping of user-provided input should generally be done on render time, not storing time.

Overview of required steps for implementing basic validation in your application:
1. Validate user input on the user interface
1. Customize AntiSamy to filter input on form submit
1. Do back-end validation and add messages to the session objects
1. Show validation feedback on the screen using liferay-ui tag library
1. Clean user-contributed output 

#### Internationalizing the Application

__Internationalization (i18n)__ means designing an application so that it isn't hardwired to one language only. 

Internationalization is not just about localizing your application, but about a preferable design pattern for any user interface-centric applications. Even if you don't intend to have your application support multiple languages, internationalization lets you create a loose coupling between the display messages and the business logic, making the application more manageable.

__Localization (i10n)__ is similar to internationalization, but adds support to a specific language.

#### About Language Keys

Language keys are unique string identifiers for the display messages. The same keys can be reused in the code as many times as needed. As a good practice, you should use describing keys like *submit-form* to improve code readability. Parametrization of keys is supported.

#### Language files

Language files, or localization files, contain a list of key value pairs for a single language. The default `Language.properties` file, which serves as a fallback default file, is automatically generated by a portlet module template and is located in the `src/main/resources/content` folder. 

Language files have the following naming syntax: `Language_LANGUAGE_CODE.properties`, where LANGUAGE_CODE is replaced either by:
* ISO 639-1 standard language code, or
* ISO 639-1 language code and ISO-3166 country code, separated by an underscore

Examples:

* Language\_en.properties (all the English variants)
* Language\_en\_US.properties (US English)

> Language files should use UTF-8 encoding.

#### Configuring Language Resources

In order to use the language files, portlets and other components have to be made aware of the available resource bundles. In the case of portlets, this is done by defining the `javax.portlet.resource-bundle` component property. When using Liferay portlet module templates, default resources and component properties are created automatically.

#### Localization Example

Let's now have a look at a concrete localization example. 

First, we have a portlet component and the `javax.portlet.resource-bundle` property. Notice that the value of the property is actually pointing to `src/main/resources/content/Language.properties`. The language file then contains a list of key value pairs for a specific language. Display messages are referenced by their respective keys, in this example, by using a Liferay tag library:

**Portlet class**
```java
@Component(
	immediate = true,
	property = {
		...
		"javax.portlet.resource-bundle=content.Language",			
		...
	},
	service = Portlet.class
)	
public class LocalizationExamplePortlet extends MVCPortlet {
	...
}
```

**Language.properties**
```properties
hello-stranger=Hello stranger
hello-you=Hello you
my-favourite-dogs-are-x-and-x=My favourite-dogs are {0} and {1} 
```

**view.jsp**
```html
<liferay-ui:message key="hello-stranger" />
<liferay-ui:message key="my-favourite-dogs-are" arguments="<%= new String[] {"Ryder", "Marshall" } %>"/>
```

> When using tag libraries, notice that when a matching language key is not found in the portlet language file, the lookup falls back to the portal resource bundle. If the value is not found, the key is shown.

#### Note on Localizing Portlet Name

Portlet standard message localization follows a special pattern:

```html
javax.portlet.<portlet-name-field>.<portlet-id>=<translation>
```

Where the *portlet-name-field* can be one of the following:

* description
* display-name
* keywords
* short-title
* title

Gradebook portlet display name:

```java
javax.portlet.display-name.gradebook-web=Gradebook Portlet
```

#### Localization in the Back-End

Accessing the localization resources in a Java class requires loading the resource bundle manually. The `target` property of the `ResourceBundleLoader` reference is used to filter the resource:

**Portlet class**
```java
public void doView(RenderRequest renderRequest, RenderResponse renderResponse) 
	throws IOException, PortletException {

	String helloStranger = getResourceBundleLocalization("hello-stranger", locale);
	String myFavoriteDogsAre = getResourceBundleLocalization("my-favourite-dogs-are-x-and-x",
		locale,  "Ryder", "Marshall");
}

private String getLanguageLocalization(String key, Locale locale, Object...objects)  {

	ResourceBundle resourceBundle = _resourceBundleLoader.loadResourceBundle(locale);
	String value = ResourceBundleUtil.getString(resourceBundle, key, objects);
	return value==null ? _language.format(locale, key, objects) : value;
}	

@Reference(
	target = "(bundle.symbolic.name=com.liferay.training.localization.example)", 
	unbind = "-"
)
private ResourceBundleLoader _resourceBundleLoader;

@Reference
private Language _language;

```

#### Sharing Resource Bundles Between Modules

In modular OSGi design, a single module is self-contained and usually contains all the resources it needs. In a multi-module application, however, you'll sometimes want either to centralize all localization resources in a dedicated module or let modules access localization resources from each other. 

`-liferay-aggregate-resource-bundles` bnd instruction lets you use other resource bundles along with module's own resources:

```properties
-liferay-aggregate-resource-bundles: \
	[bundle.symbolic.name1],\
	[bundle.symbolic.name2]
```

> The current module’s resource bundle is prioritized over those of the listed modules.

#### Adding CSS Resources

Portlet CSS files are defined in portlet component properties. The following properties are available, each of them allowing multiple values:

* `com.liferay.portlet.header-portal-css`
* `com.liferay.portlet.header-portlet-css`
* `com.liferay.portlet.footer-portal-css`
* `com.liferay.portlet.footer-portlet-css`

By default, header and footer CSS files are injected respectively in the page header and footer. The difference between `portal-css` and `portlet-css` is the context path with which the former is set to portal and the latter to the portlet context. Portlet CSS files are typically placed in the `META-INF/resources/css` folder.

Liferay uses SASS compiler. Files suffixed with `.scss` are SASS-compiled automatically: 

**CSS file**
```
css-portlet/src/main/resources/META-INF/resources/css/main.scss
```

**Component properties**
```java
...
"com.liferay.portlet.header-portlet-css=/css/main.css",
...
```

**Generated HTML**
```html
<head>
	...
	<link data-senna-track="temporary" href="http://localhost:8080/o/com.liferay.training.css/css/main.css?browserId=other&amp;themeId=classic_WAR_classictheme&amp;languageId=en_US&amp;b=7100&amp;t=1526032024000" id="605088bd" rel="stylesheet" type="text/css">
	...
```

> Notice that the reference to a CSS file in the portlet's properties is still `.css` even if you use the `.scss` as a suffix

#### Adding JavaScript Resources

Following the logic of portlet component CSS properties, portlet JavaScript files can be defined with: 

* `com.liferay.portlet.header-portal-javascript`
* `com.liferay.portlet.header-portlet-javascript`
* `com.liferay.portlet.footer-portal-javascript`
* `com.liferay.portlet.footer-portlet-javascript`

The property values can be local paths or external URLs. If Javascript files are hosted locally, they are by convention stored in `META-INF/resources/js` folder. While this approach for including Javascripts works in simple cases, there are limitations to it especially when using 3rd party libraries. For example, scripts defined in the properties are loaded synchronously and they cannot be scoped or encapsulated. In more complex application it's recommended to use NPM based approaches. See the [Liferay Blade samples](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/apps) for some options.

Portlet JavaScript resources are bundled and minified at compile time and served through combo servlet. An example:

**main1.js**
```java
console.log("This is the main1.js file.")
```

**main2.js**
```java   
console.log("This is the main2.js file.")
```

**Component Properties**
```java   
@Component(
	immediate = true,
	property = {
		...
		"com.liferay.portlet.header-portlet-javascript=/js/main1.js",
		"com.liferay.portlet.header-portlet-javascript=/js/main2.js",
		...
	},
	service = Portlet.class
)
public class CssAndJavascriptExample extends MVCPortlet {
}

```

**Generated HTML**
```html
<head>
	...
	<script data-senna-track="temporary" src="/combo?browserId=other&amp;minifierType=&amp;themeId=classic_WAR_classictheme&amp;languageId=en_US&amp;b=7100&amp;CssAndJavascriptExample_INSTANCE_3sSKqOIS8KsX:%2Fjs%2Fmain1.js&amp;CssAndJavascriptExample_INSTANCE_3sSKqOIS8KsX:%2Fjs%2Fmain2.js&amp;t=1526034802000" type="text/javascript">&lt;/script>
	...
</head>
```

The bundled, single JavaScript file contents:
```java
console.log("This is the main1.js file.")
console.log("This is the main2.js file.")
```

#### Configuring JavaScript

To configure Javascript, look for the properties with the following prefixes in the [portal properties](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/portal.properties):

* javascript
* minifier
* combo

#### Liferay JavaScript API

The Liferay JavaScript object is available on all the pages and contains helpful platform utilities like:

* __Liferay.Browser:__ browser capabilities detection 
* __Liferay.ThemeDisplay:__ ThemeDisplay object 
* __Liferay.Service:__ invokes Liferay services 
* __Liferay.Language:__ localization 

An example of calling a Liferay service with `Liferay.Service`:

```javascript
Liferay.Service(
	'/user/get-user-by-email-address',
	{
		companyId: Liferay.ThemeDisplay.getCompanyId(),
		emailAddress: 'test@liferay.com'
    },
    function(obj) {
            console.log(obj);
    }
);
```
#### Good to Know 

Alloy UI and jQuery libraries are globally available, but the platform doesn't restrict you from using any other preferred library. Also, support for ES2015 as well as NPM modules is available as well as a configurable AMD loader. These topics are discussed more in detail in the Front-End Developer course.

#### Links and Resources

* Alloy UI Validator: https://github.com/liferay/alloy-ui/blob/master/src/aui-form-validator/js/aui-form-validator.js
* Internationalizing Liferay applications: https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/internationalization
* Using JavaScript in Portlets: https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-2/using-javascript-in-your-portlets
* Liferay taglibs: https://docs.liferay.com/portal/7.2-latest/taglibs/util-taglib/

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>In modular Liferay applications, the _____________ is usually located in the _____________ module.</li>
  <li>The web module typically contains both the _____________ (user interface) and the _____________ (portlet) layers.</li>
  <li>_____________ are Liferay-provided, _____________ meant to be used in conjunction with Liferay MVC portlets.</li>
  <li>There are three types of MVC commands:
	<ul>
		<li>_____________</li>
		<li>_____________</li>
		<li>_____________</li>
	</ul>
  </li> 
</ul>
</div>