<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h1>Baker</h1>

<portlet:renderURL var="ableURL">
	<portlet:param name="mvcRenderCommandName" value="/a4p1/able" />
</portlet:renderURL>

<a href="<%= ableURL %>">Go to Able</a>