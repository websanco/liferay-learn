<%@page import="com.acme.f5d5.web.internal.constants.F5D5WebKeys" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>

<portlet:defineObjects />

<%
String itemSelectorURL = String.valueOf(request.getAttribute(F5D5WebKeys.ITEM_SELECTOR_URL));
%>

<clay:button
	id='<%= liferayPortletResponse.getNamespace() + "selectRole" %>'
	label="Select"
/>

<script>
	var selectRoleButton = document.getElementById('<portlet:namespace />selectRole');

	selectRoleButton.addEventListener(
		'click',
		function(event) {
			var itemSelectorDialog = new Liferay.Util.openSelectionModal(
				{
					onSelect: function (selectedItem) {
						if (selectedItem) {
							// Use the selected item value(s) here.
							alert (selectedItem.value);
						}
					},
					selectEventName: 'selectRole',
					title: 'Select Role',
					url: '<%= itemSelectorURL %>'
				}
			);
		}
	);
</script>