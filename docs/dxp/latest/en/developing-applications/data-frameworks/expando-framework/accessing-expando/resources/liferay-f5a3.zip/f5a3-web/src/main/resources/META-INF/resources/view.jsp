<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<liferay-theme:defineObjects />

<h4>F5A3 Portlet</h4>

someText: <%= user.getExpandoBridge().getAttribute("someText") %>