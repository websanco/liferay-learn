<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %>

<portlet:defineObjects />

<clay:button
	id='<%= liferayPortletResponse.getNamespace() + "selectRole" %>'
	label="Select"
/>

<%
String itemSelectorURL = String.valueOf(request.getAttribute("itemSelectorURL"));
%>

<aui:script require="frontend-js-web/liferay/ItemSelectorDialog.es as ItemSelectorDialog">
	var selectRoleButton = document.getElementById('<portlet:namespace />selectRole');

	selectRoleButton.addEventListener(
		'click',
		function(event) {
			var itemSelectorDialog = new ItemSelectorDialog.default(
				{
					eventName: 'selectItem',
					title: 'Select Role',
					url: '<%= itemSelectorURL %>'
				}
			);

			itemSelectorDialog.on(
				'selectedItemChange',
				function(event) {
					var selectedItem = event.selectedItem;

					if (selectedItem) {
						// Use the selected item value(s) here.
					}
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>