---
description:  3 - Liferay's OSGi Container
title: Introducing Liferay Modules
order: 3
---

## Introducing Liferay Modules

Since Liferay 7.0 / DXP, all Liferay plugins have been called *modules*. Technically, a module is a project type, but after being built and deployed, it becomes an OSGi *bundle* running in the OSGi container. In Liferay terminology, the term *module* is used to refer to both.	

Liferay provides a comprehensive set of project, module, and component templates you can use to generate project stubs and classes. 

#### Project Templates

<img src="../images/liferay-project-templates.png" style="max-height:22%;" />

#### Module Templates

A module template creates the basic project skeleton with default settings for the deployable components. When you start a new Liferay module project, you can choose from a selection of templates. Generally speaking, if you are interested only in a blank module project, the API template is a good choice, since only a minimal structure is generated. 

<img src="../images/module-templates.png" style="max-height:20%;" />

The project skeleton comprises:

* The folder structure
* bnd.bnd
* build.gradle
* The component class stub
* The default component properties for the template component(s)

> Notice that while you can still create legacy Java EE style WAR plugins, these plugins are automatically converted to OSGi bundles on deploy time by the WAB plugin. For more information on the different templates, please see this [Developer Network article](https://dev.liferay.com/develop/reference/-/knowledge_base/7-1/project-templates).

#### Component Templates

The Liferay component wizard provides a selection of common component templates. You can use this wizard to add components to your existing project:

<img src="../images/component-templates.png" style="max-height: 30%"/>

#### Creating Modules from the Command Line

Modules can also be created from the _Command Line_ with the Blade CLI command line tool and Maven: 

List available templates from Blade CLI:
```
blade create -l
```

Create an MVC portlet from Blade CLI:
```
blade create -t mvc-portlet training-portlet
```

List the project template archetypes with Maven. 
Liferay project templates are prefixed with *com.liferay.project.templates*:

```
mvn archetype:generate -Dfilter=liferay
```

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>A _________________________ creates the basic project skeleton with default settings for the deployable components.</li>
	<li>Use the ____________________________ to add components to your existing project.</li>
	<li>Module Templates can be created with ____________________.</li>
</ul>
</div>