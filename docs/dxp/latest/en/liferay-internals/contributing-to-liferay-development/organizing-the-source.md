# Organizing the Source 

It is important to have a solid understanding of how the Liferay source is organized when working on fixing a bug or adding a new feature to the product. The Liferay Source can also be a great source of reference when building custom projects because it provides a complete implementation of the various frameworks provided by the platform.

The Liferay Source is now organized using two main areas:

* **Portal Core** - These are parts of the original platform that have not been extracted to a module as of yet. They carry familiar names from Liferay 6.2 such as: portal-impl, portal-service and portal-web.
* **Modules** - Many features have now been extracted as modules and reside in the *liferay-portal/modules* directory. Many modules follow the Liferay MVC pattern and are organized in the relevant .api, .service and .web modules.

## More Information

To gain a deeper understanding of some of the patterns used in the Liferay Source, make sure to read the [Developing a Web Application tutorial](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/tutorials/developing-a-web-application) <!--change the link--> in the official Liferay docs.

## Blogs Widget

The following example uses the out of the box Blogs widget and will highlight some of the more relevant patterns in that application as well as provide links back to the documentation for specific sections in the code.

## BlogsPortlet <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/internal/portlet/BlogsPortlet.java`

*BlogsPortlet* follows the Liferay MVC pattern and is the main entry point for the Blogs Widget. One thing to note is the BlogsPortlet class does not list a view-template init param. More on that next. 

For more information, see the [Liferay MVC docs](https://help.liferay.com/hc/en-us/articles/360029028191-Liferay-MVC-Portlet).

## BlogsViewMVCRenderCommand <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/internal/portlet/BlogsViewMVCRenderCommand.java`

When a Portlet component does not list a *view-template*, this usually means that a MVCRenderCommand is what dispatches to the entry .jsp file. *BlogsViewMVCRenderCommand* handles the dispatch to */blogs/view.jsp* for both / and /blogs/view

For more information, see the [MVCRenderCommand docs](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/tutorials/mvc-render-command). <!--change the link--> 

## view.jsp <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/resources
/META-INF/resources/blogs/view.jsp`

Provides the main entry point for the blogs widget’s ui. [Developing a Web Application tutorial](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/tutorials/developing-a-web-application) <!--change the link--> has a great example for some entry level JSP development.

## Asset Renderer <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/asset/model`

Asset Renderers are a part of the Asset Framework and are used for rendering an asset using the Asset Publishing

For more information, see the [Asset Framework docs](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/tutorials/asset-framework). <!--change the link-->

## Panel Entry <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/internal/application/list/BlogsPanelApp.java`

Panel entries allow for widgets to be added to the product menu allowing for projects to have an administrative view.

For more information see the [Adding Custom Panels docs](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/tutorials/adding-custom-panel-apps.) <!--change the link-->

## Configuration <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/internal/configuration/BlogsPortletInstanceConfiguration.java`

Add configurable parameters to System Settings using the configuration component. This makes it easy to make a project configurable using out of the box functionality

For more info see creating [Configurable Apps](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/tutorials/configurable-applications) in the docs. <!--change the link-->

## Scheduler <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/internal/messaging/CheckEntryMessageListener.java`

Add scheduled tasks to any application. This is handy for if there is something that needs to run within an application periodically.

For more info see [Scheduler Blade Sample](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/extensions/scheduler-entry). <!--change the link-->

## FriendlyURL <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/internal/portlet/route/BlogsFriendlyURLMapper.java`

Friendly URLs allow applications to use more SEO friendly URLs for render and action urls.  

For more information on friendly urls see [Making URLs Friendlier](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/tutorials/making-urls-friendlier) in the docs. <!--change the link-->

## Dynamic Include <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/internal/servlet/taglib/BlogsPortletHeaderJSPDynamicInclude.java`

Dynamic includes allow for UI customizations with out the need for a Fragment bundle, this making it more upgrade friendly.

For more information see docs on using [dynamic includes](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/tutorials/dynamic-includes). <!--change the link-->

## Application Display Template <!--should be an h3?-->

`liferay-portal/modules/apps/blogs/blogs-web/src/main/java/
com/liferay/blogs/web/internal/template/BlogsPortletDisplayTemplateHandler.java`

Application Display Templates allow for the use of Freemarker templates in place of using a standard JSP. This makes ADTs more upgrade friendly then directly customizing the JSPs.

For more information see Implementing [Application Display Templates](https://portal.liferay.dev/develop/tutorials/-/knowledge_base/7-0/implementing-application-display-templates) in the docs. <!--change the link-->

## Application Display Template sample <!--should be an h3?-->

`/Users/jamie/Repos/liferay-portal/modules/apps/blogs/blogs-web/src/main/resources/
com/liferay/blogs/web/template/dependencies/portlet_display_template_basic.ftl`

Many out of the box applications come with basic Application Display Templates which can make getting started using them much easier. For example the blogs widget comes with a basic template to list blog entries and is similar to the JSP counterpart.

For more information on using ADTs see [Styling Widgets with ADTS](https://portal.liferay.dev/docs/7-1/user/tutorials/-/knowledge_base/user/styling-widgets-with-application-display-templates) <!--change the link--> in the docs.

These are only a few frameworks in use in the blogs widget. There are many more throughout the code. Most of the other widgets under modules also use this same pattern. So learning how one widget is organized will allow anyone to understand how most code is organized in the repo.