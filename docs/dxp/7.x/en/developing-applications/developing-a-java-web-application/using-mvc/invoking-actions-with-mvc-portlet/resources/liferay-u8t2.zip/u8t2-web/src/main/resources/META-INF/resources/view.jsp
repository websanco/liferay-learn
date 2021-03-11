<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:actionURL name="processAction1" var="action1URL" />

<p>
	<a href="<%= action1URL %>">Invoke Action 1</a>
</p>

<p>
	<a href="<portlet:actionURL><portlet:param name="javax.portlet.action" value="action2" /></portlet:actionURL>">Invoke Action 2</a>
</p>

<p>
	<a href="<portlet:actionURL name="processAction3" />">Invoke Action 3</a>
</p>