---
description: Real World Application
title: Integrate with Portal Search
order: 13
---

<h2 class="exercise">Exercises</h2>

## Integrate with Portal Search

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Declare dependencies</li>
		<li>Implement a Gradebook registrar class to register with the search framework</li>
		<li>Implement an Assignment Model Document Contributor to control which fields are indexed</li>
		<li>Implement an Assignment Model Indexer Writer Contributor to configure reindexing</li>
		<li>Implement a Gradebook Keyword Query Contributor to control which fields are being queried</li>
		<li>Implement a Gradebook Model Summary Contributor to control the Gradebook summaries returned</li>
		<li>Review the service implementation classes for <code>@Indexable</code> annotations</li>
		<li>Reindex the search index</li>
		<li>Test the application</li>
	</ul>
</div>

<br />

> Before version 7.1, there used to be a single indexer component for taking care of everything search indexer-related for an entity. The new design provides a more modular and a clean approach for controlling different aspects of search framework integration. You can still use the old approach, however.

> All the available contributors are not covered in this exercise. See the [Developer Network](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/enabling-search-and-indexing-for-guestbooks) for more information. Also, take a look at the optional Module 6 exercise "Enable Workflows for Assignments" where we will cover the `PreFilterContributor`.

<br />

#### Declare Dependencies

Integration to portal search depends on both the Search API and Search SPI:

1. **Open** the `build.gradle` in the *gradebook-service* module.
1. **Add** the new dependencies as follows:
	```groovy
	compileOnly group: "com.liferay", name: "com.liferay.portal.search.spi"
	compileOnly group: "com.liferay", name: "com.liferay.portal.search.api"
	```	

#### Implement a Gradebook Registrar Class

The registrar class registers the Assignments with the search framework:

1. **Create** a class `com.liferay.training.gradebook.internal.search.AssignmentSearchRegistrar` in the *gradebook-service* module.
1. **Implement** as follows:
	```java
	package com.liferay.training.gradebook.internal.search;
	
	import com.liferay.portal.kernel.search.Field;
	import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
	import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
	import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
	import com.liferay.training.gradebook.model.Assignment;
	
	import org.osgi.framework.BundleContext;
	import org.osgi.framework.ServiceRegistration;
	import org.osgi.service.component.annotations.Activate;
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Deactivate;
	import org.osgi.service.component.annotations.Reference;
	
	@Component(
		immediate = true
	)
	public class AssignmentSearchRegistrar {
	
		@Activate
		protected void activate(BundleContext bundleContext) {
			_serviceRegistration = modelSearchRegistrarHelper.register(
				Assignment.class, bundleContext,
				modelSearchDefinition -> {
					modelSearchDefinition.setDefaultSelectedFieldNames(
						Field.ASSET_TAG_NAMES, Field.COMPANY_ID,
						Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
						Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
						Field.UID);
					
					modelSearchDefinition.setDefaultSelectedLocalizedFieldNames(
						Field.DESCRIPTION, Field.TITLE);
					
					modelSearchDefinition.setModelIndexWriteContributor(
						modelIndexWriterContributor);
					
					modelSearchDefinition.setModelSummaryContributor(
						modelSummaryContributor);
				});
		}
	
		@Deactivate
		protected void deactivate() {
			_serviceRegistration.unregister();
		}
	
		@Reference(
			target = "(indexer.class.name=com.liferay.training.gradebook.model.Assignment)"
		)
		protected ModelIndexerWriterContributor<Assignment>
			modelIndexWriterContributor;
	
		@Reference
		protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;
	
		@Reference(
			target = "(indexer.class.name=com.liferay.training.gradebook.model.Assignment)"
		)
		protected ModelSummaryContributor modelSummaryContributor;
		
		private ServiceRegistration<?> _serviceRegistration;
	
	}
	```	

#### Implement an Assignment Model Document Contributor

The model document contributor controls which fields are indexed. This class’s contribute method is called each time the add and update methods in the entity’s service layer are called.

