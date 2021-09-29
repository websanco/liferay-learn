<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.acme.h6d2.model.H6D2Entry" %><%@
page import="com.acme.h6d2.service.H6D2EntryLocalServiceUtil" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<h4>H6D2 Portlet</h4>

<hr />

<h5>Add H6D2 Entry</h5>

<portlet:actionURL name="addH6D2Entry" var="addH6D2EntryURL" />

<aui:form action="<%= addH6D2EntryURL %>">
	<aui:input name="name" type="text" />

	<aui:button type="submit" value="submit" />
</aui:form>

<hr />

<h5>Entries</h5>

<%
List<H6D2Entry> h6d2Entries = H6D2EntryLocalServiceUtil.getH6D2Entries(-1, -1);
%>

<c:choose>
	<c:when test="<%= h6d2Entries.size() > 0 %>">
		<table>
			<tbody>
				<c:forEach items="<%= h6d2Entries %>" var="entry">
					<tr>
						<td>${entry}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>There are no H6D2 entries.</em>
	</c:otherwise>
</c:choose>