package com.acme.g2f3.internal.commerce.notification.type;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.notification.type.CommerceNotificationType;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"commerce.notification.type.key=g2f3",
		"commerce.notification.type.order:Integer=51"
	},
	service = CommerceNotificationType.class
)
public class G2F3ShipmentCreatedNotificationTypeImpl
	implements CommerceNotificationType {

	@Override
	public String getClassName(Object object) {
		if (!(object instanceof CommerceShipment)) {
			return null;
		}

		return CommerceShipment.class.getName();
	}

	@Override
	public long getClassPK(Object object) {
		if (!(object instanceof CommerceShipment)) {
			return 0;
		}

		CommerceShipment commerceShipment = (CommerceShipment)object;

		return commerceShipment.getPrimaryKey();
	}

	@Override
	public String getKey() {
		return "g2f3";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "g2f3-shipment-created");
	}

}