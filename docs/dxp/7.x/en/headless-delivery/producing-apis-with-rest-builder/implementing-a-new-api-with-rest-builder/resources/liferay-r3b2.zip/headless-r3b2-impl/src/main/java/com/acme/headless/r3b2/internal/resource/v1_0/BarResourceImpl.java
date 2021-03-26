package com.acme.headless.r3b2.internal.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Bar;
import com.acme.headless.r3b2.resource.v1_0.BarResource;

import com.liferay.portal.vulcan.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Jonah the son of Amittai
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/bar.properties",
	scope = ServiceScope.PROTOTYPE, service = BarResource.class
)
public class BarResourceImpl extends BaseBarResourceImpl {

	@Override
	public void deleteBar(Long barId) {
		_bars.remove(barId);
	}

	@Override
	public Bar getBar(Long barId) {
		return _bars.get(barId);
	}

	@Override
	public Page<Bar> getFooBarsPage(Long fooId) {
		List<Bar> bars = new ArrayList<>();

		for (Bar bar : _bars.values()) {
			if (fooId.equals(bar.getFooId())) {
				bars.add(bar);
			}
		}

		return Page.of(bars);
	}

	@Override
	public Bar patchBar(Long barId, Bar bar) {
		Bar oldBar = _bars.get(barId);

		if (bar.getName() != null) {
			oldBar.setName(bar.getName());
		}

		if (bar.getDescription() != null) {
			oldBar.setDescription(bar.getDescription());
		}

		return oldBar;
	}

	@Override
	public Bar postFooBar(Long fooId, Bar bar) {
		_bars.put(bar.getId(), bar);

		return bar;
	}

	@Override
	public Bar putBar(Long barId, Bar bar) {
		_bars.put(barId, bar);

		return bar;
	}

	private static final Map<Long, Bar> _bars =
		new ConcurrentHashMap<Long, Bar>() {
			{
				Bar bar1 = new Bar() {
					{
						description =
							"Faith is the substance of things hoped for.";
						fooId = 1L;
						id = 1L;
						name = "Faith";
					}
				};

				put(bar1.getId(), bar1);

				Bar bar2 = new Bar() {
					{
						description = "Lay hold of the hope set before us.";
						fooId = 2L;
						id = 2L;
						name = "Hope";
					}
				};

				put(bar2.getId(), bar2);

				Bar bar3 = new Bar() {
					{
						description = "The greatest of these is love.";
						fooId = 3L;
						id = 3L;
						name = "Love";
					}
				};

				put(bar3.getId(), bar3);

				Bar bar4 = new Bar() {
					{
						description = "Enter into the joy of your master.";
						fooId = 1L;
						id = 4L;
						name = "Joy";
					}
				};

				put(bar4.getId(), bar4);

				Bar bar5 = new Bar() {
					{
						description = "Peace to you!";
						fooId = 2L;
						id = 5L;
						name = "Peace";
					}
				};

				put(bar5.getId(), bar5);

				Bar bar6 = new Bar() {
					{
						description = "Faith produces patience.";
						fooId = 3L;
						id = 6L;
						name = "Patience";
					}
				};

				put(bar6.getId(), bar6);
			}
		};

}