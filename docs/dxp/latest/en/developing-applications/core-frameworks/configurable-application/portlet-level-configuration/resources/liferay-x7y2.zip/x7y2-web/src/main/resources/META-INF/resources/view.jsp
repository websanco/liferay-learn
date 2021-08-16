<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.acme.x7y2.web.internal.configuration.X7Y2WebPortletInstanceConfiguration" %>

<portlet:defineObjects />

<%
X7Y2WebPortletInstanceConfiguration x7y2WebPortletInstanceConfiguration = (X7Y2WebPortletInstanceConfiguration)request.getAttribute(X7Y2WebPortletInstanceConfiguration.class.getName());
String preference = (String)portletPreferences.getValue("color", "");

if (preference.equals("")) {
	preference = x7y2WebPortletInstanceConfiguration.color();
}
else {
	preference = (String)portletPreferences.getValue("color", "");
}
%>

Color: <%= preference %>