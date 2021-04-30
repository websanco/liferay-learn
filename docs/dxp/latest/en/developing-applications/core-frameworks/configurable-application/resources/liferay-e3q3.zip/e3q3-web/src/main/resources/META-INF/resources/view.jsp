<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%
Object fontFamily = request.getAttribute("fontFamily");
Object fontColor = request.getAttribute("fontColor");
Object fontSize = request.getAttribute("fontSize");
%>

<p
	style="font-family: <%= fontFamily %>; color: <%= fontColor %>; font-size: <%= fontSize %>pt;"
>
	<liferay-ui:message key="e3q3-porlet-welcome" /><br />
	font-family: <%= fontFamily %><br />
	color: <%= fontColor %><br />
	font-size: <%= fontSize %>
</p>