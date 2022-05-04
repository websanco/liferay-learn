<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %>

<%@ page import="com.acme.x9k1.internal.commerce.order.rule.web.display.context.X9K1MinimumQuantityDisplayContext" %>

<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<%
X9K1MinimumQuantityDisplayContext x9k1MinimumQuantityDisplayContext = (X9K1MinimumQuantityDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="row">
	<div class="col">
		<commerce-ui:panel
			bodyClasses="flex-fill"
			title="Configuration"
		>
			<div class="row">
				<div class="col">
					<aui:input label="minimum-quantity" name="type--settings--minimum-quantity--" required="<%= true %>" type="text" value="<%= x9k1MinimumQuantityDisplayContext.getMinimumQuantity() %>">
						<aui:validator name="number" />
					</aui:input>
				</div>
			</div>
		</commerce-ui:panel>
	</div>
</div>