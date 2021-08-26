import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyCategory;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyCategoryResource;

public class Category_POST_ToVocabulary {

	/**
	 * java -classpath .:* -DvocabularyId=1234 Category_POST_ToVocabulary
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyCategoryResource.Builder builder =
			TaxonomyCategoryResource.builder();

		TaxonomyCategoryResource taxonomyCategoryResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		TaxonomyCategory taxonomyCategory =
			taxonomyCategoryResource.postTaxonomyVocabularyTaxonomyCategory(
				Long.valueOf(System.getProperty("vocabularyId")),
				new TaxonomyCategory() {
					{
						description = "Foo";
						name = "Baker";
					}
				});

		System.out.println(taxonomyCategory);
	}

}