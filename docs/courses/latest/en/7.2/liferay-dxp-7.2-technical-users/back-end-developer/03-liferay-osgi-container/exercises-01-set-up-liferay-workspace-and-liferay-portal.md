---
description:  3 - Liferay's OSGi Container
title: Set Up the Liferay Workspace and Portal
order: 1
---

<h2 class="exercise">Exercises</h2>

## Set Up the Liferay Workspace and Portal

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Create a Liferay Workspace environment for Liferay modules</li>
		<ul>
			<li>Create an Eclipse workspace</li>
			<li>Import the provided Liferay Workspace</li>
			<li>Run the `initBundle` Gradle task on the Liferay workspace</li>
			<li>Create a new Liferay server using the Tomcat server adapter</li>
			<li>Start the server and run the portal setup wizard</li>
			<li>Activate the portal by copying the license file to the deploy folder</li>
			<li>Log in to the portal</li>
		</ul>
	</ul>
</div>

#### Create an Eclipse Workspace

1. **Find** the provided `training-workspace` folder.
	* Make sure it has been extracted/unzipped.
1. **Click** *File → Launch Workspace → New Workspace* on the _Developer Studio_ menu bar.
1. **Type** the location where a new Eclipse workspace will be created.
1. **Click** *Launch*. 

#### Import the Provided Liferay Workspace

1. **Click** the *Import Liferay Workspace* link on the Welcome Screen:
	<img src="../images/import-liferay-workspace.png" style="max-height:25%"/>
1. **Click** on the browse button and navigate to the location of the extracted training workspace.
1. **Click** *Finish* to import the Liferay Workspace and close the wizard:

<br />

<img src="../images/import-liferay-workspace-location.png" style="max-height:20%"/>

<br />

#### Run the initBundle Gradle Task

Next, we will prepare the portal bundle for Liferay Workspace. Running the `initBundle` Gradle task either downloads the bundle or, when available, uses a local copy of the portal Tomcat bundle defined in the workspace's `gradle.properties`.

1. **Expand** the *training-workspace/bundle* folder in the *Gradle Tasks* panel on the right-hand side of Developer Studio.
1. **Double-click** to run the `initBundle` task.
	<img src="../images/initbundle.png" style="max-height:25%"/>

<br />

3. **Refresh** the _Project Explorer_ view after you see a `BUILD SUCCESSFUL` message on the console.
	* Make sure that the portal bundle has been extracted to the `bundles` directory. 

#### Create a New Liferay Server

1. **Click** the link saying that there are no servers available on the *Servers* panel.

<br />	
<img src="../images/create-new-server.png" style="max-height:40%;" />
<br />	

2. **Choose** *Liferay 7.x* as the server type and click *Next*.

<img src="../images/select-server-type.png" style="max-height:25%;" />
<br />	

3. **Click** *Browse* and navigate to the bundles directory of our Liferay Workspace (`training-workspace/bundles`).
	* The wizard should detect the portal bundle type automatically.
	* Make sure the right *JDK* is selected under the runtime JRE field.
4. **Click** *Finish*:
	
	<img src="../images/bundle-directory.png" style="max-height:25%"/>

	The new server should appear in the *Servers* panel:

	<img src="../images/server-installed.png" style="max-height:25%" />

#### Start the Server and Run the Portal Setup Wizard

1. **Click** on the *Liferay 7.x* server at the bottom left.
1. **Click** the green *Start the Server* icon on the *Servers* panel to start our newly-created Liferay server.

	Watch the console log for the server startup message:
	```bash
		16-May-2018 15:07:20.054 INFO [main]
		org.apache.catalina.startup.Catalina.start Server startup in 164332 ms
	```
1. **Open** your browser to http://localhost:8080 to complete the setup.
1. **Enter** "Training Portal" for the *Portal Name*.
1. **Click** *Finish Configuration*.
1. **Accept** the *Terms and Conditions*.
1. **Type** a new password if prompted.
1. **Choose** a password reminder query.
1. **Click** *Save*.
	* You'll be redirected to the home page, where you'll see an error message saying that the portal is not activated.


#### Activate the Portal

When Liferay starts for the first time, a subfolder is created called `deploy` under the `LIFERAY_HOME` directory. This is the folder where applications and the license are deployed.

1. **Copy** the provided license file into the `training-workspace/bundles/deploy` directory.
	
	The server console should report messages like the following: 
	```bash
	2018-10-12 12:19:11.523 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing activation-key-digitalenterprisedevelopment-7.1-liferaycom.xml
	2018-10-12 12:19:20.173 INFO  [fileinstall-/opt/liferay/training-workspace/bundles/osgi/modules][LicenseManager:?] Digital Enterprise Development license validation passed
	2018-10-12 12:19:20.174 INFO  [fileinstall-/opt/liferay/training-workspace/bundles/osgi/modules][LicenseManager:?] License registered for Digital Enterprise Development
	``` 
	* The activation key xml file will vanish immediately from the `deploy` directory after it has been detected by Liferay's *AutoDeployScanner*. 
	
#### Log in to the Portal

1. **Sign in** to the portal with:
	* __Username:__ test@liferay.com
	* __Password:__ *The password you provided*

<img src="../images/checkpoint.png" style="max-height: 40%"/>
