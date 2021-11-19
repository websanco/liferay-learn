<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.acme.t2p5.model.T2P5Entry" %><%@
page import="com.acme.t2p5.service.T2P5EntryLocalServiceUtil" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<h4>T2P5 Portlet</h4>

<hr />

<h5>Add T2P5 Entry</h5>

<portlet:actionURL name="addT2P5Entry" var="addT2P5EntryURL" />

<aui:form action="<%= addT2P5EntryURL %>">
	<aui:input name="name" type="text" />

	<aui:input name="description" type="text" />

	<aui:button type="submit" value="submit" />
</aui:form>

<hr />

<h5>T2P5 Entries</h5>

<%
List<T2P5Entry> t2p5Entries = T2P5EntryLocalServiceUtil.getT2P5Entries(-1, -1);
%>

<c:choose>
	<c:when test="<%= t2p5Entries.size() > 0 %>">
		<table>
			<tbody>
				<c:forEach items="<%= t2p5Entries %>" var="entry">
					<tr>
						<td>${entry}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>There are no T2P5 entries.</em>
	</c:otherwise>
</c:choose>