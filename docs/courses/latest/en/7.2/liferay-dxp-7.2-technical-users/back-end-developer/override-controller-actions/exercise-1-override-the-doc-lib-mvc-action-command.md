---
description: 11 - Override Controller Actions
title: Override the Documents and Media MVC Action Command
order: 1
---

<h2 class="exercise">Exercises</h2>

## Override the Documents and Media MVC Action Command

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay Module project using the service template</li>
		<li>Declare dependencies</li>
		<li>Implement the MVC Action Command override</li>
		<li>Deploy and test</li>
	</ul>
</div>

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** the _Command Line shell_ in your Liferay Workspace `modules` folder.
1. **Create** the module project:
```bash
blade create -t service -p com.liferay.training.mvccommandoverride -s com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand -c DocLibMVCActionCommand mvc-command-override 
```
	
**Option 2: Use Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  "mvc-command-override"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: service
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "DocLibMVCActionCommand"
	* __Package Name__: "com.liferay.training.mvccommandoverride"
1. **Click** on the browse button next to the *Service Name* field.
	* Enter "\*.MVCActionCommand" in the *Select Service Name*.
	* Select `com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand` from the list.
1. **Click** *Finish* to close the wizard.

#### Declare Dependencies

Because we intend to override an `MVCActionCommand`, we have to depend on the `portlet-api` and `javax.servlet-api` packages. Additionally, we depend on the `com.liferay.document.library.api` package, because we interface with the Document Library. 

1. **Open** the `build.gradle` file and declare the new dependencies as follows:

```groovy
compileOnly group: "com.liferay", name: "com.liferay.document.library.api"
compileOnly group: "javax.portlet", name: "portlet-api"
compileOnly group: "javax.servlet", name: "javax.servlet-api"
```
  
#### Implement the MVC Action Command Override

<div class="note">
<ul>
	<li>When overriding action commands, find the source code for the original class and copy the required component properties.</li>
	<li>Set the `service.ranking` property higher than in the original class to replace it.</li>
	<li>Dispatch the action to the original action command in the end of your `doProcessAction()` override.</li>
</ul>
</div>

1. **Open** the class `com.liferay.training.mvccommandoverride.DocLibMVCActionCommand`.
1. **Implement** the `@Component` annotation as follows:
	```java
	@Component(
		immediate=true,
		property = {
			"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
			"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
			"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
			"mvc.command.name=/document_library/edit_folder",
			"service.ranking:Integer=100"
		},
		service = MVCActionCommand.class
	)
	```
1. **Add** a reference to the original `MVCActionCommand` to the end of the class.
  ```java
  @Reference(
    target = "(component.name=com.liferay.document.library.web.internal.portlet.action.EditFolderMVCActionCommand)"
  )
  private MVCActionCommand _mvcActionCommand;
  ```
1. **Implement** the `processAction()` method as follows. Notice dispatching to the original command:
  ```java
	@Override
	public boolean processAction(ActionRequest actionRequest, 
			ActionResponse actionResponse) throws PortletException {
		  String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		  System.out.println("CMD=" + cmd);

		  return _mvcActionCommand.processAction(actionRequest, actionResponse);
	}
  ```
1. **Resolve** the missing imports.

#### Final Code Review

1. **Check** that the final code looks like this:

**build.gradle**
```groovy
dependencies {
	compileOnly group: "com.liferay", name: "com.liferay.document.library.api"
	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "javax.portlet", name: "portlet-api"
	compileOnly group: "javax.servlet", name: "javax.servlet-api"
	compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"
}
```
 
**DocLibMVCActionCommand.java**
```java
package com.liferay.training.mvccommandoverride;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author berndt
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
		"mvc.command.name=/document_library/edit_folder",
		"service.ranking:Integer=100"
	},
	service = MVCActionCommand.class
)
public class DocLibMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest, 
			ActionResponse actionResponse) throws PortletException {
		  String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		  System.out.println("CMD=" + cmd);

		  return _mvcActionCommand.processAction(actionRequest, actionResponse);
	}

	@Reference(
		target = "(component.name=com.liferay.document.library.web.internal.portlet.action.EditFolderMVCActionCommand)"
	)
	private MVCActionCommand _mvcActionCommand;

}
```

#### Deploy and Test

1. **Deploy** the module to the Liferay server.
1. **Open** your browser to http://localhost:8080 and sign in.
1. **Click** the *Add* icon on the top right corner of the page.
1. **Add** the *Documents and Media* widget to the page.
1. **Click** the *Add* icon on the top left corner of the Documents and Media widget.
1. **Select** *Folder* and create a folder.
	* After you save and close the dialog, you should see a similar message in the log:
```bash
2019-03-22 09:37:52.373 INFO  [Refresh Thread: Equinox Container: 60da2b19-804c-0019-15e4-bd8acc061cb6][BundleStartStopLogger:35] STARTED com.liferay.training.mvccommandoverride_1.0.0 [965]
CMD=add
```

<!--
#### Takeaways

MVC Commands are the foundation in much of Liferay's application code. After running through this exercise, you should be able to use the pattern here to customize other Liferay applications that are implemented using MVCPortlet and MVC Commands. The same pattern can be used to extend and override MVCRenderCommand, MVCActionCommand, and MVCResourceCommand.
-->