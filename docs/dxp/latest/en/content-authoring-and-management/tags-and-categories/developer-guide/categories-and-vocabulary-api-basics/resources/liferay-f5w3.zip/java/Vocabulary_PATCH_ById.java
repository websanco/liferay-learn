import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyVocabulary;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyVocabularyResource;

public class Vocabulary_PATCH_ById {

	/**
	 * java -classpath .:* -DtaxonomyVocabularyId=1234 Vocabulary_PATCH_ById
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyVocabularyResource.Builder builder =
			TaxonomyVocabularyResource.builder();

		TaxonomyVocabularyResource taxonomyVocabularyResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		TaxonomyVocabulary taxonomyVocabulary =
			taxonomyVocabularyResource.patchTaxonomyVocabulary(
				Long.valueOf(System.getProperty("taxonomyVocabularyId")),
				new TaxonomyVocabulary() {
					{
						description = "Bar";
						name = "Baker";
					}
				});

		System.out.println(taxonomyVocabulary);
	}

}