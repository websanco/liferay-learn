<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.acme.h6d2.model.Todo" %><%@
page import="com.acme.h6d2.service.TodoLocalServiceUtil" %>

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
List<Todo> todoList = TodoLocalServiceUtil.getTodos(-1, -1);
%>

<h5>Todos</h5>
<c:choose>
	<c:when test="<%= (todoList != null) && (todoList.size() > 0) %>">
		<table>
			<tbody>
				<c:forEach items="<%= todoList %>" var="todo">
					<tr>
						<td>${todo.name }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>None</em>
	</c:otherwise>
</c:choose>