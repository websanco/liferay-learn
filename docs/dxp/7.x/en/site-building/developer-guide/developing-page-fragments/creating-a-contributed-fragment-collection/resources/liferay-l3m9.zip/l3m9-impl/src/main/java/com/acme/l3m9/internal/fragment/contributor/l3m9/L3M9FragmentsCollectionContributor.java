package com.acme.l3m9.internal.fragment.contributor.l3m9;

import com.liferay.fragment.contributor.BaseFragmentCollectionContributor;
import com.liferay.fragment.contributor.FragmentCollectionContributor;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "fragment.collection.key=BASIC_COMPONENT",
	service = FragmentCollectionContributor.class
)
public class L3M9FragmentsCollectionContributor
	extends BaseFragmentCollectionContributor {

	@Override
	public String getFragmentCollectionKey() {
		return "L3M9_COLLECTION";
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(target = "(osgi.web.symbolicname=com.acme.l3m9.impl)")
	private ServletContext _servletContext;

}