1. **Create** a class `com.liferay.training.gradebook.internal.search.spi.model.index.contributor.AssignmentModelDocumentContributor` in the *gradebook-service* module.
1. **Implement** as follows:
	```java
	package com.liferay.training.gradebook.internal.search.spi.model.index.contributor;
	
	import com.liferay.portal.kernel.language.LanguageUtil;
	import com.liferay.portal.kernel.search.Document;
	import com.liferay.portal.kernel.search.Field;
	import com.liferay.portal.kernel.util.HtmlUtil;
	import com.liferay.portal.kernel.util.LocaleUtil;
	import com.liferay.portal.kernel.util.LocalizationUtil;
	import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
	import com.liferay.training.gradebook.model.Assignment;
	
	import java.util.Locale;
	
	import org.osgi.service.component.annotations.Component;
	
	@Component(
		immediate = true, 
		property = "indexer.class.name=com.liferay.training.gradebook.model.Assignment", 
		service = ModelDocumentContributor.class
	)
	public class AssignmentModelDocumentContributor
		implements ModelDocumentContributor<Assignment> {
	
		@Override
		public void contribute(Document document, Assignment assignment) {
	
			// Strip HTML.
	
			String description = HtmlUtil.extractText(assignment.getDescription());
			document.addText(Field.DESCRIPTION, description);
	
			String title = HtmlUtil.extractText(assignment.getTitle());
			document.addText(Field.TITLE, title);
	
			document.addDate(Field.MODIFIED_DATE, assignment.getModifiedDate());
	
			// Handle localized fields.
	
			for (Locale locale : LanguageUtil.getAvailableLocales(
				assignment.getGroupId())) {
	
				String languageId = LocaleUtil.toLanguageId(locale);
	
				document.addText(
					LocalizationUtil.getLocalizedName(
						Field.DESCRIPTION, languageId),
					description);
				document.addText(
					LocalizationUtil.getLocalizedName(Field.TITLE, languageId),
					title);
			}
		}
	}
	```	
	
#### Implement an Assignment Model Indexer Writer Contributor

The Model Indexer Writer Contributor configures the re-indexing and batch re-indexing behavior for the model entity. This class’s method is called when a re-index is triggered from the Search administrative application found in *Control Panel → Configuration → Search*:

1. **Create** a class `com.liferay.training.gradebook.internal.search.spi.model.index.contributor.AssignmentModelIndexerWriterContributor` in the *gradebook-service* module.
1. **Implement** as follows:
	```java
	package com.liferay.training.gradebook.internal.search.spi.model.index.contributor;
	
	import com.liferay.portal.kernel.search.Document;
	import com.liferay.portal.search.batch.BatchIndexingActionable;
	import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
	import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
	import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
	import com.liferay.training.gradebook.model.Assignment;
	import com.liferay.training.gradebook.service.AssignmentLocalService;
	
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;
	
	@Component(
		immediate = true, 
		property = "indexer.class.name=com.liferay.training.gradebook.model.Assignment", 
		service = ModelIndexerWriterContributor.class
	)
	public class AssignmentModelIndexerWriterContributor
		implements ModelIndexerWriterContributor<Assignment> {
	
		@Override
		public void customize(
			BatchIndexingActionable batchIndexingActionable,
			ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {
	
			batchIndexingActionable.setPerformActionMethod(
				(Assignment assignment) -> {
					Document document =
						modelIndexerWriterDocumentHelper.getDocument(assignment);
	
					batchIndexingActionable.addDocuments(document);
				});
		}
	
		@Override
		public BatchIndexingActionable getBatchIndexingActionable() {
	
			return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
				assignmentLocalService.getIndexableActionableDynamicQuery());
		}
	
		@Override
		public long getCompanyId(Assignment assignment) {
	
			return assignment.getCompanyId();
		}
	
		@Reference
		protected AssignmentLocalService assignmentLocalService;
	
		@Reference
		protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;
	
	}
	```

#### Implement a Gradebook Keyword Query Contributor

The Keyword Query Contributor contributes model-specific clauses to the ongoing search query:

