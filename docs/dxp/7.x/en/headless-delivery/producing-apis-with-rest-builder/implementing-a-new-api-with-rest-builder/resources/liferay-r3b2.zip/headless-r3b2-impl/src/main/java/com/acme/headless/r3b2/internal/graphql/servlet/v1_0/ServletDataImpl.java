package com.acme.headless.r3b2.internal.graphql.servlet.v1_0;

import com.acme.headless.r3b2.internal.graphql.mutation.v1_0.Mutation;
import com.acme.headless.r3b2.internal.graphql.query.v1_0.Query;
import com.acme.headless.r3b2.resource.v1_0.BarResource;
import com.acme.headless.r3b2.resource.v1_0.FooResource;

import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Jonah the son of Amittai
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setBarResourceComponentServiceObjects(
			_barResourceComponentServiceObjects);
		Mutation.setFooResourceComponentServiceObjects(
			_fooResourceComponentServiceObjects);

		Query.setBarResourceComponentServiceObjects(
			_barResourceComponentServiceObjects);
		Query.setFooResourceComponentServiceObjects(
			_fooResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/headless-r3b2-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<BarResource>
		_barResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<FooResource>
		_fooResourceComponentServiceObjects;

}