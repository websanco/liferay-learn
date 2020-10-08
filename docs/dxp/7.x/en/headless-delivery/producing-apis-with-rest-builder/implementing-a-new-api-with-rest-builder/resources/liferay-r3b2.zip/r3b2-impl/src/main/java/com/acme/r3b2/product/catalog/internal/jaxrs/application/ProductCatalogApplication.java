package com.acme.r3b2.product.catalog.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false", "osgi.jaxrs.application.base=/product-catalog",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=R3B2.Acme.Product.Catalog"
	},
	service = Application.class
)
@Generated("")
public class ProductCatalogApplication extends Application {
}