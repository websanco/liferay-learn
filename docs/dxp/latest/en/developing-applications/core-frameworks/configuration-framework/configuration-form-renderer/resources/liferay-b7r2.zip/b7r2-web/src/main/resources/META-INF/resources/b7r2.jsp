<%@ taglib uri="http://liferay.com/tld/aui"prefix="aui" %>

<%@ page import="com.acme.b7r2.web.internal.configuration.B7R2WebConfiguration" %>

<%
B7R2WebConfiguration b7r2WebConfiguration = (B7R2WebConfiguration)request.getAttribute(B7R2WebConfiguration.class.getName());
%>

<p style="background-color: <%= b7r2WebConfiguration.b7r2Color() %>">
	B7R2 Color: <%= b7r2WebConfiguration.b7r2Color() %>
</p>

<aui:input name="b7r2Color" value="<%= b7r2WebConfiguration.b7r2Color() %>" />