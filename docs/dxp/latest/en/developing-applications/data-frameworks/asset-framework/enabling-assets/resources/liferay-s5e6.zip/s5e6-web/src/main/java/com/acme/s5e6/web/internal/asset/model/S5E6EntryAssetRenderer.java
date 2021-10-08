package com.acme.s5e6.web.internal.asset.model;

import com.acme.s5e6.model.S5E6Entry;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

public class S5E6EntryAssetRenderer extends BaseJSPAssetRenderer<S5E6Entry> {

	public S5E6EntryAssetRenderer(S5E6Entry s5e6Entry) {
		_s5e6Entry = s5e6Entry;
	}

	@Override
	public S5E6Entry getAssetObject() {
		return _s5e6Entry;
	}

	@Override
	public String getClassName() {
		return S5E6Entry.class.getName();
	}

	@Override
	public long getClassPK() {
		return _s5e6Entry.getS5E6EntryId();
	}

	@Override
	public long getGroupId() {
		return _s5e6Entry.getGroupId();
	}

	@Override
	public String getJspPath(
		HttpServletRequest httpServletRequest, String template) {

		return null;
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return _s5e6Entry.getDescription();
	}

	@Override
	public String getTitle(Locale locale) {
		return _s5e6Entry.getName();
	}

	@Override
	public long getUserId() {
		return _s5e6Entry.getUserId();
	}

	@Override
	public String getUserName() {
		return _s5e6Entry.getUserName();
	}

	@Override
	public String getUuid() {
		return _s5e6Entry.getUuid();
	}

	private S5E6Entry _s5e6Entry;

}