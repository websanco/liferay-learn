<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%
String fontFamily = (String)request.getAttribute("fontFamily");
String fontColor = (String)request.getAttribute("fontColor");
int fontSize = (int)request.getAttribute("fontSize");
%>

<p
	style="font-family: <%= fontFamily %>; color: <%= fontColor %>; font-size: <%= fontSize %>pt;"
>
	<liferay-ui:message key="e3q3-porlet-welcome" /><br />
  color: <%= fontColor %><br />
	font-family: <%= fontFamily %><br />
	font-size: <%= fontSize %>
</p>