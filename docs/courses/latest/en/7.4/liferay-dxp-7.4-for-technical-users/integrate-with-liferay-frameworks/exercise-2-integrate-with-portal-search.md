# Exercise 2: Integrate with Portal Search

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/FfciSpw1Ij4

> The exercise video above uses DXP 7.3. To complete the exercise using DXP/CE 7.4, follow the updated exercise steps below.

## Exercise Goals

- Declare dependencies
- Implement a Gradebook registrar class to register with the search framework
- Implement an Assignment Model Document Contributor to control which fields are indexed
- Implement an Assignment Model Indexer Writer Contributor to configure reindexing
- Implement a Gradebook Keyword Query Contributor to control which fields are being queried
- Implement a Gradebook Model Summary Contributor to control the Gradebook summaries returned
- Review the service implementation classes for `@Indexable` annotations
- Reindex the search index
- Test the application

Before version 7.1, there was a single indexer component for taking care of everything search indexer-related for an entity. The new design provides a more modular and a clean approach for controlling different aspects of search framework integration. You can still use the old approach, however.

All the available contributors are not covered in this exercise. See the [Liferay Help Center](https://help.liferay.com/hc/en-us/articles/360034199412-Enabling-Search-and-Indexing-for-Guestbooks) for more information. Also, take a look at the optional exercise "Enable Workflows for Assignments" where we will cover the `PreFilterContributor`.

## Declare Dependencies

1. **Open** the `build.gradle` in the `gradebook-service` module.
2. **Add** the new dependencies as follows:

```groovy
compileOnly group: "com.liferay", name: "com.liferay.portal.search.spi"
compileOnly group: "com.liferay", name: "com.liferay.portal.search.api"
```

The registrar class registers the Assignments with the search framework.

## Implement a Gradebook Registrar Class

1. **Create** a class `com.liferay.training.gradebook.internal.search.AssignmentSearchRegistrar` in the _gradebook-service_ module.
2. **Implement** as follows:

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

The model document contributor controls which fields are indexed. This class’s contribute method is called each time the add and update methods in the entity’s service layer are called.

## Implement an Assignment Model Document Contributor

1. **Create** a class `com.liferay.training.gradebook.internal.search.spi.model.index.contributor.AssignmentModelDocumentContributor` in the _gradebook-service_ module.
2. **Implement** as follows:

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

The Model Indexer Writer Contributor configures the re-indexing and batch re-indexing behavior for the model entity. This class's method is called when a re-index is triggered from the Search administrative application found in _Control Panel_ &rarr; _Configuration_ &rarr; _Search_.

## Implement an Assignment Model Indexer Writer Contributor

1. **Create** a class `com.liferay.training.gradebook.internal.search.spi.model.index.contributor.AssignmentModelIndexerWriterContributor` in the `gradebook-service` module.
2. **Implement** as follows:

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

The Keyword Query Contributor contributes model-specific clauses to the ongoing search query.

## Implement a Gradebook Keyword Query Contributor

1. **Create** a class `com.liferay.training.gradebook.internal.search.spi.model.query.contributor.AssignmentKeywordQueryContributor` in the `gradebook-service` module.
2. **Implement** as follows:

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

The Model Summary Contributor constructs the results summary, including specifying which fields to use.

##  Implement a Gradebook Model Summary Contributor

1. **Create** a class `com.liferay.training.gradebook.internal.search.spi.model.result.contributor.AssignmentModelSummaryContributor` in the `gradebook-service` module.
2. **Implement** as follows:

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

3. **Rebuild** the service.

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

As long as our customizations and overloads of these methods in the `AssignmentLocalServiceImpl` call the base class, we don't have to add annotations to trigger indexing. If you want your custom `AssignmentLocalServiceImpl` method to trigger indexing, annotate it with `@Indexable` and remember that an indexable method must return the updated entity.

If you have created test Assignments, you must reindex the search index to get the Assignments to appear on the results list.

## Reindex the Search Index

1. **Redeploy** the module and go to `localhost:8080` in your browser.
2. **Go to** _Global Menu_ &rarr; _Control Panel_ &rarr; _Configuration_ &rarr; _Search_.
3. **Reindex** all search indexes.

## Test the Application

1. **Use** the portal search bar to search Assignments.
2. **Create** a new Assignment and check whether it appears in the search.

---

## Previous Step

* [Integrate with the Search Framework](./integrate-with-the-search-framework.md)

## For Further Reading 

* [Asset Framework](https://learn.liferay.com/dxp/latest/en/building-applications/data-frameworks/asset-framework.html)
* [Contributing Custom Content to the Similar Results Widget](https://learn.liferay.com/dxp/latest/en/using-search/developer-guide/writing-a-similar-results-contributor.html)


