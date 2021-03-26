package com.acme.headless.r3b2.internal.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Foo;
import com.acme.headless.r3b2.resource.v1_0.FooResource;

import java.util.LinkedHashMap;
import java.util.Map;

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
		return _foos.get(fooId);
	}

	private Map<Integer, Foo> _foos = new LinkedHashMap<Integer, Foo>() {
		{
			Foo truth = new Foo();

			truth.setId(1);
			truth.setName("Truth");
			truth.setDescription("Universal truth must be transcendental.");

			put(truth.getId(), truth);

			Foo beauty = new Foo();

			beauty.setId(2);
			beauty.setName("Beauty");
			beauty.setDescription(
				"Beauty is guided by a transcendental aesthetic.");

			put(beauty.getId(), beauty);

			Foo goodness = new Foo();

			goodness.setId(3);
			goodness.setName("Goodness");
			goodness.setDescription(
				"Goodness is defined transcendentally from outside humanity.");

			put(goodness.getId(), goodness);
		}
	};

}