<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h4>A4P1 Portlet</h4>

<h5>Baker</h5>

<portlet:renderURL var="ableURL">
	<portlet:param name="mvcRenderCommandName" value="/a4p1/able" />
</portlet:renderURL>

<a href="<%= ableURL %>">Go to Able</a>