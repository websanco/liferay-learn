---
description: 1 - Setting Up the Development Environment
title: Set Up the Development Environment on Windows
order: 3
---

<h2 class="exercise">Exercises</h2>

## Set Up the Development Environment on Windows 10

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Install the Developer Studio on Linux</li>
		<ul>
			<li>Install Java 8 JDK</li>
			<li>Install Liferay Developer Studio DXP</li>
		</ul>
	</ul>
</div>

<div class="note">
These instructions were made with Windows 10. Before starting, your trainer should have provided you with:
<ul>
	<li>A Developer Studio installation file</li>
	<li>A temporary license file</li>
</ul>
Please consult your trainer if either of these are missing.

You should also have credentials to log in to Liferay's website. If you don't have those, please register at <a href="https://web.liferay.com/sign-in">https://web.liferay.com/sign-in</a>.
</div>

#### Install Java 8 JDK

If you already have a Java 8 JDK installed, you can skip this step. Please note that a full JDK is required. You can check whether Java has been installed and its version in your Command Prompt with:

```bash
java -version
```

If Java 8 is installed properly, the output should be something like:

```bash
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
```

To determine whether a full JDK is installed, type:

```bash
javac -version
```

If a full JDK is installed, output should be something like:

```bash
javac 1.8.0_181
```

#### Install Java on Windows

1. **Go to** http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html.
2. **Download** the Java 8 JDK for your processor architecture (usually Windows x64).
	* You will need to create an account with Oracle in order to download JDK 8.
1. **Run** the JDK installer, leaving all default options.
1. **Go to** *System Properties* and click on the *Advanced* tab.
1. **Click** on the *Environment Variables*.
1. **Create** a new system variable called `JAVA_HOME`.
1. **Set** the *Variable value* to the path to your JDK, for example:
```
C:\Program Files\Java\jdk1.8.0_181
```
1. **Add** `%JAVA_HOME%/bin` to your `Path` system variable:
<img src="../images/windows-path.png" style="max-height:35%" />

#### Install Dev Studio DXP

1. **Run** the Developer Studio installer to start the installation process.
	* A notification window may appear with regard to an unrecognized third-party app. If it does, click *More Info → Run Anyway*.
1. **Choose** the Java JDK and click *OK*:
1. **Choose** `C:\liferay` as the installation location for Developer Studio:
	<img src="../images//lds-location-windows.png" style="max-height:35%" />
1. **Click** _Next_ to install all Developer Studio components. (Leave _Install Command Line Tools_ checked).
1. **Enter** your credentials for liferay.com.
	<img src="../images//dxp-bundle-password-windows.png" style="max-height:35%" />
	* <div class="note">
	Notice that your credentials are not saved locally; they’re saved as a token in the `~/.liferay` folder. The token is used by your workspace if you ever decide to re-download a DXP bundle. Furthermore, the bundle that is downloaded in your workspace is also copied to your `~/.liferay/bundles` folder, so if you decide to initialize another Liferay instance of the same version, the bundle is not re-downloaded. If you have previously set up Developer Studio on your machine, you may already have the `.liferay` folder and token on your system and will not see this step in the installer.
	</div>
	* After unpacking and installing, the setup should be ready. Feel free to delete the auto-generated *liferay-workspace* created when running the installer. We'll be importing a custom training workspace later on.
1. **Double-click** the Developer Studio `.exe` located in `C:\liferay\liferay-developer-studio` to run the program.
1. **Choose** `C:\liferay\eclipse-workspace` as the Eclipse workspace location.
<img src="../images/workspace-location-windows.png" style="max-height:35%" />

Developer Studio is now set up and ready:
<img src="../images/checkpoint.png" style="max-height:35%" />
