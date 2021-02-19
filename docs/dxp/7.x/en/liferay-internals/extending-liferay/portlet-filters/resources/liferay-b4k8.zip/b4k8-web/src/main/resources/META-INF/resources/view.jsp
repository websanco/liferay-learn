<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.acme.b4k8.web.internal.constants.B4K8WebConstants" %>

<portlet:defineObjects />

<portlet:actionURL name="loadMembers" var="loadMembersURL">
</portlet:actionURL>

<h1>B4K8 Portlet</h1>

<ul>
	<c:if test="<%= renderRequest.getAttribute(B4K8WebConstants.MEMBERS) != null %>">
		<h4>Here are the team members!</h4>

		<c:forEach items="<%= renderRequest.getAttribute(B4K8WebConstants.MEMBERS) %>" var="member">
			<li>
				<div>
					<p><c:out value="${member.name}" /></p>
					<p><c:out value="${member.email}" /></p>
				</div>
			</li>
		</c:forEach>
	</c:if>
</ul>

<a href="<%= loadMembersURL %>">Load Members</a>