<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<portlet:defineObjects />

<h4>P1Z2 Portlet</h4>

Color: <%= (String)portletPreferences.getValue("color", "blue") %>