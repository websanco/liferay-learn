<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<h4>F5A3 Portlet</h4>

someText: <%= user.getExpandoBridge().getAttribute("someText") %>