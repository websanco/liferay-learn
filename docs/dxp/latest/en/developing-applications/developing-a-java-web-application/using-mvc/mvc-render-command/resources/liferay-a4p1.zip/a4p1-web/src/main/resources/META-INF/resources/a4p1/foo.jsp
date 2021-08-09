<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h1>Foo</h1>

<portlet:renderURL var="barURL">
	<portlet:param name="mvcRenderCommandName" value="/a4p1/bar" />
</portlet:renderURL>

<a href="<%= barURL %>">Go to Bar</a>