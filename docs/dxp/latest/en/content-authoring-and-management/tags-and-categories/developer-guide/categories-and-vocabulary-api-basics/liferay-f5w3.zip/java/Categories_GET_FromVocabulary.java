import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyCategory;
import com.liferay.headless.admin.taxonomy.client.pagination.Page;
import com.liferay.headless.admin.taxonomy.client.pagination.Pagination;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyCategoryResource;

public class Categories_GET_FromVocabulary {

	/**
	 * java -classpath .:* -DvocabularyId=1234 Categories_GET_FromVocabulary
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyCategoryResource.Builder builder =
			TaxonomyCategoryResource.builder();

		TaxonomyCategoryResource taxonomyCategoryResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<TaxonomyCategory> page =
			taxonomyCategoryResource.getTaxonomyVocabularyTaxonomyCategoriesPage(
				Long.valueOf(System.getProperty("vocabularyId")), null,
				null, Pagination.of(1, 2), null);

		System.out.println(page);
	}

}