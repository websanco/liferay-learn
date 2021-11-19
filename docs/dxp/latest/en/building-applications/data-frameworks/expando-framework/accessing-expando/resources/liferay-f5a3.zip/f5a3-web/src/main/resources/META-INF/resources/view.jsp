<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.expando.kernel.model.ExpandoBridge" %>

<liferay-theme:defineObjects />

<h4>F5A3 Portlet</h4>

<%
ExpandoBridge expandoBridge = user.getExpandoBridge();
%>

F5A3 Text: <%= expandoBridge.getAttribute("f5a3Text") %>