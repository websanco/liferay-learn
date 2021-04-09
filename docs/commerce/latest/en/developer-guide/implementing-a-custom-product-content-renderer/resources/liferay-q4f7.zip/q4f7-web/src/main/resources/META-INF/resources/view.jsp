<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.commerce.product.catalog.CPSku" %><%@
page import="com.liferay.commerce.product.content.constants.CPContentWebKeys" %><%@
page import="com.liferay.commerce.product.content.util.CPContentHelper" %>

<%
CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPSku cpSku = cpContentHelper.getDefaultCPSku(cpContentHelper.getCPCatalogEntry(request));
%>

<c:if test="<%= cpSku != null %>">
	SKU: <%= cpSku.getSku() %><br />
	Price: <%= cpSku.getPrice() %><br />
	Availability: <%= cpContentHelper.getAvailabilityLabel(request) %><br />
	Stock Quantity: <%= cpContentHelper.getStockQuantityLabel(request) %>
</c:if>

<liferay-util:dynamic-include key="com.liferay.commerce.product.content.web#/add_to_cart#" />