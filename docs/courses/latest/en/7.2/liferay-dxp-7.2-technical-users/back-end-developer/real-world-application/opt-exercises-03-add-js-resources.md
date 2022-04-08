---
description: Real World Application
title: Add JavaScript Resources
order: 3
---

<h2 class="exercise">Optional Exercise</h2>

## Add JavaScript Resources

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Initialize the <code>package.json</code> file for the <i>gradebook-web</i> module</li>
		<li>Install the Liferay NPM Bundler</li>
		<li>Install the Tooltip library</li>
		<li>Install the Event Emitter library</li>
		<li>Add the NPM Bundler build script</li>
		<li>Test the User Interface</li>
	</ul>
</div>

<br />

> The exercise requires the [Node.js](https://nodejs.org/en/) version 6.11.0 or greated to be installed in your development environment.

#### Initialize the `package.json` file

1. **Open** the command prompt and navigate to the root folder of *gradebook-web* project.
1. **Run** the following command to initialize the `package.json`:
```bash
npm init -y
```
The generated file will look like this:

```json
{
	"name": "gradebook-web",
	"version": "1.0.0",
	"description": "",
	"main": "index.js",
	"scripts": {
	"test": "echo \"Error: no test specified\" && exit 1"
	},
	"keywords": [],
	"author": "",
	"license": "ISC"
}
```
	
#### Install the Liferay NPM Bundler

Liferay NPM Bundler takes care of formatting the NPM bundles automatically for Liferay AMD loader. We need to include the NPM package for the tool:

1. **Open** the command prompt and navigate to the root folder of *gradebook-web* project.
1. **Run** the following command:
```bash
npm install --save-dev liferay-npm-bundler
```

#### Install the Tooltip Library

Install the Tooltip library (https://www.npmjs.com/package/tooltip):

1. **Run** the following command:
```bash
npm install tooltip --save
```

#### Install the Event Emitter Library

We also need to install Node's event emitter:
1. **Run** the following command:
```bash
npm install events --save
```

#### Modify the `package.json` Build Scripts

Add the liferay-npm-bundler to the `package.json` build script to pack the needed NPM packages and transform them to AMD. Remove the test script:

1. **Open** the `package.json` in the root module of *gradebook-web*.
1. **Replace** the contents with the following (see the highlighted lines for changes):
```json
{
	"name": "gradebook-web",
	"version": "1.0.0",
	"description": "",
	"main": "index.js",
	"scripts": {
	"build": "liferay-npm-bundler"
	},
	"keywords": [],
	"author": "",
	"license": "ISC",
	"devDependencies": {
	"liferay-npm-bundler": "^2.8.0"
	},
	"dependencies": {
	"events": "^3.0.0",
	"tooltip": "^1.6.1"
	}
}
```

#### Add the Tooltip to `view.jsp`

Add the placeholder for the tooltip in the JSP file:

1. **Open** the `src/main/resources/META-INF/resources/view.jsp` file.
1. **Add** just before the assignment heading:
	```html
	<p>
	  <a class="gradebook-tip" href="javascript:void(0);" data-tooltip="<liferay-ui:message key="assignments-help-text" />">
	    <liferay-ui:message key="help" />
	    <clay:icon symbol="question-circle" />
	  </a>
	</p>
	```
1. **Add** the Javascript to the end of the file:
```javascript
<aui:script>
	Liferay.Loader.require('gradebook-web$tooltip@1.6.1/dist/Tooltip', function(tooltip) {
			tooltip();
	});
</aui:script>
```

The complete file will now look like:
```html
<%@ include file="/init.jsp"%>

<liferay-ui:error key="serviceErrorDetails">
	<liferay-ui:message arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' key="error.assignment-service-error" />
</liferay-ui:error>
<liferay-ui:success key="assignmentAdded" message="assignment-added-successfully" />
<liferay-ui:success key="assignmentUpdated" message="assignment-updated-successfully" />
<liferay-ui:success key="assignmentDeleted" message="assignment-deleted-successfully" />

<div class="container-fluid-1280">

	<p>
		<a class="gradebook-tip" href="javascript:void(0);" data-tooltip="<liferay-ui:message key="assignments-help-text" />">
		<liferay-ui:message key="help" />
		<clay:icon symbol="question-circle" />
		</a>
	</p>

	<h1><liferay-ui:message key="assignments" /></h1>
	
<%-- Clay management toolbar. --%>

	<clay:management-toolbar
		disabled="${assignmentCount eq 0}"
		displayContext="${assignmentsManagementToolbarDisplayContext}"
		itemsTotal="${assignmentCount}"
		searchContainerId="assignmentEntries"
		selectable="false"
	/>	
	
	<%-- Search container. --%>

	<liferay-ui:search-container 
		emptyResultsMessage="no-assignments"
		id="assignmentEntries"
		iteratorURL="${portletURL}" 
		total="${assignmentCount}">

		<liferay-ui:search-container-results results="${assignments}" />

		<liferay-ui:search-container-row
			className="com.liferay.training.gradebook.model.Assignment"
			modelVar="entry">

			<%@ include file="/assignment/entry_search_columns.jspf" %>

		</liferay-ui:search-container-row>
		
		<%-- Iterator / Paging --%>

		<liferay-ui:search-iterator 
			displayStyle="${assignmentsManagementToolbarDisplayContext.getDisplayStyle()}"
			markupView="lexicon" 
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	Liferay.Loader.require('gradebook-web$tooltip@1.6.1/dist/Tooltip', function(tooltip) {
			tooltip();
	});
</aui:script>
```
	
> Note that the Tooltip library might be different from 1.6.1,  used in this exercise. Check the library version in `package.json` file in the `gradebook-web` root folder and update in the code, if necessary

#### Test the User Interface

1. **Open** the Gradebook application in your browser
1. **Hover** your cursor over the help / question mark icon.

<img src="../images/tooltip.png" style="max-height: 100%"/> 

> See the Developer Network (https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/using-npm-in-your-portlets) for more information abouy using the NPM in your portlet modules. 