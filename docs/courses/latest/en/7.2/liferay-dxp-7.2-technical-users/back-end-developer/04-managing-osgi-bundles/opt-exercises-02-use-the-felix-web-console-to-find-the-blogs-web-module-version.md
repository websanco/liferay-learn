---
description: 4 - Managing OSGi Bundles
title: Use the Felix Web Console to Find the Blogs Web Module Version
order: 2
---

<h2 class="exercise">Optional Exercise</h2>

## Use the Felix Web Console to Find the Blogs Web Module Version

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Use the Felix Web Console to find out the current version of the Blogs Web Module:</li>
		<ul>
			<li>Install Felix Web Console</li>
			<li>Find the Blogs Web module version</li>
		</ul>
	</ul>
</div>

<div class="note">
The <a href="http://felix.apache.org/documentation/subprojects/apache-felix-web-console.html">Apache Felix Web Console</a> is a good alternative for common bundle management and troubleshooting tasks. 
</div>

#### Install the Felix Web Console

Felix Web Console is an OSGi bundle that we have to install into the OSGi container.

1. **Login** to Gogo Shell
1. **Use** the `install` command to and note the bundle ID in the result output:
	```bash
	install http://www.apache.org/dist//felix/org.apache.felix.webconsole-4.3.8.jar
	```
1. **Start** the Web Console bundle:
	```
	start [BUNDLE_ID]
	```
1. **Open** your web browser to http://localhost:8080/o/system/console.
1. **Login** with the following credentials:
	* __Username__: admin
	* __Password__: admin

#### Find the Blogs Web Module Version  

1. **Enter** "com.liferay.blogs.web" in the *Filter* field
1. **Click** the arrow on the left side of the bundle name to open the information.

Here you can see the version number for the bundle.

<img src="../images/blogs-web.png" style="max-width: 100%"/>
