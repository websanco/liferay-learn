package com.acme.s5e6.web.internal.asset.model;

import com.acme.s5e6.model.S5E6Entry;
import com.acme.s5e6.service.S5E6EntryLocalService;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = AssetRendererFactory.class)
public class S5E6EntryAssetRendererFactory
	extends BaseAssetRendererFactory<S5E6Entry> {

	public static final String TYPE = "s5e6Entry";

	public S5E6EntryAssetRendererFactory() {
		setClassName(S5E6Entry.class.getName());
		setLinkable(true);
		setPortletId("com_acme_s5e6_web_internal_portlet_S5E6Portlet");
		setSearchable(true);
	}

	@Override
	public AssetRenderer<S5E6Entry> getAssetRenderer(long classPK, int type)
		throws PortalException {

		S5E6EntryAssetRenderer s5e6EntryAssetRenderer =
			new S5E6EntryAssetRenderer(
				_s5e6EntryLocalService.getS5E6Entry(classPK));

		s5e6EntryAssetRenderer.setAssetRendererType(type);
		s5e6EntryAssetRenderer.setServletContext(_servletContext);

		return s5e6EntryAssetRenderer;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getTypeName(Locale locale) {
		return "s5e6Entry";
	}

	@Reference
	private S5E6EntryLocalService _s5e6EntryLocalService;

	@Reference(target = "(osgi.web.symbolicname=com.acme.s5e6.web)")
	private ServletContext _servletContext;

}