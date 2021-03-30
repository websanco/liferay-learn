package com.acme.headless.r3b2.internal.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Goo;
import com.acme.headless.r3b2.resource.v1_0.GooResource;

import com.liferay.portal.vulcan.pagination.Page;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Jonah the son of Amittai
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/goo.properties",
	scope = ServiceScope.PROTOTYPE, service = GooResource.class
)
public class GooResourceImpl extends BaseGooResourceImpl {

	@Override
	public void deleteGoo(Long gooId) {
		_goos.remove(gooId);
	}

	@Override
	public Page<Goo> getFooGoosPage(Long fooId) {
		List<Goo> goos = new ArrayList<>();

		for (Goo goo : _goos.values()) {
			if (Objects.equals(fooId, goo.getFooId())) {
				goos.add(goo);
			}
		}

		return Page.of(goos);
	}

	@Override
	public Goo getGoo(Long gooId) {
		return _goos.get(gooId);
	}

	@Override
	public Goo patchGoo(Long gooId, Goo goo) {
		Goo oldGoo = _goos.get(gooId);

		if (goo.getDescription() != null) {
			oldGoo.setDescription(goo.getDescription());
		}

		if (goo.getName() != null) {
			oldGoo.setName(goo.getName());
		}

		return oldGoo;
	}

	@Override
	public Goo postFooGoo(Long fooId, Goo goo) {
		if (_goos.containsKey(goo.getId())) {
			throw new IllegalArgumentException(
				"Duplicate goo ID " + goo.getId());
		}

		goo.setFooId(fooId);

		_goos.put(goo.getId(), goo);

		return goo;
	}

	@Override
	public Goo putGoo(Long gooId, Goo goo) {
		_goos.put(gooId, goo);

		return goo;
	}

	private static final Map<Long, Goo> _goos = new Hashtable<Long, Goo>() {
		{
			Goo goo1 = new Goo() {
				{
					description = "Faith is the substance of things hoped for.";
					fooId = 1L;
					id = 1L;
					name = "Faith";
				}
			};

			put(goo1.getId(), goo1);

			Goo goo2 = new Goo() {
				{
					description = "Lay hold of the hope set before us.";
					fooId = 2L;
					id = 2L;
					name = "Hope";
				}
			};

			put(goo2.getId(), goo2);

			Goo goo3 = new Goo() {
				{
					description = "The greatest of these is love.";
					fooId = 3L;
					id = 3L;
					name = "Love";
				}
			};

			put(goo3.getId(), goo3);

			Goo goo4 = new Goo() {
				{
					description = "Enter into the joy of your master.";
					fooId = 1L;
					id = 4L;
					name = "Joy";
				}
			};

			put(goo4.getId(), goo4);

			Goo goo5 = new Goo() {
				{
					description = "Peace to you!";
					fooId = 2L;
					id = 5L;
					name = "Peace";
				}
			};

			put(goo5.getId(), goo5);

			Goo goo6 = new Goo() {
				{
					description = "Faith produces patience.";
					fooId = 3L;
					id = 6L;
					name = "Patience";
				}
			};

			put(goo6.getId(), goo6);
		}
	};

}