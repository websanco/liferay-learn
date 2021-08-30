import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyCategory;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyCategoryResource;

public class TaxonomyCategory_POST_ToTaxonomyVocabulary {

	/**
	 * java -classpath .:* -DtaxonomyVocabularyId=1234 TaxonomyCategory_POST_ToTaxonomyVocabulary
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyCategoryResource.Builder builder =
			TaxonomyCategoryResource.builder();

		TaxonomyCategoryResource taxonomyCategoryResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		TaxonomyCategory taxonomyCategory =
			taxonomyCategoryResource.postTaxonomyVocabularyTaxonomyCategory(
				Long.valueOf(System.getProperty("taxonomyVocabularyId")),
				new TaxonomyCategory() {
					{
						description = "Foo";
						name = "Baker";
					}
				});

		System.out.println(taxonomyCategory);
	}

}