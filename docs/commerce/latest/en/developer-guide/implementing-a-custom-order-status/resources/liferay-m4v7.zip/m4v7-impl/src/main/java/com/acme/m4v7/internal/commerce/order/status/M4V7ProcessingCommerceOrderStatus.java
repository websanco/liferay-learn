package com.acme.m4v7.internal.commerce.order.status;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.status.CommerceOrderStatus;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"commerce.order.status.key=" + CommerceOrderConstants.ORDER_STATUS_PROCESSING,
		"commerce.order.status.priority:Integer=50",
		"service.ranking:Integer=100"
	},
	service = CommerceOrderStatus.class
)
public class M4V7ProcessingCommerceOrderStatus implements CommerceOrderStatus {

	@Override
	public CommerceOrder doTransition(CommerceOrder commerceOrder, long userId)
		throws PortalException {

		commerceOrder.setOrderStatus(
			CommerceOrderConstants.ORDER_STATUS_PROCESSING);

		return _commerceOrderService.updateCommerceOrder(commerceOrder);
	}

	@Override
	public int getKey() {
		return CommerceOrderConstants.ORDER_STATUS_PROCESSING;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			locale,
			CommerceOrderConstants.getOrderStatusLabel(
				CommerceOrderConstants.ORDER_STATUS_PROCESSING));
	}

	@Override
	public int getPriority() {
		return 50;
	}

	@Override
	public boolean isComplete(CommerceOrder commerceOrder) {
		if (commerceOrder.isApproved() && !commerceOrder.isOpen() &&
			(commerceOrder.getOrderStatus() != 314159265)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isTransitionCriteriaMet(CommerceOrder commerceOrder)
		throws PortalException {

		if (commerceOrder.getOrderStatus() == 314159265) {
			return true;
		}

		return false;
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

}