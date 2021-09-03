package com.acme.t2p5.service.impl;

import com.acme.t2p5.model.Entry;
import com.acme.t2p5.service.base.EntryLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=com.acme.t2p5.model.Entry",
	service = AopService.class
)
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

	public Entry addEntry(String name, String description)
		throws PortalException {

		Entry entry = createEntry(counterLocalService.increment());

		entry.setName(name);
		entry.setDescription(description);

		return addEntry(entry);
	}

}