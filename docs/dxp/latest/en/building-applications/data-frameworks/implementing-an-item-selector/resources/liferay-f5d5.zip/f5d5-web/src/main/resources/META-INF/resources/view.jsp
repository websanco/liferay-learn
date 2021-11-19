<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>

<%@ page import="com.acme.f5d5.web.internal.constants.F5D5WebKeys" %>

<portlet:defineObjects />

<h4>F5D5 Portlet</h4>

<clay:button
	id='<%= liferayPortletResponse.getNamespace() + "selectRoleButton" %>'
	label="Select"
/>

<script>
	var selectRoleButton = document.getElementById('<portlet:namespace />selectRoleButton');

	selectRoleButton.addEventListener(
		'click',
		function(event) {
			new Liferay.Util.openSelectionModal(
				{
					onSelect: function (event) {
						alert(event.value);
					},
					selectEventName: '<portlet:namespace />selectRole',
					title: 'Select Role',
					url: '<%= request.getAttribute(F5D5WebKeys.ITEM_SELECTOR_URL) %>'
				}
			);
		}
	);
</script>