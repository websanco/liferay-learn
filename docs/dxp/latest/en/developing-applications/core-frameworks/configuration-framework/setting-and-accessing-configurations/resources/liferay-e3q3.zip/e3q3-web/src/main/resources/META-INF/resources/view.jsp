<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.acme.e3q3.web.internal.configuration.E3Q3WebConfiguration" %>

<h4>E3Q3 Portlet</h4>

<%
E3Q3WebConfiguration e3q3WebConfiguration = (E3Q3WebConfiguration)request.getAttribute(E3Q3WebConfiguration.class.getName());
%>

<p
	style="color: <%= e3q3WebConfiguration.fontColor() %>; font-family: <%= e3q3WebConfiguration.fontFamily() %>; font-size: <%= e3q3WebConfiguration.fontSize() %>pt;"
>
	<liferay-ui:message key="e3q3-portlet-welcome" /><br />
	color: <%= e3q3WebConfiguration.fontColor() %><br />
	font-family: <%= e3q3WebConfiguration.fontFamily() %><br />
	font-size: <%= e3q3WebConfiguration.fontSize() %>
</p>