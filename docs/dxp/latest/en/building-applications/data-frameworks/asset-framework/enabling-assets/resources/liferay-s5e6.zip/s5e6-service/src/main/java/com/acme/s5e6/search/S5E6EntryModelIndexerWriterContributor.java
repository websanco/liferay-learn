package com.acme.s5e6.search;

import com.acme.s5e6.model.S5E6Entry;
import com.acme.s5e6.service.S5E6EntryLocalService;

import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "indexer.class.name=com.acme.s5e6.model.S5E6Entry",
	service = ModelIndexerWriterContributor.class
)
public class S5E6EntryModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<S5E6Entry> {

	@Override
	public void customize(
		BatchIndexingActionable batchIndexingActionable,
		ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod(
			(S5E6Entry s5e6Entry) -> batchIndexingActionable.addDocuments(
				modelIndexerWriterDocumentHelper.getDocument(s5e6Entry)));
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return dynamicQueryBatchIndexingActionableFactory.
			getBatchIndexingActionable(
				s5e6EntryLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(S5E6Entry s5e6Entry) {
		return s5e6Entry.getCompanyId();
	}

	@Reference
	protected DynamicQueryBatchIndexingActionableFactory
		dynamicQueryBatchIndexingActionableFactory;

	@Reference
	protected S5E6EntryLocalService s5e6EntryLocalService;

}