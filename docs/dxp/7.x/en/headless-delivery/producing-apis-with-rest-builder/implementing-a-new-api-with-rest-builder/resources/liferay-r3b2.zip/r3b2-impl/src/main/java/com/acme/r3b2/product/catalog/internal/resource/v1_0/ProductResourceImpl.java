package com.acme.r3b2.product.catalog.internal.resource.v1_0;

import com.acme.r3b2.product.catalog.dto.v1_0.Product;
import com.acme.r3b2.product.catalog.resource.v1_0.ProductResource;

import java.util.LinkedHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Liferay
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
public class ProductResourceImpl extends BaseProductResourceImpl {

	@Override
	public Product getProduct(Integer productId) {
		if (_productMap == null) {
			_initProductMap();
		}

		return _productMap.get(productId);
	}

	private void _initProductMap() {
		Product chair = new Product();

		chair.setProductId(Integer.valueOf(1));
		chair.setName("Folding Chair");
		chair.setPrice(Integer.valueOf(15));

		Product table = new Product();

		table.setProductId(Integer.valueOf(2));
		table.setName("Dining Table");
		table.setPrice(Integer.valueOf(200));

		Product tv = new Product();

		tv.setProductId(Integer.valueOf(3));
		tv.setName("TV");
		tv.setPrice(Integer.valueOf(500));

		_productMap = new LinkedHashMap<>();

		_productMap.put(chair.getProductId(), chair);
		_productMap.put(table.getProductId(), table);
		_productMap.put(tv.getProductId(), tv);
	}

	private LinkedHashMap<Integer, Product> _productMap;

}