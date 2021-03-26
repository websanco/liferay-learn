package com.acme.headless.r3b2.internal.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Foo;
import com.acme.headless.r3b2.resource.v1_0.FooResource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

	private Map<Integer, Foo> _foos = new ConcurrentHashMap<Integer, Foo>() {
		{
			Foo foo1 = new Foo() {
				{
					description = "Universal truth must be transcendental.";
					id = 1;
					name = "Truth";
				}
			};

			put(foo1.getId(), foo1);

			Foo foo2 = new Foo() {
				{
					description =
						"Beauty is guided by a transcendental aesthetic.";
					id = 2;
					name = "Beauty";
				}
			};

			put(foo2.getId(), foo2);

			Foo foo3 = new Foo() {
				{
					description =
						"Goodness is defined transcendentally from outside " +
							"humanity.";
					id = 3;
					name = "Goodness";
				}
			};

			put(foo3.getId(), foo3);
		}
	};

}