1. **Create** a class `com.liferay.training.gradebook.internal.search.spi.model.query.contributor.AssignmentKeywordQueryContributor` in the *gradebook-service* module.
1. **Implement** as follows:
	```java
	package com.liferay.training.gradebook.internal.search.spi.model.query.contributor;
	
	import com.liferay.portal.kernel.search.BooleanQuery;
	import com.liferay.portal.kernel.search.Field;
	import com.liferay.portal.kernel.search.SearchContext;
	import com.liferay.portal.search.query.QueryHelper;
	import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
	import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;
	
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;
	
	@Component(
		immediate = true,
		property = "indexer.class.name=com.liferay.training.gradebook.model.Assignment",
		service = KeywordQueryContributor.class
	)
	public class AssignmentKeywordQueryContributor
		implements KeywordQueryContributor {
	
		@Override
		public void contribute(
			String keywords, BooleanQuery booleanQuery,
			KeywordQueryContributorHelper keywordQueryContributorHelper) {
	
			SearchContext searchContext =
				keywordQueryContributorHelper.getSearchContext();
	
			queryHelper.addSearchLocalizedTerm(
				booleanQuery, searchContext, Field.DESCRIPTION, false);
			queryHelper.addSearchLocalizedTerm(
				booleanQuery, searchContext, Field.TITLE, false);
		}
	
		@Reference
		protected QueryHelper queryHelper;
	
	}
	```

####  Implement a Gradebook Model Summary Contributor

The Model Summary Contributor constructs the results summary, including specifying which fields to use:

1. **Create** a class `com.liferay.training.gradebook.internal.search.spi.model.result.contributor.AssignmentModelSummaryContributor` in the *gradebook-service* module.
1. **Implement** as follows:
	```java
	package com.liferay.training.gradebook.internal.search.spi.model.result.contributor;
	
	import com.liferay.petra.string.StringPool;
	import com.liferay.portal.kernel.search.Document;
	import com.liferay.portal.kernel.search.Field;
	import com.liferay.portal.kernel.search.Summary;
	import com.liferay.portal.kernel.util.LocaleUtil;
	import com.liferay.portal.kernel.util.LocalizationUtil;
	import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
	
	import java.util.Locale;
	
	import org.osgi.service.component.annotations.Component;
	
	@Component(
		immediate = true,
		property = "indexer.class.name=com.liferay.training.gradebook.model.Assignment",
		service = ModelSummaryContributor.class
	)
	public class AssignmentModelSummaryContributor
		implements ModelSummaryContributor {
	
		@Override
		public Summary getSummary(
			Document document, Locale locale, String snippet) {
	
			String languageId = LocaleUtil.toLanguageId(locale);
	
			return _createSummary(
				document,
				LocalizationUtil.getLocalizedName(Field.DESCRIPTION, languageId),
				LocalizationUtil.getLocalizedName(Field.TITLE, languageId));
		}
	
		private Summary _createSummary(
			Document document, String descriptionField, String titleField) {
	
			String prefix = Field.SNIPPET + StringPool.UNDERLINE;
	
			Summary summary = new Summary(
				document.get(prefix + titleField, titleField),
				document.get(prefix + descriptionField, descriptionField));
	
			summary.setMaxContentLength(200);
	
			return summary;
		}
	
	}
	```	
1. **Rebuild** the service.

#### Review the Service Implementation Classes for `@Indexable` Annotations

The final step is to review when and how indexing is triggered. Indexing is triggered by the Local Service methods annotated with the `@Indexable` annotation. If you take a look at the `com.liferay.training.gradebook.service.base.AssignmentLocalServiceBaseImpl` class, you'll see that the methods for adding, deleting, and updating Assignments are all annotated with `@Indexable`:

```java
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Assignment addAssignment(Assignment assignment) {
		assignment.setNew(true);

		return assignmentPersistence.update(assignment);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public Assignment deleteAssignment(long assignmentId)
		throws PortalException {
		return assignmentPersistence.remove(assignmentId);
	}
```

As long as our customizations and overloads of these methods in the `AssignmentLocalServiceImpl` call the base class, we don't have to add annotations to trigger indexing. If you want your custom `AssignmentLocalServiceImpl` method to trigger indexing, just annotate it with `@Indexable` and remember that an indexable method has to return the updated entity.

#### Reindex the Search Index

If you have created test Assignments, you have to reindex the search index to get the Assignments to appear on the results list:

1. **Open** *Control Panel → Configuration → Search*.
1. **Reindex** all search indexes.

#### Test the Application

1. **Use** the portal search bar to search Assignments.
1. **Create** a new Assignment and check whether it appears in the search.