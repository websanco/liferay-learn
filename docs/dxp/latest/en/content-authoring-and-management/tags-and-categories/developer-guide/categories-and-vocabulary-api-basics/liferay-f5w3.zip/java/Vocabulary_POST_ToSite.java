import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyVocabulary;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyVocabularyResource;

public class Vocabulary_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 Vocabulary_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyVocabularyResource.Builder builder =
			TaxonomyVocabularyResource.builder();

		TaxonomyVocabularyResource taxonomyVocabularyResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		TaxonomyVocabulary taxonomyVocabulary =
			taxonomyVocabularyResource.postSiteTaxonomyVocabulary(
				Long.valueOf(System.getProperty("siteId")),
				new TaxonomyVocabulary() {
					{
						description = "Foo";
						name = "Baker";
					}
				});

		System.out.println(taxonomyVocabulary);
	}

}