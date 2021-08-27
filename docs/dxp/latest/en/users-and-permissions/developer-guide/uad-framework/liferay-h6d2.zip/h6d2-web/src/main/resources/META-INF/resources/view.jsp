<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.acme.h6d2.model.H6D2" %><%@
page import="com.acme.h6d2.service.H6D2LocalServiceUtil" %>

<%@ page import="java.util.List" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<h4>H6D2 Portlet</h4>

<portlet:actionURL name="addTodo" var="addTodoURL" />

<p>
	<aui:form action="<%= addTodoURL %>">
		<aui:input name="item" type="text" />

		<aui:button type="submit" value="submit" />
	</aui:form>
</p>

<%
List<H6D2> h6d2List = H6D2LocalServiceUtil.getH6D2s(-1, -1);
%>

<h5>Todos</h5>
<c:choose>
	<c:when test="<%= (h6d2List != null) && (h6d2List.size() > 0) %>">
		<table>
			<tbody>
				<c:forEach items="<%= h6d2List %>" var="h6d2">
					<tr>
						<td>${h6d2.todo }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>None</em>
	</c:otherwise>
</c:choose>