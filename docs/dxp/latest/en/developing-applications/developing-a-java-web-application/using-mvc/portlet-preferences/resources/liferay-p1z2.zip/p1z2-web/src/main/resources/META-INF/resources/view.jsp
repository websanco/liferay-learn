<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@page import="javax.portlet.PortletPreferences"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String preference = (String)portletPreferences.getValue("preference", "preference1");
%>

<h1><liferay-ui:message key="p1z2-portlet-welcome" /></h1>
<p>
	<liferay-ui:message key="preference" />: <liferay-ui:message key="<%= preference %>" />
</p>