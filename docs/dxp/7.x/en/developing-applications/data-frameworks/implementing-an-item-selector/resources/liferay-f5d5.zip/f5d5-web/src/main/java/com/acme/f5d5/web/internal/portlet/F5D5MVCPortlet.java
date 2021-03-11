package com.acme.f5d5.web.internal.portlet;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.roles.item.selector.RoleItemSelectorCriterion;

import java.io.IOException;

import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=F5D5",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class F5D5MVCPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		RoleItemSelectorCriterion itemSelectorCriterion =
			new RoleItemSelectorCriterion();

		ItemSelectorReturnType[] returnTypeArray =
			new ItemSelectorReturnType[1];

		returnTypeArray[0] = new UUIDItemSelectorReturnType();

		List<ItemSelectorReturnType> itemSelectorReturnTypes =
			ListUtil.fromArray(returnTypeArray);

		itemSelectorReturnTypes.add(new UUIDItemSelectorReturnType());

		itemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			itemSelectorReturnTypes);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(renderRequest);

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "selectRole",
			itemSelectorCriterion);

		renderRequest.setAttribute("itemSelectorURL", itemSelectorURL);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private ItemSelector _itemSelector;

}