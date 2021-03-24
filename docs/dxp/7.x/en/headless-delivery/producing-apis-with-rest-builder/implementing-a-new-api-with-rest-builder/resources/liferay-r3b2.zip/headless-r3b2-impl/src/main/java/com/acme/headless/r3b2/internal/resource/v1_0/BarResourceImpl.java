package com.acme.headless.r3b2.internal.resource.v1_0;

import com.acme.headless.r3b2.dto.v1_0.Bar;
import com.acme.headless.r3b2.resource.v1_0.BarResource;
import com.liferay.portal.vulcan.pagination.Page;
import java.util.ArrayList;
import java.util.HashMap;

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

		if (_barList == null) {
			initBarList();
		}

		ArrayList<Bar> bars = getBarsByFoo(fooId);
		
		Page<Bar>pageBars = new Page(new HashMap(), bars);

		return pageBars;

	}
	private ArrayList<Bar> getBarsByFoo(int fooId) {
		ArrayList<Bar>bars = new ArrayList();

		for (int i=0; i<_barList.size(); i++) {
			Bar check = _barList.get(i);
			if (check.getFooId() == fooId) {
				bars.add(check);
			}
		}
		return bars;
	}
	private void initBarList() {
		Bar faith = new Bar();

		faith.setId(1);
		faith.setFooId(1);
		faith.setName("Faith");
		faith.setDescription("Faith is the substance of things hoped for, the evidence of things not seen.");

		Bar hope = new Bar();

		hope.setId(2);
		hope.setFooId(2);
		hope.setName("Hope");
		hope.setDescription("Lay hold of the hope set before us.");

		Bar love = new Bar();

		love.setId(3);
		love.setFooId(3);
		love.setName("Love");
		love.setDescription("Now abide faith, hope, love, these three; but the greatest of these is love.");

		Bar joy = new Bar();

		joy.setId(4);
		joy.setFooId(1);
		joy.setName("Joy");
		joy.setDescription("Enter into the joy of your lord.");

		Bar peace = new Bar();

		peace.setId(5);
		peace.setFooId(2);
		peace.setName("Peace");
		peace.setDescription("Peace to you!");

		Bar patience = new Bar();

		patience.setId(6);
		patience.setFooId(3);
		patience.setName("Patience");
		patience.setDescription("Faith produces patience.");

		_barList = new ArrayList();
		_barList.add(faith);
		_barList.add(hope);
		_barList.add(love);
		_barList.add(joy);
		_barList.add(peace);
		_barList.add(patience);
	}

	private ArrayList<Bar> _barList;
}