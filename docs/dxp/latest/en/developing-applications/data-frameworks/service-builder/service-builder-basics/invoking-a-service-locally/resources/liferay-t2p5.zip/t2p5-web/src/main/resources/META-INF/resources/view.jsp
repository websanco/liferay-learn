<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.acme.t2p5.model.Entry" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<h4>T2P5 Portlet</h4>

<hr />

<portlet:actionURL name="addEntry" var="addEntryURL" />

<h5>Add Entry</h5>

<aui:form action="<%= addEntryURL %>">
	<aui:input name="name" type="text" />
	<aui:input name="description" type="text" />

	<aui:button type="submit" value="submit" />
</aui:form>

<hr />

<h5>Entries</h5>

<%
List<Entry> entries = (List<Entry>)request.getAttribute("entries");
%>

<c:choose>
	<c:when test='<%= (entries != null) && ((int)request.getAttribute("entriesCount") > 0) %>'>
		<table>
			<tbody>
				<c:forEach items="<%= entries %>" var="entry">
					<tr>
						<td>${entry}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>None</em>
	</c:otherwise>
</c:choose>