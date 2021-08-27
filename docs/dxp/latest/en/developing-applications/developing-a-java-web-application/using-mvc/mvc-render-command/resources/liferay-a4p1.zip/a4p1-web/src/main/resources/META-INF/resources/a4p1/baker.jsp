<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h4>A4P1 Portlet</h4>

<b>Baker</b>

<portlet:renderURL var="ableURL">
	<portlet:param name="mvcRenderCommandName" value="/a4p1/able" />
</portlet:renderURL>

<a href="<%= ableURL %>">Go to Able</a>