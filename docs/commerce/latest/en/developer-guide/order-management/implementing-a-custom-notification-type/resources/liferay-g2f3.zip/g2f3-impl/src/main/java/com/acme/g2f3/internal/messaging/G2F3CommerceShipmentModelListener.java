package com.acme.g2f3.internal.messaging;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.commerce.notification.util.CommerceNotificationHelper;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ModelListener.class)
public class G2F3CommerceShipmentModelListener extends BaseModelListener<CommerceShipment>{

	@Override
	public void onAfterCreate(CommerceShipment commerceShipment) {
		try {
			String accountName = commerceShipment.getCommerceAccountName();
			Long groupId = commerceShipment.getGroupId();
			Long userId = commerceShipment.getUserId();
			System.out.println("New shipment created for: " + accountName);
			System.out.println("Group ID: " + groupId);
			System.out.println("User ID: " + userId);
			
			if (!(commerceShipment instanceof CommerceShipment)) {
				System.out.println("Object not of type CommerceShipment");
			}
			else {
				System.out.println("Object is of type CommerceShipment");
			}
			
			_commerceNotificationHelper.sendNotifications(groupId, userId, "shipment-created", commerceShipment);
		}
		catch(PortalException e) {
			System.out.println("Error: " + e.getMessage()); 
		}
	}

	@Reference
	private CommerceNotificationHelper _commerceNotificationHelper;
}