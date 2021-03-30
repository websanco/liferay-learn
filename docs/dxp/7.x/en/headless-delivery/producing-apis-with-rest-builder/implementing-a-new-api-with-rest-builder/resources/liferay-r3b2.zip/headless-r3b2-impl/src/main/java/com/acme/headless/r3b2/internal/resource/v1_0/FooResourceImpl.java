package com.acme.headless.r3b2.internal.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Foo;
import com.acme.headless.r3b2.resource.v1_0.FooResource;

import com.liferay.portal.vulcan.pagination.Page;

import java.util.Hashtable;
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
	public void deleteFoo(Long fooId) {
		_foos.remove(fooId);
	}

	@Override
	public Foo getFoo(Long fooId) {
		return _foos.get(fooId);
	}

	@Override
	public Page<Foo> getFooPage() {
		return Page.of(_foos.values());
	}

	@Override
	public Foo patchFoo(Long fooId, Foo foo) {
		Foo oldFoo = _foos.get(fooId);

		if (foo.getDescription() != null) {
			oldFoo.setDescription(foo.getDescription());
		}

		if (foo.getName() != null) {
			oldFoo.setName(foo.getName());
		}

		return oldFoo;
	}

	@Override
	public Foo postFoo(Foo foo) {
		if (_foos.containsKey(foo.getId())) {
			throw new IllegalArgumentException(
				"Duplicate foo ID " + foo.getId());
		}

		_foos.put(foo.getId(), foo);

		return foo;
	}

	@Override
	public Foo putFoo(Long fooId, Foo foo) {
		_foos.put(fooId, foo);

		return foo;
	}

	private static final Map<Long, Foo> _foos = new Hashtable<Long, Foo>() {
		{
			Foo foo1 = new Foo() {
				{
					description = "Universal truth must be transcendental.";
					id = 1L;
					name = "Truth";
				}
			};

			put(foo1.getId(), foo1);

			Foo foo2 = new Foo() {
				{
					description =
						"Beauty is guided by a transcendental aesthetic.";
					id = 2L;
					name = "Beauty";
				}
			};

			put(foo2.getId(), foo2);

			Foo foo3 = new Foo() {
				{
					description =
						"Goodness is defined transcendentally from outside " +
							"humanity.";
					id = 3L;
					name = "Goodness";
				}
			};

			put(foo3.getId(), foo3);
		}
	};

}