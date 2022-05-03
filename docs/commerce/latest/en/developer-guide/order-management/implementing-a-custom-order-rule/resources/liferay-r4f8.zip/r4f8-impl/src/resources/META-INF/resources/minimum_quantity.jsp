
<%@page import="com.liferay.commerce.order.rule.web.display.context.R4F8MinimumQuantityDisplayContext" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.acme.r4f8.internal.commerce.order.rule.constants.R4F8MinimumQuantityCOREntryConstants" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ include file="/init.jsp" %>

<%
R4F8MinimumQuantityDisplayContext r4f8MinimumQuantityDisplayContext = (R4F8MinimumQuantityDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="row">
    <div class="col">
        <commerce-ui:panel
                bodyClasses="flex-fill"
                title="Configuration"
        >
        
        	<div class="row">
        		<div class="col">
					<aui:input label="maximum-container-volume-amount" name='<%= "type--settings--" + R4F8MinimumQuantityCOREntryConstants.TYPE_MINIMUM_QUANTITY + "--" %>' required="<%= true %>" type="text" value="<%= r4f8MinimumQuantityDisplayContext.getMinimumQuantity() %>">
						<aui:validator name="number" />
					</aui:input>
				</div>
			</div>
				
        </commerce-ui:panel>
    </div>
</div>