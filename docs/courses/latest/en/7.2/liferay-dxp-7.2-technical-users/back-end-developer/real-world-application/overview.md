---
description: 06 - Real World Application
title: Overview
order: 2
---

## Overview of Developing a Real World Application

In this chapter, we'll create a real-world Liferay application using Liferay's time-saving tools and frameworks. 

In the required exercise steps, we'll build an assignment management system for a course gradebook. First, we'll define the Assignment entity and use the Liferay Service Builder code generation tool to create the database and service layer for it. Then, we'll create the user interface using the JSP technology and taking advantage of Liferay's tag libraries. Finally, we'll implement access control and integrate to Liferay's Asset and Search frameworks.

In the optional exercises we'll extend the application to support assignment submissions and grading, make the application configurable, enable workflows for the assignments, create a REST service, implement integration tests, and learn how to debug and manage deployment issues.

Features and requirements for the Gradebook application:

#### Application Features

* Teachers can create assignments.
* Students can send submissions to assignments.
* Teachers can grade the submissions.

#### Functional Requirements

* Assignments have to be listable in the __Asset Publisher__ portlet
* Assignments have to be __searchable__ with portal search
* Both assignments and submissions have be under __access control__
* The Application has to be __configurable__
* The application has to support __localization__

#### Non-Functional Requirements

* The application has to be __modular__
* Data has to be __persisted__ in the database
* Form submissions have to be __validated__
* There has to be basic level __integration tests__

#### Development Technologies

We will build the model and service layer of the Gradebook application using the *Liferay Service Builder* code generation tool, which greatly reduces the need for boilerplate coding, automatically creates the persistence layer, implements caching, and creates both local and remote service APIs.

Whether you'd prefer pure Java EE or OSGi, Liferay provides several options for front-end implementation. Blade portlet samples are available for many of them:

* https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/apps
* https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/wars


In our exercise application we will use the classic Liferay MVC portlet template with JSPs because of the many useful tag libraries available for this use case.

Like Liferay core applications, this application will follow the MVC design pattern:

<img src="../images/mvc-pattern.png" style="max-height: 45%" />

#### Module Architecture

The application will be divided into four modules:

* __gradebook-api:__ the service layer API
* __gradebook-service:__ the service layer implementation
* __gradebook-web:__ the user interface with portlet component

In the optional exercises, we will also create an integration test module called *gradebook-test*.

#### Implementation Steps Overview

The main implementation steps are:

1. Create the API and service modules and define the data model
1. Create the portlet module and implement the user interface
1. Implement access control
1. Integrate with portal Asset and Search frameworks
