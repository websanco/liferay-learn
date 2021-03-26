package com.acme.headless.r3b2.internal.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Bar;
import com.acme.headless.r3b2.resource.v1_0.BarResource;

import com.liferay.portal.vulcan.pagination.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<Bar> bars = _getBarsByFoo(fooId);

		return Page.of(bars);
	}

	private List<Bar> _getBarsByFoo(int fooId) {
		List<Bar> bars = new ArrayList<>();

		for (Bar check : _bars) {
			if (check.getFooId() == fooId) {
				bars.add(check);
			}
		}

		return bars;
	}

	private List<Bar> _bars = Arrays.asList(
		new Bar() {
			{
				description =
					"Faith is the substance of things hoped for, the " +
						"evidence of things not seen.";
				fooId = 1;
				id = 1;
				name = "Faith";
			}
		},
		new Bar() {
			{
				description = "Lay hold of the hope set before us.";
				fooId = 2;
				id = 2;
				name = "Hope";
			}
		},
		new Bar() {
			{
				description =
					"Now abide faith, hope, love, these three; but the " +
						"greatest of these is love.";
				fooId = 3;
				id = 3;
				name = "Love";
			}
		},
		new Bar() {
			{
				description = "Enter into the joy of your master.";
				fooId = 1;
				id = 4;
				name = "Joy";
			}
		},
		new Bar() {
			{
				description = "Peace to you!";
				fooId = 2;
				id = 5;
				name = "Peace";
			}
		},
		new Bar() {
			{
				description = "Faith produces patience.";
				fooId = 3;
				id = 6;
				name = "Patience";
			}
		});

}