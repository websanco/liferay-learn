---
description: 1 - Setting Up the Development Environment
title: Set Up the Development Environment on OSX
order: 2
---

<h2 class="exercise">Exercises</h2>

## Set Up the Development Environment on OSX

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Install the Developer Studio on OSX</li>
		<ul>
			<li>Install Java 8 JDK</li>
			<li>Install Liferay Developer Studio DXP</li>
		</ul>
	</ul>
</div>

<div class="note">
These instructions were made with OSX. Before starting, your trainer should have provided you with:
<ul>
	<li>A Developer Studio installation file</li>
	<li>A temporary license file</li>
</ul>
Please consult your trainer if either of these are missing.

You should also have credentials to log in to Liferay's website. If you don't have those, please register at <a href="https://web.liferay.com/sign-in">https://web.liferay.com/sign-in</a>.
</div>

#### Install Java 8 JDK

If you already have a Java 8 JDK installed, you can skip this step. Please note that a full JDK is required. You can check whether Java has been installed and its version in the _Terminal_ with:

```bash
java -version
```

If Java 8 is installed properly, the output should be something like:

```bash
java version "1.8.0_161"
Java(TM) SE Runtime Environment (build 1.8.0_161-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.161-b12, mixed mode)
```

To determine whether a full JDK is installed, type:

```bash
javac -version
```

If a full JDK is installed, output should be something like:

```bash
java version "1.8.0_161"
```

#### Install Java JDK on OSX

1. **Go to** http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html in your web browser.
1. **Find** the Java 8 JDK for your processor architecture.
* **Download** the `.dmg` file.
* **Go to** where the `.dmg` file was downloaded.
  * You will need to create an account with Oracle in order to download JDK 8.
* **Double-click** the package icon to launch the installer.
* **Open** the profile in order to add the `JAVA_HOME` environment variable to your user profile settings:
```bash
nano ~/.profile
```
* **Add** the following lines to the end of the file:
```bash
export JAVA_HOME="$(/usr/libexec/java_home -v 1.8)"
```
* **Type** in the following to make the settings effective in the current _Terminal_:
```bash
source ~/.profile
```
    * You can check that the path is correct by running:
    ```bash
    echo $JAVA_HOME
    ```
    * The output should point to your JDK installation folder.

#### Install Dev Studio DXP

1. **Run** the Dev Studio installer to start the installation process.
1. **Choose** the full Java 8 JDK as the Java runtime.
1. **Choose** the installation location for Dev Studio. Typically, we create a directory called `~\liferay` or `~\lfdev`.
1. **Type** your liferay.com credentials if prompted:
    <img src="../images/dxp-bundle-password.png" style="max-height:35%;" />
    <div class="note">
    Notice that your credentials are not saved locally; they’re saved as a token in the `~/.liferay` folder. The token is used by your workspace if you ever decide to redownload a DXP bundle. Furthermore, the bundle that is downloaded in your workspace is also copied to your `~/.liferay/bundles` folder, so if you decide to initialize another Liferay instance of the same version, the bundle is not re-downloaded. If you have previously set up Developer Studio on your machine, you may already have the `.liferay` folder and token on your system and will not see this step in the installer.
    </div>
    * After unpacking and installing, the setup should be ready:
    <img src="../images/dev-studio-setup-finish.png" style="max-height:35%;" />
1. **Start** Developer Studio. Choose an Eclipse workspace location and click *Launch*:
<img src="../images/workspace-location.png" style="max-height:35%;" />

Developer Studio is now set up and ready:
<img src="../images/checkpoint.png" style="max-height:35%" />
