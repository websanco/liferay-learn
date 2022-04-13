package com.acme.g2f3.internal.notification.model.listener;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.notification.util.CommerceNotificationHelper;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ModelListener.class)
public class G2F3CommerceShipmentModelListener
	extends BaseModelListener<CommerceShipment> {

	@Override
	public void onAfterCreate(CommerceShipment commerceShipment)
		throws ModelListenerException {

		try {
			_commerceNotificationHelper.sendNotifications(
				commerceShipment.getGroupId(), commerceShipment.getUserId(),
				"g2f3", commerceShipment);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"The notification could not be processed", portalException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		G2F3CommerceShipmentModelListener.class);

	@Reference
	private CommerceNotificationHelper _commerceNotificationHelper;

}