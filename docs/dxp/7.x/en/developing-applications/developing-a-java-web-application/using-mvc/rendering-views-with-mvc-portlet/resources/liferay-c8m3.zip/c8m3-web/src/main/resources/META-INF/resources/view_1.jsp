<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h1>View 1</h1>

<portlet:renderURL var="view2URL">
	<portlet:param name="mvcPath" value="/view_2.jsp" />
</portlet:renderURL>

<a href="<%= view2URL %>">Go to View 2</a>