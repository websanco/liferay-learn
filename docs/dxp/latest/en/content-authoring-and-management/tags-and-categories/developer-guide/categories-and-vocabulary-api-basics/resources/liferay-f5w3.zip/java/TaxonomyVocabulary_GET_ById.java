import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyVocabulary;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyVocabularyResource;

public class TaxonomyVocabulary_GET_ById {

	/**
	 * java -classpath .:* -DtaxonomyVocabularyId=1234 TaxonomyVocabulary_GET_ById
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyVocabularyResource.Builder builder =
			TaxonomyVocabularyResource.builder();

		TaxonomyVocabularyResource taxonomyVocabularyResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		TaxonomyVocabulary taxonomyVocabulary =
			taxonomyVocabularyResource.getTaxonomyVocabulary(
				Long.valueOf(System.getProperty("taxonomyVocabularyId")));

		System.out.println(taxonomyVocabulary);
	}

}