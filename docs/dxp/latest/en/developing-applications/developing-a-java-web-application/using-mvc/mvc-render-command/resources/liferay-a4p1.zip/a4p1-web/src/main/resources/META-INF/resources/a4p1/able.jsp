<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h1>Able</h1>

<portlet:renderURL var="bakerURL">
	<portlet:param name="mvcRenderCommandName" value="/a4p1/baker" />
</portlet:renderURL>

<a href="<%= bakerURL %>">Go to Baker</a>