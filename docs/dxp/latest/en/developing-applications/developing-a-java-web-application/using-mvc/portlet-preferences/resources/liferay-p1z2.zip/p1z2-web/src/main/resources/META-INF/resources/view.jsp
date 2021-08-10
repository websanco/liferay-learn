<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<h1><liferay-ui:message key="p1z2-portlet-welcome" /></h1>
<p>
	<liferay-ui:message key="preference" />: <liferay-ui:message key='<%= (String)portletPreferences.getValue("preference", "preference1") %>' />
</p>