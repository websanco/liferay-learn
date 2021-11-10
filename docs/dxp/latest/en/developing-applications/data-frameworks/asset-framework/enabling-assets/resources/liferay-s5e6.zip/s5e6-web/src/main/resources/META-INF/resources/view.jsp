<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.acme.s5e6.model.S5E6Entry" %><%@
page import="com.acme.s5e6.service.S5E6EntryLocalServiceUtil" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<h4>S5E6 Portlet</h4>

<hr />

<h5>Add S5E6 Entry</h5>

<portlet:actionURL name="addS5E6Entry" var="addS5E6EntryURL" />

<aui:form action="<%= addS5E6EntryURL %>">
	<aui:input name="name" type="text" />

	<aui:input name="description" type="text" />

	<aui:button type="submit" value="submit" />
</aui:form>

<hr />

<h5>S5E6 Entries</h5>

<%
List<S5E6Entry> s5e6Entries = S5E6EntryLocalServiceUtil.getS5E6Entries(-1, -1);
%>

<c:choose>
	<c:when test="<%= s5e6Entries.size() > 0 %>">
		<table>
			<tbody>
				<c:forEach items="<%= s5e6Entries %>" var="entry">
					<tr>
						<td>${entry}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>There are no S5E6 entries.</em>
	</c:otherwise>
</c:choose>