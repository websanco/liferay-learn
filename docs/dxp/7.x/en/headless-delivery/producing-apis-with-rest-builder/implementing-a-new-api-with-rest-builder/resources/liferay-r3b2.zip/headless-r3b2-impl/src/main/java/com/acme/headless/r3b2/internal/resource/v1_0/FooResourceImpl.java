package com.acme.headless.r3b2.internal.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Bar;
import com.acme.headless.r3b2.dto.v1_0.Foo;
import com.acme.headless.r3b2.resource.v1_0.FooResource;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Jonah the son of Amittai
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

		truth.setId(1);
		truth.setName("Truth");
		truth.setDescription("Universal truth must be transcendental.");

		Foo beauty = new Foo();

		beauty.setId(2);
		beauty.setName("Beauty");
		beauty.setDescription("Beauty is guided by a transcendental aesthetic.");

		Foo goodness = new Foo();

		goodness.setId(3);
		goodness.setName("Goodness");
		goodness.setDescription(
			"Goodness is defined transcendentally from outside humanity.");

		_fooMap = new LinkedHashMap<>();

		_fooMap.put(truth.getId(), truth);
		_fooMap.put(beauty.getId(), beauty);
		_fooMap.put(goodness.getId(), goodness);


	}

	private LinkedHashMap<Integer, Foo> _fooMap;
	
}