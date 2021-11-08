package com.acme.m4v7.internal.commerce.order.status;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.status.CommerceOrderStatus;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Locale;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"commerce.order.status.key=314159265",
		"commerce.order.status.priority:Integer=40"
	},
	service = CommerceOrderStatus.class
)
public class M4V7SchedulingCommerceOrderStatus implements CommerceOrderStatus {

	@Override
	public CommerceOrder doTransition(CommerceOrder commerceOrder, long userId)
		throws PortalException {

		commerceOrder.setOrderStatus(314159265);

		return _commerceOrderService.updateCommerceOrder(commerceOrder);
	}

	@Override
	public int getKey() {
		return 314159265;
	}

	@Override
	public String getLabel(Locale locale) {
		return "Scheduling";
	}

	@Override
	public int getPriority() {
		return 40;
	}

	@Override
	public boolean isComplete(CommerceOrder commerceOrder) {
		ExpandoBridge expandoBridge = commerceOrder.getExpandoBridge();

		String[] values = (String[])expandoBridge.getAttribute(
			"m4v7Scheduling");

		if (ArrayUtil.isEmpty(values)) {
			return false;
		}

		return Objects.equals(values[0], "Confirmed");
	}

	@Override
	public boolean isTransitionCriteriaMet(CommerceOrder commerceOrder)
		throws PortalException {

		if (commerceOrder.getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_PENDING) {

			return true;
		}

		return false;
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

}