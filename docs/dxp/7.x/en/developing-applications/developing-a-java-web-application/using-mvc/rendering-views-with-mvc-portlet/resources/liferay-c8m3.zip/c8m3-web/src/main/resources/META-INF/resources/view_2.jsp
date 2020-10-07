<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<p>
	<strong>View 2</strong>
</p>

<portlet:renderURL var="view1URL">
	<portlet:param name="mvcPath" value="/view_1.jsp" />
</portlet:renderURL>

<a href="<%= view1URL %>">Go to View 1</a>