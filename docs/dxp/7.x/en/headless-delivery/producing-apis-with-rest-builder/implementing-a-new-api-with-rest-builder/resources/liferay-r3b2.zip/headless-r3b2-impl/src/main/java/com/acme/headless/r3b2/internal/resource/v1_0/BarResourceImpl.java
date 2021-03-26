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
	public Page<Bar> getFooBars(Integer fooId) {
		List<Bar> bars = new ArrayList<>();

		for (Bar bar : _bars.values()) {
			if (bar.getFooId() == fooId) {
				bars.add(bar);
			}
		}

		return Page.of(bars);
	}

	private Map<Integer, Bar> _bars = new ConcurrentHashMap<Integer, Bar>() {
		{
			Bar bar1 = new Bar() {
				{
					description = "Faith is the substance of things hoped for.";
					fooId = 1;
					id = 1;
					name = "Faith";
				}
			};

			put(bar1.getId(), bar1);

			Bar bar2 = new Bar() {
				{
					description = "Lay hold of the hope set before us.";
					fooId = 2;
					id = 2;
					name = "Hope";
				}
			};

			put(bar2.getId(), bar2);

			Bar bar3 = new Bar() {
				{
					description = "The greatest of these is love.";
					fooId = 3;
					id = 3;
					name = "Love";
				}
			};

			put(bar3.getId(), bar3);

			Bar bar4 = new Bar() {
				{
					description = "Enter into the joy of your master.";
					fooId = 1;
					id = 4;
					name = "Joy";
				}
			};

			put(bar4.getId(), bar4);

			Bar bar5 = new Bar() {
				{
					description = "Peace to you!";
					fooId = 2;
					id = 5;
					name = "Peace";
				}
			};

			put(bar5.getId(), bar5);

			Bar bar6 = new Bar() {
				{
					description = "Faith produces patience.";
					fooId = 3;
					id = 6;
					name = "Patience";
				}
			};

			put(bar6.getId(), bar6);
		}
	};

}