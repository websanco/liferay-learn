---
description: 8 - Customize the User Interface
title: Change the Default Application UI with Widget Templates
order: 3
---

## Change the Default Application UI with Widget Templates

Widget Templates, which in pre-7.2 versions were known as Application Display Templates (ADT), are a templating framework that allows you to customize and override portal application user interfaces. Native portlets supporting the Widget Templates are:

* Asset Publisher
* Asset Categories Navigation
* Asset Tags Navigation
* Blogs
* Media Gallery
* RSS
* Breadcrumb
* Language
* Navigation Menu
* SiteMap
* Wiki

> As of 7.1, portal applications are now called *Widgets*. From a back-end developer's perspective, these widgets are internally implemented as portlets, so we use the term *portlet* throughout this course.

Some example use cases for Widget Templates:

* Create a custom list layout for the Asset Publisher
* Create a custom item view layout for the Asset Publisher
* Create a custom layout for navigation
* Add audience targeting support for a navigation layout

Widget templates can be managed in the *Control Panel* in *Site Builder → Widget Templates* or from a portlet's configuration menu. Templates can exist both in the portal global and in the site scope:

<img src="../images/adt-management-from-portlet.png" style="max-height:30%;" />

A variety of Liferay utilities and services are available for Widget Templates. Notice, however, that access to some variables is restricted by default. In order to consume the Liferay service API via ADTs, users must be given access to the __serviceLocator__ utility. Access is controlled in *Control Panel → Configuration → System Settings →  Template Engines → FreeMarker Engine*.

Some common variables are available for the templates, including:

* currentUrl
* locale
* themeDisplay
* portletPreferences
* templateId
* renderRequest

Although both Velocity and FreeMarker templating languages are supported for implementing the templates, using FreeMarker is recommended. 

<img src="../images/adt-example.png" style="max-height:30%;" />

#### Adding Widget Template Support for Custom Applications {#support}

Widget Template support can be implemented in custom applications. Here is an overview of the steps required:

1. Create a service component extending the [BasePortletDisplayTemplateHandler](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/portletdisplaytemplate/BasePortletDisplayTemplateHandler.java).
1. Define template permissions using the `default.xml`.
1. Create the configuration JSP using `<liferay-ui:ddm-template-selector>`.
1. Render on the JSP using `<liferay-ddm:template-renderer>`.

Custom Template Handers components can be created for introducing custom objects and variables for the templates. See an example of the default [Wiki portlet handler](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/portlet/template/WikiPortletDisplayTemplateHandler.java).

> For more information, visit Liferay Developer Network (https://dev.liferay.com/en/discover/portal/-/knowledge_base/7-2/creating-display-templates).

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
    <li> ____________________________ is a templating framework that allows you to customize the portlet UI.</li>
    <li>Application Display Templates should be implemented using the ____________________________ template language.</li>
    <li>To consume the Liferay service API via ADTs, users must be given access to the ____________________________ utility.</li>
    <li> Application Display Template support can also be implemented in ____________________________.</li>
</ul>
</div>