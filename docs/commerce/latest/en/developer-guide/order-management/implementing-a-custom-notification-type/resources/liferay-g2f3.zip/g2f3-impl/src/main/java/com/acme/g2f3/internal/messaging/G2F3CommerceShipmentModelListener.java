package com.acme.g2f3.internal.messaging;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ModelListener.class)
public class G2F3CommerceShipmentModelListener extends BaseModelListener<CommerceShipment>{

	@Override
	public void onAfterCreate(CommerceShipment commerceShipment) {
		System.out.println("New shipment created");
	}

}