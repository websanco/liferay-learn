---
description:  3 - Liferay's OSGi Container
title: Using the Development Tools
order: 4
---

<h2 class="exercise">Optional Exercise</h2>

In this optional exercise, you can find tips and instructions for the training exercises. 

Always ask your trainer, if you are unsure about the exercise instructions.

#### Creating Modules from the Command Line

Be sure to be in the right folder when creating a module from the command line because the template generator behaves differently depending on the current location. If you are outside of the Liferay Workspace, the template generator creates an independently buildable module.

#### Creating Classes

When creating a new class or interface, you don't have to copy/enter the package name and class separately:

1. **Use** the Eclipe *File Menu -> New -> Class* or *SHIFT+ALT+N* keyboard shortcut to open the New Java Class dialog.
1. **Copy** the __full classpath__ from the exercise instructions and paste it to the `Name` field in the *New Class Dialog*. 

The path field will populate automatically.

#### Organizing Missing Imports

1. **Use** the *Source Menu -> Organize Imports* or keyboard shortcut CTRL+SHIFT+O to organize missing imports.

If you don't know, which imports to select, you can use the "Final Code Review" exercise step, when available, to check your choices. Always ask your trainer if unsure. Here are some of the most common cases:

* __Date:__ java.util.Date
* __Group:__ com.liferay.portal.kernel.model.Group
* __List:__ java.util.List
* __StringPool:__ com.liferay.petra.string.StringPool
* __ServiceContext:__ com.liferay.portal.kernel.service.ServiceContext
* __Validator:__ com.liferay.portal.kernel.util.Validator

For the component annotations, use the `org.osgi.service` instead of `bnd` packages:

* org.osgi.service.component.annotations.Activate
* org.osgi.service.component.annotations.Component
* org.osgi.service.component.annotations.Modified 

#### Declaring Dependencies

When you declare new dependencies in the `build.gradle` they should refresh automatically. If this doesn't happen and you cannot fix the missing imports, you can force the refresh manually:

1. **Right-click** on the affected module or Liferay Workspace root folder, in the Eclipse *Project Explorer* view.
1. **Select** *Gradle -> Refresh Gradle Project*

#### Opening Classes and Resources for Editing

1. **Use** CTRL+SHIFT+R and type the class name to find the resource.

> Notice that you can use wildcards like *LocalService*.



