package com.acme.r3b2.product.catalog.internal.resource.v1_0;

import com.acme.r3b2.product.catalog.dto.v1_0.Product;
import com.acme.r3b2.product.catalog.resource.v1_0.ProductResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import java.util.LinkedHashMap;

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
            initProductMap();
        }

        return _productMap.get(productId);
    }

    private void initProductMap() {
        Product chair = new Product();

        chair.setProductId(new Integer(1));
        chair.setName("Folding Chair");
        chair.setPrice(new Integer(15));

        Product table = new Product();

        table.setProductId(new Integer(2));
        table.setName("Dining Table");
        table.setPrice(new Integer(200));

        Product tv = new Product();

        tv.setProductId(new Integer(3));
        tv.setName("TV");
        tv.setPrice(new Integer(500));

        _productMap = new LinkedHashMap<Integer, Product>();

        _productMap.put(chair.getProductId(), chair);
        _productMap.put(table.getProductId(), table);
        _productMap.put(tv.getProductId(), tv);
    }

    private LinkedHashMap<Integer, Product> _productMap;
}