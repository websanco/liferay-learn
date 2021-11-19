package com.acme.s5e6.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "indexer.class.name=com.acme.s5e6.model.S5E6Entry",
	service = ModelSummaryContributor.class
)
public class S5E6EntryModelSummaryContributor
	implements ModelSummaryContributor {

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		Summary summary = _createSummary(document);

		summary.setMaxContentLength(128);

		return summary;
	}

	private Summary _createSummary(Document document) {
		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String title = document.get(prefix + Field.TITLE, Field.CONTENT);
		String content = document.get(prefix + Field.CONTENT, Field.CONTENT);

		return new Summary(title, content);
	}

}