---
description: 2 - OSGi Basics
title: Hello OSGi
order: 1
---

<h2 class="exercise">Exercises</h2>

## Hello OSGi

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Create a Hello OSGi bundle</li>
		<ul>
			<li>Create a new Plugin Project using the <i>Hello OSGi</i> template</li>
			<li>Set up the OSGi Framework run configuration</li>
			<li>Run the application</li>
		</ul>
	</ul>
	<li>This exercise will show the following basic ingredients of OSGi:</li>
		<ul>
			<li>An OSGi <b>runtime</b></li>
			<li>An OSGi <b>bundle</b></li>
			<li>The OSGi specific <b>headers</b> in the bundle JAR's manifest file</li>
		</ul>
	</ul>
</div>

#### Create a New Plugin Project Using the *Hello OSGi* Template

1. **Click** *File → New → Other* on the Dev Studio menu bar to launch the new project wizard.
1. **Enter** "plugin" in the search bar.
1. **Choose** the _Plug-in Project_ project type and click *Next*:
	<img src="../images/new-plugin-project-wizard.png" style="max-height: 25%"/>
1. **Use** the following information for the first step:
	* _Project Name_:  "hello-osgi"
	* _Target Platform_:
		* Select "an OSGi Framework".
		* Use the dropdown to select "standard".
	<div class="note">
	Note: By setting the platform to standard, we guarantee that only standard OSGi implementation features are added.
	</div>
1. **Click** _Next_.
	<img src="../images/select-osgi-framework.png" style="max-height: 30%"/>
1. **Use** the following information for the second panel of the wizard:
	* _ID:_ "com.liferay.training.hello.osgi"
	* _Name:_ "Hello OSGi"
	* _Activator:_ "com.liferay.training.hello.osgi.HelloBundleActivator"
1. **Check** *Generate an activator*.
	<img src="../images/enter-bundle-information.png" style="max-height: 27%"/>
1. **Choose** the *Hello OSGi Bundle* project template and click *Next* on the third panel.
1. **Enter** your personal hello message or leave the defaults on the fourth panel:
	<img src="../images/set-hello-message.png" style="max-height: 27%"/>
1. **Click** *Finish* to close the wizard.
1. **Click** *Open Perspective* to switch to the Plug-in project type specific view:
	<img src="../images/open-perspective.png" style="max-height: 27%"/>

You have reached the first checkpoint. Check that the following items were created:
* The bundle manifest file `MANIFEST.MF`
* The bundle activator class `HelloBundleActivator.java`

<img src="../images/hello-ready.png" style="max-height: 35%"/>

#### Set up the OSGi Framework Run Configuration

Before running the bundle, we'll enable only the bundles needed to run the OSGi container.

1. **Click** *Run → Run Configurations* on the Dev Studio menu bar to open the run configuration dialog.
1. **Double-click** the second *OSGi Framework* (with the target icon) to create a new run configuration.
1. **Expand** *OSGi Framework*  and select the *New_configuration* icon.
1. **Enter** "Training" for the *Name*.
1. **Click** the *Deselect All* button to deselect all the bundles.
1. **Select** the following bundles:
	* `hello-osgi` (under *Workspace*)
	* `org.eclipse.osgi`
	* `org.eclipse.equinox.console`
	* `org.apache.felix.gogo.command`
	* `org.apache.felix.gogo.runtime`
	* `org.apache.felix.gogo.shell`
	<img class="lrt-md-img" src="../images/set-up-run-configuration.png" style="max-height: 38%"/>
	* Use the search bar to find and filter the bundles.

1. **Click** the *Arguments* tab on the dialog and add the `-clean` argument to the end of the list in the *Program Arguments* box (upper text box).
	* `-clean` This prevents workbench creation-related error messages on some Eclipse distributions.
1. **Click** *Apply* and close the dialog.
	<img src="../images/set-run-arguments.png" style="max-height: 25%"/>

#### Run the Application

1. **Right-click** the project to open the context menu.
1. **Click**  *Run As → OSGi Framework*:
	<img src="../images/run-application.png" style="max-height: 27%"/>
	* You should see the hello message in the console:
	<img src="../images/hello-checkpoint.png" style="max-height: 28%"/>
