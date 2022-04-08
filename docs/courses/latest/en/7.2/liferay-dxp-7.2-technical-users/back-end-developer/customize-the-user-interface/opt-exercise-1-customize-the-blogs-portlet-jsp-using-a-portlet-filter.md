---
description: 8 - Customize the User Interface
title: Customize Blogs Portlet Entry View Using a Portlet Filter
order: 3
---

<h2 class="exercise">Optional Exercise</h2>

## Customize Blogs Portlet Entry View Using a Portlet Filter

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay Module project using the service template</li>
		<li>Declare dependencies</li>
		<li>Implement the RenderFilter class</li>
		<li>Final code review</li>
		<li>Deploy and test</li>
	</ul>
</div> 

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** command line shell in your Liferay Workspace `modules` folder.
1. **Run** command:
```bash
blade create -t service -p com.liferay.training.jspportletfilter -s javax.portlet.filter.RenderFilter -c BlogsRenderFilter jsp-portlet-filter
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use the Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  "jsp-portlet-filter"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: service
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "BlogsRenderFilter"
	* __Package Name__: "com.liferay.training.jspportletfilter"
	* __Service Name__: "javax.portlet.filter.RenderFilter"
1. **Click** the green plus button to add a property.
	* Enter "javax.portlet.name" for the *Name*.
	* Enter "resolveme* for the *Value*.
	> We are going to change this later in the exercise.
1. **Click** *Finish* to close the wizard

#### Declare Dependencies

Because we plan to use the `PortletFilter` interface, we have to depend on the `portlet-api` and the `javax.servlet-api` packages:

1. **Open** the `build.gradle`.
1. **Declare** the new dependencies as follows
```groovy
compileOnly group: "javax.portlet", name: "portlet-api"
compileOnly group: "javax.servlet", name: "javax.servlet-api"
```
  
#### Implement RenderFilter  
 
First we have to fix the `javax.portlet.name` component property value. We also have to change the service property to `PortletFilter` interface even though we are implementing it's extension RenderFilter:  
  
1. **Open** the class `com.liferay.training.jspportletfilter.BlogsRenderFilter`
1. **Configure** the `javax.portlet.name` property value and implement component properties as follows:
	```java
	@Component(
		immediate = true, 
		property = {
			"javax.portlet.name=" + PortletKeys.BLOGS
		}, 
		service = PortletFilter.class
	)
	```
	- Notice the [PortletKeys](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/util/PortletKeys.java) constants class. It contains names for many Liferay native portlets.
1. **Resolve** missing imports.
	- Choose `javax.portlet.filter.PortletFilter` when prompted for the right import.
1. **Implement** an inner class `StringResponseWrapper` at the end of the class to capture the Blogs Output:
  ```java
	private class StringResponseWrapper extends RenderResponseWrapper {

		public StringResponseWrapper(RenderResponse response) {

			super(response);

			_stringWriter = new StringWriter();
			_printWriter = new PrintWriter(_stringWriter);
		}

		public PrintWriter getWriter()
			throws IOException {

			return _printWriter;
		}

		public String toString() {

			return _stringWriter.toString();
		}

		private PrintWriter _printWriter;
		private StringWriter _stringWriter;
	}
	```
	- The StringResponseWrapper is responsible for capturing the output of the _Blogs_ portlet and preventing it from being written to the actual output stream. This gives us the opportunity to modify the output before it actually gets written to the portlet response.
1. **Put** the mouse over the class name (showing an error) and select *Add unimplemented methods* from the menu.	
1. **Implement** the doFilter() Method:
	```java
	@Override
	public void doFilter(
		RenderRequest request, RenderResponse response, FilterChain chain)
		throws IOException, PortletException {

		System.out.println("doFilter()");

		RenderResponseWrapper renderResponseWrapper =
			new StringResponseWrapper(response);

		chain.doFilter(request, renderResponseWrapper);

		String text = renderResponseWrapper.toString();

		if (text != null) {
			String interestingText = "<input  class=\"field form-control\"";

			int index = text.lastIndexOf(interestingText);

			if (index >= 0) {
				String newText1 = text.substring(0, index);

				String newText2 =
					"\n<p>StringResponseWrapper captures the output of the Blogs Portlet before " +
						"it gets written to the output stream. This give us the chance to manipulate" +
						" the output before sending it down the filter chain.</p>\n";

				String newText3 = text.substring(index);

				String newText = newText1 + newText2 + newText3;

				response.getWriter().write(newText);
			}
		}
	}
	```
1. **Resolve** missing imports.

#### Final Code Review

The complete implementation of `build.gradle` and the `BlogsRenderFilter`  will look like this: 

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "javax.portlet", name: "portlet-api"
	compileOnly group: "javax.servlet", name: "javax.servlet-api"
	compileOnly group: "org.osgi", name: "osgi.cmpn"
}
 ```

```java

package com.liferay.training.jspportletfilter;

import com.liferay.portal.kernel.util.PortletKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.RenderResponseWrapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + PortletKeys.BLOGS
	}, 
	service = PortletFilter.class
)
public class BlogsRenderFilter implements RenderFilter {

	@Override
	public void init(FilterConfig filterConfig)
		throws PortletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(
		RenderRequest request, RenderResponse response, FilterChain chain)
		throws IOException, PortletException {

		System.out.println("doFilter()");

		RenderResponseWrapper renderResponseWrapper =
			new StringResponseWrapper(response);

		chain.doFilter(request, renderResponseWrapper);

		String text = renderResponseWrapper.toString();

		if (text != null) {
			String interestingText = "<input  class=\"field form-control\"";

			int index = text.lastIndexOf(interestingText);

			if (index >= 0) {
				String newText1 = text.substring(0, index);

				String newText2 =
					"\n<p>StringResponseWrapper captures the output of the Blogs Portlet before " +
						"it gets written to the output stream. This give us the chance to manipulate" +
						" the output before sending it down the filter chain.</p>\n";

				String newText3 = text.substring(index);

				String newText = newText1 + newText2 + newText3;

				response.getWriter().write(newText);
			}
		}
	}

	private class StringResponseWrapper extends RenderResponseWrapper {

		public StringResponseWrapper(RenderResponse response) {

			super(response);

			_stringWriter = new StringWriter();
			_printWriter = new PrintWriter(_stringWriter);
		}

		public PrintWriter getWriter()
			throws IOException {

			return _printWriter;
		}

		public String toString() {

			return _stringWriter.toString();
		}

		private PrintWriter _printWriter;
		private StringWriter _stringWriter;
	}

}
```

#### Deploy and Test

1. **Deploy** the module to the Liferay server.
1. **Open** your browser to http://localhost:8080 and sign in.
1. **Click** the *Add* icon on the top right corner of the page.
1. **Add** *Blogs* widget to the page.

<img src="../images/blogs-filter.png" style="max-height:100%;"/>

#### Takeaways

Portlet filters can be used to do many different types of pre or post processing for portlets. In this case, you've seen that you can use a RenderFilter to modify the content of a portlet without having to change the code directly. Along with using Module Fragments, this gives you another option to customize the view layer of existing Liferay applications.
