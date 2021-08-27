<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:actionURL name="doSomething" var="actionURL" />

<h4>U8T2 Portlet</h4>

<p>
	<a href="<%= actionURL %>">Do Something</a>
</p>

<p>
	<a href="<portlet:actionURL name="doSomethingElse" />">Do Something Else</a>
</p>

<p>
	<a href="<portlet:actionURL><portlet:param name="javax.portlet.action" value="nameForTheDoSomethingMoreMethod" /></portlet:actionURL>">Do Something More</a>
</p>