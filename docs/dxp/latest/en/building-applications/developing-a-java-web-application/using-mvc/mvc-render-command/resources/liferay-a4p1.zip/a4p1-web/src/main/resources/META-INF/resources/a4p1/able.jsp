<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h4>A4P1 Portlet</h4>

<h5>Able</h5>

<portlet:renderURL var="bakerURL">
	<portlet:param name="mvcRenderCommandName" value="/a4p1/baker" />
</portlet:renderURL>

<a href="<%= bakerURL %>">Go to Baker</a>