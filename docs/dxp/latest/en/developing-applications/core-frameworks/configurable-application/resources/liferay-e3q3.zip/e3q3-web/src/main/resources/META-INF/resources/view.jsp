<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.acme.e3q3.web.internal.configuration.E3Q3WebConfiguration" %>

<%
E3Q3WebConfiguration configuration = (E3Q3WebConfiguration)request.getAttribute(E3Q3WebConfiguration.class.getName());
%>

<p
	style="color: <%= fontColor %>; font-family: <%= fontFamily %>; font-size: <%= fontSize %>pt;"
>
	<liferay-ui:message key="e3q3-porlet-welcome" /><br />
	color: <%= configuration.fontColor() %><br />
	font-family: <%= configuration.fontFamily() %><br />
	font-size: <%= configuration.fontSize() %>
</p>