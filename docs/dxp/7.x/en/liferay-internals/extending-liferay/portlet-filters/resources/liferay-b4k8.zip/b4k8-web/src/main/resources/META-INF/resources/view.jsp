<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.acme.b4k8.web.internal.portlet.B4K8Portlet" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String iconsPath = themeDisplay.getPathThemeImages() + "/lexicon/icons.svg";
%>

<liferay-portlet:actionURL name="<%= B4K8Portlet.LOAD_USERS_ACTION %>" var="loadURL" />

<h3><liferay-ui:message key="render_filter_portlet.caption" /></h3>

<ul class="list-group show-quick-actions-on-hover">

	<c:if test="<%= renderRequest.getAttribute(B4K8Portlet.MEMBERLIST_ATTRIBUTE) != null %>">
		<h4 class="list-group-header-title"><liferay-ui:message key="render_filter_portlet.members.title" /></h4>

		<c:forEach items="<%= renderRequest.getAttribute(B4K8Portlet.MEMBERLIST_ATTRIBUTE) %>" var="user">
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col">
					<div class="sticker sticker-secondary">
						<span class="inline-item">
							<svg class="lexicon-icon lexicon-icon-users" focusable="false" role="presentation">
								<use xlink:href="<%= iconsPath %>#users" />
							</svg>
						</span>
					</div>
				</div>

				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate"><c:out value="${user.name}" /></p>
					<p class="list-group-subtitle text-truncate"><c:out value="${user.email}" /></p>
				</div>
			</li>
		</c:forEach>
	</c:if>
</ul>

<aui:button
	href="<%= loadURL %>"
	primary="true"
	value="render_filter_portlet.button.load_users"
/>