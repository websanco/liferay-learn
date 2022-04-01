---
description: 1 - Setting Up the Development Environment
title: Set Up the Development Environment on Linux
order: 1
---

<h2 class="exercise">Exercises</h2>

## Set Up the Development Environment on Linux

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
These instructions were made with Ubuntu LTS 16.04. Before starting, your trainer should have provided you with:
<ul>
	<li>A Developer Studio installation file</li>
	<li>A temporary license file</li>
</ul>
Please consult your trainer if either of these are missing.

You should also have credentials to log in to Liferay's website. If you don't have those, please register at <a href="https://web.liferay.com/sign-in">https://web.liferay.com/sign-in</a>.
</div>

#### Install Java 8 JDK

If you already have a Java 8 JDK installed, you can skip this step. Please note that a full JDK is required. You can check whether Java has been installed and its version in the _Command Line_ with:

```bash
java -version
```

If Java 11 is installed properly, output should be something like:

```bash
java version "1.8.0_161"
Java(TM) SE Runtime Environment (build 1.8.0_161-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.161-b12, mixed mode)
```

To determine whether a full JDK is installed, type:

```shell
javac -version
```

If a full JDK is installed, output should be something like:

```shell
javac version "1.8.0_161"
```

If you need to install a JDK, you can use your Linux distribution's software management tools to install Java globally on your machine or download a JDK from Oracle's website and install it only for your user profile. The following describes the steps to install Java for your user profile only:

### Installing Java on Linux

1. **Open** your web browser and go to http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html.
1. **Choose** the Java JDK for your processor architecture and download the `.tar.gz` package.
	* You will need to create an account with Oracle in order to download JDK 8.
	<img src="../images/download-oracle-java.png" style="max-height:100%;" />
1. **Extract** the installation archive to your installation location. In this example, we will be using the `/opt` folder:
```bash
sudo tar -xzf jdk-8u172-linux-x64.tar.gz -C /opt/
```
1. **Add** a `JAVA_HOME` environment variable to your profile configuration in `~/.profile`:
```bash
export JAVA_HOME=[YOUR_JAVA_INSTALLATION_FOLDER]
export PATH="$PATH:$JAVA_HOME/bin"
```
1. **Refresh** your profile configuration by running:
```bash
source ~/.profile
```
1. **Check** that the setting was read:
```bash
echo $JAVA_HOME
```

The output should point to your JDK installation folder.

#### Install Dev Studio DXP

1. **Run** the Developer Studio installer script to start the installation process.
	* In case of problems, check that the file is executable.
1. **Select** a full Java JDK for Java runtime.
1. **Choose** `/home/[YOUR_USERNAME]/liferay` as the installation directory for Developer Studio:
1. **Type** your liferay.com credentials if prompted:
	<img src="../images/dxp-bundle-password.png" style="max-height:25%;" />
	* After unpacking and installing, the setup should be ready:
	<img src="../images/dev-studio-setup-finish.png" style="max-height:25%;" />
	<div class="note">
	Notice that your credentials are not saved locally; they’re saved as a token in the `~/.liferay` folder. The token is used by your workspace if you ever decide to redownload a DXP bundle. Furthermore, the bundle that is downloaded in your workspace is also copied to your `~/.liferay/bundles` folder, so if you decide to initialize another Liferay instance of the same version, the bundle is not re-downloaded. If you have previously set up Developer Studio on your machine, you may already have the `.liferay` folder and token on your system and will not see this step in the installer.
	</div>
1. **Start** Developer Studio by replacing the path below with your installation path:
```bash
~/liferay/liferay-developer-studio/DeveloperStudio
```
1. **Choose** `/home/[YOUR_USERNAME]/liferay/eclipse-workspace` as your Eclipse workspace location and click *Launch*:
<img src="../images/workspace-location.png" style="max-height:16%;" />

<br />

Developer Studio is now set up and ready:
<img src="../images/checkpoint.png" style="max-height:30%;" />
