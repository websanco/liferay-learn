package com.liferay.learn.fragments;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.fragment.contributor.BaseFragmentCollectionContributor;
import com.liferay.fragment.contributor.FragmentCollectionContributor;

/**
 * @author liferay
 */
@Component(
	service = FragmentCollectionContributor.class
)
public class FragmentsCollectionContributor extends BaseFragmentCollectionContributor {

	@Override
	public String getFragmentCollectionKey() {
	    return "Marketing Collection";
	}
	
	@Override
	public ServletContext getServletContext() {
	    return _servletContext;
	}
	
	@Reference(
    target = "(osgi.web.symbolicname=com.liferay.learn.fragments)"
	)
	
	private ServletContext _servletContext;

}