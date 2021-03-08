<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:actionURL var="action1URL">
	<portlet:param name="javax.portlet.action" value="action1" />
</portlet:actionURL>

<portlet:actionURL var="action2URL">
	<portlet:param name="javax.portlet.action" value="action2" />
</portlet:actionURL>

<a href="<%= action1URL %>">Invoke Action 1</a><br />
<a href="<%= action2URL %>">Invoke Action 2</a><br />