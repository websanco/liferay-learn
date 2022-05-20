package com.acme.r1s1.internal.search.similar.results.contributor;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.service.KBArticleLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.similar.results.web.spi.contributor.SimilarResultsContributor;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.CriteriaBuilder;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.CriteriaHelper;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.DestinationBuilder;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.DestinationHelper;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.RouteBuilder;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.RouteHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = SimilarResultsContributor.class)
public class R1S1SimilarResultsContributor
	implements SimilarResultsContributor {

	@Override
	public void detectRoute(
		RouteBuilder routeBuilder, RouteHelper routeHelper) {

		String[] pathParts = StringUtil.split(
			_httpComponentsUtil.getPath(routeHelper.getURLString()),
			Portal.FRIENDLY_URL_SEPARATOR);

		String[] parameters = StringUtil.split(
			pathParts[pathParts.length - 1], CharPool.FORWARD_SLASH);

		if (!parameters[0].matches("knowledge_base")) {
			throw new RuntimeException(
				"Knowledge base article was not detected");
		}

		routeBuilder.addAttribute("urlTitle", parameters[1]);
	}

	@Override
	public void resolveCriteria(
		CriteriaBuilder criteriaBuilder, CriteriaHelper criteriaHelper) {

		String urlTitle = (String)criteriaHelper.getRouteParameter("urlTitle");

		KBArticle kbArticle = _kbArticleLocalService.fetchKBArticleByUrlTitle(
			criteriaHelper.getGroupId(),
			KBFolderConstants.DEFAULT_PARENT_FOLDER_ID, urlTitle);

		if (kbArticle == null) {
			return;
		}

		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			criteriaHelper.getGroupId(), kbArticle.getUuid());

		if (assetEntry == null) {
			return;
		}

		String uidField = String.valueOf(kbArticle.getPrimaryKeyObj());

		if (ReleaseInfo.getBuildNumber() ==
				ReleaseInfo.RELEASE_7_2_10_BUILD_NUMBER) {

			uidField = String.valueOf(kbArticle.getResourcePrimKey());
		}

		criteriaBuilder.uid(Field.getUID(assetEntry.getClassName(), uidField));
	}

	@Override
	public void writeDestination(
		DestinationBuilder destinationBuilder,
		DestinationHelper destinationHelper) {

		String urlTitle = (String)destinationHelper.getRouteParameter(
			"urlTitle");

		AssetRenderer<?> assetRenderer = destinationHelper.getAssetRenderer();

		KBArticle kbArticle = (KBArticle)assetRenderer.getAssetObject();

		destinationBuilder.replace(urlTitle, kbArticle.getUrlTitle());
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private HttpComponentsUtil _httpComponentsUtil;

	@Reference
	private KBArticleLocalService _kbArticleLocalService;

}