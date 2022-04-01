---
description: 4 - Managing OSGi Bundles
title: Use the Felix Web Console to Locate MVCRender Command Components for the Blogs Portlet
order: 3
---

<h2 class="exercise">Optional Exercise</h2>

## Use the Felix Web Console to Locate MVCRender Command Components for the Blogs Portlet

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Use the Apache Felix Web Console tool to locate all the MVC command components listening to the action path starting with <code>/blogs</code></li>
	</ul>
</div>

<br />

> In case you didn't do the previous optional exercise *Use the Felix Web Console to Find the Blogs Web Module Version*, use the instructions there to install the Felix Web Console before proceeding.

## Find MVC Command Component

1. **Open** your web browser to http://localhost:8080/o/system/consoler.
1. **Login** with the following credentials:
	* __Username__: admin
	* __Password__: admin
1. **Go** to *OSGi → Services* in the main menu.
	<img src="../images/services-menu.png" style="max-width: 100%"/>
1. **Enter** the LDAP style filter command in the *Filter* field:
	```bash
	(mvc.command.name=/blogs*)
	```

The output will show you all the [MVCRenderCommand](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.java) components having the componen property `mvc.command.name=/blog`:	

<img src="../images/blogs-commands.png" style="max-width: 100%"/>
