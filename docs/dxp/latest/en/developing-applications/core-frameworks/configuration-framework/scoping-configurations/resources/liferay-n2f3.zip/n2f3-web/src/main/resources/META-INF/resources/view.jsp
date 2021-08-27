<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.acme.n2f3.web.internal.configuration.N2F3WebConfiguration" %>

<h4>N2F3 Portlet</h4>

<%
N2F3WebConfiguration n2f3WebConfiguration = (N2F3WebConfiguration)request.getAttribute(N2F3WebConfiguration.class.getName());
%>

<p
	style="color: <%= n2f3WebConfiguration.fontColor() %>; font-family: <%= n2f3WebConfiguration.fontFamily() %>; font-size: <%= n2f3WebConfiguration.fontSize() %>pt;"
>
	<liferay-ui:message key="n2f3-portlet-welcome" /><br />
	color: <%= n2f3WebConfiguration.fontColor() %><br />
	font-family: <%= n2f3WebConfiguration.fontFamily() %><br />
	font-size: <%= n2f3WebConfiguration.fontSize() %>
</p>