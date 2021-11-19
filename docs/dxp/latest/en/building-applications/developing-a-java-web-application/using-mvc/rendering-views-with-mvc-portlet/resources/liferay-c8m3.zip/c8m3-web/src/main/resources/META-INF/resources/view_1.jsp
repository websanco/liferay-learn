<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h4>C8M3 Portlet</h4>

<h5>View 1</h5>

<portlet:renderURL var="view2URL">
	<portlet:param name="mvcPath" value="/view_2.jsp" />
</portlet:renderURL>

<a href="<%= view2URL %>">Go to View 2</a>