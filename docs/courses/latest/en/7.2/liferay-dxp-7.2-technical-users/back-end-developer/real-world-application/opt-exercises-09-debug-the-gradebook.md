---
description: 6 - Real World Application
title: Debug the Gradebook
order: 9
---

<h2 class="exercise">Optional Exercise</h2>

## Debug the Gradebook

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>(Re)start Tomcat in debug mode</li>
		<li>Add breakpoints</li>
		<li>Use debugger steps to control program execution</li>
	</ul>
</div>

#### (Re)start Tomcat in Debug Mode

1. **Stop** the Liferay server.
2. **Start** the Liferay server in debug mode by clicking the "bug" icon:

<img src="../images/starting-debug.png" style="max-height: 100%"/>

#### Add Breakpoint

1. **Open** `com.liferay.training.gradebook.service.impl.AssignmentLocalServiceImpl` class in the *gradebook-service* module.
1. **Find** call to the `_assignmentValidator.validate()` in the beginning of `addAssignment()` method.
1. **Right-click** on the left side of the line (in the margin) and select *Toggle Breakpoint* from the context menu:
	<img src="../images/toggle-breakpoint.png" style="max-height: 100%"/>
1. **Open** you browser and add a new assignment in the Gradebook application.
	* Adding an assignment should pause in the breakpoint you just defined. In the IDE, there should be a dialog asking to switch to the debug perspective. Click "Yes".
	<img src="../images/switch-to-debug-perspective.png" style="max-height: 100%"/>
1. **Click** *Yes* to switch to the debug perspective.
	* The application execution is now halted at the breakpoint. Let's check what the values are for our assignment title.
1. **Click** the *Variables* to show the variables stack:
	<img src="../images/debug-variables.png" style="max-height: 100%"/>
	* From this panel, you can check and manipulate variable values on the fly.
	* **Click** the *titleMap* variable and browse it. In this case, you can see the Map values easily in the bottom panel, but most of the time it's hard to find some specific Array or Map value there, which is why we need the *Logical Structure view*.  
1. **Click** the *Show Logical Structure* button to show the Map's logical structure
1. **Browse** the *titleMap* variable again.
	<img src="../images/show-logical-structure.png" style="max-height: 100%"/>

In the debug mode, you can fully control application execution. You can move back and forward as well as dive into the method calls. Try *Step over* to take one step forward in the method execution. Try *Step into* to go to the method behind the call. Watch the variables panel while you move. Click *Resume* to continue application execution:

<img src="../images/debug-step-over.png" style="max-height: 100%"/>

After you're done with the exercise, restart the server in normal mode.
