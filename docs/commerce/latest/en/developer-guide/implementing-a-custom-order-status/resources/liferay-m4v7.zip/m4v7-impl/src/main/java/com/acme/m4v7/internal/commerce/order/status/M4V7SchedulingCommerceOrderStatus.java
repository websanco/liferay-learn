package com.acme.m4v7.internal.commerce.order.status;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.status.CommerceOrderStatus;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

@Component(
	property = {
		"commerce.order.status.key=31415926535",
		"commerce.order.status.priority:Integer=40"
	},
	service = CommerceOrderStatus.class
)
public class M4V7SchedulingCommerceOrderStatus implements CommerceOrderStatus {

	@Override
	public CommerceOrder doTransition(CommerceOrder commerceOrder, long userId)
		throws PortalException {

		commerceOrder.setOrderStatus(31415926535);

		return _commerceOrderService.updateCommerceOrder(commerceOrder);
	}

	@Override
	public int getKey() {
		return 31415926535;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "scheduling");
	}

	@Override
	public int getPriority() {
		return 40;
	}

	@Override
	public boolean isComplete(CommerceOrder commerceOrder) {
		ExpandoBridge expandoBridge = commerceOrder.getExpandoBridge();

		List<String> list = _toList(
			expandoBridge.getAttribute("m4v7Scheduling"));

		if (list.isEmpty()) {
			return false;
		}

		return Objects.equals(list.get(0), "confirmed");
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

	private List<String> _toList(Object object) {
		if (object instanceof Collection) {
			Collection<?> collection = (Collection<?>)object;

			if (!collection.isEmpty()) {
				Iterator<?> iterator = collection.iterator();

				Object element = iterator.next();

				if (element instanceof String) {
					return new ArrayList<>((Collection<String>)object);
				}
			}
		}
		else if (object instanceof String) {
			return new ArrayList<>(Collections.singletonList((String)object));
		}
		else if (object instanceof String[]) {
			return new ArrayList<>(Arrays.asList((String[])object));
		}

		return new ArrayList<>();
	}

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile CommerceOrderService _commerceOrderService;

}