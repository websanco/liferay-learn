<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h4>C8M3 Portlet</h4>

<h5>View 2</h5>

<portlet:renderURL var="view1URL">
	<portlet:param name="mvcPath" value="/view_1.jsp" />
</portlet:renderURL>

<a href="<%= view1URL %>">Go to View 1</a>