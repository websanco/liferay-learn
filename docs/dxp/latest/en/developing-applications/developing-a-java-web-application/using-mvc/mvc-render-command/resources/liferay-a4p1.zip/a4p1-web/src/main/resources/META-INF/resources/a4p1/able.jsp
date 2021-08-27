<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h4>A4P1 Portlet</h4>

<b>Able</b>

<portlet:renderURL var="bakerURL">
	<portlet:param name="mvcRenderCommandName" value="/a4p1/baker" />
</portlet:renderURL>

<a href="<%= bakerURL %>">Go to Baker</a>