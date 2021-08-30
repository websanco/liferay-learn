import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyCategory;
import com.liferay.headless.admin.taxonomy.client.pagination.Page;
import com.liferay.headless.admin.taxonomy.client.pagination.Pagination;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyCategoryResource;

public class TaxonomyCategories_GET_FromTaxonomyVocabulary {

	/**
	 * java -classpath .:* -DtaxonomyVocabularyId=1234 TaxonomyCategories_GET_FromTaxonomyVocabulary
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyCategoryResource.Builder builder =
			TaxonomyCategoryResource.builder();

		TaxonomyCategoryResource taxonomyCategoryResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		Page<TaxonomyCategory> page =
			taxonomyCategoryResource.
				getTaxonomyVocabularyTaxonomyCategoriesPage(
					Long.valueOf(System.getProperty("taxonomyVocabularyId")),
					null, null, Pagination.of(1, 2), null);

		System.out.println(page);
	}

}