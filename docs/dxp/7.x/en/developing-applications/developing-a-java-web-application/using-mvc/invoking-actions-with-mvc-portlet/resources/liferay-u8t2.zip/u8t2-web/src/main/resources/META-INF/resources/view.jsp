<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:actionURL name="doSomething" var="action1URL" />

<p>
	<a href="<%= action1URL %>">Invoke Action 1</a>
</p>

<p>
	<a href="<portlet:actionURL name="doSomethingElse" />">Invoke Action 2</a>
</p>

<p>
	<a href="<portlet:actionURL><portlet:param name="javax.portlet.action" value="doSomethingMore" /></portlet:actionURL>">Invoke Action 3</a>
</p>