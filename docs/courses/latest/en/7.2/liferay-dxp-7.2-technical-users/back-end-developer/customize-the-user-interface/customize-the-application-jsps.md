---
description: 8 - Customize the User Interface
title: Customize the Application JSPs
order: 4
---

## Customize the Application JSPs

Widget Templates have limitations: not all applications support them, they are only implemented for modifying view templates, and you can only use them to customize _some_ parts of the JSP file. Sometimes, you need to customize the JSP directly. There are a few ways to do this, from API-based content injection to overriding the JSP files completely:

* Dynamic includes
* Custom JSP bag
* Fragment modules
* Portlet filters

#### Dynamic Includes {#dynamic}

Dynamic includes allow you to inject content in the JSP files in the pre-defined places implemented with the `<liferay-util:dynamic-include>` tag. Dynamic includes are supported, for example, in Wysiwyg editors, Login, Blogs, and Asset Publisher portlets.

Below is an example of a Dynamic Include Tag in the Blogs portlet:

```html
<%@ include file="/blogs/init.jsp" %>

<liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#pre" />

<%
	String redirect = ParamUtil.getString(request, "redirect");
	... />
```

- See the complete source: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/view_entry.jsp.

A custom Dynamic Include class for this tag would register to `com.liferay.blogs.web#/blogs/view_entry.jsp#pre`:

```java
@Component(
	immediate = true,
	service = DynamicInclude.class
)
public class BlogsDynamicInclude implements DynamicInclude {

	@Override
	public void include(HttpServletRequest request, HttpServletResponse response, String key) throws IOException {

		PrintWriter printWriter = response.getWriter();
		printWriter.println("<h2>Added by Blogs Dynamic Include</h2><br />");
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
			"com.liferay.blogs.web#/blogs/view_entry.jsp#pre");
	}
}
```

This would produce a customized Blogs entry view:

<img src="../images/dynamic-includes.png"  style="max-height:25%;" />

#### Custom JSP Bag {#jsp}

Custom JSP bags are a JSP customization method for legacy portal core JSP files. This method has only a few applicable use cases and is deprecated as of Liferay DXP 7.0. Read more information about it on the Developer Network: https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/jsp-overrides-using-custom-jsp-bag.

#### Fragment Modules {#fragment}

Fragment modules allow you to override entire JSPs. This is a fast and straightforward method, but it also requires attention to platform patches and upgrades because there might be breaking changes in the original JSP.

The process of creating a fragment module, which is equivalent to an *OSGi Fragment Bundle*, is simple:

1. Create a Liferay fragment module for the desired module JSP
1. Override the desired JSP in the same path as the original

#### Example: Overriding the Blogs Portlet `view_entry.jsp` {#blogs}

#### Step 1 - Create a Fragment Module {#one}

Choose the host bundle and file to override:

<img src="../images/fragment-example-1.png" style="max-height:40%;" />

#### Step 2 - Override the JSP {#two}

```html
<%@ include file="/blogs/init.jsp" %>

<h2>This is a JSP overridden by a Fragment Module.</h2>

<liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#pre" />

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcRenderCommandName", "/blogs/view");

	redirect = portletURL.toString();
}
```

#### Step 3 - Deploy and Test {#three}

<img src="../images/fragment-example-2.png" style="max-height:30%;" />

Fragment bundles always need a host bundle. In the example below, the generated `bnd.bnd` file could have looked like this:

```properties
Bundle-Name: JSP Fragment Module Example
Bundle-SymbolicName: com.liferay.training.jsp.fragment.module
Bundle-Version: 1.0.0
Fragment-Host: com.liferay.blogs.web;bundle-version="3.0.0" 
```

#### Portlet Filter {#filter}

The portlet filter is an API-based way of overriding the application JSPs. The portlet filter operates directly on the request and response content, giving access to all the content sent back to the client. 

Below is an example portlet render filter component for a Blogs portlet:

```java
@Component(
    immediate = true,
    property = {
         "javax.portlet.name=" + PortletKeys.BLOGS
    },
    service = PortletFilter.class
)
public class BlogsRenderFilter implements RenderFilter  {

	@Override
	public void doFilter(
		RenderRequest request, RenderResponse response, FilterChain chain)
		throws IOException, PortletException {

		// Stream manipulation code here.
	}
}
```

> See more information about this approach on the [Developer Network](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/jsp-overrides-using-portlet-filters).

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
    <li> In customization cases where there is no ADT support available or the support is not enough, you need to ________________.</li>
    <li> Dynamic includes allow you to ____________________ in the JSP files.</li>
    <li> __________________ allow you to override entire JSPs.</li>
    <li> ___________________ is an API-based and Liferay-recommended method of overriding the application JSPs.</li>
</ul>
</div>