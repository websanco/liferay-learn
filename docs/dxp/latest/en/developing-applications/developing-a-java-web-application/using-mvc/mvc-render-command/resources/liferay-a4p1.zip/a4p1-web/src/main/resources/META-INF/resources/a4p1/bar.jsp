<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h1>Bar</h1>

<portlet:renderURL var="fooURL">
	<portlet:param name="mvcRenderCommandName" value="/a4p1/foo" />
</portlet:renderURL>

<a href="<%= fooURL %>">Go to Foo</a>