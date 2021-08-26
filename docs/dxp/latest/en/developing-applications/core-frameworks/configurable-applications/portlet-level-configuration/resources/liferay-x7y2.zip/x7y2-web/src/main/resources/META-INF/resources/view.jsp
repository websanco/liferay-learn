<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.acme.x7y2.web.internal.configuration.X7Y2PortletInstanceConfiguration" %>

<portlet:defineObjects />

<%
String color = (String)portletPreferences.getValue("color", "");

if (color.equals("")) {
	X7Y2PortletInstanceConfiguration x7y2WebPortletInstanceConfiguration = (X7Y2PortletInstanceConfiguration)request.getAttribute(X7Y2PortletInstanceConfiguration.class.getName());

	color = x7y2WebPortletInstanceConfiguration.color();
}
%>

Color: <%= color %>