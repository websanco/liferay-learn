<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.acme.e4g5.model.E4G5Entry" %><%@
page import="com.acme.e4g5.service.E4G5EntryLocalServiceUtil" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<h2>E4G5 Portlet</h2>

<hr />

<h3>E4G5 Entries</h3>

<%
List<E4G5Entry> e4g5Entries = E4G5EntryLocalServiceUtil.getE4G5Entries(-1, -1);
%>

<c:choose>
	<c:when test="<%= (e4g5Entries != null) && (e4g5Entries.size() > 0) %>">
		<table>
			<tbody>
				<c:forEach items="<%= e4g5Entries %>" var="e4g5Entry">
					<tr>
						<td>${e4g5Entry}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>There are no E4G5 entries</em>
	</c:otherwise>
</c:choose>

<hr />

<h3>Add a E4G5 Entry</h3>

<portlet:actionURL name="addE4G5Entry" var="addE4G5EntryURL" />

<aui:form action="<%= addE4G5EntryURL %>">
	<aui:input name="name" type="text" />
	<aui:input name="description" type="text" />

	<aui:button type="submit" value="add" />
</aui:form>

<hr />

<h3>Update a E4G5 Entry</h3>

<portlet:actionURL name="updateE4G5Entry" var="updateE4G5EntryURL" />

<aui:form action="<%= updateE4G5EntryURL %>">
	<aui:input name="e4g5EntryId" type="text" />
	<aui:input name="name" type="text" />
	<aui:input name="description" type="text" />

	<aui:button type="submit" value="update" />
</aui:form>

<hr />

<h3>Delete a E4G5 Entry</h2>
<portlet:actionURL name="deleteE4G5Entry" var="deleteE4G5EntryURL" />

<aui:form action="<%= deleteE4G5EntryURL %>">
	<aui:input name="e4g5EntryId" type="text" />

	<aui:button type="submit" value="delete" />
</aui:form>