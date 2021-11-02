package com.acme.m5x7.internal.commerce.product.data.source;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.data.source.CPDataSource;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "commerce.product.data.source.name=m5x7",
	service = CPDataSource.class
)
public class M5X7CPDataSource implements CPDataSource {

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, "products-ending-in-the-same-word");
	}

	@Override
	public String getName() {
		return "m5x7-commerce-product-data-source";
	}

	@Override
	public CPDataSourceResult getResult(
			HttpServletRequest httpServletRequest, int start, int end)
		throws Exception {

		CPCatalogEntry cpCatalogEntry =
			(CPCatalogEntry)httpServletRequest.getAttribute(
				CPWebKeys.CP_CATALOG_ENTRY);

		if (cpCatalogEntry == null) {
			return new CPDataSourceResult(new ArrayList<>(), 0);
		}

		return _cpDefinitionHelper.search(
			_portal.getScopeGroupId(httpServletRequest),
			new SearchContext() {
				{
					setAttributes(
						HashMapBuilder.<String, Serializable>put(
							Field.STATUS, WorkflowConstants.STATUS_APPROVED
						).put(
							"excludedCPDefinitionId",
							cpCatalogEntry.getCPDefinitionId()
						).build());
					setCompanyId(_portal.getCompanyId(httpServletRequest));
					setKeywords(
						StringPool.STAR + _getLastWordOfName(cpCatalogEntry));
				}
			},
			new CPQuery(), start, end);
	}

	private String _getLastWordOfName(CPCatalogEntry cpCatalogEntry)
		throws Exception {

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpCatalogEntry.getCPDefinitionId());

		String name = cpDefinition.getName();

		String[] nameParts = name.split(" ");

		return nameParts[nameParts.length - 1];
	}

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private Portal _portal;

}