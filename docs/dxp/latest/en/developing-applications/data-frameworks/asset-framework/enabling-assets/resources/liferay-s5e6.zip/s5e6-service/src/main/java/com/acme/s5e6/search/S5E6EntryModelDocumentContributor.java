package com.acme.s5e6.search;

import com.acme.s5e6.model.S5E6Entry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "indexer.class.name=com.acme.s5e6.model.S5E6Entry",
	service = ModelDocumentContributor.class
)
public class S5E6EntryModelDocumentContributor
	implements ModelDocumentContributor<S5E6Entry> {

	@Override
	public void contribute(Document document, S5E6Entry s5e6Entry) {
		try {
			document.addDate(Field.MODIFIED_DATE, s5e6Entry.getModifiedDate());

			Locale defaultLocale = PortalUtil.getSiteDefaultLocale(
				s5e6Entry.getGroupId());

			String localizedTitle = LocalizationUtil.getLocalizedName(
				Field.TITLE, defaultLocale.toString());

			document.addText(localizedTitle, s5e6Entry.getName());
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to index s5e6Entry " + s5e6Entry.getS5E6EntryId(),
					portalException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		S5E6EntryModelDocumentContributor.class);

}