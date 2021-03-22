package com.acme.r3b2.foo.internal.resource.v1_0;

import com.acme.r3b2.foo.dto.v1_0.Foo;
import com.acme.r3b2.foo.resource.v1_0.FooResource;

import java.util.LinkedHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Liferay
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/foo.properties",
	scope = ServiceScope.PROTOTYPE, service = FooResource.class
)
public class FooResourceImpl extends BaseFooResourceImpl {

	@Override
	public Foo getFoo(Integer fooId) {
		if (_fooMap == null) {
			_initFooMap();
		}

		return _fooMap.get(fooId);
	}

	private void _initFooMap() {
		Foo truth = new Foo();

		truth.setFooId(Integer.valueOf(1));
		truth.setBar("Universal truth must be transcendental.");

		Foo beauty = new Foo();

		beauty.setFooId(Integer.valueOf(2));
		beauty.setBar("Beauty is guided by a transcendental aesthetic.");

		Foo goodness = new Foo();

		goodness.setFooId(Integer.valueOf(3));
		goodness.setBar(
			"Goodness is defined transcendentally from outside humanity.");

		_fooMap = new LinkedHashMap<>();

		_fooMap.put(truth.getFooId(), truth);
		_fooMap.put(beauty.getFooId(), beauty);
		_fooMap.put(goodness.getFooId(), goodness);
	}

	private LinkedHashMap<Integer, Foo> _fooMap;

}